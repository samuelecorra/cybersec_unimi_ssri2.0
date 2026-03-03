# **Lezione 2: Incident response**

### 1. Definizione di incidente informatico e incident response

Nel contesto della sicurezza dei sistemi informativi, un incidente informatico è definito come qualsiasi situazione che comporti una violazione della sicurezza, reale o potenziale. Un incidente può riguardare, ad esempio, l’accesso non autorizzato a un sistema o ai dati, l’utilizzo improprio di account o lo sfruttamento di vulnerabilità tecniche. Anche l’uso scorretto di un account legittimo può costituire un incidente.

L’incident response rappresenta l’insieme delle attività che consentono di reagire a un incidente informatico in modo efficace e tempestivo. L’organizzazione deve essere in grado di identificare rapidamente l’evento, stabilire se esso costituisca effettivamente un incidente e attivare le procedure appropriate.

Le attività di risposta devono garantire:

- L’identificazione tempestiva dell’evento
    
- La classificazione come incidente informatico
    
- La definizione delle operazioni da svolgere
    
- La pianificazione delle successive attività investigative
    

Questo approccio consente di contenere i danni e di preservare eventuali prove digitali.

---

### 2. Esempi di incidenti informatici

Gli incidenti possono assumere molte forme diverse e riguardare sia comportamenti interni sia attacchi esterni.

Tra gli esempi più comuni vi sono:

- Utilizzo non autorizzato di servizi informatici
    
- Uso improprio di apparecchiature aziendali per fini personali o di profitto
    
- Presenza di server personali non autorizzati nella rete aziendale
    
- Attività di spionaggio o monitoraggio delle comunicazioni
    
- Furto di dispositivi come notebook
    
- Copia non autorizzata di dati tramite supporti rimovibili
    
- Attacchi tramite trojan o tecniche di tunneling
    
- Hoax o false segnalazioni
    
- Attività di scansione aggressiva della rete
    

Questi esempi evidenziano come gli incidenti possano derivare sia da comportamenti dolosi sia da errori umani.

---

### 3. Fasi operative della gestione dell’incidente

La gestione operativa di un incidente informatico segue una sequenza di attività strutturate.

La fase di rilevamento consiste nel riconoscimento di un potenziale incidente.

Segue l’identificazione, nella quale vengono individuate le possibili prove relative all’incidente e viene avviata la catena di custodia con eventuale acquisizione dei dati.

La conservazione riguarda il mantenimento dell’integrità e dell’attendibilità delle prove raccolte nel lungo periodo.

L’analisi consiste nell’esame delle prove informatiche e rientra nelle attività di informatica forense.

Queste fasi dimostrano il collegamento tra gestione degli incidenti e investigazione digitale.

---

### 4. Contenuti del piano di incident response

Un piano di incident response deve prevedere una serie di attività organizzative e operative.

Tra queste vi sono:

- Prima valutazione dell’incidente
    
- Comunicazione interna
    
- Contenimento del danno e riduzione del rischio
    
- Identificazione del tipo e della gravità del sistema compromesso
    
- Protezione delle evidenze
    
- Notifica a contatti esterni di supporto
    
- Ripristino dei sistemi coinvolti
    
- Documentazione completa dell’incidente
    
- Valutazione dei danni e dei costi
    
- Revisione e aggiornamento delle politiche
    

Il piano deve quindi coprire l’intero ciclo di gestione dell’incidente, dalla rilevazione alla fase di miglioramento.

---

### 5. Assessment iniziale dell’incidente

La prima attività consiste nella valutazione iniziale (assessment) dell’evento.

Occorre determinare se si tratti di un problema di configurazione o di un vero incidente, stabilire la gravità e raccogliere informazioni utili per ulteriori analisi.

È necessario anche valutare le strategie di contenimento e registrare tutte le operazioni svolte.

La comunicazione è un elemento essenziale: l’incidente deve essere comunicato al CSIRT e devono essere identificati eventuali contatti esterni da coinvolgere.

La documentazione accurata delle attività rappresenta un requisito fondamentale.

---

### 6. Contenimento del danno

Il contenimento del danno è una fase critica nella quale rapidità e decisione possono fare la differenza.

Le priorità operative includono:

- Protezione dei dati sensibili e classificati
    
