## **Lezione 20: Metodi di ricerca sulle stringhe in JavaScript**

In questa lezione vediamo i **metodi delle stringhe** che servono per **cercare** sottostringhe o caratteri dentro una stringa:

- `indexOf`, `lastIndexOf`
    
- `search`
    
- `match`, `matchAll`
    
- `includes`
    
- `startsWith`, `endsWith`
    
- concetto di **case sensitive**
    

Useremo sempre come stringa di riferimento:

```js
let x = 'Ciao sono Edoardo';
```

---

### **1. `indexOf()` – primo indice in cui compare qualcosa**

```js
let x = 'Ciao sono Edoardo';

console.log(x.indexOf('Edoardo'));   // 10
```

- `indexOf('Edoardo')` ti dice **da quale indice** inizia la sottostringa `'Edoardo'`.
    
- Conta sempre da **0**:
    
    - `C` → indice 0
        
    - `i` → 1
        
    - `a` → 2
        
    - `o` → 3
        
    - spazio → 4
        
    - `s` → 5
        
    - `o` → 6
        
    - `n` → 7
        
    - `o` → 8
        
    - spazio → 9
        
    - `E` → **10** (inizio di `"Edoardo"`)
        

Se cerchi una **sottostringa più lunga**, ti dà comunque **solo l’indice del primo carattere** di quella sottostringa:

```js
console.log(x.indexOf('sono'));   // indice dove inizia 'sono'
```

Se **non trova** ciò che cerchi, ritorna `-1`:

```js
console.log(x.indexOf('pippo'));  // -1
console.log(x.indexOf('z'));      // -1
```

Lo stesso vale per un **singolo carattere**:

```js
console.log(x.indexOf('a'));      // 2 (la prima 'a')
```

> `indexOf` restituisce **solo la prima occorrenza**.

---

### **2. `lastIndexOf()` – ultimo indice in cui compare qualcosa**

`lastIndexOf` fa la stessa cosa di `indexOf`, ma partendo **da destra**:

```js
let x = 'Ciao sono Edoardo';

console.log(x.lastIndexOf('a'));
// indice dell’ultima 'a' nella stringa
```

Esempio dal video (ricostruito):

- Le `a` in `"Ciao sono Edoardo"` sono:
    
    - `a` a indice 2 (`Ci**a**o`)
        
    - `a` in `Edo**a**rdo`
        
- `x.indexOf('a')` → `2` (la prima)
    
- `x.lastIndexOf('a')` → indice dell’ultima `a` (quella in `Edoardo`)
    

Anche qui, se non trova niente → `-1`.

---

### **3. `search()` – simile a `indexOf`, ma pensato per le regex**

Di base:

```js
let x = 'Ciao sono Edoardo';

console.log(x.search('Edoardo'));   // 10
```

Per **stringhe normali** il comportamento è praticamente come `indexOf`.

La **differenza importante** è che `search` è pensato per lavorare con le **regular expression**:

```js
x.search(/Edoardo/);  // uso di una regex
```

Nel corso, per ora:

- `indexOf` → cerca stringhe “normali”
    
- `search` → lo incontri soprattutto insieme alle **regex** (che vedrai più avanti)
    

---

### **4. `match()` – trovare corrispondenze**

`match` cerca **corrispondenze** (match) in una stringa.

Se passi una **stringa semplice**:

```js
let x = 'Ciao sono Edoardo';

let risultato = x.match('a');
console.log(risultato);
```

Otterrai qualcosa del tipo:

```js
[
  'a',        // la sottostringa trovata
  index: 2,   // indice dove è stata trovata
  input: 'Ciao sono Edoardo',
  groups: undefined
]
```

Quindi `match` ti dice:

- cosa ha trovato (`'a'`)
    
- dove (`index: 2`)
    
- su quale stringa (`input: ...`)
    

Con le **regex**, `match` diventa potente (più avanti nel corso).

---

### **5. `matchAll()` – tutte le corrispondenze (iterator)**

