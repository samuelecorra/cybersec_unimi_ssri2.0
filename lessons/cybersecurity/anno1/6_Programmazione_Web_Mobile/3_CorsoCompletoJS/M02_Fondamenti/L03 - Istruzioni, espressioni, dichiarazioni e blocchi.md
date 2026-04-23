## **Lezione 3: Istruzioni, espressioni, dichiarazioni e blocchi**

### **1. Perché facciamo ancora teoria prima delle variabili**

In questa lezione restiamo ancora sul **livello teorico**, prima di iniziare concretamente a programmare con le **variabili**.

- Se questo fosse un mini-corso “veloce” da YouTube, probabilmente tutta questa parte la **salteremmo**.
    
- In un corso **completo**, invece, ha senso fermarsi un attimo e mettere a posto il **vocabolario**:
    
    - che cos’è una _dichiarazione_,
        
    - che cos’è una _istruzione_ (statement),
        
    - che cos’è una _espressione_,
        
    - che cos’è un _blocco di codice_.
        

> Se alcune cose non ti entrano subito: **non stressarti**.  
> Puoi programmare tranquillamente senza saper dare il nome tecnico a tutto.  
> Questi concetti **maturano con la pratica**.

Io stesso, all’inizio, programmavo e facevo piccoli siti e applicazioni senza sapere tutte queste definizioni.  
Sapevo “come farlo”, ma non avrei saputo **spiegarlo in modo teorico**.

Qui stiamo facendo il contrario: ti do subito gli strumenti concettuali che poi, col tempo, diventeranno **naturali**.

---

### **2. Dichiarazioni: dire al programma che “esiste” qualcosa**

Una **dichiarazione** (declaration) è un’istruzione in cui “annunciamo” al programma che esiste qualcosa di nuovo:

- una variabile,
    
- una funzione,
    
- una classe.
    

#### **2.1 Dichiarazione di variabile**

Esempio:

```js
let x = 5;
```

Qui stiamo:

- usando la parola chiave `let` → **keyword di JavaScript**,
    
- dicendo al programma:
    
    > “Crea una variabile che si chiama `x` e, già che ci sei, inizializzala con il valore `5`.”
    

Questa riga è:

- una **istruzione** (perché è una riga di codice eseguibile),
    
- nello specifico, è una **dichiarazione di variabile**.
    

Se dopo scrivo:

```js
x = 10;
```

questa **non è più una dichiarazione**:

- non sto creando una nuova variabile,
    
- sto **modificando il valore** di una variabile **già esistente**.
    

Quindi:

- `let x = 5;` → dichiarazione (e inizializzazione).
    
- `x = 10;` → semplice assegnazione, **non** dichiarazione.
    

#### **2.2 Dichiarazione di funzione**

Esempio classico:

```js
function saluta() {
  console.log("Ciao!");
}
```

Qui abbiamo:

- keyword `function`,
    
- nome della funzione `saluta`,
    
- `()` parentesi tonde,
    
- `{ ... }` che contengono il **blocco di codice** della funzione.
    

Questa riga/tutto questo blocco è una:

- **istruzione**,
    
- nello specifico → **dichiarazione di funzione** (function declaration).
    

#### **2.3 Dichiarazione di classe**

Più avanti parleremo di programmazione a oggetti, ma per ora basta l’idea:

```js
class Persona {
  // corpo della classe
}
```

Qui:

- `class` è una **keyword**,
    
- `Persona` è il nome della classe.
    

Questa è una:

- **istruzione**,
    
- nello specifico → **dichiarazione di classe**.
    

> Regola mentale:  
> ogni volta che vedi una keyword come `let`, `const`, `var`, `function`, `class` davanti a un nome, **quasi sempre stai dichiarando qualcosa di nuovo**.

---

### **3. Espressioni: pezzi di codice che producono un valore**

Ora entriamo nella distinzione tra:

- **istruzioni (statement)** → “fai qualcosa”
    
- **espressioni** → “calcola qualcosa e dammi un **valore**”
    

