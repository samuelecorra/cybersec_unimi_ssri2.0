# **Lezione 0 - Intro Unità Didattica 1 - Transazioni**

Questa unità didattica introduce il concetto di **transazione**, elemento centrale nella gestione dell’affidabilità e coerenza dei dati in un sistema di basi di dati. Una transazione rappresenta una **sequenza logica di operazioni** che il DBMS deve eseguire come un’unica unità atomica: o vengono completate tutte, o nessuna viene applicata.

L’obiettivo è comprendere le **proprietà fondamentali** che caratterizzano le transazioni, riassunte nell’acronimo **ACID** (Atomicità, Coerenza, Isolamento, Durabilità), e analizzare come il DBMS le garantisce in ogni circostanza, anche in presenza di **errori o concorrenza tra processi**.

Viene inoltre presentata l’**architettura interna** dei moduli del DBMS coinvolti nella gestione delle transazioni, mostrando come essi cooperino per assicurare il corretto svolgimento delle operazioni e il mantenimento dell’integrità della base di dati.