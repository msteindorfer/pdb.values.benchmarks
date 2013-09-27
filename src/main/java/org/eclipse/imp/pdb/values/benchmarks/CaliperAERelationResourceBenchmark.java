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
import java.util.Iterator;
import java.util.List;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAERelationResourceBenchmark {
	
	private IValueFactory valueFactory;

	private final TypeStore typeStore = new TypeStore();

	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	private ISet testRelation;	

	@Param
	private String resource;
	
	public static List<String> resourceValues() throws IOException {
		String resourcePrefixRelativeToClass = "rsf";
		List<String> resources = new ArrayList<>();
		
		try (
				InputStream inputStream = CaliperAERelationResourceBenchmark.class.getResourceAsStream(resourcePrefixRelativeToClass + "/" + "index_CALL.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				resources.add(resourcePrefixRelativeToClass + "/" + line);
			}
			
		}
		
		return resources;
	}
	
	@BeforeExperiment
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();

		ValueUtils utils = new ValueUtils(valueFactory, typeStore);
		this.testRelation = (ISet) utils.readValueFromResource(getClass(), resource);
	}
	
	public Object timeArity(long reps) {
		int result = 0;
		for (long r = 0; r < reps; r++) {
			result = testRelation.asRelation().arity();
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
			result = testRelation.asRelation().closure();
		}
		return result;
	}
	
	public Object timeClosureStar(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.asRelation().closureStar();
		}
		return result;
	}

	public Object timeCarrier(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.asRelation().carrier();
		}
		return result;
	}
	
	public Object timeFieldTypes(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.getType().getFieldTypes();
		}
		return result;
	}
	
	public Object timeDomain(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.asRelation().domain();
		}
		return result;
	}	

	public Object timeRange(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.asRelation().range();
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
//			result = testRelation.asRelation().selectByFieldNames();
//		}
//		return result;
//	}
	
	public Object timeCompose(int reps) {
		Object result = null;
		for (int r = 0; r < reps; r++) {
			result = testRelation.asRelation().compose(testRelation.asRelation());
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
	
	@Test
	public void testIteration() {
		for (Iterator<IValue> iterator = testRelation.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unused")
			IValue value = iterator.next();
		}
	}
	
	public void timeIteration(int reps) {
		for (int i = 0; i < reps; i++) {
			testIteration();
		}
	}			
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAERelationResourceBenchmark.class, args);
	}

}
