#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    char carattere;
    int frequenza;
} risultato;

risultato elaboraMatrice(int n, char** matrice) {

    // Ci serve di sicuro un array-buffer di supporto per contare le occorrenze dei caratteri:
    int occorrenze[256] = {0}; // Inizializzo tutto a 0
    // Ora scorro la matrice per contare le occorrenze:
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            unsigned char indice = (unsigned char)matrice[i][j];
            occorrenze[indice]++;
        }
    }
    // Ora individuiamo il carattere con il massimo delle occorrenze:
    char carattereMassimo = 0;
    int maxOccorrenze = 0;
    for(int k = 0; k < 256; k++) {
        if(occorrenze[k] > maxOccorrenze) {
            maxOccorrenze = occorrenze[k];
            carattereMassimo = (char)k;
        }
    }
    // Ora stampiamo la matrice con gli spazi:
    printf("Matrice elaborata:\n");
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(matrice[i][j] == carattereMassimo) {
                printf("%c ", matrice[i][j]);
            } else {
                printf("  ");
            }
        }
        printf("\n");
    }
    // Infine prepariamo il risultato da restituire:
    risultato res;
    res.carattere = carattereMassimo;
    res.frequenza = maxOccorrenze;
    return res;

}

// Main di test
int main() {
    int n = 4;
    // Allochiamo dinamicamente la matrice
    char** matrice = (char**)malloc(n * sizeof(char*));
    for(int i = 0; i < n; i++) {
        matrice[i] = (char*)malloc(n * sizeof(char));
    }
    // Inizializziamo la matrice con un esempio
    char esempio[4][4] = {
        {'a', 'b', 'a', 'c'},
        {'d', 'a', 'e', 'f'},
        {'g', 'h', 'a', 'i'},
        {'j', 'k', 'l', 'a'}
    };
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            matrice[i][j] = esempio[i][j];
        }
    }
    // Chiamiamo la funzione di elaborazione
    risultato res = elaboraMatrice(n, matrice);
    // Stampiamo il risultato
    printf("Carattere più frequente: '%c' con frequenza: %d\n", res.carattere, res.frequenza);
    // Liberiamo la memoria
    for(int i = 0; i < n; i++) {
        free(matrice[i]);
    }
    free(matrice);
    return 0;
}