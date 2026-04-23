package M09_OOP.L06_Inheritance;

public class Gatto extends Animale {

    // Non è static perché ogni gatto ha le sue vite!!! Non basta
    // il fatto che inizialmente tutti i gatti abbiano 9 vite!!!
    int vite = 9;

    // Non dobbiamo passare il nome del regno, perché sappiamo già che è "Animalia" di fatto.
    Gatto(String soprannome) {
        super(soprannome, "Animalia"); // Chiamo il costruttore della superclasse Animale
    }

    // E ora possiamo sbizzarrirci...

    void miagola() {
        System.out.println("Il gatto " + this.soprannome + " fa: Miao!");
    }

    void rischiaLaVita() {
        if (this.vite > 0) {
            this.vite--;
            System.out.println("Il gatto " + this.soprannome + " ha rischiato la vita! Vite rimaste: " + this.vite);
        } else {
            System.out.println("Il gatto " + this.soprannome + " non ha più vite!");
        }
    }

    void rigeneraVita() {
        if (this.vite < 9) {
            this.vite++;
            System.out.println("Il gatto " + this.soprannome + " ha rigenerato una vita! Vite attuali: " + this.vite);
        } else {
            System.out.println("Il gatto " + this.soprannome + " ha già il massimo delle vite!");
        }
    }

    void mostraVite() {
        System.out.println("Il gatto " + this.soprannome + " ha " + this.vite + " vite.");
    }
}
