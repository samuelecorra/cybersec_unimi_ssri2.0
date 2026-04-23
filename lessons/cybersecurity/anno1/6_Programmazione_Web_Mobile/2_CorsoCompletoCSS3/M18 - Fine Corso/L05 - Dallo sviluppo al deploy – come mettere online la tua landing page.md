## **Lezione 5: Dallo sviluppo al deploy – come mettere online la tua landing page**

Questa lezione chiude davvero il cerchio: hai fatto l’esercizio, hai gestito il responsive, ora vediamo **cosa fare per portare la tua pagina da “progetto locale” a “sito online decente e ottimizzato”**.

---

## **1. Obiettivo della fase finale**

Hai:

- una **landing page** funzionante in locale
    
- HTML e CSS scritti “per umani”, ben formattati
    
- qualche immagine sparsa nella cartella di progetto
    

Vuoi:

- metterla **online** su un hosting (Altervista, Hostinger, ecc.)
    
- con codice **minificato** (leggero e veloce)
    
- con **Lighthouse “verde”** (100 o quasi in Performance / Accessibility / Best Practices / SEO)
    
- rispettando accessibilità e buone pratiche base.
    

---

## **2. Struttura dei file del progetto**

Immaginiamo una struttura di lavoro “pulita”:

```text
progetto-landing/
  index.html      ← file su cui lavori
  style.css       ← CSS leggibile
  assets/         ← (opzionale) cartella generica
  images/         ← immagini (JPG/PNG/SVG...)
```

Per produzione, spesso si crea una cartella separata, ad esempio:

```text
progetto-landing/
  index.html
  style.css
  images/
  dist/           ← QUI finisce il codice minificato pronto per il deploy
    index.html
    style.css
```

Idea chiave:

- **nella root** lavori in modo comodo (indentazione, commenti, ecc.)
    
- **dentro `dist/`** metti la versione **minificata** da caricare online.
    

---

## **3. Minificazione: perché e come**

### **3.1. Perché minificare?**

La minificazione serve a:

- ridurre **peso** del file (meno caratteri, niente spazi inutili)
    
- migliorare **Performance** (Lighthouse ti ringrazia)
    
- servire al browser **meno roba** da scaricare.
    

Quindi:

- `index.html` (sviluppo) → leggibile, commenti, indentazione
    
- `dist/index.html` (produzione) → tutto su una riga, senza spazi inutili
    

Stesso discorso per `style.css` → `dist/style.css`.

### **3.2. Come minificare (a mano, per ora)**

1. Copi tutto il contenuto di `index.html`.
    
2. Vai su un sito tipo “HTML minifier” (ce ne sono mille).
    
3. Incolli → generi la versione minificata.
    
4. Crei `dist/index.html` e ci incolli il codice minificato.
    
5. Fai lo stesso per `style.css` se lo tieni separato.
    

> **Importante:**  
> In produzione, su hosting, userai **solo la versione minificata**.  
> La versione “bella” resta sul tuo PC per lavorarci.

---

## **4. Attenzione ai percorsi delle immagini**

Se sposti le immagini in una cartella dedicata, ad esempio:

```text
images/avatar.png
```

allora negli `src` devi aggiornare i percorsi, ad esempio da:

```html
<img src="avatar.png" alt="Avatar">
```

a:

```html
<img src="images/avatar.png" alt="Avatar">
```

Questo va fatto **prima** di minificare, nella versione di lavoro.  
Poi minifichi l’HTML aggiornato.

---

## **5. Deploy: caricare i file su un hosting**

Il flusso è:

1. **Registri un hosting** (Altervista, Hostinger, Netsons, ecc.).
    
2. Accedi via **FTP** o pannello file del provider.
    
3. Carichi dentro la root del sito (spesso `public_html/` o simile):
    
    - `index.html` (minificato, preso da `dist/`)
        
    - `style.css` minificato (separato, se lo usi)
        
    - cartella `images/` con le immagini.
        
4. Apri il tuo dominio (o sottodominio) nel browser e controlli che tutto funzioni.
    

> Se usi `style.css` esterno, ricordati che in `dist/index.html` il link deve puntare a `style.css` (o `style.min.css` se la rinomini).

---

## **6. Sviluppo vs produzione (concetto di “deploy”)**

È importante fissare l’idea:

- **Ambiente di sviluppo** (locale):
    
    - HTML e CSS leggibili
        
    - commenti, spazi, righe vuote
        
    - file separati, magari non ancora ottimizzati
        
- **Ambiente di produzione** (server):
    
    - file **minificati**
        
    - niente commenti inutili
        
    - solo ciò che serve al browser
        

In pratica:

- Tu **programmi comodo**
    
- Il browser riceve la versione **compressa e ottimizzata**
    

