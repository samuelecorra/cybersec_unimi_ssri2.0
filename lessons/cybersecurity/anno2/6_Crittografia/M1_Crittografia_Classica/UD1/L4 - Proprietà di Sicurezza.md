# **Lezione 4 - Proprietà di Sicurezza nei Protocolli Criptografici**

---

### **1. Introduzione**

Questa lezione introduce le principali proprietà di sicurezza che ogni protocollo crittografico può mirare a garantire. Tali obiettivi vengono raggiunti attraverso l’uso, talvolta combinato, delle primitive crittografiche presentate nella lezione precedente.

Le proprietà fondamentali considerate dalla crittografia moderna sono cinque: **confidenzialità**, **autenticazione**, **integrità**, **non ripudio**, **anonimato**.

Ogni protocollo implementa un sottoinsieme di esse, a seconda del problema da risolvere.

---

### **2. Le principali proprietà di sicurezza**

#### **2.1 Confidenzialità**

La confidenzialità (o _privacy_, _segretezza_) riguarda la protezione dei dati dall’accesso non autorizzato.

Indica che un’informazione, durante la trasmissione o quando è memorizzata, deve essere leggibile solo da chi possiede le autorizzazioni adeguate.

La confidenzialità è, in generale, la proprietà garantita dai sistemi di cifratura simmetrici e asimmetrici. È uno dei problemi storicamente centrali della crittografia: proteggere i dati trasmessi su canali insicuri.

---

#### **2.2 Autenticazione**

L’autenticazione risolve il problema di identificare con certezza l’origine di un messaggio o l’identità dell’entità con cui si sta comunicando.

Esistono tre forme principali:

##### **a) Autenticazione dell’origine del messaggio**

Permette al destinatario di essere certo che il messaggio ricevuto provenga realmente dal mittente dichiarato.

Gli _algoritmi di firma digitale_ soddisfano tipicamente questa forma di autenticazione.

##### **b) Autenticazione dell’entità**

Garantisce che durante uno scambio di messaggi si stia parlando con l’entità legittima e non con un avversario che tenta di impersonarla.

##### **c) Autenticazione temporale**

Serve a determinare con certezza i momenti di invio e ricezione.

È essenziale quando i vincoli temporali hanno valore ufficiale, ad esempio nei concorsi pubblici o nelle comunicazioni PEC, in cui è necessario attestare quando un messaggio è stato spedito e quando è stato ricevuto.

---

#### **2.3 Integrità dei dati**

L’integrità assicura che un messaggio venga ricevuto esattamente come è stato inviato, senza modifiche non autorizzate.

Per “modifica” si intende:

- alterazione del contenuto,
    
- eliminazione di parti del messaggio,
    
- creazione di nuovi contenuti,
    
- riordino dei blocchi,
    
- ritardi nella consegna,
    
- ripetizione di un messaggio precedente.


L’integrità è distinta dalla confidenzialità.

Un avversario capace può, in alcuni schemi di cifratura, _modificare_ un messaggio cifrato senza necessariamente _leggerne_ il contenuto e senza conoscere la chiave.

Il destinatario, se il protocollo non prevede un controllo d’integrità, potrebbe non accorgersene.

---

#### **2.4 Non ripudio**

La proprietà di non ripudio (o _non-repudiation_) garantisce che una parte non possa negare una determinata azione in un secondo momento.

Esistono due forme:

##### **a) Non ripudio della spedizione**

Il mittente non può negare di aver inviato il messaggio.

##### **b) Non ripudio della ricezione**

Il destinatario non può negare di aver ricevuto il messaggio.

Esempio classico: la **raccomandata**.

La firma sulla ricevuta costituisce una prova non ripudiabile della ricezione del messaggio.

---

#### **2.5 Anonimato**

L’anonimato consiste nel proteggere l’identità di chi accede a un servizio o compie una determinata azione.

Spesso si parla di **grado di anonimato**, perché garantire un anonimato assoluto è difficile.

Internet, di per sé, non offre anonimato:

ogni dispositivo ha un **indirizzo IP**, e questo può essere collegato a un individuo o a un’azienda tramite i log dell’ISP.

Inoltre, durante la navigazione, diversi dati vengono raccolti e analizzati per ricavare informazioni sulle preferenze e sul comportamento dell’utente.

L’anonimato mira a mitigare queste forme di tracciamento, proteggendo:

- l’identità dell’utente,
    
- i suoi pattern di navigazione,
    
- i metadati che il client lascia sul sistema locale,
    
- le informazioni ricavabili dall’ISP o da ascoltatori sulla rete.
    

##### **Strumenti per l’anonimato**

Numerosi strumenti permettono di aumentare il grado di anonimato:

- sistemi di anonimizzazione della posta elettronica come Remailers
    
- reti come _Crowds_ o _Anonymizer_, che rendono non determinabile l’identità del client che accede a un web server,
    
- protocolli di **autenticazione anonima**, che consentono a un utente di dimostrare di essere autorizzato senza rivelare quale degli utenti autorizzati egli sia.
    

---

### **3. Combinazione delle proprietà nei protocolli reali**

Un protocollo reale seleziona solo le proprietà necessarie per il problema da risolvere.

**Esempio: posta elettronica sicura**

Richiede generalmente:

- confidenzialità del contenuto del messaggio,
    
- integrità dei dati trasmessi,
    
- autenticazione dello scambio (identificazione delle parti),
    
- talvolta non ripudio della spedizione e/o della ricezione (come nelle raccomandate digitali).

---

### **4. Conclusione**

Queste cinque proprietà formano la base teorica per la progettazione dei protocolli crittografici.

Nelle lezioni successive verranno analizzati i meccanismi con cui tali proprietà vengono realizzate, e i modelli formali utilizzati per dimostrare la sicurezza di un protocollo.