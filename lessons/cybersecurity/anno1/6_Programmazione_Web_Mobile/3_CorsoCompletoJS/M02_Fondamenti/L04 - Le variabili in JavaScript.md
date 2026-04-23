## **Lezione 4: Le variabili in JavaScript**

### **1. Che cosa sono le variabili (idea generale)**

Le **variabili** sono un concetto trasversale a quasi tutti i linguaggi di programmazione (JavaScript, C, Java, Python, ecc.), con qualche piccola differenza da linguaggio a linguaggio.

L’idea di base è:

> Una variabile è **uno spazietto di memoria** (in RAM) che il programma usa per **ricordarsi un valore** a cui possiamo accedere tramite un **nome**.

Può contenere, ad esempio:

- un numero: `5`, `42`, `2025`
    
- una stringa: `"John"`, `"Edoardo"`, `"Ciao!"`
    
- in futuro: oggetti, array, risultati di calcoli, ecc.
    

Esempio concettuale:

- voglio inviare email personalizzate:
    
    - “Ciao John, come stai oggi?”
        
    - “Mi spiace John se non ti ho risposto in tempo…”
        
- per farlo, il programma deve **ricordarsi il nome “John”** da qualche parte → lo salva in una **variabile**.
    

Più avanti potremo avere:

- una lista di nomi (`Luca`, `John`, `Mary`, `Susi`, `Anna`, …),
    
- il testo dell’email,
    
- il numero di persone a cui inviarla.
    

Tutte queste informazioni saranno memorizzate in variabili.

---

### **2. Analogia: armadietto in palestra e “nome” nella testa**

Immagina una variabile come:

- un **armadietto** in palestra:
    
    - l’armadietto n. **39** contiene **solo le tue cose**;
        
    - se ti dico “vai all’armadietto 39”, sai esattamente **dove** andare.
        

In programmazione:

- la variabile ha un **nome** (es. `nome`, `numero`, `eta`),
    
- dentro contiene un **valore** (es. `"John"`, `5`, `26`),
    
- se scrivo `console.log(nome)` sto dicendo:
    
    > “Vai all’armadietto chiamato `nome` e mostrami cosa c’è dentro”.
    

Analogia ancora più umana:

- Se ti chiedo: “Qual è il tuo **nome**?”  
    Dentro la tua testa, alla parola “nome” è associato il valore, ad esempio, `"Edoardo"`.
    
- Se ti chiedo: “Qual è il tuo **cognome**?” → altra variabile mentale con un altro valore.
    
- Se ti chiedo: “Quanti anni hai?” → variabile `eta`, che **cambia nel tempo**.
    

In programmazione è identico:

- `nome` → potrebbe essere una **costante** (non cambia spesso),
    
- `eta` → è una **variabile** che cambia ogni anno.
    

---

### **3. Memoria: JavaScript vs linguaggi a basso livello**

A livello tecnico:

- la variabile occupa uno **spazio di memoria** nella **RAM**,
    
- il computer, internamente, gestisce indirizzi del tipo (esempio inventato):
    
    ```text
    0x0034A7F2
    ```
    

Ma noi esseri umani **non possiamo ricordarci** questi indirizzi.  
Quindi usiamo **nomi leggibili**:

- `nome`, `cognome`, `numeroTelefono`, `listaNomi`, `colorePreferito`, ecc.
    

E i nomi delle variabili diventano la nostra “etichetta” per quel pezzo di memoria.

Analogia con il web:

- il sito `codegrind.it` in realtà ha un **indirizzo IP** tipo:
    
    ```text
    192.168.1.23
    ```
    
- ma ricordarsi tutti gli IP di Google, YouTube, Instagram, ecc. sarebbe impossibile;
    
- per questo esistono i **domini** leggibili.
    

Allo stesso modo:

> Una variabile è un “nome amichevole” per un pezzo di memoria il cui indirizzo reale non ci interessa.

In linguaggi come **C / C++**:

- puoi lavorare molto più vicino alla memoria (puntatori, indirizzi, gestione manuale, ecc.),
    
- guadagni in **performance** e **controllo**, ma aumenti la complessità.
    

In **JavaScript**:

- il linguaggio è di **alto livello**:
    
    - non ti fa vedere gli indirizzi di memoria,
        
    - gestisce lui la memoria, lo “spreco”, la pulizia, ecc.
        
- in cambio hai:
    
    - più **libertà**,
        
    - meno rotture di scatole,
        
    - un po’ meno **ottimizzazione estrema** rispetto a C/C++.
        

---

### **4. Come si dichiara una variabile in JavaScript**

In JavaScript abbiamo **tre keyword principali** per creare variabili/costanti:

- `var`
    
- `let`
    
- `const`
    

In questa lezione ci concentriamo sul **concetto generale di variabile**.  
Nel dettaglio:

- `let` e `const` → moderni, standard attuale;
    
- `var` → “vecchio stile”, la vedremo nel prossimo video per capire **cosa evitare**.
    

Esempio semplice:

```js
let nome = "John";
let numero = 5;

console.log(nome);   // → "John"
console.log(numero); // → 5
```

Quando scrivi:

```js
console.log(nome);
```

JavaScript:

- guarda la variabile `nome`,
    
- recupera il valore associato (ad es. `"John"`),
    
- lo stampa in console.
    

Puoi usare la variabile **tutte le volte che vuoi** nel codice:  
il valore rimane quello, finché non lo cambi.

---

### **5. Dichiarazione, assegnazione, inizializzazione**

Ci sono tre parole chiave da distinguere:

1. **Dichiarazione**
    
    > Dire al programma: “Mi serve una variabile con questo nome”.
    
    ```js
    let numero;
    ```
    
    Qui:
    
    - stai **dichiarando** la variabile `numero`,
        
    - ma **non le assegni nessun valore**.
        
2. **Assegnazione**
    
    > Dare un valore a una variabile **già esistente**.
    
    ```js
    numero = 5;
    ```
    
    Qui:
    
    - `numero` esisteva già (dichiarata prima),
        
    - ora le assegni il valore `5`.
        
3. **Inizializzazione**
    
    > Dichiarazione + assegnazione nello **stesso momento**.
    
    ```js
    let numero = 5;
    ```
    
    Qui:
    
    - dichiari `numero`,
        
    - le assegni subito il valore `5`.
        

Ricapitolando:

```js
// Dichiarazione (solo il nome, nessun valore)
let numero;

// Assegnazione (la variabile esiste già, le do un valore)
numero = 5;

// Inizializzazione (dichiarazione + assegnazione sulla stessa riga)
let altroNumero = 10;
```

---

### **6. Cosa succede se uso una variabile senza valore: `undefined`**

Considera questo codice:

```js
let numero;
console.log(numero); // ??
numero = 5;
console.log(numero);
```

Output in console:

```text
undefined
5
```

Spiegazione:

- `let numero;`  
    La variabile **esiste**, ma non ha ancora un valore definito.
    
- `console.log(numero);`  
    JavaScript ti mostra `undefined` → significa **“non definito”**.
    
- `numero = 5;`  
    Adesso assegni un valore.
    
- `console.log(numero);`  
    Ora stampa `5`.
    

Quindi:

> Una variabile dichiarata ma non inizializzata ha valore **`undefined`**.

---

### **7. Cambiare il valore di una variabile (riassegnazione)**

Le variabili sono fatte apposta per poter **cambiare valore nel tempo**.

Esempio:

```js
let numero = 55;
console.log(numero); // 55

numero = 500;
console.log(numero); // 500
```

Esempio anche con una stringa:

```js
let nome = "Edoardo";
console.log(nome);  // "Edoardo"

nome = "Leo Landro";
console.log(nome);  // "Leo Landro"
```

Cosa succede qui?

- all’inizio `nome` contiene `"Edoardo"`,
    
