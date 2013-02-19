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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.junit.Test;

import com.google.caliper.Param;

public class CaliperAARelationBenchmark extends AbstractCaliperBenchmark {
	
	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	private IRelation testRelation;	

	@Param
	private String resource;
	
	public static List<String> resourceValues() throws IOException {
		String resourcePrefixRelativeToClass = "rsf";
		List<String> resources = new ArrayList<>();
		
		try (
				InputStream inputStream = CaliperAARelationBenchmark.class.getResourceAsStream(resourcePrefixRelativeToClass + "/" + "index_CALL.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				resources.add(resourcePrefixRelativeToClass + "/" + line);
			}
			
		}
		
		return resources;
	}
	
	@Override
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();

		try (InputStream inputStream = CaliperAARelationBenchmark.class.getResourceAsStream(resource)) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			testRelation = (IRelation) binaryReader.deserialize();
		}
	}
	
	public Object timeArity(long reps) {
		int result = 0;
		for (long r = 0; r < reps; r++) {
			result = testRelation.arity();
		}
		return result;
	}

	public Object timeSize(long reps) {
		Object result = null;
		for (long r = 0; r < reps; r++) {
			result = testRelation.size();
		}
		return result;
	}	
	
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
	
	public Object timeFieldTypes(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.getFieldTypes();
		}
		return result;
	}
	
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
	
	public Object timeCompose(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.compose(testRelation);
		}
		return result;
	}		

	
	/* SET OPERATIONS */
	
	public Object timeUnion(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.union(testRelation);
		}
		return result;
	}		
	
	public Object timeIntersect(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.intersect(testRelation);
		}
		return result;
	}	
	
	public Object timeSubstract(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.subtract(testRelation);
		}
		return result;
	}		
	
	public Object timeProduct(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.product(testRelation);
		}
		return result;
	}		

	@Test
	public void testEquals() {
		testRelation.equals(testRelation);
	}
	
	public void timeEquals(int reps) {
		for (int i = 0; i < reps; i++) {
			testEquals();
		}
	}
	
	@Test
	public void testIsEqual() {
		testRelation.isEqual(testRelation);
	}
	
	public void timeIsEqual(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEqual();
		}
	}		
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.Runner.main(CaliperAARelationBenchmark.class, args);
	}

}
