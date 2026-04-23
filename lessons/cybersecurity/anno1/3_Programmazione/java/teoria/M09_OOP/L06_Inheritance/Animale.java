package M09_OOP.L06_Inheritance;

public class Animale extends Regno {

    // L'animale non ha bisogno di nuovi attributi rispetto a Regno,
    // quindi non ne dichiariamo nessuno.
    Animale(String soprannome, String nomeRegno) {
        super(soprannome, nomeRegno);
    }

    // Però di sicuro ha bisogno di un metodo specifico per mangiare, rispetto a una pianta o un fungo.
    void mangia() {
        System.out.println("L'animale soprannominato " + this.soprannome + " sta mangiando.");
    }

}
