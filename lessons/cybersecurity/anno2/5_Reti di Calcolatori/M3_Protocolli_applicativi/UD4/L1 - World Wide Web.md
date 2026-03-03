# **Lezione 1: World Wide Web**

---

### **1. Il Web come sistema client/server**

Il **World Wide Web** è il **più grande sistema client/server al mondo**.  
Si basa sullo **scambio di messaggi** secondo il paradigma **richiesta–risposta**, dove:

- il **client** (il browser) invia una **richiesta HTTP** a un server;
    
- il **server Web** elabora la richiesta e invia una **risposta** con i contenuti richiesti.
    

Ogni interazione sul Web — dall’apertura di una pagina alla visualizzazione di un’immagine — segue esattamente questo schema.

---

### **2. Comunicazione via Web**

Quando l’utente digita un **URL** (Uniform Resource Locator) o clicca su un collegamento ipertestuale, il **browser**:

![](imgs/Pasted%20image%2020260225163022.png)

1. **Identifica** il server Web indicato nell’URL.
    
2. **Invia una richiesta** HTTP per la risorsa desiderata.
    
3. **Riceve una risposta** contenente:
    
    - il **tipo di contenuto** (HTML, immagine, PDF, file ZIP, ecc.);
        
    - e i **dati veri e propri** della pagina.
        

Il browser utilizza le informazioni sul tipo di contenuto per **decidere come visualizzarlo**.  
Se la pagina HTML contiene altri elementi (immagini, applet, file CSS o script), **il browser deve richiederli separatamente**, aprendo più richieste verso lo stesso server.

---

### **3. Esempio pratico del flusso Web**

Supponiamo che un utente clicchi su un link all’interno del browser:

1. Il **browser** identifica il **server Web** (es. `www.unimi.it`).
    
2. Il browser **stabilisce una connessione** con il server, tramite Internet.
    
3. Il **server** cerca nel proprio filesystem la **pagina richiesta** (ad esempio `index.html`).
    
4. Il server invia la pagina come **risposta HTTP**.
    
5. Il browser **interpreta e visualizza** il contenuto sullo schermo dell’utente.
    

In sostanza, il browser e il server comunicano usando **messaggi testuali** standardizzati, costruiti secondo il protocollo HTTP.

---

### **4. Il protocollo HTTP**

**HTTP (HyperText Transfer Protocol)** è un **protocollo di livello applicativo**, progettato per sistemi **ipermediali, collaborativi e distribuiti**.  
È il **protocollo principale del Web**, pensato per essere:

- **orientato agli oggetti** e **generico**;
    
- **stateless**, cioè senza memoria delle richieste precedenti;
    
- compatibile con **firewall traversal**, garantendo connettività tra qualunque coppia di nodi Internet.
    

Grazie a queste caratteristiche, HTTP è utilizzato non solo per le pagine web, ma anche per **sistemi distribuiti** e **servizi di naming**.

---

### **5. HTTP/1.0**

La prima versione diffusa del protocollo, **HTTP/1.0**, prevedeva una gestione molto semplice ma inefficiente delle connessioni:

- Ogni richiesta o risposta richiedeva **una nuova connessione TCP**.
    
- Per scaricare una singola pagina con immagini, il browser doveva aprire **più connessioni consecutive**, una per ogni elemento.
    

Questo comportamento causava:

- **sovraccarico** del server,
    
- **ritardi** di risposta,
    
- **aumento dell’overhead** di rete (cioè il tempo speso a stabilire continuamente nuove connessioni).
    

---

### **6. HTTP/1.1**

Per risolvere questi limiti, fu introdotta la versione **HTTP/1.1**, che fornisce **connessioni persistenti** di default.

In pratica:

- Una volta stabilita la connessione, **resta aperta** fino a quando non viene chiusa dal client o dal server (oppure scade un timeout).
    
- Ciò consente di **riutilizzare la stessa connessione** per più richieste consecutive.
    

Questo miglioramento comporta:

- **riduzione del numero di connessioni** totali;
    
- **minore overhead**;
    
- possibilità di inviare **più richieste in parallelo** senza aspettare le risposte (tecnica detta _pipelining_).
    

Esempio:  
il browser può chiedere **tutte le immagini di una pagina HTML contemporaneamente**, velocizzando il caricamento.

---

### **7. HTTP come protocollo stateless**

HTTP è un protocollo **stateless**, cioè **non mantiene memoria** delle interazioni precedenti.  
Due richieste identiche, inviate una dopo l’altra, vengono trattate **come indipendenti**.

Questo approccio è efficiente per le risorse statiche, ma diventa limitante per le **applicazioni dinamiche**, come l’e-commerce.  
Infatti, siti come **Amazon** devono ricordare:

- il nome dell’utente;
    
- gli acquisti passati;
    
- la carta di credito e le preferenze.
    

Per simulare una **sessione stateful** (cioè persistente), vengono introdotti **meccanismi esterni** al protocollo, come:

- **cookie** salvati lato client,
    
- **sessioni lato server** con identificatori univoci.
    

---

### **8. Sintesi finale**

|Versione|Caratteristiche principali|Limiti o vantaggi|
|---|---|---|
|**HTTP/1.0**|Nuova connessione per ogni richiesta|Overhead elevato|
|**HTTP/1.1**|Connessioni persistenti, pipelining|Maggiore efficienza|
|**HTTP (generico)**|Stateless, compatibile con firewall, orientato agli oggetti|Richiede meccanismi aggiuntivi per la gestione di sessioni utente|

---

### **9. Conclusione**

Il **World Wide Web** è costruito interamente su **HTTP**, un protocollo semplice, scalabile e indipendente dallo stato.  
La sua architettura client/server e il modello richiesta–risposta rendono possibile ogni interazione tra browser e server, dalle pagine statiche ai complessi servizi cloud di oggi.

Con l’introduzione di versioni più avanzate (come **HTTP/1.1**, poi **HTTP/2** e **HTTP/3**), il Web è passato da un sistema di documenti collegati a una vera **piattaforma distribuita globale**, capace di supportare applicazioni interattive, transazioni e comunicazioni in tempo reale.