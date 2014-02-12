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

public class CaliperAESetEvenOddBenchmark {
	
	private IValueFactory valueFactory;
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	

	private ISet testSetEvenHalf;
	private ISet testSetOddHalf;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();

		ISetWriter writerEven = valueFactory.setWriter();
		ISetWriter writerOdd = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i);
			
			if (i % 2 == 0) {
				writerEven.insert(current);
			} else {
				writerOdd.insert();
			}
		}
		
		testSetEvenHalf = writerEven.done();
		testSetOddHalf = writerOdd.done();
	}
	
	/* SET OPERATIONS */

	@Test
	public void testUnionEvenOdd() {
		testSetEvenHalf.union(testSetOddHalf);
	}	
	
	public Object timeUnionEvenOdd(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSetEvenHalf.union(testSetOddHalf);
		}
		return result;
	}		
	
	@Test
	public void testIntersectEvenOdd() {
		testSetEvenHalf.intersect(testSetOddHalf);
	}	
	
	public Object timeIntersectEvenOdd(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSetEvenHalf.intersect(testSetOddHalf);
		}
		return result;
	}
		
	@Test
	public void testSubtractEvenOdd() {
		testSetEvenHalf.subtract(testSetOddHalf);
	}	
	
	public Object timeSubtractEvenOdd(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSetEvenHalf.subtract(testSetOddHalf);
		}
		return result;
	}	
	
	@Test
	public void testProductEvenOdd() {
		testSetEvenHalf.product(testSetOddHalf);
	}	
	
	public Object timeProductEvenOdd(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSetEvenHalf.product(testSetOddHalf);
		}
		return result;
	}
	
	@Test
	public void testEqualsEvenOdd() {
		testSetEvenHalf.equals(testSetOddHalf);
	}
	
	public void timeEqualsEvenOdd(long reps) {
		for (int i = 0; i < reps; i++) {
			testSetEvenHalf.equals(testSetOddHalf);
		}
	}

	@Test
	public void testIsEqualEvenOdd() {
		testSetEvenHalf.isEqual(testSetOddHalf);
	}
	
	public void timeIsEqualEvenOdd(long reps) {
		for (int i = 0; i < reps; i++) {
			testSetEvenHalf.isEqual(testSetOddHalf);
		}
	}

}
