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

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

public class SetWriterJUnitBenchmark extends AbstractJUnitBenchmark {

	private static final boolean CALLGC = true;
	private static final int BENCHMARK_ROUNDS = 10;	
	private static final int WARMUP_ROUNDS = 5;
	
	public SetWriterJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private int COUNT = 10_000;	
	
	private ISet testSet;	
	
	private ISetWriter testWriter;
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = COUNT; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();

		testWriter = valueFactory.setWriter();
		
		for (int i = COUNT; i > 0; i--) {
			testWriter.insert(valueFactory.integer(i));
		}
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}	
	
	@Test
	@BenchmarkOptions(callgc=CALLGC, clock=Clock.NANO_TIME, benchmarkRounds=BENCHMARK_ROUNDS, warmupRounds=WARMUP_ROUNDS)
	public void timeInsert() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	@Test
	@BenchmarkOptions(callgc=CALLGC, clock=Clock.NANO_TIME, benchmarkRounds=BENCHMARK_ROUNDS, warmupRounds=WARMUP_ROUNDS)
	public void timeInsertAll() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		writer.done();
	}
	
	@Test
	@BenchmarkOptions(callgc=CALLGC, clock=Clock.NANO_TIME, benchmarkRounds=BENCHMARK_ROUNDS, warmupRounds=WARMUP_ROUNDS)
	public void timeInsertIndividuallyAndAllSame() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		writer.insertAll(testSet);
		
		writer.done();
	}	

	@Test
	@BenchmarkOptions(callgc=CALLGC, clock=Clock.NANO_TIME, benchmarkRounds=BENCHMARK_ROUNDS, warmupRounds=WARMUP_ROUNDS)
	public void timeInsertAllAndIndividuallySame() {
		ISetWriter writer = valueFactory.setWriter();
		
		writer.insertAll(testSet);
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}

}