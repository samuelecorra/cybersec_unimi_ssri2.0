Nella lezione precedente (L3) abbiamo ripercorso la nascita di Internet come infrastruttura di comunicazione globale, dalle prime sperimentazioni militari e accademiche fino alla diffusione progressiva della rete negli anni Settanta e Ottanta. Abbiamo visto come la crescita dei nodi connessi abbia reso necessario lo sviluppo di protocolli, architetture e servizi sempre più sofisticati.  
Questa lezione si inserisce come naturale prosecuzione: non ci fermiamo alla genesi della rete, ma osserviamo come negli anni Ottanta e Novanta Internet abbia iniziato a trasformarsi in un fenomeno planetario, destinato a cambiare la società. In particolare, analizzeremo la cronologia che porta alla nascita del **World Wide Web** e all’affermazione di standard e organizzazioni internazionali che ancora oggi regolano la vita della rete.

---
#  INTRODUZIONE

L’obiettivo della lezione è continuare ad approfondire la **cronologia dello sviluppo di Internet** e delle tecnologie ad esso collegate.  
Un concetto chiave qui è l’**accelerazione esponenziale**:

- Il numero di nodi connessi cresce in pochissimo tempo di ordini di grandezza.
    
- Parallelamente, le tecnologie informatiche si evolvono con una velocità altrettanto impressionante.  
    In altre parole: non si tratta solo di “più computer collegati”, ma di un vero salto qualitativo che prepara la strada a servizi sempre più complessi.
    

---

## Timeline dello sviluppo (anni ’80 – ’90)

C'è una sequenza temporale di eventi cruciali:

- **1986** → L’Italia entra ufficialmente in Internet.
    
    - Il collegamento parte dal Centro Nazionale di Calcolo di Pisa.
        
    - Primo dominio italiano registrato: **cnr.it**, ancora oggi attivo (Centro Nazionale di Ricerca)
        
    - È un passaggio storico perché segna l’integrazione dell’Italia nella rete mondiale.
        
- **1987** → Gli indirizzi in rete sono già **10.000**.
    
    - Ciò indica che Internet non è più un esperimento ristretto ma sta crescendo in modo tangibile.
        
- **1989** → Nasce il **World Wide Web** al CERN di Ginevra.
    
    - Non entriamo nei dettagli tecnici (saranno trattati dopo), ma segnaliamo che questo evento segna la nascita di un servizio che renderà Internet accessibile a tutti.
        
- **1989** → Gli indirizzi salgono a **100.000**.
    
    - Il salto in soli due anni (da 10.000 a 100.000) evidenzia la crescita esponenziale.
        
- **1991** → Viene pubblicato il **primo sito web**.
    
    - A livello simbolico è l’inizio del Web come lo intendiamo oggi.
        
- **1994** → Nasce il **World Wide Web Consortium (W3C)**.
    
    - Organismo internazionale non governativo che stabilisce **standard tecnici** (linguaggi, protocolli, accessibilità).
        
    - Fondamentale per garantire uniformità e compatibilità tra sistemi diversi.
        

---

#### 3. Approfondimento sul W3C

Il **W3C** (World Wide Web Consortium):

- È una **organizzazione non governativa internazionale** (NGO).
    
- Ha il compito di **sviluppare tutte le potenzialità del Web**.
    
- Obiettivo principale: **diffondere la cultura dell’accessibilità** (il Web deve essere fruibile da chiunque, indipendentemente dal dispositivo o dalle capacità dell’utente).
    
- Attività centrale: definire e mantenere **standard tecnici** per il Web.
    
    - Esempi: linguaggi di markup come HTML, protocolli di comunicazione come HTTP.
        
- In sintesi: senza il W3C il Web sarebbe frammentato in tante versioni incompatibili. Grazie agli standard, invece, ogni browser può leggere pagine scritte nello stesso linguaggio comune.
    

---

👉 I punti cardine sono dunque:

1. **La continuità storica** → dal collegamento italiano del 1986 fino al primo sito del 1991.
    
2. **Il concetto di crescita esponenziale** → da 10.000 a 100.000 indirizzi in due anni.
    
3. **Il ruolo delle istituzioni** → come il W3C nel mantenere coerenza e universalità al Web.

---

# Che cos’è il Web (WWW/W3)

