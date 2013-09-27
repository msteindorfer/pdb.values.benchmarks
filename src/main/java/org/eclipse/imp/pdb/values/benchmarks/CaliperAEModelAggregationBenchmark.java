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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.eclipse.imp.pdb.facts.type.TypeStore;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Param;
import com.google.caliper.api.Macrobenchmark;

public class CaliperAEModelAggregationBenchmark {

	private IValueFactory valueFactory;

	private final TypeStore typeStore = new TypeStore();

	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;
		
	@SuppressWarnings("rawtypes")
	private static volatile Class lastValueFactoryClass = Object.class; // default non-factory value	
	
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
	
	private static IValue[] constructorValues;

	private static ISet[] unionRelations;
		
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		String resourcePrefixRelativeToClass = "model-aggregation";
		List<String> resources = new ArrayList<>();
		
		try (
				InputStream inputStream = CaliperAEModelAggregationBenchmark.class.getResourceAsStream(resourcePrefixRelativeToClass + "/" + "index.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				resources.add(resourcePrefixRelativeToClass + "/" + line);
			}
			
		}
		constructorValues = (IValue[]) readValuesFromFiles(this.getClass(), resources);

				
		// TODO: load from serialized files instead of computing.
		unionRelations = unionRelations();
	}

	@BeforeExperiment
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance(); 

		// detect change of valueFactory
		if (!lastValueFactoryClass.equals(valueFactory.getClass())) {
			setUpStaticValueFactorySpecificTestData();
			lastValueFactoryClass = valueFactory.getClass();
		}
	}
	
	private IValue[] readValuesFromFiles(Class<?> clazz, List<String> resources) throws Exception {
		IValue[] values = new IValue[resources.size()];
				
		for (int i = 0; i < resources.size(); i++) {
			try (InputStream inputStream = clazz.getResourceAsStream(resources.get(i))) {
				
				BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
				values[i] = binaryReader.deserialize();
			}
		}
		
		return values;
	}
	
	@Macrobenchmark
	public ISet[] unionRelations() throws Exception {
		
		// initialize
		ISet[] relations = new ISet[relationNames.length];
		for (int i = 0; i < relations.length; i++) {
			relations[i] = valueFactory.set();
		}

		// compute / accumulate
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				ISet one = relations[i];
				ISet two = (ISet) constructor.asAnnotatable().getAnnotation(relationName);
				
				relations[i] = one.union(two); 
			}		
		}
				
		return relations;
	}

	@Macrobenchmark
	public ISet[] subtractRelations() throws Exception {
		
		// initialize
		ISet[] relations = unionRelations;
			
		// compute / accumulate		
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				ISet one = relations[i];
				ISet two = (ISet) constructor.asAnnotatable().getAnnotation(relationName);
				
				relations[i] = one.subtract(two); 
			}		
		}
				
		return relations;
	}

	@Macrobenchmark
	public ISet[] intersectRelations() throws Exception {
		
		// initialize
		ISet[] relations = unionRelations;
				
		// compute / accumulate
		for (IValue value : constructorValues) {
			IConstructor constructor = (IConstructor) value;

			for (int i = 0; i < relations.length; i++) {
				String relationName = relationNames[i];
				ISet one = relations[i];
				ISet two = (ISet) constructor.asAnnotatable().getAnnotation(relationName);
				
				relations[i] = one.intersect(two); 
			}		
		}
				
		return relations;
	}	
	
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAEModelAggregationBenchmark.class, args);
	}
	
}
