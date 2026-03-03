# **M3 UD1 Lezione 2 - Creazione e terminazione dei processi**

### **1. Introduzione**

In questa lezione analizziamo **come i processi vengono generati e come terminano la loro esecuzione**.  
Ogni sistema operativo deve poter **creare nuovi processi**, **gestire la loro esecuzione concorrente** e **terminarli correttamente** liberando le risorse.

Capire come avvengono questi meccanismi significa comprendere **la dinamica della vita di un processo**: dalla nascita alla fine.

---
### **2. Processi come flussi di esecuzione**

Un **processo** è un **flusso di operazioni**: una sequenza ordinata di istruzioni che elabora dati nel tempo.  
Più processi rappresentano **flussi paralleli o concorrenti di computazione**, che possono **collaborare o evolvere indipendentemente**.

$$  
\begin{cases}  
\textbf{Flussi sincronizzati:}~ & \text{processi che si coordinano durante l’esecuzione.} \\\\  
\textbf{Flussi indipendenti:}~ & \text{processi che operano autonomamente, senza sincronizzazione.}  
\end{cases}  
$$

Questa distinzione è fondamentale: un sistema operativo deve poter **gestire entrambi i tipi** in modo corretto, evitando conflitti e garantendo prestazioni ottimali.

---
### **3. Modelli di computazione e realizzazione**

Il comportamento dei processi può essere modellato in base al **modo in cui il programma e i suoi flussi sono organizzati**.

#### **3.1. Modelli di computazione**

$$  
\begin{cases}  
\textbf{Processo monolitico:}~ & \text{un solo flusso di esecuzione — un unico processo.} \\\\  
\textbf{Processi cooperanti:}~ & \text{più flussi sincronizzati o concorrenti che collaborano tra loro.}  
\end{cases}  
$$

#### **3.2. Modelli di realizzazione del codice eseguibile**

$$  
\begin{cases}  
\textbf{Programma monolitico:}~ & \text{un unico file eseguibile che rappresenta tutto il lavoro.} \\\\  
\textbf{Programmi separati:}~ & \text{più eseguibili distinti che cooperano o si comunicano.}  
\end{cases}  
$$

#### **3.3. Corrispondenza tra programmi e processi**

$$  
\begin{cases}  
\text{Programma monolitico} & \Rightarrow \text{Processo monolitico.} \\\\  
\text{Programma monolitico che genera altri flussi} & \Rightarrow \text{Processi cooperanti.} \\\\  
\text{Programmi separati} & \Rightarrow \text{Processi cooperanti (possono generare altri processi).}  
\end{cases}  
$$

In sostanza, un programma può generare **uno o più processi**, e i processi stessi possono **creare altri processi**, costruendo una struttura gerarchica.

---
### **4. Generazione di un processo**

Quando un processo in esecuzione desidera creare un nuovo processo, **invoca una funzione del sistema operativo** che genera e attiva il processo figlio.

$$  
\begin{cases}  
\textbf{Processo padre:}~ & \text{il processo che invoca la creazione.} \\\\  
\textbf{Processo figlio:}~ & \text{il nuovo processo creato dal padre.}  
\end{cases}  
$$

#### **4.1. Condivisione delle risorse**

Alla nascita, il processo figlio può:  
$$  
\begin{cases}  
\textbf{Condividere le risorse del padre;} \\\\  
\textbf{Condividerle solo parzialmente;} \\\\  
\textbf{Essere completamente indipendente, ottenendo risorse proprie dal sistema.}  
\end{cases}  
$$

Oltre alle risorse, il padre può **passare dati di inizializzazione** al figlio (es. parametri, ambiente di esecuzione, argomenti di input).

---
### **5. Spazio di indirizzamento**

Il **processo figlio** ha sempre un proprio **spazio di indirizzamento separato**, ma il suo contenuto iniziale dipende dal metodo di creazione usato.

