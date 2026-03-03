public class Queue {
	private static final String EMPTYSTRING = "";
	private /*@ spec_public @*/ String[] arr;
	private /*@ spec_public @*/ int top;
	private /*@ spec_public @*/ int capacity;
	private /*@ spec_public @*/ int size;

	//la dimensione massima della coda deve essere compresa tra 2 e 100
	//@ requires capacity >= 2 && capacity <= 100;
	//la coda non contiene elementi
	//@ ensures (\forall int i; i >=0 && i < capacity; arr[i].equals(""));
	public Queue(int capacity) {
		arr = new String[capacity];
		for(int i = 0; i < capacity; i++) {
			arr[i] = EMPTYSTRING;
		}
		this.capacity = capacity;
		top = 0;
		size = 0;
	}

	//la lunghezza della stringa che si vuole aggiungere e' compresa tra 2 e 10 caratteri
	//@ requires str.length() > 1 && str.length() <= 10;
	//il metodo ha ritornato true se e solo se, prima dell'esecuzione del metodo, la dimensione della coda era minore della sua capacita' massima
	//@ ensures (\old(size) < capacity) <==> \result;
	//la media delle lunghezze delle stringhe contenute nell'array (tutte le stringhe, anche quelle vuote) e' minore od uguale a 10
	//@ ensures (\sum int i; i >=0 && i < capacity; arr[i].length())/capacity <= 10;
	public boolean add(String str) {
		if(size < capacity) {
			arr[(top + size)%capacity] = str;
			size++;
			return true;
		}
		return false;
	}

	public String pop() {
		if(size > 0) {
			String str = arr[top];
			arr[top] = EMPTYSTRING;
			top = (top + 1)%capacity;
			size--;
			return str;
		}
		else {
			return null;
		}
	}

	public int numberOfDistinctWords() {
		int numOfDistinctWords = 0;
		String str;
		boolean visited[] = new boolean[capacity];
		for(int i = 0; i < capacity; i++) {
			str = arr[i];
			if(!str.equals(EMPTYSTRING) && !visited[i]) {
				numOfDistinctWords++;
				for(int j = i + 1; j < capacity; j++) {
					if(!visited[j] && str.equals(arr[j])) {
						visited[j] = true;
					}
				}
			}
		}
		return numOfDistinctWords;
	}

	/*public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < arr.length - 1; i++) {
			sb.append(arr[i] + ", ");
		}
		sb.append(arr[arr.length - 1] + "]");
		return sb.toString();
	}*/

	public static void main(String[] args) {
		//Queue q = new Queue(1);//viola la precondizione sulla capacita' massima della coda
		Queue q2 = new Queue(3);
		q2.add("aaa");
		q2.add("adas");
		q2.pop();
		q2.add("vvdsdsvs");
		q2.pop();
		//q2.add("d");//viola la precondizione di "add" che richiede che la stringa abbia almeno due caratteri
	}
}