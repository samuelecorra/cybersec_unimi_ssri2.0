#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct nodo {
    int val;
    struct nodo *next;
} nodo;

static void liberaLista(nodo *head)
{
    while (head != NULL) {
        nodo *successivo = head->next;
        free(head);
        head = successivo;
    }
}

static bool inserisciOrdinato(nodo **head, int valore)
{
    nodo *nuovo = malloc(sizeof *nuovo);
    if (nuovo == NULL) {
        return false;
    }
    nuovo->val = valore;

    /* Il doppio puntatore individua anche il collegamento alla testa. */
    nodo **collegamento = head;
    while (*collegamento != NULL && (*collegamento)->val < valore) {
        collegamento = &(*collegamento)->next;
    }
    nuovo->next = *collegamento;
    *collegamento = nuovo;
    return true;
}

nodo *ordinaFile(const char *nomeFileIn, const char *nomeFileOut)
{
    FILE *fIn = fopen(nomeFileIn, "r");
    if (fIn == NULL) {
        fprintf(stderr, "Errore nell'apertura del file di input.\n");
        return NULL;
    }

    nodo *head = NULL;
    int numero;
    int esitoLettura;
    while ((esitoLettura = fscanf(fIn, "%d", &numero)) == 1) {
        if (!inserisciOrdinato(&head, numero)) {
            fprintf(stderr, "Errore di allocazione della memoria.\n");
            liberaLista(head);
            fclose(fIn);
            return NULL;
        }
    }

    if (esitoLettura != EOF || ferror(fIn)) {
        fprintf(stderr, "Il file di input contiene un dato non intero.\n");
        liberaLista(head);
        fclose(fIn);
        return NULL;
    }
    if (fclose(fIn) == EOF) {
        fprintf(stderr, "Errore durante la chiusura del file di input.\n");
        liberaLista(head);
        return NULL;
    }

    FILE *fOut = fopen(nomeFileOut, "w");
    if (fOut == NULL) {
        fprintf(stderr, "Errore nell'apertura del file di output.\n");
        liberaLista(head);
        return NULL;
    }

    bool primo = true;
    bool scritturaRiuscita = true;
    for (const nodo *corrente = head;
         corrente != NULL && scritturaRiuscita;
         corrente = corrente->next) {
        if (!primo && fputc(' ', fOut) == EOF) {
            scritturaRiuscita = false;
        }
        if (scritturaRiuscita && fprintf(fOut, "%d", corrente->val) < 0) {
            scritturaRiuscita = false;
        }
        primo = false;
    }
    if (scritturaRiuscita && fputc('\n', fOut) == EOF) {
        scritturaRiuscita = false;
    }
    if (fclose(fOut) == EOF) {
        scritturaRiuscita = false;
    }

    if (!scritturaRiuscita) {
        fprintf(stderr, "Errore durante la scrittura del file di output.\n");
        liberaLista(head);
        return NULL;
    }
    return head;
}

int main(void)
{
    nodo *listaOrdinata = ordinaFile("input.txt", "output.txt");
    if (listaOrdinata == NULL) {
        return EXIT_FAILURE;
    }

    for (const nodo *corrente = listaOrdinata;
         corrente != NULL;
         corrente = corrente->next) {
        printf("[ %d ] -> ", corrente->val);
    }
    printf("NULL\n");

    liberaLista(listaOrdinata);
    return EXIT_SUCCESS;
}
