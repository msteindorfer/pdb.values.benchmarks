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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

@RunWith(Parameterized.class)
public class JUnitModelAggregationBenchmark {
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();	
	
	private static TypeStore typeStore = new TypeStore();
	private IValueFactory valueFactory;
	
	private IValue[] values;
	private ISet[] singleValueSets;
	
	public JUnitModelAggregationBenchmark(IValueFactory valueFactory) throws Exception {
		this.valueFactory = valueFactory;
	}
	
	@Parameters
	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] {
					{ org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance() },
//					{ org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance() },
					{ new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory() },
					{ org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance() }
		});
	}

	private static IValue[] readValuesFromFiles(IValueFactory valueFactory, File[] files) throws Exception {
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
		URL folderOfTestDataURL = JUnitModelAggregationBenchmark.class.getResource("model-aggregation");
		values = (IValue[]) readValuesFromFiles(valueFactory, new File(folderOfTestDataURL.getFile()).listFiles());
	
		int singleValueSetsCount = 10_000;
		singleValueSets = new ISet[singleValueSetsCount];
		for (int i = 0; i < singleValueSets.length; i++) {
			singleValueSets[i] = valueFactory.set(valueFactory.integer(i));
		}	
	
	}

	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testRelationAggregation() throws Exception {
		doRelationAggregation();
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
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testUnionSingleElementIntegerSets() {
		doUnionSingleElementIntegerSets();
	}		

	public ISet doUnionSingleElementIntegerSets() {
		ISet testSet = valueFactory.set();
		for (int i = 0; i < singleValueSets.length; i++) testSet = testSet.union(singleValueSets[i]);
				
		return testSet;
	}		
	
}
