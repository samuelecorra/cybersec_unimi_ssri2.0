# **Lezione 0 - Intro Unità Didattica 3 – Schedulazione**

### **1. Introduzione**

Dopo aver studiato **processi** e **thread**, il passo successivo è capire **come il sistema operativo decide chi esegue e quando**.  
Questo compito è affidato allo **scheduler**, il componente del kernel che assegna la **CPU** ai vari processi o thread in base a **politiche** e **criteri di efficienza**.

La schedulazione è dunque l’insieme delle strategie che permettono al sistema di garantire:

- massima **utilizzazione del processore**,
    
- **risposta rapida** agli utenti interattivi,
    
- **equità** tra i processi concorrenti.

---
### **2. Obiettivi dell’unità**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comprendere il concetto di schedulazione e il ruolo dello scheduler.} \\\\  
\textbf{2.}~ & \text{Conoscere i criteri per valutare l’efficacia di una politica di schedulazione.} \\\\  
\textbf{3.}~ & \text{Studiare le principali politiche di schedulazione adottate nei sistemi operativi.}  
\end{cases}  
$$

---
### **3. Collegamento concettuale**

La schedulazione rappresenta il **cuore dinamico** della gestione del processore:  
è qui che il sistema operativo decide _quale processo merita la CPU_ in ogni istante,  
bilanciando **reattività, produttività e giustizia**.