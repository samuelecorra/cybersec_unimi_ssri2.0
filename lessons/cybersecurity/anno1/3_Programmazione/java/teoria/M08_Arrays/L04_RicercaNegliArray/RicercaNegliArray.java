package M08_Arrays.L04_RicercaNegliArray;

import java.util.Scanner;

public class RicercaNegliArray {
    static void main(String[] args) {

        // Ricerca negli Array in Java
        int[] numeri = {10, 20, 30, 40, 50};
        int target = 30;
        boolean trovato = false;

        // Non dobbiamo far altro che ciclare, e per ogni iterazione controlliamo con if:
        // Si può fare sia con il for tradizionale:
        for(int i = 0; i < numeri.length; i++) {
            if (numeri[i] == target) {
                trovato = true;
                System.out.println(trovato ? "Elemento trovato all'indice " + i + " con for tradizionale!"
                                            : "Elemento non trovato con for tradizionale!");
                break; // Usiamo break per uscire dal ciclo una volta trovato l'elemento
            }
        }

        // Oppure con l'enhanced for, ma non possiamo dire l'indice, quindi usiamo una variabile temporanea:
        trovato = false; // Resettiamo la variabile trovato

        for (int numero : numeri) {
            if (numero == target) {
                trovato = true;
                System.out.println(trovato ? "Elemento trovato con enhanced for!"
                                            : "Elemento non trovato con enhanced for!");

                break; // Usiamo break per uscire dal ciclo una volta trovato l'elemento
            }
        }

// ========================================================================

        // NOTA: In Java esistono anche metodi già pronti per agevolare la ricerca negli array,
        // ad esempio equals() e binarySearch() della classe Arrays nel package java.util.
        // Iniziamo con equals, che serve a confrontare gli array contenenti tipi riferimento (non primitivi):
        String[] array2 = {"Lucio Cornelio Silla", "Pompeo Magno", "Marco Tullio Cicerone"};
        String stringaTarget = "Pompeo Magno";
        boolean trovatoStringa = false;
        for (String elemento : array2) {
            if (elemento.equals(stringaTarget)) { // Usiamo equals() per confrontare le stringhe
                trovatoStringa = true;
                System.out.println(trovatoStringa ? "Elemento trovato con equals()!"
                                                  : "Elemento non trovato con equals()!");
                break; // ma anche in questo caso usciamo senza poter stampare l'indice
            }
        }

        // Quindi sempre meglio attenersi allo standard for:
        trovatoStringa = false;
        for (int i = 0; i < array2.length; i++) {
            if (array2[i].equals(stringaTarget)) {
                trovatoStringa = true;
                System.out.println(trovatoStringa ? "Elemento trovato all'indice " + i + " con for tradizionale e equals()!"
                                                  : "Elemento non trovato con for tradizionale e equals()!");
                break;
            }
        }

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
                System.out.println(imperatoreTrovato ? imperatori[i] + " trovato all'indice " + i + "!"
                                                     : imperatori[i] + " non trovato!");
                break;
            }
        }

    }
}
