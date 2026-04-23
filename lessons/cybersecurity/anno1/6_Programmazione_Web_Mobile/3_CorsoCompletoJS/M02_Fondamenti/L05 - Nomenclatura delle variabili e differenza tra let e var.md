## **Lezione 5: Nomenclatura delle variabili e differenza tra `let` e `var`**

---

### **1. Prima di tutto: perché parlare di nomi e di `var`?**

Adesso che:

- sai cos’è una **variabile**,
    
- sai cosa sono **dichiarazione**, **assegnazione** e **inizializzazione**,
    

possiamo fare due passi importantissimi:

1. **Come chiamare le variabili** in modo sensato, leggibile e “standard”.
    
2. Capire perché **esistono sia `let` che `var`**, e perché **oggi si usa `let` (e `const`) e si evita `var`**.
    

Questa lezione è metà molto pratica (naming) e metà più concettuale/teorica (`var` vs `let`).  
Se la parte su `var` ti sembra “troppo”, va benissimo: ti basta ricordare una cosa:

> NEL CODICE MODERNO: **usa sempre `let` per le variabili** e **`const` per le costanti**.  
> `var` non usarlo mai in codice nuovo.

Poi, quando parleremo di **scope** e **hoisting**, potrai tornare qui e dire: “ah, ecco cosa stava succedendo sotto”.

---

### **2. Nomenclatura delle variabili: cosa è vietato**

La **nomenclatura** è l’insieme di regole (obbligatorie e di stile) su _come_ possiamo scrivere i nomi delle variabili.

#### **2.1. Simboli vietati**

Dato:

```js
let prova;
```

Non puoi usare:

- punti esclamativi: `!`
    
- altri simboli strani: `?`, `@`, `#`, `-`, `%`, ecc.
    
- spazi: `nome completo` → ❌
    

Puoi invece usare **solo due simboli speciali**:

- **`$`** → ammesso all’inizio, in mezzo, in fondo
    
- **`_`** (underscore) → ammesso all’inizio, in mezzo, in fondo
    

Esempi validi:

```js
let $nome;
let nome$;
let _nome;
let nome_persona;
let $nome_persona$;
```

Esempi NON validi:

```js
let nome-persona;   // ❌ trattino
let nome persona;   // ❌ spazio
let !nome;          // ❌ punto esclamativo
```

---

#### **2.2. Numeri nei nomi**

I numeri si possono usare, ma **non all’inizio**:

```js
let nome1;   // ✅ ok
let nome2;   // ✅ ok

let 1nome;   // ❌ non valido
```

---

#### **2.3. Parole chiave vietate**

Non puoi usare come nome di variabile le **parole chiave** del linguaggio (keyword), tipo:

```js
let let;       // ❌
let const;     // ❌
let var;       // ❌
let function;  // ❌
```

Queste parole sono **riservate** perché hanno già un significato in JavaScript.

---

### **3. Best practices: come scegliere nomi “giusti”**

Fin qui erano regole “rigide” del linguaggio.  
Ora passiamo alle **best practices**: regole di buon senso condivise tra programmatori.

Perché esistono?

- Perché se ognuno scrive i nomi **a caso**, collaborare diventa un incubo.
    
- Perché vogliamo che un altro sviluppatore, entrando in un progetto, capisca al volo cosa è:
    
    - una **variabile**
        
    - una **costante**
        
    - una **lista**
        
    - un **id**, ecc.
        

L’idea è:

> “Se tutti seguiamo le stesse convenzioni, leggere codice di altre persone diventa molto più semplice.”

---

### **3.1. CamelCase per le variabili**

In JavaScript la convenzione standard è usare il **camelCase** per le variabili:

- la **prima parola** tutta minuscola;
    
- le **parole successive** attaccate, ma con l’iniziale **maiuscola**.
    

Esempi:

```js
let nome;
let nomePersona;
let nomeCompleto;
let prezzoUnitario;
let numeroTelefonoCliente;
```

Questo stile si chiama **camelCase** (come “gobba di cammello”):

- `nomePersonaBella`
    
    - `nome` → prima parola
        
    - `Persona` → seconda parola → iniziale maiuscola
        
    - `Bella` → terza parola → iniziale maiuscola
        

---

### **3.2. Sigle e acronimi nel nome (ID, CF, ecc.)**

Cosa facciamo con sigle tipo `ID`, `CF`, `VAT`, `VPN`?

Non è una regola “assoluta”, ma una buona pratica può essere:

- se la sigla è **alla fine** del nome della variabile, puoi scriverla tutta maiuscola:
    
    ```js
    let nomePersonaID;   // ID alla fine, in maiuscolo → leggibile
    let codiceFiscaleCF; // CF ben visibile
    ```
    
- se è in mezzo, spesso si preferisce:
    
    ```js
    let userIDProva;
    ```
    

L’importante è che:

- tu sia **coerente** nel progetto,
    
- la sigla si riconosca e **non confonda** la lettura.
    

---

### **3.3. Evitare nomi generici: niente `x` se indica un prezzo**

Errore tipico:

