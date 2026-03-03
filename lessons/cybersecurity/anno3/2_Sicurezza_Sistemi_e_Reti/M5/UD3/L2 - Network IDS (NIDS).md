## **Lezione 2: Network IDS (NIDS)**

### **1. Introduzione**

Un **Network Intrusion Detection System (NIDS)** è un sistema che monitora **il traffico di rete in tempo reale**, analizzando pacchetti e flussi per **individuare comportamenti sospetti o attacchi**.  
A differenza dell’**Host-Based IDS (HIDS)**, che opera sul singolo dispositivo, il NIDS osserva l’intera rete, tipicamente da un punto strategico come una **DMZ** o una porta mirror dello switch.

> In breve, il NIDS è “l’occhio della rete”: ascolta tutto ciò che passa e avvisa quando qualcosa non torna.

---

## **2. Componenti di un NIDS**

|Componente|Funzione principale|
|---|---|
|**Sensor**|Controlla il traffico e i log, individua pattern sospetti, genera eventi di sicurezza e può interagire con il sistema (es. ACL, TCP reset).|
|**Director**|Coordina i sensori, gestisce il _security database_ e centralizza gli allarmi.|
|**IDS Message System**|Gestisce la comunicazione sicura e affidabile tra i vari componenti del sistema IDS.|

> I sensori rappresentano gli “occhi” del sistema, il Director il “cervello” che li coordina, e il Message System il “nervo” che li collega.

---

## **3. Architettura tipica di un NIDS**

Il NIDS è normalmente collocato in **una zona intermedia di rete (DMZ)**, con una struttura a livelli:

```
Rete Esterna → DMZ → Rete Interna
             ↓
         IDS Sensors
             ↓
          IDS Director
```

- **Net Sensors**: catturano il traffico di rete (sniffing).
    
- **Host Sensors**: analizzano i log locali dei sistemi della DMZ o della rete interna.
    
- **Director**: riceve e correla gli eventi, generando report o allarmi.
    

> Collocare i sensori in più punti della rete consente di **confrontare il traffico esterno, intermedio e interno**, migliorando la capacità di rilevazione.

---

## **4. Interoperabilità tra IDS**

Un problema classico è la **mancanza di standard** nei formati di firma e allarme.  
L’interoperabilità è fondamentale perché:

- gli attacchi coinvolgono **più organizzazioni**;
    
- spesso sono **rilevati da strumenti diversi**.
    

### **Standard principali**

|Tipo di formato|Standard / esempio|Descrizione|
|---|---|---|
|**Signature format**|_Snort format_|Schema di regole de-facto più diffuso.|
|**Alert message**|_IDMEF_, _IDXP_, _IODEF_ (IETF)|Standard aperti per rappresentare e scambiare messaggi di allarme.|
|**Vendor format**|_SDEE_ (Cisco, ISS, SourceFire)|Standard proprietario basato su XML/HTTP.|

---

## **5. Flusso dei dati in un NIDS**

Il processo di rilevazione segue una catena logica di trasformazioni:

```
Data Source → Sensor → Analyzer → Manager → Operator → Administrator
```

Ogni passaggio genera un diverso tipo di informazione:

|Fase|Tipo di informazione|
|---|---|
|**Activity**|Il comportamento osservato (es. pacchetti, log).|
|**Event**|L’evento sospetto rilevato.|
|**Alert**|L’allarme generato dal sistema IDS.|
|**Notification**|L’avviso inviato all’operatore.|
|**Response**|L’azione (automatica o manuale) conseguente all’allarme.|

---

## **6. IDMEF e IDXP**

### **IDMEF – Intrusion Detection Message Exchange Format**

- Standard IETF per rappresentare gli **alert** in formato XML.
    
- **Indipendente dal protocollo** (IPv4 o IPv6).
    
- Supporta **localizzazione e internazionalizzazione**.
    
- Consente **aggregazione e filtraggio** sul _manager_.
    

### **IDXP – Intrusion Detection eXchange Protocol**

- Basato su **BEEP** (RFC 3080).
    
- Permette lo scambio di profili di sicurezza _end-to-end_.
    
