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

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValueFactory;

import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class CaliperAASetWriterBenchmark extends AbstractCaliperBenchmark {
	
	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) 
	int testSetSize;
	
	private ISet testSet;
	private ISetWriter testWriter;

	private ISetWriter testEmptyWriter;
	
	@Override
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = testSetSize; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
		
		
		testWriter = valueFactory.setWriter();
		
		for (int i = testSetSize; i > 0; i--) {
			testWriter.insert(valueFactory.integer(i));
		}
		
		testEmptyWriter = valueFactory.setWriter();
	}
		
	public void timeInsert(int runs) {
		for (int i = 0; i < runs; i++) {
			testEmptyWriter.insert(valueFactory.integer(i % testSetSize));
		}
	}
	
	public void timeInsertAll(int runs) {
		for (int i = 0; i < runs; i++) {
			testEmptyWriter.insertAll(testSet);
		}
	}	

//	// How to test delete?
//	public void timeDelete(int runs) {
//		for (int i = 0; i < runs; i++) {
//			ISetWriter writer = valueFactory.setWriter();
//			writer.insertAll(testSet);
//		
//		for (IValue v : testSet) {
//			writer.delete(v);
//		}
//		
//		writer.done();
//		}
//	}
	
	public void timeSize(int runs) {
		for (int i = 0; i < runs; i++) {
			testWriter.size();
		}
	}

	public static void main(String[] args) throws Exception {
		CaliperMain.main(CaliperAASetWriterBenchmark.class, args);
	}	
	
}