## **Lezione 19: Concatenazione e primi metodi delle stringhe**

Continuiamo a lavorare con le **stringhe** in JavaScript.  
In questa lezione vediamo:

1. **Come concatenare stringhe** (metterle una dopo l’altra).
    
2. **Un primo gruppo di metodi importanti** per manipolare le stringhe.
    

Useremo come esempio principale:

```js
let x = 'Ciao sono Edoardo';
```

---

### **1. Concatenazione di stringhe (`+`)**

La **concatenazione** è semplicemente “attaccare” più stringhe una di seguito all’altra.

Esempio base:

```js
let ciaoSono = 'Ciao sono ';
let nome = 'Edoardo';

console.log(ciaoSono + nome); 
// 'Ciao sono Edoardo'
```

Notiamo subito un dettaglio:

- Se **non metti lo spazio** dentro `ciaoSono` → ottieni `'Ciao sonoEdoardo'`.
    
- Lo spazio deve comparire **da qualche parte**:
    

```js
let ciaoSono = 'Ciao sono ';
let nome = 'Edoardo';

console.log(ciaoSono + nome);    // 'Ciao sono Edoardo'
```

Oppure:

```js
let ciaoSono = 'Ciao sono';
let nome = 'Edoardo';

console.log(ciaoSono + ' ' + nome); 
// 'Ciao sono Edoardo'
```

> Regola pratica: per concatenare stringhe si usa l’operatore **`+`**.  
> Internamente non è “somma”, è proprio **concatenazione**.

#### **1.1. Concatenazione con valori non stringa**

Se uno dei due operandi è una **stringa**, e l’altro è un **numero**, JavaScript **coercizza** il numero in stringa e li attacca:

```js
let base = 'Ciao sono ';
let anni = 25;

console.log(base + anni); 
// 'Ciao sono 25'
```

Questo è di fatto lo stesso meccanismo che avevi già visto quando scrivevi `'123' + 1` ottenendo `'1231'`.

---

### **2. Metodi delle stringhe: prima parte**

Ripartiamo da una stringa:

```js
let x = 'Ciao sono Edoardo';
```

Ricorda:

- I **metodi** delle stringhe si chiamano con la sintassi:  
    `x.qualcosa(...)`
    
- Le **proprietà** senza parentesi (es. `.length`).
    

---

### **2.1. `.length`: lunghezza della stringa**

`length` restituisce il **numero di caratteri** contenuti nella stringa.

```js
let x = 'Ciao sono Edoardo';

console.log(x.length);
// ad esempio: 18 (in base agli spazi / caratteri esatti)
```

---

### **2.2. `.slice(start, end)`: estrarre una “fetta”**

`slice` prende una **sottostringa** a partire da un indice iniziale, e opzionalmente fino a un indice finale **non incluso**.

```js
let x = 'Ciao sono Edoardo';

// Dalla posizione 10 fino alla fine
let prova1 = x.slice(10);
console.log(prova1);
// 'Edoardo' (se la 'E' è a indice 10)

// Da 10 a 12 (12 escluso)
let prova2 = x.slice(10, 12);
console.log(prova2);
// 'Ed'
```

#### **Indici negativi con `slice`**

Con `slice` puoi usare **indici negativi** per contare dalla fine:

```js
let x = 'Ciao sono Edoardo';

// Ultimo carattere
console.log(x.slice(-1)); 
// 'o'

// Ultimi 7 caratteri
console.log(x.slice(-7)); 
// 'Edoardo'
```

La logica:

- `slice(-1)` → parte dall’ultimo carattere.
    
- `slice(-7)` → parte dal settimo carattere partendo da destra e va fino alla fine.
    

Se usi **due** indici negativi, devi comunque rispettare l’ordine (start < end).

---

### **2.3. `substring` e `substr` (nota veloce)**

Esistono anche `substring` e `substr`, ma:

- `substr` è **deprecato** (sconsigliato, può sparire/rompersi).
    
- In JavaScript moderno conviene usare **quasi sempre `slice`**.
    

Se vedi `substr` con un IDE che ti segnala “deprecated”, prendilo come sintomo:

> “Cerca alternativa (tipicamente `slice`)”.

---

### **2.4. `replace` e `replaceAll`**

Servono a **sostituire** parti di stringa.

```js
let x = 'Ciao sono Edoardo';

let y = x.replace('Edoardo', 'Querti');
console.log(y);
// 'Ciao sono Querti'
```

> `replace` sostituisce **solo la prima occorrenza**.

Esempio:

```js
let x = 'Edoardo Edoardo';

console.log(x.replace('Edoardo', 'Querti'));
// 'Querti Edoardo'  (solo il primo)
```

Se vuoi sostituire **tutte** le occorrenze, usi `replaceAll`:

```js
let x = 'Edoardo Edoardo';

console.log(x.replaceAll('Edoardo', 'Querti'));
// 'Querti Querti'
```

Altro esempio con un singolo carattere:

```js
let x = 'Ciao sono Edoardo';

console.log(x.replace('a', 'W'));
// sostituisce solo la **prima** 'a'

console.log(x.replaceAll('a', 'W'));
// sostituisce **tutte** le 'a'
```

