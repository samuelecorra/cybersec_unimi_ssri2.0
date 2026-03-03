# **M3 UD3 Lezione 2 - Tecniche di realizzazione della protezione**

### **1. Introduzione**

Dopo aver analizzato i concetti generali della protezione, questa lezione affronta le **tecniche pratiche di realizzazione** dei meccanismi di sicurezza nei sistemi operativi.  
L’obiettivo è comprendere come rappresentare, applicare e gestire i **diritti di accesso** tra domini (utenti o processi) e risorse (oggetti del sistema).

Le principali modalità di implementazione sono:

- la **matrice d’accesso**,
    
- le **liste di controllo degli accessi (ACL)**,
    
- le **liste di capacità dei domini**,
    
- e il **meccanismo serratura-chiave (lock-key)**.

---

### **2. Matrice d’accesso**

#### **2.1 Descrizione**

La **matrice d’accesso** è una struttura logica che descrive i **diritti di accesso** di ogni dominio (riga) verso ogni risorsa (colonna).

|               | Oggetto 1 | Oggetto 2 | Oggetto 3 | ... |
|---------------|------------|------------|------------|-----|
| **Dominio 1** | r, w       | r          | -          |     |
| **Dominio 2** | r, x       | w          | r, w       |     |
| **Dominio 3** | -          | r          | x          |     |


Ogni **cella** della matrice contiene l’insieme dei **diritti** (operazioni) che quel dominio può esercitare su quella risorsa.

#### **2.2 Vantaggi**

- Fornisce una **visione globale e completa** dei diritti d’accesso.
    
- Facilita la **gestione e la verifica** della sicurezza del sistema.
    
- Supporta meccanismi di **protezione dinamica** (concessione e revoca di permessi).

#### **2.3 Svantaggi**

- La matrice è in genere **molto sparsa** (molte celle vuote).
    
- Poco efficiente in termini di **spazio e ricerca**, poiché di dimensione $( N_{\text{domini}} \times N_{\text{oggetti}} )$.

---

### **3. Estensioni della matrice d’accesso**

#### **3.1 Diritti di copia**

È possibile estendere la matrice per consentire la **propagazione controllata dei diritti**.  
Un dominio può **concedere a un altro dominio** il diritto di accesso a una risorsa.

Esempio:

- **Prima della copiatura:**
    
    - Il dominio A ha il diritto di lettura su un file.
        
- **Dopo la copiatura:**
    
    - Il dominio B riceve lo stesso diritto, ma solo se A possedeva anche il diritto di **copia** (`copy right`).

Questo meccanismo supporta **deleghe di accesso** controllate.

---

#### **3.2 Diritti di proprietà**

Analogamente, un dominio può possedere il **diritto di proprietà** su una risorsa, che gli consente di **modificare o revocare** i diritti concessi ad altri.

Esempio:

- Il dominio proprietario può:
    
    - aggiungere o rimuovere permessi per altri domini;
        
    - trasferire la proprietà a un altro dominio.

La **matrice d’accesso con diritti di proprietà** consente quindi una **gestione dinamica e flessibile** della sicurezza.

---

### **4. Implementazioni pratiche della matrice**

Poiché una matrice completa sarebbe inefficiente, nella pratica essa viene implementata tramite due strutture equivalenti ma più compatte:

1. **Liste di controllo degli accessi (Access Control List – ACL)**
    
2. **Liste di capacità dei domini (Capability List)**

---

### **5. Liste di controllo degli accessi (ACL)**

#### **5.1 Descrizione**

Per ogni **risorsa** (oggetto) si conserva una **lista di coppie** contenente i domini e i rispettivi diritti:

$$  
\text{Risorsa} = { \langle \text{Dominio}, \text{Diritti} \rangle }  
$$

Esempio:

```
File A:
   <Utente1, {lettura, scrittura}>
   <Utente2, {lettura}>
```

#### **5.2 Vantaggi**

- Informazioni **centralizzate per ogni risorsa**, facilmente consultabili.
    
- I diritti possono essere **specificati direttamente dagli utenti** o dagli amministratori.

#### **5.3 Svantaggi**

- La gestione diventa **inefficiente nei sistemi di grandi dimensioni**, poiché le ACL possono diventare molto estese.
    
- **Revoche frequenti** richiedono aggiornamenti complessi in molte liste.

---

### **6. Liste di capacità dei domini**

#### **6.1 Descrizione**

