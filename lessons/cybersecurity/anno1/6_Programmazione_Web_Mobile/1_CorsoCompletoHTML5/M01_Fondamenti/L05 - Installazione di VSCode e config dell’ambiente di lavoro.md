# **Lezione 5: Installazione di Visual Studio Code e configurazione dell’ambiente di lavoro**

## **1. Perché serve preparare un ambiente di lavoro professionale**

Dopo aver costruito tutte le basi teoriche — cos’è il Web, cos’è l’HTML, cos’è un sito, e come funziona un motore di ricerca — è il momento di creare un ambiente di lavoro efficiente.  
HTML si può scrivere su un blocco note, ma per lavorare seriamente serve un editor pensato per programmare.

Lo strumento che useremo è **Visual Studio Code (VS Code)**, un editor moderno, potente, gratuito e perfetto per lavorare con HTML, CSS, JavaScript e molte tecnologie future.

---

## **2. Scaricare Visual Studio Code**

Digitiamo su Google “Visual Studio Code”.  
Nella home ufficiale troviamo subito il pulsante **Download for Windows** (o per il nostro sistema operativo).  
Premendolo, il browser effettua una richiesta al server di Microsoft e riceve in risposta il file di installazione.

Questo meccanismo è identico a ciò che avviene quando in un sito aggiungiamo uno spazio dedicato ai download:

- il server possiede un file,
    
- il browser lo richiede,
    
- il server lo invia.
    

La differenza è solo il livello di complessità: siti come quello di Microsoft utilizzano architetture molto più evolute, ma il principio è lo stesso.

---

## **3. Installazione di Visual Studio Code**

A download completato, apriamo il file di installazione.  
Ci verrà mostrato il contratto di licenza: lo accettiamo.

La procedura può sembrare leggermente diversa se il programma è già installato, ma le opzioni principali sono identiche. Le più importanti da selezionare sono:

### **Opzioni consigliate durante l’installazione**

1. **Crea un’icona sul desktop**  
    Utile, ma opzionale.
    
2. **Aggiungi “Apri con Code” nel menu di scelta rapida dei file**  
    Ci permette di aprire immediatamente qualunque file con VS Code.
    
3. **Aggiungi “Apri con Code” nel menu di scelta rapida delle cartelle**  
    Fondamentale per aprire interi progetti.
    
4. **Registra Code come editor predefinito**  
    Così certi file verranno aperti direttamente con VS Code.
    
5. **Aggiungi Code al PATH**  
    Ci permette di eseguire comandi come `code .` da terminale per aprire una cartella in VS Code.
    

Se non sapete cos’è il terminale, nessun problema: lo vedremo più avanti. È solo un modo alternativo per interagire con i file.

Dopo aver selezionato le opzioni, clicchiamo **Installa**.  
L’operazione dura pochi secondi.

---

## **4. Avvio di Visual Studio Code**

Una volta installato, apriamo VS Code.  
Compariranno le attività iniziali: possiamo chiuderle.  
A questo punto dobbiamo configurare il nostro editor come strumento di lavoro per HTML.

Il passo successivo consiste nell’installare le estensioni fondamentali.

---

## **5. Le estensioni essenziali per lavorare con HTML**

Le estensioni ampliano le funzionalità di VS Code.  
Clicchiamo sull’icona delle **Estensioni** nella barra laterale e installiamo quanto segue.

### **5.1 Prettier – Code Formatter**

`Prettier` è un formattatore automatico.  
Serve per organizzare il codice in modo chiaro e leggibile con un solo comando da tastiera.  
È essenziale per evitare file disordinati.

### **5.2 Auto Close Tag**

Questa estensione aggiunge automaticamente il tag di chiusura quando scriviamo un tag HTML.

### **5.3 Auto Rename Tag**

Se rinominiamo un tag di apertura, rinomina automaticamente anche quello di chiusura.

Queste due estensioni, insieme, riducono del 90% gli errori tipici dei principianti e velocizzano enormemente la scrittura del codice.

### **5.4 Live Server**

Questa è la più importante.

`Live Server` crea un **server locale fittizio** che simula il comportamento di un server reale.  
Ogni volta che salviamo una modifica, aggiorna automaticamente la pagina HTML nel browser, senza dover ricaricare manualmente.

È fondamentale per lavorare in modo professionale.

### **5.5 Microsoft Edge Tools (opzionale)**

Questa estensione non è indispensabile, ma contiene strumenti utili per:

- analisi del sito lato accessibilità,
    
- debugging,
    
- strumenti per sviluppatori integrati.
    

Per ora è solo un extra da conoscere.

---

## **6. Dimostrazione pratica delle estensioni**

Vediamo in azione quanto appena installato.

Creo un file `index.html` e scrivo:

```html
<h1>Ciao</h1>
```

Già così possiamo osservare tre comportamenti:

- **Auto Close Tag:** appena apro `<h1>`, viene creata automaticamente la chiusura `</h1>`.
    
- **Auto Rename Tag:** rinominando `<h1>` in `<h2>`, si aggiorna automaticamente anche la chiusura.
    
- **Highlight integrato:** VS Code evidenzia i tag di apertura e chiusura associati.
    

Questi automatismi rendono la scrittura del codice estremamente più sicura e fluida.

---

## **7. Avviare Live Server**

Posizioniamoci in una cartella vera (non in un singolo file isolato).  
Un sito è **una cartella con dentro dei file**, quindi Live Server funziona solo se stiamo lavorando in una cartella.

Apriamo `index.html`, clicchiamo in basso a destra su **Go Live**, e il browser si aprirà su:

```
http://127.0.0.1:5500/
```

oppure

```
http://localhost:5500/
```

“127.0.0.1” è l’indirizzo locale del nostro computer (lo vedremo molte volte nel corso).  
Ogni volta che salviamo un file, Live Server rifà automaticamente la richiesta e ricarica la pagina.

Ad esempio:

- scrivo “Ciao”
    
- salvo
    
- appare “Ciao” nel browser
    
- scrivo “QWERTY”
    
- salvo
    
- appare immediatamente “QWERTY”.
    

Il server locale sta simulando esattamente ciò che avviene in un server vero.

---

## **8. Conclusione della lezione**

Ora abbiamo installato:

- l’editor professionale che useremo per tutto il corso,
    
- le estensioni fondamentali per lavorare in modo confortevole e sicuro,
    
- lo strumento che simula un server reale e aggiorna automaticamente le pagine.
    

Nella prossima lezione faremo un passo fondamentale:

> **vedremo le scorciatoie (shortcuts) essenziali per usare VS Code in modo rapido ed efficiente.**

Capirle subito permette di velocizzare drasticamente il lavoro e di non sviluppare cattive abitudini.