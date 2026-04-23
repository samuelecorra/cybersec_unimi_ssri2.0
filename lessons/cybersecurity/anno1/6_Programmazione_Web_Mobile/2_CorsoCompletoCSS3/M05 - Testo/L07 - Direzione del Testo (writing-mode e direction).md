## **Lezione 7: Direzione del Testo (writing-mode e direction)**

### **1. Introduzione**

Riprendiamo esattamente da dove avevamo interrotto e affrontiamo un tema fondamentale quando si lavora con lingue diverse dall’italiano o dall’inglese: **la direzione del testo**.  
In CSS possiamo controllare sia l’orientamento verticale/orizzontale sia la direzione di lettura (da sinistra verso destra o viceversa).

Vedremo due proprietà distinte:

- `writing-mode` → controlla l’orientamento del testo (verticale/orizzontale)
    
- `direction` → controlla il verso di lettura del testo (L→R o R→L)
    

Queste due proprietà servono a scopi diversi e vanno utilizzate nel modo corretto per evitare confusione.

---

### **2. Writing-Mode**

### **2.1 A cosa serve**

`writing-mode` definisce **l’orientamento del testo**, cioè se deve disporsi:

- in verticale
    
- in orizzontale
    
- in verticale “ruotato” (come nei volantini o layout particolari)
    

È molto utile per lingue come il **giapponese**, che tradizionalmente può essere scritto in verticale, ma anche per effetti tipografici particolari.

### **2.2 Esempi pratici**

Se usi:

```css
writing-mode: vertical-rl;
```

ottieni un testo verticale che scorre **dall’alto verso il basso**, con le righe che vanno da **destra verso sinistra** (classico giapponese).

Se invece usi:

```css
writing-mode: vertical-lr;
```

le righe si muovono in senso opposto: dall’alto verso il basso, ma partendo **da sinistra verso destra**.

### **2.3 Gestione dell’altezza**

Se imposti un’altezza fissa, ad esempio:

```css
height: 200px;
```

CSS “va a capo” verticalmente, cioè sposta automaticamente il testo sulla colonna successiva quando finisce lo spazio.

### **2.4 Sideways**

C’è anche il valore:

```css
writing-mode: sideways-rl;
/* o sideways-lr */
```

che produce un orientamento verticale _non naturale_, molto utilizzato in grafica pubblicitaria o layout creativi.

### **2.5 Quando NON usarlo**

`writing-mode` **non deve essere usato** per impostare lingue che scrivono in orizzontale ma da destra verso sinistra (arabo, ebraico).  
Per quelle serve `direction`.

---

### **3. Direction**

### **3.1 A cosa serve**

`direction` definisce **il verso di lettura**:

- **LTR** (Left To Right) → sinistra → destra
    
- **RTL** (Right To Left) → destra → sinistra
    

Funziona sulle lingue come l’arabo o l’ebraico, che mantengono un layout orizzontale classico ma invertito.

### **3.2 Esempi**

```css
direction: rtl;
```

Fa sì che:

- il testo parta da destra
    
- la punteggiatura si posizioni correttamente
    
- gli spazi e la direzione del flusso siano coerenti culturalmente
    

Oppure:

```css
direction: ltr;
```

per ripristinare il comportamento standard.

### **3.3 Nota importante**

Negli esempi occidentali appare “strano”, perché l’alfabeto latino NON è pensato per RTL.  
Per questo, anche nel video, l’esempio risulta poco naturale: funziona tecnicamente, ma non comunica bene l’effetto reale.

La proprietà funziona perfettamente solo con lingue realmente RTL.

---

### **4. Writing-Mode vs Direction: quando usare cosa**

|Cosa vuoi ottenere|Usa `writing-mode`|Usa `direction`|
|---|---|---|
|Testo verticale|✔️|✖️|
|Testo verticale alla giapponese|✔️|✖️|
|Testo verticale laterale (grafica)|✔️|✖️|
|Testo orizzontale normale|✖️|✔️ LTR|
|Testo orizzontale arabo/ebraico|✖️|✔️ RTL|
|Invertire solo il verso di lettura|✖️|✔️|

Usa quindi:

- **`writing-mode` → orientamento grafico**
    
- **`direction` → verso di lettura**
    

---

### **5. Conclusione**

Queste due proprietà coprono tutte le necessità di base per gestire testi scritti in lingue occidentali, orientali o semitiche.

- Se vuoi il testo **verticale**, usa `writing-mode`.
    
- Se vuoi il testo **da destra verso sinistra**, usa `direction`.
    
- Per casi più complessi (giapponese verticale con punteggiatura speciale, arabo misto a inglese nello stesso paragrafo, ecc.), esistono proprietà avanzate, ma queste basi sono più che sufficienti per ora.
    

Siamo pronti per la prossima mini-lezione: **text-shadow**, che vediamo tra pochissimi secondi.