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

import java.util.Iterator;

import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

public class ListWriterJUnitBenchmark extends AbstractJUnitBenchmark {

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
	public void timeInsertAtOneElementFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.insertAt(0, v);
		}
		
		writer.done();
	}	

	@Test
	public void timeInsertAtOneElementMiddle() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		int offset = testList.length();
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext(); offset++) {
			IValue v = iterator.next();
			writer.insertAt(offset / 2, v);
		}
		
		writer.done();
	}		
	
	@Test
	public void timeInsertAtOneElementEnd() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);

		int offset = Math.max(0, testList.length() - 1);
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext(); offset++) {
			IValue v = iterator.next();
			writer.insertAt(offset, v);
		}
		
		writer.done();
	}		

	@Test
	public void timeReplaceAtOneElementFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.replaceAt(0, v);
		}
		
		writer.done();
	}	

	@Test
	public void timeReplaceAtOneElementMiddle() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		int offset = testList.length();
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			writer.replaceAt(offset / 2, v);
		}
		
		writer.done();
	}		
	
	@Test
	public void timeReplaceAtOneElementEnd() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);

		int offset = Math.max(0, testList.length() - 1);
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			writer.replaceAt(offset, v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeAppendIndividually() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.append(v);
		}
		
		writer.done();		
	}
	
	@Test
	public void timeAppendAll() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		writer.appendAll(testList);
		
		writer.done();
	}
	
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
	
}