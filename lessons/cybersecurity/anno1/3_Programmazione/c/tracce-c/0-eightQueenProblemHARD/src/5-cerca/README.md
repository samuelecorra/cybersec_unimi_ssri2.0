# 🔎 Esercizio 2.2 — Funzione `cerca()`

## 🎯 Obiettivo
Implementare una funzione `cerca` che, **senza modificare lo stato corrente**, valuti lo **spostamento ipotetico** di una regina in una data colonna verso una nuova riga e **restituisca il valore dell’euristica** dello stato risultante.

- Input: stato corrente, **colonna** (in notazione scacchistica `a..h`), **nuova riga** (in notazione scacchistica `1..8`).
- Output: valore dell’**euristica** dello stato ottenuto applicando *solo* quello spostamento.

> Nota: l’euristica è il **numero di coppie di regine in conflitto** (stessa riga o stessa diagonale); 0 indica una soluzione valida.

---

## ✅ Specifica
- La funzione **non deve alterare** l’array `stato[]` originale: lavora su una **copia**.
- Conversioni:
  - Colonna: `'a'..'h'` → indice `0..7`
  - Riga: `1..8` → indice `0..7`  
- Se la colonna o la riga richieste sono fuori range, la funzione restituisce un **codice di errore negativo**.

---

## ✍️ Firma consigliata

```c
int cerca(const int stato[8], char col_scacchi, int riga_scacchi);
````

* `stato[8]`: rappresentazione standard del progetto (colonna → riga, **0-based**).
* `col_scacchi`: `'a'..'h'` (maiusc/minusc accettate).
* `riga_scacchi`: `1..8`.

Per comodità è fornita anche una variante indicizzata:

```c
int cerca_idx(const int stato[8], int col_idx, int riga_idx); // 0..7, 0..7
```

---

## 🧪 Esempio (come nel PDF)

Stato corrente (notazione del PDF):
`4 3 2 5 4 3 2 3`  → (in 0-based: `{3,2,1,4,3,2,1,2}`)

La chiamata:

```
cerca(stato, 'g', 7)
```

valuta lo spostamento della regina in **colonna g** alla **riga 7** (G7).
Il valore atteso dell’euristica è **12** (come da figura del testo).

---

## 🔗 Dipendenze

La funzione usa `euristica(const int stato[8])` definita nel modulo 1.3.

---

## 🧠 Suggerimenti d’uso

* Integra con `stampaConfig()` per visualizzare lo stato **prima** e lo **stato ipotetico** dopo lo spostamento (stampando la copia), utile per il debug.
* In una fase successiva, `cerca()` è perfetta per costruire una **tabella dei costi** di tutte le mosse legali da uno stato dato (come la Figura 2 del PDF).

---

### `cerca.c` (da incollare in `src/5-cerca/cerca.c`)

```c
#include <stdio.h>
#include <ctype.h>

#define N 8

// Euristica già implementata nel modulo 1.3:
// conta il numero di coppie in conflitto (stessa riga o diagonale)
int euristica(const int stato[N]);

// ------------------------
// Helpers di conversione
// ------------------------
static int col_from_char(char c) {
    c = (char)tolower((unsigned char)c);
    if (c < 'a' || c > 'h') return -1;
    return (int)(c - 'a'); // 0..7
}

static int riga_from_scacchi(int r) {
    if (r < 1 || r > 8) return -1;
    return r - 1; // 1..8 -> 0..7
}

// ---------------------------------------------
// Variante indicizzata (0-based, 0-based)
// ---------------------------------------------
int cerca_idx(const int stato[N], int col_idx, int riga_idx) {
    if (col_idx < 0 || col_idx >= N) return -1; // colonna fuori range
    if (riga_idx < 0 || riga_idx >= N) return -2; // riga fuori range

    int copia[N];
    for (int c = 0; c < N; c++) copia[c] = stato[c];

    // spostamento ipotetico della sola regina in colonna col_idx
    copia[col_idx] = riga_idx;

    // valuta l'euristica della configurazione ipotetica
    return euristica(copia);
}

// ------------------------------------------------------
// Interfaccia richiesta dall'esercizio (a..h, 1..8)
// ------------------------------------------------------
int cerca(const int stato[N], char col_scacchi, int riga_scacchi) {
    int c = col_from_char(col_scacchi);
    if (c < 0) return -1;

    int r = riga_from_scacchi(riga_scacchi);
    if (r < 0) return -2;

    return cerca_idx(stato, c, r);
}

/* -------------------------
   Esempio di main minimale
   (decommenta per test locali)
-------------------------- */
/*
int euristica(const int s[N]) {
    int costo = 0;
    for (int i = 0; i < N; ++i)
        for (int j = i + 1; j < N; ++j) {
            if (s[i] == s[j]) costo++;
            if ((s[i] - s[j] < 0 ? -(s[i] - s[j]) : (s[i] - s[j])) ==
                (i - j < 0 ? -(i - j) : (i - j))) costo++;
        }
    return costo;
}

int main(void) {
    // Stato del PDF: 4 3 2 5 4 3 2 3  (1-based)
    int stato[N] = {3, 2, 1, 4, 3, 2, 1, 2}; // 0-based

    int h = cerca(stato, 'g', 7); // G7
    printf("euristica(G7) = %d (atteso 12)\n", h);

    // Variante indicizzata equivalente:
    // int h2 = cerca_idx(stato, 6, 6); // col g -> 6, riga 7 -> 6
    // printf("euristica_idx = %d\n", h2);

    return 0;
}
*/