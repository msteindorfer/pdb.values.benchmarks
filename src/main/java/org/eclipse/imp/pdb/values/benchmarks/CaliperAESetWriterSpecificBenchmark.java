/*******************************************************************************
 * Copyright (c) 2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *   * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI  
 *******************************************************************************/
package org.eclipse.imp.pdb.values.benchmarks;

//import java.util.HashSet;
//import java.util.Set;
//
//import org.eclipse.imp.pdb.facts.ISet;
//import org.eclipse.imp.pdb.facts.ISetWriter;
//import org.eclipse.imp.pdb.facts.IValue;
//import org.eclipse.imp.pdb.facts.IValueFactory;
//import org.eclipse.imp.pdb.facts.type.TypeFactory;
//
//import scala.collection.mutable.SetBuilder;
//
//import clojure.lang.IPersistentSet;
//import clojure.lang.ITransientSet;
//import clojure.lang.PersistentHashSet;
//
//import com.google.caliper.Param;
//
//public class CaliperAESetWriterSpecificBenchmark extends AbstractCaliperBenchmark {
//	
//	private IValueFactory valueFactory; 
//	
//	@Param("VF_RASCAL")
//	private ValueFactoryFactory valueFactoryFactory;
//
//	@Param({"10", "100", "1000", "10000"}) 
//	int size;
//	
//	private ISet testSet;
//	
//	@Override
//	protected void setUp() throws Exception {
//		valueFactory = valueFactoryFactory.getInstance();
//		
//		// TODO: parameterize test data generation
//		ISetWriter writer = valueFactory.setWriter();
//		
//		for (int i = size; i > 0; i--) {
//			writer.insert(valueFactory.integer(i));
//		}
//		
//		testSet = writer.done();
//	}
//
//	public void timeJavaMutable(int reps) {		
//		for (int i = 0; i < reps; i++) {
//			Set<IValue> xs = new HashSet<>();
//			for (IValue v : testSet) {
//				xs.add(v);	
//			}
//		}
//	}
//
//	public void timeSetWriter(int reps) {		
//		for (int i = 0; i < reps; i++) {
//			ISetWriter writer = valueFactory.setWriter(TypeFactory.getInstance().integerType());
//			for (IValue v : testSet) {
//				writer.insert(v);	
//			}
//			writer.done();
//		}
//	}	
//	
//	public void timeClojureTransient(int reps) {		
//		for (int i = 0; i < reps; i++) {
//			ITransientSet xs = (ITransientSet) PersistentHashSet.EMPTY.asTransient();
//			for (IValue v : testSet) {
//				xs = (ITransientSet) xs.conj(v);	
//			}
//			xs.persistent();
//		}
//	}
//		
//	public void timeClojurePersistent(int reps) {		
//		for (int i = 0; i < reps; i++) {
//			IPersistentSet xs = (IPersistentSet) PersistentHashSet.EMPTY;
//			for (IValue v : testSet) {
//				xs = (IPersistentSet) xs.cons(v);	
//			}
//		}
//	}	
//
////	public void timeScalaPersistent(int reps) { 
////		for (int i = 0; i < reps; i++) {
////			scala.collection.immutable.Set<IValue> empty = scala.collection.immutable.Set$.MODULE$.empty();
////			for (IValue v : testSet) {
////				empty = empty.$plus(v);
////			}
////		}
////	}
////	
////	public void timeScalaMutable(int reps) { 
////		for (int i = 0; i < reps; i++) {
////			scala.collection.SetLike empty = scala.collection.mutable.Set$.MODULE$.empty();
////			for (IValue v : testSet) {
////				empty = ((scala.collection.mutable.Set) empty).$plus$eq(v);
////			}
////		}
////	}	
//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void timeScalaSetBuilderPersistent(int reps) { 
//		for (int i = 0; i < reps; i++) {
//			scala.collection.Set<IValue> empty = scala.collection.immutable.Set$.MODULE$.empty();
//			scala.collection.mutable.SetBuilder builder = new SetBuilder(empty);
//			for (IValue v : testSet) {
//				builder = builder.$plus$eq(v);
//			}
//			builder.result();
//		}
//	}	
//		
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void timeScalaSetBuilderMutable(int reps) { 
//		for (int i = 0; i < reps; i++) {
//			scala.collection.Set<IValue> empty = scala.collection.mutable.Set$.MODULE$.empty();
//			scala.collection.mutable.SetBuilder builder = new SetBuilder(empty);
//			for (IValue v : testSet) {
//				builder = builder.$plus$eq(v);
//			}
//			builder.result();
//		}
//	}
//
//	public void timeString(int reps) {		
//		String sample = "ABCDEFG";
//		
//		for (int i = 0; i < reps; i++) {
//			String[] strings = new String[size];
//			
//			for (int j = 0; j < size; j++) {
//				strings[j] = new String(sample);
//			}
//		}
//	}	
//
//	public void timeStringIntern(int reps) {		
//		String sample = "ABCDEFG";
//		
//		for (int i = 0; i < reps; i++) {
//			String[] strings = new String[size];
//			
//			for (int j = 0; j < size; j++) {
//				strings[j] = new String(sample).intern();
//			}
//		}
//	}	
//		
//	public static void main(String[] args) throws Exception {
//		com.google.caliper.runner.CaliperMain.main(CaliperAESetWriterSpecificBenchmark.class, args);
//	}	
//	
//}

// public class CaliperAESetWriterSpecificBenchmark extends AbstractCaliperBenchmark {}