In matematica un’espressione è qualcosa tipo:

> (5 + 3 \div 2 \times 5^2 - x)

Alla fine deve **restituire un valore** (un numero).  
In programmazione il concetto è lo stesso: un’espressione è un pezzo di codice che, **valutato**, produce un valore.

#### **3.1 Esempio semplice: somma**

```js
let risultato = 5 + 3;
```

Qui:

- L’**istruzione** completa è `let risultato = 5 + 3;`
    
- L’**espressione** è la parte:
    
    ```js
    5 + 3
    ```
    

L’espressione `5 + 3` viene **valutata** e produce il valore `8`, che viene poi assegnato alla variabile `risultato`.

Quindi:

- non stiamo assegnando “il testo `5 + 3`”,
    
- stiamo assegnando **il risultato** dell’espressione (`8`).
    

#### **3.2 Esempio con operatore ternario**

Immagina di avere:

```js
let prova = risultato > 10 ? "ciao" : "altro";
```

Qui:

- l’intera parte
    
    ```js
    risultato > 10 ? "ciao" : "altro"
    ```
    
    è un’**espressione** (specificamente un’espressione con operatore ternario).
    

Come funziona:

- se `risultato > 10` è **true** → il valore dell’espressione è `"ciao"`,
    
- se è **false** → il valore dell’espressione è `"altro"`.
    

Di nuovo:

- non stiamo assegnando “il ternario”,
    
- stiamo assegnando **il valore che risulta** dall’espressione ternaria.
    

#### **3.3 Analogia con l’orologio**

Immagina:

- ora attuale: 14:54,
    
- devi uscire tra 45 minuti.
    

Non dici:

> “L’orario di uscita è 14:54 + 45 minuti”.

Dici:

> “L’orario di uscita è 15:39” (o 15:38, a seconda di come fai il calcolo).

In codice, l’idea è la stessa:

- `14:54 + 45 minuti` → è un’**espressione**,
    
- `15:39` → è il **risultato** dell’espressione.
    

In programmazione, ciò che **assegni** a una variabile è sempre un **valore** (il risultato), non “il testo del calcolo”.

---

### **4. Statement (istruzioni) che contengono espressioni**

Torniamo a questa riga:

```js
let risultato = 5 + 3;
```

Possiamo scomporla così:

- è una **istruzione (statement)** → una riga che il programma esegue;
    
- dentro la dichiarazione c’è un’**espressione** (`5 + 3`) che viene valutata.
    

Quindi:

- “istruzione” e “espressione” **non si escludono**,
    
- spesso un’istruzione **contiene** una o più espressioni.
    

Altri esempi:

```js
let x = 10;              // dichiarazione + espressione (il valore 10)
let y = x * 2;           // dichiarazione + espressione (x * 2)
console.log(y + 3);      // istruzione: chiamata a funzione con espressione (y + 3)
```

---

### **5. Espressioni di funzione (accenno)**

Esistono anche modi diversi di definire le funzioni, ad esempio:

```js
const somma = function (a, b) {
  return a + b;
};
```

Qui:

- `function (a, b) { ... }` è un’**espressione di funzione** (function expression),
    
- il risultato di quell’espressione (cioè “la funzione stessa”) viene assegnato alla variabile `somma`.
    

Oppure con una arrow function:

```js
const somma = (a, b) => a + b;
```

Anche qui abbiamo:

- un’**espressione** che produce una **funzione** come valore.
    

Non entriamo nel dettaglio ora perché:

- è un argomento che vedremo **bene** quando studieremo le funzioni,
    
- rischia di confondere inutilmente se sei all’inizio.
    

Tieni solo a mente che:

> Anche una funzione può essere il risultato di un’espressione.

---

### **6. Blocchi di codice `{}`: contenitori di istruzioni**

Un **blocco di codice** è un insieme di istruzioni racchiuse tra **parentesi graffe** `{}`.

Esempio:

```js
if (condizione) {
  let x = 5;
  console.log(x);
}
```

Qui:

- `if (condizione) { ... }` è un’**istruzione** (`if` è una istruzione di controllo del flusso),
    
