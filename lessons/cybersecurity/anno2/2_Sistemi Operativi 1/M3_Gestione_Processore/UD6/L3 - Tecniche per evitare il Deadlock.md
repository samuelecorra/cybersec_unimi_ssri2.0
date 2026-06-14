# **M3 UD6 Lezione 3 - Tecniche per evitare il Deadlock**

---

### **1. Introduzione**

Dopo aver visto come **prevenire** il deadlock rimuovendo a priori una delle condizioni di Coffman,  
ci concentriamo ora su un approccio più flessibile: **l’evitamento del deadlock** (_deadlock avoidance_).

L’idea di base è semplice ma potente:  

$$  
\text{Prima di concedere una risorsa, il sistema verifica se la richiesta può portare a deadlock.}  
$$

In pratica, il sistema non vieta le situazioni potenzialmente rischiose, ma **analizza ogni richiesta in tempo reale**, concedendola solo se il sistema rimane in **uno stato sicuro**.

---
### **2. Principio dell'evitamento**

#### **2.1. Obiettivi rispetto alla prevenzione**

L'approccio dell'evitamento mira a **superare i limiti** delle tecniche di prevenzione, che pur garantendo l'assenza di deadlock tendono a essere **rigide** e a portare a sottoutilizzo delle risorse. Gli obiettivi specifici sono:

- **aumentare lo sfruttamento delle risorse** rispetto al caso della prevenzione;
- ottenere un'**alta efficienza** del sistema;
- mantenere una **semplicità di gestione** ragionevole, senza sovraccaricare il sistema di elaborazione con attività di gestione a scapito del lavoro utile.

#### **2.2. Meccanismo**

L'evitamento del deadlock consiste nel:

- **verificare a priori** se l'allocazione di una risorsa può causare deadlock;
- **accettare** solo le richieste che mantengono il sistema in **stato sicuro**;
- **bloccare temporaneamente** le richieste che porterebbero a uno stato non sicuro.

$$  
\text{Stato sicuro} \Rightarrow \text{nessun deadlock} \ | \   
\text{Stato non sicuro} \Rightarrow \text{deadlock possibile}  
$$

L'obiettivo è quindi **massimizzare l'efficienza** senza sacrificare la **sicurezza del sistema**.

---
### **3. Informazioni necessarie**

Per applicare l’evitamento, il sistema operativo deve conoscere **a priori** alcune informazioni fondamentali sui processi:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Numero massimo di risorse richieste da ogni processo.} \\\\  
\textbf{2.}~ & \text{Risorse attualmente assegnate.} \\\\  
\textbf{3.}~ & \text{Risorse disponibili nel sistema.} \\\\  
\textbf{4.}~ & \text{Eventuali richieste e rilasci futuri.}  
\end{cases}  
$$

Queste informazioni consentono di costruire **modelli dinamici di allocazione**, come grafi e matrici, che aiutano a prevedere se un’operazione è “sicura”.

---
### **4. Stato sicuro**

#### **4.1. Definizione**

Uno stato si dice **sicuro** se esiste **almeno una sequenza di esecuzione dei processi**  
tale che ciascuno possa completare la propria esecuzione **senza entrare in deadlock**.

In altre parole:  
$$  
\text{Uno stato è sicuro se esiste una sequenza sicura di processi } \langle P_1, P_2, ..., P_n \rangle  
$$

In tale sequenza, le risorse richieste da ciascun processo $P_i$ possono essere soddisfatte con:

- le risorse attualmente **disponibili**, più
    
- quelle **rilasciate dai processi precedenti** della sequenza.

![](imgs/Pasted%20image%2020260529205323.png)

---
#### **4.2. Esempio di sequenza sicura**

Supponiamo di avere tre processi $P_1, P_2, P_3$ e alcune risorse condivise.  
Se possiamo stabilire una sequenza $\langle P_2, P_1, P_3 \rangle$ tale che:

- $P_2$ può terminare con le risorse disponibili;
    
- poi rilascia le sue risorse, rendendole disponibili a $P_1$;
    
- e infine $P_1$ e $P_3$ possono terminare;

allora lo stato corrente è **sicuro**.

---
#### **4.3. Condizione di sicurezza**

Per evitare il deadlock:  
$$  
\text{Il sistema deve passare sempre da uno stato sicuro a un altro stato sicuro.}  
$$

Ogni nuova richiesta viene concessa solo se mantiene il sistema in uno stato sicuro;  
altrimenti il processo viene **messo in attesa**.

##### **Attenzione: "stato non sicuro" non significa "deadlock"**

Lo **stato sicuro** è una condizione **più restrittiva** del puro "essere fuori dal deadlock":

- da uno **stato sicuro** abbiamo la **certezza** che non si entrerà mai in deadlock;
- da uno **stato non sicuro** **non possiamo dire nulla a priori**: il sistema **potrebbe** non cadere mai in deadlock (se i processi rilasciano le risorse "al momento giusto"), oppure **potrebbe** entrarvi.

