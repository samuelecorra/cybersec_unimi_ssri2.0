package E2_gestionali.GestionaleGarage.Classi;

public class Auto {
    
    // ATTRIBUTI:
    private String marca;
    private String modello;
    private final String targa; // univoca: non deve cambiare dopo la registrazione

    // COSTRUTTORE
    public Auto(String marca, String modello, String targa) {
        this.marca = richiediTesto(marca, "marca");
        this.modello = richiediTesto(modello, "modello");
        this.targa = richiediTesto(targa, "targa");
    }

    // GETTERS
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public String getTarga() { return targa; }

    // SETTERS
    public void setMarca(String marca) {
        this.marca = richiediTesto(marca, "marca");
    }
    public void setModello(String modello) {
        this.modello = richiediTesto(modello, "modello");
    }

    // STAMPA INFO AUTO
    @Override
    public String toString() {
        return "Marca: " + marca + " - Modello: " + modello + " - Targa: " + targa;
    }

    private static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.isBlank()) {
            throw new IllegalArgumentException("Il campo " + campo + " non può essere vuoto.");
        }
        return valore.trim();
    }
}
