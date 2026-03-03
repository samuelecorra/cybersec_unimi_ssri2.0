# **Lezione 4: Progetto di inter-reti**

### **1. Introduzione**

Progettare un’**inter-rete IP** significa pianificare nel dettaglio come verranno **distribuiti e organizzati gli indirizzi IP** tra le varie sottoreti di un’organizzazione.  
Un buon piano di indirizzamento deve garantire:

- **scalabilità**, per gestire l’espansione futura;
    
- **efficienza**, per evitare sprechi di indirizzi;
    
- **chiarezza**, per semplificare la configurazione dei router e degli host.
    

---

### **2. Fasi del progetto di una inter-rete**

La progettazione segue cinque passi logici e sequenziali.

#### **1. Determinazione del numero di subnet_ID necessari**

Si stima **quante sottoreti serviranno**, tenendo conto non solo delle esigenze attuali ma anche della crescita futura dell’organizzazione (nuove sedi, reparti, VLAN, ecc.).

#### **2. Determinazione del numero massimo di host per sottorete**

Per ogni subnet, si valuta il numero **massimo di dispositivi** che dovranno essere connessi (computer, stampanti, server, access point).  
Anche in questo caso si considera **un margine di crescita**.

#### **3. Scelta della maschera di sottorete**

In base al numero di sottoreti e al numero di host per sottorete, si sceglie una **maschera appropriata**.

$$  
\begin{cases}  
N_{subnet} = 2^s - 2 \\\\  
N_{host} = 2^h - 2  
\end{cases}  
$$

dove:

- $s$ = bit usati per il **subnet_ID**,
    
- $h$ = bit rimanenti per l’**host_ID**.
    

#### **4. Calcolo dei subnet_ID da utilizzare**

Si elencano le reti derivate (es. `/25`, `/26`, `/27`, …) e si assegna un **subnet_ID distinto** a ciascuna.

#### **5. Calcolo degli host_ID e assegnazione degli indirizzi**

Per ogni sottorete si individuano:

- **indirizzo di rete** (host_ID = 0),
    
- **indirizzo di broadcast** (host_ID = tutti 1),
    
- **intervallo di indirizzi assegnabili**.
    

---

### **3. FLSM e VLSM**

#### **3.1 FLSM – Fixed Length Subnet Mask**

Nel modello **FLSM**, tutte le sottoreti **usano la stessa maschera**.  
È la soluzione più semplice, ma spesso **inefficiente**: sottoreti piccole sprecano spazio d’indirizzamento.

#### **Vantaggi**

- Facilità di calcolo e di gestione.
    
- Struttura regolare e prevedibile.
    

#### **Svantaggi**

- Spreco di indirizzi quando le reti non hanno lo stesso numero di host.
    
- Difficoltà di espansione modulare.
    

---

#### **3.2 VLSM – Variable Length Subnet Mask**

Il modello **VLSM** permette di **attribuire maschere diverse** a sottoreti diverse, tutte derivate dallo stesso net_ID di partenza.  
Questa tecnica consente di **adattare la dimensione di ogni subnet** alle reali esigenze di host, ottimizzando l’uso dello spazio IP.

#### **Vantaggi**

- Utilizzo molto più efficiente degli indirizzi.
    
- Possibilità di combinare sottoreti grandi e piccole nella stessa inter-rete.
    

#### **Svantaggi**

- Progettazione più complessa.
    
- Richiede router che supportino **routing classless (CIDR)**.
    

---

### **4. Esempio di progettazione VLSM**

#### **Dati iniziali**

- Net_ID: `196.68.20.0` (rete di classe C)
    
- Sottoreti da creare: **A, B, C, D**
    
- Fabbisogno:
    
    - A → 100 host
        
    - B → 100 host
        
    - C → 24 host
        
    - D → 24 host
        

---

#### **4.1 Soluzione con FLSM**

Con una maschera fissa **255.255.255.192** (/26):