- Il profilo di sicurezza base è **TLS** (Transport Layer Security).
    

---

### **Esempio di messaggio IDMEF**

```xml
<idmef:IDMEF-Message xmlns:idmef="http://iana.org/idmef" version="1.0">
  <idmef:Alert messageid="abc123456789">
    <idmef:Analyzer analyzerid="bc-sensor01">
      <idmef:Node category="dns">
        <idmef:name>sensor.example.com</idmef:name>
      </idmef:Node>
    </idmef:Analyzer>
    <idmef:CreateTime>2025-10-21T12:00:00Z</idmef:CreateTime>
    <idmef:Source ident="a1a2" spoofed="yes">
      <idmef:Address category="ipv4-addr">
        <idmef:address>192.0.2.200</idmef:address>
      </idmef:Address>
    </idmef:Source>
    <idmef:Classification text="Ping-of-death detected"/>
  </idmef:Alert>
</idmef:IDMEF-Message>
```

> Il formato XML consente interoperabilità tra strumenti diversi e analisi automatizzata degli alert.

---

## **7. SDEE – Secure Device Event Exchange**

- Standard **proprietario Cisco**, basato su **Web Services**.
    
- I messaggi sono in formato **XML**, trasmessi tramite **HTTP o HTTPS**.
    
- Gestito da **ICSA Labs**, non open-standard.
    

> È molto usato in apparati Cisco, ma limitato fuori dall’ecosistema del produttore.

---

## **8. IODEF – Incident Object Description and Exchange Format**

- Evoluzione del formato IDMEF, sviluppato dall’IETF.
    
- Utilizzato per **scambio di informazioni sugli incidenti** tra enti diversi (CERT, SOC, aziende).
    
- Supporta **statistiche, correlazione di eventi e gestione del rischio**.
    

> Se IDMEF descrive l’allarme, IODEF ne descrive l’intero “contesto di incidente”.

---

## **9. Intrusion Prevention System (IPS)**

Un **IPS** (Intrusion Prevention System) rappresenta la **naturale estensione di un IDS**:  
unisce la capacità di rilevazione (IDS) con quella di **reazione automatica** tipica di un **firewall dinamico distribuito**.

### **Caratteristiche principali**

- Tecnologia, non singolo prodotto.
    
- Opera **in linea** (inline), non in ascolto passivo.
    
- Impatto diretto sulla rete: può **bloccare traffico legittimo** in caso di falsi positivi.
    

> L’IPS è un IDS “con i muscoli”: può agire, ma un errore costa caro.

---

## **10. Honey Pot e Honey Net**

Un **Honey Pot** è un sistema deliberatamente vulnerabile, configurato per **attirare gli attaccanti** e studiarne il comportamento.

### **Architettura d’esempio**

```
Rete esterna → DMZ → Rete interna
                     ↓
                Honey Pot (esterni)
                Honey Pot (interni)
```

- **Honey Pot esterno:** attira attacchi provenienti da Internet.
    
- **Honey Pot interno:** serve a identificare attività malevole dentro la LAN (insider threat).
    

> I dati raccolti vengono analizzati dagli IDS per aggiornare regole e firme.

---

## **11. Strumenti NIDS – Snort**

### **Caratteristiche generali**

- Software open-source, gratuito e ampiamente diffuso.
    
- Può funzionare come:
    
    - **Sniffer**,
        
    - **Packet Logger**,
        
    - **Network IDS**,
        
    - **Inline IPS** (in collaborazione con IPTables).
        
- Utilizza librerie **Libpcap** per catturare pacchetti.
    

### **File principali**

- `snort.conf` → configurazione e variabili (es. `HOME_NET`, `DNS_SERVERS`).
    
- `classification.conf` → priorità e categorie degli allarmi.
    
- `*.rules` → regole operative di detection.
    

---

## **12. Struttura di una regola Snort**

### **Formato generale**

```
funzione protocollo sorgente sport -> destinazione dport (opzione1; opzione2; …)
```

|Campo|Significato|
|---|---|
|**funzione**|azione da eseguire (`alert`, `log`, `pass`, `activate`, `dynamic`)|
|**protocollo**|`tcp`, `udp`, `icmp`, `ip`|
|**sorgente/destinazione**|indirizzi e porte|
|**opzioni**|condizioni aggiuntive e messaggi di alert|

