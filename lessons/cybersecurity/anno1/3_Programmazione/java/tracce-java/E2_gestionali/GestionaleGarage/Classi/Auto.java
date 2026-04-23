package E2_gestionali.GestionaleGarage.Classi;

public class Auto {
    
    // ATTRIBUTI:
    private String marca;
    private String modello;
    private String targa; // univoca

    // COSTRUTTORE
    public Auto(String marca, String modello, String targa) {
        this.marca = marca;
        this.modello = modello;
        this.targa = targa;
    }

    // GETTERS
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public String getTarga() { return targa; }

    // SETTERS
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModello(String modello) {
        this.modello = modello;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }

    // STAMPA INFO AUTO
    @Override
    public String toString() {
        return "Marca: " + marca + " - Modello: " + modello + " - Targa: " + targa;
    }

}