
// Un argomento molto "ostico" tanto quanto importante per chi si approccia per la
// prima volta alla programmazione in C sono le liste dinamiche.

// cos'è una lista dinamica???

// RISPOSTA RAPIDA: E' UNA STRUCT. NULLA DI PIU', NULLA DI MENO.

// MA ATTENZIONE: SI COSTRUISCE IN UNA MANIERA PRECISA!

// Visivamente, come possiamo immaginarla?

// E' una serie di "nodi" collegati tra loro, dove ogni nodo contiene
// 1. un dato (o più dati)
// 2. un puntatore al nodo successivo

// Ci sono due nodi partiocolari:
// 1. il primo nodo, detto "testa" (head):
//   - contiene il primo dato della lista e il puntatore al secondo nodo
//   - è l'unico nodo che viene "puntato" da una variabile esterna alla lista,
//     poiché prima di esso non ci sono dati da immagazzinare!
//   - viene "creato" esplicitamente

// 2. l'ultimo nodo, detto "coda" (tail):
//   - contiene l'ultimo dato della lista e il puntatore NULL, perché non c'è
//     nessun nodo successivo da puntare!
//   - viene "creato" esplicitamente

// Tutti gli altri nodi intermedi:
//   - contengono un dato e il puntatore al nodo successivo
//   - vengono "creati" dinamicamente, quando servono
//   - vengono "distrutti" dinamicamente, quando non servono più
//   - vengono "collegati" tra loro, in modo che il puntatore di un nodo
//     punti al nodo successivo

// Perché usare una lista dinamica?
// 1. Perché la memoria viene allocata dinamicamente, quindi non si spreca
//    memoria come con gli array statici
// 2. Perché la lista può crescere e diminuire di dimensione in modo
//    flessibile, senza dover ridefinire la dimensione dell'array
// 3. Perché è facile inserire e rimuovere elementi dalla lista, senza dover
//    spostare gli altri elementi come negli array statici
// 4. Perché è facile implementare strutture dati più complesse, come stack
//    e code, che si basano sulle liste dinamiche
// 5. Perché è facile implementare algoritmi di ordinamento e ricerca
//    che si basano sulle liste dinamiche
// 6. Perché è una struttura dati fondamentale per la programmazione
//    orientata agli oggetti e per la gestione della memoria

// Come si crea una lista dinamica in C?

// Vediamolo nel seguente esempio:

#include <stdio.h>
#include <stdlib.h> // per malloc e free

// Definiamo la struct per il nodo della lista
struct Nodo {
    int dato;               // il dato del nodo
    struct Nodo* prossimo;  // il puntatore al nodo successivo
};

// Funzione per creare un nuovo nodo:
// Questa funzione:
// 1. Prende in ingresso un dato che dovrà esser conservato nel nuovo nodo;
// 2. Restituisce un puntatore al nuovo nodo creato, di modo che possa esser
//    collegato alla lista.
// 3. Infatti, se stiamo creando il primo nodo, questo verrà puntato dalla
//    variabile "head" (testa della lista), altrimenti verrà collegato
//    al nodo precedente. E' un codice dunque riusabile!

struct Nodo* creaNodo(int dato) {

    // Allocazione dinamica e controllo esito:
    struct Nodo* nuovoNodo = (struct Nodo*)malloc(sizeof(struct Nodo));
    if (nuovoNodo == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1);
    }

    // Inizializzazione del nodo:
    nuovoNodo->dato = dato;
    nuovoNodo->prossimo = NULL;

    // Restituzione del puntatore al nuovo nodo:
    return nuovoNodo;
}

// Funzione per eliminare un nodo:
void eliminaNodo(struct Nodo* nodo) {
    free(nodo);
}

// In questo caso, se si elimina un nodo intermedio, il nodo precedente
// dovrà essere aggiornato per puntare al nodo successivo di quello eliminato.
// Questa funzione non si occupa di questo, ma solo di liberare la memoria
// occupata dal nodo.

int main() {

    // Creazione della testa della lista:
    struct Nodo* head = creaNodo(10); // primo nodo con dato 10

    // Creazione del secondo nodo e collegamento al primo:
    head->prossimo = creaNodo(20); // secondo nodo con dato 20

    // Creazione del terzo nodo e collegamento al secondo:
    head->prossimo->prossimo = creaNodo(30); // terzo nodo con dato 30

    // Stampa della lista, a partire dalla testa:
    struct Nodo* attuale = head;
    while (attuale != NULL) {
        printf("%d -> ", attuale->dato);
        attuale = attuale->prossimo;
    }
    printf("NULL\n");

    // Eliminazione della lista:
    attuale = head;
    struct Nodo* prossimoNodo;
    while (attuale != NULL) {
        prossimoNodo = attuale->prossimo; // salva il puntatore al prossimo nodo
        eliminaNodo(attuale);             // elimina il nodo attuale
        attuale = prossimoNodo;           // passa al prossimo nodo
    }

    return 0;
}