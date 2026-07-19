package M08_Arrays.L04_RicercaNegliArray;

import java.util.Arrays;
import java.util.Scanner;

public class RicercaNegliArray {
    public static void main(String[] args) {

        // Ricerca negli Array in Java
        int[] numeri = {10, 20, 30, 40, 50};
        int target = 30;
        boolean trovato = false;

        // Non dobbiamo far altro che ciclare, e per ogni iterazione controlliamo con if:
        // Si può fare sia con il for tradizionale:
        for(int i = 0; i < numeri.length; i++) {
            if (numeri[i] == target) {
                trovato = true;
                System.out.println("Elemento trovato all'indice " + i + " con for tradizionale!");
                break; // Usiamo break per uscire dal ciclo una volta trovato l'elemento
            }
        }
        if (!trovato) {
            System.out.println("Elemento non trovato con for tradizionale!");
        }

        // Oppure con l'enhanced for, ma non possiamo dire l'indice, quindi usiamo una variabile temporanea:
        trovato = false; // Resettiamo la variabile trovato

        for (int numero : numeri) {
            if (numero == target) {
                trovato = true;
                System.out.println("Elemento trovato con enhanced for!");

                break; // Usiamo break per uscire dal ciclo una volta trovato l'elemento
            }
        }
        if (!trovato) {
            System.out.println("Elemento non trovato con enhanced for!");
        }

// ========================================================================

        // Per cercare una String confrontiamo ciascun elemento con equals():
        String[] array2 = {"Lucio Cornelio Silla", "Pompeo Magno", "Marco Tullio Cicerone"};
        String stringaTarget = "Pompeo Magno";
        boolean trovatoStringa = false;
        for (String elemento : array2) {
            if (elemento.equals(stringaTarget)) { // Usiamo equals() per confrontare le stringhe
                trovatoStringa = true;
                System.out.println("Elemento trovato con equals()!");
                break; // ma anche in questo caso usciamo senza poter stampare l'indice
            }
        }
        if (!trovatoStringa) {
            System.out.println("Elemento non trovato con equals()!");
        }

        // Se ci serve anche l'indice, scegliamo il for tradizionale:
        trovatoStringa = false;
        for (int i = 0; i < array2.length; i++) {
            if (array2[i].equals(stringaTarget)) {
                trovatoStringa = true;
                System.out.println("Elemento trovato all'indice " + i + " con for tradizionale e equals()!");
                break;
            }
        }
        if (!trovatoStringa) {
            System.out.println("Elemento non trovato con for tradizionale e equals()!");
        }

        // Arrays.equals confronta due array interi elemento per elemento: non cerca
        // un singolo valore e offre overload sia per primitivi sia per riferimenti.
        System.out.println("I due array hanno lo stesso contenuto? "
                + Arrays.equals(numeri, new int[]{10, 20, 30, 40, 50}));

        // Arrays.binarySearch è adatto a un array ordinato.
        int indiceQuaranta = Arrays.binarySearch(numeri, 40);
        System.out.println("binarySearch trova 40 all'indice: " + indiceQuaranta);

// ========================================================================

        // Ovviamente con un minimo sforzo si può anche far cercare l'utente:

        Scanner sc = new Scanner(System.in);

        String imperatori[] = {"Augusto", "Tiberio", "Caligola", "Claudio", "Nerone"};

        System.out.print("Inserisci un nome di imperatore romano da cercare: ");
        String imperatoreDaCercare = sc.nextLine();
        boolean imperatoreTrovato = false;

        for (int i = 0; i < imperatori.length; i++) {
            if (imperatori[i].equalsIgnoreCase(imperatoreDaCercare)) { // equalsIgnoreCase ignora maiuscole/minuscole
                imperatoreTrovato = true;
                System.out.println(imperatori[i] + " trovato all'indice " + i + "!");
                break;
            }
        }

        if (!imperatoreTrovato) {
            System.out.println(imperatoreDaCercare + " non trovato!");
        }

        sc.close();

    }
}
