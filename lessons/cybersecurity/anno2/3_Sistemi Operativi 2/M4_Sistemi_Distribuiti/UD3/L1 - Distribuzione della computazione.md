# **M4 UD3 Lezione 1 - Distribuzione della computazione**

### **1. Introduzione**

La **computazione distribuita** consiste nel suddividere un compito complesso tra più nodi di un sistema distribuito, in modo che ciascuno esegua una parte del lavoro.  
L’obiettivo è migliorare l’**efficienza complessiva**, ridurre i tempi di risposta e ottimizzare l’uso delle risorse disponibili nella rete.

---

### **2. Motivazioni**

Le principali motivazioni che spingono a distribuire la computazione sono:

1. **Aumento della velocità di elaborazione**
    
    - Si sfrutta il **parallelismo**, permettendo l’esecuzione simultanea di più parti di un’applicazione.
        
    - Si ottiene una **diminuzione della latenza**, un **aumento del throughput** e una **riduzione del tempo di risposta**.
    
2. **Accesso efficiente a risorse informative o fisiche**
    
    - I nodi possono essere specializzati e avere accesso a **risorse locali ottimizzate** (database, dispositivi, sensori, ecc.).
    
3. **Elaborazione su grandi quantità di dati**
    
    - La distribuzione consente di **trattare dataset di dimensioni elevate**, spesso già localizzati su nodi differenti, riducendo il traffico di rete.
    
4. **Tolleranza ai guasti**
    
    - La presenza di più nodi consente la **ridondanza** e la **sopravvivenza del sistema** anche in caso di malfunzionamento di uno di essi.
        
    - Il sistema può rilevare e correggere errori, garantendo continuità operativa.

---

### **3. Obiettivi della distribuzione della computazione**

Lo scopo della computazione distribuita è **spostare l’elaborazione verso la macchina che possiede le risorse più adatte** a soddisfare l’obiettivo applicativo.

In altre parole:

> la computazione deve avvenire **là dove i dati o le risorse necessarie sono già presenti**, evitando trasferimenti inutili di informazioni e migliorando le prestazioni complessive.

---

### **4. Tecniche per la distribuzione della computazione**

Per realizzare un sistema distribuito efficiente si utilizzano diverse **tecniche operative**:

1. **Chiamate di procedura remote (RPC – Remote Procedure Call)**
    
    - Consentono di eseguire funzioni o procedure **su nodi remoti** come se fossero locali.
        
    - Mascherano la complessità della comunicazione in rete e semplificano la programmazione distribuita.
    
2. **Allocazione di processi**
    
    - I processi vengono **assegnati dinamicamente ai nodi** più idonei in base alle risorse disponibili (CPU, memoria, carico di rete, ecc.).
        
    - Permette un **bilanciamento del carico (load balancing)** e un uso efficiente dell’infrastruttura.
    
3. **Agenti distribuiti**
    
    - Un **agente** è un processo autonomo in grado di **migrare** tra nodi, spostando con sé il proprio stato e codice.
        
    - Gli agenti permettono **computazione mobile**, **adattività** e **ottimizzazione locale** delle operazioni.

---

### **5. Tecniche di supporto alla computazione distribuita**

Oltre alle tecniche di distribuzione, sono necessarie funzionalità di **supporto** che permettano ai processi di interagire e coordinarsi tra nodi diversi:

1. **Comunicazione tra processi distribuiti**
    
    - Meccanismi di scambio messaggi o invocazioni remote (RPC).
        
    - Garantisce la coerenza dei dati e la sincronizzazione delle attività.
    
2. **Sincronizzazione tra processi distribuiti**
    
    - Coordinamento degli eventi in assenza di un orologio globale.
        
    - Utilizzo di **clock logici** e algoritmi di sincronizzazione per ordinare le operazioni in modo coerente.

---

### **6. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Motivazioni**|Parallelismo, accesso efficiente alle risorse, gestione di grandi dati, tolleranza ai guasti|
|**Obiettivi**|Spostare la computazione dove si trovano le risorse più adatte|
|**Tecniche principali**|Chiamate di procedura remote, allocazione di processi, agenti distribuiti|
|**Supporto necessario**|Comunicazione e sincronizzazione tra processi|

---

### **7. Conclusione**

La **computazione distribuita** permette di sfruttare in modo ottimale la potenza complessiva di una rete di sistemi cooperanti.  
Attraverso tecniche di **parallelismo, migrazione e coordinamento**, è possibile ottenere elaborazioni più rapide, affidabili e scalabili, ponendo le basi per i moderni ambienti **cloud e grid computing**.