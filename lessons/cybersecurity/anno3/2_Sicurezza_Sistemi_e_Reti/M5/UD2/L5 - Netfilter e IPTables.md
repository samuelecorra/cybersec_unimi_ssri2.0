## **Lezione 5: Netfilter e IPTables**

### **1. Introduzione a Netfilter**

**Netfilter** è il **componente del kernel Linux** dedicato all’intercettazione e alla manipolazione dei pacchetti di rete.  
È il cuore del sistema di **firewalling** nei sistemi Linux, e offre funzionalità avanzate come:

- **Filtraggio stateful** del traffico;
    
- **Network Address Translation (NAT)**;
    
- **Deep Packet Inspection (DPI)**;
    
- **Estensioni modulari del kernel** per ispezioni specifiche.
    

L’interfaccia utente per la configurazione di Netfilter è fornita da due strumenti:

- `iptables` → per IPv4
    
- `ip6tables` → per IPv6
    

> In sintesi: Netfilter lavora nel **kernel**, mentre IPTables è lo **strumento in user space** che permette di configurarlo.

---

## **2. Struttura generale di Netfilter**

### **2.1. Tabelle e Chain**

Netfilter è organizzato in **tabelle** e **catene (chain)**:

- Ogni **tabella** gestisce un aspetto del traffico (filtraggio, traduzione, manipolazione).
    
- Ogni tabella contiene **chain**, ovvero liste ordinate di regole (rules).
    
- Ogni **regola** si compone di due parti:
    
    - **Filtro (match):** le condizioni che il pacchetto deve soddisfare;
        
    - **Target (azione):** cosa fare se il pacchetto rispetta il filtro.
        

### **2.2. Tabelle principali**

|Tabella|Funzione principale|
|---|---|
|**filter**|Controllo e filtraggio dei pacchetti|
|**nat**|Traduzione degli indirizzi (SNAT, DNAT, Masquerade)|
|**mangle**|Modifica avanzata delle opzioni dei pacchetti|
|**raw**|Gestione di eccezioni, disattivazione del tracciamento|

> Ogni tabella ha un set di **chain** predefinite, ma è possibile crearne di personalizzate per una logica di filtraggio più leggibile.

---

## **3. Tabella `filter`**

È la **tabella principale** per il controllo del traffico.  
Permette di decidere quali pacchetti accettare, bloccare o inoltrare.

### **Chain predefinite**

|Chain|Funzione|
|---|---|
|**INPUT**|Gestisce i pacchetti **in arrivo** destinati al sistema locale|
|**FORWARD**|Gestisce pacchetti in transito **da un’interfaccia all’altra** (solo se l’IP forwarding è abilitato)|
|**OUTPUT**|Gestisce i pacchetti **in uscita** generati dal sistema|

> Se il sistema funge da **router**, la chain `FORWARD` diventa fondamentale per instradare i pacchetti verso altri host.

---

## **4. Tabella `nat`**

La tabella **NAT (Network Address Translation)** regola la **modifica degli indirizzi IP e delle porte** dei pacchetti.  
Agisce **solo sul primo pacchetto** di una connessione TCP/UDP; le decisioni vengono poi memorizzate e applicate ai pacchetti successivi.

### **Chain predefinite**

|Chain|Funzione|
|---|---|
|**PREROUTING**|Esegue il **DNAT** (Destination NAT) prima della decisione di instradamento|
|**POSTROUTING**|Esegue il **SNAT** (Source NAT) dopo la decisione di instradamento|
|**OUTPUT**|Applica DNAT ai pacchetti generati localmente|

> NAT è utilizzato, ad esempio, nei router domestici per mascherare gli IP interni dietro un unico IP pubblico.

---

## **5. Tabella `mangle`**

Serve per **modificare le opzioni dei pacchetti** (es. campi TOS, TTL, QoS).  
Permette anche politiche avanzate di instradamento e prioritizzazione.

### **Chain disponibili**

