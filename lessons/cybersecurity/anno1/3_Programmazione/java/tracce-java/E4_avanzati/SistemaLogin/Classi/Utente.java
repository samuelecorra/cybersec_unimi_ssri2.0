package E4_avanzati.SistemaLogin.Classi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Utente {

    private static final int DIMENSIONE_SALT_BYTE = 16;
    private static final int ITERAZIONI_PBKDF2 = 120_000;
    private static final int DIMENSIONE_HASH_BIT = 256;
    private static final SecureRandom GENERATORE_CASUALE = new SecureRandom();

    private final String username;
    private byte[] passwordSalt;
    private byte[] passwordHash;

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

    private void setPassword(String password) {
        char[] caratteri = validaPassword(password).toCharArray();
        byte[] nuovoSalt = new byte[DIMENSIONE_SALT_BYTE];
        GENERATORE_CASUALE.nextBytes(nuovoSalt);

        try {
            byte[] nuovoHash = calcolaHash(caratteri, nuovoSalt);
            if (passwordHash != null) {
                Arrays.fill(passwordHash, (byte) 0);
            }
            if (passwordSalt != null) {
                Arrays.fill(passwordSalt, (byte) 0);
            }
            passwordSalt = nuovoSalt;
            passwordHash = nuovoHash;
        } finally {
            Arrays.fill(caratteri, '\0');
        }
    }

    public boolean verificaPassword(String password) {
        char[] caratteri = validaPassword(password).toCharArray();
        try {
            byte[] hashDaVerificare = calcolaHash(caratteri, passwordSalt);
            try {
                return MessageDigest.isEqual(passwordHash, hashDaVerificare);
            } finally {
                Arrays.fill(hashDaVerificare, (byte) 0);
            }
        } finally {
            Arrays.fill(caratteri, '\0');
        }
    }

    public void cambiaPassword(String nuovaPassword) {
        setPassword(nuovaPassword);
    }

    private static String validaPassword(String password) {
        Objects.requireNonNull(password, "password");
        if (password.isEmpty()) {
            throw new IllegalArgumentException("La password non può essere vuota");
        }
        return password;
    }

    private static byte[] calcolaHash(char[] password, byte[] salt) {
        PBEKeySpec specifica = new PBEKeySpec(password, salt, ITERAZIONI_PBKDF2, DIMENSIONE_HASH_BIT);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(specifica)
                    .getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("PBKDF2WithHmacSHA256 non disponibile", e);
        } finally {
            specifica.clearPassword();
        }
    }

    @Override
    public String toString() {
        // Non mostriamo mai la password o l'hash
        return "Utente{username='" + username + "'}";
    }
}
