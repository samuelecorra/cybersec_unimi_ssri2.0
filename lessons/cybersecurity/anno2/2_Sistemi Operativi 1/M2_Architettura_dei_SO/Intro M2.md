# Intro al Modulo 2

Dopo aver compreso nel Modulo 1 **come è fatto e come funziona un calcolatore fisico**, in questo secondo modulo spostiamo l’attenzione verso **il software che lo governa**:  
il **sistema operativo**.

Un sistema operativo (SO) è un **livello di astrazione** tra l’hardware e i programmi dell’utente.  
Il suo compito è **gestire le risorse del sistema** (CPU, memoria, dispositivi di I/O, file, rete) e **coordinare l’esecuzione dei processi** in modo sicuro, ordinato e ottimizzato.

In questo modulo ci concentreremo su due prospettive complementari:

$$  
\begin{cases}  
\textbf{Funzionale:}~ & \text{quali sono i compiti fondamentali del sistema operativo (gestione, controllo, protezione).} \\\\  
\textbf{Architetturale:}~ & \text{come tali funzioni vengono organizzate e implementate nei diversi modelli di SO.}  
\end{cases}  
$$

---

Alla fine del Modulo 2 dovremo essere in grado di:

1. **Comprendere le problematiche funzionali e architetturali** di un sistema operativo: come esso media tra hardware e processi, quali politiche adotta e come interagisce con le periferiche.
    
2. **Conoscere le diverse architetture dei sistemi operativi**, dai modelli **monolitici** ai **microkernel**, passando per le **architetture stratificate**, **a moduli dinamici**, e **virtualizzate**.

---
Se nel Modulo 1 abbiamo studiato la **macchina fisica di von Neumann**, qui analizzeremo come il sistema operativo la trasforma in una **macchina virtuale più semplice e potente**, in cui ogni processo sembra disporre di:

- una **CPU dedicata** (grazie alla gestione dei processi),
    
- una **memoria propria** (grazie alla memoria virtuale),
    
- e **dispositivi esclusivi** (grazie ai driver e al controllo del kernel).

In sintesi, il sistema operativo agisce come un **“gestore dell’illusione”**, capace di moltiplicare e isolare le risorse fisiche per offrire a ciascun programma un ambiente indipendente e coerente.