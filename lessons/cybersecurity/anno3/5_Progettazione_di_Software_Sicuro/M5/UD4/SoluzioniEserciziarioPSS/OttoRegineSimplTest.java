import static org.junit.Assert.*;
import org.junit.Test;

public class OttoRegineSimplTest {

	//Un caso di test in cui si mostri che il posizionamento di due regine,
	//la prima in (i, j) e la seconda in (h, k) (con (i, j) ed (h, k) scelti da voi
	//in modo che le regine non possano catturarsi), avviene in modo corretto.
	@Test
	public void testPlace1() {
		OttoRegineSimpl or = new OttoRegineSimpl();
		or.place(0, 0);
		assertTrue(or.getCell(0, 0));
		or.place(1, 7);
		assertTrue(or.getCell(1, 7));
	}

	//Un caso di test in cui si mostri che il posizionamento di una regina in una cella
	//in cui puo' essere catturata viene eseguito portando la scacchiera in una configurazione errata.
	//Il caso di test si aspetta che, se si prova a posizionare una regina in una cella in cui puo' essere
	//catturata, il posizionamento non dovrebbe essere eseguito: invece il metodo esegue il posizionamento.
	@Test
	public void testPlace2() {
		OttoRegineSimpl or = new OttoRegineSimpl();
		or.place(0, 0);
		assertTrue(or.getCell(0, 0));
		or.place(0, 1);
		assertFalse(or.getCell(0, 1));
	}

	//Un caso di test che evidenzi che la scacchiera impostata dal costruttore
	//non contiene la soluzione del rompicapo.
	@Test
	public void testIsSolved1() {
		OttoRegineSimpl or = new OttoRegineSimpl();
		assertFalse(or.isSolved());
	}

	//Un caso di test che evidenzi che e' possibile eseguire una serie di 8
	//posizionamenti che portano alla soluzione del rompicapo.
	@Test
	public void testIsSolved2() {
		OttoRegineSimpl or = new OttoRegineSimpl();
		or.place(0, 0);
		or.place(1, 2);
		or.place(2, 5);
		or.place(3, 6);
		or.place(4, 3);
		or.place(5, 4);
		or.place(6, 7);
		or.place(7, 1);
		assertTrue(or.isSolved());
	}
}