## **Lezione 4: Width, Height, Min/Max e comportamento automatico**

---

### **1. Introduzione alle dimensioni in CSS**

Le **dimensioni** di un elemento HTML si controllano principalmente con:

- `width`
    
- `height`
    
- `min-width`
    
- `max-width`
    
- `min-height`
    
- `max-height`
    

e tramite il modo in cui vengono calcolate (content-box vs border-box).

Oggi vediamo nel dettaglio:

- cosa succede **di default**
    
- come funzionano `auto`
    
- come cambiano le dimensioni se l’elemento è **block**
    
- perché min/max sono fondamentali per il responsive
    

---

### **2. Il comportamento `auto` (width e height)**

Se togli `width` e `height`, oppure scrivi:

```
width: auto;
height: auto;
```

l’elemento reagisce così:

#### **Width: auto**

- Se l’elemento è **block** (es. div, section, h1), la larghezza diventa:
    
    **tutto lo spazio disponibile nella riga**,  
    **esclusi i margini**.
    

Esempio:

- Il div ha `margin: 20px`
    
- Quindi: si prende tutta la larghezza del body **meno 20px a sinistra e 20px a destra**.
    

#### **Height: auto**

L’altezza diventa:

**quanto basta per contenere il contenuto + padding + border (+ margini dei figli se influenti)**

Esempio:

- contenuto `h1`
    
- padding alto 20px
    
- padding basso 20px
    
- border sopra 10px
    
- border sotto 10px
    

Totale altezza:

```
(h1) + 20 + 20 + 10 + 10
```

Il browser misura tutto automaticamente.

---

### **3. Modificare width e height manualmente**

Puoi impostare:

```
width: 200px;
height: 200px;
```

e ottieni un quadrato perfetto.

Quando fai così, stai _forzando_ il box a non adattarsi più al contenuto: diventa fisso.

---

### **4. Le dimensioni in percentuale**

Quando scrivi:

```
width: 100%;
```

significa:

**100% dello spazio dell’elemento genitore**  
(esclusi padding e border del genitore).

#### **Height al 100% è più complessa**

Funziona solo se:

- anche il genitore ha un’altezza definita  
    (altrimenti non sa il 100% di cosa calcolare)
    

Parleremo approfonditamente di percentuali e viewport tra qualche video.

---

### **5. Min-width e Max-width**

Sono fondamentali, soprattutto per layout responsive.

#### **Max-width**

```
max-width: 200px;
```

Significa:

> Puoi allargarti quanto vuoi, **ma mai oltre 200px**.

Visivamente:

- Se la viewport è larga → il div rimane _schiacciato_ a 200px
    
- Se la viewport è stretta → allora scende sotto i 200 (a meno che ci sia una min-width)
    

#### **Min-width**

```
min-width: 100px;
```

Significa:

> Puoi restringerti, ma **mai andare sotto i 100px**.

Questo impedisce alla card (o a un bottone) di collassare troppo quando lo schermo si stringe.

---

### **6. Max-height e Min-height**

Funzionano allo stesso modo di min/max-width ma sull’asse verticale.

Utilissimi per:

- card con altezza minima garantita
    
- contenitori che contengono immagini/responsive
    
- evitare “collassi” in layout responsive
    

Esempio:

```
min-height: 200px;
max-height: 600px;
```

---

### **7. Le keyword speciali (fit-content, max-content, min-content)**

Le introduciamo ora, ma ne approfondiamo l’uso più avanti.

#### **fit-content**

```
width: fit-content;
```

Significa:

> Adattati esattamente al contenuto, senza prendere spazio extra.

È il modo corretto per dire “stringiti attorno al contenuto”, mentre `auto` tende a prendere tutto lo spazio.

#### **max-content** / **min-content**

Le vedremo più avanti perché fanno parte della logica dei layout moderni (grid e flex).  
Al momento rischierebbero di confonderti.

---

### **8. Relazione tra width/height e box model**

Ripassiamo i due scenari:

#### **Content-box (default)**

```
width = solo contenuto
padding + border si aggiungono
→ il box finale è più grande della width impostata
```

#### **Border-box**

```
width = contenuto + padding + border
→ il box finale è esattamente pari alla width impostata
```

Questo impatta moltissimo nel responsive.  
Lo useremo spesso più avanti.

---

### **9. Uso pratico: quando servono min/max**

Immagina una card responsive:

- su schermi larghi vuoi che occupi 400px
    
- su schermi stretti non vuoi che rimanga enorme
    
- ma nemmeno che collassi a 20px
    

La soluzione tipica:

```
min-width: 200px;
max-width: 400px;
width: 100%;
```

Risultato:

- su desktop → 400px
    
- su mobile → si riduce, ma mai sotto i 200px
    
- su big screen → non si espande oltre i 400px
    

Perfetto per il responsive.

---

### **10. Riepilogo essenziale**

- `width: auto` → prendi tutto lo spazio disponibile
    
- `height: auto` → prendi l’altezza necessaria al contenuto
    
- `min-width` / `max-width` → limiti orizzontali
    
- `min-height` / `max-height` → limiti verticali
    
- `fit-content` → stringiti al contenuto
    
- `border-box` → la width include padding e bordi
    
- `content-box` → padding e bordi si sommano dopo
    

---

### **11. Prossima lezione**

Quando vuoi, passiamo all’**esercizio di metà sezione**.