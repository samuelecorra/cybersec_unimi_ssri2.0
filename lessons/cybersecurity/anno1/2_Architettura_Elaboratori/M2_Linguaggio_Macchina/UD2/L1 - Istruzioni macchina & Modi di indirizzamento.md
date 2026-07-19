## ***Lezione 1: Istruzioni macchina e modi di indirizzamento***

---

> 📌 Questa lezione rielabora integralmente le pagine 13–18 di `M2doc.pdf`.

### **1. Il vocabolario della CPU**

Una CPU non comprende direttamente costrutti come `while`, oggetti o funzioni di libreria: riconosce un insieme finito di **istruzioni macchina**. Questo insieme, insieme ai registri visibili, ai tipi di dato, ai formati e ai modi di indirizzamento, costituisce la sua **Instruction Set Architecture** o ISA.

L’ISA è il confine contrattuale tra hardware e software: il programmatore o il compilatore produce istruzioni conformi all’ISA; la microarchitettura le realizza mediante circuiti interni.

### **2. Componenti interni della CPU**

I blocchi mostrati nel PDF svolgono funzioni differenti:

| Componente | Natura | Ruolo |
| --- | --- | --- |
| **registri** | elementi di memoria bistabili | conservano temporaneamente parole binarie |
| **ALU** | circuito combinatorio | esegue operazioni aritmetiche e logiche |
| **Control Unit** | circuito sequenziale | ordina nel tempo trasferimenti e operazioni |
| **bus interni** | vie di comunicazione | collegano registri, ALU e unità di controllo |

Un registro non “decide” nulla: mantiene il valore caricato finché un segnale di controllo non lo modifica. ALU e unità di controllo sono invece i blocchi attivi del percorso di elaborazione.

### **3. Registri visibili al programmatore**

#### **3.1 Program Counter**

Il **PC** contiene l’indirizzo della prossima istruzione da prelevare. L’incremento produce l’esecuzione sequenziale; scrivere un nuovo valore nel PC realizza salti, chiamate e ritorni.

#### **3.2 General Purpose Registers**

I **GPR** conservano operandi, risultati intermedi e indirizzi. Poiché sono interni alla CPU, il loro accesso è più diretto di un accesso alla memoria centrale.

#### **3.3 Condition Codes**

I **CC** registrano proprietà dell’ultimo risultato rilevante e permettono di prendere decisioni. Una CPU generica può offrire flag di segno, zero, riporto e overflow; nella LC-2 studiata più avanti i codici visibili sono precisamente **N**, **Z** e **P**.

### **4. Registri di interfaccia con memoria**

- l’**Instruction Register** conserva l’istruzione attualmente decodificata o eseguita;
- il **Memory Address Register** presenta alla memoria l’indirizzo coinvolto nell’accesso;
- il **Memory Data Register** contiene la parola in transito da o verso la memoria.

MAR e MDR sono come “finestre” della CPU sui bus esterni: il primo riguarda il dove, il secondo il contenuto trasferito.

### **5. Forma di un’istruzione**

Ogni istruzione contiene almeno:

- un **opcode**, che identifica l’operazione;
- uno o più campi **operando**, che specificano registri, costanti o modalità per trovare i dati.

Le istruzioni si possono raggruppare in:

1. **operative**, per calcoli aritmetici e logici;
2. **di trasferimento**, per spostare dati fra registri, memoria e I/O;
3. **di controllo**, per modificare il flusso di esecuzione.

### **6. Modi di indirizzamento**

Un modo di indirizzamento stabilisce come interpretare il campo operando e calcolare il dato o l’**indirizzo effettivo**.

| Modo | Operando indicato dall’istruzione | Passaggio essenziale |
| --- | --- | --- |
| **immediato** | il valore stesso | nessun accesso a memoria per l’operando |
| **diretto** | l’indirizzo del dato | $DR\leftarrow M[A]$ |
| **indiretto** | l’indirizzo di una cella che contiene l’indirizzo del dato | $DR\leftarrow M[M[A]]$ |
| **base + offset** | registro base e spostamento | $EA\leftarrow R_b+\text{offset}$ |

#### **6.1 Immediato**

In `ADD R0, R0, #5`, il 5 è codificato nell’istruzione. È rapido, ma l’ampiezza del campo limita l’intervallo delle costanti.

#### **6.2 Diretto**

L’istruzione identifica una cella e ne legge o scrive il contenuto. È semplice, ma il campo indirizzo può non bastare per tutto lo spazio di memoria.

#### **6.3 Indiretto**

La prima cella contiene un puntatore alla seconda. Richiede un accesso in più, ma consente di raggiungere indirizzi completi e implementa il concetto di puntatore.

#### **6.4 Base + offset**

Somma un piccolo spostamento al contenuto di un registro. È utile per array, strutture e zone contigue: mantenendo in un registro l’indirizzo di base, si selezionano elementi vicini cambiando l’offset.

> ✅ L’ISA descrive che cosa la macchina offre al software; i modi di indirizzamento spiegano come ogni istruzione trova i propri operandi.
