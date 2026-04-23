/* ==========================================================================================

ESERCIZIO DI LABORATORIO DI PROGRAMMAZIONE [NON RICORDO LA DATA]

Data una matrice quadrata NxN di interi (dimensione dunque nota a priori, niente malloc),
scrivere un programma che costruisce una nuova matrice M2 tale che ciascun elemento 
M2[i][j] sia uguale alla somma dei valori della diagonale/delle diagonali passante/i per [i][j] 
escluso l’elemento stesso.

==============================================================================================*/

// SONO STATE REALIZZATE DUE VERSIONI DI QUESTO ESERCIZIO:

1. mainV1.c: versione inefficiente ma di facile comprensione, con complessità O(N^4).
2. mainV2.c: versione efficiente con complessità O(N^2), che sfrutta il calcolo
   preliminare delle somme delle diagonali ma che è più complessa da comprendere e quindi
   in sede d'esame difficilmente verrebbe subito in mente, in quanto comporta competenze e
   riflessioni matematiche non banali!

==============================================================================================*/