- poi scrivi `nome = "Leo Landro";` → **sovrascrivi** il contenuto,
    
- alla fine, ogni volta che usi `nome`, ottieni `"Leo Landro"`.
    

> Le **variabili** sono pensate per **cambiare**.  
> Le **costanti** (che vedremo tra poco) invece **non possono** essere cambiate dopo l’assegnazione iniziale.

---

### **8. Variabili ed espressioni**

Una variabile può contenere:

- un valore “semplice”:
    
    ```js
    let numero = 5;
    ```
    
- oppure il **risultato di un’espressione**:
    
    ```js
    let numero = 500 + 1000;
    console.log(numero); // 1500
    ```
    

Qui:

- `500 + 1000` è un’**espressione**,
    
- JavaScript la valuta,
    
- ottiene `1500`,
    
- e assegna `1500` alla variabile `numero`.
    

Questo è potentissimo, perché:

- puoi salvare in variabile **risultati di calcoli**, condizioni, ritorni di funzioni, ecc.,
    
- e poi riusare quel risultato ovunque ti serve nel programma.
    

---

### **9. Perché le variabili sono fondamentali**

Le variabili sono **la base di tutto**:

- ti permettono di **non ripeterti**,
    
- ti permettono di **tenere in memoria** dati importanti,
    
- ti permettono di **aggiornare** valori in base a eventi (input utente, timer, risposte da server, ecc.),
    
- ti permettono di costruire **logica complessa** (es. “se l’utente ha età > 18 fai questo, altrimenti fai quest’altro”).
    

Senza variabili:

- dovresti riscrivere continuamente gli stessi valori,
    
- non potresti memorizzare stati,
    
- non potresti creare algoritmi veri, solo script statici.
    

---

### **10. Anticipo: `let`, `var` e `const`**

Per ora abbiamo visto solo:

```js
let nome = "John";
```

Ma in JavaScript ci sono, storicamente, tre modi di “creare” variabili:

```js
var nomeVecchio = "Mario"; // vecchio modo
let nome = "Luca";         // modo moderno
const PI = 3.14;           // costante (non cambia)
```

- `var` → modo “antico”, ancora supportato ma con vari problemi di comportamento (scope, hoisting, ecc.).
    
- `let` → variabile moderna, è ciò che useremo di default.
    
- `const` → costante: come una variabile, ma **non puoi riassegnarle un nuovo valore**.
    

Nelle prossime lezioni vedremo:

- perché **`var` è da evitare** nel codice moderno,
    
- come usare correttamente **`let`**,
    
- come e quando preferire **`const`**.
    

---

### **11. Riepilogo**

In questa lezione abbiamo visto che:

- una **variabile** è uno **spazio di memoria** (in RAM) a cui accediamo tramite un **nome**;
    
- serve per **ricordare valori** che vogliamo riutilizzare (nome, numeri, testi, ecc.);
    
- il **nome** è un’etichetta leggibile per noi, mentre il computer usa indirizzi incomprensibili;
    
- in JavaScript dichiariamo variabili con:
    
    ```js
    let x;        // dichiarazione
    x = 5;        // assegnazione
    let y = 10;   // inizializzazione (dichiarazione + assegnazione)
    ```
    
- una variabile dichiarata ma non inizializzata ha valore **`undefined`**;
    
- possiamo **cambiare** il valore di una variabile tutte le volte che vogliamo:
    
    ```js
    x = 55;
    x = 500;
    ```
    
- possiamo assegnare a una variabile il **risultato di un’espressione**:
    
    ```js
    let numero = 500 + 1000; // 1500
    ```
    
- le variabili sono la base per **evitare ripetizioni** e costruire **logica complessa**;
    
- a livello di sintassi moderna useremo principalmente `let` e `const`, e nel prossimo video inizieremo il confronto con `var`.
    

Nella prossima lezione entriamo più nel dettaglio di:

> **`let` vs `var` vs `const`**  
> perché esistono tre parole, cosa le differenzia e quando usare che cosa.