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

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAESetDuplicateBenchmark {
	
	private IValueFactory valueFactory;
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	

	private ISet testSet;
	private ISet testSetRealDuplicate;	
	private ISet testSetDeltaDuplicate;	

	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		ISetWriter writer1 = valueFactory.setWriter();
		ISetWriter writer2 = valueFactory.setWriter();

		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i);
			
			writer1.insert(current);
			writer2.insert(current);
		}
		
		testSet = writer1.done();
		testSetRealDuplicate = writer2.done();
		
		final IValue VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
		testSetDeltaDuplicate = testSet.insert(VALUE_NOT_EXISTING).delete(VALUE_NOT_EXISTING);
	}
	
	@Test
	public void testEqualsRealDuplicate() {
		testSet.equals(testSetRealDuplicate);
	}
	
	public void timeEqualsRealDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetRealDuplicate);
		}
	}

	@Test
	public void testEqualsDeltaDuplicate() {
		testSet.equals(testSetDeltaDuplicate);
	}
	
	public void timeEqualsDeltaDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetDeltaDuplicate);
		}
	}
	
}
