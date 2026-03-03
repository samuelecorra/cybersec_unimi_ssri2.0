# **Lezione 2: Topologie ridondanti e problemi di broadcast**

### **1. Il concetto di topologia ridondante**

Una **topologia ridondante** è una rete che prevede **più percorsi alternativi** tra due nodi.  
È lo stesso principio delle **strade di una città**: se una via è chiusa per lavori, è spesso possibile raggiungere la destinazione seguendo un percorso diverso.

![](imgs/Pasted%20image%2020260213012926.png)

![](imgs/Pasted%20image%2020260213013001.png)

Quindi i vari ponti romani sono un ottimo esempio di topologia ridondante: se uno è chiuso, ciò non impedisce la traversata del Tevere:

![](imgs/Pasted%20image%2020260213013201.png)

Nelle reti locali (LAN), la ridondanza serve a:

- **aumentare l’affidabilità**: un guasto a un link o a uno switch non interrompe la comunicazione;
    
- **migliorare la resilienza**: la rete può continuare a funzionare automaticamente attraverso un percorso alternativo;
    
- **permettere aggiornamenti o manutenzioni** senza fermare il servizio.
    

Tuttavia, introdurre percorsi multipli può creare **problemi di instabilità** se non vengono gestiti correttamente.  
Prima di capire perché, analizziamo i diversi **tipi di traffico** che attraversano una rete a livello 2.

---

### **2. Tipi di traffico a livello 2**

Nel livello **Data Link (Ethernet)** possiamo distinguere quattro tipologie di traffico:

#### **a) Unicast conosciuto**

È il caso normale:  
l’indirizzo MAC di destinazione **è già noto** nella tabella dello switch.  
Il frame viene inviato **solo alla porta corretta**.

#### **b) Unicast sconosciuto**

Lo switch **non conosce ancora** la destinazione (non ha la voce in tabella MAC).  
In questo caso, **inoltra il frame su tutte le porte**, tranne quella di origine, fino a quando non “impara” l’indirizzo corretto.

![](imgs/Pasted%20image%2020260213013831.png)

#### **c) Multicast**

Il frame è destinato a un **insieme di nodi specifici**, che condividono un determinato indirizzo MAC multicast.  
Lo switch inoltra il frame **solo alle porte associate a quel gruppo**.

#### **d) Broadcast**

È il tipo di traffico più “rumoroso”: il frame è **inviato a tutti i nodi della rete**, tranne quello di origine.  
Serve per scopi come:

- la **scoperta di dispositivi** (ARP request, DHCP discover, ecc.);
    
- la **propagazione di informazioni comuni** (ad esempio nei protocolli di controllo).
    

---

### **3. Come gli switch apprendono gli indirizzi MAC**

Ogni switch mantiene una **tabella di associazione MAC → porta**, costruita in modo dinamico:

1. Quando riceve un frame, legge l’indirizzo MAC **sorgente**.
    
2. Se l’indirizzo non è presente in tabella, lo **registra** associandolo alla porta d’ingresso.
    
3. Quando successivamente riceve un frame diretto a quel MAC, sa **da dove inoltrarlo**.
    

Se l’indirizzo di destinazione **non è ancora noto**, lo switch invia il frame **in broadcast temporaneo** (unicast sconosciuto), così da poter poi aggiornare la tabella una volta ricevuta la risposta.

Questo comportamento è corretto in reti semplici, ma in **reti ridondanti** può diventare pericoloso.

---

### **4. Topologie commutate ridondanti**

Quando più switch sono collegati tra loro **con percorsi multipli**, la rete forma dei **cicli logici**.  
Ciò significa che **un frame può percorrere la rete in loop**, venendo replicato all’infinito.

Ecco perché:

- gli **switch non hanno memoria di “frame già visti”**;
    
- ogni frame broadcast o multicast viene **inviato su tutte le porte**, tranne quella d’ingresso;
    
- in presenza di un ciclo, il frame torna indietro e viene **trasmesso di nuovo**, generando **copie multiple**.
    

Questo fenomeno può portare a un **collasso della rete**.

---

### **5. Il problema del Broadcast Storm**

Il **broadcast storm** è una condizione in cui il traffico broadcast (o multicast) cresce in modo incontrollato, fino a saturare la rete.

Immaginiamo due switch (A e B) collegati tra loro con **più link ridondanti**:

![](imgs/Pasted%20image%2020260213014124.png)

1. L’host X invia un **broadcast ARP** per cercare l’indirizzo MAC del router.
    
2. Lo switch A inoltra il broadcast **su tutte le sue porte**.
    
3. Lo switch B riceve il frame e lo **rilancia su tutte le sue porte**, incluso il collegamento di ritorno verso A.
    
4. A riceve nuovamente lo stesso frame e lo **trasmette di nuovo**, innescando un **loop infinito**.
    

In pochi millisecondi la rete diventa congestionata:

- la **banda è saturata da frame duplicati**;
    
- i dispositivi **non riescono più a comunicare**;
    
- le tabelle MAC diventano **instabili**, poiché gli switch vedono lo stesso indirizzo sorgente arrivare da porte diverse.
    

Questo è l’effetto **valanga tipico del broadcast storm**.

---

### **6. Trasmissione multipla di frame unicast**

Anche il traffico **unicast** può essere duplicato in una rete ridondante.

![](imgs/Pasted%20image%2020260213014720.png)

Esempio pratico:

- L’host X vuole inviare un frame al router Y.
    
- Gli switch A e B hanno **dimenticato l’indirizzo MAC** del router (entry scaduta).
    
- L’host X ha invece ancora il MAC di Y nella sua cache ARP e invia il frame.
    
- Poiché gli switch non sanno più dove si trova Y, **trasmettono il frame su tutte le porte**.
    
- Risultato: **Y riceve più copie dello stesso frame**, da percorsi diversi.
    

Queste copie multiple non solo **consumano banda**, ma possono anche **confondere protocolli superiori**, che interpretano i duplicati come nuovi messaggi.

---

### **7. Le conseguenze di una rete ridondante non gestita**

Senza un meccanismo di controllo dei cicli, una topologia ridondante può portare a:

- **loop infiniti di frame**,
    
- **instabilità delle tabelle MAC**,
    
- **tempeste di broadcast** (broadcast storm),
    
- **duplicazione dei pacchetti** e quindi **errori logici** nei protocolli di livello superiore.
    

Il risultato finale è una **rete inutilizzabile**, anche se fisicamente integra.

---

### **8. Soluzioni possibili**

Per evitare questi problemi, le reti Ethernet moderne adottano il **protocollo Spanning Tree (STP)**, che:

- rileva automaticamente i cicli nella topologia;
    
- **disattiva i collegamenti ridondanti** non necessari;
    
- mantiene comunque una **struttura ad albero priva di loop**.
    

In questo modo, se un collegamento principale si guasta, STP può **riattivare automaticamente** un percorso alternativo, mantenendo la **ridondanza controllata**.

(Lo studio di STP e delle sue varianti sarà oggetto della lezione successiva.)

---

### **9. Sintesi concettuale**

- Le **topologie ridondanti** migliorano l’affidabilità, ma possono generare **loop e broadcast storm**.
    
- Gli **switch apprendono gli indirizzi MAC** dinamicamente, ma in presenza di cicli i frame possono rientrare e duplicarsi.
    
- I **broadcast** e gli **unknown unicast** sono i più pericolosi in reti non controllate.
    
- Il **broadcast storm** è un effetto valanga che congestiona la rete.
    
- Serve un **protocollo di controllo dei cicli**, come **Spanning Tree (STP)**, per garantire stabilità e continuità del servizio.