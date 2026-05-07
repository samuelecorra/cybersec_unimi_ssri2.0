public class Tombola {

    /*  Esercizio 3 - Tombola

        Scrivere una classe Tombola che modelli le azioni di un croupier nel gioco della tombola.

        La classe deve modellare con un array unidimensionale di interi (tabellone)
        il tabellone del croupier (il tabellone contiene 90 numeri).

        Il croupier puo aggiungere delle pedine sui numeri; nell'array,
        il valore di ogni cella di indice i indica il numero di pedine
        posizionate sul numero i + 1.

        Il costruttore della classe deve inizializzare il tabellone,
        facendo in modo che tutte le celle non contengano pedine.

        Metodo addPedina(int numero):
            - aggiunge una pedina sul tabellone;
            - controlla che il numero sia compreso tra 1 e 90;
            - controlla che la cella non contenga gia una pedina.

        Metodo isCorrect(int[] numeroCartella, boolean[] numeroPresente):
            - verifica che l'utente abbia marcato correttamente i numeri usciti;
            - ritorna true se la cartella e marcata correttamente, altrimenti false.

        Richieste JML per addPedina(int numero) - postcondizioni:
            - non esiste una cella che contiene piu di una pedina;
            - il prodotto di tutte le celle e minore o uguale a 1.

        Richieste JML per isCorrect(...) - precondizioni:
            - numeroCartella e numeroPresente hanno lunghezza 5;
            - tutti i numeri in numeroCartella sono compresi tra 1 e 90.
    */

    int[] tabellone;

    public Tombola() {
        tabellone = new int[90];
        for (int i = 0; i < tabellone.length; i++) {
            tabellone[i] = 0;
        }
    }

    //@ ensures !(\exists int i; i >= 0 && i < 90; tabellone[i] > 1);
    //@ ensures (\product int i; i >= 0 && i < 90; tabellone[i]) <= 1;
    public void addPedina(int numero) {
        if (numero >= 1 && numero <= 90 && tabellone[numero - 1] == 0) {
            tabellone[numero - 1] = tabellone[numero - 1] + 1;
        }
    }

    //@ requires numeroCartella.length == 5 && numeroPresente.length == 5;
    //@ requires (\forall int i; i >= 0 && i < 5; numeroCartella[i] >= 1 && numeroCartella[i] <= 90);
    public boolean isCorrect(int[] numeroCartella, boolean[] numeroPresente) {
        for (int i = 0; i < numeroCartella.length; i++) {
            int index = numeroCartella[i] - 1;
            if ((numeroPresente[i] && tabellone[index] == 0)
                    || (!numeroPresente[i] && tabellone[index] > 0)) {
                return false;
            }
        }
        return true;
    }
}