- dentro le `{ ... }` abbiamo un **blocco di codice**,
    
- il blocco contiene **altre istruzioni**:
    
    - `let x = 5;`
        
    - `console.log(x);`
        

Il blocco è semplicemente:

> Un “contenitore” di più istruzioni, raggruppate insieme.

Lo stesso discorso vale per altri costrutti:

```js
for (let i = 0; i < 10; i++) {
  console.log(i);
}
```

- `for (...) { ... }` → istruzione `for` (ciclo),
    
- `{ ... }` → **blocco di codice** eseguito ad ogni iterazione.
    

Puoi immaginare la gerarchia così:

- **istruzione `if`**
    
    - contiene un **blocco**
        
        - che contiene **altre istruzioni** (dichiarazioni, espressioni, ecc.)
            

---

### **7. Mettere insieme tutti i pezzi**

Ricostruiamo il quadro generale:

- **Istruzione (statement)**:
    
    - è una riga/comando che viene eseguita dal programma;
        
    - esempi: dichiarazione di variabile, `if`, `for`, chiamata a funzione, ecc.
        
- **Dichiarazione**:
    
    - è un tipo di istruzione con cui “annunci” qualcosa di nuovo:
        
        - dichiarazione di variabile (`let x = 5;`),
            
        - dichiarazione di funzione (`function f() { ... }`),
            
        - dichiarazione di classe (`class Persona { ... }`).
            
- **Espressione**:
    
    - è una porzione di codice che **restituisce un valore**;
        
    - può essere:
        
        - aritmetica (`5 + 3`),
            
        - logica (`x > 10`),
            
        - ternaria (`condizione ? valore1 : valore2`),
            
        - una funzione (`(a, b) => a + b`),
            
        - ecc.
            
    - spesso un’istruzione **contiene** una o più espressioni.
        
- **Blocco di codice**:
    
    - è un insieme di istruzioni racchiuse tra `{` e `}`,
        
    - usato ad esempio in `if`, `for`, funzioni, classi, ecc.
        

Queste parole non esistono “tanto per”: sono il **vocabolario** che userai per parlare di codice con altri programmatori.

---

### **8. Perché questa teoria è utile (anche se puoi vivere senza)**

Onestamente:

- puoi costruire **siti reali**, web app, perfino qualcosa di grande,
    
- senza sapere formalmente che “questa è una dichiarazione” o “questa è un’espressione”.
    

Però:

1. **Per parlare con altri programmatori**  
    La teoria è il nostro **gergo comune**:
    
    - “Ho dichiarato una variabile…”
        
    - “Questa espressione ritorna un boolean…”
        
    - “Dentro al blocco dell’`if` ho messo…”  
        Se non sai che parole usare, fai più fatica a farti capire.
        
2. **Per leggere la documentazione**  
    Documentazione ufficiale, guide, blog tecnici usano sempre questi termini:
    
    - statement, expression, block, declaration, function expression, ecc.  
        Senza questi concetti, la documentazione diventa molto più opaca.
        
3. **Per salire di livello**  
    La pratica ti porta lontano,  
    ma la teoria ti permette di:
    
    - ragionare meglio sul codice,
        
    - riconoscere pattern,
        
    - spiegare quello che fai,
        
    - imparare concetti avanzati più velocemente.
        

L’idea giusta è:

> Non serve **memorizarli a tavolino** adesso.  
> Basta che tu ci faccia l’orecchio.  
> Li ritroveremo in tutto il corso, e si consolideranno da soli.

---

### **9. Prossimo passo: finalmente le variabili**

Ora che abbiamo:

- visto la sintassi di base,
    
- capito cosa sono istruzioni, dichiarazioni, espressioni e blocchi,
    

siamo pronti per iniziare davvero la programmazione pratica in JavaScript partendo dal concetto fondamentale:

> **Che cos’è una variabile?**

Le abbiamo già usate nei vari esempi, ma nella prossima lezione le mettiamo **sotto la lente** in modo sistematico.