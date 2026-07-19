## ***Lezione 1: Problematiche delle operazioni di input/output***

---

> 📌 Questa lezione rielabora integralmente le pagine 31–35 di `M3doc.pdf` e introduce le tre modalità di sincronizzazione sviluppate nelle lezioni successive.

### **1. Collegare il mondo elettronico al mondo esterno**

CPU, memoria e bus costituiscono il nucleo elettronico del calcolatore. Le periferiche appartengono invece al mondo esterno e seguono fenomeni fisici e temporali propri. L’interfaccia di I/O è il punto di mediazione: presenta alla CPU registri regolari e traduce le operazioni nei segnali specifici della periferica.

Una tipica interfaccia espone celle dedicate a:

- trasferire dati dalla periferica alla CPU o in senso opposto;
- ricevere comandi dalla CPU;
- riportare lo stato della periferica.

Queste celle sono registri hardware, anche se dal programma possono apparire simili a locazioni di memoria.

### **2. Memory-mapped I/O**

Nel **memory-mapped I/O** i registri delle interfacce occupano indirizzi nello stesso spazio usato dalla memoria. Le normali istruzioni di caricamento e memorizzazione possono quindi leggere stato e dati oppure scrivere comandi e dati.

Un indirizzo identifica, a seconda della mappa del sistema:

$$
\text{memoria fisica}\quad\text{oppure}\quad\text{registro di I/O}.
$$

La soluzione rende uniforme il repertorio di istruzioni, ma gli indirizzi riservati alle periferiche non possono essere occupati da RAM o ROM. Il PDF dice che i registri “bucano” lo spazio di indirizzamento: il costo è normalmente contenuto perché i registri sono pochi rispetto alle celle di memoria.

La LC-2 adotta questo modello. Le operazioni viste nel modulo precedente sui registri di tastiera e video sono quindi accessi a indirizzi memory-mapped.

### **3. I/O isolato o I/O-mapped**

Nell’**I/O isolato** memoria e periferiche hanno due spazi distinti. La CPU usa istruzioni dedicate, tipicamente chiamate `IN` e `OUT`, per lo spazio di I/O, mentre le comuni istruzioni di memoria accedono soltanto alla RAM o alla ROM.

| Aspetto | Memory-mapped I/O | I/O isolato |
| --- | --- | --- |
| spazio di indirizzi | unico | memoria e I/O separati |
| istruzioni | normali load/store | istruzioni `IN`/`OUT` dedicate |
| costo nello spazio memoria | alcuni indirizzi riservati | nessuno |
| esempio nel PDF | LC-2 | alcune famiglie Intel |

Le due soluzioni non cambiano la necessità di sincronizzare la CPU con la periferica; cambiano il modo in cui il programma raggiunge i registri.

### **4. Due riferimenti temporali**

Ogni I/O è un incontro fra:

- il **clock interno** della CPU, segnale periodico che scandisce fetch, decode ed execute;
- il tempo reale dei fenomeni esterni, che determina quando una tastiera produce un carattere, un disco rende disponibile un byte o una rete riceve un frame.

Questi orologi non sono sincronizzati. La CPU può lavorare a gigahertz, mentre un operatore umano o un dispositivo elettromeccanico genera eventi molti ordini di grandezza più lentamente, spesso descrivibili in hertz o kilohertz. Altre periferiche, però, possono produrre flussi rapidi o eventi con scadenze strette.

### **5. Tre modalità fondamentali**

| Modalità | Chi impone il tempo | Compito della CPU | Uso tipico |
| --- | --- | --- | --- |
| **controllo di programma** | programma/clock CPU | interroga e trasferisce ogni dato | periferiche lente e semplici |
| **interrupt** | evento esterno | sospende il flusso e serve la richiesta | fenomeni urgenti |
| **DMA** | CPU e trasferimento restano in gran parte indipendenti | configura, poi è avvisata alla fine | blocchi di dati ad alta frequenza |

#### **5.1. Controllo di programma**

Il programma decide quando leggere lo stato dell’interfaccia. È una visione scherzosamente definita nel PDF “tolemaica”: il calcolatore interroga il mondo secondo il proprio ritmo. Funziona quando la CPU è molto più veloce del fenomeno esterno, come nel caso della digitazione umana.

#### **5.2. Interrupt**

La periferica segnala direttamente una necessità di servizio. È la visione “copernicana”: l’evento esterno impone alla CPU di interrompere temporaneamente l’attività corrente. È indispensabile quando una richiesta deve essere gestita entro un limite temporale.

#### **5.3. Direct Memory Access**

Per trasferire un settore di disco o un frame di rete, ripetere via software un’operazione per ogni byte è inefficiente. Con il DMA, interfaccia e circuiteria dedicata muovono autonomamente la sequenza tra periferica e memoria; la CPU viene avvisata quando l’intera operazione è terminata.

> 💡 L’interrupt riduce il ritardo di reazione a un evento; il DMA riduce il lavoro della CPU durante un trasferimento numeroso. Risolvono problemi differenti e possono essere usati insieme.

### **6. Criterio di scelta**

Un singolo carattere da tastiera può essere gestito a controllo di programma, perché il dispositivo è lento. Un allarme industriale richiede interrupt, perché conta la latenza massima. Un settore da 1024 byte o un frame di rete beneficia del DMA, perché conta evitare centinaia o migliaia di cicli software ripetitivi.

> ✅ Il problema centrale dell’I/O non è soltanto dove siano i registri, ma come accordare l’elevata frequenza interna della CPU con eventi esterni asincroni, urgenti o ripetuti.
