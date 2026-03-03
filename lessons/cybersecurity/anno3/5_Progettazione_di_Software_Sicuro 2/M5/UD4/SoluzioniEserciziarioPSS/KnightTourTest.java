import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KnightTourTest {

	//Un caso di test in cui si mostri che una mossa valida del cavallo (una mossa ad L corretta e tale
	//per cui (h, k) identifichino una cella non ancora visitata) avviene in modo corretto: il cavallo viene
	//spostato in (h, k) e la cella (h, k) viene marcata come visitata.
	@Test
	public void testMove1() {
		KnightTour kt = new KnightTour();
		kt.move(1, 2);
		assertEquals(1, kt.getX());
		assertEquals(2, kt.getY());
		assertEquals(true, kt.getCell(1, 2));
	}

	//Un caso di test in cui si mostri che una mossa non valida (non e' una mossa ad L) viene eseguita. Il
	//caso di test si aspetta che, se si prova ad eseguire una mossa errata, la mossa non dovrebbe essere
	//eseguita: invece il metodo esegue la mossa.
	@Test
	public void testMove2() {
		KnightTour kt = new KnightTour();
		kt.move(1, 2);
		kt.move(0, 0);
		assertEquals(1, kt.getX());
		assertEquals(2, kt.getY());
	}

	//Un caso di test che evidenzi che sulla scacchiera iniziale (quella costruita dal costruttore) non e'
	//stato eseguito un tour completo.
	@Test
	public void testIsTourCompleted1() {
		KnightTour kt = new KnightTour();
		assertFalse(kt.isTourCompleted());
	}

	//Un caso di test che evidenzi che e' possibile eseguire una serie di 24 mosse che portano all'esecuzione
	//di un tour completo.
	@Test
	public void testIsTourCompleted2() {
		KnightTour kt = new KnightTour();
		kt.move(2, 1);
		kt.move(4, 0);
		kt.move(3, 2);
		kt.move(4, 4);
		kt.move(2, 3);
		kt.move(0, 4);
		kt.move(1, 2);
		kt.move(2, 4);
		kt.move(4, 3);
		kt.move(3, 1);
		kt.move(1, 0);
		kt.move(0, 2);
		kt.move(1, 4);
		kt.move(3, 3);
		kt.move(4, 1);
		kt.move(2, 0);
		kt.move(0, 1);
		kt.move(1, 3);
		kt.move(3, 4);
		kt.move(4, 2);
		kt.move(3, 0);
		kt.move(2, 2);
		kt.move(0, 3);
		kt.move(1, 1);
		assertTrue(kt.isTourCompleted());
	}
}