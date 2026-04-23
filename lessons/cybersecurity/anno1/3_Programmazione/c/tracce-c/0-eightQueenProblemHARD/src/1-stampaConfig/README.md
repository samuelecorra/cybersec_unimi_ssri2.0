
## Stampa della scacchiera (`stampaConfig`)
La funzione `stampaConfig` riceve il vettore `regine[8]` e stampa la scacchiera seguendo le regole:

1. Numeri **1–8** a sinistra (righe “reali” come nel gioco).  
2. Lettere **a–h** in basso (colonne “reali”).  
3. Numeri **0–7** in alto e a destra (indici della matrice in C).  
4. **Q** per regine, **-** per caselle bianche, **\*** per caselle nere.  
5. L’angolo **in alto a sinistra è bianco** (`-`).  

Colore della casella `(r, c)`:
```c
if ((r + c) % 2 == 0)  // bianca
````

---

## Esempio di configurazione

Configurazione:

```
[7, 4, 0, 3, 6, 1, 5, 2]
```

### Output atteso (con indici 0-based in alto e a destra)

```

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
```

> **Legenda:**
>
> * Bordo sinistro → numeri classici 1–8 (righe scacchiera).
> * Bordo inferiore → lettere a–h (colonne scacchiera).
> * Bordo superiore e destro → indici 0–7 (coordinate matriciali).

### LA FUNZIONE STAMPA UNA COMBINAZIONE QUALSIASI SENZA VERIFICARLA! SRP!
(Single Responsibility Principle)

---
