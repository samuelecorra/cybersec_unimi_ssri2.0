package M05_Cicli.L02_DoWhile;

public class DoWhile {

    // Ci sono alcuni casi in cui però non vogliamo controllare subito la condizione:
    // Se usiamo un while standard e prima ancora di entrare nel ciclo la condizione risulta falsa,
    // entriamo zero volte nel ciclo.

    // Il do-while è POSTCONDIZIONATO, quindi si entra almeno una volta nel ciclo, e poi si valuta
    // la condizione alla fine di ogni iterazione.

    // ESEMPIO:
    static void main() {

        int numero;
        do {
            numero = (int)(Math.random() * 10) + 1; // genera un numero casuale tra 1 e 10
            System.out.println("Numero generato: " + numero);
        } while (numero != 5); // continua finché il numero non è 5

        System.out.println("Hai generato il numero 5, ciclo terminato!");

        // ALL'ATTO PRATICO, IN CHE CONTESTI SI USA IL DO-WHILE RISPETTO AL WHILE?
        // Il do-while è utile quando vogliamo essere sicuri di eseguire almeno una volta il blocco di codice,
        // ad esempio per mostrare un menu all'utente e poi chiedere se vuole ripetere l'operazione.
        // Il while standard è più adatto quando la condizione deve essere verificata prima di eseguire il blocco,
        // come nel caso di validazioni di input.
        // In generale, il do-while è meno comune del while standard, ma rimane uno strumento utile
        // quando la logica del programma lo richiede!

    }
}