$$  
\begin{cases}  
\textbf{Caso 1 – Spazio distinto:}~ & \text{il figlio ha un proprio spazio di memoria, diverso da quello del padre.} \\\\  
\textbf{Caso 2 – Spazio duplicato:}~ & \text{il figlio riceve una copia dello spazio del padre (stesso codice e dati all’inizio).} \\\\  
\textbf{Caso 3 – Spazio nuovo:}~ & \text{il figlio carica un nuovo programma nel suo spazio di indirizzamento.}  
\end{cases}  
$$

Questo meccanismo è analogo a quanto accade in sistemi UNIX con le chiamate `fork()` e `exec()`:

- `fork()` duplica il processo,
    
- `exec()` sostituisce il contenuto del processo figlio con un nuovo programma.

---
### **6. Esecuzione dei processi padre e figlio**

Dopo la creazione, padre e figlio possono eseguire **concorrentemente**.  
Le modalità principali sono:

$$  
\begin{cases}  
\textbf{Esecuzione concorrente:}~ & \text{il padre continua subito l’esecuzione, in parallelo col figlio.} \\\\  
\textbf{Esecuzione sincronizzata:}~ & \text{il padre attende che il figlio (o tutti i figli) termini prima di proseguire.}  
\end{cases}  
$$

Da queste relazioni nasce una struttura gerarchica detta **albero dei processi**, dove:

- ogni nodo rappresenta un processo;
    
- gli archi rappresentano relazioni padre–figlio.

Il **processo radice** (root) è spesso il primo processo generato dal sistema operativo (es. `init` in UNIX o `System` in Windows).

---
### **7. Terminazione dei processi**

Quando un processo termina, libera le proprie risorse e notifica il sistema operativo (e il padre) del suo completamento.  
Esistono due modalità di terminazione principali:

$$  
\begin{cases}  
\textbf{Terminazione normale:}~ & \text{il processo esegue l’ultima istruzione e restituisce un valore di stato.} \\\\  
\textbf{Terminazione anomala:}~ & \text{il processo viene interrotto per un errore o per richiesta esterna.}  
\end{cases}  
$$

#### **7.1. Terminazione normale**

- Tutte le risorse allocate vengono **deallocate**.
    
- Il processo restituisce un **codice di uscita** (es. `exit(0)` per successo).
    
- Il padre può leggere questo valore per sapere se l’esecuzione è andata a buon fine.

#### **7.2. Terminazione anomala**

Cause comuni:  
$$  
\begin{cases}  
\text{Uso eccessivo di risorse (memoria, CPU);} \\\\  
\text{Errore di esecuzione (divisione per zero, accesso illecito);} \\\\  
\text{Interruzione da parte dell’utente o del sistema;} \\\\  
\text{Terminazione a cascata:}~ \text{quando un padre viene terminato e i suoi figli vengono chiusi automaticamente.}  
\end{cases}  
$$

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Creazione:}~ & \text{un processo padre genera un figlio invocando una funzione del SO.} \\\\  
\textbf{Risorse:}~ & \text{possono essere condivise, parzialmente condivise o indipendenti.} \\\\  
\textbf{Spazio di indirizzamento:}~ & \text{può essere distinto, duplicato o nuovo.} \\\\  
\textbf{Esecuzione:}~ & \text{concorrente o sincronizzata tra padre e figlio.} \\\\  
\textbf{Terminazione:}~ & \text{può essere normale o anomala, con liberazione delle risorse.}  
\end{cases}  
$$

---
### **9. Conclusione**

La creazione e la terminazione dei processi rappresentano il **ciclo vitale del multitasking**.  
Il sistema operativo, come un direttore d’orchestra, **coordina la nascita, la cooperazione e la morte dei processi**, mantenendo sempre l’equilibrio tra efficienza e stabilità.

Ogni volta che un nuovo processo nasce, il sistema si arricchisce di un nuovo flusso di esecuzione;  
ogni volta che uno termina, le risorse tornano disponibili per altri.  
È un ecosistema dinamico e controllato: la base stessa del funzionamento moderno di ogni computer.