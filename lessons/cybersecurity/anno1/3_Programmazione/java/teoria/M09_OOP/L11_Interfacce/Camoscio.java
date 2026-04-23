package M09_OOP.L11_Interfacce;

/*
 * ============================================================
 * CLASSE Camoscio
 * ============================================================
 * Il Camoscio è un tipico animale montano che, nel nostro modello:
 *  - è una Preda (viene cacciato dal lupo, dall'aquila, ecc.)
 *  - è un AnimaleMontano (con il suo verso e il suo habitat)
 *
 * Notare che Camoscio implementa 2 interfacce:
 *  - Preda
 *  - AnimaleMontano
 *
 * Questo mostra chiaramente il vantaggio delle interfacce:
 * una singola classe può avere più RUOLI contemporaneamente.
 */
public class Camoscio implements Preda, AnimaleMontano {

    private String nome;

    public Camoscio(String nome) {
        this.nome = nome;
    }

    @Override
    public void fuggi() {
        System.out.println(nome + " scatta in corsa lungo i pendii rocciosi per sfuggire al predatore!");
    }

    @Override
    public void emettiVerso() {
        System.out.println(nome + " emette un breve fischio di allarme.");
    }

    @Override
    public void descriviHabitat() {
        System.out.println(nome + " vive sulle pareti rocciose e nei prati in quota.");
    }

    /*
     * Sovrascrivo il metodo di default dell'interfaccia Preda per dare
     * una strategia di sopravvivenza più specifica.
     */
    @Override
    public void strategiaDiSopravvivenza() {
        System.out.println(nome + " rimane spesso in gruppo e sfrutta l'altezza per vedere i predatori da lontano.");
    }

    @Override
    public String toString() {
        return "Camoscio{" +
                "nome='" + nome + '\'' +
                '}';
    }
}

