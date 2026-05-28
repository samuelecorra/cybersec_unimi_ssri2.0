# **M1 UD3 Lezione 3 - Tecniche di allocazione dei frame**

---

### **1. Introduzione**

La gestione della **memoria virtuale** non riguarda solo la scelta di **quale pagina sostituire**, ma anche **quanti frame assegnare a ciascun processo**.

Questa decisione, chiamata **allocazione dei frame**, influenza in modo diretto l'evoluzione della computazione del processo, soprattutto dal punto di vista della velocità.

> 📌 Il numero di frame assegnati a un processo influenza la frequenza dei **page fault**: più frame sono disponibili, minore è la probabilità che una pagina richiesta non sia già in memoria centrale.

---

### **2. Il problema dell'allocazione**

Ogni processo necessita di un certo numero di **frame fisici** per eseguire le proprie istruzioni e accedere ai propri dati. Un numero troppo basso di frame provoca numerosi page fault, mentre un numero eccessivo sottrae risorse agli altri processi.

All'aumentare del numero di frame assegnati a un processo:

- diminuisce la frequenza delle mancanze di pagina;
- aumenta la probabilità che gli accessi avvengano verso pagine già presenti in memoria centrale;
- diminuisce il **tempo medio di accesso alla memoria**;
- migliora la velocità di avanzamento della computazione del processo.

Il problema diventa quindi stabilire:

1. **quanti frame allocare** a ogni processo;
2. se usare un criterio uguale per tutti i processi;
3. se l'allocazione debba dipendere da caratteristiche specifiche, come dimensione o priorità.

> ⚠️ Allocare più frame a un processo riduce i suoi page fault, ma riduce anche i frame disponibili per gli altri processi.

---

### **3. Vincoli per l'allocazione dei frame**

L'allocazione dei frame non può essere arbitraria: deve rispettare vincoli architetturali, fisici e gestionali.

#### **3.1. Numero minimo di frame**

Ogni processo caricato in memoria centrale deve ricevere un **numero minimo di frame** per poter essere eseguito.

Questo minimo dipende tipicamente dall'**architettura fisica** del sistema e dal set di istruzioni della CPU. Alcune istruzioni possono infatti richiedere accessi contemporanei a più pagine, per esempio per codice, operandi e destinazione del risultato.

> 📌 Un processo non può ricevere meno frame del minimo richiesto dall'architettura: sotto quella soglia l'esecuzione non è garantita.

#### **3.2. Numero di frame disponibili**

Il numero totale di frame allocabili dipende dalla quantità di **memoria centrale fisica installata**.

Il sistema operativo può inoltre voler mantenere un certo numero di frame liberi per rispondere rapidamente a nuove richieste dei processi. Questi frame liberi costituiscono una riserva operativa utile per ridurre il costo immediato di alcuni page fault.

#### **3.3. Frame condivisi tra processi**

Se più processi condividono alcune pagine, tali frame non devono essere contati più volte nel totale effettivamente occupato. Le pagine condivise vengono fisicamente caricate una sola volta, anche se appartengono allo spazio virtuale di più processi.

Sia:

- $P_i$ un processo;
- $F_i$ il numero di frame assegnati al processo $P_i$;
- $G_j$ un gruppo di processi che condividono pagine;
- $S_j$ il numero di frame condivisi dal gruppo $G_j$;
- $D$ il numero totale di frame fisici disponibili per l'allocazione.

Il vincolo generale è:

$$
\sum_i F_i - \sum_j S_j \le D
$$

Il termine $\sum_j S_j$ evita di contare più volte frame che sono fisicamente condivisi tra processi dello stesso gruppo.

> ✅ Il numero totale effettivo di frame assegnati ai processi, al netto delle condivisioni, non può superare i frame disponibili nella memoria centrale fisica.

---

### **4. Ambito dell'allocazione**

L'insieme dei frame da cui il sistema operativo può effettuare l'allocazione costituisce un vincolo importante. Si distinguono due approcci principali.

#### **4.1. Allocazione globale**

Nell'**allocazione globale**, tutti i frame disponibili possono essere assegnati a tutti i processi. Il sistema operativo può quindi scegliere frame da un insieme comune e redistribuirli in modo più flessibile.

Questa tecnica permette un adattamento dinamico al carico di lavoro, ma può generare interferenza tra processi: un processo può ricevere più frame a discapito di un altro.

#### **4.2. Allocazione locale**

Nell'**allocazione locale**, gli algoritmi operano su un sottoinsieme di frame già assegnato a un processo. Le sostituzioni e le decisioni di gestione restano interne a quel sottoinsieme.

Il processo non può cercare frame al di fuori di quelli preassegnati.

> ⚠️ L'allocazione locale limita l'interferenza tra processi, ma può essere rigida: un processo con molti page fault non può ottenere automaticamente frame inutilizzati da altri processi.

