# **Lezione 0 - Intro Unit? Didattica 1 - – Schema generale**


In questa unità viene presentato lo **schema generale della tecnica di progetto Backtrack**, cioè la struttura logica e i principi comuni a tutti gli algoritmi che utilizzano la **ricerca con ritorno** per esplorare soluzioni possibili.

L’obiettivo è **individuare le componenti fondamentali** che costituiscono un algoritmo di tipo backtracking e comprenderne il ruolo nel processo di costruzione della soluzione.

Un algoritmo backtrack si basa su un’idea semplice ma potente:

> costruire la soluzione passo dopo passo, esplorando ogni scelta possibile,  
> e tornare indietro (backtrack) quando una scelta porta a una situazione non valida.

Lo schema generale prevede quindi:

1. **una rappresentazione dello stato corrente** del problema;
    
2. **un insieme di scelte possibili** a partire da quello stato;
    
3. **un test di ammissibilità**, per verificare se la scelta è valida;
    
4. **un test di completamento**, per stabilire se una soluzione è stata trovata;
    
5. e, se necessario, il **ritorno al passo precedente** per esplorare alternative.
    

Questa unità fornisce dunque la base concettuale per comprendere **come funziona la ricerca con ritorno** e come tale schema possa essere adattato a problemi di natura diversa, da quelli combinatori (come le permutazioni) a quelli più complessi (come il problema delle otto regine o il Sudoku).

---

