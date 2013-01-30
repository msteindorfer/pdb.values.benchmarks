package org.eclipse.imp.pdb.values.benchmarks;

import java.util.Iterator;

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class CaliperAAMapBenchmark extends AbstractCaliperBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;	
	
	private IMap testMap;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		IMapWriter writer = valueFactory.mapWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i)));
		}
		
		testMap = writer.done();
	}
	
	@Test
	public void testIsEmpty() {
		testMap.isEmpty();
	}

	public void timeIsEmpty(int reps) {
		for (int i = 0; i < 0; i++) {
			testIsEmpty();
		}
	}
	
	@Test
	public void testSize() {
		testMap.size();
	}

	public void timeSize(int reps) {
		for (int i = 0; i < 0; i++) {
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
		for (int i = 0; i < 0; i++) {
			testGet();
		}
	}	
	
	@Test
	public void testContainsKey() {
		for (IValue v : testMap) {
			testMap.containsKey(v);
		}
	}
	
	public void timeContainsKey(int reps) {
		for (int i = 0; i < 0; i++) {
			testContainsKey();
		}
	}	

	@Test
	public void testContainsValue() {
		for (Iterator<IValue> iterator = testMap.valueIterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			testMap.containsValue(v);
		}
	}
	
	public void timeContainsValue(int reps) {
		for (int i = 0; i < 0; i++) {
			testContainsValue();
		}
	}	

	@Test
	public void testGetKeyType() {
		testMap.getKeyType();
	}
	
	public void timeGetKeyType(int reps) {
		for (int i = 0; i < 0; i++) {
			testGetKeyType();
		}
	}	

	@Test
	public void testGetValueType() {
		testMap.getValueType();
	}
	
	public void timeGetValueType(int reps) {
		for (int i = 0; i < 0; i++) {
			testGetValueType();
		}
	}	

	@Test
	public void testJoin() {
		testMap.join(testMap);
	}	

	public void timeJoin(int reps) {
		for (int i = 0; i < 0; i++) {
			testJoin();
		}
	}	
	
	@Test
	public void testRemove() {
		testMap.remove(testMap);
	}

	public void timeRemove(int reps) {
		for (int i = 0; i < 0; i++) {
			testRemove();
		}
	}		
	
	@Test
	public void testCompose() {
		testMap.compose(testMap);
	}
	
	public void timeCompose(int reps) {
		for (int i = 0; i < 0; i++) {
			testCompose();
		}
	}		
	
	@Test
	public void testCommon() {
		testMap.common(testMap);
	}	
	
	public void timeCommon(int reps) {
		for (int i = 0; i < 0; i++) {
			testCommon();
		}
	}	
	
	@Test
	public void testIsSubMap() {
		testMap.isSubMap(testMap);
	}

	public void timeIsSubMap(int reps) {
		for (int i = 0; i < 0; i++) {
			testIsSubMap();
		}
	}	
	
	@Test
	public void testIterator() {
		Iterator<?> iterator = testMap.iterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeIterator(int reps) {
		for (int i = 0; i < 0; i++) {
			testIterator();
		}
	}		
	
	@Test
	public void testValueIterator() {
		Iterator<?> iterator = testMap.valueIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	public void timeValueIterator(int reps) {
		for (int i = 0; i < 0; i++) {
			testValueIterator();
		}
	}		
	
	@Test
	public void testEntryIterator() {
		Iterator<?> iterator = testMap.entryIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}
		
	public void timeEntryIterator(int reps) {
		for (int i = 0; i < 0; i++) {
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
		CaliperMain.main(CaliperAAMapBenchmark.class, args);
	}	
	
}