- Il _World Wide Web_ è **un servizio di Internet** che consente di **accedere e navigare** tra contenuti **ipertestuali** (pagine collegate da link). È quindi **uno strato applicativo** sopra la rete Internet: Internet è l’infrastruttura di collegamento; il Web è un servizio che la usa per mostrarti documenti collegati tra loro.
    
    
- **“www” e “w3”.** Sono solo sigle/abbreviazioni con cui ci si riferisce al Web; storicamente compaiono anche nei nomi host (es. `www.`), ma non sono “obbligatorie” per definizione. La slide usa entrambi come equivalenti.
    
    
- **Ipertesto, in parole semplici.** “Ipertestuale” = documenti fatti di testo/immagini/media **+ collegamenti** cliccabili che ti portano ad altre risorse. L’idea chiave è che **i documenti non sono isolati**: formano una rete di rimandi.

---

### La triade che fa “girare” il Web (solo quadro d’insieme, dettagli dopo)

La slide elenca **tre elementi fondamentali** che collaborano tra loro:

1. **URL** – è **l’identificatore** della risorsa (l’“indirizzo” unico che indica _cosa_ vuoi).
    
2. **HTTP** – è **il protocollo di comunicazione** tra il tuo client (browser) e il server (stabilisce _come_ chiedi e ricevi).
    
3. **HTML** – è **il linguaggio di markup** con cui è **scritto il contenuto** che il browser renderizza (_che cosa_ ti viene mostrato e _come_ è strutturato).  
    Questa triade è solo introdotta qui; la approfondiremo nelle slide successive dedicate.

> Mental model lampo: quando digiti un indirizzo e premi Invio, **il browser usa l’URL** per sapere quale risorsa richiedere, **parla in HTTP** con il server per ottenerla, e **riceve HTML** (che poi renderizza come pagina visibile).

---

### Nota storica essenziale

![Pasted image 20250909171136.png](../../imgs/Pasted%20image%2020250909171136.png)
- **6 agosto 1991: primo sito web pubblicato**
- Questo atto “ufficializza” la nascita pubblica del Web in senso pratico: da quel momento, il Web comincia a essere **consultabile** via browser.

    In realtà tutto inizia nel 1980, quando Tim Berners-Lee, un informatico britannico, è assunto al CERN come consulente nell’ambito dell’ingegneria del software. Da subito si concentra su come rendere immagazzinabili e fruibili le informazioni, che al CERN erano in costante e veloce aumento. Nel 1989 presenta un documento intitolato Information Management: a Proposal (nella sottocartella). Nell’elaborato spiega come si potrebbe usare internet per condividere documentazione scientifica in formato elettronico. Obiettivo è quello di migliorare la condivisione e la cooperazione fra i ricercatori dell’istituto attraverso un formato indipendente dalle piattaforme utilizzate. Sviluppa in prima persona, sul suo Next Cube, il primo Web server e il primo browser, sfruttando l’idea di ipertesto (introdotto a livello teorico da Ted Nelson nel 1965).

---

### Errori concettuali da evitare

- **Web ≠ Internet.** Il Web è _uno dei servizi_ su Internet, non “tutta” Internet.
    
- **HTTP ≠ HTML.** HTTP è il **modo di parlare**; HTML è **la lingua in cui è scritto il documento**.
    
- **URL ≠ “nome carino”.** L’URL è un **identificatore univoco** della risorsa; non è solo testo descrittivo.


---
# 🔑 Definizione di URL

Un **URL (Uniform Resource Locator)** è una **sequenza di caratteri** che serve per **identificare in modo univoco una risorsa** (pagina web, immagine, video, documento, ecc.) all’interno di una rete di computer.  
👉 In parole semplici: è l’**indirizzo** con cui un client (es. browser) può raggiungere una risorsa ospitata su un server.

---

## 📐 Struttura canonica dell’URL

`protocollo://[username[:password]@]host[:porta][/percorso][?querystring][#fragment]`

![Pasted image 20250909172601.png](../../imgs/Pasted%20image%2020250909172601.png)
### 1. **protocollo://**

- Specifica **il linguaggio di comunicazione** usato per accedere alla risorsa.
    
- Esempi:
    
    - `http` → HyperText Transfer Protocol
        
    - `https` → versione sicura di HTTP (con crittografia SSL/TLS)
        
    - `ftp` → File Transfer Protocol
        
    - `mms` → Microsoft Media Server (streaming multimediale, oggi obsoleto).
        

---

