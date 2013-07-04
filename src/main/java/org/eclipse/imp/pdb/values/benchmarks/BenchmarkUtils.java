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

import org.eclipse.imp.pdb.facts.IValueFactory;

public class BenchmarkUtils {
	public static enum ValueFactoryFactory {
//		VF_JAVA {
//			@Override IValueFactory getInstance() {
//				return org.eclipse.imp.pdb.facts.impl.reference.ValueFactory.getInstance();
//			}
//		},
		VF_RASCAL {
			@Override
			IValueFactory getInstance() {
				return org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
			}
//		},
//		VF_CLOJURE {
//			@Override IValueFactory getInstance() {
//				return org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
//			}
//		},
//		VF_SCALA {
//			@Override IValueFactory getInstance() {
//				return new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
//			}
		};

		abstract IValueFactory getInstance();
	}
}
