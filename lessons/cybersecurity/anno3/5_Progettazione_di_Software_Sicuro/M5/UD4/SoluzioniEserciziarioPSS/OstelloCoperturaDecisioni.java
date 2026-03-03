import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Scrivere in JUnit una test suite per il metodo "checkin" che soddisfi la
//copertura delle decisioni ma che non soddisfi anche la copertura
//delle condizioni

//decisioni di "checkin":
//stanza >= 0 & stanza < 10 & letto >= 0 & letto < 5
//lettoLibero[stanza][letto]
public class OstelloCoperturaDecisioni {

	//copre:
	//stanza >= 0 & stanza < 10 & letto >= 0 & letto < 5 a true
	//lettoLibero[stanza][letto] a true
	@Test
	public void test1() {
		Ostello o = new Ostello();
		assertTrue(o.checkin(0, 0));
	}

	//copre:
	//lettoLibero[stanza][letto] a false
	@Test
	public void test2() {
		Ostello o = new Ostello();
		assertTrue(o.checkin(0, 0));
		assertFalse(o.checkin(0, 0));
	}

	//copre:
	//stanza >= 0 & stanza < 10 & letto >= 0 & letto < 5 a false
	@Test
	public void test3() {
		Ostello o = new Ostello();
		assertFalse(o.checkin(-1, 0));
	}
}
