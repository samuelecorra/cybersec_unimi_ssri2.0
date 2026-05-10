/* In questo breve esempio, vogliamo realizzare una classe
il cui campo contatore deve sempre essere maggiore o uguale a zero.
La proprieta' viene mantenuta verificando che il decremento sia eseguito solo quando il contatore e' > 0.*/
public class PositiveCounter {
	private /*@ spec_public @*/ int counter;

	PositiveCounter() {
		counter = 0;
	}

	// Qui basta una post come già visto in Counter.java con contatore sia pos/neg.
	// Serve per evitare errori aritmetici.
	//@ ensures counter == \old(counter) + 1;
	public void incr() {
		System.out.println("All'inizio di incr. counter = " + counter);
		counter++;
		System.out.println("Alla fine di incr. counter = " + counter);
	}

	// Qui, come anticipato, serve anche una pre!
	//@ requires counter > 0;
	//@ ensures counter == \old(counter) - 1;
	public void decr() {
		System.out.println("All'inizio di decr. counter = " + counter);
		counter--;
		System.out.println("Alla fine di decr. counter = " + counter);
	}

	public static void main(String[] args) {
		PositiveCounter c = new PositiveCounter();
		c.incr();
		c.decr();
		c.decr();//viola la precondizione. Non entra nel metodo.
	}
}
