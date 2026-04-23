package E3_simulazione_giochi.VendingMachine.Main;

import E3_simulazione_giochi.VendingMachine.Classi.*;
import java.util.*;

public class VendingMachineMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine macchinetta = new VendingMachine();
        int soldi = 0;

        System.out.println("F: termina l'esecuzione");
        System.out.println("B: aggiunge una bevanda alla macchinetta (nome, costo, volume)");
        System.out.println("M: aggiunge una merendina alla macchinetta (nome, costo, calorie)");
        System.out.println("S: aggiunge soldi alla macchinetta (soldi)");
        System.out.println("CB: compra una bevanda (volume)");
        System.out.println("CM: compra una merendina (calorie)");
        System.out.println("SORT: mostra i prodotti ordinati per costo");

        while (true) {
            System.out.print("\nInserisci comando: ");
            String comando = scanner.next();

            switch (comando) {
                case "F":
                    System.out.println("Terminazione programma");
                    System.exit(0);
                case "B": {
                    String nome = scanner.next();
                    int costo = scanner.nextInt();
                    int volume = scanner.nextInt();
                    macchinetta.aggiungiProdotto(new Bevanda(costo, nome, volume));
                    System.out.println("Bevanda aggiunta");
                    break;
                }
                case "M": {
                    String nome = scanner.next();
                    int costo = scanner.nextInt();
                    int calorie = scanner.nextInt();
                    macchinetta.aggiungiProdotto(new Merendina(costo, nome, calorie));
                    System.out.println("Merendina aggiunta");
                    break;
                }
                case "S": {
                    int s = scanner.nextInt();
                    if (s < 0) {
                        throw new RuntimeException("Soldi negativi");
                    } else {
                        soldi += s;
                        System.out.println("Soldi attuali: " + soldi);
                    }
                    break;
                }
                case "CB": {
                    int volume = scanner.nextInt();
                    Bevanda b = macchinetta.compraBevanda(soldi, volume);
                    if (b != null) {
                        soldi -= b.getCosto();
                        System.out.println("Ho bevuto " + b.getNome());
                    } else {
                        System.out.println("Bevanda non trovata o soldi insufficienti");
                    }
                    break;
                }
                case "CM": {
                    int calorie = scanner.nextInt();
                    Merendina m = macchinetta.compraMerendina(soldi, calorie);
                    if (m != null) {
                        soldi -= m.getCosto();
                        System.out.println("Ho mangiato " + m.getNome());
                    } else {
                        System.out.println("Merendina non trovata o soldi insufficienti");
                    }
                    break;
                }
                case "SORT": {
                    macchinetta.stampaProdottiOrdinatiPerCosto();
                    break;
                }
                default:
                    System.out.println("Comando non valido");
            }

            System.out.println("Soldi disponibili: " + soldi);
        }
    }
}
