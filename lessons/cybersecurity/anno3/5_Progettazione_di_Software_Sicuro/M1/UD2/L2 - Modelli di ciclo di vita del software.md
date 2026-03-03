# **Lezione 2: Modelli di ciclo di vita del software**

---

### **1. Panoramica della lezione**

Questa lezione analizza i diversi **modelli di ciclo di vita del software**, cioè i modi in cui le fasi di sviluppo possono essere organizzate nel tempo.

L’obiettivo principale è comprendere:

- come differiscono i modelli di processo,
    
- quali sono i limiti del modello tradizionale,
    
- perché i modelli incrementali risultano più adatti,
    
- perché il **modello a spirale** è particolarmente importante nello sviluppo di software sicuro.
    

---

### **2. Modelli di processo**

I cicli di vita del software differiscono principalmente:

> nel modo e nell’ordine con cui le attività di sviluppo vengono realizzate.

I modelli principali sono classificati in tre categorie:

- modelli a cascata,
    
- modelli evolutivi,
    
- modelli a spirale.
    

Questa classificazione rappresenta un’evoluzione storica e concettuale verso modelli sempre più flessibili.

---

### **3. Modello a cascata (Waterfall)**

#### **3.1 Origine e struttura**

Il modello a cascata, introdotto nel 1970, rappresenta uno standard storico per la produzione di sistemi informatici.

La sua caratteristica fondamentale è:

> l’output di ogni fase rappresenta l’input della fase successiva.

Le fasi sono quindi sequenziali e rigidamente ordinate.

---

### **4. Caratteristiche e limiti del modello a cascata**

#### **4.1 Varianti organizzative**

Esistono molte varianti del modello e ogni organizzazione tende a definire la propria versione.

#### **4.2 Sequenzialità rigida**

Il modello implica:

> il completamento di una fase prima di passare alla successiva.

Questo comporta scarsa flessibilità.

#### **4.3 Processo “black box”**

Il modello è definito come un processo **black box**:

- l’utente vede il prodotto solo alla fine,
    
- non è prevista prototipazione.
    

#### **4.4 Mancanza di adattamento ai cambiamenti**

Il limite principale è che:

> non tiene conto del fatto che i requisiti cambiano nel tempo.

Le cause dei cambiamenti possono essere:

- cambiamento del contesto:
    
    - hardware,
        
    - dominio applicativo,
        
    - algoritmi;
        
- specifiche iniziali errate:
    
    - requisiti incompleti,
        
    - requisiti non catturati correttamente,
        
    - scarsa conoscenza del dominio.
        

Questo rende il modello poco adatto a sistemi complessi o innovativi.

---

### **5. Come affrontare l’evoluzione**

Le slide (pagina 3) mostrano graficamente la differenza tra:

- **modello black box** (sequenziale),
    
- **modello flessibile** (incrementale).
    

Il punto centrale è che:

> il feedback è fondamentale per consentire controlli e cambiamenti anticipati.

Solo processi **incrementali** permettono:

- feedback sugli incrementi successivi,
    
- correzioni precoci,
    
- adattamento ai cambiamenti.
    

---

### **6. Processi incrementali**

I processi incrementali rappresentano una risposta ai limiti del modello a cascata.

#### **6.1 Capacità di adattamento**

Essi sono:

> capaci di adattarsi ai cambiamenti

in:

- requisiti,
    
- specifiche,
    
- design.
    

Questo li rende più realistici rispetto allo sviluppo reale.

#### **6.2 Riciclo delle modifiche**

Un vantaggio fondamentale è il **riciclo**:

- modificare prima il progetto,
    
- poi cambiare il codice.
    

Le modifiche devono essere applicate:

> in maniera consistente in tutti i documenti.

Nella pratica, spesso le modifiche vengono applicate solo al codice, creando incoerenze con la documentazione.

---

### **7. Validazione e verifica**

Un processo incrementale consente due attività fondamentali.

#### **7.1 Validazione**

Domanda:

> Are we doing the right product?

Significa verificare che il prodotto sviluppato corrisponda ai bisogni dell’utente.

#### **7.2 Verifica**

Domanda:

> Are we doing the product right?

Significa controllare che il prodotto sia costruito correttamente rispetto alle specifiche.

La presenza di feedback incrementale migliora entrambe.

---

### **8. Tipologie di processi incrementali**

Esistono diverse forme di modelli incrementali:

- prototipazione,
    
- modello a fasi di release (incremental delivery),
    
- modello a spirale.
    

Secondo la lezione:

> il modello a spirale è ottimale per lo sviluppo di sistemi sicuri.

---

### **9. Prototipazione**

#### **9.1 Principio generale**

La prototipazione si basa sul principio:

> “Do it twice”.

Un **prototipo** è un modello approssimato dell’applicazione che serve a:

- ottenere feedback,
    
- identificare caratteristiche,
    
- individuare errori di progettazione.
    

La scelta di cosa prototipare dipende da:

> ciò che è critico accertare.

#### **9.2 Controllo dei costi**

Per evitare costi elevati è necessario:

- essere consapevoli della funzione del prototipo,
    
- basare il processo sulla riusabilità delle componenti.
    

Due strategie fondamentali:

- massimizzare la modularizzazione,
    
- ingegnerizzare solo le componenti critiche nel prototipo.
    

#### **9.3 Tipi di prototipo**

- prototipo **throw-away** (usa e getta),
    
- prototipo **evolutivo**.
    

---

### **10. Modello a fasi di release (Incremental delivery)**

Il modello a fasi di release si basa sul principio:

> early subset, early delivery, early feedback.

Si parte da:

- sottoinsiemi critici del sistema,
    
- sui quali si richiede feedback del cliente.
    

Il processo è iterativo:

- pianificazione,
    
- design,
    
- implementazione,
    
- integrazione e deployment per ogni fase.
    

Questo approccio è ancora molto utilizzato oggi.

Esempio pratico:

- versioni **beta** distribuite per testing.
    

---

### **11. Modello a spirale**

#### **11.1 Struttura generale**

Il modello a spirale è un processo ciclico che combina:

- analisi dei rischi,
    
- sviluppo (specifica, design, codifica),
    
- testing,
    
- revisione della release.
    

Il diagramma (pagina 8) mostra che ogni ciclo produce:

- prototipo,
    
- release,
    
- revisione,
    
- integrazione e deployment.
    

#### **11.2 Crescita dei costi**

Ad ogni ciclo:

> il costo aumenta come in una spirale.

Questo rappresenta l’aumento progressivo di investimento man mano che il sistema evolve.

---

### **12. Caratteristiche del modello a spirale**

Il modello a spirale:

- ingloba prototipazione,
    
- integra approccio iterativo,
    
- può essere visto come un **metamodello** (cioè un modello generale che include altri modelli).
    

Un punto importante:

> il mantenimento è semplicemente una forma di sviluppo continuo.

Questo riflette la realtà moderna del software.

---

### **13. Sintesi della lezione**

In questa lezione abbiamo visto:

- cosa è il ciclo di vita del software,
    
- i modelli con cui può essere organizzato.
    

Concetti fondamentali:

- il modello a cascata è il più usato storicamente ma presenta forti limiti,
    
- i modelli incrementali sono più flessibili,
    
- il modello ottimale per lo sviluppo è il **modello a spirale**.
    

---

### **14. Prossimi passi**

Per lo sviluppo di software sicuro:

> il modello ottimale è quello a spirale perché consente l’analisi dei rischi.

Questo tema sarà approfondito nella prossima lezione.