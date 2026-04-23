## **Lezione 10: Overflow**

### **1. Introduzione**

In questa lezione affrontiamo uno dei concetti più importanti di tutta la CSS layout theory: **l’overflow**.  
Sapere come gestirlo è fondamentale perché ti permette di:

- controllare contenuti che superano lo spazio disponibile
    
- creare scroll verticali o orizzontali
    
- costruire caroselli e layout moderni
    
- evitare che elementi dinamici “spacchino” l’interfaccia
    
- gestire testi troppo lunghi inviati dagli utenti
    

L’overflow è letteralmente **la fuoriuscita** di un contenuto oltre i limiti del suo contenitore.

---

### **2. Cos’è l’Overflow**

Hai un `<div>` di una certa dimensione.  
Se il contenuto al suo interno è più grande del div, il contenuto **esce** dai bordi.

Esempio base:

- `<div>` 300×300
    
- dentro un `<p>` con font size 30px
    
- con `lorem` troppo lungo
    

Risultato: il testo esce e rimane visibile **fuori** dal div.  
Questa fuoriuscita è l’**overflow**.

---

### **3. Le modalità principali di overflow**

La proprietà principale è:

```css
overflow: ...;
```

I valori fondamentali sono quattro:

### **3.1 overflow: auto**

Il browser decide in automatico come gestire l’eccedenza.  
Se il contenuto supera lo spazio, compare **una scrollbar**.

È lo stesso comportamento che vedi nel `body` quando la pagina è più alta della finestra.

Esempio:

```css
overflow: auto;
```

Effetto:

- mantiene tutto dentro il div
    
- compare una scrollbar verticale o orizzontale solo se necessaria
    

---

### **3.2 overflow: hidden**

Nasconde letteralmente l’eccesso.

Esempio:

```css
overflow: hidden;
```

Risultato:

- tutto ciò che eccede viene tagliato
    
- non appare alcuna scrollbar
    
- utile per immagini o contenuti che devono rimanere “ritagliati”
    

È molto importante nei **card design**, nelle **hero images**, nei **contenitori con maschere**.

---

### **3.3 overflow: scroll**

Mostra **sempre** le scrollbar, anche quando non servono.

```css
overflow: scroll;
```

Risultato:

- scrollbar verticale e orizzontale sempre presenti
    
- sconsigliato per estetica, ma utile in casi particolari
    

---

### **3.4 overflow: visible**

È il comportamento **default**: tutto ciò che eccede esce dal contenitore.

```css
overflow: visible;
```

È utile solo in casi specifici, altrimenti rischia di rovinare l'interfaccia.

---

### **4. Overflow sugli assi: X e Y**

Puoi controllare separatamente l’asse verticale e quello orizzontale:

- **overflow-y** → verticale
    
- **overflow-x** → orizzontale
    

Esempi:

```css
overflow-y: scroll;
overflow-x: hidden;
```

Questo permette di:

- avere scroll solo verticale
    
- impedire lo scroll orizzontale (molto utile nel web moderno)
    

#### Caso pratico:

A volte `overflow-y: scroll` genera _automaticamente_ anche lo scroll orizzontale quando il contenuto eccede.  
In quel caso devi aggiungere:

```css
overflow-x: hidden;
```

per bloccare il movimento laterale.

---

### **5. Overflow e caroselli (scroll orizzontale)**

La base di molti caroselli moderni (ad esempio le stories) è:

- un contenitore con **overflow-x: scroll**
    
- più div affiancati che eccedono la larghezza visibile
    
- movimento laterale con mouse, trackpad o swipe
    

Esempio tipico:

```css
overflow-x: scroll;
white-space: nowrap;
```

È una tecnica diffusissima soprattutto dopo l’arrivo delle stories su mobile (circa 6–7 anni fa), poi portata massivamente nel web.

---

### **6. Overflow e testo**

Il testo è uno degli elementi che eccede più facilmente, perché spesso:

- l’utente inserisce contenuto dinamico
    
- non controlli la lunghezza
    
- possono comparire parole lunghissime o URL
    

Per questo esistono proprietà dedicate alle parole:

---

### **7. Overflow-Wrap**

### **7.1 overflow-wrap: break-word**

`overflow-wrap` permette di mandare a capo parole troppo lunghe **prima che escano dal contenitore**.

Esempio:

```css
overflow-wrap: break-word;
```

Effetto:

- quando una parola è troppo lunga, va a capo in modo forzato
    
- evita overflow orizzontale
    
- molto utile nei layout dinamici
    

Prima:

```
supercalifragilistichespiralidoso → esce dal contenitore
```

Dopo:

```
supercalifragilistichespira
 lidoso  → spezzato correttamente
```

---

### **8. Word-Break**

`word-break` è simile ma più aggressivo.  
È documentato come più adatto a lingue come cinese/giapponese/coreano.  
Nella pratica, per testi latini è preferibile usare **overflow-wrap**, perché è più coerente e meno invasivo.

Perciò:

- `overflow-wrap: break-word` → usalo tranquillamente
    
- `word-break` → sappi che esiste, ma non serve nella maggior parte dei casi
    

---

### **9. Word-Wrap**

`word-wrap` è la versione più vecchia di `overflow-wrap`.  
Oggi è supportato ma non serve più usarlo, visto che `overflow-wrap` è il successore moderno.

Puoi dimenticarlo, ma se lo incontri sappi che fa la stessa cosa.

---

### **10. Overflow su altri elementi**

L’overflow non riguarda solo il testo.  
Funziona su _qualsiasi_ elemento:

- immagini
    
- card
    
- sidebar
    
- navbar
    
- box decorativi
    

Esempio con un div:

```css
overflow: hidden;
```

Taglia tutto ciò che sporge fuori dal box.

---

### **11. Conclusione**

L’overflow è uno degli strumenti più potenti del CSS.  
Riassunto:

- **visible** → lascia uscire
    
- **hidden** → taglia
    
- **scroll** → mostra tutte le scrollbar
    
- **auto** → scrollbar solo se necessarie
    
- **overflow-x / overflow-y** → controllo granuale
    
- **overflow-wrap** → gestisce parole troppo lunghe
    
- fondamentale per:
    
    - layout dinamici
        
    - contenuti generati dagli utenti
        
    - caroselli orizzontali
        
    - gestione dei testi nelle sidebar
        
    - evitare “rotture” del layout
        

Ora che conosci l’overflow, puoi gestire qualunque tipo di eccedenza e preparare layout robusti anche in situazioni non previste.