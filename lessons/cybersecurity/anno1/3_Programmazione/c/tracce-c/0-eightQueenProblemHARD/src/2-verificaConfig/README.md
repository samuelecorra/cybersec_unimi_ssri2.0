# Esercizio 1.2 — Verifica della configurazione

`verifica` esamina ogni coppia di colonne `(i,j)` con `i < j`. Le rispettive
regine sono in conflitto quando:

```c
stato[i] == stato[j]
```

oppure:

```c
abs(stato[i] - stato[j]) == j - i
```

La prima condizione rileva la stessa riga; la seconda entrambe le diagonali. Le
colonne non vanno controllate perché la rappresentazione contiene esattamente una
regina per ogni colonna. Ogni coppia viene contata una sola volta.

Il risultato è `0` per una soluzione e maggiore di zero altrimenti. In questa
implementazione coincide con il costo dell’esercizio 1.3; una versione minimale
di `verifica` potrebbe quindi limitarsi a chiamare `euristica`.
