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

import com.google.caliper.Benchmark;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAEListBenchmark1 {
		
	protected IValueFactory valueFactory; 
	
	@Param
	protected BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"})
	protected int size;
	
	protected IList testList;
	protected IList testListDifferent;
	
	protected int INDEX_FRONT;
	protected int INDEX_MIDDLE;
	protected int INDEX_END;
	
	protected IValue DUMMY;
	
	@BeforeExperiment
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
	
		testListDifferent = testList.delete(INDEX_END); // ~ one smaller at the end
	}	
	
	@Test
	public void testGetElementType() {
		testList.getElementType();
	}
	
	@Benchmark
	public void timeGetElementType(long reps) {
		for (int i = 0; i < reps; i++) {
			testGetElementType();
		}
	}	
	
	@Test
	public void testLength() {
		testList.length();
	}

	@Benchmark
	public void timeLength(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.length();
		}
	}		
	
	@Test
	public void testReverse() {
		testList.reverse();
	}

	@Benchmark
	public void timeReverse(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.reverse();
		}
	}	
	
	@Test
	public void testAppend() {
		testList.append(DUMMY);
	}	

	@Benchmark
	public void timeAppend(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.append(DUMMY);
		}
	}		
		
	@Test
	public void testInsert() {
		testList.insert(DUMMY);
	}

	@Benchmark
	public void timeInsert(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.insert(DUMMY);
		}
	}		
	
	@Test
	public void testConcat() {
		testList.concat(testList);
	}
	
	@Benchmark
	public void timeConcat(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.concat(testList);
		}
	}		
		
	@Test
	public void testPutFront() {
		testList.put(INDEX_FRONT, DUMMY);
	}
	
	@Benchmark
	public void timePutFront(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_FRONT, DUMMY);
		}
	}		
		
	@Test
	public void testPutMiddle() {
		testList.put(INDEX_MIDDLE, DUMMY);
	}

	@Benchmark
	public void timePutMiddle(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_MIDDLE, DUMMY);
		}
	}			
	
	@Test
	public void testPutEnd() {
		testList.put(INDEX_END, DUMMY);
	}	
	
	@Benchmark
	public void timePutEnd(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.put(INDEX_END, DUMMY);
		}
	}	
	
	@Test
	public void testGetFront() {
		testList.get(INDEX_FRONT);
	}
	
	@Benchmark
	public void timeGetFront(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_FRONT);
		}
	}		
		
	@Test
	public void testGetMiddle() {
		testList.get(INDEX_MIDDLE);
	}

	@Benchmark
	public void timeGetMiddle(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_MIDDLE);
		}
	}			
	
	@Test
	public void testGetEnd() {
		testList.get(INDEX_END);
	}	
	
	@Benchmark
	public void timeGetEnd(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.get(INDEX_END);
		}
	}	
	
	@Test
	public void testSublist() {
		testList.sublist(INDEX_FRONT + 1, INDEX_END - 1);
	}
	
	@Benchmark
	public void timeSublist(long reps) {
		for (int i = 0; i < reps; i++) {
			testSublist();
		}		
	}

	@Test
	public void testIsEmpty() {
		testList.isEmpty();
	}

	@Benchmark
	public void timeIsEmpty(long reps) {
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
	public void testReplace() {
		// TODO
	}	
	
	@Test
	public void testIsSubListOfTrue() {
		testListDifferent.isSubListOf(testList);
	}

	@Benchmark
	public void timeIsSubListOfTrue(long reps) {
		for (int i = 0; i < reps; i++) {
			testIsSubListOfTrue();
		}
	}
	
	@Test
	public void testIsSubListOfFalse() {
		testList.isSubListOf(testListDifferent);
	}

	@Benchmark
	public void timeIsSubListOfFalse(long reps) {
		for (int i = 0; i < reps; i++) {
			testIsSubListOfFalse();
		}
	}	
	
	@Test
	public void testEquals() {
		testList.equals(testList);
	}

	@Benchmark
	public void timeEquals(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.equals(testList);
		}
	}

	@Test
	public void testEqualsEndFalse() {
		testList.equals(testListDifferent);
	}
	
	@Benchmark
	public void timeEqualsEndFalse(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.equals(testListDifferent);
		}
	}
	
	@Test
	public void testIsEqual() {
		testList.isEqual(testList);
	}

	@Benchmark
	public void timeIsEqual(long reps) {
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
	
	@Benchmark
	public void timeIteration(long reps) {
		for (int i = 0; i < reps; i++) {
			testIteration();
		}
	}	
	
	@Test
	public void testHashCode() {
		testList.hashCode();
	}

	@Benchmark
	public void timeHashCode(long reps) {
		for (int i = 0; i < reps; i++) {
			testList.hashCode();
		}
	}
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAEListBenchmark1.class, args);
	}		
	
}
