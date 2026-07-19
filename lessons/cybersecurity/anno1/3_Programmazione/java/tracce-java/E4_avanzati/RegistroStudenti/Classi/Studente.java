package E4_avanzati.RegistroStudenti.Classi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Studente {

    private final int matricola;
    private final String nome;
    private final List<Integer> voti;

    public Studente(int matricola, String nome) {
        if (matricola <= 0) {
            throw new IllegalArgumentException("La matricola deve essere positiva");
        }
        this.matricola = matricola;
        this.nome = Objects.requireNonNull(nome, "nome").trim();
        if (this.nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        this.voti = new ArrayList<>();
    }

    public int getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public List<Integer> getVoti() {
        return List.copyOf(voti);
    }

    // Aggiunge un voto alla lista
    public void aggiungiVoto(int voto) {
        if (voto < 0 || voto > 30) {
            throw new IllegalArgumentException("Il voto deve essere compreso tra 0 e 30");
        }
        voti.add(voto);
    }

    // Restituisce la media dei voti; se non ci sono voti ritorna 0.0
    public double calcolaMedia() {
        if (voti.isEmpty()) {
            return 0.0;
        }
        long somma = 0;
        for (int v : voti) {
            somma += v;
        }
        return (double) somma / voti.size();
    }

    @Override
    public String toString() {
        return "Studente{" +
                "matricola=" + matricola +
                ", nome='" + nome + '\'' +
                ", voti=" + voti +
                ", media=" + String.format("%.2f", calcolaMedia()) +
                '}';
    }
}
