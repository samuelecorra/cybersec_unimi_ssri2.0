///////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Secondo esercizio di Programmazione C dell'esame di LABORATORIO DI PROGRAMMAZIONE del 16/07/2025

// CONSEGNA:
// Scrivere un programma C che sia in grado di definire una matrice quadrata di dimensione N qualsiasi,
// contenente unicamente numeri interi.

// Il programma crea poi una seconda matrice quadrata di dimensione N, nella quale il valore di ciascun singolo
// elemento è il massimo tra gli otto valori adiacenti (orizzontali, verticali e diagonali) che lo circondano
// nella matrice originale, elemento originale escluso. 

// Il programma richiede poi, giustamente, un metodo di stampa per matrice originale e matrice trasformata,
// per sincerarsi dell'avvenuto funzionamento dell'algoritmo.

// Esempio di matrice originale:

// 4 5 6 7
// 8 2 4 5
// 6 7 8 1
// 2 8 4 6

// Esempio di matrice trasformata:

// 8 8 7 6
// 7 8 8 8
// 8 8 8 8
// 8 8 8 8

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CI SONO DUE VERSIONI DI QUESTO FILE: mainV1.c E mainV2.c

// PENSATA PER C17
// La prima versione (mainV1.c) è più semplice e diretta, ma meno efficiente in termini di memoria.
// E' stata volutamente riempita di commenti esplicativi per facilitare la comprensione, sebbene
// possano sembrare ridondanti e prolissi.

// PENSATA PER GNU11 ERGO CONSENTE VLA PASSATO ALLA FUNZIONE
// La seconda versione (mainV2.c) è più compatta ed efficiente, ma meno didattica. Si consiglia di studiare prima
// la versione mainV1.c per comprendere appieno la logica dietro l'implementazione, prima di passare alla versione
// mainV2.c, che richiede uno sforzo mentale maggiore.