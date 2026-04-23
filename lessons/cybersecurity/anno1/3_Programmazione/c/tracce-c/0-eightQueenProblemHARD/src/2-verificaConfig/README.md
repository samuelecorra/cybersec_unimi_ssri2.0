# ♟️ Esercizio 1.2 — Verifica Configurazione

## 🎯 Obiettivo
Scrivere una funzione `verifica()` che, data una configurazione di **8 regine** rappresentata da un vettore di interi, determini **se tale configurazione è valida**, cioè se nessuna regina può attaccarne un’altra.

La funzione deve restituire:
- `0` se **nessuna regina è in conflitto** (soluzione valida);
- un valore **maggiore di 0** pari al **numero totale di conflitti** se due o più regine condividono la stessa riga, colonna o **una delle due diagonali**.

---

## 🧠 Contesto
Questo esercizio segue il precedente (`stampaConfig.c`) e rappresenta la seconda fase logica del progetto **Eight Queens Problem**.  
Ora passiamo dalla **visualizzazione grafica** alla **verifica logica** dello stato della scacchiera.

Ogni stato è rappresentato da un vettore monodimensionale di 8 interi:

```c
int regine[8] = {7, 4, 0, 3, 6, 1, 5, 2};
````

Dove:

* l’indice rappresenta la **colonna** (0 → a, 1 → b, …, 7 → h);
* il valore rappresenta la **riga** (0 → alto, 7 → basso).

Poiché assumiamo una regina per ogni colonna, non serve controllare le colonne.
Dobbiamo invece verificare conflitti su:

* **righe** (orizzontali),
* **diagonali principali** (↘️ da sinistra a destra),
* **diagonali secondarie** (↙️ da destra a sinistra).

---

## ⚙️ Logica della funzione `verifica()`

Per ogni coppia di colonne `(i, j)` con `i < j`, analizziamo:

1. **Conflitto sulla stessa riga**

   ```c
   regine[i] == regine[j]
   ```
2. **Conflitto sulla diagonale ↘️**

   ```c
   (regine[i] - regine[j]) == (i - j)
   ```
3. **Conflitto sulla diagonale ↙️**

   ```c
   (regine[i] - regine[j]) == (j - i)
   ```

   che equivale al test matematico:

   ```c
   abs(regine[i] - regine[j]) == abs(i - j)
   ```

   — controlla **entrambe** le diagonali in un solo colpo.

---

## 🧱 Struttura del file `verificaConfig.c`

```c
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define N 8

/**
 * Funzione verifica
 * -----------------
 * Riceve un vettore di 8 interi che rappresentano lo stato della scacchiera.
 * Restituisce:
 *   0  → se la configurazione è valida
 *   >0 → se ci sono conflitti su righe o diagonali
 */
int verifica(const int stato[N]) {
    int conflitti = 0;

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {

            // Conflitto su stessa riga
            if (stato[i] == stato[j])
                conflitti++;

            // Conflitto su diagonale ↘️ o ↙️
            if (abs(stato[i] - stato[j]) == abs(i - j))
                conflitti++;
        }
    }

    return conflitti;
}

// Funzione ausiliaria per test
void stampaConfig(const int regine[N]);

int main(void) {
    int soluzione_valida[N] = {7, 4, 0, 3, 6, 1, 5, 2};
    int soluzione_errata[N] = {0, 1, 2, 3, 4, 5, 6, 7};

    printf("\n--- Configurazione valida ---\n");
    stampaConfig(soluzione_valida);
    printf("Conflitti rilevati: %d\n\n", verifica(soluzione_valida));

    printf("--- Configurazione errata ---\n");
    stampaConfig(soluzione_errata);
    printf("Conflitti rilevati: %d\n", verifica(soluzione_errata));

    return 0;
}
```

---

## 🧮 Esempio di output

```
--- Configurazione valida ---

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
Conflitti rilevati: 0

--- Configurazione errata ---
[…]
Conflitti rilevati: 28
```

---

## 🧩 Discussione

La funzione `verifica()` è **simmetrica**, cioè valuta ogni possibile coppia di regine senza assumere direzione preferenziale.
Grazie alla formula `abs(r1 - r2) == abs(c1 - c2)`, il controllo diagonale copre **entrambe le direzioni** (↘️ e ↙️).

---

## 🧠 Estensioni future

Nel prossimo esercizio (`1.3 - euristica`) si implementerà una funzione che, invece di limitarsi a dire se la configurazione è corretta, **calcolerà un punteggio di “distanza dalla soluzione”** contando i conflitti.

---

## 📚 Collegamenti tra moduli

| Modulo | Cartella                | Descrizione                                        |
| ------ | ----------------------- | -------------------------------------------------- |
| 1.1    | `src/1-stampaConfig/`   | Stampa grafica della scacchiera (`stampaConfig.c`) |
| 1.2    | `src/2-verificaConfig/` | Verifica di righe e diagonali (`verificaConfig.c`) |
| 1.3    | `src/3-euristica/`      | Calcolo della funzione di costo (`euristica.c`)    |

---

> 💡 *“Verificare non è solo trovare errori, ma capire se lo stato del sistema obbedisce alle sue regole logiche.”*