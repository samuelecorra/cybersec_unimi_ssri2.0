## **Lezione 6: Attributo `rel` nei link HTML**

In questa lezione vediamo l’attributo **`rel`** (“relationship”), che serve a specificare **che tipo di relazione** c’è tra:

- la **pagina corrente**, e
    
- la **risorsa** puntata da un link (`<a>`) o da un tag `<link>` nel `<head>`.
    

L’utente **non vede** direttamente `rel`, ma è fondamentale per:

- motori di ricerca
    
- screen reader
    
- strumenti di analisi/SEO
    
- sicurezza del browser
    

---

### **1. Dove si usa `rel`**

Puoi trovare `rel` principalmente in due posti:

1. **Nel `<head>` con `<link>`**, ad esempio:
    
    ```html
    <link rel="icon" href="/images/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="canonical" href="https://ilmiosito.it/articolo-x">
    ```
    
2. **Nei link normali con `<a>`**, ad esempio:
    
    ```html
    <a href="https://google.com" rel="external">Google</a>
    ```
    

> Non tutti i valori di `rel` hanno senso sia su `<link>` che su `<a>`.  
> Alcuni sono tipici di `<link>` (es. `icon`, `stylesheet`, `canonical`), altri tipici di `<a>` (`nofollow`, `noopener`, `help`, `tag`, ecc.).

In questa lezione ci concentriamo soprattutto sui **link `<a>`**, perché sono quelli che userai ovunque nei tuoi siti.

---

### **2. Idea di base: descrivere la “natura” del link**

Quando aggiungi `rel` a un `<a>`, stai dicendo:

> “Il documento a cui punta questo link è **di questo tipo** rispetto alla pagina attuale.”

Esempi intuitivi:

- `rel="author"` → la pagina linkata è la **pagina dell’autore** dell’articolo.
    
- `rel="help"` → la pagina linkata è una **pagina di aiuto / FAQ / supporto**.
    
- `rel="tag"` → la pagina linkata è una **pagina di tag/categoria** collegata all’articolo.
    

Questo è utile:

- per i motori di ricerca
    
- per chi analizza il sito
    
- per mantenere un **codice semantico** e chiaro.
    

---

### **3. Alcuni valori di `rel` “semantici”**

Vediamo alcuni valori che descrivono **che tipo di risorsa** stai linkando.

#### **3.1. `rel="tag"` – pagina di tag / categoria**

Scenario: blog di cucina, articolo su una **torta al cioccolato**. Alla fine dell’articolo hai i tag:

- `dessert`
    
- `torte`
    
- `cioccolato`
    

Ogni tag punta a una pagina che raggruppa tutte le ricette con quel tag.

```html
<!-- In fondo alla ricetta "Torta al cioccolato" -->
<p>Tag:
  <a href="/tag/dessert"    rel="tag">dessert</a>,
  <a href="/tag/torte"      rel="tag">torte</a>,
  <a href="/tag/cioccolato" rel="tag">cioccolato</a>
</p>
```

Stai dicendo: “Queste pagine sono **tag** associati a questo contenuto”.

---

#### **3.2. `rel="next"` e `rel="prev"` – articoli precedente / successivo**

Scenario: serie di articoli (es. capitoli di una guida).

```html
<a href="/articoli/guida-html-parte-1" rel="prev">Articolo precedente</a>
<a href="/articoli/guida-html-parte-3" rel="next">Articolo successivo</a>
```

- `rel="prev"` → la pagina linkata è l’articolo **precedente** nella sequenza.
    
- `rel="next"` → la pagina linkata è l’articolo **successivo**.
    

Questo aiuta motori di ricerca e assistive technologies a capire che si tratta di una **serie ordinata**.

---

#### **3.3. `rel="author"` – pagina dell’autore**

Scenario: articolo con bio dell’autore.

```html
<p>
  Scritto da
  <a href="/autori/luca-rossi" rel="author">Luca Rossi</a>
</p>
```

Stai dicendo: la pagina `/autori/luca-rossi` è la pagina **autore** di chi ha scritto questo contenuto.

---

#### **3.4. `rel="external"` – risorsa esterna al sito**

```html
<a href="https://www.gazzetta.it" rel="external">
  Leggi la notizia sulla Gazzetta
</a>
```

È un modo per dichiarare: “Questo link porta **fuori** dal mio sito”.  
Non è obbligatorio, ma può essere usato da tool e motori di ricerca.

---

#### **3.5. `rel="license"` – link alla licenza**

Se hai una pagina con la **licenza** del contenuto (tipo Creative Commons, termini d’uso):

```html
<a href="/licenza" rel="license">
  Leggi la licenza d’uso
</a>
```

Oppure, se usi una licenza esterna:

```html
<a href="https://creativecommons.org/licenses/by/4.0/" rel="license">
  Licenza CC BY 4.0
</a>
```

---

#### **3.6. `rel="help"` – pagina di aiuto / FAQ**

```html
<a href="/supporto" rel="help">
  Consulta le nostre FAQ
</a>
```

Può essere una pagina di:

- FAQ
    
- supporto
    
