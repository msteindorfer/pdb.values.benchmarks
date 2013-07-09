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
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

@BenchmarkOptions(clock = Clock.NANO_TIME)
public class SingleElementSetBenchmark extends AbstractJUnitBenchmark {
	
	public SingleElementSetBenchmark(IValueFactory valueFactory, int singleValueSetsCount) throws Exception {
		super(valueFactory);
		this.singleValueSetsCount = singleValueSetsCount;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		List<Object[]> singleValueSetsCountValues = Arrays
				.asList(new Object[][] { { 10 }, { 100 }, { 1_000 }, { 10_000 } });

		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(),
				singleValueSetsCountValues);
	}

	private int singleValueSetsCount;

	private static ISet[] singleValueSets;
	
	@Override
	public void setUp() throws Exception {	
		singleValueSets = new ISet[singleValueSetsCount];
		for (int i = 0; i < singleValueSets.length; i++) {
			singleValueSets[i] = valueFactory.set(valueFactory.integer(i));
		}
	}	

	@Test
	public void testUnionSingleElementIntegerSets() {
		unionSingleElementIntegerSets();
	}
	
	public ISet unionSingleElementIntegerSets() {
		ISet resultSet = valueFactory.set();
		
		for (int i = 0; i < singleValueSets.length; i++) {
			resultSet = resultSet.union(singleValueSets[i]);
		}
		
		return resultSet;
	}
	
}
