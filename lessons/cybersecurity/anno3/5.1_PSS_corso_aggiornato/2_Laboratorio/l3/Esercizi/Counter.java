public class Counter {
	
	//Le specifiche dei metodi  prendono la visibilita' del metodo.
	//Inoltre non possono accedere a campi con visibilita' inferiore.
	//Poiche' incr() e decr sono public, anche le loro specifiche sono
	//public. Tali specifiche, quindi, non possono accedere al campo "counter" perche'
	//e' privato.
	//E' possibile, pero', definire la visibilita' dei campi all'interno delle specifiche.
	//In questo caso, con il modificatore "spec_public", abbiamo detto che il campo "counter"
	//deve essere trattato come pubblico all'interno delle specifiche JML.
	private /*@ spec_public @*/ int counter;

	Counter() {
		counter = 0;
	}

	//Solo i metodi puri possono essere utilizzati nelle precondizioni e nelle postcondizioni.
	//Un metodo puro e' un metodo che non modifica lo stato del programma.
	//Per identificare un metodo come puro lo si puo' dichiarare con il modificatore "pure".
	//In questo primo esempio, il "Getter", ovvero il metodo che deve solo recuperare il valore di
	// di un variabile della classe, è puro al 100%, in quanto non ha istruzioni che manipolano valori
	// ma ritorna solamente il valore invariato:
	public /*@ pure @*/ int getCounter() {
		return counter;
	}

	//le due postcondizioni sono equivalenti
	//@ ensures counter == \old(counter) + 1;
	//@ ensures getCounter() == \old(getCounter()) + 1;
	public void incr() {
		counter++;
	}

	//@ ensures counter == \old(counter) - 1;
	//@ ensures getCounter() == \old(getCounter()) - 1;
	public void decr() {
		counter--;
	}

	// A questo punto possiamo tranquillamente iniziare a scrivere il nostro programma principale:
	public static void main(String[] args) {
		Counter c = new Counter();
		c.incr(); // non viola nulla
		c.decr(); // idem!
	}
}