- contatti per assistenza
    

Stai dicendo: “Questo link è **un aiuto** per capire o usare meglio il sito/prodotto”.

---

### **4. I tre valori “critici”: `noopener`, `nofollow`, `noreferrer`**

Questi tre sono molto importanti, soprattutto per **sicurezza** e **SEO**.

---

#### **4.1. `rel="noopener"` – sicurezza quando usi `target="_blank"`**

Quando apri un link in **nuova scheda**:

```html
<a href="https://esempio.com" target="_blank">Apri in nuova scheda</a>
```

la pagina aperta **può accedere** alla finestra che l’ha aperta tramite `window.opener` in JavaScript.

Questo può essere usato in modo malevolo: ad esempio, la pagina esterna potrebbe cambiare l’URL della pagina originale.

Per evitare ciò, si usa:

```html
<a href="https://esempio.com"
   target="_blank"
   rel="noopener">
  Apri in nuova scheda in modo sicuro
</a>
```

Effetto di `noopener`:

- la pagina esterna **non ha accesso** a `window.opener`;
    
- riduci una possibile superficie d’attacco.
    

> Per ora, prendilo come “regola di sicurezza”:  
> **se usi `target="_blank"`, abbina sempre `rel="noopener"`**.

Spesso viene accoppiato anche con `noreferrer`:

```html
<a href="https://esempio.com"
   target="_blank"
   rel="noopener noreferrer">
  Apri in nuova scheda
</a>
```

---

#### **4.2. `rel="nofollow"` – SEO: non seguire il link**

I crawler (Google bot, ecc.) quando vedono un link **normalmente lo seguono**:

```html
<a href="https://altrsitestrano.com">Vai al sito X</a>
```

Con `rel="nofollow"` dici al motore di ricerca:

> “Questo link esiste per l’utente, ma **tu crawler non usarlo** per il ranking, non seguirlo come ‘raccomandazione’.”

Esempio:

```html
<a href="https://altrsitestrano.com" rel="nofollow">
  Vai al sito X
</a>
```

Tipiche situazioni in cui si usa `nofollow`:

- link a siti che **non vuoi “consigliare”** a livello SEO;
    
- link **pubblicitari** o sponsorizzati;
    
- link creati dagli utenti (es. commenti) dove non vuoi trasferire “autorità SEO”.
    

> Idea chiave:  
> **“nofollow” = non usare questo link per calcolare reputazione / ranking.**

---

#### **4.3. `rel="noreferrer"` – non inviare il “referrer”**

Quando clicchi un link da un sito A verso un sito B, nella richiesta HTTP di solito c’è l’header **`Referer`** (sì, scritto male senza la seconda “r” 😅) che indica **da dove arrivi**:

- “Sei arrivato a B da `https://miosito.it/articolo-xyz`”.
    

Con `rel="noreferrer"` dici al browser:

> “Non inviare il referrer quando vai su questo link”.

Esempio:

```html
<a href="https://altrsitestrano.com" rel="noreferrer">
  Vai al sito X
</a>
```

Effetti:

- il sito B **non sa** da quale pagina precisa sei arrivato;
    
- utile per motivi di **privacy** o per non rivelare URL interni / parametri.
    

Spesso usato insieme a `noopener`:

```html
<a href="https://altrsitestrano.com"
   target="_blank"
   rel="noopener noreferrer">
  Apri in nuova scheda senza passare referrer
</a>
```

---

### **5. Collegamento con SEO e collaborazione con specialisti**

Molte volte chi fa **SEO** (ottimizzazione per motori di ricerca) ti dirà cose tipo:

- “Questo link deve essere `nofollow`.”
    
- “Qui usa `noreferrer`.”
    
- “I link esterni a pagamento devono avere `rel="nofollow"`”.
    

E tu, come dev, devi solo sapere **dove** metterlo:

```html
<a href="https://esempio-partner.com"
   rel="nofollow">
  Partner commerciale
</a>
```

Non serve che tu sia un SEO specialist, ma è utilissimo capire:

- `nofollow` = non usare il link come segnale di ranking;
    
- `noreferrer` = non inviare la pagina di provenienza;
    
- `noopener` = sicurezza quando usi `target="_blank"`.
    

---

### **6. Mini–schema riepilogativo**

#### **Valori “semantici” per `<a>`**

- `rel="tag"` → la pagina linkata è un **tag/categoria**.
    
- `rel="next"` → pagina successiva in una **serie**.
    
- `rel="prev"` → pagina precedente in una **serie**.
    
- `rel="author"` → pagina **autore**.
    
- `rel="external"` → risorsa **esterna** al sito.
    
- `rel="license"` → **licenza** del contenuto.
    
- `rel="help"` → pagina di **aiuto / FAQ / supporto**.
    

#### **Valori “critici” (SEO / sicurezza)**

- `rel="noopener"` → **sicurezza** con `target="_blank"` (niente `window.opener`).
    
- `rel="nofollow"` → **non usare** il link per ranking SEO.
    
- `rel="noreferrer"` → **non inviare** il referrer (pagina sorgente) al sito di destinazione.
    
