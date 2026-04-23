## **Lezione 6 – Come il browser carica e processa il CSS (versione semplificata)**

### **Obiettivo della lezione**

Prima di imparare le regole CSS, serve una visione molto generale di **come il browser elabora HTML e CSS per trasformarli in ciò che vediamo sullo schermo**.  
Questa è una spiegazione volutamente leggera: la vedremo in profondità nella parte finale del corso, quando parleremo di ottimizzazione e performance.

Il punto essenziale è che il browser _non_ prende semplicemente l’HTML e lo “mostra”.  
Ci sono passaggi e calcoli precisi.

---

### **1. Il browser riceve l’HTML e inizia a leggerlo**

Appena la pagina viene caricata, il browser:

- legge tutto l’HTML dall’inizio alla fine;
    
- incontra tag, attributi, testi, elementi annidati;
    
- inizia a costruire una struttura interna chiamata **DOM (Document Object Model)**.
    

Il DOM è la **versione rielaborata e strutturata** del nostro HTML, creata in modo che il browser e JavaScript possano manipolarla.  
È la prima cosa che viene prodotta.

---

### **2. Durante la costruzione del DOM, il browser si ferma per scaricare file esterni**

Mentre legge l’HTML, il browser può incontrare:

- `<link rel="stylesheet" href="...">`
    
- `<script src="...">`
    
- `<script>` inline
    
- link a risorse come Google Fonts
    
- librerie esterne come jQuery, file JS, file CSS
    

Ogni volta che incontra un file esterno “bloccante” (come CSS o JavaScript), il browser:

1. **si ferma**,
    
2. inizia a scaricare quel file,
    
3. **aspetta** di averlo ricevuto,
    
4. solo allora continua a costruire il DOM.
    

Questo meccanismo è uno dei motivi per cui certi siti sono lenti:  
HTML → si ferma → download CSS → riprende → si ferma → download JS → riprende…

Vedremo come ottimizzare tutto questo quando studieremo la fase “performance”.

---

### **3. Quando il DOM è pronto, il browser passa alla fase CSS**

Ora il browser prende:

- tutte le regole CSS interne (`<style>`)
    
- tutte le regole CSS inline
    
- tutte le regole CSS esterne (file .css)
    
- tutte le regole CSS di default del browser
    

e le **unisce** seguendo i principi di:

- ordine di lettura
    
- cascata
    
- specificità
    
- importanza (inline > interno > esterno > user-agent)
    

Questa è la fase in cui il browser “decide” cosa sovrascrive cosa.

Il concetto che abbiamo visto prima — “quella sotto vince su quella sopra” — avviene precisamente qui.

---

### **4. Il browser calcola il layout (posizionamento degli elementi)**

Una volta che:

- ha la struttura HTML trasformata in DOM
    
- ha tutte le regole CSS pronte e unite in un unico risultato
    

il browser fa un’enorme quantità di calcoli:

- dimensioni reali di ogni elemento
    
- margini, padding, bordi
    
- posizionamenti verticali e orizzontali
    
- altezza della pagina
    
- larghezza di ogni box
    
- eventuali spostamenti dovuti a display, float, flexbox, grid…
    

Questa fase viene chiamata **layout** (o reflow).

---

### **5. Il browser disegna effettivamente la pagina (paint)**

Quando tutto il layout è pronto, il browser procede con la fase finale:

- **disegna a schermo** ogni pixel della pagina
    
- colora gli elementi
    
- applica font, bordi, immagini
    
- renderizza tutto ciò che deve essere visibile
    

Questo passaggio si chiama **paint**.

Successivamente, in caso di modifiche dinamiche (JavaScript), il browser può ripetere solo alcune parti di questi processi.

---

### **Schema mentale semplice da ricordare**

Per ora basta tenere in mente questa sequenza molto logica:

**1. HTML → costruisco il DOM  
2. mi fermo quando trovo file esterni → li scarico  
3. unisco tutte le regole CSS  
4. calcolo dove deve stare ogni elemento  
5. disegno la pagina a schermo**

Non è “mischio tutto e lo mostro”: ci sono passaggi precisi, ognuno fondamentale.

---

### **Cosa serve ricordare ora**

- Non devi memorizzare i nomi tecnici delle fasi.
    
- Devi solo ricordarti che il browser **processa**, **calcola**, **scarica**, **ricompone**, e _solo alla fine_ mostra la pagina.
    
- Il DOM sarà importantissimo non solo per CSS, ma soprattutto per JavaScript (lo vedremo più avanti).
    
- Approfondiremo tutti questi meccanismi nella parte finale del corso, quando avremo già molta esperienza CSS e capiremo perché certe scelte di ottimizzazione rendono un sito veloce o lento.
    

Nel prossimo video, entreremo nel dettaglio del **DOM**, la struttura fondamentale che permette al browser e a JavaScript di manipolare la pagina.