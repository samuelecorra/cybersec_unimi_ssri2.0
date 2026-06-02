# **M4 UD3 Lezione 4 - Agenti mobili**

---

### **1. Introduzione**

Questa lezione introduce brevemente un'ulteriore tecnica per realizzare la computazione in un ambiente distribuito: gli **agenti mobili**.

L'obiettivo non è approfondire in modo completo il modello ad agenti, ma citarne l'esistenza e chiarire il ruolo che può avere nella distribuzione della computazione.

La trattazione dettagliata degli agenti e degli agenti mobili appartiene ai corsi specifici dell'area dell'ingegneria del software.

> 📌 In questa sede gli agenti mobili vengono introdotti come tecnica aggiuntiva per spostare computazione e comportamento in un sistema distribuito.

---

### **2. Obiettivo del modello ad agenti**

L'obiettivo fondamentale del modello ad agenti è **innalzare il livello di astrazione della computazione**.

Invece di vedere solo programmi messi in esecuzione come processi o thread, il modello ad agenti descrive la computazione tramite entità software più autonome.

Queste entità:

- racchiudono risorse;
- espongono operazioni;
- interagiscono con l'ambiente;
- possono cooperare con altre entità;
- possono decidere come evolvere.

Il modello prende ispirazione dalla tecnologia a oggetti, ma aggiunge l'idea di comportamento attivo.

---

### **3. Richiamo al modello a oggetti**

Nel modello a oggetti, un oggetto è una collezione di:

- dati;
- metodi per gestire, usare e accedere a quei dati.

L'oggetto incapsula le proprie caratteristiche interne e mostra all'esterno solo un'interfaccia di alto livello.

I programmi vengono ancora realizzati specificando l'interazione tra oggetti, processi o thread.

> 💡 Il modello a oggetti incapsula dati e metodi; il modello ad agenti aggiunge autonomia e capacità di azione.

---

### **4. Agente software**

Un **agente** è un'entità software autonoma.

Rispetto a un normale programma o a una semplice collezione di oggetti, un agente è:

- attivo;
- proattivo;
- capace di operare in un ambiente;
- capace di decidere come evolvere;
- capace di interagire con il mondo esterno;
- capace di cooperare con altri agenti o servizi.

Un agente non si limita quindi a essere chiamato passivamente, ma può svolgere azioni in funzione dei propri obiettivi e dell'ambiente in cui opera.

> ✅ Un agente è un'entità software che possiede una certa autonomia operativa.

---

### **5. Agente mobile**

Un **agente mobile** è un agente dotato della capacità di muoversi all'interno di un sistema distribuito.

Può spostarsi tra macchine diverse per:

- cercare risorse;
- individuare servizi;
- avvicinarsi ai dati;
- eseguire localmente parte della computazione;
- interagire con le singole macchine della rete.

L'agente mobile non attende soltanto che le risorse gli vengano fornite: può decidere dove andare a reperirle.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 L'agente mobile è un agente capace di decidere come e dove muoversi nella rete per svolgere la propria computazione.

---

### **6. Differenza rispetto a processi e oggetti**

Un processo tradizionale viene eseguito su una macchina e interagisce con altri processi tramite comunicazione e sincronizzazione.

Un oggetto incapsula dati e metodi, ma normalmente non decide autonomamente dove spostarsi.

Un agente mobile combina:

- incapsulamento;
- autonomia;
- capacità di azione;
- mobilità nel sistema distribuito.

Questa combinazione consente di descrivere la computazione a un livello più alto rispetto alla semplice attivazione di processi remoti.

---

### **7. Sintesi**

| Concetto | Descrizione |
|---|---|
| **Oggetto** | Collezione di dati e metodi con interfaccia astratta |
| **Agente** | Entità software autonoma, attiva e proattiva |
| **Agente mobile** | Agente capace di spostarsi tra nodi di un sistema distribuito |
| **Obiettivo** | Innalzare il livello di astrazione della computazione distribuita |
| **Uso nel corso** | Citazione introduttiva a completamento delle tecniche di distribuzione |

---

### **8. Conclusione**

Gli agenti mobili rappresentano una tecnica ulteriore per gestire la computazione in un ambiente distribuito.

Un agente è un'entità software autonoma capace di operare e cooperare.

Un agente mobile aggiunge la capacità di spostarsi nella rete, decidendo dove reperire risorse e servizi necessari alla propria computazione.

Nel contesto di questo corso è sufficiente conoscerne il ruolo generale, senza approfondire i dettagli progettuali e implementativi propri dell'ingegneria del software.
