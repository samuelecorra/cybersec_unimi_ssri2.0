
---

# **Lezione 1: Internet Protocol (IP)**

### **1. Introduzione generale**

Il **protocollo IP (Internet Protocol)** è il cuore logico della suite TCP/IP, la base di tutta la comunicazione su Internet.  
Serve a **suddividere, etichettare e instradare i pacchetti** di dati da un host a un altro, attraverso una rete o un insieme di reti.

---

### **1.1 TCP/IP e modello ISO/OSI**

La suite di protocolli **TCP/IP** copre le funzionalità dei **livelli 3 e 4** del modello ISO/OSI:

- **Livello di rete (3)** → gestisce l’instradamento e l’indirizzamento dei pacchetti;
    
- **Livello di trasporto (4)** → gestisce la comunicazione logica e il controllo del flusso.
    

I protocolli applicativi basati su TCP/IP (HTTP, FTP, SMTP, ecc.) implementano invece le funzioni dei **livelli superiori** (5–7).

Schema di corrispondenza:

|ISO/OSI|TCP/IP|
|---|---|
|7 – Applicazione|Applicazione|
|6 – Presentazione|–|
|5 – Sessione|–|
|4 – Trasporto|TCP / UDP|
|3 – Rete|IP|
|2 – Collegamento dati|Network Interface|
|1 – Fisico|Physical Media|

Tutti i protocolli TCP/IP sono **di pubblico dominio** e vengono definiti dalla **IETF (Internet Engineering Task Force)** tramite i documenti **RFC (Request For Comments)**, consultabili liberamente online.

---

### **1.2 Un primo sguardo a TCP/IP**

**TCP/IP (Transmission Control Protocol / Internet Protocol)** è un software di rete universale, installato in tutti i sistemi operativi moderni, che consente alle applicazioni di comunicare **tramite un protocollo instradabile**, ovvero capace di attraversare più reti.

La suite definisce:

- una **porzione di header fisso**,
    
- e tre **header specifici** per diverse modalità di comunicazione:

![](imgs/Pasted%20image%2020260224205656.png)

|Protocollo|Funzione|Tipo di servizio|
|---|---|---|
|**UDP (User Datagram Protocol)**|Invio semplice e veloce|_Best Effort_|
|**TCP (Transmission Control Protocol)**|Comunicazione affidabile|_Reliable Flow_|
|**ICMP (Internet Control Message Protocol)**|Messaggi di controllo e diagnostica|Controllo di rete|

Esempio di incapsulamento:

$$  
\text{Ethernet Header} + \text{Header IP} + \text{Header TCP/UDP} + \text{Dati} + \text{Ethernet Trailer}  
$$

---

### **1.3 Lo header IPv4**

Quando un’applicazione invia dati, il software TCP/IP **suddivide l’informazione in pacchetti IP**.  
Ogni pacchetto contiene:

- un **header fisso IPv4** (con indirizzi mittente e destinatario),
    
- e i **dati** dell’applicazione.

![](imgs/Pasted%20image%2020260224205714.png)

#### **Dimensione massima del pacchetto**

Ogni pacchetto IP può avere al massimo **64 KB** di lunghezza totale, indicata in un campo da **16 bit** dello header.

L’indirizzo IP, di **32 bit**, è un indirizzo **software** che deve essere specificato dal mittente.

---

### **1.3.1 Campi dello header IPv4 in dettaglio**

| Campo                            | Significato                                                                                          |
| -------------------------------- | ---------------------------------------------------------------------------------------------------- |
| **Versione**                     | Indica la versione del protocollo (4 per IPv4).                                                      |
| **IHL (Internet Header Length)** | Numero di parole da 32 bit nello header (di solito 5).                                               |
| **Type of Service (ToS)**        | Parametro opzionale per la qualità del servizio (QoS).                                               |
| **Total Length**                 | Lunghezza totale in byte (header + dati).                                                            |
| **Identification**               | Numero univoco (16 bit) che identifica il pacchetto per la ricomposizione in caso di frammentazione. |
| **Flags**                        | 3 bit di controllo: uno è il flag “Don’t Fragment (DF)”.                                             |
| **Fragment Offset**              | Spiazzamento (offset) del frammento rispetto all’inizio del pacchetto originale.                     |
| **TTL (Time To Live)**           | Numero massimo di router (hop) attraversabili. Decrementato a ogni passaggio.                        |
| **Protocol**                     | Identifica il protocollo successivo (1=ICMP, 6=TCP, 17=UDP).                                         |
| **Checksum**                     | Somma di controllo (complemento a 1) dello header.                                                   |
| **Indirizzo IP mittente**        | Identifica l’interfaccia di origine.                                                                 |
| **Indirizzo IP destinatario**    | Identifica l’interfaccia di destinazione.                                                            |
| **Opzioni**                      | Campi facoltativi (es. per sicurezza o tracciamento).                                                |

