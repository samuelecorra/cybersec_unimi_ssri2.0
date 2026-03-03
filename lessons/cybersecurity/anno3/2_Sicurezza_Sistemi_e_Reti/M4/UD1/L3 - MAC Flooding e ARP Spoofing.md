## **Lezione 3: MAC Flooding e ARP Spoofing**

### **1. Sicurezza con gli switch**

Gli **switch** operano al **livello di collegamento (Layer 2)** del modello ISO/OSI.  
A differenza degli hub, che inviano i pacchetti a tutte le porte, gli switch inoltrano i **frame Ethernet** solo alla porta collegata al **MAC address di destinazione**, riducendo così il traffico e isolando i **collision domain**.

Ogni switch:

- apprende dinamicamente gli indirizzi **MAC** dei dispositivi connessi;
    
- memorizza queste associazioni nella **CAM (Content Addressable Memory)**, che contiene la mappatura _MAC address → porta dello switch_.
    

Questa separazione logica dei domini di collisione può però essere **violata** da attacchi mirati alla CAM stessa.

---

### **2. Attacco MAC Flooding**

Il **MAC Flooding** (o **MAC address table overflow attack**) sfrutta la **dimensione limitata** della CAM di uno switch.

L’attaccante invia un enorme numero di pacchetti con **indirizzi MAC di origine falsificati**, saturando la tabella finché lo switch non è più in grado di memorizzare nuove associazioni.

#### **Conseguenze**

- Quando la CAM è piena, lo switch passa in **modalità fail-open** e **si comporta come un hub**, inoltrando i frame su **tutte le porte**.
    
- In questo stato, un attaccante collegato allo stesso switch può **intercettare tutto il traffico locale** (sniffing).
    
- Alcuni tool sono in grado di generare fino a **160.000 indirizzi MAC al minuto**, rendendo l’attacco estremamente efficace.
    

#### **Effetto pratico**

- Viene **violata l’isolazione logica** tra le porte.
    
- Il traffico che prima era instradato solo alla destinazione ora è **visibile da ogni nodo**.
    

> In sintesi, il MAC Flooding è un attacco di _denial of service temporaneo_ che degrada lo switch a semplice hub, compromettendo la riservatezza della rete locale.

---

### **3. Protocollo ARP (Address Resolution Protocol)**

Il protocollo **ARP** collega il **livello rete (IP)** al **livello collegamento (MAC)**, traducendo gli indirizzi IP in indirizzi fisici MAC.

Ogni host mantiene una **ARP cache**, cioè una tabella che memorizza le associazioni IP–MAC già note.

#### **Funzionamento**

1. Se un host deve inviare un pacchetto a un indirizzo IP sconosciuto, trasmette una **ARP request** in broadcast:
    
    ```
    Who has 192.168.0.3? Tell 192.168.0.1
    ```
    
2. Il nodo con quell’indirizzo IP risponde con una **ARP reply** in unicast:
    
    ```
    192.168.0.3 is at 00:1A:2B:3C:4D:5E
    ```
    
3. La risposta viene salvata nella ARP cache per un periodo limitato di tempo.
    

Il comando `arp -a` consente di visualizzare la tabella ARP su sistemi **Linux** e **Windows**.

---

### **4. MAC Address e possibilità di spoofing**

Ogni **scheda di rete (NIC)** è identificata da un **indirizzo MAC a 48 bit**:

- I **primi 24 bit** identificano il produttore (OUI – _Organizationally Unique Identifier_).
    
- I **restanti 24 bit** sono assegnati in modo univoco al dispositivo.
    

Esempio:

```
00:23:A2:D6:F2:15  →  Motorola Mobility Inc.
```

Tuttavia, l’indirizzo MAC **non è immutabile**:

- Può essere **modificato a livello software** tramite il driver di rete.
    
- Un utente con privilegi amministrativi può **sostituire il MAC reale** con uno arbitrario.
    

