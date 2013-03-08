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
import org.junit.Ignore;
import org.junit.Test;

import com.google.caliper.Param;

public class CaliperACListBenchmark1 extends AbstractCaliperBenchmark {
		
	protected IValueFactory valueFactory; 
	
	@Param
	protected ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000"})
//	@Param({"10", "100", "1000", "10000", "100000"})
	protected int size;
	
	protected IList testList;
	protected IList testListDifferent;
	
	protected int INDEX_FRONT;
	protected int INDEX_MIDDLE;
	protected int INDEX_END;
	
	protected IValue DUMMY;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
		
		INDEX_FRONT = 0;
		INDEX_MIDDLE = testList.length() / 2;
		INDEX_END = Math.max(0, testList.length() - 1);
		
		DUMMY = valueFactory.integer(0);
	
		testListDifferent = testList.delete(INDEX_END);
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
			testList.length();
		}
	}		
	
	@Test
	public void testReverse() {
		testList.reverse();
	}

	public void timeReverse(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.reverse();
		}
	}	
	
	@Test
	public void testAppend() {
		testList.append(DUMMY);
	}	

	public void timeAppend(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.append(DUMMY);
		}
	}		
		
	@Test
	public void testInsert() {
		testList.insert(DUMMY);
	}

	public void timeInsert(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.insert(DUMMY);
		}
	}		
	
	@Test
	public void testConcat() {
		testList.concat(testList);
	}	
	
	public void timeConcat(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.concat(testList);
		}
	}		
		
	@Test
	public void testPutFront() {
		testList.put(INDEX_FRONT, DUMMY);
	}
	
	public void timePutFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_FRONT, DUMMY);
		}
	}		
		
	@Test
	public void testPutMiddle() {
		testList.put(INDEX_MIDDLE, DUMMY);
	}

	public void timePutMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_MIDDLE, DUMMY);
		}
	}			
	
	@Test
	public void testPutEnd() {
		testList.put(INDEX_END, DUMMY);
	}	
	
	public void timePutEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_END, DUMMY);
		}
	}	
	
	@Test
	public void testGetFront() {
		testList.get(INDEX_FRONT);
	}
	
	public void timeGetFront(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_FRONT);
		}
	}		
		
	@Test
	public void testGetMiddle() {
		testList.get(INDEX_MIDDLE);
	}

	public void timeGetMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_MIDDLE);
		}
	}			
	
	@Test
	public void testGetEnd() {
		testList.get(INDEX_END);
	}	
	
	public void timeGetEnd(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_END);
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
			testList.isEmpty();
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
			testList.equals(testList);
		}
	}

	@Test
	public void testEqualsEndFalse() {
		testList.equals(testListDifferent);
	}
	
	public void timeEqualsEndFalse(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.equals(testListDifferent);
		}
	}
	
	@Test
	public void testIsEqual() {
		testList.isEqual(testList);
	}
	
	public void timeIsEqual(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.isEqual(testList);
		}
	}
	
	@Test
	public void testIteration() {
		for (Iterator<IValue> iterator = testList.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unused")
			IValue value = iterator.next();
		}
	}
	
	public void timeIteration(int reps) {
		for (int i = 0; i < reps; i++) {
			testIteration();
		}
	}	
	
	@Test
	public void testHashCode() {
		testList.hashCode();
	}
	
	public void timeHashCode(int reps) {
		for (int i = 0; i < reps; i++) {
			testList.hashCode();
		}
	}
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.Runner.main(CaliperACListBenchmark1.class, args);
	}		
	
}
