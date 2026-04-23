## **Lezione 26: Array come costanti (`const`) – cosa si può fare e cosa è vietato**

---

### **1. Introduzione: una promessa mantenuta**

Avevamo accennato nel primo video degli array a una differenza “misteriosa” tra:

- `let nome = ...`
    
- `const nome = ...`
    

…e avevamo detto che **con gli array la differenza diventa molto più interessante** rispetto al semplice caso:

```js
const x = 10;
```

In questa lezione chiariremo finalmente:

- cosa significa avere **un array definito con `const`**
    
- cosa **possiamo modificare** al suo interno
    
- cosa **non possiamo assolutamente fare**
    
- perché questo comportamento è estremamente utile nella pratica quotidiana
    

---

## **2. `const` NON blocca il contenuto: blocca la RIASSEGNAZIONE**

Cominciamo con un esempio semplice.

```js
const numeri = [1, 2, 3, 4, 5];
```

La domanda è:

> **Posso modificare questo array?**

La risposta è **SÌ**.

Ma non posso fare tutto.  
Ecco la distinzione fondamentale che devi scolpire in testa:

---

### **2.1 COSA È VIETATO (riassegnazione)**

Non posso fare:

```js
numeri = [10, 20, 30];
```

Questo genera **errore immediato**.

Perché?

- `const` significa che **il riferimento** alla struttura di partenza è costante.
    
- L’array originale vive in una certa "celletta" di memoria.
    
- Una costante **non può essere ricollegata** a una nuova celletta.
    

Questo:

```js
const numeri = [1, 2, 3];
numeri = [1, 2, 3];  // ❌ VIETATO comunque
```

È vietato **anche se il nuovo array è identico al vecchio**.  
Per JavaScript stai comunque cercando di ricollegare la variabile a una struttura diversa.

Non importa che i valori siano uguali:  
**stai assegnando una nuova struttura → errore.**

---

### **2.2 COSA È PERMESSO (modificare il contenuto)**

Possiamo fare tutto quello che modifica **l’interno dell’array**, quindi:

- cambiare un elemento
    
- aggiungere elementi
    
- togliere elementi
    
- usare qualunque metodo (`sort`, `reverse`, `pop`, `push`, `splice`, etc.)
    
- cancellare posizioni (`delete`)
    
- mutare la struttura interna
    

Ad esempio:

```js
const numeri = [1, 2, 3, 4, 5];

numeri[1] = 10000;     // ✔ OK
delete numeri[2];       // ✔ OK
numeri.pop();           // ✔ OK
numeri.push(25);        // ✔ OK
numeri.shift();         // ✔ OK
numeri.unshift(66);     // ✔ OK
numeri.reverse();       // ✔ OK
numeri.sort();          // ✔ OK
```

Tutto questo funziona perfettamente.

Perché?

Perché **non stai cambiando la celletta di memoria dell’array**,  
stai solo cambiando **il contenuto interno**, che è ammesso.

---

## **3. Esempio completo: tutto ciò che è permesso**

Prendiamo:

```js
const numeri = [2, 3, 4, 5];
```

E facciamo tutte le operazioni viste:

```js
numeri.sort();
numeri.reverse();
numeri[1] = 10000;
delete numeri[2];
numeri.pop();
numeri.push(25);
numeri.shift();
numeri.unshift(66);
```

Alla fine:

```js
console.log(numeri);
```

Vedremo un array completamente diverso da quello iniziale.

E **funziona tutto**, nessun errore.

---

## **4. L’unica cosa vietata in assoluto**

Questo:

```js
numeri = [];
```

È **assolutamente vietato**.

Stai tentando di riassegnare una **nuova struttura**, anche se vuota.  
Per `const` questo è proibito al 100%.

---

## **5. Perché useremo quasi sempre `const` per gli array**

Questa parte è fondamentale.

Quando lavoriamo con gli array:

- nella maggior parte dei casi **non abbiamo alcuna intenzione di riassegnare un nuovo array**
    
- vogliamo solo **modificare** quello esistente (aggiungere, togliere, ordinare, filtrare…)
    
- `const` ci permette di **evitare errori stupidi** dovuti a riassegnazioni involontarie
    

Perciò nella pratica:

```js
const lista = [...];
```

è più sicuro e più usato di

```js
let lista = [...];
```

Anche se “sembra” controintuitivo per un principiante:

> **Un array `const` può essere modificato, ma non sostituito.**

E questo è esattamente ciò che vogliamo nella programmazione reale quotidiana.

---

## **6. Regola d’oro da ricordare**

### **“`const` blocca la variabile, non il contenuto.”**

O, detto meglio:

### **“`const` blocca il RIFERIMENTO all’array, non l’array stesso.”**

Questa frase ti basterà per tutta la tua carriera di programmatore JavaScript.

---

## **7. Perché accade questo? (Spiegazione semplice, senza teoria sui puntatori)**

JavaScript lavora così:

- valori primitivi → la variabile contiene il valore direttamente  
    (numeri, stringhe, booleani…)
    
- strutture complesse (array, oggetti) → la variabile contiene **un riferimento** all’oggetto reale in memoria
    

Se usi `const`:

- non puoi cambiare il **riferimento**
    
- ma puoi cambiare **la struttura reale** a cui il riferimento punta
    

È tutto qui.

Non c’è bisogno (per ora) di sapere cose pesanti come puntatori, riferimento vs valore, heap vs stack…  
Arriveremo (più avanti) a una versione avanzata.

---

## **8. Riepilogo rapido**

### ✔ Consentito con array `const`:

- modificare elementi singoli
    
- aggiungere elementi (`push`, `unshift`)
    
- togliere elementi (`pop`, `shift`, `splice`, `delete`)
    
- ordinare (`sort`)
    
- invertire (`reverse`)
    
- trasformare l’array (`splice`, `copyWithin`, ecc.)
    

### ❌ Vietato:

- riassegnare completamente l’array:
    

```js
numeri = [10, 20, 30];
numeri = [];
numeri = numeri.sort(); // ❌ anche questo è una riassegnazione!
```

---

## **9. Conclusione**

Gli array (come gli oggetti) sono strutture che in JavaScript:

- puoi **modificare** liberamente
    
- ma non puoi **rimpiazzare** se sono dichiarati con `const`
    

E questo comportamento è voluto: aiuta a scrivere codice più sicuro, pulito e prevedibile.