```js
let x = 10;    // x cosa? prezzo? metri? anni?
let y = 3;     // y cosa?
```

Se `x` rappresenta un prezzo, **chiamala `prezzo`**.  
Se rappresenta il **prezzo unitario**, meglio ancora:

```js
let prezzo;
let prezzoUnitario;
let quantita;
let profittoTotale;
```

Un nome buono deve:

- descrivere **cos’è** il dato,
    
- evitare abbreviazioni inutili:
    
    ```js
    let prx = 10;     // ❌ prx non vuol dire niente
    let prezzo = 10;  // ✅ leggibile
    ```
    

---

### **3.4. Singolare vs plurale**

Quando una variabile contiene:

- **un solo valore** → nome **singolare**
    
    ```js
    let prezzoUnitario;   // un solo prezzo
    let nomeCliente;      // un solo nome
    ```
    
- **più valori** (es. un array) → nome **plurale**
    
    ```js
    let prezziUnitari;    // lista di prezzi
    let nomiClienti;      // lista di nomi
    let indirizziEmail;   // lista di email
    ```
    

Questo rende subito chiaro:

- se stai lavorando su **un singolo elemento**,
    
- o su una **collezione**.
    

---

### **3.5. Non mischiare lingue (o fallo con criterio)**

Altro consiglio pratico:

- Non mescolare a caso **italiano** e **inglese** nei nomi:
    
    ```js
    let prezzoUnitario;
    let totalPrice;       // ❌ mescola casuale
    ```
    

Meglio:

- tutto in inglese:
    
    ```js
    let unitPrice;
    let totalPrice;
    ```
    
- oppure tutto in italiano (se sei certo che il progetto sarà solo italiano):
    
    ```js
    let prezzoUnitario;
    let prezzoTotale;
    ```
    

In pratica:

- per progetti **internazionali / generici** → preferisci **inglese**.
    
- per cose **molto italiane** (es. codice fiscale, partita IVA, normativa locale) → l’italiano ha senso.
    

L’importante è:

> Sii **consistente**.  
> Non cambiare lingua a caso da una variabile all’altra.

---

### **4. `let` contro `var`: perché `var` è considerato “vecchio”**

Ora veniamo alla seconda parte della lezione.

JavaScript, storicamente, ha introdotto per prime le variabili con **`var`**.  
Poi, con JavaScript moderno (ES2015+), sono arrivati **`let`** e **`const`**.

Oggi, nel codice moderno, la regola pratica è:

- **usa `let` per le variabili**,
    
- **usa `const` per le costanti**,
    
- **non usare `var`** in codice nuovo.
    

#### Ma allora perché `var` esiste ancora?

Perché su Internet ci sono:

- miliardi di righe di codice vecchio,
    
- siti e webapp che usano `var` dappertutto.
    

Se JavaScript “cancellasse” `var`, succederebbe il disastro:

- miliardi di pagine smetterebbero di funzionare,
    
- milioni di aziende dovrebbero riscrivere tutto “entro un mese”.
    

Quindi la regola del web è:

> “Non rompere mai il codice che è già online.”

Per questo:

- `var` **rimane**, ma è **deprecato** a livello di stile;
    
- i nuovi progetti usano `let` e `const`.
    

---

### **5. Primo confronto pratico: hoisting (`var`) vs errore (`let`)**

Guarda questo codice:

```js
console.log(a);
var a = 5;
```

Cosa ti aspetteresti?

Istintivamente: “Errore, perché `a` non esiste ancora”.

Invece l’output è:

```text
undefined
```

JavaScript con `var` fa una cosa “magica” (ne parleremo meglio con l’**hoisting**):

- è come se internamente riscrivessi:
    
    ```js
    var a;           // dichiarata in alto in automatico (hoisting)
    console.log(a);  // → undefined
    a = 5;
    ```
    

Quindi:

- `a` esiste già quando fai `console.log(a)`,
    
- ma non ha ancora un valore → **`undefined`**.
    

Adesso prova con `let`:

```js
console.log(b);
let b = 5;
```

Output:

```text
ReferenceError: Cannot access 'b' before initialization
```

Qui il comportamento è **molto più sensato**:

- non puoi usare `b` **prima** che sia inizializzata,
    
- JavaScript ti segnala **un errore reale**.
    

Questo è uno dei motivi per cui `let` è considerata **più sicura**:  
evita interi gruppi di bug “strani” dovuti alla logica di `var`.

---

### **6. Errori sintattici vs errori logici**

Quello che hai appena visto introduce una distinzione importante.

#### **6.1. Errore sintattico**

Esempio:

```js
let b5;   // ok
b5 = 5

b5 5      // ❌ sintassi sbagliata
```

Qui Visual Studio Code ti segnala:

- “Non capisco questa frase, la sintassi è sbagliata”.
    

Sono errori tipo:

- manca un `=`,
    
- hai scritto `le t` invece di `let`,
    
- parentesi non chiuse, ecc.
    

---

#### **6.2. Errore logico**

Con `var`, invece, questo codice:

```js
console.log(a);
var a = 5;
```

