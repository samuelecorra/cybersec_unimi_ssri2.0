package M11_JavaAvanzato.L07_Threading;

import java.util.Scanner;

public class Threading {

    // Il threading permette più flussi di esecuzione concorrenti. Possono essere
    // eseguiti davvero in parallelo se hardware e scheduler lo consentono.

    // È utile per mantenere responsivo il flusso principale durante I/O, rete e
    // attività in background; non rende automaticamente più veloce ogni programma.

    // Come creare un Thread in Java:

    // 1. Estendere la classe Thread, metodo più semplice ma meno flessibile.

    // 2. Implementare l'interfaccia Runnable, metodo più flessibile e preferito.

    public static void main(String[] args) {

        // Proviamo intanto a capire cos'è il main thread, ovvero il flusso esecutivo principale di un programma Java.
        Scanner sc = new Scanner(System.in);

        // Prima creiamo un Runnable separato che conta fino a 5 secondi
        MioRunnable mioRunnable = new MioRunnable();

        // E con tale Runnable creiamo un Thread passandolo al costruttore di Thread
        Thread thread = new Thread(mioRunnable);

        // Ora possiamo avviare il thread secondario
        thread.start();

        System.out.print("Digita il tuo nome mentre il thread secondario conta 5 secondi: ");
        String nome = sc.nextLine();
        System.out.println("Ciao, " + nome + "!");

        sc.close();

        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Il thread principale è stato interrotto durante l'attesa.");
        }

    }

}