L'evitamento è quindi una strategia **conservativa**: rifiuta tutte le transizioni che porterebbero in stato non sicuro, anche quelle che in pratica non genererebbero un deadlock effettivo. Il prezzo della sicurezza assoluta è qualche richiesta rifiutata in più del necessario.

---
### **5. Algoritmo del grafo di allocazione delle risorse**

#### **5.1. Concetto**

L’**algoritmo del grafo di allocazione delle risorse** estende il modello grafico visto nella Lezione 1,  
introducendo gli **archi di prenotazione** (_claim edges_), che rappresentano **richieste future**.

Ogni arco $P_i \rightarrow R_j$ può assumere tre stati:  

$$  
\begin{cases}  
\textbf{Arco di richiesta:} & \text{il processo richiede la risorsa.} \\\\  
\textbf{Arco di assegnazione:} & \text{la risorsa è assegnata al processo.} \\\\  
\textbf{Arco di prenotazione:} & \text{il processo potrebbe richiederla in futuro.}  
\end{cases}  
$$

![](imgs/Pasted%20image%2020260529205607.png)

---
#### **5.2. Funzionamento**

Quando un processo richiede una risorsa:

1. Il sistema **sostituisce** l’arco di prenotazione con un arco di richiesta.
    
2. Se la risorsa è libera, l’arco di richiesta diventa **arco di assegnazione**.
    
3. Il sistema verifica che **non si crei un ciclo** nel grafo.

Se si forma un ciclo → **lo stato diventa non sicuro** → la richiesta viene **negata**.

![](imgs/Pasted%20image%2020260529205637.png)

---
#### **5.3. Limite dell’algoritmo**

Questo metodo funziona solo per risorse con **una singola istanza**.  
Per sistemi più complessi, con **più istanze della stessa risorsa**, serve un algoritmo più generale:  
l’**algoritmo del banchiere**.

---
### **6. Algoritmo del banchiere**

#### **6.1. Principio generale**

L’algoritmo del **banchiere** (_Banker’s Algorithm_, Dijkstra, 1965) modella il sistema come una banca che deve decidere se concedere un prestito.  
Concede risorse solo se, dopo la concessione, il sistema **rimane in uno stato sicuro**.

#### **6.2. Ipotesi di base**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Le risorse hanno più istanze.} \\\\  
\textbf{2.}~ & \text{Ogni processo dichiara a priori il numero massimo di risorse che potrà richiedere.} \\\\  
\textbf{3.}~ & \text{Ogni processo deve rilasciare le risorse in un tempo finito.}  
\end{cases}  
$$

---
#### **6.3. Strutture dati principali**

Dove m = risorse e n = processi

$$  
\begin{cases}  
\textbf{Available}[1..m]~ & \text{vettore delle risorse disponibili.} \\\\  
\textbf{Max}[1..n,1..m]~ & \text{matrice del numero massimo di risorse richieste da ciascun processo.} \\\\  
\textbf{Allocation}[1..n,1..m]~ & \text{matrice delle risorse attualmente allocate.} \\\\  
\textbf{Need}[1..n,1..m]~ & \text{risorse ancora necessarie: } Need = Max - Allocation.  
\end{cases}  
$$

![](imgs/Pasted%20image%2020260614110900.png)

![](imgs/Pasted%20image%2020260614110838.png)

![](imgs/Pasted%20image%2020260614110852.png)

![](imgs/Pasted%20image%2020260614110913.png)

---
### **7. Algoritmo di verifica dello stato sicuro**

#### **7.1. Variabili di supporto**

$$  
\begin{cases}  
\textbf{Work}[1..m]~ & \text{copie temporanee delle risorse disponibili.} \\\\  
\textbf{Finish}[1..n]~ & \text{booleani che indicano se un processo può terminare.}  
\end{cases}  
$$

> 💡 **Perché un vettore temporaneo `Work`?** Si usa una copia delle disponibilità reali per **simulare** le allocazioni durante la verifica, senza alterare lo stato effettivo del sistema. Solo al termine della verifica (positiva) si decide se rendere effettive le modifiche.

#### **7.2. Passaggi operativi**

L'algoritmo non esegue realmente i processi e non modifica subito lo stato del sistema: costruisce una **simulazione** per verificare se esiste almeno un ordine in cui tutti i processi possono terminare. L'idea è chiedersi: "con le risorse disponibili adesso, riesco a far completare un processo? Se sì, dopo il suo completamento recupero le risorse che occupava e provo con gli altri".

ATTENZIONE: d'ora in avanti dunque si lavora per RIGHE, essenzialmente, ecco il perché dell'indice i.

