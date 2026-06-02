# **M4 UD3 Lezione 3 - Allocazione dei processi**

---

### **1. Introduzione**

Questa lezione affronta il secondo metodo per gestire la computazione in un ambiente distribuito: l'**allocazione dei processi**.

Allocare un processo significa decidere su quale macchina del sistema distribuito il processo deve essere eseguito.

Le due famiglie fondamentali di tecniche sono:

- allocazione statica;
- allocazione dinamica.

> 📌 L'allocazione dei processi cerca di eseguire ogni processo sulla macchina più adatta alla sua computazione.

---

### **2. Obiettivi dell'allocazione**

In un ambiente distribuito, l'allocazione dei processi mira a diversi obiettivi.

#### **2.1. Attivare il processo sulla macchina migliore**

La macchina migliore dipende da:

- carico computazionale delle macchine;
- risorse informative necessarie;
- risorse fisiche necessarie;
- compatibilità hardware e software;
- costo di accesso alle risorse.

Un processo dovrebbe essere attivato sulla macchina che consente di eseguire meglio la sua specifica computazione.

#### **2.2. Bilanciare il carico dei processori**

L'allocazione deve evitare che:

- alcuni processori restino inattivi;
- altri processori siano sovraccarichi.

Il bilanciamento del carico migliora lo sfruttamento globale dei processori e riduce la latenza dei processi.

#### **2.3. Sfruttare meglio le risorse**

Il sistema deve usare in modo efficiente:

- CPU;
- memoria;
- periferiche;
- dati;
- servizi del sistema operativo;
- risorse di rete.

#### **2.4. Minimizzare il costo di gestione**

La gestione dell'allocazione non deve diventare troppo costosa.

Il sistema deve evitare di sprecare risorse di elaborazione per una gestione più complessa del necessario.

#### **2.5. Realizzare tolleranza ai guasti**

L'allocazione può contribuire alla tolleranza ai guasti.

Se una macchina si guasta, i processi possono essere spostati o riattivati su altre macchine.

> ✅ Una buona allocazione migliora prestazioni, uso delle risorse e robustezza complessiva del sistema distribuito.

---

### **3. Allocazione statica**

Nell'**allocazione statica**, la collocazione dei processi viene definita all'atto della loro attivazione.

Una volta assegnato un processo a una macchina, l'allocazione resta permanente per tutta la vita del processo.

Il processo viene caricato e vive sulla macchina scelta.

---

### **4. Allocazione statica completa**

Nell'allocazione statica **completa**, tutti i processi da attivare vengono considerati contemporaneamente.

Il sistema:

1. analizza l'intero insieme dei processi;
2. calcola un'allocazione globale;
3. assegna i processi ai nodi;
4. attiva i processi sulle macchine scelte.

Questa tecnica è adatta quando l'insieme dei processi è noto prima dell'attivazione.

---

### **5. Allocazione statica incrementale**

Nell'allocazione statica **incrementale**, i processi non vengono creati tutti nello stesso momento.

Quando arriva un nuovo gruppo di processi:

1. l'allocazione dei processi già attivi viene considerata congelata;
2. il sistema decide dove allocare solo i nuovi processi;
3. i nuovi processi vengono attivati sulle macchine scelte.

> 📌 Nell'approccio incrementale non si spostano i processi già attivi: si decide solo la collocazione dei nuovi.

---

### **6. Funzioni obiettivo**

Per l'allocazione statica è necessario definire una **funzione obiettivo** da ottimizzare.

La funzione obiettivo esprime cosa il sistema considera una buona allocazione.

#### **6.1. Sfruttamento dei processori**

Una funzione obiettivo può mirare allo sfruttamento dei processori.

Esempi:

- minimizzare il tempo totale di inattività;
- bilanciare i tempi di inattività tra processori;
- ridurre la latenza media dei processi;
- aumentare il throughput complessivo.

#### **6.2. Efficienza della gestione**

Un'altra funzione obiettivo può mirare a ridurre il costo della gestione.

Esempi:

- minimizzare il tempo di accesso alle periferiche;
- minimizzare il tempo di accesso ai servizi del sistema operativo;
- ridurre il costo di comunicazione;
- collocare processi vicino alle risorse che usano.

