# **Lezione 3: Introduzione a Ethernet**

### **1. Le reti ad accesso condiviso**

All’origine delle reti locali, i computer venivano **collegati tutti allo stesso cavo fisico**, condividendo lo stesso mezzo trasmissivo.  
Questo tipo di rete è detta **rete ad accesso condiviso**, perché ogni nodo **può ascoltare e inviare dati sullo stesso collegamento**.

Le strutture fisiche più comuni erano:

- il **bus**, in cui tutti i nodi sono collegati in linea retta a un unico cavo (terminato alle estremità da resistenze chiamate _terminatori_);

![](imgs/Pasted%20image%2020260212143347.png)
    
- l’**anello (ring)**, dove i nodi sono disposti in una struttura chiusa e i dati circolano in un’unica direzione.

![](imgs/Pasted%20image%2020260212143502.png)

In queste configurazioni:

- **ogni trasmissione** inviata da un nodo è **ricevuta da tutti gli altri**;
    
- **non esistono switch intermedi** che filtrano i pacchetti;
    
- diventa quindi necessario un **meccanismo di controllo dell’accesso** al cavo (MAC – _Medium Access Control_), per evitare che due nodi trasmettano contemporaneamente causando **collisioni**.
    

---

### **2. Tipi di protocolli MAC**

Nel tempo sono stati sviluppati diversi **modelli di gestione del canale condiviso**, ciascuno con vantaggi e limiti.

#### **a) Assegnamento fisso**

La **banda trasmissiva** viene divisa in **canali separati**, e ogni nodo usa una parte dedicata.  
Esempi classici:

- **TDM (Time Division Multiplexing)** → divisione del tempo in slot;
    
- **FDM (Frequency Division Multiplexing)** → divisione della frequenza in sottobande.
    

Ogni nodo ha un suo spazio “privato” nel tempo o nella frequenza.

#### **b) A contesa**

Tutti i nodi usano **lo stesso canale**.  
Se due nodi trasmettono nello stesso momento, si verifica una **collisione** e i dati vanno persi, obbligando a una ritrasmissione.  
È il principio alla base di **Ethernet (CSMA/CD)**.

#### **c) Basato su token**

Le stazioni si danno il turno grazie a un **token**, cioè un “gettone logico” che autorizza una sola stazione alla volta a trasmettere.  
Quando il token è libero, chi lo riceve può inviare i propri dati.  
Esempio: **Token Ring** (standard IEEE 802.5).

---

### **3. Storia di Ethernet**

Ethernet nacque nei **laboratori Xerox PARC** all’inizio degli anni ’70 grazie a **Bob Metcalfe** e il suo team.  
Nel **1978**, Xerox, DEC e Intel ne curarono la **standardizzazione**.  
Da allora, Ethernet è diventata **il principale standard di rete locale** al mondo.

Gli standard IEEE definiti per Ethernet specificano:

- il **livello fisico** (tipologia di cavi, connettori e segnali);
    
- il **tipo di protocollo MAC** usato per gestire l’accesso al mezzo.
    

---

### **4. Evoluzione degli standard Ethernet**

#### **a) Ethernet “storica” (IEEE 802.3)**

- Protocollo **CSMA/CD (Carrier Sense Multiple Access / Collision Detection)**;
    
- Velocità iniziale: **2 Mbps**, poi **10 Mbps**;
    
- Cablaggio su **cavo coassiale**;
    
- Lunghezza massima di un segmento: **500 m**;
    
- Fino a **4 segmenti** collegati da **ripetitori di segnale (repeater)**.
    

Questa prima versione è detta anche **10Base5** (“10 Mbps, Baseband, 500 m”).

#### **b) Ethernet “moderne”**

Con il tempo sono nati nuovi standard IEEE:

- **IEEE 802.3u (Fast Ethernet)** → 100 Mbps
    
- **IEEE 802.3z (Gigabit Ethernet)** → 1 Gbps
    
- versioni successive fino a **10G, 40G e 100G Ethernet**
    

Pur aumentando enormemente la velocità, **tutte le versioni sono retrocompatibili** e rispettano la struttura logica originale di Ethernet.

