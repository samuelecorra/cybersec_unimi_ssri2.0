## **Lezione 4: Autorità di certificazione**

### **1. Introduzione**

In questa lezione si approfondisce il ruolo e il funzionamento delle **Autorità di Certificazione (CA)** all’interno delle **Public Key Infrastructures (PKI)**.  
Le CA costituiscono la **base della fiducia** nel mondo digitale, poiché sono responsabili della **creazione, firma e gestione dei certificati digitali**.  
Si analizzerà come diverse CA possano cooperare tra loro attraverso **catene di certificazione**, permettendo la validazione di certificati appartenenti a domini diversi.

---

### **2. Notazione dello standard**

Per rappresentare le relazioni di firma tra autorità e soggetti si usa una notazione compatta:

- **Y⟪X⟫** → certificato dell’utente **X** emesso (e firmato) dall’autorità **Y**
    
- **Y{I}** → firma dell’hash di un insieme di informazioni **I** da parte dell’autorità **Y**
    

Questa notazione è utile per descrivere le relazioni di fiducia e le catene di certificazione in forma sintetica.

---

### **3. Problema delle diverse CA**

Consideriamo due autorità di certificazione distinte: **CA1** e **CA2**.

#### **Scenario base**

- Alice possiede un certificato emesso da **CA1**.
    
- Bob possiede un certificato emesso da **CA2**.
    

Se Alice e Bob **non conoscono le chiavi pubbliche** delle rispettive CA, **non possono verificare i certificati reciproci**, rendendo impossibile una comunicazione autenticata.

---

### **4. Relazioni di fiducia tra CA**

Per risolvere il problema, le CA possono **certificarsi reciprocamente**:

- **CA1⟪CA2⟫** → CA1 emette un certificato per la chiave pubblica di CA2.
    
- **CA2⟪CA1⟫** → CA2 emette un certificato per la chiave pubblica di CA1.
    

In questo modo, se Alice conosce la chiave pubblica di CA1, potrà verificare il certificato di Bob tramite la catena:

$$  
CA1⟪CA2⟫ \quad e \quad CA2⟪B⟫  
$$

Alice verifica CA2 grazie a CA1, e di conseguenza può verificare anche Bob.

---

### **5. Catene di certificazione**

Quando più autorità sono collegate in modo gerarchico o incrociato, si forma una **catena di certificazione** o _certification path_.  
Ogni certificato è validato da quello dell’autorità superiore, fino ad arrivare a una **CA radice (root CA)** riconosciuta come fidata.

Esempio:  
$$  
X⟪W⟫, \quad W⟪V⟫, \quad V⟪Y⟫, \quad Y⟪Z⟫, \quad Z⟪B⟫  
$$

Questo percorso consente all’entità **A** di verificare il certificato di **B** seguendo la catena di fiducia dal vertice (root CA) fino all’utente finale.

---

### **6. Tipi di certificati tra CA**

- **Forward Certificate:**  
    È il certificato che una CA emette per un’altra CA “inferiore” nella gerarchia (es. CA1⟪CA2⟫).
    
- **Reverse Certificate:**  
    È il certificato emesso in direzione opposta, ossia da una CA subordinata verso una superiore (es. CA2⟪CA1⟫).
    

Questa doppia certificazione reciproca consente la **mutua fiducia** tra autorità appartenenti a domini differenti.

---

### **7. Gerarchia X.509**

Lo standard **X.509** definisce esplicitamente la **gerarchia delle autorità di certificazione**.  
Ogni autorità può emettere certificati per:

- utenti finali (es. Alice, Bob);
    
- altre autorità intermedie, creando così una struttura ad albero.
    

In una **gerarchia X.509**:

- le autorità si certificano reciprocamente;
    
- la verifica di un certificato richiede la ricostruzione del **cammino di certificazione** fino alla **root CA**, la cui chiave pubblica è nota e considerata sicura.
    

---

### **8. Sintesi finale**

- La **CA** è l’entità che garantisce il legame tra una chiave pubblica e un’identità.
    
- La notazione **Y⟪X⟫** indica un certificato emesso da **Y** per **X**.
    
- Le **catene di certificazione** permettono di validare certificati appartenenti a diverse autorità.
    
- Lo **standard X.509** definisce una **struttura gerarchica** di CA, grazie alla quale è possibile verificare l’autenticità dei certificati lungo un percorso di fiducia comune.