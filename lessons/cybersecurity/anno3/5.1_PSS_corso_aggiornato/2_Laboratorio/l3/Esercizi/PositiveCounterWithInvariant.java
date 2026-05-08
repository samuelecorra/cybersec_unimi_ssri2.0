/* In quest'altro esempio, facciamo uno step in più:
Il contatore deve essere sempre NON NEGATIVO, e questa affermazione con "sempre"
ci suggerisce di trasformarla in invariant! */

public class PositiveCounterWithInvariant {

	// Nell'annotazione dell'invariante, che poniamo qui, subito a inizio classe,
	// l'invariante stesso sarà pubblico in quanto ci serve poter farvi
	// riferimento anche dall'esterno della classe; per questo motivo, però,
	// il campo counter (che è private) deve essere annotato come spec_public,
	// così da poter comparire in una specifica pubblica.
	/*@ public invariant counter >= 0; @*/

	private /*@ spec_public @*/ int counter;

	PositiveCounterWithInvariant() {
		counter = 0;
	}

	//@ ensures counter == \old(counter) + 1;
	public void incr() {
		System.out.println("All'inizio di incr. counter = " + counter);
		counter++;
		System.out.println("Alla fine di incr. counter = " + counter);
	}

	//@ ensures counter == \old(counter) - 1;
	public void decr() {
		System.out.println("All'inizio di decr. counter = " + counter);
		counter--;
		System.out.println("Alla fine di decr. counter = " + counter);
	}

	public static void main(String[] args) {
		PositiveCounterWithInvariant c = new PositiveCounterWithInvariant();
		c.incr();
		c.decr();
		c.decr();//questa chiamata fa violare l'invariante
	}
}
