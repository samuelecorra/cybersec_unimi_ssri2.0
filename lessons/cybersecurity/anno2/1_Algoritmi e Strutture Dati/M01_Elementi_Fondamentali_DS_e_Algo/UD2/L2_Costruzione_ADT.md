## **Lezione 2 – Costruzione di un tipo di dato**

### **1. Introduzione**

Questa lezione rappresenta un esercizio completo di costruzione di un **tipo di dato astratto**, partendo dalla sua definizione logica fino alla sua implementazione concreta.

L’obiettivo è imparare a:

- descrivere un tipo di dato attraverso la **specifica sintattica**,
    
- definirne il comportamento mediante la **specifica semantica**,
    
- e infine **realizzarlo** nel linguaggio C, traducendo la teoria in pratica.
    

---

### **2. Obiettivo dell’esercitazione**

Si vuole costruire un **tipo di dato astratto** chiamato **mapping** (_memoria associativa_), che rappresenta una funzione tra due insiemi:

- un **dominio** $D$,
    
- e un **codominio** $C$.
    

Formalmente, un mapping è una **funzione parziale** che associa elementi di $D$ a elementi di $C$.

$$M: D \rightarrow C$$

Tuttavia, non è detto che ogni elemento di $D$ abbia un valore associato in $C$ — da qui il termine “funzione parziale”.

---

### **3. Descrizione intuitiva del mapping**

Immagina una **tabella** in cui a ogni “chiave” (cioè un elemento del dominio) può corrispondere un “valore” (cioè un elemento del codominio).

|Chiave (D)|Valore (C)|
|---|---|
|1|7|
|2|9|
|3|indefinito|

Questo tipo di struttura è alla base di moltissime applicazioni informatiche:

- tabelle di simboli nei compilatori,
    
- dizionari,
    
- cache associative,
    
- database chiave–valore,
    
- array associativi nei linguaggi moderni (come in Python o JavaScript).
    

---

### **4. Operazioni fondamentali del mapping**

Il mapping deve consentire tre operazioni principali:

1. **Creazione** di una mappatura vuota
    
2. **Assegnamento** di un valore a una chiave
    
3. **Calcolo** del valore associato a una chiave
    

### In breve:

|Operazione|Descrizione|Output|
|---|---|---|
|`crea()`|Inizializza una mappa vuota|Mapping vuoto Λ|
|`assegna(M, d, c)`|Associa alla chiave `d` il valore `c`|Nuovo mapping aggiornato|
|`calcola(M, d)`|Restituisce il valore associato a `d` (se esiste)|Coppia (valore, booleano)|

---

### **5. Specifica sintattica**

La **specifica sintattica** descrive i _nomi dei tipi di dato_ e le _operazioni disponibili_.

#### **Tipi e costanti coinvolti**

- `mapping`
    
- `dominio`
    
- `codominio`
    
- `booleano`
    
- `Λ` → mapping vuoto (Lambda)
    

#### **Operazioni specifiche**

- `crea() → mapping`
    
- `assegna(mapping, dominio, codominio) → mapping`
    
- `calcola(mapping, dominio) → codominio × booleano`
    

---

### **6. Specifica semantica**

La **specifica semantica** definisce il comportamento logico delle operazioni.

---

#### **Operatore `crea()`**

`crea() = M 
`pre: nessuna 
`post: M = Λ`

> Significa che la chiamata a `crea()` restituisce un mapping vuoto, in cui nessuna associazione è ancora definita.

---

#### **Operatore `assegna(M, d, c)`**

`assegna(M, d, c) = M’ 
`pre: nessuna 
`post: M’(d) = c   e M’(x) = M(x)   per ogni x ≠ d`

> L’operazione assegna a `d` il valore `c`, lasciando invariati tutti gli altri elementi già mappati.

---

#### **Operatore `calcola(M, d)`**

`calcola(M, d) = (c, b) 
`pre: nessuna 
`post:    - se M(d) è indefinito → b = falso   
		`- se M(d) è definito   → c = M(d), b = vero`

> L’operazione restituisce il valore associato a `d`, insieme a un booleano che indica se l’associazione esiste o meno.

---

### **7. Realizzazione in linguaggio C**

#### **Ipotesi di lavoro**

- Sia il dominio $D$ che il codominio $C$ sono rappresentati come **interi**.
    
- Il mapping sarà rappresentato come **vettore di interi**, in cui ogni indice rappresenta una chiave.
    

---

#### **Costanti e definizioni**

`#define UNDEFINED 0 
`#define DIM 100 
`#define TRUE 1 
`#define FALSE 0  
`typedef short boolean; 
`typedef int mapping; 
`mapping *M;`

> `UNDEFINED` rappresenta una posizione del mapping non ancora valorizzata.  
> `DIM` è la dimensione massima del dominio.

---
#### **Codice globale**

```c

