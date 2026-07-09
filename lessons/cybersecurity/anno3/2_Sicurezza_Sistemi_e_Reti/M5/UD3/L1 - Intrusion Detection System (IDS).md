# **UD3 – IDS e IPS**

In questa unità si studiano i sistemi di **rilevazione** e **prevenzione** delle intrusioni: **Intrusion Detection System (IDS)** e **Intrusion Prevention System (IPS)**.

I firewall controllano il traffico secondo policy di attraversamento del perimetro, ma non eliminano la possibilità che un attacco riesca, che un host interno venga compromesso o che un utente autorizzato agisca in modo anomalo. Per questo servono strumenti che osservino ciò che accade nella rete e sugli host dopo, durante o immediatamente prima di una compromissione.

---

## **Lezione 1: Intrusion Detection System (IDS)**

### **1. Perché il firewall non basta**

Finora sono stati studiati i firewall come strumenti di protezione perimetrale: packet filter, stateful firewall, proxy, application gateway e configurazioni basate su DMZ.

Un firewall può bloccare o controllare traffico che attraversa il perimetro, ma non può proteggere da tutte le minacce.

Le ragioni principali sono:

- i sistemi operativi sono complessi e possono contenere vulnerabilità non ancora note;
- una configurazione sbagliata del sistema o del firewall può far passare traffico che si credeva bloccato;
- gli utenti richiedono accesso a servizi che possono essere insicuri;
- non sempre è possibile valutare la sicurezza di servizi terzi;
- password deboli e meccanismi di autenticazione fragili possono rendere inefficace il controllo degli accessi;
- utenti autorizzati possono commettere errori, violare policy o agire in modo malevolo;
- malware e phishing possono innescare compromissioni dall'interno, senza attraversare il firewall come attacco esterno classico.

> 📌 L'idea corretta non è "se configuro bene il firewall l'intrusione è impossibile", ma "anche con un buon firewall devo assumere che un'intrusione possa avvenire".

Questo cambio di prospettiva porta alla necessità di rilevare tempestivamente sintomi di compromissione, anziché affidarsi solo alla prevenzione.

---

### **2. Concetto di intrusion detection**

L'**intrusion detection** è l'attività di monitoraggio finalizzata a individuare eventi critici per la sicurezza.

Un **IDS** è un sistema che osserva traffico, log, file, comportamento degli host o sequenze di eventi per rilevare:

- accessi non autorizzati;
- uso non conforme di reti o computer;
- violazioni di privilegi da parte di utenti autorizzati;
- attività anomale rispetto al comportamento atteso;
- segnali compatibili con malware, scansioni, esfiltrazione o compromissione.

Un IDS può collaborare con firewall e altri sistemi, per esempio correlando log di connessioni, porte attraversate, eventi host-based e traffico osservato in rete.

Può anche usare agenti installati sui singoli host, così da raccogliere informazioni che un sensore puramente di rete non vedrebbe.

> 📌 Un IDS non sostituisce il firewall: lo affianca, osservando ciò che il firewall non può sapere o non può bloccare.

---

### **3. Definizione di IDS**

Un **Intrusion Detection System** è un sistema per identificare individui, servizi o processi che usano un computer o una rete:

- senza autorizzazione;
- con autorizzazione ma violando i privilegi concessi;
- in modo non conforme alle policy di sicurezza;
- con pattern comportamentali anomali rispetto agli utenti legittimi.

L'assunzione di base è che il comportamento di un intruso sia **diverso e rilevabile** rispetto a quello di un utente o processo legittimo.

Questa assunzione non è banale: se un attaccante riesce a imitare perfettamente il comportamento normale, il rilevamento diventa difficile. L'IDS funziona quando esiste qualche differenza osservabile: sequenze di comandi, traffico, tempi, file modificati, escalation di privilegi, connessioni insolite o attività fuori profilo.

> ⚠️ Un IDS rileva pattern osservabili. Se l'anomalia non lascia tracce misurabili, il sistema non ha elementi per generare un allarme.