|Chain|Funzione|
|---|---|
|**PREROUTING**|Analizza i pacchetti prima della consultazione della tabella di routing|
|**INPUT**|Esamina pacchetti in ingresso diretti al sistema locale|
|**FORWARD**|Analizza pacchetti in transito|
|**OUTPUT**|Analizza pacchetti generati localmente|
|**POSTROUTING**|Agisce dopo la decisione di routing, prima dell’invio|

> Esempio: impostare il campo TOS (Type of Service) su “Minimize-Delay” per pacchetti DNS.

---

## **6. Tabella `raw`**

La tabella **raw** serve per gestire pacchetti che **non devono essere tracciati** da Netfilter.  
È usata in contesti in cui si desidera un comportamento **stateless**.

### **Chain disponibili**

|Chain|Descrizione|
|---|---|
|**PREROUTING**|Pacchetti provenienti da qualsiasi interfaccia|
|**OUTPUT**|Pacchetti generati localmente|

> Esempio: il target `NOTRACK` disabilita il connection tracking.

---

## **7. Flusso dei pacchetti nel kernel Linux**

L’ordine di attraversamento delle tabelle da parte di un pacchetto è complesso ma deterministico.  
In forma semplificata:

```text
           Network Packet In
                  ↓
              RAW (PREROUTING)
                  ↓
            MANGLE (PREROUTING)
                  ↓
               NAT (PREROUTING)
                  ↓
          [Decisione di Routing]
             ↙             ↘
     (per me)              (da inoltrare)
  FILTER→MANGLE→PROCESS   MANGLE→FILTER
                  ↓
             NAT (POSTROUTING)
```

> Ogni pacchetto attraversa più tabelle, ma **non tutte** in ogni percorso: dipende dal suo stato (input, output, forward).

---

## **8. Targets in Netfilter**

Il **target** indica l’azione da eseguire quando una regola viene verificata.  
Può essere:

- **Una chain definita dall’utente**, oppure
    
- **Un’azione predefinita**.
    

### **Targets principali**

|Target|Descrizione|
|---|---|
|**ACCEPT**|Permette il transito del pacchetto|
|**DROP / REJECT**|Scarta il pacchetto (silenziosamente o con messaggio)|
|**QUEUE**|Invia il pacchetto a un’applicazione in user-space|
|**RETURN**|Torna alla chain chiamante (effetto “return” ricorsivo)|
|**LOG**|Registra informazioni sul pacchetto nel syslog|
|**DNAT**|Modifica la destinazione (Destination NAT)|
|**SNAT**|Modifica l’origine (Source NAT)|
|**MASQUERADE**|Variante dinamica di SNAT per IP pubblici variabili|
|**NFLOG**|Logging avanzato su interfacce dedicate|

> Esempio: `-j LOG` registra nel demone `syslog` i dettagli del pacchetto.

---

## **9. Relazione tra Netfilter e IPTables**

- **Netfilter** opera a livello **kernel**, intercettando e manipolando i pacchetti.
    
- **IPTables** è lo strumento **user-space** che consente di:
    
    - aggiungere, modificare o cancellare regole;
        
    - impostare politiche di default;
        
    - consultare lo stato delle tabelle.
        

> In genere si usa il termine _iptables_ per riferirsi all’intera architettura Netfilter/IPTables.

---

## **10. Comandi principali di IPTables**

### **Visualizzazione e diagnostica**

```bash
iptables -L [-t tabella]
iptables -L -t nat --line-numbers
iptables -v          # modalità dettagliata
iptables -n          # evita la risoluzione dei nomi
```

---

### **Gestione delle policy**

```bash
iptables -P <chain> <target>     # imposta la policy di default
iptables -F [chain]              # flush: cancella tutte le regole
```

---

### **Inserimento, append e cancellazione di regole**

```bash
iptables -I <chain> [posizione] <filtro> -j <target>   # Inserisce
iptables -A <chain> <filtro> -j <target>               # Aggiunge in coda
iptables -D <chain> <num>                              # Elimina per numero
iptables -D <chain> <filtro> -j <target>               # Elimina per contenuto
```

