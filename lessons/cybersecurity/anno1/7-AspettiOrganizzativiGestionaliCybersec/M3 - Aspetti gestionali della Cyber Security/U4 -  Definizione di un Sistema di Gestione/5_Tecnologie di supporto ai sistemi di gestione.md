In questa lezione vedremo alcune delle tecnologie di protezione informatica che sono di supporto ai sistemi di gestione della sicurezza informatica.

---
# IDS/IPS (Intrusion Detection/Prevention Systems)

### Definizione

Un **IDS/IPS** è un sistema hardware o software che **monitora la rete e i sistemi** per:

- rilevare attività dannose,
    
- identificare violazioni delle policy di sicurezza,
    
- inviare alert agli amministratori o a piattaforme centralizzate (**SIEM**, Security Information and Event Management).
    

Differenza:

- **IDS (Intrusion Detection System):** rileva l’attacco e segnala.
    
- **IPS (Intrusion Prevention System):** rileva e agisce subito per bloccarlo.
    

---

### IDS/IPS e Firewall

- Il **Firewall** filtra i pacchetti con regole semplici (IP, porte, orari, protocolli). Lavora su livelli bassi dello stack OSI.
    
- L’**IDS/IPS** si posiziona “a valle” del firewall: analizza il contenuto dei pacchetti e i comportamenti.
    
- **Limite firewall:** non intercetta attacchi generati dall’interno della LAN o comportamenti anomali non previsti dalle sue regole.
    
- **Punto di forza IDS:** può rilevare anomalie interne e pattern sospetti, integrando la protezione.
    

---

### Metodologie di rilevazione

1. **Misuse Detection (Signature-based):**
    
    - Analizza “firme digitali” o pattern di attacchi noti.
        
    - Vantaggi: veloce, affidabile, pochi falsi positivi.
        
    - Limiti: riconosce solo minacce conosciute → inefficace contro attacchi zero-day.
        
2. **Anomaly Detection (Behaviour-based):**
    
    - Confronta traffico e comportamenti con un profilo “normale” (baseline).
        
    - Può evolversi con **AI e Machine Learning**.
        
    - Vantaggi: individua minacce nuove o sconosciute.
        
    - Limiti: molti falsi positivi, monitoraggio complesso.
        

---

### Analisi del grafico

![Pasted image 20250913155828.png](../../imgs/Pasted%20image%2020250913155828.png)

- **Parte sinistra:**
    
    - Internet → IDS → Forensic Analysis / Alert Administrator → LAN.
        
    - Mostra il ruolo dell’IDS come filtro intelligente che **analizza i pacchetti** e manda allarmi o analisi forensi.
        
- **Parte destra:**
    
    - Internet → Router → Firewall → IDS → LAN.
        
    - Qui si sottolinea che l’IDS non sostituisce il firewall, ma lo **completa**, andando oltre al semplice filtraggio meccanico.
        

---

### Spiegazione in stile Feynman

Immagina la tua rete come l’ingresso di un palazzo:

- Il **Firewall** è il portinaio: controlla chi entra, chiedendo un documento (IP, porta, protocollo). Se non rispetti le regole, non passi.
    
- L’**IDS** è una telecamera intelligente nell’atrio: anche chi è entrato legalmente viene osservato. Se si comporta in modo anomalo (corre, rompe oggetti, forza una porta), l’IDS lo segnala o lo blocca (IPS).
    
- La differenza chiave: il firewall guarda solo il “biglietto d’ingresso”; l’IDS/IPS osserva il **comportamento** dentro la rete.


---
# Anomaly Detection (AD)

![Pasted image 20250913155958.png](../../imgs/Pasted%20image%2020250913155958.png)

### Premessa

Gestire un’infrastruttura di rete significa affrontare **milioni di dati** prodotti da dispositivi, utenti e applicazioni.

- Non basta monitorare singoli componenti: serve un controllo complessivo, altrimenti si rischiano **degradi di servizio, interruzioni o incidenti di sistema**.
    
- Obiettivo dell’AD: **individuare anomalie** (anche minime) che indicano un problema nascente e agire **prima che causi danni reali**.
    

---

### Definizione

L’**Anomaly Detection (AD)** è il rilevamento avanzato delle anomalie che:

