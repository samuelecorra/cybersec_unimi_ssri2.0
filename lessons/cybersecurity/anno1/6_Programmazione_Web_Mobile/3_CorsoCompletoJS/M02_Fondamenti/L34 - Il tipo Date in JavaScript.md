## **Lezione 34: Il tipo `Date` in JavaScript**

_(Obsidian-ready, Feynman style, completa e senza omissioni)_

---

## **1. Introduzione: il tipo `Date` non è un tipo primitivo**

In JavaScript **le date non sono un tipo di dato primitivo**.

Quando scrivi:

```js
typeof new Date()
```

il risultato è:

```
object
```

Questo significa che:

- **`Date` è una classe/oggetto speciale**
    
- si crea tramite `new`
    
- contiene **metodi** per leggere, formattare, modificare e confrontare date e orari
    

Le date in programmazione sono fondamentali:

- data di pubblicazione (YouTube, TikTok…)
    
- data di invio di un commento
    
- scadenze, fatture, prenotazioni
    
- sistemi gestionali
    
- timestamp per i log
    

Per questo impararle subito è strategico.

---

## **2. Creare una data**

### **2.1. Data corrente**

```js
const oggi = new Date();
console.log(oggi);
```

Output (esempio):

```
Tue Nov 21 2023 10:30:07 GMT+0100 (Central European Standard Time)
```

Questa è la data **completa**: giorno, mese, anno, ora, minuti, secondi, millisecondi, fuso orario.

### **2.2. Data specifica tramite parametri**

```js
const dataPersonalizzata = new Date(2020, 10, 1);
```

Attenzione:

- **I mesi partono da 0 → 0 = gennaio, 11 = dicembre**
    
- quindi `10` = novembre
    

Output:

```
Sun Nov 01 2020 00:00:00
```

Ora è mezzanotte perché non abbiamo specificato:

- ore
    
- minuti
    
- secondi
    
- millisecondi
    

---

## **3. Formattare una data**

### **3.1. `toDateString()`**

Mostra solo la **data**, senza l’ora.

```js
oggi.toDateString();
```

Esempio:

```
Sun Nov 01 2020
```

### **3.2. `toLocaleTimeString()`**

Mostra solo l’**ora**, formattata secondo il paese del dispositivo.

```js
oggi.toLocaleTimeString();
```

Output italiano:

```
10:30:07
```

Output americano:

```
10:30:07 AM
```

### **3.3. `toLocaleDateString()`**

Mostra solo la data, ma nel formato del paese:

Italia → **gg/mm/aaaa**  
USA → **mm/dd/yyyy**

### **3.4. `toLocaleString()`**

Data + ora formattate secondo locale.

---

## **4. Ottenere parti della data: i metodi `get`**

Tutti questi metodi si applicano su una data:

```js
const oggi = new Date();
```

|Metodo|Significato|
|---|---|
|`getFullYear()`|anno (es. 2024)|
|`getMonth()`|mese (0–11)|
|`getDate()`|giorno del mese (1–31)|
|`getDay()`|giorno della settimana (0 = domenica)|
|`getHours()`|ore|
|`getMinutes()`|minuti|
|`getSeconds()`|secondi|
|`getMilliseconds()`|millisecondi|

Esempio completo:

```js
console.log(
  oggi.getFullYear(),
  oggi.getMonth(),
  oggi.getDate(),
  oggi.getDay(),
  oggi.getHours(),
  oggi.getMinutes(),
  oggi.getSeconds()
);
```

---

## **5. Modificare una data: i metodi `set`**

Tutto ciò che puoi “leggere” con `get`, puoi anche “impostarlo” con `set`.

```js
oggi.setFullYear(2024);
oggi.setMonth(5);      // giugno (ricorda: parte da 0)
oggi.setDate(25);
```

Output:

```
Tue Jun 25 2024 ...
```

È la **stessa variabile**, ma modificata.

---

## **6. Operazioni sulle date**

Una data non è altro che un **numero gigante di millisecondi** dal 1° gennaio 1970.  
Questo significa che puoi sottrarre due date:

```js
const dataInizio = new Date(2023, 0, 1);   // 1 gennaio 2023
const dataFine = new Date(2023, 11, 31);  // 31 dicembre 2023

const differenza = dataFine - dataInizio;
console.log(differenza);
```

Output:

```
31536000000
```

Questi sono **millisecondi**.

Per convertirli in giorni:

```js
const msPerGiorno = 1000 * 60 * 60 * 24;
const giorni = Math.floor(differenza / msPerGiorno);
console.log(giorni);
```

Output:

```
364
```

Perfetto.

---

## **7. Riepilogo finale**

- `Date` **non è** un tipo primitivo → è un **oggetto complesso**
    
- `new Date()` → data corrente
    
- `new Date(anno, mese0-11, giorno, …)` → data personalizzata
    
- `toLocale…` → formattazioni importanti per app reali
    
- `get…` → leggere parti della data
    
- `set…` → modificarle
    
- differenza tra date = **millisecondi**, convertibili in giorni
    

---

## Se vuoi, ora passiamo agli **esercizi sulle Date**, oppure proseguiamo con i **Booleani**.