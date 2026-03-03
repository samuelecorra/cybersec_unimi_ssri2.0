## **Lezione 5: Altri Attacchi a SSL/TLS**

### **1. Introduzione**

Nonostante l’evoluzione del protocollo TLS abbia corretto molte vulnerabilità delle versioni precedenti di SSL, alcuni **attacchi pratici** continuano a colpire le **implementazioni reali**, non tanto per errori nel protocollo in sé, ma per **cattiva configurazione o integrazione** con altri protocolli di rete come **HTTP**.

In questa lezione analizziamo:

1. Gli attacchi _Man-in-the-Middle_ con **downgrade del protocollo**,
    
2. Gli attacchi **Denial of Service** basati su TLS,
    
3. Gli attacchi **misti HTTP/HTTPS** come **SSLStrip**,
    
4. Le principali **contromisure moderne** (es. HSTS).
    

---

### **2. Attacco Man-in-the-Middle (MITM) con Downgrade del Protocollo**

#### **a. Scenario generale**

Un **attaccante** che controlla il traffico di rete (ad esempio in una rete Wi-Fi pubblica o LAN locale) può **intercettare e manipolare** i pacchetti scambiati tra un client e un gateway.  
Per farlo, spesso sfrutta una tecnica chiamata **ARP spoofing**, che consiste nel falsificare le tabelle ARP per dirottare il traffico attraverso la propria macchina.

#### **b. Meccanismo dell’attacco**

L’attacco, noto anche come **TLS Protocol Downgrade Attack**, funziona così:

1. Il **client** invia un messaggio `ClientHello` al server, proponendo la versione più recente di SSL/TLS (es. TLS 1.3).
    
2. L’attaccante **intercetta e scarta** questo pacchetto.
    
3. L’attaccante invia al server un pacchetto TCP con flag `FIN, ACK`, inducendolo a **chiudere la connessione**.
    
4. Il client, credendo che la connessione sia fallita, **ritenta** la connessione con una **versione inferiore del protocollo** (es. TLS 1.0 o SSL 3.0).
    
5. Il server accetta, e la comunicazione avviene ora con **standard più deboli**, vulnerabili ad attacchi di decrittazione.
    

#### **c. Stato attuale**

Le versioni moderne di TLS prevengono la modifica diretta dei pacchetti di handshake grazie all’uso di **hash e firme** nella fase finale (`Finished`).  
Tuttavia, l’attacco di downgrade può ancora avvenire **inducendo errori di connessione controllati**: non si manipolano i pacchetti, ma se ne **blocca o ritarda l’invio** per forzare il client a retrocedere di versione.

---

### **3. Attacco Denial of Service (THC-SSL-DOS)**

#### **a. Principio dell’attacco**

Stabilire una connessione SSL/TLS è **computazionalmente molto più costoso per il server** che per il client.

- Ogni handshake implica operazioni di crittografia asimmetrica (RSA o Diffie-Hellman),
    
- Il carico CPU sul server può essere **fino a 15 volte superiore** rispetto a quello del client.
    

Questa **asimmetria di potenza** viene sfruttata da strumenti come **THC-SSL-DOS**, che simulano centinaia di connessioni TLS in parallelo, sovraccaricando il server.

#### **b. Esempio pratico in Bash**

```bash
# THC-SSL-DOS example script
thc-ssl-dosit() { 
  while :; do 
    (while :; do echo R; done) | openssl s_client -connect 127.0.0.1:443 2>/dev/null; 
  done 
}
for x in `seq 1 100`; do thc-ssl-dosit & done
```

Questo script apre centinaia di connessioni simultanee TLS verso la porta 443, saturando la CPU del server.

#### **c. Variante su Kali Linux**

Su Kali Linux, THC-SSL-DOS può essere usato per **attacchi DoS** mirati sfruttando la **rinegoziazione TLS**, cioè la possibilità (nelle vecchie versioni) di riaprire un handshake all’interno di una connessione già cifrata.  
Ogni rinegoziazione forza il server a rieseguire il processo crittografico, amplificando il carico.

---

### **4. HTTPS come modello sicuro**

#### **a. HTTPS: un tunnel end-to-end**

HTTPS (HTTP over TLS) è progettato per fornire **sicurezza end-to-end** tra browser e server web, garantendo:

- **Cifratura dei dati**
    
- **Autenticazione** del server (e talvolta del client)
    
- **Integrità** dei messaggi
    

Il canale TLS funge da **tunnel crittografico** tra il browser e il server.

```
Browser  ⇄  Internet/Proxy  ⇄  HTTPS Server
       ←────── HTTPS Tunnel ──────→
```

