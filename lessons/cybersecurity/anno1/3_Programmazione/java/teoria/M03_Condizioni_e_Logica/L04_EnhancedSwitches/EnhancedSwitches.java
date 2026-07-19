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

        // Le switch expression sono definitive da Java 14. Le regole con "->"
        // non fanno fall-through e quindi non richiedono break. La stessa forma
        // grafica compare nelle lambda, ma qui appartiene alla sintassi di switch.

        // Esempio di Enhanced Switch in Java

        Scanner sc = new Scanner(System.in);

        System.out.print("Inserisci un giorno della settimana: ");
        String giorno2 = sc.nextLine();

        // Utilizzo dell'enhanced switch per determinare il tipo di giorno
        String tipoDiGiorno2 = switch (giorno2) {
            case "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì" -> "Giorno lavorativo";
            case "Sabato", "Domenica" -> "Fine settimana";
            default -> "Giorno non valido";
        };

        System.out.println(giorno2 + " è un: " + tipoDiGiorno2);
        sc.close();

    }
}
