import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SokobanTest {

	//un caso di test che mostri che, se il giocatore si trova di fronte alla parete nord,
	//non puo' muoversi verso l'alto (basarsi sul valore ritornato dal metodo)
	@Test
	public void testMove1() {
		Sokoban s = new Sokoban();
		System.out.println(s.print());
		assertFalse(s.move(1));
		System.out.println(s.print());
	}

	//un caso di test che mostri una situazione in cui il giocatore
	//riesce a spostare la cassa (basarsi sul valore ritornato dal metodo)
	@Test
	public void testMove2() {
		Sokoban s = new Sokoban();
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
	}

	//un caso di test che mostri una situazione in cui il giocatore
	//non riesce a spostare la cassa (basarsi sul valore ritornato dal metodo)
	@Test
	public void testMove3() {
		Sokoban s = new Sokoban();
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.move(1));
		System.out.println(s.print());
		assertFalse(s.move(1));
		System.out.println(s.print());
	}
	
	//un caso di test che mostri che il giocatore puo' spostare
	//la cassa in basso a destra (in posizione (4, 4));
	//rendere pure pubblici i campi della classe per eseguire il test
	@Test
	public void testMove4() {
		Sokoban s = new Sokoban();
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(4));
		System.out.println(s.print());
		assertTrue(s.move(3));
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.move(2));
		System.out.println(s.print());
		assertTrue(s.getCell(4, 4));
	}
}