## **Lezione 6: I campi numerici – `number`, `tel`, `range`**

---

### **1. Perché dedicare una mini-lezione a ciascun type?**

Nel video il docente lo dice chiaramente:  
queste lezioni sono volutamente **brevi e modulari**.

Perché?

- Se in futuro devi ripassare solo un singolo input type,  
    NON devi saltare dentro un video di 20 minuti.
    
- Ogni lezione è autonoma, facile da ritrovare e consultare.
    
- Alcuni input type appartengono allo stesso “tema”,  
    quindi vengono accorpati in mini-serie (come questa sui campi numerici).
    

L’obiettivo è **digeribilità**, non lunghezza.

Adesso entriamo nei tre type di oggi:

- `number`
    
- `tel`
    
- `range`
    

---

## **2. `type="number"` – il campo numerico vero**

```html
<input type="number" name="eta">
```

Questo input accetta **solo numeri**.

Se provi a scrivere testo:

- **non lo accetta**
    
- il browser semplicemente ignora le lettere
    

Puoi inserire il numero:

- digitando con la tastiera
    
- usando le freccette su/giù
    

Sui telefoni appare la **tastiera numerica**, molto più comoda.

### **2.1. Attributi utilissimi: `min`, `max`, `step`**

Esempio completo:

```html
<input type="number" name="quantita" min="4" max="20" step="1">
```

- **`min="4"`**  
    Non puoi scendere sotto 4 con le frecce.  
    Se inserisci a mano “2”, quando premi “Invia”, il browser blocca il form.
    
- **`max="20"`**  
    Non puoi salire sopra 20.  
    Se inserisci a mano “200”, ti viene segnalato che il valore non è valido.
    
- **`step="1"` (default)**  
    Le frecce si spostano di un’unità.  
    Puoi cambiarlo, ad esempio:
    

```html
step="0.5"
step="10"
step="0.1"
```

### **2.2. Comportamento della validazione**

- Se il valore non rispetta i limiti → il browser impedisce l’invio del form.
    
- Se rispetta `min` e `max` → tutto ok.
    

Esempio:

```html
<input type="number" min="4" max="20" required>
```

- Campo vuoto? Con `required` → errore.
    
- Campo con valore inferiore/superiore? → errore.
    
- Valore valido? → form inviabile.
    

---

## **3. `type="tel"` – il campo per numeri di telefono**

```html
<input type="tel" name="telefono">
```

A differenza di `number`, questo **non limita** ciò che puoi scrivere:

- accetta testo
    
- accetta numeri
    
- accetta simboli (`+`, `-`, `(`, `)` …)
    

Perché allora usarlo?

### **3.1. Perché `tel` è utile?**

Due motivi principali:

#### **1. Tastiera su mobile ottimizzata per telefono**

Su smartphone appare una tastiera specifica:

- numerica
    
- con simboli come `+` (prefissi internazionali)
    
- tasti grandi e comodi
    

È un enorme miglioramento di usabilità.

#### **2. Non c’è validazione automatica**

Questo è importante:

- HTML **non** controlla se è un numero di telefono valido.
    
- Se vuoi validare il formato, lo farai:
    
    - con pattern HTML5 (`pattern="..."`)
        
    - oppure con JavaScript
        
    - o lato server
        

Quindi:

> `number` = numero “matematico”  
> `tel` = numero “telefonico” (stringa specializzata)

---

## **4. `type="range"` – il selettore a scorrimento (slider)**

```html
<input type="range">
```

Appena lo scrivi, il browser trasforma il campo in uno **slider**:

- una barra orizzontale
    
- un pallino trascinabile
    
- un valore numerico interno
    

### **4.1. Attributi per controllare il range**

Esempio:

```html
<input
  type="range"
  min="1000"
  max="10000"
  step="1000"
  value="9000">
```

Significato:

- `min="1000"` → valore minimo dello slider
    
- `max="10000"` → valore massimo
    
- `step="1000"` → si muove a scatti di 1000
    
- `value="9000"` → valore iniziale quando carichi la pagina
    

Il risultato è uno slider con **10 “scalini”**:

```
1000, 2000, 3000, ..., 10000
```

### **4.2. A cosa serve un range?**

È molto utile quando vuoi far scegliere:

- un livello di volume
    
- una percentuale
    
- una fascia di valore
    
- un punteggio da 0 a 100
    
- una dimensione (es. grandezza del testo)
    
- un prezzo stimato in un configuratore
    

Visualmente dà un senso di **gradualità** immediata.

---

## **5. Riepilogo generale**

Ecco la mini-tabella mentale:

|Input type|Cosa fa|Validazione|Mobile|
|---|---|---|---|
|`text`|stringa generica|nessuna|tastiera normale|
|`number`|numeri “matematici”|controlla `min`, `max`, `step`|tastiera numerica|
|`tel`|numeri telefonici (stringa libera)|nessuna|tastiera telefonica|
|`range`|slider|controlla min/max/step|interazione touch perfetta|

E la frase chiave:

> Usa sempre il type **più specifico possibile**:  
> il browser farà metà del lavoro per te  
> e l’utente avrà un’esperienza migliore.

---

Nella prossima lezione passeremo ai tipi dedicati alle **date**:

- `date`
    
- `month`
    
- `week`
    
- `time`
    
- e anche `datetime-local`
    

…che danno accesso ai bellissimi (e potentissimi) **date picker** forniti direttamente dal browser.