1. **Inizializzazione della simulazione:**  
    $$Work = Available, \quad Finish[i] = false \text{ per tutti i processi.}$$
    
    `Work` rappresenta le risorse disponibili nella simulazione. All'inizio coincide con `Available`, perché nessun processo è ancora stato fatto terminare virtualmente.  
    `Finish[i] = false` significa invece che il processo $P_i$ non è ancora stato inserito nella sequenza sicura simulata.

2. **Ricerca di un processo che possa completare:**  
    si cerca un processo $P_i$ non ancora concluso nella simulazione e le cui richieste residue possano essere soddisfatte con le risorse attualmente indicate da `Work`:  
    
    $$Finish[i] = false \quad \text{e} \quad Need_i \leq Work$$
    
    La condizione $Need_i \leq Work$ scorre tutta la i-esima riga selezionata della matrice per tale processo, e ogni cella di fabbisogno per la singola risorsa viene confrontata con la corrispondente cella di work: per ogni tipo di risorsa, il fabbisogno residuo di $P_i$ deve essere minore o uguale alle risorse disponibili nella simulazione. 
    
    Se **nessun processo** soddisfa questa condizione, l'algoritmo non riesce più a proseguire e passa al punto 4.

3. **Simulazione del completamento del processo trovato:**  
    se esiste un processo $P_i$ che puo' terminare, l'algoritmo immagina di concedergli le risorse residue, farlo completare e poi recuperare tutte le risorse che aveva gia' allocate:  
    
    $$Work = Work + Allocation_i, \quad Finish[i] = true$$
    
    L'aumento di `Work` deriva dal fatto che, una volta terminato, $P_i$ rilascia tutta la propria riga delle risorse indicate da `Allocation_i`.  
    A questo punto $P_i$ viene marcato come completato nella simulazione e l'algoritmo torna al punto 2 per cercare un altro processo completabile.

4. **Valutazione finale dello stato:**  
    se alla fine tutti i processi hanno `Finish[i] = true`, allora è stata trovata una sequenza in cui ogni processo può terminare: lo **stato è sicuro**.  
    Se invece almeno un processo rimane con `Finish[i] = false`, significa che la simulazione si è bloccata prima di completare tutti i processi: lo **stato è non sicuro**.

> 📌 L'obiettivo dell'algoritmo non è scegliere quale processo eseguire davvero, ma verificare se esiste almeno una sequenza teorica di completamento che garantisca l'assenza di deadlock.

---
### **8. Algoritmo di richiesta delle risorse**

Quando un processo $P_i$ effettua una richiesta `Request[i]`:

1. Se $Request[i] > Need[i]$ → **errore** (richiesta illegale, sta chiedendo più di quanto gli manchi davvero per poter concludere correttamente la propria computazione!!
    
2. Se $Request[i] > Available$ → **attesa** (risorse non disponibili). (qui credo che il docente dia equivalenza tra le scritture available e work in quanto bisognerebbe basarsi sulle ATTUALMENTE disponibili durante il lavoro piuttosto che sulla config iniziale...)
    
3. Altrimenti:
    
    - ipotizza di concedere la richiesta;
        
    - aggiorna temporaneamente:
        
    
    $$  
    \begin{cases}  
    Available = Available - Request[i] \\\\  
    Allocation[i] = Allocation[i] + Request[i] \\\\  
    Need[i] = Need[i] - Request[i]  
    \end{cases}  
    $$
    
    - se lo stato risultante è **sicuro**, la richiesta viene **approvata**;
        
    - altrimenti viene **annullata** e ripristinato lo stato precedente.

---
### **9. Sintesi finale**

$$  
\begin{cases}  
\textbf{Evitamento:}~ & \text{il sistema concede risorse solo se resta in stato sicuro.} \\\\  
\textbf{Stato sicuro:}~ & \text{sequenza di processi che possono completarsi senza deadlock.} \\\\  
\textbf{Algoritmo del grafo:}~ & \text{gestisce risorse a istanza singola, rileva cicli.} \\\\  
\textbf{Algoritmo del banchiere:}~ & \text{gestisce risorse multiple, verifica la sicurezza prima di ogni allocazione.}  
\end{cases}  
$$

---
### **10. Conclusione**

Le tecniche di **evitamento del deadlock** non vietano le richieste di risorse, ma le **controllano dinamicamente** per garantire che il sistema rimanga sempre in **uno stato sicuro**.  
L'**algoritmo del banchiere**, pur essendo computazionalmente costoso, rappresenta il modello più elegante e generale per evitare deadlock nei sistemi reali.

> ✅ **Garanzia del banchiere.** L'algoritmo garantisce che **se un processo è in grado di ottenere tutte le risorse richieste senza creare condizioni di deadlock, allora le ottiene effettivamente e può procedere nella sua esecuzione**. Le richieste vengono rifiutate (o messe in attesa) **solo** quando soddisfarle porterebbe ad uno stato non sicuro — quindi l'algoritmo non è inutilmente avversario, è "giusto" nel concedere ciò che è concedibile in sicurezza.
