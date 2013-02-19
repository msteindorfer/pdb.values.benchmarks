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

import com.google.caliper.Param;

public class CaliperAAListBenchmark extends AbstractCaliperBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	private IList testList;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
	}
		
	@Test
	public void testGetElementType() {
		testList.getElementType();
	}

	public void timeGetElementType(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetElementType();
		}
	}	
	
	@Test
	public void testLength() {
		testList.length();
	}

	public void timeLength(int reps) {
		for (int i = 0; i < reps; i++) {
			testLength();
		}
	}		
	
	@Test
	public void testReverse() {
		testList.reverse();
	}

	public void timeReverse(int reps) {
		for (int i = 0; i < reps; i++) {
			testReverse();
		}
	}	
	
	@Test
	public void testAppend() {
		testList.append(valueFactory.integer(0));
	}	

	public void timeAppend(int reps) {
		for (int i = 0; i < reps; i++) {
			testAppend();
		}
	}		
		
	@Test
	public void testInsert() {
		testList.insert(valueFactory.integer(0));
	}

	public void timeInsert(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsert();
		}
	}		
	
	@Test
	public void testConcat() {
		testList.concat(testList);
	}	
	
	public void timeConcat(int reps) {
		for (int i = 0; i < reps; i++) {
			testConcat();
		}
	}		
		
	@Test
	public void testPutFront() {
		int index = 0;
		testList.put(index, valueFactory.integer(0));
	}
	
	public void timePutFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutFront();
		}
	}		
		
	@Test
	public void testPutMiddle() {
		int index = testList.length() / 2;
		testList.put(index, valueFactory.integer(0));
	}

	public void timePutMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutMiddle();
		}
	}			
	
	@Test
	public void testPutEnd() {
		int index = Math.max(0, testList.length() - 1);
		testList.put(index, valueFactory.integer(0));
	}	
	
	public void timePutEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutEnd();
		}
	}	
	
	@Test
	public void testGetFront() {
		int index = 0;
		testList.get(index);
	}
	
	public void timeGetFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetFront();
		}
	}		
		
	@Test
	public void testGetMiddle() {
		int index = testList.length() / 2;
		testList.get(index);
	}

	public void timeGetMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetMiddle();
		}
	}			
	
	@Test
	public void testGetEnd() {
		int index = Math.max(0, testList.length() - 1);
		testList.get(index);
	}	
	
	public void timeGetEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetEnd();
		}
	}	
	
	@Ignore @Test // TODO
	public void testSublist() {
		testList.sublist(0, 0);
	}

	@Test
	public void testIsEmpty() {
		testList.isEmpty();
	}

	public void timeIsEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEmpty();
		}
	}
	
	@Test // TODO
	public void testContains() {
		for (IValue v : testList) {
			testList.contains(v);
		}
	}	

	public void timeContains(int reps) {
		for (int i = 0; i < reps; i++) {
			testContains();
		}
	}	
	
	@Test // TODO
	public void testDeleteValueKeep() {
		for (IValue v : testList) {
			testList.delete(v);
		}
	}
	
	public void timeDeleteValueKeep(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteValueKeep();
		}
	}	

	@Test // TODO
	public void testDeleteValueReduce() {
		IList reducedList = testList;
		
		for (IValue v : testList) {
			reducedList = reducedList.delete(v);
		}
	}
	
	public void timeDeleteValueReduce(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteValueReduce();
		}
	}	

	@Test // TODO
	public void testDeleteIndexKeep() {
		for (int i = 0; i < testList.length(); i++) {
			testList.delete(i);
		}
	}
	
	public void timeDeleteIndexKeep(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexKeep();
		}
	}	

	@Test // TODO
	public void testDeleteIndexReduceFromFront() {
		IList reducedList = testList;
			
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(0);
		}
	}
	
	public void timeDeleteIndexReduceFromFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromFront();
		}
	}	
		
	@Test
	public void testDeleteIndexReduceFromBack() {
		IList reducedList = testList;
			
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(reducedList.length() - 1);
		}
	}
	
	public void timeDeleteIndexReduceFromBack(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromBack();
		}
	}	
	
	@Ignore @Test
	public void testProduct() {
		// TODO
	}

	@Ignore @Test
	public void testIntersect() {
		// TODO
	}
	
	@Ignore @Test
	public void testSubtract() {
		// TODO
	}
	
	@Ignore @Test
	public void testIsSubListOf() {
		// TODO
	}
	
	@Test
	public void testEquals() {
		testList.equals(testList);
	}
	
	public void timeEquals(int reps) {
		for (int i = 0; i < reps; i++) {
			testEquals();
		}
	}
	
	@Test
	public void testIsEqual() {
		testList.isEqual(testList);
	}
	
	public void timeIsEqual(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEqual();
		}
	}	
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.Runner.main(CaliperAAListBenchmark.class, args);
	}		
	
}
