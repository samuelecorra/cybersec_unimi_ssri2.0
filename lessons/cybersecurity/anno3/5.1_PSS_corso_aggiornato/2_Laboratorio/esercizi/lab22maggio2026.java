public abstract class lab22maggio2026 {
    
    // L'esame di laboratorio consisteva in una classe che rappresentava un treno i cui vagoni
    // hanno 30righe x 3 colonne ciascuno (90 posti, di cui 30 lato finestrino (indice 0), 30 al centro (indice 1) e 30 lato  corridoio (indice 2)).

    private /*@ spec_public */ boolean[][] fila;

    // Quando un posto è occupato, il suo valore è true, altrimenti è false.

    // Costruttore:
    // Come post-condizione al costruttore volevamo scrivere in JML che ogni posto è libero.
    // In JML:
    /*@ ensures (\forall int i; 0 <= i && i < 30; 
                    \forall int j; 0 <= j && j < 3; 
                        fila[i][j] == false); 
      @*/
    public lab22maggio2026() {
        fila = new boolean[30][3]; // 30 righe e 3 colonne
        // Inizialmente tutti i posti sono liberi (false)
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 3; j++) {
                fila[i][j] = false;
            }
        }
    }

    // dove f=finestrino, c=centro, o=corridoio

    // 

    public void occupaPostoFila(int riga, char colonna){
        // Innanzitutto facciamo un check sulla validità della riga:
        if (riga < 0 || riga >= 30) {
            throw new IllegalArgumentException("Riga non valida: " + riga);
        }
        if (colonna == 'f') {
            fila[riga][0] = true;
        } else if (colonna == 'c') {
            fila[riga][1] = true;
        } else if (colonna == 'o') {
            fila[riga][2] = true;
        } else {
            throw new IllegalArgumentException("Colonna non valida: " + colonna);
        }
    }
}
