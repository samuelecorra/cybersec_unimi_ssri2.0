#include <stdio.h>
#include <stdlib.h>

typedef struct nodo {
    int val;
    struct nodo* next;
} nodo;

nodo* ordinaFile(char* nomeFileIn, char* nomeFileOut) { // Ritorniamo come al solito puntatore a nodo aka testa
    
    FILE* fIn = fopen(nomeFileIn, "r"); // Apriamo in lettura
    if (fIn == NULL) {
        printf("Errore nell'apertura del file di input.\n");
        return NULL; // Gestiamo l'errore
    }

    nodo* head = NULL;  // Inizializziamo la testa della lista a NULL.

    int num;    // Ci serve un contenitore d'appoggio per i numeri letti dal file.

    // fscanf restituisce il numero di elementi letti con successo, fino a che riesce a leggere
    // un intero continuerà a restituire 1, quando arriva alla fine del file restituirà EOF.
    while (fscanf(fIn, "%d", &num) != EOF) {

        nodo* new = (nodo*)malloc(sizeof(nodo)); // Fintanto che leggiamo un numero effettivo, creiamo un nuovo nodo:
        new->val = num; // Inizializziamo il valore
        new->next = NULL; // MA non sappiamo ancora dove collocarlo!

        // Ora bisogna inserire i nodi così creati in maniera ordinata:
        
        // Serve un controllo condizionale che sappia distinguere vari scenari:

        if (head == NULL) {
            head = new; // Lista vuota: il nuovo nodo diventa la testa.
        } else if (num < head->val) { // Numero letto minore del valore in testa: inseriamo prima della testa.
            new->next = head; // La ex testa diventa il successivo del nuovo nodo...
            head = new; // ...e il nuovo nodo diventa la nuova testa.
        } else {
            // Se il nuovo nodo contiene un valore maggiore o uguale a quello della testa della lista
            // troviamo il punto esatto in cui inserirlo, scorrendo la lista.
            nodo* cur = head; // puntatore di appoggio per scorrere la lista

            while (cur->next != NULL && cur->next->val < num) { // finché non arriviamo alla fine della lista ( != NULL si può omettere)
                // e finché il successivo del nodo corrente è minore del numero da inserire
                cur = cur->next; // scorriamo in avanti
            }
            // Sia che arriviamo in fondo, sia che troviamo un nodo con valore maggiore o uguale a num,
            // dobbiamo inserire il nuovo nodo tra il corrente trovato e il suo successivo:
            new->next = cur->next; // il successivo del nuovo nodo diventa il successivo del corrente
            cur->next = new; // il successivo del corrente diventa il nuovo nodo
        }
    }

    fclose(fIn); // Parte di input completata!

    FILE* fOut = fopen(nomeFileOut, "w"); // Iniziamo la fase di scrittura in output
    if (fOut == NULL) {
        printf("Errore nell'apertura del file di output.\n");

        nodo* current = head;           // In caso di errore nell'apertura del file di output,
        while (current != NULL) {       // liberiamo la memoria allocata per la lista prima di uscire dalla funzione.
            nodo* temp = current;
            current = current->next;
            free(temp);
        }
        return NULL; // Gestiamo l'errore e usciamo dalla funzione
    }

    nodo* current = head; // Scriviamo la lista nel file di output.
    while (current != NULL) {
        fprintf(fOut, "%d ", current->val);
        current = current->next;
    }

    fclose(fOut);
    return head;
}

int main() {
    char* in = "input.txt";
    char* out = "output.txt";

    nodo* listaOrdinata = ordinaFile(in, out); // File output.txt creato! Controllare!

    // Stampiamo la lista per verificare che sia ordinata:
    nodo* current = listaOrdinata;
    while (current != NULL) {
        printf("[ %d ] -> ", current->val);
        current = current->next;
    }
    printf("NULL\n");

    return 0;
}