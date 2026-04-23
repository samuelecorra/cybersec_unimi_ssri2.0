## **Lezione 7: Esercizio 4 – Ripasso link, fragment, mailto, rel e tag**

In questo esercizio prendi tutto ciò che hai visto nel modulo sui **collegamenti ipertestuali** e lo applichi alla pagina dell’**Esercizio 3**, arricchendola con:

- link interni (document fragment)
    
- un link e-mail con oggetto (`mailto:`)
    
- un link esterno a Google con `rel` per SEO/privacy
    
- una sezione di **tag** per l’articolo
    

---

## **1. Punto di partenza: duplicare l’Esercizio 3**

1. Prendi il file:
    
    ```text
    esercizi/esercizio_3.html
    ```
    
2. Copiane il contenuto e crea:
    
    ```text
    esercizi/esercizio_4.html
    ```
    
3. Incolla il codice dell’esercizio 3 dentro `esercizio_4.html`.
    

L’esercizio 4 è quindi una **versione evoluta** dell’esercizio 3.

---

## **2. Document fragment sull’indice (link interni alla pagina)**

### 2.1. Obiettivo

Nell’esercizio 3 avevi un piccolo **indice** con:

- Sezione 1
    
- Sezione 2
    
- Sezione 3
    
- eventualmente “Titoletto 1”, “Titoletto 2”
    

Ora devi fare in modo che **ogni voce dell’indice** sia un link che:

- quando cliccato
    
- fa **scroll** alla sezione corrispondente della pagina.
    

Questo si fa con i **document fragment**: `href="#id-della-sezione"` + attributo `id` nel punto di arrivo.

---

### 2.2. Passo 1: trasformare le voci dell’indice in link

Supponiamo che l’indice sia così:

```html
<ul>
  <li>Sezione 1</li>
  <li>Sezione 2</li>
  <li>Sezione 3</li>
  <li>Titoletto 1</li>
  <li>Titoletto 2</li>
</ul>
```

Lo trasformi in:

```html
<ul>
  <li><a href="#sezione-1">Sezione 1</a></li>
  <li><a href="#sezione-2">Sezione 2</a></li>
  <li><a href="#sezione-3">Sezione 3</a></li>
  <li><a href="#titoletto-1">Titoletto 1</a></li>
  <li><a href="#titoletto-2">Titoletto 2</a></li>
</ul>
```

Regola:

- **sempre** `href="#qualcosa"`
    
- dove `qualcosa` è l’`id` dell’elemento dove vuoi arrivare.
    

---

### 2.3. Passo 2: aggiungere gli `id` alle sezioni

Ora devi definire gli **id** nei punti del contenuto in cui vuoi atterrare.

Per esempio, se le sezioni sono:

```html
<h2>Sezione 1</h2>
<p>Testo sezione 1...</p>

<h2>Sezione 2</h2>
<p>Testo sezione 2...</p>

<h2>Sezione 3</h2>
<p>Testo sezione 3...</p>

<h3>Titoletto 1</h3>
<p>Testo titoletto 1...</p>

<h3>Titoletto 2</h3>
<p>Testo titoletto 2...</p>
```

le trasformi in:

```html
<h2 id="sezione-1">Sezione 1</h2>
<p>Testo sezione 1...</p>

<h2 id="sezione-2">Sezione 2</h2>
<p>Testo sezione 2...</p>

<h2 id="sezione-3">Sezione 3</h2>
<p>Testo sezione 3...</p>

<h3 id="titoletto-1">Titoletto 1</h3>
<p>Testo titoletto 1...</p>

<h3 id="titoletto-2">Titoletto 2</h3>
<p>Testo titoletto 2...</p>
```

> **Regola sugli ID:**
> 
> - ogni `id` deve essere **univoco** nella pagina
>     
> - niente spazi → usa `-` o `camelCase` (`sezione-1` è perfetto)
>     

Ora, cliccando:

- “Sezione 1” → salta al `h2 id="sezione-1"`
    
- “Titoletto 2” → salta al `h3 id="titoletto-2"`
    

Questo è il classico uso di **document fragment**.

---

## **3. Link e-mail con oggetto (`mailto:` + `subject`)**

### 3.1. Obiettivo

Aggiungere un link che:

- apre il client di posta (Outlook, Mail, Gmail, ecc.)
    
