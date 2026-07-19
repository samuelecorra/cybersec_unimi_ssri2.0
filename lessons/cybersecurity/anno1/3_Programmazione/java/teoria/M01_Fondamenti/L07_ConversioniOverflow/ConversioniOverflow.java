package M01_Fondamenti.L07_ConversioniOverflow;

public class ConversioniOverflow {
    public static void main(String[] args) {
        // Gli otto tipi primitivi sono byte, short, int, long, float, double,
        // char e boolean. Le costanti MIN_VALUE/MAX_VALUE documentano i limiti.
        System.out.println("Intervallo int: " + Integer.MIN_VALUE + " ... " + Integer.MAX_VALUE);
        System.out.println("Intervallo long: " + Long.MIN_VALUE + " ... " + Long.MAX_VALUE);

        // Conversione widening: da un tipo numerico più piccolo a uno più ampio.
        // È implicita perché ogni valore int è rappresentabile come long.
        int popolazione = 1_500_000;
        long popolazioneEstesa = popolazione;
        System.out.println("Widening int -> long: " + popolazioneEstesa);

        // Un letterale intero è int per default: la L rende questo letterale long.
        long distanza = 9_000_000_000L;
        // Un letterale decimale è double per default: la F lo rende float.
        float temperatura = 36.5F;
        System.out.println("Long e float: " + distanza + ", " + temperatura);

        // Conversione narrowing: può perdere informazione e richiede un cast.
        double misura = 12.99;
        int parteIntera = (int) misura; // tronca verso zero, non arrotonda
        System.out.println("Cast double -> int: " + parteIntera);

        int valoreGrande = 130;
        byte valoreRistretto = (byte) valoreGrande;
        System.out.println("Cast int 130 -> byte: " + valoreRistretto); // -126

        // Nelle espressioni, byte/short/char vengono promossi almeno a int.
        byte a = 10;
        byte b = 20;
        int sommaPromossa = a + b;
        System.out.println("Somma promossa a int: " + sommaPromossa);

        // Gli interi Java usano complemento a due e l'overflow fa wrap-around:
        // non viene sollevata automaticamente un'eccezione.
        int massimo = Integer.MAX_VALUE;
        int overflow = massimo + 1;
        System.out.println("Integer.MAX_VALUE + 1: " + overflow);

        // Se il risultato matematico deve superare int, almeno un operando va
        // promosso prima dell'operazione.
        int fattore = 100_000;
        long prodottoCorretto = (long) fattore * fattore;
        System.out.println("Prodotto calcolato in long: " + prodottoCorretto);

        // Per rilevare l'overflow, Java offre anche Math.addExact,
        // Math.subtractExact e Math.multiplyExact, che lanciano
        // ArithmeticException invece di restituire un valore avvolto.
    }
}
