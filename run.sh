#export ALLOCATION_JAR=~/.m2/repository/com/google/code/java-allocation-instrumenter/java-allocation-instrumenter/2.1/java-allocation-instrumenter-2.1.jar
export ALLOCATION_JAR=~/Temporary/allocation.jar
export BENCHMARK_JAR=target/org.eclipse.imp.pdb.values.benchmarks-0.0.1-SNAPSHOT.jar
#export BENCHMARK_RUNNER=com.google.caliper.Runner

export OBJECT_EXPLORER_JAR=~/Temporary/object-explorer.jar

# leave empty (instead of YES) for using new API
export USE_LEGACY_CALIPER=YES

#java -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapBenchmark $*

java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEValueFactoryBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterSpecificBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEModelAggregationBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESingleElementSetBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationResourceBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapWriterBenchmark $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark1 $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark2 $*
java -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListWriterBenchmark $*
