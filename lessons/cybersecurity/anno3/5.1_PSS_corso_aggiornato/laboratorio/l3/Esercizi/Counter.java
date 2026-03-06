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

	//le due postcondizioni sono equivalenti
	//@ ensures counter == \old(counter) + 1;
	//@ ensures getCounter() == \old(getCounter()) + 1;
	public void incr() {
		counter++;
	}

	//@ ensures counter == \old(counter) - 1;
	public void decr() {
		counter--;
	}

	//Solo i metodi puri possono essere utilizzati nelle precondizioni e nelle postcondizioni.
	//Un metodo puro e' un metodo che non modifica lo stato del programma.
	//Per identificare un metodo come puro lo si puo' dichiarare con il modificatore "pure". 
	public /*@ pure @*/ int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		Counter c = new Counter();
		c.incr();
		c.decr();
	}
}
