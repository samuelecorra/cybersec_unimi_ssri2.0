import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ProdConsTest {

	//un caso di test che mostri che, se il buffer non e' pieno,
	//l'inserimento di un prodotto viene eseguito (basandosi sul valore ritornato dal metodo)
	@Test
	public void testProduceWithBufferNotFull() {
		ProdCons p = new ProdCons(2);
		assertTrue(p.produce());
	}

	//un caso di test che mostri che, se il buffer e' pieno,
	//l'inserimento di un prodotto non viene eseguito (basandosi sul valore ritornato dal metodo)
	@Test
	public void testProduceWithBufferFull() {
		ProdCons p = new ProdCons(2);
		p.produce();
		p.produce();
		assertFalse(p.produce());
	}

	//un caso di test che mostri che, appena dopo essere stato creato,
	//il buffer non contiene prodotti (rendere il buffer pubblico per poterlo testare)
	@Test
	public void testConstructorBufferIsEmpty() {
		int[] expected = new int[3];
		ProdCons p = new ProdCons(3);
		assertArrayEquals(expected, p.buffer);
	}
}