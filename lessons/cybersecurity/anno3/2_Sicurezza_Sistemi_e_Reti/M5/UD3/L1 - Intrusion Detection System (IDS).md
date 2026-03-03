# **UD3 – IDS e IPS**

In questa unità esploreremo i sistemi fondamentali per la **rilevazione e la prevenzione delle intrusioni** all’interno delle reti informatiche: i Intrusion Detection System (IDS) e i Intrusion Prevention System (IPS).  
Vedremo le differenze tra rilevazione e azione, le modalità operative principali, e come questi strumenti si inseriscono in un’architettura di sicurezza multilivello.

- Definire cosa sono un IDS e un IPS.
    
- Illustrare le modalità operative e le caratteristiche distintive dell’IDS rispetto all’IPS.
    
- Presentare i contesti applicativi, i pregi e i limiti di ciascuno.
    
- Preparare il terreno per l’analisi dettagliata di tecniche, architetture e scenari di attacco/difesa legati a questi sistemi.
    

---

## **Lezione 1: Intrusion Detection System (IDS)**

### **1. Introduzione**

I **firewall**, pur essendo fondamentali nella difesa perimetrale, **non possono proteggere da tutte le minacce**.  
Le reti aziendali contengono infatti diversi punti deboli:

- sistemi operativi **intrinsecamente insicuri** o **mal configurati**;
    
- utenti che richiedono l’uso di **servizi vulnerabili**;
    
- **password deboli** o riutilizzate;
    
- **vulnerabilità locali**, che possono essere sfruttate da utenti interni (_insider_).
    

> In altre parole, anche con un buon firewall, un’intrusione nella LAN **non è impossibile**.

---

### **2. Intrusion Detection: concetto generale**

Un **IDS** (Intrusion Detection System) è un **sistema di monitoraggio** che osserva il traffico o il comportamento del sistema informatico con lo scopo di:

- **individuare eventi sospetti o critici** per la sicurezza;
    
- **registrare e analizzare** le attività che possono indicare un’intrusione.
    

Si tratta quindi di un **estensione dei log di sistema**, progettata per individuare comportamenti anomali e — in alcuni casi — collaborare con il firewall per reagire automaticamente.

---

### **3. Definizione di IDS**

Un **Intrusion Detection System**:

- **identifica individui o servizi** che utilizzano un computer o una rete **senza autorizzazione**;
    
- può estendersi anche all’identificazione di **utenti autorizzati** che **violano i propri privilegi**;
    
- rileva in generale **comportamenti non conformi** alle politiche di sicurezza.
    

L’idea di base è che il **pattern comportamentale** di un intruso differisca statisticamente da quello di un utente legittimo.

---

### **4. Perché usare un IDS**

Gli IDS sono utilizzati per tre scopi principali:

1. **Rilevare attacchi non bloccati** da altri sistemi (firewall, antivirus, ecc.).
    
2. **Diagnosticare intrusioni avvenute**, analizzando come e perché sono riuscite.
    
3. **Reagire automaticamente**, ad esempio:
    
    - chiudendo connessioni sospette;
        
    - modificando la sensibilità del sistema;
        
    - isolando o spegnendo host sotto attacco.
        

> L’IDS è dunque un “osservatore intelligente” della rete: non previene, ma **rileva e reagisce**.

---

### **5. IDS: caratteristiche funzionali**

#### **IDS passivi**

- Non intervengono direttamente sul traffico.
    
- Si basano su tecniche di **riconoscimento di pattern noti** (_attack signatures_).
    
- Possono usare **checksum crittografiche** (es. _Tripwire_) per verificare l’integrità dei file.
    

#### **IDS attivi**

- Impiegano **analisi statistiche e di apprendimento** (_learning_).
    
- Monitorano sequenze di eventi e pacchetti (_monitoring_).
    
- Reagiscono automaticamente al superamento di soglie di anomalia (_reaction_).
    

> I sistemi attivi sono più sofisticati ma richiedono risorse di calcolo maggiori.

---

### **6. Cosa può rivelare un IDS**

Un IDS ben configurato può individuare:

- **scansioni di rete (network scanning)**;
    
- **traffico sospetto**, come bot IRC o abusi di FTP anonimo;
    
- **presenza di Trojan** o connessioni a host sospetti;
    
- **attacchi interni**, partiti da host già compromessi;
    
- **ricontrolli ex post** su traffico passato, utile per indagini forensi.
    

---

### **7. Cosa non può fare un IDS**

Un IDS **non può**:

- prevenire un’intrusione o un **Denial of Service (DoS)**;
    
- agire come sistema di protezione attiva.
    

> Serve a **rivelare** intrusioni, non a bloccarle.  
> L’IPS (Intrusion Prevention System) compenserà in parte questo limite.

