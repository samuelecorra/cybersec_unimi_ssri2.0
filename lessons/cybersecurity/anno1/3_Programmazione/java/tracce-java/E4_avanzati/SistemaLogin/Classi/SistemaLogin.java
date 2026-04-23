package E4_avanzati.SistemaLogin.Classi;

import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {

    // username -> Utente
    private final Map<String, Utente> utenti = new HashMap<>();

    // Registrazione di un nuovo utente
    public boolean registraUtente(String username, String password) {
        if (username == null || password == null) {
            System.out.println("Errore: username e password non possono essere null.");
            return false;
        }

        username = username.trim();
        if (username.isEmpty()) {
            System.out.println("Errore: username vuoto non consentito.");
            return false;
        }

        if (utenti.containsKey(username)) {
            System.out.println("Errore: lo username '" + username + "' è già registrato.");
            return false;
        }

        Utente u = new Utente(username, password);
        utenti.put(username, u);
        System.out.println("Registrazione completata per l'utente: " + username);
        return true;
    }

    // Login: controllo username + password
    public boolean login(String username, String password) {
        Utente u = utenti.get(username);

        if (u == null) {
            System.out.println("Login fallito: utente inesistente.");
            return false;
        }

        if (u.verificaPassword(password)) {
            System.out.println("Login riuscito! Benvenuto, " + username + "!");
            return true;
        } else {
            System.out.println("Login fallito: password errata.");
            return false;
        }
    }

    // Cambio password (richiede vecchia password corretta)
    public boolean cambiaPassword(String username, String vecchiaPassword, String nuovaPassword) {
        Utente u = utenti.get(username);

        if (u == null) {
            System.out.println("Cambio password fallito: utente inesistente.");
            return false;
        }

        if (!u.verificaPassword(vecchiaPassword)) {
            System.out.println("Cambio password fallito: vecchia password non corretta.");
            return false;
        }

        u.cambiaPassword(nuovaPassword);
        System.out.println("Password cambiata con successo per l'utente: " + username);
        return true;
    }

    // Mostra la lista degli utenti registrati (senza password)
    public void mostraUtentiRegistrati() {
        if (utenti.isEmpty()) {
            System.out.println("Nessun utente registrato.");
            return;
        }

        System.out.println("=== Utenti registrati ===");
        for (Utente u : utenti.values()) {
            System.out.println("- " + u.getUsername());
        }
    }
}