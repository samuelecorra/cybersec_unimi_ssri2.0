
public class CounterAssert {
	
	private  int counter;

	CounterAssert() {
		counter = 0;		
		//counter = -1;  //inizializzazione errata
		assert counter >=0 : "counter = " +counter; //invariante
	}

	
	//@ ensures counter == \old(counter) + 1;
	public void incr() {		
		assert counter >=0 : "counter = " +counter; //invariante
		int oldcounter=counter; //per l'assert
		counter++;
		assert counter == oldcounter +1;		
		// assert counter == oldcounter -1; // assert errato
		assert counter >=0 : "counter = " +counter; //invariante
	}

	//@ ensures counter == \old(counter) - 1;
	public void decr() {
		assert counter >=0 : "counter = " +counter; //invariante
		int oldcounter=counter; //per l'assert
		if (counter >0)
		counter--;
		assert counter == oldcounter -1;
		assert counter >=0 : "counter = " +counter; //invariante
	}

		public static void main(String[] args) {
		CounterAssert c = new CounterAssert();
		c.incr();
		c.decr();
	}
}

