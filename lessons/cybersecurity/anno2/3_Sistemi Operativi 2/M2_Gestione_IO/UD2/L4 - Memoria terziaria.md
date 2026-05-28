# **M2 UD2 Lezione 4 - Memoria terziaria**

---

### **1. Introduzione**

La **memoria terziaria** estende lo spazio di memoria di massa per contenere quantità di informazioni molto più ampie di quelle gestibili con i normali dischi permanentemente connessi al sistema.

Si basa sull'uso di supporti rimovibili, conservabili separatamente dal sistema di elaborazione e collegabili solo quando serve leggere, aggiornare o depositare informazioni.

In questa lezione vengono trattati:

- obiettivi della memoria terziaria;
- periferiche per l'archiviazione terziaria;
- caratteristiche richieste ai supporti;
- gestione dei dispositivi fisici;
- problemi di denominazione dei file;
- gestione gerarchica della memoria.

> 📌 La memoria terziaria serve ad archiviare grandissimi volumi di dati a costo molto basso, accettando una frequenza di accesso ridotta e tempi di accesso più elevati.

---

### **2. Obiettivi della memoria terziaria**

#### **2.1. Grandi quantità di informazione**

La memoria terziaria si rende necessaria quando le informazioni da conservare sono troppo numerose per essere mantenute nei normali sistemi di memoria di massa.

Invece di usare solo dispositivi permanentemente connessi al calcolatore, si usano supporti rimovibili che possono essere archiviati in grandi quantità e collegati quando necessario.

#### **2.2. Basso costo per unità di informazione**

Un obiettivo fondamentale è ottenere un costo unitario per informazione memorizzata molto basso.

Questo permette di archiviare grandi quantità di dati a costi contenuti, soprattutto quando i dati sono consultati raramente.

#### **2.3. Bassa frequenza di accesso**

La memoria terziaria è conveniente quando la frequenza con cui si accede alle informazioni è bassa.

Il supporto può rimanere scollegato dal sistema e venire riconnesso solo quando serve manipolare o aggiornare i dati.

> ⚠️ La memoria terziaria non è pensata per accessi frequenti o interattivi: privilegia capacità e costo rispetto alla latenza.

---

### **3. Periferiche di archiviazione terziaria**

Le periferiche tipiche per la memoria terziaria sono dispositivi rimovibili.

#### **3.1. Dischi rimovibili**

Esempi:

- dischetti;
- dischi magnetici rimovibili;
- dischi ottici;
- altri supporti removibili.

Questi dispositivi possono essere collegati al sistema solo quando servono e conservati separatamente per lunghi periodi.

#### **3.2. Nastri magnetici**

I **nastri magnetici** sono supporti storicamente importanti per backup e archiviazione di grandi quantità di dati.

Offrono costi molto bassi per unità di informazione, ma accesso tipicamente sequenziale.

#### **3.3. Tecnologie emergenti**

Esistono anche tecnologie più recenti o sperimentali che possono diventare interessanti per l'archiviazione terziaria, ma non sempre sono ancora mature per un uso ampio e diffuso.

---

### **4. Caratteristiche richieste**

Le periferiche per la memorizzazione terziaria devono possedere alcune caratteristiche fondamentali.

#### **4.1. Velocità**

Devono essere ragionevolmente veloci, con:

- buona larghezza di banda;
- latenza di accesso sufficientemente contenuta rispetto all'uso previsto.

La velocità è importante, ma non è il requisito dominante come nei dischi usati frequentemente.

#### **4.2. Affidabilità**

La caratteristica fondamentale è l'**affidabilità**.

I supporti devono poter essere:

- scollegati dal sistema;
- conservati per lungo tempo;
- riletti dopo lunghi periodi;
- eventualmente aggiornati o manipolati senza perdita delle informazioni.

> 📌 Nella memoria terziaria l'affidabilità a lungo termine è essenziale: il supporto deve conservare correttamente i dati anche fuori dal sistema di elaborazione.

#### **4.3. Costo**

Il costo deve essere basso, così da permettere la realizzazione di grandi archivi a costi contenuti.

Il basso costo è accettabile proprio perché la frequenza di accesso è ridotta.

---

### **5. Gestione della memoria terziaria**

La gestione della memoria terziaria richiede due livelli:

