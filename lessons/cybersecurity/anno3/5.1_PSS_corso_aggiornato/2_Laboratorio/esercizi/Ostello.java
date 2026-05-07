public class Ostello {

    /*  Esercizio 5 - Ostello

        Modellare un ostello con 10 stanze da 5 letti (totale 50 letti).
        La matrice boolean lettoLibero[stanza][letto] rappresenta lo stato:
            - true  = letto libero
            - false = letto occupato

        All'inizio tutti i letti sono liberi.

        Metodo checkin(int stanza, int letto):
            - occupa il letto se parametri validi e letto libero;
            - ritorna true se il check-in va a buon fine, false altrimenti.

        Metodo ottimizzaOstello():
            - minimizza il numero di stanze usate spostando tutti i clienti
              nelle prime celle (dalla stanza 0, letto 0, in avanti).

        Richieste JML:
            - costruttore: tutti i letti inizialmente liberi;
            - checkin:
                * tutti gli altri letti non cambiano;
                * letti occupati <= 50;
                * se stanza < 0 allora risultato false.
    */

    boolean[][] lettoLibero;

    /*@ ensures (\forall int i; i >= 0 && i < 10;
      @     (\forall int j; j >= 0 && j < 5; lettoLibero[i][j]));
      @*/
    public Ostello() {
        lettoLibero = new boolean[10][5];
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                lettoLibero[r][c] = true;
            }
        }
    }

    /*@ ensures (\forall int i; i >= 0 && i < 10;
      @     (\forall int j; j >= 0 && j < 5;
      @         (i != stanza || j != letto) ==> \old(lettoLibero[i][j]) == lettoLibero[i][j]));
      @*/
    //@ ensures (\sum int i; i >= 0 && i < 10; (\num_of int j; j >= 0 && j < 5; !lettoLibero[i][j])) <= 50;
    //@ ensures stanza < 0 ==> !\result;
    public boolean checkin(int stanza, int letto) {
        if (stanza >= 0 && stanza < 10 && letto >= 0 && letto < 5) {
            if (lettoLibero[stanza][letto]) {
                lettoLibero[stanza][letto] = false;
                return true;
            }
        }
        return false;
    }

    public void ottimizzaOstello() {
        int occupati = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                if (!lettoLibero[r][c]) {
                    occupati++;
                }
            }
        }

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                lettoLibero[r][c] = true;
            }
        }

        for (int i = 0; i < occupati; i++) {
            int stanza = i / 5;
            int letto = i % 5;
            lettoLibero[stanza][letto] = false;
        }
    }
}
