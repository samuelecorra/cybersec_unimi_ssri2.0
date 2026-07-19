package M11_JavaAvanzato.L03_TimerTasks;

import java.util.Timer;
import java.util.TimerTask;

public class MainTimerTasks {

    // Timer = classe che permette di programmare l'esecuzione di un task (un pezzo di codice) in un momento futuro
        // usata per: inviare notifiche, schedulare updates, eseguire operazioni periodiche

    // TimerTask = classe astratta che rappresenta il task da eseguire
        // Estenderemo la classe TimerTask per definire il nostro task personalizzato, ergo
        // creiamo una sub-classe che overrida il metodo run()

    public static void main(String[] args) {

        // Innanzitutto servono due oggetti:
        Timer timer1 = new Timer();  // il timer che gestisce l'esecuzione dei task
        TimerTask task1 = new TimerTask() {

            int counter = 3;

            @Override
            public void run() {
                System.out.println("Esecuzione periodica del task. Conteggio: " + counter);
                counter--;
                if (counter == 0) {
                    System.out.println("Ho finito di contare, quindi termino il timer!");
                    timer1.cancel(); // Termina il timer e tutti i task associati
                }
            }
        };

        // Un TimerTask può essere pianificato una sola volta: riusare la stessa
        // istanza in due chiamate a schedule causerebbe IllegalStateException.
        timer1.schedule(task1, 3000, 2000); // Prima esecuzione dopo 3 s, poi ogni 2 s

        // Però non possiamo eseguire all'infinito, quindi serve una condizione di uscita come nei loops!

    }
}
