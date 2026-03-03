# **Intro Modulo 6**

Questo modulo approfondisce uno degli aspetti fondamentali dei sistemi di gestione delle basi di dati: **la gestione delle transazioni**. Una transazione rappresenta un’unità logica di lavoro che deve essere eseguita in modo **affidabile**, **isolato** e **coerente**, anche in presenza di guasti o accessi concorrenti.

L’obiettivo principale è comprendere come il DBMS garantisca queste proprietà attraverso i principi **ACID** (Atomicità, Coerenza, Isolamento, Durabilità) e come gestisca le **operazioni concorrenti** mantenendo la correttezza dei dati.

Viene introdotta la **teoria della serializzabilità**, che permette di stabilire se un insieme di transazioni concorrenti produce un risultato equivalente a un’esecuzione seriale. Si studiano poi le diverse **tecniche di controllo della concorrenza**, tra cui il **locking**, i **timestamp** e i **multiversion concurrency control (MVCC)**, analizzando per ciascuna i vantaggi e le criticità.

Infine, si affrontano i **meccanismi di recupero (recovery)** che assicurano l’affidabilità del sistema in caso di errori o crash, comprendendo come vengono organizzati i **log di sistema** e come il DBMS riesca a **ripristinare lo stato coerente** della base di dati.