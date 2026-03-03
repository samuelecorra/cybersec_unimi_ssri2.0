# **Lezione 0 - Intro Unit? Didattica 2 - – Minimo albero di copertura**


In questa unità si affronta uno dei problemi fondamentali dell’informatica e della teoria dei grafi: quello del **minimo albero di copertura** (_Minimum Spanning Tree_, MST).  
L’obiettivo è **collegare tutti i nodi di un grafo** nel modo più efficiente possibile, cioè **minimizzando il costo complessivo degli archi scelti**, senza creare cicli.

Questo problema ha numerose applicazioni pratiche: dalla progettazione di **reti di comunicazione e distribuzione** (come reti telefoniche, elettriche o informatiche) all’ottimizzazione di **sistemi di trasporto e infrastrutture**.

L’unità introduce e analizza nel dettaglio l’**algoritmo di Kruskal**, una classica strategia greedy che costruisce progressivamente l’albero minimo aggiungendo, passo dopo passo, gli archi di peso minore compatibili con la struttura aciclica.  
Verranno studiati:

- il **funzionamento passo per passo** dell’algoritmo,
    
- la **valutazione della complessità computazionale**,
    
- e la **realizzazione efficiente tramite la struttura dati Mfset (Merge-Find Set)**, che consente di gestire in modo ottimale i sottoinsiemi connessi durante la costruzione dell’albero.


---