In futuro lo farà in automatico una catena di build (tool JS, bundler, ecc.), ma qui ti serve capire il concetto.

---

## **7. Lighthouse: controllare qualità e SEO base**

In Chrome DevTools c’è la sezione **Lighthouse**:

1. Apri DevTools.
    
2. Vai sulla tab **Lighthouse**.
    
3. Seleziona le categorie (Performance, Accessibility, Best Practices, SEO).
    
4. Lancia l’analisi.
    

Otterrai punteggi tipo:

- **Performance**: quanto il sito è veloce e ottimizzato
    
- **Accessibility**: quanto è accessibile a screen reader e utenti con disabilità
    
- **Best Practices**: sicurezza & buone pratiche
    
- **SEO**: quanto è “presentabile” ai motori di ricerca
    

L’obiettivo dell’esempio: arrivare a **100 / 100 / 100 / 100** oppure molto vicino.

---

## **8. Errori tipici segnalati da Lighthouse e come sistemarli**

### **8.1. Link iconici senza testo (accessibilità)**

Problema:

- hai dei `<a>` che contengono solo **icone** (es. social).
    
- visivamente si capisce che è LinkedIn / Instagram, ma uno screen reader non lo può sapere.
    

Esempio problematico:

```html
<a href="https://linkedin.com">
  <svg>...</svg>
</a>
```

Soluzione: aggiungi un’**etichetta accessibile**, per esempio con `aria-label`:

```html
<a href="https://linkedin.com" aria-label="Profilo LinkedIn">
  <svg>...</svg>
</a>
```

Oppure puoi avere del testo nascosto visualmente ma presente per lo screen reader (tecnica SR-only), ma `aria-label` è il fix più rapido.

---

### **8.2. Mancanza di meta description**

Lighthouse ti segnala che manca la **meta description** per SEO.

Vai nel `<head>` e aggiungi:

```html
<meta name="description" content="Breve descrizione chiara della tua landing page, del servizio o prodotto offerto.">
```

Questa descrizione:

- aiuta i motori di ricerca
    
- può comparire tra i risultati di Google
    
- migliora il punteggio SEO.
    

---

### **8.3. Immagini senza `width` e `height`**

Spesso Lighthouse segnala che un `<img>` non ha dimensioni definite.  
Esempio:

```html
<img src="images/avatar.svg" alt="Avatar">
```

Suggerimento:

```html
<img src="images/avatar.svg" alt="Avatar" width="128" height="128">
```

Anche se poi le dimensioni verranno eventualmente **sovrascritte dal CSS**, il browser può:

- riservare subito lo spazio corretto
    
- ridurre il **layout shift** (CLS)
    
- migliorare le performance percepite.
    

---

## **9. Ogni modifica ⇒ nuova minificazione**

Workflow realistico:

1. Modifichi `index.html` (versione “umana”).
    
2. Sistemi aria-label, meta, width/height immagini, ecc.
    
3. Quando sei soddisfatto:
    
    - rimandi il codice al minifier
        
    - sovrascrivi `dist/index.html`
        
4. Carichi tramite FTP i file aggiornati sul server.
    
5. Rilanci Lighthouse sulla versione online per verificare.
    

Finché non hai strumenti automatici (bundler, task runner, framework JS), questa **pipeline manuale** ti fa capire bene la differenza sviluppo / produzione.

---

## **10. Perché tutto questo ti prepara al “next level”**

Tutta questa sbatta:

- capisci **minificazione**
    
- capisci **deploy**
    
- capisci **accessibilità e SEO minime**
    
- vedi i limiti del fare tutto a mano
    

…e quindi sei mentalmente pronto per:

- build tools,
    
- framework JavaScript,
    
- sistemi che automatizzano:
    
    - minify
        
    - bundling
        
    - ottimizzazione immagini
        
    - generazione automatica di versioni di produzione.
        

---

## **11. Cosa devi portarti via da questa lezione**

- Il **codice con cui lavori** non è necessariamente quello che va online.
    
- Prima del deploy:
    
    - sistemi accessibilità (aria-label, alt, ecc.)
        
    - aggiungi `meta description`
        
    - definisci `width`/`height` per le immagini
        
    - **minifichi** HTML e CSS
        
- Carichi su hosting solo:
    
    - `index.html` minificato
        
    - `style.css` minificato (separato, se lo usi)
        
    - cartella `images/` (e/o `assets/`)
        
- Usi **Lighthouse** per verificare che tutto sia in ordine.
    

Quando vuoi, possiamo fare un mini “checklist di deploy” in 10 punti pronta da stampare, oppure passare direttamente agli argomenti successivi (tipo ottimizzazione avanzata o il passaggio a JS/framework).