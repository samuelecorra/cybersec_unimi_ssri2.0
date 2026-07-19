#include <math.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

void calcDeviazioneStandard(const int *array, size_t n)
{
    /* Media della popolazione: mu = (somma degli x_i) / N. */
    double somma = 0.0;
    for (size_t i = 0; i < n; i++) {
        somma += (double)array[i];
    }
    double media = somma / (double)n;
    printf("Media: %.2f\n", media);

    /* Varianza della popolazione: sigma^2 = somma((x_i - mu)^2) / N. */
    double sommatoriaScarti = 0.0;
    for (size_t i = 0; i < n; i++) {
        double scarto = (double)array[i] - media;
        sommatoriaScarti += scarto * scarto;
    }
    double varianza = sommatoriaScarti / (double)n;
    printf("Varianza: %.2f\n", varianza);

    double deviazioneStandard = sqrt(varianza);
    printf("Deviazione standard: %.2f\n", deviazioneStandard);
}

int main(void)
{
    long long quantita;
    printf("Quanto e grande la popolazione?\n> ");
    if (scanf("%lld", &quantita) != 1 || quantita <= 0) {
        fprintf(stderr, "La dimensione deve essere un intero positivo.\n");
        return EXIT_FAILURE;
    }
    if ((unsigned long long)quantita > SIZE_MAX / sizeof(int)) {
        fprintf(stderr, "Dimensione non rappresentabile in memoria.\n");
        return EXIT_FAILURE;
    }
    size_t n = (size_t)quantita;

    int *array = malloc(n * sizeof *array);
    if (array == NULL) {
        fprintf(stderr, "Errore di allocazione della memoria.\n");
        return EXIT_FAILURE;
    }

    for (size_t i = 0; i < n; i++) {
        printf("Inserisci il numero %zu:\n> ", i + 1);
        if (scanf("%d", &array[i]) != 1) {
            fprintf(stderr, "Dato non valido.\n");
            free(array);
            return EXIT_FAILURE;
        }
    }

    printf("Calcolo della deviazione standard...\n");
    calcDeviazioneStandard(array, n);
    free(array);
    return EXIT_SUCCESS;
}
