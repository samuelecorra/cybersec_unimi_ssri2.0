# **Intro al Modulo 1**
### **1. Introduzione**

Questo primo modulo ha lo scopo di **riprendere e focalizzare i concetti fondamentali dell’architettura del calcolatore**, ma in un’ottica nuova: quella del **sistema operativo** che ne governa il funzionamento.  
Se in _Architettura degli Elaboratori_ ci siamo concentrati su **come è costruita e organizzata la macchina fisica**, qui sposteremo lo sguardo su **come essa viene gestita, controllata e virtualizzata** dal software di sistema.

In altre parole, passeremo dal **punto di vista dell’hardware** a quello del **controllore logico che orchestra le sue risorse**.

---
### **2. Collegamento con Architettura degli Elaboratori**

Durante l’insegnamento di _Architettura degli Elaboratori_ abbiamo studiato i meccanismi che rendono possibile l’esecuzione delle istruzioni:

- il **modello di Von Neumann**,
    
- il **ciclo di fetch–decode–execute**,
    
- la **struttura della CPU** e del **bus di sistema**,
    
- le **memorie** (cache, RAM, registri),
    
- i **meccanismi di interruzione (interrupt)**,
    
- e il **concetto di processo** a livello hardware (salvataggio dello stato, contesto, PC, SP, registri, ecc.).

Tutti questi concetti rappresentano la **base fisica** su cui opera il sistema operativo.  
Il SO si colloca esattamente **sopra l’hardware**, e **sfrutta tali meccanismi per creare un ambiente controllato, protetto e multitasking**.  
Ogni volta che un processo viene sospeso, che una risorsa viene assegnata o che un’eccezione viene gestita, il sistema operativo **dialoga direttamente con l’hardware** attraverso istruzioni privilegiate e interfacce specifiche.

---
### **3. Obiettivi del modulo**

L’obiettivo di questo modulo è comprendere:

- **come la struttura fisica del calcolatore influenza le scelte progettuali del sistema operativo**,
    
- **quali componenti hardware devono essere controllati e in che modo**,
    
- e **quali servizi logici il SO costruisce sopra l’architettura fisica** per fornire astrazioni più semplici e uniformi agli utenti e ai programmi.

In sintesi, il modulo mira a creare un **ponte concettuale tra hardware e software di sistema**, mostrando come:  
$$  
\text{Architettura del calcolatore} \longrightarrow \text{Virtualizzazione operata dal Sistema Operativo}  
$$

---
### **4. Prospettiva generale**

Ogni risorsa fisica (CPU, memoria, dispositivi di I/O) diventa, dal punto di vista del sistema operativo, una **risorsa virtuale** gestita da politiche di controllo.  
Questo significa che:

- La **CPU fisica** viene vista come un insieme di **processori virtuali** (uno per processo).
    
- La **memoria fisica** viene trasformata in una **memoria virtuale** sicura e isolata.
    
- Le **periferiche** diventano **file astratti o flussi di dati** gestiti da driver.

Il sistema operativo, in altre parole, **nasconde la complessità dell’hardware** e la sostituisce con un insieme di **astrazioni coerenti, sicure e programmabili**.

---
### **5. Conclusione**

Questo modulo rappresenta la **cerniera logica** tra _Architettura degli Elaboratori_ e _Sistemi Operativi_:  
è il momento in cui i concetti appresi a livello circuitale e funzionale — registri, bus, memoria, CPU — vengono **reinterpretati e governati dal software** che ne coordina l’utilizzo.

Comprendere questa transizione è fondamentale per tutto ciò che seguirà: solo conoscendo **come è fatto un calcolatore** possiamo capire **come un sistema operativo lo controlla**.