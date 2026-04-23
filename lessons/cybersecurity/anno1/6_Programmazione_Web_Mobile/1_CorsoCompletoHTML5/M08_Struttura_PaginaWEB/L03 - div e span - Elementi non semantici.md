## **Lezione 3: `div` e `span` – Elementi non semantici**

---

## **1. Cosa sono davvero `div` e `span`**

Da inizio corso li vedi ovunque, ma finora li abbiamo solo “usati” senza definirli bene.

- `div`
    
    - elemento **block**
        
    - occupa tutta la larghezza disponibile
        
    - manda a capo ciò che viene dopo
        
- `span`
    
    - elemento **inline**
        
    - si comporta come una parola in mezzo al testo
        
    - non va a capo da solo
        

Entrambi sono:

- **non semantici** → il nome **non dice nulla** sul contenuto  
    (`div` non significa “sezione del sito”, `span` non significa “titolo”, ecc.)
    
- **non pre-formattati** → non hanno stile particolare di default  
    (niente grassetto, niente margini, niente comportamento speciale, a parte block/inline)
    

Sono quindi **contenitori “vuoti”**.  
Il loro valore si vede solo quando iniziamo a metterci **CSS** sopra.

---

## **2. Differenza con gli elementi semantici e pre-formattati**

Confronta:

- `article`, `header`, `nav`, `main`, `footer`  
    → semanticamente parlando, il nome **spiega il ruolo** del contenuto
    
- `h1`, `h2`, `p`, `strong`, `blockquote`, `details`, `summary`  
    → oltre al significato semantico, hanno **stile e comportamento** di default:
    
    - `h1`, `h2` → testo grande, in grassetto
        
    - `p` → paragrafo con margini
        
    - `strong` → grassetto
        
    - `blockquote` → rientro a destra
        
    - `details` + `summary` → pannello espandibile
        

Esempio:

```html
<h1>Titolo</h1>      <!-- grande, in grassetto -->
<p>Paragrafo...</p> <!-- con margini propri -->
<strong>Importante</strong> <!-- grassetto -->
```

Invece:

```html
<div>Testo</div>
<span>Testo</span>
```

- non indicano _che tipo_ di testo sia
    
- non hanno stili “speciali”, oltre al fatto che:
    
    - `div` è block
        
    - `span` è inline
        

**Conclusione:**  
`div` e `span` non ci aiutano né a livello di significato, né a livello di formattazione iniziale.

---

## **3. Perché allora esistono e perché sono così usati?**

Proprio perché **non fanno niente**, diventano perfetti come:

> “contenitori trasparenti” su cui appoggiare lo stile CSS.

Immagina un `div` come una **scatola invisibile**:

- HTML sa che la scatola esiste
    
- ma l’utente non vede niente di speciale finché non applichi il CSS
    

Esempio: diversi `div` annidati senza stile:

```html
<div>
  <div>
    <div>
      Contenuto
    </div>
  </div>
</div>
```

Visivamente:  
→ vedi solo la parola “Contenuto”, nessuna differenza evidente.

Se facessi la stessa cosa con `p`, cominceresti a vedere margini, rientri, chiusure strane ecc.  
Con i `div` no: **non si “vedono”**, ma ci sono.

Ed è proprio il fatto di essere “neutri” che li rende così potenti dal punto di vista del layout.

---

## **4. Usare `div` come contenitori di layout**

Guarda cosa succede se cominciamo a mettere del CSS sui `div`.

### **4.1 Esempio con tre `div` annidati**

```html
<div class="box-verde">
  <div class="box-rosso">
    <div class="box-blu">
      Contenuto
    </div>
  </div>
</div>
```

Con un po’ di CSS:

```css
.box-verde {
  background: green;
  padding: 30px;
}

.box-rosso {
  background: red;
  padding: 30px;
}

.box-blu {
  background: blue;
  padding: 30px;
}
```

All’improvviso:

- i tre `div` diventano **tre contenitori visibili**
    
- ciascuno con il proprio spazio interno e colore
    
- ma a livello semantico **non abbiamo aggiunto nessuna informazione “di significato”**
    

Stai solo usando `div` come **involucri grafici**.

### **4.2 Raggruppare più elementi per stilare “in blocco”**

Caso tipico:

```html
<p>Paragrafo 1</p>
<p>Paragrafo 2</p>
```

A un certo punto vuoi trattarli **come un unico blocco** (ad esempio metterli affiancati, centrarli, dargli un bordo unico, ecc.).

Avvolgi tutto in un `div`:

```html
<div class="gruppo-paragrafi">
  <p>Paragrafo 1</p>
  <p>Paragrafo 2</p>
</div>
```

Poi con il CSS (più avanti, con Flexbox) potrai fare:

```css
.gruppo-paragrafi {
  display: flex;
  gap: 16px;
}
```

E i due paragrafi potranno mettersi **affiancati**, allineati, centrati, ecc.  
Il `div` è stato usato solo come **contenitore di layout**.

---

## **5. `div` vs `section`: quando usare cosa**

Domanda naturale:

> “Ma se ho già `section`, perché usare `div`?”

- Usa **`section`** quando il blocco ha **un significato logico** all’interno della pagina:
    
    - “Servizi”
        
    - “Testimonianze”
        
    - “Chi siamo”
        
    - “FAQ”
        
- Usa **`div`** quando:
    
    - è un “pezzetto” interno di quella sezione (es. un piccolo box dentro una section)
        
    - ti serve solo per **applicare stile** (disporre, colorare, allineare)
        
    - non ha una vera identità semantica
        

Esempio corretto:

```html
<section id="servizi">
  <h2>I nostri servizi</h2>

  <div class="servizio">
    <h3>Servizio A</h3>
    <p>Descrizione...</p>
  </div>

  <div class="servizio">
    <h3>Servizio B</h3>
    <p>Descrizione...</p>
  </div>
</section>
```

Esempio **sbagliato** (abuso di `div` al posto di elementi semantici):

```html
<div class="section-servizi">
  <div class="title-h2">I nostri servizi</div>

  <div class="servizio">
    <div class="title-h3">Servizio A</div>
    <div class="paragrafo">Descrizione...</div>
  </div>
</div>
```

Qui stai usando `div` per simulare `section`, `h2`, `h3`, `p`.  
Semanticamente è povero e scorretto.

**Regola d’oro:**

- prima scegli l’**elemento semantico corretto**
    
- solo dove ti manca un contenitore neutro aggiungi `div`
    

---

## **6. E lo `span`? Stessa idea, ma inline**

Finora abbiamo parlato sempre di `div`, ma `span` è la stessa cosa:

- è **non semantico**
    
- è **non pre-formattato**
    
- la differenza è che è **inline**, quindi vive dentro il testo
    

Esempio tipico:

```html
<p>
  Questo è un testo normale, ma
  <span class="evidenziato">questa parte la voglio stilare</span>
  in modo diverso.
</p>
```

Con CSS:

```css
.evidenziato {
  background: yellow;
  font-weight: bold;
}
```

Risultato:

- il paragrafo rimane un paragrafo
    
- lo `span` diventa una “**micro-scatola**” dentro il testo, su cui applichi lo stile
    

Usi `span` quando:

- serve **intervenire su una parola o frase singola**
    
- non vuoi cambiare il significato semantico (resta pur sempre un paragrafo)
    
- ti basta una “mini scatola” inline su cui mettere colore, dimensione, ecc.
    

---

## **7. Quando è sbagliato abusare di `div` e `span`**

Errori tipici da evitare:

- usare `div` al posto di `p`:
    
    ```html
    <!-- Sbagliato -->
    <div>Questo è un paragrafo.</div>
    ```
    
- usare `div` al posto di `section` / `article`:
    
    ```html
    <!-- Sbagliato -->
    <div class="main">...</div>
    ```
    
- usare solo `div` e `span` per tutto il layout, ignorando `header`, `main`, `footer`, ecc.
    

Questo porta al famoso:

> “Div-hell” – intere pagine fatte di soli `div`, impossibili da capire.

Noi vogliamo esattamente il contrario:  
**semantica chiara** + `div`/`span` solo come supporto di stile.

---

## **8. Metafora finale: perché sono così importanti**

Pensa alla pagina web come a una casa:

- elementi semantici (`header`, `main`, `section`, `article`, `footer`)  
    → sono **stanza da letto, cucina, salotto, bagno**  
    → ti dicono a cosa serve quella parte
    
- `div` e `span`  
    → sono **scaffali, mensole, scatole dentro le stanze**  
    → ti aiutano a organizzare e sistemare, ma non cambiano il tipo di stanza
    

Se usi solo `div`:

- è come descrivere la casa dicendo solamente “scatola 1, scatola 2, scatola 3…”
    
- nessuno capisce più dove dormire, dove cucinare, dove lavarsi
    

Se combini bene:

- **stanze giuste** (semantica)
    
- **scaffali e scatole** dove servono (div/span + CSS)
    

ottieni una casa sia **ordinata** che **bella da vedere**.

---

Con questo hai il quadro completo degli **elementi non semantici**.

Nella prossima lezione entriamo nel cuore del loro utilizzo reale:

> **Classi e ID** – come “agganciare” i nostri `div`/`span` (e tutti gli altri elementi) al CSS e a JavaScript, e come dare identità alle varie parti del layout.