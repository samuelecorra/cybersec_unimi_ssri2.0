#include <stdio.h>
#define UNDEFINED 0 
#define DIM 100 // massima dimensione del dominio
#define TRUE 1 
#define FALSE 0  
typedef short boolean; 
typedef int mapping; 
mapping *M;

void crea(mapping *M) {
    int i;
    M = (mapping *) malloc(DIM * sizeof(mapping));
    
    for (i = 0; i < DIM; i++) {
        M[i] = UNDEFINED;
    }
}

void assegna(mapping *M, int chiave_dom, int valore_codom) {
    M[chiave_dom] = valore_codom;
}

boolean calcola(mapping *M, int chiave_dom, int *valore_codom) {
    if (M[chiave_dom] != UNDEFINED) {
        *valore_codom = M[chiave_dom];
        return TRUE;
    } else {
        return FALSE;
    }
}