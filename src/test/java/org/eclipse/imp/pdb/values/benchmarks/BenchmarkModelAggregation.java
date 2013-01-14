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
import org.eclipse.imp.pdb.facts.impl.fast.ValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BenchmarkModelAggregation {

	private static TypeStore typeStore = new TypeStore();
	private static IValueFactory valueFactory = ValueFactory.getInstance();	// TODO: inject ValueFactory
	
	private static IValue[] values;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URL folderOfTestDataURL = BenchmarkModelAggregation.class.getResource("./model-aggregation");		
		
		values = (IValue[]) readValuesFromFiles(new File(folderOfTestDataURL.getFile()).listFiles());
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
	public void test() {
		for (int i = 0; i < 100; i++) doTest();
	}
	
	public void doTest() {
//		String[] relationNames = new String[] { 
//				"methodBodies",
//				"classes",
//				"methodDecls",
//				"packages",
//				"fieldDecls",
//				"implements",
//				"methods",
//				"declaredFields",
//				"calls",
//				"variables",
//				"declaredMethods",
//				"types",
//				"modifiers",
//				"declaredTopTypes"
//		};
		
		IRelation methodBodies = valueFactory.relation();
		IRelation classes = valueFactory.relation();
		IRelation methodDecls = valueFactory.relation();
		IRelation packages = valueFactory.relation();
		IRelation fieldDecls = valueFactory.relation();
		IRelation _implements = valueFactory.relation();
		IRelation methods = valueFactory.relation();
		IRelation declaredFields = valueFactory.relation();
		IRelation calls = valueFactory.relation();
		IRelation variables = valueFactory.relation();
		IRelation declaredMethods = valueFactory.relation();
		IRelation types = valueFactory.relation();
		IRelation modifiers = valueFactory.relation();
		ISet declaredTopTypes = valueFactory.relation();
				
		for (IValue value : values) {
			IConstructor constructor = (IConstructor) value;

			methodBodies = methodBodies.union((IRelation) constructor.getAnnotation("methodBodies"));
			classes = classes.union((IRelation) constructor.getAnnotation("classes"));
			methodDecls = methodDecls.union((IRelation) constructor.getAnnotation("methodDecls"));
			packages = packages.union((IRelation) constructor.getAnnotation("packages"));
			fieldDecls = fieldDecls.union((IRelation) constructor.getAnnotation("fieldDecls"));
			_implements = _implements.union((IRelation) constructor.getAnnotation("implements"));
			methods = methods.union((IRelation) constructor.getAnnotation("methods"));
			declaredFields = declaredFields.union((IRelation) constructor.getAnnotation("declaredFields"));
			calls = calls.union((IRelation) constructor.getAnnotation("calls"));
			variables = variables.union((IRelation) constructor.getAnnotation("variables"));
			declaredMethods = declaredMethods.union((IRelation) constructor.getAnnotation("declaredMethods"));
			types = types.union((IRelation) constructor.getAnnotation("types"));
			modifiers = modifiers.union((IRelation) constructor.getAnnotation("modifiers"));
			
			declaredTopTypes = declaredTopTypes.union((ISet) constructor.getAnnotation("declaredTopTypes"));
		}
	}

}
