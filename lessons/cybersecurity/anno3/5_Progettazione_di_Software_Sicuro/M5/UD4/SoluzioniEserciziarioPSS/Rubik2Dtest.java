import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Rubik2Dtest {

	//un caso di test che mostri che e' possibile eseguire lo spostamento
	//su una colonna (basandosi sul valore ritornato dal metodo
	@Test
	public void testGira1() {
		Rubik2D r = new Rubik2D();
		assertTrue(r.gira(0));
	}

	//un caso di test che mostri che, se si seleziona una colonna inesistente,
	//nessuno spostamento viene eseguito (basandosi sul valore ritornato dal metodo)
	@Test
	public void testGira2() {
		Rubik2D r = new Rubik2D();
		assertFalse(r.gira(5));
	}

	//un caso di test che mostri che e' possibile risolvere la faccia
	@Test
	public void testIsSolved() {
		Rubik2D r = new Rubik2D();
		r.gira(1);
		assertTrue(r.isSolved());
	}
}