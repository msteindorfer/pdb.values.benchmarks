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

public class RelationResourceBenchmark extends AbstractJUnitBenchmark {
	
	public RelationResourceBenchmark(IValueFactory valueFactory, String relationResource) throws Exception {
		super(valueFactory);
		this.relationResource = relationResource;		
	}
	
	private String relationResource;
	private static ISet testRelation;

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
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
		try (InputStream inputStream = RelationResourceBenchmark.class.getResourceAsStream(relationResource)) {
			
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