/*
 * In molte espressioni il nome di una funzione viene convertito automaticamente
 * in un puntatore a quella funzione. Un callback deve avere una firma compatibile:
 * stesso tipo di ritorno e tipi dei parametri compatibili.
 */

#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef float (*Aggregatore)(const float valori[], size_t quantità);

static float mediaVoti(const float voti[], size_t quantità) {
    float somma = 0.0f;
    for (size_t i = 0; i < quantità; ++i) {
        somma += voti[i];
    }
    return quantità == 0 ? 0.0f : somma / (float)quantità;
}

static float votoMassimo(const float voti[], size_t quantità) {
    if (quantità == 0) {
        return 0.0f;
    }
    float massimo = voti[0];
    for (size_t i = 1; i < quantità; ++i) {
        if (voti[i] > massimo) {
            massimo = voti[i];
        }
    }
    return massimo;
}

static void stampaRisultato(const char *etichetta, Aggregatore funzione,
                            const float voti[], size_t quantità) {
    if (etichetta == NULL || funzione == NULL || voti == NULL || quantità == 0) {
        return;
    }
    printf("%s: %.2f\n", etichetta, funzione(voti, quantità));
}

int main(void) {
    size_t numeroVoti = 0;
    printf("Quanti voti vuoi inserire? ");
    if (scanf("%zu", &numeroVoti) != 1 || numeroVoti == 0
        || numeroVoti > SIZE_MAX / sizeof(float)) {
        fputs("Quantità non valida.\n", stderr);
        return EXIT_FAILURE;
    }

    float *voti = malloc(numeroVoti * sizeof *voti);
    if (voti == NULL) {
        fputs("Allocazione non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }
    for (size_t i = 0; i < numeroVoti; ++i) {
        printf("Inserisci il voto %zu: ", i + 1);
        if (scanf("%f", &voti[i]) != 1) {
            fputs("Voto non valido.\n", stderr);
            free(voti);
            return EXIT_FAILURE;
        }
    }

    Aggregatore aggregatore = mediaVoti;
    stampaRisultato("Media", aggregatore, voti, numeroVoti);
    stampaRisultato("Massimo", votoMassimo, voti, numeroVoti);

    // &mediaVoti è ammesso, ma nella chiamata il solo nome è più idiomatico.
    // Anche (*aggregatore)(...) è corretto; aggregatore(...) è equivalente.
    free(voti);
    return EXIT_SUCCESS;
}
