# **Lezione 2: Linee guida e principi per architetture sicure pt1**

### **1. Introduzione: il ruolo delle domande nella progettazione sicura**

La progettazione di software sicuro non inizia con la scrittura del codice o con la scelta delle tecnologie, ma con un processo di analisi basato su domande fondamentali. La lezione introduce infatti l’idea che, sia nel caso di un nuovo progetto sia nel caso di manutenzione o aggiornamento di software esistente, sia necessario porsi una serie strutturata di quesiti.

Le categorie principali di domande riguardano quattro ambiti:

1. vulnerabilità
    
2. risorse
    
3. software
    
4. obiettivi (goals)
    

Questa fase di interrogazione iniziale è essenziale perché consente di comprendere il contesto di sicurezza, identificare rischi e definire requisiti realistici prima della progettazione architetturale.

---

### **2. Domande sulle vulnerabilità**

Il primo gruppo di domande riguarda la comprensione delle possibili vulnerabilità del sistema.

Tra i quesiti fondamentali troviamo:

- cosa può andare storto?
    
- cosa si sta cercando di proteggere?
    
- chi potrebbe tentare di compromettere la sicurezza?
    
- qual è il punto più debole della difesa?
    

Queste domande introducono implicitamente il concetto di **threat modeling**, cioè la modellazione delle minacce, che rappresenta una pratica fondamentale nella sicurezza software moderna.

L’identificazione delle vulnerabilità non è solo un esercizio teorico, ma serve a comprendere quali componenti del sistema siano critiche e quali meccanismi di protezione debbano essere prioritari.

---

### **3. Domande sulle risorse disponibili**

Il secondo gruppo di domande riguarda le risorse disponibili per la progettazione sicura.

È importante verificare:

- se esiste già un’architettura sicura riutilizzabile
    
- se sono disponibili librerie software affidabili
    
- quali linee guida e standard sono accessibili
    
- se esistono esempi o casi studio simili
    

Questo approccio riflette uno dei principi fondamentali dell’ingegneria del software sicuro: **non reinventare la sicurezza** quando esistono soluzioni già validate.

Il riuso di componenti certificati riduce errori, costi e vulnerabilità, a condizione che tali componenti siano effettivamente affidabili.

---

### **4. Domande sul software e sul contesto operativo**

Il terzo gruppo di domande riguarda il software stesso e il suo contesto di esecuzione.

Aspetti fondamentali da analizzare includono:

- posizione del software nella chain of trust
    
- dipendenze da applicazioni successive
    
- presenza di librerie o utility che potrebbero fornire input non affidabili
    
- identificazione degli utenti legittimi
    
- controllo dell’accesso al codice sorgente o agli eseguibili
    
- previsione di cambiamenti futuri e impatto sulle assunzioni
    
- ambiente di esecuzione del software
    

Queste domande evidenziano come la sicurezza non dipenda solo dal codice interno, ma dall’intero ecosistema in cui il software opera.

---

### **5. Domande sugli obiettivi di sicurezza (goals)**

L’ultima categoria riguarda gli obiettivi di sicurezza e i compromessi progettuali.

Tra le domande principali:

- quale impatto avrebbe un attacco?
    
- quali qualità del software vengono compromesse dalle misure di sicurezza?
    
- come deve reagire il sistema se l’utente viola le misure di sicurezza?
    
- come vengono rilevate tali violazioni?
    

Questo insieme di domande introduce il concetto di **trade-off tra sicurezza e qualità del software**, come usabilità ed efficienza.

La sicurezza non è infatti un requisito isolato, ma interagisce con tutti gli altri attributi del sistema.

---

### **6. Determinare il livello di sicurezza adeguato**

Uno dei principi più importanti presentati nella lezione è il concetto di **“just secure enough”**, cioè rendere il software sufficientemente sicuro rispetto al contesto.

Tentare di rendere il sistema “il più sicuro possibile” può portare al fallimento del progetto, a causa di:

- costi eccessivi
    
- complessità troppo elevata
    
- riduzione dell’usabilità
    
- difficoltà di manutenzione
    

Il livello di sicurezza deve quindi essere determinato considerando:

- il rischio
    
- il costo delle contromisure
    
- il valore delle risorse da proteggere
    

Questo approccio è coerente con i modelli di gestione del rischio studiati nella sicurezza informatica.

---

### **7. Identificazione delle assunzioni**

L’identificazione delle assunzioni è una fase critica della progettazione sicura, perché molte vulnerabilità derivano da assunzioni implicite errate.

La lezione presenta diversi esempi significativi.

#### Assunzioni sui protocolli di rete

Un requisito può affermare che la ricezione di un pacchetto TCP con flag SYN indica che il mittente vuole avviare una comunicazione.

Tuttavia, per evitare vulnerabilità bisogna considerare che:

- il mittente potrebbe avere intenti malevoli
    
- potrebbe essere necessario identificare l’identità del mittente
    

