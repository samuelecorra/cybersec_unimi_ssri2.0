# **M1 UD3 Lezione 2 - Tecniche di sostituzione della pagina (parte 2)**

---

### **1. Introduzione**

In questa seconda parte vengono approfonditi gli aspetti relativi alla **sostituzione delle pagine**, cioè al modo in cui il sistema operativo combina lo **scaricamento** di una pagina già presente in memoria centrale e il **caricamento** della pagina richiesta per realizzare la memoria virtuale.

La situazione critica si verifica quando un processo richiede una pagina non presente in RAM e non esiste alcun frame fisico libero in cui caricarla. In questo caso il sistema operativo deve prima liberare spazio, scegliendo una pagina già caricata da rimuovere.

> 📌 La **sostituzione di pagina** consiste nell'identificare un frame da scaricare dalla memoria centrale, detto **pagina vittima** o **frame vittima**, per poter caricare la nuova pagina richiesta.

---

### **2. Concetto di sostituzione della pagina**

La **sostituzione** avviene ogni volta che si deve **scaricare una pagina** per **caricarne un'altra**.

$$
\text{Scaricamento (pagina vittima)} \Rightarrow \text{Caricamento (pagina richiesta)}
$$

Durante questo processo, il sistema operativo:

1. seleziona la **pagina vittima** secondo una determinata politica;
2. se la pagina è stata modificata, la **salva su disco** o la inserisce in un buffer di pagine da scaricare;
3. aggiorna la **tabella delle pagine** del processo proprietario della vittima, marcando la voce come invalida;
4. carica la **nuova pagina richiesta** nel frame liberato;
5. aggiorna la tabella delle pagine del processo che ha generato la richiesta.

#### **2.1. Scenario operativo**

Supponiamo che un processo richieda l'accesso a un indirizzo contenuto in una pagina logica non valida, cioè non presente in memoria centrale ma disponibile nell'area di swap. Se tutti i frame fisici sono occupati, il caricamento non può essere effettuato direttamente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il sistema operativo deve quindi liberare prima un frame fisico, rimuovendo una pagina già presente in memoria centrale. Solo dopo questa operazione la nuova pagina può essere caricata dall'area di swap.

> ⚠️ Quando non esistono frame liberi, un page fault non richiede solo il caricamento della pagina mancante: richiede anche una decisione di sostituzione.

---

### **3. Sostituzione locale e globale**

#### **3.1. Sostituzione locale**

Nella **sostituzione locale**, il sistema operativo sceglie il frame da scaricare solo tra quelli assegnati al processo che ha generato il page fault.

- Il processo può scaricare solo una pagina **tra quelle dei frame a lui assegnati**.
- Garantisce **isolamento**, perché ogni processo subisce direttamente solo le proprie scelte di sostituzione.
- Può però essere inefficiente se un processo ha pochi frame assegnati e un alto tasso di page fault.

#### **3.2. Sostituzione globale**

Nella **sostituzione globale**, il sistema operativo può scegliere come vittima una qualunque pagina fisica appartenente anche ad altri processi.

- Aumenta la **flessibilità**, perché i frame possono essere redistribuiti dinamicamente.
- Può ridurre i fault complessivi del sistema.
- Può però causare **interferenza** tra processi.

> ⚠️ La sostituzione globale può peggiorare il comportamento di un processo diverso da quello che ha generato il page fault, perché può sottrargli un frame.

---

### **4. Meccanismo generale di sostituzione**

Il processo di sostituzione implica logicamente due trasferimenti:

1. **scaricamento** della pagina vittima su disco, se necessario;
2. **caricamento** della pagina richiesta nella memoria fisica.

$$
\text{sostituzione} =
\begin{cases}
\text{scaricamento della pagina vittima} \\
\text{caricamento della pagina richiesta}
\end{cases}
$$

La **tabella delle pagine** viene aggiornata di conseguenza:

- la voce della pagina vittima è **marcata come invalida**;
- il frame liberato diventa disponibile;
- la nuova pagina è **associata al frame liberato**;
- la voce della nuova pagina viene marcata come valida.

#### **4.1. Ottimizzazione con bit di modifica**

Le prestazioni possono essere migliorate usando il **bit di modifica**. Se la pagina vittima non è stata modificata dall'ultimo caricamento, la copia presente nell'area di swap è ancora valida e non serve riscriverla su disco.

