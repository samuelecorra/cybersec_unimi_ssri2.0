import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Scrivere in JUnit una test suite per il metodo "gira"
//che soddisfi la copertura delle condizioni

//decisione: idCol >= 0 && idCol < 3
public class Rubik2DCondizioni {

	//copre:
	//idCol >= 0 a true
	//idCol < 3 a true
	@Test
	public void test1() {
		Rubik2D r = new Rubik2D();
		assertTrue(r.gira(0));
	}

	//copre:
	//idCol >= 0 a false
	@Test
	public void test2() {
		Rubik2D r = new Rubik2D();
		assertFalse(r.gira(-1));
	}

	//copre:
	//idCol < 3 a false
	@Test
	public void test3() {
		Rubik2D r = new Rubik2D();
		assertFalse(r.gira(5));
	}
}