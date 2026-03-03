# **Lezione 3: Complementi sui nomi**

---

### **1. Il namespace DNS**

Il **namespace** del DNS deve essere **gerarchico**, cioè organizzato in più livelli strutturati come un **albero**.  
L’idea è quella di assegnare i nomi alle risorse di rete secondo una logica che rispecchi la loro **posizione e appartenenza**.

La struttura segue tre livelli principali:

1. **Location** – rappresenta il contesto generale, come una nazione, un gruppo di organizzazioni o un’azienda.  
    _Esempio:_ `.it` per l’Italia, `.com` per le aziende commerciali.
    
2. **Unità all’interno della location** – identifica un’entità più specifica dentro il contesto superiore.  
    _Esempio:_ `unimi.it` per l’Università degli Studi di Milano.
    
3. **Oggetto all’interno dell’unità** – è il nome del singolo host o servizio.  
    _Esempio:_ `www.unimi.it` o `mail.unimi.it`.
    

Questa struttura gerarchica consente di **identificare univocamente ogni risorsa** e di **organizzare in modo ordinato** i nomi nel sistema globale.

---

### **2. Fully Qualified Domain Name (FQDN)**

Il **Fully Qualified Domain Name (FQDN)** è il **nome di dominio completo**, che indica l’intero percorso all’interno della gerarchia DNS.  
Un FQDN termina sempre con un **punto finale**, che rappresenta la **radice del namespace DNS**.

**Esempio:**

```
www.ripe.net.
```

Ogni parte separata da un punto è detta **etichetta** (_label_), e l’insieme delle etichette costituisce un **percorso completo** dall’alto (root) al basso (host specifico).

Il DNS fornisce la corrispondenza tra questi nomi completi e le risorse di rete (indirizzi IP, server mail, ecc.).  
Quando un client interroga il DNS, il **nome di dominio viene usato come chiave di accesso** ai dati memorizzati nel sistema.

---

### **3. Struttura ad albero dei domini**

I nomi di dominio possono essere **rappresentati come un albero**, in cui:

- ogni **nodo** corrisponde a un dominio o sottodominio;
    
- ogni **punto (“.”)** separa un livello della gerarchia;
    
- non esiste un limite al numero di nodi che si possono creare.

![](imgs/Pasted%20image%2020260225160126.png)

Esempio semplificato:

```
(root)
 ├── com
 │   └── google
 │       ├── www
 │       └── mail
 └── it
     └── unimi
         ├── www
         └── studenti
```

Ogni ramo può essere gestito in modo **indipendente**, pur restando parte dell’intera gerarchia globale.

---

### **4. Domini e namespace locali**

Un **dominio** rappresenta una **porzione del namespace**, ed è detto **namespace locale**.  
Tutto ciò che si trova _sotto_ un dominio appartiene a esso.

![](imgs/Pasted%20image%2020260225160143.png)

Esempi:

- Tutto ciò che sta sotto `.com` appartiene al **dominio “com”**.
    
- Tutto ciò che sta sotto `ripe.net` appartiene sia al **dominio “ripe.net”** sia al **dominio superiore “net”**.
    

In altre parole, i domini si **annidano** uno dentro l’altro, formando un **sistema gerarchico di contenitori**.

---

### **5. Delegazione**

Un amministratore di dominio può creare **sottodomini** per organizzare meglio le risorse, ad esempio:

- per **area geografica** (`milano.unimi.it`);
    
- per **struttura organizzativa** (`disi.unimi.it`);
    
- per **funzione** (`mail.unimi.it`).

L’amministratore può anche **delegare la gestione di un sottodominio** a un’altra persona o a un’altra entità amministrativa.  
Questa delega **non è obbligatoria**, ma è utile per distribuire il carico e rendere il sistema più flessibile.

Il dominio “padre” mantiene comunque un **collegamento verso il sottodominio delegato**, ricordando **a chi è stata affidata la gestione**.  
Questo assicura la **continuità del namespace** e l’integrità delle relazioni gerarchiche.

---

### **6. Zone e delegazioni**

Le **zone** rappresentano le **unità amministrative** del DNS.  
Ogni zona contiene i dati relativi a una o più porzioni di dominio, ed è gestita da un **amministratore di zona**.

![](imgs/Pasted%20image%2020260225160214.png)

La **delega di autorità** avviene tra un dominio “padre” e un “figlio”:  
il dominio superiore concede al sottodominio il diritto di gestire la propria zona.

Esempio:

```
zona net
 ├── dominio net
 ├── zona ripe.net
 │   └── zona disi.ripe.net
```

Ogni zona ha quindi la propria **responsabilità amministrativa**, pur restando parte del dominio più grande.

---

### **7. Name server**

I **name server** sono i server che **rispondono alle interrogazioni DNS**.  
Ne esistono di diversi tipi, ognuno con funzioni specifiche:

1. **Server autorevoli (authoritative)**
    
    - Contengono i dati ufficiali per una o più zone.
        
    - Possono essere:
        
        - **Master (primari)**: caricano i dati da un file di zona locale.
            
        - **Slave (secondari)**: copiano automaticamente i dati dal master tramite un **trasferimento di zona (zone transfer)**.

![](imgs/Pasted%20image%2020260225160259.png)

2. **Server ricorsivi (caching o forwarder)**
    
    - Non possiedono dati ufficiali, ma **eseguono ricerche per conto dei client**.
        
    - Quando ottengono una risposta da un server autorevole, la **memorizzano in cache** e la forniscono ai client successivi.
        
    - Le risposte ricorsive **non sono autorevoli**, ma riducono i tempi di accesso.
        
3. **Server misti**
    
    - Combinano funzionalità di server autorevole e ricorsivo, a seconda del tipo di richiesta.
        

---

### **8. Funzionamento dei server DNS**

- Il **server master** contiene la copia originale dei record DNS di una zona.
    
- Gli **slave** effettuano una copia periodica automatica dal master per mantenere aggiornati i dati.
    
- I **server ricorsivi**, invece, agiscono da “intermediari”: cercano le informazioni necessarie interrogando altri server, poi rispondono al client.  
    Tuttavia, le loro risposte non sono considerate _autorevoli_ perché non provengono direttamente dalla fonte ufficiale.
    

Questo modello distribuito di server consente al DNS di essere **efficiente, ridondante e veloce**, evitando sovraccarichi e garantendo risposte anche in caso di guasti.

---

### **9. Conclusione**

Il sistema DNS combina **gerarchia**, **delegazione** e **ridondanza** per gestire in modo efficiente la denominazione su scala globale.  
Ogni livello, dal dominio principale fino al singolo host, ha la propria responsabilità e autonomia, ma collabora con gli altri per mantenere la coerenza dell’intero sistema.
