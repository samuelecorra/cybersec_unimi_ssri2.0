/* Cosa succede quando invece di inizializzare una variabile decidiamo
di dichiararla e basta per poi stamparla?
=> UNDEFINED BEHAVIOR ovvero comportamento indefinito, ovvero
stamperemo un valore casuale poiché si sta accedendo a una zona di memoria
non inizializzata, che quindi magari appartiene a un'altra variabile
di un'altra funzione di un altro file... INSOMMA, ciò che conta è che 
NON OTTENIAMO QUEL CHE DESIDERIAMO, ANZI STAMPANDO UN VALORE CASUALE SI RISCHIA
DI CREARE BUG DIFFICILI DA TROVARE

Non è un “memory leak”: i leak riguardano heap non liberato (malloc/free).

Usare memoria non inizializzata è un’altra categoria di bug (CWE-457) e può portare
a vulnerabilità (es. leak di dati se stampi lo stack), ma non è un leak di memoria.

Perché da te esce 0?

Su Windows le pagine di stack appena assegnate dal SO sono spesso azzerate per motivi
di sicurezza.
In un programma piccolissimo come il nostro, main usa proprio una porzione “fresca” 
dello stack → quell’area contiene zeri → per caso leggi 0. 
In altri contesti/funzioni potresti vedere valori spazzatura.
Basta sporcare un po' lo stack (es. chiamare una funzione che usa molte variabili locali)
e magari vedi valori diversi.
*/

#include <stdio.h>

int main() {
    int a;
    char b;
    float c;
    
    printf("%d\n", a);
    printf("%c\n", b);
    printf("%f\n", c);
    return 0;
}