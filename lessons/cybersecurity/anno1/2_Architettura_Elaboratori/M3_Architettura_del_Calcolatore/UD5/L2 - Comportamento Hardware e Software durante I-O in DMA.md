## ***Lezione 2: Comportamento hardware e software durante l’I/O in DMA***

---

> 📌 Questa lezione rielabora integralmente le pagine 61–64 di `M3doc.pdf`, seguendo tutti gli attori nell’esempio di lettura di un settore da disco.

### **1. Scenario: leggere un settore da 1 KiB**

Un programma, per esempio un editor di testi, richiede la prossima parte di un documento conservato su disco. Il settore è lungo:

$$
1\ \text{KiB}=1024\ \text{byte}=8192\ \text{bit}.
$$

Il PDF sottolinea la distinzione: `KiB` indica kibibyte, mentre `Kbit` indicherebbe kilobit. Il calcolatore dispone di un DMAC per l’I/O su disco.

I tre attori sono:

- **CPU**, con attività software e reazioni hardware;
- **DMAC**, responsabile del trasferimento;
- **interfaccia a disco**, che controlla il dispositivo e presenta i byte.

### **2. Fase software: chiamata al sistema operativo**

Il programma chiama una routine di sistema, indicata nel PDF come `readisk`. Essa conosce la configurazione fisica e inizializza il DMAC:

| Registro | Valore nell’esempio |
| --- | --- |
| `PA` | identificativo dell’interfaccia a disco |
| `MDA` | indirizzo iniziale del buffer in memoria |
| `DC` | `1024` |
| `TD` | lettura, cioè trasferimento disco $\rightarrow$ memoria |

Poi configura l’interfaccia a disco specificando traccia, settore e comando di lettura. Il controllore del disco posiziona il dispositivo sul settore richiesto e avvia l’acquisizione.

### **3. Sospensione del processo richiedente**

Il programma non può usare il settore finché il blocco non è completo. Il sistema operativo può sospenderlo. In un sistema multitasking, la CPU esegue nel frattempo un altro processo pronto.

Questa sospensione è software e riguarda la disponibilità del dato. È diversa dalle brevissime cessioni hardware del bus: durante ciascun ciclo DMA la CPU può essere rallentata senza che il processo in esecuzione ne sia consapevole.

### **4. Fase hardware ripetuta per ogni byte**

Quando l’interfaccia ha un byte pronto:

1. segnala `Ready` al DMAC;
2. il DMAC attiva `HOLDREQ` verso la CPU;
3. la CPU termina l’eventuale ciclo di bus in corso;
4. la CPU porta in alta impedenza i propri driver e attiva `HOLDACK`;
5. il DMAC pone sul bus indirizzi il contenuto di `MDA`;
6. il DMAC comanda contemporaneamente la lettura dell’interfaccia e la scrittura della memoria;
7. l’interfaccia pilota il bus dati e la memoria acquisisce il byte;
8. il DMAC incrementa `MDA` e decrementa `DC`;
9. il DMAC disattiva `HOLDREQ`;
10. la CPU disattiva `HOLDACK`, riattiva le proprie uscite e riprende il bus.

Durante il punto 6, interfaccia e memoria si comportano come se stessero interagendo con la CPU. È il DMAC, però, a generare indirizzo e segnali di controllo. Questa è l’essenza dell’accesso **diretto**.

Per il byte $i$, prima del trasferimento:

$$
MDA=BufferBase+i,
\qquad DC=1024-i.
$$

Dopo il trasferimento:

$$
MDA\leftarrow MDA+1,
\qquad DC\leftarrow DC-1.
$$

### **5. Che cosa percepisce il software**

Il rilascio e la riacquisizione del bus sono azioni puramente hardware. Non modificano il PC, non chiamano una routine e non richiedono un’istruzione del programma. Dal lato software, l’operazione resta pendente.

Quando l’ultimo byte porta `DC` a zero, il DMAC genera `INTREQ`. La CPU usa il meccanismo di interrupt disponibile, cablato o vettorizzato, e avvia la RRI del controllore DMA.

### **6. Dal completamento hardware alla notifica software**

La RRI comunica a `readisk` che il trasferimento è terminato, per esempio impostando a `TRUE` una variabile flag mantenuta dal sistema operativo. Può inoltre registrare eventuali errori e liberare risorse di controllo.

Il sistema operativo cambia quindi lo stato del processo richiedente da bloccato a pronto. Quando lo scheduler gli assegna nuovamente la CPU, il programma riprende e trova nel buffer la copia del settore.

La catena completa è:

| Fase | Livello | Attore principale |
| --- | --- | --- |
| chiamata `readisk` | software | programma/SO |
| programmazione DMAC e disco | software che comanda hardware | CPU |
| produzione dei byte | hardware | interfaccia |
| trasferimenti nel buffer | hardware | DMAC |
| interrupt finale | hardware | DMAC/PIC/CPU |
| flag e risveglio del processo | software | RRI/SO |
| uso del settore | software | programma |

### **7. Errori concettuali frequenti**

> ⚠️ **Il dato non attraversa la CPU.** La CPU configura e arbitra, ma il percorso utile è interfaccia–bus dati–memoria.

> ⚠️ **La CPU non resta necessariamente inattiva per tutto il blocco.** Può eseguire altro, subendo solo le cessioni del bus, se memoria e architettura lo consentono.

> ⚠️ **Il processo non riparte nell’istante elettrico dell’ultimo byte.** Prima arrivano interrupt e RRI; poi il sistema operativo lo rende pronto e lo scheduler decide quando eseguirlo.

### **8. Sintesi operativa**

Il DMAC è progettato ad hoc per ripetere una sequenza semplice. La CPU usa software soltanto all’inizio e alla fine; ogni byte richiede una breve arbitratura hardware. Il vantaggio cresce con la dimensione del blocco, perché il costo iniziale viene ammortizzato su molti trasferimenti.

> ✅ L’I/O in DMA è una cooperazione ordinata: il software configura, l’interfaccia produce o consuma dati, il DMAC li trasferisce, l’hardware genera l’interrupt finale e il sistema operativo riattiva il programma.
