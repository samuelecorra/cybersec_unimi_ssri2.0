package M04_Stringhe.L01_MetodiStringhe;

public class MetodiStringhe {
    static void main() {
        // I metodi delle stringhe in Java permettono di manipolare e
        // analizzare le stringhe in vari modi.

        String esempio = "Ciao, Mondo!";

        // 1. length(): Restituisce la lunghezza della stringa
        int lunghezza = esempio.length();
        System.out.println("Lunghezza della stringa: " + lunghezza); // Output: 13

        // 2. charAt(int index): Restituisce il carattere alla posizione specificata
        char carattere = esempio.charAt(7);
        System.out.println("Carattere alla posizione 7: " + carattere); // Output: M
        // 3. dualmente, indexOf(char c): Restituisce l'indice della prima occorrenza del carattere specificato
        int indice = esempio.indexOf('M');
        System.out.println("Indice del carattere 'M': " + indice); // Output: 7
        // 4. lastIndexOf(char c): Restituisce l'indice dell'ultima occorrenza del carattere specificato
        int ultimoIndice = esempio.lastIndexOf('o');
        System.out.println("Ultimo indice del carattere 'o': " + ultimoIndice); // Output: 9

        // 5. toUpperCase(): Converte la stringa in maiuscolo
        String maiuscolo = esempio.toUpperCase();
        System.out.println("Stringa in maiuscolo: " + maiuscolo); // Output: CIAO, MONDO!
        // 6. toLowerCase(): Converte la stringa in minuscolo
        String minuscolo = esempio.toLowerCase();
        System.out.println("Stringa in minuscolo: " + minuscolo); // Output: ciao, mondo!

        // 7. trim(): Rimuove gli spazi bianchi iniziali e finali
        String conSpazi = "   Spazi bianchi   ";
        String senzaSpazi = conSpazi.trim();
        System.out.println("Stringa senza spazi: '" + senzaSpazi + "'"); // Output: 'Spazi bianchi'

        // 8. replace(char oldChar, char newChar): Sostituisce tutte le occorrenze di un carattere con un altro
        String sostituita = esempio.replace('o', '0');
        System.out.println("Stringa con sostituzioni: " + sostituita); // Output: Cia0, M0nd0!

        // 9. boolean isEmpty(): Verifica se la stringa è vuota
        String vuota = "";
        System.out.println("La stringa è vuota? " + vuota.isEmpty()); // Output: true

        String name = "Java";
        if (name.isEmpty()) { // Come nella lezione sulle condizioni avevamo preannunciato!
            System.out.println("La stringa è vuota.");
        } else {
            System.out.println("Bella per " + name); // Questo verrà stampato
        }

        // 10. contains(CharSequence s): Verifica se la stringa contiene una sottostringa specificata
        boolean contiene = esempio.contains("Mondo");
        System.out.println("La stringa contiene 'Mondo'? " + contiene); // Output: true

        // Quindi anche questo metodo si presta bene alle condizioni:
        if (esempio.contains("Ciao")) {
            System.out.println("La stringa inizia con un saluto.");
        } else {
            System.out.println("Nessun saluto trovato.");
        }

        // 11. equals(Object anObject): Confronta due stringhe per l'uguaglianza
        // CASE SENSITIVE
        String altraStringa = "Ciao, Mondo!";
        boolean uguali = esempio.equals(altraStringa);
        System.out.println("Le due stringhe sono uguali? " + uguali); // Output: true

        // Questo metodo è molto utile per confrontare stringhe in condizioni:
        String passwordInserita = "segreta";
        String passwordCorretta = "segreta";

        if (passwordInserita.equals(passwordCorretta)) {
            System.out.println("Accesso consentito.");
        } else {
            System.out.println("Accesso negato.");
        }

        // 12. equalsIgnoreCase(String anotherString): Confronta due stringhe per l'uguaglianza ignorando il maiuscolo/minuscolo
        String stringa1 = "java";
        String stringa2 = "JAVA";
        boolean ugualiIgnoreCase = stringa1.equalsIgnoreCase(stringa2);
        System.out.println("Le due stringhe sono uguali (ignorando maiuscolo/minuscolo)? " + ugualiIgnoreCase); // Output: true

    }
}
