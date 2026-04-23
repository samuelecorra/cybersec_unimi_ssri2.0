## **Lezione 35: I Booleani e i Falsy Values**


---

## **1. Introduzione: perché i Booleani sono il cuore dei flussi di codice**

I **Booleani** sono il fondamento di tutta la logica di un programma.

Un Booleano può essere solo:

- `$true$` → vero
    
- `$false$` → falso
    

Non esiste un terzo valore.  
La lampada è **accesa o spenta**, il computer è **acceso o spento**, un libro è **aperto o chiuso**.  
La logica dei programmi funziona esattamente così: **o sì, o no.**

Gli **if**, i cicli, i controlli di flusso sono semplicemente strade che si aprono in base a un valore vero/falso.

Esempio:

```js
let vero = true;
let falso = false;

console.log(vero);   // true
console.log(falso);  // false
```

---

## **2. I Booleani servono per verificare condizioni**

Quando facciamo confronti, il risultato è sempre `$true$` o `$false$`.

```js
console.log(5 > 10);   // false
console.log(7 === 7);  // true
```

Più avanti useremo queste condizioni per:

- attivare un blocco `if`
    
- scegliere tra due strade (`if/else`)
    
- ripetere codice finché una condizione rimane vera (`while`)
    
- controllare input, validazioni, accessi, ecc.
    

I Booleani quindi non sono “solo true e false”:  
**sono il meccanismo che decide cosa il programma deve fare.**

---

## **3. Truthy e Falsy Values: tutto può diventare un Booleano**

Qualsiasi valore in JavaScript può essere convertito in Booleano.

Esempio:

```js
console.log(Boolean(10));   // true
console.log(Boolean(0));    // false
```

Il concetto è:

> **Ogni valore o “esiste” → quindi è vero (truthy), oppure “non esiste” → quindi è falso (falsy).**

---

## **4. I veri valori FALSI in JavaScript (falsy values)**

Solo **sei** valori in JS diventano _sempre_ `false` se convertiti in Booleano.

Li analizziamo uno per uno, come nel video.

### **4.1. `false`**

Ovviamente è falso.

```js
Boolean(false);  // false
```

---

### **4.2. `0`**

Zero rappresenta “assenza di quantità”.

```js
Boolean(0); // false
Boolean(-11); // true (perché non è zero)
Boolean(35); // true
```

---

### **4.3. Stringa vuota `""`**

Una stringa senza caratteri è “assenza di contenuto”.

```js
Boolean(""); // false
Boolean("a"); // true
```

---

### **4.4. `null`**

Assenza intenzionale di valore.

```js
Boolean(null); // false
```

---

### **4.5. `undefined`**

Assenza **non intenzionale** di valore (variabile dichiarata ma non inizializzata).

```js
let x;
Boolean(x); // false
```

---

### **4.6. `NaN`**

Valore numerico non valido (“Not a Number”).

```js
Boolean(NaN); // false
```

---

## **5. Perché questi valori sono falsi? La logica dietro i falsy values**

Tutti e sei rappresentano **assenza di qualcosa**:

- `false` → assenza di verità
    
- `0` → assenza di quantità
    
- `""` → assenza di testo
    
- `null` → assenza voluta di valore
    
- `undefined` → assenza non voluta di valore
    
- `NaN` → assenza di un numero valido
    

Questo è essenziale quando costruirai condizioni, validazioni, controlli.

---

## **6. Esempi pratici: come i falsy values influenzano gli `if`**

### **6.1. Controllo di una stringa “nome”**

```js
let nome = "";

if (nome) {
  console.log("Hai un nome");
} else {
  console.log("Nome mancante");
}
```

Risultato:

```
Nome mancante
```

Perché la stringa vuota è falsy.

---

### **6.2. Controllo di una quantità**

```js
let dita = 0;

if (dita) {
  console.log("Puoi prendere il mouse");
} else {
  console.log("Non puoi prendere il mouse");
}
```

Output:

```
Non puoi prendere il mouse
```

Perché **zero = falsy**.

---

## **7. Riepilogo**

**Booleani:**

- valgono solo `true` o `false`
    
- decidono TUTTI i rami del flusso di un programma
    
- sono la base degli `if`, dei cicli e della logica del codice
    

**Falsy values (6 valori):**

1. `false`
    
2. `0`
    
3. `""`
    
4. `null`
    
5. `undefined`
    
6. `NaN`
    

Tutto il resto è **truthy**, quindi diventa `true` quando convertito in Booleano.

---

## **8. Pronti per la prossima parte**

Ora che hai capito:

- cosa sono i Booleani
    
- perché governano i flussi
    
- come funzionano i falsy values
    

possiamo passare al prossimo mattoncino:  
**gli operatori di comparazione e gli operatori logici**, per poi arrivare agli `if` e ai cicli.

Dimmi pure se vuoi passare subito agli operatori di comparazione.