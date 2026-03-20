public class Rubik2D {

    /*  Esercizio 9 - Rubik2D

        Versione semplificata di una faccia del cubo di Rubik (3x3).
        Configurazione iniziale come da testo.

        Metodo gira(int idCol):
            - sposta verso l'alto (in modo ciclico) la colonna idCol;
            - ritorna true se idCol e valido, false altrimenti.

        Metodo isSolved():
            - ritorna true se ogni riga contiene tutti valori uguali.

        Richieste JML:
            - costruttore: tutte le celle con valore tra 0 e 2;
            - gira: tre celle con valore 0, somma totale 9,
              celle fuori dalla colonna idCol invariate.
    */

    int[][] faccia;

    /*@ ensures (\forall int i; i >= 0 && i < 3;
      @     (\forall int j; j >= 0 && j < 3; faccia[i][j] >= 0 && faccia[i][j] < 3));
      @*/
    public Rubik2D() {
        faccia = new int[3][3];
        faccia[0][0] = 1;
        faccia[0][2] = 1;
        faccia[1][1] = 1;
        faccia[1][0] = 2;
        faccia[1][2] = 2;
        faccia[2][1] = 2;
    }

    //@ ensures (\sum int i; i >= 0 && i < 3; (\num_of int j; j >= 0 && j < 3; faccia[i][j] == 0)) == 3;
    //@ ensures (\sum int i; i >= 0 && i < 3; (\sum int j; j >= 0 && j < 3; faccia[i][j])) == 9;
    /*@ ensures (\forall int i; i >= 0 && i < 3;
      @     (\forall int j; j >= 0 && j < 3; j != idCol ==> faccia[i][j] == \old(faccia[i][j])));
      @*/
    public boolean gira(int idCol) {
        if (idCol >= 0 && idCol < 3) {
            int primo = faccia[0][idCol];
            for (int i = 0; i < 2; i++) {
                faccia[i][idCol] = faccia[i + 1][idCol];
            }
            faccia[2][idCol] = primo;
            return true;
        }
        return false;
    }

    public boolean isSolved() {
        for (int i = 0; i < 3; i++) {
            int valore = faccia[i][0];
            for (int j = 1; j < 3; j++) {
                if (faccia[i][j] != valore) {
                    return false;
                }
            }
        }
        return true;
    }
}
