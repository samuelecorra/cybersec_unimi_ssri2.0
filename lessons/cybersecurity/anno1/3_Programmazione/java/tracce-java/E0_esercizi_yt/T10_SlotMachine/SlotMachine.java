package E0_esercizi_yt.T10_SlotMachine;

import java.sql.SQLOutput;
import java.util.Scanner;

public class SlotMachine {

    // Creiamo un gioco da console molto semplice ma da ludopatia seria: la SLOT MACHINE!
    // Useremo le emoji come simboli della slot machine.
    static void main() {

        Scanner sc = new Scanner(System.in);

        // Chiediamo innanzitutto all'utente di quanto credito dispone: input sotto i 50 euro non lo faranno
        // nemmeno sedere alla macchinetta.
        System.out.print("Benvenuto alla Slot Machine! Inserisci il tuo credito iniziale (minimo 50€): ");
        int creditoIniziale = sc.nextInt();
        int credito = creditoIniziale;
        while (creditoIniziale < 50) {
            System.out.print("Credito insufficiente. Inserisci un credito minimo di 50€: ");
            creditoIniziale = sc.nextInt();
        }
        System.out.println("Hai un credito iniziale di " + creditoIniziale + "€. Buona fortuna!");
        System.out.println("----------------------------------------------");
        System.out.println("   WELCOME TO THE JAVA Virtual SLOT MACHINE   ");
        System.out.println("----------------------------------------------");

        // Mostriamo all'utente i simboli della slot machine:
        System.out.println("Simboli disponibili: 🍒 🍋 ⭐ 7️⃣ 💎 ");
        System.out.println("----------------------------------------------");

        while(credito >0) {
            System.out.println("Il tuo credito attuale è di: " + credito + "€");

            // Ora chiediamo la puntata effettiva:
            System.out.print("Inserisci la tua puntata (minimo 5€, massimo " + credito + "€): ");
            int puntata = sc.nextInt();

            // Validiamola
            if (puntata > credito) {
                System.out.println("Non hai abbastanza credito per questa puntata. Riprova.");
                continue;
            }
            if (puntata < 5) {
                System.out.println("La puntata minima è di 5€. Riprova.");
                continue;
            }

            // Se siamo qui, la puntata è valida
            credito -= puntata;
            System.out.println("Puntata accettata di " + puntata + "€.");
            System.out.println("Credito attuale dopo la puntata: " + credito + "€.");
            System.out.println("Spinning, spinning, spinning... 🎰");
            System.out.println();

            // Per simulare la slot machine, generiamo una matrice di 3x3 simboli casuali
            String[] simboli = {"🍒", "🍋", "⭐", "7️⃣", "💎"};
            String[][] griglia = new String[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int indiceSimbolo = (int) (Math.random() * simboli.length);
                    griglia[i][j] = simboli[indiceSimbolo];
                }
            } // Riempita


            // Stampiamo la griglia 3×3 bella ordinata
            System.out.println("+----+----+----+");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(griglia[i][j] + " | ");
                }
                System.out.println();
                System.out.println("+----+----+----+");
            }


            // Ora controlliamo se l'utente ha vinto qualcosa
            int vincita = 0;
            // Controlliamo le righe
            for (int i = 0; i < 3; i++) {
                if (griglia[i][0].equals(griglia[i][1]) && griglia[i][1].equals(griglia[i][2])) {
                    vincita += puntata * 2; // Vincita doppia per riga
                }
            }
            // Controlliamo le colonne
            for (int j = 0; j < 3; j++) {
                if (griglia[0][j].equals(griglia[1][j]) && griglia[1][j].equals(griglia[2][j])) {
                    vincita += puntata * 2; // Vincita doppia per colonna
                }
            }
            // Controlliamo le diagonali
            if (griglia[0][0].equals(griglia[1][1]) && griglia[1][1].equals(griglia[2][2])) {
                vincita += puntata * 3; // Vincita tripla per diagonale
            }
            if (griglia[0][2].equals(griglia[1][1]) && griglia[1][1].equals(griglia[2][0])) {
                vincita += puntata * 3; // Vincita tripla per diagonale
            }
            // Comunichiamo il risultato all'utente
            if (vincita > 0) {
                credito += vincita;
                System.out.println("Complimenti! Hai vinto " + vincita + "€!");
            } else {
                System.out.println("Peccato! Non hai vinto nulla questa volta.");
            }
            System.out.println("----------------------------------------------");
            System.out.print("Vuoi continuare a giocare? (sì/no): ");
            String risposta = sc.next();

            if (!risposta.equalsIgnoreCase("sì") && !risposta.equalsIgnoreCase("si")) {
                System.out.println("Grazie per aver giocato! Il tuo credito finale è di " + credito + "€.");
                if (credito > creditoIniziale) {
                    System.out.println("Sei uscito in profitto di " + (credito - creditoIniziale) + "€. Complimenti!");
                } else if (credito < creditoIniziale) {
                    System.out.println("Hai perso " + (creditoIniziale - credito) + "€. Meglio fortuna la prossima volta!");
                } else {
                    System.out.println("Sei uscito esattamente con lo stesso credito con cui sei entrato.");
                }
                break;
            }

        }
    }
}
