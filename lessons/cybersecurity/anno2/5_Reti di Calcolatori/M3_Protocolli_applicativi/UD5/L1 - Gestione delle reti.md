# **Lezione 1: Gestione delle reti**

---

### **1. Il problema della gestione di rete**

Un sistema autonomo comprende, come abbiamo visto nel modulo 2, moltissimi componenti hardware — macchine host, router, dispositivi di vario tipo — che devono interagire tra loro per far funzionare correttamente Internet.

Il concetto di **gestire una rete** di questo tipo non è soltanto un problema di avere dei protocolli per comunicare con i dispositivi e per modificarne la configurazione: è un problema molto più complesso, di cui l'aspetto tecnologico dei protocolli e delle tecniche di interrogazione e modifica degli stati degli apparati è **soltanto un lato**.

L'intero insieme di **risorse umane**, **aspetti organizzativi** e **aspetti tecnologici** ha un unico obiettivo: consentire alla rete di funzionare a un **costo contenuto** e comunque sufficiente per assicurarne il **successo commerciale**.

---

### **2. Aree funzionali della gestione di rete**

Chi ha esperienza di gestione della rete come parte del proprio lavoro sa bene che gestire una rete coinvolge varie aree funzionali, ognuna con propri protocolli e proprie figure professionali:

#### **a. Gestione delle risorse**

Inventario e supervisione di tutte le risorse fisiche e logiche: dispositivi, software, licenze, indirizzi IP, connessioni e capacità operative.

#### **b. Gestione della sicurezza**

Controllo dell'accesso alle risorse di rete e tutela dell'integrità dei dati e dei dispositivi. Include autenticazione degli utenti, definizione dei permessi e protezione da intrusioni.

#### **c. Gestione delle prestazioni**

Analisi e ottimizzazione dei parametri di efficienza: banda disponibile, latenza, throughput, tassi di errore, tempi di risposta. Lo scopo è identificare colli di bottiglia e migliorare la qualità del servizio.

#### **d. Gestione delle configurazioni**

Definizione, modifica e distribuzione delle configurazioni di rete per mantenere un ambiente coerente anche in presenza di aggiornamenti.

#### **e. Interventi in caso di guasti**

Rilevamento, diagnosi e risoluzione dei problemi. Un sistema efficiente deve identificare rapidamente il guasto, determinarne la causa e — se possibile — automatizzare il ripristino del servizio.

---

### **3. L'orientamento agli oggetti nella gestione di rete**

L'idea di fondo che sta alla base della gestione della rete è l'**orientamento agli oggetti**.

I protocolli di gestione non mandano messaggi generici: mandano lo **stato di oggetti**. Gli oggetti corrispondono a dispositivi o a **parti di dispositivi** di rete — l'unità più fine è quindi un "oggetto gestibile" che può essere una parte di un singolo apparato, non necessariamente l'intero dispositivo.

Tutti questi oggetti sono rappresentati esplicitamente con l'insieme dei loro **attributi di configurazione**. Le azioni che noi compiamo su di essi per gestire la rete sono **cambiamenti di stato** che andiamo a comandare. Non mandiamo mai messaggi generici: ci riferiamo sempre a oggetti specifici.

---

### **4. La MIB: Management Information Base**

Dove sono memorizzate le rappresentazioni degli oggetti che compongono la rete?

![](imgs/Pasted%20image%2020260225163755.png)

Gli oggetti fisici sono i dispositivi reali. Le loro **rappresentazioni** sono **righe (entry) in un database distribuito** chiamato **MIB (Management Information Base)**.

> 📌 Non esiste un unico punto centralizzato in un sistema autonomo in cui è memorizzato il MIB di quel sistema. I vari dispositivi mantengono ognuno il proprio stato e lo stato delle proprie parti. La MIB è quindi un **database partizionato**, distribuito tra i vari dispositivi della rete.

---

### **5. Il processo di gestione**

L'intero ciclo di gestione si basa su due primitive fondamentali — **GET** e **SET** — applicate agli oggetti della MIB:

![](imgs/Pasted%20image%2020260225163812.png)

1. **Raccolta dati** (GET): lettura dello stato dei vari oggetti nei dispositivi.
2. **Analisi dei dati**: rilevazione dei casi in cui è necessario intervenire.
3. **Intervento** (SET): se è rilevata un'anomalia, si modificano gli stati degli oggetti inviando messaggi di set.
4. **Monitoraggio continuo**: altrimenti si rimane nel loop di lettura e acquisizione dei dati di stato.

> ✅ Il processo è quindi un ciclo iterativo: **GET → analisi → SET (se necessario) → ritorno al GET**.

---

### **6. Il protocollo CMIP**

Negli anni '80 fu proposto un protocollo per fare il get and set degli stati dei dispositivi di rete, con l'ambizione di diventare **uno standard mondiale**: fu presentato all'**ISO (International Standard Organization)** e si chiamava **CMIP (Common Management Information Protocol)**.

CMIP fu molto importante perché fornì le **idee di base** che sono alla radice di tutta la gestione di rete moderna:

1. L'idea che i protocolli di gestione fossero **orientati agli oggetti**.
2. L'idea che le principali primitive fossero di tipo **GET e SET**.

Nonostante questo contributo concettuale fondamentale, CMIP **non è mai diventato un prodotto di successo**. I prodotti di successo nella gestione di rete si basano su protocolli diversi che vedremo nelle prossime lezioni.

---

### **7. Conclusione**

La gestione delle reti è una disciplina che integra risorse umane, aspetti organizzativi e tecnologie di protocollo. Il paradigma orientato agli oggetti — con le sue primitive GET e SET applicate a una MIB distribuita — è il fondamento concettuale su cui si basano tutti i protocolli di network management moderni, da CMIP (storico) a SNMP (standard de facto).
