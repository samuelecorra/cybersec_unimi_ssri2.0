# **Lezione 1: Introduzione all’instradamento IP**

---

### **1. Cos’è l’instradamento**

L’**instradamento (routing)** è il processo con cui un pacchetto IP viene **inviato dal mittente al destinatario** scegliendo il percorso più appropriato all’interno della rete o tra più reti connesse.

Ogni host o router, nel momento in cui riceve un pacchetto, deve decidere **a chi inoltrarlo**, analizzando l’indirizzo IP di destinazione e consultando la propria **tabella d’instradamento**.

---

### **2. Instradamento diretto**

Si parla di **instradamento diretto** quando **l’host di destinazione appartiene alla stessa rete** dell’host mittente.

In questo caso:

- il pacchetto IP può essere inviato **direttamente**,
    
- viene **incapsulato in un frame Ethernet**,
    
- e consegnato attraverso il livello di collegamento dati.
    

Non è necessario passare attraverso router intermedi.

#### **Esempio**

Due host appartenenti alla rete `192.168.1.0/24` possono comunicare direttamente:  
`192.168.1.10 → 192.168.1.20`  
Il mittente incapsula il pacchetto IP in un **frame Ethernet** e lo invia al MAC del destinatario.

---

### **3. Instradamento indiretto**

L’**instradamento indiretto** si verifica quando il destinatario **non si trova sulla stessa rete** del mittente.

In questo caso:

1. Il mittente invia il pacchetto a un **router (gateway di default)**.
    
2. Il router riceve il pacchetto, **legge il campo di destinazione IP**, e decide **verso quale altra rete** inoltrarlo.
    
3. Il processo può ripetersi per più router successivi fino alla destinazione finale.
    

#### **Esempio**

Un host `192.168.1.5` deve comunicare con `172.16.2.8`.  
Poiché i due indirizzi appartengono a reti diverse, il mittente invia il pacchetto al proprio **router di default**.

---

### **4. Come l’host decide se l’instradamento è diretto o indiretto**

Per capire se un pacchetto deve essere inviato direttamente o tramite router, l’host confronta il **net_ID** del proprio indirizzo IP con quello dell’indirizzo di destinazione, **tenendo conto della maschera di sottorete**.

$$
\text{Se } (IP_{dest} \land Mask) = (IP_{host} \land Mask) \Rightarrow \text{Instradamento diretto}
$$

Altrimenti:

$$
\text{Instradamento indiretto (tramite router di default)}
$$


Altrimenti:  
$$  
\text{Instradamento indiretto (tramite router di default)}  
$$

---

### **5. Risoluzione degli indirizzi**

Se l’instradamento è **diretto**, l’host deve conoscere il **MAC address** (indirizzo fisico) del destinatario.  
Per ottenerlo utilizza il protocollo **ARP (Address Resolution Protocol)**.

Se invece l’instradamento è **indiretto**, l’host risolve l’indirizzo MAC **del router** (gateway predefinito) e invia il pacchetto a lui.

---

### **6. L’instradamento basato su tabelle (Table-Driven Routing)**

Ogni host e ogni router mantiene una **tabella d’instradamento**, che elenca per ciascuna rete nota:

- il **net_ID di destinazione**;
    
- la **maschera di sottorete**;
    
- il **gateway d’inoltro** (se necessario);
    
- e l’**interfaccia di rete** da utilizzare (ad esempio `eth0`).

![](imgs/Pasted%20image%2020260225003106.png)

#### **Struttura tipica di una tabella**

|Net_ID destinazione|Maschera|Gateway (Next Hop)|Interfaccia|
|---|---|---|---|
|192.168.1.0|255.255.255.0|–|eth0|
|10.0.0.0|255.0.0.0|192.168.1.1|eth0|
|0.0.0.0|0.0.0.0|192.168.1.1|eth0|

> L’ultima riga rappresenta la **rotta di default**: viene utilizzata quando il net_ID di destinazione non compare in nessun’altra riga.

---

### **7. Tipologie di rotte**

Nella tabella d’instradamento di un host si distinguono tre tipi principali di rotte:

1. **Rotte dirette**  
    Reti direttamente connesse alla scheda di rete locale (nessun router intermedio).
    
2. **Rotte indirette**  
    Reti raggiungibili **tramite uno o più router**.
    
3. **Rotta di default**  
    Contiene l’indirizzo del **router predefinito** da usare per tutte le destinazioni non specificate altrove.

![](imgs/Pasted%20image%2020260213053431.png)

