/*
OPERATORI BITWISE

Gli operatori bitwise lavorano sui singoli bit dei tipi interi:

&   AND bit a bit
|   OR bit a bit
^   XOR bit a bit
~   complemento bit a bit
<<  scorrimento a sinistra
>>  scorrimento a destra

Non vanno confusi con &&, || e !, che sono operatori logici, producono 0 oppure
1 e applicano short-circuit a && e ||. Gli operatori bitwise valutano entrambi
gli operandi e combinano le rispettive rappresentazioni binarie.

Per maschere e shift è preferibile lavorare con tipi unsigned. Il numero di
posizioni deve essere non negativo e minore del numero di bit del tipo promosso.
Lo shift a sinistra di un unsigned è definito modulo 2^N; lo shift a destra
inserisce zeri. Il comportamento dello shift a destra di un signed negativo è
invece dipendente dall'implementazione.

La precedenza può sorprendere: == e != hanno precedenza maggiore di &, ^ e |.
Nel test di una maschera conviene quindi scrivere sempre (valore & MASCHERA) != 0U.
*/

#include <stdio.h>

int main(void) {
    unsigned int a = 0x0CU; // 1100 in binario
    unsigned int b = 0x0AU; // 1010 in binario

    printf("a & b = 0x%X\n", a & b); // 1000 -> 0x8
    printf("a | b = 0x%X\n", a | b); // 1110 -> 0xE
    printf("a ^ b = 0x%X\n", a ^ b); // 0110 -> 0x6
    printf("~a    = 0x%X\n", ~a);    // inverte tutti i bit di unsigned int

    unsigned int uno = 1U;
    printf("1 << 3 = %u\n", uno << 3); // 8
    printf("8 >> 2 = %u\n", 8U >> 2);  // 2

    // Esempio classico: ogni bit rappresenta un permesso indipendente.
    const unsigned int PERMESSO_LETTURA = 1U << 0;
    const unsigned int PERMESSO_SCRITTURA = 1U << 1;
    const unsigned int PERMESSO_ESECUZIONE = 1U << 2;

    unsigned int permessi = 0U;
    permessi |= PERMESSO_LETTURA;    // attiva il bit di lettura
    permessi |= PERMESSO_SCRITTURA;  // attiva il bit di scrittura

    if ((permessi & PERMESSO_LETTURA) != 0U) {
        printf("Permesso di lettura presente.\n");
    }

    permessi &= ~PERMESSO_SCRITTURA; // disattiva il bit di scrittura
    permessi ^= PERMESSO_ESECUZIONE; // inverte il bit di esecuzione

    printf("Maschera finale dei permessi: 0x%X\n", permessi);

    return 0;
}
