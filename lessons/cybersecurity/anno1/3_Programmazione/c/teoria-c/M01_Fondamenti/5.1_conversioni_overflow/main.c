/*
CONVERSIONI, PROMOZIONI E LIMITI NUMERICI

Un'espressione C può contenere operandi di tipo diverso. Prima di eseguire
l'operazione, il linguaggio applica conversioni precise:

1. char, signed char, unsigned char, short e unsigned short subiscono le
   promozioni intere: diventano int se int può rappresentarne tutti i valori,
   altrimenti unsigned int.
2. Tra tipi aritmetici differenti entrano in gioco le usual arithmetic
   conversions, che cercano un tipo comune.
3. Un cast esplicito, come (double)a, richiede deliberatamente una conversione.

La conversione non recupera informazione già persa: in 7 / 2 la divisione intera
avviene prima di un'eventuale assegnazione a double. Bisogna convertire almeno un
operando prima della divisione.

ATTENZIONE AI LIMITI:
- l'overflow di un intero signed ha comportamento indefinito;
- l'aritmetica unsigned è definita modulo 2^N;
- convertire floating-point in intero tronca verso zero, ma il valore deve essere
  rappresentabile nel tipo di destinazione;
- confrontare signed e unsigned può convertire il signed in unsigned e produrre
  risultati sorprendenti, soprattutto se il valore signed è negativo.
*/

#include <limits.h>
#include <stdio.h>

int main(void) {
    int numeratore = 7;
    int denominatore = 2;

    int quozienteIntero = numeratore / denominatore;             // 3
    double quozienteReale = (double)numeratore / denominatore;   // 3.5

    printf("Divisione intera: %d / %d = %d\n",
           numeratore, denominatore, quozienteIntero);
    printf("Divisione reale:   %d / %d = %.1f\n",
           numeratore, denominatore, quozienteReale);

    double misura = 3.9;
    int parteIntera = (int)misura; // troncamento verso zero, non arrotondamento
    printf("Cast esplicito: (int)%.1f = %d\n", misura, parteIntera);

    unsigned int contatore = UINT_MAX;
    contatore += 1U; // comportamento definito: torna a 0 modulo UINT_MAX + 1
    printf("Wrap-around unsigned: UINT_MAX + 1 = %u\n", contatore);

    int limite = INT_MAX;
    if (limite <= INT_MAX - 1) {
        limite += 1;
    } else {
        printf("Incremento evitato: INT_MAX + 1 causerebbe overflow signed.\n");
    }

    int debito = -1;
    unsigned int credito = 1U;

    // Evitiamo il confronto diretto debito < credito: in quel confronto -1 può
    // essere convertito in un grande unsigned. Separiamo prima il caso negativo.
    if (debito < 0 || (unsigned int)debito < credito) {
        printf("Il debito precede correttamente il credito nel confronto controllato.\n");
    }

    float decimo = 0.1f;
    printf("0.1f con più cifre: %.9f (molti decimali non sono esatti in base 2)\n", decimo);

    return 0;
}
