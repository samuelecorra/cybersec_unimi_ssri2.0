import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueueTest {

	//un caso di test che mostri che, se si prova ad prelevare
	//un elemento dalla coda vuota, viene ritornato null
	@Test
	public void testPopEmptyQueue() {
		Queue q = new Queue(7);
		assertNull(q.pop());
	}

	//un caso di test che mostri che, se si prova ad aggiungere
	//una stringa alla coda piena, la stringa non viene aggiunta
	//(basandosi sul valore ritornato dal metodo)
	@Test
	public void testAddFullQueue() {
		Queue q = new Queue(4);
		assertTrue(q.add("a"));
		assertTrue(q.add("b"));
		assertTrue(q.add("c"));
		assertTrue(q.add("d"));
		assertFalse(q.add("e"));
	}

	//un caso di test che mostri che il funzionamento della coda
	//e' effettivamente FIFO
	@Test
	public void testQueueIsFIFO() {
		Queue q = new Queue(3);
		q.add("a");
		q.add("b");
		q.add("c");
		assertEquals("a", q.pop());
		assertEquals("b", q.pop());
		assertEquals("c", q.pop());
	}

	//un caso di test che testi il funzionamento del
	//metodo numberOfDistinctWords
	@Test
	public void testNumberOfDistinctWords() {
		Queue q = new Queue(3);
		q.add("a");
		assertEquals(1, q.numberOfDistinctWords());
		q.add("b");
		assertEquals(2, q.numberOfDistinctWords());
		q.add("a");
		assertEquals(2, q.numberOfDistinctWords());
	}
}