---

### **1.4 Recapito IP su Ethernet**

Per comprendere come IP funzioni su Ethernet, supponiamo che ogni host mantenga una **tabella di corrispondenza IP ↔ MAC**.

1. **Se la traduzione è nota**  
    Il pacchetto viene affidato alla **scheda di rete**, che lo incapsula in un **frame Ethernet** con l’indirizzo MAC del destinatario e lo invia.  
    Tutte le schede ricevono il frame, ma **solo quella con l’indirizzo corretto lo elabora**.
    
2. **Se la traduzione è nota ma la rete è collegata ad altre tramite router**  
    Il pacchetto IP viene inserito in un frame destinato al **gateway predefinito**, cioè al router configurato nella rete locale.
    

---

### **1.5 Il ruolo dei router**

Un **router** (o gateway IP) collega **due o più reti locali**.  
Può essere:

- un dispositivo dedicato (es. router Cisco),
    
- oppure un computer configurato per l’instradamento IP (es. Linux o Windows con due schede di rete).
    

A differenza dei bridge, i router **non si occupano di frame**, ma di **pacchetti IP**.

#### **Funzionamento:**

- Il router **estrae il pacchetto** IP dal frame ricevuto e ne esamina l’indirizzo di destinazione.
    
- Se sa come raggiungere quella rete, **invia il pacchetto** su un’altra interfaccia.
    
- Se non conosce la destinazione, **inoltra il pacchetto a un altro router**.
    

Ogni volta che un pacchetto passa da un router, il campo **TTL viene decrementato di 1**.  
Quando $TTL = 0$, il pacchetto viene **scartato (drop)**.

---

### **1.6 Instradamento e filtraggio**

Quando un router riceve un pacchetto, deve decidere **a quale collega inviarlo**.  
La scelta può essere:

- **obbligata**, se conosce un solo percorso,
    
- **dinamica**, se può scegliere tra più router collegati.
    

Il router usa il **netid** dell’indirizzo IP di destinazione per determinare il percorso più efficiente.

Inoltre, i router possono:

- **scambiarsi informazioni** sui percorsi migliori (routing dinamico),
    
- **filtrare pacchetti**, ad esempio bloccando l’accesso di certi host a determinate reti (funzione di firewall di base).
    

---

### **1.7 Inter-reti**

Le **inter-reti** (internetworks) sono insiemi di reti locali collegate tramite router.  
Esse presentano tre caratteristiche fondamentali:

1. **Comunicazione tra reti diverse:** gli host appartenenti a subnet differenti possono comunicare.
    
2. **Eterogeneità dei collegamenti:** le reti locali possono usare tecnologie diverse (Ethernet, Token Ring, Wi-Fi, ecc.).
    
3. **Scalabilità:** il numero di host collegabili è virtualmente illimitato, entro i limiti imposti dal protocollo IP.

![](imgs/Pasted%20image%2020260224210458.png)

---

### **1.8 Indirizzi IP**

Ogni interfaccia di rete possiede un **indirizzo IP univoco a 32 bit (4 byte)**.  
Gli indirizzi IP sono suddivisi in **cinque classi**, denominate A, B, C, D, ed E:

| Classe | Prefisso binario | Scopo        | Struttura                     |
| ------ | ---------------- | ------------ | ----------------------------- |
| **A**  | `0`              | Grandi reti  | 7 bit net_ID, 24 bit host_ID  |
| **B**  | `10`             | Reti medie   | 14 bit net_ID, 16 bit host_ID |
| **C**  | `110`            | Piccole reti | 21 bit net_ID, 8 bit host_ID  |
| **D**  | `1110`           | Multicast    | 28 bit group_ID               |
| **E**  | `1111`           | Sperimentale | Riservata                     |

