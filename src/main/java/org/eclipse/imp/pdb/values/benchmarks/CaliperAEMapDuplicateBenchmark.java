/*******************************************************************************
 * Copyright (c) 2014 CWI
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

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAEMapDuplicateBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	
	
	private IMap testMap;
	private IMap testMapRealDuplicate;	
	private IMap testMapDeltaDuplicate;	
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		IMapWriter writer1 = valueFactory.mapWriter();
		IMapWriter writer2 = valueFactory.mapWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i));
			
			writer1.insert(current);
			writer2.insert(current);
		}
		
		testMap = writer1.done();
		testMapRealDuplicate = writer2.done();

		final IValue VALUE_EXISTING = valueFactory.integer(size - 1);
		final IValue VALUE_NOT_EXISTING = valueFactory.integer(size + 1);		
		testMapDeltaDuplicate = testMap.put(VALUE_EXISTING, VALUE_NOT_EXISTING).put(VALUE_EXISTING,
						VALUE_EXISTING);
	}
	
	@Test
	public void testEqualsRealDuplicate() {
		testMap.equals(testMapRealDuplicate);
	}
	
	public void timeEqualsRealDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testMap.equals(testMapRealDuplicate);
		}
	}

	@Test
	public void testEqualsDeltaDuplicate() {
		testMap.equals(testMapDeltaDuplicate);
	}
	
	public void timeEqualsDeltaDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testMap.equals(testMapDeltaDuplicate);
		}
	}

	@Test
	public void testIsEqualRealDuplicate() {
		testMap.isEqual(testMapRealDuplicate);
	}
	
	public void timeIsEqualRealDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testMap.isEqual(testMapRealDuplicate);
		}
	}

	@Test
	public void testIsEqualDeltaDuplicate() {
		testMap.isEqual(testMapDeltaDuplicate);
	}
	
	public void timeIsEqualDeltaDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testMap.isEqual(testMapDeltaDuplicate);
		}
	}
	
}
