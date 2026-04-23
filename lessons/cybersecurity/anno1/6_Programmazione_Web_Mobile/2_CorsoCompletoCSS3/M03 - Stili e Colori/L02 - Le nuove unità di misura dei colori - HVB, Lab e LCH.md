## **Lezione 2 – Le nuove unità di misura dei colori: HVB, Lab e LCH**

### **1. Introduzione**

In questa lezione vediamo tre ulteriori modi di rappresentare i colori in CSS:

- **HWB** (a volte scritto HVB nell’editor del corso)
    
- **Lab**
    
- **LCH**
    

Sono sistemi più moderni rispetto a RGB / HEX / HSL, e due di questi sono **già supportati dai browser moderni**.  
Uno (HWB) arriverà _a pieno supporto_ con CSS Color Level 4.

---

### **2. HWB (Hue, Whiteness, Blackness)**

Il formato **HWB** significa:

- **H** = _Hue_ → tonalità (come in HSL: 0–360°)
    
- **W** = _Whiteness_ → quantità di bianco da miscelare
    
- **B** = _Blackness_ → quantità di nero da miscelare
    

L’idea è semplice:

- scegli un colore base (Hue)
    
- aggiungi una certa quantità di **bianco**
    
- aggiungi una certa quantità di **nero**
    

Questo permette di ottenere rapidamente:

- versioni più pastello (molto whiteness)
    
- versioni più scure o “sporche” (molta blackness)
    

#### **Supporto attuale**

> **HWB NON è ancora supportato pienamente** nei browser più diffusi.

Esempio:

```css
background-color: hwb(240 30% 20%);
```

Al momento potrebbe **non funzionare** oppure funzionare in modo incompleto — dipende dal browser.

---

### **3. Lab: Lightness + due assi di colore (a, b)**

Il formato **Lab** è più complesso da comprendere, ma estremamente potente.

Struttura:

```
lab(L a b)
```

- **L** = lightness → livello di luce (0% = nero, 100% = bianco)
    
- **a** = asse tra **verde** (−) e **rosso** (+)
    
- **b** = asse tra **blu** (−) e **giallo** (+)
    

Quindi Lab non usa saturazione e hue, ma due assi cartesiani che permettono di descrivere tutti i colori percepibili dall’occhio umano.

Esempio:

```
lab(50% 120 20)
```

Attenzione:  
**Lab NON usa le virgole** → questo è fondamentale.

Esempio corretto in CSS:

```css
background-color: lab(50% 120 20);
```

Esempio _sbagliato_:

```css
background-color: lab(50%, 120, 20); /* non funziona */
```

#### **Perché Lab è importante**

Lab appartiene ai cosiddetti **color space indipendenti dal dispositivo**.

Questo significa:

- il colore resta **coerente** tra monitor diversi
    
- resta coerente anche su **stampa**
    
- è più fedele alla percezione umana (“perceptually uniform”)
    

---

### **4. LCH: Lightness, Chroma, Hue**

LCH è una variazione di Lab, ma molto più intuitiva.

Struttura:

```
lch(L C H)
```

- **L** = lightness
    
- **C** = chroma → saturazione/percezione del “vivido”
    
- **H** = hue → tonalità (come in HSL)
    

È come dire:

- _quanta luce ha?_
    
- _quanto è vivace?_
    
- _che colore è?_
    

Esempio:

```css
background-color: lch(70% 80 210);
```

Come Lab:

- **niente virgole**
    
- color space indipendente dal device
    
- molto fedele alla percezione umana
    
- garantisce colori più coerenti e più “naturali”
    

Per molti designer e frontend moderni, **LCH è preferibile a HSL** perché:

- è più proporzionale alla percezione umana
    
- permette passaggi cromatici molto più armoniosi
    
- evita il classico problema dell’HSL “slavato” quando si aumenta la lightness
    

---

### **5. Comparazione pratica tra Lab, LCH e gli altri sistemi**

|Sistema|Facile da capire|Fedeltà percettiva|Supporto browser|Note|
|---|---|---|---|---|
|RGB|Alta|Bassa|Totale|Buono per programmatori, meno per designer|
|HSL|Alta|Media|Totale|Intuitivo: Hue, Saturation, Lightness|
|HWB|Alta|Media|Parziale|Utile per varianti pastello/scure|
|Lab|Bassa|**Molto alta**|Ottimo|Il più fedele alla percezione umana|
|LCH|Media|**Molto alta**|Ottimo|Versione più intuitiva del Lab|

---

### **6. Quando usare Lab e LCH**

React, CSS moderno, design system, dark mode avanzati:

- generazione di palette consistenti
    
- transizioni di colore morbide e senza sorprese
    
- colori coerenti su ogni monitor
    
- gradienti realistici
    
- mix di colori che non “ingrigiscono” come con HSL
    

Se oggi dovessi scegliere un unico sistema per un grosso progetto UI:

👉 **sceglierei LCH**  
(perché semplice, potente, device-independent, e ora ampiamente supportato)

---

### **7. Esempi pratici di codice**

#### **Lab**

```css
.card {
  background-color: lab(80% -5 20);
}
```

#### **LCH**

```css
.alert {
  background-color: lch(65% 80 30);
}
```

#### **HWB (solo in futuro)**

```css
.box {
  background-color: hwb(200 10% 20%);
}
```

---

### **8. Riepilogo finale**

In questa lezione abbiamo visto:

- **HWB**
    
    - Hue + whiteness + blackness
        
    - ancora non pienamente supportato
        
- **Lab**
    
    - Lightness + asse verde/rosso + asse blu/giallo
        
    - estremamente fedele e device-independent
        
    - niente virgole
        
- **LCH**
    
    - Lightness + Chroma + Hue
        
    - molto intuitivo
        
    - ormai un nuovo standard moderno
        
    - consigliato per progetti seri di UI/UX
        

Nel prossimo video passeremo alla **psicologia dei colori** e a capire _perché_ scegliere certi colori rispetto ad altri nella costruzione dell’interfaccia.