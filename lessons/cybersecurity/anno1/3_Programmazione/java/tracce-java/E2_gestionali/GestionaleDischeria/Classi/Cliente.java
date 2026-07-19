package E2_gestionali.GestionaleDischeria.Classi;

import java.util.*;

public class Cliente {
    private final int id;
    private final String nome;
    private final List<Disco> dischiPresi;

    public Cliente(int id, String nome) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID del cliente deve essere positivo.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome del cliente non può essere vuoto.");
        }
        this.id = id;
        this.nome = nome.trim();
        this.dischiPresi = new ArrayList<>();
    }

    public void prendiDisco(Disco disco) {
        dischiPresi.add(disco);
    }

    public Disco restituisciDisco(int idDisco) {
        for (int i = 0; i < dischiPresi.size(); i++) {
            if (dischiPresi.get(i).getId() == idDisco) {
                return dischiPresi.remove(i);
            }
        }
        return null;
    }

    public boolean haDisco(int idDisco) {
        return dischiPresi.stream().anyMatch(disco -> disco.getId() == idDisco);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', dischiPresi=" + dischiPresi + "}";
    }
}
