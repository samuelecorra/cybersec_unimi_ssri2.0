# Esercizio 2.2 — Valutazione di una mossa

`cerca` valuta lo spostamento ipotetico di una regina senza modificare lo stato
originale. La funzione copia l’array, cambia una sola posizione e calcola
l’euristica della copia.

```c
int cerca(const int stato[8], char colonna, int rigaScacchistica);
```

- colonne `a..h`, senza distinzione tra maiuscole e minuscole;
- righe scacchistiche `1..8`, convertite internamente in `0..7`;
- `-1` per colonna non valida, `-2` per riga non valida, `-3` per stato nullo.

Esempio tratto dal PDF: lo stato 1-based `4 3 2 5 4 3 2 3` diventa
`{3,2,1,4,3,2,1,2}`. `cerca(stato, 'g', 7)` colloca temporaneamente la
regina della colonna g alla riga interna 6 e restituisce il costo atteso `12`.
