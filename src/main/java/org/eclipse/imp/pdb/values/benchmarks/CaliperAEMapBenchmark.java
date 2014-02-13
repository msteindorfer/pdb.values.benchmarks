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

import java.util.Iterator;

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAEMapBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	
	
	private IMap testMap;
	
	private IValue VALUE_EXISTING;
	private IValue VALUE_NOT_EXISTING;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		IMapWriter writer = valueFactory.mapWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i)));
		}
		
		testMap = writer.done();
		
		VALUE_EXISTING = valueFactory.integer(size - 1);
		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
	}
	
	@Test
	public void testGetKeyType() {
		testMap.getKeyType();
	}
	
	public void timeGetKeyType(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetKeyType();
		}
	}	

	@Test
	public void testGetValueType() {
		testMap.getValueType();
	}
	
	public void timeGetValueType(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetValueType();
		}
	}
	
	@Test
	public void testIsEmpty() {
		testMap.isEmpty();
	}

	public void timeIsEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEmpty();
		}
	}
	
	@Test
	public void testSize() {
		testMap.size();
	}

	public void timeSize(int reps) {
		for (int i = 0; i < reps; i++) {
			testSize();
		}
	}	
	
	@Test
	public void testGet() {
		for (IValue v : testMap) {
			testMap.get(v);
		}
	}

	public void timeGet(int reps) {
		for (int i = 0; i < reps; i++) {
			testGet();
		}
	}	
	
	@Test
	public void testContainsKey() {
		testMap.containsKey(VALUE_EXISTING);
	}
	
	public void timeContainsKey(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsKey();
		}
	}	

	@Test
	public void testContainsKeyNotExisting() {
		testMap.containsKey(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsKeyNotExisting(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsKeyNotExisting();
		}
	}	

	@Test
	public void testContainsValue() {
		testMap.containsValue(VALUE_EXISTING);
	}
	
	public void timeContainsValue(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValue();
		}
	}	
	
	@Test
	public void testContainsValueNotExisting() {
		testMap.containsValue(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsValueNotExisting(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValueNotExisting();
		}
	}

	@Test
	public void testIsSubMap() {
		testMap.isSubMap(testMap);
	}

	public void timeIsSubMap(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsSubMap();
		}
	}	
	
	@Test
	public void testIteration() {
		Iterator<?> iterator = testMap.iterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeIteration(int reps) {
		for (int i = 0; i < reps; i++) {
			testIteration();
		}
	}		
	
	@Test
	public void testValueIteration() {
		Iterator<?> iterator = testMap.valueIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeValueIteration(int reps) {
		for (int i = 0; i < reps; i++) {
			testValueIteration();
		}
	}		
	
	@Test
	public void testEntryIteration() {
		Iterator<?> iterator = testMap.entryIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}
		
	public void timeEntryIteration(int reps) {
		for (int i = 0; i < reps; i++) {
			testEntryIteration();
		}
	}
	
}
