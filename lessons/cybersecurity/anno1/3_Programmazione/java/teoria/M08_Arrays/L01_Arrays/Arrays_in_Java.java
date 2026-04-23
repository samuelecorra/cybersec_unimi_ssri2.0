package M08_Arrays.L01_Arrays;

public class Arrays_in_Java {

    // ARRAY IN C VS ARRAY IN JAVA

    // In C un array è semplicemente un blocco contiguo di memoria
    // che contiene elementi dello stesso tipo. Gli array in C non
    // hanno informazioni sul loro tipo o sulla loro dimensione.
    // Devi gestire manualmente la memoria e tenere traccia della
    // dimensione dell'array.

    // In Java, un array è un oggetto che contiene informazioni
    // sul tipo degli elementi e sulla loro dimensione. Gli array
    // in Java sono gestiti automaticamente dal garbage collector,
    // quindi non devi preoccuparti della gestione della memoria.

    // DICHIARAZIONE E INIZIALIZZAZIONE DI ARRAY IN JAVA
    static void main() {

        // Dichiarazione di un array di interi in Java
        int[] arrayDiInteri; // Forma identica al C

        int arrayDiInteri2[]; // Anche questa forma è valida, ma meno comune


        // ===================================================================

        // Dopo averlo dichiarato, va inizializzato con "new", che vedremo in futuro.
        // Per ora ci basti sapere che è la parolina magica che dà spazio effettivo all'array.

        // Inizializzazione dell'array con una dimensione specifica
        arrayDiInteri = new int[5];

        // Inizializzazione e dichiarazione in un'unica riga, sempre possibile come in C!
        int[] arrayDiInteri3 = new int[10];

        // Cosa comporta inizializzare specificando solo la dimensione con new?
        // COMPORTA CHE LE CELLE DELL'ARRAY VENGONO AUTOMATICAMENTE INIZIALIZZATE
        // AL VALORE DI DEFAULT DEL TIPO DI DATO DELL'ARRAY.
        // Per gli array di tipi primitivi come int, double, char, boolean,
        // il valore di default è rispettivamente 0, 0.0, '\u0000', false.
        // Per gli array di tipi di riferimento (oggetti), il valore di default è null.


        // ===================================================================

        // Ma attenzione: chi viene dal C già sa che non è tassativo inizializzare
        // un array con new, perché si può fare anche così:

        int[] arrayDiInteri4 = {1, 2, 3, 4, 5}; // Array di 5 interi con valori specifici

        // Dopo aver detto i vari valori, Java ne deduce la dimensione automaticamente (5 in questo caso).
        // MA non può cambiare dimensione dopo, perché gli array in Java sono di dimensione fissa,
        // come in C.

        // ===================================================================

        // Accesso agli elementi dell'array

        // Identico al C: si usa l'indice tra parentesi quadre, 0-based:
        arrayDiInteri[0] = 10;

        // Con tutte le espressioni che a questo punto vogliamo creare...
        int primoElemento = arrayDiInteri4[0]; // Ottiene il primo elemento

        // ===================================================================

        // Lunghezza dell'array - non è altro che il metodo built-in "length"
        int lunghezzaArray = arrayDiInteri4.length; // Restituisce 5
        // GRANDE PASSO IN AVANTI RISPETTO AL C, DOVE BISOGNA TENERE TRACCIA DELLA DIMENSIONE MANUALMENTE
        // facendo ad esempio int size = sizeof(array) / sizeof(array[0]);

        // ===================================================================

        // Gli array possono, come anticipato, contenere sia primitivi che riferimenti.
        // Per ora stampiamo un po' di stringhe usando un array di stringhe.

        String[] frutta = {"Mela", "Banana", "Ciliegia", "Mango", "Passion Fruit"};
        for (int i = 0; i < frutta.length; i++) { // length utilissimo!
            System.out.println("Frutto " + i + ": " + frutta[i]);
        }

        // Mi piacciono tanto le ciliegie, accedo in stampa alla cella che voglio:
        System.out.println("Mi piacciono tanto le " + frutta[2] + "!");

        // NON MI STANCHERO' MAI DI DIRLO: SONO 0 BASED, QUINDI
        // GLI INDICI VANNO DA 0 A length-1!

        // QUINDI ATTENZIONE NEL CICLO FOR => BISOGNA METTERE TASSATIVAMENTE IL MINORE STRETTO,
        // NON IL MINORE O UGUALE!

        // Cambiamo un elemento dell'array:
        frutta[3] = "Papaya"; // Sostituisco "Mango" con "Papaya"
        System.out.println("Ora il frutto in posizione 3 è: " + frutta[3]);

        // La length viene ritornata, in quanto metodo, ergo possiamo riassegnarla...
        int numFrutti = frutta.length;
        System.out.println("Numero di frutti nell'array: " + numFrutti);

        // ===================================================================

        // Iniziamo a ampliare le nostre vedute: NON SEMPRE, ma spesso, è utile
        // ciclare con un enhanced for, che si presenta così:

        System.out.println("Stampo tutti i frutti con un enhanced for:");
        for (String frutto : frutta) { // Per ogni stringa frutto (: = nell') array frutta
            System.out.println(frutto); // stampo il singolo corrente scorro implicitamente in avanti
        }

        // VANTAGGI: IPERLEGGIBILE!
        // SVANTAGGI: NON POSSO SAPERE L'INDICE CORRENTE, NÉ MODIFICARE L'ELEMENTO
        // SE NON TRAMITE L'INDICE STESSO (CHE NON HO).

    }
}
