package M09_OOP.L02_Costruttori;

public class Costruttori {

    static void main() {

        Automobile auto1 = new Automobile("Lamborghini", "Aventador",
            2020, "Giallo", 6498, 770, 350);

        Automobile auto2 = new Automobile("Ferrari", "488 Spider");

        // La prima chiamata è quella al costruttore completo, la seconda a quello sovraccaricato.
        // Stampiamo le informazioni delle auto per verificare che siano state create correttamente.
        System.out.println("Auto 1: " + auto1.marca + " " + auto1.modello + ", Anno: " +
            auto1.annoImmatricolazione + ", Colore: " + auto1.colore + ", Cilindrata: " +
            auto1.cilindrata + "cc, Cavalli: " + auto1.cavalli + "CV, Velocità Massima: " +
            auto1.velocitaMassima + "km/h, Guidabile: " + auto1.isGuidabile);
        System.out.println("Auto 2: " + auto2.marca + " " + auto2.modello);

        // Chiamiamo i metodi
        auto1.accendi();
        auto1.guida();
        auto1.spegni();

        auto2.accendi();
        auto2.guida();
        auto2.incidenta();
        auto2.spegni(); // Questa volta l'auto non è guidabile dopo l'incidente
        auto2.accendi(); // Non dovrebbe poter nemmeno accendersi
        auto2.guida(); // Né guidare
        auto2.ripara();
        auto2.accendi(); // Ora dovrebbe poter accendersi di nuovo
        auto2.guida(); // E guidare
        auto2.spegni();

    }
}
