package org.eclipse.imp.pdb.values.benchmarks;

import java.util.Iterator;

import com.google.caliper.legacy.Benchmark;
import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.Param;

public class CaliperAEMapBenchmark extends Benchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"})
	protected int size;	
	
	private IMap testMap;
	
	private IValue VALUE_EXISTING;
	
	private IValue VALUE_NOT_EXISTING;
	
	// TODO: parameterize by map size
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IMapWriter writer = valueFactory.mapWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i)));
		}
		
		testMap = writer.done();
		
		VALUE_EXISTING = valueFactory.integer(size - 1);
		VALUE_NOT_EXISTING = valueFactory.integer(size + 1);
	}
	
	@Test
	public void testIsEmpty() {
		testMap.isEmpty();
	}

	public void timeIsEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEmpty();
		}
	}
	
	@Test
	public void testSize() {
		testMap.size();
	}

	public void timeSize(int reps) {
		for (int i = 0; i < reps; i++) {
			testSize();
		}
	}	
	
	@Test
	public void testGet() {
		for (IValue v : testMap) {
			testMap.get(v);
		}
	}

	public void timeGet(int reps) {
		for (int i = 0; i < reps; i++) {
			testGet();
		}
	}	
	
	@Test
	public void testContainsKey() {
		testMap.containsKey(VALUE_EXISTING);
	}
	
	public void timeContainsKey(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsKey();
		}
	}	

	@Test
	public void testContainsKeyNotExisting() {
		testMap.containsKey(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsKeyNotExisting(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsKeyNotExisting();
		}
	}	

	@Test
	public void testContainsValue() {
		testMap.containsValue(VALUE_EXISTING);
	}
	
	public void timeContainsValue(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValue();
		}
	}	
	
	@Test
	public void testContainsValueNotExisting() {
		testMap.containsValue(VALUE_NOT_EXISTING);
	}
	
	public void timeContainsValueNotExisting(int reps) {
		for (int i = 0; i < reps; i++) {
			testContainsValueNotExisting();
		}
	}	

	@Test
	public void testGetKeyType() {
		testMap.getKeyType();
	}
	
	public void timeGetKeyType(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetKeyType();
		}
	}	

	@Test
	public void testGetValueType() {
		testMap.getValueType();
	}
	
	public void timeGetValueType(int reps) {
		for (int i = 0; i < reps; i++) {
			testGetValueType();
		}
	}	

	@Test
	public void testJoin() {
		testMap.join(testMap);
	}	

	public void timeJoin(int reps) {
		for (int i = 0; i < reps; i++) {
			testJoin();
		}
	}	
	
	@Test
	public void testRemove() {
		testMap.remove(testMap);
	}

	public void timeRemove(int reps) {
		for (int i = 0; i < reps; i++) {
			testRemove();
		}
	}		

	@Test
	public void testCompose() {
		testMap.compose(testMap);
	}
	
	public void timeCompose(int reps) {
		for (int i = 0; i < reps; i++) {
			testCompose();
		}
	}		
	
	@Test
	public void testCommon() {
		testMap.common(testMap);
	}	
	
	public void timeCommon(int reps) {
		for (int i = 0; i < reps; i++) {
			testCommon();
		}
	}	
	
	@Test
	public void testIsSubMap() {
		testMap.isSubMap(testMap);
	}

	public void timeIsSubMap(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsSubMap();
		}
	}	
	
	@Test
	public void testIterator() {
		Iterator<?> iterator = testMap.iterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeIterator(int reps) {
		for (int i = 0; i < reps; i++) {
			testIterator();
		}
	}		
	
	@Test
	public void testValueIterator() {
		Iterator<?> iterator = testMap.valueIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeValueIterator(int reps) {
		for (int i = 0; i < reps; i++) {
			testValueIterator();
		}
	}		
	
	@Test
	public void testEntryIterator() {
		Iterator<?> iterator = testMap.entryIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}
		
	public void timeEntryIterator(int reps) {
		for (int i = 0; i < reps; i++) {
			testEntryIterator();
		}
	}	
	
	@Test
	public void testEquals() {
		testMap.equals(testMap);
	}
	
	public void timeEquals(int reps) {
		for (int i = 0; i < reps; i++) {
			testEquals();
		}
	}
	
	@Test
	public void testIsEqual() {
		testMap.isEqual(testMap);
	}
	
	public void timeIsEqual(int reps) {
		for (int i = 0; i < reps; i++) {
			testIsEqual();
		}
	}

	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAEMapBenchmark.class, args);
	}	
	
}
