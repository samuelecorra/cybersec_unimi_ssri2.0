## **Lezione 5: Contromisure**

### **1. Obiettivo delle contromisure**

Le contromisure contro il malware hanno lo scopo di **prevenire**, **rilevare**, **identificare** e, quando possibile, **rimuovere** software dannoso da un sistema informatico.

I software che svolgono questo ruolo sono spesso chiamati genericamente **antivirus**, anche se oggi non si limitano più ai soli virus: devono contrastare worm, trojan, rootkit, backdoor, spyware, adware e molte altre forme di malware.

L’obiettivo ideale è impedire:

- l’installazione del malware;
    
- l’esecuzione del codice infetto;
    
- la propagazione verso altri sistemi;
    
- il danneggiamento o la sottrazione di dati.
    

> 📌 La migliore difesa è sempre la prevenzione: rilevare e rimuovere un malware dopo l’infezione è possibile, ma spesso significa intervenire quando il sistema è già stato compromesso.

---

### **2. Prevenzione**

La prevenzione è la strategia più efficace per ridurre il rischio di infezione. Nel transcript vengono richiamati quattro elementi fondamentali.

#### **2.1. Politica di sicurezza**

Una **politica di sicurezza** definisce le regole che disciplinano il comportamento dei componenti del sistema:

- chi può installare software;
    
- quali programmi possono essere eseguiti;
    
- quali connessioni di rete sono consentite;
    
- quali privilegi vengono concessi agli utenti;
    
- come devono essere protetti file, servizi e credenziali.
    

Una buona politica di sicurezza riduce la superficie d’attacco: anche se un utente o un programma viene compromesso, il danno rimane più circoscritto.

#### **2.2. Consapevolezza degli utenti**

Gli utenti devono essere informati sui principali comportamenti rischiosi:

- aprire allegati sospetti;
    
- cliccare link ricevuti via email o messaggistica;
    
- installare software da fonti non affidabili;
    
- ignorare avvisi di sicurezza;
    
- usare password deboli o riutilizzate.
    

Molti malware sfruttano il fattore umano: senza consapevolezza, anche il miglior sistema tecnico può essere aggirato.

#### **2.3. Mitigazione delle vulnerabilità**

Mitigare le vulnerabilità significa ridurre le debolezze presenti nel software e nella configurazione del sistema:

- applicare patch e aggiornamenti;
    
- rimuovere servizi inutili;
    
- correggere configurazioni insicure;
    
- limitare i privilegi dei processi;
    
- monitorare librerie, componenti e applicazioni vulnerabili.
    

#### **2.4. Mitigazione delle minacce**

Mitigare le minacce significa considerare i modi concreti in cui gli attaccanti possono agire e predisporre misure adeguate:

- filtri contro allegati malevoli;
    
- blocco di siti sospetti;
    
- controllo degli eseguibili scaricati;
    
- monitoraggio delle connessioni;
    
- segmentazione della rete;
    
- backup e procedure di ripristino.
    

---

### **3. Quando la prevenzione fallisce**

Se le tecniche preventive falliscono, il sistema deve passare a tre fasi successive.

#### **3.1. Rilevamento**

Il **rilevamento** consiste nell’accorgersi che è in corso un’infezione o un comportamento anomalo.

Esempi:

- un processo modifica file di sistema;
    
- un programma apre connessioni verso domini sospetti;
    
- un host esegue scansioni verso molte macchine;
    
- un eseguibile tenta di iniettare codice in altri processi.
    

#### **3.2. Identificazione**

L’**identificazione** cerca di capire quale malware sia presente:

- famiglia del malware;
    
- vettore di infezione;
    
- file coinvolti;
    
- processi attivi;
    
- connessioni usate;
    
- eventuale persistenza sul sistema.
    

#### **3.3. Rimozione e ripristino**

La **rimozione** deve eliminare il malware e ripristinare il corretto funzionamento del sistema:

- terminare processi malevoli;
    
- cancellare file infetti;
    
- rimuovere chiavi di registro o meccanismi di avvio automatico;
    
- chiudere backdoor;
    
- revocare credenziali compromesse;
    
