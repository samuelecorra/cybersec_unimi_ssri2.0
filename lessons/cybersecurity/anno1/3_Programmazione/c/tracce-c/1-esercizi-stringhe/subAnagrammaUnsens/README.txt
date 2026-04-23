// ESERCIZIO SULLA GESTIONE DI STRINGHE: SUB-ANAGRAMMI (VERSIONE CASE-UNSENSITIVE)

// Siano date due stringhe ALFABETICHE s1 e s2, modificabili direttamente nel main per comodità di test,
// e dunque per garantire che funzioni tutto a prescindere dalle due stringhe scelte.

// Si scriva una funzione che determini se s1 è un sub-anagramma di s2, ovvero se tutti i caratteri
// della prima compaiono nella seconda con almeno la stessa frequenza.

// Non è tassativo implementare un controllo che verifichi che le stringhe contengano solo caratteri alfabetici,
// ma si presuppone che sia così. FACOLTATIVO: si implementi tale controllo.

// La corrispondenza è case-unsensitive, ovvero non fa differenza tra maiuscole e minuscole.
// Si veda poi anche la versione case-sensitive dello stesso esercizio.

// CI SONO DUE VERSIONI DI QUESTO ESERCIZIO:

// 1) mainV1.c: versione base, non ottimizzata ma funzionante.
// 2) mainV2.c: versione migliorata, con alcune ottimizzazioni e accorgimenti vari.

// La seconda versione è più efficiente e più elegante, ergo meglio studiarsela al meglio!
// Contiene un espediente mediante bitmasking per valutare se sono anagrammi veri e propri.