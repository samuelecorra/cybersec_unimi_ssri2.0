public class Tombola {
	public int[] tabellone;

	public Tombola() {
		tabellone = new int[90];
	}

	//non esiste una cella che contiene piu' di una pedina
	//@ ensures !(\exists int i; i >= 0 && i < 90; tabellone[i] > 1);
	//il prodotto di tutte le celle e' minore o uguale a 1
	//@ ensures (\product int i; i >= 0 && i < 90; tabellone[i]) <= 1;
	public void addPedina(int numero) {
		if(numero >= 1 && numero <= 90 && tabellone[numero - 1] == 0) {
			tabellone[numero - 1] = tabellone[numero - 1] + 1;
		}
	}

	//l'array "numeroCartella" e l'array "numeroPresente" sono entrambi lunghi 5 posizioni  
	//@ requires numeroCartella.length == 5 && numeroPresente.length == 5;
	//tutti i numeri contenuti in "numeroCartella" sono compresi nell'intervallo [1,90]
	//@ requires (\forall int i; i >= 0 && i < 5; numeroCartella[i] > 0 && numeroCartella[i] <= 90);
	public boolean isCorrect(int[] numeroCartella, boolean[] numeroPresente) {
		for(int i = 0; i < numeroCartella.length; i++) {
			int index = numeroCartella[i] - 1;
			if((numeroPresente[i] && tabellone[index] == 0) ||
			   (!numeroPresente[i] && tabellone[index] > 0)) {
				return false;
			}
		}
		return true;
	}
}