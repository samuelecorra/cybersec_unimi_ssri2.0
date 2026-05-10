import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EsempioPerCoverageConLazy_CoperturaDecisioni {

	//copre la decisione (a < 10 && (b || c > 5)) a true
	@Test
	public void testFoo_coperturaDecisioni1() {
		assertEquals(1, EsempioPerCoverageConLazy.foo(1, true, 1));
	}

	//copre la decisione (a < 10 && (b || c > 5)) a false
	@Test
	public void testFoo_coperturaDecisioni2() {
		assertEquals(2, EsempioPerCoverageConLazy.foo(11, true, 1));
	}
}
