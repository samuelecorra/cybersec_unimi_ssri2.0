## **Lezione 12: Numeri e operatori aritmetici in JavaScript**

In questa lezione iniziamo la sottosezione sui **tipi di dato** partendo dai **numeri**.  
Vediamo:

- come JavaScript rappresenta i numeri,
    
- cos’è `NaN`,
    
- gli **operatori aritmetici di base**,
    
- l’operatore **modulo `%`**,
    
- gli **operatori di incremento/decremento** `++` e `--` (pre e post).
    

---

### **1. I numeri in JavaScript: `number` unico per tutto**

In JavaScript **esiste un solo tipo di dato numerico**: `number`.

Questo tipo rappresenta **sia**:

- numeri interi → `42`
    
- numeri con la virgola (float) → `42.14`
    

Esempio:

```js
let numeroIntero = 42;
let numeroFloat = 42.14;

console.log(typeof numeroIntero); // "number"
console.log(typeof numeroFloat);  // "number"
```

Non esiste distinzione tra `int`, `float`, `double` ecc. come in C, C++, Java ecc.:

- per **JavaScript** sono entrambi `number`,
    
- la gestione “di basso livello” dei bit te la nasconde lui.
    

---

### **2. `NaN`: `Not a Number` (ma il tipo è comunque `number`)**

A volte, quando fai operazioni “sbagliate” con i numeri, ottieni `NaN`:

```js
let risultato = 10 / "ciao";

console.log(risultato);        // NaN
console.log(typeof risultato); // "number"
```

Due cose importantissime:

1. **`NaN` significa “Not a Number”** → “non sono riuscito a ricavare un vero numero da questa operazione”.
    
2. **`typeof NaN` è comunque `"number"`** → per JavaScript stai sempre lavorando nel “mondo dei numeri”, ma il risultato è invalido.
    

Tipica origine di `NaN`:

- divisione per una stringa non numerica,
    
- conversioni fallite (`Number("123abc")`),
    
- formule che internamente usano valori sbagliati (`undefined`, `null`, stringhe, ecc.).
    

`NaN` è spesso un **sintomo**:

> “Da qualche parte nei tuoi calcoli c’è un valore che non è quello che pensavi”.

---

### **3. Operazioni aritmetiche di base**

Useremo due variabili:

```js
let x = 10;
let y = 5;
```

Possiamo fare:

```js
let somma        = x + y;   // 10 + 5  = 15
let differenza   = x - y;   // 10 - 5  = 5
let prodotto     = x * y;   // 10 * 5  = 50
let divisione    = x / y;   // 10 / 5  = 2
let resto        = x % y;   // 10 % 5  = 0
```

Ricorda: **non serve** creare sempre una variabile intermedia `risultato`:

```js
console.log(10 + 5);      // 15
console.log(x * y);       // 50
```

L’esempio con `let risultato = ...` è solo per rendere leggibili i passaggi.

---

### **4. L’operatore modulo `%`: il resto della divisione**

L’operatore `%` **non** è “percentuale” → è il **modulo**, ossia **il resto** della divisione intera.

Esempio:

```js
let x = 10;
let y = 3;

console.log(x / y);  // 3.3333333...
console.log(x % y);  // 1  (resto)
```

Interpretazione “alla elementari”:

- `10 / 3` = **3 con resto 1**
    
- il risultato “normale” (`/`) è `3.3333...`,
    
- il **modulo** (`%`) ti restituisce il **resto**, quindi `1`.
    

Altro esempio:

```js
console.log(10 % 5); // 0  (10 è perfettamente divisibile per 5)
console.log(10 % 3); // 1
console.log(7  % 2); // 1
console.log(8  % 2); // 0
```

#### **4.1 Usare `%` per controllare se un numero è pari**

Un numero è **pari** se è divisibile per 2 → cioè se il resto è 0:

```js
let n = 10;

if (n % 2 === 0) {
  console.log("pari");
} else {
  console.log("dispari");
}
```

- `10 % 2` → `0` → pari
    
- `11 % 2` → `1` → dispari
    

Questo trucco “modulo 2” lo userai ovunque.

---

### **5. Espressioni più complesse e precedenza (mini spoiler)**

Puoi combinare gli operatori:

