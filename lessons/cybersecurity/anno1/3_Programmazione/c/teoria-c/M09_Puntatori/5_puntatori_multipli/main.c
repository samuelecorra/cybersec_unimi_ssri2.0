
// Concludiamo il percorso sui puntatori introducendo un concetto molto importante:
// i puntatori multipli.

// Quando parliamo di puntatori multipli, intendiamo i puntatori a puntatori,
// ovvero variabili che contengono l'indirizzo di un'altra variabile puntatore.

// Facciamo prima a vedere un esempio:

#include <stdio.h>

int main() {
    
    int x = 19;

    // Dichiariamo un puntatore a int:
    int *p1 = &x;

    // Dichiariamo un puntatore a puntatore a int:
    int **p2 = &p1;

    // E anche un'altro puntatore a puntatore a int:
    int ***p3 = &p2;

    // Partiamo con la stampa del valore di x:
    printf("Valore di x: %d\n", x);
    printf("Valore di x tramite p1: %d\n", *p1);
    printf("Valore di x tramite p2: %d\n", **p2);
    printf("Valore di x tramite p3: %d\n", ***p3);

    // E' intuitivo: ogni volta che dereferenzio un puntatore, scendo di un livello
    // nella "catena" dei puntatori.
    // Quindi man mano che aggiungo a p1 un altro livello di indirezione,
    // per ottenere il valore di x devo dereferenziare di più.

    // Vediamo ora gli indirizzi:
    printf("Indirizzo di x: 0x%p\n", &x);
    printf("Indirizzo di x tramite p1: 0x%p\n", p1);
    printf("Indirizzo di x tramite p2: 0x%p\n", *p2);
    printf("Indirizzo di x tramite p3: 0x%p\n", **p3);


// ====================================================================================


    // Ora vediamo il valore e l'indirizzo di ciascuna delle 4 variabili:

    printf("\nValore di x: %d, indirizzo di x: 0x%p\n", x, &x);
    printf("Valore di p1: 0x%p, indirizzo di p1: 0x%p\n", p1, &p1);
    printf("Valore di p2: 0x%p, indirizzo di p2: 0x%p\n", p2, &p2);
    printf("Valore di p3: 0x%p, indirizzo di p3: 0x%p\n", p3, &p3);

    //  Noteremo che indirizzo di x e valore di p1 sono uguali,
    //  così come indirizzo di p1 e valore di p2, e indirizzo di p2 e valore di p3.
    //  Questo perché ogni puntatore contiene l'indirizzo della variabile a cui punta.

    
    printf("Premi invio per continuare...");
    getchar();

    // Complichiamo un po' le cose con gli array:
    // Ricordando che il nome di un array decade automaticamente a puntatore al primo elemento,
    // possiamo fare questo:

    int array[] = { 10, 20, 30, 40, 50 };

    int *p_array = array; // puntatore al primo elemento dell'array
    int **pp_array = &p_array; // puntatore a puntatore al primo elemento
    int ***ppp_array = &pp_array; // puntatore a puntatore a puntatore al primo elemento

    // Stampiamo il primo elemento dell'array in tutti i modi possibili:
    printf("Primo elemento dell'array: %d\n", array[0]);
    printf("Primo elemento dell'array tramite p_array: %d\n", *p_array);
    printf("Primo elemento dell'array tramite pp_array: %d\n", **pp_array);
    printf("Primo elemento dell'array tramite ppp_array: %d\n", ***ppp_array);

    // Idem con gli indirizzi:
    printf("Indirizzo del primo elemento dell'array: 0x%p, ma anche 0x%p\n", &array[0], array);
    printf("Indirizzo del primo elemento dell'array tramite p_array: 0x%p\n", p_array);
    printf("Indirizzo del primo elemento dell'array tramite pp_array: 0x%p\n", *pp_array);
    printf("Indirizzo del primo elemento dell'array tramite ppp_array: 0x%p\n", **ppp_array);

    // Ora proviamo a stampare l'elemento di indice 1, ovvero il secondo:
    printf("Secondo elemento dell'array: %d\n", array[1]);
    printf("Secondo elemento dell'array tramite p_array: %d\n", *(p_array + 1));
    printf("Secondo elemento dell'array tramite pp_array: %d\n", *(*pp_array + 1));
    printf("Secondo elemento dell'array tramite ppp_array: %d\n", **(*ppp_array + 1));

    // Ma possiamo anche mischiare puntatori e indici:
    printf("Secondo elemento dell'array tramite array con indice: %d\n", array[1]);
    printf("Secondo elemento dell'array tramite p_array con indice: %d\n", p_array[1]);
    printf("Secondo elemento dell'array tramite pp_array con indice: %d\n", (*pp_array)[1]);
    printf("Secondo elemento dell'array tramite ppp_array con indice: %d\n", (**ppp_array)[1]);


    printf("Premi invio per continuare...");
    getchar();
    return 0;
}