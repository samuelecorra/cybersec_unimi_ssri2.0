import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

/**
 * Contiene una serie di test che illustrano i vari metodi che
 * possono essere utilizzati per il testing con JUnit.
 *
 */
public class EsempiAssert {

	@Test
	public void assertEqualsExampleTest1() {
		int expected = 4;
		int actual = Esempi.max(2, 4);
		assertEquals(expected, actual);//test ok
	}	

	@Test
	public void assertEqualsExampleTest2() {
		int expected = 4;
		int actual = Esempi.maxWrong(2, 4);//metodo max errato
		assertEquals(expected, actual);//il test fallisce
	}

	@Test
	public void assertEqualsMessageExampleTest1() {
		int expected = 4;
		int actual = Esempi.max(2, 4);
		String message = "Errore. Sarebbe dovuto essere " + expected + " e invece e' " + actual;
		assertEquals(message, expected, actual);//test ok
	}

	@Test
	public void assertEqualsMessageExampleTest2() {
		int expected = 4;
		int actual = Esempi.maxWrong(2, 4);//metodo max errato
		String message = "Errore. Sarebbe dovuto essere " + expected + " e invece e' " + actual;
		assertEquals(message, expected, actual);//il test fallisce
	}

	/**
	 * void org.junit.Assert.assertEquals(double expected, double actual, double delta)
	 * Asserts that two doubles or floats are equal to within a positive delta.
	 * If they are not, an AssertionError is thrown. If the expected value is infinity
	 * then the delta value is ignored.
	 */
	@Test
	public void assertEqualsDouble1() {
		double sqrt = Math.sqrt(25);
		assertEquals(5, sqrt, 0);//il delta e' zero perche' siamo sicuri che non ci siano errori di approssimazione
	}

	/**
	 * Accettiamo un margine di incertezza di 10^-4.
	 * Il test e' superato se 4.8989 - 10^-4 <= sqrt <= 4.8989 + 10^-4   
	 */
	@Test
	public void assertEqualsDouble2() {
		double sqrt = Math.sqrt(24);
		assertEquals(4.8989, sqrt, 0.0001);
	}

	@Test
	public void assertEqualsPunto2D() {
		Punto2D p1 = new Punto2D(2, 5);
		Punto2D p2 = new Punto2D(2, 5);
		assertEquals(p1, p2);//ok. assertEquals confronta le stringhe con equals
		assertNotSame(p1, p2);//ok. assertNotSame confonta che i riferimenti siano diversi
	}

	@Test
	public void assertSameExampleTest1() {
		String str1 = new String("pippo");
		String str2 = new String("pippo");
		assertSame(str1, str2);//fallisce perche' same controlla i riferimenti
	}

	@Test
	public void assertSameExampleWithMessageTest1() {
		String str1 = new String("pippo");
		String str2 = new String("pippo");
		String message = "I riferimenti str1 e str2 identificano due oggetti diversi.";
		assertSame(message, str1, str2);//fallisce perche' assertSame controlla i riferimenti
	}	

	@Test
	public void assertSameExampleTest2() {
		String str1 = new String("pippo");
		String str2 = str1;
		assertSame(str1, str2);//ok perche' i riferimenti sono uguali
	}

	@Test
	public void assertNotSameExampleTest1() {
		String str1 = new String("pippo");
		String str2 = new String("pippo");
		assertNotSame(str1, str2);//ok perche' i riferimenti non sono uguali
	}

	@Test
	public void assertNullExampleTest() {
		String str = null;
		assertNull(str);//assertNull controlla se un riferimento e' null
	}

	@Test
	public void assertNotNullExampleTest() {
		Stack<String> stack = new Stack<String>();
		stack.push("pippo");
		assertNotNull(stack.pop());//assertNotNull controlla se un riferimento non e' null
	}

	@Test
	public void assertTrueExampleTest() {
		int[] arr = {1, 2, 3};
		boolean result = Sort.isSortedAsc(arr); 
		//assertTrue(Sort.isSortedAsc(arr));
		assertTrue(result);//assertTrue controlla che l'argomento sia true
	}

	@Test
	public void assertFalseExampleTest() {
		int[] arr = {1, 2, 0};
		assertFalse(Sort.isSortedAsc(arr));//assertFalse controlla che l'argomento sia false
	}

	@Test
	public void failExampleTest() {
		fail("fallito");//fail serve per fare fallire in modo esplicito un test
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testException() {
		int[] arr = new int[4];
		int y = arr[5];
	}
}
