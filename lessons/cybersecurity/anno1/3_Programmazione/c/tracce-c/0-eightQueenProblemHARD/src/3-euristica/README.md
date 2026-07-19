# Esercizio 1.3 — Funzione euristica

L’euristica richiesta dal PDF è il numero di coppie di regine in conflitto per
riga o diagonale. Con otto regine il costo appartiene all’intervallo `0..28`,
perché esistono $\binom{8}{2}=28$ coppie.

```c
if (stato[i] == stato[j]
    || abs(stato[i] - stato[j]) == j - i) {
    ++costo;
}
```

- `0`: soluzione valida;
- `28`: tutte le regine sulla stessa riga oppure sulla stessa diagonale;
- valori intermedi: numero esatto di coppie in conflitto.

Il costo non è il numero minimo di mosse necessario per arrivare a una soluzione:
è una misura locale utile per confrontare stati. La Figura 2 del PDF mostra il
costo di ogni stato ottenibile con una singola mossa da una configurazione data.
