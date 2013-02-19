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
import org.eclipse.imp.pdb.facts.type.TypeStore;

abstract class AbstractCaliperBenchmark extends com.google.caliper.SimpleBenchmark {

	protected final TypeStore typeStore = new TypeStore();
	
	protected enum ValueFactoryFactory {
		VF_RASCAL {
			@Override IValueFactory getInstance() {
				return org.eclipse.imp.pdb.facts.impl.fast.ValueFactory.getInstance();
			}
		},
		VF_CLOJURE {
			@Override IValueFactory getInstance() {
				return org.eclipse.imp.pdb.facts.impl.persistent.clojure.ValueFactory.getInstance();
			}
		},
		VF_SCALA {
			@Override IValueFactory getInstance() {
				return new org.eclipse.imp.pdb.facts.impl.persistent.scala.ValueFactory();
			}
		};
		
		abstract IValueFactory getInstance();
	}
	
}