> Questo permette di **impersonare un altro dispositivo** sulla rete, simulandone l’identità fisica.

---

### **5. Attacco di MAC Spoofing**

L’attacco di **MAC spoofing** consiste nell’assumere il MAC address di un’altra macchina della rete per **ingannare gli switch e i router**.

#### **Fasi tipiche dell’attacco**

1. L’attaccante scopre il **MAC address della vittima**.
    
2. Configura manualmente la propria interfaccia di rete per utilizzare quel MAC.
    
3. Disconnette o spegne la macchina originale.
    
4. Lo switch associa ora quell’indirizzo MAC alla **porta dell’attaccante**.
    

#### **Contromisure**

- **Bloccare automaticamente** le porte dello switch quando un host viene disconnesso.
    
- **Disabilitare gli indirizzi MAC duplicati** nella rete.
    
- Implementare **filtri statici** o sistemi di **port security** sugli switch.
    

---

### **6. ARP Spoofing e Cache Poisoning**

Il protocollo ARP **non prevede autenticazione**: ogni host accetta come valida qualsiasi risposta ARP ricevuta, anche se **non ha fatto una richiesta**.  
Questo comportamento consente l’attacco di **ARP Spoofing** (o **ARP cache poisoning**).

#### **Meccanismo**

1. L’attaccante invia risposte ARP false, associando il proprio MAC a un IP legittimo (es. quello del gateway).
    
2. Le macchine vittima aggiornano la loro ARP cache con l’informazione falsificata.
    
3. Tutto il traffico diretto al gateway viene **inviato all’attaccante**, che può:
    
    - analizzarlo (**sniffing**),
        
    - modificarlo (**man-in-the-middle**),
        
    - o interromperlo (**denial of service**).
        

#### **Motivo della vulnerabilità**

- Gli host **non tracciano** le richieste inviate.
    
- Le **risposte ARP non sono autenticate**.
    
- Le implementazioni ARP sono **senza stato (stateless)**: ogni risposta aggiorna la cache, anche se non richiesta.
    

---

### **7. Difese contro l’ARP Spoofing**

Le principali contromisure includono:

- **Voci ARP statiche:** inserire manualmente nella cache le associazioni IP–MAC affidabili (soluzione efficace ma difficile da gestire in reti grandi).
    
- **DHCP Snooping:** consente di autorizzare solo i **server DHCP legittimi** e garantisce che gli host ricevano **solo gli IP assegnati**.
    
- **ARPWatch:** software che monitora la rete e **notifica via e-mail** quando si verifica un cambiamento sospetto nella mappatura IP–MAC.
    
- **Switch Port Security:** blocca la porta in caso di traffico anomalo o cambi di MAC improvvisi.
    

> Attenzione: l’ARP poisoning può anche avere **usi legittimi**, ad esempio per **reindirizzare gli utenti** verso un portale di autenticazione o registrazione in reti pubbliche.

---

### **8. Sintesi finale**

Gli attacchi **MAC Flooding** e **ARP Spoofing** dimostrano quanto sia fragile la sicurezza dei protocolli **di livello 2**.  
Entrambi sfruttano la **fiducia implicita** del traffico LAN e la mancanza di autenticazione nei meccanismi di base.

|**Attacco**|**Livello**|**Effetto**|**Contromisure**|
|---|---|---|---|
|**MAC Flooding**|Link Layer|Trasforma lo switch in un hub, consentendo lo sniffing.|Limitazione CAM, port security.|
|**MAC Spoofing**|Link Layer|Impersonazione di un host legittimo.|Filtri MAC, disabilitazione duplicati.|
|**ARP Spoofing**|Link Layer / Network|Avvelenamento della cache ARP e MITM.|ARPWatch, DHCP snooping, ARP statiche.|

> In sintesi, la **protezione delle reti locali** passa dall’implementazione di meccanismi di **autenticazione, monitoraggio e isolamento** — poiché gli attacchi di livello 2 non vengono fermati dai firewall di livello superiore.

---