- trova e corregge gli incidenti non appena iniziano a verificarsi,
    
- usa tecniche di **Machine Learning** per apprendere il comportamento normale dei dispositivi,
    
- fornisce **avvisi puntuali sugli errori critici**.
    

Gli AD si basano su **sonde** collegate in mirroring agli switch di rete, capaci di leggere tutti i pacchetti in transito.

---

### Funzioni principali

1. **Analisi del comportamento dei dispositivi**
    
    - Ogni device è monitorato in base a tipologia, frequenza e destinazione dei messaggi.
        
    - Si costruiscono modelli che descrivono come “normalmente” il dispositivo comunica.
        
    - Se il comportamento devia da questo modello, si segnala un’anomalia.
        
2. **Analisi del traffico di rete**
    
    - Individua anomalie come picchi di traffico, cadute improvvise, dispositivi che si attivano senza motivo.
        
    - Si confronta con una **baseline** (conoscenza storica del traffico) per capire se un cambiamento è legittimo o sospetto.
        
3. **Baseline Definition**
    
    - La baseline è il “profilo standard” della rete, costruito in un periodo di apprendimento.
        
    - È la base di confronto per identificare comportamenti anomali.
        
4. **Deep Packet Inspection (DPI)**
    
    - Analisi approfondita dei pacchetti in transito: header, payload, protocolli.
        
    - Serve a distinguere tra traffico lecito e malevolo anche se viaggia su porte standard (es. malware nascosto in HTTPS).
        

---

### Analisi del grafico

- L’immagine mostra un’**architettura di rete industriale** con:
    
    - **PLC, RTU e SCADA** (livello OT) che generano traffico.
        
    - **Sonde (in blu)** distribuite lungo la rete che raccolgono pacchetti.
        
    - Una **console centrale di management (CMC)** che invia i dati a **SIEM** e **SOC**.
        
- Funzione: correlare i dati raccolti, evidenziare anomalie e avvisare tempestivamente.
    

---

### Spiegazione in stile Feynman

Immagina la rete come una città:

- Ogni dispositivo è un cittadino che compie routine quotidiane (andare al lavoro, fare spesa, ecc.).
    
- L’**Anomaly Detection** è come una telecamera intelligente che osserva la città:
    
    - Se qualcuno cambia improvvisamente abitudini (va in un posto insolito a un orario strano) → anomalia.
        
    - Se aumenta improvvisamente il traffico in un quartiere → anomalia.
        
    - Se un cittadino compie azioni mai viste prima (baseline violata) → allerta.
        
- L’uso del Machine Learning rende la telecamera capace di **imparare** con il tempo e distinguere tra novità lecite e minacce.


---
# Il SIEM

![Pasted image 20250913160119.png](../../imgs/Pasted%20image%2020250913160119.png)
### Definizione

Il **SIEM** (Security Information and Event Management) è una **soluzione software** che:

- raccoglie in tempo reale gli eventi e le informazioni che accadono nella rete,
    
- centralizza i log provenienti da vari dispositivi e sistemi di sicurezza (firewall, router, switch, server, workstation, IPS, endpoint),
    
- li **correla** e li **analizza** per individuare pattern sospetti,
    
- genera **allarmi** e, in certi casi, attiva risposte automatiche.
    

---

### Funzionalità principali

1. **Raccolta dati**
    
    - Log e eventi raccolti tramite agent installati sui dispositivi o tramite analisi diretta dei flussi.
        
2. **Correlazione e aggregazione**
    
    - Mette insieme eventi diversi (es. login falliti + traffico anomalo + accesso a file critici) per riconoscere attacchi complessi.
        
3. **Console centralizzata**
    
    - Interfaccia unica per il monitoraggio.
        
    - Fornisce dashboard, alert in tempo reale, reportistica.
        
4. **Risposta automatica**
    
    - Possibilità di far scattare azioni predefinite in risposta a certi eventi (es. blocco IP sospetto, chiusura di una porta, isolamento di un host compromesso).
        

---

### Analisi del grafico

- **A sinistra:** puzzle di elementi (firewall, server, IPS, router, switch, workstation) → il SIEM è il “collante” che li unisce e permette di leggere gli eventi in modo integrato.
    
