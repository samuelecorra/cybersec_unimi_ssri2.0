## **Lezione 8: Commenti in JavaScript**

In questa lezione vediamo una cosa super semplice ma **fondamentale** per lavorare bene su progetti veri:  
i **commenti**.

I commenti non cambiano cosa fa il programma, ma cambiano moltissimo **come lo capiamo** noi (e chi verrà dopo di noi).

---

### **1. Cosa sono i commenti**

Un **commento** è una riga (o più righe) di testo:

- scritta nel file `.js`,
    
- **ignorata completamente** dal motore JavaScript,
    
- usata solo da **noi umani** per:
    
    - spiegare cosa fa un pezzo di codice,
        
    - lasciare appunti a noi stessi (“qui faccio X perché Y…”),
        
    - disattivare temporaneamente delle righe di codice senza cancellarle.
        

Per il motore JS, una riga commentata è come una riga vuota:  
**non viene eseguita**.

---

### **2. Commenti a singola riga: `//`**

Il primo tipo di commento è quello **su una sola riga**, che si scrive con `//`.

Tutto quello che c’è **dopo** `//` sulla stessa riga viene ignorato:

```js
// Questo è un commento su una riga intera

let nomePersona = "Luca";  // Questo è un commento dopo il codice
```

Regole importanti:

- Se scrivi `//` **all’inizio della riga**, stai commentando **tutta la riga**.
    
- Se scrivi `//` **dopo del codice**, stai commentando **solo la parte finale**.
    

Esempio:

```js
// Dichiarazione di una persona
const nomePersona = "Luca";       // nome
const cognomePersona = "Rossi";   // cognome
```

Cosa **non** puoi fare: mettere il commento _davanti_ al codice sperando che poi il codice “riparta” dopo.  
Con:

```js
// const nomePersona = "Luca";
```

tutta la riga è commentata, quindi **non viene eseguito niente**.

---

### **3. Commenti multi-riga: `/* ... */`**

Se vuoi commentare **più righe di seguito**, invece di scrivere `//` su ogni riga puoi usare:

```js
/*
  Questo è un commento
  su più righe.
  Tutto quello che è tra /* e */ viene ignorato.
*/
```

Sintassi:

```js
/* inizio commento
   ...
   fine commento */
```

Puoi usarlo anche su una sola riga:

```js
/* commento su una sola riga */
```

Oppure “a lato” del codice (anche se è meno comune):

```js
let x = 10; /* questo è il valore iniziale */
```

La differenza con `//`:

- `//` commenta **da lì fino a fine riga**.
    
- `/* ... */` commenta **tutto il blocco** tra apertura e chiusura, anche su più righe.
    

---

### **4. Scorciatoia veloce in VS Code**

In Visual Studio Code c’è una scorciatoia molto comoda:

- **Windows / Linux**: `Ctrl + /` (o `Ctrl + accentata` a seconda del layout)
    
- **macOS**: `Cmd + /`
    

Funziona così:

- Se selezioni **una o più righe** e premi la scorciatoia → VS Code mette `//` all’inizio di ogni riga selezionata.
    
- Se lo premi di nuovo → toglie i `//` e “decommenta”.
    

Esempio:

```js
let nomePersona = "Luca";
let cognomePersona = "Rossi";
console.log(nomePersona, cognomePersona);
```

Se selezioni tutto e fai `Ctrl + /`, diventa:

```js
// let nomePersona = "Luca";
// let cognomePersona = "Rossi";
// console.log(nomePersona, cognomePersona);
```

---

### **5. Perché i commenti sono importanti (davvero)**

#### 5.1. Per te stesso “del futuro”

Scenario:

- oggi scrivi una funzione un po’ intricata;
    
- poi fai altro per una settimana;
    
- torni sul file e guardi il codice → **“ma che cavolo avevo in mente qui?”**
    

Se sopra hai scritto:

```js
// Calcolo il BMI (indice di massa corporea) dato peso e altezza
```

ci metti **un secondo** a ricordare il senso.  
Senza commento, devi:

- leggere tutto il codice,
    