### 2. **[username[:password]@]** _(opzionale)_

- Serve per fornire **credenziali di accesso** se la risorsa richiede autenticazione.
    
- Oggi è poco usato per motivi di sicurezza.
    
- Esempio:
    
    `ftp://utente:password@ftp.miosito.it`
    
    (connettendosi a un server FTP con user e password inline).
    

---

### 3. **host**

- È l’**indirizzo del server** che ospita la risorsa.
    
- Può essere espresso:
    
    - Come **nome di dominio**: `www.unimi.it`
        
    - Come **indirizzo IP**: `192.168.1.1`
        
- È l’elemento indispensabile che dice _dove_ andare a cercare la risorsa.
    

---

### 4. **[:porta]** _(opzionale, default se non specificata)_

- Indica la **porta TCP** su cui il server accetta le richieste.
    
- Se non scritta, il browser usa la porta **di default** del protocollo:
    
    - HTTP → 80
        
    - HTTPS → 443
        
    - FTP → 21
        
- Esempio:
    
    `http://www.miosito.it:8080`
    
    (richiesta HTTP sulla porta 8080 invece che sulla 80).
    

---

### 5. **[/percorso]**

- Indica la **posizione interna** della risorsa all’interno del server.
    
- Funziona come un “indirizzo di cartelle e file”.
    
- Esempio:
    
    `https://www.unimi.it/dipartimenti/cybersec/programmi.pdf`
    
    Qui `dipartimenti/cybersec/` è il percorso, `programmi.pdf` è la risorsa.
    

---

### 6. **[?querystring]** _(opzionale)_

- Contiene **parametri aggiuntivi** per personalizzare la richiesta.
    
- Tipico per i moduli e le ricerche.
    
- Sintassi: coppie `chiave=valore` separate da `&`.
    
- Esempio:
    
    `https://www.google.com/search?q=cybersecurity&hl=it`
    
    - `q=cybersecurity` → parametro di ricerca.
        
    - `hl=it` → parametro lingua.
        

---

### 7. **[#fragment]** _(opzionale)_

- Indica una **porzione specifica** della risorsa (es. un punto preciso di una pagina).
    
- Non viene inviato al server: è interpretato solo dal browser.
    
- Esempio:
    
    `https://it.wikipedia.org/wiki/Internet#Storia`
    
    porta direttamente alla sezione “Storia” della pagina.
    

---

## 🌍 Risoluzione dell’URL: il DNS

- L’**host** contenuto nell’URL deve essere tradotto in un **indirizzo IP**, perché i protocolli di rete lavorano su IP.
    
- Questo avviene tramite il **DNS (Domain Name System)**: un **database distribuito** che associa ogni nome di dominio (`.com`, `.it`, `.eu`, ecc.) al relativo indirizzo IP.  
    👉 Esempio: digitando `www.unimi.it`, il DNS restituisce un IP come `159.149.90.4`, che è l’indirizzo reale del server.
    

---

## 🎯 Esempio completo

`https://user:pass@www.unimi.it:443/dipartimenti/cybersec/programmi.pdf?lang=it#capitolo2`

- Protocollo → `https`
    
- Username/password → `user:pass`
    
- Host → `www.unimi.it`
    
- Porta → `443`
    
- Percorso → `/dipartimenti/cybersec/programmi.pdf`
    
- Query → `lang=it`
    
- Fragment → `#capitolo2`

---
# 🔑 Che cos’è HTTP

- **Nome completo:** _HyperText Transfer Protocol_.
    
- **Definizione:** è un **protocollo di comunicazione testuale** che stabilisce **come** client e server dialogano tra loro su Internet per scambiarsi risorse (pagine web, immagini, video, file).
    
- **Ruolo:** consente al browser (client) di **richiedere** risorse e al server di **rispondere** con i contenuti richiesti.
    
- **Caratteristica importante:** è un protocollo **stateless** → ogni richiesta è indipendente e non conserva memoria della precedente. Dopo lo scambio (richiesta ↔ risposta), la connessione si chiude. Questo lo rende leggero, dinamico e scalabile.
    

---

## 📡 Come funziona

1. Il **client** (tipicamente il browser) invia un **messaggio di richiesta** (HTTP Request) verso il server.
    
2. Il **server** riceve la richiesta, la elabora e risponde con un **messaggio di risposta** (HTTP Response).
    