```js
let x = 'Ciao sono Edoardo';

let iterator = x.matchAll('a');
console.log(iterator);
// output: RegExp String Iterator
```

`matchAll` restituisce un **iteratore** con tutte le match.  
In pratica, lo usi così (con una regex):

```js
let x = 'Ciao sono Edoardo';

for (const m of x.matchAll(/a/g)) {
  console.log(m[0], 'a indice', m.index);
}
```

Per ora, concetto da parcheggiare:

- sapendo che `matchAll` → **tutte** le corrispondenze,
    
- `match` → si usa spesso per la **prima** o per un set di match con regex.
    

---

### **6. `includes()` – controllare se una sottostringa è presente**

`includes` è uno dei metodi più comodi: ti dice semplicemente **sì/no** se la stringa ne contiene un’altra.

```js
let x = 'Ciao sono Edoardo';

console.log(x.includes('Edoardo'));  // true
console.log(x.includes('Edvardo'));  // false
console.log(x.includes('a'));        // true
console.log(x.includes('z'));        // false
```

Ritorna sempre un **booleano**: `true` o `false`.

Usalo quando ti interessa solo sapere **“c’è o non c’è”**, senza richiedere la posizione.

---

### **7. `startsWith()` e `endsWith()` – inizio e fine stringa**

#### **7.1. `startsWith()`**

Controlla se la stringa **comincia** con una certa sottostringa:

```js
let x = 'Ciao sono Edoardo';

console.log(x.startsWith('C'));      // true
console.log(x.startsWith('Ciao'));   // true
console.log(x.startsWith('ciao'));   // false (case sensitive)
```

#### **7.2. `endsWith()`**

Controlla se la stringa **finisce** con una certa sottostringa:

```js
let x = 'Ciao sono Edoardo';

console.log(x.endsWith('Edoardo'));  // true
console.log(x.endsWith('do'));       // true
console.log(x.endsWith('edoardo'));  // false (case sensitive)
```

---

### **8. Case sensitive: attenzione a maiuscole/minuscole**

Quasi tutti questi metodi (`indexOf`, `includes`, `startsWith`, `endsWith`, `search` con stringa normale) sono **case sensitive**:

- `'C'` diverso da `'c'`
    
- `'Edoardo'` diverso da `'edoardo'`
    

Esempio:

```js
let x = 'Ciao sono Edoardo';

console.log(x.startsWith('C'));   // true
console.log(x.startsWith('c'));   // false
```

Se vuoi fare un controllo “ignorando le maiuscole/minuscole”, tipica tecnica:

```js
let x = 'Ciao sono Edoardo';

console.log(x.toLowerCase().startsWith('ciao'));  // true
console.log(x.toLowerCase().includes('edoardo')); // true
```

---

### **9. Riepilogo veloce**

Metodi visti:

- **`indexOf(substring)`**  
    → indice della **prima** occorrenza, oppure `-1` se non trovata.
    
- **`lastIndexOf(substring)`**  
    → indice dell’**ultima** occorrenza, oppure `-1`.
    
- **`search(pattern)`**  
    → simile a `indexOf`, ma pensato per le **regex**.
    
- **`match(pattern)`**  
    → ritorna info sulla **prima match** (o set di match con regex).
    
- **`matchAll(pattern)`**  
    → iteratore con **tutte** le match (di solito con regex e flag `g`).
    
- **`includes(substring)`**  
    → `true`/`false` se la stringa contiene quella sottostringa.
    
- **`startsWith(substring)`**  
    → `true` se la stringa **inizia** con quella sottostringa.
    
- **`endsWith(substring)`**  
    → `true` se la stringa **termina** con quella sottostringa.
    
- Tutti questi confronti sono **case sensitive**.
    

Con questi strumenti hai un “pacchetto base” completo per fare **ricerche** dentro le stringhe.  
Nel prossimo passo, i **template literal** renderanno la costruzione di stringhe complesse molto più leggibile e meno “piena di `+`”.