Questo esempio richiama direttamente attacchi come il **SYN flood**.

#### Assunzioni sugli utenti

Un requisito potrebbe affermare che gli utenti sono esseri umani. Tuttavia, in contesti reali bisogna considerare bot, script automatici e attaccanti.

#### Assunzioni sull’autenticazione

Possibili meccanismi includono:

- shared secret per componenti software
    
- metodi biometrici per utenti umani
    

#### Assunzioni sulle risorse computazionali

Ad esempio:

- disponibilità di memoria
    
- spazio di esecuzione
    
- capacità di elaborazione
    

Tutte queste assunzioni influenzano le scelte progettuali e possono introdurre vulnerabilità se non esplicitate correttamente.

---

### **8. Progettare con in mente il nemico (Adversary Principle)**

Uno dei principi fondamentali della sicurezza è progettare il software assumendo che sarà attaccato dal nemico più intelligente possibile.

Questo principio è noto come **Adversary Principle**.

Il progettista deve anticipare attacchi provenienti da:

- avversari intelligenti
    
- avversari razionali
    
- avversari irrazionali
    

Il riferimento ai GASSP (Generally Accepted System Security Principles) sottolinea che questa mentalità è parte delle pratiche riconosciute della sicurezza.

Non è possibile progettare con in mente il nemico senza avere una comprensione realistica degli obiettivi dell’attaccante e delle risorse che potrebbe voler compromettere.

---

### **9. Conoscere e rispettare la chain of trust**

La chain of trust rappresenta la catena di fiducia tra componenti del sistema.

Un principio fondamentale è:

> non invocare programmi non fidati da programmi fidati

Questo errore è comune quando applicazioni invocano comandi di sistema senza verifiche adeguate.

La lezione cita come esempio una vulnerabilità della utility `man` di Unix.

Una regola generale afferma che un programma non deve delegare l’autorità senza delegare anche la responsabilità di controllo.

Un’applicazione rispetta la chain of trust se:

- valida tutti gli input
    
- non delega compiti a entità meno fidate
    
- produce output affidabili quanto le altre componenti
    

È inoltre necessario ripulire tutti i dati esterni prima dell’esecuzione, inclusi:

- file di configurazione
    
- parametri di comando
    
- nomi di file
    
- URL
    

---

### **10. Privilegi adeguati e principio del minimo privilegio**

Ogni applicazione necessita di privilegi adeguati per funzionare, ma tali privilegi devono essere limitati al minimo necessario.

Il principio del minimo privilegio implica, ad esempio:

- non aprire file in scrittura se serve solo la lettura
    
- utilizzare group access o ACL quando necessario
    

Questo principio riduce drasticamente l’impatto di eventuali compromissioni.

---

### **11. Test delle azioni rispetto alla policy — Mediazione completa**

Il principio della **mediazione completa** stabilisce che ogni azione deve essere verificata rispetto alla policy di sicurezza.

La verifica deve essere effettuata continuamente, non solo all’inizio dell’esecuzione.

Questo garantisce che le decisioni momento per momento siano coerenti con la configurazione aggiornata del sistema.

L’esempio fornito riguarda applicazioni di e-commerce con carrello della spesa.

---

### **12. Offuscamento delle informazioni**

Il principio del **non offuscamento** afferma che nascondere il funzionamento interno di un componente non è una strategia di sicurezza affidabile.

La sicurezza non deve basarsi sulla segretezza del funzionamento (security through obscurity).

Tuttavia, l’offuscamento può avere un ruolo secondario come tecnica di difesa aggiuntiva per confondere gli attaccanti.

---

### **13. Mantenere lo stato minimale (Minimal Retained State)**

Lo stato rappresenta le informazioni che un programma mantiene in memoria durante l’esecuzione.

Il principio del **minimal retained state** stabilisce che il sistema deve mantenere la quantità minima di stato possibile.

Mantenere troppo stato aumenta:

- complessità
    
- superficie di attacco
    
- consumo di risorse
    

La lezione cita come controesempi:

- SYN flood attacks
    
- attacchi a CGI scripts
    

Questo principio è fondamentale nella progettazione di sistemi scalabili e resistenti agli attacchi DoS.

---

### **14. Sintesi della lezione**

In questa lezione è stata introdotta una prima serie di principi guida per la progettazione di architetture sicure.

In particolare:

- l’importanza delle domande iniziali
    
- la determinazione del livello di sicurezza adeguato
    
- l’identificazione delle assunzioni
    
- il principio dell’avversario
    
- la chain of trust
    
- il minimo privilegio
    
- la mediazione completa
    
- il non offuscamento
    
- lo stato minimale
    

Questi principi costituiscono la base concettuale per la progettazione sicura e verranno approfonditi ulteriormente nelle lezioni successive.

---

### **15. Prossimi passi**

La prossima lezione continuerà l’analisi dei principi per architetture sicure.