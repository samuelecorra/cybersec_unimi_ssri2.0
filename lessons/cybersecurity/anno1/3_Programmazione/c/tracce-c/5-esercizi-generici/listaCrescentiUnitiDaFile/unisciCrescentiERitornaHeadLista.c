#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

// Innanzitutto definiamo per bene com'è fatta la lista dinamica:
typedef struct nodo {
    int valore;
    struct nodo *next;
} Nodo;


Nodo* unisciFileLista(const char* file1, const char* file2) {

    // Apriamo i file in lettura con rispettive verifiche se l'open è andato a buon fine
    FILE* f1 = fopen(file1, "r");
    if (f1 == NULL) {
        fprintf(stderr, "Errore nell'apertura del file %s\n", file1);
        return NULL;
    }
    FILE* f2 = fopen(file2, "r");
    if (f2 == NULL) {
        fprintf(stderr, "Errore nell'apertura del file %s\n", file2);
        fclose(f1); // Chiudiamo il primo file prima di uscire
        return NULL;
    }

    // Dopo aver aperto entrambi i file in lettura (supponiamo che in entrambi
    // i file, i numeri siano ordinati in ordine crescente e separati da spazi o
    // newline), procediamo con la lettura e l'inserimento nella lista dinamica
    
    // Nella lista che creeremo, manterremo l'ordinamento crescente:
    // Questo obiettivo ci suggerisce di usare un approccio "merge" simile
    // a quello usato nell'algoritmo di merge sort.

    // COME FUNZIONA IL MERGE?
    // Abbiamo due puntatori, uno per ciascun file, che leggono i numeri
    // uno alla volta. Confrontiamo i numeri letti e inseriamo il più
    // piccolo nella lista. Avanziamo il puntatore del file da cui abbiamo
    // preso il numero. Ripetiamo finché non abbiamo esaurito entrambi i file.

    Nodo* head = NULL; // Testa della lista
    Nodo* tail = NULL; // Coda della lista, per facilitare l'inserimento alla fine

    // Leggiamo i numeri dai file e li inseriamo nella lista
    int n1, n2;
    int r1 = fscanf(f1, "%d", &n1);
    int r2 = fscanf(f2, "%d", &n2);

    while (r1 == 1 && r2 == 1) { // 1) Finché ENTRAMBI hanno un numero pronto


        int take; // Variabile di appoggio per il numero da prendere

        if (n1 <= n2) {               // <= mantiene la stabilità
            take = n1;
            r1 = fscanf(f1, "%d", &n1);   // avanza SOLO f1
        } else {
            take = n2;
            r2 = fscanf(f2, "%d", &n2);   // avanza SOLO f2
        }

        Nodo* node = malloc(sizeof *node);
        if (!node) {
            fprintf(stderr, "malloc fallita\n");
            fclose(f1); 
            fclose(f2); 
            return NULL;
        }

        node->valore = take;
        node->next = NULL;

        // Ora controlliamo se è il primo nodo o no: se la testa è NULL l'if è vero
        // e quindi stiamo inserendo il primo nodo: 
        if (!head) {
            head = tail = node; // assegnamo node a tail, e poi anche a head, così abbiamo il primo nodo!
        } 
        else {
            tail->next = node; 
            tail = node; 
        }
    }

    while (r1 == 1) { // 2) Scarica il resto di f1 (se f2 è finito)
        
        Nodo* node = malloc(sizeof *node);
        
        if (!node) { 
            fprintf(stderr, "malloc fallita\n"); 
            fclose(f1);
            fclose(f2);
            return NULL; 
        }
        
        node->valore = n1;
        node->next = NULL;
        
        if (!head) {
            head = tail = node;
        } else {
            tail->next = node; 
            tail = node; 
        }

        r1 = fscanf(f1, "%d", &n1);
    }

    while (r2 == 1) { // 3) Scarica il resto di f2 (se f1 è finito)
        
        Nodo* node = malloc(sizeof *node);
        
        if (!node) {
            fprintf(stderr, "malloc fallita\n");
            fclose(f1);
            fclose(f2);
            return NULL;
        }

        node->valore = n2;
        node->next = NULL;

        if (!head) { 
            head = tail = node;
        } else { 
            tail->next = node;
            tail = node; 
        }
        
        r2 = fscanf(f2, "%d", &n2);
       
    }

    fclose(f1);
    fclose(f2);

    return head;
}

int main() {

    char file1[100], file2[100]; // Chiediamo i nomi dei file all'utente
    printf("Inserisci il nome del primo file: ");
    scanf("%s", file1);
    // getchar NON SERVE qui, perché scanf con %s consuma lo spazio
    // successivo (incluso il newline)

    printf("Inserisci il nome del secondo file: ");
    scanf("%s", file2);

    // Chiamiamo la funzione per unire i file in una lista
    Nodo* testaListaDaStampare = unisciFileLista(file1, file2);
    if (testaListaDaStampare == NULL) {
        fprintf(stderr, "Errore nella creazione della lista.\n");
        return 1;
    }

    // Stampiamo la lista risultante
    printf("Lista unita in ordine crescente:\n");
    Nodo* current = testaListaDaStampare;
    while (current != NULL) {
        printf("%d -> ", current->valore);
        current = current->next;
    }
    printf("NULL\n");

    // Liberiamo la memoria allocata per la lista
    current = testaListaDaStampare;
    while (current != NULL) {
        Nodo* temp = current;
        current = current->next;
        free(temp);
    }

    return 0;
}