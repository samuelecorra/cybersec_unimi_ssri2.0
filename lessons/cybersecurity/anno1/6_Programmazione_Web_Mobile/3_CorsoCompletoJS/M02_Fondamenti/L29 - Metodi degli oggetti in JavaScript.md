## **Lezione 29: Metodi degli oggetti in JavaScript**

---

### **1. Due “famiglie” di metodi per gli oggetti**

Quando parliamo di “metodi degli oggetti” in JavaScript in realtà stiamo parlando di due cose diverse:

1. **Metodi della classe globale `Object`**
    
    - Sono funzioni “statiche” che si chiamano così:
        
    
    ```js
    Object.keys(...)
    Object.values(...)
    Object.entries(...)
    Object.freeze(...)
    Object.isFrozen(...)
    ```
    
    - Lavorano **su un oggetto passato come argomento** (non su `this`).
        
2. **Metodi definiti dentro un singolo oggetto**
    
    - Sono **proprietà il cui valore è una funzione**:
        
    
    ```js
    const persona = {
      nome: "Luca",
      presentati: function () { ... }
    };
    ```
    
    - Si chiamano con la dot notation:
        
    
    ```js
    persona.presentati();
    ```
    

Per ora le funzioni non le hai ancora studiate a fondo, quindi tienilo così:

> Un “metodo” è semplicemente **una funzione salvata dentro un oggetto** come proprietà.

---

### **2. Metodi statici di `Object`**

Supponiamo di avere:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,
  hobby: ["calcio", "tennis"],
  isOnline: true,
  prova: "Ciao sono Edo",
};
```

#### **2.1 `Object.keys(obj)` – elenco delle chiavi**

- Firma (semplificata):
    

```ts
Object.keys(obj: object) => string[]
```

- Restituisce un **array di stringhe** con i nomi delle proprietà (le chiavi) dell’oggetto.
    

```js
const chiavi = Object.keys(persona);
console.log(chiavi);
// ["nome", "cognome", "eta", "hobby", "isOnline", "prova"]
```

Se vuoi le chiavi di un sotto–oggetto:

```js
const indirizzo = {
  via: "Viale le mani da qui",
  civico: 23,
  cap: "89670",
  citta: "Milano",
  provincia: "MI",
  paese: "IT",
};

const chiaviIndirizzo = Object.keys(indirizzo);
console.log(chiaviIndirizzo);
// ["via", "civico", "cap", "citta", "provincia", "paese"]
```

🔎 **Uso tipico** (che vedrai dopo): ciclare dinamicamente sulle proprietà di un oggetto.

---

#### **2.2 `Object.values(obj)` – elenco dei valori**

- Restituisce un **array con i valori** delle proprietà:
    

```js
const valori = Object.values(persona);
console.log(valori);
// ["Luca", "Rossi", 25, ["calcio", "tennis"], true, "Ciao sono Edo"]
```

Stesso ordine di `Object.keys`: indice 0 della chiave corrisponde all’indice 0 del valore.

---

#### **2.3 `Object.entries(obj)` – coppie [chiave, valore]**

- Restituisce un **array di array**, ognuno lungo 2:
    

```js
const entries = Object.entries(persona);
console.log(entries);
/*
[
  ["nome", "Luca"],
  ["cognome", "Rossi"],
  ["eta", 25],
  ["hobby", ["calcio", "tennis"]],
  ["isOnline", true],
  ["prova", "Ciao sono Edo"],
]
*/
```

Ogni elemento è una coppia:

- posizione `0` → nome della proprietà
    
- posizione `1` → valore della proprietà
    

🔎 Molto utile combinato con i cicli (`for`, `for...of`, ecc.: li vedrai dopo).

---

#### **2.4 `Object.freeze(obj)` – rendere l’oggetto immutabile**

`Object.freeze` **congela** un oggetto:

- non puoi più:
    
    - modificare valori delle proprietà esistenti,
        
    - aggiungere nuove proprietà,
        
    - eliminare proprietà.
        

Esempio:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,
  prova: "Ciao sono Edo",
};

Object.freeze(persona);

// Tentativi di modifica:
persona.nome = "Marco";         // ignorato
persona.prova = "Ciao sono Luca"; // ignorato
persona.nuovaProp = 123;        // ignorato
delete persona.cognome;         // ignorato

console.log(persona);
// resta invariata
```

