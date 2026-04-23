## **Lezione 7: Contrasto tra colori e accessibilità (color contrast)**

---

### **1. Perché il contrasto è un tema “serio”**

Quando parliamo di colori in CSS, non possiamo limitarci a “cosa è bello”.  
Dobbiamo chiederci:

- questo testo si legge **sempre**?
    
- anche per chi ha problemi alla vista o percepisce male i colori?
    
- anche su schermi con luminosità bassa o troppa luce?
    

Qui entra in gioco il concetto di **accessibilità**: la capacità del nostro sito di essere fruibile anche da persone con:

- daltonismo (confusione tra certi colori)
    
- difficoltà a distinguere sfumature (nero vs grigio chiaro, ecc.)
    
- vista ridotta o affaticata
    

Ma, in realtà, il problema del contrasto lo sentono anche gli utenti “normovedenti”: se un testo è grigino chiaro su sfondo chiaro, non lo legge nessuno.  
Un sito “bello ma illeggibile” è, di fatto, un sito sbagliato.

---

### **2. Esempio classico: il “grigino carino” ma illeggibile**

Errore tipico (soprattutto in temi preconfezionati per WordPress e simili):

- sfondo chiaro
    
- testo in un grigio molto chiaro “perché è elegante”
    

Per esempio:

```css
h1 {
    color: #aaaaaa;  /* grigino pallido */
}
```

Risultato:  
magari esteticamente “minimal”, ma:

- il testo non si legge bene
    
- chi ha anche un minimo problema alla vista è praticamente escluso
    
- l’utente medio deve sforzare gli occhi
    

Quindi: non è solo una questione “per disabili”, è proprio **usabilità base**.

---

### **3. Perché il contrasto influisce anche sulla SEO**

Motori di ricerca e strumenti di analisi (tipo Lighthouse, integrato in Chrome) valutano anche l’**accessibilità**, compreso il contrasto tra:

- colore del testo (foreground)
    
- colore dello sfondo (background)
    

Se il contrasto è troppo basso:

- Lighthouse segnala errore
    
- a livello SEO, il sito può essere penalizzato (non è l’unico fattore, ma pesa)
    

Quindi un contrasto sbagliato significa:

- esperienza utente peggiore
    
- potenziale perdita di posizionamento
    
- minore conversione (ad esempio, in un e-commerce: meno click sul pulsante “Compra” perché non si nota)
    

---

### **4. Come controllare il contrasto con Lighthouse**

Passi pratici (Chrome):

1. Tasto destro sulla pagina → “Ispeziona” (oppure F12).
    
2. Apri il pannello “Lighthouse”.
    
3. Seleziona almeno la voce “Accessibility”.
    
4. Avvia l’analisi.
    

Se il rapporto di contrasto tra testo e sfondo è insufficiente, Lighthouse ti mostrerà un avviso del tipo:

> Il rapporto di contrasto tra i colori di sfondo e il primo piano non è sufficiente.

E ti indicherà quali elementi (tag, classi, ecc.) sono problematici.

Questo è un controllo che puoi (e dovresti) fare:

- ogni tanto durante lo sviluppo
    
- sempre alla fine, prima di considerare “chiusa” la parte grafica
    

---

### **5. Controllare il contrasto “al volo” con il color picker di DevTools**

Oltre a Lighthouse, puoi fare un controllo rapidissimo direttamente sul colore del testo.

Procedura:

1. Ispeziona un elemento di testo (ad esempio un `h1` o un `p`).
    
2. Nel pannello CSS, clicca sul quadretto del colore (il color picker).
    
3. Vedrai, vicino al colore, una sezione dedicata al **contrast ratio**.
    

Chrome ti mostra:

- il rapporto attuale (es. 1.29:1, 3:1, 7:1, ecc.)
    
- se quel rapporto è sufficiente o meno per gli standard di accessibilità
    

Se aumenti il contrasto (rendendo il testo più scuro o lo sfondo più chiaro), vedrai il numero salire.  
Più il rapporto è alto, più il testo è leggibile.

Esempio di ragionamento:

- 1.29:1 → praticamente illeggibile
    
- ~3:1 → minimo accettabile per testi grandi
    
- ~4.5:1 o superiore → leggibilità buona per testi normali
    

