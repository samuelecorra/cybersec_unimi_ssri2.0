## **Lezione 18: Introduzione alle stringhe in JavaScript**

In questa lezione entriamo nel mondo delle **stringhe**, uno dei tipi di dato più importanti in assoluto.

L’idea chiave da fissare è:

> Una **stringa** è semplicemente **testo**: una sequenza di caratteri.

Nomi, cognomi, frasi, codici fiscali, numeri di telefono scritti come testo, password, messaggi di chat… sono tutti rappresentati come **stringhe**.

---

### **1. Cos’è una stringa**

In JavaScript, una stringa è un valore di tipo `string` che rappresenta testo.

Esempio:

```js
let testo = 'Ciao, sono Luca';
```

Qui `testo` contiene una stringa.

Le stringhe vengono usate soprattutto per:

- mostrare testo a schermo (nella pagina o in console);
    
- memorizzare dati testuali (nomi, email, password, messaggi, codici, numeri di telefono, ecc.).
    

In gergo:

> Tutta la “roba testuale” in programmazione viene chiamata **stringa**  
> (in inglese `string`).

---

### **2. Come si scrive una stringa in JavaScript**

JavaScript permette **tre sintassi** per definire una stringa:

```js
// 1. Apici singoli
let testo1 = 'Ciao, sono Luca';

// 2. Doppi apici
let testo2 = "Ciao, sono Luca";

// 3. Backtick (template literal)
let testo3 = `Ciao, sono Luca`;
```

Se fai:

```js
console.log(typeof testo1);
console.log(typeof testo2);
console.log(typeof testo3);
```

otterrai sempre:

```txt
string
string
string
```

Quindi:

- con **apici singoli** (`'...'`) → stringa;
    
- con **doppi apici** (`"..."`) → stringa;
    
- con **backtick** (`` `...` ``) → tecnicamente sono detti **template literal**, ma anche loro sono stringhe, solo più “potenziate”.
    

Per ora:

- concentrati su **apici singoli** e **doppi apici**;
    
- terremo i **template literal** per una lezione dedicata.
    

---

### **3. Problema classico: apici, apostrofi e “escape”**

Il problema tipico è questo:

```js
let frase = 'Ciao, sono Luca, il “cupido dell\'amore”';
```

Oppure:

```js
let frase = 'Ciao, sono Luca, il cupido dell'amore';
```

Se scrivi qualcosa del genere **senza** attenzione, VS Code ti segnala errore.

Perché succede?

#### **3.1. Il conflitto di caratteri delimitatori**

Se usi:

```js
let frase = 'Ciao, sono Luca, il cupido dell'amore';
```

JavaScript ragiona così:

- vede `'` iniziale → “inizio stringa”;
    
- scorre i caratteri fino a `dell'`;
    
- l’apostrofo di `dell'` viene interpretato come **fine della stringa**;
    
- tutto ciò che viene dopo sembra “codice rotto”.
    

Risultato: **errore di sintassi**.

Stessa cosa se usi le **virgolette doppie** dentro una stringa delimitata da `"`.

#### **3.2. Soluzione 1: scegliere l’altro delimitatore**

Se sai che dentro userai l’apostrofo, puoi usare i doppi apici:

```js
let frase = "Ciao, sono Luca, il cupido dell'amore";
```

Se sai che dentro userai le virgolette (`"qualcosa"`), puoi usare gli apici singoli:

```js
let frase = 'Mia madre dice: "Vai a scuola!"';
```

Spesso questa è la soluzione più semplice.

#### **3.3. Soluzione 2: l’escape dei caratteri**

A volte però non puoi o non vuoi cambiare i delimitatori.  
Allora usi il cosiddetto **escape dei caratteri** tramite il backslash `\`.

Esempi:

```js
let frase1 = 'Ciao, sono Luca, il cupido dell\'amore';
let frase2 = "Mia madre dice: \"Vai a scuola!\"";
```

Il simbolo `\` dice a JavaScript:

> “Il carattere che viene subito dopo non va interpretato come fine stringa o simbolo speciale, ma come semplice **carattere di testo**.”

Quindi:

- `\'` ⇒ un apostrofo **dentro** una stringa delimitata da `'...'`;
    
- `\"` ⇒ una virgolette **dentro** una stringa delimitata da `"..."`.
    

