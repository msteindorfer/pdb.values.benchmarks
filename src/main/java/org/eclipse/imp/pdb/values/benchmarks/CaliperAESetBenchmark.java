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

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAESetBenchmark {
	
	private IValueFactory valueFactory;
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	

	private ISet testSet;
		
	private IValue VALUE_EXISTING;
	private IValue VALUE_NOT_EXISTING;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i);			
			writer.insert(current);
		}
		
		testSet = writer.done();
			
		VALUE_EXISTING = valueFactory.integer(size - 1);
		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
	}

	@Test
	public void testGetElementType() {
		testSet.getElementType();
	}
	
	public Object timeGetElementType(long reps) {
		Object result = null;
		for (int i = 0; i < reps; i++) {
			result = testSet.getElementType();
		}
		return result;
	}	
	
	@Test
	public void testIsEmpty() {
		testSet.isEmpty();
	}	
	
	public Object timeIsEmpty(long reps) {
		boolean result = false;
		for (long r = 0; r < reps; r++) {
			result = testSet.isEmpty();
		}
		return result;
	}

	@Test
	public void testSize() {
		testSet.size();
	}
	
	public Object timeSize(long reps) {
		Object result = null;
		for (long r = 0; r < reps; r++) {
			result = testSet.size();
		}
		return result;
	}
	
	@Test
	public void testContainsValue() {
		testSet.contains(VALUE_EXISTING);
	}
	
	public void timeContainsValue(long reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValue();
		}
	}	
	
	@Test
	public void testContainsValueNotExisting() {
		testSet.contains(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsValueNotExisting(long reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValueNotExisting();
		}
	}
	
	@Test
	public void testInsert() {
		testSet.insert(VALUE_NOT_EXISTING);
	}	
	
	public Object timeInsert(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.insert(VALUE_NOT_EXISTING);
		}
		return result;
	}

	@Test
	public void testInsertExisting() {
		testSet.insert(VALUE_EXISTING);
	}	
	
	public Object timeInsertExisting(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.insert(VALUE_EXISTING);
		}
		return result;
	}	
	
	@Test
	public void testDelete() {
		testSet.delete(VALUE_EXISTING);
	}
	
	public Object timeDelete(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.delete(VALUE_EXISTING);
		}
		return result;
	}
	
	@Test
	public void testDeleteNonExisting() {
		testSet.delete(VALUE_NOT_EXISTING);
	}	
	
	public Object timeDeleteNonExisting(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.delete(VALUE_NOT_EXISTING);
		}
		return result;
	}
	
	@Test
	public void testIteration() {
		for (Iterator<IValue> iterator = testSet.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unused")
			IValue value = iterator.next();
		}
	}
	
	public void timeIteration(long reps) {
		for (int i = 0; i < reps; i++) {
			testIteration();
		}
	}	
	
	@Test
	public void testHashCode() {
		testSet.hashCode();
	}
	
	public void timeHashCode(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.hashCode();
		}
	}

}
