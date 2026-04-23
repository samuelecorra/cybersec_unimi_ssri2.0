## **Lezione 6: Stili dei Font in CSS**

### **1. Introduzione**

Abbandoniamo la parte teorica sulla psicologia dei font e passiamo finalmente a un insieme di **regole pratiche CSS** che useremo costantemente in qualunque progetto front-end.  
I prossimi video sono brevi perché trattano proprietà molto semplici, intuitive e immediate, ma **fondamentali**.  
In questa lezione iniziamo a vedere le **proprietà CSS che controllano lo stile dei font**.

---

### **2. Font Family (ripasso veloce)**

Abbiamo già visto `font-family`. Per ora nel codice è stata rimossa, ma resta una delle proprietà principali per definire l’identità tipografica del progetto.  
Oggi, però, ci concentriamo su tutte le **altre proprietà di stile** applicabili ai font.

---

### **3. Font Size**

#### **3.1 La dimensione di default**

Il browser utilizza come dimensione di default del testo **16px**.  
Lo puoi confermare dagli strumenti di sviluppo:

- un elemento `<p>` appare con **font-size: 16px**
    
- un `<h1>` di default è più grande (es. **32px**), quindi se applichi `2rem` il valore resta comunque 32px perché 1rem = 16px.
    

#### **3.2 REM e pixel**

È possibile:

- usare **pixel** per una dimensione assoluta
    
- usare **rem** per una dimensione proporzionale alla dimensione di base
    

Esempio:

- `1rem = 16px`
    
- `2rem = 32px`
    
- `3rem = 48px`
    

---

### **4. Font Style**

La proprietà `font-style` permette di definire lo stile inclinato del testo.

I valori utili sono:

- **normal** → testo standard
    
- **italic** → corsivo effettivo (se il font lo prevede)
    
- **oblique** → inclinazione forzata (meno usato)
    

Quando selezioni _Italic_, ottieni la classica forma obliqua vista già in Word o Google Docs.

---

### **5. Font Weight**

Questa è una delle proprietà più importanti del CSS tipografico.

`font-weight` definisce **lo spessore** del carattere, cioè il “grassetto”.

#### **5.1 I possibili valori**

I valori vanno da **100 a 900**:

- 100 → sottilissimo
    
- 200
    
- 300
    
- 400 → normal
    
- 500
    
- 600 → semi-bold
    
- 700 → bold
    
- 800
    
- 900 → extra bold / black
    

#### **5.2 Bold vs Bolder**

In CSS puoi trovare anche:

- **bold** (equivale a 700)
    
- **bolder** (aumenta il peso rispetto al genitore)
    

#### **5.3 Attenzione: dipende dal font**

Ogni font ha un certo numero di pesi disponibili.

Esempi pratici:

- Un font minimal potrebbe avere **solo 100 e 700** → quindi tutti i valori intermedi daranno lo stesso risultato.
    
- Font come **Roboto** (Google Fonts) hanno tantissimi pesi → ogni numero produce realmente un effetto diverso.
    

Molti font “display” (decorativi o particolari) **non hanno una versione bold**, perché sono più simili a disegni che a famiglie tipografiche complesse.

---

### **6. Text Transform**

`text-transform` modifica come vengono visualizzate le lettere:

- **capitalize** → ogni parola inizia con la maiuscola
    
- **uppercase** → tutto maiuscolo
    
- **lowercase** → tutto minuscolo
    
- **none** → nessuna trasformazione (default)
    

Questa proprietà non modifica realmente le lettere nel codice HTML, ma solo **la resa visiva**.

---

### **7. Text Decoration**

`text-decoration` permette di applicare linee grafiche al testo.

I valori principali:

- **line-through** → linea che taglia il testo (tipica delle _to-do list_ per segnare “completato”)
    
- **overline** → linea sopra al testo
    
- **underline** → linea sotto al testo (classico stile dei link)
    

Gli altri valori non ci interessano in questa fase.

**Nota importante:**  
`text-decoration` non è un bordo.  
È una linea pensata per il testo, mentre il bordo (`border`) è un elemento grafico separato.

---

### **8. Suggerimenti pratici di studio**

Prima di passare alla prossima lezione, dedica 2–3 minuti a un piccolo “quiz mentale”:

- “Come cambio il grassetto?” → `font-weight`
    
- “Come metto tutto maiuscolo?” → `text-transform: uppercase`
    
- “Come faccio il corsivo?” → `font-style: italic`
    
- “Come applico una linea sotto?” → `text-decoration: underline`
    

Queste proprietà le userai ovunque, in tutto il corso di CSS e in qualsiasi progetto front-end.

---

### **9. Anticipazione**

La prossima proprietà sarà **text-direction**, e da qui inizieremo a gestire anche aspetti più particolari del testo, inclusi gli esempi in giapponese che hai visto nel setup.