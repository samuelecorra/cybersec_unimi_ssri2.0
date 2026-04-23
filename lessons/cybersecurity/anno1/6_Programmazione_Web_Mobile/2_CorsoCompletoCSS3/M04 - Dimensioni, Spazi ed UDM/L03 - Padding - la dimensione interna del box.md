## **Lezione 3: Padding - la dimensione interna del box**

Il **padding** è la seconda grande dimensione del box model, dopo il margine.  
È fondamentale per capire come “respira” un elemento e come si distribuisce lo spazio _dentro_ il box.

---

### **1. Che cos’è il padding?**

Il **padding** è lo **spazio interno** tra:

- **il contenuto**
    
- **il bordo (border)**
    

È ciò che impedisce al testo o ai contenuti di “schiacciarsi” contro il bordo.

Nel box model:

- margin → esterno
    
- border → cornice
    
- **padding → interno**
    
- content → il cuore dell’elemento
    

Quando il padding aumenta, il contenuto si sposta verso l’interno e l’elemento diventa **visivamente più grande**.

---

### **2. Il padding influenza la dimensione del box**

A differenza del margin, il padding _fa parte_ della dimensione totale del box.

Cosa vuol dire?

Dipende dal box model.

#### **Content-box (default)**

`width` e `height` indicano **solo il contenuto**, il padding è aggiunto dopo.

Esempio:

- `width: 100px`
    
- `padding-left: 20px`
    
- `padding-right: 20px`
    
- `border-left: 10px`
    
- `border-right: 10px`
    

Larghezza totale =  
`100 + 20 + 20 + 10 + 10 = 160px`

Ed è ESATTAMENTE quello che hai visto nel video.

---

#### **Border-box**

Quando fai:

```
box-sizing: border-box;
```

allora:

- la `width` include **contenuto + padding + bordi**
    
- la dimensione reale NON cresce
    

Se imposti:

```
width: 100px;
box-sizing: border-box;
padding: 20px;
```

il browser riduce automaticamente il contenuto per farci stare tutto dentro i 100px.

---

### **3. Padding e background**

Il padding è parte del box interno, quindi:

- **il background-color lo copre**
    
- può essere limitato col `background-clip`
    

Esempio:

```
background-clip: content-box;
```

mostra chiaramente dove finisce il padding e inizia il contenuto.

Appena togli `background-clip`, il background riempie tutto compreso il padding.

---

### **4. Padding e scorciatoie**

Il padding funziona esattamente come il margin:

#### **Un valore**

```
padding: 20px;
```

→ 20px ovunque

#### **Due valori**

```
padding: 20px 0;
```

→ 20px sopra/sotto  
→ 0px destra/sinistra

#### **Tre valori**

```
padding: 20px 0 50px;
```

→ top  
→ right/left  
→ bottom

#### **Quattro valori (ordine orario)**

```
padding: 20px 0 50px 2px;
```

interpreta:

1. top
    
2. right
    
3. bottom
    
4. left
    

Esattamente come per i margini.

---

### **5. Padding individuali**

Puoi usare:

```
padding-top
padding-right
padding-bottom
padding-left
```

E funzionano identici ai margini, solo che lo spazio si crea _dentro_ l’elemento.

---

### **6. Relazione padding ↔ testo/immagini**

Il padding “crea respiro” interno.  
È fondamentale soprattutto:

- nei bottoni
    
- nei div contenitore
    
- nelle card
    
- nei form
    
- nei menu
    

Perché impedisce ai contenuti di appiccicarsi ai bordi.

---

### **7. Riepilogo visivo del box model**

```
+------------------------------+
|        MARGIN (fuori)        |
|  +------------------------+  |
|  |     BORDER (cornice)   |  |
|  |  +-------------------+ |  |
|  |  |   PADDING (dentro) | |  |
|  |  |  +--------------+  | |  |
|  |  |  |  CONTENT     |  | |  |
|  |  |  +--------------+  | |  |
|  |  +-------------------+ |  |
|  +------------------------+  |
+------------------------------+
```

---

### **8. Essenza del padding**

- È **interno** → separa contenuto e bordo
    
- Allarga il box in **content-box**
    
- Non allarga il box in **border-box**
    
- Usa le stesse scorciatoie del margin
    
- Influenza sempre il layout del contenuto
    

---

### **9. Prossima lezione**

Adesso che margine e padding sono chiari, possiamo passare alle **dimensioni effettive: width e height**.