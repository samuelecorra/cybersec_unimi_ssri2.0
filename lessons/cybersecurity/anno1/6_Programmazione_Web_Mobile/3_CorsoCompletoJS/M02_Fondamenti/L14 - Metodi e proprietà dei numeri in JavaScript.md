## **Lezione 14: Metodi e proprietà dei numeri in JavaScript**

In questa lezione entriamo finalmente nel mondo di **metodi** e **proprietà** applicati ai numeri.  
Anche se non abbiamo ancora studiato la Programmazione a Oggetti, possiamo comunque capire **subito** come funzionano, perché i concetti che ci servono qui sono molto intuitivi:

> Un **metodo** è un’azione che un valore può eseguire.  
> Una **proprietà** è un’informazione già pronta che possiamo leggere.

Quando scrivi un numero in JavaScript, questo numero viene “impacchettato” da JavaScript in un oggetto interno temporaneo, così da poter utilizzare metodi come `.toString()`, `.toFixed()`, ecc.

---

### **1. Metodi dei numeri: cosa sono e perché esistono**

Partiamo con un esempio:

```js
let x = 123;
console.log(x.toString());
```

Appena scrivi `x.` (x punto), l’editor ti mostra una lista di funzioni disponibili. Quella lista contiene i **metodi** dei numeri.

È la stessa logica che vedevamo su:

```js
console.log( ... )
```

Qui il punto serve per “entrare” nelle funzionalità disponibili su quel valore.

Ricorda questa regola generale:

> Se dopo il punto compare qualcosa con le parentesi `()`, stai chiamando un metodo.

---

### **2. Metodo `.toString()`: convertire un numero in stringa**

```js
let x = 123;
console.log(typeof x);           // "number"
console.log(typeof x.toString()); // "string"
```

`.toString()` serve quando vuoi trattare un numero come testo.

Esempi utili nella pratica:

- Quando devi **concatenarlo** con altre stringhe;
    
- Quando devi salvarlo in un file JSON;
    
- Quando devi mostrarlo formattato.
    

---

### **3. Metodi per formattare decimali: `.toFixed()` e `.toPrecision()`**

Questi due metodi servono a gestire **quanti numeri** vengono mostrati dopo la virgola.

#### **3.1 `.toFixed(n)` → “fammi vedere n decimali”**

Esempio:

```js
let x = 123.952;
console.log(x.toFixed(2));  // "123.95"
```

Il numero viene **arrotondato** e restituito come **stringa**.

#### **3.2 `.toPrecision(n)` → “fammi vedere n cifre totali”**

Esempio:

```js
let x = 123.952;
console.log(x.toPrecision(4)); // "123.9"
```

Qui non contano i decimali, ma **tutte le cifre complessive**.

---

### **4. Il metodo `.toExponential()`**

Restituisce il numero in **notazione esponenziale** (scientifica).

```js
let x = 12345;
console.log(x.toExponential(2)); // "1.23e+4"
```

È utile in ambiti scientifici o quando lavori con numeri molto grandi.

---

### **5. L’oggetto globale `Number`: proprietà e metodi statici**

Oltre ai metodi chiamabili su una variabile numerica, esiste l’oggetto globale:

```js
Number
```

Questo rappresenta **l’interfaccia dei numeri in JavaScript**.

Puoi accedere a proprietà importanti senza avere una variabile numerica:

```js
console.log(Number.MAX_VALUE);
console.log(Number.MIN_VALUE);
console.log(Number.NaN);
```

- **`Number.MAX_VALUE`** → il numero più grande rappresentabile
    
- **`Number.MIN_VALUE`** → il numero positivo più piccolo rappresentabile
    
- **`Number.NaN`** → rappresenta “Not a Number” (identico a scrivere `NaN`)
    

Queste proprietà servono **raramente**, tranne in contesti tecnici avanzati (fisica, simulazioni, calcoli ad alta precisione).

---

### **6. Conversioni utilissime: `parseInt()` e `parseFloat()`**

Questi non sono metodi dei numeri, ma **funzioni globali** che convertono stringhe in valori numerici.

#### **6.1 `parseInt()` → converte in intero**

```js
console.log(parseInt("23.75")); // 23
```

Si ferma **al primo carattere che non capisce**.

#### **6.2 `parseFloat()` → converte in decimale**

```js
console.log(parseFloat("23.75")); // 23.75
```

Perfetto quando devi estrarre valori numerici da testo:

- campi input HTML
    
- file JSON
    
- API che ritornano dati come stringhe
    

---

### **7. Attenzione: l’output colore rivela il tipo**

Nella console del browser (Chrome, Firefox, Edge), i valori vengono colorati:

- **numeri** → in blu
    
- **stringhe** → in nero/bianco (dipende dal tema)
    

Esempio:

```js
console.log(123);     // colore dei numeri
console.log("123");   // colore delle stringhe
```

È un piccolo trucco visivo per distinguere rapidamente cosa stai mandando a schermo.

---

### **8. Riepilogo della lezione**

In questa lezione hai visto che:

- Un **numero** in JavaScript ha dei **metodi** disponibili tramite `x.`
    
- I metodi più importanti sono:
    
    - `.toString()`
        
    - `.toFixed()`
        
    - `.toPrecision()`
        
    - `.toExponential()`
        
- L’oggetto globale `Number` contiene:
    
    - costanti (MAX_VALUE, MIN_VALUE, NaN)
        
    - metodi utili (vedremo più avanti quelli matematici)
        
- Due strumenti fondamentali per la conversione sono:
    
    - `parseInt()`
        
    - `parseFloat()`
        

Questi elementi saranno indispensabili quando inizierai a lavorare con:

- input utente,
    
- API,
    
- form HTML,
    
- operazioni matematiche,
    
- formattazione dei risultati.
    

Nella prossima lezione passeremo alle **stringhe**, dove scopriremo metodi ancora più ricchi e interessanti.