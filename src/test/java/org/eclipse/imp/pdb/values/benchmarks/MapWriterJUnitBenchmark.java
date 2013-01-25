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

public class MapWriterJUnitBenchmark extends AbstractJUnitBenchmark {

	static {
		AbstractJUnitBenchmark.printParameters(getTestParameters());
	}
	
	public MapWriterJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private static IMap testMap;	
	private static Iterable<ITuple> testTuples;
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		IMapWriter writer = valueFactory.mapWriter();
		Collection<ITuple> newTuples = new java.util.LinkedList<>();
		
		for (int i = 10_000; i > 0; i--) {
			ITuple newTuple = valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i));
			writer.insert(newTuple);
			newTuples.add(newTuple);
		}
		
		testMap = writer.done();
		testTuples = Collections.unmodifiableCollection(newTuples);
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}	
	
	
	@Test
	public void timeInsertAt() {
		IMapWriter writer = valueFactory.mapWriter();
		writer.insertAll(testTuples);
		
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		
		writer.done();
	}	
	
	@Test
	public void timeInsert() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAll() {
		IMapWriter writer = valueFactory.mapWriter();
		writer.insertAll(testTuples);
		writer.done();
	}
	
	@Test
	public void timeInsertIndividuallyAndAllSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		writer.insertAll(testTuples);
		
		writer.done();
	}	

	@Test
	public void timeInsertAllAndIndividuallySame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		writer.insertAll(testTuples);
		for (IValue v : testMap) {
			writer.insert(valueFactory.tuple(v, testMap.get(v)));
		}
		
		writer.done();
	}
	
	@Test
	public void timePut() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		
		writer.done();
	}
	
	@Test
	public void timePutAll() {
		IMapWriter writer = valueFactory.mapWriter();
		writer.putAll(testMap);
		writer.done();
	}
	
	@Test
	public void timePutIndividuallyAndAllSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		writer.putAll(testMap);
		
		writer.done();
	}	

	@Test
	public void timePutAllIndividuallyAndSame() {
		IMapWriter writer = valueFactory.mapWriter();
		
		writer.putAll(testMap);
		for (IValue v : testMap) {
			writer.put(v, testMap.get(v));
		}
		
		writer.done();
	}
	
	@Ignore @Test
	public void timePutAllFromJavaMap() {
	}
	
	@Ignore @Test
	public void timePutIndividuallyAndAllSameFromJavaMap() {
	}	

	@Ignore @Test
	public void timePutAllIndividuallyAndSameFromJavaMap() {
	}		

}