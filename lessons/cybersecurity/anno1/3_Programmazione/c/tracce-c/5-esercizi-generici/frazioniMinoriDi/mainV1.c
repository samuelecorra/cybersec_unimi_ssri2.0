#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int numeratore;
    int denominatore;
} frac_t;

typedef struct node {
    frac_t valore;
    struct node *next;
} node;

/*
 * Confronta a/b e c/d senza conversioni in double. Prima rende positivi i
 * denominatori, poi usa i prodotti incrociati: a/b < c/d se a*d < c*b.
 * int64_t evita l'overflow del prodotto di due valori di tipo int.
 */
static bool frazioneMinore(frac_t sinistra, frac_t destra)
{
    int64_t a = sinistra.numeratore;
    int64_t b = sinistra.denominatore;
    int64_t c = destra.numeratore;
    int64_t d = destra.denominatore;

    if (b < 0) {
        a = -a;
        b = -b;
    }
    if (d < 0) {
        c = -c;
        d = -d;
    }
    return a * d < c * b;
}

/* Restituisce -1 se la soglia o un elemento ha denominatore nullo. */
int contaLeFrazioniMinoriDi(const node *testaLista, frac_t soglia)
{
    if (soglia.denominatore == 0) {
        return -1;
    }

    int contatore = 0;
    for (const node *corrente = testaLista;
         corrente != NULL;
         corrente = corrente->next) {
        if (corrente->valore.denominatore == 0) {
            return -1;
        }
        if (frazioneMinore(corrente->valore, soglia)) {
            contatore++;
        }
    }
    return contatore;
}

static node *creaNodo(frac_t valore)
{
    node *nuovo = malloc(sizeof *nuovo);
    if (nuovo != NULL) {
        nuovo->valore = valore;
        nuovo->next = NULL;
    }
    return nuovo;
}

static void liberaLista(node *testa)
{
    while (testa != NULL) {
        node *successivo = testa->next;
        free(testa);
        testa = successivo;
    }
}

int main(void)
{
    node *primo = creaNodo((frac_t){1, 2});
    node *secondo = creaNodo((frac_t){3, 4});
    node *terzo = creaNodo((frac_t){5, 6});
    if (primo == NULL || secondo == NULL || terzo == NULL) {
        fprintf(stderr, "Errore di allocazione della memoria.\n");
        free(primo);
        free(secondo);
        free(terzo);
        return EXIT_FAILURE;
    }
    primo->next = secondo;
    secondo->next = terzo;

    frac_t soglia = {2, 3};
    int risultato = contaLeFrazioniMinoriDi(primo, soglia);
    if (risultato < 0) {
        fprintf(stderr, "Una frazione ha denominatore nullo.\n");
        liberaLista(primo);
        return EXIT_FAILURE;
    }

    printf("Frazioni minori di %d/%d: %d\n",
           soglia.numeratore,
           soglia.denominatore,
           risultato);
    liberaLista(primo);
    return EXIT_SUCCESS;
}
