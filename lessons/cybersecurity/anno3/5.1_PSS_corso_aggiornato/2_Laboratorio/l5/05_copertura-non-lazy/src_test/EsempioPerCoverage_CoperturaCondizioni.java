import static org.junit.Assert.*;

import org.junit.Test;

public class EsempioPerCoverage_CoperturaCondizioni {

	//copre:
	//- if("a < 10") a true
	//- if (b & (c | d < 5)):
	//  *) "b" a true
	//  *) "c" a true
	//  *) "d < 5" a true
	@Test
	public void testFoo_coperturaCondizioni1() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(5, EsempioPerCoverage.foo(1, true, true, 1, arr));
	}

	//copre:
	//- if (b & (c | d < 5))
	//  *) "c" a false
	//  *) "d < 5" a false
	//- if(b) a true
	@Test
	public void testFoo_coperturaCondizioni2() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(4, EsempioPerCoverage.foo(1, true, false, 7, arr));
	}

	//copre:
	//- if (b & (c | d < 5))
	//  *) "b" a false
	//- if(b) a false
	//- if(c) a true
	@Test
	public void testFoo_coperturaCondizioni3() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(3, EsempioPerCoverage.foo(1, false, true, 7, arr));
	}

	//copre:
	//- if(c) a false
	@Test
	public void testFoo_coperturaDecisioni4() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(2, EsempioPerCoverage.foo(1, false, false, 7, arr));
	}

	//copre:
	//- if(a < 10) a false
	@Test
	public void testFoo_coperturaDecisioni5() {
		int[] arr = {1, 2, 3, 4, 5};
		assertEquals(1, EsempioPerCoverage.foo(11, false, false, 7, arr));
	}
}