```js
let x = 10;
let y = 5;

let risultato = x * 5 - y + 3; // espressione mista
```

Vale la **stessa logica della matematica**:

1. parentesi
    
2. moltiplicazioni/divisioni
    
3. addizioni/sottrazioni
    

Esempio:

```js
let r1 = 10 + 5 * 2;      // 10 + 10 = 20
let r2 = (10 + 5) * 2;    // 15 * 2  = 30
```

La **precedenza degli operatori** in JS è più ampia (perché ci sono anche `==`, `&&`, `||`, ecc.), ma per la parte aritmetica la regola è quella che già conosci.

---

### **6. Operatori di incremento e decremento**

Incrementare o decrementare di **1** è talmente comune in programmazione che esistono operatori dedicati:

- `x++` → incrementa `x` di 1
    
- `x--` → decrementa `x` di 1
    

In pratica:

```js
let x = 10;

x++;  // equivalente a: x = x + 1;
console.log(x); // 11

x--;  // equivalente a: x = x - 1;
console.log(x); // 10
```

Fin qui tutto semplice.  
La parte interessante (e più sottile) è la differenza tra:

- **pre-incremento** → `++x`
    
- **post-incremento** → `x++`
    

Stesso discorso per `--x` e `x--`.

---

### **7. Pre-incremento vs post-incremento**

Quando usi `++` **da solo su una riga**, tipo:

```js
x++;
++x;
```

il risultato finale su `x` è lo stesso: `x` aumenta di 1.

La differenza viene fuori quando **usi il valore dentro un’espressione**, ad esempio durante un’assegnazione.

#### **7.1 Pre-incremento: `++x`**

```js
let x = 10;
let y = ++x;

console.log(x); // 11
console.log(y); // 11
```

Flusso mentale:

1. `++x` → prima incrementa `x` (da 10 a 11),
    
2. poi il valore “nuovo” (11) viene assegnato a `y`.
    

Quindi **entrambi** finiscono a `11`.

---

#### **7.2 Post-incremento: `x++`**

```js
let x = 10;
let y = x++;

console.log(x); // 11
console.log(y); // 10
```

Qui cambia:

1. `y = x++` → prima assegna a `y` il **valore attuale** di `x` (10),
    
2. **dopo** incrementa `x` (che passa a 11).
    

Risultato:

- `x` → 11
    
- `y` → 10
    

Questa è una delle prime cose che “fa saltare il cervello” quando inizi, ma la regola è:

- `++x` → **prima** incrementa, **poi** usa il valore
    
- `x++` → **prima** usa il valore, **poi** incrementa
    

Stessa identica logica per:

- `--x` (pre-decremento)
    
- `x--` (post-decremento)
    

---

### **8. Collegamento intuitivo: lettura “riga per riga”**

Pensa ad una lista della spesa:

- sei su un elemento,
    
- poi “scendi” di una riga,
    
- o “risali” di una riga.
    

Con i cicli (più avanti) userai spesso pattern del tipo:

```js
i = i + 1;
i += 1;
i++;
```

Sono tutte forme di “vai avanti di uno” in una sequenza:

- elementi di un array,
    
- righe di un file,
    
- step di un ciclo.
    

Gli operatori `++` e `--` sono una scorciatoia sintattica per un’operazione che farai **centinaia di volte**.

---

### **9. Riepilogo rapido**

- JavaScript ha un solo tipo numerico: **`number`**, che contiene sia interi che decimali.
    
- `NaN` = **Not a Number** → risultato invalido di un’operazione numerica.  
    `typeof NaN` è comunque `"number"`.
    
- Operatori base:
    
    - `+`, `-`, `*`, `/`, `%`
        
- `%` è il **resto** della divisione:
    
    - utilissimo per capire se un numero è **pari** (`n % 2 === 0`) o **dispari**.
        
- `++` e `--` servono per incrementare/decrementare di 1:
    
    - `++x` → incrementa **prima**, usa il valore **dopo** (pre-incremento).
        
    - `x++` → usa il valore **prima**, incrementa **dopo** (post-incremento).
        

Nelle prossime lezioni entrerai ancora più nel dettaglio dei numeri (altri metodi, altre situazioni) e poi passerai alla **precedenza degli operatori** e agli **operatori di assegnamento**.