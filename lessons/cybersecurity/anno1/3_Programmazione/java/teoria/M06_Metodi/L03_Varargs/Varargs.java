package M06_Metodi.L03_Varargs;

public class Varargs {

    // Nella scorsa lezione abbiamo visto i metodi con parametri fissi, che
    // richiedono overloading se vogliamo gestire con il medesimo nome di metodo
    // più quantità di argomenti diversi.

    // Però non è l'unico modo per gestire un numero variabile di argomenti!
    // Java supporta infatti i "varargs" (variable arguments), che permettono
    // di definire un metodo che accetta un numero variabile di argomenti
    // dello stesso tipo, senza dover fare overloading.

    // Java si occupa di impacchettare gli argomenti in un array (studiare il Modulo 08 ad essi dedicato prima di procedere).

    // ==============================================================

    // Come primo esempio per impratichirsi basta un semplice metodo che somma
    // un numero variabile di interi:

    static int sommaVarargs(int... numeri) { // i tre puntini dopo il tipo indicano che è un argomento variabile!
        int somma = 0; // qui dentro i numeri sono accessibili come un array
        for (int n : numeri) { // ottimizziamo la leggibilità con enhanced for
            somma += n; // augmented assignment operator
        }
        return somma;
    }

    // Facciamo anche un secondo esempio, media aritmetica di un numero variabile di double:
    static double mediaVarargs(double... numeri) {
        double somma = 0.0;
        for (double n : numeri) {
            somma += n;
        }
        return numeri.length == 0 ? 0.0 : somma / numeri.length; // evitiamo divisione per zero
    }

    // Testiamo i metodi nel main
    static void main(String[] args) {

        System.out.println("Somma di 1, 2, 3: " + sommaVarargs(1, 2, 3));
        System.out.println("Somma di 10, 20, 30, 40, 50: " + sommaVarargs(10, 20, 30, 40, 50));
        System.out.println("Somma di nessun numero: " + sommaVarargs());

        System.out.println("Media di 2.0, 4.0, 6.0: " + mediaVarargs(2.0, 4.0, 6.0));
        System.out.println("Media di 1.5, 2.5, 3.5, 4.5: " + mediaVarargs(1.5, 2.5, 3.5, 4.5));
        System.out.println("Media di nessun numero: " + mediaVarargs());

    }
}
