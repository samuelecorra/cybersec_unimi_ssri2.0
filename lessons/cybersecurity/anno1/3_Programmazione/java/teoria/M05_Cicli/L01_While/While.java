package M05_Cicli.L01_While;

import java.util.Scanner;

public class While {

    // Il ciclo while è PRECONDIZIONATO,
    // cioè verifica la condizione PRIMA di eseguire il blocco di codice.
    // In generale continua a iterare finché la condizione è vera (true).
    // Nel blocco di codice, è importante modificare una variabile
    // coinvolta nella condizione, altrimenti si rischia un ciclo infinito.
    // Ergo il blocco contiene la condizione di terminazione del ciclo.

    static void main() {

        Scanner input = new Scanner(System.in);

        String nome = "";
        // ESEMPIO: chiedere il nome finché l'utente non inserisce effettivamente una stringa valida:
        while (nome.isEmpty()) { // ritorna un booleano
            System.out.print("Inserisci il tuo nome: ");
            nome = input.nextLine();
        }

        System.out.println("Ciao, " + nome + "!");

        // Ovviamente dovendo valutare una condizione l'importante è che alla fine della fiera
        // nella parentesi tonda ci sia un'espressione/variabile booleana valida.
        // ESEMPIO 2:
        boolean eStudente = true;
        int annoDiScuola = 1;

        while (eStudente) {
            System.out.println("Sei ancora uno studente del " + annoDiScuola + "° anno.");
            annoDiScuola++;
            if (annoDiScuola > 5) {
                eStudente = false; // condizione di terminazione
            }
        }

        // Possiamo comodamente dire all'utente di premere un determinato tasto per uscire
        // e usare questo come condizione di terminazione mediante equals():
        String comando = "";
        while (!comando.equals("Q")) { // continua finché comando NON è uguale a "Q"
            System.out.print("Stai giocando... Premi Q per uscire: ");
            comando = input.nextLine().toUpperCase(); // convertiamo in maiuscolo per sicurezza
        }

        // Ma anche per imporre validazioni!
        System.out.println("Inserisci la tua età (0-120): ");
        int eta = input.nextInt();
        while (eta < 0 || eta > 120) { // finché l'età non è valida
            System.out.print("Età non valida. Inserisci una età compresa tra 0 e 120: ");
            eta = input.nextInt();
        }
        System.out.println("Età inserita: " + eta);

        // Come anticipato, si possono chainare più condizioni logiche:
        int numero = 0;
        while (numero % 2 != 0 || numero < 10 || numero > 100) {
            System.out.print("Inserisci un numero pari compreso tra 10 e 100: ");
            numero = input.nextInt();
        }
        System.out.println("Numero valido inserito: " + numero);

        input.close();
    }
}
