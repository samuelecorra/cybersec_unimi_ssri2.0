## **Lezione 9: Tipi di dato in JavaScript**

In questa lezione iniziamo a mettere ordine su un concetto chiave della programmazione:  
**i tipi di dato** (data types).

Finora hai già visto al volo:

- numeri → `10`, `104`, `3.14`
    
- stringhe → `"ciao"`, `'Edoardo'`
    
- qualcosa tipo `undefined` quando una variabile non aveva ancora un valore
    
- una “lista” con le parentesi quadre → `[...]` (che in JS è un **array**)
    

Ora facciamo un quadro chiaro:

1. cosa vuol dire “tipo di dato”
    
2. differenza tra linguaggi statici/dinamici e forti/deboli
    
3. come si comporta **JavaScript**
    
4. panoramica sui principali tipi di dato di JS
    

---

### **1. Che cos’è un tipo di dato (intuitivamente)**

Un **tipo di dato** è il “genere” di informazione che stai rappresentando:

- numero → `10`, `3.14`
    
- testo → `"ciao"`, `"Edoardo"`
    
- vero/falso → `true`, `false`
    
- nessun valore → `null`, `undefined`
    
- strutture più complesse → oggetti, array, ecc.
    

Per il **motore JavaScript** il tipo di dato è importante perché decide:

- **che operazioni** ha senso fare (somma, concatenazione, confronti…),
    
- **come** rappresentare il valore in memoria,
    
- **come** si comporta quando combini più valori.
    

---

### **2. Linguaggi statici vs dinamici, fortemente vs debolmente tipizzati**

Qui entra un po’ di teoria, ma è quella che poi chiarisce _perché_ JS è così “libero”.

#### **2.1. Statico vs dinamico**

- **Linguaggio statico**:
    
    - il tipo di una variabile è deciso **a compilazione** (prima di eseguire).
        
    - una volta dichiarato, **non può cambiare tipo**.
        
    - es.: C, C++, Java, C#, TypeScript.
        
    
    Esempio stile “linguaggio statico”:
    
    ```ts
    let prova: number = 10;   // prova è di tipo number
    prova = "ciao";           // ❌ errore: non puoi assegnare una stringa
    ```
    
- **Linguaggio dinamico**:
    
    - il tipo è deciso **a runtime** (mentre gira il programma).
        
    - la stessa variabile può contenere tipi diversi in momenti diversi.
        
    - es.: JavaScript, Python, Ruby.
        
    
    In JavaScript:
    
    ```js
    let prova = 10;    // qui è un numero
    prova = "ciao";    // ora è una stringa, e JS non si lamenta
    ```
    

JavaScript è un linguaggio **dinamico**:  
il tipo di `prova` non è “scolpito nella pietra”, dipende da cosa ci metti dentro **in quel momento**.

---

#### **2.2. Fortemente vs debolmente tipizzato**

Altra distinzione (spesso intrecciata con la precedente):

- **Fortemente tipizzato**:
    
    - il linguaggio controlla con rigore i tipi,
        
    - conversioni implicite (automatiche) sono limitate,
        
    - mischiare tipi a caso genera errori o richiede cast espliciti.
        
- **Debolmente tipizzato**:
    
    - il linguaggio è molto “tollerante”,
        
    - converte i tipi in automatico in tanti contesti,
        
    - rischi di avere comportamenti strani se non stai attento.
        

JavaScript è considerato **dinamico** e **debolmente tipizzato**:

- dinamico → il tipo di una variabile può cambiare nel tempo;
    
- debolmente tipizzato → JS fa un sacco di magie di conversione automatica  
    (che poi studieremo con **coercizione**).
    

---

### **3. JavaScript vs TypeScript**

Nel mondo JS è nato **TypeScript**:

- **TypeScript** è un “superset” di JavaScript:
    
    - prende JavaScript,
        
    - aggiunge un sistema di tipi **forte e statico**,
        
    - poi viene “compilato” in JavaScript puro.
        

