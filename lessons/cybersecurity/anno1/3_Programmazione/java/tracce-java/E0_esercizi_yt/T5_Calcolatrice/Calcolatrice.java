package E0_esercizi_yt.T5_Calcolatrice;

import java.util.Scanner;

public class Calcolatrice {

    // Dopo aver compreso gli switch enhanced, vediamo come realizzare una semplice calcolatrice
    // che chiede all'utente due numeri e l'operazione da eseguire (+, -, *, /), e poi mostra il risultato.
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double num1, num2, risultato = 0;
        char operazione;
        boolean operazioneValida = true;
        System.out.println("Benvenuto nella calcolatrice!");
        System.out.print("Inserisci il primo numero: ");
        num1 = input.nextDouble();
        System.out.print("Inserisci l'operazione (+, -, *, /, ^): ");
        operazione = input.next().charAt(0);
        System.out.print("Inserisci il secondo numero: ");
        num2 = input.nextDouble();

        switch (operazione) {
            case '+' -> risultato = num1 + num2;
            case '-' -> risultato = num1 - num2;
            case '*' -> risultato = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println("Errore: Divisione per zero non consentita.");
                    operazioneValida = false;
                } else {
                    risultato = num1 / num2;
                }
            }
            case '^' -> {
                risultato = Math.pow(num1, num2);
                System.out.printf("Risultato: %.2f ^ %.2f = %.2f%n", num1, num2, risultato);
            }
            default -> {
                System.out.println("Operazione non valida.");
                operazioneValida = false;
            }
        }
            // Ora decidiamo se stampare in base al booleano:
            if(operazioneValida) {
                System.out.printf("Risultato: %.2f %c %.2f = %.2f%n", num1, operazione, num2, risultato);
            }

            input.close();
    }
}
