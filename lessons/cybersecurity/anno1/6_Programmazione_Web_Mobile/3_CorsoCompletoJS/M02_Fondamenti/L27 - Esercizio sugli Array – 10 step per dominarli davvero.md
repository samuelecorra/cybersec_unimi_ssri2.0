## **Lezione 27: Esercizio sugli Array – 10 step per dominarli davvero**

---

### **1. Introduzione**

In questo esercizio lavoriamo su un **unico array**, trasformandolo passo dopo passo.  
L’obiettivo è fissare definitivamente tutti i metodi fondamentali:

- `split`
    
- `length`
    
- `indexOf`
    
- `slice`
    
- `unshift`, `pop`
    
- `sort`, `reverse`
    
- `join`
    
- `toUpperCase`
    
- `includes`
    

Ogni singolo step è concatenato al precedente, proprio come faresti in un progetto reale.

---

## **2. Codice iniziale**

Partiamo da un codice fiscale di esempio:

```js
const codiceFiscale = "RSSMRA85M01H501Z";
```

---

## **3. Step-by-step (con spiegazione + codice esatto)**

---

### **### 1. Trasforma il codice fiscale in un array di caratteri**

Il metodo `split("")` spezza una stringa **in singoli caratteri**.

```js
const arrayCaratteri = codiceFiscale.split("");
console.log(arrayCaratteri);
```

Ora `arrayCaratteri` è qualcosa tipo:

```
["R","S","S","M","R","A","8","5","M","0","1","H","5","0","1","Z"]
```

---

### **### 2. Trova la lunghezza dell’array**

```js
const lunghezzaArray = arrayCaratteri.length;
console.log(lunghezzaArray);
```

---

### **### 3. Trova l’indice del carattere "M"**

```js
const indiceM = arrayCaratteri.indexOf("M");
console.log(indiceM);
```

Restituisce **la prima M** che trova.

---

### **### 4. Estrai una sottostringa usando `slice`**

Prendiamo la porzione tra indice **6** incluso e **11** escluso:

```js
const sottoStringa = arrayCaratteri.slice(6, 11);
console.log(sottoStringa);
```

`slice` **non modifica l’array originale**.

---

### **### 5. Aggiungi un carattere all’inizio dell’array**

`unshift` inserisce all’inizio:

```js
arrayCaratteri.unshift("X");
console.log(arrayCaratteri);
```

---

### **### 6. Rimuovi l’ultimo carattere**

`pop` rimuove l’ultimo elemento:

```js
arrayCaratteri.pop();
console.log(arrayCaratteri);
```

---

### **### 7. Ordina l’array in ordine alfabetico**

```js
arrayCaratteri.sort();
console.log(arrayCaratteri);
```

L’ordinamento alfabetico standard dà sempre prima **le maiuscole**.

---

### **### 8. Inverti completamente l’array**

```js
arrayCaratteri.reverse();
console.log(arrayCaratteri);
```

---

### **### 9. Unisci tutti gli elementi in una stringa**

`join("")` crea una stringa **senza separatori**:

```js
const nuovaStringa = arrayCaratteri.join("");
console.log(nuovaStringa);
```

È l’opposto dello `split("")`.

---

### **### 10. Trasforma la stringa in maiuscolo**

```js
const stringaMaiuscola = nuovaStringa.toUpperCase();
console.log(stringaMaiuscola);
```

---

## **4. Step extra: verifica se l’array contiene la lettera “W”**

`includes` restituisce **true/false**.

```js
console.log(arrayCaratteri.includes("W"));
```

Qui darà **false** perché il codice fiscale non contiene "W".

---

## **5. Risultati e competenze consolidate**

Con questo esercizio hai ripassato:

- come convertire una stringa in un array
    
- come ottenere informazioni dall’array (index, length)
    
- come estrarre pezzi con `slice`
    
- come aggiungere e togliere in modo controllato (`unshift`, `pop`)
    
- come ordinare e invertire l’ordine
    
- come passare array ⟶ stringa (`join`)
    
- come tornare a stringhe manipolabili
    
- come cercare elementi specifici con `includes`
    

È esattamente ciò che serve per padroneggiare il 90% degli array in JavaScript “reale”.
