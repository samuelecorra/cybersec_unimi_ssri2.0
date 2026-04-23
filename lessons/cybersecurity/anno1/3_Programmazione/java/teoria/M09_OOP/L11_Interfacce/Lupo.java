package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * CLASSE Lupo
 * ============================================================
 * Il Lupo è un predatore terrestre dell'ecosistema montano.
 * Ruoli:
 *  - Predatore
 *  - AnimaleMontano
 *
 * Notare che il metodo caccia(Preda p) NON si interessa del tipo
 * concreto della preda (Marmotta, Camoscio...). Riceve solo il
 * riferimento all'interfaccia Preda. Questo è il cuore del POLIMORFISMO.
 */
public class Lupo implements Predatore, AnimaleMontano {

    private String nome;

    public Lupo(String nome) {
        this.nome = nome;
    }

    @Override
    public void caccia(Preda p) {
        System.out.println(nome + " individua una preda: " + p + ".");
        avvicinatiAllaPredaSilenziosamente(); // metodo di default dell'interfaccia Predatore
        System.out.println(nome + " scatta all'inseguimento!");
        p.fuggi();
    }

    @Override
    public void emettiVerso() {
        System.out.println(nome + " ulula alla luna.");
    }

    @Override
    public void descriviHabitat() {
        System.out.println(nome + " vive nei boschi montani e si muove in branchi.");
    }

    /*
     * Sovrascriviamo il metodo di default per dare un comportamento più specifico.
     */
    @Override
    public void avvicinatiAllaPredaSilenziosamente() {
        System.out.println(nome + " si muove rasente al terreno, sfruttando il bosco come copertura.");
    }

    @Override
    public String toString() {
        return "Lupo{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
