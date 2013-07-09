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

import java.util.Arrays;
import java.util.List;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

@BenchmarkOptions(clock = Clock.NANO_TIME)
public class RelationBenchmark extends AbstractJUnitBenchmark {
	
	public RelationBenchmark(IValueFactory valueFactory, int size) throws Exception {
		super(valueFactory);
		this.size = size;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(), getSizeParameters());
	}
	
	public static List<Object[]> getSizeParameters() {
		return Arrays.asList(new Object[][] { { 10_000 }, { 100_000 }, { 1_000_000 } });
	}			
	
//	protected IValueFactory valueFactory; 
	protected int size;
	
	private ISet testSet;
	
	@Override
	public void setUp() throws Exception {	
		ISetWriter writer = valueFactory.setWriter();
		
		for (int i = size; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testSet = writer.done();
	}
		
	@Test
	public void timeArity() {
		testSet.asRelation().arity();
	}
	
	@Test
	public void timeClosure() {
		testSet.asRelation().closure();
	}
	
	@Test	
	public void timeClosureStar() {
		testSet.asRelation().closureStar();
	}
	
	@Test
	public void timeCarrier() {
		testSet.asRelation().carrier();
	}
	
	@Test
	public void timeFieldTypes() {
		testSet.getType().getFieldTypes();
	}
	
	@Test
	public void timeDomain() {
		testSet.asRelation().domain();
	}	

	@Test
	public void timeRange() {
		testSet.asRelation().range();
	}	

	@Test
	public void timeProject() {
		testSet.asRelation().project();
	}		
	
	@Ignore @Test
	/*
	 * projectByFieldNames throws an Exception if the underlying relation does
	 * not have field names. Therefore this benchmark is ignored for the moment.
	 */
	public void timeProjectByFieldNames() {
		testSet.asRelation().projectByFieldNames();
	}
	
	@Test
	public void timeCompose() {
		testSet.asRelation().compose(testSet.asRelation());
	}
	
}