# Intro al Modulo 3

### **1. Cosa faremo**

In questo modulo entriamo nel **cuore operativo del sistema**: la **CPU**.  
Dopo aver studiato come è strutturato e avviato un sistema operativo, ora analizzeremo **come esso gestisce il processore** per garantire che tutti i processi in esecuzione condividano la CPU in modo ordinato, efficiente e controllato.

L’obiettivo principale è comprendere **come il sistema operativo crea l’illusione** che ogni processo disponga di una CPU dedicata, mentre in realtà un solo processore (o pochi core) serve molti programmi contemporaneamente.

---

### **2. Obiettivi del modulo**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comprendere le problematiche di gestione e condivisione del processore.} \\  
\textbf{2.}~ & \text{Conoscere le principali tecniche di scheduling e controllo della CPU.} \\  
\textbf{3.}~ & \text{Saper valutare quale strategia di gestione risulta più adatta in base al contesto.}  
\end{cases}  
$$

---

### **3. Concetto chiave**

Il sistema operativo è il **direttore d’orchestra** della CPU:  
decide _chi_ può usare il processore, _quando_ e _per quanto tempo_, garantendo equilibrio tra **reattività, efficienza e correttezza**.

Nei moduli successivi vedremo come questo controllo sia realizzato attraverso **processi, thread, politiche di scheduling e meccanismi di sincronizzazione**.