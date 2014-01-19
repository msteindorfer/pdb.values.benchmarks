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
	private ISet testSetEvenHalf;
	private ISet testSetOddHalf;
	
	private ISet testSetDuplicate;	
	private ISet testSetOneSmaller;	
		
	private IValue VALUE_EXISTING;
	private IValue VALUE_NOT_EXISTING;
	
	private ISet SET1_DISJOINT;
	private ISet SET1_INTERRELATED;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		ISetWriter writer1 = valueFactory.setWriter();
		ISetWriter writer2 = valueFactory.setWriter();

		ISetWriter writerEven = valueFactory.setWriter();
		ISetWriter writerOdd = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i);
			
			writer1.insert(current);
			writer2.insert(current);

			if (i % 2 == 0) {
				writerEven.insert(current);
			} else {
				writerOdd.insert();
			}
		}
		
		testSet = writer1.done();
		testSetDuplicate = writer2.done();
		
		testSetEvenHalf = writerEven.done();
		testSetOddHalf = writerOdd.done();
	
		VALUE_EXISTING = valueFactory.integer(size - 1);
		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
	
		testSetOneSmaller = testSet.delete(VALUE_EXISTING);

		// TODO: test case with small change
//		testSetDuplicate = testSet.insert(VALUE_NOT_EXISTING);
//		testSetDuplicate = testSetDuplicate.delete(VALUE_NOT_EXISTING);
		
		SET1_DISJOINT = valueFactory.set(VALUE_NOT_EXISTING);
		SET1_INTERRELATED = valueFactory.set(VALUE_EXISTING);
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
	public void testUnionSelf() {
		testSet.union(testSet);
	}	
	
	public Object timeUnionSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(testSet);
		}
		return result;
	}

	@Test
	public void testUnionOneSmaller() {
		testSet.union(testSetOneSmaller);
	}
	
	public Object timeUnionOneSmaller(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(testSetOneSmaller);
		}
		return result;
	}	
	
	@Test
	public void testUnionSet1Disjoint() {
		testSet.union(SET1_DISJOINT);
	}	
	
	public Object timeUnionSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(SET1_DISJOINT);
		}
		return result;
	}	

	@Test
	public void testUnionSet1Interrelated() {
		testSet.union(SET1_INTERRELATED);
	}	
	
	public Object timeUnionSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(SET1_INTERRELATED);
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
	public void testIntersectSelf() {
		testSet.intersect(testSet);
	}	
	
	public Object timeIntersectSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(testSet);
		}
		return result;
	}	

	@Test
	public void testIntersectDifferent() {
		testSet.intersect(testSetOneSmaller);
	}
	
	public Object timeIntersectDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(testSetOneSmaller);
		}
		return result;
	}	

	@Test
	public void testIntersectSet1Disjoint() {
		testSet.intersect(SET1_DISJOINT);
	}	
	
	public Object timeIntersectSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(SET1_DISJOINT);
		}
		return result;
	}	
	
	@Test
	public void testIntersectSet1Interrelated() {
		testSet.intersect(SET1_INTERRELATED);
	}	

	public Object timeIntersectSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(SET1_INTERRELATED);
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
	public void testSubstractSelf() {
		testSet.subtract(testSet);
	}		
	
	public Object timeSubstractSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(testSet);
		}
		return result;
	}		

	@Test
	public void testSubstractDifferent() {
		testSet.subtract(testSetOneSmaller);
	}	
	
	public Object timeSubstractDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(testSetOneSmaller);
		}
		return result;
	}
	
	@Test
	public void testSubtractSet1Disjoint() {
		testSet.subtract(SET1_DISJOINT);
	}	
	
	public Object timeSubtractSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(SET1_DISJOINT);
		}
		return result;
	}	

	@Test
	public void testSubtractSet1Interrelated() {
		testSet.subtract(SET1_INTERRELATED);
	}
	
	public Object timeSubtractSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(SET1_INTERRELATED);
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
	public void testProductSelf() {
		testSet.product(testSet);
	}	
	
	public Object timeProductSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.product(testSet);
		}
		return result;
	}
	
	@Test
	public void testProductDifferent() {
		testSet.product(testSetOneSmaller);
	}
	
	public Object timeProductDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.product(testSetOneSmaller);
		}
		return result;
	}			
	
	@Test
	public void testProductSet1Disjoint() {
		testSet.product(SET1_DISJOINT);
	}	
	
	public Object timeProductSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.product(SET1_DISJOINT);
		}
		return result;
	}	

	@Test
	public void testProductSet1Interrelated() {
		testSet.product(SET1_INTERRELATED);
	}
	
	public Object timeProductSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.product(SET1_INTERRELATED);
		}
		return result;
	}	
	
	@Test
	public void testEquals() {
		testSet.equals(testSet);
	}
	
	public void timeEquals(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSet);
		}
	}

	@Test
	public void testEqualsEndFalse() {
		testSet.equals(testSetOneSmaller);
	}
	
	public void timeEqualsEndFalse(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetOneSmaller);
		}
	}
	
	@Test
	public void testEqualsDuplicate() {
		testSet.equals(testSetDuplicate);
	}
	
	public void timeEqualsDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetDuplicate);
		}
	}

	@Test
	public void testIsEqual() {
		testSet.isEqual(testSet);
	}
	
	public void timeIsEqual(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.isEqual(testSet);
		}
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
