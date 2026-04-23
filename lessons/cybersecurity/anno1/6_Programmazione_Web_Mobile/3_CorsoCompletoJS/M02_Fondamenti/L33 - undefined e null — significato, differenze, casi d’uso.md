## **Lezione 33: `undefined` e `null` — significato, differenze, casi d’uso**


---

## **1. Introduzione**

In JavaScript i valori _mancanti_ possono presentarsi in due modi:

- **`undefined`**
    
- **`null`**
    

A prima vista sembrano identici.  
In realtà hanno **ruoli diversi**, **cause diverse** e **semantica diversa**.

L’obiettivo della lezione è capire:

- **quando nasce `undefined`**
    
- **quando scegliere `null`**
    
- **perché uno è implicito e l’altro è esplicito**
    
- **come interpretarli quando compaiono nei console log**
    

---

## **2. `undefined`: assenza _implicita_ di valore**

### **2.1. Definizione intuitiva**

> **`undefined` significa: “questa variabile esiste, ma non le è stato assegnato nessun valore”.**

È il valore che appare **automaticamente** quando:

- dichiari una variabile **senza** inizializzarla
    
- chiami una funzione che **non restituisce** nulla
    
- provi a leggere proprietà o elementi **non esistenti**
    
- il programma si aspetta un valore, ma **quel valore non arriva**
    

### **2.2. Esempio 1 — Variabile non inizializzata**

```js
let x;
console.log(x); 
```

Output:

```
undefined
```

- La variabile **esiste**
    
- Ma non le è stato assegnato nulla
    

### **2.3. Esempio 2 — Funzione senza return**

```js
function saluta() {
  console.log("Ciao!");
}

console.log( saluta() ); 
```

Output:

```
Ciao!
undefined
```

- La funzione **non restituisce nulla**
    
- Il valore di ritorno è `undefined`
    

### **2.4. Esempio 3 — Accesso a proprietà inesistente**

```js
const persona = { nome: "Luca" };
console.log(persona.cognome);
```

Output:

```
undefined
```

---

## **3. `null`: assenza _esplicita_ di valore**

### **3.1. Definizione intuitiva**

> **`null` significa: “qui NON c’è un valore, e l’ho deciso io, il programmatore”.**

È una scelta **intenzionale**.

Lo usi quando:

- vuoi rappresentare una informazione _momentaneamente vuota_
    
- vuoi dire “questo valore non è ancora disponibile”
    
- vuoi segnalare _volontariamente_ che ci sarà un valore “più avanti”
    

### **3.2. Esempio — Valore non ancora noto**

```js
const persona = {
  nome: "Alice",
  eta: 30,
  cognome: null  // cognome non ancora specificato
};
```

`null` comunica CHIARAMENTE:

- _Non è un errore_
    
- _Non l’ho dimenticato_
    
- _Il valore è volutamente assente_
    

---

## **4. Perché `undefined` ≠ `null`?**

### **4.1. Differenza di origine**

|Aspetto|`undefined`|`null`|
|---|---|---|
|Chi lo mette?|**JavaScript** automaticamente|**Il programmatore**|
|Significa|Valore assente per errore/dimenticanza|Assenza _intenzionale_ di valore|
|Tipo di assenza|Implicita|Esplicita|
|Tipico scenario|Variabile non inizializzata|“Non c’è un valore, e va bene così”|

---

## **5. Situazioni tipiche nella vita reale**

### **5.1. `undefined` come “errore logico”**

Se ottieni `undefined`, spesso vuol dire:

- ti sei dimenticato di inizializzare una variabile
    
- la funzione non restituisce nulla
    
- i dati non sono arrivati
    
- hai letto qualcosa che non esiste
    

È un **campanello d’allarme**.

### **5.2. `null` come scelta consapevole**

Usi `null` quando:

- vuoi preparare una variabile per un valore futuro
    
- vuoi “azzerare” qualcosa
    
- vuoi indicare _esplicitamente_ un valore mancante ma previsto
    

---

## **6. Confronto diretto e finale (regola d’oro)**

### **6.1. Se il valore manca perché lo hai dimenticato → `undefined`**

```js
let x;
console.log(x);  // undefined
```

### **6.2. Se il valore manca perché È UNA SCELTA → `null`**

```js
let rispostaServer = null;
```

### **6.3. Pensala così:**

- **`undefined` = non definito dal programmatore (errore / mancanza non intenzionale)**
    
- **`null` = definito dal programmatore come vuoto (mancanza intenzionale)**
    

---

## **7. Una metafora semplice e perfetta**

- **`undefined`** è come entrare in una stanza e non trovare l’interruttore perché nessuno lo ha mai installato.
    
- **`null`** è trovare l’interruttore, ma scoprirlo deliberatamente spento.
    

---

## **8. Sintesi completa (da ricordare sempre)**

- `undefined` = assenza di valore **generata dal programma**
    
- `null` = assenza di valore **decisa dal programmatore**
    
- `undefined` spesso indica **errore o omissione**
    
- `null` indica una **scelta chiara e intenzionale**
    

---

Questa è la versione Obsidian-ready, completa e Feynman-style.  
Se vuoi, procediamo con la prossima lezione: **le Date in JavaScript**.