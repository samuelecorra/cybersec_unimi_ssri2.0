package M11_JavaAvanzato.L05_HashMaps;

import java.util.HashMap;

public class HashMaps {

    // L'HashMap è una struttura dati che memorizza coppie chiave-valore.
    // Le chiavi devono per forza di cose essere univoche, mentre i valori possono essere duplicati.

    // NON MANTENGONO ALCUN ORDINE! -> MA SONO MEMORY EFFICIENT!

    // SINTASSI BASE:
    // HashMap<Chiave, Valore> nomeHashMap = new HashMap<>();

    static void main() {

        HashMap<String, Double> mappa = new HashMap<>();

        // Aggiungere elementi
        mappa.put("Alice", 85.5);
        mappa.put("Bob", 92.0);
        mappa.put("Charlie", 78.0);

        // Stampare l'intera mappa
        System.out.println("Mappa iniziale: " + mappa);
        // Esce già preformattato come {Alice=85.5, Bob=92.0, Charlie=78.0}

        // Accedere a un valore tramite la chiave
        Double votoAlice = mappa.get("Alice");
        System.out.println("Voto di Alice: " + votoAlice);

        // Rimuovere un elemento tramite la chiave
        mappa.remove("Bob");
        System.out.println("Dopo aver rimosso Bob: " + mappa);

        // Verificare se una chiave esiste
        boolean esisteCharlie = mappa.containsKey("Charlie");
        System.out.println("Charlie esiste? " + esisteCharlie);

        // Verificare se un valore esiste
        boolean esisteVoto90 = mappa.containsValue(90.0);
        System.out.println("Esiste un voto di 90.0? " + esisteVoto90);

        // Se tentassimo di aggiungere un elemento con una chiave già esistente, il valore verrà sovrascritto
        mappa.put("Alice", 90.0);
        System.out.println("Dopo aver aggiornato il voto di Alice: " + mappa);

        // Ottenere la dimensione della mappa
        int dimensione = mappa.size();
        System.out.println("Dimensione della mappa: " + dimensione);

        // Possiamo stampare formattando meglio...
        // Dobbiamo trovare ogni chiave, il metodo più semplice è usare un enhanced for loop
        // che recupera ogni chiave tramite keySet(), che è un metodo che restituisce un Set di tutte le chiavi presenti nella mappa

        System.out.println("Stampo formattato:");

        for(String chiave : mappa.keySet()) {
            System.out.println(chiave + " ha voto: " + mappa.get(chiave));
        }


    }
}
