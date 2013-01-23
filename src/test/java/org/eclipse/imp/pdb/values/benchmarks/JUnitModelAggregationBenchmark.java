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
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

@BenchmarkOptions(callgc=true)
@BenchmarkMethodChart(filePrefix="method")
@BenchmarkHistoryChart(filePrefix="history")
@RunWith(Parameterized.class)
public class JUnitModelAggregationBenchmark {
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();	
	
	private static TypeStore typeStore = new TypeStore();

	private IValueFactory valueFactory;
	
	public JUnitModelAggregationBenchmark(IValueFactory valueFactory) throws Exception {
		this.valueFactory = valueFactory;
	}
	
	@Parameters
	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] {
//				{ org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance() },
				{ org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance() },
				{ org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance() },
				{ new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory() },
		});
	}
		
	@SuppressWarnings("rawtypes")
	private static volatile Class lastValueFactoryClass = Object.class; // default non-factory value	
	
	private static IValue[] constructorValues;
	
	private static String[] relationNames = new String[] { 
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
//			"declaredTopTypes" // ISet
	};
	
	private static IRelation[] unionRelations;
		
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		URL folderOfTestDataURL = CaliperModelAggregationBenchmark.class.getResource("model-aggregation");
		constructorValues = (IValue[]) readValuesFromFiles(new File(folderOfTestDataURL.getFile()).listFiles());
		
		// TODO: load from serialized files instead of computing.
		unionRelations = unionRelations();
	}

	@Before
	public void setUp() throws Exception {
		// detect change of valueFactory
		if (!lastValueFactoryClass.equals(valueFactory.getClass())) {
			setUpStaticValueFactorySpecificTestData();
			lastValueFactoryClass = valueFactory.getClass();
		}
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
	
	public IRelation[] unionRelations() throws Exception {
		
		// initialize
		IRelation[] relations = new IRelation[relationNames.length];
		for (int i = 0; i < relations.length; i++) {
			relations[i] = valueFactory.relation();
		}

		// compute / accumulate
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				IRelation one = relations[i];
				IRelation two = (IRelation) constructor.getAnnotation(relationName);
				
				relations[i] = one.union(two); 
			}		
		}
				
		return relations;
	}

	@Test
	public void timeUnionRelations() throws Exception {
		unionRelations();
	}	
	
	public IRelation[] subtractRelations() throws Exception {
		
		// initialize
		IRelation[] relations = unionRelations;
			
		// compute / accumulate		
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				IRelation one = relations[i];
				IRelation two = (IRelation) constructor.getAnnotation(relationName);
				
				relations[i] = one.subtract(two); 
			}		
		}
				
		return relations;
	}
	
	@Test
	public void timeSubtractRelations() throws Exception {
		subtractRelations();
	}	

	public IRelation[] intersectRelations() throws Exception {
		
		// initialize
		IRelation[] relations = unionRelations;
				
		// compute / accumulate
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				IRelation one = relations[i];
				IRelation two = (IRelation) constructor.getAnnotation(relationName);
				
				relations[i] = one.intersect(two); 
			}		
		}
				
		return relations;
	}
	
	@Test
	public void timeIntersectRelations() throws Exception {
		intersectRelations();
	}	
	
}
