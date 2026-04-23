# 📘 Teoria C — Moduli e Appunti

Raccolta organica e super esaustiva degli **appunti teorici sul linguaggio C**, 
pensata per precedere le *tracce pratiche* presenti in `c/tracce-c`.  
Qui trovi moduli brevi e mirati: dalla sintassi base fino a puntatori a funzione e strutture dati dinamiche.

> **FONTE:** Corso completo su youtube di BroCode https://youtu.be/xND0t1pr3KY?si=7EU-3Fm5tXQYffV3.

> **Target:** studio universitario con occhio agli esercizi d’esame e al codice “da laboratorio”.

---

## 🗂️ Struttura dei moduli

- **[M1_Fondamenti](./M1_Fondamenti/)**  
  Sintassi di base, tipi primitivi, `printf/scanf`, operatori aritmetici e relazionali, promozioni di tipo.

- **[M2_Matematica](./M2_Matematica/)**  
  Operatori e precedenze, overflow/underflow, floating point e cenni a `math.h`.

- **[M3_Condizioni_&_Logica](./M3_Condizioni_&_Logica/)**  
  `if/else`, `switch`, operatori logici, *short-circuit*, verità in C (qualsiasi valore ≠0 è *true*).

- **[M4_Funzioni](./M4_Funzioni/)**  
  Prototipi, *scope* e durata delle variabili, passaggio per valore, ricorsione, separazione `.h/.c`.

- **[M5_Cicli](./M5_Cicli/)**  
  `for/while/do-while`, `break/continue`, pattern idiomatici, cenni di complessità sui loop annidati.

- **[M6_Arrays](./M6_Arrays/)**  
  Array mono/bi-dimensionali, passaggio a funzione (decadimento a puntatore), basi delle stringhe C.

- **[M7_C_avanzato](./M7_C_avanzato/)**  
  Ternary operator, typedef, enumerations, combinazioni dei precedenti...

- **[M8_Strutture](./M8_Strutture/)**  
  `struct`, `typedef`, array di struct, struct annidate, passaggio per riferimento simulato.

- **[M9_Puntatori](./M9_Puntatori/)**  
  Aritmetica dei puntatori, `*`/`&`, puntatori a `const`, doppi puntatori, stringhe come `char*`.

- **[M10_File_Handling](./M10_File_Handling/)**  
  `FILE*`, `fopen` (modalità testo/bin), `fgets/fscanf`, `fwrite/fread`, gestione errori ed EOF.

- **[M11_Allocazione_Dinamica](./M11_Allocazione_Dinamica/)**  
  `malloc/calloc/realloc/free`, *ownership*, *leak* e *dangling pointer*, pattern di inizializzazione.

- **[M12_IMPORTANTE](./M12_IMPORTANTE/)**  
  Focus su due temi chiave:
  - **[1_Puntatori_a_funzione](./M12_IMPORTANTE/1_Puntatori_a_funzione/)** — firme, callback, `qsort`, FSM/dispatch table.
  - **[2_Liste_dinamiche](./M12_IMPORTANTE/2_Liste_dinamiche/)** — liste semplici/doppie, inserimento ordinato, rimozione, ricerca, *sentinel*.

---

## 🔗 Come usarla con le tracce

- Leggi il modulo teorico corrispondente, poi passa alla cartella pratica in `../tracce-c/…`.  
  Esempio: **M10_File_Handling** → `tracce-c/3-esercizi-file/`.

---

## 🛠️ Compilazione rapida (GCC)
SI CONSIGLIA CODERUNNER, ESTENSIONE DI VSCODE SUPERCOMODA CHE PERMETTE DEBUG IN TERMINALE CON UN SINGOLO CLICK!

ALTRIMENTI:

```bash
# file singolo
gcc -std=c17 -Wall -Wextra -O2 main.c -o main

# progetto con header
gcc -std=c17 -Wall -Wextra -O2 *.c -o app