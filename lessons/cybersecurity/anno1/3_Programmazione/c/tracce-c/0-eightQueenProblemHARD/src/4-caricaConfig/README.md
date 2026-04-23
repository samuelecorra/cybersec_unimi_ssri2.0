# 💾 Esercizio 2.1 — Funzione `carica()`

## 🎯 Obiettivo
Scrivere una funzione `carica()` che riceva in ingresso il **nome di un file di testo**, lo apra in lettura e **carichi da esso uno stato valido della scacchiera** (cioè un vettore di 8 interi che rappresentano le posizioni delle regine).

Se il file esiste, si assume che sia **strutturato correttamente**.  
Se il file non esiste o non può essere aperto, la funzione deve segnalarlo con un messaggio di errore.

---

## 🧠 Contesto
Questo esercizio segna l’inizio della **Parte 2** del progetto *Eight Queens Problem*.

Dopo aver realizzato:
- la stampa grafica della scacchiera (`stampaConfig()`),
- la verifica logica (`verifica()`),
- e la misura del costo (`euristica()`),

ora vogliamo **leggere configurazioni da file**, in modo da poterle testare e manipolare senza reinserirle manualmente ogni volta.

---

## 📄 Struttura attesa del file
Il file contiene **otto numeri interi**, separati da spazi o da ritorni a capo.  
Ogni numero rappresenta la **riga** (da 0 a 7) della regina nella rispettiva colonna.

### Esempio di file `config.txt`
```

7 4 0 3 6 1 5 2

```

oppure:
```

7
4
0
3
6
1
5
2

````

Entrambi i formati sono accettabili.

---

## ⚙️ Firma della funzione

```c
int carica(const char *nome_file, int stato[8]);
````

### Parametri:

* `nome_file` → stringa con il percorso del file da leggere
* `stato[8]` → array di interi in cui verrà salvata la configurazione letta

### Valore di ritorno:

* `1` → se il caricamento ha avuto successo
* `0` → se si è verificato un errore (file inesistente, dati insufficienti, ecc.)

---

## 🧱 Implementazione — `caricaConfig.c`

```c
#include <stdio.h>
#include <stdlib.h>

#define N 8

/**
 * Funzione carica
 * ---------------
 * Legge da file una configurazione valida di 8 regine e la memorizza nell'array passato.
 * Restituisce 1 se il caricamento ha successo, 0 in caso di errore.
 */
int carica(const char *nome_file, int stato[N]) {
    FILE *fp = fopen(nome_file, "r");
    if (fp == NULL) {
        printf("Errore: impossibile aprire il file '%s'\n", nome_file);
        return 0;
    }

    for (int i = 0; i < N; i++) {
        if (fscanf(fp, "%d", &stato[i]) != 1) {
            printf("Errore: formato non valido nel file '%s'\n", nome_file);
            fclose(fp);
            return 0;
        }
    }

    fclose(fp);
    return 1;
}

// Funzione di stampa già definita nell’esercizio 1.1
void stampaConfig(const int regine[N]);

int main(void) {
    int stato[N];
    const char *nome_file = "config.txt";

    if (carica(nome_file, stato)) {
        printf("Configurazione caricata con successo da '%s'\n\n", nome_file);
        stampaConfig(stato);
    } else {
        printf("Caricamento fallito.\n");
    }

    return 0;
}
```

---

## 📊 Esempio di output

Supponendo un file `config.txt` con il contenuto:

```
7 4 0 3 6 1 5 2
```

### Output del programma:

```
Configurazione caricata con successo da 'config.txt'

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

---

## 🧩 Discussione tecnica

* La funzione `fscanf()` legge automaticamente i numeri anche se separati da spazi o newline.
* Nessuna allocazione dinamica è necessaria.
* Il programma è *robusto ma minimale*: controlla solo la presenza e il formato base.
* Puoi facilmente estenderlo per leggere anche **N variabile**, aggiungendo un `#define` o leggendo `N` dalla prima riga del file.

---

> 💡 *“Prima di cercare la soluzione, bisogna saperla leggere.”*