3. La comunicazione avviene normalmente sulla **porta 80** (per HTTP), o 443 (per HTTPS, versione sicura).
    

👉 Questo meccanismo client–server è alla base di tutto il Web: ogni clic su un link o caricamento di pagina è una sequenza di richieste e risposte.

---

## 🛠️ I principali metodi HTTP

HTTP definisce una serie di **metodi** (o “verbi”) che specificano l’azione che il client vuole compiere sulla risorsa:

- **GET** → recupera una risorsa dal server.  
    Esempio: visitare una pagina (`GET /index.html`).
    
- **POST** → invia dati al server.  
    Esempio: compilare un modulo di registrazione, inviare un commento.
    
- **DELETE** → elimina una risorsa dal server.  
    Esempio: rimuovere un file o un record da un database tramite API.
    
- **PUT** → memorizza o aggiorna una risorsa sul server.  
    Esempio: caricare un file o aggiornare un documento esistente.
    
- **HEAD** → richiede solo l’header della risposta, senza il corpo (la risorsa).  
    Esempio: verificare se una pagina è disponibile senza scaricarla interamente (utile per test o caching).
    

---

## 🌐 Il ruolo del browser

- Il **browser** è l’applicazione client che:
    
    1. Invia le richieste HTTP.
        
    2. Riceve le risposte.
        
    3. Interpreta i contenuti (es. HTML, CSS, immagini).
        
- In pratica, è l’interfaccia che rende utilizzabile e navigabile il Web per l’utente finale.
    

---

## 🎯 In sintesi

- HTTP = linguaggio di comunicazione client-server per il Web.
    
- Stateless = ogni richiesta/risposta è indipendente.
    
- Metodi principali = GET, POST, DELETE, PUT, HEAD.
    
- Browser = il “client specializzato” che usa HTTP per presentare all’utente le risorse richieste.


---
# 🔑 Cos’è l’HTML

- **Acronimo:** _HyperText Markup Language_.
    
- **Non è un linguaggio di programmazione**, ma un **linguaggio di markup**: serve a **marcare** (annotare) il testo per dire al browser **cosa deve mostrare e come deve disporre gli elementi**.
    
- In pratica: HTML fornisce **istruzioni di struttura e presentazione** di una pagina web.
    

---

## 🧩 Come funziona

- Le istruzioni sono espresse tramite **tag**, cioè **parole chiave racchiuse tra parentesi angolari**.
    
    - Esempio: `<p>` per aprire un paragrafo, `</p>` per chiuderlo.
        
    - I tag sono spesso in coppia apertura/chiusura, ma esistono anche tag “auto-chiudenti” (es. `<br>` per andare a capo, `<img>` per inserire un’immagine).
        
- Il browser legge il file HTML, interpreta i tag e genera la visualizzazione a schermo.
    

---

## 🔎 Struttura tipica di un documento HTML

Il codice riportato nella slide ci mostra la forma base di una pagina:

`<!DOCTYPE html>       <!-- Dichiara il tipo di documento --> <html>                <!-- Radice del documento --> <head>                <!-- Contiene meta-info e titolo -->   <title>Body, paragrafi e a capo</title> </head> <body>                <!-- Contenuto visibile nella pagina -->   <p>Questa è la sezione body della pagina HTML, <br>   delimitata dai tag di apertura e chiusura omonimi.</p>    <p>Quello sopra è un paragrafo, <br>   e io sono un secondo paragrafo.</p> </body> </html>`

- **`<!DOCTYPE html>`** → serve a dire al browser che il documento è HTML5.
    
- **`<html>`** → tag radice che racchiude tutta la pagina.
    
- **`<head>`** → contiene informazioni _non visibili_ direttamente (titolo, meta-tag, link a fogli di stile).
    
- **`<title>`** → titolo che appare sulla scheda del browser.
    
- **`<body>`** → contiene il contenuto principale che l’utente vede (testo, immagini, link, ecc.).
    
- **`<p>`** → paragrafo.
    
- **`<br>`** → interruzione di riga.
    

---

## 🎭 Caratteristica fondamentale

L’HTML **separa la struttura logica** del documento (definita dal markup) dalla sua **rappresentazione grafica**, che viene gestita dal browser.

- Struttura: cosa c’è nella pagina e in che ordine (titoli, paragrafi, liste).
    
- Presentazione: come appare graficamente (font, colori, layout), spesso gestita da CSS (Cascading Style Sheets).
    

