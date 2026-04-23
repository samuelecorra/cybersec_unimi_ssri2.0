## **Lezione 2: Sintassi base di JavaScript**

### **1. Esecuzione dall’alto verso il basso (codice sincrono)**

Partiamo da una cosa fondamentale che vale praticamente per **tutti** i linguaggi di programmazione (JavaScript compreso):

> Il codice viene eseguito **dall’alto verso il basso**, in modo **sequenziale** (sincrono).

Immagina di avere in `index.js` qualcosa del genere (semplifico):

```js
let x = 5;
console.log("Ciao, sono Edo da script JS");
x = 10;
console.log(x);
```

Quando il browser carica questo file, fa:

1. Legge `let x = 5;` → crea una variabile `x` e le assegna il valore `5`.
    
2. Legge `console.log("Ciao, sono Edo da script JS");` → manda in console la stringa.
    
3. Legge `x = 10;` → cambia il valore di `x` da `5` a `10`.
    
4. Legge `console.log(x);` → manda in console il valore corrente di `x`, cioè `10`.
    

In console vedrai:

- `Ciao, sono Edo da script JS`
    
- `10`
    

Questa è l’idea di **esecuzione sequenziale**: ogni riga è eseguita **dopo** quella precedente.

> Usiamo il termine **sincrono** proprio per questo: le istruzioni vengono eseguite una dopo l’altra, in ordine.

Più avanti, nel corso, vedremo cosa significa **asincronia** (callback, Promise, `async/await`), ma il punto di partenza è sempre questo:  
di base JavaScript esegue il codice dall’alto verso il basso, riga dopo riga.

---

### **2. Istruzioni (statement): cosa sono**

Ogni riga “significativa” di codice viene chiamata:

- **istruzione** (in italiano)
    
- **statement** (in inglese)
    

Esempi:

```js
let x = 5;              // istruzione 1
console.log("Test");    // istruzione 2
x = 10;                 // istruzione 3
console.log(x);         // istruzione 4
```

Ogni istruzione:

- dice al computer di **fare qualcosa**,
    
- viene eseguita (in codice sincrono) nell’ordine in cui è scritta.
    

La programmazione **classica** funziona così:

> Sei tu programmatore che devi dire al computer, passo per passo, cosa fare:  
> “prima fai questo, poi questo, poi se succede X fai quest’altro, altrimenti fai Y, alla fine mostrami il risultato…”

È esattamente come scrivere una **formula** o un **algoritmo**:

- definisci l’algoritmo una volta sola,
    
- poi puoi applicarlo a **mille casi diversi** (come una formula di fisica o di statistica).
    

Con l’**intelligenza artificiale** il paradigma è diverso: di solito dai un **prompt** e lasci il modello “decidere” come arrivare al risultato in base a ciò che ha imparato.  
Nel **coding classico**, invece, sei tu che devi specificare **in modo esplicito** tutti i passaggi logici.

---

### **3. Il punto e virgola `;`**

In moltissimi linguaggi (C, C++, Java, ecc.) il **punto e virgola**:

```js
let x = 5;
```

serve a indicare **la fine dell’istruzione**.

- Il compilatore/interprete ha bisogno di sapere **dove finisce** l’istruzione.
    
- Se manca il `;` in quei linguaggi, spesso il codice **non compila** e dà errore.
    

In **JavaScript**, invece, il linguaggio è stato progettato in modo da **indovinare** la fine di molte istruzioni anche **senza** `;`.  
Per questo motivo puoi trovare codice:

- scritto con `;` alla fine di ogni riga,
    
- oppure completamente **senza** `;`.
    

Esempi **entrambi validi** in JavaScript:

```js
let x = 5;
console.log(x);
```

oppure

```js
let x = 5
console.log(x)
```

JavaScript ha un meccanismo chiamato **ASI (Automatic Semicolon Insertion)** che, semplificando, “inserisce mentalmente” i `;` dove servono.

> Regola pratica:
> 
> - o **usi sempre** il `;`
>     
> - o **non lo usi mai**  
>     L’importante è essere **coerente** nello stile.
>     

---

### **4. Spazi bianchi e righe vuote**

Osserva queste due righe:

```js
x = 10;
```

e

```js
x      =       10      ;
```

Per JavaScript è **esattamente la stessa cosa**.

Gli **spazi bianchi** e le **righe vuote**:

- servono **solo per la leggibilità**,
    
- il motore JavaScript li **ignora** (tranne dentro stringhe o in linguaggi particolari stile Python, dove l’indentazione ha valore sintattico, ma questo non è il caso).
    

Esempio:

```js
let x = 5;

x = 10;

console.log(x);
```

Queste righe vuote servono solo a rendere il codice più chiaro per gli umani.

Di solito:

- metti uno spazio **attorno agli operatori** (es. `x = 10`, non `x=10`),
    
- usi righe vuote per separare blocchi logici di codice.
    

---

### **5. Parentesi: tonde, graffe e significato**

In JavaScript abbiamo diversi tipi di parentesi, ognuna con un significato preciso:

- **parentesi tonde `()`**  
    Usate per:
    
    - chiamare funzioni:
        
        ```js
        console.log("Ciao");
        ```
        
    - passare **argomenti** a funzioni:
        
        ```js
        somma(3, 5);
        ```
        
- **parentesi graffe `{}`**  
    Le vedremo più avanti per:
    
    - blocchi di codice (`if`, `for`, funzioni…),
        
    - oggetti,
        
    - ecc.
        

Nel caso di:

```js
console.log(x);
```

abbiamo:

- `console` → un oggetto fornito dal browser (la console di sviluppo),
    
- `log` → un **metodo** (una funzione) di `console`,
    
- `()` → chiamata del metodo,
    
