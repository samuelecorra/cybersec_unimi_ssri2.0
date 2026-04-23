#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void calcDeviazioneStandard (int* array, int N) {
    // MEDIA = μ = (Σxᵢ/N)
    float somma = 0;
    for (int i=0; i<N; i++) {
        somma += array[i];
    }
    printf("Media: %.2f\n", somma/N);
    
    // VARIANZA = σ² = (Σ(xᵢ-μ)²/N)
    float sommatoriaScarti = 0;
    for (int i=0; i<N; i++) {
        float scartoQuadratico = pow(array[i] - (somma/N), 2);
        sommatoriaScarti += scartoQuadratico;
    }
    float varianza = sommatoriaScarti / N;
    printf("Varianza: %.2f\n", varianza);
    float deviazioneStandard = sqrt(varianza);
    printf("Deviazione Standard: %.2f\n", deviazioneStandard);

}

int main() {

    int N;
    printf("Quanto è grande la popolazione?\n> ");
    scanf("%d", &N);
    while (N <= 0) {
        printf("Come faccio a calcolarti anche solo la media con popolazione non positiva?\n> ");
        scanf("%d", &N);
    }

    int* array = malloc(N* sizeof(int)); // alloco dinamicamente
    if (array == NULL) {
        printf("Errore di allocazione della memoria\n"); return 1;
    }

    for (int i=0; i<N; i++) {
        printf("Inserisci il numero %d:\n> ", i+1);
        scanf("%d", &array[i]); // riempio l'array
    }

    printf("Calcolo della deviazione standard...\n");
    calcDeviazioneStandard(array, N);
    free(array);
    return 0;
}