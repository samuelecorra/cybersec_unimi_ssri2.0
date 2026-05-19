# **Esercitazione Fine UD2 – Soluzioni**

Soluzioni dei 25 quesiti del file `DAM_M2_U2_FINE_UD_DOMANDE.pdf`, basate sulle lezioni dell'Unità Didattica 2 (Protocolli di rete TCP/IP).

---

## **Quesito 1 — Strati del modello TCP/IP e corrispondenza con OSI**

Il modello TCP/IP è composto da **quattro strati**, mentre il modello OSI ne prevede sette. La corrispondenza è la seguente:

| Modello OSI            | Modello TCP/IP        |
| ---------------------- | --------------------- |
| 7 – Applicazione       |                       |
| 6 – Presentazione      | **Applicazione**      |
| 5 – Sessione           |                       |
| 4 – Trasporto          | **Trasporto**         |
| 3 – Rete               | **Inter-rete**        |
| 2 – Collegamento dati  |                       |
| 1 – Fisico             | **Interfaccia di rete** |

I livelli applicativi 5-6-7 di OSI sono unificati nello strato Applicazione del TCP/IP; i livelli 1-2 di OSI confluiscono nell'Interfaccia di rete.

---

## **Quesito 2 — Header speciali previsti da TCP/IP**

Oltre allo header IP fisso, la suite TCP/IP prevede tre header specifici a seconda della modalità di comunicazione:

- **UDP (User Datagram Protocol)** → header semplice per il *best effort* (invio non affidabile).
- **TCP (Transmission Control Protocol)** → header complesso per il *reliable flow* (comunicazione affidabile, orientata alla connessione).
- **ICMP (Internet Control Message Protocol)** → header dedicato ai messaggi di controllo e diagnostica della rete.

---

## **Quesito 3 — Struttura di incapsulamento di un frame TCP**

Un frame Ethernet pronto per essere spedito ha la seguente struttura, dall'interno verso l'esterno:

```
+----------------+-----------+-----------+------+----------------+
| Ethernet header| Header IP | Header TCP| Dati | Ethernet trailer|
+----------------+-----------+-----------+------+----------------+
```

I dati applicativi vengono incapsulati progressivamente: prima nello header TCP, poi nello header IP, infine nel frame Ethernet (header + trailer).

---

## **Quesito 4 — Header IPv4: campi e descrizione**

Lo header IPv4 è organizzato in parole da 32 bit:

