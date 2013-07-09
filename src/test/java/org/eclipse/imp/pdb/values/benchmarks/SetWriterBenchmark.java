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

import java.util.Arrays;
import java.util.List;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

@BenchmarkOptions(clock = Clock.NANO_TIME)
public class SetWriterBenchmark extends AbstractJUnitBenchmark {
	
	public SetWriterBenchmark(IValueFactory valueFactory, int size) throws Exception {
		super(valueFactory);
		this.size = size;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(), getSizeParameters());
	}
	
	public static List<Object[]> getSizeParameters() {
		return Arrays.asList(new Object[][] { { 10_000 }, { 100_000 }, { 1_000_000 } });
	}		
	
//	protected IValueFactory valueFactory; 
	protected int size;	
	
	private ISet testSet;	
		
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	}
		
	@Test
	public void timeInsert() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAll() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		writer.done();
	}
	
	@Test
	public void timeInsertIndividuallyAndAllSame() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		writer.insertAll(testSet);
		
		writer.done();
	}	

	@Test
	public void timeInsertAllAndIndividuallySame() {
		ISetWriter writer = valueFactory.setWriter();
		
		writer.insertAll(testSet);
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}

}