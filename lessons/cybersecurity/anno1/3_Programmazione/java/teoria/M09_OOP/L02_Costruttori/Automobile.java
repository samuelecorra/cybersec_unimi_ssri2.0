package M09_OOP.L02_Costruttori;

public class Automobile {

    String marca;
    String modello;
    int annoImmatricolazione;
    String colore;
    double cilindrata;
    double cavalli;
    double velocitaMassima;
    boolean isGuidabile;

    // Scriviamo un costruttore per la classe Automobile
    public Automobile(String marca, String modello, int annoImmatricolazione, String colore,
                      double cilindrata, double cavalli, double velocitaMassima) {
        this.marca = marca;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
        this.colore = colore;
        this.cilindrata = cilindrata;
        this.cavalli = cavalli;
        this.velocitaMassima = velocitaMassima; // Nella scorsa lezione abbiamo anticipato il concetto di "this"
        this.isGuidabile = true; // Impostiamo isGuidabile a true di default per tutte le auto create, quindi non serve passarlo come parametro
    }

    // la keyword this si riferisce all'istanza corrente della classe e quindi permette di istanziare
    // diversi oggetti con attributi diversi ad ogni chiamata del costruttore.

    // Non per forza però dobbiamo usare tutti i parametri nel costruttore, possiamo anche crearne di più semplici.
    public Automobile(String marca, String modello) {
        this.marca = marca;
        this.modello = modello;
        this.isGuidabile = true;
    }

    // Questo ad esempio è un costruttore "sovraccaricato" (overloaded constructor), ovvero
    // un secondo costruttore con una firma diversa (diverso numero di parametri).
    // Questo ci permette di istanziare oggetti Automobile in modi diversi a seconda delle
    // informazioni che abbiamo a disposizione al momento della creazione dell'oggetto.

    // Creiamo qualche metodo semplice:
    void accendi() {
        if(isGuidabile) {
            System.out.println("L'automobile " + marca + " " + modello + " è stata accesa.");
        } else {
            System.out.println("L'automobile " + marca + " " + modello + " non può essere accesa perché non è guidabile.");
        }
    }

    void spegni() {
        if(isGuidabile) {
            System.out.println("L'automobile " + marca + " " + modello + " è stata spenta.");
        } else {
            System.out.println("L'automobile " + marca + " " + modello + " non può essere spenta perché non è guidabile.");
        }
    }

    void guida() {
        if (isGuidabile) {
            System.out.println("Stai guidando l'automobile " + marca + " " + modello + ".");
        } else {
            System.out.println("Non puoi guidare questa automobile.");
        }
    }

    void incidenta() {
        isGuidabile = false;
        System.out.println("L'automobile " + marca + " " + modello + " ha subito un incidente ed ora non è più guidabile.");
    }

    void ripara() {
        isGuidabile = true;
        System.out.println("L'automobile " + marca + " " + modello + " è stata riparata ed è di nuovo guidabile.");
    }
    // Torniamo nel main e testiamo..
}
