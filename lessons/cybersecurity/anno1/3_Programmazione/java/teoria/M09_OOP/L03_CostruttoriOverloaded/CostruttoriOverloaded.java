package M09_OOP.L03_CostruttoriOverloaded;

public class CostruttoriOverloaded {

    // Abbiamo già anticipato il concetto di costruttore sovraccaricato nella lezione precedente.
    // In questa lezione lo approfondiamo ulteriormente.

    static void main() {

        Utente user1 = new Utente("mario_rossi");
        System.out.println("Utente 1: " + user1.username + ", Email: " + user1.email + ", Età: " + user1.eta);

        Utente user2 = new Utente("luigi_verdi", "luigiverdi@gmail.com");
        System.out.println("Utente 2: " + user2.username + ", Email: " + user2.email + ", Età: " + user2.eta);

        Utente user3 = new Utente("anna_bianchi", "anninawhite@outlook.com", 28);
        System.out.println("Utente 3: " + user3.username + ", Email: " + user3.email + ", Età: " + user3.eta);

        Utente user4 = new Utente();
        System.out.println("Utente 4: " + user4.username + ", Email: " + user4.email + ", Età: " + user4.eta);

    }
}