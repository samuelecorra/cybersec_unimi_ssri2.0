package M09_OOP.L19_Eccezioni;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Esempio di gestione delle eccezioni CHECKED:
 * FileReader e BufferedReader lanciano IOException.
 */
public class MainEccezioniChecked {

    static void main(String[] args) {

        System.out.println("=== Lettura da file con eccezioni checked ===");

        try {
            BufferedReader br = new BufferedReader(new FileReader("file_che_non_esiste.txt"));
            String linea = br.readLine();
            System.out.println("Contenuto: " + linea);
            br.close();
        } catch (IOException e) {
            System.out.println("ERRORE: impossibile leggere il file!");
            System.out.println("Dettagli: " + e.getMessage());
        }

        System.out.println("\nIl programma continua comunque.");
    }
}
