## **Lezione 4: Stile delle Tabelle**

### **1. Introduzione**

Chiudiamo la sezione sugli stili di altri elementi HTML parlando delle **tabelle**.  
Le tabelle hanno alcune esigenze specifiche:

- gestione dei **bordi** (esterni e interni)
    
- **righe alternate** con colori diversi (zebra stripes)
    
- dimensioni (larghezza/altezza)
    
- allineamento orizzontale e verticale del contenuto
    

Vediamo tutto in modo ordinato e pratico.

---

### **2. Creazione di una tabella di base**

Partiamo da una tabella minimale, senza `thead`/`tbody`, giusto per capire la struttura:

```html
<table>
  <tr>
    <th>Nome</th>
    <th>Cognome</th>
  </tr>
  <tr>
    <td>Luca</td>
    <td>Rossi</td>
  </tr>
  <tr>
    <td>Mario</td>
    <td>Bianchi</td>
  </tr>
  <tr>
    <td>Anna</td>
    <td>Verdi</td>
  </tr>
</table>
```

- `<th>` → intestazioni di colonna (header)
    
- `<td>` → celle dei dati
    
- ogni riga è un `<tr>`
    

Su questa base andremo ad applicare lo stile.

---

### **3. Gestione dei bordi**

#### **3.1 Bordo della tabella**

Prima impostiamo un bordo attorno all’intera tabella:

```css
table {
  border: 1px solid black;
}
```

Questo crea un rettangolo attorno alla tabella, ma non i bordi delle singole celle.

#### **3.2 Bordi delle celle**

Aggiungiamo bordi anche a `th` e `td`:

```css
th,
td {
  border: 1px solid black;
}
```

A questo punto vediamo:

- un bordo esterno della tabella
    
- bordi tra una cella e l’altra
    

Ma di default c’è uno **spazio** tra i bordi interni (un doppio bordino), dovuto a una proprietà implicita: `border-spacing`.

---

### **4. border-collapse**

Per unire i bordi delle celle e il bordo della tabella in un’unica linea usiamo:

```css
table {
  border: 1px solid black;
  border-collapse: collapse;
}
```

Spiegazione:

- `border-collapse: separate` (default) → bordi separati, c’è spazio tra uno e l’altro
    
- `border-collapse: collapse` → i bordi che si “toccano” vengono fusi in uno solo
    

Risultato:

- il bordo esterno della tabella e quelli delle celle si uniscono in un reticolato pulito
    
- niente più doppio bordo o spazio bianco fra le celle
    

Se togli `border-collapse: collapse`, rimangono solo i bordi delle celle, ma non quello della tabella (nel caso in cui tu abbia tolto il `border` su `table`): con `collapse` hai un comportamento coerente e controllato.

---

### **5. Dimensioni della tabella**

#### **5.1 Larghezza al 100%**

```css
table {
  width: 100%;
}
```

La tabella occupa tutta la larghezza disponibile del contenitore (es. il `body`).

#### **5.2 Altezza delle celle di intestazione (`th`)**

```css
th {
  height: 100px;
}
```

Imposta un’altezza minima di 100px per le celle di intestazione.  
È possibile fare lo stesso con `td` se vuoi celle dati più alte.

---

### **6. Allineamento del testo**

#### **6.1 Allineamento orizzontale (`text-align`)**

```css
td {
  text-align: center;
}
```

- `left` → allineato a sinistra
    
- `center` → centrato
    
- `right` → allineato a destra
    
- `end` → “fine” in base alla direzione del testo (es. destra per LTR)
    

Per i `th`, di default il contenuto è già centrato, ma puoi personalizzarlo:

```css
th {
  text-align: end;
}
```

#### **6.2 Allineamento verticale (`vertical-align`)**

```css
td {
  vertical-align: middle;
}
```

Valori utili:

- `top` → in alto
    
- `middle` → in mezzo
    
- `bottom` → in basso
    

È molto evidente quando l’altezza della cella è più grande del contenuto.

---

