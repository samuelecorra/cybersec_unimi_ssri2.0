import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NaturalSetCoperturaDecisioni {

	//"i < set.length" a true
	//"set[i] == value" a true
	@Test
	public void testCoperturaDecisioni1() {
		NaturalSet set = new NaturalSet(10);
		set.add(2);
		assertEquals(2, set.remove(2));
	}

	//"i < set.length" a false
	//"set[i] == value" a false
	@Test
	public void testCoperturaDecisioni2() {
		NaturalSet set = new NaturalSet(10);
		assertEquals(-1, set.remove(1));
	}
}