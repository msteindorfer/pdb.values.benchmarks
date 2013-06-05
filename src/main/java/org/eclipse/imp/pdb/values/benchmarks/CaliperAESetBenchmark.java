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

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;

public class CaliperAESetBenchmark extends AbstractCaliperBenchmark {
	
	private IValueFactory valueFactory;
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"})
	protected int size;	

	private ISet testSet;	
	private ISet testSetDuplicate;	
	private ISet testSetDifferent;	
		
	private IValue VALUE_EXISTING;
	private IValue VALUE_NOT_EXISTING;
	
	private ISet SET1_DISJOINT;
	private ISet SET1_INTERRELATED;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	
		VALUE_EXISTING = valueFactory.integer(size - 1);
		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
	
		testSetDifferent = testSet.delete(VALUE_EXISTING); // ~ one smaller

		testSetDuplicate = testSet.insert(VALUE_NOT_EXISTING);
		testSetDuplicate = testSetDuplicate.delete(VALUE_NOT_EXISTING);
		
		SET1_DISJOINT = valueFactory.set(VALUE_NOT_EXISTING);
		SET1_INTERRELATED = valueFactory.set(VALUE_EXISTING);
	}

	@Macrobenchmark
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
	
	@Macrobenchmark
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

	@Macrobenchmark
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
	
	@Macrobenchmark
	@Test
	public void testContainsValue() {
		testSet.contains(VALUE_EXISTING);
	}
	
	public void timeContainsValue(long reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValue();
		}
	}	
	
	@Macrobenchmark
	@Test
	public void testContainsValueNotExisting() {
		testSet.contains(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsValueNotExisting(long reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValueNotExisting();
		}
	}
	
	@Macrobenchmark
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

	@Macrobenchmark
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
	
	@Macrobenchmark
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
	
	@Macrobenchmark
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
	
	@Macrobenchmark
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

	@Macrobenchmark
	@Test
	public void testUnionDifferent() {
		testSet.union(testSetDifferent);
	}
	
	public Object timeUnionDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(testSetDifferent);
		}
		return result;
	}	
	
	@Macrobenchmark
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

	@Macrobenchmark
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
	
	@Macrobenchmark
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

	@Macrobenchmark
	@Test
	public void testIntersectDifferent() {
		testSet.intersect(testSetDifferent);
	}
	
	public Object timeIntersectDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(testSetDifferent);
		}
		return result;
	}	

	@Macrobenchmark
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
	
	@Macrobenchmark
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
	
	@Macrobenchmark
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

	@Macrobenchmark
	@Test
	public void testSubstractDifferent() {
		testSet.subtract(testSetDifferent);
	}	
	
	public Object timeSubstractDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(testSetDifferent);
		}
		return result;
	}
	
	@Macrobenchmark
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

	@Macrobenchmark
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

	@Macrobenchmark
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
	
	@Macrobenchmark
	@Test
	public void testProductDifferent() {
		testSet.product(testSetDifferent);
	}
	
	public Object timeProductDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.product(testSetDifferent);
		}
		return result;
	}			
	
	@Macrobenchmark
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

	@Macrobenchmark
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
	
	@Macrobenchmark
	@Test
	public void testEquals() {
		testSet.equals(testSet);
	}
	
	public void timeEquals(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSet);
		}
	}

	@Macrobenchmark
	@Test
	public void testEqualsEndFalse() {
		testSet.equals(testSetDifferent);
	}
	
	public void timeEqualsEndFalse(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetDifferent);
		}
	}
	
	@Macrobenchmark
	@Test
	public void testEqualsDuplicate() {
		testSet.equals(testSetDuplicate);
	}
	
	public void timeEqualsDuplicate(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetDuplicate);
		}
	}

	@Macrobenchmark
	@Test
	public void testIsEqual() {
		testSet.isEqual(testSet);
	}
	
	public void timeIsEqual(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.isEqual(testSet);
		}
	}
	
	@Macrobenchmark
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
	
	@Macrobenchmark
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
