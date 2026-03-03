# **Lezione 0 - Intro Unit? Didattica 1 - – Schema generale**


In questa unità viene introdotto lo **schema generale della tecnica di programmazione dinamica**, evidenziandone la struttura logica, le componenti fondamentali e le differenze rispetto alla tecnica **greedy**.

Un **algoritmo di programmazione dinamica** si basa sull’idea di **suddividere un problema complesso in sottoproblemi più piccoli**, risolverli una sola volta e **memorizzarne i risultati** per poterli riutilizzare.  
Questo approccio consente di evitare la ripetizione di calcoli identici, migliorando drasticamente l’efficienza rispetto a una semplice soluzione ricorsiva.

Lo schema generale prevede tre fasi principali:

1. **Definizione dei sottoproblemi** – individuare le sottoistanze del problema che, una volta risolte, possono essere combinate per ottenere la soluzione globale.
    
2. **Relazione di ricorrenza** – stabilire la formula che collega la soluzione di un problema alle soluzioni dei suoi sottoproblemi.
    
3. **Strategia di calcolo e memorizzazione** – scegliere se risolvere in modo **bottom-up** (costruendo una tabella progressivamente) o **top-down** (ricorsione con _memoization_).
    

La **programmazione dinamica** si distingue dalla **tecnica greedy** per un aspetto cruciale:  
mentre un algoritmo greedy **sceglie localmente la soluzione migliore** sperando che porti alla soluzione ottima globale, un algoritmo di programmazione dinamica **esamina sistematicamente tutte le possibilità**, sfruttando le relazioni di dipendenza tra sottoproblemi per **garantire l’ottimalità** della soluzione finale.

In sintesi, questa unità introduce i **principi costitutivi** della programmazione dinamica e prepara alla loro applicazione concreta in problemi classici come lo **zaino (knapsack)**, i **cammini minimi** e le **sequenze ottimali**, mostrando come la “memoria” dei calcoli diventi lo strumento fondamentale per trasformare la ricorsione in efficienza.


---

