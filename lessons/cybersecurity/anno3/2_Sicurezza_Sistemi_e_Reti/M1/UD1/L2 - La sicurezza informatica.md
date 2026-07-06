## **Lezione 2: La sicurezza informatica**

### **1. Introduzione**

La **sicurezza informatica** è oggi un tema di assoluta attualità.  
Ogni giorno, i media riportano casi di attacchi informatici che colpiscono istituzioni, aziende e privati, causando danni economici e sociali significativi.  
L’obiettivo di questa lezione è comprendere **la natura del problema della sicurezza** e il motivo per cui rappresenta una sfida tanto complessa quanto universale.

---

### **2. Attacchi informatici nella cronaca**

Gli incidenti di sicurezza mostrano come le minacce digitali possano compromettere servizi pubblici essenziali.  
Un esempio emblematico è l’**attacco alla Regione Lazio**: a distanza di un mese, **13 servizi su 36** risultavano ancora non operativi, con gravi ripercussioni su sanità, farmacie e infrastrutture digitali.  
Questo caso evidenzia la **dipendenza critica** delle istituzioni dai sistemi informatici e la difficoltà di ripristinarli dopo un’infezione o un attacco ransomware.

La cronaca mostra inoltre che gli attacchi non hanno una sola motivazione. Alcuni mirano a influenzare dinamiche politiche o internazionali, altri allo spionaggio di funzionari e istituzioni, altri ancora al furto di dati ministeriali o al semplice compiacimento di compiere un’azione illecita. Nei casi con ritorno economico, come ransomware e campagne estorsive, il danno non coincide solo con la perdita dei dati: il blocco dei servizi può impedire prenotazioni, attività amministrative e normali prestazioni pubbliche anche per settimane.

---

### **3. Vulnerabilità nei sistemi moderni**

#### **3.1. Sicurezza in iOS e macOS**

Nel 2020 il ricercatore **Pawel Wylecial** scoprì una grave vulnerabilità nel browser Safari legata all’API _Web Share_ di Apple.  
Nonostante la segnalazione, la correzione venne posticipata di oltre un anno.  
L’episodio mostra due aspetti cruciali:

- anche le grandi aziende possono **ignorare vulnerabilità note**;
    
- la sicurezza dipende anche dalla **responsabilità etica** dei ricercatori e dei produttori.
    

Questa API, che consente la condivisione di file, poteva essere sfruttata per sottrarre dati privati con la collaborazione inconsapevole dell’utente.

Il punto didattico non è solo la vulnerabilità in sé, ma il fatto che anche piattaforme percepite come “più sicure”, come l’ecosistema Apple, possono contenere bug di programmazione. La gestione della divulgazione responsabile diventa quindi parte della sicurezza: se il produttore non corregge in tempi adeguati, la pubblicazione del bug può accelerare la patch, ma aumenta anche il rischio che l’attacco venga replicato.

---

#### **3.2. Sicurezza nel 5G**

Il caso **Huawei** rappresenta un punto di tensione tra tecnologia e geopolitica.  
Il governo statunitense ha accusato il colosso cinese di essere potenzialmente legato a rischi di spionaggio, limitando l’uso dei suoi componenti in infrastrutture nazionali.  
Il problema centrale non è solo politico:

> non abbiamo altro modo che fidarci dei sistemi che utilizziamo, pur non potendo verificarne realmente l’affidabilità.

Questo porta alla domanda fondamentale: **come possiamo fidarci di tecnologie che non controlliamo?**

La questione è più ampia del singolo produttore. Nel 5G Huawei non era rilevante solo per gli smartphone, ma anche per apparati e componenti destinati a diventare parte dell’infrastruttura di rete. Verificare completamente una piattaforma è estremamente difficile perché la filiera va dall’hardware al firmware, dal sistema operativo alle applicazioni: una backdoor o un comportamento non documentato può trovarsi a livelli diversi e non essere rilevabile con un controllo superficiale.

---

### **4. Sicurezza e tecnologia**

Le moderne filiere tecnologiche aumentano la superficie di attacco:

- i **pacchetti open source** possono essere manomessi inserendo backdoor nei repository pubblici;
	    
- il **Google Play Store** è spesso veicolo di _app malevole_;
    
- il caso **NotPetya (2017)** mostrò come un aggiornamento software fraudolento possa diffondere malware su scala globale;
    
- anche i **chip hardware** possono contenere backdoor inserite in fase di produzione;
    
- perfino agenzie governative (come la **NSA**) hanno sfruttato spedizioni di router per intercettare comunicazioni.
    

In sintesi, **ogni fase del ciclo di vita tecnologico può essere compromessa**.

Nel software open source la libertà di contribuzione accelera lo sviluppo e riduce i costi, ma rende più complesso controllare l’intero processo di produzione del codice. Nei marketplace mobili, invece, esistono controlli e rimozioni di applicazioni sospette, ma la facilità di pubblicazione resta appetibile per chi vuole distribuire malware sotto forma di app apparentemente legittime.

Il caso **NotPetya** mostra bene il rischio degli attacchi alla supply chain: il malware venne distribuito tramite l’aggiornamento di un software di contabilità usato in Ucraina. Gli utenti non stavano installando consapevolmente codice sospetto, ma accettavano un update apparentemente normale da un canale ritenuto fidato.

Anche per i chip hardware il problema è simile: la verifica completa del funzionamento interno è molto difficile, soprattutto quando il mercato dipende da pochi grandi produttori. In pratica ci si affida spesso alla reputazione del fornitore, ma la fiducia nel marchio non equivale a una prova tecnica dell’assenza di backdoor.

