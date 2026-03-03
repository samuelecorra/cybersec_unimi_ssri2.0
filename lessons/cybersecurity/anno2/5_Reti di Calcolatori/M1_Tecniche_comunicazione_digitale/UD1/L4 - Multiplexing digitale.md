# **Lezione 4: Multiplexing digitale**

### **1. Origine del multiplexing digitale**

Il **multiplexing digitale** nasce nei **Laboratori Bell**, dove fu sviluppato il primo sistema **T-carrier**, progettato per trasportare **più comunicazioni digitali su un unico cavo**.

L’idea è semplice: se ogni utente richiede solo una piccola porzione di banda, si può **combinare più flussi digitali in uno solo**, ottimizzando la capacità del canale.  
Questa combinazione controllata di più segnali in un canale comune è detta **multiplexing**.

Il primo sistema commerciale fu il **T1**, introdotto da **AT&T**, che poteva trasportare **24 canali vocali** contemporaneamente, ognuno a **56 kbps**, più il bit di segnalazione.

In Europa, l’equivalente del T1 è il **E1**, con una capacità complessiva di **2,048 Mbps**, leggermente superiore a quella americana.

---

### **2. Struttura del sistema T1**

Nel sistema T1, ogni canale vocale viene campionato e digitalizzato (come visto nella Lezione 2), generando un flusso a **64 kbps** (8 bit × 8 kHz).  
Tuttavia, per garantire sincronizzazione e segnalazione, il sistema T1 riserva solo **7 bit utili per frame**, ottenendo effettivamente **56 kbps per utente**.

Il **multiplexer** raccoglie i flussi dei 24 utenti e li alterna ordinatamente in un’unica sequenza binaria condivisa.  
Questo processo si chiama **Time Division Multiplexing (TDM)**.

---

### **3. Gerarchia DS (Digital Signal)**

Negli Stati Uniti, le velocità dei sistemi digitali sono indicate con la sigla **DS-x** (Digital Signal level x).  
Ogni livello rappresenta un insieme gerarchico di canali DS-0 da 64 kbps.

|Livello|Velocità|Numero di canali DS-0|Overhead|Capacità totale|
|---|---|---|---|---|
|**DS-0**|64 kbps|1|–|64 kbps|
|**DS-1**|1.544 Mbps|24|8 kbps|1.544 Mbps|
|**DS-2**|6.312 Mbps|96|168 kbps|6.312 Mbps|
|**DS-3**|44.736 Mbps|672|1.728 kbps|44.736 Mbps|
|**DS-4**|274.176 Mbps|4.032|16.128 kbps|274.176 Mbps|

Ogni livello DS incorpora i flussi del livello inferiore e aggiunge **bit di controllo (overhead)** per la sincronizzazione e la correzione di errori.

---

### **4. Tecniche di multiplexing**

Esistono tre principali **tecniche di accesso multiplo**, usate per condividere un canale tra più stazioni.

#### **a) FDMA – Frequency Division Multiple Access**

- Ogni stazione trasmette su una **frequenza diversa** (o portante).
    
- I segnali non si sovrappongono, ma occupano porzioni diverse dello spettro.
    
- Tipico delle **radio analogiche** e delle reti **1G**.

“prendo tutte le bande allocate ai vari utenti e le sommo per ottenere la banda totale necessaria del sistema”

$$  
\text{Canale totale} = \sum_i \text{Banda}_i  
$$

#### **b) TDMA – Time Division Multiple Access**

- Le stazioni trasmettono **a turno**, in **slot temporali distinti**.
    
- Ogni utente riceve un piccolo intervallo di tempo (frame) ciclico.
    
- È la base del **GSM** e di molte reti digitali.
    

$$  
\text{Frame totale} = t_1 + t_2 + t_3 + \dots + t_n  
$$

#### **c) CDMA – Code Division Multiple Access**

- Tutte le stazioni trasmettono **simultaneamente sulla stessa banda**, ma con **codici diversi**.
    
- I segnali si **sovrappongono**, ma grazie ai codici ortogonali restano **intelligibili**.
    
- È la tecnica usata nei sistemi **IS-95** e **3G (UMTS)**.
    
- È spesso paragonata al **“cocktail party effect”**: molte persone parlano insieme, ma ciascuno riesce a riconoscere una voce specifica.
    

---

### **5. Multiplexer digitale e TDM**

Il **TDM (Time Division Multiplexing)** è la tecnica su cui si basano tutti i sistemi T-carrier.  
Il multiplexer riceve più flussi digitali e li inserisce **uno dopo l’altro** in un’unica sequenza, separandoli logicamente nel tempo.  
A destinazione, un **demultiplexer** esegue l’operazione inversa, ricostruendo i flussi originali.

In altre parole, TDM è come un **autobus digitale**: ogni utente sale al proprio turno e scende alla fermata corretta.

---

### **6. Gerarchia europea E-carrier**

In Europa si utilizza uno standard più regolare, detto **E-carrier**, compatibile con il sistema americano ma con diverse capacità di aggregazione.

|Livello|Velocità|Rapporto con livello inferiore|
|---|---|---|
|**E1**|2.048 Mbps|–|
|**E2**|8.448 Mbps|4 × E1|
|**E3**|34.368 Mbps|16 × E1|
|**E4**|139.264 Mbps|4 × E3|
|**E5**|565.148 Mbps|4 × E4|

Gli standard **T** ed **E** non corrispondono perfettamente tra loro, ma il principio di base è lo stesso: una **gerarchia di flussi multiplexati** che unisce canali più piccoli in canali sempre più veloci.

---

### **7. Sintesi concettuale**

- Il **multiplexing digitale** consente di condividere un unico canale tra più utenti.
    
- Il sistema **T1** (USA) e **E1** (Europa) sono i primi esempi di multiplexing digitale.
    
- Ogni canale vocale occupa **64 kbps (DS-0)**.
    
- Le tecniche principali di accesso multiplo sono **FDMA**, **TDMA** e **CDMA**.
    
- I sistemi T-carrier e E-carrier rappresentano le **gerarchie di trasporto digitale** su cui si fondano le moderne reti di telecomunicazioni.