---

## 📂 File HTML

- Tutte queste istruzioni vengono scritte in un **file di testo** con estensione `.html`.
    
- Un singolo file HTML = una **pagina web**.
    

---

### 🎯 In sintesi

- **HTML** è il linguaggio che definisce la **struttura** delle pagine del Web.
    
- È fatto di **tag** che “marcano” i contenuti.
    
- Non programma, ma **descrive**.
    
- Ogni pagina web che navighiamo è, alla base, un file HTML interpretato dal browser.


---
Perfetto, chiudiamo in bellezza con la parte più “numerica” e al tempo stesso più concettuale: **lo sviluppo di Internet in termini di nodi connessi**.

---

## 📊 Crescita dei nodi connessi

Dalla tabella e dal grafico si nota chiaramente un **andamento esponenziale**. Alcuni dati-chiave:
![Pasted image 20250909173516.png](../../imgs/Pasted%20image%2020250909173516.png)
![Pasted image 20250909173545.png](../../imgs/Pasted%20image%2020250909173545.png)
- **1969** → 4 nodi (ARPANET, le origini).
    
- **1971** → 23 nodi.
    
- **1984** → 1.000 nodi.
    
- **1987** → 10.000 nodi.
    
- **1989** → 100.000 nodi.
    
- **1996** → 10.000.000 nodi.
    
- **2008** → 600.000.000 nodi.
    
- **2009** → 1 miliardo.
    
- **2011** → 2 miliardi.
    
- **2015** → 3,3 miliardi.
    
- **2020** → circa 20 miliardi di dispositivi.
    

👉 In poco più di 50 anni, Internet è passato da una rete di ricerca con pochi computer a una **rete planetaria con miliardi di dispositivi interconnessi**.

---

## 📈 Interpretazione del grafico

- Fino agli anni ’80 la crescita è lenta ma costante: la rete era confinata ad ambiti militari, accademici e industriali di nicchia.
    
- Con l’introduzione del **World Wide Web** nei primi anni ’90, la curva comincia a salire più rapidamente.
    
- Dagli anni 2000 in poi si ha la vera **esplosione esponenziale**, favorita da:
    
    - **Diffusione dei personal computer domestici.**
        
    - **Avvento della banda larga** e poi della fibra.
        
    - **Smartphone e dispositivi portatili**, che hanno reso Internet ubiquo.
        
    - **Internet of Things (IoT)**, che ha moltiplicato il numero di oggetti connessi (non più solo PC e telefoni, ma sensori, automobili, elettrodomestici, wearable…).
        

---

## 🌍 Dato significativo

Nel 2020 si contavano circa **20 miliardi di dispositivi connessi**, equivalenti a circa **2,5 dispositivi per ogni abitante del pianeta**.  
Non parliamo quindi solo di “utenti umani”, ma di un ecosistema sempre più popolato da macchine che comunicano tra loro.

---

## 🔐 Implicazioni per la Cybersecurity

Un tale incremento porta **opportunità** ma anche **rischi enormi**:

- **Superficie d’attacco enorme:** più dispositivi = più potenziali vulnerabilità.
    
- **Eterogeneità:** non tutti i dispositivi hanno gli stessi livelli di sicurezza (si pensi agli oggetti IoT economici).
    
- **Dati in tempo reale:** miliardi di dispositivi scambiano informazioni continue, spesso sensibili (posizione, dati sanitari, finanze).
    
- **Capillarità:** i dispositivi sono ovunque, fissi o in movimento, e “pluggati” in rete in modo spesso invisibile per l’utente.
    
- **Scalabilità dei rischi:** un attacco che prima poteva colpire un’azienda oggi può compromettere intere infrastrutture globali.
    

👉 È proprio qui che nasce la sfida moderna della **cybersecurity**: proteggere non solo i server e i PC, ma un intero ecosistema diffuso, fatto di miliardi di nodi interconnessi e difficilmente centralizzabili.

---

## 🎯 Sintesi finale della lezione

1. Abbiamo visto la **cronologia dello sviluppo** di Internet (anni ’80-’90).
    
2. Ci siamo concentrati sul **World Wide Web** e sulla triade **URL–HTTP–HTML**.
    
3. Abbiamo osservato come in 50 anni la rete sia cresciuta da pochi nodi a **20 miliardi di dispositivi**, anticipando le sfide della cybersecurity.