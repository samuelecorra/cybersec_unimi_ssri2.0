# 🏁 Esercizio 3 — Main interattivo “N Regine”

Programma **interattivo** per cercare manualmente la soluzione al problema delle **N regine** (default: `N = 8`, definito a *compile time*).  
Usa le funzioni sviluppate nei moduli precedenti:

- `stampaConfig(const int stato[N])` — stampa scacchiera con bordi (1..8, a..h) e indici 0-based in alto/destra.
- `verifica(const int stato[N])` — numero conflitti (0 ⇒ soluzione).
- `euristica(const int stato[N])` — numero di coppie in conflitto.
- `carica(const char *file, int stato[N])` — carica stato da file.
- `cerca(const int stato[N], char col, int riga)` — valuta *senza modificare* lo stato l’euristica dopo uno spostamento ipotetico.

> Convenzione di stato: **array 0-based** `stato[c] = r` ⇒ nella **colonna `c`** (0..N-1) la regina è alla **riga `r`** (0..N-1).  
> In input da utente usiamo notazione scacchistica: **colonna `a..h`**, **riga `1..8`** (adattata a N).

---

## 🎮 Menu

All’avvio il programma chiede un numero e poi mostra il seguente menu ciclico:

```

1. Stampa lo stato corrente della scacchiera
2. Verifica (soluzione / non soluzione)
3. Calcola costo (euristica) dello stato corrente
4. Carica stato da file
5. Valuta una mossa (senza modificare lo stato)
6. Esegui una mossa (modifica lo stato)
7. Esci

>

```

### Formato mosse
- **Notazione scacchistica compatta**: `A8`, `g7`, `d3` (case-insensitive)
- **Oppure separata**: `g 7`  
Colonne valide: `a..` (fino a `a+N-1`). Righe valide: `1..N`.

---

## 🧪 Esempio di sessione

```

N-regine (N=8). Inserisci un numero qualunque per iniziare: 1

1. Stampa stato
2. Verifica soluzione
3. Costo euristico
4. Carica da file
5. Valuta mossa (senza modificare)
6. Esegui mossa
7. Esci

> 1

    0   1   2   3   4   5   6   7   ← indici colonne
  ---------------------------------
8 | - | * | Q | * | - | * | - | * | 0 ← indici righe
7 | * | - | * | - | * | Q | * | - | 1
6 | - | * | - | * | - | * | - | Q | 2
5 | * | - | * | Q | * | - | * | - | 3
4 | - | Q | - | * | - | * | - | * | 4
3 | * | - | * | - | * | - | Q | - | 5
2 | - | * | - | * | Q | * | - | * | 6
1 | Q | - | * | - | * | - | * | - | 7
  ---------------------------------
    a   b   c   d   e   f   g   h


> 2
> Stato: SOLUZIONE NON VALIDA (conflitti = 1)

> 3
> Costo euristico: 1

> 5
> Mossa da valutare (es. g7 o "g 7"): g7
> Costo ipotetico: 12 (lo stato corrente NON è stato modificato)

> 6
> Mossa da eseguire (es. a8 o "a 8"): a8
> Mossa eseguita. Nuovo stato applicato.

> 0
> Bye!

```
---

## 🧩 Validazioni & gestione errori

- Input menu: accetta solo `0..6`, ripete in caso di caratteri non numerici.
- Mossa: parsing robusto (`A8`, `a8`, `a 8`, spazi extra); rifiuta out-of-range.
- File: se `carica()` fallisce, lo stato rimane invariato.
- Il programma **non termina mai** in caso di input errati: chiede di nuovo (loop).

---

## 🛠️ Compilazione e link

Struttura tipica (adatta i path ai tuoi file):

```bash
gcc -DN=8 \
  ../1-stampaConfig/stampaConfig.c \
  ../2-verificaConfig/verificaConfig.c \
  ../3-euristica/euristica.c \
  ../4-caricaConfig/caricaConfig.c \
  ../5-cerca/cerca.c \
  main.c -o nregine
````

> Cambia `-DN=8` se vuoi altre dimensioni.

---

## 📂 File accettati da `carica()`

File di testo con **N interi 0-based** separati da spazi o newline.
Per N=8:

```
7 4 0 3 6 1 5 2
```

---

## 🔍 Note didattiche

* `verifica(stato)` può delegare a `euristica(stato)` (0 ⇒ soluzione).
* `cerca(stato, col, riga)` costruisce una **copia** e valuta il costo: perfetto per esplorazioni/euristiche.
* Il main è scritto per **N generico**: controlli su `a..a+N-1` e `1..N`.

---
