# 📘 Teoria C — Moduli e Appunti

Raccolta organica e super esaustiva degli **appunti teorici sul linguaggio C**, 
pensata per precedere le *tracce pratiche* presenti in `c/tracce-c`.  
Qui trovi moduli brevi e mirati: dalla sintassi base fino a puntatori a funzione e strutture dati dinamiche.

> **FONTE:** Corso completo su youtube di BroCode https://youtu.be/xND0t1pr3KY?si=7EU-3Fm5tXQYffV3.

> **Target:** studio universitario con occhio agli esercizi d’esame e al codice “da laboratorio”.

---

## 🗂️ Struttura dei moduli

- **[M01_Fondamenti](./M01_Fondamenti/)**
  Struttura del programma, tipi fondamentali, `printf`/`scanf`, conversioni, overflow,
  operatori aritmetici e bitwise, input testuale e primi progetti.

- **[M02_Matematica](./M02_Matematica/)**
  Funzioni di `math.h`, geometria, interesse semplice e composto, `rand`/`srand` e relativi limiti.

- **[M03_Condizioni_&_Logica](./M03_Condizioni_&_Logica/)**
  `if`/`else`, confronti tra stringhe, `switch`, condizioni annidate e operatori logici.

- **[M04_Funzioni](./M04_Funzioni/)**
  Dichiarazioni, definizioni e chiamate, `return`, passaggio per valore, ricorsione e scope.

- **[M05_Cicli](./M05_Cicli/)**
  `for`/`while`/`do-while`, `break`/`continue`, cicli annidati e piccoli programmi interattivi.

- **[M06_Arrays](./M06_Arrays/)**
  Array mono e bidimensionali, stringhe, array di stringhe, input e passaggio degli array alle funzioni.

- **[M07_C_avanzato](./M07_C_avanzato/)**
  Operatore condizionale, `typedef`, enumerazioni, `const`, durata e visibilità,
  preprocessore, macro e include guard.

- **[M08_Strutture](./M08_Strutture/)**
  `struct`, inizializzatori designati, array di struct, passaggio tramite puntatore,
  padding e tagged union.

- **[M09_Puntatori](./M09_Puntatori/)**
  Operatori `*`/`&`, relazione tra array e puntatori, aritmetica entro i limiti,
  puntatori multipli e parametri di uscita.

- **[M10_File_Handling](./M10_File_Handling/)**  
  `FILE *`, modalità di `fopen`, lettura e scrittura testuale, EOF ed errori,
  I/O binario e posizionamento con `fseek`.

- **[M11_Allocazione_Dinamica](./M11_Allocazione_Dinamica/)**  
  `malloc/calloc/realloc/free`, *ownership*, *leak* e *dangling pointer*, pattern di inizializzazione.

- **[M12_IMPORTANTE](./M12_IMPORTANTE/)**  
  Focus su due temi chiave:
  - **[1_Puntatori_a_funzione](./M12_IMPORTANTE/1_Puntatori_a_funzione/)** — firme, alias e callback selezionabili.
  - **[2_Liste_dinamiche](./M12_IMPORTANTE/2_Liste_dinamiche/)** — lista semplicemente concatenata, inserimento, rimozione e distruzione sicura.

---

## 🔗 Come usarla con le tracce

- Leggi il modulo teorico corrispondente, poi passa alla cartella pratica in `../tracce-c/…`.  
  Esempio: **M10_File_Handling** → `tracce-c/3-esercizi-file/`.

---

## 🛠️ Compilazione rapida (GCC)

Gli esempi che includono `windows.h` richiedono Windows e un ambiente GCC compatibile,
come MinGW-w64. Code Runner può avviare rapidamente il comando configurato in VS Code;
non sostituisce il debugger né il controllo dei warning del compilatore.

```bash
# file singolo
gcc -std=c17 -Wall -Wextra -Wpedantic -O2 main.c -o main

# progetto con header
gcc -std=c17 -Wall -Wextra -Wpedantic -O2 *.c -o app
```

Su sistemi Unix-like, gli esempi che usano `math.h` possono richiedere `-lm` in fase
di linking. Il controllo sintattico più severo usato nella revisione è:

```bash
gcc -std=c17 -Wall -Wextra -Wpedantic -Wformat=2 -Wshadow -Wconversion -fsyntax-only main.c
```
