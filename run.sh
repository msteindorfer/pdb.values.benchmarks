#export ALLOCATION_JAR=~/.m2/repository/com/google/code/java-allocation-instrumenter/java-allocation-instrumenter/2.1/java-allocation-instrumenter-2.1.jar
export BENCHMARK_JAR=target/org.eclipse.imp.pdb.values.benchmarks-0.1.0-SNAPSHOT.jar

export BENCHMARK_RUNNER=com.google.caliper.runner.CaliperMain
export JAVA_ARGS='-Xmx12g' #  -Xdebug -Xrunjdwp:transport=dt_socket,address=8001,server=y,suspend=y'
# Options for enabling FlightRecoreder and memory tracking features.
# See http://hirt.se/blog/?p=401 and other posts on same site.
#export JAVA_ARGS='-Xmx4g -XX:NativeMemoryTracking=detail -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -XX:-AutoShutdownNMT'
#export JAVA_ARGS='-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,settings=custom' 

export OBJECT_EXPLORER_JAR=~/.m2/repository/com/google/memory-measurer/1.0-SNAPSHOT/memory-measurer-1.0-SNAPSHOT.jar
# NOTE: run with --dry-run to experiment with instrumentation (i.e. doesn't spwawn new JVM instance)
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEModelAggregationBenchmark $*

# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEValueFactoryBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetDuplicateBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetEvenOddBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterBenchmark $*
# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESetWriterSpecificBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEModelAggregationBenchmark $*
# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAESingleElementSetBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAERelationResourceBenchmark $*
java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapDuplicateBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapEvenOddBenchmark $*
# java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEMapWriterBenchmark $*
# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark1 $*
# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListBenchmark2 $*
# # java $JAVA_ARGS -javaagent:$OBJECT_EXPLORER_JAR -cp $BENCHMARK_JAR $BENCHMARK_RUNNER org.eclipse.imp.pdb.values.benchmarks.CaliperAEListWriterBenchmark $*
