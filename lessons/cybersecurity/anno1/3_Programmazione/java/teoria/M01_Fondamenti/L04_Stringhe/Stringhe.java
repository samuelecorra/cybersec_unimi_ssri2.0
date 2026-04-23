package M01_Fondamenti.L04_Stringhe;

public class Stringhe {

    static void main() {
        // Per ora array e oggetti sono troppo avanzati, non abbiamo le basi!

        // => Iniziamo dalle stringhe:

        // STRINGHE -> DOPPIE VIRGOLETTE " "
        // CARATTERI -> VIRGOLETTE SINGOLE ' '

        String testo = "Ciao, sono una stringa";

        // Concatenazione di stringhe mediante println con operatore +
        System.out.println(testo + " e posso essere concatenata con altre stringhe!");
        String testo2 = " e sono più comoda rispetto al C, non credi?";
        System.out.println(testo + testo2);

        // Ovviamente non c'è limite al numero di concatenazioni, ma
        // ATTENZIONE: il vero VANTAGGIO è che possiamo mischiare i tipi di dato concatenati:
        int anno = 2000;
        String inizio = "Il millenium Bug è un problema del ";
        System.out.println(inizio + anno + ", non del " + (anno - 1) + "!");

    }
}
