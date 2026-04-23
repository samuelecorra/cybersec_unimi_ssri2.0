package M11_JavaAvanzato.L07_Threading;

import java.util.Scanner;

public class Threading {

    // Il Threading in Java è un concetto avanzato che permette di eseguire più operazioni contemporaneamente all'interno di un programma.
    // Un "thread" è un flusso di esecuzione separato che può operare indipendentemente dagli altri flussi.

    // E' fondamentale quando si tratta di andare a migliorare le performance con tutte quelle operazioni
    // che consumano tempo rilevante, come operazioni di I/O, comunicazioni di rete, o qualsiasi background task che
    // non deve bloccare il flusso principale dell'applicazione.

    // Come creare un Thread in Java:

    // 1. Estendere la classe Thread, metodo più semplice ma meno flessibile.

    // 2. Implementare l'interfaccia Runnable, metodo più flessibile e preferito.

    static void main() {

        // Proviamo intanto a capire cos'è il main thread, ovvero il flusso esecutivo principale di un programma Java.
        Scanner sc = new Scanner(System.in);

        // Prima creiamo un Runnable separato che conta fino a 5 secondi
        MioRunnable mioRunnable = new MioRunnable();

        // E con tale Runnable creiamo un Thread passandolo al costruttore di Thread
        Thread thread = new Thread(mioRunnable);

        // Ora possiamo avviare il thread secondario
        thread.setDaemon(true); // Impostiamo il thread come daemon, così si chiude quando il main thread termina
        thread.start();

        System.out.print("Hai 5 secondi nel thread principale per digitare il tuo nome: ");
        String nome = sc.nextLine();
        System.out.println("Ciao, " + nome + "!");

        sc.close();

    }

}
