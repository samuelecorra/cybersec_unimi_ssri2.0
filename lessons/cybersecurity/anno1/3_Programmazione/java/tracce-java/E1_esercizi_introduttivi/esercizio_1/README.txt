/* ----------------------------------------------------------------------

    ESERCIZIO:
    Realizzare un programma Java che consenta all'utente, mediante
    l'utilizzo della console, di calcolare il volume di una sfera
    inserendo il valore del raggio.

----------------------------------------------------------------------- */

    NOTE UTILI:

    0. La classe contenente il metodo main deve essere pubblica e
       deve avere lo stesso nome del file che la contiene.
       Inoltre è buona prassi che il nome della classe inizi con
       una lettera maiuscola.


    1. Lo Scanner si può importare con:
       import java.util.Scanner;

       oppure importandolo "inline" come mostrato nel codice di esempio qui sotto.
       java.util.Scanner scanner = new java.util.Scanner(System.in);

    Chiaramente per snellire il codice è preferibile la prima opzione.


    2. Differenze tra System.out.print() e System.out.println():
       - System.out.print() stampa il testo senza andare a capo alla fine
       - System.out.println() stampa il testo e va a capo alla fine

    Quando chiediamo un input all'utente, è preferibile usare System.out.print()
    in modo che il cursore rimanga sulla stessa riga della domanda.


    3. La classe Math fornisce il valore di PI con Math.PI, e la potenza con
       Math.pow(base, esponente).
       Si deve includere? No, Math è una classe "built-in" di Java, quindi non necessita
       di importazioni particolari.


    4. printf serve solo dopo, quando abbiamo dell'output da dover formattare.