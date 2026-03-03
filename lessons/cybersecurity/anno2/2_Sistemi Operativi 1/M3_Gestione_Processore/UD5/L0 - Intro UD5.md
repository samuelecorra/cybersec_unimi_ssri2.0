# **Lezione 0 - Intro Unità Didattica 5 – Sincronizzazione dei processi**

Dopo aver analizzato i diversi meccanismi di **comunicazione tra processi**, ci concentriamo ora su un aspetto altrettanto cruciale: la **sincronizzazione**.  
Quando più processi vengono eseguiti **contemporaneamente** — cioè in **concorrenza** — possono accedere alle **stesse risorse** (file, variabili, memoria, dispositivi).  
Per evitare **incoerenze** e **conflitti** è necessario coordinare il loro comportamento.

La **sincronizzazione dei processi** ha quindi l’obiettivo di:  

$$  
\begin{cases}  
\textbf{1.}~ & \text{garantire l’accesso ordinato alle risorse condivise;} \\\\  
\textbf{2.}~ & \text{preservare la consistenza dei dati;} \\\\  
\textbf{3.}~ & \text{rendere le operazioni concorrenti corrette e atomiche.}  
\end{cases}  
$$

In questa unità studieremo:

- il **concetto di concorrenza** e i rischi legati all’esecuzione parallela dei processi;
    
- i **meccanismi di sincronizzazione** (come semafori e monitor);
    
- e infine il concetto di **transazione atomica**, fondamentale nei sistemi che richiedono coerenza assoluta.