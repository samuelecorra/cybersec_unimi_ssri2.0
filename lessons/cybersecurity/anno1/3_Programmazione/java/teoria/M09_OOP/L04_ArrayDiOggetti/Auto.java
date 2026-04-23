package M09_OOP.L04_ArrayDiOggetti;

public class Auto {

    String modello;
    String colore;

    // Costruttore completo:
    Auto(String modello, String colore) {
        this.modello = modello;
        this.colore = colore;
    }

    void guida() {
        System.out.println("Sto guidando una " + modello + " di colore " + colore);
    }
}
