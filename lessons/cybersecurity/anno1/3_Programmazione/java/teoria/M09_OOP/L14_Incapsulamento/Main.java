package M09_OOP.L14_Incapsulamento;

/*
 * ============================================================
 * MAIN DI TEST
 * ============================================================
 * Dimostra:
 * - come si crea un oggetto Automobile
 * - come leggere campi tramite getters
 * - come modificare campi tramite setters e metodi controllati
 */
public class Main {

    public static void main(String[] args) {

        // Creiamo un'auto
        Automobile auto = new Automobile("Tesla", "Model 3", 2022);

        System.out.println("=== STATO INIZIALE ===");
        System.out.println(auto);

        // Leggiamo dati tramite getters
        System.out.println("\nMarca: " + auto.getMarca());
        System.out.println("Modello: " + auto.getModello());
        System.out.println("Anno: " + auto.getAnno());

        // Modifichiamo dati tramite metodi controllati
        System.out.println("\n=== TEST VELOCITÀ ===");
        auto.accelera(50);
        auto.accelera(100);
        auto.frena(30);
        auto.accelera(500); // scatta limite sicurezza

        // Test setter anno con valore illegale
        System.out.println("\n=== SET ANNO NON VALIDO ===");
        auto.setAnno(1200); // l’auto non esisteva nel 1200
        System.out.println("Anno corretto: " + auto.getAnno());
    }
}
