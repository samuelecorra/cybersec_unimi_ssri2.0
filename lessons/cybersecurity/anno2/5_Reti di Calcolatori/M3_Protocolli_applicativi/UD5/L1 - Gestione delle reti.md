# **Lezione 1: Gestione delle reti**

---

### **1. Introduzione**

Le reti moderne sono costituite da **centinaia o migliaia di componenti**, sia **hardware** (router, switch, server, terminali) sia **software** (servizi, applicazioni, protocolli), che interagiscono tra loro in modo continuo e complesso.  
Per garantire che un sistema così articolato funzioni correttamente e con **costi di esercizio contenuti**, è necessario un **processo di gestione della rete** ben strutturato.

La **gestione delle reti** comprende tutte le attività di:

- **sviluppo**, **integrazione** e **coordinamento** di risorse fisiche e umane;
    
- **controllo**, **interrogazione**, **configurazione** e **valutazione** dei componenti di una rete IP;
    
- **monitoraggio** continuo per prevenire guasti e degradazioni delle prestazioni.
    

In sintesi, gestire una rete significa **mantenere in equilibrio un ecosistema tecnologico complesso**, assicurando funzionalità, efficienza e sicurezza.

---

### **2. Aree funzionali della gestione di rete**

La gestione di una rete si articola in **cinque aree funzionali principali**, ciascuna con obiettivi e strumenti specifici.

#### **a. Gestione delle risorse**

Riguarda l’**inventario e la supervisione** di tutte le risorse fisiche e logiche presenti in rete:  
dispositivi, software, licenze, indirizzi IP, connessioni e capacità operative.

#### **b. Gestione della sicurezza**

Controlla l’**accesso alle risorse di rete** e tutela l’integrità dei dati e dei dispositivi.  
Include l’autenticazione degli utenti, la definizione dei permessi e la protezione da intrusioni o attacchi.

#### **c. Gestione delle prestazioni**

Analizza e ottimizza i **parametri di efficienza** della rete: banda disponibile, latenza, throughput, tassi di errore, tempi di risposta.  
Lo scopo è **identificare colli di bottiglia** e migliorare la qualità complessiva del servizio (QoS).

#### **d. Gestione delle configurazioni**

Si occupa della **definizione, modifica e distribuzione** delle configurazioni di rete.  
Permette di mantenere un ambiente coerente anche in presenza di aggiornamenti o nuove installazioni.

#### **e. Gestione dei guasti**

È l’area dedicata al **rilevamento, diagnosi e risoluzione** dei problemi.  
Un sistema di gestione efficiente deve:

- identificare rapidamente un guasto;
    
- determinarne la causa;
    
- e, se possibile, **automatizzare il ripristino del servizio**.
    

---

### **3. La MIB: Management Information Base**

Ogni dispositivo gestito (router, switch, host, ecc.) contiene una serie di **oggetti gestiti**, cioè **parametri e variabili di stato** che descrivono il suo funzionamento.

![](imgs/Pasted%20image%2020260225163755.png)

Tutti questi dati sono raccolti all’interno di una **MIB (Management Information Base)**, una vera e propria **base dati gerarchica** usata per rappresentare le informazioni di gestione di rete.

La MIB consente ai sistemi di monitoraggio di:

- **leggere lo stato** di ciascun dispositivo (es. traffico, errori, connessioni attive);
    
- **modificare parametri operativi** tramite protocolli di gestione come SNMP.
    

---

### **4. Il processo di gestione**

L’intero ciclo di gestione può essere rappresentato come una **sequenza di fasi iterative**:

![](imgs/Pasted%20image%2020260225163812.png)

1. **Raccolta dati** dalle periferiche di rete (tramite agenti SNMP o altri protocolli).
    
2. **Analisi dei dati raccolti**, per individuare anomalie o inefficienze.
    
3. **Configurazione automatica o manuale** dei dispositivi, in base ai risultati dell’analisi.
    
4. **Intervento umano**, se necessario, per decisioni strategiche o manutenzioni critiche.
    
5. **Aggiornamento delle configurazioni**, per mantenere la rete coerente e documentata.
    

Questo processo chiude un ciclo continuo di **osservazione → valutazione → intervento**, che costituisce la base della gestione automatizzata delle reti moderne.

---

### **5. Il protocollo CMIP**

Oltre a SNMP, esiste anche un altro protocollo storico di gestione: **CMIP (Common Management Information Protocol)**.

#### **Caratteristiche principali:**

- è un protocollo **ISO-standard** per il **monitoraggio e controllo** di reti conformi agli standard OSI;
    
- definisce un insieme di **operazioni generali** per il recupero e la modifica di informazioni di gestione;
    
- fu progettato negli **anni ’80** come **standard universale** di network management.
    

#### **Motivo del successo limitato**

Nonostante la sua solidità teorica, CMIP non ebbe un’ampia diffusione a causa di:

- **complessità elevata** di implementazione;
    
- **sviluppo troppo lento** rispetto all’evoluzione pratica di Internet;
    
- maggiore **leggerezza e diffusione di SNMP**, che ne prese il posto come standard de facto.
    

---

### **6. Conclusione**

La gestione delle reti è una disciplina fondamentale per mantenere in efficienza i sistemi informatici distribuiti.  
Ogni rete moderna richiede **monitoraggio continuo, configurazioni coerenti e sicurezza costante**.

Protocolli come **SNMP** e strutture come la **MIB** rappresentano gli strumenti chiave che permettono agli amministratori di controllare **migliaia di dispositivi da un’unica postazione**, riducendo costi e tempi di intervento.

Capire questi principi è la base per comprendere come funziona **l’amministrazione remota** delle infrastrutture di rete globali.