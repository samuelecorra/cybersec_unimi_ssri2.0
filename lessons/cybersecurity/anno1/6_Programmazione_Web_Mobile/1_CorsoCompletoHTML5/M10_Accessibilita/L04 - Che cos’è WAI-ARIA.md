## **Lezione 4: Che cos’è WAI-ARIA**

### **1. Introduzione: arriviamo al cuore dell’accessibilità moderna**

Siamo finalmente arrivati alla parte più importante di tutto il modulo: **WAI-ARIA**.  
Questa sigla la vedrai ovunque nel mondo dell’accessibilità, ed è quello che useremo per completare tutto ciò che HTML da solo non può fare.

In questo primo video capiamo:

- che cos’è
    
- perché esiste
    
- come funziona davvero
    
- perché non “fa nulla” se lo guardi da fuori
    
- come è strutturato
    

Nel prossimo video lo useremo **praticamente**, con esempi chiari e immediati.

---

## **2. Che cosa significa WAI-ARIA**

WAI-ARIA significa:

**W**eb  
**A**ccessibility  
**I**nitiative  
**A**ccessible  
**R**ich  
**I**nternet  
**A**pplications

Tradotto:

> un’iniziativa internazionale che vuole rendere più accessibili tutte le applicazioni web moderne, aggiungendo attributi che arricchiscono il nostro codice.

È un progetto del gruppo W3C, cioè la stessa organizzazione che governa lo standard HTML.

Il suo scopo è semplice:

### **→ permettere a screen reader e browser di “capire” elementi complessi**

che HTML da solo non riesce a descrivere in modo completo.

---

## **3. Nella pratica, che cosa _è_ ARIA?**

ARIA non è una libreria.  
Non è un plugin.  
Non è un framework.

ARIA è semplicemente **un insieme di attributi**.

Tutti iniziano con `aria-`.

Qualche esempio che sicuramente hai già visto ispezionando i siti:

- `aria-label`
    
- `aria-hidden`
    
- `aria-expanded`
    
- `aria-describedby`
    
- `aria-haspopup`
    
- …
    

Sono decine e decine di attributi ufficiali.

---

## **4. La cosa più importante da capire: ARIA non modifica il comportamento reale dell’elemento**

Questa è la parte più controintuitiva per chi inizia.

Se scrivi:

```html
<h1 aria-autocomplete="both">Ciao</h1>
```

visivamente **non cambia nulla**.  
L’elemento rimane un `<h1>`.  
Non diventa un input.  
Non acquisisce “autocomplete”.

Stai semplicemente aggiungendo _informazioni_.

Informazioni che:

- il browser può usare
    
- gli screen reader possono leggere
    
- gli strumenti di sviluppo possono riconoscere
    
- Visual Studio Code può validare
    

Non sono attributi “magici”.  
Sono informazioni destinate **esclusivamente alla tecnologia assistiva**.

Puoi anche scrivere:

```html
<h1 aria-mia-nonna="true">
```

e non succede niente.

Ma **se usi gli attributi ARIA corretti**, allora screen reader e browser sanno leggere quello standard… e improvvisamente l’interfaccia diventa accessibile.

---

## **5. Perché ARIA è uno standard affidabile**

ARIA funziona perché:

- è stata sviluppata in collaborazione con i browser
    
- è riconosciuta da tutti gli screen reader moderni
    
- è supportata dagli strumenti di sviluppo
    
- esiste una documentazione formale e aggiornata
    
- è diventata un linguaggio comune per descrivere l’accessibilità
    

È un po’ come dire:  
«da oggi usiamo questa lingua per parlare di accessibilità».

I browser hanno detto: ok.  
Gli screen reader hanno detto: ok.  
Gli editor hanno detto: ok.

Risultato: **se usi ARIA, tutto l’ecosistema capisce cosa stai descrivendo**.

---

## **6. Perché non sei obbligato a usarlo (ma prima o poi dovrai)**

Nessuno ti obbliga formalmente ad aggiungere ARIA al tuo codice.  
Il sito funziona lo stesso.

Però:

- il mondo del web sta andando verso l’accessibilità
    
- le normative si stanno allargando
    
- i siti moderni devono essere navigabili anche da screen reader
    
- i design moderni richiedono semantica ricca
    
- lavorare senza ARIA significa tagliarsi fuori da un pezzo enorme del mercato
    

Quindi, se vuoi fare un web **moderno**, **professionale** e **pulito**, ARIA diventerà parte del tuo lavoro.

---

## **7. Le tre categorie fondamentali di ARIA**

Gli attributi ARIA non sono tutti uguali.  
Esistono tre famiglie principali:

---

### **7.1. Ruoli (`role="..."`)**

Descrivono **che tipo di elemento è**.

Esempi (senza entrare nel dettaglio tecnico):

```html
<nav role="navigation">
```

```html
<header role="banner">
```

```html
<div role="button">
```

È come dire allo screen reader:

> “Ehi, questo elemento svolge questa funzione”.

---

### **7.2. Proprietà (attributi `aria-*`)**

Aggiungono **informazioni extra** sull’elemento.

Per esempio:

```html
<div aria-describedby="info-utente">
```

→ questo div è descritto dal testo che ha id `info-utente`.

Oppure:

```html
<button aria-label="Apri menu">
```

→ questo pulsante ha un’etichetta accessibile che gli screen reader leggeranno.

Le proprietà spiegano **relazioni, descrizioni, caratteristiche**.

---

### **7.3. Stati (sempre attributi `aria-*`)**

Dicono **com’è messo l’elemento in questo momento**.

Esempi tipici:

```html
<button aria-disabled="true">
```

→ il bottone è disabilitato (anche se non hai messo `disabled`).

```html
<button aria-expanded="false">
```

→ il menu non è aperto.

```html
<div aria-hidden="true">
```

→ questo elemento è invisibile agli screen reader.

Gli stati servono soprattutto per **componenti interattivi**, come dropdown, modali, menu, tab, slider.

---

## **8. Ricapitolando in una frase**

**WAI-ARIA è uno standard internazionale di attributi che aggiungono ruoli, proprietà e stati agli elementi HTML, per rendere comprensibile la pagina anche ai browser e agli screen reader.**

Nel prossimo video iniziamo finalmente **a usarli uno per uno**, con esempi reali e casi pratici, esattamente come sempre nel corso.