/*******************************************************************************
 * Copyright (c) 2012 CWI
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.junit.AfterClass;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class CaliperModelAggregationBenchmark extends SimpleBenchmark {

	private static TypeStore typeStore = new TypeStore();

	private IValueFactory valueFactory;
	@Param private ValueFactoryFactory valueFactoryFactory;
	
	public enum ValueFactoryFactory {
		VF_RASCAL {
			@Override IValueFactory getInstance() {
				return org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
			}
		},
		VF_CLOJURE {
			@Override IValueFactory getInstance() {
				return org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
			}
		},
		VF_SCALA {
			@Override IValueFactory getInstance() {
				return new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
			}
		};
		
		abstract IValueFactory getInstance();

	}
	
	private IValue[] values;
	private ISet[] singleValueSets;

	@Param({"10", "100", "1000", "10000"}) int singleValueSetsCount;
	
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance(); 
		
//		URL folderOfTestDataURL = CaliperModelAggregationBenchmark.class.getResource("./model-aggregation");
//		values = (IValue[]) readValuesFromFiles(new File(folderOfTestDataURL.getFile()).listFiles());
	
		singleValueSets = new ISet[singleValueSetsCount];
		for (int i = 0; i < singleValueSets.length; i++) {
			singleValueSets[i] = valueFactory.set(valueFactory.integer(i));
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private IValue[] readValuesFromFiles(File[] files) throws Exception {
		IValue[] values = new IValue[files.length];
		
		for (int i = 0; i < files.length; i++) {
			try (InputStream inputStream = new FileInputStream(files[i])) {
				
				BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
				values[i] = binaryReader.deserialize();
			}
		}
		
		return values;
	}

//	public Object timeRelationAggregation(int reps) throws Exception {
//		Object dummy = null;
//		
//		for (int i = 0; i < reps; i++) dummy = relationAggregation();
//		
//		return dummy;
//	}
	
	public IRelation[] relationAggregation() throws Exception {
		String[] relationNames = new String[] { 
				"methodBodies",
				"classes",
				"methodDecls",
				"packages",
				"fieldDecls",
				"implements",
				"methods",
				"declaredFields",
				"calls",
				"variables",
				"declaredMethods",
				"types",
				"modifiers",
//				"declaredTopTypes" // ISet
		};
		
		IRelation[] relations = new IRelation[relationNames.length];
		for (int i = 0; i < relations.length; i++) {
			relations[i] = valueFactory.relation();
		}
		
//		ISet declaredTopTypes = valueFactory.relation();
				
		for (IValue value : values) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				IRelation one = relations[i];
				IRelation two = (IRelation) constructor.getAnnotation(relationName);
				
				relations[i] = one.union(two); 
			}
			
//			declaredTopTypes = declaredTopTypes.union((ISet) constructor.getAnnotation("declaredTopTypes"));
		}
		
//		// Writing output to file
//		for (int i = 0; i < relations.length; i++) {
//			try (OutputStream outputStream = new FileOutputStream("_union_of_" + relationNames[i])) {
//				
//				BinaryWriter binaryWriter = new BinaryWriter(relations[i], outputStream, typeStore);
//				binaryWriter.serialize();
//			}
//		}
		
		return relations;
	}
	
	public ISet timeUnionSingleElementIntegerSets(int reps) {
		ISet testSet = null;
		
		for (int r = 0; r < reps; r++) {
			System.out.println("Reps: " + r);
			testSet = valueFactory.set();
			for (int i = 0; i < singleValueSets.length; i++) testSet = testSet.union(singleValueSets[i]);
		}
		
		return testSet;
	}		

//	public static void main(String[] args) throws Exception {
//		Runner.main(CaliperModelAggregationBenchmark.class, new String[]{"-Jmemory=-Xms2048m", "--measureMemory"});
//	}
	
}
