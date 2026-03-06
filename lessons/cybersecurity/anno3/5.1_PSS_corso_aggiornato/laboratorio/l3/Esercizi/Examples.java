public class Examples {

	//Precondizione: i parametri di input devono essere non negativi
	//@ requires  a>= 0 && b >=0;
	//Postcondizione: il risultato deve essere il massimo tra i due parametri
	//@ ensures (a >= b && \result == a) || (b > a && \result == b);
	//oppure, in modo equivalente
	//@ ensures (a >= b ==> \result == a) && (b > a ==> \result == b);
	static int maxPositiveIntegers(int a, int b) {
		return Math.max(a, b);
	}
	

	//Versione di maxPositiveIntegers che puo' far violare la
	//postcondizione quando a>b
	//@ requires a >= 0 && b >=0;
	//@ ensures (a >= b && \result == a) || (b > a && \result == b);
	static int maxPositiveIntegersWrong(int a, int b) {
		return Math.max(a, b) + (a>b?1:0);
	}

	//Precondizione: l'array deve essere diverso da null e ordinato.
	//Postcondizione: se l'elemento e' presente deve ritornarne l'indice, altrimenti
	//                deve ritornare -1.
	/*@ requires arr != null &&
	  @          (\forall int i; i >= 0 && i < arr.length - 1; arr[i] <= arr[i + 1]);
	  @
	  @ ensures (!(\exists int i; i >= 0 && i < arr.length; arr[i]==key) && \result == -1) ||
	  @           (\exists int i; i >= 0 && i < arr.length; arr[i]==key && \result == i);
	  @*/
	static int binarySearch(int[] arr, int key) {
		int start = 0;
		int end = arr.length - 1;
		int middle = 0;
		while (start <= end) {
			middle = (start + end) / 2;
			if (key < arr[middle]) {
				end = middle - 1;
			} else {
				if (key > arr[middle]) {
					start = middle + 1;
				} else {
					return middle;
				}
			}
		}
		return -1;
	}

	//Versione errata di binarySearch, per visualizzare la violazione
	//della prima parte della postcondizione.
	/*@ requires arr != null &&
	  @          (\forall int i; i >= 0 && i < arr.length - 1; arr[i] <= arr[i + 1]);
	  @
	  @ ensures (!(\exists int i; i >= 0 && i < arr.length; arr[i]==key) && \result == -1) ||
	  @           (\exists int i; i >= 0 && i < arr.length; arr[i]==key && \result == i);
	  @*/
	static int binarySearchWrong(int[] arr, int key) {
		int start = 0;
		int end = arr.length - 1;
		int middle = 0;	
		while (start <= end) {
			middle = (start + end) / 2;
			if (key < arr[middle]) {
				end = middle - 1;
			} else {
				if (key > arr[middle]) {
					start = middle + 3;//potrebbe far violare la postcondizione
				} else {
					return middle;
				}
			}
		}
		return -1;
	}

	//Versione errata di binarySearch, per visualizzare la violazione
	//della seconda parte della postcondizione.
	/*@ requires arr != null &&
	  @          (\forall int i; i >= 0 && i < arr.length - 1; arr[i] <= arr[i + 1]);
	  @
	  @ ensures (!(\exists int i; i >= 0 && i < arr.length; arr[i]==key) && \result == -1) ||
	  @           (\exists int i; i >= 0 && i < arr.length; arr[i]==key && \result == i);
	  @*/
	static int binarySearchWrong2(int[] arr, int key) {
		int start = 0;
		int end = arr.length - 1;
		int middle = 0;
		while (start <= end) {
			middle = (start + end) / 2;
			if (key < arr[middle]) {
				end = middle - 1;
			} else {
				if (key > arr[middle]) {
					start = middle + 1;
				} else {
					return middle + 1;//puo' causare la violazione della postcondizione
				}
			}
		}
		return -1;
	}

	//Precondizione: ci deve essere un solo elemento dell'array che vale true.
	//Postcondizione: il risultato deve essere l'indice dell'elemento che vale true.
	/*@ 
	  @ requires (\num_of int i; i >= 0 && i < arrBool.length; arrBool[i]) == 1; 
	  @
	  @ ensures arrBool[\result];
	  @*/
	public static int getTrueIndex(/*@ non_null @*/boolean[] arrBool) {
		for(int i = 0; i < arrBool.length; i++) {
			if(arrBool[i]) {
				return i;
			}
		}
		return -1;
	}

	//La postcondizione richiede che il risultato sia il massimo elemento dell'array.
	/*@ 
	  @ ensures \result == (\max int i; i >=0 && i < arr.length; arr[i]);
	  @*/
	public static int getMaxArr(/*@ non_null @*/int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	//La postcondizione richiede che il risultato sia il minimo elemento dell'array.
	/*@ 
	  @ ensures \result == (\min int i; i >=0 && i < arr.length; arr[i]);
	  @*/
	public static int getMinArr(/*@ non_null @*/int[] arr) {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}

	private static String printArray(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(arr != null) {
			for(int i = 0; i < arr.length - 1; i++) {
				sb.append(arr[i] + ", ");
			}
			sb.append(arr[arr.length - 1]);
		}
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {
		//maxPositiveIntegers soddisfa sempre la postcondizione
	System.out.println("Massimo tra 1 e 2 = " + maxPositiveIntegers(1, 2));//chiamata corretta che soddisfa la precondizione
		//System.out.println("Massimo tra 3 e 1 = " + maxPositiveIntegers(3, 1));//chiamata corretta che soddisfa la precondizione
		//System.out.println("Massimo tra 4 e 4 = " + maxPositiveIntegers(4, 4));//chiamata corretta che soddisfa la precondizione
		System.out.println("Massimo tra -1 e 2 = " + maxPositiveIntegers(-1, 2));//chiamata errata che viola la precondizione

		//maxPositiveIntegersWrong puo' far fallire la postcondizione
		//System.out.println("Massimo tra 1 e 4 = " + maxPositiveIntegersWrong(1, 4));//non viene violata la postcondizione
		//System.out.println("Massimo tra 4 e 1 = " + maxPositiveIntegersWrong(4, 1));//viene violata la postcondizione

		//-----------------------------------------------------------

		//Chiamate a binarySearch che rispettano sia la precondizione (chiamate con input corretto)
		//che le postcondizioni (perche' il metodo e' corretto)
		int[] arrSorted = {1, 3, 4, 6};
		//System.out.println("Indice di 3 in " + printArray(arrSorted) + ": " + binarySearch(arrSorted, 3));
		//System.out.println("Indice di 10 in " + printArray(arrSorted) + ": " + binarySearch(arrSorted, 10));

		//per testare la prima parte della precondizione: arr != null 
		//int[] arrNull = null;
		//System.out.println(binarySearch(arrNull, 3));//chiamata errata che viola la precondizione che l'array sia non nullo
		//per testare la seconda parte della precondizione: (\forall int i; i >= 0 && i < arr.length - 1; arr[i] <= arr[i + 1]);
		int[] arrNotSorted = {1, 5, 4, 6};
		//System.out.println("Indice di 3 in " + printArray(arrNotSorted) + ": " + binarySearch(arrNotSorted, 3));//chiamata errata che viola la precondizione che l'array sia ordinato

		//-----------------------------------------------------------

		//binarySearchWrong puo' far fallire la postcondizione perche' puo' ritornare -1
		//anche quando non dovrebbe
		int[] arrSorted2 = {1, 3, 4, 4, 5, 6};
		//System.out.println("Indice di 5 in " + printArray(arrSorted2) + ": " + binarySearchWrong(arrSorted2, 5));//viene violata la postcondizione
		//System.out.println("Indice di 1 in " + printArray(arrSorted2) + ": " + binarySearchWrong(arrSorted2, 1));//non viene violata la postcondizione

		//-----------------------------------------------------------

		//binarySearchWrong2 puo' far fallire la postcondizione perche' puo'
		//ritornare un indice non corretto (incrementato di 1 rispetto all'indice corretto)
		int[] arrSorted3 = {1, 3, 4, 5, 6};
		//System.out.println("Indice di 4 in " + printArray(arrSorted3) + ": " + binarySearchWrong2(arrSorted3, 4));//viene violata la postcondizione
		int[] arrSorted4 = {1, 3, 4, 4, 6};
		//System.out.println("Indice di 4 in " + printArray(arrSorted4) + ": " + binarySearchWrong2(arrSorted4, 4));//non viene violata la postcondizione

		//-----------------------------------------------------------

		boolean[] arrBool = {true, false, false};
		//System.out.println("Indice del true = " + getTrueIndex(arrBool));//non viene violata la precondizione
		boolean[] arrBool2 = {true, false, true};
		//System.out.println("Indice del true = " + getTrueIndex(arrBool2));//viene violata la precondizione

		//-----------------------------------------------------------

		int[] arr = {3, 54, -3, 5};
		//System.out.println("Il massimo elemento di " + printArray(arr) + " e' " + getMaxArr(arr));//la postcondizione e' soddisfatta
		//System.out.println("Il minimo elemento di " + printArray(arr) + " e' " + getMinArr(arr));//la postcondizione e' soddisfatta
	}
}
