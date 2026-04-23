package M02_Matematica.L01_Aritmetica;

public class Aritmetica {

    public static void main(String[] args) {
        // Esempi di operazioni aritmetiche di base in Java

        int a = 10;
        int b = 3;

        // Addizione
        int somma = a + b;
        System.out.println("Somma: " + somma); // Output: 13

        // Sottrazione
        int differenza = a - b;
        System.out.println("Differenza: " + differenza); // Output: 7

        // Moltiplicazione
        int prodotto = a * b;
        System.out.println("Prodotto: " + prodotto); // Output: 30

        // Divisione
        int quoziente = a / b;
        System.out.println("Quoziente: " + quoziente); // Output: 3

        // Modulo
        int resto = a % b;
        System.out.println("Resto: " + resto); // Output: 1

        // AUGMENTED ASSIGNED OPERATORS - anche dette forme compatte...
        int x = 10;
        x += 5; // Equivalente ad:   x = x + 5
        System.out.println("x dopo x += 5: " + x); // Output: 15
        x *= 2; // Equivalente ad:   x = x * 2
        System.out.println("x dopo x *= 2: " + x); // Output: 30
        x -= 10; // Equivalente ad:   x = x - 10
        System.out.println("x dopo x -= 10: " + x); // Output: 20
        x /= 4; // Equivalente ad:   x = x / 4
        System.out.println("x dopo x /= 4: " + x); // Output: 5
        x %= 3; // Equivalente ad:   x = x % 3
        System.out.println("x dopo x %= 3: " + x); // Output: 2

        // OPERATORI DI INCREMENTO E DECREMENTO
        int y = 5;
        y++; // Equivalente ad:   y = y + 1
        System.out.println("y dopo y++: " + y); // Output: 6
        y--; // Equivalente ad:   y = y - 1
        System.out.println("y dopo y--: " + y); // Output: 5
        // NOTA: Gli operatori di incremento e decremento possono essere
        // usati anche in forma prefissa (++y, --y), ma per ora
        // ci limitiamo alla forma suffissa (y++, y--).


        /*
    ==========================================================
                 PRECEDENZA E ASSOCIAZIONE DEGLI OPERATORI
                          (ORDINE DI VALUTAZIONE)
    ==========================================================

    In Java, come in matematica, esiste un ordine preciso con cui
    le espressioni vengono valutate. Questo ordine determina
    quali operatori vengono eseguiti prima di altri.

    ► 1. POSTFISSI (valutati per primi)
        - a++, a--
        - Accesso a indice: a[i]
        - Accesso a metodo: obj.metodo()
        - Accesso a campo: obj.campo
        - Cast postfisso a tipo array
        Associatività: da sinistra a destra

    ► 2. PREFISSI E UNARI
        - ++a, --a
        - +a, -a
        - !a (NOT logico), ~a (NOT bitwise)
        - (tipo) a  (cast)
        Associatività: da destra a sinistra

    ► 3. MOLTIPLICATIVI
        - *, /, %
        Associatività: da sinistra a destra

    ► 4. ADDITIVI
        - +, -
        (inclusa concatenazione di stringhe con +)
        Associatività: da sinistra a destra

    ► 5. SHIFT BITWISE
        - << (shift a sinistra)
        - >> (shift a destra)
        - >>> (shift a destra senza segno)
        Associatività: da sinistra a destra

    ► 6. RELAZIONALI
        - <, <=, >, >=
        - instanceof
        Associatività: da sinistra a destra

    ► 7. UGUAGLIANZA
        - ==, !=
        Associatività: da sinistra a destra

    ► 8. AND BITWISE
        - &
        Associatività: da sinistra a destra

    ► 9. XOR BITWISE
        - ^
        Associatività: da sinistra a destra

    ► 10. OR BITWISE
        - |
        Associatività: da sinistra a destra

    ► 11. AND LOGICO
        - &&
        (esegue short-circuit: se il primo è false, non valuta il secondo)
        Associatività: da sinistra a destra

    ► 12. OR LOGICO
        - ||
        (short-circuit: se il primo è true, non valuta il secondo)
        Associatività: da sinistra a destra

    ► 13. OPERATORE TERNARIO
        - condizione ? valore1 : valore2
        Associatività: da destra a sinistra

    ► 14. ASSEGNAZIONE
        - =, +=, -=, *=, /=, %=, &=, ^=, |=, <<=, >>=, >>>=
        Associatività: da destra a sinistra

    ► 15. VIRGOLA (COMMA OPERATOR)
        - ,
        (usabile solo in contesti come i for)
        Associatività: da sinistra a destra


    ----------------------------------------------------------
    NOTA IMPORTANTE:
    Le parentesi “( )” hanno precedenza ASSOLUTA
    e permettono di controllare l'ordine di valutazione.
    ----------------------------------------------------------

    In ambito anglosassone si ricorda tutto con la sigla:
        P E M D A S
        Parentheses
        Exponents (non esiste in Java come operatore! Si usa Math.pow)
        Multiplication / Division / Modulo
        Addition / Subtraction

    Gli operatori non menzionati in PEMDAS (come quelli logici,
    bitwise, assegnamenti, ternario, incrementi) sono specifici
    del linguaggio e seguono la scala sopra indicata.
*/

    }
}
