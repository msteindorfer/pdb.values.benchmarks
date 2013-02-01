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
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;

import clojure.lang.IPersistentSet;
import clojure.lang.ITransientSet;
import clojure.lang.PersistentHashSet;

import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class CaliperAASetWriterSpecificBenchmark extends AbstractCaliperBenchmark {
	
	private IValueFactory valueFactory; 
	
	@Param("VF_RASCAL")
	private ValueFactoryFactory valueFactoryFactory;

	@Param({"10", "100", "1000", "10000"}) 
	int testSetSize;
	
	private ISet testSet;
	
	@Override
	protected void setUp() throws Exception {
		valueFactory = valueFactoryFactory.getInstance();
		
		// TODO: parameterize test data generation
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = testSetSize; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	}
	
	public void timeClojureTransient(int reps) {		
		for (int i = 0; i < reps; i++) {
			ITransientSet xs = (ITransientSet) PersistentHashSet.EMPTY.asTransient();
			for (IValue v : testSet) {
				xs = (ITransientSet) xs.conj(v);	
			}
			xs.persistent();
		}
	}
		
	public void timeClojurePersistent(int reps) {		
		for (int i = 0; i < reps; i++) {
			IPersistentSet xs = (IPersistentSet) PersistentHashSet.EMPTY;
			for (IValue v : testSet) {
				xs = (IPersistentSet) xs.cons(v);	
			}
		}
	}	
	
	public static void main(String[] args) throws Exception {
		CaliperMain.main(CaliperAASetWriterSpecificBenchmark.class, args);
	}	
	
}