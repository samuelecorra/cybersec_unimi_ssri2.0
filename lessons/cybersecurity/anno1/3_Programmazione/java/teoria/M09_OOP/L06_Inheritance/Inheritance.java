package M09_OOP.L06_Inheritance;

public class Inheritance {

    // Uno dei pilastri portanti dell'OOP è l'ereditarietà (inheritance).
    // L'ereditarietà permette di creare una nuova classe (classe derivata o sottoclasse o classe figlia)
    // basata su una classe esistente (classe base o superclasse o classe padre/genitore).
    // La classe derivata eredita attributi e metodi dalla classe base,
    // permettendo il riutilizzo del codice e la creazione di gerarchie di classi.

    // PER CAPIRCI AL VOLO: è possibile programmare in Java l'intera classificazione di Carlo Linneo
    // (Regno, Phylum, Classe, Ordine, Famiglia, Genere, Specie) usando l'ereditarietà.
    // Ad esempio, possiamo avere una classe "Animale" come superclasse,
    // e poi classi derivate come "Mammifero", "Uccello", "Pesce", ecc.
    // Ogni classe derivata può avere attributi e metodi specifici, oltre a quelli ereditati dalla classe base.

    static void main() {

        Gatto g1 = new Gatto("Garfield");
        g1.mostraInfoRegno(); // Metodo ereditato da Regno
        g1.mangia();          // Metodo ereditato da Animale
        g1.miagola();        // Metodo specifico di Gatto

        g1.mostraVite();
        g1.rischiaLaVita();
        g1.mostraVite();
        g1.rigeneraVita();
        g1.mostraVite();

        // Possiamo anche creare una pianta
        Pianta p1 = new Pianta("Ficus", "Plantae");
        p1.fotosintetizza();   // Metodo specifico di Pianta

    }
}
