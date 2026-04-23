package M05_Cicli.L04_BreakContinue;

public class BreakContinue {
    static void main() {

        // break e continue in Java

        // Il comando break viene utilizzato per uscire immediatamente da un ciclo (for, while, do-while)
        // o da un blocco switch. Quando viene incontrato, il controllo del programma salta fuori dal ciclo o dallo switch.
        // Esempio di break in un ciclo for:
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break; // Esce dal ciclo quando i è uguale a 5
            }
            System.out.println(i); // Stampa i numeri da 0 a 4
        }

        // Il comando continue viene utilizzato per saltare l'iterazione corrente di un ciclo
        // e passare direttamente alla successiva. Quando viene incontrato, il resto del codice
        // all'interno del ciclo per quell'iterazione viene ignorato.
        // Esempio di continue in un ciclo for:
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue; // Salta il resto del ciclo per i numeri pari
            }
            System.out.println(i); // Stampa solo i numeri dispari
        }

    }
}
