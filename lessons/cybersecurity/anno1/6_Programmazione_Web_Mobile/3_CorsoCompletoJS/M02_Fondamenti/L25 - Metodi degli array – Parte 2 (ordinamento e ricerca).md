## **Lezione 25: Metodi degli array – Parte 2 (ordinamento e ricerca)**

---

### **1. Introduzione: ordinare e cercare negli array**

In questa lezione vediamo i metodi degli array legati a:

- **ordinamento**:
    
    - `sort`
        
    - `reverse`
        
    - `sort` con **funzione di confronto personalizzata**
        
- **ricerca**:
    
    - `find`
        
    - `findIndex`
        
    - `indexOf`
        
    - `lastIndexOf`
        

L’idea generale:

- `sort` e `reverse` **modificano** l’array originale
    
- `find` e `findIndex` usano una **funzione di condizione** (predicato)
    
- `indexOf` e `lastIndexOf` cercano un **valore esatto** (non una condizione)
    

Useremo spesso questo array di esempio:

```js
let numeri = [4, 2, 8, 1, 6];
```

---

### **2. `sort()` – ordinamento base**

#### **2.1 Uso semplice di `sort()`**

```js
let numeri = [4, 2, 8, 1, 6];

numeri.sort();

console.log(numeri); // [1, 2, 4, 6, 8]
```

Così usato:

- `sort()` **modifica** l’array originale
    
- ordina gli elementi **come stringhe** in ordine “lessicografico” (alfabetico)
    

Con soli **numeri piccoli** come quelli in esempio spesso “sembra” funzionare bene, ma:

```js
let arr = [1, 2, 15, 3];

arr.sort();
console.log(arr); // [1, 15, 2, 3]
```

Perché?

- li confronta come **stringhe**: `"1"`, `"15"`, `"2"`, `"3"`
    
- l’ordine alfabetico è: `"1"`, `"15"`, `"2"`, `"3"`
    

Per numeri reali, useremo quasi sempre la **versione con funzione di confronto** (vedi §4).

---

### **3. `reverse()` – invertire l’ordine dell’array**

`reverse()` **inverte l’ordine** degli elementi:

```js
let numeri = [1, 2, 4, 6, 8];

numeri.reverse();

console.log(numeri); // [8, 6, 4, 2, 1]
```

- **modifica** l’array originale
    
- non ha parametri: lavora sull’array così com’è
    

Possiamo combinarlo con `sort`:

```js
let numeri = [4, 2, 8, 1, 6];

numeri.sort();    // [1, 2, 4, 6, 8]
numeri.reverse(); // [8, 6, 4, 2, 1]
```

---

### **4. `sort()` con funzione di confronto (numeri)**

Per ordinare numeri **in modo corretto**, si passa a `sort` una **funzione di confronto**:

```js
let numeri = [4, 2, 8, 1, 6];

numeri.sort((a, b) => a - b);  // crescente

console.log(numeri); // [1, 2, 4, 6, 8]
```

La funzione `(a, b) => a - b` viene chiamata più volte da `sort` con **coppie** di elementi dell’array.

Regola:

- se il risultato è **< 0**, `a` deve venire **prima** di `b`
    
- se è **> 0**, `b` deve venire **prima** di `a`
    
- se è **0**, l’ordine relativo non cambia
    

Per l’**ordine decrescente**:

```js
let numeri = [4, 2, 8, 1, 6];

numeri.sort((a, b) => b - a);  // decrescente

console.log(numeri); // [8, 6, 4, 2, 1]
```

Osservazione importante:

- `sort((a, b) => b - a)` fa praticamente la stessa cosa di
    
    - `sort((a, b) => a - b)` **+** `reverse()`
        
- ma in un solo passaggio e in modo più esplicito.
    

---

### **5. `sort()` con stringhe: problema delle maiuscole**

Esempio:

```js
let frutti = ["mela", "banana", "Arancia", "Kiwi", "Mela"];

frutti.sort();

console.log(frutti);
// Ad es. ["Arancia", "Kiwi", "Mela", "banana", "mela"]
```

Comportamento di base:

- tutte le stringhe **MAIUSCOLE** prima
    
- poi le **minuscole**
    
- all’interno di ciascun blocco, ordine alfabetico
    

Quindi:

- `"Arancia"` e `"Kiwi"` (con lettera iniziale maiuscola) vengono **prima**
    
- `"banana"`, `"mela"`, `"mela"` vengono dopo
    

Se vogliamo un ordinamento **case-insensitive** (che ignora maiuscolo/minuscolo):

```js
let frutti = ["mela", "banana", "Arancia", "Kiwi", "Mela"];

frutti.sort((a, b) => {
  let aa = a.toLowerCase();
  let bb = b.toLowerCase();

  if (aa < bb) return -1; // a prima di b
  if (aa > bb) return 1;  // b prima di a
  return 0;               // uguali
});

console.log(frutti);
// ["Arancia", "banana", "Kiwi", "Mela", "mela"] (ordinati ignorando il case)
```

- convertiamo entrambe le parole in minuscolo
    
- confrontiamo le versioni minuscole
    
- l’ordinamento risulta “alfabetico” indipendentemente dal fatto che siano scritte in maiuscolo o minuscolo
    

---

