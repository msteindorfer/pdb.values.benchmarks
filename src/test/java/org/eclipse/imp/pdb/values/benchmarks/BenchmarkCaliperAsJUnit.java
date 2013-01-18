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

import org.junit.Test;

public class BenchmarkCaliperAsJUnit {

	public static String[] ARGS = new String[]{"-Jmemory=-Xmx8g", "--measureMemory"};	
	
	@Test
	public void runCaliperBenchmarks() {
		com.google.caliper.Runner.main(CaliperRelationBenchmark.class, ARGS);
		com.google.caliper.Runner.main(CaliperModelAggregationBenchmark.class, ARGS);
	}

}