---

## **11. Costruzione di una regola**

Ogni regola è composta da:

- **Filtro (match):**
    
    - `-s` → sorgente
        
    - `-d` → destinazione
        
    - `-i` / `-o` → interfacce
        
    - `-p` → protocollo (`tcp`, `udp`, `icmp`)
        
    - `--sport` / `--dport` → porte sorgente/destinazione
        
    - `-m` → modulo di estensione (es. `state`, `multiport`)
        
- **Target (azione):**
    
    - definito tramite `-j <target>`
        

> Esempio:
> 
> ```bash
> iptables -A INPUT -s 10.0.0.1 -p tcp --dport 22 -j ACCEPT
> ```

---

## **12. Comandi riassuntivi**

|Comando|Esempio|Descrizione|
|---|---|---|
|`-A`|`iptables -A INPUT ...`|Aggiunge una regola in coda|
|`-I`|`iptables -I INPUT 1 ...`|Inserisce in posizione specifica|
|`-D`|`iptables -D INPUT --dport 80 -j DROP`|Cancella una regola|
|`-R`|`iptables -R INPUT 1 ...`|Sostituisce una regola|
|`-L`|`iptables -L INPUT`|Elenca regole della chain|
|`-F`|`iptables -F INPUT`|Pulisce tutte le regole|
|`-N` / `-X`|`iptables -N allowed` / `iptables -X allowed`|Crea o elimina chain personalizzate|
|`-P`|`iptables -P INPUT DROP`|Imposta la policy di default|
|`-E`|`iptables -E old new`|Rinomina una chain|

---

## **13. Match generici**

|Match|Esempio|Descrizione|
|---|---|---|
|`-p`|`-p tcp`|Specifica il protocollo|
|`-s`|`-s 10.0.0.1`|Sorgente IP|
|`-d`|`-d 192.168.1.0/24`|Destinazione IP|
|`-i` / `-o`|`-i eth0`, `-o wlan0`|Interfacce|
|`--sport` / `--dport`|`--dport 22`|Porte TCP/UDP|
|`--tcp-flags`|`--tcp-flags SYN,ACK SYN`|Specifica i flag TCP da analizzare|
|`--icmp-type`|`--icmp-type 8`|Filtra pacchetti ICMP (es. echo request)|
|`-m multiport`|`--ports 22,53,80`|Gestisce più porte contemporaneamente|
|`-m state`|`--state NEW,ESTABLISHED`|Filtra per stato della connessione|

---

## **14. Stati della connessione**

|Stato|Descrizione|
|---|---|
|**NEW**|Pacchetto che avvia una nuova connessione|
|**ESTABLISHED**|Pacchetto appartenente a una connessione già esistente|
|**RELATED**|Pacchetto correlato a una connessione già aperta (es. FTP dati)|

---

## **15. Targets principali**

|Target|Descrizione|
|---|---|
|**ACCEPT**|Permette il passaggio del pacchetto|
|**DROP**|Scarta silenziosamente|
|**REJECT**|Scarta inviando un messaggio d’errore|
|**LOG**|Registra i dettagli del pacchetto|
|**DNAT**|Modifica la destinazione (Destination NAT)|
|**SNAT**|Modifica l’origine (Source NAT)|
|**MASQUERADE**|Esegue un NAT dinamico|
|**RETURN**|Ritorna alla chain chiamante|

> Ogni regola definisce un **match** e un **target**, che insieme determinano il comportamento del firewall.

---

## **16. Conclusione**

Netfilter e IPTables rappresentano il **cuore della sicurezza Linux**:  
un’infrastruttura modulare, potente e profondamente integrata nel kernel, che consente di:

- filtrare i pacchetti a livello di rete e trasporto,
    
- applicare politiche NAT e QoS,
    
- implementare firewall stateful e proxy di rete,
    
- ispezionare contenuti applicativi tramite DPI.
    

> In sintesi: Netfilter è l’architettura, IPTables è il linguaggio con cui la si controlla.