- **A destra:** esempio di console SIEM (dashboard Splunk o simile):
    
    - mostra eventi notabili divisi per categoria (accessi, endpoint, rete, identità, audit, ecc.),
        
    - evidenzia trend temporali e fonti principali di eventi,
        
    - segnala anomalie con codici colore e livelli di urgenza.
        

---

### Spiegazione in stile Feynman

Immagina l’azienda come una città piena di telecamere, vigili e centraline di allarme.

- Senza SIEM: ogni telecamera o sensore avvisa separatamente → caos, difficile avere una visione d’insieme.
    
- Con SIEM: tutti i segnali confluiscono in una **centrale unica di controllo**.
    
    - Qui gli operatori vedono il quadro generale, capiscono se 10 allarmi diversi fanno parte dello **stesso incidente**, e possono reagire subito.
        
- In più, alcuni allarmi possono generare **azioni automatiche** (come la polizia che fa scattare il semaforo rosso in una zona per bloccare un ladro in fuga).


---
# VPN (Virtual Private Network)

Una **VPN** crea una **rete privata virtuale** sopra un’infrastruttura pubblica (tipicamente Internet), con lo scopo di:

- garantire **privacy** (i dati non sono leggibili da terzi),
    
- mantenere l’**anonimato** dell’utente,
    
- assicurare la **sicurezza dei dati** grazie a un canale criptato (tunnel VPN).
    

La VPN permette a dispositivi che **non sono nella stessa LAN** di comunicare come se fossero **nella stessa rete locale privata**.

---

### Meccanismo

- La VPN si basa su un **tunnel cifrato**: i dati vengono incapsulati e crittografati, viaggiando “al sicuro” dentro Internet.
    
- Lato pratico: l’utente (VPN Client) si connette al **VPN Server** tramite il tunnel → da lì ottiene accesso alla rete interna (LAN aziendale, risorse, servizi).
    

---

### Limiti

- La crittografia assicura **riservatezza e integrità dei dati in transito**, ma **non garantisce** che i pacchetti stessi non siano portatori di malware o contenuti malevoli.
    
- Per questo la VPN deve sempre integrarsi con firewall, antivirus ed endpoint protection.
    

---

### Protocolli di criptazione VPN

1. **SSTP (Secure Socket Tunneling Protocol):** usa HTTPS (porta 443), molto utile per aggirare firewall restrittivi.
    
2. **OpenVPN:** open source, sicuro e flessibile, usa SSL/TLS per la cifratura.
    
3. **IPSec (Internet Protocol Security):** suite di protocolli a livello IP, usata spesso per VPN site-to-site.
    
4. **SSL (Secure Sockets Layer):** oggi sostituito da TLS, usato nelle VPN per applicazioni web.
    

---

### Analisi del grafico

![Pasted image 20250913160246.png](../../imgs/Pasted%20image%2020250913160246.png)

- Mostra l’architettura base di una VPN:
    
    - **VPN Client** si connette a Internet.
        
    - Attraverso il **tunnel VPN cifrato**, raggiunge il **VPN Server** dietro il firewall.
        
    - Una volta autenticato, può accedere a risorse interne (es. Web Server nella “perimeter network”).
        
- Il tunnel è disegnato come un “tubo protetto” che avvolge il traffico in transito → rende visivamente l’idea del canale sicuro sopra la rete pubblica.
    

---

### Spiegazione in stile Feynman

Immagina di dover inviare una lettera privata attraverso una città pericolosa:

- Senza VPN → la lettera viaggia aperta, chiunque può leggerla.
    
- Con VPN → la lettera è chiusa in una cassaforte (cifratura) e trasportata in un tunnel sotterraneo che porta direttamente a destinazione (tunnel VPN).
    
- Attenzione: anche se la cassaforte è sicura, **potresti aver messo dentro un virus**: ecco perché la VPN protegge la comunicazione, non i contenuti.


---
# Endpoint Protection

### Definizione

- In senso lato, **endpoint** = qualunque dispositivo o connessione che accede alla rete aziendale.
    
- Nella pratica recente, il termine si associa soprattutto a **laptop, smartphone e tablet**, cioè i device che ogni giorno usiamo per collegarci a sistemi e dati aziendali.
    
