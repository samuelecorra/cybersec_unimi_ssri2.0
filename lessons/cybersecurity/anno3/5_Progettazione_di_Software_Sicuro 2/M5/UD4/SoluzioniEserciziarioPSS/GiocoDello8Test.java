import static org.junit.Assert.*;

import org.junit.Test;

public class GiocoDello8Test {
	//un caso di test in cui si mostri che lo spostamento di una tessera da (i, j) a (h, k)
	//(scelti da voi in modo che identifichino celle contigue, che (i, j) contenga una tessera, e che (h, k) sia vuota)
	//avviene in modo corretto.
	@Test
	public void testMove1() {
		GiocoDello8 g = new GiocoDello8();
		assertEquals(6, g.get(0, 2));
		assertEquals(0, g.get(1, 2));
		g.move(0, 2, 1, 2);
		assertEquals(0, g.get(0, 2));
		assertEquals(6, g.get(1, 2));
	}

	//un caso di test in cui si mostri che lo spostamento di una tessera da una cella piena (i, j) a una
	//cella piena (h, k) viene eseguito portando la griglia in una configurazione errata (sparisce la tessera
	//presente nella cella di destinazione). Il caso di test si aspetta che, se si prova a spostare una tessera in
	//una cella piena, lo spostamento non dovrebbe essere eseguito: invece il metodo esegue lo spostamento.
	@Test
	public void testMove2() {
		GiocoDello8 g = new GiocoDello8();
		assertEquals(2, g.get(0, 0));
		assertEquals(3, g.get(0, 1));
		g.move(0, 0, 0, 1);
		assertEquals(2, g.get(0, 0));
		assertEquals(3, g.get(0, 1));
	}

	//scrivere in JUnit un caso di test che evidenzi che la griglia impostata dal
	//costruttore non contiene la soluzione del rompicapo
	@Test
	public void testIsSolved1() {
		GiocoDello8 g = new GiocoDello8();
		assertFalse(g.isSolved());
	}

	//scrivere in JUnit un caso di test che evidenzi che e' possibile eseguire una serie di
	//7 mosse che portano alla soluzione del rompicapo
	@Test
	public void testIsSolved2() {
		GiocoDello8 g = new GiocoDello8();
		g.move(0, 2, 1, 2);
		g.move(0, 1, 0, 2);
		g.move(0, 0, 0, 1);
		g.move(1, 0, 0, 0);
		g.move(2, 0, 1, 0);
		g.move(2, 1, 2, 0);
		g.move(2, 2, 2, 1);
		assertTrue(g.isSolved());
	}
}
