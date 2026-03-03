# **Lezione 2: Indirizzi IPv6**

---

### **1. Struttura degli indirizzi IPv6**

Gli indirizzi IPv6 sono lunghi **128 bit** e vengono scritti come **otto gruppi da 16 bit** ciascuno, in **notazione esadecimale**, separati da due punti.

Esempio:

```
5f1b:df00:ce3e:e200:0020:0800:2078:e3e3
```

Ogni gruppo rappresenta **16 bit**, e i bit più significativi (a sinistra) determinano **il tipo di indirizzo** e la sua **portata** (locale, globale, multicast, ecc.).

> IPv6 non utilizza la notazione decimale puntata come IPv4, ma una rappresentazione esadecimale molto più compatta ed estensibile.

---

### **2. Tipi di indirizzi IPv6**

IPv6 distingue principalmente tre categorie di indirizzi:

|**Tipo di indirizzo**|**Descrizione**|
|---|---|
|**Unicast**|Identifica un singolo host o interfaccia. Il pacchetto viene inviato a un solo destinatario.|
|**Multicast**|Identifica un gruppo di nodi; il pacchetto viene consegnato a tutti i membri del gruppo.|
|**Anycast**|Identifica più host ma il pacchetto è inviato **solo al nodo più vicino** (in termini di routing).|

---

### **3. Indirizzi unicast globali aggregabili**

Gli **indirizzi unicast globali** sono simili agli indirizzi pubblici di IPv4:  
sono **univoci su Internet** e possono essere **aggregati** in blocchi per semplificare l’instradamento.

![](imgs/Pasted%20image%2020260225154929.png)

La loro struttura prevede:

- **Prefisso globale** (fornito dall’ISP o dall’ente di assegnazione),
    
- **Identificatore di sottorete**,
    
- **Identificatore di interfaccia** (64 bit finali).
    

> Questa organizzazione gerarchica riduce le dimensioni delle tabelle di routing e semplifica la gestione degli indirizzi.

---

### **4. Indirizzi IPv6 mappati IPv4**

Gli **indirizzi mappati IPv4** permettono la **convivenza tra IPv4 e IPv6**.  
Servono per consentire la comunicazione tra:

- un host che supporta **sia IPv4 che IPv6**, e
    
- un host che supporta **solo IPv4**.

![](imgs/Pasted%20image%2020260225154956.png)

La struttura di un indirizzo IPv6 mappato IPv4 è:

$$  
\underbrace{0000\dots0}_{80\text{ bit}} ; \underbrace{1111\dots1}_{16\text{ bit}} ; \text{indirizzo IPv4 (32 bit)}  
$$

Esempio:

```
::ffff:192.168.0.1
```

> Il prefisso `::ffff:` segnala che si tratta di un indirizzo IPv4 “incapsulato” in IPv6.  
> In questo modo, un sistema dual stack può gestire entrambe le comunicazioni senza modifiche applicative.

---

### **5. Funzionamento con il DNS**

Quando un’applicazione IPv6 interroga il **Domain Name System (DNS)** per risolvere un nome host:

- Se l’host possiede **solo un indirizzo IPv4**,  
    → il DNS **genera automaticamente un indirizzo IPv6 mappato IPv4**.
    
- Il **kernel del sistema operativo** riconosce che l’indirizzo è speciale e **utilizza IPv4** per la comunicazione effettiva.
    

> Questo meccanismo permette di usare un’unica interfaccia applicativa anche in ambienti misti IPv4/IPv6.

---

### **6. Indirizzi IPv6 compatibili IPv4**

Gli **indirizzi compatibili IPv4** sono simili ai mappati, ma hanno una funzione diversa:  
permettono a un host IPv6 di **comunicare con altri host IPv6 anche se la rete intermedia non supporta IPv6**.

In questo caso, il sistema crea **un tunnel automatico IPv6 su IPv4**, incapsulando il pacchetto IPv6 dentro un pacchetto IPv4.

![](imgs/Pasted%20image%2020260225155111.png)

Struttura:

$$  
\underbrace{0000\dots0}_{96\text{ bit}} ; \text{indirizzo IPv4 (32 bit)}  
$$

Esempio:

```
::192.168.0.1
```

> In pratica, il kernel si accorge della compatibilità e **esegue il tunneling** in modo trasparente, senza intervento dell’utente.

---

### **7. Tunneling**

Il **tunneling IPv6 su IPv4** è il processo che consente di **trasportare pacchetti IPv6 dentro pacchetti IPv4**.  
È eseguito automaticamente dal **kernel** quando vengono utilizzati **indirizzi compatibili IPv4**.

![](imgs/Pasted%20image%2020260225155147.png)

Questo permette di far transitare il traffico IPv6 anche attraverso router o tratti di rete **non ancora aggiornati** al nuovo protocollo.

> Il tunneling è una delle tecniche chiave di **transizione da IPv4 a IPv6**, insieme a dual stack e NAT64.

---

### **8. Programmazione socket IPv6**

Il passaggio a IPv6 ha comportato anche **novità nella programmazione di rete**.  
Le socket IPv6 introducono nuove **famiglie di indirizzi e strutture dati**:

|**Elemento**|**Descrizione**|
|---|---|
|`AF_INET6`|Nuova famiglia di indirizzi per IPv6.|
|`in6_addr`|Tipo di dato per rappresentare indirizzi IPv6.|
|`sockaddr_in6`|Struttura che contiene indirizzo, porta e altre informazioni IPv6.|

> Queste strutture sostituiscono le controparti `AF_INET`, `in_addr` e `sockaddr_in` di IPv4, ma restano compatibili per agevolare il porting del software.

---

### **9. Server doppio (dual-stack)**

Oggi è essenziale realizzare **server capaci di gestire sia IPv4 che IPv6**.  
Il sistema operativo, che dispone di **pile di protocolli per entrambi**, si occupa automaticamente del **demultiplexing dei pacchetti** in arrivo.

- Se arriva un pacchetto IPv4, il kernel lo instrada alla pila IPv4.
    
- Se arriva un pacchetto IPv6, lo gestisce con la pila IPv6.
    

> Spesso, il server usa **indirizzi IPv6 mappati IPv4** per identificare in modo unificato i client provenienti da entrambi i protocolli.

---

### **10. Programmazione mista IPv4–IPv6**

Quando un’applicazione di rete riceve un pacchetto:

- il **kernel** determina automaticamente **il tipo di indirizzo** (IPv4 o IPv6);
    
- se necessario, effettua la **conversione o il tunneling**.

![](imgs/Pasted%20image%2020260225155233.png)

Gli sviluppatori possono anche verificare il tipo di connessione tramite apposite **funzioni di libreria**, ad esempio per riconoscere se un client è IPv4 o IPv6.

> In pratica, grazie al kernel, un programma moderno “parla IPv6 a tutti”, ma resta pienamente compatibile con IPv4.

---

### **11. Conclusione**

IPv6 introduce un **nuovo paradigma di indirizzamento**, ma mantiene compatibilità con IPv4 grazie a:

- **indirizzi mappati e compatibili**,
    
- **DNS adattivo**,
    
- **tunneling automatico**,
    
- **server dual-stack**.
    

> La transizione verso IPv6 non è uno strappo netto, ma un’evoluzione progressiva, progettata per garantire continuità operativa su Internet.