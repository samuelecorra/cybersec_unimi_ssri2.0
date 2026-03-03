import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class UndirectedGraphTest {

	//un caso di test che mostri che, se gli indici sono corretti,
	//l'arco viene aggiunto
	@Test
	public void testAddArcOk() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertTrue(dg.addArc(2, 3));
	}

	//un caso di test che mostri che, se gli indici non sono corretti,
	//l'arco non viene aggiunto
	@Test
	public void testtestAddArcNo() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertFalse(dg.addArc(-2, 3));
	}

	//un caso di test che mostri che un determinato grafo da voi costruito
	//contiene un ciclo di lunghezza 3
	@Test
	public void testContainsCycle3() {
		UndirectedGraph dg = new UndirectedGraph(5);
		dg.addArc(2, 3);
		assertFalse(dg.cycle3());
		dg.addArc(3, 4);
		assertFalse(dg.cycle3());
		dg.addArc(4, 2);
		assertTrue(dg.cycle3());
	}
}