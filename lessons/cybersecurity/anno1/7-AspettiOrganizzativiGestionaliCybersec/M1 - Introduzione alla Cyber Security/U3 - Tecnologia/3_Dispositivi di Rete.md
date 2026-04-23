In questa lezione si passa dalla teoria dei protocolli e dei modelli logici all’analisi dei **dispositivi concreti** che compongono le reti.  
Con “dispositivo di rete” si intende un **apparato attivo**, quindi alimentato da corrente elettrica e in grado di svolgere funzioni di elaborazione. Non si tratta solo di collegare fili, ma di **ottimizzare la rete** in termini di:

- **efficienza**: ridurre ritardi e sprechi di banda,
    
- **affidabilità**: garantire che i dati arrivino correttamente a destinazione,
    
- **scalabilità**: permettere alla rete di crescere senza collassare.
    

Ogni dispositivo si colloca in uno o più **livelli dello stack ISO/OSI**, anche se la tendenza moderna è avere dispositivi multifunzionali, che inglobano funzioni tipiche di altri apparati.  

Questo fenomeno si chiama CONVERGENZA FUNZIONALE:
![Pasted image 20250909234927.png](../../imgs/Pasted%20image%2020250909234927.png)

- gli **switch** hanno sostituito gli hub,
    
- i **router** moderni fanno anche da **gateway**,
    
- molti **router di fascia alta** incorporano già le funzioni di **firewall**.
    

Questa convergenza riduce la varietà di apparati e favorisce la standardizzazione: poche famiglie di dispositivi, sempre più potenti, dominano il mercato.

---

## Livelli ISO/OSI e Dispositivi

Il modello ISO/OSI ha 7 livelli:

1. Fisico
    
2. Collegamento
    
3. Rete
    
4. Trasporto
    
5. Sessione
    
6. Presentazione
    
7. Applicazione
    

Ciascun dispositivo di rete può essere “mappato” su un livello, in base a quali funzioni svolge:

- **Hub** → livello fisico (1)
    
- **Switch** → livello collegamento (2), ma i modelli avanzati arrivano fino al 3-4-7
    
- **Router** → livello rete (3)
    
- **Gateway** → livello applicazione (7), perché traducono protocolli
    
- **Firewall** → tipicamente livelli 3 e 4, ma alcuni arrivano fino al 7
    

L’idea è che ogni apparato contribuisca, dal basso verso l’alto, a rendere la comunicazione più “intelligente” e più sicura.


---
Partiamo con il più semplice (e ormai storico) degli apparati: **l’Hub**.

---

## L’Hub

![Pasted image 20250909235032.png](../../imgs/Pasted%20image%2020250909235032.png)

Un **Hub** è un dispositivo che funge da **ripetitore multiporta**:

- prende un segnale in ingresso da una porta,
    
- lo **rigenera** (cioè lo amplifica, corregge il segnale elettrico degradato),
    
- e lo **ritrasmette identico su tutte le altre porte**.
    

In pratica, se colleghi più computer all’hub, ogni volta che uno manda un pacchetto, questo viene copiato e inviato a **tutti gli altri**.

### Caratteristiche principali

- Si colloca al **Livello 1 (Fisico)** del modello ISO/OSI.
    
- Non interpreta i dati, non legge indirizzi MAC o IP: si limita a “sparare” il segnale ovunque.
    
- Tutti i dispositivi connessi all’hub sono in un **unico dominio di collisione**, cioè i pacchetti possono scontrarsi se inviati contemporaneamente.
    
- È, in sostanza, un “**megafono**” che diffonde il segnale a tutti, senza distinzione.
    

### Limiti

- **Basso livello di efficienza**: genera molto traffico inutile, perché ogni nodo riceve anche pacchetti che non lo riguardano.
    
- **Alto rischio di collisioni**: la rete diventa lenta e poco scalabile.
    
- **Obsolescenza**: oggi gli hub sono praticamente spariti, sostituiti dagli **switch**, che svolgono lo stesso ruolo in modo intelligente.
    

### Esempio pratico

Immagina una stanza con 5 persone e un hub come un altoparlante centrale.  
Se uno parla, l’hub “ripete la voce” e la trasmette a tutti gli altri, anche a chi non era interessato.  
Con più persone che parlano insieme, il segnale diventa caotico e incomprensibile (collisione).


---
Adesso analizziamo lo **Switch**, il vero protagonista delle reti locali moderne.

---

## Lo Switch

![Pasted image 20250909235215.png](../../imgs/Pasted%20image%2020250909235215.png)

Lo **switch** è un dispositivo **intelligente**, dotato di **CPU** e di memoria interna.  
A differenza dell’hub (che replica il segnale a tutti), lo switch **invia i dati solo alla porta corretta**, riducendo traffico e collisioni.

