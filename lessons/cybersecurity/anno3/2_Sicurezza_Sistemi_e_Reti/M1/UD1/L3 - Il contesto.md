
### **1. Terminologia di base**

Un **sistema** può essere inteso a diversi livelli di complessità:

- un singolo dispositivo (PC, smartphone, smartcard, ecc.);
    
- l’insieme del dispositivo più il sistema operativo e di comunicazione;
    
- il sistema completo con le applicazioni;
    
- il sistema comprensivo del personale che lo gestisce;
    
- il sistema esteso agli utenti o clienti esterni.
    

Un **soggetto** può essere:

- una **persona fisica** o **giuridica**;
    
- un **attore** tecnico o logico, come un dispositivo, un ruolo o un’entità complessa (es. _“Bob che finge di essere Alice”_).
    

Il **livello di astrazione** può variare molto:

- “La smart card di Bob che rappresenta Bob che sostituisce Alice”
    
- “Bob usa la carta di Alice in sua assenza”
    

Queste formulazioni aiutano a comprendere quanto la sicurezza dipenda dal **modello di interazione tra soggetti, ruoli e dispositivi**.

Definire il perimetro di un sistema significa quindi decidere che cosa includere nell’analisi di sicurezza. Limitarsi al solo dispositivo fisico è spesso insufficiente: bisogna considerare il software installato, le applicazioni, i canali di comunicazione verso l’esterno e i comportamenti degli utenti legittimi o esterni. Un attacco può infatti sfruttare non solo un bug interno, ma anche una connessione di rete, una configurazione errata o l’uso improprio di credenziali.

Nel caso dei soggetti, è importante distinguere tra identità reale, dispositivo usato e ruolo assunto nel sistema. Se Bob usa la smart card rubata ad Alice, il sistema può “vedere” Alice anche se l’attore fisico è Bob: la sicurezza deve quindi ragionare non solo su chi possiede un oggetto, ma su come l’identità viene autenticata e su quali assunzioni vengono fatte sull’uso corretto di quell’oggetto.

---

### **2. Concetto di rete**

Una **rete** è una configurazione di individui o entità interconnessi.  
Il **networking** indica la capacità di comunicare all’interno di un gruppo.  
Esempi di reti:

- reti di quartiere o di sorveglianza (_neighborhood watch_),
    
- reti di intelligence o spionaggio,
    
- reti televisive o di informazione,
    
- reti sociali (_social media_ come Facebook, Twitter, Instagram).
    

---

### **3. Reti di computer**

Le **reti di computer** sono la spina dorsale della società moderna.  
Si possono osservare da due prospettive:

#### **Fisica**

Una rete è un’infrastruttura **hardware** che collega dispositivi come PC, smartphone, sensori, attuatori, televisori intelligenti, lampade smart, ecc.

#### **Logica**

Dal punto di vista **software**, una rete è un sistema che consente lo **scambio di informazioni** tra applicazioni che non condividono lo stesso spazio di memoria.

Esempi di reti:

- reti domestiche (_home network_),
    
- reti aziendali o istituzionali,
    
- ISP regionali e globali,
    
- reti mobili.
    

---

### **4. Internet: crescita e numeri**

- Internet nacque nel **1969** con soli **4 host**.
    
- Nel **1983** gli host erano già **500**.
    
- Oggi ci sono **più dispositivi connessi che persone sulla Terra**.
    
- Gli utenti Internet superano i **5 miliardi** (oltre il 63% della popolazione mondiale).
    
- L’**e-commerce** genera oltre **8,1 trilioni di dollari** annui, con una proiezione del **24% del commercio mondiale entro il 2026**.
    
- Nel 2020, solo nel _Black Friday_, sono stati spesi **14 miliardi di dollari online**.
    
- L’e-learning è in rapida crescita: piattaforme come _Coursera_ hanno superato i **15 milioni di studenti**.
    

---

### **5. Internet: struttura e protocolli**

Internet è una **rete di reti**.  
Comprende numerosi ISP interconnessi e si basa su **protocolli di comunicazione standardizzati**, come:

- **TCP**, **IP**, **HTTP**, **802.11**, **Skype**, ecc.
    

Gli standard vengono definiti in documenti **RFC (Request for Comments)** dall’**IETF (Internet Engineering Task Force)**.

Internet fornisce:

- servizi di comunicazione (web, email, VoIP, social network, e-commerce, giochi, ecc.);
    
- interfacce di programmazione (API) che permettono alle applicazioni di connettersi e scambiarsi dati.
	    
La standardizzazione è ciò che permette a software, dispositivi e organizzazioni diverse di interoperare: due applicazioni possono comunicare anche se appartengono a produttori differenti, purché rispettino gli stessi protocolli. Questo vale tanto per protocolli di base come TCP/IP quanto per protocolli applicativi come HTTP, posta elettronica o servizi di messaggistica.

