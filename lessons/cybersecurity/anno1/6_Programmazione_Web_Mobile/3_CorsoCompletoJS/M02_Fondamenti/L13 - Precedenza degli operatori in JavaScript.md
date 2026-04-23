## **Lezione 13: Precedenza degli operatori in JavaScript**

In questa lezione capiamo **chi viene eseguito prima** quando in un’unica riga scriviamo tante operazioni insieme:

- perché `10 + 2 * 2` dà `14` e non `24`,
    
- perché `2 ** 10 * 2 + 10` esplode a migliaia,
    
- perché a volte “vincono” prima le moltiplicazioni, altre volte i confronti, altre volte l’assegnazione.
    

L’idea chiave è:

> In un’espressione con più operatori, **non** viene semplicemente eseguito tutto “da sinistra a destra”.  
> JavaScript segue delle **regole di precedenza** tra gli operatori.

---

### **1. Cos’è la precedenza degli operatori**

Quando scrivi qualcosa come:

```js
let risultato = 10 + 2 * 2;
```

qui compaiono **due operatori**:

- `+` (addizione)
    
- `*` (moltiplicazione)
    

La domanda è: **cosa viene fatto prima?**

- se facessi prima `10 + 2` → 12, poi `12 * 2` → 24
    
- se facessi prima `2 * 2` → 4, poi `10 + 4` → 14
    

JavaScript segue la **stessa logica della matematica**:

- **moltiplicazioni e divisioni hanno precedenza** su addizioni e sottrazioni.
    

Quindi:

```js
let risultato = 10 + 2 * 2;
// prima:  2 * 2 = 4
// poi:    10 + 4 = 14
console.log(risultato); // 14
```

Se vuoi “forzare” l’ordine, usi le **parentesi**:

```js
let risultato = (10 + 2) * 2;
// prima:  10 + 2 = 12
// poi:    12 * 2 = 24
console.log(risultato); // 24
```

**MORALE:** le parentesi vincono su tutto.

---

### **2. Aggiungiamo la potenza: l’operatore `**`**

JavaScript moderno ha l’operatore di **potenza**:

```js
let x = 2 ** 10;   // 2 alla decima
console.log(x);    // 1024
```

La precedenza tra i principali operatori aritmetici è:

1. **`()`** → parentesi
    
2. **`**`** → potenza
    
3. **`*`, `/`, `%`** → moltiplicazione, divisione, modulo
    
4. **`+`, `-`** → addizione, sottrazione
    

Esempio famoso della lezione:

```js
let x = 2 ** 10 * 2 + 10;
console.log(x);
```

L’ordine reale è:

1. `2 ** 10` → 1024
    
2. `1024 * 2` → 2048
    
3. `2048 + 10` → 2058
    

Quindi:

```js
console.log(x); // 2058
```

Se avessi scritto:

```js
let x = 2 * 2 ** 10 + 10;
```

il risultato sarebbe **lo stesso**, perché comunque la potenza ha precedenza sulla moltiplicazione.

---

### **3. Non solo matematica: assegnazione, `typeof`, confronti**

La precedenza non riguarda solo `+`, `-`, `*`, `/`.  
Entra in gioco anche con cose come:

- **assegnazione** → `=`
    
- **confronti** → `<`, `>`, `<=`, `>=`, `==`, `===`, …
    
- **`typeof`**
    
- ecc.
    

L’idea generale è:

> Prima si valutano **le espressioni a destra**, poi il risultato viene **assegnato** alla variabile a sinistra.

#### **3.1 Esempio con `typeof`**

```js
let x = typeof 2;

console.log(x);        // "number"
console.log(typeof x); // "string"
```

Ordine:

1. JavaScript vede `typeof 2` → calcola il tipo → `"number"`
    
2. solo **dopo** fa `x = "number"`.
    

Quindi:

- `typeof` ha precedenza sull’assegnazione `=`.
    
- In generale, **quasi tutto** viene valutato **prima** di `=`.
    

---

### **4. Precedenza con le espressioni di confronto**

Ora un esempio con i **confronti**:

```js
let confronto = 2 < 10;
console.log(confronto); // true
```

Fin qui ovvio.

Ma cosa succede se scrivi:

```js
let confronto = 2 < 10 * 5;
console.log(confronto);
```