### Collocazione nello stack

- Opera principalmente al **Livello 2 (Data Link)** del modello ISO/OSI.
    
- Può però gestire funzioni anche ai livelli **3 (Rete)**, **4 (Trasporto)** e persino **7 (Applicazione)** negli switch più avanzati (chiamati multilayer switch).
    

### Funzionamento

- Lo switch legge gli **indirizzi MAC** nei frame ricevuti.
    
- Mantiene in memoria una **tabella di commutazione** che associa ogni indirizzo MAC a una specifica porta.
    
- Quando riceve un frame, lo inoltra **solo alla porta del destinatario**, evitando che tutto il traffico sia condiviso.
    

Risultato:

- maggiore **efficienza** (niente pacchetti inutili per chi non c’entra),
    
- meno **collisioni**,
    
- più **banda disponibile** per ciascun nodo.
    

---

## Segmentazione con VLAN

Lo switch permette anche di **segmentare il dominio di broadcast** attraverso le **VLAN (Virtual Local Area Network)**.  
In pratica:

- Si crea una rete logica separata **all’interno dello stesso switch fisico**,
    
- Ogni VLAN funziona come se fosse una rete distinta, anche se le macchine sono fisicamente collegate allo stesso apparato.
    

Esempio: in un’azienda si possono separare i PC degli uffici amministrativi da quelli della produzione pur usando lo stesso hardware.

---

## Tipologie di Switch

### 1. In base alla **gestione**

- **Unmanaged Switch** (non gestito):
    
    - plug-and-play, nessuna configurazione, economico.
        
    - È l’evoluzione diretta degli hub.
        
- **Managed Switch** (gestito):
    
    - configurabile via interfaccia web o CLI, con funzioni avanzate di sicurezza, monitoraggio e VLAN.
        
    - Nelle versioni enterprise hanno persino un **sistema operativo interno**.
        

### 2. In base alla **modalità di instradamento**

- **Cut-through**:
    
    - legge solo l’indirizzo MAC di destinazione e inizia subito l’invio,
        
    - **bassa latenza**, ma non controlla errori.
        
- **Store-and-forward**:
    
    - legge l’intero frame, calcola il **CRC** (controllo errori),
        
    - invia solo se il pacchetto è integro,
        
    - più sicuro, ma con latenza maggiore.
        
- **Fragment-free**:
    
    - compromesso: legge i primi 64 byte, intercetta errori più comuni,
        
    - equilibrio tra velocità e affidabilità.
        

---

### Esempio pratico

Immagina una segretaria in un ufficio (lo switch).  
Quando arriva una lettera (frame), controlla l’indirizzo e la consegna **solo all’ufficio giusto**.  
Non mette fotocopie in tutte le stanze come faceva l’hub!


---
## Il Gateway

![Pasted image 20250909235308.png](../../imgs/Pasted%20image%2020250909235308.png)
Il **gateway** è un dispositivo che funge da **convertitore di protocolli di rete**.  
Il suo scopo è permettere a due reti diverse, basate su protocolli differenti, di **comunicare tra loro**.

### Collocazione nello stack

- Opera tipicamente al **Livello 7 (Applicazione)** del modello ISO/OSI.
    
- Infatti non si limita a leggere indirizzi, ma deve **interpretare i protocolli** e tradurli.
    

### Funzionamento

- Quando due reti parlano linguaggi diversi (per esempio IP ↔ IPX, o IPv4 ↔ IPv6), il gateway traduce i pacchetti da un formato all’altro.
    
- Non a caso viene definito un “**traduttore universale**” per la rete.
    

### Funzioni aggiuntive

Oggi i gateway sono spesso **integrati nei router** e inglobano altre funzioni tipiche di livello applicativo, come:

- **Proxy**
    
- **DNS**
    
- **Firewall**
    
- **NAT (Network Address Translation)**
    

#### Il NAT

Il NAT è un meccanismo che permette di **modificare gli indirizzi IP** dei pacchetti in transito.  
Esempio classico:

- All’interno della rete locale i PC hanno indirizzi privati (es. 192.168.1.x).
    
- Quando escono su Internet, il gateway traduce tutti questi indirizzi in **un unico indirizzo pubblico**.
    
- Allo stesso modo, quando arriva una risposta da fuori, il gateway la ritrasmette al PC interno giusto.
    

### Utilità del NAT

- **Risparmio di indirizzi IP pubblici**.
    
- **Maggiore sicurezza**, perché gli host interni non sono esposti direttamente su Internet.
    

---

### Esempio pratico

Immagina un gruppo di persone che parlano lingue diverse.  
Il gateway è l’**interprete** che ascolta, traduce e rimanda il messaggio nella lingua dell’altro gruppo.


---
## Il Router

