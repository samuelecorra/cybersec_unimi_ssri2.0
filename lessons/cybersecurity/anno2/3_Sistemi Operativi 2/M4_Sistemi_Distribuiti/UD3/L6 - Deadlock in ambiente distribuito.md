# **M4 UD3 Lezione 6 - Deadlock in ambiente distribuito**

### **1. Introduzione**

Il **deadlock distribuito** è una condizione in cui due o più processi, residenti su **macchine diverse**, rimangono **in attesa reciproca di risorse** che non verranno mai rilasciate.  
La sua gestione è più complessa rispetto al caso di una singola macchina, poiché non esiste un **controllo centralizzato** e ogni nodo dispone solo di **informazioni locali**.

In questa lezione analizziamo:

- come estendere i metodi classici di prevenzione e rilevamento ai sistemi distribuiti;
    
- le principali strategie di gestione dello stallo.

---

### **2. Prevenzione dello stallo**

Le tecniche di prevenzione in ambiente distribuito derivano da quelle per i sistemi centralizzati, ma con modifiche per gestire la **distribuzione delle risorse** e la **latenza di comunicazione**.

#### **2.1 Estensione dei metodi classici**

- Gli **algoritmi per macchina singola** vengono estesi al sistema distribuito.
    
- Si introduce un **ordinamento globale delle risorse**, condiviso tra tutte le macchine.
    
- Il sovraccarico introdotto è **minimo**, poiché non richiede sincronizzazione continua.

#### **2.2 Algoritmo del banchiere distribuito**

- È un’estensione dell’**algoritmo del banchiere di Dijkstra**.
    
- Valuta se l’assegnazione richiesta porterebbe a una **situazione insicura**.
    
- Ha un **sovraccarico elevato** e **prestazioni basse** a causa della **centralizzazione** del controllo.
    
- È adatto solo a **piccoli sistemi distribuiti** o per **simulazioni teoriche**.

---

### **3. Prevenzione con marche di tempo**

Per gestire le richieste in ordine coerente tra nodi diversi, si assegna a ciascun processo una **marca di tempo (timestamp)** che ne rappresenta la priorità globale.

#### **3.1 Rilascio anticipato della risorsa**

Se un processo $P$ possiede una risorsa e un altro processo $Q$ con **priorità più alta** (marca più piccola) la richiede:

- $P$ **rilascia la risorsa**,
    
- viene eseguito un **rollback** del suo stato,
    
- la risorsa viene assegnata a $Q$.

⚠️ Questo metodo può generare **starvation**, poiché i processi a bassa priorità potrebbero venire continuamente interrotti.  
**Soluzione:** non modificare la marca temporale dopo il rollback.

---

### **4. Strategie di prevenzione basate su timestamp**

#### **4.1 Schema _Wait-Die_**

- **Nessun rilascio anticipato.**
    
- Se la risorsa è occupata da un processo $P$:
    
    - Se il processo richiedente $Q$ ha **marca minore**, **attende**.
        
    - Se $Q$ ha **marca maggiore**, effettua **rollback** e “muore” (verrà riavviato più tardi).
    
- Evita starvation se al processo in rollback **non viene assegnata una nuova marca**.

#### **4.2 Schema _Wound-Wait_**

- **Con rilascio anticipato delle risorse.**
    
- Se la risorsa è occupata da $P$:
    
    - Se il richiedente $Q$ ha **marca maggiore**, **attende**.
        
    - Se il richiedente $Q$ ha **marca minore**, forza il **rollback** di $P$ e ottiene la risorsa.
    
- Anche in questo caso la starvation è evitata se le marche non vengono rigenerate dopo il rollback.

---

### **5. Rilevamento dello stallo**

Il rilevamento si basa sull’analisi di un **grafo di attesa (Wait-For Graph, WFG)**, che rappresenta le relazioni tra processi e risorse.

#### **5.1 Principio base**

- Ogni nodo mantiene un **grafo locale** delle proprie attese.
    
- L’unione dei grafi locali forma il **grafo globale**.
    
- Se nel grafo globale esiste un **ciclo**, allora si è verificato uno **stallo**.

#### **5.2 Problema**

L’assenza di cicli nei grafi locali **non garantisce** l’assenza di deadlock globale.  
È quindi necessario combinare o comunicare i grafi tra nodi.

