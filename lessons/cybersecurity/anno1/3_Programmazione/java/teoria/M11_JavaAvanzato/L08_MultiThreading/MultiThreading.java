package M11_JavaAvanzato.L08_MultiThreading;

public class MultiThreading {

    // Promemoria: il thread è un flusso esecutivo di istruzioni che runna indipendentemente.
    // Utile per background tasks o time-consumiung operations.

    // Il multithreading permette di eseguire più thread contemporaneamente.
    // Vantaggi:
    // 1. Responsività migliorata (es. UI thread separato da background tasks)
    // 2. Utilizzo migliore delle risorse CPU (sfruttando core multipli)
    // 3. Semplificazione della gestione di operazioni concorrenti (es. server handling multiple clients)

    static void main() {

        // MioRunnable mioRunnable = new MioRunnable();
        // Thread thread1 = new Thread(mioRunnable);
        // thread1.start();

        // A sto punto possiamo anche snellire il codice passando il new MioRunnable direttamente al costruttore di Thread:
        Thread thread1 = new Thread(new MioRunnable("Ping")); // Funziona comunque come sopra!
        Thread thread2 = new Thread(new MioRunnable("Pong"));

        System.out.println("Inizia il gioco...!");
        thread1.start();
        thread2.start();

        // Ma se facciamo così, il main thread non aspetta che gli altri thread finiscano prima di proseguire!
        // Ovvero uscirebbe scritto game over prima che i thread abbiano finito di eseguire il loro codice.
        // Per far sì che il main thread aspetti, usiamo il metodo join():
        // esso blocca il thread chiamante (main) finché il thread su cui è chiamato (thread1 o thread2) non termina.
        // In poche parole è come se thread1 e thread2 fossero "joinati" al main thread, che aspetta la loro fine.
        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e) {
            System.out.println("Il main thread è stato interrotto.");
        }

        System.out.println("Game over!");
    }
}
