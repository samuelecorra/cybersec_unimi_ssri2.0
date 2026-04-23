## **Lezione 9: Dimensioni e margini _inline_ (inline-size, margin-inline-start, margin-inline-end)**

---

### **1. Perché esistono margin-inline e inline-size?**

Queste proprietà servono per gestire **layout che cambiano direzione di scrittura**.

La scrittura può essere:

- **orizzontale** → come italiano, inglese, spagnolo (left-to-right)
    
- **orizzontale RTL** → come arabo, ebraico (right-to-left)
    
- **verticale** → come giapponese o cinese in alcune impostazioni
    

Quando cambia la direzione del testo, cambiano anche:

- “l’inizio” e “la fine” di una riga,
    
- la posizione dei margini,
    
- ciò che significa “larghezza”.
    

Le proprietà tradizionali (`margin-left`, `margin-right`, `width`, ecc.) **non si adattano automaticamente**.  
Le proprietà _inline_ invece sì.

---

### **2. Writing-mode: la direzione del testo**

Per capire margin-inline, devi partire dal concetto di **writing-mode**:

```css
writing-mode: horizontal-tb; /* scrittura orizzontale */
writing-mode: vertical-rl;   /* scrittura verticale */
writing-mode: vertical-lr;
```

A seconda della writing-mode:

- lo _start inline_ cambia posizione,
    
- lo _end inline_ cambia posizione,
    
- inline-size cambia significato.
    

---

### **3. margin-inline-start e margin-inline-end**

#### **3.1. Significato generale**

Le proprietà inline non guardano destra/sinistra, ma guardano **l’inizio e la fine della riga**, in qualunque direzione sia quella riga.

- `margin-inline-start`: margine **all’inizio del testo**
    
- `margin-inline-end`: margine **alla fine del testo**
    

#### **3.2. Con scrittura orizzontale (europaea)**

```css
writing-mode: horizontal-tb;
margin-inline-start: 50px;
```

→ Il margine va **a sinistra**, perché in occidente la riga inizia a sinistra.

#### **3.3. Con scrittura verticale**

```css
writing-mode: vertical-rl;
margin-inline-start: 50px;
```

→ Il margine va **in alto**, perché in verticale la riga parte dall’alto.

#### **Esempio chiave del docente**

**Con margin-left → errore**

Usa sempre la sinistra, anche quando il testo diventa verticale.

**Con margin-inline-start → corretto**

Resta sempre “l’inizio della frase”, qualunque sia la direzione del testo.

---

### **4. inline-size**

#### **4.1. Cos’è**

`inline-size` è la “larghezza” **relativa alla direzione del testo**.

- In orizzontale → inline-size = _width_
    
- In verticale → inline-size = _height_
    

Esempio:

```css
inline-size: 200px;
```

- Con `writing-mode: horizontal-tb` → diventa **200px di larghezza**
    
- Con `writing-mode: vertical-rl` → diventa **200px di altezza**
    

Serve quando vuoi che la dimensione principale dell’elemento **segua la direzione del testo**, non l’asse fisso dello schermo.

#### **4.2. Perché non basta width?**

Perché succede questo:

- In orizzontale `width: 200px` è perfetto.
    
- In verticale `width: 200px` diventa un valore “laterale” e spesso **non è quello che vuoi**.
    

Inline-size invece:

- si adatta automaticamente,
    
- funziona in tutte le lingue,
    
- non richiede controlli condizionali.
    

---

### **5. Quando le userai davvero?**

→ **Quasi mai.**

Queste proprietà servono solo quando:

- un sito deve supportare lingue con scritture verticali,
    
- un layout deve essere internazionale,
    
- si lavora con componenti UI multilingua complessi,
    
- si progettano librerie o framework CSS.
    

Il 99% delle pagine web usa:

- margin-top/bottom/left/right
    
- width / height
    

E va benissimo così.

---

### **6. Riepilogo finale**

#### **6.1. Proprietà principali**

- `margin-inline-start` → margine all’inizio della riga
    
- `margin-inline-end` → margine alla fine della riga
    
- `inline-size` → dimensione lungo la direzione del testo
    

#### **6.2. Collegamento chiave**

Queste proprietà **dipendono da writing-mode**:

- In orizzontale → start = sinistra, inline-size = larghezza
    
- In verticale → start = alto, inline-size = altezza
    

#### **6.3. Perché esistono**

Per permettere layout fluidi in lingue e direzioni di scrittura diverse.