---

### **Esempi**

```bash
alert tcp any any -> 193.205.161.191/23 (msg:"Tentativo di connessione Telnet"; content:"Last login";)
alert tcp !192.168.1.0/24 any -> 192.169.1.0/24 111 (content:"|00 01 86 a5|"; msg:"external mountd access";)
alert tcp $EXTERNAL_NET any -> $HTTP_SERVERS 80 (msg:"WEB-IIS cmd.exe access"; content:"cmd.exe"; nocase;)
```

---

## **13. Modalità operative di Snort**

|Modalità|Descrizione|
|---|---|
|**Sniffer Mode**|Visualizza in tempo reale i pacchetti che transitano su un’interfaccia (`snort -v -i eth0`).|
|**Packet Logger Mode**|Registra i pacchetti su disco per analisi successive (`snort -dev -i eth0 -l ./log`).|
|**IDS Mode**|Analizza il traffico e genera allarmi secondo le regole (`snort -c snort.conf`).|
|**Inline Mode**|Lavora insieme a IPTables e può bloccare pacchetti sospetti (`QUEUE`, `drop`, `reject`, `sdrop`).|

---

## **14. Snort in modalità Inline (IPS)**

Esempio di regola che ordina il blocco del traffico HTTP:

```bash
drop tcp any any -> any 80 (classtype:attempted-user; msg:"Port 80 connection initiated";)
```

Azioni principali:

- **drop** → scarta pacchetto;
    
- **reject** → scarta e invia _tcp_reset_;
    
- **sdrop** → scarta senza loggare.
    

> In questa modalità, Snort collabora con IPTables per realizzare un vero e proprio sistema IPS.

---

## **15. Strumenti di analisi e GUI**

|Strumento|Descrizione|
|---|---|
|**BASE (Basic Analysis and Security Engine)**|Interfaccia web per visualizzare e gestire gli alert (deriva da ACID).|
|**SNIPS**|Progetto di ricerca per correlare alert e identificare malware o attaccanti.|
|**Snorby**|Interfaccia moderna basata su Ruby on Rails.|

> Molti progetti di front-end per Snort sono nati e scomparsi nel tempo: l’ecosistema è molto dinamico.

---

## **16. Altri strumenti di monitoraggio**

### **Argus**

- Non è un IDS in senso stretto.
    
- Registra i flussi di connessione (non i singoli pacchetti).
    
- Fornisce statistiche di rete utili per analisi forense o capacity planning.
    
- Dati esportabili in formato binario, leggibili con comandi come `ratop` o `ra`.
    

### **Suricata**

- Evoluzione moderna di Snort.
    
- Supporta IDS, IPS, _Network Security Monitoring_ (NSM) e analisi offline di file `pcap`.
    
- Utilizza architettura multithread e supporta direttamente il formato Snort per le regole.
    

---

## **17. Ruolo pratico degli IDS oggi**

Gli IDS, nella pratica operativa, risultano:

- **essenziali nel post-attacco**, per analizzare vulnerabilità e pattern;
    
- utili per **correlare host e flussi coinvolti** in incidenti complessi;
    
- strumenti di **intelligence di rete**, più che di protezione immediata.
    

> In una timeline di attacco, l’IDS è efficace nella **fase post-intrusione**, quando serve capire cosa è successo e come rafforzare le difese.

---

### **Schema riassuntivo**

```
Pre-Attacco → Attacco → Post-Attacco
   ↓             ↓             ↓
   Ricognizione  Intrusione    Analisi e correlazione (IDS)
```

---

### **18. Conclusione**

Il **Network IDS** è un elemento chiave nella sicurezza moderna:  
unisce analisi del traffico, standard di interoperabilità e capacità di ispezione profonda.  
Con strumenti come **Snort** e **Suricata**, la difesa di rete diventa **osservabile, misurabile e automatizzabile**.

> In un’architettura multilivello, l’IDS osserva, l’IPS reagisce, e insieme forniscono la consapevolezza situazionale indispensabile alla sicurezza.


---
