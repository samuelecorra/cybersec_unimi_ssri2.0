package M04_Stringhe.L03_ImmutabilitaStringBuilder;

import java.util.Objects;

public class ImmutabilitaStringBuilder {
    public static void main(String[] args) {
        // String è immutabile: i suoi metodi non modificano l'oggetto esistente,
        // ma restituiscono una nuova stringa.
        String saluto = "ciao";
        saluto.toUpperCase();
        System.out.println(saluto); // ancora "ciao": il risultato è stato ignorato

        String maiuscolo = saluto.toUpperCase();
        System.out.println(maiuscolo); // "CIAO"

        // Il compilatore concatena bene poche parti con +. Per costruire una
        // stringa in un ciclo è preferibile StringBuilder, che è mutabile.
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(i);
        }
        String elenco = builder.toString();
        System.out.println("Elenco: " + elenco);

        // equals confronta il contenuto. Objects.equals è null-safe e quindi
        // evita NullPointerException se uno dei riferimenti è null.
        String valoreAssente = null;
        System.out.println("Confronto null-safe: " + Objects.equals(valoreAssente, "testo"));

        // StringBuilder non è sincronizzato ed è la scelta normale locale.
        // StringBuffer offre operazioni sincronizzate, ma ha un costo maggiore.
    }
}