- Protezione di altri dati aziendali
    
- Protezione dell’hardware e del software
    
- Minimizzazione dell’interruzione dei servizi
    

Le azioni possibili includono la disabilitazione del punto di ingresso dell’attacco, la ricostruzione del sistema compromesso e la modifica delle password.

L’obiettivo è ripristinare la normale operatività nel minor tempo possibile evitando che l’incidente si ripeta.

---

### 7. Determinazione della natura e della gravità dell’attacco

Dopo il contenimento iniziale è necessario comprendere la natura dell’attacco.

Occorre identificare l’origine, gli obiettivi dell’attaccante, i sistemi compromessi e i file coinvolti.

La valutazione della gravità richiede la collaborazione tra i membri del CSIRT e l’analisi di indicatori come accessi non autorizzati, modifiche ai permessi, anomalie nei log, nuovi programmi in esecuzione automatica o variazioni nelle prestazioni del sistema.

È importante anche verificare la presenza di dati non pertinenti all’attività aziendale e valutare eventuali rischi per la privacy dei dipendenti.

Questa fase consente di comprendere l’impatto reale dell’incidente.

---

### 8. Raccolta e protezione delle prove

La raccolta delle prove deve essere effettuata con procedure rigorose.

È consigliabile effettuare acquisizioni multiple, preferibilmente su supporti differenti, per garantire la conservazione dei dati e consentire sia il recupero sia l’analisi forense.

Le prove devono essere documentate accuratamente per garantirne l’integrità e la validità legale.

Questa fase collega direttamente la gestione degli incidenti alle attività di digital forensics.

---

### 9. Comunicazione esterna

La gestione della comunicazione verso l’esterno rappresenta un aspetto delicato dell’incident response.

I potenziali interlocutori possono includere forze di polizia, agenzie di sicurezza, esperti di malware e altri soggetti di supporto.

È fondamentale coordinarsi con il legale dell’organizzazione per definire eventuali comunicazioni pubbliche, che dipendono dal tipo di azienda, dal tipo di clientela e dall’impatto dell’incidente.

Una comunicazione non corretta può aggravare i danni reputazionali.

---

### 10. Documentazione dell’incidente

La documentazione deve includere informazioni dettagliate su:

- Tipo di attacco
    
- Risposta adottata
    
- Responsabili coinvolti
    
- Tempistiche
    
- Motivazioni delle decisioni
    

La documentazione deve essere organizzata e analizzata anche con il supporto del legale per verificare eventuali responsabilità interne o la presenza di insider.

Una documentazione completa è fondamentale sia per fini legali sia per il miglioramento futuro.

---

### 11. Valutazione dei danni e dei costi

La valutazione economica dell’incidente deve considerare sia costi diretti sia indiretti.

Tra questi vi sono:

- Perdita di competitività
    
- Interruzione delle attività
    
- Spese legali
    
- Costi di indagine
    
- Costi di recupero dei sistemi
    
- Sostituzione hardware e software
    
- Aggiornamenti di sicurezza
    
- Danni reputazionali
    

La quantificazione dei costi consente di valutare l’impatto complessivo e giustificare eventuali investimenti in sicurezza.

---

### 12. Revisione e miglioramento

Dopo la gestione dell’incidente è necessario avviare una fase di revisione.

Occorre analizzare cosa ha funzionato, cosa deve essere migliorato e come prevenire incidenti futuri.

Le politiche di aggiornamento devono essere riviste considerando nuove tecnologie e possibili miglioramenti dei meccanismi di prevenzione.

Questa fase chiude il ciclo di gestione dell’incidente e contribuisce al miglioramento continuo della sicurezza.

---

### 13. Sintesi finale

La lezione ha illustrato il concetto di incident response, le fasi operative e i contenuti di un piano di risposta agli incidenti informatici. Sono stati analizzati l’assessment iniziale, il contenimento del danno, la raccolta delle prove, la comunicazione e la valutazione dei costi.

È stato evidenziato che una gestione efficace degli incidenti richiede pianificazione preventiva, documentazione accurata e coordinamento tra diverse figure organizzative, con particolare attenzione alla comunicazione esterna e agli aspetti legali.

La capacità di risposta agli incidenti rappresenta un elemento fondamentale della sicurezza informatica moderna.