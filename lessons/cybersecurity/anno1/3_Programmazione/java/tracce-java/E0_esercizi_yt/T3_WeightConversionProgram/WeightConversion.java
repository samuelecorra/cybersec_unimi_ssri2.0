package E0_esercizi_yt.T3_WeightConversionProgram;

import java.util.Scanner;

public class WeightConversion {

    // PROGRAMMA DI CONVERSIONE DEL PESO DA CHILOGRAMMI A LIBBRE E VICEVERSA
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double massa;
        double massaConvertita;
        int scelta;

        System.out.println("Benvenuto al programma di conversione del peso!");
        System.out.println("Scegli l'operazione da eseguire:");
        System.out.println("1. Converti da chilogrammi a libbre");
        System.out.println("2. Converti da libbre a chilogrammi");
        System.out.print("Inserisci la tua scelta (1 o 2): ");
        scelta = sc.nextInt();

        if(scelta == 1) {
            System.out.print("Inserisci il peso in chilogrammi: ");
            massa = sc.nextDouble();
            massaConvertita = massa * 2.20462; // Conversione kg a lb
            System.out.printf("%.2f chilogrammi sono %.2f libbre.%n", massa, massaConvertita);
        } else if(scelta == 2) {
            System.out.print("Inserisci il peso in libbre: ");
            massa = sc.nextDouble();
            massaConvertita = massa / 2.20462; // Conversione lb a kg
            System.out.printf("%.2f libbre sono %.2f chilogrammi.%n", massa, massaConvertita);
        } else {
            System.out.println("Scelta non valida. Per favore, esegui il programma di nuovo e scegli 1 o 2.");
        }

        sc.close();
    }
}
