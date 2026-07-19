# Esercizio 2.1 — Caricamento da file

`caricaConfig` legge esattamente otto interi `0..7`, separati da spazi o righe.
Il file di esempio contiene:

```text
7 3 0 2 5 1 6 4
```

La funzione restituisce `1` soltanto se apertura, contenuto e chiusura hanno avuto
successo. La lettura avviene prima in un array temporaneo: in caso di errore lo
stato del chiamante resta invariato. Valori fuori intervallo, dati insufficienti o
token aggiuntivi rendono il file non valido.

Il formato memorizza le righe interne `0..7`; per convertire una configurazione
scritta nel PDF con righe `1..8` bisogna sottrarre uno a ogni valore.