- è **sintatticamente valido** → nessun errore rosso in editor,
    
- ma **logicamente fuorviante** → stampa `undefined`, non quello che ti aspetteresti se ti scordi dell’hoisting.
    

Questi sono **errori logici**:

- il programma _funziona_,
    
- ma non fa quello che tu _credevi_.
    

`var` rende molto più facile farsi male con questo tipo di errori, perché:

- il codice “gira”,
    
- ma con comportamenti poco intuitivi.
    

`let` è più “rigido” e quindi più **amico del programmatore**:  
ti blocca in situazioni ambigue.

---

### **7. Secondo confronto: scope (contesto) di `var` vs `let`**

Non hai ancora visto nel dettaglio **funzioni** e **scope**, ma ti faccio intuire il problema.

Immagina questo schema (semplificato):

```js
function esempio() {
  if (true) {
    var x = 10;
  }

  console.log(x);
}

esempio();
console.log(x);
```

Con `var` succede:

- dentro la funzione `esempio`, la variabile `x` dichiarata con `var`:
    
    ```js
    if (true) {
      var x = 10;
    }
    ```
    
    è **visibile in tutta la funzione**, non solo dentro l’`if`.
    
- quindi:
    
    - `console.log(x);` _dentro_ la funzione → stampa `10`,
        
    - `console.log(x);` _fuori_ dalla funzione → errore (non esiste a livello globale).
        

Con `let`, invece, riscrivi:

```js
function esempio() {
  if (true) {
    let x = 10;
  }

  console.log(x);  // ReferenceError
}

esempio();
```

Perché?

> `let` è **block-scoped**:  
> esiste solo **dentro il blocco** `{ ... }` in cui è stata dichiarata.

Quindi:

- dichiarata dentro l’`if`,
    
- **non accessibile** fuori dall’`if`.
    

Questo comportamento:

- è molto più **prevedibile**,
    
- ti evita orde di bug dovuti a variabili che “sanguinano” fuori dal blocco.
    

Con `var`, invece, lo **scope** è solo a livello di **funzione**, non di blocco:

- dentro una funzione, tutte le `var` dichiarate in sottoblocchi (`if`, `for`, ecc.) sono **visibili ovunque nella funzione**.
    

Di nuovo: motivo per cui `var` è considerato pericoloso e ormai superato.

---

### **8. Cosa devi fare in pratica (anche se `var` non ti è chiarissimo)**

Per il tuo codice, la regola operativa è semplicissima:

- **Non usare mai `var`** in codice nuovo.
    
- Usa:
    
    - `let` per variabili che **cambiano** nel tempo;
        
    - `const` per valori che **non devono cambiare** (vedremo a breve).
        

Esempi:

```js
// Variabili "normali"
let contatore = 0;
let nomeUtente = "Luca";

// Costanti (vedremo meglio fra poco)
const PI_GRECO = 3.14159;
const SERVER_URL = "https://api.miosito.com";
```

`var` ti serve solo:

- per **capire codice legacy** (vecchio),
    
- o per risolvere bug su progetti dove altri hanno usato `var`.
    

---

### **9. Riepilogo finale**

In questa lezione hai visto due blocchi di concetti:

#### **9.1. Nomenclatura variabili**

- **Simboli ammessi**: lettere, numeri (non all’inizio), `_`, `$`.
    
- Non puoi usare: spazi, trattini, simboli come `!`, parole chiave (`let`, `const`, `function`, ecc.).
    
- Usa **camelCase** per le variabili:
    
    - `nome`, `nomePersona`, `prezzoUnitario`, `numeroTelefonoCliente`.
        
- Per liste/array → nomi al **plurale**:
    
    - `prezziUnitari`, `nomiClienti`, `indirizziEmail`.
        
- Non usare nomi generici (`x`, `y`) quando puoi usare nomi descrittivi (`prezzoUnitario`, `profittoTotale`).
    
- Non mischiare italiano/inglese a caso: sii **coerente** all’interno del progetto.
    

#### **9.2. `let` vs `var`**

- `var` è la vecchia keyword per le variabili:
    
    - esiste ancora per **compatibilità**,
        
    - ma oggi è considerata **in disuso**.
        
- Problemi principali di `var`:
    
    - **hoisting**: puoi fare `console.log(a)` prima di `var a = 5;` e ottieni `undefined` → comportamento poco intuitivo;
        
    - **scope di funzione**: una `var` dichiarata dentro un `if` è visibile in tutta la funzione → facile creare bug logici.
        
- `let` è più sicuro:
    
    - blocca l’accesso a variabili prima dell’inizializzazione (`ReferenceError`),
        
    - è **block-scoped** (valida solo dentro `{ ... }` dove è stata dichiarata).
        
- Regola pratica:
    
    - **usa sempre `let` e `const`**,
        
    - dimentica `var` nel codice nuovo.
        

---

Nella **prossima lezione** vediamo finalmente:

> **Le costanti (`const`): cosa sono, come funzionano e come usarle in modo pulito**,  
> e completiamo il quadro `let / var / const` una volta per tutte.