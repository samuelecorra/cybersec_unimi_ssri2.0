public class ProduttoreConsumatore {
    
    /* Scrivere una classe ProdCons che simuli il problema del produttore-consumatore.


    La classe deve disporre di un buffer che contenga gli elementi prodotti dal produttore.

    Il buffer deve essere modellato con un array di interi:

        • Una cella vale 0 se non contiene il prodotto.

        • Una cella vale 1 se lo contiene.

        • Un opportuno indice deve indicare la prima cella senza prodotti.

    La classe deve disporre di un metodo produce per produrre un prodotto ed un metodo consume per consumare un prodotto.

    Il costruttore della classe deve inizializzare il buffer alla dimensione comunicata tramite parametro.
    All’inizio il buffer non contiene prodotti.

        • Metodo produce → Il metodo aggiunge un prodotto al buffer solo se c’è spazio.
        • Deve ritornare un booleano: true o false a seconda che sia stato inserito o meno il prodotto.
        • Metodo consume → Il metodo elimina un prodotto dal buffer se questo contiene almeno un prodotto.
        • Deve ritornare un booleano: true o false a seconda che sia stato eliminato o meno un prodotto.
    
        Scrivere in JML la seguente precondizione al costruttore:
            
        • La capacità con cui deve essere inizializzato il buffer deve essere compresa tra 1 e 100.
    
        Scrivere in JML le seguenti postcondizioni al metodo produce:
        
        • Il buffer contiene solo 0 o 1.
        • Nel buffer non c’è mai un 1 dopo uno 0.
        • Il numero di 1 nel buffer dopo l’esecuzione del metodo è uguale o
        al massimo maggiore di un’unità al numero di 1 presenti nel buffer
        prima dell’esecuzione del metodo.  */

    int[] buffer;
    int indice;

    /* @ requires 1 <= capacity && capacity <= 100; */
    public ProduttoreConsumatore(int capacity) {
        buffer = new int[capacity];
        indice = 0;
    }

    /* @ ensures (\forall int i; 0 <= i && i < buffer.length; buffer[i] == 0 || buffer[i] == 1);
       @ ensures !(\exists int i; i>=0 && i<buffer.length-1; buffer[i]==0 && buffer[i+1]==1);
       @ ensures (\sum int i; 0 <= i && i < buffer.length; buffer[i]) == \old(\sum int i; 0 <= i && i < buffer.length; buffer[i]) + (produced ? 1 : 0); */
    public boolean produce() {
        if (indice < buffer.length) {
            buffer[indice] = 1;
            indice++;
            return true;
        }
        return false;
    }
    
    /* @ ensures (\forall int i; 0 <= i && i < buffer.length; buffer[i] == 0 || buffer[i] == 1);
       @ ensures !(\exists int i; i>=0 && i<buffer.length-1; buffer[i]==0 && buffer[i+1]==1);
       @ ensures (\sum int i; 0 <= i && i < buffer.length; buffer[i]) == \old(\sum int i; 0 <= i && i < buffer.length; buffer[i]) - (consumed ? 1 : 0); */
    public boolean consume() {
        if (indice > 0) {
            indice--;
            buffer[indice] = 0;
            return true;
        }
        return false;
    }
    
}
