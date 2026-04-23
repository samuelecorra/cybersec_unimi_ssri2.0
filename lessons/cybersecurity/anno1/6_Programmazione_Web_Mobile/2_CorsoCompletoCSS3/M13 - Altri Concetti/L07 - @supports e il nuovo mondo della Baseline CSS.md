## **Lezione 7: @supports e il nuovo mondo della Baseline CSS**

Questa lezione introduce due concetti fondamentali che fino a un anno fa erano separati, mentre oggi lavorano assieme:

1. **La direttiva `@supports {}`**, che permette di verificare se il browser supporta una certa proprietà o valore CSS.
    
2. **Il nuovo modello globale chiamato _CSS Baseline_**, che sta rivoluzionando la compatibilità tra browser.
    

Vediamoli con chiarezza, un pezzo alla volta.

---

# **1. Cos’è `@supports`**

`@supports` permette di scrivere CSS condizionale, cioè:

> “SE il browser supporta questa proprietà → allora applica questo stile;  
> ALTRIMENTI → applica quest’altro stile.”

### **Esempio classico: Grid vs Flex**

```css
/* Fallback generico */
.container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

/* Se il browser supporta Grid */
@supports (display: grid) {
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 40px;
  }
}
```

Funziona così:

- Prima viene applicato il layout Flex.
    
- Se il browser **supporta veramente `display: grid`**, allora sovrascrive e usa il layout Grid.
    

Questo approccio era **fondamentale** negli anni in cui i browser differivano moltissimo tra loro (Chrome sì, Firefox forse, Safari no, Edge metà…).

---

# **2. Perché @supports serviva di più in passato**

Fino al 2022 circa, lo scenario era:

- Ogni browser implementava le funzioni _a caso_, in tempi diversi.
    
- Ogni sviluppatore doveva controllare **browser per browser** cosa era supportato.
    
- Il sito di riferimento era (e resta) **CanIUse**.
    

Esempio: `grid` supportata così e così…  
`flexbox` supportata con bug…  
`transform` supportato solo parzialmente…  
ecc.

Con @supports si faceva una “rete di sicurezza” per proteggersi dalle differenze.

---

# **3. Entra in scena la _CSS Baseline_** (2023–2024)

Questa è la vera rivoluzione di cui parlava il video.

### **Cosa significa “Baseline”?**

È un accordo ufficiale tra **tutti i principali browser**:

- Chrome
    
- Safari
    
- Firefox
    
- Edge
    

per rilasciare nuove funzionalità CSS **tutte insieme**, cioè in modo sincronizzato.

### **Cosa cambia per te?**

Prima:

- una proprietà usciva su Chrome → 6 mesi dopo su Firefox → 3 mesi dopo su Safari → mai su IE
    
- noi sviluppatori impazzivamo
    

Ora:

- una proprietà _entra in Baseline_
    
- significa che **da quel momento** è garantita su _tutti_ i browser moderni
    
- quindi possiamo usarla tranquillamente
    

## 🟢 In altre parole:

> **Se una feature è “Baseline 2024/2025”, tu la puoi usare senza paura.  
> Non servono fallback.  
> Non servono @supports.**

---

# **4. Baseline ≠ compatibilità vecchia**

La cosa geniale è che:

### **Internet Explorer è morto.**

### Nessuno sviluppatore serio tiene più conto dei browser del passato.

Questo permette:

- uno spazio di innovazione rapidissimo
    
- feature CSS prima impensabili
    
- meno workaround
    
- meno “CSS voodoo”
    
- codici più corti, più puliti, più moderni
    

---

# **5. Da sapere: i browser si aggiornano automaticamente**

Per anni la paura era:

> “Sì, ma gli utenti non aggiornano!”

Oggi:

- Chrome aggiorna in background
    
- Edge aggiorna in background
    
- Firefox aggiorna in background
    
- Safari aggiorna con l'OS (e su iPhone è praticamente immediato per tutti)
    

→ tutti hanno praticamente **le stesse feature**.

---

# **6. Usare Baseline nella documentazione**

Nella documentazione di MDN (Mozilla) ora vedi una sezione come:

```
🌐 Baseline: Widely supported
```

Questo significa:

> **Puoi usarlo senza preoccuparsi della compatibilità.**

Esempio reale:

- `dialog`
    
- `:has()`
    
- `@layer`
    
- `transform: translate/scale/rotate` unificati
    
- nuove unità come `lvh`, `svh`, `dvh`
    
- container queries
    
- nesting nativo di CSS
    
- ecc.
    

Tutte sono già Baseline.

---

# **7. Ma @supports serve ancora?**

Sì, ma molto meno.

Serve solo se:

- **stai usando una feature appena uscita** (es. Baseline 2025, ma non 2024)
    
- **devi supportare un browser molto vecchio specifico** (cliente aziendale con sistemi obsoleti)
    
- **vuoi un fallback elegante** per utenti antichi
    

Esempio moderno:

```css
@supports (backdrop-filter: blur(10px)) {
  .card {
    backdrop-filter: blur(10px);
  }
}

@supports not (backdrop-filter: blur(10px)) {
  .card {
    background: rgba(255,255,255,0.6);
  }
}
```

Serve per effetti “cool” non ancora universalmente presenti.

---

# **8. Schema mentale da tenere a mente**

### **Prima del 2022**

- CanIUse per ogni proprietà
    
- fallback ovunque
    
- tante differenze tra browser
    
- @supports quasi obbligatorio
    

### **Dopo il 2023 (oggi)**

- Baseline decide cosa si può usare in sicurezza
    
- se una cosa è in Baseline, la usi. Punto.
    
- @supports serve solo per feature sperimentali, non mainstream
    

---

# **9. Conclusione**

`@supports` rimane uno strumento importante perché:

- ti permette di fare fallback eleganti
    
- ti permette di testare feature nuove
    
- ti protegge in caso di browser molto obsoleti
    

Ma grazie a:

- CSS Baseline
    
- aggiornamenti automatici
    
- abbandono totale di Internet Explorer
    

…il suo uso è molto meno necessario.

Il messaggio principale:

### 👉 **Oggi puoi usare il CSS moderno senza paura.**

### 👉 **E @supports è solo un alleato extra, non un obbligo.**

