import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Scrivere in JUnit una test suite che soddisfi la copertura delle
//condizioni per il metodo "addArc"

//decisione: nodeA >= 0 && nodeA <= incidenceMatrix.length && nodeB >= 0 && nodeB <= incidenceMatrix.length
public class UndirectedGraphCoperturaCondizioni {

	//copre:
	//nodeA >= 0 a true
	//nodeA <= incidenceMatrix.length a true
	//nodeB >= 0 a true
	//nodeB <= incidenceMatrix.length a true
	@Test
	public void testAddArcCoperturaCondizioni1() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertTrue(dg.addArc(2, 3));
	}

	//copre:
	//nodeA >= 0 a false
	@Test
	public void testAddArcCoperturaCondizioni2() {
		UndirectedGraph dg = new UndirectedGraph(5);
		dg.addArc(-1, 3);
	}

	//copre:
	//nodeA <= incidenceMatrix.length a false
	@Test
	public void testAddArcCoperturaCondizioni3() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertFalse(dg.addArc(6, 3));
	}

	//copre:
	//nodeB >= 0 a false
	@Test
	public void testAddArcCoperturaCondizioni4() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertFalse(dg.addArc(2, -1));
	}

	//copre:
	//nodeB <= incidenceMatrix.length a false
	@Test
	public void testAddArcCoperturaCondizioni5() {
		UndirectedGraph dg = new UndirectedGraph(5);
		assertFalse(dg.addArc(2, 6));
	}
}