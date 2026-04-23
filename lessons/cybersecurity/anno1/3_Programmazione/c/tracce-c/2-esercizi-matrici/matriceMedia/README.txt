/*------------------------------------------------------------------------------------

ESERCIZIO:

Scrivere un programma che acquisisce da tastiera i valori di una matrice 
quadrata di dimensione N, tutti numeri interi. Il programma crea un nuova 
matrice in cui il valore di ciascun elemento corrisponde alla media dei 
valori sopra/sotto/sx/dx rispetto all'elemento considerato nella matrice
originale (escluso quindi il valore dell'elemento stesso).
Al termine, il programma visualizza le due matrici affiancate.

ESEMPIO

Ingresso:                   Uscita:

4 5 6 7                     5.00 4.80 4.60 5.00
8 2 4 5                     4.80 6.00 5.12 5.20
6 7 8 1                     5.40 5.25 4.62 5.40
2 8 4 6                     7.00 5.40 6.00 4.33

------------------------------------------------------------------------------------*/

SONO PRESENTI DUE VERSIONI:

1) mainV1.c è prolissa di commenti, più "didattica"
2) mainV2.c è la versione ottimizzata, che vede una padronanza maggiore del linguaggio

// ENTRAMBE UTILIZZANO #DEFINE N PER NON INCAPPARE IN PROBLEMI DI PASSAGGIO ALLA FUNZIONE
// IN BASE AL COMPILER/STANDARD DEL C USATO!