#### **Classi e intervalli decimali**

| Classe | Primo byte (decimale) | Intervallo tipico           |
| ------ | --------------------- | --------------------------- |
| A      | 0 – 127               | 1.0.0.0 – 126.255.255.255   |
| B      | 128 – 191             | 128.0.0.0 – 191.255.255.255 |
| C      | 192 – 223             | 192.0.0.0 – 223.255.255.255 |
| D      | 224 – 239             | Multicast                   |
| E      | 240 – 255             | Riservato                   |

Gli indirizzi IP seguono la **proprietà di prefisso**, cioè la parte iniziale (net_ID) identifica la rete di appartenenza.

![](imgs/Pasted%20image%2020260224210711.png)

---

### **1.9 Address Resolution Protocol (ARP)**

Il protocollo **ARP (Address Resolution Protocol)** serve a **tradurre un indirizzo IP in un indirizzo fisico (MAC)**.

#### **Procedura ARP:**

1. Il mittente controlla la **cache ARP** locale per verificare se conosce già l’associazione IP ↔ MAC.
    
2. Se non trova la corrispondenza, invia un **messaggio ARP broadcast** sulla rete locale:  
    “Chi ha l’indirizzo IP X.X.X.X? Risponda con il proprio indirizzo MAC.”
    
3. L’host che possiede quell’indirizzo risponde in unicast con il suo MAC.
    
4. Entrambi aggiornano la **cache ARP** per memorizzare la corrispondenza.
    

Esempio di risposta:

> “Io sono 200.20.1.50 e il mio indirizzo Ethernet è 20-4C-4F-4F-50-20.”

---

### **1.10 Maschere di sottorete (Subnet Mask)**

La **maschera di sottorete** serve a distinguere la parte di un indirizzo che identifica la rete da quella che identifica l’host.

Applicando un’operazione **AND logico** tra indirizzo IP e maschera si ottiene il **Net ID**:

$$  
\text{Net ID} = \text{IP }  \text{ AND }  \text{ Subnet Mask}  
$$

Maschere **di default** per le classi:

| Classe | Maschera di default |
| ------ | ------------------- |
| A      | 255.0.0.0           |
| B      | 255.255.0.0         |
| C      | 255.255.255.0       |

---

### **1.11 Subnetting**

Quando la divisione standard non è sufficiente, si può **modificare la maschera** per creare **sottoreti** più piccole (subnet).

Esempio:

- Maschera modificata: **255.255.255.128**  
    → divide una rete di classe C in **due subnet**, ciascuna con 128 indirizzi.
    


Per determinare la subnet di appartenenza di un host si applica l’operazione logica **AND** tra il suo indirizzo IP e la **Subnet Mask**:

$$
\text{Net ID} = \text{IP} \;\textbf{AND}\; \text{Subnet Mask}
$$

Nel caso della maschera:

$$
255.255.255.128 = /25
$$

si ottengono due possibili Network ID:

- per gli indirizzi con ultimo ottetto compreso tra **0 e 127**:
$$
\text{Net ID} = 196.20.70.0
$$

- per gli indirizzi con ultimo ottetto compreso tra **128 e 255**:
$$
\text{Net ID} = 196.20.70.128
$$

Ogni host calcola quindi il proprio **Net ID** tramite l’operazione AND e fa lo stesso per l’indirizzo IP del destinatario.

- Se:
$$
\text{Net ID}_{mittente} = \text{Net ID}_{destinatario}
$$
allora i due host appartengono **alla stessa subnet** e la comunicazione avviene direttamente.

- Se invece i Net ID sono diversi, il pacchetto deve essere inviato al **router (gateway)**.

---

### **Conclusione**

In questa lezione abbiamo introdotto i principi fondamentali dell’**Internet Protocol (IPv4)**:

- struttura dello header,
    
- funzionamento dei router e del recapito IP,
    
- sistema di indirizzamento classful,
    
- ruolo del protocollo ARP e delle maschere di sottorete.


---

![](ironmath-demo/programma/4.%20Università%20STEM/cybersecurity/anno2/5_Reti%20di%20Calcolatori/M2_Protocolli_rete_TCP_IP/UD2/imgs/DAMNO_M2_U2_L1_D.pdf)