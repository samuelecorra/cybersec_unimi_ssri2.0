import static org.junit.Assert.assertEquals;

import org.junit.Test;

//la decisione e' (a < 10 && (b || c > 5))
public class EsempioPerCoverageConLazy_CoperturaCondizioni {

	//copre la condizione:
	//- "a < 10" a true
	//- "b" a true
	//Attenzione: a causa della lazy evaluation non copre la condizione "c > 5"
	@Test
	public void testFoo_coperturaCondizioni1() {
		assertEquals(1, EsempioPerCoverageConLazy.foo(1, true, 1));
	}

	//copre la condizione:
	//- "a < 10" a false
	//Attenzione: a causa della lazy evaluation non copre le condizioni
	//            "b" e "c > 5"
	@Test
	public void testFoo_coperturaDecisioni2() {
		assertEquals(2, EsempioPerCoverageConLazy.foo(11, true, 1));
	}

	//copre la condizione:
	//- "b" a false
	//- "c > 5" a true
	@Test
	public void testFoo_coperturaCondizioni3() {
		assertEquals(1, EsempioPerCoverageConLazy.foo(1, false, 6));
	}

	//copre la condizione:
	//- "c > 5" a false
	@Test
	public void testFoo_coperturaCondizioni4() {
		assertEquals(2, EsempioPerCoverageConLazy.foo(1, false, 1));
	}
}
