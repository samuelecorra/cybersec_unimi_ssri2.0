# **M10 UD1 Lezione 5 - Data Mining**

### **1. Introduzione**

Il **data mining** è l’insieme delle **tecniche di analisi automatica** dei dati volte a **estrarre informazioni non esplicite**, cioè **conoscenza nascosta** all’interno di grandi insiemi di dati.  
È una **disciplina interdisciplinare**, che combina strumenti e concetti provenienti da:

- **statistica**, per l’analisi quantitativa;
    
- **algoritmi e intelligenza artificiale**, per l’automatizzazione dei processi decisionali;
    
- **reti neurali**, per l’apprendimento e la classificazione dei dati complessi.

L’obiettivo è **scoprire pattern, regole e relazioni** utili per interpretare e prevedere fenomeni reali, trasformando i dati grezzi in **conoscenza utile per il supporto alle decisioni**.

---

### **2. Il processo di Data Mining**

Il processo di data mining non è lineare, ma **iterativo e adattativo**, poiché la conoscenza viene costruita progressivamente.

Le principali fasi sono:

1. **Comprensione del dominio applicativo**
    
    - Definizione degli obiettivi e del contesto (es. analisi di mercato, rischio creditizio, frodi, produzione).
    
2. **Preparazione dell’insieme di dati**
    
    - **Selezione** del sottoinsieme di dati rilevanti dal data warehouse;
        
    - **Pulizia e discretizzazione** (riduzione della complessità dei valori, gestione dei dati mancanti o rumorosi).
    
3. **Scoperta dei pattern**
    
    - Applicazione di algoritmi per individuare **regole, correlazioni o modelli nascosti** nei dati.
    
4. **Valutazione dei pattern**
    
    - Misura della qualità e della significatività dei risultati (affidabilità, generalità, utilità).
    
5. **Utilizzo dei risultati**
    
    - Traduzione dei pattern scoperti in **decisioni operative o strategiche** (marketing, controllo qualità, sicurezza, ecc.).

---

### **3. Applicazioni del Data Mining**

Le principali aree di applicazione includono:

- **Analisi di mercato** → identificazione di prodotti acquistati insieme o in sequenza.
    
- **Analisi di comportamento** → individuazione di **usi fraudolenti** di carte di credito o accessi sospetti.
    
- **Previsione** → stima del costo di cure mediche, domanda futura, o tendenza di mercato.
    
- **Controllo** → rilevazione di **errori di produzione** o anomalie nei processi industriali.

---

### **4. Regole di associazione**

Le **regole di associazione** cercano **regolarità nei dati**, identificando relazioni frequenti tra insiemi di elementi.

#### **Struttura di una regola**

Ogni regola ha la forma:

$$  
\text{Premessa} \Rightarrow \text{Conseguenza}  
$$

dove:

- la **premessa** (o corpo) è un insieme di condizioni;
    
- la **conseguenza** (o testa) è ciò che si prevede accada quando la premessa è verificata.

#### **Esempio**

```
Pannolini → Birra
```

- Il **30%** delle transazioni che contengono pannolini contiene anche birra.
    
- Il **2%** di tutte le transazioni contiene sia pannolini sia birra.

---

### **5. Indicatori principali**

Per valutare la validità di una regola di associazione si usano due indicatori fondamentali:

#### **a) Supporto**

Misura la **frequenza relativa** con cui la regola si verifica nel database.  
È la **probabilità** che in una transazione siano presenti **entrambi gli elementi** (premessa e conseguenza).

$$  
\text{supporto}(A \Rightarrow B) = P(A \cap B)  
$$

Esempio:  
Se il 2% di tutte le transazioni contiene sia pannolini sia birra → **supporto = 0.02**.

---

#### **b) Confidenza**

Misura la **forza della correlazione** tra premessa e conseguenza.  
È la **probabilità condizionata** che, data la presenza della premessa, si verifichi anche la conseguenza:

$$  
\text{confidenza}(A \Rightarrow B) = P(B|A)  
$$

Esempio:  
Se il 30% delle transazioni che contengono pannolini contiene anche birra → **confidenza = 0.30**.

---

### **6. Esempio pratico di regole di associazione**

|**No. Transazione**|**Prodotti acquistati**|
|---|---|
|1|Pasta, Ragù|
|2|Pasta, Acqua|
|3|Pasta, Acqua, Passata|

Le regole generate possono essere, ad esempio:

|**Premessa**|**Conseguenza**|**Supporto**|**Confidenza**|
|---|---|---|---|
|Pasta|Ragù|0.33|0.33|
|Pasta|Acqua|0.66|0.66|
|Pasta|Passata|0.33|0.33|
|Ragù|Pasta|0.33|1.00|
|Acqua|Pasta|0.66|1.00|
|Acqua|Passata|0.33|0.50|
|Passata|Acqua|0.33|1.00|
|{Pasta, Acqua}|Passata|0.33|0.50|
|{Acqua, Passata}|Pasta|0.33|1.00|

#### **Obiettivo del data mining per associazione**

Estrarre **tutte le regole** che presentano **supporto e confidenza superiori a soglie prefissate**, per individuare correlazioni statisticamente significative.

---

### **7. Tecniche di classificazione**

Oltre all’associazione, il data mining include le **tecniche di classificazione**, che servono a **catalogare un fenomeno in una classe predefinita**.

#### **Caratteristiche principali**

- Il fenomeno da classificare è rappresentato come una **tupla** (cioè un insieme ordinato di attributi).
    
- Il **classificatore** è un algoritmo che, basandosi su un insieme di dati di addestramento (**training set**), impara a riconoscere le **regole di appartenenza** a una classe.
    
- Il classificatore viene poi **applicato a nuovi casi**, non presenti nel training set.
    
- Può essere rappresentato come un **albero di decisione**, dove ogni nodo rappresenta una condizione e ogni foglia una classe.

---

### **8. Esempio di classificazione**

#### **Fenomeno da classificare:**

```
POLIZZA(NumPatente, Età, TipoAuto)
```

#### **Regole decisionali:**

```
Se Età < 23 → rischio elevato
Se TipoAuto = sportiva → rischio elevato
Se TipoAuto = camion → rischio elevato
Altrimenti → rischio basso
```

L’albero di decisione risultante può essere visualizzato così:

```
          Età < 23 ?
          /        \
      sì /          \ no
       ↓              ↓
  rischio elevato   TipoAuto = sportiva ?
                      /          \
                  sì /            \ no
                   ↓                ↓
            rischio elevato     TipoAuto = camion ?
                                   /         \
                               sì /           \ no
                                ↓               ↓
                        rischio elevato     rischio basso
```

---

### **9. Sintesi finale**

In questa lezione conclusiva abbiamo analizzato i **principali strumenti del data mining**, con particolare attenzione a:

- il **processo** iterativo di analisi e scoperta dei pattern;
    
- le **regole di associazione**, con gli indicatori di **supporto** e **confidenza**;
    
- e le **tecniche di classificazione**, basate su alberi decisionali e training set.

**In sintesi:**  
il **data mining** rappresenta la fase più avanzata dei sistemi di **supporto alle decisioni**, poiché consente di **trasformare i dati analitici in conoscenza predittiva**, capace di guidare le scelte aziendali, migliorare i processi e individuare pattern di comportamento nascosti nei dati.

---


![](imgs/Pasted%20image%2020251125055633.png)

![](imgs/Pasted%20image%2020251125055649.png)


![](imgs/Eserciziario_Complementi.pdf)