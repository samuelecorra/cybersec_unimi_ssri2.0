/*
 * Una union sovrappone tutti i membri nella stessa area di memoria: in ogni
 * istante va letto soltanto il membro attivo. Un enum discriminante consente di
 * ricordare quale rappresentazione sia valida (tagged union).
 */

#include <stdio.h>

typedef enum {
    VALORE_INTERO,
    VALORE_REALE
} TipoValore;

typedef struct {
    TipoValore tipo;
    union {
        int intero;
        double reale;
    } dato;
} Valore;

static void stampaValore(const Valore *valore) {
    switch (valore->tipo) {
        case VALORE_INTERO:
            printf("Intero: %d\n", valore->dato.intero);
            break;
        case VALORE_REALE:
            printf("Reale: %.2f\n", valore->dato.reale);
            break;
        default:
            puts("Tipo non valido");
            break;
    }
}

int main(void) {
    Valore quantità = {.tipo = VALORE_INTERO, .dato.intero = 42};
    Valore media = {.tipo = VALORE_REALE, .dato.reale = 27.5};

    stampaValore(&quantità);
    stampaValore(&media);
    printf("La union occupa %zu byte.\n", sizeof quantità.dato);
    return 0;
}
