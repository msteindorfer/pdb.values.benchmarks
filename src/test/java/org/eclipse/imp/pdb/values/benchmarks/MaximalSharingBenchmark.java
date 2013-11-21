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

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.Clock;

@BenchmarkOptions(clock = Clock.NANO_TIME, callgc = true, benchmarkRounds = 1, warmupRounds = 0)
public class MaximalSharingBenchmark extends AbstractJUnitBenchmark {

	private final int depth;
	
	public MaximalSharingBenchmark(IValueFactory valueFactory, int depth) throws Exception {
		super(valueFactory);
		this.depth =depth;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		List<Object[]> singleValueSetsCountValues = Arrays
				.asList(new Object[][] {
//						{  0 }, {  1}, {  2 }, {  3 }, {  4 }, {  5 }, {  6 }, {  7 }, {  8 }, {  9 },
//						{ 10 }, { 11}, { 12 }, { 13 }, { 14 }, { 15 }, { 16 }, { 17 }, { 18 }, { 19 },
//						{ 20 }, { 21}, { 22 }, { 23 }, { 24 }, { 25 }, { 26 }, { 27 }, { 28 }, { 29 },
//						{ 30 }, { 31}
						{ 20 }, // VALUES 2 and 1 are good for calibrating
						});

		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(),
				singleValueSetsCountValues);
	}

	/**
	 * Baseline benchmark that creates a tree with a given {@link #depth}. All
	 * leaf nodes are the same and therefore do not have hash collisions.
	 */
	@Test
	public void testTreeWithShareableElements() {
		final IValue one = createTreeWithShareableElements();
		final IValue two = createTreeWithShareableElements();
		final IValue thr = createTreeWithShareableElements();
				
		one.equals(two);		
		one.equals(thr);
		two.equals(one);
		two.equals(thr);		
		thr.equals(one);
		thr.equals(two);		
//		one.equals(two);
//		one.equals(two);
	}
	
	/**
	 * Baseline benchmark that creates a tree with a given {@link #depth}. All
	 * leaf nodes are the same and therefore do not have hash collisions.
	 */
	public IValue createTreeWithShareableElements() {
		final Queue<IValue> queue = new LinkedList<>();
				
		final long count = (long) Math.pow(2, depth);
		assertTrue(count > 0);
		
		// create (IInteger)
		for (long i = 0; i < count; i++) {
			queue.offer(valueFactory.integer(0));
		}
		
		// reduce (INode)
		boolean exhausted = false;
		IValue result = null;
		do {
			IValue one = queue.poll();
			IValue two = queue.poll();
			
			if (two == null) {
				exhausted = true;
				result = one;
			} else {
				queue.offer(valueFactory.node("treeNode", one, two));
			}			
		} while (!exhausted);
		
		return result;
	}
	
	/**
	 * Baseline benchmark that creates a tree with a given {@link #depth}. All
	 * leaf nodes are the same and therefore do not have hash collisions.
	 */
	@Test
	public void testTreeWithUniqueElements() {
		final IValue one = createTreeWithUniqueElements();
		final IValue two = createTreeWithUniqueElements();
		final IValue thr = createTreeWithUniqueElements();
				
		one.equals(two);		
		one.equals(thr);
		two.equals(one);
		two.equals(thr);		
		thr.equals(one);
		thr.equals(two);		
//		one.equals(two);
//		one.equals(two);
	}
	
	/**
	 * Baseline benchmark that creates a tree with a given {@link #depth}. All
	 * leaf nodes are distinct integers and therefore do not have hash collisions.
	 */
	public IValue createTreeWithUniqueElements() { 
		Queue<IValue> queue = new LinkedList<>();
		
		final long count = (long) Math.pow(2, depth);
		assertTrue(count > 0);
		
		// create (IInteger)
		for (long i = 0; i < count; i++) {
			queue.offer(valueFactory.integer(i));
		}
		
		// reduce (INode)
		boolean exhausted = false;
		IValue result = null;
		do {
			IValue one = queue.poll();
			IValue two = queue.poll();
			
			if (two == null) {
				exhausted = true;
				result = one;
			} else {
				queue.offer(valueFactory.node("treeNode", one, two));
			}			
		} while (!exhausted);
		
		return result;
	}
	
}
