package E3_simulazione_giochi.VendingMachine.Classi;

import java.util.*;

public class VendingMachine {
    private final List<Prodotto> prodotti;

    public VendingMachine() {
        prodotti = new ArrayList<>();
    }

    public void aggiungiProdotto(Prodotto p) {
        prodotti.add(p);
    }

    public Merendina compraMerendina(int soldi, int calorie) {
        for (Prodotto p : prodotti) {
            if (p instanceof Merendina) {
                Merendina m = (Merendina) p;
                if (m.getCalorie() == calorie && soldi >= m.getCosto()) {
                    prodotti.remove(m);
                    return m;
                }
            }
        }
        return null;
    }

    public Bevanda compraBevanda(int soldi, int volume) {
        for (Prodotto p : prodotti) {
            if (p instanceof Bevanda) {
                Bevanda b = (Bevanda) p;
                if (b.getVolume() == volume && soldi >= b.getCosto()) {
                    prodotti.remove(b);
                    return b;
                }
            }
        }
        return null;
    }

    public void stampaProdottiOrdinatiPerCosto() {
        System.out.println("Prodotti disponibili (ordinati per costo):");
        List<Prodotto> ordinati = new ArrayList<>(prodotti);
        Collections.sort(ordinati, new Comparator<Prodotto>() {
            @Override
            public int compare(Prodotto p1, Prodotto p2) {
                return Integer.compare(p1.getCosto(), p2.getCosto());
            }
        });
        for (Prodotto p : ordinati) {
            System.out.println(p.getNome() + " - " + p.getCosto());
        }
    }
}
