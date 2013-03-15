package org.eclipse.imp.pdb.values.benchmarks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class MemoryMeasurmentBenchmark extends AbstractJUnitBenchmark {

	String resource;
	IValue value;
	
	public MemoryMeasurmentBenchmark(IValueFactory valueFactory, String resource) throws Exception {
		super(valueFactory);
		this.resource = resource;
	}

	@Parameters(name="{0}, {1}")
	public static List<Object[]> getTestParameters() throws Exception {
		List<Object[]> resources = new ArrayList<>();
		
		String resourcePrefixRelativeToClass = "php";
		
		try (
				InputStream inputStream = MemoryMeasurmentBenchmark.class.getResourceAsStream(resourcePrefixRelativeToClass + "/" + "index.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				resources.add(new Object[] { resourcePrefixRelativeToClass + "/" + line} );
			}			
		}
		
//		resources.addAll(Arrays.asList(new Object[][] {
//				{ "rsf/Eclipse202a.rsf_CALL" }, 
//				{ "rsf/jdk14v2.rsf_CALL" },
//				{ "rsf/JDK140AWT.rsf_CALL" }, 
//				{ "rsf/JHotDraw52.rsf_CALL" },
//				{ "rsf/JWAM16FullAndreas.rsf_CALL" } 
//				}));
		
		return AbstractJUnitBenchmark.productOfTestParameters(
				AbstractJUnitBenchmark.getTestParameters(),
				resources);
	}
	
	public void setUpStaticValueFactorySpecificTestData() throws Exception {}
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		ValueUtils utils = new ValueUtils(valueFactory, typeStore);
		this.value = utils.readValueFromResource(getClass(), resource);
	}
	
	@SuppressWarnings({ "resource", "unused" })
	@Test
	public void test() {
		
		Predicate<Object> p = Predicates.not(Predicates.instanceOf(IValue.class)); 		
		
		String D = "\n";
		System.out.println(valueFactory + D + resource + D
				+ objectexplorer.MemoryMeasurer.measureBytes(value) + D
				+ objectexplorer.ObjectGraphMeasurer.measure(value) + D);

		{
			System.out.print("IT'S TIME FOR A HEAPDUMP... ");

			Scanner in = new Scanner(System.in);
			int num = in.nextInt();

			System.out.println("THANKS.");	
		}

//		System.out.println(value);
//		System.out.println(objectexplorer.MemoryMeasurer.measureBytes(value));		
//		System.out.println(objectexplorer.ObjectGraphMeasurer.measure(value));	
//		System.out.println();
	}

}