---

### **8. Tecniche di instradamento non basate su tabelle**

Oltre al table-driven routing, esistono tecniche più “primitive” o speciali:

|Tecnica|Descrizione|Vantaggi|Svantaggi|
|---|---|---|---|
|**Random routing**|Il pacchetto è inviato a un router scelto casualmente tra quelli disponibili.|Distribuisce il traffico|Nessuna garanzia di consegna|
|**Flooding**|Il pacchetto è inviato **a tutti i router vicini**, che a loro volta lo replicano.|Garantisce che il pacchetto raggiunga la destinazione|Genera **enorme traffico ridondante**|
|**Hot-potato routing**|Ogni router invia il pacchetto al nodo più vicino o con la coda più libera.|Minimizza il carico locale|Può causare percorsi subottimali|

> Tra queste, **solo il flooding** garantisce che il pacchetto arrivi sempre a destinazione, **ma con un costo altissimo in termini di traffico**.

---

### **9. L’algoritmo ingenuo di instradamento**

In un’implementazione base, l’instradamento avviene tramite **scansione lineare della tabella**:

1. Si confronta il net_ID di destinazione con quello di ogni riga.
    
2. Se c’è corrispondenza, il pacchetto viene inoltrato tramite il gateway indicato.
    
3. Se non c’è match, viene usata la **rotta di default**.

![](imgs/Pasted%20image%2020260213053914.png)

Questo metodo è **semplice ma inefficiente**.  
Nei router dedicati, invece, si usano **tecniche hardware parallele** che confrontano contemporaneamente più voci di tabella.

![](imgs/Pasted%20image%2020260213053955.png)

#### **Rete logica da implementare (extra)

L’idea è sostituire la **scansione lineare** della tabella con una rete combinatoria che, dato l’IP di destinazione D, **valuta in parallelo tutte le righe** e poi seleziona quella “giusta” (tipicamente con **Longest Prefix Match**, cioè la maschera più specifica).

---

##### **1) Cosa deve verificare ogni riga i-esima**

Ogni entry della tabella contiene:

- net_id[i] (Destination net_id)
    
- mask[i] (NetMask)
    
- gateway[i]
    
- iface[i]
    

La condizione di match è:


$$
match_i = \Big( (D \ \&\ mask_i) = net_{id_i} \Big)
$$


Quindi per **ogni riga** servono due blocchi:

1. **AND bit-a-bit**: calcola D_masked[i] = D & mask[i]
    
2. **comparatore di uguaglianza** a 32 bit: confronta D_masked[i] con net_id[i]

Uscita: un bit match_i.

---

##### **2) Tutti i match in parallelo**

Metti n copie di quel blocco (una per riga). Ottieni:

- match_1, match_2, ..., match_n

Esempio (coerente con la slide):

- Riga 1: mask = 255.255.255.0 (/24) → match se D è nella rete 196.20.14.0/24
    
- Riga 2: mask = 255.0.0.0 (/8) → match se D è 127.0.0.0/8
    
- Riga n: mask = 0.0.0.0 (/0) → **default route**: D & 0 = 0, quindi match sempre (se presente)

---

##### **3) Se più righe matchano: selezione della “migliore”**

Qui arriva la parte davvero “da router”: se hai più match_i = 1, devi scegliere la rotta con **prefisso più lungo** (maschera più specifica).

Formalmente: scegli i* tale che:

- $match_{i*} = 1$
    
- $len(mask_{i*})$ massimo (numero di 1 nella maschera, cioè /24 > /8 > /0)

In hardware lo fai così (concettualmente):

###### **3.1 Calcolo priorità**

Per ogni riga hai anche un valore costante $p_i = prefixlen(mask_i)$ (es. 24, 8, 0).

Poi costruisci un selettore che realizza:

- “tra tutte le righe con $match=1$, prendi quella con $p$ massimo”.

###### **3.2 Implementazioni tipiche**

- **Priority encoder**: se la tabella è già ordinata per priorità (prima /32, poi /31, …, fino a /0), allora basta un priority encoder che prende il “primo 1”.
    
- **Comparator tree** (albero di confronti): se non vuoi imporre ordinamento, fai un torneo: confronti coppie (match, p) e tieni il migliore, fino a ottenere l’indice vincente.

Nei router reali, questa parte spesso è implementata con **TCAM** (content-addressable memory): fa match del prefisso “per natura” e restituisce già la best match, ma la rete logica della slide è proprio l’idea equivalente.

---

