## **Lezione 30: Dot notation vs Bracket notation**

---

### **1. Dot notation: la notazione “puntata”**

**Dot notation** = notazione con il punto.

L’hai già usata ovunque:

```js
console.log(persona.nome);
persona.presentati();
persona.indirizzo.citta;
```

Caratteristiche:

- Usi il **punto** dopo il nome dell’oggetto.
    
- Dopo il punto puoi scrivere **solo identificatori validi JS**, cioè:
    
    - niente spazi
        
    - niente trattini
        
    - niente numeri all’inizio
        
    - niente variabili: dev’essere proprio scritto il nome della proprietà
        

Esempi validi:

```js
persona.nome
persona.cognome
persona.indirizzo.citta
```

Esempi **non validi**:

```js
persona."nome"       // ❌ sintassi non valida
persona['nome-cognome'] // con il punto non puoi
persona[variabile]   // col punto non puoi usare una variabile
```

---

### **2. Bracket notation: la notazione con le parentesi quadre**

La **bracket notation** usa le **parentesi quadre**:

```js
persona["nome"]
persona["cognome"]
persona["indirizzo"]["citta"]
```

L’hai già vista sugli array:

```js
const listaNumeri = [3, 5, 7];
listaNumeri[0]; // 3
listaNumeri[1]; // 5
```

Sugli oggetti funziona in modo analogo, ma invece dell’indice metti una **stringa con il nome della proprietà**:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  indirizzo: {
    citta: "Milano",
  },
};

// Equivalenti:
persona.cognome;
persona["cognome"];

// Equivalenti:
persona.indirizzo.citta;
persona["indirizzo"]["citta"];
```

Puoi anche **mischiare** le due notazioni:

```js
persona.indirizzo["citta"];
persona["indirizzo"].citta;
```

---

### **3. Quando dot e bracket sono equivalenti**

Sono equivalenti quando:

- la proprietà ha un **nome valido come identificatore JS**, ad esempio:
    
    - `nome`
        
    - `cognome`
        
    - `eta`
        
    - `indirizzo`
        
    - `hobby`
        

In questi casi puoi scegliere:

```js
persona.nome;
persona["nome"];
```

Entrambe funzionano.

---

### **4. Quando ti serve davvero la bracket notation: accesso dinamico**

Il caso in cui **la bracket notation è indispensabile** è quando il **nome della proprietà non è scritto nel codice**, ma ti arriva **dinamicamente**:

- da una variabile
    
- da un input dell’utente
    
- calcolato a runtime
    
- scelto in modo casuale
    

#### **4.1 Esempio: scegliere una proprietà a caso da un oggetto**

Partiamo da un oggetto:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,
  hobby: ["calcio", "tennis"],
  isOnline: true,
};
```

1. Prima estrai tutte le **chiavi** dell’oggetto con `Object.keys`:
    

```js
const keys = Object.keys(persona);
// Esempio: ["nome", "cognome", "eta", "hobby", "isOnline"]
```

2. Poi scegli una **posizione casuale** nell’array delle chiavi:
    

```js
const indiceCasuale = Math.floor(Math.random() * keys.length);
const chiaveCasuale = keys[indiceCasuale];

console.log(chiaveCasuale);
// Esempio di output: "hobby" oppure "nome" oppure "eta" ...
```

3. Ora vuoi il **valore** della proprietà corrispondente a `chiaveCasuale`.
    

Se provi con la dot notation:

```js
console.log(persona.chiaveCasuale); // ❌ sempre undefined
```

Perché?

- `persona.chiaveCasuale` cerca una proprietà **letteralmente chiamata** `"chiaveCasuale"`.
    
- Ma il tuo oggetto ha `nome`, `cognome`, `eta`, `hobby`, `isOnline`, non `chiaveCasuale`.
    

Quindi per usare il **contenuto della variabile** devi passare alla **bracket notation**:

```js
console.log(persona[chiaveCasuale]);
// Se chiaveCasuale = "hobby" → stampa ["calcio", "tennis"]
// Se chiaveCasuale = "eta"   → stampa 25
// ecc.
```

Questa è la situazione tipo:

> Quando il **nome della proprietà è in una variabile/stringa**, devi usare  
> `oggetto[variabile]` e non `oggetto.variabile`.

---

### **5. Altri casi in cui la bracket notation è obbligatoria**

Oltre al caso “dinamico”, devi usare la bracket notation quando:

1. La proprietà ha un nome **non valido come identificatore JS**:
    

```js
const utente = {
  "nome-completo": "Mario Rossi",
  "data di nascita": "2000-01-01",
};

utente["nome-completo"];    // ✅
utente["data di nascita"];  // ✅
// utente.nome-completo     // ❌ interpreta "-" come operatore meno
// utente.data di nascita   // ❌ sintassi non valida
```

2. Il nome della proprietà contiene caratteri speciali, numeri all’inizio, spazi, ecc.
    
3. Il nome della proprietà arriva **da fuori** (parametro di funzione, input utente, risultato di un calcolo…):
    

```js
function leggiProp(obj, propName) {
  return obj[propName]; // ✅ bracket notation
}

leggiProp(persona, "nome");  // "Luca"
leggiProp(persona, "hobby"); // ["calcio", "tennis"]
```

---

### **6. Regole pratiche da ricordare**

- **Usa la dot notation di default**:
    
    - È più leggibile, più corta, e l’editor ti offre meglio l’autocompletamento.
        
    - Esempio: `persona.nome`, `persona.indirizzo.citta`.
        
- **Passa alla bracket notation quando**:
    
    1. Il **nome della proprietà è in una variabile/stringa**  
        → `persona[chiaveCasuale]`
        
    2. Il **nome della proprietà non è un identificatore valido**  
        → `oggetto["nome-completo"]`, `oggetto["data di nascita"]`
        
    3. Devi costruire accessi **dinamici** (input utente, chiavi di un oggetto, sorteggi, ecc.)
        

Se tieni in mente questa sintesi:

> - **Punto** → proprietà con nome fisso scritto nel codice.
>     
> - **Parentesi quadre** → nome della proprietà in **stringa/variabile**.
>     

…sei a posto per qualsiasi esercizio sulla dot vs bracket notation.