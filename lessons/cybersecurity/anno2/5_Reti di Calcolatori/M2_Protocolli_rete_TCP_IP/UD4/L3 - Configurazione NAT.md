## **Lezione 3: Configurazione NAT**

### **1. Aspetti critici del NAT**

Il **NAT (Network Address Translation)** è una tecnologia molto utile, ma introduce anche diversi **problemi tecnici e limiti strutturali** che devono essere conosciuti per configurarlo in modo corretto e sicuro.

---

### **1.1 Prestazioni**

Ogni volta che il NAT modifica l’intestazione di un pacchetto IP, deve **ricalcolare i checksum** (controlli di integrità) per garantire la coerenza dei dati trasmessi:

- Quando viene modificato l’**indirizzo IP** sorgente o destinazione, è necessario **ricalcolare il checksum IP**.
    
- Quando viene modificato il **numero di porta TCP o UDP**, è necessario **ricalcolare anche il checksum TCP o UDP**.
    

Questo processo comporta un **costo computazionale**: il router o firewall NAT deve eseguire operazioni aggiuntive per ogni pacchetto, riducendo leggermente le prestazioni complessive della rete.

> In reti con traffico elevato, questo può introdurre **latenza** o **saturazione** della CPU del dispositivo NAT.

---

### **1.2 Frammentazione dei pacchetti**

Un altro problema riguarda la **frammentazione IP**.  
Se un pacchetto è stato frammentato **prima** di raggiungere il dispositivo NAT, ogni frammento deve essere gestito in modo coerente.

Il NAT deve assicurarsi che **tutti i frammenti** di uno stesso pacchetto ricevano:

- lo stesso indirizzo IP tradotto, e
    
- lo stesso numero di porta.
    

Altrimenti, i frammenti non potranno essere correttamente ricomposti dal destinatario, causando **perdita o corruzione dei dati**.

---

### **1.3 Connessione punto-punto**

Uno degli effetti più evidenti del NAT è la **perdita della raggiungibilità universale** tipica di Internet.

Nel modello originario di Internet, ogni host con un IP pubblico può comunicare **direttamente** con qualunque altro.  
Il NAT rompe questa simmetria:

- Gli host **interni** alla rete privata possono iniziare connessioni verso Internet,
    
- ma gli host **esterni** **non possono iniziare** connessioni verso dispositivi interni, poiché questi non hanno un indirizzo IP pubblico raggiungibile.
    

> Questo rende difficile, ad esempio, ospitare un server web o FTP all’interno di una rete privata senza configurare **port forwarding** o **regole DNAT**.

Il problema si amplifica quando **due host situati in reti private diverse** devono comunicare tra loro: entrambe sono dietro NAT, e nessuna delle due può essere raggiunta direttamente.

---

### **1.4 Indirizzi IP nei dati applicativi**

Molte applicazioni includono **indirizzi IP nei dati del livello applicativo** (cioè nel payload).  
Esempi tipici: FTP, SIP, H.323, alcuni protocolli di videoconferenza o giochi online.

In questi casi, il NAT **non può limitarsi a tradurre l’header IP**, perché l’indirizzo originale compare anche all’interno del messaggio applicativo.  
Di conseguenza, la comunicazione può fallire o funzionare in modo anomalo.

#### **Soluzioni possibili**

Alcuni dispositivi NAT evoluti implementano funzioni di **Application-Level Gateway (ALG)**, che analizzano il contenuto dei pacchetti applicativi.  
Se trovano un indirizzo IP, lo traducono coerentemente con la tabella NAT.

> Tuttavia, questa operazione aumenta ulteriormente il carico di elaborazione e può introdurre vulnerabilità.

---

### **2. Configurazione NAT in Linux**

In ambiente Linux, la gestione delle regole NAT è affidata al **framework Netfilter** e al comando **iptables**.  
Le regole NAT vengono definite nella **tabella `nat`**, suddivisa in tre catene principali:

|Catena|Fase di elaborazione|Funzione|
|---|---|---|
|**PREROUTING**|Prima dell’instradamento|Modifica la destinazione dei pacchetti in ingresso (DNAT)|
|**POSTROUTING**|Dopo l’instradamento|Modifica l’indirizzo sorgente dei pacchetti in uscita (SNAT / MASQUERADE)|
|**OUTPUT**|Pacchetti generati localmente|Gestisce il NAT per processi locali|

---

### **2.1 SNAT – Static NAT**

Usato per tradurre **indirizzi sorgente privati** in **indirizzi pubblici** specifici.

```bash
iptables -t nat -A POSTROUTING -s 10.0.1.2 -j SNAT --to-source 128.143.71.21
```

➡️ Traduzione statica: il traffico proveniente da `10.0.1.2` uscirà sempre con IP pubblico `128.143.71.21`.

---

### **2.2 Pool di indirizzi IP**

Per condividere un insieme di indirizzi pubblici tra più host:

```bash
iptables -t nat -A POSTROUTING -s 10.0.1.0/24 -j SNAT --to-source 128.128.71.0-128.128.71.30
```

➡️ Il router sceglierà dinamicamente un IP disponibile nel range specificato per ciascuna connessione in uscita.

---

### **2.3 Migrazione tra provider (ISP)**

Quando un’azienda cambia ISP e quindi range di IP pubblici, è sufficiente **aggiornare le regole NAT**, senza riconfigurare gli host interni:

```bash
iptables -t nat -R POSTROUTING -s 10.0.1.0/24 -j SNAT --to-source 128.195.4.0-128.195.4.254
```

➡️ Nessuna modifica necessaria nei dispositivi della LAN, solo nel router NAT.

---

### **2.4 IP Masquerading (PAT/NAPT)**

Utilizzato quando la rete dispone di un **solo indirizzo IP pubblico**:

```bash
iptables -t nat -A POSTROUTING -s 10.0.1.0/24 -o eth1 -j MASQUERADE
```

➡️ Il router sostituirà l’indirizzo sorgente privato con il proprio indirizzo pubblico dell’interfaccia `eth1`.  
Le traduzioni multiple vengono gestite attraverso numeri di **porta diversi**.

---

### **2.5 Bilanciamento del carico (DNAT)**

Per distribuire il traffico in ingresso verso **più server interni**:

```bash
iptables -t nat -A PREROUTING -i eth1 -j DNAT --to-destination 10.0.1.2-10.0.1.4
```

➡️ Il traffico ricevuto dall’interfaccia `eth1` (esterna) viene distribuito tra i server `10.0.1.2`, `10.0.1.3` e `10.0.1.4` secondo logica round-robin o algoritmi di bilanciamento.

---

### **3. Conclusione**

Il NAT è uno strumento potente ma delicato:  
per funzionare correttamente richiede un’attenta configurazione e la consapevolezza dei suoi limiti strutturali.

Abbiamo visto che:

- può **degradare le prestazioni** per via dei ricalcoli di checksum;
    
- crea **problemi di raggiungibilità punto-punto**;
    
- può interferire con protocolli applicativi che includono indirizzi IP nel payload;
    
- ma può anche essere configurato in modo **flessibile e potente** grazie alle funzionalità di `iptables`.
    

Nelle mani di un amministratore consapevole, il NAT rimane un **pilastro pratico della connettività IPv4**, in attesa che IPv6 renda superflue molte di queste soluzioni di traduzione.


---

![](imgs/Pasted%20image%2020251125065012.png)

![](imgs/Pasted%20image%2020251125065038.png)

