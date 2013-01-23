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

import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Ignore;
import org.junit.Test;

public class ListWriterJUnitBenchmark extends AbstractJUnitBenchmark {

	static {
		AbstractJUnitBenchmark.printParameters(getTestParameters());
	}
	
	public ListWriterJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private static IList testList;	
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}	
	
	
	@Test
	public void timeInsertAt() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.insert(v);
		}
		
		writer.done();
	}	

	
	

	
	/* 
	 * FROM LIST TESTS 
	 */	
	
	@Test
	public void timeAppend() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.append(v);
		}
	}	
			
	@Test
	public void timeDeleteValueReduce() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.delete(v);
		}
	}	

	@Test
	public void timeDeleteIndexReduceFromFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
			
		while (writer.size() != 0) {
			writer.delete(0);
		}
	}	
		
	@Test
	public void timeDeleteIndexReduceFromBack() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
			
		while (writer.size() != 0) {
			writer.delete(writer.size() - 1);
		}
	}
	
	@Ignore @Test
	public void  timeProduct() {
		// TODO
	}

	@Ignore @Test
	public void  timeIntersect() {
		// TODO
	}
	
	@Ignore @Test
	public void  timeSubtract() {
		// TODO
	}
	
	@Ignore @Test
	public void  timeIsSubListOf() {
		// TODO
	}	
	
	
	

	
	/* 
	 * FROM SET WRITER TESTS 
	 */
	
	@Test
	public void timeInsert() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAll() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		writer.done();
	}
	
	@Test
	public void timeInsertIndividuallyAndAllSame() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}
		writer.insertAll(testList);
		
		writer.done();
	}	

	@Test
	public void timeInsertAllAndIndividuallySame() {
		IListWriter writer = valueFactory.listWriter();
		
		writer.insertAll(testList);
		for (IValue v : testList) {
			writer.insert(v);
		}
		
		writer.done();
	}	
		
	@Test
	public void timeDelete() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.delete(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndCheckSize() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
			writer.size();
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndDelete() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}

		for (IValue v : testList) {
			writer.delete(v);
		}
				
		writer.done();
	}

}