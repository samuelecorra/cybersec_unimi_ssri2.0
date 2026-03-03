import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OstelloTest {

	//un caso di test che mostri che e' possibile trovare un
	//letto libero ed occuparlo (basandosi sul valore ritornato dal metodo)
	@Test
	public void testCheckin1() {
		Ostello o = new Ostello();
		assertTrue(o.checkin(0, 0));
	}

	//un caso di test che mostri che non e' possibile occupare un
	//letto gia' occupato (basandosi sul valore ritornato dal metodo)
	@Test
	public void testCheckin2() {
		Ostello o = new Ostello();
		assertTrue(o.checkin(0, 0));
		assertFalse(o.checkin(0, 0));
	}

	//un caso di test che mostri che se si mettono 5 persone nell'ultima stanza
	//e poi si ottimizza l'occupazione delle stanze dell'ostello, gli ospiti
	//sono spostati tutti nella prima stanza; rendere pubblico il campo
	//"lettoLibero"	per facilitare la scrittura del test
	@Test
	public void testOttimizzaOstello() {
		Ostello o = new Ostello();
		for(int l = 0; l < 5; l++) {
			assertTrue(o.checkin(9, l));
		}
		for(int s = 0; s < 10; s++) {
			for(int l = 0; l < 5; l++) {
				if(s == 9) {
					assertFalse(o.lettoLibero[s][l]);
				}
				else {
					assertTrue(o.lettoLibero[s][l]);
				}
			}
		}
		o.ottimizzaOstello();
		for(int s = 0; s < 10; s++) {
			for(int l = 0; l < 5; l++) {
				if(s == 0) {
					assertFalse(o.lettoLibero[s][l]);
				}
				else {
					assertTrue(o.lettoLibero[s][l]);
				}
			}
		}
	}
}