Ordine di esecuzione:

1. prima viene valutato `10 * 5` → 50 (moltiplicazione)
    
2. poi si valuta `2 < 50` → `true`
    
3. il valore `true` viene assegnato a `confronto`
    

Se cambi:

```js
let confronto = 50 < 10 * 2;
console.log(confronto); // false, perché 50 < 20 è falso
```

E se vuoi vedere il valore “intermedio”, puoi fare:

```js
console.log(10 * 2); // 20
```

Quindi la catena logica è:

1. **aritmetica** (moltiplicazioni, somme, ecc.)
    
2. **confronto** (`<`, `>`, `==`, `===`, …)
    
3. **assegnazione** (`=`)
    

---

### **5. Un esempio combinato (operazioni + confronto + assegnazione)**

Guarda questa variante:

```js
let x = 20 == 10 * 2;
console.log(x); // true
```

Ordine:

1. `10 * 2` → 20
    
2. `20 == 20` → `true`
    
3. `x = true`
    

Se cambi valore:

```js
let x = 20 == 10 * 3;
console.log(x); // false
```

Perché:

1. `10 * 3` → 30
    
2. `20 == 30` → `false`
    
3. `x = false`
    

La cosa importante da interiorizzare è:

- la **comparazione** (`==`, `<`, `>`, ecc.) vede come operandI delle **espressioni già risolte**,
    
- quindi “aspetta” che la parte aritmetica sia completata, poi confronta.
    

---

### **6. Collegamento con incremento/decremento e assegnazione**

Esempio con incremento:

```js
let x = 10;
x = x + 1;     // prima valuta x + 1 (11), poi assegna
```

Con l’operatore `++`:

```js
let x = 10;
x++;           // x diventa 11
```

E con assegnazione più complessa:

```js
let y = 5;
let z = (y += 3) * 2;
```

Ordine (senza stressarti con tutti i dettagli, solo concetto):

1. `y += 3` è un’assegnazione “estesa”: equivale a `y = y + 3` → `y` diventa `8`.
    
2. il valore dell’espressione `y += 3` è `8`.
    
3. `z = 8 * 2` → `z` diventa `16`.
    

Questo tipo di “mini-casini” di precedenza/ordine di valutazione lo capisci al 100% **facendo pratica**, non studiando la tabella a memoria.

---

### **7. Precedenza: come usarla davvero nella pratica**

Cose importanti da tenere a mente:

1. **Non serve imparare a memoria tutta la tabella** (tipo W3Schools).
    
2. Devi ricordare bene solo questi principi:
    
    - le **parentesi** vincono su tutto;
        
    - `**` (potenza) viene prima di `*`, `/`, `%`;
        
    - `*`, `/`, `%` vengono prima di `+`, `-`;
        
    - prima si valutano le espressioni a destra, poi si fa l’**assegnazione** a sinistra;
        
    - i **confronti** (`<`, `>`, `==`, `===`) vedono sempre risultati di espressioni già calcolate.
        
3. Se anche hai **un minimo dubbio** su come verrà valutata un’espressione:
    
    - aggiungi parentesi e rendi l’ordine **esplicito**,
        
    - oppure spezza il codice in più righe:
        

```js
let base   = 10 * 2;
let check  = 20 == base;
let finale = check;
```

Questo è **molto più leggibile** e riduce tantissimo gli errori logici.

---

### **8. Riepilogo**

- La **precedenza degli operatori** decide **chi viene eseguito prima** in un’espressione con più operatori.
    
- Per i numeri:
    
    - `()` → prima di tutto
        
    - `**` → potenza
        
    - `*`, `/`, `%`
        
    - `+`, `-`
        
- Operatori come `typeof`, confronti (`<`, `>`, `==`, `===`) e assegnazione (`=`) hanno anche loro un ordine:
    
    - prima le espressioni, poi il confronto, poi l’assegnazione.
        
- Non serve conoscere a memoria la tabella completa:
    
    - basta capire la **logica generale**
        
    - e usare **parentesi** quando vuoi essere sicuro di cosa succede.
        

Quando passerai a condizioni (`if`), cicli, funzioni più complesse, questa cosa ti eviterà bug mentali del tipo “perché diavolo qui è `true` invece di `false`?”.