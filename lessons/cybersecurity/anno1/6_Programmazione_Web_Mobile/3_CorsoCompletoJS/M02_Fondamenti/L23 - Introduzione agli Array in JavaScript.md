## **Lezione 23: Introduzione agli Array in JavaScript**

---

### **1. Che cos’è un array (concetto base)**

Un **array** in JavaScript è:

- una **collezione ordinata di valori**
    
- memorizzata dentro **un’unica variabile**
    
- i valori si trovano tra **parentesi quadre** `[...]`, separati da virgole
    

Esempio:

```js
let listaNumeri = [1, 2, 3, 4, 5];

let listaSpesa  = ['pane', 'sale', 'latte'];

let listaMista  = [1, 'Edo', true, { }, 1234567890123456789n];
```

Caratteristiche importanti:

- un array può contenere **qualunque tipo di dato**:
    
    - numeri
        
    - stringhe
        
    - boolean
        
    - oggetti
        
    - altri array (array “dentro” array → array multidimensionali)
        
    - persino `bigint` e `null` / `undefined`
        
- puoi avere:
    
    - un array **omogeneo** (solo numeri, solo stringhe, …)
        
    - un array **eterogeneo** (tipi diversi mescolati)
        

---

### **2. `typeof` degli array: perché dice `object`?**

Se fai:

```js
let lista = [1, 'Edo', true];

console.log(typeof lista);
```

Ottieni:

```txt
object
```

Perché?

- tecnicamente, in JavaScript, gli array sono un **tipo speciale di oggetti**
    
- però, a livello pratico, li tratteremo come una **categoria a sé stante**:
    
    - “oggetti” → per cose tipo `{ nome: 'Edo', età: 31 }`
        
    - “array” → per liste ordinate di valori
        

Quindi:

- **tecnicamente**: `typeof lista === 'object'`
    
- **a parole**: “`lista` è un array”
    

---

### **3. Array come “contenitori di valori”**

Prima avevamo variabili “singole”:

```js
let x = 10;
let nome = 'Edoardo';
let attivo = true;
```

Con gli array puoi avere **più valori** dentro una sola variabile:

```js
let voti = [30, 28, 25, 30];
let hobby = ['gaming', 'coding', 'palestra'];
```

Questa è l’idea fondamentale:

> Un array è una **lista ordinata** di valori, logicamente collegati tra loro.

Esempi tipici di array:

- lista dei **voti**
    
- lista degli **utenti**
    
- lista dei **messaggi** di una chat
    
- lista degli **articoli** di un carrello
    

---

### **4. Nomi degli array: perché usare il plurale**

È buona pratica chiamare gli array con un **nome al plurale**, perché rappresentano “molti” elementi:

```js
const numeri  = [1, 2, 3];
const messaggi = [
  { testo: 'Ciao', autore: 'Edo' },
  { testo: 'Tutto bene?', autore: 'Luca' }
];
const prodotti = ['pane', 'sale', 'latte'];
```

Questo ti aiuta tantissimo quando inizierai con i **cicli**:

- `for (const messaggio of messaggi) { ... }`
    
- `for (const prodotto of prodotti) { ... }`
    

Leggere il codice diventa automatico:  
**`messaggi` → tanti**, **`messaggio` → uno**.

---

### **5. Proprietà `.length` (la lunghezza dell’array)**

Gli array hanno una proprietà speciale:

```js
const numeri = [1, 2, 3, 4, 5];

console.log(numeri.length);   // 5
```

- `.length` ti dice **quanti elementi** ci sono nell’array
    
- è **identico concettualmente** a `stringa.length`, solo che qui conti elementi, non caratteri
    

Se ci sono 5 elementi, gli **indici validi** saranno:

- da `0` a `4` (cioè da `0` a `length - 1`)
    

---

### **6. Indexing: accedere agli elementi tramite indice**

Come per le stringhe:

- l’array parte da **indice 0**
    
- per leggere un elemento usi **le parentesi quadre** `[]`
    

Esempio:

```js
const listaSpesa = ['pane', 'sale', 'latte'];

console.log(listaSpesa[0]);  // 'pane'
console.log(listaSpesa[1]);  // 'sale'
console.log(listaSpesa[2]);  // 'latte'
```