Non devi ricordarti i numeri a memoria: DevTools ti dice già se va bene o no.

---

### **6. Esempio pratico: titolo e sottotitolo**

Supponiamo di voler distinguere:

- titolo principale molto evidente
    
- sottotitolo o descrizione un po’ più “soft”, ma comunque leggibile
    

Possibile scelta:

```css
h1 {
    color: #000000;      /* nero pieno: contrasto massimo */
}

h2 {
    color: #474747;      /* grigio scuro: ancora ben leggibile */
}
```

Poi in HTML:

```html
<h1>Titolo principale della card</h1>
<h2>Breve sottotitolo o descrizione della card</h2>
<p>Testo descrittivo aggiuntivo, ecc.</p>
```

Idea:

- il titolo “spacca” (bianco su sfondo molto scuro, o nero su sfondo chiaro)
    
- il sottotitolo è più “morbido” ma ha contrasto sufficiente
    
- non esistono testi in grigio chiarissimo su fondo quasi bianco: inutili e illeggibili
    

---

### **7. Esempio reale: struttura di una card in un layout scuro**

Schema tipico (come nell’esempio del sito dell’insegnante):

- sfondo generale: molto scuro (quasi nero)
    
- testo principale (titolo articolo): bianco
    
- meta-info (data, tag): grigio chiaro
    
- descrizione breve: grigio intermedio
    
- link/CTA (“Leggi”, “Scopri di più”): colore di accento (es. viola) con buon contrasto
    

Così:

- l’occhio va prima al titolo
    
- poi alla descrizione
    
- poi ai dettagli (data, categoria)
    
- e infine al pulsante o link di azione
    

E ogni step è leggibile senza fatica.

---

### **8. Workflow sano quando progetti i colori di un sito**

Modo di lavorare intelligente:

1. Decidi la tua **palette base**:
    
    - colori di sfondo (scuro, chiaro, neutri)
        
    - colori del testo (bianco, nero, grigi)
        
    - uno o due colori di accento (per bottoni e link principali)
        
2. Prova direttamente in CSS:
    
    - imposta `background` e `color` sugli elementi chiave (body, titoli, testi, bottoni, link)
        
    - fai qualche variazione di shade fino a trovare qualcosa che ti convinca visivamente
        
3. Controlla il contrasto:
    
    - con il color picker di DevTools (rapporto di contrasto subito visibile)
        
    - con Lighthouse per una “fotografia” generale dell’accessibilità
        
4. Quando trovi una combinazione:
    
    - che ti piace
        
    - che si legge bene
        
    - che passa i controlli di accessibilità
        
    
    annota i valori precisi (HEX o HSL) e considerali la tua **palette ufficiale**.
    
5. Da quel momento:
    
    - usa sempre gli stessi colori per ruoli precisi (titoli, testi, sfondi, accenti)
        
    - evita improvvisazioni tipo “uso questo grigino random perché è carino”
        

---

### **9. Rapporto con il designer (o con te stesso se fai tutto)**

Può succedere:

- il designer propone una palette super cool, ma con poco contrasto
    
- il risultato è bello in Figma, ma pessimo nel browser e davanti a Lighthouse
    

In questo caso:

- il sito deve funzionare prima di tutto come **prodotto usabile**
    
- l’estetica va adattata ai vincoli di leggibilità e accessibilità
    

Quindi, se sei tu il dev:

- puoi (e devi) dire: “Con questi colori il contrasto non è sufficiente, servono shade diverse”.
    
- la palette va aggiustata finché:
    
    - l’interfaccia resta “bella”
        
    - ma rientra nei parametri di accessibilità
        

Se il sito è un e-commerce, un SaaS, una banca, ecc. un pulsante “Compra” o “Login” poco visibile è letteralmente un danno economico.

---

### **10. Messaggio chiave**

Quando lavori coi colori, ricordati sempre:

- non basta che un sito sia bello
    
- deve essere **leggibile** per tutti
    
- i motori di ricerca e gli strumenti automatici controllano il contrasto
    
- hai strumenti integrati (DevTools, Lighthouse) per testarlo in pochi secondi
    

Estetica senza accessibilità = UX pessima + penalizzazione potenziale.