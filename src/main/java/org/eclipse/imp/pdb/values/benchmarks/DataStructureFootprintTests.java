package org.eclipse.imp.pdb.values.benchmarks;

import java.util.Random;

import objectexplorer.MemoryMeasurer;

import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.IMapWriter;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.util.AbstractTypeBag;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class DataStructureFootprintTests {

	private static int size = 64;
	
	private ISet createSetWithContinuousIntegers(IValueFactory valueFactory) {
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i); 

			writer.insert(current);
		}
		
		return writer.done();		
	}
	
	private ISet createSetWithRandomIntegers(IValueFactory valueFactory) {
		ISetWriter writer = valueFactory.setWriter();

		Random rand = new Random();
		
		for (int i = size; i > 0; i--) {
			final int j = rand.nextInt();
			final IValue current = valueFactory.integer(j); 
			
			writer.insert(current);
		}
		
		return writer.done();		
	}
	
	private IMap createMapWithContinuousIntegers(IValueFactory valueFactory) {
		IMapWriter writer = valueFactory.mapWriter();
		
		for (int i = size; i > 0; i--) {
			final IValue current = valueFactory.integer(i); 
			
			writer.put(current, current);
		}
		
		return writer.done();		
	}
	
	private IMap createMapWithRandomIntegers(IValueFactory valueFactory) {
		IMapWriter writer = valueFactory.mapWriter();
		
		Random rand = new Random();
		
		for (int i = size; i > 0; i--) {
			final int j = rand.nextInt();
			final IValue current = valueFactory.integer(j); 
			
			writer.put(current, current);
		}
		
		return writer.done();		
	}	
	
	private void measureAndReport(IValueFactory valueFactory, final Object o) {
		Predicate<Object> isRoot = new Predicate<Object>() {
			@Override
			public boolean apply(Object arg0) {
				return arg0 == o;
			}
		};

		Predicate<Object> jointPredicate = Predicates.or(isRoot, Predicates.and(
						Predicates.not(Predicates.instanceOf(IValue.class)),
						Predicates.not(Predicates.instanceOf(AbstractTypeBag.class))));
		
		long footprint = MemoryMeasurer.measureBytes(o, jointPredicate);
		System.out.println(String.format("%d bytes are needed by %s [%s].", footprint,
						valueFactory, o.getClass().getCanonicalName()));		
	}

//	@Test
//	public void footprintSet_Reference() {
//		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}	
//	
//	@Test
//	public void footprintSet_Fast() {
//		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}
//	
//	@Test
//	public void footprintSet_Clojure() {
//		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}
//
//	@Test
//	public void footprintSet_Persistent() {
//		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory1.getInstance();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}
//
//	@Test
//	public void footprintSet_Persistent_2() {
//		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory2.getInstance();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}
//	
//	@Test
//	public void footprintSet_Scala() {
//		IValueFactory valueFactory = new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
//		ISet set = createSetWithContinuousIntegers(valueFactory);
//		measureAndReport(valueFactory, set);
//	}
	
	@Test
	public void footprintMap_Reference() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}	
	
	@Test
	public void footprintMap_Fast() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}
	
	@Test
	public void footprintMap_Clojure() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}

	@Test
	public void footprintMap_Persistent() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory1.getInstance();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}

	@Test
	public void footprintMap_Persistent_2() {
		IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory2.getInstance();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}
	
	@Test
	public void footprintMap_Scala() {
		IValueFactory valueFactory = new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
		IMap map = createMapWithRandomIntegers(valueFactory);
		measureAndReport(valueFactory, map);
	}	

}
