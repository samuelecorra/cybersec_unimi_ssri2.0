// Un albero, in informatica, è una struttura dati non lineare che rappresenta una gerarchia di elementi. Ogni elemento dell'albero è chiamato nodo, e ogni nodo può avere zero o più nodi figli. Il nodo principale dell'albero è chiamato radice, e i nodi senza figli sono chiamati foglie. Gli alberi sono utilizzati in molte applicazioni, come la rappresentazione di strutture gerarchiche, la gestione di dati in database, e l'implementazione di algoritmi di ricerca e ordinamento.

// L'albero genealogico è un esempio di albero, ma è molto generico: in questo
// e nei prossimi file .c andremo ad approfondire questa ds e tutte le sue sfaccettature:
// alberi "standard", alberi binari, alberi di ricerca binari, alberi bilanciati, ecc.

// Gli alberi standard sono da intendersi visivamente come un albero rovesciato, con la root
// in alto e le foglie alla fine del proprio ramo, in basso.

// Esempio visivo:

//         A
//       /   \
//      B     C
//     / \   / \
//    D   E F   G

// Il livello, o depth, di un nodo di solito vede la radice a lv.0 e i figli della radice a lv.1, e così via. 
// In questo esempio, il nodo A è a livello 0, i nodi B e C sono a livello 1, e i nodi D, E, F e G sono a livello 2.

// Discriminando uno specifico nodo, possiamo dire che è la radice di un sottoalbero (se discriminiamo la radice, sottoalbero == albero intero).

// =============================================================================================================================================

// Proviamo a creare la nostra ds albero in C.

#include <stdbool.h>
#include <stdio.h>

// Serve innanzitutto una struct "nodo", che deve ammettere una quantità arbitraria di figli - con gli alberi binari sarà più easy!
typedef struct nodo {

    char valore; // Valore del nodo (può essere un carattere, un intero, ecc.)
    struct nodo** figli; // Array di puntatori ai figli del nodo
    int num_figli; // Numero di figli del nodo

} Nodo;

// Ora serve una funzione che crei l'albero, una che inserisca la radice, una che inserisca un sottoalbero e una che cancelli il sottoalbero.
// Tutte ritornano un puntatore al nodo creato, oppure NULL in caso di errore, quindi il tipo di ritorno sarà Nodo*.
Nodo* crea_albero() {
    Nodo* radice = NULL;
    return radice;
}

Nodo* inserisci_radice(Nodo** radice, char valore) {
    if (*radice != NULL) {
        return NULL; // La radice esiste già, non possiamo inserirne un'altra
    }
    *radice = (Nodo*)malloc(sizeof(Nodo));
    if (*radice == NULL) {
        return NULL; // Errore di allocazione della memoria
    }
    (*radice)->valore = valore;
    (*radice)->figli = NULL;
    (*radice)->num_figli = 0;
    return *radice;
}

Nodo* inserisci_figlio(Nodo* padre, char valore) {
    if (padre == NULL) {
        return NULL; // Il nodo padre non esiste
    }
    Nodo* nuovo_figlio = (Nodo*)malloc(sizeof(Nodo));
    if (nuovo_figlio == NULL) {
        return NULL; // Errore di allocazione della memoria
    }
    nuovo_figlio->valore = valore;
    nuovo_figlio->figli = NULL;
    nuovo_figlio->num_figli = 0;

    // Aumentiamo il numero di figli del nodo padre e riallochiamo l'array dei figli
    padre->num_figli++;
    padre->figli = (Nodo**)realloc(padre->figli, padre->num_figli * sizeof(Nodo*));
    if (padre->figli == NULL) {
        free(nuovo_figlio);
        return NULL; // Errore di riallocazione della memoria
    }
    padre->figli[padre->num_figli - 1] = nuovo_figlio;
    return nuovo_figlio;
}