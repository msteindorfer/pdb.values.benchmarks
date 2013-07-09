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

import java.util.Arrays;
import java.util.List;

import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

@BenchmarkOptions(clock = Clock.NANO_TIME)
public class ListBenchmark2 extends AbstractJUnitBenchmark {
	
	public ListBenchmark2(IValueFactory valueFactory, int size) throws Exception {
		super(valueFactory);
		this.size = size;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(), getSizeParameters());
	}
	
	public static List<Object[]> getSizeParameters() {
		return Arrays.asList(new Object[][] { { 100 }, { 1_000 }, { 10_000 } });
	}			
	
//	protected IValueFactory valueFactory; 
	protected int size;
	
	protected IList testList;
	
	@Override
	public void setUp() throws Exception {		
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
	}
	
	@Test
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
	
	@Test
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

	@Test
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

	@Test
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

	@Test
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
	public void testDeleteIndexReduceFromMiddle() {
		IList reducedList = testList;
		int length = testList.length();
		
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(length / 2);
			length = length - 1;
		}
	}
	
	public void timeDeleteIndexReduceFromMiddle(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromMiddle();
		}
	}	
	
	@Test
	public void testDeleteIndexReduceFromBack() {
		IList reducedList = testList;
		int length = testList.length();
			
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(length - 1);
			length = length - 1;
		}
	}
	
	public void timeDeleteIndexReduceFromBack(int reps) {
		for (int i = 0; i < reps; i++) {
			testDeleteIndexReduceFromBack();
		}
	}
	
}
