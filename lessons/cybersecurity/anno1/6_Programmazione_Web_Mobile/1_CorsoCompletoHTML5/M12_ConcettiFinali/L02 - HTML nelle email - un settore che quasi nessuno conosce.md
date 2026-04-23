## **Lezione 2: HTML nelle email — un settore che quasi nessuno conosce, ma che usa SOLO ciò che hai appena imparato**

Arrivati alla fine del corso, prima dell’esercizio conclusivo e della preparazione al “packaging” del sito, c’è un ultimo concetto fondamentale da vedere: **l’HTML nelle email**.

Questa lezione serve a farti capire una cosa importantissima:

> **Tutto ciò che hai imparato non serve solo per creare siti web.  
> L’HTML vive anche in contesti completamente diversi, e uno dei più importanti è proprio il mondo delle email.**

---

# **1. Le email sono HTML (quasi sempre)**

Quando ricevi email come:

- newsletter Amazon
    
- conferme ordini
    
- email da Spotify, Netflix, Airbnb
    
- email promozionali
    
- email con layout grafici complessi
    

…quelle **non** sono immagini.  
Sono **HTML**.

La struttura è identica a quella di un sito:

- `<img>`
    
- `<a>`
    
- `<table>`
    
- `<td>`
    
- `<div>`
    
- `<span>`
    
- classi
    
- id
    
- attributi
    

Esattamente gli stessi mattoncini che hai imparato.

---

# **2. Che differenza c’è rispetto ad una pagina web?**

La differenza non è **cosa** puoi usare.

È **quale HTML puoi usare senza rompere tutto**.

Perché i client email:

- NON funzionano come un browser moderno
    
- NON interpretano bene il CSS avanzato
    
- NON supportano flexbox, grid, animation, variabili CSS, ecc.
    
- NON hanno uno standard unificato
    

Ogni client fa a modo suo:

- Gmail
    
- Outlook
    
- Apple Mail
    
- Yahoo
    
- Thunderbird
    
- Client mobile diversi
    
- Web-app diversi
    

Per questo le email HTML si basano ancora su:

### **✔ Table layout (tabelle)**

È l’unico metodo 100% compatibile ovunque.

### **✔ Stile inline**

Non `<style>`, ma:

```html
<td style="color:#444; padding:20px;">
```

### **✔ CSS minimo**

Molto semplice, niente effetti moderni.

### **✔ HTML essenziale**

Blocchi puliti, nessuna complessità.

---

# **3. Perché i template email “seri” usano i `<table>`?**

Perché le tabelle:

- garantiscono layout stabile su TUTTI i client email
    
- non vengono “rotte” da Gmail
    
- vengono rispettate anche su Outlook (che usa un engine di rendering simile a Microsoft Word…)
    
- assicurano che la newsletter arrivi **uguale** a tutti
    

Per un sito web useresti layout moderni.

Per una mail no: le tabelle sono ancora lo standard universale.

---

# **4. E i servizi online?**

Esistono editor drag&drop come:

- Stripo
    
- BeeFree
    
- Mailchimp template builder
    
- SendGrid email builder
    

Sono comodissimi.  
Ma se guardi il codice generato…

Trovi esattamente:

- HTML puro
    
- `<table>` ovunque
    
- `<td>` come mattoni
    
- `<img>` con attributi classici
    
- Stile inline
    
- Classi CSS di backup
    

E se apri il “codice sorgente” vedi cose come:

```html
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td style="padding:20px; background:#fafafa;">
       <img src="..." width="200" alt="Logo">
    </td>
  </tr>
</table>
```

Che tu ormai capisci perfettamente.

---

# **5. Puoi creare template email solo con HTML? Sì.

E puoi anche VENDERLI.**

Il mercato dei template email è enorme:

- promozioni
    
- newsletter
    
- notifiche di sistema
    
- email aziendali
    
- campagne marketing
    

E moltissime persone:

- non sanno HTML
    
- non hanno tempo
    
- preferiscono comprare un template già pronto
    

Per questo esistono marketplace dove vendono template email a 3–10€ l’uno.

E tu, con SOLO HTML + CSS semplice, potresti crearne quanti vuoi.

---

# **6. Perché HTML per email è un ottimo esercizio?**

Perché ti insegna:

### **✔ Pulizia del markup**

Devi scrivere HTML chiaro, semplice, lineare.

### **✔ Layout robusti**

Niente effetti strani: solo blocchi solidi e prevedibili.

### **✔ Usare le tabelle come impalcature**

Competenza che serve ANCHE per PDF, generazione automatica, documenti strutturati.

### **✔ Capire davvero come si costruisce una UI senza framework**

Se capisci le email, capisci tutto.

---

# **7. Cosa vedrai quando apri un template email?**

Esattamente ciò che hai imparato:

- `<html>`
    
- `<body>`
    
- `<table>`
    
- `<tr>`
    
- `<td>`
    
- `<img>`
    
- `<a>`
    
- classi
    
- stili inline
    

E CSS tipo:

```css
@media screen and (max-width:600px) {...}
```

(o addirittura senza media query).

---

# **8. Conclusione: HTML serve ovunque, non solo nei siti**

Questa lezione doveva lasciarti un messaggio:

> HTML è un linguaggio universale.  
> Non è solo “scrivo un sito semplice”.

È:

- siti
    
- app web
    
- template email
    
- anteprime di pagamento
    
- notifiche
    
- layout per PDF
    
- interfacce interne aziendali
    
- builder no-code
    
- modelli per marketplace
    

Con le competenze che hai già acquisito, puoi muoverti in settori che tantissimi ignorano.

---
