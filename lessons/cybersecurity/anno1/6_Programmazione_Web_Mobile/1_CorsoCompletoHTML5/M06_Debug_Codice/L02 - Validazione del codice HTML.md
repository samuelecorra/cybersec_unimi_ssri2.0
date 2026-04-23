## **Lezione 2: Validazione del codice HTML — come controllare se il tuo codice è corretto al 100%**

Questo è il secondo video del mini-modulo _Debug_ ed è dedicato a una cosa fondamentale:  
🔍 **verificare che il tuo HTML sia valido, corretto e sicuro.**

Quando scrivi molto codice — soprattutto da principiante — puoi voler essere sicuro al 100% che:

- non ci siano errori nascosti,
    
- non ci siano attributi obsoleti,
    
- non ci siano tag aperti o chiusi male,
    
- non ci siano problemi di accessibilità o semantica,
    
- la pagina rispetti gli standard ufficiali.
    

Per questo esiste lo strumento perfetto:  
👉 **Il Validatore ufficiale del W3C (World Wide Web Consortium)**  
il sito _ufficiale_ che definisce gli standard HTML.

---

# **1. Dove si valida HTML**

Usiamo:

> **W3C Markup Validator**

Link (non lo scrivo cliccabile per evitare preview):  
`validator.w3.org`

Appena ci accedi, hai 3 possibilità:

1. **Validate by URL**: se il file è già online.
    
2. **Validate by File Upload**: carichi il file `.html`.
    
3. **Validate by Direct Input** → **è quello che usiamo noi**.
    

---

# **2. Validazione tramite copia-incolla**

### Passo 1 — Prendi tutto l’HTML

Nel tuo editor (VSCode):

- **CTRL + A** → seleziona tutto
    
- **CTRL + C** → copia
    

### Passo 2 — Incolla nel validatore

Nel box centrale → **CTRL + V**.

### Passo 3 — Check

Clicca **Check**.

---

# **3. Come leggere il report del validatore**

Il validatore analizza l’intero DOM e ti segnala:

- ❌ errori veri e propri (tag mancanti, struttura non valida, attributi illeciti)
    
- ⚠️ warnings (cose sconsigliate, deprecate, obsolete)
    
- ℹ️ info aggiuntive
    
- suggerimenti semantici e di accessibilità
    

---

# **4. L’esempio del bordo "border"**

Nel tuo caso, il validatore ti ha segnalato:

> `border` è obsoleto nelle tabelle → usare CSS.

Infatti è vero:  
`<table border="1">` è un retaggio degli anni ’90.

Oggi si usa:

```html
<table class="mia-tabella">
```

e poi in CSS:

```css
.mia-tabella {
  border: 1px solid black;
  border-collapse: collapse;
}
```

MA:  
In HTML _del tuo corso_ ancora non sappiamo usare CSS, quindi:

- il bordino si mette in HTML solo per studiare,
    
- ma il validatore — giustamente — te lo segnala come obsoleto.
    

---

# **5. L’avviso sullo slash finale (`/>`)**

Il validatore ha segnalato anche:

> Lo slash finale sugli elementi void (`<img />`) non serve in HTML5.

HTML5 consente:

- `<img>`  
    NON serve più `<img />`.
    

Il tuo editor (Prettier) lo aggiunge automaticamente.

E infatti:

- puoi lasciarlo,
    
- puoi toglierlo,
    
- puoi configurare Prettier per NON aggiungerlo.
    

### Se deselezioni la voce relativa in Prettier:

non te lo aggiungerà più.

---

# **6. Validazione completa → 0 errori, 0 warning**

Dopo aver:

- rimosso il bordo obsoleto dal codice di test,
    
- tolto gli slash finali,
    
- lasciato una struttura HTML pulita
    

il validatore ti mostra:

> **No errors or warnings to show**

Significa:

- Il tuo HTML è perfetto ✔️
    
- È conforme agli standard ✔️
    
- Sarà correttamente interpretato da tutti i browser ✔️
    

Questo è molto più di un semplice check:  
è una garanzia di qualità.

---

# **7. Perché il validatore è utile**

### 💠 È un secondo parere affidabile

Anche se hai VSCode, estensioni, linting, Prettier…  
il validatore è lo _standard ufficiale_.

### 💠 Ti aiuta a imparare

Ogni messaggio ti insegna una piccola parte dello standard HTML.

### 💠 Evita errori che causano problemi più avanti

Specie quando inizierai CSS e JavaScript.

### 💠 È un'abitudine da professionista

Tutti i web developer seri validano i file finali.