L’operazione di mettere il `\` si chiama appunto **escape** del carattere.

---

### **4. Le stringhe come sequenze di caratteri**

Concetto fondamentale:

> Una **stringa** è una **sequenza ordinata** di caratteri.

Esempio:

```js
let nome = 'Edoardo';
```

Questa stringa è composta da 7 caratteri:

- `'E'`
    
- `'d'`
    
- `'o'`
    
- `'a'`
    
- `'r'`
    
- `'d'`
    
- `'o'`
    

Anche gli **spazi** sono caratteri a tutti gli effetti.

---

### **5. La proprietà `.length`: lunghezza della stringa**

Ogni stringa ha una proprietà `.length` che indica **quanti caratteri contiene**.

Esempio:

```js
let nome = 'Edoardo';
console.log(nome.length);   // 7
```

`length` restituisce un **numero**, non una stringa.

È importante ricordare:

- `.length` = numero di caratteri totali della stringa.
    

---

### **6. Accesso ai singoli caratteri (indicizzazione)**

Se una stringa è una sequenza ordinata di caratteri, allora:

> ogni carattere ha una **posizione** (indice) interna.

In JavaScript, come in quasi tutti i linguaggi moderni, l’indice parte da **0**.

Per accedere al singolo carattere si usa la notazione con le parentesi quadre:

```js
let nome = 'Edoardo';

console.log(nome[0]); // 'E'
console.log(nome[1]); // 'd'
console.log(nome[2]); // 'o'
console.log(nome[3]); // 'a'
console.log(nome[4]); // 'r'
console.log(nome[5]); // 'd'
console.log(nome[6]); // 'o'
console.log(nome[7]); // undefined
```

Osservazioni:

- Il primo carattere ha indice `0`.
    
- L’ultimo carattere ha indice `length - 1`.
    
- Se provi a leggere oltre (`nome[7]` in questo caso), ottieni `undefined`.
    

Metafora:

- immagina la stringa come una **via piena di villette**;
    
- ogni villetta è un carattere;
    
- il “civico” parte da `0` e arriva a `length - 1`.
    

---

### **7. Combinare `.length` e gli indici: l’ultimo carattere**

Spesso ci serve l’**ultimo carattere** della stringa, ma la lunghezza può cambiare (perché il testo lo inserisce l’utente, o arriva da un API).

Non possiamo scrivere a mano l’indice (tipo `[6]`), perché non sappiamo a priori la lunghezza.

Soluzione:

1. prendiamo la lunghezza: `nome.length`;
    
2. sottraiamo 1 per l’ultimo indice: `nome.length - 1`;
    
3. usiamo quell’indice per accedere al carattere.
    

Esempio:

```js
let nome = 'Edoardo';

let ultimoIndice = nome.length - 1;
let ultimoCarattere = nome[ultimoIndice];

console.log(ultimoCarattere);   // 'o'
```

In forma più compatta:

```js
let nome = 'Edoardo';

console.log(nome[nome.length - 1]); // 'o'
```

Questo pattern:

> `stringa[stringa.length - 1]`

è il modo standard per prendere l’**ultimo carattere** di una stringa in JavaScript.

---

### **8. Riassunto della lezione**

Concetti da portarti via:

- Una **stringa** è testo: una sequenza di caratteri.
    
- Puoi definirla con:
    
    - `'...'` (apici singoli);
        
    - `"..."` (doppi apici);
        
    - `` `...` `` (template literal, che vedremo dopo).
        
- Se usi lo stesso simbolo dentro e fuori (apostrofo o virgolette) rischi di **chiudere la stringa per sbaglio**.
    
- Per evitare errori:
    
    - scegli l’altro delimitatore **oppure**
        
    - usa l’**escape**: `\'`, `\"`.
        
- `.length` ti dice **quanti caratteri** ha la stringa.
    
- Puoi accedere al singolo carattere con `stringa[indice]`.
    
- Gli indici partono da `0` e arrivano a `length - 1`.
    
- L’ultimo carattere si prende con:
    

```js
stringa[stringa.length - 1];
```

---

Quando vuoi, passiamo al prossimo video sulle stringhe, dove inizieremo a vedere **metodi** e manipolazioni più interessanti 😈📜