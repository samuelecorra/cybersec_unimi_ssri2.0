package E0_esercizi_yt.T2_ShoppingCartProgram;

import java.util.Scanner;

public class ShoppingCartProgram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String articolo;
        double prezzo;
        int quantita;
        char valuta = '€';
        double totale;

        System.out.println("Benvenuto al programma del carrello della spesa!");
        System.out.println("-----------------------------------------------");
        System.out.println("Che articolo desideri acquistare?");
        articolo = scanner.nextLine();
        System.out.println("Inserisci il prezzo unitario di " + articolo + " in " + valuta + ":");
        prezzo = scanner.nextDouble();
        if (!Double.isFinite(prezzo) || prezzo < 0) {
            System.out.println("Il prezzo deve essere un numero finito non negativo.");
            scanner.close();
            return;
        }
        System.out.println("Quante unità di " + articolo + " desideri acquistare?");
        quantita = scanner.nextInt();
        if (quantita < 0) {
            System.out.println("La quantità non può essere negativa.");
            scanner.close();
            return;
        }

        totale = prezzo * quantita;
        final double aliquotaIva = 0.22;
        // Se il prezzo inserito comprende già l'IVA al 22%, la quota d'imposta
        // si scorpora dal lordo: lordo * 22 / 122, non lordo * 22 / 100.
        double ivaCompresa = totale * aliquotaIva / (1 + aliquotaIva);
        System.out.printf("Il totale per %d unità di %s è: %.2f%c", quantita, articolo, totale, valuta);
        System.out.printf(" (di cui %.2f%c di IVA).%n", ivaCompresa, valuta);

        scanner.close();

    }
}
