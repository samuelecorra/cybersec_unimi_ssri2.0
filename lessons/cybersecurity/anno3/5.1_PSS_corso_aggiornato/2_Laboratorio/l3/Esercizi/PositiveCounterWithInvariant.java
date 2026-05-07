/**
 * Il contatore deve sempre essere maggiore o uguale a zero.
 * Un invariante richiede che il contatore sia sempre maggiore o uguale a zero.
 *
 */
public class PositiveCounterWithInvariant {
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
