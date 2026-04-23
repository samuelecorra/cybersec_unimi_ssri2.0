## **Lezione 4: Classi e ID**

---

### **1. Da dove ripartiamo**

Nel video precedente ci eravamo lasciati con un:

```html
<div class="section">
  ...
</div>
```

e avevamo detto:

> “Se vuoi una section, usa proprio `<section>`.”

Quindi:

- **per la struttura** usiamo gli **elementi semantici** (`section`, `article`, `header`, `footer`, …)
    
- ma **sui vari elementi** possiamo appiccicare delle **etichette**: gli **ID** e le **classi**
    

Ora vediamo:

- cosa sono gli **ID**
    
- cosa sono le **classi**
    
- perché **gli ID devono essere unici**
    
- perché **le classi sono lo strumento principale per lo stile**
    

---

### **2. ID: identificatore univoco**

`id` sta per **identifier** → _identificatore_.

L’idea è la stessa di:

- targa dell’auto
    
- codice fiscale
    
- numero di telefono
    
- username su una piattaforma
    

In un certo contesto ce n’è **uno solo**.

> Un ID identifica **un singolo elemento** in tutta la pagina.

#### **2.1 Esempio con le label dei form**

Usiamo l’esempio classico:

```html
<label for="prova">Accetta le condizioni</label>
<input id="prova" type="checkbox">
```

Qui succede una cosa comoda:

- quando clicchi sulla **label**, si attiva la **checkbox** collegata all’`id="prova"`
    

Se però faccio un errore e duplico l’ID:

```html
<label for="prova">Accetta le condizioni</label>
<input id="prova" type="checkbox">

<label for="prova">Newsletter</label>
<input id="prova" type="checkbox">
```

Cosa accade?

- HTML ti **avvisa**: ci sono **più elementi con lo stesso ID**
    
- il browser, in pratica, **considera solo il primo** `id="prova"` come “vero”
    
- le label `for="prova"` continueranno a riferirsi **sempre e solo** al primo elemento
    

Quindi:

- la pagina non “esplode”, ma il comportamento è **ambiguo e sbagliato**
    
- per JavaScript (quando lo useremo) sarà **un disastro**: si aspetta un solo elemento
    

**Regola ferrea:**

> In un documento HTML, ogni valore di `id` deve comparire **al massimo una volta**.

---

### **3. ID e stile: perché non usarli per tutto**

È possibile usare un ID anche nel CSS:

```html
<label id="qwerty">Testo label</label>
```

```css
#qwerty {
  background: red;
}
```

Funziona, ma:

- **l’ID è unico** → quello stile si applica ad **un solo elemento**
    
- se per sbaglio copi-incolli lo stesso `id` altrove, CSS _sembra_ applicarsi a più elementi, ma:
    
    - HTML e JavaScript continueranno a considerare l’ID **non valido**
        
    - il comportamento non è affidabile
        

Quindi:

- **usare gli ID per lo stile è possibile**, ma **non è la scelta migliore**
    
- lo useremo principalmente per:
    
    - collegare `label` e `input`
        
    - creare anchor di pagina (frammenti tipo `#sezione`)
        
    - agganciare JavaScript a un singolo elemento specifico
        

Per “gruppi” di elementi, ci serve altro → **le classi**.

---

### **4. Il problema che le classi risolvono**

Immagina di avere 3 label:

```html
<label>Opzione A</label>
<label>Opzione B</label>
<label>Opzione C</label>
```

Vuoi che **tutte e tre**:

- abbiano **sfondo rosso**
    
- ma tutte le altre label della pagina **no**
    

Soluzione ingenua:

- aggiungere lo stile inline a ognuna:
    

```html
<label style="background:red;">Opzione A</label>
<label style="background:red;">Opzione B</label>
<label style="background:red;">Opzione C</label>
```

Problemi:

- devi ripetere lo stile a mano
    
- se domani vuoi cambiare il colore in blu, devi cercare e modificare **tutte** le label
    
- su un sito con tante pagine diventa ingestibile
    

Serve quindi:

> un modo per dire “questi elementi **appartengono alla stessa categoria** e la categoria ha un certo stile”

Quella “categoria” è la **classe**.

---

### **5. Classi: etichette per gruppi di elementi**