- applicare patch;
    
- verificare che non restino tracce dell’infezione.
    

> ⚠️ Rimuovere il file principale non basta sempre: rootkit, backdoor e meccanismi di persistenza possono lasciare componenti nascosti che riattivano l’infezione.

---

### **4. Generazioni di antivirus**

Le tecniche antivirus si sono evolute nel tempo per rispondere all’evoluzione dei malware.

### **4.1. Scanner di prima generazione: firme**

Gli scanner di prima generazione cercano **firme** note del malware:

- sequenze di istruzioni;
    
- stringhe caratteristiche;
    
- pattern presenti in file eseguibili;
    
- frammenti riconoscibili in memoria o su disco.
    

Il principio è semplice: se un file contiene una sequenza già nota come malevola, viene segnalato come infetto.

Questa tecnica funziona bene contro malware già conosciuti, ma è debole contro malware che cambiano forma.

> ⚠️ Polimorfismo e metamorfismo sono stati sviluppati proprio per rendere meno efficace il rilevamento basato su firme statiche.

---

### **4.2. Scanner di seconda generazione: euristiche e integrità**

Gli antivirus di seconda generazione non cercano soltanto una firma esatta, ma usano criteri più generali.

#### **Analisi euristica**

L’analisi euristica cerca pattern sospetti:

- istruzioni tipiche dei malware;
    
- tentativi di modificare altri eseguibili;
    
- codice offuscato;
    
- routine di cifratura sospette;
    
- sequenze associate a comportamenti anomali.
    

L’obiettivo è riconoscere malware anche quando non coincide perfettamente con una firma già nota.

#### **Controllo di integrità**

Il controllo di integrità verifica che file e programmi importanti non siano stati modificati:

- programmi di sistema;
    
- librerie;
    
- file di configurazione;
    
- eseguibili critici.
    

Se un file che dovrebbe rimanere invariato cambia senza motivo, può esserci stata un’infezione.

---

### **4.3. Antivirus di terza generazione: analisi dinamica**

Gli antivirus di terza generazione osservano il **comportamento del programma durante l’esecuzione**.

Invece di chiedersi solo “questo file contiene una firma nota?”, cercano di capire “questo programma sta facendo qualcosa di pericoloso?”.

Esempi di azioni sospette:

- aprire connessioni verso siti sconosciuti o sospetti;
    
- stabilire molte connessioni dirette verso macchine remote;
    
- contattare molti host con frequenza anomala;
    
- modificare file di sistema;
    
- installare componenti persistenti;
    
- tentare di nascondere processi o file.
    

Questa tecnica è utile contro malware nuovi, ma introduce un problema: l’azione dannosa potrebbe avvenire prima che il sistema riesca a bloccarla.

---

### **4.4. Antivirus di quarta generazione: pacchetti integrati**

Gli antivirus di quarta generazione combinano più tecniche:

- firme statiche;
    
- euristiche;
    
- controllo di integrità;
    
- analisi dinamica;
    
- monitoraggio della rete;
    
- sandboxing;
    
- blocco comportamentale.
    

Sono quindi piattaforme di protezione più complesse, pensate per contrastare malware moderni che cambiano forma, usano più canali e cercano di eludere il rilevamento.

---

### **5. Analisi in sandbox**

Una **sandbox** è un ambiente isolato in cui eseguire codice potenzialmente malevolo senza esporre direttamente il sistema reale.

Il termine significa letteralmente “scatola di sabbia”: un luogo protetto dove il programma può essere osservato mentre opera.

Una sandbox può essere realizzata tramite:

- macchina virtuale;
    
- ambiente controllato;
    
- sistema operativo isolato;
    
- contenitore;
    
- ambiente di analisi dedicato.
    

L’idea è eseguire il codice sospetto prima di lasciarlo interagire con il sistema effettivo.

#### **Vantaggi**

La sandbox permette di rilevare malware anche quando cambia aspetto:

- il file può non avere una firma nota;
    
- il codice può essere cifrato o offuscato;
    
- il malware può essere polimorfico o metamorfico;
    
- il comportamento malevolo può comunque emergere durante l’esecuzione.
    

