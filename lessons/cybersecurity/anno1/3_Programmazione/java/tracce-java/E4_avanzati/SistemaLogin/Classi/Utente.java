package E4_avanzati.SistemaLogin.Classi;

import java.util.Objects;

public class Utente {

    private final String username;
    private int passwordHash;

    public Utente(String username, String password) {
        this.username = Objects.requireNonNull(username, "username").trim();
        if (this.username.isEmpty()) {
            throw new IllegalArgumentException("Lo username non può essere vuoto");
        }
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    private int calcolaHash(String password) {
        // "Finto" hash per l'esercizio: usiamo hashCode() della String.
        // NON usarlo mai così in un sistema reale.
        return Objects.requireNonNull(password, "password").hashCode();
    }

    private void setPassword(String password) {
        this.passwordHash = calcolaHash(password);
    }

    public boolean verificaPassword(String password) {
        return this.passwordHash == calcolaHash(password);
    }

    public void cambiaPassword(String nuovaPassword) {
        setPassword(nuovaPassword);
    }

    @Override
    public String toString() {
        // Non mostriamo mai la password o l'hash
        return "Utente{username='" + username + "'}";
    }
}