---

### **4. Perché usare un IDS**

Un IDS serve principalmente per tre motivi.

#### **4.1. Rilevare attacchi non prevenuti**

Il primo scopo è rilevare attacchi o violazioni che non sono stati bloccati da firewall, antivirus o altri controlli.

Esempi:

- traffico malevolo consentito da una regola troppo permissiva;
- compromissione tramite credenziali valide;
- malware installato da un utente;
- abuso di un servizio esposto legittimamente;
- scansioni o movimenti laterali all'interno della LAN.

#### **4.2. Fornire diagnosi post-intrusione**

Il secondo scopo è fornire informazioni per capire:

- come è avvenuta l'intrusione;
- quali sistemi sono coinvolti;
- quali vulnerabilità sono state sfruttate;
- quali correzioni servono;
- quali policy o configurazioni devono essere riviste.

Un IDS è quindi anche uno strumento diagnostico e forense.

#### **4.3. Supportare reazioni automatiche**

In alcuni casi un IDS può attivare risposte automatiche, per esempio:

- chiudere o bloccare connessioni sospette;
- modificare regole firewall;
- aumentare il livello di logging;
- isolare un host;
- disabilitare temporaneamente un servizio;
- spegnere o mettere in quarantena una macchina sotto attacco.

> ⚠️ La reazione automatica va progettata con prudenza: un falso positivo può causare indisponibilità o blocco di traffico legittimo.

---

### **5. IDS passivi e IDS attivi**

Gli IDS possono essere classificati anche in base al modo in cui rilevano e reagiscono.

#### **5.1. IDS passivi**

Gli **IDS passivi** rilevano condizioni note senza intervenire direttamente sul sistema.

Usano tecniche come:

- firme di attacco;
- pattern matching;
- checksum crittografici;
- controllo di integrità dei file;
- confronto con database di eventi noti.

Esempio classico: strumenti come **Tripwire**, che verificano se file, configurazioni o permessi sono stati modificati rispetto a uno stato atteso.

Il limite è che firme, checksum e pattern devono essere aggiornati. Un IDS basato solo su firme è efficace contro attacchi noti, ma più debole contro attacchi nuovi o varianti non ancora modellate.

#### **5.2. IDS attivi**

Gli **IDS attivi** cercano di rilevare comportamenti anomali tramite analisi statistica, apprendimento o monitoraggio di sequenze di eventi.

Il docente distingue tre momenti logici:

- fase di **learning** o addestramento, in cui il sistema osserva il comportamento normale;
- fase di **monitoring**, in cui osserva traffico, eventi o sequenze operative;
- fase di **reaction**, in cui confronta ciò che osserva con soglie o modelli e genera allarmi o reazioni.

Questi sistemi sono più flessibili, ma difficili da tarare: se il periodo di addestramento contiene già attività anomale, il sistema può imparare un comportamento sbagliato come normale.

> 📌 Gli IDS attivi promettono di rilevare anomalie non note a priori, ma richiedono addestramento, soglie e contesto operativo ben definiti.

---

### **6. Che cosa può rilevare un IDS**

Un IDS può essere utile per osservare ciò che accade **dopo il firewall** o dentro la rete.

Può rilevare:

- scansioni interne o post-perimetro;
- traffico sospetto generato da host compromessi;
- connessioni verso server di comando e controllo;
- malware che comunica cifrando il traffico verso l'esterno;
- host interni che comunicano con destinazioni insolite;
- abuso di FTP anonimo o altri servizi;
- traffico riconducibile a bot, trojan o worm;
- tentativi di attacco generati dalla propria rete verso terzi;
- attività anomale che non transitano dal firewall perimetrale;
- eventi ricostruibili anche a distanza di tempo tramite log e tracce storiche.

Un aspetto spesso trascurato è il traffico **in uscita**. Se un host interno viene compromesso e partecipa a un DDoS, a scansioni o a spam, l'organizzazione può finire in blacklist o subire ban sugli indirizzi IP.

