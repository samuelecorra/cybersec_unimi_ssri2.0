# Esercizio 1.1 — Stampa della configurazione

`stampaConfig` riceve un array con una regina per colonna e visualizza:

- righe scacchistiche `8..1` dall’alto verso il basso;
- colonne `a..h` e relativi indici interni `0..7`;
- `Q` per una regina, `-` e `*` per l’alternanza delle caselle vuote.

La convenzione interna è `regine[colonna] = riga - 1`: il valore `7` indica la
riga scacchistica 8, mentre `0` indica la riga 1. L’esempio usa la configurazione
della Figura 1 del PDF, tradotta da `{8,4,1,3,6,2,7,5}` a
`{7,3,0,2,5,1,6,4}`.

La funzione stampa anche configurazioni non valide. Questo è intenzionale: la
verifica dei conflitti appartiene all’esercizio successivo.