---

### **5. Allocazione omogenea**

Nell'**allocazione omogenea**, tutti i processi ricevono la stessa quantità di frame.

Se:

- $D$ è il numero totale di frame disponibili per i processi;
- $n$ è il numero di processi attivi;
- $F_i$ è il numero di frame assegnati al processo $P_i$;

allora:

$$
F_i = \frac{D}{n}
$$

#### **5.1. Vantaggi**

Il criterio è semplice da implementare ed è equo dal punto di vista formale, perché ogni processo riceve la stessa quota di memoria fisica.

#### **5.2. Svantaggi**

Il limite principale è che i processi possono avere dimensioni molto diverse. Un processo grande può richiedere più memoria centrale per ridurre il numero di page fault, mentre un processo piccolo può ricevere più frame di quelli realmente utili.

> ⚠️ L'allocazione omogenea è semplice, ma ignora le esigenze effettive dei processi.

---

### **6. Allocazione proporzionale alla dimensione**

Nell'**allocazione proporzionale alla dimensione**, il numero di frame assegnati a ciascun processo è proporzionale alla dimensione del suo spazio di memoria virtuale.

Se:

- $D$ è il numero totale di frame disponibili;
- $s_i$ è la dimensione, in pagine, dello spazio virtuale del processo $P_i$;
- $\sum_k s_k$ è la dimensione complessiva degli spazi virtuali dei processi considerati;
- $F_i$ è il numero di frame assegnati a $P_i$;

allora:

$$
F_i = D \cdot \frac{s_i}{\sum_k s_k}
$$

Il rapporto:

$$
\frac{s_i}{\sum_k s_k}
$$

indica la percentuale di memoria virtuale complessiva riconducibile al processo $P_i$. La stessa percentuale viene usata per assegnare i frame fisici.

#### **6.1. Vantaggi**

I processi più grandi ricevono più frame, riducendo il numero di page fault dovuti alla maggiore ampiezza del loro spazio di indirizzamento.

#### **6.2. Svantaggi**

I processi piccoli possono risultare penalizzati se ricevono troppo pochi frame rispetto al loro comportamento reale. La dimensione dello spazio virtuale, infatti, non coincide necessariamente con il working set effettivamente usato durante una fase della computazione.

> 💡 La dimensione del processo è un criterio utile, ma non sempre descrive bene quante pagine il processo sta usando davvero in un certo momento.

---

### **7. Allocazione proporzionale alla priorità**

Nell'**allocazione proporzionale alla priorità**, il numero di frame assegnati dipende dalla priorità logica del processo.

L'idea è che un processo con priorità più alta debba evolvere più rapidamente. Per ottenere questo risultato, gli si assegna un numero maggiore di frame, così da ridurre i page fault e il tempo medio di accesso alla memoria.

Assumendo una logica positiva, in cui un valore di priorità più alto indica maggiore importanza, siano:

- $D$ il numero totale di frame disponibili;
- $q_i$ la priorità del processo $P_i$;
- $\sum_k q_k$ la somma delle priorità dei processi considerati;
- $F_i$ il numero di frame assegnati a $P_i$.

Allora:

$$
F_i = D \cdot \frac{q_i}{\sum_k q_k}
$$

Il rapporto:

$$
\frac{q_i}{\sum_k q_k}
$$

determina la quota di frame totali da assegnare al processo $P_i$.

#### **7.1. Vantaggi**

Questo criterio favorisce i processi più importanti, per esempio processi di sistema, interattivi o real-time, permettendo loro di ridurre i page fault e avanzare più rapidamente.

#### **7.2. Svantaggi**

I processi a bassa priorità possono ricevere pochi frame e subire un aumento dei page fault. In condizioni di memoria scarsa, questo può rallentare fortemente la loro esecuzione.

> ⚠️ L'allocazione proporzionale alla priorità migliora il comportamento dei processi importanti, ma può penalizzare quelli con priorità bassa.

---

### **8. Sintesi finale**

> ✅ L'allocazione dei frame stabilisce quanti frame fisici assegnare ai processi e influenza direttamente frequenza dei page fault, tempo medio di accesso alla memoria e velocità della computazione.

Le principali strategie viste sono:

- **allocazione omogenea**, in cui tutti i processi ricevono lo stesso numero di frame;
- **allocazione proporzionale alla dimensione**, in cui i frame sono distribuiti in base alla dimensione dello spazio virtuale;
- **allocazione proporzionale alla priorità**, in cui i processi più importanti ricevono più frame;
- distinzione tra **allocazione globale** e **allocazione locale**, che determina da quale insieme di frame il sistema operativo può attingere.

Ogni criterio presenta vantaggi e svantaggi. La scelta dipende dalla memoria fisica disponibile, dal numero di processi, dalla loro dimensione, dalla loro priorità e dal grado di isolamento desiderato tra processi.
