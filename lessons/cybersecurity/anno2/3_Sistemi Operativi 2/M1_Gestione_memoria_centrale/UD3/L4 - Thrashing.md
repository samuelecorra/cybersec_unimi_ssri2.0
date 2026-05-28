# **M1 UD3 Lezione 4 - Thrashing**

---

### **1. Introduzione**

Il **thrashing** è una condizione di grave degrado delle prestazioni in un sistema con **memoria virtuale**.

Si verifica quando un processo dispone di **troppi pochi frame** e genera page fault così frequenti da passare **più tempo a gestire la paginazione** che a eseguire istruzioni utili.

In pratica, il sistema entra in un circolo vizioso di caricamenti e scaricamenti continui, consumando gran parte del tempo di CPU in operazioni di gestione della memoria invece che in elaborazione effettiva.

> 📌 Il thrashing è una forma di "paginazione spazzatura": il sistema lavora molto per spostare pagine, ma produce poca o nessuna evoluzione utile dei processi.

---

### **2. Definizione del fenomeno**

Il thrashing è legato allo spreco di tempo nella gestione della paginazione a causa di un'elevata frequenza di richieste di cambiamento di pagina.

#### **2.1. Condizione tipica**

Il fenomeno compare quando:

- il numero di frame assegnati a un processo è insufficiente;
- le pagine attivamente usate dal processo non riescono a stare contemporaneamente in memoria centrale;
- ogni nuova pagina richiesta provoca la rimozione di un'altra pagina che servirà poco dopo;
- il sistema operativo trascorre più tempo a gestire page fault che a eseguire il processo.

#### **2.2. Effetto sulle prestazioni**

Le conseguenze sono:

- grave perdita delle prestazioni globali;
- aumento del tempo medio di risposta;
- riduzione della computazione utile;
- uso del processore concentrato sulla gestione delle pagine.

> ⚠️ Il sintomo critico del thrashing non è solo l'aumento dei page fault, ma il fatto che il sistema smette di progredire utilmente perché la CPU lavora quasi solo per la paginazione.

---

### **3. Cause principali del thrashing**

#### **3.1. Multiprogrammazione eccessiva**

La prima causa è il tentativo di garantire un grado di multiprogrammazione troppo alto, cioè caricare molti processi in memoria centrale dando pochi frame a ciascuno.

Per aumentare il numero di processi residenti in memoria, il sistema tende a ridurre i frame assegnati a ogni processo. Se questa riduzione scende sotto il numero di frame necessari a contenere la località attiva dei processi, i page fault aumentano rapidamente.

#### **3.2. Schedulazione a lungo termine non controllata**

Lo schedulatore a lungo termine può cercare di introdurre nuovi processi ogni volta che osserva il processore inattivo, con l'obiettivo di incrementare lo sfruttamento della CPU.

Il problema è che il processore può apparire inattivo non perché manchino processi, ma perché i processi già caricati stanno aspettando continuamente operazioni di paginazione.

> ⚠️ Se l'inattività della CPU è causata da page fault continui, caricare nuovi processi peggiora il problema: aumenta la multiprogrammazione ma riduce ulteriormente i frame disponibili per ciascun processo.

#### **3.3. Andamento uso CPU e multiprogrammazione**

All'inizio, aumentando il grado di multiprogrammazione aumenta anche l'utilizzo del processore, perché ci sono più processi pronti a essere eseguiti.

Oltre una certa soglia, però, i processi ricevono troppo pochi frame. La percentuale di tempo spesa nella gestione della paginazione cresce, mentre la percentuale di tempo dedicata alla computazione utile diminuisce drasticamente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Quando l'utilizzo utile della CPU comincia a diminuire bruscamente, il sistema è entrato in thrashing.

---

### **4. Strategie per evitare il thrashing**

Il thrashing può essere evitato a priori intervenendo sulle politiche di schedulazione e sulle politiche di allocazione dei frame.

#### **4.1. Politiche di schedulazione**

Una politica di schedulazione può impedire il thrashing limitando il caricamento di nuovi processi quando il numero di frame allocati ai processi diventa troppo basso.

In questo caso, i nuovi processi possono essere attivati logicamente ma lasciati nell'area di swap, senza portarli subito in memoria centrale.

Il sistema operativo può quindi:

- limitare il numero di processi residenti;
- sospendere temporaneamente processi già caricati;
- evitare di aumentare la multiprogrammazione quando la memoria è già sotto pressione.

#### **4.2. Politiche di allocazione**

Un'altra strategia consiste nell'usare politiche di allocazione dei frame che impediscano al thrashing di propagarsi tra processi.

In particolare, si può restringere l'insieme dei frame eleggibili per lo scaricamento. Se un processo genera un page fault, anziché sottrarre frame ad altri processi, si può usare una politica di **allocazione locale**, confinando la sostituzione all'interno del processo stesso.

> 💡 L'allocazione locale non elimina necessariamente i page fault del processo che ha pochi frame, ma evita che quel processo tolga risorse agli altri e provochi thrashing globale.

---

### **5. Prevenzione basata sulla località**

Oltre a evitare il thrashing con vincoli rigidi, il sistema può prevenirlo osservando il comportamento dei processi.

Un processo presenta una **località di esecuzione**: in un certo intervallo di tempo tende ad accedere frequentemente ad alcune zone di memoria, sia di codice sia di dati.

Prevenire il thrashing significa identificare un numero di frame sufficiente a contenere la località attiva del processo durante la sua esecuzione.