- ricostruire mentalmente la logica,
    
- perdere minuti (o mezz’ora) per rientrare nel contesto.
    

I commenti sono come una **lista della spesa**:  
la spesa puoi farla anche a memoria… ma è un suicidio per il cervello.

---

#### 5.2. Per chi lavorerà dopo di te

Magari:

- cambi azienda,
    
- un collega prende in mano il tuo progetto,
    
- oppure tu stesso ci torni dopo mesi con la testa su 1000 altre cose.
    

Un commento chiaro sopra un pezzo delicato di codice significa:

- meno domande,
    
- meno tempo sprecato,
    
- meno bestemmie davanti allo schermo.
    

Esempio:

```js
// Qui gestisco un caso particolare per evitare un bug noto su iOS Safari
// Non rimuovere questa logica senza testare sui dispositivi reali
```

Chi legge capisce che:

- non è “codice inutile”,
    
- c’è un motivo preciso,
    
- va toccato con attenzione.
    

---

### **6. Commenti per “spegnere” pezzi di codice**

Un uso super pratico dei commenti è per **disattivare temporaneamente** del codice:

```js
console.log("Persona 1:", persona1);
console.log("Persona 2:", persona2);

// Per ora non mi interessa più questo log:
// console.log("Debug dettagliato:", personaDettaglio);
```

Oppure, se vuoi provare una versione alternativa senza perdere quella vecchia:

```js
// Versione vecchia:
// const risultato = calcolaBmi(peso, altezza);

// Nuova versione in test:
const risultato = calcolaBmiMigliorato(peso, altezza);
```

Cosa **non** devi fare è trasformare il file in un museo delle versioni:

```js
// vecchiaVersione1
// vecchiaVersione2
// vecchiaVersione3
// vecchiaVersione4
// ...
```

Per le versioni vecchie esiste **Git**.  
I commenti vanno usati per:

- test veloci,
    
- esperimenti,
    
- disattivare una parte mentre ne provi un’altra.
    

Poi il codice “definitivo” va **pulito**.

---

### **7. Buone pratiche sui commenti**

- **Non commentare ogni riga ovvia**:
    
    ```js
    let x = 5;      // assegno 5 alla variabile x  ❌ inutile
    ```
    
- Commenta **il perché**, non solo **il cosa**:
    
    ```js
    // Uso Math.floor per evitare arrotondamenti verso l'alto
    const numeroPagamenti = Math.floor(totale / rataMensile);
    ```
    
- Usa i commenti per spiegare:
    
    - casi particolari,
        
    - scelte progettuali non immediate,
        
    - workaround per bug di librerie / browser,
        
    - parti “magiche” o non banali.
        
- Non usare i commenti per nascondere **codice morto** per mesi:  
    se non serve più, **eliminalo**.  
    Per recuperarlo c’è la cronologia Git.
    

---

### **8. Mini esercizio mentale**

Prendi questo pezzo di codice e prova (mentalmente o sul tuo file) a:

1. Aggiungere **commenti utili** (non ovvi).
    
2. Commentare temporaneamente una riga per cambiare il comportamento.
    

```js
const nomePersona = "Luca";
const cognomePersona = "Rossi";

const nomeCompleto = nomePersona + " " + cognomePersona;

console.log(nomeCompleto);
```

Esempio di possibile soluzione:

```js
// Dati anagrafici dell'utente attualmente loggato
const nomePersona = "Luca";
const cognomePersona = "Rossi";

// Unisco nome e cognome in una singola stringa
const nomeCompleto = nomePersona + " " + cognomePersona;

// Mostro il nome completo in console (per debug)
// console.log(nomePersona); // versione alternativa
console.log(nomeCompleto);
```

---

Con questo abbiamo chiuso il “blocco” su:

- variabili,
    
- costanti,
    
- commenti.
    

Ora, come ha anticipato il prof nel video, il passo naturale è iniziare con i **tipi di dato** in JavaScript (numeri, stringhe, booleani, ecc.): la “materia prima” che finirà dentro variabili e costanti.