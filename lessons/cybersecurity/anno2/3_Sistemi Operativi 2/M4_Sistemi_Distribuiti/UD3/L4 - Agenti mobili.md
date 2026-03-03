# **M4 UD3 Lezione 4 - Agenti mobili**

### **1. Introduzione**

L’evoluzione dei sistemi distribuiti ha portato verso modelli computazionali sempre più **astratti e flessibili**, capaci di rappresentare entità autonome che interagiscono e cooperano all’interno della rete.  
Uno di questi modelli è quello basato sugli **agenti mobili**, che innalza il livello di astrazione della computazione secondo i principi dell’**ingegneria del software orientata agli oggetti**.

---

### **2. Obiettivi**

- Innalzare il livello di astrazione nella rappresentazione della computazione.
    
- Descrivere la computazione tramite **tecnologie orientate agli oggetti**, dotate di **autonomia e capacità di azione**.
    
- Integrare concetti di **mobilità** e **cooperazione** all’interno di un ambiente distribuito.

---

### **3. Modello computazionale ad oggetti**

Nel paradigma orientato agli oggetti:

- Gli **oggetti** sono **collezioni di dati** (stato) e di **procedure (metodi)** per la loro gestione.
    
- Ogni oggetto **incapsula** i propri attributi e le proprie operazioni, fornendo un’interfaccia ben definita al mondo esterno.
    
- In un sistema operativo distribuito, i **programmi** possono essere rappresentati da **processi** o **thread**, che implementano e gestiscono gli oggetti attivi.

Questo modello costituisce la base per la costruzione di **agenti software**, che estendono il concetto di oggetto aggiungendo autonomia decisionale e capacità di movimento.

---

### **4. Agenti software**

Un **agente** è un’entità software caratterizzata da quattro proprietà fondamentali:

1. **Autonomia** – è in grado di prendere decisioni ed eseguire azioni senza intervento diretto dell’utente.
    
2. **Inserimento in un ambiente** – opera in un contesto distribuito, interagendo con altri agenti o risorse.
    
3. **Proattività** – agisce in modo autonomo per raggiungere obiettivi prefissati.
    
4. **Cooperazione** – può comunicare e collaborare con altri agenti per svolgere compiti complessi.

Gli agenti, in questo senso, rappresentano un’estensione naturale del modello a oggetti, in cui ogni entità non è solo passiva ma **attiva, intelligente e collaborativa**.

---

### **5. Agenti mobili**

Gli **agenti mobili** costituiscono una specializzazione ulteriore:  
sono **agenti software capaci di muoversi** dinamicamente all’interno di un sistema distribuito.

#### **Caratteristiche principali**

- **Mobilità:** possono migrare tra nodi diversi della rete.
    
- **Interazione locale:** una volta arrivati su una macchina remota, possono interagire con i suoi servizi e risorse.
    
- **Scoperta di risorse:** esplorano il sistema distribuito per identificare risorse disponibili, servizi attivi o altre entità con cui cooperare.

#### **Esempio concettuale**

Un agente mobile può:

1. Avviarsi su un nodo sorgente.
    
2. Spostarsi su un nodo remoto per elaborare dati locali.
    
3. Restituire il risultato al nodo di origine o a un altro nodo.

Questo riduce il traffico di rete e consente di **avvicinare il calcolo ai dati**, invece di trasferire grandi quantità di dati al calcolo.

---

### **6. Vantaggi degli agenti mobili**

- **Efficienza:** il codice si sposta verso i dati, riducendo la quantità di informazioni trasmesse.
    
- **Scalabilità:** più agenti possono cooperare e bilanciare dinamicamente il carico tra nodi.
    
- **Flessibilità:** si adattano ai cambiamenti dell’ambiente distribuito (nuove risorse, nodi non disponibili).
    
- **Affidabilità:** in caso di guasti, gli agenti possono migrare verso nodi sani, garantendo continuità.
    
- **Modularità:** ogni agente incapsula il proprio comportamento e le proprie politiche decisionali.

---

### **7. In sintesi**

|Concetto|Descrizione|
|---|---|
|**Agente**|Entità software autonoma, proattiva e cooperante|
|**Agente mobile**|Agente con capacità di spostarsi tra nodi del sistema distribuito|
|**Obiettivi**|Innalzare l’astrazione, integrare l’approccio orientato agli oggetti|
|**Applicazioni**|Ricerca di risorse, gestione distribuita, automazione, cloud computing|

---

### **8. Conclusione**

Gli **agenti mobili** rappresentano un passo evolutivo nella progettazione dei sistemi distribuiti:  
spostano il focus dall’esecuzione centralizzata alla **computazione distribuita intelligente**, in cui il software diventa capace di **muoversi, adattarsi e cooperare**.

Questo paradigma è alla base di tecnologie moderne come i **microservizi dinamici**, le **architetture di agenti intelligenti** e i **sistemi autonomi cloud-native**.