- Problema: ogni endpoint è anche un **potenziale punto d’ingresso per le minacce informatiche** (malware, phishing, azioni interne malevole).
    

---

### Funzioni principali di Endpoint Security

1. **Machine Learning** → per rilevare minacce **zero-day** (non ancora note).
    
2. **Protezione antimalware e antivirus** → prima linea di difesa contro software malevoli.
    
3. **Mobile Security** → protezione di cellulari, tablet e laptop usati da remoto.
    
4. **Sicurezza Web** → filtri di navigazione, protezione contro siti malevoli e download infetti.
    
5. **Classificazione dei dati** → distinguere informazioni pubbliche, riservate o critiche, applicando controlli mirati.
    
6. **Firewall integrato** → blocca traffico sospetto direttamente sul device.
    
7. **Gateway e-mail** → protezione contro phishing e social engineering.
    
8. **Protezione da minacce interne** → rileva e blocca azioni involontarie (es. errori) o intenzionali (insider threat).
    
9. **Gestione centralizzata** → piattaforma che permette agli amministratori di monitorare e configurare tutti gli endpoint da un’unica console.
    
10. **Crittografia** → su endpoint, e-mail e disco → garantisce riservatezza anche in caso di furto fisico del device.
    

---

### Analisi concettuale

- Gli endpoint sono l’anello **più debole** della catena di sicurezza, perché sono **dispositivi mobili, distribuiti, usati da persone comuni**.
    
- Proteggere solo il perimetro (firewall, IDS/IPS, VPN) non basta: un laptop infetto può entrare in rete e diffondere minacce dall’interno.
    
- Per questo l’endpoint protection è diventata oggi un pilastro fondamentale delle architetture di cyber security.
    

---

### Spiegazione in stile Feynman

Immagina la rete aziendale come un castello:

- Firewall, IDS/IPS, VPN sono i **muri, torri e fossati** che difendono l’ingresso.
    
- Ma se i soldati (endpoint) che entrano ed escono ogni giorno portano con sé armi infette senza saperlo, il castello viene comunque compromesso.
    
- L’endpoint protection è l’**armatura personale di ogni soldato**: filtra messaggi, blocca attacchi, cifra documenti, avvisa quando qualcosa non va.
    

---

### Integrazione con il sistema di gestione

- Endpoint protection **dialoga con SIEM e SOC**, inviando log e segnalazioni centralizzate.
    
- È quindi parte integrante del sistema di gestione della sicurezza: dai dispositivi periferici al controllo centralizzato.


---

# RIEPILOGO:
# Modulo 3 – U.D. 4 – Lezione 5

## Tecnologie di supporto ai sistemi di gestione

### Introduzione

In questa lezione vengono analizzate le **principali tecnologie informatiche di protezione** che supportano i sistemi di gestione della sicurezza informatica. Dopo aver visto modelli, paradigmi e standard (CIA, ISA-95, IT/OT), l’attenzione si sposta sugli **strumenti concreti** per monitorare, rilevare e mitigare minacce.

Le tecnologie trattate sono:

- IDS/IPS (Intrusion Detection/Prevention Systems)
    
- Anomaly Detection (AD)
    
- SIEM (Security Information and Event Management)
    
- VPN (Virtual Private Network)
    
- Endpoint Protection
    

---

## 1. IDS/IPS – Intrusion Detection / Prevention Systems

- Sistemi hardware/software che monitorano reti e sistemi per rilevare attività dannose o violazioni delle policy.
    
- Possono **rilevare (IDS)** o anche **bloccare (IPS)** intrusioni.
    
- Integrano i firewall:
    
    - **Firewall:** controlla i pacchetti in ingresso/uscita con regole semplici (IP, porte, protocolli).
        
    - **IDS/IPS:** analizza i pacchetti e i comportamenti → individua attacchi anche dall’interno.
        

**Metodologie di rilevazione:**

- **Misuse Detection (signature-based):**
    
    - Confronta attività con firme di attacchi noti.
        
    - Veloce, pochi falsi positivi.
        
    - Limite: non riconosce attacchi sconosciuti.
        