#include <stdio.h>

#define UNDEFINED 0
#define DIM 100 // massima dimensione del dominio
#define TRUE 1
#define FALSE 0  
typedef short boolean;
typedef int mapping;
mapping *M;  

void crea(mapping *M) {
    int i;
    M = (mapping *) malloc(DIM * sizeof(mapping));
    
    for (i = 0; i < DIM; i++) {
        M[i] = UNDEFINED;
    }
}

  

void assegna(mapping *M, int chiave_dom, int valore_codom) {
    M[chiave_dom] = valore_codom;
}

boolean calcola(mapping *M, int chiave_dom, int *valore_codom) {

    if (M[chiave_dom] != UNDEFINED) {
        *valore_codom = M[chiave_dom];
        return TRUE;
    } else {
        return FALSE;
    }
}

```
### **8. Implementazione delle operazioni**

#### **Funzione `crea()`**

```c

void crea(mapping *M) {
    int i;
    M = (mapping *) malloc(DIM * sizeof(mapping));
    
    for (i = 0; i < DIM; i++) {
        M[i] = UNDEFINED;
    }
}

```

**Analisi logica:**

- Alloca dinamicamente un vettore di `DIM` interi.
    
- Inizializza ogni posizione a `UNDEFINED`.
    
- Rappresenta quindi il mapping vuoto Λ.
    

---

#### **Funzione `assegna()`**

```c

void assegna(mapping *M, int chiave_dom, int valore_codom) {
    M[chiave_dom] = valore_codom;
}

```

**Analisi logica:**

- Assegna al dominio `d` il valore `c`.
    
- Sostituisce il precedente valore se già esistente.
    
- Costo computazionale: $O(1)$ (accesso diretto).
    

---

#### **Funzione `calcola()`**

```c

boolean calcola(mapping *M, int chiave_dom, int *valore_codom) {

    if (M[chiave_dom] != UNDEFINED) {
        *valore_codom = M[chiave_dom];
        return TRUE;
    } else {
        return FALSE;
    }
}

```

**Analisi logica:**

- Controlla se esiste un valore associato a `d`.
    
- Se sì, restituisce `TRUE` e memorizza il valore in `*c`.
    
- Se no, restituisce `FALSE`.
    
- Anche qui la complessità è **O(1)**.
    

---

### **9. Analisi complessiva**

|Operazione|Significato|Complessità|Descrizione|
|---|---|---|---|
|`crea()`|Inizializza il mapping|O(n)|inizializza ogni cella del vettore|
|`assegna()`|Associa una chiave a un valore|O(1)|accesso diretto in memoria|
|`calcola()`|Restituisce un valore se definito|O(1)|accesso diretto con controllo|

> Il costo principale è nella creazione iniziale, mentre le operazioni successive sono tutte in **tempo costante**.

---

### **10. Osservazioni sulla struttura**

Il tipo di dato `mapping` rappresenta una **struttura dati associativa semplice**, basata su vettore.  
È un primo esempio concreto di come:

- il formalismo matematico (dominio, codominio, funzione)
    
- si traduca in un modello computazionale (vettore indicizzato).
    

L’approccio sarà poi generalizzato per costruire:

- tabelle hash,
    
- dizionari dinamici,
    
- e altre strutture più efficienti per domini di grandi dimensioni.
    

---

### **11. Sintesi finale**

- È stato costruito un **tipo di dato astratto** completo: il _mapping_.
    
- Abbiamo seguito le tre fasi canoniche:
    
    1. **Specifica sintattica** (nomi, tipi, funzioni)
        
    2. **Specifica semantica** (comportamento e proprietà logiche)
        
    3. **Realizzazione** (implementazione in C)
        
- Abbiamo inoltre visto come stimare la **complessità operativa** di ciascuna funzione.
    

> Questa lezione chiude il **Modulo 1** con un passaggio cruciale:  
> l’astrazione matematica diventa codice eseguibile.  
> Da qui in poi, il programmatore non è solo un esecutore di istruzioni, ma un **architetto della conoscenza logica**, capace di trasformare strutture astratte in sistemi operativi reali.