ESERCIZIO: SOMMA DELLE DIAGONALI PASSANTI PER OGNI CELLA

Per ogni cella (i,j), costruire il valore dato dalla somma degli elementi sulle
due diagonali che la attraversano, escludendo la cella stessa.

Sulle diagonali discendenti è costante la differenza `riga-colonna`; sulle
ascendenti è costante la somma `riga+colonna`. Non si tratta di un rapporto.

mainV1.c confronta ogni cella con tutte le altre: tempo O(N^4).
mainV2.c pre-calcola le 2N-1 somme per ciascun verso: tempo e memoria O(N^2).
Nella ricostruzione sottrae due volte la cella corrente perché compare in entrambe
le somme diagonali.