```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version|  IHL  | Type of Service|        Total Length           |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|        Identification          |Flags|     Fragment Offset     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|  Time to Live |    Protocol    |        Header Checksum        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Source IP Address (32)                     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                 Destination IP Address (32)                   |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options                     |    Padding    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                            Dati                                |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

| Campo | Bit | Descrizione |
|---|---|---|
| **Versione** | 4 | Versione del protocollo (4 = IPv4). |
| **IHL (Internet Header Length)** | 4 | Numero di parole da 32 bit dello header (in genere 5). |
| **Type of Service (ToS)** | 8 | Parametro opzionale per la qualità del servizio (QoS). |
| **Total Length** | 16 | Lunghezza totale del pacchetto in byte (header + dati). |
| **Identification** | 16 | Identificatore univoco del datagramma, usato per la ricomposizione dei frammenti. |
| **Flags** | 3 | Bit di controllo: include il flag DF (*Don't Fragment*) e MF (*More Fragments*). |
| **Fragment Offset** | 13 | Offset del frammento rispetto all'inizio del pacchetto originale (in unità da 8 byte). |
| **Time To Live (TTL)** | 8 | Numero massimo di router (hop) attraversabili; decrementato di 1 a ogni passaggio. |
| **Protocol** | 8 | Identifica il protocollo di livello superiore (1 = ICMP, 6 = TCP, 17 = UDP). |
| **Header Checksum** | 16 | Somma di controllo (complemento a 1) calcolata sul solo header. |
| **Source Address** | 32 | Indirizzo IP del mittente. |
| **Destination Address** | 32 | Indirizzo IP del destinatario. |
| **Options** | var. | Campi facoltativi (es. sicurezza, tracciamento). |
| **Padding** | var. | Riempimento per allineare lo header a multipli di 32 bit. |

---

## **Quesito 5 — Perché la dimensione massima del pacchetto IPv4 è di 64 KB**

Perché il campo **Total Length** dello header IPv4, che indica la dimensione complessiva del pacchetto (header + dati), è di **16 bit**.

Il valore massimo rappresentabile è quindi:

$$
2^{16} - 1 = 65\,535 \text{ byte} \approx 64 \text{ KB}
$$

---

## **Quesito 6 — Operazioni di un gateway su un pacchetto non risolto**

Quando un gateway (router) riceve un frame contenente un pacchetto IP per il quale l'host mittente non ha saputo risolvere l'indirizzo, esegue queste operazioni:

1. **Estrae il pacchetto IP** dal frame Ethernet.
2. **Esamina l'indirizzo IP di destinazione** del pacchetto.
3. Si verificano due possibili situazioni:
   - **A — Risoluzione possibile**: il router sa tradurre l'indirizzo IP nell'indirizzo hardware di una macchina su una delle reti a cui è collegato. Crea un nuovo frame su quella rete e inoltra il pacchetto.
   - **B — Risoluzione non possibile**: il router non sa risolvere l'indirizzo. Crea allora un frame diretto a un altro router (vicino), posto su una delle reti a cui è connesso, e gli inoltra il pacchetto.
4. **Decrementa di 1 il campo TTL** dello header IP. Se il TTL raggiunge 0, il pacchetto viene scartato (drop).

---

## **Quesito 7 — Classi di indirizzamento IP e subnet mask di default**

Gli indirizzi IPv4 a 32 bit sono organizzati in cinque classi, identificate dal prefisso binario iniziale:

```
Classe A:  0 | net_ID (7 bit)  | host_ID (24 bit)
Classe B:  10 | net_ID (14 bit) | host_ID (16 bit)
Classe C:  110 | net_ID (21 bit) | host_ID (8 bit)
Classe D:  1110 | group_ID (28 bit)            (Multicast)
Classe E:  1111 | riservata (27 bit)           (Sperimentale)
```

| Classe | Primo byte | Subnet mask di default | Uso |
|---|---|---|---|
| A | 1 – 126 | **255.0.0.0** (/8) | Grandi reti |
| B | 128 – 191 | **255.255.0.0** (/16) | Reti medie |
| C | 192 – 223 | **255.255.255.0** (/24) | Piccole reti |
| D | 224 – 239 | — | Multicast |
| E | 240 – 255 | — | Riservata/sperimentale |

---

## **Quesito 8 — Tre fattori che rendono TCP/IP il più diffuso**

I tre fattori principali sono:

1. **TCP/IP è il protocollo usato su Internet**: chi vuole connettersi a Internet deve necessariamente parlare TCP/IP.
2. **TCP/IP è instradabile**: i pacchetti possono attraversare reti diverse passando da un router all'altro.
3. **Supporto universale**: tutti i sistemi operativi moderni supportano TCP/IP, consentendo la comunicazione fra sistemi di fornitori differenti.

---

## **Quesito 9 — Scopo dello strato di applicazione TCP/IP**

Lo strato di applicazione **si interfaccia con l'utente (e con le applicazioni)** e invia i dati da trasmettere allo **strato di trasporto sottostante**, che provvede a suddividerli in pacchetti. È il livello in cui operano i protocolli applicativi come HTTP, FTP, SMTP, DNS, ecc.

---

## **Quesito 10 — Comando Win 2000 per controllare l'indirizzo IP**

Il comando è **`ipconfig`** (eseguito dal Prompt dei comandi). Con l'opzione `ipconfig /all` mostra anche maschera di sottorete, gateway, DNS e altri parametri.

---

## **Quesito 11 — Cos'è il gateway predefinito**

Il **gateway predefinito** è un **router IP collegato ad almeno un'altra rete remota**. Quando il mittente di un pacchetto IP non riesce a risolvere localmente l'indirizzo del destinatario (perché si trova su una rete diversa dalla propria), invia il pacchetto al gateway predefinito, che si incarica di inoltrarlo verso la destinazione.

---

## **Quesito 12 — Classi di indirizzi privati**

Le **classi di indirizzi privati** sono blocchi di indirizzi IP **non instradabili su Internet**, riservati all'uso all'interno di **reti locali** i cui computer non debbono essere direttamente visibili da Internet.

| Classe | Intervallo privato |
|---|---|
| A | `10.0.0.0` – `10.255.255.255` |
| B | `172.16.0.0` – `172.31.255.255` |
| C | `192.168.0.0` – `192.168.255.255` |

**Caratteristiche e scopo**:
- Non sono unici a livello globale (più organizzazioni possono usare gli stessi indirizzi internamente).
- I router di Internet scartano i pacchetti diretti verso questi indirizzi.
- Servono a creare reti locali isolate, con maggiore sicurezza (le macchine non sono direttamente raggiungibili da Internet).
- L'accesso a Internet richiede un meccanismo di traduzione come il **NAT** (Network Address Translation).

---

## **Quesito 13 — Cos'è il DHCP**

Il **DHCP (Dynamic Host Configuration Protocol)** è un **programma/protocollo di attribuzione dinamica degli indirizzi IP**, che mantiene un database di parametri di configurazione validi per l'intera rete. Il server DHCP assegna automaticamente a ogni host che ne fa richiesta:

- indirizzo IP,
- maschera di sottorete,
- gateway predefinito,
- server DNS,
- e altri parametri opzionali,

per un periodo di tempo limitato (**lease**), rinnovabile.

---

## **Quesito 14 — Vantaggi del DHCP rispetto agli indirizzi statici**

I due vantaggi principali sono:

1. **Uso efficiente degli indirizzi IP**: gli indirizzi sono assegnati solo quando servono e tornano nel pool quando non più utilizzati. Particolarmente utile con indirizzi pubblici, che sono limitati e costosi.
2. **Eliminazione degli errori di configurazione manuale**: nessun host viene configurato a mano, quindi spariscono i tipici errori (indirizzi duplicati, maschere sbagliate, gateway errato), e si supporta facilmente la mobilità.

---

## **Quesito 15 — Significato di `196.20.55.10/24`**

La notazione `/24` (notazione **CIDR**) indica che i primi **24 bit** dell'indirizzo identificano la rete. Equivale quindi alla subnet mask:

$$
255.255.255.0
$$

ovvero la **maschera di default di una classe C**. L'indirizzo `196.20.55.10` appartiene a una rete `196.20.55.0/24`.

---

## **Quesito 16 — CIDR della Classe B (128/2) e della Classe C**

L'intera **Classe B** è caratterizzata dai due bit iniziali **`10`** (i bit che la distinguono da A, C, D, E). Il valore decimale corrispondente al primo byte fissato a `10000000` è **128**, e i bit fissi sono **2**. Quindi tutta la classe B in CIDR si esprime come:

$$
128.0.0.0/2
$$

Per la **Classe C** i tre bit iniziali sono **`110`** (primo byte fissato a `11000000` = 192) e i bit fissi sono **3**. L'espressione CIDR è quindi:

$$
192.0.0.0/3
$$

---

## **Quesito 17 — Cos'è il multihoming**

Il **multihoming** è una proprietà delle schede di rete moderne che possono essere **configurate per corrispondere a più indirizzi IP** sulla stessa interfaccia (fino a circa 5 indirizzi). Ogni indirizzo può anche essere associato a un **gateway predefinito differente**, abilitando scenari di failover, multi-VLAN o server con più ruoli.

---

## **Quesito 18 — Net_ID validi per classe A o B**

Analizzando il primo byte:

- **196.200.3.0** → primo byte 196 → classe C → **NON valido** per A o B.
- **10.0.0.0** → primo byte 10 → classe A privata → **valido**, ID di rete di classe A privato per la rete.
- **200.20.1.0** → primo byte 200 → classe C → **NON valido** per A o B.
- **150.20.0.0** → primo byte 150 → classe B → **valido** come net_ID di classe B (anche se coincide con un indirizzo pubblico registrato).

In sintesi, **10.0.0.0** (classe A privata) e **150.20.0.0** (classe B) possono essere usati. Gli altri sono di classe C.

---

## **Quesito 19 — Stessa subnet senza conoscere la maschera (classless)**

**No**, non è possibile. Nel caso **classless** (CIDR) **non si possono fare assunzioni** sulla subnet mask di default basate sulla classe dell'indirizzo, perché la maschera può avere una lunghezza arbitraria. Senza conoscere esplicitamente la subnet mask, non si può calcolare il Net_ID dei due indirizzi e quindi non si può stabilire se essi appartengono alla stessa sottorete.

---

## **Quesito 20 — Effetto del flag DF = 1 su un pacchetto da frammentare**

Il flag **DF (Don't Fragment)** indica che il pacchetto non può essere frammentato. Se un router si trova a dover frammentare un pacchetto con DF = 1 (perché la sua MTU è inferiore alla dimensione del pacchetto), il **datagramma viene scartato** e il router **genera un messaggio ICMP** di tipo *Fragmentation Needed and Don't Fragment was Set* indirizzato al mittente, in modo che possa adattare la dimensione dei pacchetti successivi.

---

## **Quesito 21 — Verifica stessa subnet (255.255.248.0)**

Si convertono gli indirizzi in binario e si calcola **`IP AND maschera`**.

**Indirizzo 1: 129.10.157.32**

```
  10000001 . 00001010 . 10011101 . 00100000   (129.10.157.32)
