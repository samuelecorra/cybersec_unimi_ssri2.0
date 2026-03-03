# **Lezione 2: Protocolli del livello di rete**

### **1. Il problema della frammentazione**

Quando un pacchetto è **più grande della dimensione massima** che un frame può contenere, nasce un problema:  
come trasmettere quel pacchetto senza superare i limiti fisici della rete?

#### **Soluzione: frammentazione**

Il **software di rete** suddivide (frammenta) il pacchetto in **più parti** e aggiunge a ciascun frammento un **numero identificativo**, detto _numero di frammento_ (spiazzamento/offset che dir si voglia).  
Questo numero consente al destinatario di **ricomporre** correttamente il pacchetto originale una volta ricevuti tutti i frammenti.

![](imgs/Pasted%20image%2020260213040742.png)

Le applicazioni **non si accorgono** del processo di frammentazione: l’operazione è totalmente **trasparente** per loro, tranne per un lieve **rallentamento** dovuto alle operazioni di divisione e ricostruzione.

Schema:

$$  
\text{Pacchetto originale} \rightarrow  
\begin{cases}  
\text{Frammento 1} = \text{Header} + \text{Dati parziali} \\\\  
\text{Frammento 2} = \text{Header} + \text{Dati parziali}  
\end{cases}  
$$

Ogni frammento viene poi incapsulato in un **frame Ethernet**:

$$  
\text{Frame Ethernet} = \text{Ethernet header} + \text{Frammento} + \text{Ethernet trailer}  
$$

---

### **2. Il collo di bottiglia interno**

#### **2.1. Dove nasce**

All’interno dell’**host mittente**, per inviare un dato sulla rete, è necessario **copiare** l’informazione più volte tra zone di memoria:

1. **Dallo spazio dell’applicazione** a quello del **kernel** del sistema operativo (dove agisce il software di rete).
    
2. **Dal kernel** alla **scheda di rete**.
    

Queste copie sono eseguite dalla **CPU**, e quindi **non avvengono alla stessa velocità** della trasmissione sulla rete.  
La loro velocità dipende dal **bit rate del bus interno** dell’host, indicato con $b$.

Se il bus interno è più lento del canale di rete ($b < B$), si crea un **collo di bottiglia**: la rete potrebbe trasmettere più dati di quanti il sistema riesca a preparare.

---

#### **2.2. Calcolo del bit rate effettivo**

Il bit rate effettivo del collo di bottiglia interno si calcola come:

$$  
\text{Bit rate effettivo} = \frac{b}{k}  
$$

dove:

- $b$ = bit rate del bus di sistema;
    
- $k$ = numero di operazioni di copia necessarie (in genere $k = 2$).
    

**Esempio:**

Un host con bus PCI a **1056 MB/s** (≈ 8.45 Gbit/s) e $k = 2$ ha:

$$  
\frac{8.45}{2} = 4.22 \text{ Gbit/s}  
$$

Se la connessione Ethernet opera a **5 Gbit/s**, significa che:

$$  
B > \frac{b}{k}  
$$

👉 la rete **non viene sfruttata pienamente**, a causa della lentezza del bus interno.

---

### **3. Tecniche di ottimizzazione: Gather-Write**

Per ridurre le copie di memoria e migliorare le prestazioni, si utilizza una tecnica chiamata **Gather-Write**.

#### **Come funziona**

1. Il **software di rete** riceve dall’applicazione **puntatori** ai dati e all’header (indirizzi di memoria), invece di copiare tutto nel kernel.
    
2. Questi indirizzi vengono passati alla **scheda di rete**, che **ricompone il frame** completo **prima dell’invio**, usando:
    
    - accessi **DMA (Direct Memory Access)**, oppure
        
    - un **interrupt** hardware.

![](imgs/Pasted%20image%2020260213041155.png)

In pratica, il frame viene assemblato “al volo” al momento della trasmissione, risparmiando tempo e copie inutili.

#### **Vantaggi**

- Si elimina **una delle due copie** (dal kernel all’applicazione).
    
- La seconda copia avviene **al massimo rate** del bus interno.
    
- La tecnica può essere usata anche in ricezione, dove prende il nome di **Scatter-Read**.
    

In sintesi:

|Tecnica|Direzione|Copie risparmiate|Effetto|
|---|---|---|---|
|Gather-Write|Trasmissione|1|Maggiore efficienza|
|Scatter-Read|Ricezione|1|Minore overhead|

---

### **4. Gli indirizzi software**

