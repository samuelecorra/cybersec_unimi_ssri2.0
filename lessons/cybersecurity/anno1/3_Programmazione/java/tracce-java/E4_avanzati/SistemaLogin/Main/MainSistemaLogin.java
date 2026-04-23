package E4_avanzati.SistemaLogin.Main;

import E4_avanzati.SistemaLogin.Classi.*;
import java.util.Scanner;

public class MainSistemaLogin {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaLogin sistema = new SistemaLogin();

        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== MENU SISTEMA DI LOGIN ===");
            System.out.println("1) Registrazione utente");
            System.out.println("2) Login");
            System.out.println("3) Cambio password");
            System.out.println("4) Mostra utenti registrati");
            System.out.println("0) Esci");
            System.out.print("> ");

            String scelta = scanner.nextLine().trim();

            switch (scelta) {
                case "1":
                    System.out.print("Inserisci username: ");
                    String usernameReg = scanner.nextLine();

                    System.out.print("Inserisci password: ");
                    String passwordReg = scanner.nextLine();

                    sistema.registraUtente(usernameReg, passwordReg);
                    break;

                case "2":
                    System.out.print("Username: ");
                    String usernameLogin = scanner.nextLine();

                    System.out.print("Password: ");
                    String passwordLogin = scanner.nextLine();

                    sistema.login(usernameLogin, passwordLogin);
                    break;

                case "3":
                    System.out.print("Username: ");
                    String usernameCp = scanner.nextLine();

                    System.out.print("Vecchia password: ");
                    String vecchiaPassword = scanner.nextLine();

                    System.out.print("Nuova password: ");
                    String nuovaPassword = scanner.nextLine();

                    sistema.cambiaPassword(usernameCp, vecchiaPassword, nuovaPassword);
                    break;

                case "4":
                    sistema.mostraUtentiRegistrati();
                    break;

                case "0":
                    esci = true;
                    System.out.println("Chiusura del programma...");
                    break;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;
            }
        }

        scanner.close();
    }
}
