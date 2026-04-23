## **Lezione 7: Nuove unità di misura del 2022 (lvh, svh, dvh e familiari)**

---

### **1. Perché servivano nuove unità?**

Negli ultimi anni gli smartphone hanno introdotto:

- barre di navigazione che **scompaiono e ricompaiono** mentre scrolli,
    
- safe-area variabili,
    
- notch, gesture bar, indicatori dinamici,
    
- comportamenti diversi tra Android, iOS e perfino tra versioni diverse dello stesso OS.
    

Risultato:  
**le dimensioni reali del viewport cambiano** mentre l’utente interagisce.

`vh` e `vw`, che erano perfetti nei browser desktop, su mobile non bastano più:

- a volte includono la barra,
    
- a volte no,
    
- a volte cambiano mentre scrolli,
    
- e il layout “salta”.
    

Per risolvere il problema il CSS Working Group ha introdotto nel 2022 una serie di **nuove viewport-relative units**, le più importanti delle quali sono queste tre:

- `svh / svw` → **Small Viewport**
    
- `lvh / lvw` → **Large Viewport**
    
- `dvh / dvw` → **Dynamic Viewport**
    

Queste sono quelle che il docente cita come _smallest_, _largest_ e _dynamic_ viewport.

---

### **2. Spiegazione delle tre nuove misure**

Le nuove unità sono un’evoluzione dei classici `vh` e `vw`.

#### **2.1. svh / svw — Small Viewport Height/Width**

**Smallest viewport possibile**, cioè _la dimensione minima_ che il browser può assumere.

È la situazione in cui:

- la barra del browser è **visibile**,
    
- il contenuto si riduce perché la UI occupa spazio,
    
- il layout deve adattarsi allo scenario più “compresso”.
    

Serve quando vuoi creare layout **stabili**, che non saltano quando la barra compare/scompare.

Esempio:

```css
height: 100svh;
```

→ l’altezza sarà **il minimo** possibile in ogni stato della barra del browser.

---

#### **2.2. lvh / lvw — Large Viewport Height/Width**

**Largest viewport**, ovvero _la dimensione massima_ ottenibile.

È la condizione in cui:

- la barra del browser è **completamente nascosta**,
    
- l’app occupa tutto lo spazio disponibile.
    

È utilissima per:

- header “full screen” che devono davvero occupare tutto lo schermo reale,
    
- effetti parallax che non devono ridursi perché la barra compare.
    

```css
height: 100lvh;
```

→ prende tutta l’altezza quando il browser è “pulito”.

---

#### **2.3. dvh / dvw — Dynamic Viewport Height/Width**

È il valore **dinamico**: cambia _in tempo reale_ mentre l’utente scrolla.

Se la barra scompare → `dvh` cresce  
Se la barra ricompare → `dvh` si riduce

È lo scenario reale dei telefoni moderni:

- quando scrolli verso il basso, la barra sparisce → il viewport diventa più grande
    
- quando risali verso l’alto, la UI ricompare → il viewport diventa più piccolo
    

`dvh` segue esattamente ciò che accade.

Esempio:

```css
height: 100dvh;
```

→ l’altezza si aggiorna dinamicamente mentre il browser cambia dimensione.

---

### **3. Perché servono davvero? (L’esempio dell’immagine del corso)**

Il docente ti mostra tre tipi di schermi:

1. Quello **con la barra visibile** → viewport più piccolo
    
2. Quello **senza barra** → viewport più grande
    
3. Quello **dinamico** → cambia dimensione durante lo scroll
    

Con `vh` normale non potevi distinguere queste tre situazioni.  
Il risultato era:

- layout che saltavano,
    
- elementi che si tagliavano,
    
- header full-screen che non erano mai veramente full-screen.
    

Con queste nuove unità puoi scegliere _esattamente_ il comportamento che vuoi.

---

### **4. Quando useremo queste unità?**

Non ancora adesso.

Servono soprattutto in:

- componenti mobile-first avanzati,
    
- layout dinamici,
    
- fullscreen reali,
    
- navbar sticky che devono seguire il movimento delle UI del browser,
    
- responsive design complesso.
    

Per ora ti basta:

- **sapere che esistono**,
    
- ricordare la differenza tra small / large / dynamic,
    
- sapere che risolvono i problemi dei viewport mobili moderni.
    

Quando studierai il _responsive design_ con CSS, torneranno utilissime.

---

### **5. Riepilogo rapido “a memoria”**

|Unità|Significato|Quando usarla|
|---|---|---|
|**svh / svw**|Smallest viewport|Layout stabili che non devono saltare|
|**lvh / lvw**|Largest viewport|Hero full-screen, effetti a schermo pieno|
|**dvh / dvw**|Dynamic viewport|Layout che si adattano in tempo reale allo scroll|