---

### **2.5. `toLowerCase()` e `toUpperCase()`**

Per convertire la stringa tutta in **minuscolo** o tutta in **maiuscolo**:

```js
let x = 'Ciao sono Edoardo';

console.log(x.toLowerCase());
// 'ciao sono edoardo'

console.log(x.toUpperCase());
// 'CIAO SONO EDOARDO'
```

---

### **2.6. `concat(...)`: concatenare stringhe**

Invece di usare il `+`, puoi usare il metodo `concat`:

```js
let x = 'Ciao sono Edoardo';
let prova = 'Querti';

let risultato = x.concat(' ', prova);
console.log(risultato);
// 'Ciao sono Edoardo Querti'
```

`concat` accetta **più argomenti**:

```js
let risultato2 = x.concat(' ', prova, ' !!!');
console.log(risultato2);
// 'Ciao sono Edoardo Querti !!!'
```

In pratica:

- `x.concat(a, b, c)` ≈ `x + a + b + c`.
    

Nella pratica moderna, si usano moltissimo:

- `+` per concatenazioni semplici
    
- i **template literal** (`` `${...}` ``) per concatenazioni complesse (li vedrai dopo).
    

---

### **2.7. `trim()`, `trimStart()`, `trimEnd()`**

Servono a rimuovere **spazi bianchi** all’inizio e/o alla fine della stringa.

```js
let x = '     Ciao  ';
console.log('>' + x + '<');
// '>     Ciao  <'
```

- `trim()` → toglie spazi **iniziali e finali**:
    

```js
console.log('>' + x.trim() + '<');
// '>Ciao<'
```

- `trimStart()` → toglie solo **all’inizio**.
    
- `trimEnd()` → toglie solo **alla fine**.
    

Esempio:

```js
let y = '   Ciao   ';

console.log('>' + y.trimStart() + '<');
// '>Ciao   <'

console.log('>' + y.trimEnd() + '<');
// '>   Ciao<'
```

---

### **2.8. `charAt()` e accesso con `[]`**

Hai due modi equivalenti per prendere il carattere in una certa posizione:

```js
let x = 'Querti';

console.log(x.charAt(3));  // 'r'
console.log(x[3]);         // 'r'
```

Internamente:

- `charAt(3)` è la vecchia sintassi;
    
- `x[3]` è la sintassi “moderna” tipo array.
    

---

### **2.9. `charCodeAt()`: codice numerico del carattere**

Ogni carattere ha un **codice numerico** (codifica Unicode / UTF-16).

```js
let x = 'Querti';

console.log(x.charCodeAt(3));
// es: 114 → codice del carattere 'r'
```

Puoi incrociarlo con `Alt + codice` sulle tastiere Windows, o vedere le tabelle Unicode.

Esempio:

```js
let backtick = '`';
console.log(backtick.charCodeAt(0)); 
// 96 (codice del carattere `)
```

---

### **2.10. `split(separator)`: da stringa ad array di pezzi**

`split` è fondamentale: trasforma una stringa in una **lista di sottostringhe** (un array) in base a un separatore.

Immagina di ricevere:

```js
let x = 'Andrea Carlo Marco Pino';
```

Vuoi ottenere i singoli nomi separati.  
Il separatore è lo **spazio** `' '`:

```js
let nomi = x.split(' ');

console.log(nomi);
// ['Andrea', 'Carlo', 'Marco', 'Pino']
```

Ora `nomi` **non è più una stringa**, ma un **array** di stringhe:

- `nomi[0]` → `'Andrea'`
    
- `nomi[1]` → `'Carlo'`
    
- ecc.
    

Altro esempio: sostituire tutti gli spazi con virgole:

```js
let x = 'Andrea Carlo Marco Pino';

let conVirgole = x.replaceAll(' ', ',');
console.log(conVirgole);
// 'Andrea,Carlo,Marco,Pino'
```

---

### **3. Riepilogo rapido**

In questa lezione hai visto:

- **Concatenazione** con `+` (e la gestione degli spazi).
    
- Alcuni metodi fondamentali delle stringhe:
    
    - `.length` → numero di caratteri;
        
    - `.slice(start, end)` → estrarre una sottostringa (supporta indici negativi);
        
    - `replace` / `replaceAll` → sostituzione di parti di stringa;
        
    - `toLowerCase()` / `toUpperCase()` → tutto minuscolo / tutto maiuscolo;
        
    - `concat(...)` → alternative al `+`;
        
    - `trim()`, `trimStart()`, `trimEnd()` → rimozione spazi bianchi;
        
    - `charAt()`, `x[index]` → singolo carattere;
        
    - `charCodeAt()` → codice numerico del carattere;
        
    - `split(separator)` → stringa → array di sottostringhe.
        

Nel prossimo pezzo sulle stringhe potrai concentrarti su **ricerca**, **controlli** (inizia con…, include…, ecc.) e i famosi **template literal** che rendono tutto questo ancora più elegante e leggibile.