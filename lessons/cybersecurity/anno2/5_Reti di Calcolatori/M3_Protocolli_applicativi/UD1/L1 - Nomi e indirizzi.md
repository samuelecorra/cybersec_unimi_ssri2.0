# **Lezione 1: Nomi e indirizzi**

---

### **1. Scopi della denominazione**

Nelle reti di calcolatori, gli **indirizzi** (come quelli **IPv4** o **IPv6**) servono per **individuare le risorse di rete**. Tuttavia, le **sequenze numeriche** sono difficili da ricordare per gli utenti, che trovano più semplice memorizzare **nomi simbolici**.

Per questo motivo è necessario un **servizio di denominazione**, cioè un sistema che permetta di ottenere l’**indirizzo** di una risorsa partendo dal suo **nome**.

Il **Domain Name Service (DNS)** svolge proprio questa funzione: fornisce una **corrispondenza (mapping)** tra **nomi** e **risorse di rete** di vario tipo.  
In pratica, traduce un nome comprensibile all’uomo (es. `www.unimi.it`) nel corrispondente indirizzo IP (es. `159.149.10.16`).

---

### **2. Nomi piatti e nomi gerarchici**

Un **nome piatto** è un identificatore privo di struttura, che fa riferimento direttamente a un punto finale.  
Esempi di nomi piatti: `Davide`, `Milano`, `Stampante1`.  
Questi nomi non contengono informazioni sulla loro posizione o contesto.

Un **nome gerarchico**, invece, mostra un **percorso strutturato** che conduce al punto finale.  
Esempio:

- Nome gerarchico: `www.tin.it`
    
- Indirizzo IP corrispondente: `62.211.65.12`
    

La gerarchia permette di **organizzare meglio lo spazio dei nomi**, evitando ambiguità e rendendo scalabile il sistema di denominazione.

---

### **3. Storia della denominazione**

- Nel **1970**, la rete **ARPANET** utilizzava un **sistema piatto** per associare nomi e indirizzi.  
    Tutte le corrispondenze erano memorizzate in un file chiamato **`hosts.txt`**, gestito dal **SRI-NIC** (Stanford Research Institute Network Information Center).  
    Ogni notte, il file veniva **trasmesso a tutti gli host** della rete.
    
    Tuttavia, questo approccio causava vari **problemi**:
    
    - elevato **consumo di banda**;
        
    - **collisioni di nomi**, poiché potevano esistere nomi duplicati.
        
- Nel **1983**, nacque il **DNS gerarchico**, ideato da **Paul Mockapetris**, descritto negli **RFC 1034** e **RFC 1035**.  
    Da allora, il sistema è stato **migliorato e aggiornato** da numerosi documenti RFC successivi, evolvendosi fino alla struttura distribuita e affidabile che conosciamo oggi.
    

---

### **4. Il Domain Name Service (DNS)**

Il **DNS** è un **meccanismo di ricerca** che consente di **tradurre oggetti in altri oggetti**, ad esempio:

- **nomi in indirizzi IP**;
    
- o anche, **indirizzi IP in nomi** (funzione inversa).
    

Il DNS è costituito da tre elementi fondamentali:

1. **Namespace** → lo spazio dei nomi, cioè l’insieme di tutti i nomi gestiti dal sistema.
    
2. **Server DNS** → i server che rendono disponibile e gestiscono il namespace.
    
3. **Resolver** → i client che interrogano i server per ottenere informazioni.
    

---

### **5. Funzionamento di un DNS Server**

Un **server DNS** è una macchina che **mantiene una tabella** di associazioni tra **nomi** e **indirizzi IP**.

- Esistono **13 root server principali** nel mondo, che gestiscono i **domini di primo livello (TLD)** come `.it`, `.de`, `.com`, `.edu`, ecc.
    
- Quando un’applicazione (ad esempio un browser) deve risolvere un nome di dominio:
    
    1. il **programma locale** invia la richiesta al **server DNS più vicino** (solitamente quello del provider o del sistema operativo);
        
    2. se l’informazione **non è presente nella cache**, il server locale inoltra la richiesta a un **root server**;
        
    3. il **root server** identifica il **server autorevole** per quel dominio e gli **trasmette la richiesta**;
        
    4. infine, l’indirizzo IP risolto viene **ritornato al client** e **memorizzato nella cache** per le successive richieste.