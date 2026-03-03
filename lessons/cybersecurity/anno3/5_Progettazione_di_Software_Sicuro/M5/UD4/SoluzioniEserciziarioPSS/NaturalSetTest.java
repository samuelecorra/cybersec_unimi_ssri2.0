import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class NaturalSetTest {

	//un caso di test che mostri che, se si prova ad aggiungere due
	//volte lo stesso elemento, la seconda volta l'elemento non viene
	//aggiunto (basandosi sul valore ritornato dal metodo)
	@Test
	public void testAdd1() {
		NaturalSet set = new NaturalSet(10);
		assertTrue(set.add(3));
		assertFalse(set.add(3));
	}

	//un caso di test che mostri che, se si prova ad aggiungere un numero negativo,
	//il numero viene aggiunto (basandosi sul valore ritornato dal metodo);
	//il caso di test si aspetta che il codice sia corretto e quindi che, se si
	//aggiunge un numero negativo, il numero non dovrebbe essere aggiunto: il metodo,
	//invece, aggiunge l'elemento
	@Test
	public void testAdd2() {
		NaturalSet set = new NaturalSet(10);
		assertFalse(set.add(-3));
	}

	//un caso di test che mostri che in un insieme inizializzato con 10 posizioni
	//libere si possono inserire 10 elementi distinti
	@Test
	public void testAdd3() {
		NaturalSet set = new NaturalSet(10);
		for(int i = 0; i < 10; i++) {
			assertTrue(set.add(i));
		}
	}
}