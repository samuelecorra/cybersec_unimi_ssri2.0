## **Lezione 4 – Palette di Colori e Schema Cromatico nel Web Design**

### **1. Introduzione alla palette**

La **palette di colori** (o _color scheme_) è l’insieme dei colori che userai **in modo coerente** su tutto il sito:

- lo sfondo
    
- gli elementi principali
    
- i pulsanti
    
- i titoli
    
- il testo
    
- gli accent color (colori che attirano l’azione)
    

È una delle decisioni più importanti del design perché determina:

- l’identità visiva del sito
    
- la coerenza grafica
    
- la percezione psicologica del brand
    
- l’accessibilità complessiva
    

---

### **2. Il concetto di “Palette Generator”**

Su Google puoi cercare _palette generator_ e ti troverai davanti decine di strumenti come:

- Coolors
    
- Adobe Color
    
- Colors.co
    
- Paletton
    
- Colormind (AI)
    

Questi tool servono a generare **4 o 5 colori principali** coordinati tra loro, solitamente rappresentati come:

- rettangoli affiancati
    
- quadrati colorati
    
- blocchi con valori HEX, RGB, HSL ecc.
    

Questa è la **base** di una palette.

Tu scegli uno o due colori, e il generatore calcola automaticamente il resto, creando un sistema armonico.

---

### **3. Esempi reali di palette: PayPal, McDonald's, ecc.**

Se pensi a PayPal, riconosci subito la sua palette:

- **blu** dominante
    
- **giallo** come accent color
    
- **bianco** e **nero** per tipi di testo
    

Al di là del design, ciò che ti resta impresso è la **coerenza visiva**, cioè l’uso sempre costante degli stessi colori nei punti chiave.

Stessa cosa per:

- McDonald’s → giallo, rosso, arancione
    
- Glovo → giallo + verde acqua
    
- Discord → viola
    
- LinkedIn → blu professionale
    
- Apple → bianco + grigi + nero
    

👉 La palette identifica il sito prima ancora che leggiamo una parola.

---

### **4. Cosa contiene veramente una palette**

Quello che vedete nei generatori (4–5 colori) è solo **la facciata**.

In realtà uno schema di colori professionale comprende:

#### **● Colori strutturali**

- background principale
    
- background secondari
    
- sezioni evidenziate
    
- card, banner, navbar…
    

#### **● Accent color**

Il colore che “comanda”:  
pulsanti, call-to-action, link, highlight.

#### **● Neutrali (testo e contorno)**

Quasi tutti i siti hanno:

- nero puro
    
- grigio scuro
    
- grigio medio
    
- grigio chiaro
    
- bianco
    

Perché i testi devono mantenere leggibilità e contrasto.

#### **● Stati interattivi**

Ogni colore chiave necessita varianti:

- hover
    
- active
    
- disabled
    
- focus
    
- outline
    
- border
    

#### **● Sfumature (shades)**

Ogni colore principale ha 8–12 varianti, ad esempio:

- 50
    
- 100
    
- 200
    
- 300
    
- 400
    
- 500 (colore principale)
    
- 600
    
- 700
    
- 800
    
- 900
    
- 950 (tema scuro)
    

Come fa TailwindCSS o Material Design.

👉 Ciò permette di avere sempre il colore giusto, perfettamente coerente.

---

### **5. Esempio pratico di generatore**

Se apri un generatore come Coolors:

1. Premi “Generate”.
    
2. Ottieni 5 colori.
    
3. Puoi bloccarne uno.
    
4. Modifichi gli altri.
    
5. Il sistema ricalcola tutto.
    

È un processo **veloce, visivo e guidato**.

Esempio:

- decidi che il tuo colore principale è un viola #7C3AED
    
- blocchi quel colore
    
- Coolors ti propone una palette coordinata
    

Puoi sempre:

- aggiungere un colore
    
- eliminarlo
    
- cambiare modalità (complementare, triadica, analogica…)
    

Alla fine il generatore produce una combinazione coerente da applicare al tuo sito.

---

### **6. La palette del sito vs. la palette del testo**

Attenzione: nel design reale le palette non sono solo “5 colori”.

I testi necessitano **colori dedicati**:

- titolo principale → nero 900
    
- sottotitolo → grigio 700
    
- testo normale → grigio 600
    
- note → grigio 500
    
- testo su pulsanti → bianco 100
    
- testo su sfondi scuri → bianco 90%
    
- testo disabilitato → grigio 400
    

E poi:

- border → grigio 300
    
- outline focus → blu 400
    
- background cards → grigio 100
    
- background secondario → grigio 50
    

Ecco perché gli schemi professionali hanno anche **15–20 valori**.

---

### **7. Accessibilità: il contrasto dei colori**

Non si può parlare di palette senza parlare di **accessibilità**.

Se apri “Lighthouse” nei DevTools (Chrome → Audits):

- trovi un test dedicato al contrasto
    
- verifica se un testo è leggibile sullo sfondo
    
- verifica se i colori rispettano gli standard WCAG
    

Molti siti “belli” hanno testi illeggibili perché il contrasto è insufficiente:

- grigio chiaro su bianco
    
- viola su blu
    
- giallo su grigio
    
- pastello su pastello
    

Gli errori di contrasto rendono il sito:

- poco leggibile
    
- poco accessibile
    
- non conforme agli standard (anche legali)
    

E gli utenti daltonici o con ipovisione  
hanno bisogno di contrasti molto più netti.

---

### **8. Riassunto chiave: cos’è una palette?**

Una **palette** è:

1. Una lista di 4–5 colori principali
    
2. Da cui derivano decine di varianti per testo, sfondi, pulsanti, bordi
    
3. Selezionati in base alla psicologia del colore
    
4. Organizzati per creare identità e coerenza
    
5. Validati tramite strumenti di accessibilità
    

La palette è il _cuore estetico_ del tuo sito.

---

### **9. Prossima lezione**

Ora che sappiamo cos’è una palette e come si usa,  
possiamo affrontare un argomento fondamentale:

👉 **come implementare light mode e dark mode in CSS**  
(usando strategia, variabili e media queries avanzate).