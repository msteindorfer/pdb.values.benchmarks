package org.eclipse.imp.pdb.values.benchmarks;

import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Ignore;
import org.junit.Test;

public class ListJUnitBenchmark extends AbstractJUnitBenchmark {

	public ListJUnitBenchmark(IValueFactory valueFactory) throws Exception {
		super(valueFactory);
	}

	private IList testList;
	
	@Override
	public void setUp() throws Exception {	
		// TODO: parameterize test data generation
		IListWriter writer = valueFactory.listWriter();
		
		for (int i = 10_000; i > 0; i--) {
			writer.insert(valueFactory.integer(i));
		}
		
		testList = writer.done();
	}
	
	@Override
	public void setUpStaticValueFactorySpecificTestData() throws Exception {
		// no static setup
	}		
	
	@Test
	public void timeGetElementType() {
		testList.getElementType();
	}
	
	@Test
	public void timeLength() {
		testList.length();
	}

	@Test
	public void timeReverse() {
		testList.reverse();
	}

	@Ignore @Test
	public void timeAppend() {
		testList.append(null);
	}	

	@Test
	public void timeInsert() {
		testList.insert(valueFactory.integer(0));
	}
	
	@Test
	public void timeConcat() {
		testList.concat(testList);
	}	
	
	@Test
	public void timePutFront() {
		int index = 0;
		testList.put(index, valueFactory.integer(0));
	}
	
	@Test
	public void timePutMiddle() {
		int index = testList.length() / 2;
		testList.put(index, valueFactory.integer(0));
	}		
	
	@Test
	public void timePutEnd() {
		int index = Math.max(0, testList.length() - 1);
		testList.put(index, valueFactory.integer(0));
	}	
	
	@Test
	public void timeGet() {
		for (int i = 0; i < testList.length(); i++) {
			testList.get(i);
		}
	}
			
	@Ignore @Test
	public void timeSublist() {
		testList.sublist(0, 0);
	}

	@Test
	public void timeIsEmpty() {
		testList.isEmpty();
	}

	@Test
	public void timeContains() {
		for (IValue v : testList) {
			testList.contains(v);
		}
	}	

	@Test
	public void timeDeleteValueKeep() {
		for (IValue v : testList) {
			testList.delete(v);
		}
	}	

	@Test
	public void timeDeleteValueReduce() {
		IList reducedList = testList;
		
		for (IValue v : testList) {
			reducedList = reducedList.delete(v);
		}
	}		

	@Test
	public void timeDeleteIndexKeep() {
		for (int i = 0; i < testList.length(); i++) {
			testList.delete(i);
		}
	}	

	@Test
	public void timeDeleteIndexReduceFromFront() {
		IList reducedList = testList;
			
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(0);
		}
	}	
		
	@Test
	public void timeDeleteIndexReduceFromBack() {
		IList reducedList = testList;
			
		while (!reducedList.isEmpty()) {
			reducedList = reducedList.delete(reducedList.length() - 1);
		}
	}
	
	@Ignore @Test
	public void  timeProduct() {
		// TODO
	}

	@Ignore @Test
	public void  timeIntersect() {
		// TODO
	}
	
	@Ignore @Test
	public void  timeSubtract() {
		// TODO
	}
	
	@Ignore @Test
	public void  timeIsSubListOf() {
		// TODO
	}
	
	@Test
	public void timeEquals() {
		testList.equals(testList);
	}
	
}
