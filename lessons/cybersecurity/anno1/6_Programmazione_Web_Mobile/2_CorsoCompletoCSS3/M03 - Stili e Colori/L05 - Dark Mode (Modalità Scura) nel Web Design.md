## **Lezione 5 – Dark Mode (Modalità Scura) nel Web Design**

### **1. Introduzione**

Nella sezione dedicata allo **stile e ai colori**, questa è l’ultima lezione teorica prima di passare al CSS pratico.  
Oggi analizziamo in profondità **la Dark Mode**, cioè l’interfaccia con sfondo scuro ormai diffusissima sul web.

Capirai:

- perché esiste
    
- perché è diventata uno standard
    
- quali vantaggi ha
    
- perché dovresti prevederla nei tuoi siti
    
- come i browser e i sistemi operativi interagiscono con la Dark Mode
    

---

### **2. La Dark Mode non è una moda recente**

Molti pensano che sia “la moda del momento”, ma in realtà la Dark Mode esiste **da decenni** nei software professionali.

Basta aprire:

- Visual Studio Code
    
- Photoshop
    
- Blender
    
- DaVinci Resolve
    
- IDE per programmare
    
- Software 3D, editing video, editing foto
    

Quasi tutti nascono **con tema scuro di default**.

Perché?  
Per un motivo semplicissimo: **ci lavoriamo per ore**.

Lo sfondo scuro:

- affatica meno la vista
    
- riduce l’abbagliamento
    
- rende più comodo lavorare in ambienti poco illuminati
    
- permette di concentrarsi meglio sui contenuti
    
- aumenta la permanenza dell’utente sul prodotto
    

Quando passi al tema chiaro all’improvviso, ti “acceca”.  
È un fatto biologico: il bianco pieno, soprattutto in un ambiente buio, **provoca stress visivo**.

---

### **3. Perché è utile sul Web**

Il tema scuro è diventato standard nel web perché ormai:

- un'enorme fetta di utenti lo preferisce
    
- molti sistemi operativi hanno una Dark Mode di sistema
    
- molti browser la recepiscono automaticamente
    
- è un criterio di comfort visivo
    
- migliora l’esperienza su mobile (di notte è fondamentale)
    
- apre la strada al _design minimal tech_
    
- riduce il consumo sugli schermi OLED/LED
    

In breve:  
**Dark Mode = ergonomia + estetica + durata + comfort + modernità.**

---

### **4. Dark Mode e branding**

Ogni brand può decidere come comportarsi:

- Alcuni siti sono **solo chiari** (vecchio stile o brand minimal classico).
    
- Alcuni siti sono **solo scuri** (tech, gaming, cybersecurity, design moderni).
    
- Molti siti offrono **uno switch Light/Dark**.
    
- Altri ancora seguono **le impostazioni del sistema operativo**.
    

Nel tuo caso:  
il sito di CodeGrind è totalmente dark perché:

1. il brand è scuro
    
2. è tech
    
3. è moderno
    
4. va visto spesso per molte ore
    
5. rispecchia la tua estetica
    
6. dà un’immagine più professionale e “da developer”
    

Perfettamente sensato.

---

### **5. Perché avere almeno la Dark Mode è importante**

Ormai l’esperienza web punta alla **personalizzazione** e al comfort.  
Non avere una Dark Mode oggi è considerato un limite, perché:

- tanti utenti la pretendono
    
- tanti utenti la cercano automaticamente
    
- tanti utenti abbandonano un sito se lo trovano troppo bianco
    
- sempre più siti la offrono
    
- si sta progressivamente trasformando in un "segnale di cura" verso l’utente
    

In futuro (e già adesso in parte) anche gli strumenti di audit come Lighthouse potrebbero iniziare a:

- valutare la presenza del tema scuro
    
- segnalare problemi di contrasto in Light Mode
    
- suggerire alternative più accessibili
    

---

### **6. Come funziona a livello tecnico (preview concettuale)**

Non implementiamo ancora il codice, ma è importante anticipare _come_ funziona.

Le Dark Mode possono essere:

#### **A. Fisse**

Il sito è solo scuro.  
→ Come CodeGrind.

#### **B. Con switch Light/Dark**

L’utente clicca un bottone.  
→ Molti siti moderni, esempio: Documentazione TailwindCSS.

#### **C. Automatiche basate sul sistema (CSS media query)**

Se il tuo device è in tema scuro, anche i siti lo diventano:

```css
@media (prefers-color-scheme: dark) {
    /* Regole dark mode */
}
```

Questo è lo stesso meccanismo usato da:

- Windows
    
- macOS
    
- Android
    
- iOS
    
- i browser moderni
    

Tailwind, ad esempio, quando lo apri, automaticamente adatta il suo tema alla tua modalità di sistema.  
È un comportamento **intelligente e automatico**.

---

### **7. Dark Mode, estetica e psicologia**

La Dark Mode:

- comunica tecnologia
    
- comunica modernità
    
- comunica eleganza
    
- riduce la distrazione
    
- fa risaltare gli accent color
    
- favorisce un design pulito e minimal
    

Ed è parte integrante delle scelte estetiche di tantissimi brand tech:

- Discord
    
- GitHub
    
- Vercel
    
- Twitter
    
- Tailwind
    
- Apple su molti contenuti video
    
- Netflix e tutte le piattaforme streaming
    

---

### **8. Impatto energetico**

Gli schermi OLED (e molti LED moderni):

- **non consumano energia per i pixel neri**
    
- consumano molto più per il bianco puro
    

Quindi i temi scuri:

- consumano meno
    
- scaldano meno il dispositivo
    
- durano di più su mobile
    

Questo non è banale, soprattutto su smartphone.

---

### **9. Conclusione**

La Dark Mode oggi è:

- ergonomica
    
- moderna
    
- attesa dagli utenti
    
- coerente con i brand tech
    
- vantaggiosa dal punto di vista energetico
    
- già integrata nei sistemi operativi
    

Per tutti questi motivi, quando progetti un sito **dovresti sempre prevedere almeno la possibilità di supportarla**, anche se poi decidi di non offrire lo switch e usare solo un tema scuro.

---

### **10. Prossima lezione**

Adesso iniziamo finalmente a **scrivere CSS sul serio** nella sezione:

👉 **Background e colori in CSS: `background-color`, abbreviazioni, gradienti, pattern, ecc.**