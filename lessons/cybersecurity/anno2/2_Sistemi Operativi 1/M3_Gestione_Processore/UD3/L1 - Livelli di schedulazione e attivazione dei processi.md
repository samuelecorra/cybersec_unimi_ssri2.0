# **M3 UD3 Lezione 1 - Livelli di schedulazione e attivazione dei processi**

---

### **1. Introduzione**

La **schedulazione** è il meccanismo attraverso cui il **sistema operativo decide quale processo deve usare la CPU** in un determinato momento.  
L’obiettivo è ottenere un uso efficiente del processore e una gestione equilibrata dei processi, in modo da offrire all’utente l’impressione che tutti stiano procedendo contemporaneamente.

In realtà, la CPU può eseguire **un solo processo per volta** su ogni core, ma grazie alla velocità di commutazione tra processi (context switch) si ottiene una **concorrenza apparente**, tipica dei sistemi _time-sharing_.

---
### **2. Obiettivi della schedulazione**

Il compito dello scheduler è quello di:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Gestire la turnazione dei processi sul processore.} \\\\  
\textbf{2.}~ & \text{Stabilire politiche di ordinamento per decidere chi eseguire per primo.} \\\\  
\textbf{3.}~ & \text{Massimizzare lo sfruttamento della CPU e migliorare la reattività del sistema.}  
\end{cases}  
$$

A tale scopo, il sistema operativo utilizza **diversi livelli di schedulazione**, ciascuno con ruoli e frequenze differenti.

---
### **3. Livelli della schedulazione**

Esistono tre livelli principali di schedulazione, ognuno dei quali opera in momenti e con obiettivi diversi:

$$  
\begin{cases}  
\textbf{Schedulazione a lungo termine} & \text{(job scheduler)} \\\\  
\textbf{Schedulazione a medio termine} & \text{(medium-term scheduler)} \\\\  
\textbf{Schedulazione a breve termine} & \text{(CPU scheduler)}  
\end{cases}  
$$

I termini "breve / medio / lungo" si riferiscono alla **durata** rispetto all'evoluzione della computazione dei processi:

- **breve termine**: tempi tipicamente **al di sotto del secondo**;
- **lungo termine**: tempi tipicamente **nell'ordine dei minuti**;
- **medio termine**: una via di mezzo tra i due.

---
### **4. Schedulazione a breve termine (short-term scheduling)**

#### **4.1. Funzione**

È il livello più vicino all’hardware e il più frequente.  
Il **CPU scheduler** ordina i processi che sono già **in memoria centrale** e nello stato di **pronto all’esecuzione (Ready)**.

Il processo posto in cima alla lista sarà quello scelto dal **dispatcher**, che effettuerà il **cambio di contesto (context switch)** per metterlo in esecuzione. Il processo precedentemente in esecuzione viene messo tra quelli che saranno **successivamente rischedulati** per riottenere il processore — non appena disporrà di **tutte le risorse informative e fisiche** che gli servono per l'evoluzione della computazione.

#### **4.2. Caratteristiche principali**

- Eseguita molto frequentemente, tipicamente **ogni 100 ms** circa, per dare l'impressione di evoluzione parallela di tutti i processi.
    
- Deve essere **estremamente veloce**, per non introdurre ritardi di gestione.
    
- Usa algoritmi semplici per minimizzare l'overhead.

##### **Motivazione della semplicità degli algoritmi**

Il punto chiave è che il **carico di lavoro indotto dall'esecuzione stessa dell'algoritmo di scheduling deve essere minimo**. Sarebbe un controsenso avere una schedulazione "ideale" ma passare, per esempio, il **90% del tempo della CPU** nel calcolarla anziché nell'eseguire i processi applicativi. La CPU deve essere usata in larga parte per **lavoro utile**, non per gestione: di qui la scelta di algoritmi **molto semplici** per lo short-term.

Questo tipo di schedulazione è tipico dei sistemi _time-sharing_, dove la CPU passa rapidamente da un processo all'altro per mantenere alta la reattività.

---
### **5. Schedulazione a lungo termine (long-term scheduling)**

#### **5.1. Funzione**

Il **job scheduler** seleziona **quali processi ammettere in memoria centrale** per l'esecuzione, tra quelli che sono stati attivati o caricati nel sistema.  
In pratica, determina **quanti e quali processi potranno entrare nello stato di pronto**.

##### **Dove stanno i processi non ancora caricati**

Quando un sistema attiva molti processi, non tutti possono stare efficientemente in memoria centrale (sarebbe penalizzato lo spazio di ciascuno e si sovraccaricherebbe la gestione della memoria virtuale). I processi attivati ma non ancora caricati vengono quindi tenuti in **appositi spazi di memoria di massa**.

Il long-term scheduler sceglie tra questi quali **portare in memoria centrale** e mettere nello stato **Ready**. In questo modo, lo **short-term scheduler opera solo su un sottoinsieme** dei processi attivi nel sistema — precisamente quelli che il long-term ha già caricato in RAM.

#### **5.2. Obiettivo**

Bilanciare il gruppo di processi caricati in memoria per ottenere un uso ottimale della CPU.  
In particolare, lo scheduler cerca di mescolare:

$$  
\begin{cases}  
\textbf{Processi CPU-bound:}~ & \text{richiedono molta elaborazione e poco I/O;} \\\\  
\textbf{Processi I/O-bound:}~ & \text{richiedono frequenti operazioni di input/output.}  
\end{cases}  
$$

Una combinazione equilibrata massimizza l’utilizzo complessivo del processore.

#### **5.3. Frequenza e complessità**

- Viene eseguito **poco frequentemente**, in genere ogni pochi minuti — per non sovraccaricare il sistema con un algoritmo complesso eseguito troppo spesso.
    
