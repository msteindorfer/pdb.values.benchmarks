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
import java.util.List;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.io.binary.BinaryReader;
import org.eclipse.imp.pdb.facts.type.TypeStore;

public class ValueUtils {
	
	IValueFactory valueFactory;
	TypeStore typeStore;
	
	public ValueUtils(IValueFactory valueFactory, TypeStore typeStore) {
		this.valueFactory = valueFactory;
		this.typeStore = typeStore;
	}
	
	public IValue[] readValuesFromResources(Class<?> clazz, List<String> resources) throws Exception {
		IValue[] values = new IValue[resources.size()];
				
		for (int i = 0; i < resources.size(); i++) {
			values[i] = readValueFromResource(clazz, resources.get(1));
		}
		
		return values;
	}

	public IValue readValueFromResource(Class<?> clazz, String resource) throws Exception {
		try (InputStream inputStream = clazz.getResourceAsStream(resource)) {
			
			BinaryReader binaryReader = new BinaryReader(valueFactory, typeStore, inputStream);
			return binaryReader.deserialize();
		}
	}
	
}
