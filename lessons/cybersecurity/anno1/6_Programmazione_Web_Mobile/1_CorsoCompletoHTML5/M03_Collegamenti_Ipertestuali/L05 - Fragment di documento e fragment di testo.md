## **Lezione 5: Fragment di documento e fragment di testo**

In questa lezione vediamo in profondità i **fragment** nei link:

- **document fragment** → linkano a un _punto_ del documento tramite un `id`.
    
- **text fragment** → linkano a un _pezzo di testo_ presente nella pagina, anche senza `id`.
    

Sono entrambi **link interni** (`internal links`), ma funzionano in modo diverso.

---

### **1. Cosa sono i fragment**

Un **fragment** è tutto ciò che viene dopo il `#` in un URL:

```text
/pagina.html#qualcosa
```

Serve a dire al browser:

> “Apri questa pagina, poi **scorri fino a…**”

Può essere:

- un **document fragment**: `#id-dell-elemento`
    
- un **text fragment**: `#:~:text=testo-encodato`
    

---

### **2. Document fragment: link a un `id` della pagina**

#### **2.1. Esempio base: piccolo indice di pagina**

Poniamo di avere una pagina lunga, con varie sezioni, e in alto un **indice**:

```html
<ul>
  <li>Prima sezione</li>
  <li>Seconda sezione</li>
</ul>
```

Vogliamo che cliccando su **“Prima sezione”** la pagina scrolli fino al blocco relativo.

Per farlo dobbiamo:

1. **Dare un `id`** al punto di arrivo.
    
2. Collegarci a quell’`id` con un link `href="#id"`.
    

Esempio:

```html
<!-- Indice in alto -->
<ul>
  <li><a href="#prima-sezione">Prima sezione</a></li>
  <li>Seconda sezione</li>
</ul>

<!-- ...tanto contenuto nel mezzo... -->

<!-- Punto di arrivo -->
<p id="prima-sezione">
  Questo è il contenuto della prima sezione.
</p>
```

Comportamento:

- quando clicchi su **“Prima sezione”**, il browser:
    
    - resta _nella stessa pagina_,
        
    - scorre fino all’elemento che ha `id="prima-sezione"`.
        

> Il `#prima-sezione` è il **document fragment**: indica _“vai all’elemento con questo id”_.

---

#### **2.2. Regole fondamentali sugli `id`**

- Un `id` è come un **codice fiscale** dell’elemento:
    
    - **unico** nella pagina (non puoi avere due elementi con lo stesso `id`);
        
    - un elemento può avere **un solo `id`**.
        
- Convenzioni comuni:
    
    - niente spazi → usa trattini:
        
        - `id="prima-sezione"`, non `id="prima sezione"`;
            
    - niente caratteri strani → solo lettere, numeri, trattini, underscore.
        

Questo è importante perché:

- se esistono **due** `id="prima-sezione"`, il browser si comporta in modo ambiguo;
    
- se l’`id` viene **rimosso** o cambiato, il fragment `#prima-sezione` **non troverà più il bersaglio**.
    

---

#### **2.3. Document fragment da un’altra pagina**

I document fragment non sono limitati alla stessa pagina.  
Puoi linkare **un’altra pagina** ed **una sezione interna** di quella pagina.

Esempio: dalla `index.html` voglio saltare a **“Sezione 3”** dentro `esercizi/esercizio3.html`.

```html
<!-- index.html -->
<a href="/esercizi/esercizio3.html#sezione-3">
  Vai alla sezione 3 dell'esercizio 3
</a>
```

Nella pagina di destinazione (`esercizio3.html`) ci deve essere qualcosa tipo:

```html
<h2 id="sezione-3">Sezione 3</h2>
<p>Contenuto della terza sezione...</p>
```

Comportamento:

1. Il browser carica `/esercizi/esercizio3.html`.
    
2. Poi guarda il fragment `#sezione-3`.
    
3. Cerca l’elemento con `id="sezione-3"` e ci scrolla sopra.
    

---

#### **2.4. Cosa succede se l’`id` non esiste?**

Se linki:

```html
<a href="/esercizi/esercizio3.html#sezione-3">
```

ma in `esercizio3.html` **non esiste** alcun elemento con `id="sezione-3"`:

- la pagina viene comunque aperta;
    
- **non** avviene nessuno scroll particolare;
    
- l’utente resta in alto (come un link normale senza fragment).
    

Quindi:

> Il document fragment funziona **solo se** nella pagina c’è effettivamente un elemento con quell’`id`.

Questo è il motivo per cui mantenere gli `id` coerenti e non cambiarli “a caso” è importante.

---

### **3. Text fragment: `#:~:text=`**

I **text fragment** sono una funzione più recente dei browser (supportata bene da Chrome/Chromium, in ritardo su Firefox) e permettono di fare qualcosa di diverso:

> Non linki a un `id`, ma a **una stringa di testo presente nella pagina**.

In pratica puoi dire:

- “Apri la pagina X”
    
- “poi scrolla fino a quando trovi questo testo”
    
- “e **evidenzialo** automaticamente”.
    

La sintassi base è:

```text
/pagina.html#:~:text=TESTO_URL_ENCODED
```

Dentro il `href`:

