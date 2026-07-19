/*
 * const esprime l'intenzione di non modificare un oggetto attraverso un certo
 * accesso. Non rende sempre il dato immutabile in assoluto: conta dove compare.
 */

#include <stddef.h>
#include <stdio.h>

static int somma(const int valori[], size_t quantità) {
    int totale = 0;
    for (size_t i = 0; i < quantità; ++i) {
        totale += valori[i];
        // valori[i] = 0; // errore: la funzione ha promesso di non modificare l'array
    }
    return totale;
}

int main(void) {
    const int soglia = 18;            // oggetto non modificabile tramite "soglia"
    int voto = 27;
    const int *lettura = &voto;       // puntatore a int const
    int *const posizione = &voto;     // puntatore const a int modificabile

    printf("Soglia: %d, voto: %d\n", soglia, *lettura);
    *posizione = 28;                   // valido: cambia voto
    // posizione = &soglia;            // errore: posizione non può essere riassegnato
    // *lettura = 30;                   // errore: lettura non consente la modifica

    const int voti[] = {28, 30, 27};
    printf("Somma: %d\n", somma(voti, sizeof voti / sizeof voti[0]));
    return 0;
}
