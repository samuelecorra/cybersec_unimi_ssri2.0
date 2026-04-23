package M09_OOP.L09_toStringOverride;

public class toStringOverride {

    // Per quanto concerne il debug, il metodo per antonomasia è il toString().

    // Ogni classe in Java eredita il metodo toString() dalla classe Object.
    // La sua implementazione di default restituisce una stringa composta dal nome della classe
    // seguito dal simbolo "@" e dall'hashcode dell'oggetto in esadecimale.
    // Tuttavia, questa rappresentazione di default non è sempre utile o informativa.
    // Pertanto, è una buona pratica sovrascrivere il metodo toString() nelle proprie classi
    // per fornire una rappresentazione più significativa degli oggetti.

    // E' una vera e propria "customizzazione" del metodo toString() per adattarlo alle esigenze specifiche della classe.

    static void main(String[] args) {
        Studente studente1 = new Studente("Mario", "Rossi", 12345, 2,
                                        27.5, true, false);

        // Primo metodo: stampare ogni attributo singolarmente
        System.out.println("Nome: " + studente1.nome);
        System.out.println("Cognome: " + studente1.cognome);
        System.out.println("Matricola: " + studente1.matricola);
        System.out.println("Anno Corso: " + studente1.annoCorso);
        System.out.println("Media Voti: " + studente1.mediaVoti);
        System.out.println("Ha Pagato Tasse: " + studente1.haPagatoTasse);
        System.out.println("Abbonato Bar Universitario: " + studente1.abbonatoBarUniversitario);

        // Secondo metodo: utilizzare il metodo toString() sovrascritto
        System.out.println(studente1); // Chiamata implicita a studente1.toString()

        // Anche se creiamo un altro studente, il metodo toString() funziona allo stesso modo
        // perché ritorna i this.attributi dell'oggetto su cui viene chiamato.
        Studente studente2 = new Studente("Luisa", "Bianchi", 67890, 1,
                                        30.0, false, true);
        System.out.println(studente2); // Chiamata implicita a studente2.toString()

    }

}
