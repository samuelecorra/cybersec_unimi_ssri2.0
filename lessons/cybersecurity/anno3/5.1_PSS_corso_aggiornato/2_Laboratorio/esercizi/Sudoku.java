public class Sudoku {

    /*  Esercizio 4 - Sudoku

        Scrivere una classe Sudoku che permetta di giocare ad una versione
        semplificata del sudoku in cui la scacchiera e di dimensione 4x4.

        Una cella e vuota se contiene 0.
        Il gioco e risolto se:
            - ogni riga contiene i numeri [1,4] senza ripetizioni;
            - ogni colonna contiene i numeri [1,4] senza ripetizioni;
            - ogni sotto-scacchiera 2x2 contiene i numeri [1,4] senza ripetizioni.

        Metodo add(int r, int c, int value):
            - inserisce value in board[r][c] e ritorna true solo se parametri validi;
            - altrimenti ritorna false.

        Metodo isMaybeSolved():
            - verifica una condizione necessaria (non sufficiente):
                * ogni riga somma 10;
                * ogni colonna somma 10;
                * ogni sotto-scacchiera 2x2 somma 10.

        Richieste JML per add(int r, int c, int value):
            - pre: tutti gli elementi della riga r sono diversi da value;
            - post: il numero di celle vuote e <= 16;
            - post: \result e true sse la condizione dell'if e true.
    */

    int[][] board;

    public Sudoku() {
        board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
    }

    //@ requires r >= 0 && r <= 3;
    //@ requires (\forall int j; j >= 0 && j <= 3; board[r][j] != value);
    //@ ensures (\sum int i; i >= 0 && i <= 3; (\num_of int j; j >= 0 && j <= 3; board[i][j] == 0)) <= 16;
    //@ ensures \result <==> (r >= 0 && r <= 3 && c >= 0 && c <= 3 && value >= 1 && value <= 4);
    public boolean add(int r, int c, int value) {
        if (r >= 0 && r <= 3 && c >= 0 && c <= 3 && value >= 1 && value <= 4) {
            board[r][c] = value;
            return true;
        } else {
            return false;
        }
    }

    public boolean isMaybeSolved() {
        for (int i = 0; i < 4; i++) {
            int sommaRiga = 0;
            for (int j = 0; j < 4; j++) {
                sommaRiga += board[i][j];
            }
            if (sommaRiga != 10) {
                return false;
            }
        }

        for (int j = 0; j < 4; j++) {
            int sommaColonna = 0;
            for (int i = 0; i < 4; i++) {
                sommaColonna += board[i][j];
            }
            if (sommaColonna != 10) {
                return false;
            }
        }

        for (int r = 0; r < 4; r += 2) {
            for (int c = 0; c < 4; c += 2) {
                int sommaSottoScacchiera = 0;
                for (int i = r; i < r + 2; i++) {
                    for (int j = c; j < c + 2; j++) {
                        sommaSottoScacchiera += board[i][j];
                    }
                }
                if (sommaSottoScacchiera != 10) {
                    return false;
                }
            }
        }

        return true;
    }
}
