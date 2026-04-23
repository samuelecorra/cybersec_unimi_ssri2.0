package M03_Condizioni_e_Logica.L03_TernaryOperator;

public class Ternary {
    public static void main(String[] args) {
        // Operatore ternario in Java
        // Sintassi: condizione ? valore_se_vero : valore_se_falso;

        int a = 10;
        int b = 20;

        // Esempio 1: Trovare il massimo tra due numeri
        int max = (a > b) ? a : b;
        System.out.println("Il massimo tra " + a + " e " + b + " è: " + max);

        // Esempio 2: Verificare se un numero è pari o dispari
        int numero = 15;
        String risultato = (numero % 2 == 0) ? "pari" : "dispari";
        System.out.println("Il numero " + numero + " è: " + risultato);

        // Esempio 3: Assegnare un messaggio in base all'età
        int eta = 18;
        String messaggio = (eta >= 18) ? "Sei maggiorenne." : "Sei minorenne.";
        System.out.println(messaggio);

        // Esempio 4: dare un'esito in base al voto
        int voto = 75;
        String esito = (voto >= 60) ? "Promosso" : "Bocciato";
        System.out.println("Esito dell'esame: " + esito);

        // LA VERA DOMANDA E' - QUANDO E' PREFERIBILE USARE L'OPERATORE TERNARIO RISPETTO ALL'IF-ELSE?
        // Risposta: Quando si tratta di assegnare valori in modo conciso basato su una condizione semplice.
        // Per condizioni più complesse o per eseguire più azioni, è meglio usare if-else.

    }
}
