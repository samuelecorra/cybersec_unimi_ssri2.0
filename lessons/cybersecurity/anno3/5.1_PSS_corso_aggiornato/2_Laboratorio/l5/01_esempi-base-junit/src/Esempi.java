public class Esempi {
	public static boolean foo(int a, int b) {
		if(a > b)
			return true;
		else
			return false;
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}

	public static int maxWrong(int a, int b) {
		return Math.max(a, b) + 1;
	}

	public static int euclideMCD(int a, int b) {
		int numA = a;
		int numB = b;
		while (numA != numB) {
			if (numA > numB) {
				numA = numA - numB;
			} else {
				numB = numB - numA;
			}
		}
		return numA;
	}

	public static int euclideMCDwrong(int a, int b) {
		int numA = a;
		int numB = b;
		while (numA != numB) {
			if (numA > numB) {
				numA = numA - numB;
			} else {
				numB = numA;//errore
			}
		}
		return numA;
	}

	/**
	 * Dice se n e' un numero di Fibonacci.
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static boolean isFibonacciNumber(int n) {
		if(n < 0) {
			System.out.println("Specificare un numero maggiore o uguale a zero.");
			return false;
		}
		if(n < 4) {
			return true;//0, 1, 2 e 3 sono numeri di Fibonacci
		}
		int penultimo = 2;
		int ultimo = 3;
		int oldUltimo;
		while(ultimo < n) {
			oldUltimo = ultimo;
			ultimo = ultimo + penultimo;
			penultimo = oldUltimo;
			if(ultimo == n) {
				return true;
			}
		}
		return false;
	}
}