---

### **5. Le criticità del 5G**

Il **5G** non serve solo a velocizzare i contenuti, ma a connettere miliardi di dispositivi (_Internet of Things_).  
Ciò introduce nuovi rischi:

- automobili, centrali, città intelligenti e dispositivi autonomi comunicano senza intervento umano;
    
- un attacco o una backdoor potrebbe **interrompere servizi vitali** o causare danni fisici diretti.
    

Il vero pericolo non è l’intercettazione dei dati, ma il potenziale di **sabotaggio sistemico**.

Se una rete 5G supporta servizi autonomi o semi-autonomi, una backdoor può diventare molto più grave di un canale di spionaggio: può permettere di manipolare traffico veicolare, dispositivi industriali, sistemi di sicurezza e infrastrutture critiche. Il dibattito sulla sicurezza del 5G nasce proprio dal fatto che l’infrastruttura di comunicazione diventa il supporto tecnico di molte attività fisiche del paese.

---

### **6. Il problema della sicurezza**

Le strategie di difesa seguono due approcci principali:

1. **Ispezione tecnica:** migliorare il controllo dei prodotti di cui si possiede il codice sorgente.
    
2. **Affidabilità strutturale:** progettare sistemi sicuri anche se alcune componenti possono essere compromesse.
    

Nasce così una domanda chiave:

> È possibile costruire sistemi affidabili utilizzando parti non affidabili?

L’esempio storico di **Internet** risponde in parte: è nata come rete affidabile costruita su componenti potenzialmente inaffidabili.

L’analogia è utile ma va letta con cautela: Internet risolve soprattutto un problema di affidabilità e interconnessione tra reti non gestite centralmente. La sicurezza è più difficile, perché le componenti non sono solo soggette a guasti casuali, ma possono essere state progettate o manipolate intenzionalmente da sviluppatori malevoli, criminali informatici o agenzie governative.

---

### **7. Sicurezza e affidabilità**

La sicurezza è **più complessa** dell’affidabilità.  
È difficile garantire un sistema sicuro persino quando le sue parti sono affidabili, figuriamoci quando non lo sono.  
Le minacce provengono da:

- criminali informatici,
    
- governi,
    
- insider e sviluppatori malevoli.
    

Le tecnologie attuali non bastano a contrastare attacchi sempre più sofisticati, mentre la **dipendenza digitale** dell’umanità cresce in modo esponenziale.

Questa dipendenza rende critici sistemi che in passato sarebbero stati considerati solo “informativi”: gestione sanitaria, distribuzione dell’energia elettrica, dighe, traffico e servizi pubblici. Quando questi processi vengono informatizzati, un attacco non produce solo un problema tecnico, ma può interrompere attività essenziali di una città o di un intero paese.

---

### **8. Sicurezza e guasti**

La **sicurezza** si distingue dall’ingegneria tradizionale del software perché non mira solo a “far funzionare” un sistema, ma a **impedire comportamenti indesiderati**.  
L’obiettivo non è l’efficienza, ma la **resistenza attiva** contro un avversario intenzionale.  
Mentre i guasti normali derivano da errori o malfunzionamenti, gli attacchi sono **atti deliberati di sovversione**.

Per questo la sicurezza ha una natura “negativa”: non basta dimostrare che il sistema realizza correttamente una funzione prevista, bisogna ragionare su tutti i modi in cui un avversario potrebbe aggirare le difese. Il difensore deve quindi anticipare scenari molteplici e spesso non ovvi; l’attaccante, invece, può avere successo trovando anche una sola strada non prevista.

---

### **9. L’interdisciplinarietà della sicurezza**

La sicurezza informatica è una disciplina **profondamente interdisciplinare**, che coinvolge molte aree della Computer Science:

- **Crittografia**
    
- **Networking e protocolli di comunicazione**
    
- **Sistemi operativi**
    
- **Basi di dati**
    
- **Intelligenza artificiale e machine learning**
    
- **Architettura e hardware**
    
- **Linguaggi di programmazione e compilatori**
    
- **Interazione uomo-macchina (HCI)** e **psicologia cognitiva**
	    

Solo la sinergia tra questi ambiti consente di sviluppare sistemi realmente sicuri.

Ogni area contribuisce con un tipo diverso di problema e di contromisura: la crittografia fornisce primitive matematiche, ma non basta se il sistema operativo, il database o la rete espongono dati e privilegi in modo scorretto. Le architetture hardware possono introdurre vulnerabilità legate a ottimizzazioni dei processori, come negli attacchi della famiglia **Spectre**, in cui meccanismi pensati per aumentare le prestazioni hanno aperto canali per inferire dati riservati. Linguaggi, compilatori e runtime devono invece ridurre la possibilità di eseguire codice malevolo o non sicuro.

La componente umana è altrettanto importante: molte tecniche di **social engineering** sfruttano fiducia, fretta, abitudini e interfacce progettate male. Un sistema tecnicamente robusto può fallire se induce l’utente a concedere permessi, installare applicazioni malevole o condividere dati senza comprenderne le conseguenze.

---

### **10. Conclusione**

- La sicurezza informatica influenza **ogni ambito della vita moderna**, dalla politica all’economia, fino alla vita quotidiana.
    
- **Non esistono piattaforme o tecnologie intrinsecamente sicure**: ogni sistema può essere violato.
    
- La sicurezza è una **sfida continua**, che richiede aggiornamento costante, visione sistemica e collaborazione tra discipline.
