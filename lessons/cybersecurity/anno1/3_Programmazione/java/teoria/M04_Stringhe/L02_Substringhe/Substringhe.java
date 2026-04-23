package M04_Stringhe.L02_Substringhe;

import java.util.Scanner;

public class Substringhe {

    // Prendiamoci una lezione a parte dai metodi "standard" delle stringhe
    // per parlare delle substringhe, ovvero delle "sottostringhe".
    static void main() {

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

        // Uniamo l'utile al dilettevole e iniziamo a controllare se l'email è valida:
        Scanner inputUtente = new Scanner(System.in);
        System.out.print("Inserisci la tua email: ");
        String emailUtente = inputUtente.nextLine();

        if (!(emailUtente.contains("@"))) {
            System.out.println("Email non valida! Manca la chiocciola (@).");
        } else {
            String user = emailUtente.substring(0, emailUtente.indexOf('@'));
            String domain = emailUtente.substring(emailUtente.indexOf('@') + 1);
            if (user.contains("-")) {
                System.out.println("Non puoi mettere il trattino (-) nell'username!");
            } else if (!(domain.contains("."))) {
                System.out.println("Dominio non valido! Manca il punto (.)");
            } else {
                System.out.println("Email valida!");
                System.out.println("Username: " + user);
                System.out.println("Dominio: " + domain);
            }
        }

    }
}
