package org.eclipse.imp.pdb.values.benchmarks;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.eclipse.imp.pdb.facts.IValue;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class MemoryMeasurmentBenchmark {

	@Test
	public void test() {
		fail("Not yet implemented");

		Predicate<Object> p = Predicates.not(Predicates.instanceOf(IValue.class)); 		
		
		System.out.println(objectexplorer.MemoryMeasurer.measureBytes(new BigInteger("1"), p));		
		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(new BigInteger("1"), p));	

//		System.out.println("\n\nvalueFactory is " + valueFactoryFactory);
//		
//		System.out.println("valueFactory #" + objectexplorer.MemoryMeasurer.measureBytes(valueFactory));
//		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(valueFactory));
//		
//		System.out.println("new Integer(1) #" + objectexplorer.MemoryMeasurer.measureBytes(new Integer(1)));
//		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(new Integer(1)));
//		
//		System.out.println("1 #" + objectexplorer.MemoryMeasurer.measureBytes(1));
//		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(1));
//		
//		System.out.println("vf.integer(1) #" + objectexplorer.MemoryMeasurer.measureBytes(valueFactory.integer(1)));
//		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(valueFactory.integer(1)));	
	}

}
