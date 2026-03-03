# **Lezione 0 - Intro Unit? Didattica 1 - – Problemi facili e difficili: strumenti di analisi**


In questa prima unità entriamo nel cuore della **teoria della complessità computazionale**, affrontando la distinzione tra **problemi facili** e **problemi difficili** in base al **tempo necessario** per calcolare una soluzione.

Un **problema facile** è uno che può essere risolto da un algoritmo **deterministico** in **tempo polinomiale**, ossia con una crescita dei tempi di esecuzione “ragionevole” rispetto alla dimensione dell’input.  
Al contrario, un **problema difficile** richiede un tempo **esponenziale** o superiore, e quindi diventa impraticabile anche per istanze di dimensioni moderate.

Per analizzare e classificare tali problemi, verranno introdotti alcuni concetti fondamentali:

- Il **certificato polinomiale** di una soluzione, che permette di **verificare rapidamente** la correttezza di una risposta proposta, anche se non è facile trovarla.
    
- L’idea di **oracolo**, un dispositivo teorico capace di fornire istantaneamente la risposta a un sottoproblema, utile per modellare processi di calcolo ideali o non deterministici.
    
- Il concetto di **algoritmo non deterministico**, che rappresenta un modello teorico in cui il calcolatore può “scegliere” simultaneamente tra più strade possibili, trovando una soluzione in tempo polinomiale se ne esiste almeno una.
    

Infine, impareremo a **tradurre un algoritmo non deterministico in un algoritmo enumerativo**, cioè una procedura deterministica che esplora tutte le possibilità, e a comprendere la differenza tra problemi facili e difficili **attraverso l’albero di enumerazione**, che rappresenta graficamente lo spazio di ricerca.

Questa unità fornisce dunque gli **strumenti concettuali fondamentali** per comprendere come si misura la difficoltà computazionale di un problema, e prepara alle unità successive, dedicate alla classificazione formale delle classi **P**, **NP** e **NP-complete**.


---