#### **Limiti**

L’analisi in sandbox è più costosa e complessa rispetto alla scansione statica.

Inoltre non sempre produce risultati:

- alcuni malware rilevano di essere in una sandbox;
    
- altri attendono condizioni specifiche prima di attivarsi;
    
- altri diventano operativi solo dopo una certa data, un certo input o una connessione di rete reale;
    
- altri sembrano innocui finché non ricevono comandi da un server remoto.
    

> ⚠️ Se la condizione scatenante non si verifica durante l’analisi, il malware può apparire innocuo anche se non lo è.

---

### **6. Analisi dinamica e blocco comportamentale**

L’**analisi dinamica** osserva il comportamento di un programma in tempo reale, mentre viene eseguito sul sistema o in un ambiente controllato.

Il sistema di protezione monitora azioni come:

- apertura di connessioni di rete;
    
- modifica di file critici;
    
- creazione di processi sospetti;
    
- accesso a directory protette;
    
- scrittura in aree di avvio automatico;
    
- invio anomalo di dati verso l’esterno.
    

Quando viene rilevata un’azione potenzialmente dannosa, il sistema può:

- bloccare l’azione;
    
- terminare il processo;
    
- mettere il file in quarantena;
    
- avvisare l’utente o l’amministratore;
    
- registrare l’evento per analisi successive.
    

Il limite principale è temporale: bisogna bloccare l’azione **prima** che produca danni.

Se il comportamento malevolo è nuovo o non corrisponde a pattern già osservati, il sistema può non riconoscerlo in tempo.

---

### **7. Scansione perimetrale e monitoraggio del traffico**

Un’altra classe di contromisure riguarda la **protezione perimetrale**: sistemi che controllano il traffico in ingresso e in uscita dal sistema o dalla rete da proteggere.

Queste tecniche saranno approfondite più avanti nel corso, ma l’idea generale è costruire un perimetro attorno alla rete o al singolo host.

Esempi:

- firewall;
    
- sistemi di intrusion detection;
    
- sistemi di intrusion prevention;
    
- filtri sul traffico;
    
- monitor locali delle connessioni aperte dai programmi.
    

Un caso comune è il software che chiede conferma quando un programma tenta di aprire una connessione non prevista.

---

### **7.1. Monitor in ingresso**

Il monitor in ingresso controlla il traffico che arriva dall’esterno verso la rete o verso il computer protetto.

Può rilevare:

- connessioni verso indirizzi IP non usati;
    
- tentativi di accesso a porte non autorizzate;
    
- scansioni di rete;
    
- molte connessioni ripetute dallo stesso host;
    
- traffico verso servizi vulnerabili.
    

Questo consente di bloccare tentativi preliminari di attacco, come ricognizione e port scanning.

---

### **7.2. Monitor in uscita**

Il monitor in uscita controlla il traffico generato dai sistemi interni verso l’esterno.

È utile perché un host già compromesso può:

- contattare un server di comando e controllo;
    
- inviare dati riservati fuori dalla rete;
    
- effettuare scansioni verso altri sistemi;
    
- connettersi a siti sospetti;
    
- diffondere malware o spam.
    

> 📌 Il traffico in uscita è spesso decisivo: un malware già entrato nel sistema deve comunicare, aggiornarsi o esfiltrare dati. Monitorare solo il traffico in ingresso lascia scoperta metà del problema.

---

### **8. Sintesi finale**

Le contromisure contro il malware si organizzano in più livelli:

- **prevenzione**, tramite politiche di sicurezza, consapevolezza, patch e riduzione delle minacce;
    
- **rilevamento**, tramite firme, euristiche, integrità e analisi comportamentale;
    
- **analisi protetta**, tramite sandbox e macchine virtuali;
    
- **risposta**, tramite blocco, quarantena, rimozione e ripristino;
    
- **protezione perimetrale**, tramite monitoraggio del traffico in ingresso e in uscita.
    

> ✅ Nessuna contromisura è sufficiente da sola. La difesa efficace nasce dalla combinazione di prevenzione, rilevamento, analisi dinamica, risposta e controllo del traffico.
