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

	public Object timeGetElementType(long reps) {
		Object result = null;
		for (int i = 0; i < reps; i++) {
			result = testSet.getElementType();
		}
		return result;
	}	
	
	
	public Object timeIsEmpty(long reps) {
		boolean result = false;
		for (long r = 0; r < reps; r++) {
			result = testSet.isEmpty();
		}
		return result;
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
	
	public Object timeInsert(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.insert(VALUE_NOT_EXISTING);
		}
		return result;
	}

	public Object timeInsertExisting(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.insert(VALUE_EXISTING);
		}
		return result;
	}	
	
	public Object timeDelete(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.delete(VALUE_EXISTING);
		}
		return result;
	}
	
	public Object timeDeleteNonExisting(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.delete(VALUE_NOT_EXISTING);
		}
		return result;
	}	
	
	/* SET OPERATIONS */
	
	public Object timeUnionSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(testSet);
		}
		return result;
	}

	public Object timeUnionDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(testSetDifferent);
		}
		return result;
	}	

	public Object timeUnionSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(SET1_DISJOINT);
		}
		return result;
	}	

	public Object timeUnionSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.union(SET1_INTERRELATED);
		}
		return result;
	}	
		
	public Object timeIntersectSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(testSet);
		}
		return result;
	}	

	public Object timeIntersectDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(testSetDifferent);
		}
		return result;
	}	

	public Object timeIntersectSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(SET1_DISJOINT);
		}
		return result;
	}	

	public Object timeIntersectSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.intersect(SET1_INTERRELATED);
		}
		return result;
	}	
	
	public Object timeSubstractSelf(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(testSet);
		}
		return result;
	}		

	public Object timeSubstractDifferent(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(testSetDifferent);
		}
		return result;
	}
	
	public Object timeSubtractSet1Disjoint(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(SET1_DISJOINT);
		}
		return result;
	}	

	public Object timeSubtractSet1Interrelated(long reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.subtract(SET1_INTERRELATED);
		}
		return result;
	}	
		
//	public Object timeProductSelf(long reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testSet.product(testSet);
//		}
//		return result;
//	}
//	
//	public Object timeProductDifferent(long reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testSet.product(testSetDifferent);
//		}
//		return result;
//	}			
//	
//	public Object timeProductSet1Disjoint(long reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testSet.product(SET1_DISJOINT);
//		}
//		return result;
//	}	
//
//	public Object timeProductSet1Interrelated(long reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testSet.product(SET1_INTERRELATED);
//		}
//		return result;
//	}	

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
		testSet.equals(testSetDifferent);
	}
	
	public void timeEqualsEndFalse(long reps) {
		for (int i = 0; i < reps; i++) {
			testSet.equals(testSetDifferent);
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

//	@Test
//	public void testIsEqual() {
//		testSet.isEqual(testSet);
//	}
//	
//	public void timeIsEqual(long reps) {
//		for (int i = 0; i < reps; i++) {
//			testSet.isEqual(testSet);
//		}
//	}
	
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
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAESetBenchmark.class, args);
	}

}
