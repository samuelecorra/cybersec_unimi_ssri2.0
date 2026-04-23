package M11_JavaAvanzato.L06_Enums;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Enumerazioni {

    // Le enumerazioni (enums) sono un tipo di dato speciale in Java che consente di definire un insieme di costanti predefinite.
    // Deve essere un fixed set, quindi in italiano: un insieme fisso di valori.
    // Sono utili quando si desidera rappresentare un insieme limitato di valori possibili per una variabile.

    // MIGLIORANO LA LEGGIBILITA' E SONO FACILI DA MANTENERE.
    // ANCOR PIU EFFICIENTI INTEGRANDOCI SWITCHES PIUTTOSTO CHE CON LA CLASSICA COMPARAZIONE DI STRINGHE!

    // ======================================================================================================

    static void main() {

        // Creiamo un oggetto di tipo giorno:
        Giorno oggi = Giorno.VENERDI;
        System.out.println("Oggi è: " + oggi); // Stampa "Oggi è: VENERDI"

        // Oppure...
        switch(oggi){
            case LUNEDI,
                 MARTEDI,
                 MERCOLEDI,
                 GIOVEDI,
                 VENERDI -> System.out.println("È un giorno feriale.");
            case SABATO,
                 DOMENICA -> System.out.println("È un giorno festivo.");
        }

        // =======================================================================================

        // Facciamolo con user input:
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci un giorno della settimana (LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI, SABATO, DOMENICA): ");
        String input = scanner.nextLine().toUpperCase();
        try {
            Giorno giornoInserito = Giorno.valueOf(input);
            System.out.println("Hai inserito: " + giornoInserito);

            switch(giornoInserito){
                case LUNEDI,
                     MARTEDI,
                     MERCOLEDI,
                     GIOVEDI,
                     VENERDI -> System.out.println("È un giorno feriale.");
                case SABATO,
                     DOMENICA -> System.out.println("È un giorno festivo.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Input non valido. Per favore inserisci un giorno corretto.");
        }

    }

}
