package M09_OOP.L06_Inheritance;

public class Pianta extends EssereVivente  {

    // Le piante non hanno bisogno di nuovi attributi rispetto a Regno,
    // quindi non ne dichiariamo nessuno.
    Pianta(String soprannome, String nomeRegno) {
        super(soprannome); // Chiamo il costruttore della superclasse EssereVivente
    }

    // Però di sicuro ha bisogno di un metodo specifico per fotosintetizzare, rispetto a un animale o un fungo.
    void fotosintetizza() {
        System.out.println("La pianta soprannominata " + this.soprannome + " si prepara a lanciare solarraggio come un Pokémon Erba.");
    }
}
