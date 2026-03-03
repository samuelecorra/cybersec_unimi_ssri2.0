public class NaturalSet {
	private /*@ spec_public @*/ int[] set;

	//la dimensione massima dell'insieme deve essere compresa tra 1 e 100
	//@ requires size >= 1 && size <= 100;
	//l'insieme non contiene elementi
	//@ ensures (\forall int i; i >= 0 && i < size; set[i] == -1);
	public NaturalSet(int size) {
		set = new int[size];
		for(int i = 0; i < size; i++) {
			set[i] = -1;
		}
	}

	//il valore "value" e' positivo
	//@ requires value >= 0;
	//nell'insieme non esistono due elementi uguali
	/*@ ensures !(\exists int i; i >=0 && i < set.length; 
	  @            (\exists int j; j >=0 && j < set.length; (j!=i) ==> set[i] == set[j] && set[i] != -1));
	  @*/
	//il numero di elementi dell'insieme dopo l'esecuzione del metodo è uguale o
	//al massimo maggiore di un'unita' al numero di elementi dell'insieme prima
	//dell'esecuzione del metodo
	/*@ ensures ((\num_of int i; i >= 0 && i < set.length; set[i] > -1) -
	  @          (\num_of int i; i >= 0 && i < set.length; \old(set[i] > -1))) == 1
	  @         ||
	  @         ((\num_of int i; i >= 0 && i < set.length; set[i] > -1) -
	  @          (\num_of int i; i >= 0 && i < set.length; \old(set[i] > -1))) == 0;
	  @*/
	public boolean add(int value) {
		int index = -1;
		for(int i = 0; i < set.length; i++) {
			if(set[i] == value) {
				return false;
			}
			else if(index == -1 && set[i] == -1) {
				index = i;
			}
		}
		if(index == -1) {
			return false;
		}
		else {
			set[index] = value;
			return true;
		}
	}

	public int remove(int value) {
		int i = 0;
		while(i < set.length) {
			if(set[i] == value) {
				set[i] = -1;
				return value;
			}
			i++;
		}
		return -1;
	}

	public static void main(String[] args) {
		//viola la precondizione "la dimensione massima dell'insieme deve essere compresa tra 1 e 100"
		//NaturalSet set = new NaturalSet(200);

		NaturalSet set1 = new NaturalSet(10);
		set1.add(6);
		//set1.add(-5);//viola la precondizione "il valore "value" e' positivo"
	}
}