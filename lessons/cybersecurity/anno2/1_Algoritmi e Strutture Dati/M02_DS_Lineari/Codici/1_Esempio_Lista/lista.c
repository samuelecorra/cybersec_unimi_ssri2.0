#include <stdio.h>
#include <stdbool.h>

typedef struct _cella {
  int elem;
  struct _cella *next, *prev;
} cella;

typedef cella posizione, lista;

// Varie operazioni sulla lista così definita:

// Funzione per inserire una nuova cella tra la cella di posizione p e la precedente
void inslista(int a, posizione *p) {

  // Innanzitutto serve creare una nuova cella per il dato passato:
  posizione *q;
  q = malloc(sizeof(cella));

  q->elem = a; // popolo la cella col dato passato
  
  q->prev = p->prev; // il precedente di q sarà il precedente di p
  
  q->next = p;      // il successivo di q sarà p
  
  p->prev->next = q; // il successivo del precedente di p sarà q
  
  p->prev = q; // il precedente di p sarà q
}