Questa infrastruttura abilita modelli di interazione molto diversi: relazioni tra utenti finali e fornitori di servizi, commercio elettronico tra aziende, scambi tra privati, servizi pubblici online come dichiarazioni fiscali o votazioni elettroniche. Dal punto di vista della sicurezza, ogni nuovo modello di interazione introduce nuovi attori, nuove risorse e nuovi possibili abusi.


---

### **6. Internet oggi**

#### **Aspetto fisico**

È la più grande rete di computer mai costruita, basata su protocolli di comunicazione globali per l’invio e la ricezione di messaggi tra miliardi di dispositivi.

#### **Aspetto logico**

È una struttura distribuita che fornisce servizi a un’enorme varietà di applicazioni: web, posta elettronica, VoIP, streaming, gaming, ecc.  
Internet è dunque un **mezzo universale di trasporto dei dati**, a diversi livelli di astrazione.

---

### **7. Internet delle cose (IoT) ed embedded systems**

I **sistemi embedded** (cioè integrati in dispositivi fisici) possono essere classificati in:

- **Standalone:** funzionano autonomamente.
    
- **Network-aware:** offrono funzionalità limitate accessibili dall’esterno.
    
- **Network-connected:** sono sempre connessi tramite protocolli aperti.
    
- **Network-central:** nati per operare all’interno di reti (es. smartphone, smart TV, luci intelligenti).
    
- **Fully networked:** completamente integrati nell’ecosistema digitale.
    

L’**Internet of Things (IoT)** rappresenta l’estensione di Internet al mondo fisico.  
Si stima che:

- i dispositivi IoT passeranno da **8,7 miliardi (2020)** a oltre **25 miliardi (2030)**;
    
- la **Cina** guida il mercato con oltre **3 miliardi di dispositivi** connessi.
    

L’IoT rende possibili scenari innovativi come **case, città, edifici e sanità intelligenti**.

Il valore di molti dispositivi IoT deriva proprio dall’essere raggiungibili, controllabili e integrabili con altri servizi. Sensori, attuatori, elettrodomestici, apparati medicali e dispositivi domestici possono raccogliere dati, inviarli ad applicazioni remote e reagire a comandi esterni. Questa apertura aumenta l’utilità del sistema, ma amplia anche il perimetro da proteggere.

---

### **8. Il futuro: IoT e Intelligenza Artificiale**

L’integrazione tra **IoT e AI** trasformerà profondamente la società:

- **Trasporti:** veicoli autonomi e smart mobility.
    
- **Industria:** robot industriali basati su AI.
    
- **Sanità:** diagnosi automatizzate, scoperta di nuovi farmaci, assistenti virtuali.
    
- **Educazione:** tutor virtuali e analisi emotiva tramite riconoscimento facciale.
    
- **Media e giornalismo:** produzione automatica di notizie (es. Bloomberg).
    
- **Customer service:** assistenti vocali e chatbot capaci di conversazioni realistiche.
    

Queste tecnologie aumentano la produttività, ma anche la **superficie d’attacco** e i **rischi di sicurezza**.

La combinazione tra IoT, raccolta dati e AI rende possibile prendere decisioni o produrre reazioni in tempo reale: un sistema può misurare l’ambiente, elaborare i dati e comandare automaticamente dispositivi fisici. Per questo la sicurezza non riguarda più soltanto la protezione di informazioni digitali, ma anche la prevenzione di effetti concreti su trasporti, sanità, industria e servizi pubblici.

---

### **9. Cos’è la sicurezza informatica**

La **sicurezza** consiste nel raggiungere un obiettivo anche **in presenza di un avversario**.  
Un sistema sicuro è quello che continua a funzionare come previsto nonostante i tentativi di sabotaggio.

Per definire un contesto di sicurezza servono tre elementi:

1. **Politica di sicurezza:**
    
    - regole che il sistema deve far rispettare;
        
    - es. “Solo Bob può leggere il file F”.
        
2. **Modello di minaccia (threat model):**
    
    - ipotesi su cosa può fare un attaccante (es. Alice non conosce la password ma può provare a indovinarla).
        
3. **Meccanismo di sicurezza:**
	    
    - componenti software o hardware che fanno rispettare la politica, nei limiti del modello di minaccia.
	        
Questi tre elementi devono essere coerenti. Una politica può dire “solo Bob legge il file”, ma il modello di minaccia deve specificare quali capacità ha l’avversario: può accedere fisicamente al computer? può provare password? può rubare una smart card? può inviare richieste dalla rete? Il meccanismo di sicurezza è adeguato solo rispetto a queste ipotesi: se il modello sottovaluta l’attaccante, anche un meccanismo corretto può risultare insufficiente.

