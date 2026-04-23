package E3_simulazione_giochi.Serpenti_e_Scale.Classi;

import java.util.Random;

public class Dado {
    private final int facce;
    private final Random random;

    public Dado(int facce) {
        if (facce <= 0) {
            throw new IllegalArgumentException("Un dado deve avere almeno 1 faccia");
        }
        this.facce = facce;
        this.random = new Random();
    }

    public int lancia() {
        // Valori da 1 a facce
        return random.nextInt(facce) + 1;
    }

    public int getFacce() {
        return facce;
    }
}

