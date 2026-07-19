package M01_Fondamenti.L03_Variabili;

public class Variabili {
    public static void main(String[] args) {
        // Una variabile è un nome associato a un dato di un tipo preciso.
        // La Java Language Specification ne definisce semantica e valori;
        // la disposizione fisica in memoria è un dettaglio della JVM.

        // NON SIAMO PIÙ IN C: distinguiamo tipi primitivi e tipi di riferimento.
        // Dobbiamo introdurre, oltre agli appena citati, anche i tipi di riferimento (reference types).

        // TIPI PRIMITIVI
        // Sono i tipi di dato più semplici e basilari.
        int intero = 42;               // Numeri interi (4 byte)
        double decimale = 3.14;       // Numeri decimali (8 byte)
        char carattere = 'A';         // Unità di codice UTF-16 senza segno a 16 bit
        boolean booleano = true;      // Valori logici true/false; la dimensione in memoria non è fissata dal linguaggio
        // TIPI DI RIFERIMENTO
        // Sono tipi di dato più complessi che fanno riferimento a oggetti.
        String testo = "Ciao, mondo!"; // ATTENZIONE ALLA MAIUSCOLA! (sono oggetti, non tipi primitivi)
        // Poi ci sono gli array
        // E gli oggetti, siano essi di Java Standard, di librerie esterne o definiti da noi.

        // DICHIARAZIONE E INIZIALIZZAZIONE
        int a;              // Dichiarazione (crea la variabile, ma non le assegna un valore)
        // NON STAMPARE A VIDEO UNA VARIABILE NON INIZIALIZZATA!
        a = 10;             // Assegnazione (assegna un valore alla variabile)
        int b = 20;        // Dichiarazione + Inizializzazione (crea la variabile e le assegna un valore in un'unica operazione)
        // STAMPARE A VIDEO
        System.out.println("Valore di a: " + a);
        System.out.println("Valore di b: " + b);
        System.out.println("Valore di intero: " + intero);
        System.out.println("Valore di decimale: " + decimale);
        System.out.println("Valore di carattere: " + carattere);
        System.out.println("Valore di booleano: " + booleano); // In C stampavamo 1 o 0, qui stampa true o false,
        // il che è più semanticamente leggibile!
        System.out.println("Valore di testo: " + testo);

        // DICHIARAZIONI MULTIPLE
        int x = 1, y = 2, z = 3; // Si possono dichiarare più variabili dello stesso tipo in una sola riga.
        System.out.println("Valori di x, y, z: " + x + ", " + y + ", " + z);
        // OPPURE:
        int x2, y2, z2; // Dichiarazione multipla senza inizializzazione

        // RIASSEGNAMENTO
        // facciamo una copia:
        int a2 = a; // Copia del valore di 'a' in 'a2'
        System.out.println("Valore di a2 (copia di a): " + a2);
        a = 15; // Si può riassegnare un nuovo valore a una variabile già esistente.
        System.out.println("Nuovo valore di a dopo riassegnamento: " + a);

    }
}
