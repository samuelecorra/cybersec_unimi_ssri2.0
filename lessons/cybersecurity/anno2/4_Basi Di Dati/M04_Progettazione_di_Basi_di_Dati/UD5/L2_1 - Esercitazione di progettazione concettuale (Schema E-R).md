# **M4 UD5 Lezione 2 - Parte I - Esercitazione di progettazione concettuale (Schema E-R)**

### **1. Introduzione**

In questa esercitazione si realizza uno **schema concettuale E–R** a partire dai requisiti di un’azienda reale: la **ditta Sempre Vivo (SV)**, specializzata nella **produzione e vendita di manichini, busti vetrina, torsi e accessori**.  
L’obiettivo è analizzare i requisiti testuali e tradurli in un **modello Entità–Relazione** corretto, chiaro e completo.

---

### **2. Analisi dei requisiti**

#### **2.1 Requisiti iniziali**

La ditta **Sempre Vivo (SV)** vuole realizzare una **base di dati** per gestire le proprie attività commerciali e operative.

I **prodotti SV** sono principalmente **manichini** e **accessori**.  
Per ciascun **prodotto** si conoscono:

- un **codice identificativo**;
    
- il **materiale** di cui è composto;
    
- le **dimensioni**;
    
- il **prezzo**.

Per gli **accessori**, inoltre, sono specificati:

- i **colori disponibili**;
    
- il **nome del produttore**, ma **solo se** l’accessorio è **venduto ma non prodotto internamente** da SV.

---

#### **2.2 Negozi**

La società SV possiede **diversi negozi** distribuiti su tutto il territorio nazionale.  
Per ogni negozio si conoscono:

- il **nome**;
    
- le **informazioni di contatto**, che comprendono:
    
    - **nome del gestore**,
        
    - **indirizzo**,
        
    - **numeri di telefono**;
    
- l’**insieme dei prodotti venduti** nel negozio.

> Da ciò si deduce una **relazione N:M** tra _Negozio_ e _Prodotto_  
> (un negozio può vendere più prodotti, e un prodotto può essere venduto in più negozi).

---

#### **2.3 Servizi aziendali**

Oltre alla vendita, l’azienda SV offre anche un **servizio diretto di noleggio e allestimento** per:

- **fiere**,
    
- **manifestazioni**,
    
- **vetrine espositive**.

Tali servizi implicano la gestione di **clienti**, **ordini** e **moduli di servizio**.

---

#### **2.4 Clienti e ordini**

I **clienti** possono rivolgersi sia ai **negozi** che direttamente all’azienda SV.  
Per ogni cliente si registrano le **informazioni anagrafiche standard** (nome, cognome, indirizzo, contatti).

Ogni cliente compila un **buono d’ordine**, che riporta:

- la **data di compilazione**;
    
- l’**eventuale codice del negozio** presso cui è stato compilato;
    
- il **tipo di servizio richiesto** (es. vendita, noleggio, allestimento);
    
- i **prodotti richiesti**, con la relativa **quantità**.

> Da qui si deduce:
> 
> - una **relazione N:1** tra _Buono d’ordine_ e _Cliente_;
>    
> - una **relazione N:M** tra _Buono d’ordine_ e _Prodotto_, con attributo `quantità`.

---

#### **2.5 Moduli di servizio**

Ad ogni buono d’ordine può essere associato **un modulo di servizio**, che specifica:

- il **luogo** in cui devono essere consegnati i prodotti;
    
- la **data di inizio del servizio**;
    
- la **durata**;
    
- il **prezzo totale del servizio**;
    
- eventualmente, il **nome dell’evento** per il quale il servizio è richiesto.

> La relazione tra _Buono d’ordine_ e _Modulo di servizio_ è quindi **1:1**,  
> ma il modulo può essere **opzionale**, in quanto non tutti gli ordini prevedono un servizio associato.

---

### **3. Costruzione dello schema E–R**

#### **3.1 Entità individuate**

|**Entità**|**Attributi principali**|
|---|---|
|**Prodotto**|codice, materiale, dimensioni, prezzo|
|**Accessorio**|colori_disponibili, produttore (opzionale)|
|**Negozio**|nome, gestore, indirizzo, telefono|
|**Cliente**|dati anagrafici completi|
|**Buono d’ordine**|data_compilazione, tipo_servizio|
|**Modulo di servizio**|luogo, data_inizio, durata, prezzo_totale, evento (opzionale)|

---

#### **3.2 Relazioni principali**

|**Relazione**|**Tipo**|**Descrizione**|
|---|---|---|
|**Vendita**|N:M|tra _Negozio_ e _Prodotto_|
|**Composizione ordine**|N:M|tra _Buono d’ordine_ e _Prodotto_, con attributo `quantità`|
|**Compilazione**|N:1|tra _Buono d’ordine_ e _Cliente_|
|**Emissione**|1:1 (opzionale)|tra _Buono d’ordine_ e _Modulo di servizio_|
|**Riferimento negozio**|N:1|tra _Buono d’ordine_ e _Negozio_|

---

#### **3.3 Gerarchie**

- **Accessorio** è una **specializzazione** di _Prodotto_  
    (in quanto condivide attributi di base come codice, prezzo, materiale).
    
- Si tratta di una **gerarchia parziale**, poiché non tutti i prodotti sono accessori.

---

### **4. Sintesi dello schema concettuale**

$$
\begin{aligned}
\text{ENTITA: } { \text{Prodotto, Accessorio, Negozio, Cliente, BuonoOrdine, ModuloServizio} } \\  
\text{RELAZIONI: } { \text{Vendita, ComposizioneOrdine, Compilazione, Emissione, RiferimentoNegozio} }
\end{aligned}
$$

**Caratteristiche principali:**

- Relazioni molti-a-molti per _vendita_ e _composizione ordine_;
    
- Gerarchia parziale per _Accessorio_ come sottotipo di _Prodotto_;
    
- Presenza di una relazione **1:1 opzionale** (_BuonoOrdine–ModuloServizio_).

---

### **5. Conclusione**

L’esercitazione ha mostrato come tradurre una descrizione testuale in un **modello concettuale E–R** rigoroso, evidenziando:

- la **classificazione delle entità** e dei loro attributi;
    
- le **relazioni chiave** con le relative cardinalità;
    
- la **struttura gerarchica** dei prodotti dell’azienda;
    
- la gestione dei **servizi opzionali** tramite relazioni dedicate.

> Lo schema così ottenuto rappresenta la base per la successiva **progettazione logica**, dove verranno introdotti gli attributi chiave, le chiavi esterne e i vincoli di integrità referenziale.