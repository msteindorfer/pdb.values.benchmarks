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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Ignore;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAEMapWriterBenchmark {
	
	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"})
	protected int size;	
	
	private static IMap testMap;	
	private static Iterable<ITuple> testTuples;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IMapWriter writer = valueFactory.mapWriter();
		Collection<ITuple> newTuples = new java.util.LinkedList<>();
		
		for (int i = size; i > 0; i--) {
			ITuple newTuple = valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i));
			writer.insert(newTuple);
			newTuples.add(newTuple);
		}
		
		testMap = writer.done();
		testTuples = Collections.unmodifiableCollection(newTuples);
	}

	@Test
	public void testInsert() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		
		writer.done();
	}
	
	public void timeInsert(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsert();
		}
	}
	
	@Test
	public void testInsertAll() {
		IMapWriter writer = valueFactory.mapWriter();
		writer.insertAll(testTuples);
		writer.done();
	}

	public void timeInsertAll(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAll();
		}
	}
		
	@Test
	public void testInsertIndividuallyAndAllSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		writer.insertAll(testTuples);
		
		writer.done();
	}	

	public void timeInsertIndividuallyAndAllSame(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertIndividuallyAndAllSame();
		}
	}	
	
	@Test
	public void testInsertAllAndIndividuallySame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		writer.insertAll(testTuples);
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		
		writer.done();
	}
	
	public void timeInsertAllAndIndividuallySame(int reps) {
		for (int i = 0; i < reps; i++) {
			testInsertAllAndIndividuallySame();
		}
	}
		
	@Test
	public void testPut() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		
		writer.done();
	}
	
	public void timePut(int reps) {
		for (int i = 0; i < reps; i++) {
			testPut();
		}
	}
		
	@Test
	public void testPutAll() {
		IMapWriter writer = valueFactory.mapWriter();
		writer.putAll(testMap);
		writer.done();
	}

	public void timePutAll(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutAll();
		}
	}
		
	@Test
	public void testPutIndividuallyAndAllSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		writer.putAll(testMap);
		
		writer.done();
	}	

	public void timePutIndividuallyAndAllSame(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutIndividuallyAndAllSame();
		}
	}
	
	@Test
	public void testPutAllIndividuallyAndSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		writer.putAll(testMap);
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		
		writer.done();
	}
	
	public void timePutAllIndividuallyAndSame(int reps) {
		for (int i = 0; i < reps; i++) {
			testPutAllIndividuallyAndSame();
		}
	}
		
	@Ignore @Test
	public void testPutAllFromJavaMap() {
	}
	
	@Ignore @Test
	public void testPutIndividuallyAndAllSameFromJavaMap() {
	}	

	@Ignore @Test
	public void testPutAllIndividuallyAndSameFromJavaMap() {
	}		

	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAEMapWriterBenchmark.class, args);
	}	
	
}