AND
  11111111 . 11111111 . 11111000 . 00000000   (255.255.248.0)
= 10000001 . 00001010 . 10011000 . 00000000   → 129.10.152.0
```

**Indirizzo 2: 129.10.158.85**

```
  10000001 . 00001010 . 10011110 . 01010101   (129.10.158.85)
AND
  11111111 . 11111111 . 11111000 . 00000000   (255.255.248.0)
= 10000001 . 00001010 . 10011000 . 00000000   → 129.10.152.0
```

I due Net_ID coincidono (`129.10.152.0`), quindi i due indirizzi sono **sulla stessa sottorete**.

---

## **Quesito 22 — Indirizzi in binario e classe di appartenenza**

| Indirizzo | Binario | Primo byte | Classe |
|---|---|---|---|
| **145.32.59.24** | `10010001.00100000.00111011.00011000` | 145 ∈ [128, 191] | **B** (/16) |
| **200.42.129.16** | `11001000.00101010.10000001.00010000` | 200 ∈ [192, 223] | **C** (/24) |
| **14.82.19.54** | `00001110.01010010.00010011.00110110` | 14 ∈ [1, 126] | **A** (/8) |

---

## **Quesito 23 — Pacchetti scartati dai router di Internet**

| Indirizzo destinazione | Risultato | Motivo |
|---|---|---|
| (a) **10.1.1.5** | **Scartato** | Indirizzo privato di classe A (`10.0.0.0/8`), non instradabile su Internet. |
| (b) **172.30.3.102** | **Scartato** | Indirizzo privato di classe B (range `172.16.0.0 – 172.31.255.255`), non instradabile. |
| (c) **192.169.100.210** | **Instradato** | `192.169.x.x` **non** rientra nel range privato di classe C (`192.168.0.0/16`): è un indirizzo pubblico regolare. |

> Attenzione: `192.169.x.x` è facile da confondere con `192.168.x.x`, ma **solo** `192.168.0.0 – 192.168.255.255` è privato.

---

## **Quesito 24 — Aggregazione CIDR di /24**

Scriviamo i quattro indirizzi in binario e individuiamo il prefisso comune:

```
212.56.132.0/24   11010100 . 00111000 . 10000100 . 00000000
212.56.133.0/24   11010100 . 00111000 . 10000101 . 00000000
212.56.134.0/24   11010100 . 00111000 . 10000110 . 00000000
212.56.135.0/24   11010100 . 00111000 . 10000111 . 00000000
                  ─────────────────────────────────────────