### **6. `sort()` su array di oggetti (esempio tipico)**

Questo schema è **ovunque** nel mondo reale.

Immagina un array di oggetti **persona**:

```js
let persone = [
  { nome: "Luca",    eta: 30 },
  { nome: "Edoardo", eta: 25 },
  { nome: "Giulia",  eta: 35 }
];
```

Se facessi:

```js
persone.sort();
```

otterresti qualcosa di poco utile, perché `sort` non sa “da solo” **su cosa** confrontare gli oggetti.

Definiamo invece una funzione di confronto sulla proprietà `eta`:

```js
persone.sort((a, b) => a.eta - b.eta);

console.log(persone);
/*
[
  { nome: "Edoardo", eta: 25 },
  { nome: "Luca",    eta: 30 },
  { nome: "Giulia",  eta: 35 }
]
*/
```

Logica:

- `a.eta - b.eta < 0` → `a` viene prima (età più piccola)
    
- `a.eta - b.eta > 0` → `b` viene prima
    
- `a.eta - b.eta === 0` → pari età, ordine non cambia
    

Questo pattern si usa per:

- ordinare prodotti per **prezzo**
    
- ordinare messaggi per **data**
    
- ordinare utenti per **nome**, **cognome**, ecc.
    

---

### **7. `find()` – trovare il **primo** elemento che rispetta una condizione**

`find` cerca **il primo elemento** dell’array per cui una **condizione** (funzione) è vera.

Sintassi:

```js
array.find(elemento => condizione);
```

Esempio: trovare il **primo numero maggiore di 5**

```js
let numeri = [4, 2, 8, 1, 6];

let risultato = numeri.find(numero => numero > 5);

console.log(risultato); // 8
```

Come ragiona:

1. prende `4` → `4 > 5`? no
    
2. prende `2` → `2 > 5`? no
    
3. prende `8` → `8 > 5`? sì → **stop**, ritorna `8`
    
4. `6` non viene neanche considerato, perché il primo l’ha già trovato
    

Punti chiave:

- ritorna **il valore trovato**, non l’indice
    
- se **non trova niente**, ritorna `undefined`
    
- **non** restituisce tutti gli elementi che soddisfano la condizione → solo il **primo**
    

---

### **8. `findIndex()` – indice del primo elemento che soddisfa la condizione**

È la versione “a indice” di `find`.

Sintassi:

```js
array.findIndex(elemento => condizione);
```

Esempio: indice del primo numero > 5

```js
let numeri = [4, 2, 8, 1, 6];

let indice = numeri.findIndex(numero => numero > 5);

console.log(indice);     // 2  (perché numeri[2] = 8)
console.log(numeri[2]);  // 8
```

Se non trova nulla:

```js
let indice = numeri.findIndex(numero => numero > 100);
console.log(indice); // -1
```

- `find` → valore
    
- `findIndex` → **posizione** (o `-1` se non trovato)
    

Molto utile quando:

- vuoi prima **trovare** un elemento
    
- poi usarne la **posizione** per fare operazioni con `splice`, ecc.
    

---

### **9. `indexOf()` e `lastIndexOf()` – ricerca per valore esatto**

Questi non usano funzioni di condizione: cercano un **valore preciso**.

#### **9.1 `indexOf()`**

```js
let numeri = [4, 2, 8, 1, 6];

let i1 = numeri.indexOf(2);
console.log(i1); // 1  (perché numeri[1] = 2)

let i2 = numeri.indexOf(5);
console.log(i2); // -1 (5 non c'è)
```

Secondo argomento: **indice di partenza** da cui cominciare a cercare.

```js
let numeri = [4, 2, 8, 2, 6];

let i = numeri.indexOf(2, 2); // cerca "2" a partire da indice 2
console.log(i); // 3 (il secondo 2)
```

---

#### **9.2 `lastIndexOf()`**

Uguale a `indexOf`, ma parte **da destra** e torna indietro.

```js
let numeri = [4, 2, 8, 2, 6];

let i = numeri.lastIndexOf(2);
console.log(i); // 3 (l'ultima ricorrenza di 2)
```

Se l’elemento **non esiste**:

```js
numeri.lastIndexOf(5); // -1
```

---

### **10. Riepilogo mentale rapido**

- **Ordinamento**
    
    - `sort()` → ordina l’array (di default come **stringhe**)
        
    - `sort((a, b) => a - b)` → numeri in ordine **crescente**
        
    - `sort((a, b) => b - a)` → numeri in ordine **decrescente**
        
    - `sort(custom)` su stringhe con `toLowerCase()` → ordine alfabetico ignorando maiuscole/minuscole
        
    - `sort((a, b) => a.eta - b.eta)` → ordinare array di oggetti per **età**
        
    - `reverse()` → inverte l’ordine corrente
        
- **Ricerca con condizione**
    
    - `find(cond)` → **primo valore** che soddisfa `cond`
        
    - `findIndex(cond)` → **indice** del primo valore che soddisfa `cond` (o `-1`)
        
- **Ricerca per valore esatto**
    
    - `indexOf(val)` → prima posizione di `val` (o `-1`)
        
    - `indexOf(val, from)` → cerca da `from` in poi
        
    - `lastIndexOf(val)` → ultima posizione di `val` (o `-1`)
        