#### **6.3. Ottimizzazione multiobiettivo**

Se più obiettivi sono importanti, si può usare una tecnica di ottimizzazione multiobiettivo.

In questo caso si cerca di ottimizzare una combinazione di obiettivi.

Per esempio:

$$
F = \alpha \cdot \text{carico} + \beta \cdot \text{costo comunicazione} + \gamma \cdot \text{latenza}
$$

dove $\alpha$, $\beta$ e $\gamma$ pesano l'importanza dei diversi obiettivi.

---

### **7. Vincoli dell'allocazione**

L'ottimizzazione deve rispettare vincoli.

Non tutti i processi possono essere allocati ovunque.

#### **7.1. Vincoli obbligatori**

Alcuni processi devono essere collocati su una macchina specifica.

Per esempio, un processo che controlla una periferica locale può dover essere eseguito sulla macchina a cui la periferica è collegata.

#### **7.2. Vincoli di esclusione**

Alcuni processi non devono mai essere collocati su certe macchine.

Le cause possono essere:

- incompatibilità hardware;
- incompatibilità software;
- assenza di librerie;
- assenza di sistema operativo adatto;
- politiche di sicurezza.

#### **7.3. Vincoli legati alle risorse**

La posizione delle risorse informative o fisiche può condizionare l'allocazione.

Esempi:

- interazione con l'utente;
- base di dati molto grande;
- periferica utilizzabile solo localmente;
- file o servizi disponibili solo su un nodo.

#### **7.4. Incompatibilità tra processi**

Alcuni processi applicativi possono essere incompatibili tra loro se collocati sulla stessa macchina.

L'algoritmo deve quindi cercare una soluzione che ottimizzi la funzione obiettivo rispettando tutti i vincoli.

> ⚠️ L'allocazione non è solo un problema di prestazioni: è un problema di ottimizzazione vincolata.

---

### **8. Classificazione degli algoritmi di allocazione**

Gli algoritmi di allocazione possono essere classificati secondo diversi criteri.

#### **8.1. Modalità di ricerca**

Gli algoritmi possono essere:

- **deterministici**, se esplorano lo spazio delle soluzioni in modo definito;
- **euristici**, se cercano una buona soluzione senza garantire l'esplorazione completa.

Gli approcci euristici sono utili quando lo spazio delle soluzioni è molto grande o ci sono molti vincoli.

#### **8.2. Modalità di esecuzione**

Gli algoritmi possono essere:

- **centralizzati**, se una macchina decide per tutto il sistema distribuito;
- **distribuiti**, se più macchine eseguono parti dell'algoritmo e cooperano nella scelta.

#### **8.3. Qualità della soluzione**

Gli algoritmi possono cercare:

- una soluzione ottima;
- una soluzione subottima ma accettabile.

Spesso ci si accontenta di una soluzione subottima per ridurre il costo della ricerca.

#### **8.4. Ambito di azione**

L'allocazione può essere:

- **globale**, se considera tutta la rete;
- **locale**, se considera una singola macchina o un sottoinsieme.

#### **8.5. Attivazione sender o receiver**

L'algoritmo può essere attivato:

- dal processore **emittente**, che vuole liberarsi di un processo;
- dal processore **ricevente**, che valuta se accettare un processo.

---

### **9. Allocazione dinamica**

Nell'**allocazione dinamica**, la collocazione dei processi può cambiare durante la loro vita.

L'allocazione non è permanente.

Il sistema può rimettere in discussione la posizione di uno o più processi e riallocarli su altri processori.

Questa tecnica serve ad adattarsi a:

- variazioni di carico;
- guasti;
- cambiamenti nelle risorse disponibili;
- nuove esigenze applicative;
- condizioni di prestazione degradate.

---

### **10. Allocazione dinamica totale e parziale**

L'allocazione dinamica può essere:

- totale;
- parziale.

#### **10.1. Allocazione totale**

Viene rivalutata l'allocazione di tutti i processi contemporaneamente.

È più completa, ma più costosa.

#### **10.2. Allocazione parziale**

