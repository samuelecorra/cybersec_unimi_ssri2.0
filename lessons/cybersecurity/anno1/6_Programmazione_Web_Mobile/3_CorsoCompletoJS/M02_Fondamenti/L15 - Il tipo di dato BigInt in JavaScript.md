## **Lezione 15: Il tipo di dato BigInt in JavaScript**

In questa lezione entriamo nel mondo dei **BigInt**, un tipo di dato introdotto in tempi recentissimi per colmare una grande limitazione storica di JavaScript:

> **l’impossibilità di rappresentare interi oltre le 15 cifre senza perdere precisione.**

---

## **1. Cos’è BigInt e perché esiste**

BigInt è stato introdotto con **ECMAScript 2020 (ES2020)** come **nuovo tipo di dato numerico** in JavaScript.  
Prima di questa introduzione, JavaScript usava un unico formato numerico interno basato sugli IEEE 754 **double-precision floating point**.

Questo formato funziona bene per i decimali e per la maggior parte dei numeri…  
ma ha un limite strutturale:

### **🔸 oltre le ≈ 15 cifre, JavaScript inizia ad arrotondare automaticamente.**

Esempio:

```js
let x = 12345678901234567;
console.log(x); 
```

Risultato:

```
12345678901234560
```

La finezza delle ultime cifre viene **persa**.

BigInt risolve questo problema consentendo di rappresentare **interi arbitrariamente grandi**, senza alcun limite pratico (salvo memoria disponibile).

---

## **2. Come si crea un BigInt**

Due sintassi possibili.

### **2.1 Con la lettera `n` finale**

```js
let x = 123456789012345678901234567890n;
```

La `n` dice a JavaScript:

> “Questo non è un numero normale: trattalo come BigInt”.

### **2.2 Con il costruttore `BigInt()`**

```js
let x = BigInt("123456789012345678901234567890");
```

È utile quando il numero arriva da:

- un input utente,
    
- un file,
    
- un’API,
    
- oppure è troppo grande per essere scritto letteralmente nel codice.
    

Entrambe le forme producono lo stesso risultato.

---

## **3. Controllare il tipo**

```js
console.log(typeof x); 
```

Risultato:

```
"bigint"
```

BigInt ha quindi **un tipo dedicato**, diverso dagli altri tipi numerici.

---

## **4. Operazioni aritmetiche con BigInt**

I BigInt possono fare tutte le operazioni dei numeri normali:

```js
let a = 50n;
let b = 4n;

console.log(a * b); // 200n
```

Osserva che **le operazioni restituiscono sempre BigInt**, quindi anche il risultato ha la `n` finale.

---

## **5. ❌ Non puoi mescolare BigInt e Number**

Una delle regole fondamentali:

```js
let x = 100n;
let y = 2;

console.log(x / y); 
```

Questo genera un **errore**:

```
TypeError: Cannot mix BigInt and other types
```

Per far funzionare la divisione devi:

### **A) Convertire il BigInt in Number**

```js
console.log(Number(x) / y);
```

→ Perderai precisione se il numero è troppo grande.

### **B) Convertire il Number in BigInt**

```js
console.log(x / BigInt(y));
```

→ Mantieni la precisione nell'aritmetica BigInt.

---

## **6. BigInt come intero sicuro: Safe Integer**

JavaScript ha una proprietà importante:

```js
Number.MAX_SAFE_INTEGER
```

Questo è il **più grande intero che può essere rappresentato esattamente** con il tipo `Number`.

Valore:

```
9007199254740991
```

Oltre questo numero, non puoi fidarti dell’accuratezza.

BigInt nasce proprio per superare questo limite.

Esempio:

```js
console.log(Number.MAX_SAFE_INTEGER + 1); 
console.log(Number.MAX_SAFE_INTEGER + 2);
```

Risultati sbagliati.

Con BigInt invece:

```js
console.log(9007199254740991n + 1n);
console.log(9007199254740991n + 2n);
```

→ Funziona e resta accurato.

---

## **7. Funzioni utili: Number.isInteger e Number.isSafeInteger**

Vediamo un’applicazione concreta del tipo BigInt.

```js
let x = 12345678901234567890n;

// Verifica se è integer
console.log(Number.isInteger(2));  // true
console.log(Number.isInteger(x));  // false (perché è BigInt)

// Verifica se è un safe integer
console.log(Number.isSafeInteger(2)); // true
console.log(Number.isSafeInteger(x)); // false
console.log(Number.isSafeInteger(Number.MAX_SAFE_INTEGER)); // true
```

Significato:

- BigInt **non è** considerato un "integer" da `Number.isInteger`, perché il metodo è pensato solo per il tipo Number.
    
- Ovviamente **non è un safe integer**, perché supera i limiti del tipo Number.
    

---

## **8. Quando userai davvero BigInt?**

La risposta onesta:

> **Quasi mai.**

A meno che tu non lavori in campi come:

- crittografia,
    
- intelligenza artificiale matematica avanzata,
    
- simulazioni scientifiche,
    
- finanza ad alta precisione,
    
- ricerca universitaria (CERN, ecc.).
    

Per la programmazione web normale, i BigInt non compariranno quasi mai.

Ma sapere che esistono è fondamentale.

---

## **9. Riepilogo della lezione**

- BigInt è un tipo di dato introdotto in **ES2020**.
    
- Serve per rappresentare **interi più grandi di 15 cifre** senza perdita di precisione.
    
- Si crea con:
    
    - sintassi finale `n`
        
    - costruttore `BigInt()`
        
- Non puoi mischiarlo con numeri normali (`Number`)
    
- È sempre di tipo `"bigint"`
    
- Permette operazioni familiari: `+ - * /`
    
- È fondamentale quando superi `Number.MAX_SAFE_INTEGER`.
    

---

## **Prossima lezione**

Ora che conosci i BigInt possiamo passare agli **operatori di assegnazione**, una parte che inizialmente confonde tutti ma che diventa naturale già dopo pochi esempi.

Fammi sapere quando vuoi iniziare!