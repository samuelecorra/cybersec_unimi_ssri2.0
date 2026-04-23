package M03_Condizioni_e_Logica.L02_NestedIfs;

public class NestedIfs {
    static void main() {
        int numero = 120;

        // Uno studente di quinta superiore ha lo sconto al cinema
        // Uno studente non in quinta superiore non ha lo sconto
        // Una persona non studente non può acquistare assolutamente il biglietto.
        boolean èStudente = true;
        boolean èInQuintaSuperiore = false;
        double prezzoBigliettoCinema = 10.0;
        double sconto = 0.80; // 20% di sconto

        if (èStudente) {
            if (èInQuintaSuperiore) {
                double prezzoScontato = prezzoBigliettoCinema * sconto;
                System.out.println("Hai diritto allo sconto! Il prezzo del biglietto scontato è: " + prezzoScontato + " euro.");
            } else {
                System.out.println("Non hai diritto allo sconto. Il prezzo del biglietto è: " + prezzoBigliettoCinema + " euro.");
            }
        } else {
            System.out.println("Non hai diritto a frequentare il cinema del liceo.");
        }

        // Altri esempi più complessi con molti livelli di if annidati
        // possono essere difficili da leggere e mantenere:

        // ESEMPIO:
        int età = 25;
        boolean haPatente = true;
        boolean haAssicurazione = false;

        if (età >= 18) {
            if (haPatente) {
                if (haAssicurazione) {
                    System.out.println("Puoi guidare l'auto.");
                } else {
                    System.out.println("Non puoi guidare l'auto senza assicurazione.");
                }
            } else {
                System.out.println("Non puoi guidare l'auto senza patente.");
            }
        } else {
            System.out.println("Non puoi né guidare l'auto né conseguire la patente se sei minorenne.");
        }

        // Quando un blocco if/else ha solo un'istruzione, le
        // parentesi graffe possono essere omesse per semplicità. Rifacciamo lo scorso esempio senza graffe:
        if (età >= 18)
            if (haPatente)
                if (haAssicurazione)
                    System.out.println("Puoi guidare l'auto.");
                else
                    System.out.println("Non puoi guidare l'auto senza assicurazione.");
            else
                System.out.println("Non puoi guidare l'auto senza patente.");
        else
            System.out.println("Non puoi né guidare l'auto né conseguire la patente se sei minorenne.");

        // Pura indentazione!

    }

}
