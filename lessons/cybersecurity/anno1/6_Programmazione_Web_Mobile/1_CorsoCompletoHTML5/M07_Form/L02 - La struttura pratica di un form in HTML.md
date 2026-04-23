## **Lezione 2: La struttura pratica di un form in HTML**

---

### **1. Dalla teoria al codice: il primo vero form**

Dopo tutta l’introduzione concettuale e “grafica”, ora vediamo **come un form appare realmente nel codice HTML**.  
Non devi seguirlo in tempo reale: l’obiettivo è **solo vedere come si scrive** ciò che abbiamo descritto nel video precedente, per iniziare a “toccare” la sintassi.

I form sono bellissimi perché “si portano dietro” un’enorme quantità di elementi HTML.  
Molti tag esistono _solo_ per i form.  
Quindi, quando usiamo un form, entriamo in un mini-mondo tutto dedicato ai dati.

Il primo elemento fondamentale è il tag:

```html
<form> ... </form>
```

Tutto ciò che riguarda il form sta **dentro** questo contenitore.

---

### **2. Gli attributi fondamentali del `<form>`: `action` e `method`**

Il tag `<form>` deve avere almeno due attributi chiave.

#### **Action**

`action="..."` indica **a chi consegniamo i dati** quando l’utente preme “Invia”.

Metaforicamente:

> “Ehi server, sto mandando questi dati. Questa è la porta a cui busso.”

Può essere:

- l’URL di una pagina PHP (stile anni ’90 / primi 2000), es:  
    `action="registrazione_utente.php"`
    
- una route più moderna (Node, Python, Java, ecc.), es:  
    `action="/api/registrazione"`
    

Per noi, adesso, è irrilevante:  
in questo corso **non invieremo niente realmente**.  
Il form è solo un modo per imparare la struttura HTML.

#### **Method**

`method="..."` definisce **il tipo di richiesta HTTP** che inviamo.

HTML ne supporta solo due:

- `GET` → richiesta “per ottenere dati”
    
- `POST` → richiesta “per inviare dati”
    

Il 99% delle volte userai **POST**, perché:

- nasconde i dati nella richiesta (non compaiono nella URL)
    
- è pensato per l’invio di dati sensibili o importanti
    

Noi ragioneremo sempre così:

```html
<form action="..." method="post">
```

Tutto ciò che è più complesso (PUT, PATCH, DELETE) appartiene a **API e backend**, non ai form HTML puri.

---

### **3. I “campi”: input, field, form controls**

Dentro il form inseriamo i **campi**.  
In inglese:

- _form controls_
    
- _fields_
    
- _inputs_
    

Tutti questi termini vogliono dire la stessa cosa:  
sono i pezzi in cui l’utente scrive o seleziona dati.

Il più semplice in assoluto è:

```html
<input type="text">
```

Qui abbiamo:

- `input` = il campo
    
- `type="text"` = indica che si tratta di testo generico
    

Ma ai form serve anche **un nome**.  
Il server deve sapere come identificare il campo.

E quindi aggiungiamo l’attributo `name="..."`.

```html
<input type="text" name="nome">
```

Questo vuol dire:

> “Caro server, il contenuto di questo campo si chiama _nome_.”

Se duplico:

```html
<input type="text" name="cognome">
```

Ora ho un campo “nome” e un campo “cognome”.

---

### **4. Il pulsante per inviare: `type="submit"`**

Per mandare i dati al server, serve un bottone con:

```html
<button type="submit">Invia</button>
```

Esiste anche:

```html
<button type="reset">Reset</button>
```

Questo resetta tutto il form ai valori iniziali.  
Utile se l’utente vuole ricominciare da zero.

Se provi:

- scrivi “Luca” e “Rossi”
    
- premi “Reset”
    
- tutto sparisce
    

Funzionerà nel browser anche se **non c’è alcun server dietro**.

Logico: il reset è un comportamento gestito dal browser.

---

### **5. L’attributo `type="password"`**

Già ora possiamo vedere un assaggio:

```html
<input type="password" name="password">
```

Il testo non viene mostrato, ma appare come pallini/asterischi.  
Non è sicurezza vera, solo **cosmetica**: serve per evitare che le persone vicino vedano la tua password.

Lo studieremo bene più avanti.

---

### **6. L’importanza fondamentale delle `<label>`**

Il validatore (o VS Code stesso) ti segnala subito un errore:

> i campi non hanno label

Perché le label servono per:

- accessibilità
    
- chiarezza visuale
    
- collegare testo e campo come un unico elemento cliccabile
    

Una label corretta si scrive così:

```html
<label for="nome">Nome</label>
<input type="text" id="nome" name="nome">
```

Regole d’oro:

- l’attributo `for` della label deve avere **lo stesso valore** dell’attributo `id` dell’input
    
- quasi sempre `id` e `name` coincidono (`nome/ nome`, `email/ email`)
    

Quando fai questo collegamento:

- cliccando sulla label, il cursore si posiziona nel campo
    
- il validatore smette di segnalare errori
    
- il form diventa più usabile e più accessibile
    

Stessa cosa per il cognome:

```html
<label for="cognome">Cognome</label>
<input type="text" id="cognome" name="cognome">
```

---

### **7. Mettiamo tutto insieme: il nostro primo form funzionante (lato HTML)**

Ecco la struttura completa mostrata nel video:

```html
<form action="registrazione_utente.php" method="post">
  
  <label for="nome">Nome</label>
  <input type="text" id="nome" name="nome" />

  <label for="cognome">Cognome</label>
  <input type="text" id="cognome" name="cognome" />

  <label for="password">Password</label>
  <input type="password" id="password" name="password" />

  <button type="submit">Invia</button>
  <button type="reset">Reset</button>

</form>
```

Se lo incolli in un file HTML:

- puoi scrivere nei campi
    
- puoi cliccare sulla label per attivare il campo
    
- puoi resettare tutto
    
- puoi premere “Invia” (ma non succederà nulla perché non c’è un server collegato)
    

È un form _vero_, solo che non è _collegato_.

---

### **8. Perché tutto questo è importante: ordine e struttura**

Adesso il form è “becero”: solo campi uno sotto l’altro.  
Funziona, ma:

- è piatto
    
- poco leggibile
    
- impossibile da stilare bene in CSS
    
- ingestibile se dovessimo avere 10, 15, 20 campi
    

Dalla prossima lezione cominciamo la **strutturazione logica** del form:

- sezioni
    
- gruppi
    
- gerarchie
    
- ordini coerenti
    

Questo è fondamentale prima di introdurre:

- CSS (per renderlo bello)
    
- JavaScript (per validarlo e migliorarlo)
    
- backend (per usarlo davvero)
    

---

### **9. Riepilogo della lezione**

- Abbiamo visto il tag `<form>`.
    
- Abbiamo introdotto gli attributi `action` e `method`.
    
- I form HTML accettano solo `get` e `post`.
    
- Abbiamo creato i primi input (`type="text"`, `type="password"`).
    
- Abbiamo aggiunto il bottone di invio (`submit`) e di reset (`reset`).
    
- Abbiamo introdotto le label, fondamentali per accessibilità e usabilità.
    
- Abbiamo costruito il primo form completo, anche se semplice.
    

---

Nella prossima lezione iniziamo a vedere **come strutturare** un form complesso in modo ordinato, leggibile e pronto per il CSS e per tutto ciò che verrà dopo.