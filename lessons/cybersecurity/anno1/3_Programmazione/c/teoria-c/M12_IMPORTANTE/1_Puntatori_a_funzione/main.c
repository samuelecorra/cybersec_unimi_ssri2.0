
// Ora che sappiamo dichiarare, definire e chiamare le funzioni, rimane un dubbio...
// come posso passare una funzione come argomento di un'altra funzione?

// 1. Il C non permette di passare direttamente una funzione come argomento

// 2. Però possiamo passare un puntatore a funzione come argomento di un'altra funzione


// ====================================================================================


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

// Partiamo da un esempio molto banale ma che renderà bene l'idea:
// Supponiamo di avere una prima funzione che restituisce un float, la media dei
// voti di uno studente

float media_voti(float *voti, int numeroVoti) {
    float somma = 0;
    for (int i = 0; i < numeroVoti; i++) {
        somma += voti[i];
    }
    return somma / numeroVoti;
}


// Ora possiamo creare una funzione che prende come argomento un puntatore a funzione
void stampa_media(float (*ptr_media_voti)(float*, int), float *voti, int numeroVoti) {

    // All'interno della funzione di stampa, possiamo chiamare la funzione media_voti:
    // mediante il puntatore a funzione che abbiamo passato come argomento:
    float mediaDaStampare = ptr_media_voti(voti, numeroVoti);
    printf("La media dei voti è: %.2f\n", mediaDaStampare);
}


// Ora possiamo testare il tutto nel main:


int main() {
    
    SetConsoleOutputCP(CP_UTF8); // Per permettere la stampa di caratteri speciali come "è"

    // Ovviamente vogliamo che il programma sia versatile, quindi l'array di float
    // sarà allocato dinamicamente:
    int numeroVoti;
    
    printf("Quanti voti vuoi inserire? ");
    scanf("%d", &numeroVoti);
    getchar(); // Per consumare il newline rimasto nel buffer

    float *voti = (float*)malloc(numeroVoti * sizeof(float));
    if (voti == NULL) {
        fprintf(stderr, "Errore di allocazione della memoria.\n");
        return 1;
    }   // Se arriviamo qui tutto ok, procediamo con l'inserimento dei voti

    for (int i = 0; i < numeroVoti; i++) {
        printf("Inserisci il voto %d: ", i + 1);
        scanf("%f", &voti[i]);
        getchar(); // Per consumare il newline rimasto nel buffer
    }

    // Ora il nostro array di float dinamico è pronto, possiamo chiamare la funzione
    // e stamparne la media, che sarà computata internamente dalla funzione di stampa
    // la quale richiama la funzione media_voti tramite il puntatore a funzione
    // che definiamo così:
    float (*ptr_media_voti)(float*, int) = media_voti;
    
    // Ora possiamo passare il puntatore a funzione alla funzione di stampa e chiamarla!
    stampa_media(ptr_media_voti, voti, numeroVoti);

    return 0;
}

// QUINDI RICAPITOLANDO LA SITUAZIONE:
// 1. In fase di dichiarazione di una funzione che prende come argomento un'altra funzione,
// le si passa un puntatore a funzione, che ha la stessa firma della funzione che si vuole
// passare come argomento (stesso tipo di ritorno e stessi tipi e numero di argomenti).

// Nel passare il puntatore si può omettere anche il nome dei parametri, come in questo caso:
// void stampa_media(float (*)(float*, int), float *voti, int numeroVoti);

// 2. In fase di chiamata della funzione che prende come argomento un puntatore a funzione,
// si può passare direttamente il nome della funzione che si vuole usare come argomento,
// senza usare l'operatore & (che comunque sarebbe corretto usarlo, ma è opzionale).