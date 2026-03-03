# **Lezione 1: Proprietà del software — Prodotto e Processo**

---

### **1. Introduzione alla lezione**

Questa prima lezione introduce un concetto fondamentale della progettazione del software, in particolare del **software sicuro**: la distinzione tra **prodotto** e **processo**, e le rispettive **qualità** associate a ciascuno.

L’obiettivo principale è comprendere che:

- il software non è solo ciò che viene realizzato (**prodotto**),
    
- ma anche il modo in cui viene realizzato (**processo**),
    
- e che la qualità finale dipende strettamente da entrambi.
    

---

### **2. Prodotto e processo nel software**

Nel contesto dell’ingegneria del software possiamo distinguere due dimensioni principali:

#### **2.1 Il prodotto**

Il **prodotto** è ciò che viene costruito, cioè il **software stesso**:

- applicazioni,
    
- sistemi informatici,
    
- servizi digitali,
    
- componenti software.
    

In altre parole, il prodotto rappresenta il risultato finale del lavoro di sviluppo.

#### **2.2 Il processo**

Il **processo** rappresenta invece il modo in cui il prodotto viene realizzato.

Include:

- le metodologie di sviluppo,
    
- le fasi del ciclo di vita,
    
- gli strumenti utilizzati,
    
- l’organizzazione del lavoro,
    
- le pratiche di verifica e validazione.
    

Esistono diversi modelli per organizzare le fasi di sviluppo del software, e la scelta del modello influenza profondamente la qualità del risultato finale.

Un punto essenziale da ricordare è che:

> **Prodotto e processo sono entrambi estremamente importanti e possiedono proprie qualità.**

---

### **3. Caratteristiche del prodotto software**

Il software presenta caratteristiche molto diverse rispetto ai prodotti tradizionali (come oggetti fisici o manufatti industriali).

Le principali proprietà distintive sono tre.

#### **3.1 Intangibilità**

Il software è un prodotto **intangibile**.

Questo significa che:

- non ha una forma fisica,
    
- è difficile da descrivere completamente,
    
- è complesso da valutare senza strumenti adeguati.
    

A differenza di un oggetto materiale, non può essere osservato direttamente nella sua interezza.

#### **3.2 Malleabilità**

Il software è **malleabile**, cioè può essere modificato e trasformato con relativa facilità.

È possibile:

- aggiungere nuove funzionalità,
    
- modificare il comportamento,
    
- adattarlo a nuovi contesti,
    
- correggere errori.
    

Questa proprietà rende il software estremamente flessibile, ma introduce anche complessità nella gestione delle modifiche.

#### **3.3 Human-intensive**

Il software è un prodotto **human intensive**, cioè:

- non richiede processi manifatturieri tradizionali,
    
- dipende principalmente dal lavoro umano,
    
- il costo principale è rappresentato dall’attività degli sviluppatori.
    

Il valore del software deriva quindi soprattutto dalle competenze delle persone coinvolte.

---

### **4. Qualità di prodotto e qualità di processo**

Nel software engineering si distinguono due grandi categorie di qualità:

#### **4.1 Qualità del processo**

Riguardano i metodi utilizzati per sviluppare il software.

Sono legate a:

- organizzazione del lavoro,
    
- gestione delle attività,
    
- metodologie di sviluppo,
    
- strumenti di controllo.
    

#### **4.2 Qualità del prodotto**

Riguardano direttamente il software sviluppato.

Sono sempre valutabili, perché si manifestano nel comportamento del sistema e nelle sue caratteristiche tecniche.

#### **4.3 Relazione tra le due categorie**

Un principio fondamentale è che:

> **Le qualità del processo influiscono direttamente sulle qualità del prodotto.**

Un processo ben progettato tende a produrre software di qualità superiore.

---

### **5. Qualità del processo software**

Le principali qualità associate al processo di sviluppo software sono tre.

#### **5.1 Produttività**

La produttività misura:

> l’efficienza del processo di produzione del software in termini di velocità di consegna.

Un processo produttivo è in grado di:

- realizzare software rapidamente,
    
- ottimizzare l’uso delle risorse,
    
- ridurre sprechi di tempo e lavoro.
    

#### **5.2 Tempestività**

La tempestività misura:

> la capacità del processo di rispettare i tempi di consegna previsti.

Riguarda quindi:

- pianificazione,
    
- gestione delle scadenze,
    
- controllo delle attività.
    

#### **5.3 Trasparenza**

La trasparenza misura:

> la capacità di comprendere lo stato attuale del processo e tutte le sue fasi.

Un processo trasparente permette di sapere:

- cosa è stato fatto,
    
- cosa deve essere fatto,
    
- quali problemi esistono,
    
- quale è l’avanzamento del progetto.
    

---

### **6. Qualità del software: interne ed esterne**

Le qualità del software possono essere classificate in due categorie principali.

#### **6.1 Qualità esterne — Black box view**

Le qualità esterne sono quelle percepite da un osservatore esterno che analizza il software come una **scatola nera**.

Caratteristiche:

- visibili agli utenti,
    
- legate alle funzionalità,
    
- osservabili durante l’uso del sistema.
    

#### **6.2 Qualità interne — White box view**

Le qualità interne sono quelle percepite da un osservatore che analizza il software come una **scatola trasparente**.

Caratteristiche:

- non visibili agli utenti,
    
- legate alla struttura interna,
    
- dipendono dalle scelte di sviluppo.
    

#### **6.3 Relazione tra qualità interne ed esterne**

Le due categorie sono strettamente connesse.

Un principio fondamentale è:

