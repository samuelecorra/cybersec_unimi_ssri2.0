## **Lezione 21: Template Literals in JavaScript**

## **1. Introduzione ai Template Literals**

I **template literals** sono un tipo speciale di stringa introdotto con **ES6 (2016)**.  
Sono scritti usando **i backtick** (`` ` ``), non gli apici `' "`.

```js
let x = `Ciao sono Edoardo`;
```

Sembrano semplici stringhe… ma sono _molto_ più potenti.  
Sono nati per risolvere problemi reali che le stringhe normali gestiscono male:

- concatenazione lunga → codice illeggibile
    
- inserire variabili dentro una stringa → pieno di `+`
    
- testo multilinea → impossibile con stringhe normali
    
- generare HTML, email, template → difficilissimo prima dei template literals
    

---

## **2. Prima grande differenza: funzionano su più righe**

Con le stringhe normali:

```js
let x = "Ciao
sono
Edoardo";   // ERRORE
```

Con i template literals:

```js
let x = `
Ciao
sono
Edoardo
`;

console.log(x);
```

Ottieni una **stringa multilinea vera**, perfetta per:

- email
    
- template HTML lato server (SSR)
    
- porzioni di codice generate dinamicamente
    
- testo complesso su più righe
    

---

## **3. Inserire variabili dentro la stringa (“interpolazione”)**

Prima dovevamo fare:

```js
let nome = 'Edoardo';
let y = 'come va';

let frase = "Ciao sono " + nome + " " + y + " e ho " + (2 + 2) + " anni";
```

Uno **sbatti cosmico**.  
Mille `+`, mille virgolette, mille spazi. Pericoloso e illeggibile.

Con i template literals:

```js
let nome = 'Edoardo';

let frase = `Ciao sono ${nome}`;
```

Il risultato è:

```
Ciao sono Edoardo
```

Il simbolo `${ ... }` permette di inserire **codice JavaScript**.  
Questa è la **potenza dei template literals**.

---

## **4. Interpolazione = codice dinamico dentro una stringa**

Dentro `${ ... }` puoi mettere **qualsiasi espressione**, ovvero qualsiasi pezzo di codice che ritorna un valore.

Esempi:

### **4.1 Un’espressione matematica**

```js
`2 + 2 = ${2 + 2}`
```

→ `2 + 2 = 4`

### **4.2 Una proprietà di stringa**

```js
let nome = 'Edoardo';

`Il mio nome inizia con: ${nome[0]}`
```

→ `Il mio nome inizia con: E`

### **4.3 Un metodo**

```js
`${nome.toUpperCase()}`
```

→ `EDOARDO`

### **4.4 Qualsiasi combinazione**

```js
`${nome.toUpperCase()[0]} ha ${10 + 21} anni`
```

---

## **5. Cosa NON puoi inserire**

Dentro `${ ... }` puoi mettere solo **espressioni**, non **istruzioni**.

Quindi NO:

- dichiarazioni `let`, `const`, `var`
    
- `if`
    
- `for`, `while`
    
- definizioni di funzione
    

```js
`${ let x = 10 }`      // NO
`${ if (true) { } }`   // NO
```

Perché?  
Perché un template literal deve **generare un valore** in ogni punto.

---

## **6. Esempio completo ricostruito dal video**

```js
const nome = 'Edoardo';
const città = 'New York';
const età = 10;

const x = `
Ciao sono ${nome}
vivo a ${città}
e ho ${età + 21} anni.
`;

console.log(x);
```

Output:

```
Ciao sono Edoardo
vivo a New York
e ho 31 anni.
```

Tutto leggibile, pulito, chiaro.

---

## **7. Perché sono migliori della concatenazione?**

Perché la concatenazione:

- è brutta
    
- è lunga
    
- è fragile
    
- è difficilissima da leggere
    
- rompe la formattazione
    

Un esempio equivalente in concatenazione sarebbe così:

```js
"Ciao sono " + nome + "\nvivo a " + città + "\ne ho " + (età + 21) + " anni."
```

Brutto. Pieno di escape.  
Difficile da mantenere.  
Illeggibile se diventa più complesso.

I template literal hanno vinto **ogni battaglia**.

---

## **8. Template Literal = scelta standard nel 2025**

Dopo ES6, tutta la comunità JS ha praticamente abbandonato la concatenazione manuale.

I motivi:

- più leggibili
    
- permettono la multilinea
    
- interpolazione elegante
    
- perfetti per HTML generato
    
- riducono errori di battitura
    

Oggi si usano OVUNQUE:

- React / Next.js SSR
    
- Node.js
    
- Email templating
    
- Generazione XML/JSON
    
- Script complessi lato server
    

---

## **9. Menzione speciale: le “tagged template functions”**

Edo nel video dice:

> sono potentissime, ma servono le funzioni prima.

È corretto.  
Le tag function permettono di trasformare i template literals in **mini-linguaggi personalizzati**:

```js
funzioneTag`Ciao sono ${nome}`;
```

JavaScript scompone la stringa e i valori, e ti permette di manipolarli come vuoi.

Le vedremo **molto più avanti**, quando avrai basi solide sulle funzioni.

---

## **10. Mini-riassunto della lezione**

### **Template literals:**

- si scrivono con `` ` ``
    
- supportano **multilinea**
    
- supportano **interpolazione** `${ ... }`
    
- accettano **solo espressioni**
    
- sono usati praticamente ovunque nel JS moderno
    

Sono la versione “definitiva” delle stringhe in JavaScript.

---

Vuoi procedere con gli **esercizi sulle stringhe** o passiamo prima alla prossima lezione del corso?