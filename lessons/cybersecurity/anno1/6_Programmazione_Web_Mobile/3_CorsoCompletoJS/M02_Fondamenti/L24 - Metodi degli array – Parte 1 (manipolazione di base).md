## **Lezione 24: Metodi degli array – Parte 1 (manipolazione di base)**

---

### **1. Metodi degli array: idea generale**

Un **metodo** è un’azione che possiamo eseguire su un valore.  
Per gli array, i metodi si usano così:

```js
numeri.metodo(...argomenti)
```

oppure direttamente sul valore “grezzo”:

```js
[1, 2, 3].metodo(...argomenti);
```

Concetto chiave:

- ciò che scriviamo **prima del punto** è l’array su cui lavoriamo
    
- ciò che scriviamo **dopo il punto** è il **metodo** (cioè l’azione)
    

In questa lezione vediamo:

- conversione in stringa
    
- aggiunta/rimozione in testa e in coda
    
- unione in stringa (`join`)
    
- cancellazione con `delete`
    
- concatenazione di array
    
- “appiattimento” di array annidati (`flat`)
    
- rimozione/inserimento con `splice`
    
- estrazione di porzioni con `slice`
    

Useremo come esempio di base:

```js
let numeri = [1, 2, 3, 4, 5];
```

---

### **2. `toString()` – da array a stringa**

Il metodo:

```js
numeri.toString()
```

converte l’array in **stringa**, separando automaticamente gli elementi con **virgole**.

Esempio:

```js
let numeri = [1, 2, 3, 4, 5];

console.log("toString:", numeri.toString());
```

Output:

```txt
toString: 1,2,3,4,5
```

Osservazioni:

- il risultato è una **stringa** (non più un array)
    
- se vuoi tornare a un array, puoi usare `split` sulla stringa:
    

```js
let s = numeri.toString(); // "1,2,3,4,5"
let diNuovoArray = s.split(","); // ["1", "2", "3", "4", "5"]
```

(NB: gli elementi tornano come stringhe, non numeri.)

---

### **3. I quattro metodi fondamentali: `pop`, `push`, `shift`, `unshift`**

Questi quattro metodi sono **la base** per manipolare l’inizio e la fine di un array.

#### **3.1 `pop()` – rimuove l’ultimo elemento**

```js
let numeri = [1, 2, 3, 4, 5];

let ultimo = numeri.pop();

console.log(numeri); // [1, 2, 3, 4]
console.log(ultimo); // 5
```

- **modifica** l’array originale
    
- **ritorna** l’elemento rimosso (l’ultimo)
    

---

#### **3.2 `push()` – aggiunge in fondo**

```js
let numeri = [1, 2, 3, 4];

numeri.push(6);

console.log(numeri); // [1, 2, 3, 4, 6]
```

- **modifica** l’array originale
    
- **ritorna** la nuova lunghezza dell’array (anche se spesso questo valore non lo usiamo)
    

---

#### **3.3 `shift()` – rimuove il primo elemento**

```js
let numeri = [1, 2, 3, 4, 6];

let primo = numeri.shift();

console.log(numeri); // [2, 3, 4, 6]
console.log(primo);  // 1
```

- **modifica** l’array originale
    
- **ritorna** l’elemento rimosso (il primo)
    

---

#### **3.4 `unshift()` – aggiunge in testa**

```js
let numeri = [2, 3, 4, 6];

numeri.unshift(0);

console.log(numeri); // [0, 2, 3, 4, 6]
```

- **modifica** l’array originale
    
- **ritorna** la nuova lunghezza dell’array
    

---

#### **3.5 Filastrocca per ricordarli**

Dividili in coppie:

- `pop` / `push` → lavorano in **coda**
    
- `shift` / `unshift` → lavorano in **testa**
    

E per ognuna:

- `pop` → **rimuove** in fondo
    
- `push` → **aggiunge** in fondo
    
- `shift` → **rimuove** in testa
    
- `unshift` → **aggiunge** in testa
    

---

### **4. `join()` – unire gli elementi con un separatore**

`join` è simile a `toString`, ma ti permette di scegliere il **separatore**.

```js
let numeri = [1, 2, 3, 4];

let licenza = numeri.join("-");

console.log(licenza); // "1-2-3-4"
```

- risultato: **stringa**
    
- non modifica l’array originale
    
- il separatore può essere qualsiasi stringa: `" - "`, `" | "`, `""`, ecc.
    

Esempio senza separatore:

```js
let s = numeri.join("");
console.log(s); // "1234"
```

---

### **5. `delete` sugli array – attenzione ai “buchi”**

Possiamo usare l’operatore generico `delete` per eliminare un elemento in una certa posizione:

```js
let numeri = [0, 2, 4, 6];

delete numeri[2];

console.log(numeri);      // [0, 2, empty, 6]
console.log(numeri[2]);   // undefined
console.log(numeri.length); // 4 (la lunghezza NON cambia)
```

- `delete` **non compatta** l’array
    
- lascia un buco (`empty` / `undefined`) in quella posizione
    
