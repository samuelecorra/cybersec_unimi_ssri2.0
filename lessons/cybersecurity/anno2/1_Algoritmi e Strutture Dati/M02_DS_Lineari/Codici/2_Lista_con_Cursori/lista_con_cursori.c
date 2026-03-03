#include <stdio.h>

#define MAXL 10
typedef int lista, posizione;

typedef struct _cella {
  posizione prev, next;
  int elemento;
} cella;

lista listalibera;
cella spazio[MAXL];

void inizializza() {
  int i; // indice di scorrimento
  listalibera = 0; // la lista libera inizia dalla cella 0
  spazio[0].next = 1; // il successivo di 0 è 1
  spazio[0].prev = MAXL - 1; // il precedente di 0 è l'ultimo elemento
  for (i = 1; i < MAXL; i++) { // per ogni cella dello spazio 
    // che non sia la prima o l'ultima, otteniamo:
    spazio[i].next = (i + 1) % MAXL; // il successivo è (i+1) mod MAXL. Il modulo serve
    // per far sì che l'ultimo elemento punti a 0 e che dunque non ci siano né buchi
    // né sforamenti dell'array.
    spazio[i].prev = (i - 1) % MAXL; // Viceversa per il precedente
  }
}

// Ora vogliamo definire la funzione che sposta la cella in posizione p
// prima della cella in posizione q.
void sposta(posizione *p, posizione *q) {
  posizione t; // variabile temporanea per salvare il successivo di p
  t = spazio[*p].next;

  spazio[spazio[*p].prev].next = spazio[*p].next;
  spazio[spazio[*p].next].prev = spazio[*p].prev;
  spazio[*p].prev = spazio[*q].prev;
  spazio[spazio[*q].prev].next = *p;
  spazio[*p].next = *q;
  spazio[*q].prev = *p;
  *q = *p;
  *p = t;
}