Tuttavia, molte vulnerabilità emergono quando **HTTPS** e **HTTP** vengono **combinati in modo improprio**.

---

### **5. Contenuto misto (Mixed Content)**

#### **a. Cos’è**

Il _mixed content_ si verifica quando una pagina servita su **HTTPS** include **risorse caricate via HTTP**, come immagini, script o file Flash.

#### **b. Esempio**

```html
<script src="http://www.site.com/script.js"></script>
```

Se il file viene caricato da HTTP, un **attaccante di rete** può intercettarlo e modificarlo, inserendo **codice malevolo** nella pagina sicura.

#### **c. Effetti**

- Il browser mostra un **lucchetto rotto o con avviso** (“mixed content”).
    
- Alcuni browser (es. vecchie versioni di IE o Firefox) non mostrano alcun avviso.
    
- Flash e plugin simili non attivano controlli di sicurezza, aprendo ulteriori falle.
    

#### **d. Mitigazione**

È consigliabile usare riferimenti **relativi al protocollo**, ad esempio:

```html
<script src="//www.site.com/script.js"></script>
```

In questo modo, se la pagina è caricata su HTTPS, anche lo script verrà richiesto in HTTPS.

---

### **6. Attacco SSLStrip**

#### **a. Funzionamento**

L’attacco **SSLStrip** mira a **rimuovere la protezione HTTPS** durante la navigazione, convertendo le connessioni cifrate in semplici HTTP, senza che l’utente se ne accorga.

**Fasi:**

1. L’attaccante si interpone nel traffico (MITM tramite **ARP spoofing**).
    
2. Tutte le richieste HTTPS del client vengono **convertite in HTTP** verso il server.
    
3. L’attaccante stabilisce una connessione HTTPS con il server reale, ma presenta al client solo HTTP.
    
4. I dati scambiati (inclusi username e password) vengono **registrati in chiaro**.
    

#### **b. Struttura del flusso**

```
Client  →  Attaccante (HTTP)
Attaccante  →  Server (HTTPS)
```

L’utente crede di essere su un sito sicuro, ma in realtà la protezione è stata rimossa “a monte”.

#### **c. Strumenti usati**

- **ARP poisoning** → per impersonare il gateway.
    
- **SSLStrip** → per riscrivere dinamicamente le richieste HTTPS in HTTP.
    

#### **d. Esempi pratici**

SSLStrip riscrive:

- `<a href="https://...">` in `<a href="http://...">`
    
- `Location: https://...` in `Location: http://...`
    
- `<form action="https://...">` in `<form action="http://...">`
    

In questo modo, anche le richieste POST con credenziali vengono intercettate.

---

### **7. Contromisure: HTTP Strict Transport Security (HSTS)**

Per prevenire attacchi come SSLStrip, dal 2012 è stato introdotto **HSTS (HTTP Strict Transport Security)**.

#### **a. Funzione**

HSTS impone ai browser di **utilizzare sempre HTTPS** per un determinato dominio, anche se l’utente o un link tenta di forzare HTTP.

#### **b. Funzionamento**

1. Il server invia un’intestazione speciale:
    
    ```
    Strict-Transport-Security: max-age=31536000; includeSubDomains
    ```
    
2. Il browser memorizza questa policy per la durata specificata (es. 1 anno).
    
3. Da quel momento, **rifiuta qualunque connessione HTTP** a quel dominio.
    

#### **c. Requisiti**

- La policy deve essere **supportata da entrambi** (browser e server).
    
- Non tutti i browser più vecchi sono compatibili.
    

#### **d. Adozione**

- Implementata da grandi siti come **Gmail, PayPal, Twitter**.
    
- In passato **Facebook (specie la versione mobile)** era vulnerabile perché non applicava HSTS su tutte le versioni.
    

---

### **8. Conclusione**

Gli attacchi moderni a SSL/TLS non mirano più a bucare la **crittografia matematica**, ma a sfruttare:

- **Errori di implementazione**,
    
- **Cattive configurazioni**,
    
- o **combinazioni insicure tra protocolli**.
    

Le contromisure efficaci includono:

- **Aggiornare sempre a TLS 1.3**,
    
- **Disabilitare SSL e TLS obsoleti**,
    
- **Applicare HSTS** e rimuovere contenuti misti,
    
- **Configurare correttamente ARP e DNS** nelle reti locali.
    

> La sicurezza del trasporto non è solo un fatto di crittografia, ma di **coerenza di sistema**: ogni singolo elemento, dal browser al router, deve mantenere il canale veramente “end-to-end”.
