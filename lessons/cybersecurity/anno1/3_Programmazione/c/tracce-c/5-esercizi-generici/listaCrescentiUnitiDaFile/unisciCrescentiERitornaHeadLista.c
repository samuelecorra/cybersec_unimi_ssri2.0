#include <stdbool.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct nodo {
    int valore;
    struct nodo *next;
} Nodo;

static void liberaLista(Nodo *head)
{
    while (head != NULL) {
        Nodo *successivo = head->next;
        free(head);
        head = successivo;
    }
}

static bool accoda(Nodo **head, Nodo **tail, int valore)
{
    Nodo *nuovo = malloc(sizeof *nuovo);
    if (nuovo == NULL) {
        return false;
    }
    nuovo->valore = valore;
    nuovo->next = NULL;

    if (*tail == NULL) {
        *head = nuovo;
    } else {
        (*tail)->next = nuovo;
    }
    *tail = nuovo;
    return true;
}

Nodo *unisciFileLista(const char *file1, const char *file2)
{
    FILE *f1 = fopen(file1, "r");
    if (f1 == NULL) {
        fprintf(stderr, "Errore nell'apertura del file %s.\n", file1);
        return NULL;
    }
    FILE *f2 = fopen(file2, "r");
    if (f2 == NULL) {
        fprintf(stderr, "Errore nell'apertura del file %s.\n", file2);
        fclose(f1);
        return NULL;
    }

    Nodo *head = NULL;
    Nodo *tail = NULL;
    int n1;
    int n2;
    int r1 = fscanf(f1, "%d", &n1);
    int r2 = fscanf(f2, "%d", &n2);

    /* Merge lineare: si accoda il minore e si avanza soltanto nel suo file. */
    while (r1 == 1 && r2 == 1) {
        int valore;
        if (n1 <= n2) {
            valore = n1;
            r1 = fscanf(f1, "%d", &n1);
        } else {
            valore = n2;
            r2 = fscanf(f2, "%d", &n2);
        }
        if (!accoda(&head, &tail, valore)) {
            fprintf(stderr, "Errore di allocazione della memoria.\n");
            liberaLista(head);
            fclose(f1);
            fclose(f2);
            return NULL;
        }
    }

    while (r1 == 1) {
        if (!accoda(&head, &tail, n1)) {
            fprintf(stderr, "Errore di allocazione della memoria.\n");
            liberaLista(head);
            fclose(f1);
            fclose(f2);
            return NULL;
        }
        r1 = fscanf(f1, "%d", &n1);
    }
    while (r2 == 1) {
        if (!accoda(&head, &tail, n2)) {
            fprintf(stderr, "Errore di allocazione della memoria.\n");
            liberaLista(head);
            fclose(f1);
            fclose(f2);
            return NULL;
        }
        r2 = fscanf(f2, "%d", &n2);
    }

    bool letturaValida = r1 == EOF && r2 == EOF && !ferror(f1) && !ferror(f2);
    bool chiusuraValida = fclose(f1) != EOF;
    chiusuraValida = fclose(f2) != EOF && chiusuraValida;
    if (!letturaValida || !chiusuraValida) {
        fprintf(stderr, "I file contengono dati non validi o non sono leggibili.\n");
        liberaLista(head);
        return NULL;
    }
    return head;
}

static bool leggiNomeFile(const char *richiesta, char *destinazione, size_t capacita)
{
    printf("%s", richiesta);
    if (capacita > (size_t)INT_MAX ||
        fgets(destinazione, (int)capacita, stdin) == NULL) {
        return false;
    }
    size_t newline = strcspn(destinazione, "\n");
    if (destinazione[newline] == '\n') {
        destinazione[newline] = '\0';
        return destinazione[0] != '\0';
    }

    /* Se manca la newline, il nome potrebbe essere stato troncato. */
    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {
    }
    return false;
}

int main(void)
{
    char file1[100];
    char file2[100];
    if (!leggiNomeFile("Inserisci il nome del primo file: ", file1, sizeof file1) ||
        !leggiNomeFile("Inserisci il nome del secondo file: ", file2, sizeof file2)) {
        fprintf(stderr, "Nome file assente o troppo lungo.\n");
        return EXIT_FAILURE;
    }

    Nodo *head = unisciFileLista(file1, file2);
    if (head == NULL) {
        fprintf(stderr, "Errore nella creazione della lista.\n");
        return EXIT_FAILURE;
    }

    printf("Lista unita in ordine crescente:\n");
    for (const Nodo *corrente = head;
         corrente != NULL;
         corrente = corrente->next) {
        printf("%d -> ", corrente->valore);
    }
    printf("NULL\n");
    liberaLista(head);
    return EXIT_SUCCESS;
}