- la lunghezza (`length`) resta invariata
    

Di solito, per rimuovere elementi “sul serio”, è **meglio** usare metodi come `splice` (vedi sotto), non `delete`.

---

### **6. `concat()` – concatenare array**

`concat` serve a **unire** array, creando un **nuovo array**.

```js
let numeri1 = [1, 2, 3];
let numeri2 = [7, 8, 9];

let numeri3 = numeri1.concat(numeri2);

console.log(numeri1); // [1, 2, 3]
console.log(numeri2); // [7, 8, 9]
console.log(numeri3); // [1, 2, 3, 7, 8, 9]
```

- **non** modifica gli array originali
    
- **ritorna** un nuovo array contenente tutti gli elementi di entrambi
    

---

### **7. `flat()` – appiattire un array annidato**

Supponiamo di avere un array **annidato** (multidimensionale):

```js
let annidato = [1, 2, [3, 4], [5, 6]];
```

Con `flat()`:

```js
let piatto = annidato.flat();

console.log(piatto); // [1, 2, 3, 4, 5, 6]
```

- prende gli elementi dei sotto-array di **un livello** e li porta “in superficie”
    
- molto utile quando hai array di array e ti serve una lista “piatta” di elementi
    

---

### **8. `splice()` – rimuovere e/o inserire in mezzo all’array**

`splice` è potente ma un po’ insidioso. La sua firma base è:

```js
array.splice(start, deleteCount, elemento1, elemento2, ...);
```

Dove:

- `start` → indice da cui partire
    
- `deleteCount` → **quanti elementi** rimuovere a partire da `start`
    
- `elemento1, elemento2, ...` → eventuali elementi da **inserire** al posto di quelli rimossi
    

Restituisce:

- un **nuovo array** contenente gli elementi rimossi.
    

Modifica:

- **modifica direttamente** l’array originale.
    

#### **8.1 Esempio base con rimozione e inserimento**

```js
let numeri = [0, 2, undefined, 4, 6];

// Parto dall'indice 1, rimuovo 2 elementi, inserisco 10 e 11
let rimossi = numeri.splice(1, 2, 10, 11);

console.log(numeri);  // [0, 10, 11, 4, 6]
console.log(rimossi); // [2, undefined]
```

Spiegazione:

- `start = 1` → parto dal valore `2`
    
- `deleteCount = 2` → rimuovo `2` e `undefined`
    
- poi inserisco `10` e `11` al loro posto
    

---

#### **8.2 Solo inserimento (deleteCount = 0)**

```js
let numeri = [0, 2, undefined, 4, 6];

let rimossi = numeri.splice(1, 0, 10, 11);

console.log(numeri);  // [0, 10, 11, 2, undefined, 4, 6]
console.log(rimossi); // []
```

- `start = 1`
    
- `deleteCount = 0` → non rimuove nulla
    
- inserisce `10` e `11` **prima** dell’elemento con indice `1` (`2`)
    

---

### **9. `slice()` – estrarre una porzione senza modificare l’originale**

`slice` è simile a `splice`, ma:

- **non modifica** l’array originale
    
- restituisce **solo la porzione estratta**
    

Firma base:

```js
array.slice(start, end);
```

Dove:

- `start` → indice di partenza (incluso)
    
- `end` → indice di fine (**escluso**)
    

Esempio:

```js
let numeri = [0, 2, undefined, 4, 6];

// Prende dagli indici 1 a 4 (4 escluso): quindi 1, 2, 3
let estratti = numeri.slice(1, 4);

console.log(estratti); // [2, undefined, 4]
console.log(numeri);   // [0, 2, undefined, 4, 6] (invariato)
```

---

### **10. Splice vs Slice – differenza concettuale**

Riassunto importantissimo:

- **`splice(start, deleteCount, ...daAggiungere)`**
    
    - **modifica** l’array originale
        
    - può **rimuovere** e **aggiungere**
        
    - ritorna gli elementi rimossi
        
- **`slice(start, end)`**
    
    - **non modifica** l’array originale
        
    - ritorna un **nuovo array** con la porzione `[start, end)`  
        (end escluso)
        

---

### **11. Riepilogo dei metodi visti**

- `toString()` → array → stringa `"1,2,3"`
    
- `pop()` → rimuove **l’ultimo**, ritorna l’elemento rimosso
    
- `push()` → aggiunge **in coda**, ritorna la nuova length
    
- `shift()` → rimuove **il primo**, ritorna l’elemento rimosso
    
- `unshift()` → aggiunge **in testa**, ritorna la nuova length
    
- `join(separatore)` → array → stringa con separatore scelto
    
- `delete array[i]` → segna il posto come `empty`/`undefined`, non compatta l’array
    
- `concat()` → unisce più array in **uno nuovo**
    
- `flat()` → appiattisce un array annidato di un livello
    
- `splice(start, deleteCount, ...elem)` → rimuove/aggiunge e **modifica** l’array
    
- `slice(start, end)` → estrae una porzione senza modificare l’originale
    
