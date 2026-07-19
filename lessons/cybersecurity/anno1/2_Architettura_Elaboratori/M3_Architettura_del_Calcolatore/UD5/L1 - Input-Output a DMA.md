## ***Lezione 1: Input/output a DMA***

---

> 📌 Questa lezione rielabora integralmente le pagine 54–60 di `M3doc.pdf`, inclusi arbitraggio del bus, singolo trasferimento e registri del DMAC.

### **1. Perché introdurre il DMA**

Dischi, SSD e interfacce di rete trasferiscono blocchi: settori o frame composti da centinaia o migliaia di byte. Spesso il programma può procedere soltanto quando l’intera sequenza è disponibile.

Se la CPU sposta ogni byte, oltre all’accesso utile deve eseguire ripetutamente:

- fetch e decodifica delle istruzioni;
- aggiornamento del puntatore in memoria;
- decremento o incremento del contatore;
- controllo della fine del ciclo;
- eventuali altre variabili di servizio.

La CPU è general purpose e paga questa flessibilità. Un circuito dedicato può invece orchestrare lo stesso trasferimento con molte meno operazioni.

### **2. Accesso diretto alla memoria**

Nel **Direct Memory Access** un dispositivo ausiliario accede direttamente alla memoria senza far transitare il dato attraverso un registro della CPU:

$$
\text{periferica}\longleftrightarrow\text{memoria}.
$$

Il dispositivo diventa temporaneamente **master del bus**. Poiché due master non possono pilotare contemporaneamente le stesse linee, la CPU deve cedergli bus indirizzi, dati e controllo.

Il componente dedicato è il **DMAC**, *Direct Memory Access Controller*. La CPU lo configura una volta per il blocco; il DMAC ripete poi in hardware i trasferimenti elementari.

### **3. HOLDREQ e HOLDACK**

Il protocollo di possesso usa:

- `HOLDREQ`, *Hold Request*: il DMAC chiede alla CPU di sospendere l’uso del bus;
- `HOLDACK`, *Hold Acknowledge*: la CPU conferma di averlo rilasciato.

Se più controllori possono chiedere il bus in istanti indipendenti, `HOLDREQ` è una linea condivisa attiva bassa, realizzata con uscite open-collector e pull-up. Qualunque richiedente può portarla a zero senza conflitto.

La CPU completa l’accesso corrente, porta in alta impedenza le uscite con cui pilota address bus e control bus e attiva `HOLDACK`. Da quel momento il DMAC può guidare il sistema.

> 📌 L’affermazione “il bus indirizzi è monosorgente e usa totem-pole” vale soltanto se la CPU è sempre l’unico master. In presenza di DMA, le sue uscite devono poter assumere `Z`.

### **4. Programmazione del DMAC**

Prima dell’avvio, la CPU scrive nei registri del controllore:

| Registro | Nome | Contenuto |
| --- | --- | --- |
| `PA` | Peripheral Address | identificativo dell’interfaccia da servire |
| `MDA` | Memory Data Address | indirizzo del prossimo byte in memoria |
| `DC` | Data Counter | numero di byte ancora da trasferire |
| `TD` | Transfer Direction | direzione: ingresso o uscita |

Il PDF usa talvolta “Memory Data Address”: il significato operativo è un **indirizzo di memoria**, non un dato. Dopo ogni byte `MDA` viene aggiornato e `DC` decrementato.

### **5. Trasferimento di un byte in ingresso**

Supponiamo di copiare un blocco dalla periferica alla memoria.

1. L’interfaccia segnala al DMAC che un byte è pronto (`Ready`).
2. Il DMAC attiva `HOLDREQ`.
3. La CPU termina l’uso corrente, mette i propri driver in `Z` e attiva `HOLDACK`.
4. Il DMAC esegue simultaneamente tre azioni coordinate:
   - pone `MDA` sul bus indirizzi;
   - ordina all’interfaccia di pilotare il bus dati;
   - ordina alla memoria di scrivere il valore presente sul bus dati.
5. Il byte passa direttamente dall’interfaccia alla memoria.
6. Il DMAC incrementa `MDA`, decrementa `DC` e rilascia `HOLDREQ`.
7. La CPU disattiva `HOLDACK`, riabilita i propri driver e torna master.

Per un trasferimento in uscita si inverte il senso utile: la memoria legge la cella indirizzata e l’interfaccia acquisisce il dato dal bus.

### **6. Fine del blocco e interrupt**

Quando:

$$
DC=0,
$$

l’intera tabella è stata trasferita. Il DMAC genera allora una richiesta di interrupt. La CPU non viene interrotta a ogni byte: riceve una notifica alla fine dell’operazione globale. In un sistema vettorizzato, la richiesta passa normalmente attraverso il PIC, che presenta alla CPU il vettore associato al DMAC.

### **7. Trasparenza hardware e rallentamento**

Il programma in esecuzione non osserva i singoli cicli di DMA. Durante ogni cessione la CPU attende brevemente il bus; questo rallentamento è detto spesso **cycle stealing**, perché il DMAC “ruba” cicli di accesso senza modificare direttamente lo stato software della CPU.

Trasparente non significa gratuito: CPU e DMAC contendono una risorsa finita. Significa che il programma non contiene un’istruzione per ogni byte e viene coinvolto soltanto nella configurazione e nella conclusione.

### **8. Confronto con le altre modalità**

| Modalità | Intervento CPU | Notifica | Scala adatta |
| --- | --- | --- | --- |
| controllo di programma | per ogni stato e dato | polling | poche operazioni lente |
| interrupt per evento | RRI a ogni richiesta | immediata | eventi urgenti e sporadici |
| DMA | configurazione e fine blocco | interrupt finale | sequenze numerose |

> ✅ Il DMA separa quasi completamente elaborazione e trasferimento: il DMAC diventa master per il tempo necessario a muovere ogni dato e notifica la CPU soltanto quando il blocco è completo.