Esempio concettuale:

```ts
let prova: number = 10;  // TypeScript: prova deve essere un number

prova = "ciao";          // ❌ TypeScript segnala errore già in fase di sviluppo
```

Motivi per cui TypeScript esiste ed è usatissimo:

1. **Meno errori stupidi**:
    
    - se per sbaglio passi una stringa dove ti serve un numero, il tipo ti ferma.
        
2. **Più aiuto dall’editor**:
    
    - autocomplete, suggerimenti, controlli statici molto più precisi.
        
3. **Codice grande più gestibile**:
    
    - su progetti grossi, il sistema di tipi è una rete di sicurezza pazzesca.
        

JavaScript puro, invece:

- non ti fa dichiarare i tipi in modo esplicito,
    
- deduce il tipo **dal valore**,
    
- ti lascia riassegnare tranquillamente valori di tipo diverso.
    

---

### **4. L’analogia della scatola (variabile) e del contenuto (tipo)**

Ricollegandoci alle variabili:

- la **variabile** è la “scatola” (la celletta di memoria),
    
- il **tipo di dato** è **il contenuto**.
    

In JavaScript:

```js
let contenitore = 10;     // prima contiene un numero
contenitore = "ciao";     // ora contiene una stringa
```

È come dire:

- prima nella scatola ci sono le **scarpe**,
    
- poi butti via le scarpe e ci versi i **cereali**.
    

JavaScript guarda ogni volta **cosa c’è dentro** e si comporta di conseguenza.

In un linguaggio statico/forte, invece, la scatola nascerebbe “per scarpe” o “per cereali” e basta:

- se è dichiarata come “scatola di numeri” → può contenere solo numeri,
    
- se è “scatola di stringhe” → solo stringhe.
    

---

### **5. Perché i tipi contano anche a livello di performance**

Storicamente, nei linguaggi di basso livello (C, C++):

- la memoria era pochissima;
    
- dovevi **risparmiare ogni byte**;
    
- si distinguevano decine di tipi numerici:
    
    - intero con segno / senza segno,
        
    - 8 bit, 16 bit, 32 bit, 64 bit,
        
    - float a singola o doppia precisione, ecc.
        

Questo serviva per:

- non sprecare spazio,
    
- essere super precisi su come il dato è memorizzato,
    
- scrivere programmi **più veloci** e **più ottimizzati**.
    

Oggi abbiamo macchine molto più potenti, ma:

- **sui problemi grandi** (render 3D, AI, analisi dati, ecc.)
    
- l’efficienza di memoria e CPU conta ancora tantissimo.
    

JavaScript ti evita la fatica di pensare a tutti questi dettagli, ma in cambio:

- è meno efficiente di C/C++/Rust,
    
- il controllo sui tipi è molto più soft,
    
- devi essere tu più attento a cosa stai facendo.
    

TypeScript è un compromesso:  
ti dà **più sicurezza sui tipi** senza rinunciare al mondo JavaScript.

---

### **6. Tipi di dato principali in JavaScript (panoramica)**

Vediamo adesso una prima panoramica dei tipi che userai sempre.  
Li riprenderemo e approfondiremo uno per uno.

#### **6.1. `number`**

In JavaScript **tutti i numeri** (interi e decimali) hanno lo stesso tipo: `number`.

```js
let intero = 104;     // intero
let decimale = 3.14;  // decimale

typeof intero;   // "number"
typeof decimale; // "number"
```

Non esiste distinzione tra `int`, `float`, `double` ecc. come in altri linguaggi:  
è tutto sempre `number`.

---

#### **6.2. `string`**

Una **stringa** è testo: una sequenza di caratteri.

Puoi scriverla con:

```js
let saluto = 'ciao';       // apici singoli
let nome   = "Edoardo";    // doppi apici
```

(Esistono anche i backtick `...` per le template string, le vedrai più avanti.)

La parola che devi interiorizzare è: **stringa** → “testo”.

