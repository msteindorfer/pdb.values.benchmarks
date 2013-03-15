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

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.IRelationWriter;
import org.eclipse.imp.pdb.facts.IValueFactory;

import com.google.caliper.Param;

public class CaliperAERelationBenchmark extends AbstractCaliperBenchmark {
	
	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"})
	protected int size;	
	
	private IRelation testSet;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		IRelationWriter writer = valueFactory.relationWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	
//		VALUE_EXISTING = valueFactory.integer(size - 1);
//		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
//	
//		testSetDifferent = testSet.delete(VALUE_EXISTING); // ~ one smaller
//
//		testSetDuplicate = testSet.insert(VALUE_NOT_EXISTING);
//		testSetDuplicate = testSetDuplicate.delete(VALUE_NOT_EXISTING);
	}
	
	public Object timeArity(long reps) {
		int result = 0;
		for (long r = 0; r < reps; r++) {
			result = testSet.arity();
		}
		return result;
	}

	public Object timeClosure(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.closure();
		}
		return result;
	}
	
	public Object timeCompose(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.compose(testSet);
		}
		return result;
	}		
		
	public Object timeClosureStar(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.closureStar();
		}
		return result;
	}

	public Object timeCarrier(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.carrier();
		}
		return result;
	}
	
	public Object timeFieldTypes(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.getFieldTypes();
		}
		return result;
	}
	
	public Object timeDomain(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.domain();
		}
		return result;
	}	

	public Object timeRange(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.range();
		}
		return result;
	}	

	public Object timeSelect(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.select();
		}
		return result;
	}		
	
	public Object timeSelectByFieldNames(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testSet.selectByFieldNames();
		}
		return result;
	}
		
	public static void main(String[] args) throws Exception {
		com.google.caliper.Runner.main(CaliperAERelationBenchmark.class, args);
	}

}
