## ***Lezione 2: Input/output a controllo di programma***

---

> 📌 Questa lezione rielabora integralmente le pagine 36–40 di `M3doc.pdf`, compreso il flow-chart dell’eco tastiera-video.

### **1. Registri dell’interfaccia**

Nel controllo di programma la CPU accede ai registri dell’interfaccia attraverso il bus di sistema. La struttura usata nel PDF comprende:

| Registro | Funzione |
| --- | --- |
| **comando** | ordina alla periferica di iniziare un’attività |
| **stato** | espone condizioni quali pronto, occupato o dato disponibile |
| **dato-in** | contiene il dato acquisito dalla periferica |
| **dato-out** | riceve il dato da inviare alla periferica |

Il programma deve eseguire esplicitamente sia i trasferimenti sia le letture di stato e le scritture di comando.

### **2. Esempio completo: leggere un tasto e produrre l’eco**

L’interfaccia considerata collega tastiera e video. L’algoritmo è:

1. scrivere `ON` nel bit `attesa_tasto` del registro di comando;
2. leggere ripetutamente `STATO.tasto_premuto` finché non vale `TRUE`;
3. copiare `DATO-IN` in un registro generale della CPU;
4. leggere ripetutamente `STATO.video_libero` finché non vale `TRUE`;
5. copiare il carattere dal registro CPU a `DATO-OUT`;
6. scrivere `ON` nel bit `visualizza_carattere` del registro di comando.

In pseudocodice:

```text
COMANDO.attesa_tasto <- ON
while STATO.tasto_premuto = FALSE:
    continua a interrogare
R <- DATO_IN

while STATO.video_libero = FALSE:
    continua a interrogare
DATO_OUT <- R
COMANDO.visualizza_carattere <- ON
```

`DATO-IN` può contenere, per esempio, il codice ASCII del tasto. Il trasferimento verso `DATO-OUT` non implica da solo la visualizzazione: occorre il comando che avvia effettivamente la periferica di uscita.

> ⚠️ Dato e comando non sono intercambiabili. Scrivere il carattere nel registro dati prepara l’operazione; attivare il bit di comando la avvia.

### **3. Polling e attesa attiva**

La lettura ripetuta di un bit di stato prende il nome di **polling**. Nel ciclo la CPU non è ferma elettricamente: continua a prelevare, decodificare ed eseguire le istruzioni che controllano la condizione. È quindi un’attesa **attiva** o busy waiting.

Se vi sono più periferiche, il software deve decidere:

- con quale frequenza interrogare ogni interfaccia;
- in quale ordine farlo;
- quale priorità assegnare quando più dispositivi sono pronti.

La politica è flessibile perché è software, ma una periferica controllata raramente può subire una latenza elevata.

### **4. Evitare lo spreco con il multitasking**

Invece di restare nel ciclo, un sistema operativo multitasking può sospendere il processo che attende l’I/O ed eseguire un altro processo pronto. Ciò sfrutta meglio la CPU, purché l’attività alternativa non dipenda dal dato ancora mancante.

Questa strategia non trasforma il controllo di programma in interrupt: qualcuno dovrà comunque tornare a interrogare l’interfaccia. Il sistema operativo gestisce l’alternanza dei processi, non elimina il meccanismo di polling sottostante.

### **5. Perché il controllo di programma spesso funziona**

Il clock di una CPU moderna è dell’ordine dei gigahertz, mentre molti eventi esterni sono molto più lenti. Anche un dattilografo veloce non produce caratteri a una frequenza paragonabile a quella delle istruzioni. Interrogare periodicamente una tastiera può quindi essere sufficiente e rende il circuito molto semplice.

Il comportamento resta però dipendente dall’istante in cui il programma esegue il prossimo controllo. Poiché l’evento esterno e il clock CPU sono indipendenti, la latenza di risposta varia tra quasi zero e l’intero intervallo di polling, oltre agli eventuali ritardi dovuti ad altre attività.

### **6. Primo limite: fenomeni urgenti**

Se la CPU rimane a lungo impegnata, una richiesta può essere scoperta dopo la propria scadenza. Il caso proposto è un allarme in un processo industriale: oltre un certo tempo, l’azione del calcolatore non soddisfa più le specifiche.

Per garantire la risposta occorrerebbe ridurre l’intervallo di polling, ma ciò aumenta il tempo speso a interrogare la periferica. L’interrupt risolverà questo conflitto facendo segnalare l’evento al dispositivo.

### **7. Secondo limite: eventi ad alta frequenza**

Per trasferire $n$ byte a controllo di programma, la CPU ripete per ciascuno:

- fetch e decodifica delle istruzioni del ciclo;
- accesso utile al registro dati e alla memoria;
- incremento del puntatore;
- aggiornamento del contatore;
- test della condizione di fine.

Il lavoro amministrativo può superare il singolo trasferimento utile. L’interrupt migliora la tempestività, ma se arriva per ogni byte conserva gran parte di questo costo. Il DMA trasferirà invece l’intero blocco con hardware dedicato.

### **8. Valutazione complessiva**

| Aspetto | Valutazione |
| --- | --- |
| hardware | semplice |
| controllo software | diretto e flessibile |
| uso CPU durante l’attesa | inefficiente nel busy waiting |
| latenza garantita | no, dipende dal polling |
| trasferimenti massivi | inefficienti |

> ✅ Il controllo di programma impone il ritmo della CPU alla periferica. È adatto a casi semplici e lenti, ma non garantisce la reazione a eventi urgenti e spreca lavoro nei trasferimenti ripetitivi.