> Non è possibile ottenere qualità esterne se il software non possiede qualità interne adeguate.

Ad esempio:

- un software difficile da mantenere (qualità interna bassa)
    
- difficilmente potrà essere affidabile o corretto nel tempo (qualità esterna).
    

---

### **7. Qualità esterne del software**

Le principali qualità esterne includono:

- correttezza,
    
- affidabilità,
    
- efficienza,
    
- usabilità,
    
- portabilità,
    
- interoperabilità,
    
- robustezza.
    

Vediamole nel dettaglio.

---

### **8. Correttezza (funzionalità)**

Un software è **corretto** se:

> rispetta le specifiche funzionali di progetto.

La correttezza è una proprietà di natura matematica che stabilisce l’equivalenza tra:

- il software,
    
- la sua specifica.
    

Problemi di correttezza possono derivare da:

- requisiti errati,
    
- errori nella conoscenza del dominio,
    
- specifiche incomplete.
    

Se le specifiche sono espresse formalmente, la correttezza può essere:

- dimostrata come un teorema (**verifica formale**),
    
- valutata tramite controesempi (**testing**).
    

---

### **9. Affidabilità**

Un software è **affidabile** se:

> si comporta come previsto.

L’affidabilità può essere definita matematicamente come:

> la probabilità di assenza di fallimenti in un dato intervallo di tempo.

Un principio importante:

- se le specifiche sono corrette, tutto il software corretto è affidabile,
    
- ma non tutto il software affidabile è necessariamente corretto.
    

Questo significa che un sistema può funzionare bene nella pratica pur contenendo errori nascosti.

---

### **10. Efficienza**

Un software è **efficiente** se utilizza in modo intelligente le risorse di calcolo.

Le risorse includono:

- memoria,
    
- tempo di elaborazione,
    
- banda di comunicazione.
    

L’efficienza può essere valutata tramite:

- analisi di complessità,
    
- simulazione di scenari critici.
    

Aspetti importanti:

- influisce sulla scalabilità,
    
- dipende dalla tecnologia disponibile,
    
- può influenzare l’usabilità.
    

Una soluzione efficiente in piccolo può non esserlo in grande scala.

---

### **11. Usabilità**

L’usabilità rappresenta:

> la facilità d’uso da parte dell’utente.

È una qualità:

- difficile da valutare,
    
- molto soggettiva,
    
- legata al tipo di utente atteso (**expected user**).
    

Influisce fortemente sulle interfacce:

- grafiche,
    
- testuali,
    
- interattive.
    

---

### **12. Portabilità**

Un software è **portabile** se può funzionare su più piattaforme.

Esempio classico:

- programmi sviluppati in Java.
    

La portabilità riduce la dipendenza da ambienti specifici.

---

### **13. Interoperabilità**

L’interoperabilità è:

> la capacità di un sistema di coesistere e cooperare con altri sistemi.

Esempio:

- un word processor che permette di integrare grafici prodotti da altri strumenti.
    

È una qualità fondamentale nei sistemi moderni distribuiti.

---

### **14. Robustezza**

Un software è **robusto** se:

> si comporta in modo ragionevole anche in condizioni non previste dalle specifiche.

Ad esempio:

- input incorretti,
    
- errori hardware,
    
- guasti di sistema.
    

Nota importante:

- correttezza e affidabilità si basano sulle specifiche,
    
- la robustezza riguarda situazioni non previste.
    

---

### **15. Qualità interne del software**

Le principali qualità interne includono:

- riusabilità,
    
- verificabilità,
    
- facilità di manutenzione.
    

---

### **16. Riusabilità**

Un software è **riusabile** se:

> può essere utilizzato, in tutto o in parte, per costruire nuovi sistemi.

La riusabilità riduce:

- tempi di sviluppo,
    
- costi,
    
- errori.
    

È un obiettivo importante dell’ingegneria del software moderna.

---

### **17. Verificabilità**

La verificabilità è:

> la possibilità di dimostrare a posteriori la correttezza o altre caratteristiche del software.

Dipende dalla qualità della progettazione e dalla documentazione.

---

### **18. Facilità di manutenzione**

La manutenzione riguarda:

- adattamenti,
    
- evoluzioni,
    
- correzioni.
    

Un software è facile da mantenere se:

- è strutturato per facilitare la ricerca degli errori (modifiche correttive),
    
- permette l’aggiunta di nuove funzionalità (modifiche perfettive),
    
- consente l’adattamento ai cambiamenti del dominio (modifiche adattative).
    

La manutenzione rappresenta spesso la parte più costosa del ciclo di vita del software.

---

### **19. Sintesi della lezione**

In questa lezione sono stati introdotti concetti fondamentali:

- differenza tra processo e prodotto,
    
- caratteristiche distintive del software rispetto ad altri prodotti,
    
- qualità del processo:
    
    - produttività,
        
    - tempestività,
        
    - trasparenza,
        
- qualità del prodotto software:
    
    - interne:
        
        - riusabilità,
            
        - verificabilità,
            
        - facilità di manutenzione,
            
    - esterne:
        
        - funzionalità,
            
        - affidabilità,
            
        - usabilità,
            
        - efficienza,
            
        - portabilità,
            
        - interoperabilità,
            
        - robustezza.
            

Un principio centrale è che:

> qualità di processo e qualità di prodotto sono fortemente legate tra loro.

---

### **20. Prossimi passi**

Nella prossima lezione verranno analizzate:

> le qualità specifiche che il software **sicuro** deve possedere, oltre alle qualità generali introdotte in questa lezione.