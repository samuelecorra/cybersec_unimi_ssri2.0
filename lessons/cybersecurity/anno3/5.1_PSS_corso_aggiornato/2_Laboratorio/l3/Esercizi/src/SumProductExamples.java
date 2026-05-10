// Ormai dovreste sapere a menadito l'intuizione geniale
// che ebbe Gauss da bambino per sommare i primi n numeri naturali.
// Vediamo brevemente la sua equazione sommaNaturali = n*(n + 1)/2 e poi
// anche il fattoriale.

public class SumProductExamples {

	// Il tipo di parametro passato assicura che siano interi, ma
	// serve anche una pre che li confermi positivi per ridurre
	// il discorso dall'insieme Z all'insieme N:
	
	// La post invece richiede che venga ritornato un risultato uguale
	// alla SOMMATORIA di tutti i numeri x compresi tra 1 ed n stesso.
	// JML con \sum ci permette di farlo agilmente!
	/*@ requires n > 0; 
	  @
	  @ ensures \result == (\sum int x; x >= 1 && x <= n; x); 
	  @*/
	public static int sumN(int n) {
		return n*(n + 1)/2;
	}

	//---------------------------------------------------------------

	// Per quanto riguarda il fattoriale:

	// La pre deve anche assicuarci di non farci crashare il pc, quindi
	// limitiamo anche da sopra, oltre il 21 i calcoli diventano enormi!

	// La post assicura che il risultato sia proprio la PRODUTTORIA, quindi
	// un'iterazione di prodotti che vanno similmente dal primo numero fino all'n-esimo compreso.
	// Anche in questo esempio, \product facilita la scrittura, mentre nel codice sorgente
	// dobbiamo esplicitare l'algoritmo, in questo caso iterativo.

	/*@ requires n >=0 && n <= 21;
	  @
	  @ ensures \result == (\product int i; i > 0 && i <= n; i); 
	  @*/
	public static long fattoriale(int n) {
		long result = 1l;
		for(int i = 2; i <= n; i++) {
			result = result * i;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(sumN(3));
	}
}
