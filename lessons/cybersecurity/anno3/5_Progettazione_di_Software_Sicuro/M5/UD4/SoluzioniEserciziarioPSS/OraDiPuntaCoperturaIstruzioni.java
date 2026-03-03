import static org.junit.Assert.*;

import org.junit.Test;

//Scrivere in JUnit una test suite per il metodo "spostaMacchina" che soddisfi
//la copertura delle istruzioni

public class OraDiPuntaCoperturaIstruzioni {

	//copre istruzioni: da 25 a 32
	@Test
	public void test1() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(2, 2, 1));
	}

	//copre istruzioni: da 35 a 39
	@Test
	public void test2() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(2, 2, 2));
	}

	//copre istruzioni: da 42 a 46
	@Test
	public void test3() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(2, 2, 3));
	}

	//copre istruzioni: da 49 a 53
	@Test
	public void test4() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(2, 2, 4));
	}

	//copre istruzioni: 34 e 60
	@Test
	public void test5() {
		OraDiPunta r = new OraDiPunta();
		assertFalse(r.spostaMacchina(2, 5, 1));
	}

	//copre istruzioni: 41
	@Test
	public void test6() {
		OraDiPunta r = new OraDiPunta();
		assertFalse(r.spostaMacchina(2, 5, 2));
	}

	//copre istruzioni: 48
	@Test
	public void test7() {
		OraDiPunta r = new OraDiPunta();
		assertFalse(r.spostaMacchina(2, 5, 3));
	}

	//copre istruzioni: 55
	@Test
	public void test8() {
		OraDiPunta r = new OraDiPunta();
		assertTrue(r.spostaMacchina(4, 1, 4));
		assertFalse(r.spostaMacchina(4, 0, 4));
	}
}