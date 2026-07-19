package E4_avanzati.SistemaLogin.Classi;

import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {

    // username -> Utente
    private final Map<String, Utente> utenti = new HashMap<>();

    // Registrazione di un nuovo utente
    public boolean registraUtente(String username, String password) {
        String usernameNormalizzato = normalizzaUsername(username);
        if (usernameNormalizzato == null || password == null || password.isEmpty()) {
            System.out.println("Errore: username e password devono essere valorizzati.");
            return false;
        }

        if (utenti.containsKey(usernameNormalizzato)) {
            System.out.println("Errore: lo username '" + usernameNormalizzato + "' è già registrato.");
            return false;
        }

        Utente u = new Utente(usernameNormalizzato, password);
        utenti.put(usernameNormalizzato, u);
        System.out.println("Registrazione completata per l'utente: " + usernameNormalizzato);
        return true;
    }

    // Login: controllo username + password
    public boolean login(String username, String password) {
        String usernameNormalizzato = normalizzaUsername(username);
        if (usernameNormalizzato == null || password == null || password.isEmpty()) {
            System.out.println("Login fallito: credenziali non valide.");
            return false;
        }

        Utente u = utenti.get(usernameNormalizzato);

        if (u == null) {
            System.out.println("Login fallito: utente inesistente.");
            return false;
        }

        if (u.verificaPassword(password)) {
            System.out.println("Login riuscito! Benvenuto, " + usernameNormalizzato + "!");
            return true;
        } else {
            System.out.println("Login fallito: password errata.");
            return false;
        }
    }

    // Cambio password (richiede vecchia password corretta)
    public boolean cambiaPassword(String username, String vecchiaPassword, String nuovaPassword) {
        String usernameNormalizzato = normalizzaUsername(username);
        if (usernameNormalizzato == null || vecchiaPassword == null || vecchiaPassword.isEmpty()
                || nuovaPassword == null || nuovaPassword.isEmpty()) {
            System.out.println("Cambio password fallito: dati non validi.");
            return false;
        }

        Utente u = utenti.get(usernameNormalizzato);

        if (u == null) {
            System.out.println("Cambio password fallito: utente inesistente.");
            return false;
        }

        if (!u.verificaPassword(vecchiaPassword)) {
            System.out.println("Cambio password fallito: vecchia password non corretta.");
            return false;
        }

        u.cambiaPassword(nuovaPassword);
        System.out.println("Password cambiata con successo per l'utente: " + usernameNormalizzato);
        return true;
    }

    // Mostra la lista degli utenti registrati (senza password)
    public void mostraUtentiRegistrati() {
        if (utenti.isEmpty()) {
            System.out.println("Nessun utente registrato.");
            return;
        }

        System.out.println("=== Utenti registrati ===");
        utenti.values().stream()
                .map(Utente::getUsername)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .forEach(username -> System.out.println("- " + username));
    }

    private static String normalizzaUsername(String username) {
        if (username == null) {
            return null;
        }
        String normalizzato = username.trim();
        return normalizzato.isEmpty() ? null : normalizzato;
    }
}