> 📌 Un IDS serve anche a capire se la propria rete è diventata sorgente di attacchi verso altri.

---

### **7. Che cosa non può fare un IDS**

Un IDS, in senso stretto, non è un sistema di prevenzione.

Non può:

- garantire che l'intrusione non avvenga;
- impedire da solo un Denial of Service;
- sostituire un firewall;
- sostituire hardening, patching o autenticazione forte;
- proteggere un sistema senza generare solo rilevazione o allarme.

Può osservare che sta accadendo qualcosa, ma non è questo a renderlo automaticamente un sistema di protezione.

La distinzione è didatticamente importante:

- il firewall è un sistema di protezione/prevenzione del traffico;
- l'IDS è un sistema di rilevazione e monitoraggio;
- l'IPS, che sarà trattato dopo, aggiunge una componente di prevenzione o blocco automatico.

> ⚠️ Chiamare "protezione" ciò che è solo rilevazione è pericoloso: un allarme non blocca l'attacco se nessuno lo gestisce o se non esiste una risposta automatica affidabile.

---

### **8. Esempio: compromissione dell'account di root**

Il docente usa la compromissione dell'account di root per distinguere attacchi remoti e locali.

#### **8.1. Compromissione remota**

Un attacco remoto può avvenire tramite un servizio vulnerabile esposto verso l'esterno.

In questo caso la sicurezza perimetrale può essere utile:

- il firewall può bloccare l'accesso al servizio;
- può consentirlo solo da origini autorizzate;
- può limitare porte e protocolli;
- può ridurre la superficie esposta.

#### **8.2. Compromissione locale**

Un attacco locale può avvenire quando un utente ottiene un accesso non privilegiato e poi sfrutta un bug locale per ottenere privilegi di root.

Qui il firewall perimetrale non aiuta, perché il vettore non attraversa il perimetro: l'attacco avviene già sulla macchina.

Le contromisure principali sono:

- hardening del sistema;
- patching;
- configurazione corretta dei privilegi;
- separazione dei ruoli;
- monitoraggio locale;
- IDS host-based.

Un IDS può rilevare segnali come:

- escalation di privilegi inattesa;
- uso anomalo di comandi amministrativi;
- accesso in orari o modalità non usuali;
- modifiche sospette a file di sistema;
- alterazioni dei log;
- comportamento diverso dal profilo tipico dell'utente.

> 📌 Gli attacchi locali mostrano il limite della difesa perimetrale: se il problema nasce dentro l'host, serve osservabilità sull'host.

---

### **9. HIDS e NIDS**

Dal punto di vista topologico si distinguono due categorie principali.

#### **9.1. Host-Based IDS (HIDS)**

Un **HIDS** controlla un singolo host.

Può analizzare:

- log del sistema operativo;
- log applicativi;
- file di configurazione;
- permessi;
- processi;
- attività degli utenti;
- modifiche al filesystem;
- eventi di autenticazione;
- tentativi di escalation.

È particolarmente utile su server critici, endpoint sensibili e macchine in cui il traffico cifrato impedisce a un NIDS di vedere il contenuto applicativo.

#### **9.2. Network-Based IDS (NIDS)**

Un **NIDS** monitora traffico di rete, tipicamente tramite sniffing, porte mirror, TAP o punti strategici di osservazione.

Può essere posizionato:

- dentro la LAN;
- in DMZ;
- appena fuori o appena dentro il firewall;
- in segmenti critici della rete.

Il suo obiettivo è rilevare tentativi di compromissione, accessi anomali, pattern di traffico sospetti e firme di attacco osservabili sui pacchetti o sui flussi.

> 📌 HIDS e NIDS vedono cose diverse: l'HIDS vede il comportamento dell'host, il NIDS vede il traffico nel segmento di rete osservato.

---

### **10. Valutare la qualità di un IDS**

Poiché un IDS è un classificatore di eventi, va valutato con metriche di classificazione.

