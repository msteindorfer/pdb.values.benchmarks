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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BenchmarkModelAggregation {

	private static TypeStore typeStore = new TypeStore();
	// TODO: inject ValueFactory
//	private static IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
//	private static IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance();
//	private static IValueFactory valueFactory = new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
	private static IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
		
	private static IValue[] values;
	private static ISet[] singleValueSets;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URL folderOfTestDataURL = BenchmarkModelAggregation.class.getResource("./model-aggregation");
		values = (IValue[]) readValuesFromFiles(new File(folderOfTestDataURL.getFile()).listFiles());
	
		int singleValueSetsCount = 1_000_000;
		singleValueSets = new ISet[singleValueSetsCount];
		for (int i = 0; i < singleValueSets.length; i++) {
			singleValueSets[i] = valueFactory.set(valueFactory.integer(i));
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private static IValue[] readValuesFromFiles(File[] files) throws Exception {
		IValue[] values = new IValue[files.length];
		
		for (int i = 0; i < files.length; i++) {
			try (InputStream inputStream = new FileInputStream(files[i])) {
				
				BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
				values[i] = binaryReader.deserialize();
			}
		}
		
		return values;
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRelationAggregation() throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Logger logger = LoggerFactory.getLogger(BenchmarkModelAggregation.class);

		long startTime = System.nanoTime();
		long startMemory = (runtime.totalMemory() - runtime.freeMemory());
		
		for (int i = 0; i < 100; i++) doRelationAggregation();

	    long estimatedTime = System.nanoTime() - startTime;
		long estimatedMemory = (runtime.totalMemory() - runtime.freeMemory()) - startMemory;

	    logger.info("Duraction: {} seconds", estimatedTime / 1_000_000_000);
		logger.info("Memory used: {} mb", estimatedMemory / (1024 * 1024));		
	}
	
	public IRelation[] doRelationAggregation() throws Exception {
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

	@Test
	public void testUnionSingleElementIntegerSets() {
		Runtime runtime = Runtime.getRuntime();
		Logger logger = LoggerFactory.getLogger(BenchmarkModelAggregation.class);

		long startTime = System.nanoTime();
		long startMemory = (runtime.totalMemory() - runtime.freeMemory());
		
		doUnionSingleElementIntegerSets();
		
	    long estimatedTime = System.nanoTime() - startTime;
		long estimatedMemory = (runtime.totalMemory() - runtime.freeMemory()) - startMemory;

	    logger.info("Duraction: {} seconds", estimatedTime / 1_000_000_000);
		logger.info("Memory used: {} mb", estimatedMemory / (1024 * 1024));
	}	
	
	public ISet doUnionSingleElementIntegerSets() {
		ISet testSet = valueFactory.set();
		for (int i = 0; i < singleValueSets.length; i++) testSet = testSet.union(singleValueSets[i]);
				
		return testSet;
	}		
	
}
