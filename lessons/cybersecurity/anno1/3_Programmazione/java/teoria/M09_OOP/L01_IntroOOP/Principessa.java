package M09_OOP.L01_IntroOOP;

// Creiamo una classe e andiamo a definire alcuni attributi basilari
public class Principessa {

    // Tali attributi vanno anche inizializzati
    String nome = "Principessa Gommarosa";
    int eta = 20;
    String regnoDiResidenza = "Javalandia";
    double livelloPotereMagico = 190.4;
    boolean eFidanzata = false;
    String nomePartner = "";

    void castaIncantesimo() {
        System.out.println(nome + " lancia un incantesimo potentissimo!");
    }

    void banchetta() {
        System.out.println(nome + " sta banchettando con deliziosi dolcetti e tè!");
    }

    void siFidanza(String nomePartner) {
        if (!eFidanzata) {
            eFidanzata = true;
            this.nomePartner = nomePartner;
            System.out.println(nome + " si è appena fidanzata con " + nomePartner + "!");
        } else {
            System.out.println(nome + " non può fidanzarsi con " + nomePartner + ", è già fidanzata con " + this.nomePartner + "!");
        }
    }
}
