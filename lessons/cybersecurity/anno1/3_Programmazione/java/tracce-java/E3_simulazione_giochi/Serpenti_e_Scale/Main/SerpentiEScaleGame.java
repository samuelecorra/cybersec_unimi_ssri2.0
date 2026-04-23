package E3_simulazione_giochi.Serpenti_e_Scale.Main;

import E3_simulazione_giochi.Serpenti_e_Scale.Classi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SerpentiEScaleGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto a Serpenti e Scale!");

        int numGiocatori;
        do {
            System.out.print("Quanti giocatori? (min 2): ");
            numGiocatori = scanner.nextInt();
            scanner.nextLine(); // consuma il newline
        } while (numGiocatori < 2);

        List<String> nomi = new ArrayList<>();
        for (int i = 1; i <= numGiocatori; i++) {
            System.out.print("Nome del giocatore " + i + ": ");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                nome = "Giocatore" + i;
            }
            nomi.add(nome);
        }

        Partita partita = new Partita(nomi);
        partita.gioca();
    }
}