- **Anomaly Detection (behaviour-based):**
    
    - Confronta attività con il profilo normale (baseline).
        
    - Può evolversi con AI e ML.
        
    - Vantaggio: riconosce anche minacce nuove.
        
    - Limite: produce molti falsi positivi, richiede monitoraggio complesso.
        

---

## 2. Anomaly Detection (AD)

- Obiettivo: individuare e correggere incidenti **appena iniziano a verificarsi**, prima che causino danni.
    
- Utilizza **Machine Learning** per analizzare enormi quantità di dati, apprendere comportamenti normali e segnalare deviazioni.
    
- Funziona tramite **sonde** in mirroring sugli switch di rete → leggono i pacchetti in transito.
    

**Funzioni principali:**

1. **Analisi comportamento dispositivi:** crea modelli su frequenza, tipologia e destinazione dei messaggi.
    
2. **Analisi traffico di rete:** identifica picchi, cali, sovraccarichi o attivazioni anomale.
    
3. **Baseline Definition:** costruisce un profilo storico di riferimento con cui confrontare i dati futuri.
    
4. **Deep Packet Inspection (DPI):** analizza header e payload dei pacchetti per distinguere traffico lecito da malevolo.
    

---

## 3. SIEM – Security Information and Event Management

- Soluzione software che raccoglie, correla e analizza in tempo reale eventi e log da diversi sistemi di sicurezza (firewall, server, router, workstation, endpoint).
    
- Fornisce una **console centralizzata** per:
    
    - monitoraggio dei parametri critici,
        
    - segnalazione automatica di anomalie,
        
    - risposta a determinati eventi.
        

**Funzioni principali:**

- **Raccolta log/eventi** da agent o fonti dirette.
    
- **Correlazione e aggregazione**: unisce più segnali per individuare attacchi complessi.
    
- **Dashboard**: vista unica su sicurezza aziendale.
    
- **Azioni automatiche**: blocco IP, isolamento host compromessi.
    

---

## 4. VPN – Virtual Private Network

- Crea una rete privata virtuale su infrastruttura pubblica (Internet).
    
- Garantisce **privacy, anonimato e sicurezza dei dati** tramite un tunnel cifrato.
    
- Permette a dispositivi remoti di agire come se fossero nella stessa LAN.
    

**Caratteristiche:**

- Il traffico viaggia in un **canale criptato (tunnel VPN)** → impedisce intercettazioni.
    
- **Limite:** la VPN protegge la comunicazione, non i contenuti → malware può comunque transitare.
    

**Protocolli comuni:**

- **SSTP (Secure Socket Tunneling Protocol)**
    
- **OpenVPN** (open source, SSL/TLS)
    
- **IPSec (Internet Protocol Security)**
    
- **SSL (Secure Sockets Layer, oggi TLS)**
    

---

## 5. Endpoint Protection

- **Endpoint:** qualunque dispositivo che accede alla rete. Oggi associato soprattutto a **smartphone, tablet e laptop**.
    
- Ogni endpoint è un **punto d’accesso e di rischio** → può diventare la via di ingresso di malware o phishing.
    

**Funzioni di sicurezza:**

- Machine learning per minacce zero-day.
    
- Antivirus e antimalware.
    
- Mobile security.
    
- Sicurezza web.
    
- Classificazione dei dati.
    
- Firewall integrato.
    
- Gateway e-mail per phishing e social engineering.
    
- Protezione da minacce interne (errori, insider threat).
    
- Gestione centralizzata di tutti gli endpoint.
    
- Crittografia (endpoint, disco, e-mail).
    

---

# Riepilogo della lezione

- Gli strumenti tecnologici rafforzano e completano i sistemi di gestione.
    
- **IDS/IPS**: rilevano intrusioni note e anomalie di rete.
    
- **AD**: usano ML e baseline per scoprire comportamenti anomali.
    
- **SIEM**: centralizzano log ed eventi, correlano e reagiscono.
    
- **VPN**: garantiscono comunicazioni sicure su reti pubbliche.
    
- **Endpoint Protection**: difendono i dispositivi periferici, spesso il punto più vulnerabile.
    

Queste tecnologie, integrate in un **SGSI** (Sistema di Gestione della Sicurezza delle Informazioni), permettono di tradurre in pratica le strategie organizzative e normative viste in precedenza.