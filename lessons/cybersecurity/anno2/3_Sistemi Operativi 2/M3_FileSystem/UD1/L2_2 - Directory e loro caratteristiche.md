# **M3 UD1 Lezione 2 - Directory e loro caratteristiche (parte 2)**

### **1. Introduzione**

Questa seconda parte conclude l’analisi delle **directory** e dell’**interfaccia del file system**, introducendo due aspetti fondamentali dell’organizzazione delle memorie di massa:

- la **partizione** dei dischi,
    
- e il **montaggio dei file system**.

Questi meccanismi consentono al sistema operativo di **gestire più volumi** di memoria e di **rendere accessibili i dati** presenti su dispositivi diversi come se appartenessero a un unico spazio logico coerente.

---

### **2. Memorie di massa a dischi multipli**

I sistemi moderni possono disporre di più **dischi fisici**, ciascuno dei quali rappresenta una **unità indipendente** di memorizzazione.

Ogni disco può contenere:

- **uno o più file system**, oppure
    
- aree destinate ad altri scopi (es. **swap**, **RAID**, **archiviazione**).

Il sistema operativo deve quindi fornire un **modello logico unificato**, nascondendo la complessità fisica sottostante.  
Questa unificazione viene realizzata attraverso la **partizione** e il **montaggio** dei file system.

---

### **3. Partizioni**

#### **3.1 Definizione**

Una **partizione** è un’**astrazione logica** del dispositivo fisico di memorizzazione.  
Consente di **suddividere un disco** in più sezioni indipendenti, ognuna delle quali può essere gestita separatamente.

#### **3.2 Caratteristiche**

- Ogni partizione appare al sistema come **un disco autonomo**.
    
- Può ospitare un **file system distinto**, un’**area di swap** o una **partizione di boot**.
    
- Le partizioni possono essere **fisse o ridimensionabili**, a seconda del supporto e del sistema operativo.

#### **3.3 Vantaggi**

- Maggiore **flessibilità** nella gestione dello spazio.
    
- Possibilità di **separare dati e sistemi operativi** sullo stesso disco.
    
- Supporto a **sistemi multi-boot** e configurazioni complesse.
    
- Aumento della **sicurezza** e **affidabilità**, isolando aree potenzialmente soggette a errori.

#### **3.4 Esempio**

Un disco può essere suddiviso in:

- una partizione per il **sistema operativo**;
    
- una per i **dati utente**;
    
- e una dedicata allo **swap** o al **backup**.

---

### **4. Montaggio dei file system**

#### **4.1 Definizione**

Il **montaggio** è l’operazione mediante la quale un file system presente su un dispositivo viene **reso accessibile** all’interno della gerarchia logica principale del sistema.

Il punto di collegamento tra il file system montato e quello principale è detto **punto di montaggio (mount point)**.

#### **4.2 Processo di montaggio**

1. Il sistema operativo individua il **dispositivo o la partizione** da montare.
    
2. Viene verificata la **struttura del file system** e la **coerenza dei metadati**.
    
3. Il file system viene **collegato alla directory principale (root)** in corrispondenza del punto di montaggio.
    
4. Da quel momento, i file e le directory della partizione diventano **parte integrante** dell’albero logico del sistema.

#### **4.3 Esempio**

In un sistema UNIX o Linux:

```bash
mount /dev/sdb1 /mnt/dati
```

- `/dev/sdb1` → partizione fisica del disco;
    
- `/mnt/dati` → directory del file system principale dove viene montato.

Tutti i file della partizione `/dev/sdb1` diventano accessibili sotto il percorso `/mnt/dati`.

#### **4.4 Smontaggio**

L’operazione inversa, detta **unmount**, disconnette il file system dalla gerarchia logica.  
È necessaria per:

- garantire l’integrità dei dati;
    
- evitare corruzioni dovute a scritture incomplete o processi ancora attivi.

---

### **5. Visione logica del file system**

Il **file system montato** appare come parte integrante del sistema operativo.  
L’utente e i processi non percepiscono differenze tra file appartenenti a dischi o partizioni differenti: l’intera memoria appare come **un unico albero logico coerente**.

#### **Schema concettuale**

$$  
\text{File System Globale} =  
\text{File System Principale (root)} +  
\sum_{i=1}^{n} \text{File System Montati su partizioni o dispositivi esterni}  
$$

---

### **6. In sintesi**

|Concetto|Descrizione|
|---|---|
|**Partizione**|Astrazione logica di un disco fisico; può contenere file system o altre aree|
|**Montaggio**|Operazione che collega un file system secondario alla gerarchia principale|
|**Punto di montaggio**|Directory che funge da collegamento tra due file system|
|**Vantaggi**|Flessibilità, modularità, sicurezza, isolamento dei dati|
|**Visione logica**|Il sistema operativo unifica più file system in un unico spazio coerente|

---

Con la partizione e il montaggio, il sistema operativo realizza una **visione astratta e virtuale della memoria di massa**, consentendo di:

- gestire in modo uniforme dispositivi differenti;
    
- separare logicamente dati e programmi;
    
- integrare supporti esterni o remoti nel file system locale.