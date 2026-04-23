## **Lezione 9: I Bordi in CSS (border, direction, radius)**

### **1. Introduzione ai bordi**

I bordi in CSS sembrano semplici, ma nascondono una buona quantità di dettagli:  
possono avere uno **stile**, una **larghezza**, un **colore**, possono essere applicati **a tutti i lati** o solo a uno, e possono anche essere **arrotondati** tramite `border-radius`.

Questa lezione chiude definitivamente il cerchio e sistematizza tutto in modo chiaro, eliminando la confusione che nasce quando si incontrano border scritti in modi diversi.

---

### **2. La proprietà shorthand: border**

Quando scrivi:

```css
border: solid 5px black;
```

stai usando una **scorciatoia** che definisce in un’unica riga:

- **stile del bordo** → `solid`
    
- **spessore del bordo** → `5px`
    
- **colore del bordo** → `black`
    

Questa riga corrisponde internamente a:

```css
border-style: solid;
border-width: 5px;
border-color: black;
```

Il risultato è identico.  
La differenza?  
La shorthand è comoda e compatta, mentre le versioni separate sono più leggibili e didattiche.

---

### **3. Le tre proprietà fondamentali**

I bordi sono descritti da tre attributi di base. Capiti questi tre, hai capito il 90% dei bordi.

#### **3.1 border-style**

Indica lo stile del bordo. I più comuni:

- `solid` – linea piena
    
- `dashed` – linea tratteggiata
    
- `dotted` – punteggiata
    
- `double` – doppia linea
    
- `none` – nessun bordo
    

Applica a tutti i lati:

```css
border-style: dashed;
```

Oppure solo ad un lato:

```css
border-left-style: dashed;
```

#### **3.2 border-width**

Spessore del bordo:

```css
border-width: 15px;
```

Oppure per un lato soltanto:

```css
border-right-width: 15px;
```

#### **3.3 border-color**

Colore del bordo:

```css
border-color: red;
```

Oppure:

```css
border-top-color: blue;
```

---

### **4. Bordo per lato: top, right, bottom, left**

Ogni proprietà dei bordi può essere applicata:

- a **tutti i lati** (`border-style`)
    
- a un **lato specifico**:
    
    - `border-top-style`
        
    - `border-right-width`
        
    - `border-bottom-color`
        
    - `border-left-style`
        
    - ecc.
        

Esempio:

```css
border-left-width: 15px;
border-top-style: dashed;
border-right-color: blue;
```

Ogni lato è totalmente personalizzabile.

---

### **5. La grande scorciatoia: border**

Questa è la forma breve che unisce stile, spessore e colore:

```css
border: solid 20px red;
```

CSS interpreta dinamicamente l’ordine:  
non è fondamentale scrivere prima lo stile, poi il valore, poi il colore.  
CSS “capisce” i tre elementi per tipo.

---

### **6. Arrotondare gli angoli: border-radius**

La proprietà `border-radius` permette di arrotondare gli angoli.

#### **6.1 Arrotondamento uniforme**

```css
border-radius: 20px;
```

Tutti gli angoli vengono arrotondati allo stesso modo.

#### **6.2 Arrotondamento per angolo**

Ogni angolo ha la sua proprietà:

- `border-top-left-radius`
    
- `border-top-right-radius`
    
- `border-bottom-left-radius`
    
- `border-bottom-right-radius`
    

Esempio completo:

```css
border-top-left-radius: 20px;
border-top-right-radius: 5px;
border-bottom-left-radius: 10px;
border-bottom-right-radius: 50px;
```

Effetto:

- in alto a sinistra → grande curva
    
- in alto a destra → curva piccola
    
- in basso a sinistra → curva media
    
- in basso a destra → curva enorme
    

#### **6.3 Border-radius in percentuale**

Puoi anche usare percentuali:

```css
border-radius: 50%;
```

Risultato: un cerchio perfetto se l’elemento è quadrato.

---

### **7. Riepilogo operativo**

Per non confondersi:

#### **7.1 Ogni bordo si controlla con tre valori**

- **stile** → `border-style`
    
- **spessore** → `border-width`
    
- **colore** → `border-color`
    

#### **7.2 Puoi applicarli**

- a **tutti i bordi**, oppure
    
- a **un lato singolo**:  
    `top`, `right`, `bottom`, `left`
    

#### **7.3 Puoi usare una scorciatoia unica**

```css
border: solid 5px black;
```

#### **7.4 Puoi arrotondare**

- tutti gli angoli → `border-radius`
    
- ogni angolo → `border-*-*-radius`
    

---

### **8. Conclusione**

Abbiamo visto:

- come funzionano i bordi,
    
- come controllare stile, colore e spessore,
    
- come applicarli ai vari lati,
    
- come usare le shortcut,
    
- e come arrotondare tutti o singoli angoli.
    

Il prossimo passo è analizzare gli **outline**, simili ai bordi ma concettualmente diversi, e molto usati soprattutto in accessibilità e focus.