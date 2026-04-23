## **Lezione 10: Outline in CSS (outline, offset, differenze dai bordi)**

### **1. Introduzione agli outline**

L’**outline** è una linea disegnata _all’esterno del bordo_ dell’elemento. A prima vista sembra simile al border, ma in realtà ha due differenze fondamentali:

1. **L’outline non occupa spazio nel layout.**  
    L’elemento non si sposta e non cambia dimensioni quando l’outline viene aggiunto.
    
2. **L’outline è unico e non può essere applicato per lato.**  
    Non esistono `outline-left`, `outline-top`, ecc.  
    A differenza dei bordi, l’outline è sempre un blocco unico tutto attorno.
    

Queste due caratteristiche rendono l’outline perfetto per evidenziare lo stato di focus o interazione di un elemento, specialmente durante la navigazione con la tastiera.

---

### **2. Perché esiste l’outline?**

La domanda naturale è: _“Perché non usare semplicemente un border più spesso per evidenziare un elemento?”_

Ci sono due motivi pratici:

1. **Il border modifica il layout**  
    Se aumenti lo spessore del bordo, l’elemento si ingrandisce e sposta tutto ciò che lo circonda.
    
2. **L’outline non modifica nulla**  
    L’outline si disegna _sopra_ gli altri elementi, senza occupare spazio e senza spostare nulla.
    

Per testarlo, basta confrontare:

```css
border: solid 15px red;
```

→ sposta gli elementi accanto.

```css
outline: solid 30px red;
```

→ non sposta niente, “galleggia” sopra all’elemento.

Questa differenza è **il motivo principale per cui l’outline è usato per i focus**: mette in evidenza un elemento _senza mandare in confusione l’intero layout_.

---

### **3. L’outline nel focus della tastiera**

Quando premi **TAB** su un form, probabilmente hai visto comparire un rettangolo colorato attorno ai campi o ai pulsanti.

Quello è quasi sicuramente creato tramite:

```css
outline
```

Questo accade perché:

- gli utenti che non possono usare il mouse **si muovono con TAB**;
    
- gli screen reader seguono la sequenza di focus;
    
- il mouse non “dice” allo screen reader dove stai puntando;
    
- il focus è fondamentale per accessibilità e UX.
    

Ecco perché l’outline è indispensabile nel web moderno.

---

### **4. Proprietà fondamentali dell’outline**

L’outline ha una sintassi molto simile ai bordi:

#### **4.1 outline-style**

```css
outline-style: solid;
outline-style: dotted;
outline-style: double;
outline-style: groove;
```

#### **4.2 outline-width**

```css
outline-width: 20px;
```

#### **4.3 outline-color**

```css
outline-color: aqua;
```

#### **4.4 Shortcut completa**

Come per border, esiste la shorthand:

```css
outline: solid 20px aqua;
```

---

### **5. L’outline non è direzionale**

A differenza dei bordi, **non esistono**:

- `outline-left-style`
    
- `outline-bottom-color`
    
- `outline-right-width`
    

L’outline è uno spazio unico, indivisibile, che contorna l’intero elemento.

Questo è un altro motivo per cui viene usato principalmente per focus ed evidenziazione, non per creare layout complessi.

---

### **6. L’outline-offset**

Una proprietà aggiuntiva e molto utile:

#### **6.1 Che cos’è?**

`outline-offset` sposta l’outline **verso l’esterno** o **verso l’interno** rispetto al bordo.

```css
outline-offset: 10px;
```

Significa:

> “Disegna l’outline a 10px di distanza dal bordo dell’elemento.”

L’effetto è un **bordo staccato**, con una fascia bianca in mezzo.

#### **6.2 Perché può servire?**

- per creare effetti grafici più eleganti
    
- per evidenziare un focus senza coprire ombre o altri elementi
    
- per creare stacchi decorativi
    
- perché alcuni design richiedono outline non attaccati alla cornice
    

Non è una proprietà fondamentale per tutti i layout, ma quando serve… serve.

---

### **7. Differenze riassuntive tra border e outline**

#### **7.1 Border**

- occupa spazio
    
- può essere direzionale
    
- modifica layout e dimensioni
    
- è parte del box model (content → padding → border → margin)
    

#### **7.2 Outline**

- non occupa spazio
    
- non può essere direzionale
    
- non modifica il layout
    
- “galleggia” sopra l’elemento
    
- perfetto per focus e accessibilità
    

---

### **8. Conclusione**

L’outline è uno strumento semplice ma potente:  
non serve per decorare la UI di base, ma è essenziale per accessibilità, evidenziazione e interazione via tastiera.

Hai ora tutti i concetti fondamentali:

- cosa distingue border da outline
    
- sintassi dell’outline
    
- ruolo dell’outline nel focus
    
- come funziona `outline-offset`
    
- perché l’outline non influisce sul layout
    
- perché è usato in tutti i design accessibili moderni