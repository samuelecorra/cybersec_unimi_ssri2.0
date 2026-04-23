package E0_esercizi_yt.T13_CountdownTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

    static void main() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Da quanti secondi vuoi iniziare il conto alla rovescia: ");
        int startSeconds = sc.nextInt();

        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {

            int counter = startSeconds;

            @Override
            public void run(){

                System.out.println("Countdown: " + counter);
                counter--;
                if(counter < 0){
                    System.out.println("Buon Capodanno!");
                    timer1.cancel();
                }
            }
        };

        timer1.scheduleAtFixedRate(task1, 0, 1000); // atFixedRate = esegui ripetutamente ogni tot ms

    }
}
