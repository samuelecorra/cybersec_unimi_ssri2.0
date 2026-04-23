## **Lezione 6: Costanti in JavaScript (`const`)**

In questa lezione completiamo il quadro sulle **“celle di memoria”** in JavaScript:

- abbiamo già visto le **variabili** (`let`)
    
- ora vediamo le **costanti** (`const`)
    

Vedrai che a livello di “cella di memoria” sono la _stessa cosa_:  
quello che cambia è **se il loro valore è modificabile oppure no**.

---

### **1. Cosa sono le costanti (`const`)**

A livello puramente tecnico:

- una **variabile** e una **costante** sono entrambe **uno spazietto in RAM**,
    
- identificate da un **nome**,
    
- che contengono un **valore**.
    

Quindi, a livello di memoria:

```js
let x = 10;
const y = 10;
```

sono **due celle di memoria** con un numero dentro.

La differenza è:

- con `let` il valore **può cambiare** nel tempo → _variabile_
    
- con `const` il valore **non può essere riassegnato** → _costante_
    

---

### **2. Variabile vs costante: il concetto chiave**

Con `let`:

```js
let eta = 10;

// compio gli anni
eta = eta + 1;  // 11
// oppure:
eta++;          // 12
// oppure:
eta += 1;       // 13
```

La variabile `eta` può cambiare, perché è **variabile per definizione**.

Con `const`:

```js
const ETA = 10;

ETA = ETA + 1;  // ❌ ERRORE
```

JavaScript ti segnala:

> **Assignment to constant variable**  
> (Assegnazione a una variabile costante)

Qui la parola “variable” non vuol dire “che cambia”, ma “cella di memoria / identificatore”.  
Quindi il messaggio significa:

> stai cercando di cambiare il valore di una **cella che è stata dichiarata costante**.

---

### **3. `const` deve essere inizializzata subito**

Con `let` puoi dichiarare una variabile senza darle subito un valore:

```js
let numero;
numero = 5;
```

Con `const` **non puoi**:

```js
const PI_GRECO;   // ❌ ERRORE: Missing initializer in const declaration
```

Messaggio tipico:

> **Missing initializer in const declaration**  
> (“Manca il valore iniziale in una dichiarazione `const`”)

Perché?

- una **costante** NON può essere riassegnata,
    
- se la dichiari **senza valore**, resterebbe per sempre senza valore,
    
- quindi JavaScript ti obbliga a **dichiararla e inizializzarla insieme**.
    

Quindi:

```js
// ✅ corretto
const PI_GRECO = 3.14159;
```

Qui hai:

- **dichiarazione + assegnazione** → **inizializzazione** in un colpo solo.
    

---

### **4. Perché usare le costanti? (parte logica)**

#### 4.1. Valori che nel programma non devono cambiare

Tipici esempi:

```js
const PI_GRECO = 3.1415926535;
const GIORNI_SETTIMANA = 7;
const SECONDI_IN_GIORNO = 24 * 60 * 60;
const URL_API = "https://api.miosito.com";
```

Questi valori:

- nel **contesto del programma** sono considerati fissi;
    
- se li cambi **per sbaglio**, distruggi tutti i calcoli a valle.
    

Esempio:

```js
const GIORNI_SETTIMANA = 7;
const SECONDI_IN_GIORNO = 24 * 60 * 60;

const SECONDI_IN_SETTIMANA = GIORNI_SETTIMANA * SECONDI_IN_GIORNO;
```

Se qualcuno per errore facesse:

```js
GIORNI_SETTIMANA = 8;   // con let sarebbe possibile
```

tutti i risultati basati su quella costante diventerebbero falsi.

Con `const`:

- se qualcuno **ci prova**, il programma lancia un errore,
    
- e tu ti accorgi subito del bug.
    

Quindi usare `const` è un modo per dire:

> “Questo valore non _deve_ cambiare mai durante l’esecuzione.  
> Se provo a cambiarlo, fermami.”

---

#### 4.2. Esempio “realistico”: numero di studenti per classe

Immagina:

```js
const NUMERO_STUDENTI_PER_CLASSE = 22;
```

Nel mondo reale:

- la legge può cambiare domani → 20 studenti, 25, ecc.
    
- quindi diresti: “È un numero _variabile_”.
    

Nel **codice**, però:

- finché la legge **non cambia**, per noi è **fisso**;
    
- se cambia, **noi** andiamo a modificare **a mano** la riga:
    
    ```js
    const NUMERO_STUDENTI_PER_CLASSE = 20;
    ```
    

