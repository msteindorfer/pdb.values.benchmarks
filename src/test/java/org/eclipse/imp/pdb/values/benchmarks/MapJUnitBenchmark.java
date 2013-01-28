package org.eclipse.imp.pdb.values.benchmarks;

import java.util.Iterator;

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

public class MapJUnitBenchmark extends AbstractJUnitBenchmark {

	static {
		AbstractJUnitBenchmark.printParameters(getTestParameters());
	}
	
	public MapJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private IMap testMap;
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		IMapWriter writer = valueFactory.mapWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.tuple(valueFactory.integer(i), valueFactory.integer(i)));
		}
		
		testMap = writer.done();
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}		
	
	@Test
	public void timeIsEmpty() {
		testMap.isEmpty();
	}
	
	@Test
	public void timeSize() {
		testMap.size();
	}
	
	@Test
	public void timeGet() {
		for (IValue v : testMap) {
			testMap.get(v);
		}
	}

	@Test
	public void timeContainsKey() {
		for (IValue v : testMap) {
			testMap.containsKey(v);
		}
	}	

	@Test
	public void timeContainsValue() {
		for (Iterator<IValue> iterator = testMap.valueIterator(); iterator.hasNext();) {
			IValue v = iterator.next();
			testMap.containsValue(v);
		}
	}	

	@Test
	public void timeGetKeyType() {
		testMap.getKeyType();
	}	

	@Test
	public void timeGetValueType() {
		testMap.getValueType();
	}	

	@Test
	public void timeJoin() {
		testMap.join(testMap);
	}	

	@Test
	public void timeRemove() {
		testMap.remove(testMap);
	}
	
	@Test
	public void timeCompose() {
		testMap.compose(testMap);
	}
	
	@Test
	public void timeCommon() {
		testMap.common(testMap);
	}	

	@Test
	public void timeIsSubMap() {
		testMap.isSubMap(testMap);
	}
	
	@Test
	public void timeIterator() {
		Iterator<?> iterator = testMap.iterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}

	@Test
	public void timeValueIterator() {
		Iterator<?> iterator = testMap.valueIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}	
	
	@Test
	public void timeEntryIterator() {
		Iterator<?> iterator = testMap.entryIterator();		
		while (iterator.hasNext() && (iterator.next() == null || iterator.next() != null));
	}
		
	@Test
	public void timeEquals() {
		testMap.equals(testMap);
	}
	
	
}
