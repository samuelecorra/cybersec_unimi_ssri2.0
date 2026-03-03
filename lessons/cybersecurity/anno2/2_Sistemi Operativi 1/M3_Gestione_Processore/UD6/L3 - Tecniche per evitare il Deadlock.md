# **M3 UD6 Lezione 3 - Tecniche per evitare il Deadlock**

### **1. Introduzione**

Dopo aver visto come **prevenire** il deadlock rimuovendo a priori una delle condizioni di Coffman,  
ci concentriamo ora su un approccio più flessibile: **l’evitamento del deadlock** (_deadlock avoidance_).

L’idea di base è semplice ma potente:  
$$  
\text{Prima di concedere una risorsa, il sistema verifica se la richiesta può portare a deadlock.}  
$$

In pratica, il sistema non vieta le situazioni potenzialmente rischiose, ma **analizza ogni richiesta in tempo reale**, concedendola solo se il sistema rimane in **uno stato sicuro**.

---
### **2. Principio dell’evitamento**

L’evitamento del deadlock consiste nel:

- **verificare a priori** se l’allocazione di una risorsa può causare deadlock;
    
- **accettare** solo le richieste che mantengono il sistema in **stato sicuro**;
    
- **bloccare temporaneamente** le richieste che porterebbero a uno stato non sicuro.

$$  
\text{Stato sicuro} \Rightarrow \text{nessun deadlock} \  
\text{Stato non sicuro} \Rightarrow \text{deadlock possibile}  
$$

L’obiettivo è quindi **massimizzare l’efficienza** senza sacrificare la **sicurezza del sistema**.

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

---
#### **5.2. Funzionamento**

Quando un processo richiede una risorsa:

1. Il sistema **sostituisce** l’arco di prenotazione con un arco di richiesta.
    
2. Se la risorsa è libera, l’arco di richiesta diventa **arco di assegnazione**.
    
3. Il sistema verifica che **non si crei un ciclo** nel grafo.

Se si forma un ciclo → **lo stato diventa non sicuro** → la richiesta viene **negata**.

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

$$  
\begin{cases}  
\textbf{Available}[1..m]~ & \text{vettore delle risorse disponibili.} \\\\  
\textbf{Max}[1..n,1..m]~ & \text{matrice del numero massimo di risorse richieste da ciascun processo.} \\\\  
\textbf{Allocation}[1..n,1..m]~ & \text{matrice delle risorse attualmente allocate.} \\\\  
\textbf{Need}[1..n,1..m]~ & \text{risorse ancora necessarie: } Need = Max - Allocation.  
\end{cases}  
$$

---
### **7. Algoritmo di verifica dello stato sicuro**

#### **7.1. Variabili di supporto**

$$  
\begin{cases}  
\textbf{Work}[1..m]~ & \text{copie temporanee delle risorse disponibili.} \\\\  
\textbf{Finish}[1..n]~ & \text{booleani che indicano se un processo può terminare.}  
\end{cases}  
$$

#### **7.2. Passaggi operativi**

1. Inizializzazione:  
    $$Work = Available, \quad Finish[i] = false \text{ per tutti i processi.}$$
    
2. Cerca un processo $P_i$ tale che:  
    $$Finish[i] = false \quad \text{e} \quad Need_i \leq Work$$
    
3. Se trovato, simula la sua terminazione:  
    $$Work = Work + Allocation_i, \quad Finish[i] = true$$  
    e torna al passo 2.
    
4. Se per tutti i processi $Finish[i] = true$, allora **lo stato è sicuro**.

---
### **8. Algoritmo di richiesta delle risorse**

Quando un processo $P_i$ effettua una richiesta `Request[i]`:

1. Se $Request[i] > Need[i]$ → **errore** (richiesta illegale).
    
2. Se $Request[i] > Available$ → **attesa** (risorse non disponibili).
    
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
L’**algoritmo del banchiere**, pur essendo computazionalmente costoso, rappresenta il modello più elegante e generale per evitare deadlock nei sistemi reali.