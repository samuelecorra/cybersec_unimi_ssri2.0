package M01_Fondamenti.L02_Output;

public class Output {
    public static void main(String[] args) {

        // Tre tipi di output a video in Java
        System.out.println("Ciao, questo è un output con println! Vedrai che il prossimo output sarà su una nuova riga.");
        System.out.print("Ciao, questo è un output con print! ");
        System.out.print("Vedi? Non è andato a capo! ");
        System.out.printf("Ciao, questo è un output con printf! Ecco un numero formattato: %.2f\n", 3.14159);
        System.out.println("Fine degli output di esempio.");

        // Nonostante faremo largamente uso dei più comodi println e print...

        // Ripassiamo printf, come avevamo fatto in C:
        // Sintassi di base:
        // System.out.printf("formato", valori...);

        // Lo specificatore di formato ha sintassi:
        // %[flags][width][.precision]conversion   dove:

        // flags: opzioni aggiuntive:
        //     - '-' : allinea a sinistra
        //     - '+' : forza il segno
        //     - '0' : riempie con zeri

        // width: larghezza minima del campo (di base riempie con spazi)

        // .precision: numero di cifre decimali per i float o lunghezza massima per le stringhe
        // conversion: tipo di dato:
        //     - 'd' : interi decimali
        //     - 'f' : numeri in virgola mobile
        //     - 's' : stringhe
        //     - 'c' : caratteri
        //     - 'x' : interi esadecimali
        //     - 'X' : interi esadecimali maiuscoli
        //     - 'o' : interi ottali
        //     - 'e' : notazione scientifica
        //     - 'b' : booleani
        //     - '%' : simbolo percentuale come escape (%% stampa %)
        //     - '\' : se raddoppiato (\\) stampa \

        // In C c'erano i puntatori e quindi %p per gli indirizzi di memoria,
        // in Java non ha senso, quindi non esiste.


        // Altre informazioni fondamentali per essere completi:
//
// - Le sequenze di escape supportano tutti gli escape Java:
//   \n, \t, \", \', \\, \r, \b, \f
//
// - La precisione funziona diversamente a seconda del tipo:
//     • per i float/double: numero di cifre decimali (%.3f → 3 decimali)
//     • per le stringhe: lunghezza massima stampata (%.5s → primi 5 caratteri)
//
// - %n è il newline portabile (meglio di \n)
//
// - Flags aggiuntive:
//     ' ' (spazio) → riserva spazio per il segno
//     ',' → separatore delle migliaia (dipende dal Locale)
//
// - Conversioni avanzate:
//     %h e %H → hashCode in esadecimale
//     %a → notazione esadecimale scientifica dei floating point
//
// - Locale: printf rispetta il Locale della JVM
//     System.out.printf(Locale.US, "%.2f", 3.14);
//
// - %s stampa qualsiasi oggetto usando toString()
//
// - printf e format sono completamente equivalenti
//
// - Ricorda che printf NON manda a capo: usare %n o \n
//
// - È possibile indicare l'indice dell'argomento:
//     System.out.printf("%2$d %1$d", 10, 20);  // stampa 20 10

    }
}