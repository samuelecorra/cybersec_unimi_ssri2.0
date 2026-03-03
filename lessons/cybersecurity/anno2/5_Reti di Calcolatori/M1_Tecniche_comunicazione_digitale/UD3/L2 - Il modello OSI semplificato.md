# **Lezione 2: Il modello OSI semplificato**

### **1. Dalla teoria alla pratica**

Il **modello ISO/OSI** nasce come schema teorico ideale, con **sette livelli distinti** e **sei interfacce** tra un livello e l’altro.  
Ogni interfaccia definisce **come i dati passano da un livello al successivo**, con funzioni e responsabilità chiaramente separate.

Questa modularità è preziosa dal punto di vista concettuale, ma nella pratica **non tutte le applicazioni di rete rispettano rigorosamente i sette livelli**.  
L’evoluzione tecnologica e, soprattutto, la diffusione dei protocolli **Internet (TCP/IP)** hanno portato a una **semplificazione** del modello.

---

### **2. L’influenza dei protocolli Internet**

Con l’affermazione dello **stack TCP/IP**, molte funzioni originariamente separate nel modello OSI sono state **accorpate** in livelli più ampi.  
L’obiettivo era **ridurre le interfacce** e semplificare l’implementazione dei protocolli reali, mantenendo comunque una struttura stratificata.

Infatti, mentre il modello OSI era pensato come **astrazione universale**, lo stack TCP/IP è nato da **necessità operative concrete**: far comunicare tra loro computer diversi, in modo semplice ed efficiente.

---

### **3. Confronto tra OSI e TCP/IP**

Vediamo Sora come i due modelli si relazionano tra loro.

![](imgs/Pasted%20image%2020260213010822.png)

Come si può notare:

- i **livelli superiori** (applicazione, presentazione, sessione) vengono **unificati** nel livello **Applicazione** di TCP/IP, che gestisce direttamente la comunicazione tra programmi;
    
- il livello **Rete** del modello OSI corrisponde al livello **Internet** nello stack TCP/IP, dove opera il protocollo IP;
    
- infine, i livelli **Data Link** e **Fisico** vengono spesso trattati come un unico blocco, chiamato **Network Access Layer** o **livello di accesso alla rete**.
    

---

### **4. Il modello OSI semplificato (5 livelli)**

Per scopi didattici o di analisi, si adotta spesso una **versione intermedia a 5 livelli**, che mantiene una buona chiarezza concettuale ma più vicina alla realtà dei protocolli moderni:

![](imgs/Pasted%20image%2020260213011246.png)

|**Livello**|**Funzione principale**|
|:--|:--|
|**Applicazione**|Interfaccia con l’utente e i programmi di rete (HTTP, FTP, DNS...)|
|**Trasporto**|Comunicazione affidabile end-to-end (TCP, UDP)|
|**Rete**|Instradamento e indirizzamento logico (IP)|
|**Collegamento dati (Data Link)**|Trasmissione affidabile su un singolo collegamento (Ethernet, Wi-Fi)|
|**Fisico**|Trasmissione dei bit sul mezzo fisico|

Questa versione è detta **OSI semplificato**, e rappresenta un ottimo compromesso tra **teoria e pratica**.

---

### **5. La logica della riduzione**

La riduzione dei livelli nasce da tre motivazioni principali:

1. **Semplificazione dell’implementazione**: meno interfacce significa meno overhead e maggiore efficienza.
    
2. **Allineamento con i protocolli reali**: Internet utilizza solo quattro (o cinque) livelli ben definiti, senza separazioni rigide come presentazione/sessione.
    
3. **Convergenza hardware/software**: molte funzioni che in passato richiedevano un livello dedicato (es. crittografia, compressione) oggi sono gestite direttamente dalle applicazioni o dai dispositivi fisici.
    

---

### **6. Divisione in livelli di responsabilità**

Nonostante le semplificazioni, il principio fondamentale rimane invariato: **ogni livello ha una responsabilità chiara e distinta**.

- **Livelli inferiori** → gestiscono **trasmissione e instradamento** dei dati (aspetto tecnico).
    
- **Livelli superiori** → gestiscono **applicazioni e semantica** della comunicazione (aspetto logico).
    

Questa separazione consente:

- indipendenza tra protocolli di diversi livelli;
    
- sostituibilità dei moduli (es. si può cambiare il protocollo di rete senza riscrivere l’applicazione);
    
- e maggiore chiarezza nell’analisi dei problemi di comunicazione.
    

---

### **7. Sintesi concettuale**

- Il modello **ISO/OSI** nasce con **7 livelli teorici** e **6 interfacce**.
    
- Nella pratica, lo **stack TCP/IP** ne riduce il numero a **4** (Applicazione, Trasporto, Internet, Accesso alla rete).
    
- Il **modello OSI semplificato** a **5 livelli** rappresenta la versione didattica più usata oggi.
    
- La riduzione delle interfacce è dovuta all’evoluzione delle reti e all’influenza dei protocolli Internet.
    
- Ogni livello mantiene la propria **responsabilità logica**, garantendo **modularità e interoperabilità**.

![](imgs/Pasted%20image%2020251125063252.png)

![](imgs/Pasted%20image%2020251125063300.png)