$$
\text{scaricamento fisico} =
\begin{cases}
\text{necessario} & \text{se } M=1 \\
\text{evitabile} & \text{se } M=0
\end{cases}
$$

> 💡 Il bit di modifica evita scritture inutili su disco: una pagina non modificata può essere semplicemente invalidata, perché il suo contenuto è già disponibile nello swap.

---

### **5. Politiche di sostituzione della pagina**

Per effettuare la sostituzione si possono adottare diverse politiche di selezione del frame da scaricare dalla memoria centrale:

1. **FIFO** (*First In, First Out*);
2. **Sostituzione ottima** (*Optimal*);
3. **LRU** (*Least Recently Used*);
4. **Reference Bits**;
5. **Second Chance**;
6. **LFU** (*Least Frequently Used*);
7. **MFU** (*Most Frequently Used*).

Ciascuna politica adotta un criterio diverso per selezionare la pagina da rimuovere.

---

### **6. Politica FIFO**

La politica **FIFO** (*First In, First Out*) scarica il frame più vecchio, cioè quello caricato per primo tra quelli attualmente presenti in memoria centrale.

Nel caso di sostituzione locale si considera solo l'insieme dei frame assegnati al processo che ha generato la richiesta. Nel caso di sostituzione globale si considerano tutti i frame fisici occupati.

#### **6.1. Principio**

La pagina più vecchia viene considerata meno utile e quindi rimossa per prima.

> 📌 FIFO usa solo il tempo di permanenza in memoria: non considera se una pagina è stata usata recentemente o se sarà probabilmente usata di nuovo.

#### **6.2. Esempio**

Con una stringa di riferimento e tre frame assegnati al processo, le prime tre pagine richieste occupano i tre frame liberi. Alla quarta pagina richiesta, se nessuno dei frame è libero, FIFO sostituisce la pagina caricata per prima.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se successivamente viene richiesta una pagina già presente in memoria, non avviene alcuna sostituzione. Se invece viene richiesta una nuova pagina non presente, viene rimossa la più vecchia tra quelle rimaste in RAM.

> ⚠️ FIFO è semplice, ma può produrre scelte pessime: una pagina caricata da molto tempo può essere ancora molto usata.

---

### **7. Politica ottima**

La politica **ottima** sostituisce la pagina che **non verrà usata per il periodo di tempo più lungo nel futuro**.

$$
\text{vittima} = \arg\max_{p \in F} \left(\text{prossimo\_uso}(p)\right)
$$

dove $F$ è l'insieme dei frame candidati e $\text{prossimo\_uso}(p)$ indica la distanza temporale dal prossimo riferimento alla pagina $p$.

#### **7.1. Principio**

La scelta è ottima perché rimuove la pagina che servirà più tardi rispetto alle altre, minimizzando il numero di page fault.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Limite pratico**

La politica ottima richiede di conoscere in anticipo la stringa di riferimento futura. In generale questo non è possibile, quindi la politica viene usata come riferimento teorico per confrontare le altre strategie.

> ⚠️ La politica ottima non è implementabile nei sistemi generali: è una baseline teorica, non un algoritmo operativo realistico.

---

### **8. Politica LRU**

La politica **LRU** (*Least Recently Used*) scarica il frame che è stato usato meno recentemente.

#### **8.1. Principio**

LRU approssima la politica ottima usando il passato recente come indicazione del futuro prossimo: una pagina non usata da molto tempo ha minore probabilità di essere richiesta a breve.

> 💡 LRU sostituisce la domanda "quale pagina sarà usata più tardi?" con una stima più realistica: "quale pagina non viene usata da più tempo?".

#### **8.2. Esempio**

Con tre frame, dopo il caricamento iniziale delle prime pagine, quando arriva una nuova richiesta non presente, LRU sceglie come vittima la pagina a cui si è acceduto più anticamente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se una pagina viene referenziata, diventa automaticamente la più recente e quindi non è candidata immediata alla sostituzione.

#### **8.3. Implementazione con contatore o orologio**

Per realizzare LRU è possibile introdurre un **orologio logico** o un contatore. Non serve conoscere l'ora assoluta: è sufficiente un valore crescente che rappresenti il passare del tempo.

Ogni voce della tabella delle pagine conserva, oltre al riferimento al frame fisico, il valore del contatore corrispondente all'ultimo accesso. Quando la pagina viene referenziata, il valore viene aggiornato.

La vittima è quindi la pagina con il timestamp più vecchio:

