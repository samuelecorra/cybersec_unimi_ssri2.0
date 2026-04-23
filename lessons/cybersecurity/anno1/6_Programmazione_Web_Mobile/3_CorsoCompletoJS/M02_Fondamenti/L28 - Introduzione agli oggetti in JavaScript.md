## **Lezione 28: Introduzione agli oggetti in JavaScript**

---

### **1. Perché esistono gli oggetti**

Finora hai visto:

- variabili “singole”:
    

```js
const eta = 25;
const nome = "Luca";
const cognome = "Rossi";
```

- array come **liste** di valori:
    

```js
const voti = [28, 30, 27];
```

Gli oggetti servono quando vuoi **rappresentare qualcosa di reale** (una persona, un libro, un post, un prodotto…) che ha **più caratteristiche eterogenee**:

- nome (stringa)
    
- cognome (stringa)
    
- età (numero)
    
- hobby (array)
    
- indirizzo (a sua volta oggetto)
    
- ecc.
    

Invece di avere 10 variabili separate, le **condensi in un’unica struttura coerente**: l’oggetto.

---

### **2. Sintassi di base di un oggetto**

Oggetto vuoto:

```js
const oggetto = {};
```

Oggetto persona con alcune proprietà:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,
};
```

Osserva bene:

- `{ ... }` definisce un **object literal**
    
- ogni riga interna è una **coppia chiave–valore**:
    
    - `nome` → chiave (key)
        
    - `"Luca"` → valore (value)
        
- ogni coppia è separata da **virgola**
    

---

### **3. Coppie chiave–valore e “proprietà”**

Un oggetto è:

> Una **collezione di coppie chiave–valore**.

Esempio:

```js
const persona = {
  nome: "Luca",     // chiave: nome   → valore: "Luca"
  cognome: "Rossi", // chiave: cognome → valore: "Rossi"
  eta: 25,          // chiave: eta    → valore: 25
};
```

Per una persona:

- `nome`, `cognome`, `eta` sono chiamate **proprietà** dell’oggetto `persona`.
    

👉 Pensiero corretto:  
“Questa **cosa di codice** non è solo un ‘object’: per noi umani rappresenta una **persona**.”

Il **nome della variabile** rende chiaro cosa rappresenta:

- `persona` → ha senso avere `nome`, `cognome`, `eta`
    
- `libro` → avrebbe più senso avere `titolo`, `autore`, `isbn`
    
- `brano` → potrebbe avere `titolo`, `autore`, `durata`
    

Dare **nomi sensati** è fondamentale per capire il codice e sarà cruciale nella **programmazione a oggetti**.

---

### **4. Tipi di valori che un oggetto può contenere**

Dentro un oggetto puoi mettere **qualunque tipo di dato**:

- numeri
    
- stringhe
    
- boolean
    
- array
    
- altri oggetti
    
- (più avanti) funzioni, ecc.
    

Esempio più ricco:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,

  hobby: ["calcio", "tennis"],

  indirizzo: {
    via: "Viale le mani da qui",
    civico: 23,
    cap: "89670",
    citta: "Milano",
    provincia: "MI",
    paese: "IT",
  },

  isOnline: true,
};
```

Qui hai:

- proprietà semplici: `nome`, `cognome`, `eta`, `isOnline`
    
- proprietà che è un **array**: `hobby`
    
- proprietà che è a sua volta un **oggetto**: `indirizzo`
    

Puoi anche definire l’indirizzo separato e poi inserirlo:

```js
const indirizzo = {
  via: "Viale le mani da qui",
  civico: 23,
  cap: "89670",
  citta: "Milano",
  provincia: "MI",
  paese: "IT",
};

const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,
  hobby: ["calcio", "tennis"],
  indirizzo: indirizzo,
  isOnline: true,
};
```

---

### **5. Oggetti vs array: due ruoli diversi**

- **Array**
    
    - Modello mentale: **lista**
        
    - Tipico uso: elenco di elementi omogenei
        
        - lista di numeri
            
        - lista di stringhe
            
        - lista di oggetti (`listaPersone`, `listaMessaggi`, …)
            
- **Oggetto**
    
    - Modello mentale: **“cosa” con proprietà**
        
    - Tipico uso: rappresentare un’entità del mondo reale
        
        - una persona, un libro, un post, un prodotto, una macchina, ecc.
            

Esempio combinato:

