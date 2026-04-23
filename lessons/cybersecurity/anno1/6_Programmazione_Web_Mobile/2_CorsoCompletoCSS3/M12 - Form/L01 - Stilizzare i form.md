## **Lezione 1: Stilizzare i form: base senza pseudoclassi**

### **1. Obiettivo della sezione**

In questa mini sezione sui form lavoriamo in due step:

1. **Stile base dei form**  
    Come rendere un form gradevole e leggibile senza preoccuparci ancora degli stati interattivi.
    
2. **Stile con le pseudoclassi** (nella prossima lezione)  
    Vedremo gli stati come `:focus`, `:valid`, `:invalid`, ecc.
    

Per ora ci concentriamo solo sull’aspetto “statico” del form, senza interazioni particolari.

---

### **2. Costruzione del form in HTML**

Partiamo da un form semplice ma pseudo-realistico, con:

- `nome`
    
- `cognome`
    
- `biografia`
    
- checkbox per “termini e condizioni”
    
- un bottone di submit con icona
    

Una struttura HTML di base potrebbe essere:

```html
<form>
  <div>
    <label for="nome">Nome</label>
    <input id="nome" type="text">
  </div>

  <div>
    <label for="cognome">Cognome</label>
    <input id="cognome" type="text">
  </div>

  <div>
    <label for="bio">Biografia</label>
    <textarea id="bio"></textarea>
  </div>

  <div class="flex-row">
    <input id="termini" type="checkbox">
    <label for="termini">Accetti i termini e condizioni</label>
  </div>

  <button type="submit">
    Invia
    <!-- qui dentro andrà l'icona SVG -->
  </button>
</form>
```

- Le **label** descrivono ogni campo.
    
- `textarea` è usata per la biografia.
    
- Una **checkbox** gestisce i termini.
    
- Il bottone è di tipo `submit`.
    

In HTML “crudo” il risultato è brutto, disallineato e senza spazio. Il resto del lavoro lo fa il CSS.

---

### **3. Stile di base del form: layout e contenitore**

Partiamo dal form come blocco principale.

#### **3.1. Impostazioni generali del form**

Obiettivo: rendere il form centrato, con un contenitore leggibile.

```css
form {
  margin-top: 5em;
  width: 50%;
  margin-inline: auto;        /* per centrarlo in orizzontale */
  display: flex;
  flex-direction: column;
  background-color: #eee;     /* da “acqua” a un grigio più amichevole */
  padding: 40px;
  border-radius: 20px;
}
```

Commenti:

- `width: 50%` → il form non occupa tutta la larghezza.
    
- `margin-inline: auto` → centra il form orizzontalmente.
    
- `display: flex` + `flex-direction: column` → tutti i campi vanno in colonna.
    
- `padding` e `border-radius` rendono il blocco più moderno e leggibile.
    

> Nota: se avessimo il `body` in `display: flex` e centrato, quel `margin-top` manuale sarebbe spesso inutile. Qui invece lo usiamo per “abbassare” un po’ il form.

---

### **4. Raggruppare label e input: i div di campo**

Per gestire meglio i singoli campi, raggruppiamo ogni coppia **label + input/textarea/select** in un `div`.  
In CSS possiamo stilare questi “blocchi” così:

```css
form div {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}
```

Effetto:

- Ogni campo (nome, cognome, bio, città, ecc.) ha:
    
    - label sopra
        
    - input/textarea sotto
        
    - un po’ di spazio sotto (`margin-bottom`)
        

Per rendere più leggibile la label:

```css
form label {
  margin-bottom: 5px;
}
```

Risultato: c’è spazio **tra label e input** e **tra un campo e l’altro**.

---

### **5. Gestire la checkbox con layout orizzontale**

La checkbox è diversa: vogliamo checkbox a sinistra e testo a destra, in **riga**.

Nell’HTML abbiamo usato:

```html
<div class="flex-row">
  <input id="termini" type="checkbox">
  <label for="termini">Accetti i termini e condizioni di utilizzo</label>
</div>
```

CSS:

```css
.flex-row {
  display: flex;
  flex-direction: row;
  align-items: center;
}
```

Ora impostiamo un po’ di spazio tra checkbox e testo.  
Qui entra il tema **selettore sbagliato vs corretto**:

- Se scriviamo:
    
    ```css
    form > input[type="checkbox"] {
      margin-right: 10px;
    }
    ```
    
    è **sbagliato**, perché il `form` non ha come **figlio diretto** un `input[type="checkbox"]`: prima c’è il `div`.
    
- Se invece usiamo un selettore discendente:
    
    ```css
    form input[type="checkbox"] {
      margin-right: 10px;
    }
    ```
    
    funziona, perché ora diciamo “qualsiasi `input` type checkbox **dentro** il form”, a qualunque livello (figlio o nipote).
    

Questo è un errore molto comune: usare `>` quando in realtà l’elemento è nidificato dentro un `div`.

---

### **6. Spaziatura e leggibilità generale**

Riassumendo, per la struttura base del form:

