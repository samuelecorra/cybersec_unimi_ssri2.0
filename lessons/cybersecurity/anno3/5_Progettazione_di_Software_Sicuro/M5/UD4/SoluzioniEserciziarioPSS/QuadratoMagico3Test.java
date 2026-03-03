import static org.junit.Assert.*;

import org.junit.Test;

public class QuadratoMagico3Test {

	//caso di test in cui si mostra che la modifica di una cella vuota viene
	//eseguita correttamente
	@Test
	public void testSet1() {
		QuadratoMagico3 q = new QuadratoMagico3();
		q.set(0, 0, 1);
		assertEquals(1, q.getCell(0, 0));
	}

	//caso di test in cui si mostra che la modifica di una cella piena viene eseguita
	//in modo errato. Il caso di test si aspetta che, se si prova a modificare una cella
	//piena, la cella non dovrebbe essere modificata: invece il metodo la modifica
	@Test
	public void testSet2() {
		QuadratoMagico3 q = new QuadratoMagico3();
		q.set(0, 0, 1);
		q.set(0, 0, 2);
		assertEquals(1, q.getCell(0, 0));
	}

	//caso di test che evidenzia che e' possibile costruire un quadrato che sia magico
	@Test
	public void testIsMagic1() {
		QuadratoMagico3 q = new QuadratoMagico3();
		q.set(0, 0, 2);
		q.set(0, 1, 7);
		q.set(0, 2, 6);
		q.set(1, 0, 9);
		q.set(1, 1, 5);
		q.set(1, 2, 1);
		q.set(2, 0, 4);
		q.set(2, 1, 3);
		q.set(2, 2, 8);
		assertTrue(q.isMagic());
	}

	//caso di test che evidenzia che e' possibile costruire un quadrato che non sia magico
	@Test
	public void testIsMagic2() {
		QuadratoMagico3 q = new QuadratoMagico3();
		int value = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				q.set(i, j, value);
				value++;
			}
		}
		assertFalse(q.isMagic());
	}
}