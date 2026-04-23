package E0_esercizi_yt.T2_ShoppingCartProgram;

import java.util.Scanner;

public class ShoppingCartProgram {
    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String articolo;
        double prezzo;
        int quantita;
        char valuta = '€';
        double totale = 0.0;

        System.out.println("Benvenuto al programma del carrello della spesa!");
        System.out.println("-----------------------------------------------");
        System.out.println("Che articolo desideri acquistare?");
        articolo = scanner.nextLine();
        System.out.println("Inserisci il prezzo unitario di " + articolo + " in " + valuta + ":");
        prezzo = scanner.nextDouble();
        System.out.println("Quante unità di " + articolo + " desideri acquistare?");
        quantita = scanner.nextInt();

        totale = prezzo * quantita;
        int iva = 22;
        double diCuiIVA = (totale * iva) / 100;
        System.out.print("Il totale per " + quantita + " unità di " + articolo + " è: " + totale + valuta);
        System.out.print(" (di cui " + diCuiIVA + valuta + " di IVA).");

    }
}
