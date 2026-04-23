package M05_Cicli.L03_For;

import java.util.Arrays;
import java.util.Scanner;

public class For {

    // For loop in Java
    public static void main(String[] args) throws InterruptedException {

        // Il ciclo for è utilizzato per eseguire un blocco di codice un numero specifico di volte.
        // Questo è utile quando si conosce in anticipo quante iterazioni sono necessarie.
        // Sintassi del ciclo for:
        // for (inizializzazione; condizione; incremento/decremento) {
        //     // blocco di codice da eseguire
        // }

        // Esempio 1: Stampare i numeri da 1 a 5
        System.out.println("Esempio 1: Stampare i numeri da 1 a 5");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        // Esempio 2: Calcolare la somma dei numeri da 1 a 10
        System.out.println("Esempio 2: Calcolare la somma dei numeri da 1 a 10");
        int somma = 0;
        for (int i = 1; i <= 10; i++) {
            somma += i; // somma = somma + i
        }
        System.out.println("La somma dei numeri da 1 a 10 è: " + somma);

        // Esempio 3: stampare un array (vedremo tra non molto gli array in dettaglio)
        System.out.println("Esempio 3: Stampare gli elementi di un array");
        int[] numeri = {10, 20, 30, 40, 50};
        for (int i = 0; i < numeri.length; i++) {
            System.out.println("Elemento in posizione " + i + ": " + numeri[i]);
        }

        // Costruiamo dinamicamente le interazioni
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quante volte iteriamo: ");
        int n = scanner.nextInt();
        System.out.println("Iteriamo " + n + " volte:");
        for (int i = 0; i < n; i++) {
            System.out.println("Iterazione numero: " + (i + 1));
        }

        scanner.close();

        // Ovviamente la parte di aggiornamento può essere anche negativa:
        // E spoiler: si può rallentare con sleep (vedremo più avanti i thread in Java)
        // l'intervallo di tempo tra un'iterazione e l'altra:
        // Ricordiamo che in questo caso il metodo main deve dichiarare "throws InterruptedException"
        System.out.println("Esempio 3b: Conto alla rovescia da 5 a 1");
        for (int i = 5; i >= 1; i--) {
            System.out.println("Decollo in " + i + "...");
            Thread.sleep(1000); // Pausa di 1 secondo tra le iterazioni
        }
        System.out.println("Decollo!");

        // E' possibile snellire la sintassi del for loop?

        // 1. Sì, usando l'enhanced for loop:
        // 2. Sì, usando il metodo forEach che però non è un vero e proprio ciclo for, ma appunto un metodo!
        System.out.println("Esempio 4: Usare l'enhanced for loop per stampare gli elementi di un array");
        for (int numero : numeri) {
            System.out.println("Elemento: " + numero);
        }

        System.out.println("Esempio 5: Usare il metodo forEach per stampare gli elementi di un array");
        Arrays.stream(numeri).forEach(numero -> System.out.println("Elemento: " + numero));

        // NON FATECI CASO, LI VEDREMO PIU' AVANTI NEL CORSO!
    }
}
