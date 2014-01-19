package org.eclipse.imp.pdb.values.benchmarks;

import objectexplorer.MemoryMeasurer;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

public class DataStructureFootprintTests {

	private static int size = 100_000;
	
	private ISet createSetWithContinuousIntegers(IValueFactory valueFactory) {
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		return writer.done();		
	}
	
	private void measureAndReport(IValueFactory valueFactory, Object o) {
		long footprint = MemoryMeasurer.measureBytes(o);
		System.out.println(String.format("%d bytes are needed by %s.", footprint, valueFactory));		
	}

	@Test
	public void footprintSet_Reference() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}	
	
	@Test
	public void footprintSet_Fast() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}
	
	@Test
	public void footprintSet_Clojure() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}

	@Test
	public void footprintSet_Persistent() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory1.getInstance();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}

	@Test
	public void footprintSet_Persistent_2() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory2.getInstance();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}
	
	@Test
	public void footprintSet_Scala() {
		IValueFactory valueFactory = new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
		ISet set = createSetWithContinuousIntegers(valueFactory);
		measureAndReport(valueFactory, set);
	}

}
