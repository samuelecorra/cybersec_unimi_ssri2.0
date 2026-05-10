import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Uguale alla copertura delle istruzioni.
 * Nei commenti dei test case mettiamo solo le decisioni coperte
 * in più rispetto ai test case precedenti: se una decisione è anche
 * coperta da un test case precendente non la riportiamo.
 */
public class EsempioPerCoverage_CoperturaDecisioni {

	//copre:
	// - "a < 10" a true
	// - "b & (c | d < 5)" a true
	@Test
	public void testFoo_coperturaDecisioni1() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(5, EsempioPerCoverage.foo(1, true, true, 1, arr));
	}

	//copre:
	// - "b & (c | d < 5)" a false
	// - "b" a true
	@Test
	public void testFoo_coperturaDecisioni2() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(4, EsempioPerCoverage.foo(1, true, false, 7, arr));
	}

	//copre:
	// - "b" a false
	// - "c" a true
	@Test
	public void testFoo_coperturaDecisioni3() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(3, EsempioPerCoverage.foo(1, false, true, 7, arr));
	}

	//copre:
	// - "c" a false
	@Test
	public void testFoo_coperturaDecisioni4() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(2, EsempioPerCoverage.foo(1, false, false, 7, arr));
	}

	//copre:
	// - "a < 10" a false
	@Test
	public void testFoo_coperturaDecisioni5() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(1, EsempioPerCoverage.foo(11, false, false, 7, arr));
	}
}