Se provi un indice che **non esiste**:

```js
console.log(listaSpesa[3]);  // undefined
```

---

### **7. Collegamento con le stringhe**

Quello che hai visto per le stringhe vale pari pari per gli array:

- **stringa**: collezione di caratteri
    
- **array**: collezione di valori
    

Per le stringhe:

```js
const nome = 'Amore';

console.log(nome.length); // 5
console.log(nome[0]);     // 'A'
console.log(nome[4]);     // 'e'
console.log(nome[5]);     // undefined (non esiste)
```

Per gli array:

```js
const numeri = [10, 20, 30, 40, 50];

console.log(numeri.length); // 5
console.log(numeri[0]);     // 10
console.log(numeri[4]);     // 50
console.log(numeri[5]);     // undefined (non esiste)
```

Regola generale:

> Se la lunghezza è `n`, l’ultimo indice valido è sempre `n - 1`.

---

### **8. Modificare gli elementi di un array**

Puoi cambiare un elemento semplicemente assegnando un nuovo valore alla sua posizione:

```js
let numeri = [1, 2, 3, 4, 5];

numeri[0] = 100;   // cambio il primo elemento
numeri[4] = 999;   // cambio l'ultimo elemento

console.log(numeri);   // [100, 2, 3, 4, 999]
```

La logica è la stessa della lettura, ma con l’assegnazione a sinistra:

```js
numeri[indice] = nuovoValore;
```

---

### **9. Aggiungere elementi “saltando” indici**

Puoi anche assegnare un elemento **a un indice che ancora non esiste**:

```js
let numeri = [1, 2, 3];

numeri[5] = 42;

console.log(numeri);
```

In console ottieni qualcosa del tipo:

```txt
(6) [1, 2, 3, empty × 2, 42]
```

Concetto importante:

- la `.length` diventa `6`
    
- l’array ha creato dei “buchi” (posizioni **vuote** / `undefined`) negli indici saltati:
    
    - indice `3` → vuoto
        
    - indice `4` → vuoto
        
- indice `5` → `42`
    

Se poi assegni anche agli indici “buchi”:

```js
numeri[3] = 7;
numeri[4] = 8;

console.log(numeri);  // [1, 2, 3, 7, 8, 42]
```

Ora non ci sono più buchi.

---

### **10. `let` vs `const` sugli array (teaser)**

Edo nel video fa notare una cosa strana:

```js
const numeri = [1, 2, 3];

numeri[0] = 100;    // OK
numeri[1] = 200;    // OK
```

Ma:

```js
const numeri = [1, 2, 3];

numeri = [2000, 3000];   // ERRORE
```

Quindi:

- con `const` puoi **modificare il contenuto interno** dell’array  
    (cambiare singoli elementi, aggiungere, togliere)
    
- ma **non puoi riassegnare** la variabile a un **nuovo array**
    

Il perché succede questo lo vedremo in un video dedicato più avanti, quando parleremo:

- di cosa rappresenta veramente la variabile
    
- di cosa c’è “dietro” a un array in memoria
    
- e della differenza tra “mutare un oggetto” e “rimpiazzarlo”
    

Per ora ti basta questa regola pratica:

- `const` per array che **esistono sempre**, ma i cui **elementi possono cambiare**
    
- `let` per array che vuoi proprio **rimpiazzare** con un nuovo array
    

---

### **11. Riassunto della lezione**

- Un **array** è una **lista ordinata di valori** racchiusi tra `[...]`.
    
- Può contenere **qualsiasi tipo di dato**, anche misti.
    
- `typeof` di un array è `"object"`, ma nel linguaggio comune diciamo “array”.
    
- `.length` ti dice **quanti elementi** ci sono.
    
- Gli indici partono da `0`, l’ultimo è `length - 1`.
    
- Puoi **leggere** con `lista[indice]`.
    
- Puoi **modificare** con `lista[indice] = nuovoValore`.
    
- Se assegni a un indice “lontano”, crei **buchi** (`undefined`) in mezzo.
    
- Con `const` puoi modificare gli elementi, ma **non** puoi riassegnare l’intero array.