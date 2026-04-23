package M09_OOP.L08_MethodOverriding;

public class Harrier extends Aircraft {

    double aperturaAlare;       // in metri
    double capacitaCarburante;  // in litri
    double velocitaMassima;     // in km/h (valore corrente, dipende dal postbruciatore)
    boolean postBruciatoreAttivo;
    boolean modalitaVTOLAttiva;

    public Harrier() {
        super();
        this.aperturaAlare = 9.96;     // apertura alare in metri (F-16 reale)
        this.capacitaCarburante = 7810; // capacità carburante approssimativa in litri
        this.velocitaMassima = 1350;    // velocità max indicativa senza postbruciatore (km/h)
        this.postBruciatoreAttivo = false;
        this.modalitaVTOLAttiva = false; // Vertical Take-Off and Landing di base disattivata
    }

    void decolloLineare() {
        System.out.println("Decollo lineare in corso...");
    }

    void atterraggioLineare() {
        System.out.println("Atterraggio lineare in corso...");
    }

    void decolloVTOL() {
        this.modalitaVTOLAttiva = true;
        System.out.println("Modalità VTOL attivata. Decollo verticale in corso...");
    }

    void atterraggioVTOL() {
        this.modalitaVTOLAttiva = true;
        System.out.println("Modalità VTOL attivata. Atterraggio verticale in corso...");
    }

    void attivaVTOLinVolo() {
        System.out.println("Modalità VTOL attivata in volo. Stabilizzazione in corso...");
    }

    void disattivaVTOLinVolo() {
        System.out.println("Modalità VTOL disattivata in volo. Ritorno al volo normale...");
    }

    void attivaPostBruciatore() {
        System.out.println("Postbruciatore attivato. Aumento spinta e velocità massima!");
        this.postBruciatoreAttivo = true;
        this.velocitaMassima = 2150; // velocità max indicativa con postbruciatore (km/h)
        System.out.println("Velocità massima attuale: " + velocitaMassima + " km/h.");
    }

    void disattivaPostBruciatore() {
        System.out.println("Postbruciatore disattivato. Si torna a regime normale.");
        this.postBruciatoreAttivo = false;
        this.velocitaMassima = 1350;
        System.out.println("Velocità massima attuale: " + velocitaMassima + " km/h.");
    }


    @Override
    void startEngine() {
        System.out.println("Harrier pronto per il decollo! Motore avviato con successo.");
    }

    @Override
    public String getPatenteRichiesta() {
        return "Licenza di pilota militare avanzata (VTOL-capable)";
    }
}