---

### **6. Rilevamento centralizzato**

In questa strategia un **coordinatore centrale** raccoglie e unisce i grafi locali in un **grafo di attesa globale**.

#### **6.1 Costruzione del grafo globale**

- Ogni macchina invia il proprio grafo di attesa locale.
    
- Il coordinatore costruisce un grafo con:
    
    - un **nodo per ogni processo**;
        
    - archi derivati dalle attese locali.

#### **6.2 Analisi**

- Se esiste un **ciclo**, il sistema è in stallo.
    
- Se non esiste, **non si può garantire** che non vi siano deadlock (la verifica è parziale).

#### **6.3 Aggiornamento del grafo**

Due possibili strategie:

1. **Aggiornamento immediato:** ogni volta che un arco viene aggiunto o rimosso.
    
2. **Aggiornamento periodico:** dopo un certo numero di modifiche locali.

Questa soluzione, pur precisa, comporta un **forte carico di comunicazione** e un **punto singolo di guasto**.

---

### **7. Rilevamento distribuito**

Per evitare la centralizzazione, si adotta un approccio **pienamente distribuito**.

#### **7.1 Funzionamento**

- Ogni nodo costruisce **una parte del grafo globale**.
    
- In ogni grafo locale viene aggiunto un **nodo Pex** per rappresentare le **attese verso risorse esterne**.
    
- Se un grafo locale presenta un **ciclo senza Pex**, il sistema è sicuramente in stallo.
    
- Se il ciclo **coinvolge Pex**, è necessario **verificare con altri nodi** se lo stallo è reale.

#### **7.2 Ottimizzazione**

Per evitare sovraccarico di messaggi:

- si assegna un **identificatore univoco** a ciascun processo;
    
- quando una macchina rileva un ciclo con Pex,  
    → invia il messaggio di rilevamento **solo se** il processo precedente nel ciclo ha un identificatore **minore** del successivo;  
    → altrimenti, **ignora** il ciclo, lasciando l’iniziativa ad altri nodi.

Questo riduce la **ridondanza dei messaggi** e il **sovraccarico di rete**.

---

### **8. Gestione dello stallo**

Una volta rilevato lo stallo, è necessario liberare le risorse coinvolte.

#### **8.1 Scelta della vittima**

- Il **coordinatore** seleziona uno dei processi coinvolti nel ciclo come **vittima**.
    
- Tutti i nodi vengono informati della decisione.
    
- I processi che interagivano con la vittima eseguono a loro volta **rollback parziale**.

#### **8.2 Rollback inutili**

In ambiente distribuito, possono verificarsi **falsi positivi**, cioè falsi cicli nel grafo globale dovuti a:

- **ritardi nella trasmissione dei messaggi**,
    
- **aggiornamenti non sincronizzati** dei grafi locali.

Per evitarli:

- ogni richiesta di risorsa deve avere un **identificatore o timestamp univoco**;
    
- nel grafo globale devono apparire **solo le richieste non immediatamente soddisfatte**.

---

### **9. Sintesi finale**

|Tema|Tecnica|Descrizione|
|---|---|---|
|**Prevenzione**|Ordinamento globale, algoritmo del banchiere|Evitare la formazione di cicli di attesa|
|**Prevenzione avanzata**|Wait-Die, Wound-Wait|Uso di timestamp per gestire priorità e rollback|
|**Rilevamento centralizzato**|Coordinatore unico|Analisi globale del grafo di attesa|
|**Rilevamento distribuito**|Analisi cooperativa tra nodi|Cicli con nodo Pex e comunicazione selettiva|
|**Gestione**|Rollback, scelta della vittima|Rimozione dei cicli e ripristino della coerenza|

---

### **10. Conclusione**

La gestione dei **deadlock distribuiti** è una delle sfide più complesse nei sistemi operativi moderni.  
Le strategie di **prevenzione**, **rilevamento** e **gestione** devono garantire un equilibrio tra:

- **precisione del controllo**,
    
- **efficienza della comunicazione**,
    
- **tolleranza ai guasti**.

In sintesi:

> Un sistema distribuito efficace non elimina gli stalli, ma li **riconosce, gestisce e recupera** in modo coerente, minimizzando l’impatto sulle prestazioni complessive.