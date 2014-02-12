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

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAEMapEvenOddBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) // , "100000", "1000000"
	protected int size;	
	
	private IMap testMapEvenHalf;
	private IMap testMapOddHalf;
	
	@BeforeExperiment
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		IMapWriter writerEven = valueFactory.mapWriter();
		IMapWriter writerOdd = valueFactory.mapWriter();
		
		for (int i = size; i > 0; i--) {
			IValue current = valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i));
			
			if (i % 2 == 0) {
				writerEven.insert(current);
			} else {
				writerOdd.insert(current);
			}
		}
		
		testMapEvenHalf = writerEven.done();
		testMapOddHalf = writerOdd.done();
	}

	@Test
	public void testJoin() {
		testMapEvenHalf.join(testMapOddHalf);
	}	

	public void timeJoin(int reps) {
		for (int i = 0; i < reps; i++) {
			testJoin();
		}
	}
	
	@Test
	public void testRemove() {
		testMapEvenHalf.remove(testMapOddHalf);
	}

	public void timeRemove(int reps) {
		for (int i = 0; i < reps; i++) {
			testRemove();
		}
	}		

	@Test
	public void testCompose() {
		testMapEvenHalf.compose(testMapOddHalf);
	}
	
	public void timeCompose(int reps) {
		for (int i = 0; i < reps; i++) {
			testCompose();
		}
	}		
	
	@Test
	public void testCommon() {
		testMapEvenHalf.common(testMapOddHalf);
	}	
	
	public void timeCommon(int reps) {
		for (int i = 0; i < reps; i++) {
			testCommon();
		}
	}
	
}