- gestione del dispositivo fisico, come per qualunque periferica;
- costruzione di un'astrazione virtuale che renda il supporto utilizzabile dal sistema operativo e dalle applicazioni.

#### **5.1. Dischi rimovibili**

I dischi rimovibili possono essere mostrati al sistema in due modi:

- come **dispositivo grezzo**, cioè come insieme di blocchi;
- come supporto strutturato con un **file system**.

Durante il periodo in cui sono montati nel sistema, possono spesso essere condivisi tra più processi.

L'accesso può essere:

- diretto, come nei dischi fissi;
- sequenziale, se la tecnologia o l'organizzazione lo richiede.

#### **5.2. Nastri magnetici**

I nastri magnetici sono gestiti tipicamente come dispositivi grezzi, senza una struttura di file system complessa.

I file vengono collocati uno dopo l'altro sul supporto.

Caratteristiche principali:

- uso esclusivo da parte di un processo alla volta;
- accesso sequenziale;
- tempi di posizionamento elevati;
- buona convenienza per archiviazione e backup.

> ⚠️ Nei nastri magnetici l'accesso sequenziale è una conseguenza della natura del supporto: raggiungere un dato può richiedere lo scorrimento di una lunga porzione del nastro.

---

### **6. Denominazione dei file**

Uno dei problemi principali nella gestione della memoria terziaria è la **denominazione univoca dei file**.

Poiché i supporti sono rimovibili, un file deve poter essere identificato in modo univoco anche quando il supporto viene spostato da un sistema a un altro.

#### **6.1. Problema della portabilità**

La portabilità del supporto rende necessario identificare:

- il supporto fisico;
- il file system eventualmente contenuto nel supporto;
- il singolo file all'interno del supporto.

Attualmente non esiste una soluzione completamente omogenea e universale per tutti i tipi di supporto rimovibile.

#### **6.2. Soluzioni parziali**

Esistono alcuni standard per specifiche classi di dispositivi, per esempio nel mondo dei CD-ROM o dei DVD.

Negli altri casi, l'identificazione univoca è spesso lasciata:

- all'applicazione;
- al sistema di archiviazione;
- all'utente.

> ⚠️ Senza una denominazione univoca del supporto e dei file, diventa difficile integrare informazioni archiviate su supporti diversi in un unico spazio logico coerente.

---

### **7. Gestione gerarchica della memoria**

Un modo per realizzare l'astrazione della memoria terziaria è la **gestione gerarchica della memoria** (*Hierarchical Storage Management*, **HSM**).

L'obiettivo è estendere la visione del file system della memoria secondaria per incorporare anche la memoria terziaria, fornendo una visione omogenea a utenti e programmi.

#### **7.1. Integrazione nel file system**

La gestione gerarchica richiede di assegnare una denominazione univoca ai supporti terziari, così da integrare la porzione di file system presente su questi dispositivi nel file system globale del sistema.

#### **7.2. Migrazione dei file**

In un sistema HSM:

- i file piccoli e usati frequentemente restano sui dischi magnetici o sui supporti secondari più veloci;
- i file grandi, vecchi o poco usati vengono spostati su dispositivi terziari, per esempio dischi ottici in un jukebox o nastri.

Questa organizzazione consente di mantenere veloci i dati più usati e ridurre il costo dei dati meno usati.

> 💡 L'HSM nasconde all'utente la migrazione dei file tra livelli diversi: l'utente vede un unico spazio logico, mentre il sistema decide dove collocare fisicamente i dati.

---

### **8. Sintesi finale**

> ✅ La memoria terziaria estende la capacità di archiviazione usando supporti rimovibili, economici e affidabili, adatti a dati consultati raramente.

In questa lezione sono stati analizzati:

- obiettivi della memoria terziaria: grande capacità, basso costo e bassa frequenza di accesso;
- supporti tipici: dischi rimovibili, dischi ottici e nastri magnetici;
- caratteristiche richieste: velocità sufficiente, elevata affidabilità e basso costo;
- gestione dei dischi rimovibili come raw device o file system;
- gestione dei nastri come dispositivi sequenziali e dedicati;
- problema della denominazione univoca dei file;
- gestione gerarchica della memoria per integrare memoria secondaria e terziaria.

La memoria terziaria completa la gerarchia di memorizzazione offrendo grande capacità a basso costo, a prezzo di tempi di accesso più elevati e gestione più complessa.
