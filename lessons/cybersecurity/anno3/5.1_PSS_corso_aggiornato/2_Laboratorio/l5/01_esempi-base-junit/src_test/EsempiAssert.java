// ===========================================================================================
// IMPORT PER JUNIT - Cosa sono e perché ci servono
// ===========================================================================================

// Partiamo da "import": in Java, quando vogliamo usare codice scritto da altri
// (librerie, framework, strumenti...), dobbiamo dire al compilatore DOVE andare
// a prendere quel codice. È come dire: "Ehi Java, vai in questo scaffale della
// libreria e prendimi questo libro". Senza l'import, Java non sa che quel codice esiste.

// Ora, cosa significa "static"? Normalmente quando importiamo una classe,
// per usare i suoi metodi dobbiamo scrivere: NomeClasse.nomeMetodo().
// Con "import static" invece possiamo usare i metodi DIRETTAMENTE, senza
// ripetere ogni volta il nome della classe davanti. È una comodità sintattica.

// Esempio pratico:
//   SENZA static import → Assert.assertEquals(4, risultato);
//   CON static import   → assertEquals(4, risultato);        ← più pulito!

// E quel ".*" finale? L'asterisco è un cosiddetto "wildcard" (carattere jolly).
// Significa: "importami TUTTI i metodi statici di quella classe".
// Potremmo anche importarli uno per uno, tipo:
//   import static org.junit.Assert.assertEquals;
//   import static org.junit.Assert.assertTrue;
//   import static org.junit.Assert.assertFalse;
//   ...e così via per ognuno.
// Ma con .* li prendiamo tutti in un colpo solo. Comodo, no?

// Infine, scomponiamo il percorso "org.junit.Assert":
//   - "org"    → sta per "organization". È una convenzione nei nomi dei pacchetti
//                Java: i progetti open-source usano "org", le aziende "com"
//                (es: com.google, com.microsoft). È solo un modo per organizzare
//                il codice ed evitare conflitti di nomi tra librerie diverse.
//   - "junit"  → è il nome del framework di testing che stiamo usando.
//                JUnit è LO strumento standard per scrivere test in Java.
//   - "Assert" → è la classe specifica che contiene i metodi di "asserzione",
//                cioè quei metodi che ci permettono di VERIFICARE se il nostro
//                codice si comporta come ci aspettiamo. I principali sono:
//                  • assertEquals(atteso, ottenuto)  → controlla che due valori siano uguali
//                  • assertTrue(condizione)          → controlla che qualcosa sia true
//                  • assertFalse(condizione)         → controlla che qualcosa sia false
//                  • assertNull(oggetto)             → controlla che qualcosa sia null
//                  • assertNotNull(oggetto)          → controlla che qualcosa NON sia null
//                Se l'asserzione fallisce, il test viene marcato come FALLITO (rosso).
//                Se passa, il test è SUPERATO (verde). Semplice.

import static org.junit.Assert.*;

// Qui importiamo la classe Stack da java.util.
// "java.util" è un pacchetto della libreria standard di Java (quella che viene
// già installata con il JDK, non devi scaricare niente) e contiene strutture
// dati utilissime: liste, mappe, code, pile (stack), ecc.
// In questo caso ci serve Stack perché è la classe che vogliamo TESTARE.
// In generale, qui importerai qualsiasi classe su cui vuoi scrivere i test.

import java.util.Stack;

// Questa è l'importazione dell'annotazione @Test.
// Ma cos'è un'annotazione? In Java, le annotazioni sono quei "tag" che iniziano
// con la @ (chiocciola) e si mettono SOPRA un metodo o una classe per dare
// istruzioni speciali al compilatore o al framework.
// 
// @Test in particolare dice a JUnit: "Ehi, il metodo qui sotto NON è un metodo
// normale! È un TEST. Quando l'utente clicca 'Run Test' su VSCode (o lancia
// i test da terminale), DEVI eseguire questo metodo e dirmi se passa o fallisce."
//
// Senza @Test, JUnit ignorerebbe completamente il metodo: lo tratterebbe
// come un metodo qualsiasi e non lo eseguirebbe mai come test.
//
// In VSCode vedrai comparire un piccolo pulsante "Run Test" (▶) proprio sopra
// ogni metodo annotato con @Test. Cliccandolo, VSCode esegue SOLO quel test
// e ti mostra il risultato: spunta verde ✓ se passa, croce rossa ✗ se fallisce.

import org.junit.Test;

// Testiamo le classi src/Esempi.java e Punto2D, che contengono metodi di esempio (alcuni corretti, altri con errori intenzionali) su cui scriveremo i nostri test.
// L'obiettivo è imparare a usare le asserzioni di JUnit per verificare se i metodi funzionano come ci aspettiamo.
// Nel sibling file di questa folder, ovvero EsempiAssertArrayEquals, testeremo invece la classe src/Sort.java

public class EsempiAssert { // Possiamo anche runnare tutti i test di questa classe insieme, cliccando su "Run Test" accanto al nome della classe.

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
