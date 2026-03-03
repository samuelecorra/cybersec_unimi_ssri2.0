# Lezione 0 - Intro Unità 2 - Thread

### **1. Introduzione**

Dopo aver studiato il concetto di **processo** come unità base di esecuzione, in questa unità analizzeremo un livello più fine: il **thread**.  
Un **thread** rappresenta **un flusso di esecuzione interno a un processo**, cioè la parte del programma che effettivamente utilizza la CPU.

L’introduzione dei thread nasce dall’esigenza di **aumentare la concorrenza** senza moltiplicare il numero di processi completi: più thread possono condividere lo stesso spazio di memoria, comunicare rapidamente e cooperare all’interno dello stesso programma.

---
### **2. Obiettivi dell’unità**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comprendere il concetto di thread e i vantaggi rispetto ai processi tradizionali.} \\\\  
\textbf{2.}~ & \text{Conoscere le architetture dei sistemi multi-threaded.} \\\\  
\textbf{3.}~ & \text{Studiare le principali strategie di schedulazione dei thread.}  
\end{cases}  
$$

---
### **3. Collegamento concettuale**

Se il processo è una “fabbrica” che gestisce risorse e dati, i thread sono gli “operai” che **eseguono realmente le istruzioni**.  
Capire come vengono **creati, sincronizzati e pianificati** è essenziale per comprendere la gestione moderna della CPU nei sistemi **multi-core** e **multi-processore**.