$$
\text{vittima} = \arg\min_{p \in F} \left(\text{ultimo\_accesso}(p)\right)
$$

#### **8.4. Implementazione con stack**

Un'alternativa consiste nell'usare uno **stack** che rappresenta l'ordine di uso delle pagine. Quando una pagina viene referenziata, viene spostata in cima; la pagina in fondo allo stack è quella usata meno recentemente ed è quindi candidata alla rimozione.

> ⚠️ LRU è concettualmente efficace, ma può essere costosa da implementare perché richiede aggiornamenti a ogni accesso in memoria.

---

### **9. Politica con Reference Bits**

Per ridurre il costo di LRU, molti sistemi usano bit ausiliari che indicano se una pagina è stata referenziata di recente. Questi bit sono chiamati **bit di riferimento** (*reference bits*).

Il bit di riferimento di una pagina viene:

- posto a $1$ dall'hardware quando la pagina viene referenziata;
- lasciato a $0$ se la pagina non è stata referenziata;
- azzerato periodicamente dal sistema operativo.

Dopo un intervallo di tempo $T$, le pagine con bit di riferimento pari a $0$ sono quelle a cui non si è acceduto dall'ultimo azzeramento.

$$
R(p)=
\begin{cases}
1 & \text{se la pagina } p \text{ è stata referenziata nel periodo } T \\
0 & \text{se la pagina } p \text{ non è stata referenziata nel periodo } T
\end{cases}
$$

Il frame da scaricare sarà quindi scelto preferibilmente tra quelli con $R=0$.

> ⚠️ Un singolo bit di riferimento è una misura grossolana: distingue solo l'ultimo periodo osservato e non conserva la storia precedente.

#### **9.1. Bit di riferimento multipli**

Per considerare una storia più ampia, si può associare a ciascun frame un gruppo di $N$ bit di riferimento.

Alla fine di ogni periodo:

1. i bit vengono shiftati verso destra, cioè verso le posizioni meno significative;
2. viene inserito $0$ nella posizione più significativa;
3. quando la pagina viene referenziata, il bit più significativo viene posto a $1$.

In questo modo il gruppo di bit conserva una traccia degli ultimi $N$ periodi. La pagina vittima è quella con il gruppo di bit avente valore minore.

$$
\text{vittima} = \arg\min_{p \in F} \left(\text{valore\_bit\_storici}(p)\right)
$$

> 💡 Più il valore binario dei bit storici è piccolo, più lontano nel tempo risale l'ultimo uso significativo della pagina.

---

### **10. Politica Second Chance**

La politica **Second Chance** concede a una pagina una seconda possibilità prima di considerarla vittima.

Le pagine vengono esaminate ciclicamente, a partire da una posizione corrente, in modo simile a un meccanismo round-robin. Il sistema cerca la prima pagina con bit di riferimento pari a $0$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.1. Funzionamento**

Per ogni pagina esaminata:

$$
\text{azione}(p)=
\begin{cases}
\text{seleziona } p \text{ come vittima} & \text{se } R(p)=0 \\
\text{azzera } R(p) \text{ e passa alla successiva} & \text{se } R(p)=1
\end{cases}
$$

Se il bit di riferimento è $1$, la pagina è stata usata recentemente: il sistema operativo azzera il bit e le concede una seconda opportunità. Se in seguito la pagina non viene più referenziata, al passaggio successivo potrà essere rimossa.

> 📌 Second Chance approssima LRU con un costo più basso: evita di rimuovere subito pagine usate di recente, ma non richiede un timestamp preciso per ogni accesso.

---

### **11. Uso combinato di bit di riferimento e bit di modifica**

Per rendere più efficiente la selezione della vittima si usano spesso due bit:

- il **bit di riferimento** $R$, che indica se la pagina è stata acceduta recentemente;
- il **bit di modifica** $M$, che indica se il contenuto della pagina è stato modificato rispetto alla copia presente nello swap.

Le combinazioni principali sono:

- $R=0, M=0$: pagina non usata recentemente e non modificata. È il candidato ideale, perché può essere rimossa senza scrittura su disco.
- $R=0, M=1$: pagina non usata recentemente ma modificata. È un buon candidato, ma prima deve essere salvata.
- $R=1, M=0$: pagina usata recentemente ma non modificata. È meno conveniente rimuoverla, perché potrebbe essere richiesta di nuovo a breve.
- $R=1, M=1$: pagina usata e modificata recentemente. È il candidato peggiore, perché potrebbe servire presto e richiederebbe anche una scrittura su disco.

