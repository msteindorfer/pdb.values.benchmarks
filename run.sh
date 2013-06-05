#export ALLOCATION_JAR=~/.m2/repository/com/google/code/java-allocation-instrumenter/java-allocation-instrumenter/2.1/java-allocation-instrumenter-2.1.jar
export ALLOCATION_JAR=~/Temporary/allocation.jar
export BENCHMARK_JAR=target/org.eclipse.imp.pdb.values.benchmarks-0.0.1-SNAPSHOT.jar

export BENCHMARK_RUNNER=com.google.caliper.runner.CaliperMain
export JAVA_ARGS=-Xmx8g

export OBJECT_EXPLORER_JAR=~/Temporary/object-explorer.jar

java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetBenchmark $*

# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEValueFactoryBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterSpecificBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEModelAggregationBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESingleElementSetBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationResourceBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapWriterBenchmark $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark1 $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark2 $*
# java $JAVA_ARGS -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListWriterBenchmark $*