- compila **già destinatario + oggetto**.
    

Esempio: vicino ai dati dell’autore.

---

### 3.2. Struttura di base `mailto:`

Struttura:

```html
<a href="mailto:lucarossi@gmail.com">Contattami per una consulenza</a>
```

Questo apre la schermata di nuova mail con destinatario compilato.

---

### 3.3. Aggiungere l’oggetto (`subject`)

Per aggiungere l’oggetto si usa la query string nella `href`:

- `?subject=...`
    

Gli spazi vanno **URL-encodati** come `%20`.

Esempio:

```html
<a href="mailto:lucarossi@gmail.com?subject=Informazioni%20urgenti">
  Contattami per una consulenza
</a>
```

Qui:

- `mailto:lucarossi@gmail.com` → destinatario
    
- `?subject=Informazioni%20urgenti` → oggetto “Informazioni urgenti”
    

---

### 3.4. (Opzionale) indicare che è la pagina autore

Puoi anche aggiungere `rel="author"` se il link rappresenta chiaramente il **contatto dell’autore** dell’articolo:

```html
<a href="mailto:lucarossi@gmail.com?subject=Informazioni%20urgenti"
   rel="author">
  Contattami per una consulenza
</a>
```

---

## **4. Link a Google senza “follow” e senza referrer**

### 4.1. Obiettivo

Aggiungere un link a Google che:

- funzioni normalmente per l’utente
    
- ma per il motore di ricerca sia:
    
    - **`nofollow`** → non usare questo link per il ranking
        
    - **`noreferrer`** → non inviare la pagina di provenienza come referrer
        

---

### 4.2. Esempio di link

```html
<a href="https://www.google.com"
   rel="nofollow noreferrer">
  Vai su Google
</a>
```

Spiegazione:

- `rel="nofollow"` → dica al crawler: “non usare questo link come segnale SEO”
    
- `rel="noreferrer"` → il sito di destinazione non riceve il referrer (l’URL della pagina da cui arrivi).
    

> Volendo, potresti anche usare `target="_blank"` e aggiungere `noopener`, ma nella traccia vengono richiesti solo **no follow** e **no referrer**.

---

## **5. Inserire i tag dell’articolo (`rel="tag"`)**

### 5.1. Obiettivo

Aggiungere in fondo all’articolo una **lista di tag**, ognuno:

- è un link a una pagina del sito dedicata a quel tag
    
- ha `rel="tag"` per dichiarare semanticamente che si tratta di un **tag dell’articolo**
    

---

### 5.2. Esempio pratico

Metti in fondo alla pagina, sotto il contenuto principale, ad esempio:

```html
<p>Tag dell'articolo:</p>
<ul>
  <li>
    <a href="/tag/html" rel="tag">HTML</a>
  </li>
  <li>
    <a href="/tag/formattazione-testo" rel="tag">Formattazione testo</a>
  </li>
  <li>
    <a href="/tag/link" rel="tag">Link</a>
  </li>
</ul>
```

Nel tuo esercizio il docente ha usato nomi generici tipo `Tag 1`, `Tag 2`, `Tag 3`, ma l’idea “giusta” in un blog reale sarebbe:

- `/tag/html`
    
- `/tag/collegamenti-ipertestuali`
    
- `/tag/seo`
    
- ecc.
    

---

## **6. Riepilogo logico dell’esercizio 4**

Nell’**Esercizio 4** hai preso la pagina articolo e l’hai resa più “vera”:

1. **Indice con fragment**
    
    - link come `<a href="#sezione-1">...`
        
    - sezioni con `id="sezione-1"`, ecc.
        
2. **Link e-mail (`mailto:`) con oggetto**
    
    - `mailto:indirizzo?subject=Testo%20oggetto`
        
    - opzionale `rel="author"` se è il contatto dell’autore.
        
3. **Link esterno a Google con `rel` SEO/privacy**
    
    - `rel="nofollow noreferrer"` per non dare “peso” SEO e non inviare referrer.
        
4. **Tag dell’articolo con `rel="tag"`**
    
    - lista di link ai tag, con `rel="tag"`.
        

Tutto questo ti abitua a:

- usare i link in modo **semantico e corretto**
    
- ragionare sia su **esperienza utente** sia su **motori di ricerca / accessibilità**.
    