```js
const messaggio = {
  id: 123,
  testo: "Ciao sono Edo",
  data: "2024-01-01",
};

const chat = [
  messaggio,
  { id: 124, testo: "Come va?", data: "2024-01-02" },
];
```

---

### **6. Accesso alle proprietà: dot notation**

Per leggere l’oggetto intero:

```js
console.log(persona);
```

Per leggere una singola proprietà (dot notation):

```js
console.log(persona.nome);     // "Luca"
console.log(persona.cognome);  // "Rossi"
console.log(persona.eta);      // 25
```

Nel **devtools** del browser, un oggetto appare espandibile:

- puoi aprirlo con la freccetta e vedere tutte le proprietà
    
- a volte le vedi **in ordine alfabetico**, non necessariamente nell’ordine in cui le hai dichiarate
    

---

### **7. Accesso a oggetti annidati e array dentro oggetti**

#### **7.1 Oggetto dentro oggetto**

Prendiamo `indirizzo` dentro `persona`:

```js
console.log(persona.indirizzo);         // stampa l'oggetto indirizzo
console.log(persona.indirizzo.citta);   // "Milano"
console.log(persona.indirizzo.paese);   // "IT"
```

L’idea è:

> Vai “a punti” finché non arrivi a ciò che ti interessa.

#### **7.2 Array dentro oggetto**

`hobby` è un array, quindi usi gli indici:

```js
console.log(persona.hobby);    // ["calcio", "tennis"]
console.log(persona.hobby[0]); // "calcio"
console.log(persona.hobby[1]); // "tennis"
```

Combinazione oggetto + array + oggetto:

```js
// esempio teorico:
persona.indirizzo.hobby[0];
// oggetto → oggetto → array → elemento dell'array
```

---

### **8. Modifica, aggiunta e rimozione di proprietà**

#### **8.1 Aggiungere una nuova proprietà**

Basta assegnarla con il punto:

```js
persona.prova = "Ciao, sono Edo";
console.log(persona.prova); // "Ciao, sono Edo"
```

Se la proprietà **non esisteva**, viene **creata** sul momento.

---

#### **8.2 Modificare una proprietà esistente**

Vale lo stesso schema:

```js
persona.indirizzo.civico = 999;
console.log(persona.indirizzo.civico); // 999
```

Hai “sovrascritto” il vecchio valore (23) con il nuovo (999).

---

#### **8.3 Eliminare una proprietà: `delete`**

Per rimuovere del tutto una proprietà da un oggetto:

```js
delete persona.indirizzo;
console.log(persona.indirizzo); // undefined
```

Dopo il `delete`, `indirizzo` **non esiste più** all’interno di `persona`.

Puoi usare `delete` anche su altre proprietà:

```js
delete persona.prova;
delete persona.isOnline;
```

---

### **9. Oggetti e `const` (anticipazione rapida)**

Quando scrivi:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
};
```

- **non puoi** riassegnare `persona` a un altro oggetto:
    

```js
persona = { nome: "Marco" }; // ❌ Errore
```

- **puoi** modificare le sue proprietà interne:
    

```js
persona.nome = "Marco"; // ✅ OK
persona.eta = 30;       // ✅ aggiunta di una nuova proprietà
delete persona.cognome; // ✅ rimozione proprietà
```

Regola mentale:

> `const` blocca il **riferimento** (non puoi riassegnare un altro oggetto).  
> Le **proprietà interne** invece sono modificabili.

---

### **10. Riepilogo concettuale**

- Un **oggetto** è una **struttura di dati complessa**:
    
    - composta da **coppie chiave–valore**
        
    - le chiavi sono chiamate **proprietà**
        
- Gli oggetti servono a rappresentare **entità del mondo reale**:
    
    - `persona`, `libro`, `canzone`, `indirizzo`, `prodotto`, ecc.
        
- Un oggetto può contenere:
    
    - tipi primitivi (`number`, `string`, `boolean`, …)
        
    - `array`
        
    - altri **oggetti**
        
- Accesso alle proprietà:
    
    - `persona.nome`
        
    - `persona.indirizzo.citta`
        
    - `persona.hobby[0]`
        
- Modifica / aggiunta / rimozione:
    
    - aggiunta: `persona.nuovaProp = valore`
        
    - modifica: `persona.eta = 30`
        
    - rimozione: `delete persona.indirizzo`
        
- Con `const`:
    
    - non puoi fare `persona = { ... }` (nuovo oggetto)
        
    - puoi cambiare ciò che c’è **dentro** `persona`
        
