Il modulo sulle **Firme Digitali** introduce uno dei pilastri fondamentali della sicurezza informatica moderna: la **garanzia di autenticità e integrità dei documenti digitali**. In questo modulo si approfondiscono i requisiti che un buon schema di firma deve soddisfare (autenticazione, integrità, non ripudio), le **principali tecniche crittografiche** utilizzate per realizzarla (in particolare basate su cifratura asimmetrica), e le **procedure pratiche di generazione e verifica** di una firma digitale.  
L’obiettivo è comprendere non solo il funzionamento tecnico, ma anche l’importanza della firma digitale come strumento giuridico e di sicurezza nei sistemi informativi.


---

## **Lezione 1: Scenario e applicazioni**

### **1. Introduzione**

Questa lezione introduce il concetto di **firma digitale**, spiegando il suo scopo, il funzionamento di base e i principali tipi di attacco.  
L’obiettivo è comprendere come una firma digitale permetta di garantire **autenticità, integrità e non ripudio** dei messaggi.

---

### **2. Dalla firma tradizionale alla firma digitale**

Una soluzione ingenua consisterebbe nell’**inserire un’immagine della firma scritta a mano** in un documento elettronico.  
Tuttavia, questo metodo non offre alcuna sicurezza: la firma può essere copiata o riutilizzata, come nel mondo cartaceo.

La **firma digitale vera** si basa invece su tecniche **crittografiche** che legano in modo univoco un messaggio $M$ all’identità del firmatario, rendendo possibile verificarne l’origine e l’integrità.

---

### **3. Requisiti fondamentali di uno schema di firma digitale**

Un buon schema di firma digitale deve rispettare tre requisiti principali:

1. **Facilità di produzione** – il legittimo firmatario deve poter creare facilmente la propria firma.
    
2. **Impossibilità di falsificazione** – nessun altro utente deve poter riprodurre la firma di un altro.
    
3. **Facilità di verifica** – chiunque deve poter verificare una firma tramite la chiave pubblica del firmatario.
    

Ogni utente dispone quindi di:

- una **chiave privata** $k_{priv}$, segreta e usata per firmare;
    
- una **chiave pubblica** $k_{pub}$, condivisa e usata per verificare.
    

---

### **4. Processo di firma**

La firma di un messaggio $M$ avviene attraverso una funzione di generazione:

$$  
F \leftarrow \text{FIRMA}(M, k_{priv})  
$$

Il risultato è la **firma digitale** $F$, che accompagna il messaggio originale.

|Soggetto|Chiave usata|Operazione|Output|
|---|---|---|---|
|Alice|$k_{priv}$|FIRMA($M$, $k_{priv}$)|$F$|

Alice invia quindi a Bob la coppia $(M, F)$.

---

### **5. Processo di verifica**

Il destinatario (Bob) riceve $(M, F)$ e desidera accertarsi che $F$ sia una firma autentica di Alice.  
Per farlo, utilizza la chiave pubblica di Alice nella funzione di verifica:

$$  
\text{VERIFICA}(F, M, k_{pub}) =  
\begin{cases}  
\text{SI} & \text{se la firma è autentica} \  
\text{NO} & \text{altrimenti}  
\end{cases}  
$$

Se l’esito è positivo (_SI_), Bob è certo che:

- il messaggio proviene da Alice (**autenticità**);
    
- il contenuto non è stato modificato (**integrità**);
    
- Alice non può negare di averlo firmato (**non ripudio**).
    

---

### **6. Tipologie di attacco**

La **sicurezza** di uno schema di firma digitale dipende dalle conoscenze e dalle capacità dell’attaccante.  
Si distinguono vari tipi di attacco:

- **Key-only attack** – l’attaccante conosce solo la chiave pubblica del firmatario.
    
- **Known message attack** – conosce una lista di messaggi e delle loro firme legittime.
    
- **Chosen message attack** – può scegliere dei messaggi e chiederne la firma.
    
- **Adaptive chosen message attack** – può scegliere nuovi messaggi da far firmare in base ai risultati precedenti.
    

---

### **7. Scopo dell’attacco**

Gli attacchi possono avere differenti obiettivi:

- **Total break** – l’attaccante riesce a ricavare la chiave privata $k_{priv}$ e può firmare qualsiasi messaggio.
    
- **Selective forgery** – produce una firma valida per un messaggio specifico $M$.
    
- **Existential forgery** – trova almeno una coppia $(M, F)$ tale che la verifica risulti positiva.
    

---

### **8. Sintesi finale**

In sintesi:

- uno schema di firma digitale è composto da **due procedure fondamentali**:
    
    1. **Generazione della firma** (con la chiave privata)
        
    2. **Verifica della firma** (con la chiave pubblica)
        
- la sicurezza del sistema dipende sia dall’algoritmo crittografico sia dalla **protezione della chiave privata**.
    

Una firma digitale efficace deve impedire che un attaccante, anche conoscendo molte firme valide, possa crearne di false o compromettere la chiave segreta del firmatario.