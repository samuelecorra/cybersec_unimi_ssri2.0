package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * CLASSE AquilaReale
 * ============================================================
 * L'Aquila reale è un predatore aereo tipico dell'ambiente montano.
 * Ruoli:
 *  - Predatore
 *  - AnimaleMontano
 *
 * Implementa lo stesso contratto di Lupo per l'interfaccia Predatore,
 * ma con una STRATEGIA DI CACCIA completamente diversa.
 * Questo è un esempio di POLIMORFISMO: stessa firma di metodo, logica diversa.
 */
public class AquilaReale implements Predatore, AnimaleMontano {

    private String nome;

    public AquilaReale(String nome) {
        this.nome = nome;
    }

    @Override
    public void caccia(Preda p) {
        System.out.println(nome + " volteggia in alto nel cielo e osserva la preda: " + p + ".");
        avvicinatiAllaPredaSilenziosamente();
        System.out.println(nome + " si lancia in picchiata verso la preda!");
        p.fuggi();
    }

    @Override
    public void emettiVerso() {
        System.out.println(nome + " emette un acuto strillo dall'alto.");
    }

    @Override
    public void descriviHabitat() {
        System.out.println(nome + " nidifica sulle pareti rocciose e caccia in ampie zone montane.");
    }

    @Override
    public void avvicinatiAllaPredaSilenziosamente() {
        System.out.println(nome + " plana silenziosa sfruttando le correnti d'aria.");
    }

    @Override
    public String toString() {
        return "AquilaReale{" +
                "nome='" + nome + '\'' +
                '}';
    }
}