In questo caso, per ogni **dominio** si conserva la lista delle risorse accessibili e dei relativi diritti:

$$  
\text{Dominio} = { \langle \text{Risorsa}, \text{Diritti} \rangle }  
$$

Esempio:

```
Dominio A:
   <File1, {r, w}>
   <Stampante, {x}>
```

#### **6.2 Vantaggi**

- Informazioni **localizzate** per ciascun dominio.
    
- Facilita la gestione delle **autorizzazioni personali** e delle **deleghe dinamiche**.

#### **6.3 Svantaggi**

- **Revoca dei diritti inefficiente**, poiché i diritti risultano sparsi tra molte liste.
    
- Difficoltà nel mantenere **visione globale** dei permessi di sistema.

---

### **7. Meccanismo serratura-chiave (Lock-Key)**

#### **7.1 Descrizione**

Ogni **risorsa** possiede una **serratura (lock)** e ogni **dominio** una o più **chiavi (key)**.  
L’accesso è consentito solo se la chiave del dominio **combacia con la serratura** della risorsa.

Formalmente:  
$$  
\text{Accesso consentito se } \text{Key}_{dominio} = \text{Lock}_{risorsa}  
$$

#### **7.2 Caratteristiche**

- Serrature e chiavi sono generalmente **stringhe di bit**.
    
- Permettono un controllo di accesso **veloce ed efficiente**.
    
- Adatte a sistemi con **accessi frequenti e dinamici**.

---

### **8. Sistemi basati sulle capacità**

Alcuni sistemi operativi forniscono un **supporto nativo** alla protezione basata su capacità.  
In questi sistemi:

- gli utenti possono **definire, duplicare e trasferire capacità**,
    
- e il sistema operativo ne verifica l’autenticità e la validità ad ogni accesso.

Questa architettura permette **flessibilità e decentralizzazione** nella gestione dei diritti.

---

### **9. Protezione basata sul linguaggio**

La **protezione basata sul linguaggio** integra i meccanismi di sicurezza direttamente nel **linguaggio di programmazione**.

#### **9.1 Funzionamento**

- Il compilatore e il run-time system verificano **i riferimenti e gli accessi alle risorse**.
    
- I controlli vengono effettuati **in fase di compilazione** o **durante l’esecuzione**.

#### **9.2 Vantaggi**

- Consente un **controllo più granulare** e orientato all’applicazione.
    
- La responsabilità è condivisa tra:
    
    - **progettista:** definisce i meccanismi e le regole di base;
        
    - **amministratore:** imposta le politiche di protezione;
        
    - **utente o programmatore:** può specificare diritti aggiuntivi.

#### **9.3 Svantaggi**

- **Minor sicurezza** rispetto ai controlli imposti dal kernel.
    
- **Maggiore flessibilità ed efficienza**, a scapito del rigore formale.

---

### **10. Confronto tra i modelli**

|Meccanismo|Prospettiva|Punti di forza|Limiti|
|---|---|---|---|
|**Matrice d’accesso**|Rappresentazione globale|Chiarezza, completezza|Poco efficiente, struttura sparsa|
|**ACL**|Per risorsa|Centralizzata, intuitiva|Scalabilità limitata|
|**Capability list**|Per dominio|Flessibilità, località|Revoca complessa|
|**Lock-Key**|Basata su matching|Efficienza, semplicità|Limitata flessibilità|
|**Protezione linguistica**|A livello applicativo|Granularità fine, leggerezza|Meno sicura|

---

### **11. Conclusione**

Le tecniche di realizzazione della protezione forniscono **diversi livelli di astrazione e controllo**.  
Dalle matrici d’accesso ai meccanismi lock-key, fino alle protezioni integrate nei linguaggi, tutte perseguono lo stesso obiettivo:  
garantire che **ogni risorsa venga utilizzata solo da chi ne possiede il diritto**, bilanciando **sicurezza, efficienza e flessibilità**.

---

### **12. Fine del Modulo 3**

Con questa lezione si conclude il **Modulo 3 – File System** di _Sistemi Operativi II_.  
Abbiamo analizzato:

- la **struttura e l’implementazione del file system**,
    
- le **strategie di allocazione e gestione dello spazio**,
    
- le **tecniche di protezione delle risorse**.

Il prossimo passo sarà lo studio del **Modulo 4 – Sistemi distribuiti**, che introduce le problematiche di **gestione delle risorse condivise su più nodi** e le **strategie di sincronizzazione e comunicazione** nei sistemi operativi distribuiti.