---

#### **6.3. `boolean`**

Il tipo booleano ha solo due valori possibili:

```js
let attivo = true;
let bannato = false;
```

Esempi d’uso:

- “L’utente è loggato?” → `true` / `false`
    
- “L’articolo è pubblicato?” → `true` / `false`
    

I booleani sono fondamentali per gli `if`, i cicli, i controlli logici.

---

#### **6.4. `undefined`**

`undefined` significa, letteralmente:

> “Questa variabile **esiste**, ma **non ha ancora un valore definito**.”

Esempio:

```js
let x;
console.log(x);  // undefined
```

Hai **dichiarato** `x`, ma non l’hai **inizializzata**.

---

#### **6.5. `null`**

`null` significa:

> “Questa variabile **ha un valore**, ed è esplicitamente ‘nessun valore’.”

Esempio:

```js
let risultato = null;    // qui sto dicendo: per ora non c'è nessun risultato

// Più tardi...
risultato = 42;
```

Differenza concettuale (che approfondiremo):

- `undefined` → “non ci ho ancora messo niente” (tipico quando dichiari e basta),
    
- `null` → “ho messo **volutamente** ‘vuoto’/‘niente’”.
    

---

#### **6.6. `symbol`**

`symbol` è un tipo introdotto nel JavaScript moderno:

- serve per creare **identificatori unici**,
    
- è usato in scenari avanzati (API, metaprogrammazione, ecc.),
    
- lo userai raramente all’inizio, ma è comunque parte dell’elenco.
    

Esempio base:

```js
const idUnico = Symbol("descrizione");
```

---

#### **6.7. `object`**

Gli **oggetti** sono uno dei pilastri di JavaScript.

Sono collezioni di **coppie `chiave: valore`**:

```js
const persona1 = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25
};
```

Qui:

- `nome`, `cognome`, `eta` sono le **chiavi**,
    
- `"Luca"`, `"Rossi"`, `25` sono i **valori**.
    

L’oggetto ti permette di raggruppare i dati con **significato**:

- non hai una lista anonima di valori,
    
- sai che `persona1.nome` è il nome,
    
- `persona1.eta` è l’età, ecc.
    

Accesso:

```js
console.log(persona1.nome);   // "Luca"
console.log(persona1.eta);    // 25
```

---

#### **6.8. `array`**

Un **array** è una lista ordinata di valori:

```js
const votiStoria = [10, 5, 6, 9, 7];
```

Può contenere:

- numeri,
    
- stringhe,
    
- oggetti,
    
- misti (anche se di solito è meglio essere coerenti).
    

Differenza concettuale con l’oggetto:

- **array** → lista “anonima” basata sull’ordine:
    
    - `votiStoria[0]`, `votiStoria[1]`, ecc.
        
- **oggetto** → struttura con chiavi esplicite:
    
    - `persona1.nome`, `persona1.eta`, ecc.
        

Esempio di accesso:

```js
console.log(votiStoria[0]);  // 10 (primo elemento)
console.log(votiStoria[3]);  // 9  (quarto elemento)
```

---

### **7. Riassunto concettuale**

- Ogni variabile in JS ha **un tipo di dato**, determinato dal valore che contiene in quel momento.
    
- JavaScript è:
    
    - **dinamico** → il tipo può cambiare durante l’esecuzione,
        
    - **debolmente tipizzato** → fa molte conversioni automatiche, nel bene e nel male.
        
- I tipi principali che userai ovunque sono:
    
    - `number`, `string`, `boolean`,
        
    - `undefined`, `null`,
        
    - `object`, `array`,
        
    - più `symbol` per casi specifici.
        

Nel prossimo video/lezione il prof entra in due parole che in JS sono cruciali:

- **conversione** (quando _sei tu_ a cambiare tipo),
    
- **coercizione** (quando è JavaScript a farlo _da solo_).
    

Ed è lì che iniziano tante delle “sorprese” tipiche di JavaScript 😈.