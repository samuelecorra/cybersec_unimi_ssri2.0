## **Lezione 2: MAC Flooding e ARP Spoofing**

### **1. Sicurezza con gli switch**

Gli **switch** sono dispositivi di rete che operano al **livello 2 (link layer)** del modello ISO/OSI.  
Ogni porta dello switch è collegata a un computer e lo switch apprende gli **indirizzi MAC** dei dispositivi connessi, memorizzandoli in una **CAM (Content Addressable Memory)** che associa ogni MAC a una porta specifica.

Grazie a questa tabella:

- i **frame Ethernet** vengono inviati solo alla porta corretta,
    
- si ottiene una **separazione logica** dei _collision domain_ (che però non è fisica).
    

> Se la tabella CAM viene compromessa, lo switch può comportarsi come un **hub**, rendendo la rete vulnerabile agli attacchi di sniffing.

---

### **2. Attacco di MAC Flooding**

Il **MAC Flooding** (o _MAC address table overflow_) sfrutta la **capacità limitata** della CAM degli switch.

#### **Meccanismo dell’attacco**

1. L’attaccante invia migliaia di frame con **indirizzi MAC falsificati** come sorgente.
    
2. La tabella CAM si **riempie completamente**.
    
3. Lo switch non è più in grado di memorizzare nuovi MAC address.
    
4. Entra in modalità **fail-open**, comportandosi come un **hub**.
    
5. Tutti i pacchetti vengono inoltrati su **tutte le porte**.
    

#### **Conseguenze**

- L’attaccante può **intercettare il traffico** di rete locale.
    
- Viene **violata la separazione logica** dei canali di comunicazione.
    
- Il traffico privato tra due host diventa **visibile all’intera LAN**.
    

> Gli strumenti di attacco possono generare fino a **160.000 MAC al minuto**, saturando anche switch di fascia alta.

---

### **3. Address Resolution Protocol (ARP)**

Il **protocollo ARP** collega il livello di rete (IP) al livello di collegamento (MAC), traducendo un indirizzo IP in un indirizzo fisico.

Ogni nodo mantiene una **ARP cache**, cioè una tabella con le associazioni IP–MAC già note.

#### **Funzionamento**

1. Quando un host deve inviare un pacchetto, controlla nella cache se conosce il MAC dell’IP di destinazione.
    
2. Se non lo conosce, invia in **broadcast** una **ARP request**:
    
    ```
    Who has 192.168.0.3? Tell 192.168.0.1
    ```
    
3. L’host con quell’indirizzo IP risponde in **unicast** con una **ARP reply**:
    
    ```
    192.168.0.3 is at 00:1A:2B:3C:4D:5E
    ```
    
4. L’associazione viene salvata nella cache per un tempo limitato.  
    Il comando `arp -a` mostra la tabella ARP su Linux e Windows.
    

---

### **4. Messaggi ARP e funzionamento pratico**

- Le **ARP request** vengono inviate in broadcast (indirizzo `FF:FF:FF:FF:FF:FF`).
    
- Le **ARP reply** sono unicast e contengono il MAC del mittente.
    
- L’ARP è **stateless**: ogni risposta ricevuta aggiorna la cache, anche se non richiesta.
    

> Questa assenza di verifica è la base delle **vulnerabilità ARP** più note.

---

### **5. Indirizzi MAC e spoofing**

Ogni **scheda di rete (NIC)** è identificata da un indirizzo **MAC a 48 bit**, scritto in formato esadecimale.  
I primi 24 bit rappresentano il **produttore (OUI)**, i restanti 24 identificano il dispositivo.

Esempio:

```
00:23:A2:D6:F2:15  →  Motorola Mobility Inc.
```

#### **Riconfigurazione e spoofing**

- L’indirizzo MAC può essere **modificato via software** dal driver di rete.
    
- Un utente con privilegi adeguati può **sostituire il MAC reale** con uno arbitrario.
    
- Conoscendo il MAC di un host assente, l’attaccante può **impersonarlo**.
    

#### **Contromisure**

- Bloccare la porta dello switch quando un host viene disconnesso.
    
- Disabilitare o segnalare indirizzi MAC duplicati.
    

---

### **6. ARP Spoofing e ARP Cache Poisoning**

L’**ARP Spoofing** (o **ARP cache poisoning**) consiste nell’inviare **risposte ARP falsificate** a una LAN per modificare le tabelle ARP degli host.

#### **Fasi dell’attacco**

1. L’attaccante invia messaggi ARP che associano il proprio MAC a un IP legittimo (es. quello del gateway).
    
2. Le macchine vittime aggiornano la loro ARP cache con l’informazione falsa.
    
3. Tutto il traffico destinato al gateway viene **inoltrato verso l’attaccante**, che può:
    
    - intercettarlo (**sniffing**);
        
    - modificarlo (**man-in-the-middle**);
        
    - bloccarlo (**DoS**).
        

#### **Motivo della vulnerabilità**

- Nessuna autenticazione nei messaggi ARP.
    
- Nessun tracciamento delle richieste inviate.
    
- Le cache vengono aggiornate da qualsiasi risposta, anche non richiesta.
    

> L’ARP è quindi un protocollo **privo di verifica d’identità**, basato esclusivamente sulla fiducia tra nodi della LAN.

---

### **7. Difese contro l’ARP Spoofing**

|**Contromisura**|**Descrizione**|
|---|---|
|**Voci ARP statiche**|Inserimento manuale delle coppie IP–MAC affidabili (efficace ma difficile da mantenere).|
|**DHCP Snooping**|Autorizza solo i server DHCP legittimi e controlla che gli host usino IP validi.|
|**ARPWatch**|Strumento che monitora variazioni sospette nelle tabelle ARP e invia notifiche via e-mail.|
|**Switch Port Security**|Blocca la porta se rileva traffico anomalo o cambi di MAC address.|

> Nota: l’ARP poisoning può anche essere usato in modo **legittimo**, ad esempio per reindirizzare un utente verso un **portale di autenticazione** prima di concedere l’accesso alla rete.

---

### **8. Sintesi finale**

Gli attacchi **MAC Flooding** e **ARP Spoofing** sfruttano due punti deboli fondamentali delle reti LAN:

|**Attacco**|**Livello OSI**|**Effetto**|**Contromisure**|
|---|---|---|---|
|**MAC Flooding**|Link Layer|Trasforma lo switch in un hub, consentendo sniffing del traffico.|Limitazione CAM, port security.|
|**MAC Spoofing**|Link Layer|Impersonazione di un host legittimo.|Filtri MAC, blocco duplicati.|
|**ARP Spoofing**|Link / Network|Avvelenamento della cache ARP, MITM.|ARPWatch, DHCP snooping, ARP statiche.|

> In conclusione, le vulnerabilità del **livello 2** rappresentano il punto più debole delle reti locali: la sicurezza deve essere garantita **a monte**, attraverso monitoraggio continuo e politiche di autenticazione e isolamento.

---

Perfetto — ecco la **Lezione 3 – IP Spoofing** convertita in **Markdown compatibile con Obsidian**, nello stesso stile didattico delle lezioni precedenti. (Basata sul materiale che mi hai fornito).

---