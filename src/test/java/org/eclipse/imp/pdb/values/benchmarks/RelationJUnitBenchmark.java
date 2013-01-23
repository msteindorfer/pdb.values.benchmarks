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

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.junit.Test;

public class RelationJUnitBenchmark extends AbstractJUnitBenchmark {

	public RelationJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private IRelation testRelation;	
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		try (InputStream inputStream = RelationJUnitBenchmark.class.getResourceAsStream("rsf/JHotDraw52.rsf_CALL")) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			testRelation = (IRelation) binaryReader.deserialize();
		}
	}
	
	@Test
	public void timeArity() {
		testRelation.arity();
	}
	
	@Test
	public void timeClosure() {
		testRelation.closure();
	}
	
	@Test	
	public void timeClosureStar() {
		testRelation.closureStar();
	}
	
	@Test
	public void timeCarrier() {
		testRelation.carrier();
	}
	
	@Test
	public void timeFieldTypes() {
		testRelation.getFieldTypes();
	}
	
	@Test
	public void timeDomain() {
		testRelation.domain();
	}	

	@Test
	public void timeRange() {
		testRelation.range();
	}	

	@Test
	public void timeSelect() {
		testRelation.select();
	}		
	
	@Test
	public void timeSelectByFieldNames() {
		testRelation.selectByFieldNames();
	}
	
	@Test
	public void timeCompose() {
		testRelation.compose(testRelation);
	}		

	
	/* SET OPERATIONS */
	
	@Test
	public void timeUnion() {
		testRelation.union(testRelation);
	}		
	
	@Test
	public void timeIntersect() {
		testRelation.intersect(testRelation);
	}	
	
	@Test
	public void timeSubstract() {
		testRelation.subtract(testRelation);
	}		
	
	@Test
	public void timeProduct() {
		testRelation.product(testRelation);
	}
	
}