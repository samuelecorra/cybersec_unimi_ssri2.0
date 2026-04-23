package E4_avanzati.ClassificaVideogame.Classi;

import java.util.Objects;

public final class Giocatore {

    private final int id;
    private final String nickname;
    private int punteggio;

    public Giocatore(int id, String nickname, int punteggioIniziale) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'id deve essere positivo");
        }
        this.id = id;
        this.nickname = Objects.requireNonNull(nickname, "nickname").trim();
        if (this.nickname.isEmpty()) {
            throw new IllegalArgumentException("Il nickname non può essere vuoto");
        }
        this.punteggio = punteggioIniziale;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public String toString() {
        return "Giocatore{id=" + id +
                ", nickname='" + nickname + '\'' +
                ", punteggio=" + punteggio +
                '}';
    }
}
