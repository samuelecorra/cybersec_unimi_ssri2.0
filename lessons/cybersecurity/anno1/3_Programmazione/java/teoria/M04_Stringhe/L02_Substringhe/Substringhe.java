package M04_Stringhe.L02_Substringhe;

import java.util.Scanner;

public class Substringhe {

    // Prendiamoci una lezione a parte dai metodi "standard" delle stringhe
    // per parlare delle substringhe, ovvero delle "sottostringhe".
    public static void main(String[] args) {

        // Una sottostringa è una parte di una stringa più grande.
        // In Java, possiamo estrarre una sottostringa usando il metodo substring().

        // Sintassi:
        // substring(int beginIndex) - Restituisce la sottostringa dalla posizione beginIndex fino alla fine della stringa.
        // substring(int beginIndex, int endIndex) - Restituisce la sottostringa dalla posizione beginIndex fino a endIndex-1.

        // Esempio 1: Estrarre una sottostringa dall'indice 11 fino alla fine
        String mail = "principessa.gommarosa80@gmail.com";
        // principessa = 11 lettere, +1 il punto, +9 gommarosa, +2 le cifre, +1 la chiocciola = 23 caratteri prima di gmail.com

        // Se passiamo indice di inizio e fine, il carattere all'indice di fine non viene incluso:
        // La chiocciola è il 24esimo carattere, quindi indice 23
        String username = mail.substring(0, 23); // Da indice 0 a chiocciola esclusa
        String dominio = mail.substring(24); // Da indice della chiocciola+1 fino alla fine
        System.out.println("Username email: " + username); // Output: principessa.gommarosa80
        System.out.println("Dominio email: " + dominio); // Output: gmail.com

        // Nota: Gli indici in Java sono basati su zero, quindi il primo carattere è all'indice 0.

        // Possiamo subito migliorare il nostro email-slicer usando indexOf per trovare la posizione della chiocciola:
        String username2 = mail.substring(0, mail.indexOf('@'));
        String dominio2 = mail.substring(mail.indexOf('@') + 1);

        System.out.println("Username email (metodo dinamico): " + username2); // Output: principessa.gommarosa80
        System.out.println("Dominio email (metodo dinamico): " + dominio2); // Output: gmail.com

        // Applichiamo alcuni controlli strutturali minimi. Non è una validazione
        // completa secondo gli standard delle email e non sostituisce una conferma
        // tramite messaggio inviato all'indirizzo.
        Scanner inputUtente = new Scanner(System.in);
        System.out.print("Inserisci la tua email: ");
        String emailUtente = inputUtente.nextLine();

        int indiceChiocciola = emailUtente.indexOf('@');
        boolean unaSolaChiocciola = indiceChiocciola == emailUtente.lastIndexOf('@');
        boolean chiocciolaInPosizioneValida = indiceChiocciola > 0
                && indiceChiocciola < emailUtente.length() - 1;

        if (!unaSolaChiocciola || !chiocciolaInPosizioneValida) {
            System.out.println("Formato non valido: serve una sola @, non iniziale né finale.");
        } else {
            String user = emailUtente.substring(0, indiceChiocciola);
            String domain = emailUtente.substring(indiceChiocciola + 1);
            int indicePunto = domain.lastIndexOf('.');
            if (user.contains("-")) {
                System.out.println("Per questa regola didattica l'username non può contenere '-'.");
            } else if (indicePunto <= 0 || indicePunto == domain.length() - 1) {
                System.out.println("Dominio non valido: il punto non può essere iniziale o finale.");
            } else {
                System.out.println("I controlli strutturali minimi sono superati.");
                System.out.println("Username: " + user);
                System.out.println("Dominio: " + domain);
            }
        }
        inputUtente.close();

    }
}
