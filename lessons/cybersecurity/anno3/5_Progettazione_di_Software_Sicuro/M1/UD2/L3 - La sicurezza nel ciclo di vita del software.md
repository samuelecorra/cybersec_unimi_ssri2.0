# **Lezione 3: La sicurezza nel ciclo di vita del software**

---

### **1. Panoramica della lezione**

Questa lezione affronta un tema centrale dell’ingegneria del software sicuro: **come integrare la sicurezza all’interno del ciclo di vita del software**.

L’obiettivo è comprendere:

- il rapporto tra sicurezza e funzionalità,
    
- il ruolo dell’analisi dei rischi,
    
- come la sicurezza si inserisce nelle diverse fasi di sviluppo,
    
- quali standard e metodologie esistono per la valutazione dei sistemi sicuri.
    

Un concetto chiave che attraversa tutta la lezione è che:

> l’analisi della sicurezza non coincide con il processo di sviluppo, ma lo attraversa in modo trasversale.

---

### **2. Sicurezza vs funzionalità**

Progettare un sistema software che garantisca contemporaneamente:

- proprietà di sicurezza,
    
- qualità del software,
    

è un’attività complessa.

La difficoltà nasce dal fatto che:

- sicurezza e funzionalità possono entrare in conflitto,
    
- risorse e tempo sono limitati,
    
- non tutte le proprietà possono essere garantite allo stesso livello.
    

Per questo motivo è fondamentale l’**analisi di sicurezza**, chiamata anche:

> analisi e gestione dei rischi.

Questa analisi è indispensabile per:

- decidere quali obiettivi realizzare,
    
- stabilire quali proprietà e qualità garantire,
    
- bilanciare sicurezza e funzionalità.
    

Un punto fondamentale:

> l’analisi di sicurezza è un processo ortogonale al processo di sviluppo del software.

Ciò significa che:

- non è una fase separata,
    
- ma accompagna tutte le fasi dello sviluppo.
    

---

### **3. Modello ottimale per software sicuro**

La lezione ribadisce che:

> il modello a spirale è il modello di sviluppo ottimale per software sicuro.

Le motivazioni sono:

- include la fase di analisi dei rischi,
    
- è flessibile,
    
- consente raffinamenti successivi,
    
- permette validazione già nelle fasi iniziali,
    
- consente integrare soluzioni scoperte a posteriori.
    

Queste caratteristiche lo rendono particolarmente adatto ai sistemi critici.

---

### **4. L’ingegnere della sicurezza**

Viene introdotta una nuova figura professionale:

> l’ingegnere della sicurezza.

Questa figura:

- si occupa dell’analisi di sicurezza,
    
- lavora in modo ortogonale al processo di sviluppo.
    

Competenze richieste:

- profonda conoscenza dello sviluppo software,
    
- competenze specifiche di sicurezza.
    

Questa figura evidenzia come la sicurezza sia una disciplina specializzata.

---

### **5. Artifatti e stakeholder nell’analisi di sicurezza**

L’analisi di sicurezza utilizza:

- tutti gli **artifatti esistenti**,
    
- tutti gli **stakeholder coinvolti**,
    

come risorse per identificare i rischi.

#### **5.1 Artifatti**

Tra gli artifatti disponibili:

- documenti dei requisiti,
    
- documenti di architettura,
    
- documenti di design,
    
- modelli,
    
- codice esistente,
    
- casi di test.
    

#### **5.2 Stakeholder**

Gli stakeholder includono:

- utenti,
    
- clienti,
    
- ingegneri del software,
    
- ingegneri della sicurezza,
    
- esperti di dominio.
    

Il coinvolgimento di più prospettive aiuta a individuare rischi nascosti.

---

### **6. Sicurezza e requisiti**

Durante la definizione dei requisiti di sicurezza è necessario identificare:

- cosa deve essere protetto,
    
- da chi deve essere protetto,
    
- per quanto tempo deve essere protetto,
    
- quanto vale mantenere i dati protetti.
    

I requisiti devono essere:

- chiari,
    
- completi.
    

#### **6.1 Esempio di requisito mal posto**

“Questa applicazione deve usare la crittografia quando necessario.”

Questo è un requisito mal formulato perché:

- descrive una soluzione,
    
- non identifica il problema.
    

#### **6.2 Esempio di requisito ben posto**

“I numeri delle carte di credito vanno protetti contro potenziali furti perché sono informazioni delicate.”

Questo è corretto perché:

- spiega il motivo,
    
- lascia la soluzione alla fase successiva.
    

---

### **7. Analisi dei rischi**

L’analisi dei rischi ha l’obiettivo di:

- identificare i rischi possibili,
    
- valutare strategie per prevenirli o affrontarli.
    

Influisce anche sul processo di auditing.

#### **7.1 Classificazione dei rischi**

I rischi devono essere classificati in base alla severità.

Questa classificazione:

- non è assoluta,
    
- dipende dal contesto (context-sensitive),
    
- serve per allocare risorse.
    

#### **7.2 Difficoltà di valutazione della sicurezza**

È difficile confrontare sistemi in termini di sicurezza perché:

- non esiste una metrica assoluta,
    
- esistono solo linee guida generali.
    

Ad esempio:

- come eseguire l’analisi,
    
- quali rischi considerare.
    

---

### **8. Sicurezza e specifica**