---

### **5. Il principio CSMA/CD**

Ethernet usa una strategia di accesso al mezzo chiamata **CSMA/CD**, sigla che descrive le tre funzioni chiave:

- **CS – Carrier Sense:**  
    ogni nodo **ascolta il canale** prima di trasmettere; invia solo se il mezzo è libero.
    
- **MA – Multiple Access:**  
    più nodi condividono lo stesso mezzo; tutti possono potenzialmente trasmettere.
    
- **CD – Collision Detection:**  
    se due nodi trasmettono contemporaneamente, **la collisione viene rilevata**.  
    Entrambi interrompono subito la trasmissione e ritentano dopo un tempo casuale (backoff esponenziale).
    

In sintesi:  
Ethernet si comporta come una **conversazione civile**:  
prima “ascolta” per vedere se qualcuno sta parlando, poi parla, e se due persone iniziano insieme, si fermano e riprovano dopo una pausa casuale.

---

### **6. Dalla topologia a bus alla topologia a stella**

Con l’evoluzione delle reti, si è passati dalla topologia a **bus** alla **stella**, più affidabile e semplice da gestire.

![](imgs/Pasted%20image%2020260212144056.png)

Ogni **host** è connesso tramite un cavo **RJ-45** a un dispositivo centrale chiamato **hub**.  
L’hub **non instrada** i pacchetti, ma **li replica su tutte le porte**, comportandosi come un rigeneratore di segnale.  

![](imgs/Pasted%20image%2020260212144208.png)

In pratica, tutti i nodi connessi allo stesso hub **appartengono allo stesso dominio di collisione**.

---

### **7. La regola 5-4-3 di Ethernet**

Per evitare ritardi eccessivi nella rilevazione delle collisioni, Ethernet classica impone la **regola 5-4-3**:

> Tra una qualunque coppia di host possono esserci al massimo **5 segmenti** di rete collegati da **4 repeater**, di cui **solo 3 possono ospitare dispositivi attivi**.

![](imgs/Pasted%20image%2020260212144343.png)

Questa regola nasce dal fatto che:

- il **tempo di propagazione** del segnale tra i due punti più lontani della rete  
    **non deve superare** la durata del **test di linea libera** (tempo minimo di rilevazione collisione);
    
- se la rete è troppo lunga o con troppi repeater, le collisioni **non vengono rilevate in tempo**,  
    portando a **errori di trasmissione invisibili**.
    

In formule semplificate, per garantire il corretto funzionamento del CSMA/CD:  
$$  
t_\text{propagazione} < t_\text{trasmissione\ minima}  
$$

dove:

- $t_\text{propagazione}$ è il tempo che impiega il segnale a percorrere la distanza massima del cavo,
    
- $t_\text{trasmissione minima}$ è il tempo necessario per inviare il frame minimo (512 bit).
    

---

### **8. Limiti della rete Ethernet “storica”**

L’Ethernet a bus con hub e repeater aveva due grandi limiti:

1. **Collisioni frequenti**, che aumentavano con il numero di nodi;
    
2. **Scalabilità ridotta**, perché non si potevano aggiungere segmenti oltre i limiti fisici imposti dalla regola 5-4-3.
    

Per superare questi problemi, sono stati introdotti gli **switch Ethernet**, che segmentano il traffico e **eliminano le collisioni** dividendo la rete in **domini indipendenti** (tema che verrà ripreso nei moduli successivi).

---

### **9. Sintesi concettuale**

- Le **prime reti locali** erano a **bus** o **anello**, con accesso condiviso.
    
- Ethernet, nata nei **laboratori Xerox PARC**, è diventata lo **standard IEEE 802.3**.
    
- Il protocollo **CSMA/CD** consente di condividere il mezzo riducendo il rischio di collisioni.
    
- La **regola 5-4-3** limita la lunghezza e la complessità dei segmenti Ethernet.
    
- Gli **hub** rigenerano e replicano i segnali, ma **non instradano**.
    
- La crescita della rete e la necessità di efficienza porteranno, nei moduli successivi, all’introduzione di **switch e topologie commutate**.