- ogni subnet dispone di **62 host utilizzabili** $(2^6 - 2 = 62)$;
    
- si ottengono **4 sottoreti** $(2^2 = 4)$.
    

Tuttavia:

- A e B richiedono 100 host → 62 non bastano;
    
- servirebbero due reti contigue, con **spreco di spazio IP**.
    

In sintesi:  
la rete dispone di 4×62 = **248 indirizzi validi** (sufficienti in totale), ma **mal distribuiti**.

---

#### **4.2 Soluzione con VLSM**

Con VLSM, si applicano **maschere differenti** a seconda della dimensione richiesta.

|Sottorete|Maschera|Bit prefisso|Host utilizzabili|Range IP esempio|
|---|---|---|---|---|
|A|255.255.255.128|/25|126|196.68.20.0 – 196.68.20.127|
|B|255.255.255.128|/25|126|196.68.20.128 – 196.68.20.255|
|C|255.255.255.224|/27|30|196.68.21.0 – 196.68.21.31|
|D|255.255.255.224|/27|30|196.68.21.32 – 196.68.21.63|

In questo modo:

- le reti A e B (più grandi) ricevono più spazio;
    
- le reti C e D (più piccole) usano una maschera più lunga, evitando sprechi.
    

---

### **5. Vantaggi progettuali del VLSM**

1. **Massima efficienza** nell’uso dello spazio IP: si usano solo gli indirizzi necessari.
    
2. **Flessibilità modulare**: reti di dimensioni diverse convivono senza problemi.
    
3. **Supporto al routing gerarchico**: i router possono aggregare (supernetting) reti contigue riducendo la dimensione delle tabelle.
    
4. **Scalabilità futura**: è possibile espandere alcune subnet senza modificare le altre.
    

---

### **6. Sintesi generale del processo**

|Passaggio|Obiettivo|Esempio pratico|
|---|---|---|
|1|Determinare numero di subnet|Uffici, reparti, piani, sedi|
|2|Calcolare numero host per subnet|PC, server, stampanti|
|3|Scegliere maschere (FLSM/VLSM)|/25, /26, /27…|
|4|Calcolare subnet_ID|196.68.20.0, .64, .128…|
|5|Assegnare host_ID|1–62, 65–126, ecc.|

---

### **7. Conclusione**

Il **progetto di inter-rete IP** è il momento in cui la teoria dell’indirizzamento diventa pratica.  
Le tecniche **FLSM** e **VLSM** permettono di creare reti scalabili e razionali:

- la prima semplice ma rigida,
    
- la seconda più complessa ma estremamente efficiente.
    

La progettazione corretta di subnet e maschere è la base per realizzare **infrastrutture IP ottimizzate**, pronte per essere configurate nei router e nei gateway.

---

## **Dispensa – Progetto di inter-reti (Approfondimento)**

### **1. Introduzione**

La progettazione di una **inter-rete IP** è un processo che richiede pianificazione logica e capacità di previsione.  
L’obiettivo è **assegnare gli indirizzi IP** in modo ordinato, scalabile e coerente con la topologia fisica e logica della rete.

Il processo si articola in cinque fasi principali, a cui segue un approfondimento sulle tecniche **FLSM** e **VLSM**.

---

## **1.1 Le fasi progettuali**

### **Fase 1 – Determinazione del numero di subnet_ID**

Serve un **subnet_ID univoco** per:

- ogni rete locale (fisica o virtuale);
    
- ogni connessione esterna (es. verso Internet).
    

#### **Esempio**

In un’inter-rete con cinque reti denominate **A, B, C, D, E**:

- A, B e D rappresentano reti locali collegate da router;
    
- C ed E sono connessioni Internet con indirizzi pubblici.
    

In totale servono **cinque subnet_ID distinti**.

---

### **Fase 2 – Determinazione del numero massimo di host**

Per ogni subnet bisogna calcolare **quanti indirizzi IP saranno necessari**, considerando:

- ogni scheda di rete su computer e stampanti TCP/IP;
    
- ogni interfaccia di router (un router connesso a due subnet usa **due indirizzi IP**).
    

#### **Esempio pratico**

Supponiamo:

- due sottoreti, ciascuna con 50 host;
    
- un router tra le due reti;
    
- un net_ID pubblico di classe C: `202.20.16.0`.
    

Con subnet mask **255.255.255.192** (/26):

- otteniamo **2 sottoreti**,
    
- ognuna con **62 host validi** $(2^6 - 2)$.
    

Se la rete cresce e servono più di 62 host per subnet, bisognerà **modificare la maschera** e riconfigurare tutto il sistema: operazione lunga e rischiosa.

> 🔸 Una pianificazione errata può obbligare a ridisegnare l’intera rete.

---

### **Fase 3 – Scelta della maschera di sottorete**

Con **FLSM** (Fixed Length Subnet Mask), la maschera è **uguale per tutte le sottoreti**.

Per calcolare una maschera:

- si parte dalla **maschera di default** della classe assegnata;
    
- si aggiungono bit a 1 alla parte di rete per ottenere più subnet.
    

#### **Esempio**

Net_ID classe B: `202.20.0.0`  
Maschera di default: `255.255.0.0`

In binario:

```
11111111.11111111.00000000.00000000
```

Aggiungendo 3 bit (per ottenere 6 sottoreti):

```
11111111.11111111.11100000.00000000
```

→ Nuova maschera: **255.255.224.0**

---

### **Fase 4 – Calcolo dei subnet_ID**

Ci sono tre metodi:

1. **Calcolo manuale**  
    Si elencano tutte le combinazioni binarie dei bit aggiuntivi, scartando quelle con tutti 0 e tutti 1.  
    Esempio:  
    $$  
    000,;001,;010,;011,;100,;101,;110,;111  
    $$  
    Scartando 000 e 111, restano 6 subnet.
    
2. **Procedura incrementale (abbreviata)**
    
    - Si osserva l’ottetto della maschera che contiene i nuovi bit.
        
    - Si prende il **bit 1 meno significativo** e lo si converte in decimale.
        
    - Questo valore è il **delta** (incremento) tra un subnet_ID e il successivo.
        
    - Si somma ripetutamente il delta al net_ID di partenza.
        
    
    #### **Esempio**
    
    Maschera: `255.255.224.0` → bit addizionali = `11100000`  
    Il bit meno significativo vale `00100000` = **32**, quindi  
    $$\Delta = 32$$  
    → subnet successive: `.32`, `.64`, `.96`, `.128`, `.160`, `.192`
    
3. **Consultazione tabelle**  
    Si usano tabelle precompilate (come in appendice) per evitare errori di calcolo.
    

---

### **Fase 5 – Calcolo degli host_ID**

Gli **indirizzi IP validi** di ciascuna sottorete partono dal primo indirizzo **dopo il subnet_ID** e terminano **prima del successivo subnet_ID**.

#### **Esempio**

Maschera: `255.255.224.0`  
Subnet_ID calcolati per `202.20.0.0`:

|Subnet|Range IP utilizzabili|
|---|---|
|202.20.32.0|202.20.32.1 – 202.20.63.254|
|202.20.64.0|202.20.64.1 – 202.20.95.254|
|202.20.96.0|202.20.96.1 – 202.20.127.254|
|202.20.128.0|202.20.128.1 – 202.20.159.254|
|202.20.160.0|202.20.160.1 – 202.20.191.254|
|202.20.192.0|202.20.192.1 – 202.20.223.254|

---

## **2. Variable Length Subnet Mask (VLSM)**

A volte la maschera a lunghezza fissa (FLSM) **limita l’efficienza**.  
Con **VLSM**, ogni sottorete può avere **una maschera diversa**, sempre derivata dallo stesso net_ID.

### **Esempio pratico**

Richiesta:

- 4 sottoreti (A, B, C, D)
    
- Fabbisogno:
    
    - A: 100 host
        
    - B: 8 host
        
    - C: 8 host
        
    - D: 4 host
        
- Net_ID classe C: `196.68.20.0`
    

#### **Soluzione FLSM**

Maschera `255.255.255.224` → 4 subnet da 30 host ciascuna.  
Totale indirizzi = 120, ma la distribuzione non soddisfa i fabbisogni (A ne richiede 100).

#### **Soluzione VLSM**

|Rete|Maschera|Bit prefisso|Host validi|Range IP|
|---|---|---|---|---|
|A|255.255.255.128|/25|126|196.68.20.129 – 254|
|B|255.255.255.240|/28|14|196.68.20.17 – 31|
|C|255.255.255.240|/28|14|196.68.20.33 – 47|
|D|255.255.255.240|/28|14|196.68.20.49 – 63|

> Nota: in VLSM si possono usare subnet_ID **con tutti 1**, ma **non con tutti 0**.

---

## **3. Appendice: Tabelle di riferimento**

### **Tabella 1 – Classe A**

|Bit aggiuntivi|Sottoreti $(2^n - 2)$|Host per subnet $(2^{24-n} - 2)$|Maschera|
|---|---|---|---|
|0|0|16.777.214|255.0.0.0|
|2|2|4.194.302|255.192.0.0|
|3|6|2.097.150|255.224.0.0|
|4|14|1.048.574|255.240.0.0|
|5|30|524.286|255.248.0.0|
|6|62|262.142|255.252.0.0|
|7|126|131.070|255.254.0.0|
|8|254|65.534|255.255.0.0|

---

### **Tabella 2 – Classe B**

|Bit aggiuntivi|Sottoreti $(2^n - 2)$|Host per subnet $(2^{16-n} - 2)$|Maschera|
|---|---|---|---|
|0|0|65.534|255.255.0.0|
|2|2|16.382|255.255.192.0|
|3|6|8.190|255.255.224.0|
|4|14|4.094|255.255.240.0|
|5|30|2.046|255.255.248.0|
|6|62|1.022|255.255.252.0|
|7|126|510|255.255.254.0|
|8|254|254|255.255.255.0|

---

### **Tabella 3 – Classe C**

|Bit aggiuntivi|Sottoreti $(2^n - 2)$|Host per subnet $(2^{8-n} - 2)$|Maschera|
|---|---|---|---|
|0|0|254|255.255.255.0|
|2|2|62|255.255.255.192|
|3|6|30|255.255.255.224|
|4|14|14|255.255.255.240|
|5|30|6|255.255.255.248|
|6|62|2|255.255.255.252|

---

## **4. Conclusione**

Il **progetto di inter-reti IP** è una competenza fondamentale per chiunque lavori in ambito networking o cybersecurity.  
Saper calcolare subnet, maschere e range validi significa **sapere progettare l’ossatura logica di Internet**.

La scelta tra **FLSM** e **VLSM** dipende dal contesto:

- reti semplici e statiche → FLSM;
    
- reti complesse, scalabili e dinamiche → VLSM.



---

ESERCIZI:

A una organizzazione viene assegnato il net_id 151.144.0.0. L'organizzazione vuole creare 15 sottoreti e poter collegare fino a 1000 host per sottorete.


ESERCIZIO 2 
A una organizzazione viene assegnato il net_id 112.0.0.0. L'organizzazione al momento dispone di 5 sottoreti, ma vuole dividere ciascuna delle attuali sottoreti in 25 nuove sottoreti, per rendere più facile l'amministrazione dell’intera rete. Quanti bit vengono usati per la maschera di sottorete? 


SOLUZIONE Si tratta di un net_id di classe A. Per le 5 sottoreti la maschera originaria sarà stata di 11 bit. Volendo ora 125 sottoreti bisognerà portare la maschera a 15 bit per un totale di 126 sottoreti