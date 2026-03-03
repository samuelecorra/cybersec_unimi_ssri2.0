# **M1 UD3 Lezione 3 - Tecniche di allocazione dei frame**

### **1. Introduzione**

La gestione della **memoria virtuale** non riguarda solo la scelta di **quale pagina sostituire**, ma anche **quante pagine assegnare a ciascun processo**.  
Questa decisione, chiamata **allocazione dei frame**, influenza in modo diretto le prestazioni complessive del sistema e la frequenza di _page fault_.

---

### **2. Il problema dell’allocazione**

Ogni processo necessita di un certo numero di **frame fisici** per eseguire le proprie istruzioni.  
Un numero troppo basso di frame provoca numerosi _page fault_, mentre un numero eccessivo sottrae risorse agli altri processi.

#### **Osservazione**

- All’aumentare del numero di frame assegnati a un processo,  
    → **diminuisce la probabilità di page fault**  
    → **diminuisce il tempo medio di accesso alla memoria**.
    
- Tuttavia, la memoria fisica totale è limitata, quindi il sistema deve stabilire **criteri di distribuzione ottimale**.

#### **Domande principali**

1. Quanti frame assegnare a ciascun processo?
    
2. L’allocazione deve essere **uguale per tutti (omogenea)** o **dipendere da parametri specifici (eterogenea)**?

---

### **3. Vincoli per l’allocazione dei frame**

L’allocazione dei frame non può essere arbitraria: esistono vincoli tecnici e fisici.

#### **a. Numero minimo di frame**

- Ogni processo deve disporre di un numero minimo di frame per poter essere eseguito.
    
- Questo numero dipende dall’**architettura hardware** e dal **set di istruzioni** della CPU.  
    Alcune istruzioni possono infatti riferirsi a più pagine contemporaneamente.

#### **b. Frame disponibili**

- Il numero totale di frame allocabili dipende dalla **memoria centrale fisica installata**.

#### **c. Frame allocati**

- Indichiamo con ( f_i ) il numero di frame assegnati al processo ( P_i ).
    
- Alcuni frame possono essere **condivisi** tra processi (ad esempio, librerie comuni).  
    In tal caso:
    
    - ( r_j ) = frame condivisi dal gruppo di processi ( G_j ).
        
    - ( d ) = frame fisici ancora disponibili.

#### **Vincolo generale**

$$  
\sum_i f_i - \sum_j r_j \le d  
$$

Il totale dei frame assegnati, al netto di quelli condivisi, **non deve superare** il numero di frame fisici disponibili.

---

### **4. Tipi di allocazione**

L’allocazione può essere effettuata in due modalità principali:

- **Allocazione globale:**  
    il sistema sceglie i frame da qualunque area della memoria fisica, senza distinzioni tra processi.
    
- **Allocazione locale:**  
    ogni processo utilizza solo i frame già assegnati al proprio spazio; le sostituzioni avvengono **internamente**.

---

### **5. Allocazione omogenea**

Nel modello più semplice, ogni processo riceve **lo stesso numero di frame**, indipendentemente dalla sua dimensione o priorità.

Sia:

- $( m )$ = numero totale di frame disponibili;
    
- $( n )$ = numero di processi attivi;
    
- $( f_i )$ = frame assegnati al processo ( P_i ).

Allora:

$$  
f_i = \frac{m}{n}  
$$

#### **Caratteristiche**

- **Semplice da implementare**, equa dal punto di vista della distribuzione.
    
- Tuttavia, può risultare **inefficiente** se i processi hanno dimensioni molto diverse.

---

### **6. Allocazione proporzionale alla dimensione**

In questo caso, il numero di frame assegnati a ciascun processo è proporzionale alla **dimensione del suo spazio di memoria virtuale**.

Sia:

- $( m )$ = numero totale di frame disponibili;
    
- $( s_i )$ = dimensione (in pagine) dello spazio virtuale del processo $( P_i )$;
    
- $( f_i )$ = frame assegnati a $( P_i )$.

La formula è:

$$  
f_i = m \cdot \frac{s_i}{\sum_i s_i}  
$$

#### **Caratteristiche**

- I processi più grandi ricevono più frame, riducendo i loro _page fault_.
    
- Garantisce una distribuzione **più equilibrata e proporzionata** alle esigenze effettive.
    
- Tuttavia, i processi piccoli potrebbero risultare **penalizzati**, ricevendo pochi frame.

---

### **7. Allocazione proporzionale alla priorità**

Qui l’allocazione è basata sulla **priorità logica** del processo.  
I processi più importanti o critici ricevono più frame rispetto a quelli secondari.

Sia:

- $( m )$ = numero totale di frame disponibili;
    
- $( p_i )$ = priorità del processo $( P_i )$ (espressa come numero positivo);
    
- $( f_i )$ = frame assegnati a $( P_i )$.

La formula diventa:

$$  
f_i = m \times \frac{p_i}{\sum_i p_i}  
$$

#### **Caratteristiche**

- Permette di favorire i processi con **alta priorità** (es. processi di sistema o in tempo reale).
    
- Può però generare **starvation** per i processi a bassa priorità se la memoria è scarsa.

---

### **8. Sintesi finale**

- L’**allocazione dei frame** influenza direttamente la frequenza dei _page fault_ e quindi le prestazioni del sistema.
    
- Esistono tre principali strategie:
    
    1. **Omogenea:** distribuzione uguale per tutti i processi.
        
    2. **Proporzionale alla dimensione:** più memoria ai processi più grandi.
        
    3. **Proporzionale alla priorità:** più memoria ai processi più importanti.
    
- Ogni sistema operativo adotta una combinazione di queste tecniche, adattandola dinamicamente in base al **carico di lavoro**, alla **memoria disponibile** e al **livello di priorità dei processi**.