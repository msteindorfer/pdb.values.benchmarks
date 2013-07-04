package org.eclipse.imp.pdb.values.benchmarks;

import com.google.caliper.legacy.Benchmark;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.junit.Test;

import com.google.caliper.Param;

public class CaliperAEValueFactoryBenchmark extends Benchmark {

	private IValueFactory valueFactory; 
	
	@Param
	private BenchmarkUtils.ValueFactoryFactory valueFactoryFactory;
	
	@Override
	protected void setUp() throws Exception {	
		valueFactory = valueFactoryFactory.getInstance();
	}
	
	@Test
	public void testBoolTrue() {
		valueFactory.bool(true);
	}

	public void timeBoolTrue(long reps) {
		for (long i = 0; i < reps; i++) {
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
	public void testInteger30() {
		valueFactory.integer(2^30);
	}

	public void timeInteger30(int reps) {
		for (int i = 0; i < reps; i++) {
			testInteger30();
		}
	}	
	
	@Test
	public void testIntegerMax() {
		valueFactory.integer(Integer.MAX_VALUE);
	}

	public void timeIntegerMax(int reps) {
		for (int i = 0; i < reps; i++) {
			testIntegerMax();
		}
	}	

	@Test
	public void testLongMax() {
		valueFactory.integer(Long.MAX_VALUE);
	}

	public void timeLongMax(int reps) {
		for (int i = 0; i < reps; i++) {
			testLongMax();
		}
	}

	@Test
	public void testLongMaxPlusOne() {
		valueFactory.integer("9223372036854775808");
	}

	public void timeLongMaxPlusOne(int reps) {
		for (int i = 0; i < reps; i++) {
			testLongMaxPlusOne();
		}
	}	
	
	@Test
	public void testStringShort() {
		valueFactory.string(new String("A String."));
	}

	public void timeStringShort(int reps) {
		for (int i = 0; i < reps; i++) {
			testStringShort();
		}
	}

	/*
	 * size = 250
	 * See http://www.ososo.de/randomletters/
	 */
	@Test
	public void testStringMedium() {
		valueFactory.string(new String("dCRtUPCaeeICrvPdwZjscPNLlshdtXovmLdGKtOrjeYsjVtGmlPxSyIfcsTshECwHOCLgdlAkRVRZYToNrPbWVbpHfjKSexcLqSRPzjiUPWGUCCFiGcSPQkStzSVYQhTmJsHStkwBvhXWApzOgCYTaJNetDrMidKreuUFySiLeIcYAgRlFMiZiFVPLbbcWEsDhxkUjunYAziuEGhnzRsAaSpClofhdFrpmtOvNubBdAormehaYAwtkmuWw"));
	}

	public void timeStringMedium(int reps) {
		for (int i = 0; i < reps; i++) {
			testStringMedium();
		}
	}

	/*
	 * size = 2500
	 * See http://www.ososo.de/randomletters/
	 */
	@Test
	public void testStringLong() {
		valueFactory.string(new String("ZWyKMeRvLAWeZNOlvMmcoWUmbDpJgHRveabrYPbONUxLVXUjPKjOzaSmAvqClBEupjfmKrQFvhYqdOtGRnbCFmtnVecrHHiELzCQFSjmbVPbbjsHyiwmXhbMZAkmcJBHgXSdNMmOGTlUpEPtntMtRVLbtjQxRMVLVxAnIolCaAbsVBFVNXaiDtbNoRqXVbltjJfQXfzFLElSfjFgOlymUlzSKOIiIscYsKugnEAvUVtxotexsyQXqQMmPtaouhoZVmaGSPjhdJznxfKIwusvWDEdorgQpUzzMDiHqdlFWvSgOxbtwcYoxkinSERSYCKGZJqpApiPZUcYDRvwyAMPdiSzmVupYRrTgJvkwcoXphGUaaVcIxcmAyLNwDprPJEhcwWqYfNADMEetNobXqbTyFlbwRGsRmayDxegDZWnqCzlgHqYQtcxQLMyhuZzfdgXqGuKFrCVkpIKbkemXDCZrPcIiKFeDyJKacNperaCxjQilAeyEFiBJeGRpLDWPQUMMmxgKcvnJCFOYwJdotdeXaMVrINmqUvnFZEUIQetslhhIBSklRuETKjeXNiyfpCLzxRcdNBZcyirFnDJfDZTElqqCAMiisidZwLMouGrmsuqZzKUncoLEmqMvbVuCefaWCvymMyJfkGNuNrykWCCyvkmhaZswadloDfrzVfGZLdttXCtrJRdtGbpRfXajWsrkkaCjcGmXlVjgUlYVdCJXHisCgKHrIiGhIRgDVoRxFvnlDlTaZriizztqrUThGXJHlxCoqTWHWMndlQbVxjctTTGVTMUGwLXQVFbgEfwOylcNhiaGTxdllVycpAozVBsfUMORbFiHssiBBCmNVuqmRHdnmgfiBTFWmaabyFZDAvtgxkdcJalxfvmZtaSgtGkegakjarhrIbMPlzfADrzIiQkVSCOFkJKWcLxOnUzFYpcAPksGbzTyEyinXxpsVUxxSXjVbundWBCZdXNytPtHAzAnCodcxCMDguSKZlYRtGxFIIDymRLYwCqcqHGbtWwBPrpaDlJJOYMabICrXqiFgQAJrkvhQyEkqUcndAHWIoFDhomhaeSrMriplAFtaFArRNciIEpBPIKKhRplAAfPGlkSqqiygDJODrgtGcIdrZJrxMuuaVxyZeSvFYupnsIAhTqZBIBuuiMmgaLjuEfYvezsYaMFthepkCitIpVJXTupWkiptgIieaETBWadLEWDdVUWtbzTbaEtBdRAcoJgBztsraODCoESjjsvTBSBsnBCmRobNxjflIGyYfNnJSAlrOlUqGAAEJaHWROcnwLzsgnaJtZxTAljvNvHmSkrJzLatiBBUhPeRLPCQgWSJssRmJHCUaqvZhVtFmUoUepDIqBHdrPAthvPrkLSUmQIVYFocYqpNmBmOoHOGpuokoayBxTRIGsotowcSXUPCckmydRbgVQJyXqddvOltgstkVgfrgMavlEhOflaOeLgPXdMimSuvDNRkGQrPQcNtbBpfnIyySuHhErgYZYCLYPVuWZBLmVHLwlMJOFXjjjmLeqcbmoUObpnhhTjCdUpUCuVCAxLkGnklnefcrMqAItiEzobVVYmOOkGWHTDomWFlGiyfoufejilEMzuSzzlteJZvFklaFXhosCYYQDfmgTqwgubtmKuuFzbaJIGAEnCBcAJyCjCGipPbvKzhtSEkRyyvyaDJiwvxchsGQWXESwbSRGPePRQPvVWQqKuuUgzeWkJzPOaRMJTzUtSCkBwFdMgmCerDFOztrLmmLgXSbFwOSgbznZBiGhdNWMEFvQoZfHphiQseAoZzOAiQRvDXsdswRxKvxJXnLuHCgvEqpLlRNMvQPvLcMdpHjUmghfJdaAVCZeYbgZhhiBqPxaATvgEMrkXuJKGCGlHbeYMPednvpVsHVWBPMRbOTGOWboxFwjagIJUfWGmzensUlTNMWkOLFpkRVnIRFxngmWaPorDQbDvvQUWbQsPuLdYTIqzOpBZguLZTiEkVjcFMfCOUVcAgPJaqBoeYLNQWJREhNSheaVfpxRePgKRtjTZRKovUkluaTXyLHPkNyOuIsKJwskAIvSiNUPfWIuGeurqaLCdFagaMOPwIYbcwMrWcNaFCRVexjhkKfeJsoNBapSTTGkrByyyqrQdyhVLuxvhuzhmjVDkiSSYCkhWnsrYkAWqYHpnHMNCcSmSQUIJeDfwQcwmazdNfoVQdcXbGXrotOOGqfufqXTozjCJsBotavuaqzzTZkVrnvXCCiuKRGowopULqBAntSWWVrRjkcnfMdVDGbKcWoVdOBMFuIlxWYiznMxtACDnOBrqVlqtAsSkTRDhbXLlKpgqxUAJqMhUPqZRMXosORRPmaFHyWMuaPNQsZTsRYQIeabCTAzUEnkTIZclmBndccIYpMIcgMVjzRkxQlQmJwRvWzoHtbNByCMBssIlkHtjQrvaCsndKItMTCORtQOFZdYTuIElUXYMiVLbrGmyyxVWlTHgcxHEVSgWAlttTDZ"));
	}

	public void timeStringLong(int reps) {
		for (int i = 0; i < reps; i++) {
			testStringLong();
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

	// no @Test
	public void timeListEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			valueFactory.list();
		}
	}	

	// no @Test
	public void timeMapEmpty(int reps) {
		Type voidType = TypeFactory.getInstance().voidType();
		
		for (int i = 0; i < reps; i++) {
			valueFactory.map(voidType, voidType);
		}
	}	

	// no @Test
	public void timeSetEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			valueFactory.set();
		}
	}	
	
	// no @Test
	public void timeNodeEmpty(int reps) {
		for (int i = 0; i < reps; i++) {
			valueFactory.node("NODE_NAME");
		}
	}	

	// no @Test
	public void timeConstructorEmpty(int reps) {
		Type voidType = TypeFactory.getInstance().voidType();
		
		for (int i = 0; i < reps; i++) {
			valueFactory.constructor(voidType);
		}
	}	
		
	public static void main(String[] args) throws Exception {
		com.google.caliper.runner.CaliperMain.main(CaliperAEValueFactoryBenchmark.class, args);
	}	
	
}