![Pasted image 20250909235356.png](../../imgs/Pasted%20image%2020250909235356.png)

Il **router** è il dispositivo incaricato di **instradare i pacchetti** tra reti diverse.  
Il suo compito principale è quello di **decidere il percorso migliore** che i dati devono seguire per arrivare a destinazione.

### Collocazione nello stack

- Opera principalmente al **Livello 3 (Rete)** del modello ISO/OSI.
    
- In particolare, gestisce i pacchetti IP e usa le **tabelle di routing** per scegliere il cammino.
    
- Alcuni router avanzati implementano anche funzioni di livelli superiori (es. firewall integrato, NAT, VPN).
    

### Funzionamento

- Riceve un pacchetto, legge l’**indirizzo IP di destinazione**,
    
- consulta la propria **tabella di routing**,
    
- inoltra il pacchetto verso la porta corretta (che punta alla prossima rete o al prossimo router sul percorso).
    

Se la destinazione è **nella stessa rete locale**, consegna direttamente.  
Se la destinazione è **su un’altra rete**, “instruisce” il pacchetto verso la giusta direzione.

### Caratteristiche

- Può collegare reti diverse anche con **mezzi trasmissivi differenti**:
    
    - fibra ↔ rame,
        
    - Wi-Fi ↔ doppino telefonico,
        
    - LAN interna ↔ Internet.
        
- Ogni router è **consapevole solo della sua tabella**: non conosce l’intera rete mondiale, ma sa come arrivare ad alcuni nodi o ad altri router vicini.
    

---

### Simboli grafici

Nelle rappresentazioni schematiche si usa spesso:

- un cerchio con due frecce incrociate = router,
    
- router con piccolo “scudo” o “fiamma” = router con firewall integrato.
    

---

### Esempio pratico

Immagina il router come un **vigile urbano** a un incrocio:

- ogni auto (pacchetto) arriva con la sua destinazione (indirizzo IP),
    
- il vigile guarda la mappa (tabella di routing)
    
- e instrada il veicolo nella direzione corretta.


---
## Il Firewall

![Pasted image 20250909235529.png](../../imgs/Pasted%20image%2020250909235529.png)

Il **firewall** è un dispositivo (hardware o software) che ha lo scopo di **controllare e filtrare il traffico di rete**.  
Il suo nome deriva dall’inglese “muro tagliafuoco”: proprio come una barriera fisica limita la propagazione di un incendio, il firewall blocca la diffusione di traffico dannoso o indesiderato.

### Collocazione nello stack

- In genere lavora tra i livelli **3 (Rete)** e **4 (Trasporto)**,
    
- ma alcuni firewall più sofisticati arrivano a gestire persino funzioni del **livello 7 (Applicazione)**, dove analizzano il contenuto dei pacchetti.
    

---

## Funzionamento

Il firewall si comporta come un **filtro**:

- riceve il traffico in entrata e in uscita,
    
- lo confronta con una **tabella di regole** predefinite,
    
- decide se **consentire** o **bloccare** la comunicazione.
    

Le regole possono basarsi su:

- indirizzo **IP sorgente**,
    
- indirizzo **IP di destinazione**,
    
- **porta** (che indica il servizio: es. 80 per HTTP, 443 per HTTPS, 25 per SMTP),
    
- tipo di protocollo (TCP, UDP, ecc.).
    

---

## Politiche di configurazione

Esistono due approcci fondamentali:

1. **Default-deny**
    
    - tutto è bloccato, salvo ciò che è **esplicitamente permesso**;
        
    - più sicuro, usato nelle aziende.
        
2. **Default-allow**
    
    - tutto è permesso, salvo ciò che è **esplicitamente bloccato**;
        
    - più semplice, ma meno sicuro.
        

---

## Tipi di Firewall

- **Firewall hardware**: appliance dedicate, molto performanti.
    
- **Firewall software**: applicazioni installate su PC o server.
    
- **Firewall di nuova generazione (NGFW)**: integrano intrusion detection, analisi dei pacchetti a livello applicativo, VPN, ecc.
    

---

## Esempio pratico

Immagina il firewall come un **buttafuori davanti a un locale**:

- controlla chi entra e chi esce,
    
- si basa su una lista (regole),
    
- decide chi far passare e chi respingere.


---
## Riepilogo della Lezione 3

- **Hub**: ripetitore multiporta, obsoleto, livello fisico.
    
- **Switch**: intelligente, usa MAC address, segmenta con VLAN, vari tipi di instradamento.
    
- **Gateway**: traduttore di protocolli, opera al livello applicativo, gestisce NAT e altri servizi.
    
- **Router**: cuore del routing, livello rete, collega reti diverse.
    
- **Firewall**: guardiano della sicurezza, filtra traffico in base a regole.
