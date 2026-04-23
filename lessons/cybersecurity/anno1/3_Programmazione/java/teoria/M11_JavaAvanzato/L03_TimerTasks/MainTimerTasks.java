package M11_JavaAvanzato.L03_TimerTasks;

import java.util.Timer;
import java.util.TimerTask;

public class MainTimerTasks {

    // Timer = classe che permette di programmare l'esecuzione di un task (un pezzo di codice) in un momento futuro
        // usata per: inviare notifiche, schedulare updates, eseguire operazioni periodiche

    // TimerTask = classe astratta che rappresenta il task da eseguire
        // Estenderemo la classe TimerTask per definire il nostro task personalizzato, ergo
        // creiamo una sub-classe che overrida il metodo run()

    static void main() {

        // Innanzitutto servono due oggetti:
        Timer timer1 = new Timer();  // il timer che gestisce l'esecuzione dei task
        TimerTask task1 = new TimerTask() {

            int counter = 3;

            @Override
            public void run() {
                System.out.println("Eseguendo il task come previsto: 3sec dopo!");
                counter--;
                if (counter == 0) {
                    System.out.println("Ho finito di contare, quindi termino il timer!");
                    timer1.cancel(); // Termina il timer e tutti i task associati
                }
            }
        };

        timer1.schedule(task1, 3000); // Dopo una costante in ms, una singola volta
        timer1.schedule(task1, 3000, 2000); // Dopo una costante in ms, ripetutamente ogni tot ms

        // Però non possiamo eseguire all'infinito, quindi serve una condizione di uscita come nei loops!

    }
}
