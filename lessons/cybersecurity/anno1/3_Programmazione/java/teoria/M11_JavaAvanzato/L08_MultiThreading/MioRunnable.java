package M11_JavaAvanzato.L08_MultiThreading;

public class MioRunnable implements Runnable {

    private final String testo;

    public MioRunnable(String testo) {
        this.testo = testo;
    }
    @Override
    public void run () {
        // Codice da eseguire nel thread
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000); // Pausa di 500 millisecondi
                System.out.println(testo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