- `x` dentro le parentesi → **argomento** passato al metodo.
    

L’idea è:

> “Prendi la `console` e **logga** (mandami a schermo) il valore di `x`.”

Immaginalo come:

> “Luca, salutami tua madre.”  
> `Luca` → oggetto/persona  
> `salutami()` → azione/metodo  
> `tua madre` → argomento

In codice:

```js
Luca.saluta("tua madre");
```

---

### **6. Operatori: `=` e oltre**

In questa riga:

```js
x = 10;
```

l’`=` non significa “uguale” in senso matematico, ma:

> **“assegna il valore 10 alla variabile x”**.

È un **operatore di assegnazione**.

In programmazione avremo:

- operatori aritmetici (`+`, `-`, `*`, `/`, `%`, …),
    
- operatori di confronto (`>`, `<`, `>=`, `<=`, `===`, `!==`, …),
    
- operatori logici (`&&`, `||`, `!`),
    
- operatori di assegnazione “combinati” (`+=`, `-=`, `*=`, `/=`, …).
    

Li vedremo con calma. Per ora ti basta sapere che:

- `=` in JavaScript significa **assegna**,
    
- il concetto di “confronto” di uguaglianza si fa con `==` o meglio `===` (che vedremo più avanti).
    

---

### **7. Parole chiave (keyword) e nomi vietati**

In questa riga:

```js
let x = 5;
```

- `let` è una **keyword** (parola chiave),
    
- `x` è il **nome della variabile** (o **identificatore**),
    
- `5` è il **valore**,
    
- `=` è l’**operatore di assegnazione**.
    

Le **keyword** sono parole **riservate** dal linguaggio.  
Alcuni esempi:

```js
let
const
var
if
else
for
while
return
function
```

Non puoi usarle come nomi di variabili:

```js
let let = 5;     // ❌ NO, errore
let const = 10;  // ❌ NO
```

Mentre puoi usare:

```js
let x = 5;               // ✅ OK
let nome = "Mario";      // ✅ OK
let parolaccia = 42;     // ✅ tecnicamente OK (ma forse poco elegante)
```

Il punto è:

> I nomi che scegli per variabili, funzioni, ecc. sono **liberi**,  
> ma non possono coincidere con le parole chiave del linguaggio.

---

### **8. Stringhe di testo**

Un’altra cosa che abbiamo visto è la **stringa**:

```js
console.log("Ciao, sono Edo");
```

Una **stringa** è testo racchiuso tra:

- doppi apici `"`
    
    ```js
    "Ciao"
    ```
    
- oppure apici singoli `'`
    
    ```js
    'Ciao'
    ```
    

JavaScript accetta entrambi, ma devi:

- aprire e chiudere con lo **stesso tipo** di apice.
    

Esempi validi:

```js
"Test"
'Test'
```

Esempi non validi:

```js
"Test'   // ❌ NO, aperto con " e chiuso con '
```

La stringa può essere infilata dentro un `console.log` per mostrarla in console.

---

### **9. Scomponiamo un esempio riga per riga**

Prendiamo tutto quello che hai visto e leggiamolo con consapevolezza:

```js
let x = 5;
console.log("Ciao, sono Edo");
x = 10;
console.log(x);
```

- `let x = 5;`
    
    - `let` → parola chiave
        
    - `x` → nome della variabile (identificatore)
        
    - `=` → operatore di assegnazione
        
    - `5` → valore assegnato
        
    - `;` → fine istruzione
        
- `console.log("Ciao, sono Edo");`
    
    - `console` → oggetto fornito dal browser
        
    - `.` → accesso a una proprietà/metodo dell’oggetto
        
    - `log` → metodo (azione) di `console`
        
    - `("Ciao, sono Edo")` → chiamata del metodo con una stringa come argomento
        
    - `;` → fine istruzione
        
- `x = 10;`
    
    - `x` → variabile già esistente
        
    - `=` → nuova assegnazione
        
    - `10` → nuovo valore
        
    - `;` → fine istruzione
        
- `console.log(x);`
    
    - `console` → oggetto console
        
    - `.log` → metodo per loggare in console
        
    - `(x)` → stampa il valore attuale di `x` (cioè 10)
        
    - `;` → fine istruzione
        

Ora, ogni singolo carattere di queste righe ha un significato chiaro.

---

### **10. Collegamento con altri linguaggi**

Molte delle cose viste in questa lezione:

- esecuzione **dall’alto verso il basso**,
    
- concetto di **istruzione**,
    
- presenza di **keyword**,
    
- uso di **operatori**,
    
- ignoranza degli **spazi bianchi** e delle righe vuote,
    

valgono anche per altri linguaggi di programmazione (Java, C, C++, C#, ecc.).

Ci sono linguaggi speciali (come **Python**) dove:

- la **formattazione** (indentazione) ha valore sintattico,
    
- cioè come vai a capo e come metti gli spazi _cambia il significato del codice_.
    

Ma in JavaScript **no**: l’indentazione serve a rendere il codice leggibile, non a cambiare il comportamento (a parte casi particolari con ASI, ma ci arriveremo).

---

### **11. Cosa vedremo subito dopo: statement vs espressioni**

Adesso hai:

- capito che il codice va dall’alto verso il basso;
    
- visto cos’è un’istruzione (statement);
    
- riconosciuto:
    
    - parole chiave,
        
    - operatori,
        
    - parentesi,
        
    - stringhe,
        
    - oggetti e metodi (`console.log`),
        
    - ruolo del `;`.
        

Nel prossimo step dobbiamo fare un’ulteriore distinzione importante:

> **statement (istruzioni)** vs **espressioni**.

È un concetto che sembra sottile ma è fondamentale per capire bene JavaScript (e la programmazione in generale).

Ci vediamo nella prossima lezione.