Ogni pacchetto deve contenere **un indirizzo software** che identifica **il destinatario**.  
Questo indirizzo viene scritto nello **header** dal software di rete, in base alle informazioni fornite dall’applicazione mittente (spesso tramite input dell’utente).

#### **Caratteristiche principali**

- È una **sequenza di 32 bit** (tipicamente un indirizzo IP).
    
- Identifica **l’interfaccia software** di un host, non l’hardware.
    
- È **indipendente** dall’indirizzo Ethernet (che è fisico e a 48 bit).
    

⚠️ **Attenzione:**  
L’indirizzo software **non è l’indirizzo MAC**.  
Il primo appartiene alla **logica di rete (livello 3)**, il secondo alla **fisica della scheda (livello 2)**.

---

### **5. Differenza tra indirizzi Ethernet e software**

|Tipo di indirizzo|Bit|Identifica|Livello OSI|Stabilito|
|---|---|---|---|---|
|**Ethernet (MAC)**|48|Scheda di rete|Livello 2|In fabbrica|
|**Software (IP)**|32|Interfaccia software|Livello 3|In configurazione|

- **Più interfacce software** possono coesistere sulla **stessa scheda fisica**.
    
- L’indirizzo IP può essere **cambiato** (es. via DHCP), mentre l’indirizzo MAC in teoria **rimane fisso**.
    

---

### **6. Il problema del recapito**

#### **Situazione**

- L’applicazione mittente inserisce nello header **l’indirizzo software (IP)** del destinatario.
    
- Tuttavia, per inviare fisicamente il frame, la **scheda di rete** ha bisogno dell’**indirizzo Ethernet (MAC)** del destinatario.
    

**Domanda:**  
Come fa il software di rete a scoprire l’indirizzo MAC corrispondente all’indirizzo IP?

#### **Possibili soluzioni**

1. **Derivazione diretta:**  
    usare una sottosequenza dei 48 bit del MAC per formare l’indirizzo IP a 32 bit.  
    → **Non praticabile**, poiché romperebbe l’indipendenza tra livelli OSI.
    
2. **Tabelle di traduzione:**  
    ogni host mantiene una **tabella** che associa indirizzi IP e MAC (come la **tabella ARP**).
    
    - Queste tabelle possono essere **costruite e aggiornate** dinamicamente usando **broadcast Ethernet**.
        

---

### **7. Protocolli instradabili e non instradabili**

#### **Protocolli non instradabili**

Permettono la comunicazione **solo tra host nella stessa rete locale (LAN)**.  
Esempio: protocolli storici come **NetBEUI** (Microsoft) o **indirizzi Ethernet puri**.

#### **Protocolli instradabili**

Permettono la comunicazione **tra reti diverse**, purché connesse tramite **router** (instradatori).  
Esempi: **IP, IPX/SPX, TCP/IP**.

Questi protocolli rendono possibile la costruzione di **inter-reti (internetworks)**, cioè insiemi di più reti locali collegate.  
L’esempio più celebre è **Internet**, la rete delle reti.

---

### **8. Proprietà del prefisso e instradabilità**

Perché un protocollo sia **instradabile**, gli indirizzi devono rispettare una **proprietà di prefisso comune**.

#### **Condizioni fondamentali**

1. **Indirizzi nella stessa rete → stesso prefisso**
    
    Permette di testare se due host appartengono alla stessa rete confrontando i loro indirizzi.
    
    $$  
    \text{Stesso prefisso} \Rightarrow \text{recapito diretto via Ethernet}  
    $$
    
    In caso contrario, il pacchetto viene **inoltrato a un router**.
    
2. **Instradamento per reti, non per host**
    
    I router devono conoscere **solo il percorso verso la rete** del destinatario, non verso ogni singolo host.  
    Questo riduce enormemente le tabelle di routing.
    
3. **Ethernet non è instradabile**
    
    Guardando due indirizzi MAC, **non è possibile sapere** se appartengono alla stessa rete o meno.  
    Per questo, protocolli come IP introducono un livello logico aggiuntivo per gestire l’instradamento.
    

---

### **9. Conclusione**

Il livello di rete svolge un ruolo cruciale:  
traduce la logica delle applicazioni in flussi di pacchetti fisicamente trasmissibili.

Abbiamo visto:

- come il software di rete gestisce la **frammentazione**,
    
- come si ottimizza la comunicazione con tecniche come **gather-write**,
    
- e come la distinzione tra **indirizzi logici (IP)** e **fisici (MAC)** consenta la costruzione di reti globali interconnesse.
    

La lezione si conclude con un principio chiave:

> **Solo i protocolli instradabili permettono la comunicazione tra reti distinte, rendendo possibile Internet.**