public class OraDiPunta {

    /*  Esercizio 8 - Ora di punta

        Griglia 6x6 con 6 macchine (id 1..6), celle vuote a 0.
        Obiettivo: portare la macchina 1 in posizione (2,5).

        Metodo spostaMacchina(int riga, int colonna, int dir):
            - dir: 1 alto, 2 destra, 3 basso, 4 sinistra
            - ritorna true se la macchina e stata spostata, false altrimenti.

        Metodo macchinaRossaUscita():
            - ritorna true se la macchina rossa (1) e nella cella di uscita (2,5).

        Richieste JML:
            - costruttore: ci sono 6 celle non vuote;
            - spostaMacchina: pre su parametri validi;
            - spostaMacchina: post sul prodotto delle celle e assenza di valori negativi.
    */

    int[][] griglia;

    /*@ ensures (\sum int i; i >= 0 && i < 6;
      @     (\num_of int j; j >= 0 && j < 6; griglia[i][j] != 0)) == 6;
      @*/
    public OraDiPunta() {
        griglia = new int[6][6];
        griglia[2][2] = 1;
        griglia[1][5] = 2;
        griglia[2][5] = 3;
        griglia[3][5] = 4;
        griglia[4][1] = 5;
        griglia[5][2] = 6;
    }

    //@ requires riga >= 0 && riga < 6 && colonna >= 0 && colonna < 6 && dir >= 1 && dir <= 4;
    //@ ensures (\product int i; i >= 0 && i < 6; (\product int j; j >= 0 && j < 6; griglia[i][j])) == 0;
    //@ ensures !(\exists int i; i >= 0 && i < 6; (\exists int j; j >= 0 && j < 6; griglia[i][j] < 0));
    public boolean spostaMacchina(int riga, int colonna, int dir) {
        if (riga < 0 || riga >= 6 || colonna < 0 || colonna >= 6 || dir < 1 || dir > 4) {
            return false;
        }

        int car = griglia[riga][colonna];
        if (car == 0) {
            return false;
        }

        int nuovaRiga = riga;
        int nuovaColonna = colonna;
        switch (dir) {
            case 1:
                nuovaRiga = riga - 1;
                break;
            case 2:
                nuovaColonna = colonna + 1;
                break;
            case 3:
                nuovaRiga = riga + 1;
                break;
            case 4:
                nuovaColonna = colonna - 1;
                break;
            default:
                return false;
        }

        if (nuovaRiga < 0 || nuovaRiga >= 6 || nuovaColonna < 0 || nuovaColonna >= 6) {
            return false;
        }
        if (griglia[nuovaRiga][nuovaColonna] != 0) {
            return false;
        }

        griglia[nuovaRiga][nuovaColonna] = car;
        griglia[riga][colonna] = 0;
        return true;
    }

    public boolean macchinaRossaUscita() {
        return griglia[2][5] == 1;
    }
}
