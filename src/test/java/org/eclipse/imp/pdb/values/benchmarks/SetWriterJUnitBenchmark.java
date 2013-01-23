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

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class SetWriterJUnitBenchmark extends AbstractJUnitBenchmark {

	static {
		AbstractJUnitBenchmark.printParameters(getTestParameters());
	}
	
	public SetWriterJUnitBenchmark(IValueFactory valueFactory, String setResource) throws Exception {
		super(valueFactory);
		this.setResource = setResource;		
	}

	private String setResource;
	private static ISet testSet;	
	
	@Parameters
	public static List<Object[]> getTestParameters() {
		List<Object[]> relationResourceValues = Arrays.asList(new Object[][] {
//				{ "rsf/Eclipse202a.rsf_CALL" }, 
//				{ "rsf/jdk14v2.rsf_CALL" },
//				{ "rsf/JDK140AWT.rsf_CALL" }, 
				{ "rsf/JHotDraw52.rsf_CALL" },
//				{ "rsf/JWAM16FullAndreas.rsf_CALL" } 
				});

		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(),
				relationResourceValues);
	}	
	
	@Override
	public void setUp() throws Exception {	
		try (InputStream inputStream = SetWriterJUnitBenchmark.class.getResourceAsStream(setResource)) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			testSet = (ISet) binaryReader.deserialize();
		}
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}	
	
	@Test
	public void timeInsert() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAll() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		writer.done();
	}
	
	@Test
	public void timeInsertIndividuallyAndAllSame() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}
		writer.insertAll(testSet);
		
		writer.done();
	}	

	@Test
	public void timeInsertAllAndIndividuallySame() {
		ISetWriter writer = valueFactory.setWriter();
		
		writer.insertAll(testSet);
		for (IValue v : testSet) {
			writer.insert(v);
		}
		
		writer.done();
	}	
		
	@Test
	public void timeDelete() {
		ISetWriter writer = valueFactory.setWriter();
		writer.insertAll(testSet);
		
		for (IValue v : testSet) {
			writer.delete(v);
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndCheckSize() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
			writer.size();
		}
		
		writer.done();
	}
	
	@Test
	public void timeInsertAndDelete() {
		ISetWriter writer = valueFactory.setWriter();
		
		for (IValue v : testSet) {
			writer.insert(v);
		}

		for (IValue v : testSet) {
			writer.delete(v);
		}
				
		writer.done();
	}

}