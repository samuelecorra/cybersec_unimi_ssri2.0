import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuTest {

	//un caso di test che mostri che, se i parametri attuali sono corretti, il valore viene aggiunto
	//alla scacchiera (basandosi sul valore ritornato dal metodo)
	@Test
	public void testAdd1() {
		Sudoku s = new Sudoku();
		assertTrue(s.add(0, 0, 1));
	}

	//un caso di test in cui si mostri che si puo' aggiungere un numero alla scacchiera che
	//non soddisfa le regole del sudoku (ad esempio un numero gia' contenuto nella riga). Il
	//caso di test si aspetta che, se si prova a mettere un numero in una riga che contiene gia'
	//quel numero in una posizione diversa, l'aggiunta non dovrebbe essere eseguita: invece il
	//metodo esegue l'aggiunta
	@Test
	public void testAdd2() {
		Sudoku s = new Sudoku();
		assertTrue(s.add(0, 0, 1));
		assertFalse(s.add(0, 1, 1));
	}

	//un caso di test che mostri che, se si inseriscono i numeri come mostrato dalla tabella 2
	//il metodo isMaybeSolved dice che il sodoku potrebbe contenere la soluzione
	@Test
	public void testIsMaybeSolved() {
		Sudoku s = new Sudoku();
		assertTrue(s.add(0, 0, 1));
		assertTrue(s.add(0, 1, 2));
		assertTrue(s.add(0, 2, 3));
		assertTrue(s.add(0, 3, 4));
		assertTrue(s.add(1, 0, 3));
		assertTrue(s.add(1, 1, 4));
		assertTrue(s.add(1, 2, 1));
		assertTrue(s.add(1, 3, 2));
		assertTrue(s.add(2, 0, 2));
		assertTrue(s.add(2, 1, 3));
		assertTrue(s.add(2, 2, 4));
		assertTrue(s.add(2, 3, 1));
		assertTrue(s.add(3, 0, 4));
		assertTrue(s.add(3, 1, 1));
		assertTrue(s.add(3, 2, 2));
		assertFalse(s.isMaybeSolved());
		assertTrue(s.add(3, 3, 3));
		assertTrue(s.isMaybeSolved());
	}
}
