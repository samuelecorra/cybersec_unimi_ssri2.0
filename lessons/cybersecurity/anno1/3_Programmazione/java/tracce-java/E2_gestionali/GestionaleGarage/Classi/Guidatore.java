package E2_gestionali.GestionaleGarage.Classi;

public class Guidatore {

    // ATTRIBUTI
    private String nomeECognome;
    private Auto auto;

    // COSTRUTTORE
    public Guidatore(String nomeECognome, Auto auto) {
        this.nomeECognome = nomeECognome;
        this.auto = auto;
    }

    // GETTERS
    public String getNomeECognome() { return nomeECognome; }
    public Auto getAuto() { return auto; }

    // SETTERS (opzionali ma comodi se vuoi cambiare auto o nome)
    public void setNomeECognome(String nomeECognome) { this.nomeECognome = nomeECognome; }
    public void setAuto(Auto auto) { this.auto = auto; }

    // METODI RICHIESTI
    public int parcheggia(Garage garage) {
        return garage.aggiungiAuto(auto);
    }

    public int rimuoviAuto(Garage garage) {
        return garage.rimuoviAuto(auto);
    }

    // STAMPA INFO GUIDATORE
    @Override
    public String toString() {
        return "Guidatore: " + nomeECognome + " | Auto: " + auto;
    }
}
