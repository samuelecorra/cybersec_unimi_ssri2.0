## **Lezione 3: Pseudoclassi per i form**

### **1. Perché sono importanti**

Le pseudoclassi dei form servono per **stilare gli input in base al loro stato**:

- attivo / disattivato
    
- compilato / vuoto
    
- valido / non valido
    
- dentro / fuori dal range
    
- placeholder presente / non presente  
    ecc.
    

Non aggiungono logica (non “validano” davvero i dati), ma permettono di dare un **feedback visivo chiaro** all’utente: colori, bordi, bottoni che si attivano/disattivano.

Esistono molte pseudoclassi, ma alcune sono talmente rare o inutili nella pratica che **non vale la pena** usarle (es. lo stato “indeterminato” di una checkbox). Ci concentriamo sulle **poche davvero utili**.

---

### **2. `:disabled` e `:enabled`**

Un bottone, un input o qualsiasi controllo di form può essere:

- **abilitato** (default),
    
- **disabilitato**, usando l’attributo `disabled`.
    

Esempio HTML:

```html
<button id="prova" disabled>Ciao</button>
```

Il browser di default:

- mostra il bottone **più grigio**,
    
- lo rende **non cliccabile**.
    

In CSS:

```css
#prova:disabled {
  background-color: red;
}

#prova:enabled {
  background-color: green;
}
```

- `:disabled` → stile quando il controllo è disabilitato.
    
- `:enabled` → stile quando è abilitato (di solito è lo stato normale).
    

Usato spesso per:

- bottoni che si attivano solo dopo aver compilato correttamente i campi,
    
- campi non modificabili fino a una certa selezione.
    

---

### **3. `:checked` (checkbox e radio)**

`checked` è lo stato di:

- `input type="checkbox"`
    
- `input type="radio"`
    

Quando la checkbox è spuntata o il radio è selezionato:

```css
input[type="checkbox"]:checked {
  /* stile quando è spuntata */
}
```

Nel trucco della checkbox custom:

- usavamo la checkbox vera nascosta,
    
- e uno span `.checkmark` come checkbox visiva.
    

Il selettore chiave era:

```css
.hidden-checkbox:checked ~ .checkmark::before {
  /* disegna la spunta dentro .checkmark */
}
```

Quindi:

- `:checked` ci permette di **agganciarci allo stato** dell’input
    
- e propagare una modifica grafica su un altro elemento (la “finta” checkbox).
    

---

### **4. `:placeholder-shown`**

Serve a stilare un input **quando il placeholder è visibile**.

Esempio:

```html
<input type="text" placeholder="Inserisci il nome">
```

```css
input:placeholder-shown {
  background-color: black;
  color: white;
}
```

Comportamento:

- finché l’utente NON ha scritto niente → il placeholder è visibile → la pseudoclasse è attiva,
    
- appena l’utente scrive qualcosa → il placeholder sparisce → la pseudoclasse non è più attiva.
    

È utile per:

- distinguere graficamente il campo “ancora da compilare”,
    
- dare suggerimenti visivi finché il campo è vuoto.
    

---

### **5. `:required` e `:optional`**

`required` è un attributo HTML che indica che il campo **deve essere compilato**.

```html
<input type="text" required>
```

In CSS:

```css
input:required {
  background-color: gold;
}

input:optional {
  background-color: lightblue;
}
```

- `:required` → tutti i campi con l’attributo `required`.
    
- `:optional` → tutti i campi SENZA `required` (è lo stato di default).
    

Questo è comodo per:

- rendere molto chiari i campi obbligatori,
    
- oppure al contrario evidenziare quelli facoltativi.
    

> Nota: se vuoi applicare lo stile a **tutti** i controlli (input, select, textarea), dovresti includerli nel selettore (`input:required, select:required, textarea:required`).

---

### **6. `:valid` e `:invalid`**

Queste due pseudoclassi si appoggiano all’**HTML5 validation**:

- tipo di campo (`type="email"`, `type="number"`, ecc.),
    
- attributi come `required`, `min`, `max`, `pattern`, ecc.
    

Esempio con email:

```html
<input type="email" required>
```

```css
input:valid {
  border: 5px solid green;
}

input:invalid {
  border: 5px solid red;
}
```

Comportamento:

- campo vuoto ma NON `required` → di solito è considerato `valid`,
    
- se è `required` e vuoto → `invalid`,
    
- se è `type="email"` e scrivi `ciao` → `invalid`,
    
- se scrivi `nome@dominio.it` → `valid`.
    

Serve per:

- colorare il bordo di verde/rosso,
    
- dare feedback immediato sul fatto che la forma dei dati inseriti sia corretta.
    

> Importante: questa validazione è solo **visiva** lato CSS.  
> Non sostituisce la validazione logica lato JavaScript o lato server, ma rende immediata la comprensione per l’utente.

---

### **7. `:in-range` e `:out-of-range` (con `type="number"`)**

Queste pseudoclassi funzionano con input che hanno un **range numerico**:

```html
<input type="number" min="0" max="10" value="20">
```

In CSS:

```css
input:in-range {
  background-color: violet;
}

input:out-of-range {
  border: 5px solid red;
}
```

Comportamento:

- Se il valore è tra `min` e `max` → `:in-range`.
    
- Se è fuori dal range → `:out-of-range` → spesso diventa anche `:invalid`.
    

Esempio:

- `value="5"` → valido, in range.
    
- `value="20"` (con `max="10"`) → fuori range, quindi rosso.
    

È utile per:

- slider,
    
- campi quantità con limiti,
    
- qualsiasi input numerico con vincoli significativi.
    

---

### **8. Collegare le pseudoclassi all’esperienza utente**

Con tutte queste pseudoclassi puoi:

- disabilitare un bottone finché i campi non sono validi,
    
- colorare di rosso i campi errati e di verde quelli corretti,
    
- evidenziare i campi obbligatori,
    
- mostrare un aspetto diverso finché l’utente vede il placeholder.
    

In combinazione con JavaScript:

- puoi abilitare/disabilitare il bottone,
    
- cambiare emoji, icone o testi in base alla validazione,
    
- aggiornare lo stato in tempo reale.
    

L’idea generale è sempre la stessa:

> Dare all’utente **un feedback immediato e leggibile** di che cosa sta succedendo nel form, senza costringerlo ad aspettare l’invio per capire dove ha sbagliato.

Con questo abbiamo chiuso la parte di **pseudoclassi legate ai form** lato CSS.  
Nel passo successivo, queste conoscenze verranno applicate nell’**esercizio di fine sezione**.