## **Lezione 4: Struttura di un form control (i singoli campi)**

---

### **1. Cos’è un “form control”**

Quando parliamo di **form control** stiamo parlando dei **campi** del form:

- casella di testo
    
- campo password
    
- menu a tendina
    
- area di testo lunga
    
- pulsante invia, reset, ecc.
    

In HTML, i principali “mattoni base” dei form control sono:

- `input` → il jolly, usato per tantissimi tipi di campi
    
- `textarea` → area di testo grande, multi riga
    
- (più avanti vedremo anche `select`, `button`, ecc.)
    

Oggi ci concentriamo sulla **struttura interna** di questi campi:  
quali attributi **minimi** devono avere, e cosa significano veramente.

---

### **2. `input` e `textarea`: la base**

Due esempi:

```html
<input type="text" name="nome">

<textarea name="descrizione"></textarea>
```

Differenze:

- `input` è pensato per **valori brevi**:
    
    - nome
        
    - cognome
        
    - email
        
    - username
        
    - password
        
- `textarea` è pensata per **testi lunghi**:
    
    - descrizione
        
    - biografia
        
    - messaggio lungo
        
    - commento articolato
        

`textarea` può:

- essere ridimensionata dall’utente (angolino in basso a destra)
    
- gestire comodamente più righe di testo
    

Per ora la teniamo come “teaser”, perché la vedremo meglio nella carrellata di tutti i form control. Il focus adesso è: **gli attributi fondamentali di un singolo campo**.

---

### **3. L’attributo `type`: che tipo di campo è?**

Per `input`, l’attributo più caratteristico è:

```html
<input type="text">
```

Finora abbiamo visto:

- `type="text"` → campo di testo normale
    
- `type="password"` → campo di testo che nasconde i caratteri
    
- `type="submit"` → pulsante per inviare il form
    
- `type="reset"` → pulsante per resettare i campi
    

In futuro vedremo molti altri `type` (`email`, `number`, `date`, ecc.).  
Per ora ci basta l’idea:

> `type` definisce **la natura del campo**: cosa fa e come si comporta.

---

### **4. L’attributo `id`: aggancio per label, CSS e JavaScript**

Esempio:

```html
<input
  type="text"
  id="nome"
  name="nome">
```

L’`id` serve principalmente a:

1. **Collegare la label al campo**
    
    ```html
    <label for="nome">Nome</label>
    <input id="nome" name="nome" type="text">
    ```
    
    Il `for="nome"` punta all’`id="nome"` → click sulla label = focus sul campo.
    
2. **Agganciarci CSS**
    
    ```css
    #nome {
      background-color: red;
    }
    ```
    
3. **Agganciarci JavaScript**
    
    ```js
    document.getElementById('nome');
    ```
    

Cosa importante che spesso non si sa all’inizio:

> L’`id` **non è solo** per i form control.  
> Può essere messo su _qualsiasi_ elemento HTML.

Nel contesto dei form, però, lo vedrai spessissimo sugli `input`, proprio per:

- collegare le label
    
- stilare il singolo campo
    
- controllarlo in JavaScript
    

---

### **5. L’attributo `name`: il vero identificatore per il backend**

Questo è **il più importante** lato “dati”:

```html
<input
  type="text"
  id="nome"
  name="nome">
```

- L’utente si orienta con:
    
    - il testo della **label** (“Nome”)
        
    - l’aspetto visuale del campo
        
- Il **computer**, invece, non guarda:
    
    - né la label
        
    - né l’`id` (dal punto di vista dei dati inviati)
        

Per il backend il campo è identificato dal **`name`**.

Quando invii il form, i dati vengono spediti così (semplificando):

```txt
nome=Edoardo&cognome=Rossi
```

Qui `nome` e `cognome` sono proprio i **name** dei campi.

> Il `name` è **l’identificatore ufficiale del campo nei dati inviati**.  
> Senza `name`, quel campo è praticamente invisibile per il server.

L’`id` può anche mancare (se non ti serve per label/CSS/JS),  
ma il `name` è fondamentale se il dato deve essere usato lato server.

---