La classe si dichiara con l’attributo `class`.

```html
<label class="rosso">Opzione A</label>
<label class="rosso">Opzione B</label>
<label class="rosso">Opzione C</label>
```

Poi, nel CSS, definisci lo stile della classe:

```css
.rosso {
  background: red;
}
```

Risultato:

- tutte le label con `class="rosso"` hanno lo **stesso stile**
    
- se domani cambi l’implementazione:
    

```css
.rosso {
  background: #ff8080;
  color: white;
}
```

si aggiornano **tutte insieme**, senza toccare l’HTML.

**Idea chiave:**

> La classe è come un **post-it** che appiccichi a più elementi per dire “voi siete dello stesso tipo grafico”.

---

### **6. Più classi sullo stesso elemento**

Un elemento può avere **più classi**:

```html
<label class="rosso padding">Opzione A</label>
```

E nel CSS:

```css
.rosso {
  background: red;
}

.padding {
  padding: 10px;
}
```

Così:

- `rosso` gestisce il **colore**
    
- `padding` gestisce lo **spazio interno**
    

L’elemento eredita **entrambi** gli stili.

Questo ti permette di **combinare** classi come mattoncini Lego:

- una classe per il colore
    
- una per il padding
    
- una per l’allineamento
    
- …ecc.
    

---

### **7. Esempio più “reale”: `avviso-errore`**

Supponiamo di voler creare un box “errore” riutilizzabile:

```html
<div class="avviso-errore">
  Errore: email non valida.
</div>

<div class="avviso-errore">
  Errore: campi obbligatori mancanti.
</div>
```

CSS:

```css
.avviso-errore {
  padding: 10px;
  border-radius: 10px;
  background: #ffdddd;
  border: 2px solid red;
  text-align: center;
  margin-bottom: 10px;
}
```

Cosa abbiamo ottenuto?

- una **classe sola** che definisce:
    
    - spazio interno
        
    - bordo
        
    - colore
        
    - allineamento testo
        
    - margine inferiore
        
- ogni volta che vogliamo un avviso di errore, basta:
    

```html
<div class="avviso-errore">Testo dell’errore...</div>
```

Non devi ricordarti il `border`, il `padding`, il `background` ogni volta:  
basta appiccicare l’etichetta giusta.

---

### **8. ID vs classi: confronto finale**

Mettiamola secca:

#### **ID (`id`)**

- **Unico** in tutta la pagina
    
- Serve a:
    
    - collegare `label` ↔ `input`
        
    - creare ancore (`<a href="#contatti">`)
        
    - agganciare uno specifico elemento in JavaScript
        
- Può essere usato nel CSS (`#mio-id { ... }`), ma non è l’uso principale
    

#### **Classe (`class`)**

- Può essere usata **su N elementi**
    
- Uno stesso elemento può avere **più classi**
    
- Serve principalmente a:
    
    - applicare **stili** a gruppi di elementi
        
    - categorizzare gli elementi (“bottoni primari”, “bottoni secondari”, “avvisi errore”, ecc.)
        

---

### **9. Collegare il tutto al discorso semantica**

Ricordiamo sempre la gerarchia:

1. **Scegli l’elemento semantico giusto**
    
    - `header`, `main`, `section`, `article`, `aside`, `footer`, `p`, `h1`, `ul`, `li`, ecc.
        
2. **Dai un ID** solo quando ti serve:
    
    - un collegamento univoco
        
    - un anchor di pagina
        
    - un target specifico per JS
        
3. **Aggiungi classi** per lo stile:
    
    - `class="avviso-errore"`
        
    - `class="btn btn-primario"`
        
    - `class="card prodotto"`
        

Noi **non** useremo più:

```html
<div class="main"></div>
<div class="section"></div>
<div class="footer"></div>
```

ma:

```html
<main class="layout-main">...</main>
<section class="hero">...</section>
<footer class="site-footer">...</footer>
```

- elemento semantico → **significato**
    
- classe → **presentazione/stile**
    

---

Con questo hai tutto il quadro su **ID e classi**.

La prossima lezione (87) passerà agli **elementi `br` e `hr`**, e vedremo quando ha senso usarli e quando invece è meglio affidarsi al CSS per gli spazi e le separazioni.