Il codice **non esplode**: semplicemente le modifiche non hanno effetto (in strict mode certe operazioni lanciano errore, ma concettualmente l’oggetto è “bloccato”).

---

#### **2.5 `Object.isFrozen(obj)` – verificare se è congelato**

Per sapere se un oggetto è stato “freezato”:

```js
console.log(Object.isFrozen(persona)); // true o false
```

Esempio comparativo:

```js
const persona1 = { nome: "Luca" };
const persona2 = { nome: "Marco" };

Object.freeze(persona1);

console.log(Object.isFrozen(persona1)); // true
console.log(Object.isFrozen(persona2)); // false
```

---

### **3. Metodi definiti dentro un oggetto (funzioni come proprietà)**

Finora i tuoi oggetti avevano solo **dati** (nome, cognome, età…).  
Ma possiamo anche aggiungere **comportamenti**, cioè funzioni salvate come proprietà.

#### **3.1 Oggetto persona con metodi**

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,

  presentati: function () {
    console.log(`Ciao, sono ${this.nome} ${this.cognome} e ho ${this.eta} anni.`);
  },

  cambiaNome: function () {
    this.nome = "QWERTY";
  },
};
```

Qui succede una cosa importante:

- `presentati` è una **proprietà** il cui valore è una **funzione** → la chiamiamo **metodo**.
    
- Dentro il metodo usi `this`:
    
    - `this.nome` → si riferisce al `nome` di **questo oggetto** (`persona`).
        
    - `this.cognome`, `this.eta`, ecc.
        

Chiamata dei metodi:

```js
persona.presentati();
// "Ciao, sono Luca Rossi e ho 25 anni."

persona.cambiaNome();
persona.presentati();
// "Ciao, sono QWERTY Rossi e ho 25 anni."
```

---

#### **3.2 Perché usare un metodo invece di un semplice `console.log` fuori?**

Versione **senza metodo**, tutto “fuori”:

```js
console.log(
  `Ciao, sono ${persona.nome} ${persona.cognome} e ho ${persona.eta} anni.`
);
```

Versione **ad oggetti**:

```js
persona.presentati();
```

Differenza concettuale:

- fuori dall’oggetto:
    
    - sei tu che ti arrangi ogni volta a costruire il messaggio
        
    - devi ricordarti il formato, le `${...}`, ecc.
        
- dentro l’oggetto:
    
    - l’oggetto **sa da solo** come presentarsi
        
    - usi un linguaggio più naturale: `persona.presentati()`
        

Questa è la base della **programmazione ad oggetti**:

> Oggetto = dati + comportamenti che hanno senso per quell’oggetto.

---

#### **3.3 Il tipo di un metodo: è una `function`**

Se controlli il tipo di un metodo:

```js
console.log(typeof persona.presentati); // "function"
```

Quindi:

- una funzione può essere trattata come **valore**
    
- puoi salvarla in una proprietà di un oggetto
    
- quando fai `persona.presentati()` la **esegui**
    

---

### __4. Collegare tutto: Object._ + metodi interni_*

Ricapitolando cosa hai ora sugli oggetti:

- Oggetti come **strutture di dati**:
    
    - coppie chiave–valore
        
    - valori di qualsiasi tipo (numeri, stringhe, booleani, array, altri oggetti, funzioni)
        
- Metodi statici di `Object` per lavorare “da fuori”:
    
    - `Object.keys(obj)` → lista delle chiavi
        
    - `Object.values(obj)` → lista dei valori
        
    - `Object.entries(obj)` → coppie `[chiave, valore]`
        
    - `Object.freeze(obj)` → blocca ulteriori modifiche
        
    - `Object.isFrozen(obj)` → verifica se è congelato
        
- Metodi interni all’oggetto:
    
    - funzioni salvate come proprietà (`presentati`, `cambiaNome`, …)
        
    - usano `this` per riferirsi ai dati dell’oggetto stesso
        

---

### **5. Mini–esercizi veloci (mentali)**

Puoi provarli al volo per fissare:

1. Crea un oggetto `libro` con proprietà:
    
    - `titolo`, `autore`, `anno`
        
    - aggiungi un metodo `descrivi()` che stampa:  
        `"Titolo: ... - Autore: ... - Anno: ..."`
        
2. Usa `Object.keys(libro)` e `Object.values(libro)` e guarda cosa restituiscono.
    
3. Applica `Object.freeze(libro)` e prova a cambiare `libro.titolo`.  
    Controlla `Object.isFrozen(libro)`.
    