Le due grandezze introdotte dal docente sono:

- **accuratezza**, cioè il rapporto tra allarmi corretti e totale degli allarmi;
- **completezza**, cioè il rapporto tra allarmi corretti e numero reale di intrusioni.

In forma intuitiva:

$$
\text{Accuratezza} = \frac{\text{allarmi corretti}}{\text{allarmi totali}}
$$

$$
\text{Completezza} = \frac{\text{allarmi corretti}}{\text{intrusioni reali}}
$$

Un allarme è corretto quando l'IDS genera un allarme e l'intrusione esiste davvero.

---

### **11. Matrice di classificazione**

Per ragionare sulla qualità dell'IDS si usa la logica di una matrice di classificazione.

![](imgs/Pasted%20image%2020260709175735.png)

I casi sono quattro:

- **vero positivo (TP)**: c'è un attacco e l'IDS genera allarme;
- **falso positivo (FP)**: non c'è un attacco, ma l'IDS genera allarme;
- **falso negativo (FN)**: c'è un attacco, ma l'IDS non genera allarme;
- **vero negativo (TN)**: non c'è un attacco e l'IDS non genera allarme.

Il problema operativo è bilanciare falsi positivi e falsi negativi.

Un falso negativo è pericoloso perché un attacco passa inosservato. Un falso positivo è costoso perché consuma tempo, genera allarmismo e può far perdere fiducia nel sistema.

> ⚠️ Un IDS con troppi falsi positivi può diventare inutilizzato: gli operatori iniziano a ignorare gli allarmi, inclusi quelli realmente importanti.

---

### **12. Trade-off tra falsi positivi e falsi negativi**

Falsi positivi e falsi negativi sono spesso inversamente correlati.

Se il sistema viene tarato per essere molto sensibile, tenderà a rilevare più attacchi veri, ma produrrà anche più falsi allarmi.

Se il sistema viene tarato per generare pochi allarmi, ridurrà i falsi positivi, ma rischierà di perdere attacchi reali.

Questo è un problema comune a molti classificatori.

> 📌 La domanda pratica non è "come elimino tutti gli errori?", ma "quale tipo di errore è più accettabile nel mio contesto operativo?".

In un ambiente molto critico può essere accettabile gestire più falsi positivi pur di non perdere attacchi. In un ambiente con pochi operatori, troppi allarmi possono saturare il SOC e produrre disattenzione.

---

### **13. Precision, recall, specificità e FPR**

Il docente collega la valutazione degli IDS alle metriche classiche dei classificatori.

![](imgs/Pasted%20image%2020260709175805.png)

Le grandezze principali sono:

$$
\text{Precision} = \frac{TP}{TP + FP}
$$

$$
\text{Recall} = \text{Sensibilità} = \frac{TP}{TP + FN}
$$

$$
\text{Specificità} = \frac{TN}{TN + FP}
$$

$$
FPR = 1 - \text{Specificità} = \frac{FP}{TN + FP}
$$

L'accuratezza globale, nella formulazione standard dei classificatori, è:

$$
\text{Accuracy} = \frac{TP + TN}{TP + TN + FP + FN}
$$

La **precision** risponde alla domanda: tra gli allarmi generati, quanti sono davvero attacchi?

La **recall** risponde alla domanda: tra gli attacchi reali, quanti sono stati rilevati?

> 📌 Per un IDS non basta dire "rileva molti attacchi": bisogna sapere quanti allarmi sono affidabili e quanti attacchi vengono persi.

---

### **14. ROC e AUC**

La relazione tra **True Positive Rate** e **False Positive Rate** viene rappresentata tramite la curva **ROC**.

![](imgs/Pasted%20image%2020260709175820.png)

Una curva ideale separerebbe perfettamente attacchi e non-attacchi: tutti i veri positivi sarebbero rilevati senza falsi positivi. In quel caso l'area sotto la curva sarebbe pari a `1`.

