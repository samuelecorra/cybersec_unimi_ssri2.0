package E0_esercizi_yt.T7_BankingProgram;

import java.util.Scanner;

public class BankingProgram {

    // Java Banking Program per principianti
    static void main(String[] args) {

        // 1. Dichiarare le variabili necessarie:
        Scanner scanner = new Scanner(System.in);
        double balance = 0.0;
        boolean isRunning = true;
        int scelta;

        // 2. Creare un ciclo principale per il menù:
        System.out.println("Benvenuto nel programma di banking!");
        while (isRunning) {
            System.out.println("\nSeleziona un'opzione:");
            System.out.println("1. Visualizza saldo");
            System.out.println("2. Deposita denaro");
            System.out.println("3. Preleva denaro");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();

            // 3. Gestire le scelte dell'utente con uno switch:
            switch (scelta) {
                case 1:
                    viewBalance(balance);
                    break;
                case 2:
                    balance = depositMoney(scanner, balance);
                    break;
                case 3:
                    balance = withdrawMoney(scanner, balance);
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Grazie per aver usato il programma di banking. Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida. Per favore, riprova.");
                    break;
            }
        }
    }
    // Creiamo i metodi ausilirari per deposito, prelievo e visualizzazione saldo
            static void viewBalance(double balance) {
                System.out.printf("Il tuo saldo attuale è: €%.2f%n", balance);
            }

            // Ritornano entrambi il nuovo saldo dopo l'operazione
            static double withdrawMoney(Scanner scanner, double balance) {
                System.out.print("Inserisci l'importo da prelevare: €");
                double amount = scanner.nextDouble();
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    System.out.printf("Hai prelevato: €%.2f%n", amount);
                } else if (amount > balance) {
                    System.out.println("Fondi insufficienti per questo prelievo.");
                } else {
                    System.out.println("Importo non valido. Il prelievo deve essere positivo.");
                }
                return balance;
            }

            // Idem, e ricordiamone la staticità!
            static double depositMoney(Scanner scanner, double balance) {
                System.out.print("Inserisci l'importo da depositare: €");
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    balance += amount;
                    System.out.printf("Hai depositato: €%.2f%n", amount);
                } else {
                    System.out.println("Importo non valido. Il deposito deve essere positivo.");
                }
                return balance;
            }
}