Prefisso comune:  11010100 . 00111000 . 100001  (22 bit)
```

I primi **22 bit** sono identici (gli ultimi due bit del terzo byte variano). L'aggregazione CIDR è quindi:

$$
\boxed{212.56.132.0/22}
$$

---

## **Quesito 25 — Tabelle di indirizzamento (Classe A, B, C)**

Formule:

- **Numero di subnet** = $2^s$ (effettivi tra parentesi: $2^s - 2$, escludendo subnet con tutti 0 e tutti 1).
- **Numero di host per subnet** = $2^h - 2$ (esclusi *network address* e *broadcast*).

### **Classe A**

| Network Bits | Subnet Mask | Numero di Subnet | Numero di Host |
|---|---|---|---|
| /8  | 255.0.0.0           | 0                  | 16 777 214 |
| /9  | 255.128.0.0         | 2 (0)              | 8 388 606  |
| /10 | 255.192.0.0         | 4 (2)              | 4 194 302  |
| /11 | 255.224.0.0         | 8 (6)              | 2 097 150  |
| /12 | 255.240.0.0         | 16 (14)            | 1 048 574  |
| /13 | 255.248.0.0         | 32 (30)            | 524 286    |
| /14 | 255.252.0.0         | 64 (62)            | 262 142    |
| /15 | 255.254.0.0         | 128 (126)          | 131 070    |
| /16 | 255.255.0.0         | 256 (254)          | 65 534     |
| /17 | 255.255.128.0       | 512 (510)          | 32 766     |
| /18 | 255.255.192.0       | 1 024 (1 022)      | 16 382     |
| /19 | 255.255.224.0       | 2 048 (2 046)      | 8 190      |
| /20 | 255.255.240.0       | 4 096 (4 094)      | 4 094      |
| /21 | 255.255.248.0       | 8 192 (8 190)      | 2 046      |
| /22 | 255.255.252.0       | 16 384 (16 382)    | 1 022      |
| /23 | 255.255.254.0       | 32 768 (32 766)    | 510        |
| /24 | 255.255.255.0       | 65 536 (65 534)    | 254        |
| /25 | 255.255.255.128     | 131 072 (131 070)  | 126        |
| /26 | 255.255.255.192     | 262 144 (262 142)  | 62         |
| /27 | 255.255.255.224     | 524 288 (524 286)  | 30         |
| /28 | 255.255.255.240     | 1 048 576 (1 048 574) | 14      |
| /29 | 255.255.255.248     | 2 097 152 (2 097 150) | 6       |
| /30 | 255.255.255.252     | 4 194 304 (4 194 302) | 2       |

### **Classe B**

| Network Bits | Subnet Mask | Numero di Subnet | Numero di Host |
|---|---|---|---|
| /16 | 255.255.0.0         | 0               | 65 534 |
| /17 | 255.255.128.0       | 2 (0)           | 32 766 |
| /18 | 255.255.192.0       | 4 (2)           | 16 382 |
| /19 | 255.255.224.0       | 8 (6)           | 8 190  |
| /20 | 255.255.240.0       | 16 (14)         | 4 094  |
| /21 | 255.255.248.0       | 32 (30)         | 2 046  |
| /22 | 255.255.252.0       | 64 (62)         | 1 022  |
| /23 | 255.255.254.0       | 128 (126)       | 510    |
| /24 | 255.255.255.0       | 256 (254)       | 254    |
| /25 | 255.255.255.128     | 512 (510)       | 126    |
| /26 | 255.255.255.192     | 1 024 (1 022)   | 62     |
| /27 | 255.255.255.224     | 2 048 (2 046)   | 30     |
| /28 | 255.255.255.240     | 4 096 (4 094)   | 14     |
| /29 | 255.255.255.248     | 8 192 (8 190)   | 6      |
| /30 | 255.255.255.252     | 16 384 (16 382) | 2      |

### **Classe C**

| Network Bits | Subnet Mask | Numero di Subnet | Numero di Host |
|---|---|---|---|
| /24 | 255.255.255.0       | 0           | 254 |
| /25 | 255.255.255.128     | 2 (0)       | 126 |
| /26 | 255.255.255.192     | 4 (2)       | 62  |
| /27 | 255.255.255.224     | 8 (6)       | 30  |
| /28 | 255.255.255.240     | 16 (14)     | 14  |
| /29 | 255.255.255.248     | 32 (30)     | 6   |
| /30 | 255.255.255.252     | 64 (62)     | 2   |

---

### **Riferimenti alle lezioni**

- **L1 – Internet Protocol (IP)**: quesiti 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 22.
- **L2 – Introduzione alla configurazione IP**: quesiti 11, 12, 18, 23.
- **L3 – Complementi di IP**: quesiti 15, 16, 17, 19, 20, 21, 24, 25.
- **L4 – Progetto di inter-reti**: quesito 25 (tabelle FLSM per classi A, B, C).
- **L5 – Assegnazione dinamica di indirizzi IP**: quesiti 13, 14.
- **L6 – DHCP**: quesiti 13, 14.
