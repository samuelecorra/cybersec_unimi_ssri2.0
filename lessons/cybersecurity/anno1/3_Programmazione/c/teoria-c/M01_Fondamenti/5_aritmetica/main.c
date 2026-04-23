/*
L'aritmetica in C è basilare e intutiva, l'unica cosa davvero importante
COME AL SOLITO E' INGRANARE SUBITO PER QUANTO RIGUARDA L'OPERATOR PRECEDENCE!

Operatori aritmetici e logici del C:

//================================================================================

// FONDAMENTALI:

+   addizione
-   sottrazione
*   moltiplicazione
/   divisione

%   modulo (resto della divisione intera)

//================================================================================

// OPERATORI DI INCREMENTO E DECREMENTO:

++  incremento di 1
--  decremento di 1

// ATTENZIONE: possono essere usati sia in forma prefissa che in forma postfissa!
// Vedremo poi un esempio sotto...


//================================================================================

=   assegnazione // DA SAPERE! BEN DIVERSO DA:

// OPERATORI DI UGUAGLIANZA, DISUGUAGLIANZA E CONFRONTO:

==  confronto di uguaglianza

!=  confronto di disuguaglianza
>   confronto maggiore
<   confronto minore
>=  confronto maggiore o uguale
<=  confronto minore o uguale

//================================================================================

// FORME COMPATTE E COMBINATE:

+=  addizione e assegnazione
-=  sottrazione e assegnazione
*=  moltiplicazione e assegnazione
/=  divisione e assegnazione    

%=  modulo e assegnazione                                                           */


//================================================================================

#include <stdio.h>
#include <stdbool.h>      // necessario per usare il tipo bool

int main() {

    int a = 5;
    int b = 2;

    // Operatori aritmetici di base
    printf("a + b = %d\n", a + b); // addizione
    printf("a - b = %d\n", a - b); // sottrazione
    printf("a * b = %d\n", a * b); // moltiplicazione
    printf("a / b = %d\n", a / b); // divisione (intera)
    printf("a %% b = %d\n", a % b); // modulo

    // Operatori di incremento e decremento
    int c = 5;
    printf("c++ = %d\n", c++); // postfisso: stampa c, poi incrementa
    printf("++c = %d\n", ++c); // prefisso: incrementa, poi stampa c // ci ritroveremo con c = 7
    printf("c-- = %d\n", c--); // postfisso: stampa c, poi decrementa
    printf("--c = %d\n", --c); // prefisso: decrementa, poi stampa c // ci ritroveremo con c = 5

    // Operatori di confronto
    bool eq = (a == b);
    bool neq = (a != b);
    bool gt = (a > b);
    bool lt = (a < b);
    bool gte = (a >= b);
    bool lte = (a <= b);

    printf("a == b: %d\n", eq);
    printf("a != b: %d\n", neq);
    printf("a > b: %d\n", gt);
    printf("a < b: %d\n", lt);
    printf("a >= b: %d\n", gte);
    printf("a <= b: %d\n", lte);

    // Forme compatte e combinate
    int d = 10;
    d += 5; // d = d + 5
    printf("d after d += 5: %d\n", d);
    d -= 3; // d = d - 3
    printf("d after d -= 3: %d\n", d);
    d *= 2; // d = d * 2
    printf("d after d *= 2: %d\n", d);
    d /= 4; // d = d / 4
    printf("d after d /= 4: %d\n", d);
    d %= 3; // d = d % 3
    printf("d after d %%= 3: %d\n", d);

    return 0;
}