##### **4) Uscite finali: MUX su gateway e interfaccia**

Una volta ottenuto l’indice selezionato i*, devi “leggere” i campi associati:

- gateway_out = gateway[i*]
    
- iface_out = iface[i*]

In logica combinatoria: un **multiplexer** controllato dall’indice i*.

---

##### **Schema a blocchi (come lo disegneresti)**

**Input:** D (32 bit)

**Per i = 1..n (tutto in parallelo):**

- AND32: D_masked[i] = D & mask[i]
    
- EQ32: match[i] = (D_masked[i] == net_id[i])

**Selezione:**

- blocco SELECT_BEST(match[1..n], prefixlen[1..n]) -> i*
    
    - (priority encoder se ordinato) **oppure**
        
    - (albero di confronto)

**Output:**

- MUX su gateway: gateway_out = MUX(gateway[1..n], i*)
    
- MUX su iface: iface_out = MUX(iface[1..n], i*)

---

##### **Mini-nota sul caso “default”**

La riga con mask = 0.0.0.0 produce sempre:

$$

D \ \&\ 0 = 0

$$

quindi matcha sempre con net_id = 0.0.0.0. Per questo deve avere **priorità più bassa**: viene scelta solo se nessun prefisso più specifico matcha.

---

### **10. Esempio di instradamento**

Si consideri un pacchetto con **indirizzo IP di destinazione**:  
$$  
202.18.14.5  
$$

#### **Tabella del router**

|Net_ID|Maschera|Gateway|Interfaccia|
|---|---|---|---|
|202.18.14.0|255.255.255.0|–|eth0|
|195.0.0.0|255.0.0.0|192.168.1.1|eth1|
|0.0.0.0|0.0.0.0|10.0.0.1|eth2|

#### **Verifica dei match**

1. $202.18.14.5  \land  255.255.255.0 = 202.18.14.0$ → **MATCH**
    
2. $202.18.14.5 \land 255.0.0.0 = 195.0.0.0$ → **NO MATCH**
    
3. $202.18.14.5 \land 0.0.0.0 = 0.0.0.0$ → **MATCH**
    

> Si scelgono i **match con la maschera più lunga** (più specifica), cioè quella con più bit a 1:  
> in questo caso, **202.18.14.0/24**. L'ultima che matcha "0" è un match di fallback!

---

### **11. Comando ROUTE (Windows)**

Il comando **ROUTE** serve per visualizzare o modificare la tabella d’instradamento locale di un computer.

#### **Sintassi generale**

```
ROUTE [-fp] [comando] [destinazione] [MASK netmask] [gateway]
```

#### **Parametri principali**

|Parametro|Descrizione|
|---|---|
|**PRINT**|Visualizza la tabella completa|
|**ADD**|Aggiunge una nuova rotta|
|**DELETE**|Rimuove una rotta|
|**CHANGE**|Modifica una rotta esistente|
|**-f**|Cancella l’intera tabella|

#### **Esempi**

- Visualizzare la tabella:  
    `route print`
    
- Aggiungere una rotta:  
    `route add 192.168.2.0 mask 255.255.255.0 192.168.1.1`
    
- Eliminare una rotta:  
    `route delete 192.168.2.0 192.168.1.1`
    

---

### **12. Comando TRACERT**

Il comando **TRACERT** (abbreviazione di _trace route_) mostra l’elenco dei router attraversati da un pacchetto fino alla destinazione.

#### **Funzionalità**

- Visualizza **tutti i router intermedi (hop)**;
    
- Mostra **i tempi di risposta** di ciascun salto;
    
- Indica il **punto in cui il pacchetto si ferma** se la destinazione non è raggiungibile.
    

#### **Esempio**

```
tracert www.unimi.it
```

Output (semplificato):

```
1   192.168.1.1     <1 ms
2   93.45.0.1        5 ms
3   93.45.2.65       8 ms
4   156.54.1.10     15 ms
```

---

### **13. Conclusione**

L’instradamento IP è la **logica fondamentale** che consente ai pacchetti di viaggiare attraverso reti complesse.  
In questa lezione abbiamo distinto:

- instradamento **diretto** e **indiretto**;
    
- instradamento **basato su tabelle** e tecniche **non tabellari**;
    
- uso dei comandi **ROUTE** e **TRACERT** per la gestione e la diagnostica.
    

Nelle lezioni successive approfondiremo le **strategie statiche (Hot-Potato, Floating Routing)** e le **configurazioni pratiche** di router e gateway all’interno di reti reali.