- Può essere **assente** o ridotto al minimo in sistemi con carico costante.
    
- Usa **algoritmi complessi**, poiché deve tener conto della **predizione del comportamento dei processi** ed eseguire un **bilanciamento ottimale** tra le varie esigenze del sistema.

---
### **6. Schedulazione a medio termine (medium-term scheduling)**

#### **6.1. Motivazioni**

Il medium-term nasce come **mediazione** tra short-term e long-term: combina la **rapidità ed efficienza** dello short-term con la **capacità di previsione del comportamento** dei processi tipica del long-term, attenuando i limiti di entrambi (lo short-term ha poca visione, il long-term è troppo lento).

Anche dopo una buona selezione iniziale del long-term, le prestazioni possono non restare ottimali:

- la **previsione** del comportamento dei processi può non essere eccellente;
- i processi possono **cambiare il loro modo di comportarsi** durante l'esecuzione, vanificando le predizioni iniziali;
- nel tempo può accumularsi un **numero eccessivo di processi in memoria centrale**: l'alta concorrenza riduce le prestazioni globali a causa del sovraccarico nella gestione della memoria.

In un sistema attivo, si possono quindi verificare problemi come:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Troppi processi concorrenti che rallentano il sistema.} \\\\  
\textbf{2.}~ & \text{Uso inefficiente della CPU per sbilanciamento tra CPU-bound e I/O-bound.} \\\\  
\textbf{3.}~ & \text{Memoria centrale insufficiente per ospitare tutti i processi attivi.}  
\end{cases}  
$$

#### **6.2. Obiettivi**

Per risolvere questi problemi, lo **scheduler a medio termine**:  
$$  
\begin{cases}  
\textbf{1.}~ & \text{Riduce la concorrenza, sospendendo processi temporaneamente.} \\\\  
\textbf{2.}~ & \text{Ottimizza la distribuzione dei processi attivi.} \\\\  
\textbf{3.}~ & \text{Libera memoria centrale per aumentare l’efficienza.}  
\end{cases}  
$$

#### **6.3. Soluzione: swapping**

La tecnica principale è lo **swapping**, ossia lo spostamento temporaneo dei processi — anche **parzialmente eseguiti** — tra memoria centrale e una **memoria di massa temporanea** (_swap area_):

![Swapping out e swapping in tra memoria centrale e memoria di massa](imgs/Pasted%20image%2020260319154257.png)

$$  
\begin{cases}  
\textbf{Swapping out:}~ & \text{spostamento di un processo dalla RAM alla swap area su memoria di massa;} \\\\  
\textbf{Swapping in:}~ & \text{reintroduzione del processo dalla swap area nella memoria centrale.}  
\end{cases}  
$$

##### **Meccanica operativa**

Sostanzialmente, il medium-term scheduler interviene così:

- i processi entrano nella **coda dei pronti** in memoria centrale e vengono lavorati dallo short-term;
- quando opportuno (es. carico eccessivo, bilanciamento da rivedere), un processo può essere **scaricato in memoria di massa** anche se è solo **parzialmente eseguito** (swap-out);
- il medium-term provvederà **successivamente** a **ricaricarlo** in memoria centrale (swap-in), rimettendolo nella coda dei pronti;
- gli altri processi continuano la propria evoluzione normalmente.

In questo modo il medium-term **adatta dinamicamente** l'insieme dei processi selezionato dal long-term alle **effettive caratteristiche del carico di lavoro** rilevate durante l'esecuzione, mantenendo un bilanciamento ottimale tra CPU-bound e I/O-bound e uno sfruttamento elevato del processore.

---
### **7. Tipologie di attivazione**

La schedulazione può essere attivata in due modi, a seconda di come viene interrotto un processo in esecuzione.

---
#### **7.1. Schedulazione non pre-emptive**

Il processo in esecuzione **non viene interrotto forzatamente**:  
cede il processore **solo in punti naturali della sua esecuzione**, ad esempio quando:

- richiede un’operazione di I/O,
    
- crea un processo e attende la sua terminazione,
    
- rilascia volontariamente la CPU,
    
- o termina.

È una **gestione sincrona** con l’evoluzione del programma: il cambio di contesto avviene solo in momenti “sicuri”.

---
#### **7.2. Schedulazione pre-emptive**

Nei sistemi _time-sharing_, la CPU può essere **sottratta forzatamente** a un processo anche se non ha terminato la propria attività.  
Questo avviene quando scade il **quanto di tempo (time quantum)** assegnato.

È una **gestione asincrona**, perché il cambio di contesto può avvenire indipendentemente dallo stato del processo.

Questo approccio migliora la **responsività** e l’equità, ma introduce un leggero overhead dovuto al maggior numero di commutazioni.

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Schedulazione a lungo termine:}~ & \text{decide quali processi caricare in memoria.} \\\\  
\textbf{Schedulazione a medio termine:}~ & \text{regola dinamicamente i processi attivi (swapping).} \\\\  
\textbf{Schedulazione a breve termine:}~ & \text{sceglie quale processo pronto deve usare la CPU.} \\\\  
\textbf{Attivazione non pre-emptive:}~ & \text{rilascio volontario o naturale della CPU.} \\\\  
\textbf{Attivazione pre-emptive:}~ & \text{rilascio forzato allo scadere del quanto di tempo.}  
\end{cases}  
$$

---
### **9. Conclusione**

La schedulazione è la chiave dell’efficienza di un sistema operativo:  
decide chi lavora, per quanto tempo e con quale priorità.

I tre livelli — lungo, medio e breve termine — collaborano per mantenere il sistema **reattivo, bilanciato e sempre operativo**, dando l’illusione di un’esecuzione parallela dei processi anche su CPU singole.