# **Lezione 0 - Intro Unità Didattica 1 – Struttura dei sistemi operativi**

### **1. Introduzione**

In questa unità studieremo **come è fatto e come funziona un sistema operativo**.  
Dopo aver analizzato nel Modulo 1 la macchina fisica, ci concentriamo ora sul **software che la governa**, ossia il sistema operativo: il mediatore tra **hardware e applicazioni**.

Il suo ruolo è gestire le risorse, controllare l’esecuzione dei programmi, coordinare le periferiche e offrire all’utente un ambiente stabile e interattivo.

---
### **2. Obiettivi**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comprendere le funzioni fondamentali di un sistema operativo.} \\\\  
\textbf{2.}~ & \text{Conoscere le principali architetture dei sistemi operativi.} \\\\  
\textbf{3.}~ & \text{Capire come avviene la generazione e l’avvio (bootstrap) del sistema.} \\\\  
\textbf{4.}~ & \text{Riconoscere le interfacce fornite dal sistema: CLI, GUI e API.}  
\end{cases}  
$$

---
### **3. Struttura generale**

Il sistema operativo è composto da **moduli** che si occupano di:

- **gestione della CPU, della memoria e dell’I/O**,
    
- **controllo e protezione dei processi**,
    
- **fornitura di interfacce** per utenti e applicazioni.

A seconda del modello architetturale può essere:

- **monolitico**,
    
- **stratificato**,
    
- **a microkernel**,
    
- o **modulare**.

---
### **4. Conclusione**

Il sistema operativo è ciò che **trasforma il calcolatore in un ambiente funzionante**:  
coordina risorse, gestisce i processi e stabilisce le regole di comunicazione tra hardware e software.  
Da qui inizieremo a esplorare **come la sua struttura influenza il funzionamento interno del kernel**.