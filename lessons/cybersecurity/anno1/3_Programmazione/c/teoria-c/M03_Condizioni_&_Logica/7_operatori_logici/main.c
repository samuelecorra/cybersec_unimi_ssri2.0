/*
Ho atteso finora prima di introdurre gli operatori logici
perché diventano utili solo nel momento in cui si sanno già padroneggiare
if e switch! */

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(65001);

    // Gli operatori logici sono:
    // && (AND)
    // || (OR)
    // !  (NOT)

    // Si usano per combinare, modificare o anche negare condizioni booleane

// ====================================================================================

    // Partiamo con un costrutto già conosciuto ma che ha bisogno di revisione...
    int temperatura;

    printf("Inserisci la temperatura: ");
    scanf("%d", &temperatura);
    getchar();

    if (temperatura > 0) {
        printf("La temperatura è nella norma!\n");
    } else if (temperatura < 30) {
        printf("La temperatura è nella norma!\n");
    } else {
        printf("La temperatura è fuori norma (caldo/freddo eccessivo)!\n");
    }

    // Il codice sopra funziona, ma non è ottimale.
    // Infatti, se la temperatura inserita è un numero assurdamente grande,
    // il programma dirà che è nella norma, il che non ha senso perché staremmo
    // già morendo fusi come fossimo sulla superficie del sole!

    // Se scambiassimo il primo if con il primo else if, il problema si speculerebbe:
    // se inserissimo un numero assurdamente piccolo, il programma direbbe che è nella norma,
    // ma saremmo già morti di ipotermia prima che il programma finisca di eseguire.

    // La soluzione è usare l'operatore AND (&&):
    if (temperatura > 0 && temperatura < 30) {
        printf("La temperatura è nella norma!\n");
    } else {
        printf("La temperatura è fuori norma (caldo/freddo eccessivo)!\n");
    }

// ====================================================================================


    // Possiamo riarrangiare il precedente esempio per introdurre anche OR:

    if (temperatura < 0 || temperatura > 30) {
        printf("La temperatura è fuori norma (caldo/freddo eccessivo)!\n");
    } else {
        printf("La temperatura è nella norma!\n");
    }

// ====================================================================================


    // Infine, vediamo l'operatore NOT (!):

    bool piove = false;

    if(!piove) {
        printf("Non piove, puoi uscire senza ombrello!\n");
    } else {
        printf("Piove, prendi l'ombrello!\n");
    }   

    // Leggibilità molto alta! Sembra quasi di leggere l'italiano!
    // if non piove puoi uscire senza ombrello!
    // Che nel nostro caso è vero, dato che piove è false e quindi negato diventa true!

    
    return 0;
}