package M11_JavaAvanzato.L07_Threading;

public class MioRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000); // Pausa di 1 secondo
            } catch (InterruptedException e) {
                System.out.println("Il thread è stato interrotto...");
            }

            if(i == 5) {
                System.out.println("\nTempo scaduto!");
                // Forziamo l'uscita prematura del programma
                System.exit(0);
            }
        }
    }
}
