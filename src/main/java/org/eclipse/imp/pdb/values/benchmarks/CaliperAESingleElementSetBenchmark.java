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
import org.eclipse.imp.pdb.facts.IValueFactory;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;

public class CaliperAESingleElementSetBenchmark {

	private IValueFactory valueFactory; 
	
	private ISet[] singleValueSets;

	@Param({"10", "100", "1000", "10000"}) 
	int size;
	
	@Param
	public BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;
	
	@BeforeExperiment
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();
		
		singleValueSets = new ISet[size];
		for (int i = 0; i < singleValueSets.length; i++) {
			singleValueSets[i] = valueFactory.set(valueFactory.integer(i));
		}
	}
	
	public ISet timeUnionSingleElementIntegerSets(int reps) {
		ISet resultSet = null;
		
		for (int r = 0; r < reps; r++) {
			resultSet = valueFactory.set();
			for (int i = 0; i < singleValueSets.length; i++) resultSet = resultSet.union(singleValueSets[i]);
		}
		
		return resultSet;
	}
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAESingleElementSetBenchmark.class, args);
	}
	
}
