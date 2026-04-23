#include <stdio.h>
#include <stdlib.h>

// I vari nodi della lista saranno strutturati in questo modo:
typedef struct valore {
    int z; // numero intero
    struct valore* next; // puntatore al nodo seguente
} valore;

// La lista per semplicitià conterrà solo il puntatore al primo nodo
typedef struct lista {
    valore* testa; // puntatore al primissimo nodo nella lista
} lista;


// Dal momento che dobbiamo creare una nuova lista e quindi creare nuovi nodi
// dinamicamente, dobbiamo avere una funzione dedicata. La faremo restituire
// un puntatore al nuovo nodo creato:
valore* creaNodo(int z) {
    valore* nuovo = (valore*)malloc(sizeof(valore)); // alloco memoria per il nuovo nodo
    if (nuovo == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1);
    }
    nuovo->z = z; // assegno il valore al nodo
    nuovo->next = NULL; // il prossimo nodo è NULL per ora
    return nuovo; // ritorno il puntatore al nuovo nodo
}

// Una volta creato, va inserito in lista nella maniera ordinata che desideriamo in base
// al valore z che contiene. La funzione restituirà la nuova testa della lista. Per forza
// di cose dovremo dunque passare come parametro la testa della lista attuale e il valore da inserire,
// di modo da chiamare internamente la funzione di creazione e passare nuovamente il valore z:
valore* mettiOrdinato(valore* testa, int z) {
    valore* nuovo = creaNodo(z); // sto creando un nodo nuovo inserendo il valore z

    // Ora ci sono due casi:
    if (testa == NULL || z < testa->z) { // se la lista è vuota oppure il nuovo valore è inferiore al primo
        nuovo->next = testa; // il nuovo nodo punta alla testa attuale, ergo lo inseriamo PRIMA rimpiazzandola
        return nuovo; // il nuovo nodo diventa la nuova testa
    }
    // Altrimenti dobbiamo scorrere la lista per trovare la posizione giusta
    valore* corrente = testa; // corrente serve a scorrere la nostra lista per intero
    while (corrente->next != NULL && corrente->next->z < z) {
        corrente = corrente->next; // avanziamo finché il valore successivo non è uguale o maggiore di z
    }
    // Quando effettivamente il successivo del nodo corrente è >= z, inseriamo il nuovo nodo qui
    nuovo->next = corrente->next; // il nuovo nodo punta a quello che era il successivo del corrente
    corrente->next = nuovo; // il successivo del corrente diventa il nuovo nodo
    return testa;
}


// Ora ci vuole una funzione che unisca le due liste ordinate senza ripetizioni
lista* unisciListe(lista* L1, lista* L2) {

    // Prima di tutto serve creare la nuova lista, inizialmente vuota
    lista* listaUnita = (lista*)malloc(sizeof(lista));
    if (listaUnita == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1);
    }
    listaUnita->testa = NULL;

    // Ora bisogna appoggiarsi a due puntatori correnti per scorrere le due liste
    // partendo chiaramente dalle teste
    valore* corr1 = L1->testa;
    valore* corr2 = L2->testa;

    // Dovendo scorrerle obbligatoriamente tutte, condizione di uscita è che entrambe
    // non siano NULL
    while (corr1 != NULL && corr2 != NULL) {

        // Confrontiamo i valori:
        if (corr1->z < corr2->z) {
            // Caso valore di L1 minore di L2
            printf("Valore L1: %d minore di L2: %d\nInserisco %d\n", corr1->z, corr2->z, corr1->z);
            listaUnita->testa = mettiOrdinato(listaUnita->testa, corr1->z);
            corr1 = corr1->next; // avanziamo solo la prima lista
        } else if (corr1->z > corr2->z) {
            // Caso valore di L2 minore di L1
            printf("Valore L2: %d minore di L1: %d\nInserisco %d\n", corr2->z, corr1->z, corr2->z);
            listaUnita->testa = mettiOrdinato(listaUnita->testa, corr2->z);
            corr2 = corr2->next; // avanziamo solo la seconda lista
        } else {
            // Caso valori uguali
            printf("Valore L1: %d uguale a L2: %d\nInserisco %d una volta sola\n", corr1->z, corr2->z, corr1->z);
            listaUnita->testa = mettiOrdinato(listaUnita->testa, corr1->z);
            corr1 = corr1->next; 
            corr2 = corr2->next; // avanziamo entrambe le liste evitando duplicati
        }
    }
    // Le liste non sono necessariamente della stessa lunghezza, quindi dobbiamo
    // anche gestire l'eventualità che una delle due sia finita prima dell'altra:

    // Prima valutiamo il caso in cui la seconda sia più corta e quindi rimangano
    // elementi nella prima:
    while (corr1 != NULL) {
        printf("Rimangono elementi in L1: %d\nInserisco %d\n", corr1->z, corr1->z);
        listaUnita->testa = mettiOrdinato(listaUnita->testa, corr1->z);
        corr1 = corr1->next;
    }
    // Ora valutiamo il caso in cui la prima sia più corta e quindi rimangano
    // elementi nella seconda:
    while (corr2 != NULL) {
        printf("Rimangono elementi in L2: %d\nInserisco %d\n", corr2->z, corr2->z);
        listaUnita->testa = mettiOrdinato(listaUnita->testa, corr2->z);
        corr2 = corr2->next;
    }
    return listaUnita;
}

void liberaLista(lista* L) {
    if (!L) return;
    valore* curr = L->testa;
    while (curr) { // sottintende curr != NULL
        valore* next = curr->next;
        free(curr);
        curr = next;
    }
    free(L); // libera anche la struct contenitore
}


int main() {

    // Possiamo tranquillamente testare il tutto con allocazione manuale dinamica:
    lista* L1 = (lista*)malloc(sizeof(lista));
    lista* L2 = (lista*)malloc(sizeof(lista));
    if (L1 == NULL || L2 == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1); // piccolo controllo di errore
    }

    // Settiamo tutto manualmente
    L1->testa = NULL;
    L2->testa = NULL;
    // Usiamo la funzione di utility per riempire le liste un po' come ci pare
    L1->testa = mettiOrdinato(L1->testa, 1);
    L1->testa = mettiOrdinato(L1->testa, 3);
    L1->testa = mettiOrdinato(L1->testa, 5);
    L1->testa = mettiOrdinato(L1->testa, 7);
    L2->testa = mettiOrdinato(L2->testa, 2);
    L2->testa = mettiOrdinato(L2->testa, 3);
    L2->testa = mettiOrdinato(L2->testa, 4);
    L2->testa = mettiOrdinato(L2->testa, 6);
    L2->testa = mettiOrdinato(L2->testa, 8);
    lista* listaUnita = unisciListe(L1, L2);

    // Infine serve un puntatore alla testa della lista unita per stamparla
    valore* corrente = listaUnita->testa;
    printf("Lista unita: ");
    while (corrente != NULL) {
        printf("[ %d ] -> ", corrente->z);
        corrente = corrente->next;
    }
    printf("NULL\n");

    // Liberiamo la memoria allocata (non dimentichiamolo mai!)
    liberaLista(L1);
    liberaLista(L2);
    liberaLista(listaUnita);
    return 0;
}