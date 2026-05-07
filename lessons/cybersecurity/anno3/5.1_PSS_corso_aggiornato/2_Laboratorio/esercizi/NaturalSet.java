public class NaturalSet {

    /*  Scrivere una classe NaturalSet che rappresenti un insieme di numeri naturali.
        
        La classe rappresenta l'insieme usando un array di interi: 
        le celle libere dell'array contengono il valore -1.
        
        Il costruttore della classe deve inizializzare l'array usando come dimensione 
        il valore che viene passato come parametro al costruttore.
        All'inizio l'insieme è vuoto.

        Metodo add → La classe deve disporre di un metodo booleano add(int value) che permetta di
        aggiungere un elemento all'insieme.
            • Se il valore value non è già presente nell'insieme, deve essere inserito e il metodo deve ritornare true.
            • Se il valore è già presente nell'insieme, non deve essere inserito e il metodo deve ritornare false.
            • Il metodo non deve controllare che il valore sia positivo (lo fa la precondizione JML).

        Metodo remove → La classe deve disporre di un metodo remove(int value) che permetta di
        rimuovere un elemento dall'insieme.
            • Se il valore value è presente nell'insieme, il metodo lo rimuove e lo ritorna, 
              altrimenti viene ritornato il valore -1.

        Scrivere in JML la seguente precondizione al costruttore:
            • La dimensione massima dell'insieme deve essere compresa tra 1 e 100.
        
        Scrivere in JML la seguente postcondizione al costruttore:
            • L'insieme non contiene elementi.

        Scrivere in JML la seguente precondizione al metodo add(int value):
            • Il valore value è positivo.
        
        Scrivere in JML le seguenti postcondizioni al metodo add(int value):
            • Nell'insieme non esistono due elementi uguali.
            • Il numero di elementi dell'insieme dopo l'esecuzione del metodo è uguale
              o al massimo maggiore di un'unità al numero di elementi dell'insieme
              prima dell'esecuzione del metodo.
    */

    int[] set;

    //@ requires size >= 1 && size <= 100;
    //@ ensures (\forall int i; i >= 0 && i < size; set[i] == -1);
    public NaturalSet(int size) {
        set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = -1;
        }
    }

    //@ requires value >= 0;
    /*@ ensures !(\exists int i; i >= 0 && i < set.length;
      @     (\exists int j; j >= 0 && j < set.length; 
      @         j != i && set[i] == set[j] && set[i] != -1));
      @*/
    /*@ ensures ((\num_of int i; i >= 0 && i < set.length;set[i] > -1) -
      @          (\num_of int i; i >= 0 && i < set.length; \old(set[i]) > -1)) <= 1;
      @*/
    public boolean add(int value) {
        int index = -1;
        for (int i = 0; i < set.length; i++) {
            if (set[i] == value) {
                return false;
            } else if (index == -1 && set[i] == -1) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        } else {
            set[index] = value;
            return true;
        }
    }

    public int remove(int value) {
        for (int i = 0; i < set.length; i++) {
            if (set[i] == value) {
                set[i] = -1;
                return value;
            }
        }
        return -1;
    }

}
