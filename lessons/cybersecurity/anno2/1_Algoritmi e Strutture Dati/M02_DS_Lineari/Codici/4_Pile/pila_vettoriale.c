#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define maxlung 10

// La struttura della pila vettoriale si presenta così:
typedef struct _pila {
    int testa;
    int elementi[maxlung];
} pila;

void creaPilaVuota(pila *P) {
    P->testa = 0;
}

bool isPilaVuota(pila *P) {
    return (P->testa == 0);
}

int leggiElementoInCima(pila *P) {
    if (!isPilaVuota(P))
        return P->elementi[P->testa - 1];
    else {
        printf("Pila vuota: impossibile leggere elemento\n");
        exit(1);
    }
}

void poppaDallaCima(pila *P) {
    if (!isPilaVuota(P))
        P->testa--;
}

void impilaInCima(int elem, pila *P) {
    if (P->testa == maxlung)
        printf("Pila piena: impossibile impilare l'elemento %d\n", elem);
    else {
        P->elementi[P->testa] = elem;
        P->testa++;
    }
}

// Vogliamo realizzare una funzione di stampa che:
// 1) Se la pila è vuota, stampa: "Pila vuota (testa = 0)"
// 2) Altrimenti, stampa: "Pila con N/maxlung elementi: [ elem1 i=0 ] [ elem2 i=1 ] ..., [ elemN i=N-1 ] (testa = <testa>)"
void stampaPila(pila *P) {
    if (isPilaVuota(P)) {
        printf("Pila vuota (testa = 0)\n");
    } else {
        printf("Pila con %d/%d elementi: ", P->testa, maxlung);
        for (int i = 0; i < P->testa; i++) {
            printf(" [ %d i=%d ]", P->elementi[i], i);
        }
        }
        printf(" ] (testa = %d)\n", P->testa);
    }
// Facciamo una demo per le nostre geniali elucubrazioni:
int main() {
    pila P;
    creaPilaVuota(&P);
    // Stampiamo lo stato iniziale:
    stampaPila(&P);
    // Riempiamo con un ciclo una pila di 10 elementi da 10 a 100, e a ogni iterazione la stampiamo:
    for (int i = 1; i <= 10; i++) {
        impilaInCima(i * 10, &P);
        stampaPila(&P);
    }

    // Proviamo a impilare un undicesimo elemento per vedere il messaggio di pila piena:
    impilaInCima(110, &P);
    
    // Infine poppiamo tutti gli elementi uno alla volta, stampando lo stato della pila ad ogni iterazione:
    for (int i = 10; i >= 1; i--) {
        poppaDallaCima(&P);
        stampaPila(&P);
    }

    return 0;
}