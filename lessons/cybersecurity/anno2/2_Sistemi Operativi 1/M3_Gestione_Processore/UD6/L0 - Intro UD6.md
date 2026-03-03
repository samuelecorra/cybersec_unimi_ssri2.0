# Lezione 0 - Intro Unità Didattica 6 - Deadlock

### **Introduzione**

Dopo aver studiato la **sincronizzazione** tra processi e i meccanismi di mutua esclusione, ci concentriamo ora su uno dei problemi più critici dei sistemi concorrenti: il **deadlock** (o _stallo_).

Il deadlock rappresenta una situazione in cui **due o più processi rimangono bloccati indefinitamente**, ciascuno in attesa di una risorsa detenuta da un altro processo del gruppo.  
Nessuno può proseguire, poiché ogni processo **trattiene qualcosa che serve agli altri** — creando un circolo vizioso di attese reciproche.

$$  
\text{P}_1 \Rightarrow \text{attende risorsa di P}_2 \quad \text{e} \quad  
\text{P}_2 \Rightarrow \text{attende risorsa di P}_1  
$$

Il risultato è un **blocco permanente del sistema**, che può compromettere non solo i processi coinvolti ma l’intera stabilità del sistema operativo.

---
### **Obiettivi dell’unità**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comprendere il concetto e le cause del deadlock.} \\  
\textbf{2.}~ & \text{Analizzare le condizioni necessarie al suo verificarsi.} \\  
\textbf{3.}~ & \text{Studiare i principali metodi di gestione:} \\  
& \quad \text{– prevenzione} \\  
& \quad \text{– evitamento} \\  
& \quad \text{– rilevamento e ripristino.}  
\end{cases}  
$$

---
### **Collegamento concettuale**

In questa unità si passa dal **sincronizzare correttamente** i processi al **gestire gli errori di sincronizzazione**.  
Il deadlock rappresenta, infatti, **il fallimento estremo della cooperazione** tra processi:  
una condizione che il sistema operativo deve **prevedere, evitare o risolvere** per garantire il corretto funzionamento del sistema monoprocessore.