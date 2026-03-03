## **Lezione 4: Applicazione delle funzioni hash – Notaio digitale**

### **1. Introduzione**

Le funzioni hash trovano applicazione in numerosi contesti di sicurezza, e uno dei più interessanti è il **digital timestamping**, ovvero la **marcatura temporale digitale**.  
L’obiettivo è dimostrare **quando un documento è stato creato o esisteva** in una certa data, senza dover necessariamente rivelarne il contenuto.

In termini semplici, la **marca temporale** è una prova digitale che attesta che un certo documento $D$:

- è stato prodotto **prima** o **dopo** un determinato istante di tempo,
    
- **non è stato modificato** successivamente.
    

---

### **2. Il concetto di marca temporale**

Una **marca temporale** (timestamp) è un’informazione aggiunta a un documento che:

- certifica la **data e l’ora di creazione o ricezione**,
    
- può essere verificata da terze parti,
    
- è basata su **funzioni hash** e **firme digitali**.
    

Esempio di domanda fondamentale:

> “Quando è stato creato il documento $D$?”

---

### **3. Esempi di marcatura temporale**

Alcuni metodi pratici, anche _non digitali_, per ottenere una marca temporale:

- **Deposito presso un notaio**  
    (garantisce una data certa ma è costoso e lento)
    
- **Invio a se stessi tramite posta**  
    (l’annullo postale indica la data)
    
- **Pubblicazione su un giornale**  
    (rende pubblica l’esistenza del documento)
    
- **Registrazione su un protocollo ufficiale**  
    (es. registro aziendale o PA)
    
- **Inserimento in un brevetto o foto con quotidiano**  
    (tecnica usata in perizie o sequestri)
    

Tutti questi metodi forniscono prove di **esistenza** del documento in un dato momento, ma non garantiscono efficienza o riservatezza.

---

### **4. Difficoltà pratiche**

- È **facile** provare che un documento è stato creato **dopo** una certa data.
    
- È invece **difficile** provare che esisteva **prima** di una data fissata.
    

Da qui nasce l’esigenza di un **meccanismo digitale** che automatizzi questa prova in modo affidabile e sicuro.

---

### **5. La soluzione “naive”**

#### **5.1 Prima versione**

Alice invia all’**Autorità Fiduciaria (Trusted Authority)** il suo documento $D$, che viene archiviato insieme a:

- il nome del mittente,
    
- la data e l’ora,
    
- la sua **firma digitale**.
    

Schema:

```
Autorità Fiduciaria
   └── "Ricevuto il 2/5/2002 ore 15:00 da Alice"
   └── Firma digitale dell’autorità
```

**Problemi:**

- Il documento $D$ può essere molto **grande** (difficile da trasmettere e memorizzare).
    
- L’autorità deve **conservare copie integrali** di tutti i documenti.
    
- **Rischio di violazione della privacy**: il contenuto di $D$ è visibile.
    

---

#### **5.2 Seconda versione (con hash)**

Per risolvere questi problemi, Alice invia **solo l’hash del documento** $H(D)$ all’autorità fidata, che firma digitalmente il messaggio:

$$  
\text{Firma}_\text{Autorità}( \text{Alice}, H(D), \text{data}, \text{ora} )  
$$

In questo modo:

- la comunicazione è **leggera** (solo pochi byte),
    
- il documento originale **resta privato**,
    
- e la verifica resta possibile: chiunque, ricalcolando $H(D)$, può controllare la marca.
    

---

### **6. Il problema dell’affidabilità**

Resta però la domanda classica di Giovenale:

> “**Sed quis custodiet ipsos custodes?**”  
> (_Chi sorveglierà i sorveglianti stessi?_)

Quanto possiamo fidarci dell’Autorità Fiduciaria (TSA – Time Stamping Authority)?  
Per questo motivo, sono stati proposti **due tipi di protocolli** per gestire la marcatura temporale.

---

### **7. Due famiglie di protocolli**

