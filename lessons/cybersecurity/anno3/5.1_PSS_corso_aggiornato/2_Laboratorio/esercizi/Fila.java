public class Fila {

    /*  Esercizio 6 - Fila

        Modellare una fila allo sportello per massimo 200 utenti numerati 0..199.
        fila[i] contiene i minuti stimati (1..15) per il numero i.
        Una cella a 0 indica che quel numero non e stato inserito in fila.

        Variabili:
            - prossimoInFila: primo numero libero da assegnare
            - prossimoServito: prossimo numero che verra chiamato

        Metodi:
            - ritiroNumero(int minuti)
            - chiamataAlloSportello()
            - stimaMinuti(int numero)

        Richieste JML implementate:
            - invariante su prossimoInFila/prossimoServito;
            - postcondizione costruttore (fila vuota);
            - pre/post per ritiroNumero;
            - post per stimaMinuti.
    */

    private /*@ spec_public @*/ int[] fila;
    public int prossimoInFila;
    private /*@ spec_public @*/ int prossimoServito;

    /*@ public invariant prossimoInFila >= 0 && prossimoInFila <= 200
      @     && !(prossimoServito > prossimoInFila);
      @*/

    //@ ensures (\forall int i; i >= 0 && i < 200; fila[i] == 0);
    public Fila() {
        fila = new int[200];
        prossimoInFila = 0;
        prossimoServito = 0;
    }

    //@ requires minuti >= 1 && minuti <= 15;
    //@ ensures (\forall int i; i >= 0 && i < prossimoInFila; fila[i] > 0);
    //@ ensures (\exists int i; i >= 0 && i < 200; fila[i] > 0);
    //@ ensures (\forall int i; i >= 0 && i < 199; ((fila[i] == 0) ==> (fila[i + 1] == 0)));
    //@ ensures (\forall int i; i >= 0 && i < 199; !((fila[i] == 0) && (fila[i + 1] > 0)));
    public int ritiroNumero(int minuti) {
        int numero = -1;
        if (prossimoInFila < 200 && prossimoInFila >= 0) {
            fila[prossimoInFila] = minuti;
            numero = prossimoInFila++;
        }
        return numero;
    }

    public int chiamataAlloSportello() {
        if (prossimoServito < prossimoInFila && prossimoServito < 200) {
            int numero = prossimoServito;
            prossimoServito++;
            return numero;
        }
        return -1;
    }

    //@ ensures (prossimoServito > numero) ==> \result == -1;
    public int stimaMinuti(int numero) {
        int risultato = 0;
        if (numero < 0 || prossimoServito > numero || numero >= prossimoInFila) {
            risultato = -1;
        } else {
            for (int i = prossimoServito; i < numero; i++) {
                risultato += fila[i];
            }
        }
        return risultato;
    }
}
