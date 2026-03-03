import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OraDiPuntaTest {

	//un caso di test che mostri che, se si prova a spostare una macchina in una cella libera,
	//lo spostamento viene eseguito (basandosi sul valore ritornato dal metodo)
	@Test
	public void test1() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(1, 5, 1));
	}

	//un caso di test che mostri che, se si prova a spostare una macchina in una cella occupata,
	//lo spostamento non viene eseguito (basandosi sul valore ritornato dal metodo)
	@Test
	public void test2() {
		OraDiPunta r = new OraDiPunta();
		assertFalse(r.spostaMacchina(2, 5, 1));
	}

	//un caso di test che mostri che e' possibile che portare la macchina rossa all'uscita
	@Test
	public void testGameOk() {
		OraDiPunta r = new OraDiPunta();
		assertFalse(r.macchinaRossaUscita());
		r.spostaMacchina(1,5,1);
		assertFalse(r.macchinaRossaUscita());
		r.spostaMacchina(2,5,1);
		assertFalse(r.macchinaRossaUscita());
		r.spostaMacchina(2,2,2);
		assertFalse(r.macchinaRossaUscita());
		r.spostaMacchina(2,3,2);
		assertFalse(r.macchinaRossaUscita());
		r.spostaMacchina(2,4,2);
		assertTrue(r.macchinaRossaUscita());
	}
}