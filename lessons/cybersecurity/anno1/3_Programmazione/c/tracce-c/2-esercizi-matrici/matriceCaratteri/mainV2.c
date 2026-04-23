#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    char carattere;
    int frequenza;
} risultato;

risultato elaboraMatrice(int n, char matriceQuadrata[n][n]) {

    // L'idea è sempre la solita: i sottoprogrammi conta-caratteri sfruttano
    // il fatto che ad ogni carattere corrisponde un valore ASCII univoco che può
    // essere usato come indice di un array di contatori.
    int asciiCounters[256] = {0};

    // Spezziamo il problema in due fasi: prima si contano le occorrenze di ogni elemento,
    // e solo dopo si trova nell'array così aggiornato il valore massimo.

    // Scorriamo la matrice e contiamo le occorrenze di ogni elemento, ovvero immagazzinandole
    // con un'incremento del loro posto nell'array ASCII:
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            asciiCounters[(unsigned char)matriceQuadrata[i][j]]++;
        
    // Ora scandiamo l'array aggiornato e troviamo la cella col valore più grande,
    // aka il carattere più frequente: per farlo, servono almeno due variabili di appoggio:
    int maxTrovato = 0;
    char carPiuFreq = 0;

    for(int i = 0; i < 256; i++) {
        if(asciiCounters[i] > maxTrovato) {
            maxTrovato = asciiCounters[i];
            carPiuFreq = (char)i;
        }
        // se no niente e reiteriamo sul sucessivo
    }

    // Ora stampiamo la matrice mostrando solo il carattere più frequente:
    printf("Matrice, considerando solo il carattere più frequente:\n\n");
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            printf("%c ", matriceQuadrata[i][j] == carPiuFreq ? carPiuFreq : '$' );
        }
        printf("\n");
    }
    
    // Popoliamo la struttura del risultato:
    risultato res = {carPiuFreq, maxTrovato};
    return res;

}

// Testiamo:
int main(void) {
    
    char matrix[5][5] = {
        {'e', 'r', 'i', 'k', 'a'},
        {'l', 'u', 'c', 'a', 's'},
        {'b', 'i', 'l', 'l', 'y'},
        {'v', 'e', 'c', 'n', 'a'},
        {'a', 'm', 'o', 'r', 'e'}
    };

    risultato r = elaboraMatrice(5, matrix);
    printf("\nCarattere più frequente: %c, compare %d volte", r.carattere, r.frequenza);
    
    return 0;
}
