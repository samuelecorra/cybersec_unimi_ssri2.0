package M09_OOP.L04_ArrayDiOggetti;

public class ArrayDiOggetti {

    static void main() {

        // Creiamo qualche auto e poi riuniamole in un parco auto (ovvero un array di oggetti Auto)
        Auto auto1 = new Auto("Fiat Panda", "Bianco");
        Auto auto2 = new Auto("Ford Fiesta", "Blu");
        Auto auto3 = new Auto("Volkswagen Golf", "Nero");

        Auto[] parcoAuto = {auto1, auto2, auto3};

        // Stampiamo a schermo le informazioni di ogni auto nel parco auto
        for (int i = 0; i < parcoAuto.length; i++) {
            System.out.println("Auto " + (i + 1) + ": " + parcoAuto[i].modello + " di colore " + parcoAuto[i].colore);
        }

        // Con un enhanced for loop possiamo chiamare un metodo per ogni auto nel parco auto
        for (Auto auto : parcoAuto) {
            auto.guida();
        }

        // ====================================================================================

        // Facciamo un altro array ma stavolta lo inizializziamo internamente:
        Auto[] parcoAuto2 = {
            new Auto("Toyota Corolla", "Rosso"),
            new Auto("Honda Civic", "Grigio"),
            new Auto("BMW Serie 3", "Bianco")
        };

        // Nel parcoAuto2 c'è un writer con tantissima vernice nera:
        for(Auto auto : parcoAuto2) {
            auto.colore = "Nero";
        }

        // Tutti decidono di driftare nel parcoAuto2:
        for(Auto auto : parcoAuto2) {
            auto.guida();
        }

    }
}
