## **Lezione 10: Conversione e Coercizione dei tipi di dato in JavaScript**

In questa lezione mettiamo ordine su due parole che troverai spesso in documentazione:

- **conversione** (conversion)
    
- **coercizione** (coercion)
    

Sono entrambe trasformazioni di **tipo di dato**, ma avvengono in modi diversi:

- **Conversione esplicita** → sei **tu** a chiedere la conversione.
    
- **Coercizione implicita** → è **JavaScript** che converte automaticamente “dietro le quinte”.
    

Capire bene cosa succede ti evita bug “magici” tipo `123` che si comporta da stringa, oppure confronti che sembrano veri “a occhio” ma sono `false` in JS.

---

## **1. Conversione esplicita vs coercizione implicita**

### **1.1 Conversione esplicita (conversione “manuale”)**

È quando **sei tu** a dire al motore JS:

> “Prendi questo valore e trasformalo in un altro tipo.”

Esempi classici:

```js
Number("123");   // da stringa → number
String(123);     // da number → stringa
Boolean(0);      // da number → boolean
```

Qui la conversione è **intenzionale**: nel codice si vede chiaramente che vuoi cambiare tipo.

---

### **1.2 Coercizione implicita (conversione automatica)**

È quando **JavaScript decide da solo** di convertire un valore in un altro tipo, perché l’operatore o il contesto lo “costringono”.

Esempi:

- concatenazione stringhe con `+`
    
- confronti con `==`
    
- alcune operazioni aritmetiche
    

Tu non hai scritto `Number(...)` o `String(...)`, ma il motore JS, per poter fare l’operazione, converte automaticamente.

---

## **2. Differenza tra numero e “numero stringa”**

Partiamo da un esempio che sembra stupido, ma è la base di tutto:

```js
let numero1 = 123;      // numero
let numeroStringa = "123";  // stringa

console.log(typeof numero1);        // "number"
console.log(typeof numeroStringa);  // "string"
```

Per **i tuoi occhi** sembrano identici.  
Per **JavaScript** sono due mondi diversi:

- `123` → un **numero**
    
- `"123"` → una **stringa di testo** (tre caratteri: `'1'`, `'2'`, `'3'`)
    

E questo cambia radicalmente cosa succede con gli operatori.

---

## **3. Esempio chiave: `+` con numero vs `+` con stringa**

### **3.1 Con un vero `number`**

```js
let numero = 123;
let risultato = numero + 1;

console.log(risultato);       // 124
console.log(typeof risultato); // "number"
```

JS vede due numeri, quindi fa **somma aritmetica**.

---

### **3.2 Con una “stringa numero”**

```js
let numeroStringa = "123";
let risultato = numeroStringa + 1;

console.log(risultato);        // "1231"
console.log(typeof risultato); // "string"
```

Qui succede la magia:

- `numeroStringa` è una **stringa** (`"123"`)
    
- l’operatore `+` con una stringa fa **concatenazione**, non somma
    
- quindi `"123" + 1` diventa `"1231"`
    

Per JS `"123"` è come `"ciao"`:

```js
"ciao" + 1    // "ciao1"
"123" + 1     // "1231"
```

Il tuo cervello vede “numero”, ma per JS è solo testo.

---

## **4. Quando la conversione esplicita è necessaria**

### **4.1. Input da form: numeri che arrivano come stringhe**

In un form HTML, se l’utente inserisce la sua età `30`, il valore arriva in JS come **stringa**:

```js
let eta = "30";  // arriva così da un input

// Se fai questo:
let dopoUnAnno = eta + 1;
console.log(dopoUnAnno);   // "301"  (stringa concatenata)
```

Se vuoi fare calcoli, devi **convertire esplicitamente**:

```js
let eta = "30";

let etaNumero = Number(eta);
let dopoUnAnno = etaNumero + 1;

console.log(dopoUnAnno);        // 31
console.log(typeof dopoUnAnno); // "number"
```

Questa è **conversione esplicita**: sei tu a chiamare `Number(...)`.

---

### **4.2. Quando invece ti serve una stringa (es. numero di telefono)**

Per un **numero di telefono**:

```js
let telefono = "0278 123 456";  // meglio come stringa
```

Se lo trattassi come numero:

```js
let telefono = 0278123456;
```

Problemi:

- lo zero iniziale **sparisce** (per un number non ha senso),
    
- perdi formattazione (spazi, trattini),
    
- rischi di superare i limiti dei numeri se il numero è lungo (es. IBAN).
    

