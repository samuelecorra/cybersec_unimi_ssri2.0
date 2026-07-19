/* Cosa succede quando invece di inizializzare una variabile decidiamo
di dichiararla e basta per poi stamparla?
=> UNDEFINED BEHAVIOR, ovvero comportamento indefinito. Non è corretto dire che
"stamperemo un valore casuale": il linguaggio non garantisce alcun risultato e il
compilatore può trasformare il programma assumendo che tale lettura non avvenga.

Non è un “memory leak”: i leak riguardano heap non liberato (malloc/free).

Usare memoria non inizializzata è un’altra categoria di bug (CWE-457) e può portare
a vulnerabilità (es. leak di dati se stampi lo stack), ma non è un leak di memoria.

Se in una particolare esecuzione compare 0, è soltanto una possibile manifestazione
dell'UB: non costituisce una regola e non rende valido il programma. Inoltre, la memoria
di un processo è isolata dagli altri processi: non stiamo leggendo variabili di altri
programmi.

L'esempio pericoloso è disabilitato di default, così il file resta compilabile ed
eseguibile in sicurezza. Impostare ESEGUI_ESEMPIO_UB a 1 serve solo a osservare che
l'esito non è affidabile; non rende il codice corretto.
*/

#include <stdio.h>

#define ESEGUI_ESEMPIO_UB 0

int main(void) {
#if ESEGUI_ESEMPIO_UB
    int a;
    char b;
    float c;
    
    printf("%d\n", a);
    printf("%c\n", b);
    printf("%f\n", c);
#else
    printf("Esempio UB disabilitato: inizializza sempre una variabile prima di leggerla.\n");
#endif
    return 0;
}