```html
<a href="/esercizi/esercizio3.html#:~:text=21%20marzo%202024">
  Vai alla data di pubblicazione
</a>
```

---

#### **3.1. Esempio: testo singolo**

Supponiamo che in `esercizio3.html` ci sia questa data in fondo:

```html
<time datetime="2024-03-21">21 marzo 2024</time>
```

Vogliamo creare un link che:

- apra quella pagina,
    
- scrolli fino alla data,
    
- la evidenzi.
    

Scriviamo:

```html
<a href="/esercizi/esercizio3.html#:~:text=21%20marzo%202024">
  Vai alla data di pubblicazione
</a>
```

- Dopo il `#` abbiamo: `:~:text=...`
    
- `21%20marzo%202024` è `21 marzo 2024` **URL-encodato** (`%20` per gli spazi).
    

Effetto (in un browser che supporta i text fragment):

- la pagina si apre;
    
- scrolla fino a trovare **“21 marzo 2024”**;
    
- quel testo viene **evidenziato**.
    

Se carichi la stessa pagina **senza** il fragment, l’evidenziazione non c’è: è il browser che la aggiunge solo quando vede `#:~:text=` nell’URL.

---

#### **3.2. Esempio: inizio e fine (`start` / `end`)**

Spesso vuoi evidenziare **un pezzo più lungo**, non solo una parola singola.

La sintassi supporta:

```text
#:~:text=INIZIO,FINE
```

Dove:

- `INIZIO` = testo da cui iniziare;
    
- `FINE` = testo a cui arrivare.
    

Esempio tipico: voglio evidenziare **tutta la mail** `Luca Rossi ... .com`.

Testo nella pagina:

```html
<p>Contattami all'indirizzo Luca Rossi at esempio.com per info.</p>
```

Voglio selezionare dalla parola `Luca` fino a `.com`.

URL (semplificato):

```html
<a href="/esercizi/esercizio3.html#:~:text=Luca%20Rossi,com">
  Vai all'indirizzo e-mail di Luca
</a>
```

- `Luca%20Rossi` → inizio
    
- `com` → fine
    

Il browser cercherà la prima occorrenza coerente di quella coppia “inizio, fine” e evidenzierà l’intervallo corrispondente.

> Nota: in realtà la specifica prevede anche _prefissi_ e _suffissi_ più avanzati, ma sono dettagli che puoi approfondire dopo: per ora ti basta l’uso base con `text=...` e opzionale `,fine`.

---

### **4. Document fragment vs Text fragment**

Mettiamoli a confronto.

#### **4.1. Document fragment (`#id`)**

- Si appoggia a un **id HTML**:
    
    - serve un elemento con `id="qualcosa"`.
        
- Sintassi:
    
    ```html
    href="/pagina.html#id-elemento"
    ```
    
- Pro:
    
    - Chiaro e standard.
        
    - Funziona su tutti i browser.
        
    - Semplicissimo lato HTML/JS.
        
- Contro:
    
    - Devi **gestire a mano gli `id`**:
        
        - se un `id` cambia o viene rimosso, i link si “rompono”.
            

---

#### **4.2. Text fragment (`#:~:text=`)**

- Si appoggia al **testo presente nella pagina**, non a un `id`.
    
- Sintassi:
    
    ```html
    href="/pagina.html#:~:text=testo%20encodato"
    ```
    
- Pro:
    
    - Può funzionare **anche se non hai messo id** sulla sezione.
        
    - È utile per linkare in profondità in pagine non tue (tipo articoli lunghi).
        
    - Evidenzia automaticamente il testo.
        
- Contro:
    
    - Supporto non ancora universale (Firefox è in ritardo).
        
    - Se il testo cambia (anche solo una parola), il fragment non trova più il match.
        
    - Devi ricordarti di **URL-encodare** correttamente il testo.
        

---

### **5. Sicurezza: `rel="noopener"`**

Per i link che aprono altre pagine (in particolare quando usi cose più “avanzate” come i text fragment) è buona pratica aggiungere:

```html
<a href="...#:~:text=..." target="_blank" rel="noopener">
  Apri con text fragment in nuova scheda
</a>
```

- `target="_blank"` → apre il link in **nuova scheda**.
    
- `rel="noopener"` → migliora la **sicurezza**, impedendo alla pagina aperta di avere accesso alla `window.opener` della pagina chiamante (evita alcuni possibili attacchi o comportamenti indesiderati).
    

Più avanti, quando affronterai l’attributo `rel` in dettaglio, vedrai meglio tutte le varianti (`noreferrer`, `nofollow`, ecc.).  
Per ora ti basta ricordare:

> Se apri link in **nuova scheda**, usa `rel="noopener"` come default di sicurezza.

---

### **6. Riassunto operativo**

- Usa **document fragment** (`#id`):
    
    - per costruire **indici di pagina** (indice iniziale → varie sezioni);
        
    - per linkare sezioni di **pagine interne al sito**;
        
    - quando puoi controllare gli `id` del tuo HTML.
        
- Usa **text fragment** (`#:~:text=`):
    
    - quando vuoi mandare qualcuno **esattamente** a una frase all’interno di una pagina (anche non tua);
        
    - quando non hai `id` disponibili o non puoi modificare il codice HTML;
        
    - sapendo che il supporto browser non è ancora perfetto al 100%.
        