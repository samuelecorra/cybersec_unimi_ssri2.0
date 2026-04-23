package M02_Matematica.L02_LaClasseMath;

public class LaClasseMath {
    static void main() {
        // La classe Math in Java fornisce metodi e costanti matematiche utili.
        // NON SERVE IMPORTARLA, FA PARTE DEL PACKAGE JAVA.LANG CHE VIENE IMPORTATO AUTOMATICAMENTE.
        // E soprattutto non serve istanziarla, perché tutti i suoi membri sono statici (si capirà dopo).

        // ==========================================================

        //                COSTANTI MATEMATICHE
        // ==========================================================

        System.out.println("Costante Math.PI: " + Math.PI); // Valore di π
        System.out.println("Costante Math.E: " + Math.E);   // Valore di e

        // ==========================================================
        //                METODI MATEMATICI COMUNI
        // ==========================================================

        // 1. Potenza
        double base = 2.0;
        double esponente = 3.0;
        double potenza = Math.pow(base, esponente); // 2^3 = 8.0
        System.out.println("2 elevato alla 3 = " + potenza);

        // 2. Radice quadrata
        double radice = Math.sqrt(16.0); // √16 = 4.
        System.out.println("Radice quadrata di 16 = " + radice);

        // 3. Valore assoluto
        int valoreNegativo = -10;
        int valoreAssoluto = Math.abs(valoreNegativo); // | -10 |
        System.out.println("Valore assoluto di -10 = " + valoreAssoluto);

        // 4. fabs per double
        double valoreNegativoDouble = -15.5;
        double valoreAssolutoDouble = Math.abs(valoreNegativoDouble); // | -15.5 |
        System.out.println("Valore assoluto di -15.5 = " + valoreAssolutoDouble);

        // 5. Arrotondamento generalizzato
        double numero1 = 5.7;
        double arrotondato1 = Math.round(numero1); // 6.0, arrotonda al più vicino
        System.out.println("5.7 arrotondato = " + arrotondato1);
        double numero2 = 5.3;
        double arrotondato2 = Math.round(numero2); // 5.0
        System.out.println("5.3 arrotondato = " + arrotondato2);

        // 6. Arrotondamento per difetto
        double arrotondatoPerDifetto = Math.floor(5.9); // 5.0
        System.out.println("5.9 arrotondato per difetto = " + arrotondatoPerDifetto);
        // 7. Arrotondamento per eccesso
        double arrotondatoPerEccesso = Math.ceil(5.1); // 6.0
        System.out.println("5.1 arrotondato per eccesso = " + arrotondatoPerEccesso);

        // 8. Massimo e minimo
        int maxVal = Math.max(10, 20); // 20
        int minVal = Math.min(10, 20); // 10
        System.out.println("Massimo tra 10 e 20 = " + maxVal);
        System.out.println("Minimo tra 10 e 20 = " + minVal);

        // Applichiamo Pitagora
        double cateto1 = 3.0;
        double cateto2 = 4.0;
        double ipotenusa = Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2)); // √(3^2 + 4^2)
        System.out.println("Ipotenusa di un triangolo con cateti 3 e 4 = " + ipotenusa);
        // CLASSICO ESEMPIO DI COME POSSIAMO SFRUTTARE LE PARENTESI PER AVERE IL CONTROLLO TOTALE
        // SULL'ORDINE DI VALUTAZIONE DELLE ESPRESSIONI MATEMATICHE!

        // Insomma, le possibilità sono molteplici:
        // Calcoliamo al volo, dato un raggio:
        // Area e circonferenza del cerchio, area della sfera, volume della sfera
        double raggio = 5.0;

        double areaCerchio = Math.PI * Math.pow(raggio, 2); // πr^2
        double circonferenza = 2 * Math.PI * raggio; // 2πr

        double areaSfera = 4 * Math.PI * Math.pow(raggio, 2); // 4πr^2
        double volumeSfera = (4.0 / 3.0) * Math.PI * Math.pow(raggio, 3); // (4/3)πr^3

        System.out.println("Per un cerchio/sfera di raggio " + raggio + ":");
        System.out.println("Area del cerchio = " + areaCerchio);
        System.out.println("Circonferenza = " + circonferenza);
        System.out.println("Area della sfera = " + areaSfera);
        System.out.println("Volume della sfera = " + volumeSfera);
        // Però così facendo stampiamo sempre 6 cifre decimali.
        // Usiamo printf per formattare meglio l'output:
        System.out.printf("Area del cerchio (formattata) = %.2f%n", areaCerchio);
        System.out.printf("Circonferenza (formattata) = %.2f%n", circonferenza);
        System.out.printf("Area della sfera (formattata) = %.2f%n", areaSfera);
        System.out.printf("Volume della sfera (formattata) = %.2f%n", volumeSfera);


    }
}
