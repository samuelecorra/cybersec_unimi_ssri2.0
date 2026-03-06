/**
 * Metodi usati per mostrare quanto e' facile/difficile scrivere
 * dei test case che generano un failure usando del codice che
 * contiene un fault.
 *
 */
public class SumClass {

	//esegue la somma in modo corretto
	public static int sum(int a, int b) {
		return a + b;
	}

	//il fault e' abbastanza semplice da trovare
	public static int wrongSum1(int a, int b) {
		return a*2;
	}

	//Il fault potrebbe essere piu' difficile da trovare
	//Dipende da come viene fatto il test.
	public static int wrongSum2(int a, int b) {
		return a + Math.max(a, b);//La somma e' errata quando a > b
	}
}