Quindi:

- per **fare calcoli** → converto a `number`
    
- per **identificare, formattare, stampare** (telefono, IBAN, codice fiscale) → tengo `string`
    

---

## **5. Conversione esplicita con `Number`, `String`, `Boolean`**

Esempio dalla lezione:

```js
let numeroStringa = "123";

let numero = Number(numeroStringa);   // conversione esplicita
console.log(numero);                  // 123
console.log(typeof numero);           // "number"

let somma = numero + 100;
console.log(somma);                   // 223
```

Se la stringa non è un numero valido:

```js
let sporca = "123abc";
let n = Number(sporca);

console.log(n);        // NaN
console.log(typeof n); // "number"
```

`NaN` = **Not a Number**  
È un valore speciale di tipo `number` che indica “non sono riuscito a convertire”.

(Lo approfondirai quando parlerete dei numeri.)

---

## **6. Coercizione implicita: quando è JS a convertire da solo**

### **6.1 Concatenazione di stringhe**

```js
let numero = 5;
let risultato = "Il numero è " + numero;

console.log(risultato);        // "Il numero è 5"
console.log(typeof risultato); // "string"
```

Qui succede:

- hai una stringa `"Il numero è "`
    
- hai un number `5`
    
- l’operatore `+` vede una **stringa** → converte il `5` in `"5"`
    
- risultato finali: `"Il numero è 5"` (stringa)
    

Questa è **coercizione da number → string**.

---

### **6.2 Confronti: `==` vs `===`**

Blocchetto importante:

```js
let stringa = "5";   // stringa
let numero  = 5;     // number
```

#### Con `==` (confronto “lasco”)

```js
let confronto1 = (stringa == numero);
console.log(confronto1);  // true
```

Cosa fa JS:

- vede che i tipi sono diversi (string vs number),
    
- **fa coercizione**: converte `"5"` in `5`,
    
- confronta `5 == 5` → `true`.
    

#### Con `===` (confronto “stretto”)

```js
let confronto2 = (stringa === numero);
console.log(confronto2);  // false
```

Qui:

- **non** fa coercizione,
    
- controlla sia il **valore** sia il **tipo**,
    
- `"5"` (string) **non è identico** a `5` (number) → `false`.
    

Riassunto:

- `==` → “i valori sono uguali **dopo** eventuali conversioni automatiche?”
    
- `===` → “i valori sono uguali **e** hanno lo stesso tipo?”
    

**Best practice** in JS moderno:  
usa **quasi sempre `===` e `!==`** per evitare sorprese da coercizione implicita.

---

## **7. Come usare queste idee nella pratica**

### **7.1 Quando usare conversione esplicita**

- Quando leggi input da:
    
    - form HTML,
        
    - prompt,
        
    - API esterne …
        
- e ti serve fare calcoli → usa `Number(...)`, `parseInt(...)`, `parseFloat(...)`.
    
- Quando ti serve una stringa:
    
    - per concatenare,
        
    - per mostrare a schermo,
        
    - per formattare → usa `String(...)` o template string (con backtick, più avanti).
        

---

### **7.2 Come gestire la coercizione implicita senza farti male**

- **Concatenazione con `+`**:
    
    - se una delle parti è stringa → il risultato è stringa.
        
    - se ti serve somma numerica, assicurati che siano **entrambi numeri**.
        
- **Confronti**:
    
    - evita `==` se non sei assolutamente certo di cosa stai facendo.
        
    - preferisci `===` per confrontare valore **e** tipo.
        

---

## **8. Sintesi finale**

- **Conversione esplicita**:
    
    - la fai tu, con funzioni tipo `Number(...)`, `String(...)`, `Boolean(...)`.
        
    - la usi quando **vuoi controllo totale** sul tipo di dato.
        
- **Coercizione implicita**:
    
    - la fa JS automaticamente, per far funzionare operatori e confronti.
        
    - avviene in casi come:
        
        - stringa + numero → tutto diventa stringa,
            
        - `==` tra due tipi diversi → prova a convertire.
            
- Più vai avanti con i progetti, più queste cose diventano **seconda natura**:
    
    - all’inizio ti esplode un po’ la testa,
        
    - poi inizi a “sentire” quando qualcosa è ancora stringa e dovrebbe essere numero.
        

Nelle prossime lezioni, gli esercizi sui tipi di dato e poi ogni singolo tipo (numeri, stringhe, booleani, oggetti, array…) comincerà ad avere **video e pratica dedicata**.