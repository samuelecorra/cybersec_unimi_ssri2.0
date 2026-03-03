public class ProdCons {
	public int[] buffer;
	private int free;

	//la capacita' con cui deve essere inizializzato il buffer deve essere compresa tra 1 e 100
	//@ requires capacity > 0 && capacity <= 100; 
	public ProdCons(int capacity) {
		buffer = new int[capacity];
		free = 0;
	}

	//il buffer contiene solo 0 o 1
	//@ ensures (\forall int i; i >= 0 && i < buffer.length; buffer[i] == 0 || buffer[i] == 1);
	//nel buffer non c'e' mai un 1 dopo uno 0
	//@ ensures !(\exists int i; i > 0 && i < buffer.length; buffer[i-1] == 0 && buffer[i] == 1);
	//il numero di 1 nel buffer dopo l'esecuzione del metodo e' uguale o al massimo maggiore di
	//un'unita' al numero di 1 presenti nel buffer prima dell'esecuzione del metodo
	/*@ ensures ((\num_of int i; i >= 0 && i < buffer.length; buffer[i] == 1) -
	  @			   (\num_of int i; i >= 0 && i < buffer.length; \old(buffer[i]) == 1)) <= 1;
	  @*/
	public boolean produce() {
		if(free < buffer.length) {
			buffer[free] = 1;
			free++;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean consume() {
		if(free > 0) {
			free--;
			buffer[free] = 0;
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		//ProdCons p1 = new ProdCons(1000);//viola la precondizione

		ProdCons p = new ProdCons(2);
		p.consume();//non consuma nulla
		p.produce();
		p.produce();
		p.produce();//non viene messo
		p.consume();
		p.consume();
	}
}