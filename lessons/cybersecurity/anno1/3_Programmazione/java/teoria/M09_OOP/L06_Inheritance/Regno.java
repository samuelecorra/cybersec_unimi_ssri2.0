package M09_OOP.L06_Inheritance;

public class Regno extends EssereVivente {

    // I regni sono le suddivisioni più ampie nella classificazione degli esseri viventi.
    // I principali regni sono: Animalia (animali), Plantae (piante), Fungi (funghi), Protista (protisti), Monera (batteri).

    // Come rendiamo il concetto in JAVA?
    // Aggiungiamo un attributo per dare più specificità alla classe Regno rispetto alla superclasse EssereVivente.
    String nomeRegno;

    // Costruttore della classe Regno
    Regno(String soprannome, String nomeRegno) {
        super(soprannome); // Chiamo il costruttore della superclasse EssereVivente
        this.nomeRegno = nomeRegno; // Inizializzo l'attributo specifico della classe Regno
    }

    void mostraInfoRegno() {
        System.out.println(this.soprannome + " è un essere vivente del regno: " + this.nomeRegno);
    }

}