> 📌 Una località è l'insieme di pagine che un processo usa intensamente in una fase della computazione.

Esistono due approssimazioni principali di questo approccio:

1. **Working Set Model**, basato sull'insieme delle pagine usate recentemente;
2. **Page-Fault Frequency**, basato sulla frequenza osservata dei page fault.

---

### **6. Working Set Model**

Il **Working Set** di un processo rappresenta l'insieme delle pagine usate più recentemente durante una finestra temporale di ampiezza $\Delta$.

#### **6.1. Definizione**

Sia $\Delta$ l'ampiezza della finestra del working set. All'istante $t$, il working set del processo $P_i$ è:

$$
WS_i(t,\Delta)=\{p \mid p \text{ è stata referenziata da } P_i \text{ nell'intervallo } [t-\Delta,t]\}
$$

Se una pagina è in uso attivo, allora si trova nel working set. Se invece non viene più usata, dopo $\Delta$ periodi di tempo dal suo ultimo utilizzo esce dal working set.

#### **6.2. Esempio su stringa di riferimento**

Considerando una stringa di riferimento, il working set cambia a seconda dell'istante osservato e della finestra $\Delta$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In un certo istante $t_1$, il working set può contenere le pagine usate negli ultimi $\Delta$ riferimenti, per esempio $\{1,2,5,6,7\}$. In un istante successivo $t_2$, la località può essere cambiata e il working set può ridursi, per esempio a $\{3,4\}$.

> 💡 Il working set non misura la dimensione totale del processo, ma la sua località attiva in un intervallo recente.

---

### **7. Allocazione basata sul Working Set**

Per evitare il thrashing, il sistema dovrebbe assegnare a ogni processo abbastanza frame da contenere il suo working set.

Sia:

- $M$ il numero totale di frame fisici disponibili;
- $F_i$ il numero di frame da allocare al processo $P_i$;
- $WS_i(t,\Delta)$ il working set del processo $P_i$ all'istante $t$.

Idealmente, per un processo $P_i$ si dovrebbe avere:

$$
F_i \ge |WS_i(t,\Delta)|
$$

per ogni fase significativa della sua esecuzione.

Se si volesse garantire il contenimento del working set lungo tutta l'esecuzione, si potrebbe allocare:

$$
F_i = \max_t |WS_i(t,\Delta)|
$$

#### **7.1. Vincolo globale**

Per mantenere tutti i working set in memoria centrale, deve valere:

$$
\sum_i |WS_i(t,\Delta)| \le M
$$

Se la somma dei frame richiesti dai working set dei processi eccede $M$, non è possibile contenere simultaneamente tutti i working set in memoria centrale.

In questo caso il sistema deve scegliere un processo da sospendere o scaricare, liberando frame per evitare il thrashing.

> ⚠️ Il Working Set Model è semplice dal punto di vista logico, ma complesso da realizzare in pratica perché richiede di stimare dinamicamente il working set dei processi.

---

### **8. Page-Fault Frequency**

Una buona approssimazione alternativa è la tecnica della **Page-Fault Frequency** (**PFF**), basata sull'osservazione della frequenza dei page fault.

Le prestazioni di un processo cambiano in funzione del numero di frame disponibili:

- più frame vengono concessi, più basso tende a essere il tasso di page fault;
- meno frame vengono concessi, più alta tende a essere la probabilità di page fault.

#### **8.1. Soglie superiore e inferiore**

Il sistema definisce:

- una soglia superiore accettabile $U$;
- una soglia inferiore accettabile $L$.

La frequenza di page fault del processo deve rimanere tra questi due limiti:

$$
L \le PFF_i \le U
$$

#### **8.2. Regola di controllo**

Se la frequenza di page fault supera il limite superiore, il processo ha troppo pochi frame e il sistema prova ad allocargliene altri:

$$
PFF_i > U \Rightarrow F_i \leftarrow F_i + k
$$

Se la frequenza di page fault scende sotto il limite inferiore, il processo ha più frame del necessario e il sistema può deallocarne alcuni:

$$
PFF_i < L \Rightarrow F_i \leftarrow F_i - k
$$

> 📌 La PFF mantiene il processo in una zona operativa accettabile: né troppi page fault, né spreco di frame.

#### **8.3. Mancanza di frame liberi**

Se un processo supera la soglia superiore ma non ci sono frame liberi da assegnare, il sistema può sospendere un processo per liberare frame.

Questo permette di caricare nuove pagine in memoria centrale senza dover sempre scaricare pagine vecchie a causa della mancanza di frame disponibili.

---

### **9. Sintesi finale**

> ✅ Il thrashing si verifica quando il sistema passa più tempo nella gestione della paginazione che nell'esecuzione utile dei processi.

Le cause principali sono:

- multiprogrammazione eccessiva;
- assegnazione di troppi pochi frame ai processi;
- schedulazione a lungo termine che introduce nuovi processi anche quando la memoria è già sotto pressione;
- politiche di allocazione che permettono interferenza e sottrazione di frame tra processi.

Le tecniche di gestione viste sono:

- limitare il caricamento di nuovi processi;
- usare allocazione locale per confinare la sostituzione;
- stimare la località tramite **Working Set**;
- controllare dinamicamente l'allocazione tramite **Page-Fault Frequency**.

Entrambe le tecniche mirano a garantire che ogni processo disponga di abbastanza frame per contenere la propria località attiva, evitando che il sistema degeneri in paginazione continua.