Nei sistemi reali, invece, le distribuzioni di traffico normale e attacco si sovrappongono: compaiono falsi positivi e falsi negativi, e la curva ROC mostra il compromesso ottenibile variando la soglia di decisione.

L'**AUC** misura quanto la curva si avvicina al comportamento ideale.

> 📌 Più l'AUC è alta, più il classificatore distingue bene tra traffico normale e traffico malevolo; ma nessun IDS reale elimina completamente FP e FN.

![](imgs/Pasted%20image%2020260709175905.png)

---

### **15. Probabilità a priori e valore degli allarmi**

Il docente sottolinea un punto spesso trascurato: il valore pratico di un allarme dipende anche dalla probabilità a priori che un attacco sia effettivamente in corso.

Se un IDS genera migliaia di allarmi al giorno in un contesto in cui gli attacchi reali sono pochi, la probabilità che un singolo allarme sia davvero rilevante può essere bassa, anche se il sistema sembra tecnicamente sensibile.

Questo produce il problema dell'**alert fatigue**:

- troppi allarmi;
- troppi falsi positivi;
- operatori saturi;
- tendenza a ignorare notifiche ripetitive;
- rischio di perdere l'allarme importante.

> ⚠️ La qualità di un IDS non si misura solo con la teoria del classificatore: si misura anche con la capacità degli operatori di gestire utilmente gli allarmi generati.

---

### **16. Strumenti di supporto: SIV e LFM**

Nel contesto degli IDS host-based, il docente cita due famiglie di strumenti.

#### **16.1. System Integrity Verifier (SIV)**

Un **System Integrity Verifier** controlla filesystem e configurazioni per rilevare modifiche non autorizzate.

Può rilevare:

- modifiche a file di sistema;
- cambiamenti nei registri;
- alterazioni di configurazioni;
- cambiamenti nei privilegi;
- modifiche a file critici.

Esempio: **Tripwire**.

#### **16.2. Log File Monitor (LFM)**

Un **Log File Monitor** analizza i log di sistemi operativi e applicazioni per riconoscere pattern associati ad attacchi o anomalie.

Può cercare:

- sequenze di login falliti;
- accessi fuori orario;
- errori ripetuti;
- escalation sospette;
- pattern noti nei log applicativi.

Esempio: **Swatch**.

> 📌 SIV e LFM non sostituiscono l'IDS: sono strumenti che rafforzano la capacità di rilevare compromissioni e anomalie sugli host.

---

### **17. Limiti pratici della taratura**

Un IDS mal posizionato, mal configurato o mal tarato può non portare vantaggi.

Può generare:

- troppi falsi positivi;
- troppi falsi negativi;
- allarmi non azionabili;
- carico computazionale eccessivo;
- disattenzione degli operatori;
- falsa sensazione di sicurezza.

La taratura richiede quindi:

- conoscenza della rete;
- conoscenza dei servizi;
- baseline del comportamento normale;
- aggiornamento delle firme;
- valutazione continua delle soglie;
- collocazione corretta dei sensori;
- processi di risposta agli allarmi.

> ⚠️ Un IDS installato "tanto per averlo" rischia di diventare rumore operativo. Deve essere configurato, mantenuto e integrato nei processi di risposta.

---

### **18. Conclusione**

Gli IDS sono fondamentali perché coprono scenari che la sola difesa perimetrale non può arrestare:

- compromissioni locali;
- attività interne malevole o inconsapevoli;
- malware già presente nella LAN;
- traffico anomalo tra host interni;
- escalation di privilegi;
- eventi rilevabili solo tramite log o integrità del filesystem.

Il loro ruolo è la **rilevazione**, non la prevenzione pura.

Per essere utili devono essere:

- posizionati correttamente;
- tarati sul contesto;
- aggiornati;
- integrati con log, firewall e processi operativi;
- valutati rispetto a falsi positivi, falsi negativi, precision, recall e AUC.

> ✅ Un IDS efficace non rende impossibile l'intrusione: rende più difficile che l'intrusione passi inosservata.
