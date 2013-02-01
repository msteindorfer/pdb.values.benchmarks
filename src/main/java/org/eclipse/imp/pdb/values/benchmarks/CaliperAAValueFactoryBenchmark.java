package org.eclipse.imp.pdb.values.benchmarks;

import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Test;

import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class CaliperAAValueFactoryBenchmark extends AbstractCaliperBenchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private ValueFactoryFactory valueFactoryFactory;	
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
	}
	
	@Test
	public void testBoolTrue() {
		valueFactory.bool(true);
	}

	public void timeBoolTrue(int reps) {
		for (int i = 0; i < reps; i++) {
			testBoolTrue();
		}
	}

	@Test
	public void testIntegerOne() {
		valueFactory.integer(1);
	}

	public void timeIntegerOne(int reps) {
		for (int i = 0; i < reps; i++) {
			testIntegerOne();
		}
	}	
	
	@Test
	public void testStringShort() {
		valueFactory.string("A String.");
	}

	public void timeStringShort(int reps) {
		for (int i = 0; i < reps; i++) {
			testStringShort();
		}
	}
	
	@Test
	public void testStringEmpty() {
		valueFactory.string("");
	}

	public void timeStringEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			testStringEmpty();
		}
	}	
	
	public static void main(String[] args) throws Exception {
		CaliperMain.main(CaliperAAValueFactoryBenchmark.class, args);
	}	
	
}