#### **7.1 Protocolli distribuiti (senza autorità fidata)**

- L’idea è di ottenere **più testimonianze indipendenti del tempo**.
    
- Ogni “testimone” (altra persona o server) firma digitalmente l’hash del documento.
    
- Le persone da contattare sono:
    
    - **scelte in modo casuale**,
        
    - **dipendenti dal documento stesso**, quindi non predeterminabili.
        

**Vantaggio:**  
Un singolo nodo malevolo non può compromettere il sistema.  
**Svantaggio:**  
Serve un grande numero di partecipanti online e reattivi.

---

#### **7.2 Protocolli con link (con autorità fidata)**

- Esiste un **servizio centralizzato** detto **TSS (Time Stamping Service)**.
    
- Il TSS riceve tutte le richieste in **intervalli di tempo regolari**,  
    le collega tra loro e genera una **struttura ad albero di hash**.
    
- Ogni utente riceve una **marca temporale firmata**, che include:
    
    - il proprio hash $h_i$,
        
    - la data e ora,
        
    - la firma del TSS,
        
    - e gli elementi necessari per verificare che $h_i$ sia incluso nell’albero.
        

---

### **8. Albero di hash (Merkle tree)**

L’idea di base è combinare i valori hash dei documenti ricevuti in un **albero binario**:

$$  
\begin{aligned}  
h_{12} &= H(h_1, h_2) \\  
h_{34} &= H(h_3, h_4) \\  
h_{14} &= H(h_{12}, h_{34})  
\end{aligned}  
$$

Il valore hash della **radice dell’albero** $h_{1m}$ rappresenta **tutte le marche emesse** in quel periodo di tempo.

Alla fine di ogni intervallo temporale, il TSS pubblica:

- la **radice dell’albero (SuperHash)**,
    
- la **firma digitale**,
    
- e i riferimenti temporali.
    

---

### **9. Sicurezza dell’albero di hash**

Una volta fissato il valore hash della radice:

- **non è possibile inserire nuovi valori** senza cambiare la radice,
    
- **non è possibile modificare un singolo valore** senza creare una collisione,
    
- quindi l’integrità del sistema dipende interamente dalla **resistenza alle collisioni** della funzione hash.
    

---

### **10. Implementazioni reali**

#### **10.1 Surety – Digital Notary (1995)**

- Usa un digest combinato **MD5 + SHA**, lungo **288 bit**.
    
- Ogni secondo rappresenta un’unità di tempo.
    
- Il **SuperHash** giornaliero viene pubblicato su media pubblici (rete, CD-ROM, persino sul _Sunday New York Times_).
    
- Inserisce un **numero seriale** nel documento, associato alla marca.
    

#### **10.2 PGP Digital Timestamping Service**

- Ogni documento ricevuto è **firmato** dal TSS.
    
- Attivo dal **1995**, assegna a ciascuna marca:
    
    - un **numero seriale**,
        
    - data e ora,
        
    - e pubblica giornalmente il **numero seriale dell’ultima firma**.
        
- Tutte le marche giornaliere possono essere verificate pubblicamente.
    

---

### **11. Problemi residui nei protocolli distribuiti**

- È necessario un **grande numero di partecipanti** sempre disponibili.
    
- Le **firme digitali scadono**: una chiave privata può essere compromessa o un algoritmo reso obsoleto.  
    → Di conseguenza, la validità della marca temporale deve essere **rinnovata periodicamente**.
    

---

### **12. Sintesi e conclusioni**

- Le funzioni hash sono fondamentali per il **digital timestamping**.
    
- Gli alberi di hash (Merkle tree) consentono di **collegare tra loro le marche** in modo verificabile e sicuro.
    
- La **sicurezza** dell’intero sistema si basa sulla **resistenza alle collisioni** della funzione hash.
    
- Esistono implementazioni operative e commerciali di questi sistemi, utilizzate ancora oggi in ambito legale e digitale.