Viene rivalutata solo l'allocazione di un sottoinsieme di processi.

Il sottoinsieme può essere scelto secondo una regola, per esempio:

- processi su macchine sovraccariche;
- processi con elevata latenza;
- processi che usano risorse diventate remote;
- processi su nodi guasti o degradati.

---

### **11. Attivazione dell'allocazione dinamica**

L'allocazione dinamica può essere attivata in tre modi principali.

#### **11.1. Periodica**

A intervalli regolari viene avviato l'algoritmo di allocazione.

Il sistema rivaluta la collocazione dei processi in modo totale o parziale.

#### **11.2. Reattiva**

L'algoritmo viene attivato quando si verifica una condizione.

Esempi:

- sovraccarico di una macchina;
- guasto di un nodo;
- aumento della latenza;
- esaurimento di una risorsa;
- squilibrio del carico.

#### **11.3. Volontaria**

La riallocazione può essere richiesta direttamente da uno o più processi tramite una chiamata al sistema operativo.

> 📌 L'allocazione dinamica permette di adattare il sistema mentre i processi sono già in esecuzione.

---

### **12. Funzioni obiettivo e vincoli nella dinamica**

Anche nell'allocazione dinamica esistono:

- funzioni obiettivo;
- vincoli;
- algoritmi con caratteristiche analoghe a quelli dell'allocazione statica.

Si possono ottimizzare:

- carico dei processori;
- tempi di risposta;
- costo di comunicazione;
- accesso alle risorse;
- tolleranza ai guasti;
- uso della rete.

Si devono ancora rispettare:

- vincoli hardware;
- vincoli software;
- vincoli di sicurezza;
- vincoli di localizzazione delle risorse.

---

### **13. Problema della migrazione dei processi**

Il problema fondamentale dell'allocazione dinamica è la **migrazione dei processi**.

Se un processo deve essere spostato da una macchina a un'altra, ci sono due possibilità:

- terminare il processo e riavviarlo da zero sulla nuova macchina;
- congelare lo stato della computazione, trasferirlo e riattivarlo sulla macchina destinataria.

La seconda soluzione richiede di trasferire:

- codice;
- dati;
- stato del processo;
- registri;
- contesto di esecuzione;
- informazioni sulle risorse usate.

Bisogna inoltre garantire compatibilità tra macchina sorgente e macchina destinazione.

> ⚠️ La migrazione è complessa perchè deve preservare lo stato di evoluzione del processo.

---

### **14. Costo della migrazione**

Nell'allocazione statica si considera soprattutto il costo di calcolare l'allocazione.

Nell'allocazione dinamica bisogna considerare anche il costo della migrazione.

Il costo include:

- tempo di sospensione del processo;
- tempo di trasferimento dello stato;
- tempo di riconfigurazione;
- tempo di riattivazione;
- traffico generato sulla rete;
- eventuale conversione di rappresentazioni.

La migrazione conviene solo se il beneficio atteso supera questo costo.

$$
\text{migrazione conveniente} \iff \text{beneficio} > \text{costo di migrazione}
$$

---

### **15. Sintesi**

| Aspetto | Allocazione statica | Allocazione dinamica |
|---|---|---|
| **Momento** | All'attivazione dei processi | Durante la vita dei processi |
| **Stabilita'** | Permanente | Modificabile |
| **Tipi** | Completa, incrementale | Totale, parziale |
| **Attivazione** | Prima dell'esecuzione | Periodica, reattiva, volontaria |
| **Costo principale** | Calcolo dell'allocazione | Calcolo + migrazione |
| **Flessibilita'** | Bassa | Alta |
| **Problema centrale** | Scelta iniziale corretta | Migrazione e costo di spostamento |

---

### **16. Conclusione**

L'allocazione dei processi in un ambiente distribuito serve a collocare ogni processo sulla macchina più adatta.

L'allocazione statica decide la collocazione all'attivazione e la mantiene fissa.

L'allocazione dinamica può modificare la collocazione durante l'esecuzione, adattandosi a carico, guasti e disponibilità delle risorse.

La scelta tra le due tecniche dipende dal compromesso tra flessibilità, costo di gestione e costo di migrazione.
