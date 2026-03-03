# **M1 UD2 Lezione 1 - Paginazione (parte 2)**

### **1. Introduzione**

Nella prima parte abbiamo visto i principi della **paginazione** e il ruolo della **MMU** nel tradurre automaticamente gli indirizzi logici in indirizzi fisici.  
Tuttavia, quando lo spazio di indirizzamento diventa molto ampio (ad esempio nei sistemi a 32 o 64 bit), le **tabelle delle pagine** possono diventare estremamente grandi e difficili da gestire.

In questa lezione analizziamo le principali **tecniche di ottimizzazione** delle tabelle delle pagine e le **funzionalità aggiuntive** legate alla protezione e condivisione delle pagine.

---

### **2. Gestione di grandi tabelle delle pagine**

Quando un processo ha un vasto spazio logico, la sua tabella delle pagine può contenere **migliaia o milioni di voci**.  
Per ridurre lo spazio necessario e migliorare la velocità di traduzione degli indirizzi, si utilizzano diverse tecniche:

- **Memoria ausiliaria di traduzione (TLB)**
    
- **Tabelle gerarchiche delle pagine**
    
- **Tabelle con hashing**
    
- **Tabelle invertite**

Ognuna di queste strategie mira a trovare un equilibrio tra **rapidità di accesso** e **riduzione dello spazio occupato**.

---

### **3. Memoria ausiliaria di traduzione (TLB)**

La **Translation Look-Aside Buffer (TLB)** è una **memoria associativa** ad alta velocità situata all’interno della **MMU**.

#### **Funzione**

La TLB memorizza un piccolo insieme di **corrispondenze recenti** tra pagine logiche e pagine fisiche.  
Quando il processore genera un indirizzo logico:

1. La MMU consulta prima la **TLB**.
    
2. Se la voce è presente (_TLB hit_), la traduzione è immediata.
    
3. Se non è presente (_TLB miss_), la MMU consulta la tabella principale delle pagine in RAM e aggiorna la TLB.

#### **Vantaggio**

Riduce drasticamente il tempo medio di accesso alla memoria, poiché la traduzione logico-fisica avviene nella maggior parte dei casi **senza dover accedere alla RAM**.

---

### **4. Tabella gerarchica delle pagine**

Per ridurre le dimensioni complessive della tabella, è possibile organizzarla in **più livelli**.  
La forma più comune è la **tabella delle pagine a due livelli**.

#### **Funzionamento**

- La prima tabella contiene **puntatori** a tabelle secondarie.
    
- Ogni tabella secondaria gestisce una porzione dello spazio di indirizzamento.
    
- Solo le tabelle effettivamente utilizzate dai processi vengono create e mantenute in memoria.

#### **Esempio concettuale**

$$  
\text{Indirizzo logico} = (\text{Indice}_1, \text{Indice}_2, \text{Offset})  
$$

- **Indice₁** → seleziona una voce nella tabella principale.
    
- **Indice₂** → seleziona la pagina all’interno della tabella secondaria.
    
- **Offset** → specifica la posizione esatta nella pagina.

Questo approccio riduce lo spazio necessario, poiché le tabelle secondarie **vengono allocate solo quando servono**.

---

### **5. Tabella delle pagine con hashing**

Nei sistemi con spazio di indirizzamento molto grande (soprattutto a 64 bit), si adotta un approccio basato su **funzioni di hash**.

#### **Struttura**

Ogni voce della tabella contiene:

- il **numero della pagina virtuale**,
    
- il **numero del frame fisico** corrispondente,
    
- un **puntatore** all’eventuale voce successiva in caso di collisione.

#### **Funzionamento**

1. Si applica una **funzione hash** al numero di pagina virtuale.
    
2. Si accede alla voce corrispondente nella tabella.
    
3. In caso di collisione, si segue la lista concatenata fino a trovare la voce corretta.

Questo metodo è efficiente in sistemi con molti processi attivi e spazi logici estesi.

---

### **6. Tabella invertita delle pagine**

La **tabella invertita** rappresenta un approccio completamente diverso: invece di mantenere una tabella per ogni processo, il sistema tiene **una sola tabella globale** per tutta la memoria fisica.

#### **Struttura**

$$  
\text{TabellaInversa[PaginaFisica]} =  
\begin{cases}  
(\text{Processo}, \text{PaginaLogica}), & \text{se caricata} \\\\  
\text{---}, & \text{se non caricata}  
\end{cases}  
$$

Ogni voce indica **quale processo** e **quale pagina logica** si trovano in una determinata **pagina fisica**.

#### **Caratteristiche**

- È **unica per tutto il sistema**, non per singolo processo.
    
- Riduce notevolmente lo spazio occupato da più tabelle individuali.
    
- La ricerca richiede però un meccanismo di confronto più complesso, spesso supportato da hardware dedicato.

---

### **7. Protezione delle pagine**

Ogni processo deve poter accedere **solo alle proprie pagine**, garantendo sicurezza e isolamento tra i programmi.

#### **Meccanismo di protezione**

- Il sistema operativo associa a ciascuna voce della tabella un **bit di protezione**.
    
- Questi bit definiscono i **permessi di accesso** per ogni pagina:

|Tipo di accesso|Descrizione|
|---|---|
|**Lettura/Scrittura**|La pagina può essere letta e modificata.|
|**Sola Lettura**|La pagina può solo essere letta.|
|**Sola Esecuzione**|La pagina contiene codice eseguibile ma non modificabile.|

L’uso dei bit di protezione consente di rilevare e prevenire accessi non autorizzati (ad esempio tentativi di scrittura su pagine di codice).

---

### **8. Condivisione delle pagine**

In alcuni casi, più processi possono condividere porzioni di codice o dati comuni (ad esempio librerie di sistema o moduli condivisi).  
La paginazione semplifica questa operazione: **più tabelle delle pagine possono puntare alla stessa pagina fisica**, mantenendo però i propri permessi di accesso.

Ciò consente di:

- risparmiare memoria fisica;
    
- favorire la cooperazione tra processi;
    
- garantire coerenza dei dati condivisi.

---

### **9. Sintesi finale**

- La **paginazione** consente di gestire efficientemente la memoria, spostando solo **piccole porzioni di codice o dati**.
    
- Le **tecniche avanzate** (TLB, tabelle gerarchiche, con hashing e invertite) riducono i costi in spazio e tempo.
    
- La **protezione e condivisione** delle pagine assicurano isolamento, sicurezza e cooperazione tra processi.
    
- Il sistema operativo gestisce tutto in modo **automatico e trasparente**, fornendo a ogni processo l’illusione di disporre di **una memoria continua e privata**, molto più ampia di quella fisicamente presente.