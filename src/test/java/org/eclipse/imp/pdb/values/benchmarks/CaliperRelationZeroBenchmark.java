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

import java.io.InputStream;

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;

import com.google.caliper.Param;
import com.google.caliper.Runner;

public class CaliperRelationZeroBenchmark extends RascalBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	private IRelation testRelation;
	
	@Override
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();

		try (InputStream inputStream = CaliperRelationZeroBenchmark.class.getResourceAsStream("./rsf/JHotDraw52.rsf_CALL")) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			testRelation = (IRelation) binaryReader.deserialize();
		}	
	}
	
//	public Object timeArity(int reps) {
//		int result = 0;
//		for (int r = 0; r < reps; r++) {
//			result = testRelation.arity();
//		}
//		return result;
//	}

	public Object timeClosure(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.closure();
		}
		return result;
	}
	
	public Object timeClosureStar(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.closureStar();
		}
		return result;
	}

	public Object timeCarrier(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.carrier();
		}
		return result;
	}
	
//	public Object timeFieldTypes(int reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testRelation.getFieldTypes();
//		}
//		return result;
//	}
	
	public Object timeDomain(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.domain();
		}
		return result;
	}	

	public Object timeRange(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.range();
		}
		return result;
	}	

//	public Object timeSelect(int reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testRelation.select();
//		}
//		return result;
//	}		
//	
//	public Object timeSelectByFieldNames(int reps) {
//		Object result = null;
//		for (int r = 0; r < reps; r++) {
//			result = testRelation.selectByFieldNames();
//		}
//		return result;
//	}		
	
	public static void main(String[] args) throws Exception {
		Runner.main(CaliperRelationZeroBenchmark.class, BenchmarkCaliperAsJUnit.ARGS);
	}

}