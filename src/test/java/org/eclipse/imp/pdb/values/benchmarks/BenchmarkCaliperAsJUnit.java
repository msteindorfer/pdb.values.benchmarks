package org.eclipse.imp.pdb.values.benchmarks;

import org.junit.Test;

public class BenchmarkCaliperAsJUnit {

	@Test
	public void runCaliperBenchmarks() {
		com.google.caliper.Runner.main(CaliperModelAggregationBenchmark.class, new String[]{"-Jmemory=-Xmx8g", "--measureMemory", "--saveResults", "output.txt"});
	}

}
