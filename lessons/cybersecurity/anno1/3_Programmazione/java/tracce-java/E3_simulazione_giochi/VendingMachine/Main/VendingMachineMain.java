package E3_simulazione_giochi.VendingMachine.Main;

import E3_simulazione_giochi.VendingMachine.Classi.*;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachineMain {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            VendingMachine macchinetta = new VendingMachine();
            int soldi = 0;

            stampaComandi();
            while (scanner.hasNextLine()) {
                System.out.print("\nInserisci comando: ");
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] parti = linea.split("\\s+");
                String comando = parti[0].toUpperCase(Locale.ROOT);

                try {
                    switch (comando) {
                        case "F" -> {
                            richiediArgomenti(parti, 1, "F");
                            System.out.println("Terminazione programma");
                            return;
                        }
                        case "B" -> {
                            richiediArgomenti(parti, 4, "B <nome> <costo> <volume>");
                            macchinetta.aggiungiProdotto(new Bevanda(
                                    Integer.parseInt(parti[2]), parti[1], Integer.parseInt(parti[3])));
                            System.out.println("Bevanda aggiunta");
                        }
                        case "M" -> {
                            richiediArgomenti(parti, 4, "M <nome> <costo> <calorie>");
                            macchinetta.aggiungiProdotto(new Merendina(
                                    Integer.parseInt(parti[2]), parti[1], Integer.parseInt(parti[3])));
                            System.out.println("Merendina aggiunta");
                        }
                        case "S" -> {
                            richiediArgomenti(parti, 2, "S <soldi>");
                            int importo = Integer.parseInt(parti[1]);
                            if (importo <= 0 || soldi > Integer.MAX_VALUE - importo) {
                                throw new IllegalArgumentException("L'importo deve essere positivo e non causare overflow");
                            }
                            soldi += importo;
                            System.out.println("Soldi attuali: " + soldi);
                        }
                        case "CB" -> {
                            richiediArgomenti(parti, 2, "CB <volume>");
                            Bevanda bevanda = macchinetta.compraBevanda(soldi, Integer.parseInt(parti[1]));
                            if (bevanda != null) {
                                soldi -= bevanda.getCosto();
                                System.out.println("Ho bevuto " + bevanda.getNome());
                            } else {
                                System.out.println("Bevanda non trovata o soldi insufficienti");
                            }
                        }
                        case "CM" -> {
                            richiediArgomenti(parti, 2, "CM <calorie>");
                            Merendina merendina = macchinetta.compraMerendina(soldi, Integer.parseInt(parti[1]));
                            if (merendina != null) {
                                soldi -= merendina.getCosto();
                                System.out.println("Ho mangiato " + merendina.getNome());
                            } else {
                                System.out.println("Merendina non trovata o soldi insufficienti");
                            }
                        }
                        case "SORT" -> {
                            richiediArgomenti(parti, 1, "SORT");
                            macchinetta.stampaProdottiOrdinatiPerCosto();
                        }
                        default -> System.out.println("Comando non valido");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Input non valido: " + e.getMessage());
                }

                System.out.println("Soldi disponibili: " + soldi);
            }
        }
    }

    private static void stampaComandi() {
        System.out.println("F: termina l'esecuzione");
        System.out.println("B: aggiunge una bevanda (nome, costo, volume)");
        System.out.println("M: aggiunge una merendina (nome, costo, calorie)");
        System.out.println("S: aggiunge credito (soldi)");
        System.out.println("CB: compra una bevanda (volume)");
        System.out.println("CM: compra una merendina (calorie)");
        System.out.println("SORT: mostra i prodotti ordinati per costo");
    }

    private static void richiediArgomenti(String[] parti, int numeroAtteso, String uso) {
        if (parti.length != numeroAtteso) {
            throw new IllegalArgumentException("uso: " + uso);
        }
    }
}
