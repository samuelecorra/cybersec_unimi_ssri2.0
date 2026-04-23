## **Lezione 2: Attributi Extra — Drag & Drop**

In questo video il tuo docente ti ha mostrato per la prima volta il mondo **drag & drop**.  
Un mondo che prima o poi userai davvero (in JavaScript), ma che **ora devi solo capire concettualmente**, senza memorizzare nulla del codice.

Questa lezione serve esattamente a questo:  
**farti capire cosa sta succedendo quando trascini un elemento sulla pagina.**

---

# **1. Perché gli eventi di drag sono diversi dagli altri**

Fino a ora hai visto gli eventi più semplici:

- `onclick`
    
- `ondblclick`
    
- `onfocus`
    
- `onmouseenter`
    
- `onchange`
    
- `onkeydown`
    

Tutti perfettamente isolati: **succede una cosa → scatta un evento**.

Drag & drop invece è diverso.

### ⚡ Drag richiede una “coreografia” di eventi coordinati.

Perché quando trascini qualcosa **stai facendo più azioni contemporaneamente**:

1. inizi a trascinarlo
    
2. lo tieni spostato
    
3. entri in un’area che può accettarlo
    
4. ci passi sopra
    
5. lo rilasci
    

Quindi non basta un `ondrag`.  
Servono **più attributi** _su elementi diversi_.

---

# **2. L’idea generale del drag & drop**

Il tuo docente in pratica ha fatto questo:

### **A. Un div blu con un’immagine dentro → elemento trascinabile**

```html
<div id="blu" draggable="true">
  <img src="rana.jpg" alt="rana" />
</div>
```

L’attributo chiave è:

```
draggable="true"
```

Senza questo, HTML non permette nemmeno di iniziare il drag.

---

### **B. Un div rosso → contenitore di destinazione**

```html
<div id="rosso"></div>
```

Questo non contiene nulla, ma è un’area “di arrivo”.

---

### **C. Alcuni attributi di evento su entrambi i contenitori**

Perché?

Perché **il drag non “funziona” da solo**:  
serve dire al browser quali aree possono accettare l’oggetto trascinato.

---

# **3. I tre attributi fondamentali che devi capire**

Non serve memorizzare il codice, devi capire **la logica**.

---

## **1. `ondragstart` → l’utente comincia a trascinare**

Serve per dire:

> “Ok, ho preso questo oggetto. Tienilo a mente.”

È l’evento usato per **registrare quale elemento è stato trascinato**.

---

## **2. `ondragover` → sto passando sopra un contenitore**

Senza questo attributo **non puoi lasciare cadere nulla**.

Infatti, per default:

> gli elementi HTML NON accettano nulla che gli venga trascinato sopra.

`ondragover` serve solo a dire:

> “Quest’area è autorizzata a ricevere qualcosa.”

Non fa altro.

---

## **3. `ondrop` → rilascio finale**

Questo è quello che “completa l’azione”:

> “Hai lasciato l’oggetto proprio qui:  
> bene, ora spostalo dentro questo div.”

Questo evento contiene la logica vera del drop:  
lo spostamento _fisico_ dell’elemento trascinato.

---

# **4. Cosa stavi davvero vedendo nel video**

### **Blu (contenitore iniziale)**

- contiene l’elemento da trascinare
    
- ha `ondragstart`
    

### **L’immagine**

- ha `draggable="true"`
    
- è l’oggetto che si sposta
    

### **Rosso (contenitore target)**

- ha `ondragover` → permette di passare sopra
    
- ha `ondrop` → permette di rilasciare
    

Senza almeno questi tre eventi, il drag & drop non esiste.

---

# **5. Perché non devi imparare il codice adesso**

La parte JavaScript:

- tiene memoria dell’elemento trascinato
    
- lo sposta nel punto giusto quando avviene il drop
    
- impedisce i blocchi di default del browser
    
- aggiorna la pagina visivamente
    

Ma lo vedrai **solo** nel modulo JavaScript, quando avrai:

- DOM
    
- variabili
    
- funzioni
    
- eventi
    
- manipolazione dei nodi
    
- append e remove
    
- prevenzione del comportamento default
    

Adesso sarebbe completamente prematuro.

Lo scopo qui era solo:

## ✔ capire che drag richiede più eventi coordinati

## ✔ capire che servono eventi su più elementi diversi

## ✔ capire che HTML dà _solo_ gli hook (attributi `on...`)

## ✔ capire che la logica vera è sempre in JavaScript

E questo adesso lo sai.

---

# **6. Cosa porterai con te per quando farai JavaScript**

Quando avremo JS in mano, saprai già che:

- `dragstart` serve a memorizzare cosa stai portando
    
- `dragover` serve solo a permettere di passare sopra
    
- `drop` esegue l’azione finale
    
- servono entrambi gli elementi (origine e destinazione)
    
- serve manipolare il DOM per spostare davvero qualcosa
    

In pratica, sei già mentalmente pronto.

---

# **7. Prossima lezione**

Nel prossimo video ti parleranno degli **attributi personalizzati**, una delle cose più utili e potenti dell’intero HTML moderno, e fondamentali per il JavaScript avanzato.