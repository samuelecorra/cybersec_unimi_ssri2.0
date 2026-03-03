## **Lezione 2: Tabelle Hash**

---

### **1. Introduzione**

In questa lezione viene presentata una seconda realizzazione del tipo di dato **dizionario**, basata sulle **tabelle hash**.  
L’idea alla base di questo approccio è quella di **ricavare direttamente la posizione di una chiave** all’interno del dizionario partendo dal suo valore.

Con questa strategia:

- gli **operatori principali** (inserimento, cancellazione, ricerca) hanno **complessità $O(1)$** nel caso medio;
    
- tuttavia, nel **caso pessimo**, la complessità può degenerare fino a $**O(n)**$.
    

---

### **2. Concetto di tabella hash**

Sia $K$ l’insieme di tutte le possibili chiavi distinte, e sia $D$ un vettore di **dimensione** $m$ destinato a memorizzare il dizionario.

L’obiettivo è definire una **funzione di accesso** (o **funzione hash**) che, data una chiave $k$, restituisca **la posizione in $D$** in cui memorizzarla:

$$  
H : K \to {1, 2, \dots, m}  
$$

#### **Proprietà ideale**

Nel caso ideale, per ogni coppia di chiavi distinte $k_1, k_2 \in K$:

$$  
k_1 \neq k_2 \Rightarrow H(k_1) \neq H(k_2)  
$$

Tuttavia, una funzione perfettamente iniettiva richiederebbe un vettore $D$ grande quanto l’insieme di tutte le chiavi possibili $|K|$, con un enorme spreco di memoria.

---

### **3. Collisioni e compromesso pratico**

Per motivi di efficienza, si sceglie una dimensione $m$ **molto minore di $|K|$**, accettando che possano verificarsi **collisioni**, cioè:

$$  
k_1 \neq k_2 \Rightarrow H(k_1) = H(k_2)  
$$

Quando due chiavi diverse producono lo stesso valore hash, **devono essere memorizzate in posizioni diverse**, secondo regole di gestione dette **metodi di scansione** o **tecniche di risoluzione delle collisioni**.

---

### **4. Scelta dei parametri e della funzione hash**

#### **Osservazioni**

- Se $m = 1$, si minimizza lo spazio, ma ogni operazione ha complessità $O(n)$.
    
- Se $1 < m \ll |K|$, si ottiene un compromesso accettabile tra efficienza e memoria.
    

#### **Per ottenere operatori efficienti**, la funzione hash $H(k)$ deve:

- essere **calcolabile in tempo costante** $O(1)$;
    
- **distribuire uniformemente** le chiavi all’interno del vettore $D$;
    
- ridurre al minimo il numero di collisioni.
    

#### **Il metodo di scansione** deve inoltre:

- permettere di **posizionare e recuperare** le chiavi anche in caso di collisione;
    
- garantire l’esplorazione di **tutto il vettore $D$**;
    
- evitare la formazione di **agglomerati di chiavi**.
    

In genere si sceglie ( m ) come una **sovrastima** del numero di chiavi previste (spesso circa il **doppio**
del numero medio di chiavi attese).

---

### **5. Funzione hash $H(k)$**

La funzione $H(k)$ calcola un valore intero che rappresenta la **posizione di inserimento** della chiave $k$ nel vettore.

#### **Proprietà desiderate**

- Il valore generato deve apparire **casuale**, pur essendo **ripetibile** (stessa chiave → stesso risultato).
    
- Gli elementi del vettore sono numerati da $0$ a $m-1$.
    

#### **Esempio di definizione**

Se $m = 2^p$ e $bin(k)$ rappresenta la forma binaria di $k$, si può definire:

- $H(k) = int(b)$, dove:

  - $b$ è un **sottoinsieme centrale di p bit** di $bin(k)$;
  
  - oppure $b$ è la **somma modulo 2** di vari sottoinsiemi di $p$ bit;
  
  - oppure $b$ è un **sottoinsieme di bit centrali** ottenuti da una trasformazione della rappresentazione binaria di $k$.

In generale, l’obiettivo è che $H(k)$ produca una **distribuzione uniforme** di chiavi su $D$.


---

### **6. Metodi di scansione (collision resolution)**

I **metodi di scansione** servono per decidere **dove collocare** una chiave che ha generato una collisione.  
Essi si distinguono in:

- **metodi esterni**, se le chiavi in conflitto vengono salvate **fuori** dal vettore;  
- **metodi interni**, se le chiavi vengono ricollocate **all’interno** del vettore stesso.

---

### **7. Metodi esterni – Liste di trabocco**

L’idea è quella di **associare a ciascuna posizione $H(k)$** una **lista di trabocco** $V[H(k)]$.

#### **Vantaggi**

- Nessun limite fisso alla capacità del dizionario.  
- Evita completamente la formazione di agglomerati.

#### **Complessità**

Gli operatori hanno complessità proporzionale alla **lunghezza media della lista di trabocco**:

$$
O(|V[H(k)]|)
$$

---

### **8. Metodi interni di scansione**

Quando si preferisce mantenere tutte le chiavi **all’interno** del vettore $D$, si possono adottare diversi metodi di scansione interna.

#### **Scansione lineare**

$$
f_i = (H(k) + h \cdot i) \bmod m
$$

dove $h$ è **primo rispetto a $m$**.  
Ad ogni collisione si avanza di $h$ posizioni fino a trovarne una libera.

#### **Scansione quadratica**

$$
f_i = (H(k) + h \cdot i + \frac{i(i-1)}{2}) \bmod m
$$

dove $m$ è **primo**.  
Questo metodo riduce la possibilità di formare cluster lineari.

#### **Hashing doppio**

$$
f_i = (H(k) + i \cdot F(k)) \bmod m
$$

dove $F(k)$ è una **seconda funzione hash** indipendente da $H(k)$.  
Offre un’ottima distribuzione, ma richiede il calcolo di due funzioni hash per ogni chiave.

---

### **9. Ristrutturazione della tabella hash**

Con il tempo, anche una buona tabella hash può degradare in prestazioni a causa dell’aumento del numero di chiavi.  
Quando si supera una certa **soglia di riempimento**, si effettua una **ristrutturazione**:

- si crea un nuovo dizionario di **dimensione doppia** $m' = 2m$;  
- tutte le chiavi vengono **ricalcolate e ricollocate** nella nuova tabella, poiché i valori di hash dipendono da $m$.

Questo processo consente di mantenere bassa la probabilità di collisione e quindi l’efficienza delle operazioni.

---

### **10. Sintesi finale**

- È stata introdotta una **realizzazione del tipo di dato dizionario** basata su **tabelle hash**.  
- Sono stati discussi i **principi di funzionamento** di una tabella hash efficiente.  
- Sono stati analizzati i principali **metodi di risoluzione delle collisioni**, sia **interni** (scansioni) che **esterni** (liste di trabocco).  
- È stato mostrato come la **ristrutturazione periodica** consenta di mantenere prestazioni ottimali.  
- In media, le **tabelle hash con liste di trabocco** risultano **più efficienti** di quelle con scansione interna.