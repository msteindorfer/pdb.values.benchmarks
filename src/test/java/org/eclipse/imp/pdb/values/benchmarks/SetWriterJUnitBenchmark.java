/*******************************************************************************
 * Copyright (c) 2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *   * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI  
 *******************************************************************************/
package org.eclipse.imp.pdb.values.benchmarks;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

public class SetWriterJUnitBenchmark extends AbstractJUnitBenchmark {

	static {
		AbstractJUnitBenchmark.printParameters(getTestParameters());
	}
	
	public SetWriterJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private ISet testSet;	
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}	
	
	@Test
	public void timeInsert() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAll() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		writer.done();
	}
	
	@Test
	public void timeInsertIndividuallyAndAllSame() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		writer.insertAll(testSet);
		
		writer.done();
	}	

	@Test
	public void timeInsertAllAndIndividuallySame() {
		ISetWriter writer = valueFactory.setWriter();
		
		writer.insertAll(testSet);
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}	
		
	@Test
	public void timeDelete() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		
		for (IValue v : testSet) {
			writer.delete(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndCheckSize() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
			writer.size();
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndDelete() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}

		for (IValue v : testSet) {
			writer.delete(v);
		}
				
		writer.done();
	}

}