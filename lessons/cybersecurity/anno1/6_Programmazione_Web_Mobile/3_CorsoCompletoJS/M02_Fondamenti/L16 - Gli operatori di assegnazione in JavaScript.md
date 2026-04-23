## **Lezione 16: Gli operatori di assegnazione in JavaScript**

In questa lezione affrontiamo una categoria di operatori fondamentale:

> **gli operatori di assegnazione**, cioè quegli operatori che aggiornano il valore di una variabile usando il suo valore precedente.

Questi operatori sono onnipresenti in qualunque programma reale.

---

## **1. Cos’è l’assegnazione**

Il primo operatore di assegnazione che conosci già è il più semplice:

```js
let x = 10;
```

Qui stai **assegnando** il valore `10` alla variabile `x`.

- Se la variabile viene creata e valorizzata insieme, parliamo di **inizializzazione**.
    
- Se viene aggiornata dopo, parliamo di vera e propria **assegnazione**.
    

L’esempio metaforico è:

> “Compito di Luca = pulire casa”.  
> Stai assegnando un valore (il compito) a un contenitore (Luca).

Nella programmazione funziona nello stesso modo.

---

## **2. Il problema: aggiornare una variabile**

Supponiamo di voler aumentare `x` di uno:

```js
x++;
```

Lo conosciamo già.  
Ma se volessimo aumentarlo di **3**, di **10** o di **100**?

Non possiamo certo scrivere:

```js
x++;
x++;
x++;
...
```

Sarebbe ridicolo e impraticabile.

---

## **3. Il modo corretto: usare il valore precedente**

Supponiamo che oggi io abbia **25 anni**.

Domani compio gli anni: quale sarà la mia età?

> **La mia età di domani = la mia età di oggi + 1**

Tradotto in codice:

```js
eta = eta + 1;
```

Questo modo di pensare è fondamentale.

### **⚠️ L’obiezione del principiante**

“Ma se `eta` diventa 26, allora `eta + 1` sarebbe 27… e si somma all’infinito!?”

No.

Grazie alla **precedenza degli operatori**, prima viene valutata la parte a destra:

```js
eta + 1
```

usando **il valore originale** (25).  
Solo dopo il risultato (26) viene assegnato alla variabile.

---

## **4. Evitare la ripetizione inutile**

Scrivere:

```js
eta = eta + 3;
eta = eta + 10;
eta = eta + 100;
```

È corretto, ma ripetitivo.

Per evitare questa duplicazione è stato creato l’operatore di assegnazione più famoso:

```js
eta += 3;
```

Si legge:

> “Somma 3 a `eta`, poi riassegna il risultato”.

All’inizio può confondere, quindi aiutati con una filastrocca:

> **“Prima sommo, poi assegno.”**

Dopo 20-30 utilizzi, ti verrà naturale.

---

## **5. Gli operatori di assegnazione aritmetici**

Tutto ciò che puoi fare con i normali operatori aritmetici, lo puoi fare anche in forma compatta.

Ecco la lista completa.

### **5.1 Addizione**

```js
x += 3;
```

Equivale a:

```js
x = x + 3;
```

---

### **5.2 Sottrazione**

```js
x -= 3;
```

Equivale a:

```js
x = x - 3;
```

---

### **5.3 Moltiplicazione**

```js
x *= 3;
```

Equivale a:

```js
x = x * 3;
```

---

### **5.4 Divisione**

```js
x /= 3;
```

Equivale a:

```js
x = x / 3;
```

---

### **5.5 Modulo (il resto della divisione)**

```js
x %= 3;
```

Equivale a:

```js
x = x % 3;
```

Esempi pratici:

```js
let x = 10;
x /= 2;   // 5
x %= 3;   // 5 % 3 = 2
```

Oppure:

```js
let x = 11;
x %= 3;   // resto 2
```

---

### **5.6 Potenza**

```js
x **= 2;
```

Equivale a:

```js
x = x ** 2;
```

Esempio:

```js
let x = 5;
x **= 2;  // 25
```

---

## **6. Gli operatori di assegnazione che non vediamo ora**

Esistono anche operatori di assegnazione legati a:

- **bitwise** (`&=`, `|=`, `^=`, ecc.)
    
- **shift** (`<<=`, `>>=`, `>>>=`)
    

Questi servono per manipolare direttamente i **bit**.  
Li vedremo più avanti, nel modulo dedicato alla logica booleana e al livello basso.

In questa lezione ci concentriamo solo sugli operatori aritmetici, perché sono quelli usati nel 99% dei casi.

---

## **7. Riepilogo della lezione**

Gli operatori di assegnazione servono per:

- aggiornare una variabile usando il suo valore precedente;
    
- evitare ripetizioni inutili (`x = x + n`);
    
- rendere il codice più leggibile.
    

Tabella finale:

|Operatore|Significato|Forma estesa|
|---|---|---|
|`+=`|addizione|`x = x + y`|
|`-=`|sottrazione|`x = x - y`|
|`*=`|moltiplicazione|`x = x * y`|
|`/=`|divisione|`x = x / y`|
|`%=`|modulo|`x = x % y`|
|`**=`|potenza|`x = x ** y`|

---

## **Prossima lezione**

Passiamo ora agli **esercizi sui numeri**, per fissare in modo definitivo questa parte del corso.