### **6. L’attributo `value`: il valore effettivo del campo**

Ogni campo di input ha un **valore**.  
Esistono due momenti chiave:

1. **Valore iniziale** (che puoi definire tu):
    
    ```html
    <input
      type="text"
      name="nome"
      value="Edoardo">
    ```
    
    Appena carichi la pagina, nel campo vedi già scritto “Edoardo”.
    
2. **Valore aggiornato mentre l’utente scrive**
    
    Se lo stesso campo è vuoto:
    
    ```html
    <input type="text" name="nome">
    ```
    
    e l’utente scrive “Edoardo”, il **value** “interno” diventa:
    
    - prima `E`
        
    - poi `Ed`
        
    - poi `Edo`
        
    - … e così via
        
    - fino a `Edoardo`
        

Per JavaScript (o per il backend) il **value** è ciò che conta:

- se in JS chiedi `campo.value`, otterrai il contenuto corrente
    
- se invii il form, viene mandato proprio il **value**
    

Quando vedi un form con campi **precompilati** (nome, email, ecc.):

- molto spesso sono stati riempiti mettendo il `value` nel markup
    
- oppure via JavaScript, ma il concetto è lo stesso:  
    il `value` parte già con un certo testo.
    

---

### **7. `placeholder` vs `value`: stessa cosa? No, due ruoli diversi**

Esempio:

```html
<input
  type="text"
  name="nome"
  placeholder="Inserisci il tuo nome">
```

Differenze fondamentali:

- **`value`**
    
    - è il **valore reale** del campo
        
    - viene inviato al server
        
    - se c’è, il campo è _già compilato_
        
    - il testo è di solito nero (normale)
        
- **`placeholder`**
    
    - è un **testo di aiuto**
        
    - scompare appena l’utente inizia a scrivere
        
    - non viene inviato come valore se l’utente lascia il campo vuoto
        
    - il testo è di solito grigio chiaro
        

Esempio pratico:

```html
<!-- Value -->
<input
  type="text"
  name="nome"
  value="Edoardo">

<!-- Placeholder -->
<input
  type="text"
  name="nome"
  placeholder="Edoardo">
```

Visivamente:

- il `value="Edoardo"` è un **dato reale** già inserito  
    → il cursore può mettersi in mezzo alla parola e modificare
    
- il `placeholder="Edoardo"` è solo un esempio  
    → appena scrivi, scompare
    

Uso tipico del `placeholder`:

- spiegare **come** va inserito il dato
    

Esempi:

```html
<label for="sito">Nome sito</label>
<input
  id="sito"
  name="sito"
  type="text"
  placeholder="miosito.it">
```

```html
<label for="username">Username</label>
<input
  id="username"
  name="username"
  type="text"
  placeholder="@nome_scelto">
```

```html
<label for="email">Email</label>
<input
  id="email"
  name="email"
  type="email"
  placeholder="nome@dominio.it">
```

Molti designer **tolgono la label** e mettono tutto nel placeholder.  
Tecnicamente si può fare, ma a livello di **accessibilità** è sconsigliato:

- le label sono fondamentali per screen reader
    
- il placeholder scompare quando scrivi
    

Quindi: **label + placeholder** è la combo migliore.

---

### **8. Attributi extra importanti: `required`, `disabled`, `readonly`**

Passiamo agli attributi che controllano **il comportamento** del campo.

#### **8.1. `required`: campo obbligatorio**

```html
<input
  type="text"
  name="nome"
  required>
```

Significa:

- il campo **non può essere vuoto** quando invii il form
    
- il browser farà un controllo base:
    
    - se il campo è vuoto → blocca l’invio
        
    - mostra un messaggio tipo “Compila questo campo”
        

È una prima forma di **validazione lato client**,  
prima ancora di JavaScript e del backend.

> In futuro useremo JS e controlli lato server per validazioni più precise,  
> ma `required` è già un aiuto gratuito.

---

#### **8.2. `disabled`: campo disabilitato (non usabile per ora)**

```html
<input
  type="text"
  name="cognome"
  disabled>
```

Effetti:

- non puoi cliccarci
    