> 📌 La sicurezza non si definisce in astratto: si definisce sempre rispetto a una politica, a un modello di minaccia e a meccanismi concreti che devono far rispettare quella politica.


---

### **10. Perché la sicurezza è difficile da garantire**

La sicurezza è un **obiettivo negativo**:  
deve assicurare che _nulla di indesiderato accada_, a prescindere dalle azioni dell’attaccante.

- È facile verificare che Alice **possa accedere** a un file.
    
- È molto più difficile dimostrare che **nessun altro possa farlo**.
    

Il processo di progettazione è **iterativo**:

- si identifica l’anello più debole,
    
- si corregge il modello di minaccia,
    
- si aggiornano i meccanismi di difesa.
    

La sicurezza assoluta non esiste:  
si tratta di **gestire il rischio**, bilanciando costi e benefici.

Questo processo è iterativo perché l’analisi può rivelare che il modello di minaccia era incompleto, oppure che il meccanismo scelto contiene bug. Il software è un prodotto umano, spesso molto grande e difficile da verificare interamente: una vulnerabilità scoperta dopo il rilascio può cambiare radicalmente la valutazione di sicurezza del sistema.

L’obiettivo non è assumere che tutto sia rotto e quindi rinunciare all’uso dei sistemi informatici. L’approccio corretto consiste nel capire quali benefici offre il sistema, quali informazioni o funzionalità mette a rischio e quali controlli riducono il rischio a un livello accettabile.

---

### **11. Perché i sistemi informatici sono vulnerabili**

Le reti di computer sono **sistemi di sistemi**, caratterizzati da:

- crescente complessità e interdipendenza;
    
- pressioni economiche che impongono un _time-to-market_ ridotto;
    
- molteplicità di connessioni che amplificano le vulnerabilità;
    
- lentezza nelle risposte agli incidenti e frequenti errori umani.
	    
Un dispositivo isolato, protetto fisicamente e con software limitato, può essere analizzato in modo relativamente più semplice. Una rete, invece, è immersa in un ambiente dinamico: cambiano utenti, configurazioni, servizi, dispositivi collegati e modalità di accesso. Ogni nuova connessione aumenta le interazioni possibili e rende più difficile controllare l’intero comportamento del sistema.

La pressione commerciale peggiora il problema: in mercati competitivi si tende a ridurre il tempo di sviluppo e a rilasciare rapidamente prodotti monetizzabili, rimandando test e revisioni di sicurezza. Questo lascia agli avversari più occasioni per sfruttare software non completamente verificato.

Anche la risposta agli incidenti richiede tempo: bisogna rilevare l’attacco, capirne la causa, correggere il difetto, distribuire patch e modificare configurazioni. In tutte queste fasi può intervenire l’errore umano, dall’amministratore che configura male un sistema all’organizzazione che sottovaluta o ritarda la comunicazione dell’incidente.


Esempi tipici:

- _worm outbreaks_, _botnet_, gestione manuale degli incidenti, _incident hiding_ (tentativi di occultare le violazioni).
    

---

### **12. Motivazioni degli attaccanti**

Le motivazioni alla base degli attacchi informatici sono molteplici e cambiano nel tempo:

- **Intelligence e militari (fino agli anni ’80):**  
    _security by obscurity_, guerre crittografiche.
    
- **Hacker spirit (anni ’80–2000):**  
    divertimento, competizione, notorietà (_CTF_, _Pwnie Awards_, _Chaos Computer Club_).
    
- **Cybercrime (dal 2000):**  
    guadagni economici, _phishing_, _spam_, _botnet_.
    
- **Cyberwarfare e obiettivi politici (dal 2007):**  
    Estonia, conflitti Russia–Georgia, _Stuxnet_.
    
- **Hacktivism (dal 2011):**  
    attacchi simbolici a fini ideologici o sociali (_Anonymous_, _LulzSec_).
	    
Le motivazioni si sovrappongono spesso: una botnet può essere usata per profitto, per estorsione o per attacchi distribuiti contro servizi pubblici; una vulnerabilità può essere sfruttata da criminali, apparati statali o gruppi ideologici. Per questo il difensore non può basarsi solo sull’identità dell’attaccante, ma deve valutare capacità, obiettivi e impatto potenziale.


---

### **13. Conclusione**

- La **sicurezza dei sistemi e delle reti** è un pilastro della società digitale.
    
- Garantirla è **difficile e mai definitivo**: è un processo continuo e adattivo.
    
- Gli attaccanti hanno **motivazioni diverse**, ma condividono la capacità di sfruttare le debolezze del sistema.
    
- La sicurezza è un **processo negativo**, che deve anticipare e prevenire il comportamento malevolo.
    

In sintesi:

> La sicurezza non è uno stato, ma un equilibrio dinamico tra protezione, rischio e innovazione.