Quindi nel codice:

- non serve che la variabile sia “modificabile” in runtime,
    
- ci basta che sia facilmente **aggiornabile nel sorgente** quando serve.
    

Da qui l’idea:

> nel codice, tutto ciò che non deve cambiare **durante l’esecuzione**  
> è un’ottima candidata per essere una **costante**.

---

### **5. Convenzione di naming per le costanti**

Per le costanti si usa quasi ovunque questa convenzione:

- **TUTTO MAIUSCOLO**
    
- parole separate da **underscore `_`**
    

Si chiama:

- **UPPER_SNAKE_CASE**
    

Esempi:

```js
const PI_GRECO = 3.14159;
const GIORNI_SETTIMANA = 7;
const SECONDI_IN_GIORNO = 86400;
const NUMERO_STUDENTI_PER_CLASSE = 22;
```

Perché è utile?

1. **Visivamente** distingui subito costanti e variabili:
    
    ```js
    const GIORNI_SETTIMANA = 7;
    let etaPersona = 19;
    
    const SECONDI_IN_SETTIMANA = GIORNI_SETTIMANA * SECONDI_IN_GIORNO;
    ```
    
    Qui vedi al volo:
    
    - `GIORNI_SETTIMANA` e `SECONDI_IN_GIORNO` → costanti
        
    - `etaPersona` → variabile
        
2. In mezzo a **centinaia di righe**, l’occhio “aggancia” subito le **costanti**.
    

---

### **6. Perché non usare `let` ovunque e basta?**

Domanda lecita:

> “Non posso semplicemente usare sempre `let` e stare attento a non cambiare certe variabili?”

In teoria sì.  
In pratica:

- **prima o poi qualcuno sbaglia**:
    
    - tu di fretta,
        
    - un collega,
        
    - uno stagista,
        
    - un refactor automatico,
        
- e modifica una variabile che _doveva_ restare fissa.
    

Esempio:

```js
let GIORNI_SETTIMANA = 7;

// moltipliconi vari...

// Qualcuno più giù nel codice:
GIORNI_SETTIMANA = 8;   // “test veloce”, “mi sono sbagliato”, “non ho guardato bene”
```

Risultato:

- nessun errore sintattico,
    
- nessun warning automatico,
    
- ma **risultati sbagliati** dappertutto (errore logico).
    

Con `const`:

```js
const GIORNI_SETTIMANA = 7;

GIORNI_SETTIMANA = 8;   // ❌ ti esplode in faccia subito
```

Quindi `const`:

- è una specie di **“cintura di sicurezza”** del linguaggio,
    
- serve a **proteggerti dai tuoi stessi errori** (e da quelli degli altri).
    

Esattamente come `let` vs `var`:

- la sintassi più moderna è costruita per **farti sbagliare meno**.
    

---

### **7. Riassunto operativo**

1. **Costante = celletta di memoria “bloccata” nella riassegnazione**
    
    ```js
    const PI_GRECO = 3.14159;
    // PI_GRECO = 3.2;  // ❌ errore
    ```
    
2. Con `const` sei **obbligato** a inizializzare subito:
    
    ```js
    const x;      // ❌ ERRORE
    const y = 10; // ✅
    ```
    
3. Usa `const` per:
    
    - valori “fissi” nel tuo dominio:
        
        - `PI_GRECO`, `GIORNI_SETTIMANA`, `SECONDI_IN_GIORNO`, `URL_API`, ecc.
            
    - configurazioni, parametri, numeri magici che non devono cambiare durante l’esecuzione.
        
4. Convenzione di naming:
    
    - **variabili**: `camelCase` → `prezzoUnitario`, `etaPersona`, `numeroStudenti`
        
    - **costanti**: `UPPER_SNAKE_CASE` → `PI_GRECO`, `GIORNI_SETTIMANA`, `NUMERO_STUDENTI_PER_CLASSE`
        
5. Regola d’oro nel codice moderno:
    
    ```js
    // per valori che NON devono cambiare
    const ...
    
    // per valori che cambiano nel tempo
    let ...
    
    // non usare var in codice nuovo
    ```
    

---

Se vuoi, nel prossimo passo possiamo fare un mini blocco di **esercizi mirati** solo su:

- riconoscere cosa deve essere `const` e cosa `let`,
    
- rinominare variabili in modo pulito,
    
- trasformare un pezzetto di codice “sporco” in uno stile **let/const + naming corretto**.