---

### **8. Esempio: compromissione dell’account di root**

Due casi tipici:

- **Remoto:** accesso tramite un servizio vulnerabile esposto verso l’esterno → può essere mitigato con un firewall perimetrale.
    
- **Locale:** accesso come utente non privilegiato (es. password rubata) e poi _privilege escalation_ → più difficile da prevenire.
    

> Qui entra in gioco l’IDS, che può registrare e segnalare comportamenti anomali anche da utenti apparentemente legittimi.

---

### **9. IDS: caratteristiche topologiche**

|Tipo di IDS|Descrizione|Ambito di monitoraggio|
|---|---|---|
|**HIDS (Host-Based IDS)**|Analizza log e attività del singolo host (SO e applicazioni).|Locale|
|**NIDS (Network-Based IDS)**|Monitora il traffico di rete in tempo reale tramite _sniffing_.|Rete|

- Gli **HIDS** si installano su server o endpoint critici.
    
- I **NIDS** analizzano il traffico di segmenti di rete, tipicamente tramite porte mirror o TAP.
    

---

### **10. Valutazione dell’efficacia di un IDS**

Due parametri fondamentali:

- **Accuratezza (Accuracy)** =  
    $$\text{Allarmi corretti} / \text{Allarmi totali}$$  
    Misura la **qualità complessiva** del sistema.
    
- **Completezza (Completeness)** =  
    $$\text{Allarmi corretti} / \text{Numero reale di intrusioni}$$  
    Misura la **copertura** nella rilevazione.
    

||Intrusione|Nessuna intrusione|
|---|---|---|
|**Allarme**|Allarme corretto (TP)|Falso allarme (FP)|
|**Nessun allarme**|Mancata segnalazione (FN)|Rifiuto corretto (TN)|

---

### **11. Falsi allarmi e bilanciamento**

Un IDS deve bilanciare:

- **Falsi negativi (FN):** attacchi non rilevati.
    
- **Falsi positivi (FP):** normali attività segnalate come attacchi.
    

Il compromesso è inevitabile:

- maggiore sensibilità → più FP;
    
- maggiore tolleranza → più FN.
    

> Un IDS perfetto non esiste: l’obiettivo è **ottimizzare il trade-off**.

---

### **12. Precisione e Richiamo (Precision & Recall)**

- **FP (Type I Error):** falso allarme.
    
- **FN (Type II Error):** attacco non rilevato (_miss_).
    
- **Specificità:**  
    $$\text{Specificità} = \frac{TN}{TN + FP}$$
    
- **Tasso di falsi positivi (FPR):**  
    $$FPR = 1 - \text{Specificità}$$
    
- **Accuratezza:**  
    $$\frac{TP + TN}{Totale}$$
    
- **Precisione:**  
    $$\frac{TP}{TP + FP}$$
    
- **Sensibilità (Recall):**  
    $$\frac{TP}{TP + FN}$$
    

La relazione tra **TPR (True Positive Rate)** e **FPR (False Positive Rate)** si visualizza nel grafico **ROC (Receiver Operating Characteristic)**.  
L’**AUC (Area Under Curve)** misura l’efficacia complessiva del sistema.

---

### **13. Analisi ROC e AUC**

- Il grafico **ROC** mostra il compromesso tra **TPR** e **FPR**.
    
- Più l’area sotto la curva (**AUC**) è grande, **più l’IDS è preciso**.
    
- Nella pratica, gli IDS devono generare migliaia di allarmi al giorno:  
    la probabilità che uno di essi corrisponda a un vero attacco dipende anche dalla **probabilità a priori** che un attacco avvenga.
    

> Un IDS efficace deve quindi essere calibrato sul contesto reale dell’organizzazione.

---

### **14. Strumenti di supporto: SIV e LFM**

|Strumento|Nome completo|Funzione|Esempi|
|---|---|---|---|
|**SIV**|System Integrity Verifier|Controlla i file e il filesystem per rilevare modifiche non autorizzate (registri, cron, privilegi).|_Tripwire_|
|**LFM**|Log File Monitor|Analizza i file di log (SO e applicazioni) per riconoscere pattern tipici di attacco.|_Swatch_|

> Questi strumenti completano gli IDS, permettendo di **rilevare variazioni di integrità** e **correlare eventi nei log**.

---

### **15. Conclusione**

Gli **IDS** costituiscono la **seconda linea di difesa** dopo il firewall.  
Non impediscono gli attacchi, ma consentono di:

- **rilevarli tempestivamente**,
    
- **valutarne l’impatto**,
    
- **preparare contromisure** più efficaci.
    

> In un sistema di sicurezza moderno, il firewall controlla _chi entra_, ma l’IDS osserva _cosa accade dentro_.


---
