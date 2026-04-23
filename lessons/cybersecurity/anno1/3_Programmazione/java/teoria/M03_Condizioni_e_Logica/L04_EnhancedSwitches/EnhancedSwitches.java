package M03_Condizioni_e_Logica.L04_EnhancedSwitches;

import java.util.Scanner;

public class EnhancedSwitches {
    public static void main(String[] args) {

        // In Java gli switch tradizionali utilizzano la parola chiave "case"
        // seguita da due punti ":" e richiedono l'uso di "break", come in C/C++:

        // ESEMPIO:
        String giorno = "Sabato";
        String tipoDiGiorno;

        switch (giorno) {
            case "Lunedì":
            case "Martedì":
            case "Mercoledì":
            case "Giovedì":
            case "Venerdì":
                tipoDiGiorno = "Giorno lavorativo";
                break;
            case "Sabato":
            case "Domenica":
                tipoDiGiorno = "Fine settimana";
                break;
            default:
                tipoDiGiorno = "Giorno non valido";
                break;
        }

        System.out.println(giorno + " è un: " + tipoDiGiorno);

        // Però a partire da Java 14, è stato introdotto l'Enhanced Switch,
        // che utilizza la freccia "->" e non richiede l'uso di "break":
        // La freccia è un operatore lambda, che vedremo più avanti.

        // Esempio di Enhanced Switch in Java

        Scanner sc = new Scanner(System.in);

        String giorno2 = sc.nextLine();
        String tipoDiGiorno2;

        // Utilizzo dell'enhanced switch per determinare il tipo di giorno
        tipoDiGiorno = switch (giorno) {
            case "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì" -> "Giorno lavorativo";
            case "Sabato", "Domenica" -> "Fine settimana";
            default -> "Giorno non valido";
        };

        System.out.println(giorno + " è un: " + tipoDiGiorno);

    }
}
