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
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

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
		return Arrays.asList(new Object[][] { { 10_000 }, { 100_000 }, { 1_000_000 }, { 10_000_000 }});
	}			
	
//	protected IValueFactory valueFactory; 
	protected int size;
	
	private String relationResource;
	private static ISet testRelation;
	
	@Override
	public void setUp() throws Exception {	
		try (InputStream inputStream = RelationBenchmark.class.getResourceAsStream(relationResource)) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			testRelation = (ISet) binaryReader.deserialize();
		}
	}
		
	@Test
	public void timeArity() {
		testRelation.asRelation().arity();
	}
	
	@Test
	public void timeClosure() {
		testRelation.asRelation().closure();
	}
	
	@Test	
	public void timeClosureStar() {
		testRelation.asRelation().closureStar();
	}
	
	@Test
	public void timeCarrier() {
		testRelation.asRelation().carrier();
	}
	
	@Test
	public void timeFieldTypes() {
		testRelation.getType().getFieldTypes();
	}
	
	@Test
	public void timeDomain() {
		testRelation.asRelation().domain();
	}	

	@Test
	public void timeRange() {
		testRelation.asRelation().range();
	}	

	@Test
	public void timeProject() {
		testRelation.asRelation().project();
	}		
	
	@Ignore @Test
	/*
	 * projectByFieldNames throws an Exception if the underlying relation does
	 * not have field names. Therefore this benchmark is ignored for the moment.
	 */
	public void timeProjectByFieldNames() {
		testRelation.asRelation().projectByFieldNames();
	}
	
	@Test
	public void timeCompose() {
		testRelation.asRelation().compose(testRelation.asRelation());
	}		
	
//	/* SET OPERATIONS */
//	
//	@Test
//	public void timeUnion() {
//		testRelation.union(testRelation);
//	}		
//	
//	@Test
//	public void timeIntersect() {
//		testRelation.intersect(testRelation);
//	}	
//	
//	@Test
//	public void timeSubstract() {
//		testRelation.subtract(testRelation);
//	}		
//	
//	@Test
//	public void timeProduct() {
//		testRelation.product(testRelation);
//	}
//	
//	@Test
//	public void timeEquals() {
//		testRelation.equals(testRelation);
//	}
//	
//	@Test
//	public void timeIsEqual() {
//		testRelation.isEqual(testRelation);
//	}
	
}