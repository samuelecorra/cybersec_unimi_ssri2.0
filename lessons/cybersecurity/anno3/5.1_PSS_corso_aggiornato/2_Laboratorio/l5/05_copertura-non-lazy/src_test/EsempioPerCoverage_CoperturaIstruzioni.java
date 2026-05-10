import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Nei commenti dei test case mettiamo solo le istruzioni coperte
 * in più rispetto ai test case precedenti: se un'istruzione è anche
 * coperta da un test case precendente non la riportiamo.
 */
public class EsempioPerCoverage_CoperturaIstruzioni {

	//copre istruzioni alle righe 4, 5, 6
	@Test
	public void testFoo_coperturaIstruzioni1() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(5, EsempioPerCoverage.foo(1, true, true, 1, arr));
	}

	//copre istruzioni alle righe 9, 10
	@Test
	public void testFoo_coperturaIstruzioni2() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(4, EsempioPerCoverage.foo(1, true, false, 7, arr));
	}

	//copre istruzioni alle righe 13, 14
	@Test
	public void testFoo_coperturaIstruzioni3() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(3, EsempioPerCoverage.foo(1, false, true, 7, arr));
	}

	//copre istruzione alla riga 17
	@Test
	public void testFoo_coperturaIstruzioni4() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(2, EsempioPerCoverage.foo(1, false, false, 7, arr));
	}

	//copre istruzione alla riga 23
	@Test
	public void testFoo_coperturaIstruzioni5() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(1, EsempioPerCoverage.foo(11, false, false, 7, arr));
	}
}
