## **Lezione 1: Font Family — Le Famiglie Tipografiche e i Font Safe/Web**

### **1. Introduzione alle famiglie di font**
Quando lavoriamo sul testo in CSS dobbiamo prima capire **come sono classificati i font**.  
La tipografia web è organizzata in **famiglie**: insiemi di font con caratteristiche comuni.  
Conoscerle è essenziale, perché tutte le funzioni CSS relative al testo partono da qui.

Le cinque famiglie principali sono:

#### **1.1 Serif**
Font dotati delle piccole “graffette” o “piedini” alle estremità delle lettere.  
Richiamano la tipografia tradizionale a stampa.

- Leggibilità alta nei testi lunghi (libri, articoli).
- Aspetto elegante e classico.

Esempi comuni: *Times New Roman, Georgia*.

#### **1.2 Sans Serif**
“Senza serif”. Linee pulite, moderne, prive di decorazioni finali.

- I font più usati nel web.
- Perfetti per UI moderne e testi brevi.

Esempi: *Arial, Helvetica, Roboto*.

#### **1.3 Monospace**
Ogni carattere ha **esattamente la stessa larghezza**.  
Sono font che imitano la macchina da scrivere o il terminale.

- Ideali per mostrare codice.
- Perfetti nelle documentazioni tecniche.

Esempi: *Courier New, Consolas*.

#### **1.4 Script**
Font che imitano la **scrittura a mano** o calligrafica.

- Eleganti, decorativi.
- Da usare con moderazione: leggibilità più bassa.

Esempi: *Brush Script, Pacifico*.

#### **1.5 Display**
Categoria “artistica”. Font creativi, decorativi, spesso molto distintivi.

- Perfetti per loghi, titoli, grafiche.
- Da evitare nei testi lunghi.

Esempi: font fantasy, western, futuristici, cartoon ecc.

---

### **2. Le famiglie CSS di default**
Quando digiti `font-family` in CSS, il browser ti propone delle famiglie generiche:

- `serif`
- `sans-serif`
- `monospace`
- `cursive` (→ script)
- `fantasy` (→ display)

Queste **non sono font specifici**.  
Sono *macro-categorie*: il browser sceglie automaticamente un font reale presente nel sistema dell’utente.

Esempio:

```css
font-family: monospace;
```

Il browser potrebbe mostrare _Courier New_, _Consolas_ o un altro monospace disponibile.

---

### **3. Font “safe” — i Web Safe Font**

I **web safe font** sono font installati quasi ovunque (Windows, macOS, Linux, iOS, Android).  
Usarli significa essere quasi certi che verranno visualizzati correttamente su qualsiasi dispositivo.

Esempi di web safe font:

- **Arial**
    
- **Verdana**
    
- **Georgia**
    
- **Courier New**
    
- **Times New Roman**
    
- **Trebuchet MS**
    

Questi font sono “plugin-free”: **non devono essere scaricati**, perché già presenti sui sistemi operativi.

---

### **4. Lo _stack_ di fallback (font-family stacking)**

È fondamentale indicare **più font in fallback**, per evitare problemi di caricamento.  
Se il primo non esiste nel dispositivo dell’utente, il browser userà il successivo.

Esempio:

`font-family: "Courier New", Courier, monospace;`

Ordine logico:

1. _font specifico_ → `"Courier New"`
    
2. _font simile_ → `Courier`
    
3. _famiglia generica_ → `monospace`
    

Così l’utente _vedrà sempre il testo_, a prescindere dal font disponibile.

> L'obiettivo non è mantenere un certo stile, ma **garantire sempre la leggibilità**.  
> Se il font specifico manca, meglio un monospace generico che un testo invisibile.

---

### **5. Web Safe Font e percentuali di installazione**

Dal grafico citato nella lezione:

- Arial → presente nel ~99% dei Windows e ~98% dei Mac
    
- Courier New → presente quasi ovunque
    
- Calibri → molto diffuso su Windows, meno su Mac
    
- Altri font → spesso specifici di un sistema operativo
    

Questo ti permette di scegliere in sicurezza quali font usare per progetti semplici senza caricare webfont.

---

### **6. Perché esistono i fallback?**

Perché durante il caricamento:

- un font può non essere disponibile,
    
- un file può rompersi,
    
- la connessione può essere lenta,
    
- un utente può bloccare i font esterni.
    

In qualsiasi scenario il sito deve comunque **restare leggibile**.

Esempio pratico:  
Se devi pagare una bolletta, è molto meglio vedere il testo in _Courier_ che non vederlo affatto perché il font personalizzato non si è caricato.

---

### **7. Anticipazione: i Web Font (Google Fonts, font importati)**

Nel prossimo video vedrai:

- come importare font personalizzati,
    
- come funziona `@font-face`,
    
- come caricare font da Google Fonts,
    
- come combinarli con uno stack sicuro.
    

I web font sono font _non installati_ nei dispositivi.  
Devono essere scaricati via rete: più flessibilità estetica, meno sicurezza.

Per ora però è essenziale che tu abbia chiari:

- i tipi di font,
    
- le famiglie CSS,
    
- il concetto di _stack_,
    
- il significato dei web safe font.