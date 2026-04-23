#include <stdio.h>
#include <stdlib.h>

// definisco la struttura per la lista di divisori. la lista contiene un intero e il puntatore al prossimo nodo
typedef struct ListaDivisori {
    int divisore;
    struct ListaDivisori* next;
} ListaDivisori;

// ho scelto una lista collegata per gestire un numero variabile di divisori senza spreco di memoria

ListaDivisori* calcolaDivisoriDi(int n) {
    if (n < 1) {
        printf("Input non valido: deve essere un numero positivo.\n");
        return NULL;
    }
    ListaDivisori *head = NULL, *tail = NULL; // inizializzo la testa e la coda della lista

    // I divisori di n sono compresi tra 1 e n, quindi scorriamo in questo intervallo:
    for (int i = 1; i <= n; i++) {

        
        if (n % i == 0) { // Se il modulo è zero, i è un divisore di n
            ListaDivisori* nuovo = malloc(sizeof(ListaDivisori)); // alloco memoria per il nuovo nodo
            nuovo->divisore = i; // e ci metto il divisore
            nuovo->next = NULL; // il prossimo è NULL per ora

            if (!head) { // se la lista è vuota, il nuovo nodo è sia testa che coda
                head = nuovo; // la testa punta al nuovo nodo
                tail = nuovo; // la coda punta al nuovo nodo
            } else { // altrimenti aggiungo il nuovo nodo in fondo
                tail->next = nuovo; // la EX-coda punta al nuovo nodo perché ora è il penultimo
                tail = nuovo; // e infine la nuova coda è il nuovo nodo
            }
        }
    }
    return head; // ritorno la testa della lista
}

int main(){

    int n = 657; 
    
    printf("Divisori di %d:\n", n);
    
    ListaDivisori* lista = calcolaDivisoriDi(n); // chiamo la funzione
    
    // stampa dei divisori (solo per test, richiesta dal main)
    // Si noti che dopo il primo ; "curr;" sottintende curr != NULL
    for (ListaDivisori* curr = lista; curr; curr = curr->next) // itero su ogni nodo della lista
        printf("[ %d ] -> ", curr->divisore); // stampo ogni divisore
    printf("NULL\n");

    // libero la memoria allocata
    ListaDivisori* curr = lista;      // 1️⃣ partiamo dalla testa
    while (curr) {                    // 2️⃣ finché il puntatore non è NULL
        ListaDivisori* next = curr->next; // 3️⃣ salviamo l’indirizzo del prossimo nodo
        free(curr);                       // 4️⃣ liberiamo il nodo corrente
        curr = next;                      // 5️⃣ passiamo al prossimo
    }

    return 0;
}