- `form` → contenitore principale (larghezza, padding, sfondo, bordi arrotondati).
    
- `form div` → ogni campo è un blocco con `margin-bottom`.
    
- `form label` → piccola distanza sotto la label.
    
- `.flex-row` → layout orizzontale per checkbox + testo.
    
- `input[type="checkbox"]` → margine a destra per separare la checkbox dal testo.
    

Questo definisce la **struttura**. Ora passiamo allo stile dei singoli controlli.

---

### **7. Stile di input e textarea**

Vogliamo che _tutti_ gli input e la textarea abbiano:

- padding interno,
    
- bordi arrotondati,
    
- nessun bordo “brutto” di default,
    
- per la `textarea`, niente “resize” manuale.
    

Una prima versione potrebbe essere:

```css
form input,
form textarea {
  padding: 10px;
  border-radius: 20px;
  border: none;
}
```

Per la `textarea` aggiungiamo:

```css
form textarea {
  resize: none;
}
```

Effetto:

- Scrivendo nel campo, il testo non è attaccato ai bordi (grazie al `padding`).
    
- I bordi arrotondati rendono i controlli più moderni.
    
- `border: none;` rimuove il bordo standard del browser.
    
- `resize: none;` blocca il triangolino di resize della textarea (se non lo vogliamo).
    

> Le pseudoclassi tipo `:focus` le teniamo per la prossima lezione: qui ci limitiamo al look “statico”.

---

### **8. La `select` per la città**

Aggiungiamo una `select` per scegliere la città, prima della checkbox.

#### **8.1. HTML della select**

```html
<div>
  <label for="citta">Città</label>
  <select id="citta" class="form-control">
    <option value="MI">Milano</option>
    <option value="NA">Napoli</option>
    <option value="FI">Firenze</option>
  </select>
</div>
```

#### **8.2. Stile base della select**

Senza un minimo di stile, la `select` stona rispetto agli input.  
Applichiamo uno stile coerente:

```css
form select {
  padding: 10px 20px;
  border-radius: 20px;
  border: none;
}
```

Se non ci piace la freccettina nativa:

```css
form select {
  appearance: none;
}
```

(Consapevoli che poi, per un design serio, andrebbe aggiunta un’icona custom.)

---

### **9. Evitare codice duplicato: la classe `form-control`**

Notiamo che **input**, **textarea** e **select** condividono un sacco di codice:

- `padding`
    
- `border-radius`
    
- `border: none`
    
- eventuale background, font-size, font-weight…
    

Invece di ripetere tutto, creiamo una classe riutilizzabile, ad esempio:

```css
.form-control {
  padding: 10px;
  border-radius: 20px;
  border: none;
  /* background-color, font-size, font-family, ecc… volendo */
}
```

Poi la applichiamo ai controlli HTML:

```html
<input id="nome" type="text" class="form-control">

<input id="cognome" type="text" class="form-control">

<textarea id="bio" class="form-control"></textarea>

<select id="citta" class="form-control">...</select>
```

- La `textarea` può avere in più `resize: none;` in una regola separata.
    
- La checkbox può rimanere fuori da `form-control`, perché è un tipo diverso di controllo e ha altre esigenze.
    

Questa idea di **“form control”** è standard in tanti framework CSS (Bootstrap, ecc.), per cui è utile familiarizzare con il nome.

---

### **10. Stilizzare il bottone di invio con icona**

Abbiamo un bottone tipo:

```html
<button type="submit" class="form-control">
  Invia
  <!-- icona SVG da Heroicons, ad esempio -->
  <svg>...</svg>
</button>
```

#### **10.1. Allineare testo e icona**

Problema: spesso l’icona SVG e il testo non sono allineati.  
Soluzione: trasformare il bottone in un contenitore flex.

```css
button[type="submit"] {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;         /* oppure margin-right sull’SVG */
  padding: 10px 20px;
  border-radius: 20px;
  background-color: rebeccapurple;  /* esempio */
  color: white;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  border: none;
  cursor: pointer;
}
```

Per l’icona:

```css
button[type="submit"] svg {
  height: 16px;
}
```

- `display: flex` + `align-items: center` → allinea verticalmente testo e icona.
    
- `gap` o `margin-right` sull’`svg` → separa testo e icona.
    
- `cursor: pointer` → feedback visivo corretto.
    

---

### **11. Rifiniture e prossimi step**

Fin qui abbiamo:

- Un form centrato, con contenitore leggibile.
    
- Campi ben separati (`div` per ogni campo, label con margine).
    
- Input, textarea e select con uno stile coerente tramite `.form-control`.
    
- Checkbox in layout orizzontale e spazio corretto tra checkbox e testo.
    
- Bottone di invio con icona SVG, allineato e stilizzato.
    

Mancano ancora:

- stati di **focus**,
    
- stati di **errore/validazione** (`:invalid`, `:valid`),
    
- eventuali effetti hover, disabled, ecc.
    

Questi li affronteremo nella parte successiva, dedicata alle **pseudoclassi dei form**.