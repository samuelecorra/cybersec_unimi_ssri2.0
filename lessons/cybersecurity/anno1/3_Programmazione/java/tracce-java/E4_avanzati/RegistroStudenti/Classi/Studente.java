package E4_avanzati.RegistroStudenti.Classi;

import java.util.ArrayList;
import java.util.List;

public class Studente {

    private final int matricola;
    private final String nome;
    private final List<Integer> voti;

    public Studente(int matricola, String nome) {
        this.matricola = matricola;
        this.nome = nome;
        this.voti = new ArrayList<>();
    }

    public int getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public List<Integer> getVoti() {
        return voti;
    }

    // Aggiunge un voto alla lista
    public void aggiungiVoto(int voto) {
        voti.add(voto);
    }

    // Restituisce la media dei voti; se non ci sono voti ritorna 0.0
    public double calcolaMedia() {
        if (voti.isEmpty()) {
            return 0.0;
        }
        int somma = 0;
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

