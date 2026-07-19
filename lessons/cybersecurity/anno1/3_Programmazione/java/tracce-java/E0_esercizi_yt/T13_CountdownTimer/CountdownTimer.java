package E0_esercizi_yt.T13_CountdownTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Da quanti secondi vuoi iniziare il conto alla rovescia: ");
        while (!sc.hasNextInt()) {
            System.out.print("Inserisci un numero intero non negativo: ");
            sc.next();
        }
        int startSeconds = sc.nextInt();
        while (startSeconds < 0) {
            System.out.print("Inserisci un numero intero non negativo: ");
            while (!sc.hasNextInt()) {
                sc.next();
            }
            startSeconds = sc.nextInt();
        }
        sc.close();

        final int secondiIniziali = startSeconds;
        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {

            int counter = secondiIniziali;

            @Override
            public void run(){

                System.out.println("Countdown: " + counter);
                if (counter == 0) {
                    System.out.println("Buon Capodanno!");
                    timer1.cancel();
                    return;
                }
                counter--;
            }
        };

        timer1.scheduleAtFixedRate(task1, 0, 1000); // atFixedRate = esegui ripetutamente ogni tot ms

    }
}
