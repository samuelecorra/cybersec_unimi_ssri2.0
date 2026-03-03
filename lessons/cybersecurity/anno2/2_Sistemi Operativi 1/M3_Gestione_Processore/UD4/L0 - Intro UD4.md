# **Lezione 0 - Intro Unità Didattica 4 – Comunicazione tra processi**

Dopo aver studiato la schedulazione e la sincronizzazione, affrontiamo ora un aspetto altrettanto cruciale: **la comunicazione tra processi**.  
In un sistema operativo moderno, i processi raramente operano in modo isolato: spesso devono **cooperare**, **scambiarsi dati** o **sincronizzare le proprie azioni** per portare a termine un compito comune.

La comunicazione tra processi (IPC – _InterProcess Communication_) fornisce proprio gli strumenti per rendere possibile questa cooperazione.  
Essa permette ai processi di:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Condividere informazioni.} \\\\  
\textbf{2.}~ & \text{Coordinare le rispettive attività.} \\\\  
\textbf{3.}~ & \text{Sincronizzarsi in modo controllato.}  
\end{cases}  
$$

Studieremo quindi:

- il concetto di **processi cooperanti** e perché è fondamentale nei sistemi multiprogrammati;
    
- i **meccanismi di comunicazione** che il sistema operativo mette a disposizione, come **pipe**, **messaggi**, **memoria condivisa** e **socket**.

L’obiettivo è comprendere **come i processi possano collaborare senza conflitti**, garantendo coerenza, sicurezza e corretto scambio di dati anche in ambienti concorrenti o distribuiti.