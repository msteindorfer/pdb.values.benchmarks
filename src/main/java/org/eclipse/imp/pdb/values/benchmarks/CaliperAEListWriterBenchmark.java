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

import com.google.caliper.Param;

public class CaliperAEListWriterBenchmark extends AbstractCaliperBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;
	
	@Param({"10", "100", "1000", "10000"})
	protected int size;	
	
	private IList testList;	
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
	}
		
	@Test
	public void testInsertAtOneElementFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.insertAt(0, v);
		}
		
		writer.done();
	}	

	public void timeInsertAtOneElementFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAtOneElementFront();
		}
	}
	
	@Test
	public void testInsertAtOneElementMiddle() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		int offset = testList.length();
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext(); offset++) {
			IValue v = iterator.next();
			writer.insertAt(offset / 2, v);
		}
		
		writer.done();
	}		

	public void timeInsertAtOneElementMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAtOneElementMiddle();
		}
	}	
	
	@Test
	public void testInsertAtOneElementEnd() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);

		int offset = Math.max(0, testList.length() - 1);
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext(); offset++) {
			IValue v = iterator.next();
			writer.insertAt(offset, v);
		}
		
		writer.done();
	}		

	public void timeInsertAtOneElementEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAtOneElementEnd();
		}
	}
		
	@Test
	public void testReplaceAtOneElementFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.replaceAt(0, v);
		}
		
		writer.done();
	}
	
	public void timeReplaceAtOneElementFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testReplaceAtOneElementFront();
		}
	}
	
	@Test
	public void testReplaceAtOneElementMiddle() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		int offset = testList.length();
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			writer.replaceAt(offset / 2, v);
		}
		
		writer.done();
	}		
	
	public void timeReplaceAtOneElementMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testReplaceAtOneElementMiddle();
		}
	}	
	
	@Test
	public void testReplaceAtOneElementEnd() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);

		int offset = Math.max(0, testList.length() - 1);
		
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			writer.replaceAt(offset, v);
		}
		
		writer.done();
	}

	public void timeReplaceAtOneElementEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testReplaceAtOneElementEnd();
		}
	}	
	
	@Test
	public void testAppendIndividually() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.append(v);
		}
		
		writer.done();		
	}

	public void timeAppendIndividually(int reps) {
		for (int i = 0; i < reps; i++) {
			testAppendIndividually();
		}
	}
	
	@Test
	public void testAppendAll() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		writer.appendAll(testList);
		
		writer.done();
	}	
	
	public void timeAppendAll(int reps) {
		for (int i = 0; i < reps; i++) {
			testAppendAll();
		}
	}	
			
	@Test
	public void testDeleteValueReduce() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.delete(v);
		}
		
		writer.done();		
	}	

	public void timeDeleteValueReduce(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteValueReduce();
		}
	}	
	
	@Test
	public void testDeleteIndexReduceFromFront() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
			
		while (writer.size() != 0) {
			writer.delete(0);
		}
		
		writer.done();		
	}
	
	public void timeDeleteIndexReduceFromFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromFront();
		}
	}	
	
	@Test
	public void testDeleteIndexReduceFromBack() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
			
		while (writer.size() != 0) {
			writer.delete(writer.size() - 1);
		}
		
		writer.done();		
	}
	
	public void timeDeleteIndexReduceFromBack(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromBack();
		}
	}	
	
	@Test
	public void testInsert() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	public void timeInsert(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsert();
		}
	}	
	
	@Test
	public void testInsertAll() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		writer.done();
	}

	public void timeInsertAll(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAll();
		}
	}	
	
	@Test
	public void testInsertIndividuallyAndAllSame() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}
		writer.insertAll(testList);
		
		writer.done();
	}
	
	public void timeInsertIndividuallyAndAllSame(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertIndividuallyAndAllSame();
		}
	}	

	@Test
	public void testInsertAllAndIndividuallySame() {
		IListWriter writer = valueFactory.listWriter();
		
		writer.insertAll(testList);
		for (IValue v : testList) {
			writer.insert(v);
		}
		
		writer.done();
	}	

	public void timeInsertAllAndIndividuallySame(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAllAndIndividuallySame();
		}
	}		
	
	@Test
	public void testDelete() {
		IListWriter writer = valueFactory.listWriter();
		writer.insertAll(testList);
		
		for (IValue v : testList) {
			writer.delete(v);
		}
		
		writer.done();
	}
	
	public void timeDelete(int reps) {
		for (int i = 0; i < reps; i++) {
			testDelete();
		}
	}		
	
	@Test
	public void testInsertAndCheckSize() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
			writer.size();
		}
		
		writer.done();
	}
	
	public void timeInsertAndCheckSize(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAndCheckSize();
		}
	}			
	
	@Test
	public void testInsertAndDelete() {
		IListWriter writer = valueFactory.listWriter();
		
		for (IValue v : testList) {
			writer.insert(v);
		}

		for (IValue v : testList) {
			writer.delete(v);
		}
				
		writer.done();
	}
	
	public void timeInsertAndDelete(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAndDelete();
		}
	}	

	public static void main(String[] args) throws Exception {
		com.google.caliper.Runner.main(CaliperAEListWriterBenchmark.class, args);
	}	
	
}