- non puoi scrivere
    
- il campo appare “spento”
    
- **non viene inviato** al server
    

Tipica casistica:

- campo che si abilita **solo dopo** una certa condizione
    

Esempio:

- non puoi compilare “Cognome” finché non hai inserito “Nome”
    
- non puoi selezionare alcune opzioni finché non hai accettato i termini
    

In quel caso:

- inizialmente l’`input` è `disabled`
    
- con JavaScript, quando la condizione è soddisfatta, togli `disabled`
    

---

#### **8.3. `readonly`: campo di sola lettura**

```html
<input
  type="text"
  name="email"
  value="utente@example.com"
  readonly>
```

Effetti:

- il campo è **visibile**
    
- il testo è **selezionabile** (puoi copiarlo)
    
- ma non puoi modificarlo (i tasti non hanno effetto)
    

Differenza con `disabled`:

- `disabled` → campo “spento”, ignorato dal form (non inviato)
    
- `readonly` → campo “solo lettura”, **inviato** normalmente con il suo value
    

Uso tipico di `readonly`:

- mostrare dati che l’utente **non può modificare**, ma che:
    
    - vuole vedere
        
    - il server ha bisogno di ricevere di nuovo nell’invio
        

Esempi:

- username non modificabile
    
- email principale non modificabile (puoi cambiarla solo contattando il supporto)
    
- codice univoco dell’utente
    

Spesso `readonly` va a braccetto con `value`:

```html
<input
  type="text"
  name="username"
  value="cyber_samu_01"
  readonly>
```

---

### **9. `textarea` vs `input`: quando usare cosa**

Esempio di `textarea`:

```html
<label for="bio">Biografia</label>
<textarea
  id="bio"
  name="bio"
  placeholder="Scrivi qualcosa su di te..."></textarea>
```

Differenze pratiche rispetto a `input type="text"`:

- `textarea`:
    
    - supporta **più righe**
        
    - è comodissima per testi lunghi
        
    - può essere ridimensionata (di default)
        
- `input type="text"`:
    
    - va bene per una singola riga di testo
        
    - diventa scomodo se devi scrivere frasi lunghe:  
        devi scorrere a destra e sinistra continuamente
        

Regola pratica:

- **Dati brevi** → `input`
    
- **Testi lunghi e descrittivi** → `textarea`
    

---

### **10. Anticipazione: `select` + `option` (menu a tendina)**

Solo un assaggio, che poi approfondiremo:

```html
<label for="paese">Paese</label>
<select id="paese" name="paese">
  <option value="it">Italia</option>
  <option value="es">Spagna</option>
  <option value="fr">Francia</option>
</select>
```

È il classico **menu a tendina**:

- `select` = il controllo principale
    
- `option` = singole scelte
    
- `value` di `option` = dato effettivo inviato (`"it"`, `"es"`, ecc.)
    

È un altro tipo di form control che vedremo nella “carrellata” dedicata.

---

### **11. Riepilogo**

In questa lezione abbiamo messo a fuoco **la struttura interna di un singolo campo**:

- `type` → definisce il tipo di input (testo, password, submit, reset, ecc.)
    
- `id` → serve per collegare label, per CSS e JavaScript; può stare su qualunque elemento
    
- `name` → è l’**identificatore ufficiale** del campo nei dati inviati al server
    
- `value` → contiene il **valore reale** del campo (iniziale e durante la digitazione)
    
- `placeholder` → è solo un suggerimento, scompare quando inizi a scrivere
    
- `required` → rende il campo obbligatorio
    
- `disabled` → campo disabilitato, non modificabile e non inviato
    
- `readonly` → campo di sola lettura, non modificabile ma inviato
    
- `textarea` → usata per testi lunghi, più comoda di `input` in quel caso
    
- `select` → menu a tendina (con `option`), che vedremo meglio in seguito
    

---

Dalla prossima lezione inizieremo la **carrellata dei vari tipi di form control**:  
vedremo `input` specializzati (`email`, `number`, `date`, `checkbox`, `radio`, ecc.) e capiremo quando usare ciascuno per costruire form robusti, chiari e facili da usare.