> ✅ La migliore vittima è una pagina con $R=0$ e $M=0$: non è stata usata di recente e non richiede scaricamento fisico su disco.

---

### **12. Politica LFU**

La politica **LFU** (*Least Frequently Used*) sceglie come vittima la pagina usata meno frequentemente.

#### **12.1. Funzionamento**

Per realizzarla si associa a ogni frame un contatore degli accessi:

1. il contatore viene inizializzato a $0$;
2. a ogni accesso alla pagina, il contatore viene incrementato;
3. viene scaricato il frame con il contatore più piccolo.

$$
\text{vittima} = \arg\min_{p \in F} \left(\text{accessi}(p)\right)
$$

#### **12.2. Problema del passato remoto**

Il limite principale è che una pagina può essere stata usata molto in passato e avere quindi un contatore alto, pur non essendo più utile. In questo caso LFU rischia di mantenere in memoria pagine ormai inattive.

Per attenuare il problema si applica un **decadimento periodico**:

$$
\text{accessi}(p) \leftarrow \left\lfloor \frac{\text{accessi}(p)}{2} \right\rfloor
$$

In questo modo il sistema dimentica progressivamente la frequenza d'uso del passato remoto.

> ⚠️ Senza decadimento, LFU può proteggere pagine vecchie solo perché sono state molto usate in una fase precedente della computazione.

---

### **13. Politica MFU**

La politica **MFU** (*Most Frequently Used*) sceglie come vittima la pagina più frequentemente usata.

L'ipotesi è opposta a quella di LFU: una pagina molto usata potrebbe aver già esaurito la propria utilità immediata, mentre una pagina poco usata potrebbe essere stata caricata da poco e quindi potrebbe servire nel prossimo futuro.

$$
\text{vittima} = \arg\max_{p \in F} \left(\text{accessi}(p)\right)
$$

> 💡 LFU e MFU partono da ipotesi diverse sul significato della frequenza: LFU protegge ciò che è stato usato spesso, MFU protegge ciò che è stato usato poco perché potrebbe essere appena entrato nel working set.

---

### **14. Politiche di selezione delle pagine da caricare**

Oltre a scegliere quale pagina scaricare, il sistema operativo deve decidere **quali pagine caricare** in memoria centrale.

La scelta più semplice è il **caricamento su richiesta**: si carica soltanto la pagina che ha generato il page fault.

Un'alternativa è guardare più avanti e tentare di prevedere quali pagine saranno richieste. In questo caso si possono caricare anticipatamente:

- la pagina richiesta e alcune pagine immediatamente successive;
- una breve porzione prevista della stringa di riferimento;
- le pagine adiacenti, assumendo un comportamento abbastanza sequenziale del processo.

Se la stringa di riferimento fosse nota, si potrebbero caricare le $N$ pagine immediatamente successive alla posizione corrente. Poiché questo normalmente non è possibile, il sistema operativo usa euristiche basate sulla località e sulla prevedibilità degli accessi.

> ⚠️ Il caricamento anticipato conviene solo se la località degli accessi è sufficientemente prevedibile; altrimenti può peggiorare le prestazioni consumando frame e banda di I/O inutilmente.

---

### **15. Sintesi finale**

> ✅ La sostituzione delle pagine gestisce il caso in cui una pagina richiesta non è in RAM e non esistono frame liberi. Il sistema operativo seleziona una vittima, aggiorna le tabelle, scarica la vittima se modificata e carica la nuova pagina.

Le politiche principali viste sono:

- **FIFO**, che elimina la pagina caricata da più tempo;
- **Ottima**, che elimina la pagina usata più lontano nel futuro;
- **LRU**, che elimina la pagina usata meno recentemente;
- **Reference Bits**, che approssimano la storia recente degli accessi;
- **Second Chance**, che evita di rimuovere subito pagine referenziate di recente;
- **LFU**, che elimina la pagina meno frequentemente usata;
- **MFU**, che elimina la pagina più frequentemente usata;
- politiche di **caricamento anticipato**, che cercano di ridurre i page fault futuri prevedendo le prossime richieste.

La qualità della politica dipende dal bilanciamento tra accuratezza della previsione, costo di implementazione e numero di trasferimenti tra memoria centrale e area di swap.