La fase di specifica deve:

> integrare le possibili soluzioni ai rischi individuati.

L’analisi di sicurezza continua anche in questa fase.

Una buona specifica dovrebbe essere:

- dettagliata,
    
- formale,
    
- completa,
    
- non ambigua,
    
- chiara,
    
- comprensibile,
    
- eseguibile (per ottenere feedback immediato).
    

Se emergono nuovi rischi:

> devono essere riflessi nei requisiti.

Questo dimostra la natura iterativa del processo.

---

### **9. Sicurezza e design**

Durante la progettazione è necessario identificare:

- come i dati passano tra componenti,
    
- utenti, ruoli e diritti (espliciti o impliciti),
    
- soluzioni possibili ai problemi individuati,
    
- relazioni di fiducia (trust) tra componenti.
    

Un concetto importante:

> il security plan deve essere applicato in fase di design per essere efficace.

La lezione cita esempi di sistemi progettati senza principi di sicurezza:

- Windows 9X,
    
- Unix (nelle versioni iniziali).
    

Questo mostra le conseguenze di una progettazione non orientata alla sicurezza.

---

### **10. Sicurezza e implementazione**

L’implementazione è una fase critica perché:

> molti attacchi derivano da errori introdotti nel codice.

Compiti dell’ingegnere della sicurezza:

- definire linee guida di codifica sicura,
    
- effettuare controllo del codice (**code audit**).
    

Questo evidenzia l’importanza della sicurezza a livello di programmazione.

---

### **11. Sicurezza e testing**

Nei sistemi sicuri si distinguono due tipi di testing.

#### **11.1 Testing funzionale**

Serve a verificare che:

> il sistema faccia ciò che deve fare.

Include condizioni:

- normali,
    
- critiche.
    

#### **11.2 Testing di sicurezza**

Serve a verificare il sistema:

> come farebbe un utente malizioso.

Quindi si tenta di:

- sfruttare vulnerabilità,
    
- individuare punti deboli.
    

#### **11.3 Code coverage**

Il **code coverage** rimane importante nel testing di sicurezza perché:

- individua parti di codice mai eseguite,
    
- codice non eseguito può contenere vulnerabilità gravi.
    

---

### **12. Criteri comuni e standard di sicurezza**

La valutazione dei sistemi sicuri richiede standard.

#### **12.1 Orange Book (1985)**

Pubblicato da:

- NSA,
    
- Department of Defense.
    

Nome ufficiale:

> Trusted Computer Systems Evaluation Criteria.

#### **12.2 Common Criteria**

Standard ISO molto ampio (~600 pagine).

Definisce:

- classi,
    
- componenti,
    

che possono essere combinati per creare:

> profili di protezione per prodotti IT.

Applicabile a:

- hardware,
    
- firmware,
    
- software.
    

---

### **13. Common Evaluation Methodology**

La **Common Evaluation Methodology** (~400 pagine) definisce:

> le modalità di valutazione dei sistemi rispetto ai Common Criteria.

Un esempio applicativo:

- industria delle smart card,
    
- Smart Card Security User’s Group.
    

---

### **14. Processo di valutazione**

La lezione descrive un processo articolato (pagine 8–9).

#### **14.1 Fasi principali**

1. Creazione di un profilo di protezione (es. firewall per dati finanziari).
    
2. Valutazione del profilo con la metodologia comune.
    
3. Pubblicazione del profilo (target di sicurezza).
    
4. Realizzazione del prodotto da parte di un vendor (target di valutazione).
    
5. Invio a un istituto accreditato per valutazione.
    
6. Applicazione della metodologia per verificare conformità.
    
7. Controllo da parte del NVLAP.
    
8. Certificazione finale del prodotto.
    

Questo processo dimostra la complessità della certificazione di sicurezza.

---

### **15. Limiti dei Common Criteria**

I limiti principali sono:

- la sicurezza è più complessa della semplice applicazione di criteri,
    
- i problemi di sicurezza sono context-sensitive,
    
- gli standard definiscono il “cosa” ma non il “come”.
    

Nel campo della sicurezza è invece fondamentale:

- sapere come affrontare i problemi,
    
- sapere come gestire i rischi.
    

---

### **16. Valutazione delle smart cards**

Dal 2001 lo Smart Card Security User’s Group lavora sui criteri per smart card.

Esistono due approcci principali:

1. Profilo europeo:
    
    - maggiore attenzione alla protezione fisica dei dati.
        
2. Profilo americano:
    
    - maggiore attenzione alla protezione software,
        
    - prevenzione di attacchi noti.
        

Molti enti del settore non sono interessati ai Common Criteria.

---

### **17. Sintesi della lezione**

In questa lezione abbiamo visto:

- come integrare la sicurezza nel ciclo di sviluppo,
    
- cosa significa analisi di sicurezza,
    
- come si applica nelle varie fasi,
    
- quali standard esistono per la valutazione dei sistemi sicuri.
    

Concetti fondamentali:

- l’analisi di sicurezza è un processo ortogonale allo sviluppo,
    
- l’ingegnere della sicurezza è la figura responsabile.
    

---

### **18. Prossimi passi**

Gli argomenti introdotti saranno approfonditi nelle lezioni successive, in particolare:

- analisi dei rischi e degli attacchi,
    
- fattori tecnici e umani che introducono errori nel codice.