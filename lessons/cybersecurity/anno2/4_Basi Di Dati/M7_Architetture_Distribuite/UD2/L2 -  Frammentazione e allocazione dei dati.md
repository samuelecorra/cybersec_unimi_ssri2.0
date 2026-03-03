# **M7 UD2 Lezione 2 - Frammentazione e allocazione dei dati**

### **1. Introduzione**

Nelle basi di dati distribuite, i dati devono essere **organizzati e collocati** nei diversi server della rete in modo efficiente.  
A questo scopo si utilizzano due tecniche fondamentali:

- la **frammentazione**, che suddivide una relazione in più parti logiche o fisiche;
    
- l’**allocazione**, che definisce **su quali server** questi frammenti vengono memorizzati.

L’obiettivo è garantire **efficienza, affidabilità e coerenza**, permettendo alle applicazioni di operare come se i dati fossero in un’unica base centralizzata.

---

### **2. Frammentazione dei dati**

La **frammentazione** consiste nell’applicare **operazioni algebriche** su una relazione $R$ per suddividerla in frammenti $R_1, R_2, ..., R_n$.  
Esistono due principali tipologie:

#### **a) Frammentazione orizzontale**

- Ogni frammento contiene **un sottoinsieme di tuple** della relazione originale.
    
- Può essere ottenuto tramite **operazioni di selezione** ($\sigma$) sull’insieme delle tuple.
    
- Ogni frammento conserva **tutti gli attributi** della relazione.

Esempio:

$$  
IMPIEGATO_1 = \sigma_{Empnum \le 3}(IMPIEGATO)  
$$

$$  
IMPIEGATO_2 = \sigma_{Empnum > 3}(IMPIEGATO)  
$$

La relazione completa può essere **ricostruita** tramite l’unione:

$$  
IMPIEGATO = IMPIEGATO_1 \cup IMPIEGATO_2  
$$

---

#### **b) Frammentazione verticale**

- Ogni frammento contiene **un sottoinsieme di attributi** della relazione originaria.
    
- Si ottiene tramite **operazioni di proiezione** ($\pi$).
    
- Ogni frammento deve **includere la chiave primaria** della relazione, per garantire la possibilità di ricostruirla.

Esempio:

$$  
IMPIEGATO_1 = \pi_{Empnum, Nome}(IMPIEGATO)  
$$

$$  
IMPIEGATO_2 = \pi_{Empnum, Dipnum, Salario, Tasse}(IMPIEGATO)  
$$

La ricostruzione avviene tramite **join naturale** sulla chiave primaria:

$$  
IMPIEGATO = IMPIEGATO_1 \bowtie IMPIEGATO_2  
$$

---

### **3. Proprietà di correttezza della frammentazione**

Per essere corretta, la frammentazione deve rispettare due proprietà fondamentali:

- **Completezza**:  
    ogni dato della relazione originaria $R$ deve comparire in **almeno un frammento** $R_i$.
    
- **Ricostruibilità**:  
    deve essere possibile **ricostruire l’intera relazione $R$** a partire dai suoi frammenti, senza perdita o duplicazione di dati.

Inoltre:

- i **frammenti orizzontali** sono in genere **disgiunti** (non condividono tuple);
    
- i **frammenti verticali** devono **contenere la chiave primaria** per garantire la ricostruibilità.

---

### **4. Esempi pratici**

#### **Frammentazione orizzontale**

Tabella originale:

|Empnum|Nome|Dipnum|Salario|Tasse|
|:-:|:--|:-:|:-:|:-:|
|1|Roberto|Produzione|3.7M|1.2M|
|2|Giovanni|Amministrazione|3.5M|1.1M|
|3|Anna|Produzione|5.3M|2.1M|
|4|Carlo|Marketing|3.5M|1.1M|

Frammenti:

- **IMPIEGATO₁** = tuple con `Empnum ≤ 3`
    
- **IMPIEGATO₂** = tuple con `Empnum > 3`

| IMPIEGATO₁              | IMPIEGATO₂ |
| ----------------------- | ---------- |
| Roberto, Giovanni, Anna | Carlo      |

Ricostruzione:  
$$IMPIEGATO = IMPIEGATO_1 \cup IMPIEGATO_2$$

---

#### **Frammentazione verticale**

Frammenti derivati:

- **IMPIEGATO₁** = ${Empnum, Nome}$
    
- **IMPIEGATO₂** = ${Empnum, Dipnum, Salario, Tasse}$

Questa separazione consente di **conservare i dati anagrafici e organizzativi separatamente**, ma la chiave primaria (`Empnum`) è presente in entrambi i frammenti per permettere la ricostruzione tramite join.

Ricostruzione:  
$$IMPIEGATO = IMPIEGATO_1 \bowtie IMPIEGATO_2$$

---

### **5. Allocazione dei dati**

Dopo la frammentazione, è necessario **decidere dove memorizzare i frammenti**.  
L’**allocazione** definisce il **mapping** tra ciascun frammento e il **server** che lo ospita.

- Ogni frammento corrisponde fisicamente a **un file** e viene **allocato su un server specifico**.
    
- L’intera relazione globale può essere vista come una **vista virtuale** sui vari frammenti.

Esistono due modalità di allocazione:

#### **a) Allocazione non ridondante**

Ogni frammento o relazione è memorizzato **su un solo server**.  
→ Massimizza l’efficienza ma riduce la disponibilità in caso di guasto.

#### **b) Allocazione ridondante**

Almeno un frammento o relazione è **replicato su più server**.  
→ Migliora la **disponibilità e la tolleranza ai guasti**, ma aumenta il **costo di aggiornamento e sincronizzazione**.

---

### **6. Sintesi finale**

In questa lezione abbiamo visto:

- le **due forme principali di frammentazione** dei dati:
    
    - orizzontale (per tuple);
        
    - verticale (per attributi);
    
- le **proprietà di correttezza** (completezza e ricostruibilità);
    
- i **criteri di allocazione** dei frammenti sui server, distinguendo tra approcci ridondanti e non ridondanti.

La frammentazione e l’allocazione costituiscono le **fondamenta operative** delle basi di dati distribuite, poiché consentono di ottenere **efficienza, modularità e affidabilità** senza sacrificare la coerenza logica dell’intero sistema.

---


![](imgs/Pasted%20image%2020251125052954.png)

