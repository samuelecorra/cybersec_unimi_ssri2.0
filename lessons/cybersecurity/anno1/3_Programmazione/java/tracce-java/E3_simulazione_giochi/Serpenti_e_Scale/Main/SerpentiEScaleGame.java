package E3_simulazione_giochi.Serpenti_e_Scale.Main;

import E3_simulazione_giochi.Serpenti_e_Scale.Classi.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SerpentiEScaleGame {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Benvenuto a Serpenti e Scale!");

            int numGiocatori = leggiNumeroGiocatori(scanner);
            List<String> nomi = new ArrayList<>();
            Set<String> nomiUsati = new HashSet<>();

            for (int i = 1; i <= numGiocatori; i++) {
                String nome;
                while (true) {
                    System.out.print("Nome del giocatore " + i + ": ");
                    nome = scanner.nextLine().trim();
                    if (nome.isEmpty()) {
                        nome = "Giocatore" + i;
                    }
                    if (nomiUsati.add(nome)) {
                        break;
                    }
                    System.out.println("Nome già usato: scegline uno diverso.");
                }
                nomi.add(nome);
            }

            Partita partita = new Partita(nomi, scanner);
            partita.gioca();
        }
    }

    private static int leggiNumeroGiocatori(Scanner scanner) {
        while (true) {
            System.out.print("Quanti giocatori? (min 2): ");
            String input = scanner.nextLine().trim();
            try {
                int numero = Integer.parseInt(input);
                if (numero >= 2) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Il messaggio comune qui sotto copre anche l'input non numerico.
            }
            System.out.println("Inserisci un numero intero maggiore o uguale a 2.");
        }
    }
}
