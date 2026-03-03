import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TombolaTest {

	//un caso di test che mostri che tutti i numeri compresi
	//nell'intervallo [1,90] possono essere aggiunti al tabellone
	//(rendere pubblico il campo tabellone per facilitare
	//la scrittura del test)
	@Test
	public void testAddPedina() {
		Tombola tombola = new Tombola();
		for(int i = 1; i <= 90; i++) {
			assertEquals(0, tombola.tabellone[i - 1]);
			tombola.addPedina(i);
			assertEquals(1, tombola.tabellone[i - 1]);
		}
	}

	//un caso di test che mostri che il metodo "isCorrect" e' in
	//grado di identificare un giocatore che non bara, cioe' che ha
	//segnato correttamente i numeri che sono usciti sul tabellone
	@Test
	public void testIsCorrect1() {
		Tombola tombola = new Tombola();
		tombola.addPedina(2);
		tombola.addPedina(23);
		tombola.addPedina(45);
		int[] cartella = {2, 12, 24, 32, 45};
		boolean[] usciti = {true, false, false, false, true};
		assertTrue(tombola.isCorrect(cartella, usciti));
	}

	//un caso di test che mostri che il metodo "isCorrect" e' in
	//grado di scoprire un giocatore che bara, cioe' che ha segnato dei
	//numeri che non sono usciti sul tabellone
	@Test
	public void testIsCorrect2() {
		Tombola tombola = new Tombola();
		tombola.addPedina(2);
		tombola.addPedina(24);
		tombola.addPedina(45);
		int[] cartella = {1, 12, 45, 50, 72};
		//il giocatore bara, perche' il numero 12 non e' uscito
		boolean[] usciti = {false, true, true, false, false};
		assertFalse(tombola.isCorrect(cartella, usciti));
	}
}