### **7. Migliorare la leggibilità: colori e padding**

#### **7.1 Colore di sfondo (header)**

Coloriamo l’intestazione:

```css
th {
  background-color: #2f4f4f; /* ad es. un verde scuro/grigio */
  color: white;              /* testo chiaro per contrasto */
}
```

In questo modo:

- le intestazioni si distinguono dalle righe dati
    
- il contrasto è migliore (accessibilità)
    

#### **7.2 Padding interno celle**

Per evitare testo attaccato ai bordi, aggiungiamo padding:

```css
th,
td {
  padding: 10px;
}
```

Ora le celle “respirano” e risultano più leggibili.

---

### **8. Effetto hover sull’intera riga**

Vogliamo che passando col mouse una riga si evidenzi completamente.

```css
tr:hover {
  background-color: #f0f0f0;
}
```

Spiegazione:

- `tr:hover` seleziona l’intera riga quando il cursore ci passa sopra
    
- cambia lo sfondo di tutte le celle in quella riga
    

Se avessimo usato `td:hover`, avremmo evidenziato solo la singola cella, non tutta la riga.

---

### **9. Solo bordi inferiori (stile “minimal”)**

Invece del reticolato completo, possiamo scegliere uno stile più leggero:

```css
th,
td {
  border-bottom: 1px solid #ccc;
  padding: 10px;
}
```

- non mettiamo più un bordo completo intorno ad ogni cella
    
- solo una linea sotto → effetto tabella minimal, più moderna
    

---

### **10. Righe alternate con `nth-child`**

#### **10.1 Creare l’effetto “zebra”**

Coloriamo una riga sì e una no usando lo pseudo-selettore `:nth-child()`:

```css
tr:nth-child(even) {
  background-color: #e8f5e9; /* ad es. verdino chiaro */
}
```

- `even` → seleziona le righe con indice pari (2, 4, 6, …)
    
- `odd` → seleziona le righe dispari (1, 3, 5, …)
    

Questo è l’effetto classico “zebra stripes” che migliora molto la leggibilità.

#### **10.2 Collegamento al nome `nth-child`**

`nth-child` significa “figlio numero n-esimo”.

- `first` → 1st
    
- `second` → 2nd
    
- `third` → 3rd
    
- `fourth`, `fifth`, … → 4th, 5th, ecc.
    

Quando non sappiamo quale numero specifico, usiamo `nth` = “n-esimo”, quindi `nth-child` → _il figlio al posto n_, dove n può essere un numero specifico (3), una formula (`2n`), oppure parole chiave come `even`/`odd`.

Esempi:

```css
tr:nth-child(3) { ... }    /* solo la terza riga */
tr:nth-child(odd) { ... }  /* righe dispari */
tr:nth-child(even) { ... } /* righe pari */
```

Questo concetto lo userai anche con:

- liste (`li:nth-child(...)`)
    
- altri contenitori con elementi ripetuti
    

---

### **11. Collegamento con `thead` e `tbody`**

Se in futuro aggiungerai:

```html
<thead> ... </thead>
<tbody> ... </tbody>
```

Potrai limitare lo stile solo alle righe del corpo:

```css
tbody tr:nth-child(even) {
  background-color: #e8f5e9;
}
```

Così eviti di colorare anche la riga di intestazione.

---

### **12. Conclusione**

In questa lezione sulle tabelle hai visto come:

- creare una tabella base con `tr`, `th`, `td`
    
- gestire i bordi con `border` e `border-collapse`
    
- controllare dimensioni (larghezza/altezza)
    
- allineare il testo in orizzontale e verticale
    
- usare `padding` e colori per migliorare la leggibilità
    
- aggiungere effetto hover su tutta la riga (`tr:hover`)
    
- impostare righe alternate con `tr:nth-child(even)`
    
- capire il significato di `nth-child` e la sua logica
    

Queste tecniche sono la base per qualsiasi tabella leggibile e professionale nel web moderno, specialmente quando i dati sono molti e devono essere scansionati rapidamente dall’utente.