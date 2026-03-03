# **Lezione 1: La valutazione del rischio**

### 1. Introduzione alla valutazione del rischio nella sicurezza informatica

La valutazione del rischio rappresenta uno degli elementi centrali nella gestione della sicurezza dei sistemi informativi. Essa consiste nell’identificazione, analisi e controllo dei rischi che possono compromettere asset, dati e infrastrutture tecnologiche di un’organizzazione. L’obiettivo principale non è eliminare completamente il rischio, ma ridurlo a livelli accettabili e gestibili.

Un approccio efficace alla sicurezza non può essere reattivo, ovvero basato sulla risposta agli incidenti dopo che si sono verificati, ma deve essere preventivo. Pianificare e gestire i rischi in condizioni di tranquillità consente di ridurre costi, tempi di intervento e impatti negativi sull’organizzazione.

---

### 2. Malware: tipologie e caratteristiche

Una delle principali fonti di rischio per i sistemi informativi è rappresentata dal malware, ovvero software progettato con lo scopo di causare danni ai dati o ai sistemi sui quali viene eseguito.

Tra le principali tipologie di malware si distinguono:

Il ransomware, che richiede il pagamento di un riscatto per ripristinare il sistema o recuperare i dati compromessi. Una forma particolarmente diffusa è il cryptolocker, che cifra i dati della vittima richiedendo un pagamento per la decifratura.

Il virus è un malware capace di infettare altri sistemi attraverso file eseguibili e di autoreplicarsi. Anche quando non causa danni diretti, comporta comunque consumo di risorse hardware come memoria, CPU e spazio disco.

Il worm è un malware che si autoreplica senza necessità di eseguibili per diffondersi.

Il trojan è un malware nascosto all’interno di programmi apparentemente legittimi, che consente l’apertura di una backdoor per il controllo remoto del sistema. I sistemi compromessi possono diventare bot o zombie, spesso utilizzati per creare botnet.

Il dialer è un malware che stabilisce connessioni telefoniche senza il consenso dell’utente, anche se esistono dialer legittimi.

Lo spyware raccoglie informazioni sull’attività dell’utente senza autorizzazione, spesso per finalità di marketing o attacchi mirati.

Il termine virus viene spesso utilizzato impropriamente come sinonimo generale di malware, anche se tecnicamente rappresenta solo una specifica categoria.

---

### 3. Attacchi DoS e DDoS

Gli attacchi Denial of Service (DoS) sono attacchi informatici che mirano a rendere indisponibile un sistema o un servizio, ad esempio un sito web, saturando le risorse disponibili fino a impedirne il funzionamento.

Gli attacchi Distributed Denial of Service (DDoS) utilizzano lo stesso principio ma vengono realizzati attraverso numerose macchine attaccanti che costituiscono una botnet. Queste macchine, spesso compromesse tramite trojan, possono essere attivate simultaneamente per eseguire l’attacco.

La disponibilità rappresenta uno dei pilastri della sicurezza informatica e questi attacchi ne compromettono direttamente il funzionamento.

---

### 4. Phishing, smishing e ingegneria sociale

Il phishing è una tecnica basata sull’ingegneria sociale che induce la vittima a fornire informazioni sensibili, come credenziali di accesso o dati finanziari.

L’attacco può avvenire tramite email fraudolente che imitano comunicazioni ufficiali, contatti telefonici o messaggi SMS (smishing).

L’elemento centrale del phishing è la manipolazione psicologica della vittima, più che lo sfruttamento di vulnerabilità tecniche.

---

### 5. Pharming e spoofing

Il pharming rappresenta un’evoluzione del phishing e consiste nel reindirizzare la vittima verso un sito web clone tramite manipolazione del sistema DNS. L’utente inserisce inconsapevolmente dati personali su un sito fraudolento credendo di essere su quello legittimo.

Lo spoofing è una tecnica che prevede la falsificazione dell’identità dell’attaccante per ottenere accesso a servizi o risorse sotto falsa identità.

Entrambe le tecniche evidenziano come la sicurezza dipenda anche dall’autenticità delle comunicazioni e dall’affidabilità delle infrastrutture di rete.

---

### 6. Tecniche di raccolta informazioni: port scanning e sniffing

Il port scanning è una tecnica utilizzata per identificare quali porte di un sistema sono aperte e quali servizi sono in esecuzione. Può avere finalità legittime, come la verifica dei servizi disponibili, oppure essere utilizzata come fase preparatoria per attacchi informatici.

Lo sniffing consiste nell’intercettazione passiva dei dati che transitano in una rete. Gli strumenti di sniffing analizzano i pacchetti e ricostruiscono il flusso di comunicazione, salvo nel caso di protocolli cifrati.

Queste tecniche dimostrano come le attività di raccolta informazioni possano essere sia strumenti difensivi sia strumenti offensivi.

---

### 7. Approcci alla gestione degli incidenti informatici

Un approccio improvvisato alla gestione degli incidenti consiste nella ricerca di soluzioni rapide dopo il verificarsi dell’evento, spesso senza una reale strategia. Questo approccio porta frequentemente a costi elevati, tempi lunghi e inefficienza operativa.

Un approccio organizzato prevede invece la preparazione preventiva, la definizione di policy, l’integrazione degli incidenti nelle attività aziendali e la gestione dei rischi prima che si verifichino problemi.

La prevenzione riduce significativamente tempi e costi e consente di individuare partner esterni e soluzioni assicurative in modo pianificato.

Il confronto tra approccio improvvisato e organizzato evidenzia l’importanza della gestione preventiva del rischio.

---

### 8. Valutazioni operative nella gestione del rischio

La valutazione del rischio richiede l’analisi di numerosi aspetti organizzativi e tecnici.

Tra le valutazioni fondamentali rientrano:

- Modalità di esecuzione e verifica dei backup
    
- Identificazione degli asset da proteggere
    
- Analisi dei rischi fisici e logici
    
- Monitoraggio del traffico di rete
    
- Identificazione dei servizi esposti
    
- Controllo delle comunicazioni e dei permessi
    
- Prestazioni della rete
    
- Sistemi di posta elettronica e misure di sicurezza
    

La valutazione del rischio dipende dal valore degli asset e dalla soglia di rischio tollerabile. Quando il rischio supera tale soglia, sono necessari interventi di mitigazione.

Questo concetto è rappresentato dalla cosiddetta parabola del rischio tollerabile, che distingue tra rischio eccessivo e rischio adeguatamente controllato.

---

### 9. Livelli da valutare e controllare

La gestione del rischio deve considerare diversi livelli dell’organizzazione.

Tra questi vi sono:

- Sicurezza fisica e logica
    
- Dati
    
- Applicazioni
    
- Dispositivi
    
- Rete
    
- Perimetro e locali
    
- Documentazione non digitale
    
- Persone
    
- Know-how
    
- Processi
    
- Policy
    

Questa visione multidimensionale evidenzia come la sicurezza informatica non sia solo un problema tecnologico ma anche organizzativo e umano.

---

### 10. Attività preventive e gestione delle vulnerabilità

Le attività preventive iniziano spesso con azioni organizzative di base, definite attività soft.

Queste includono:

- Definizione delle policy
    
- Organizzazione degli staff
    
- Formazione del personale
    
- Valutazioni periodiche delle vulnerabilità
    
- Test di social engineering
    
- Aggiornamento dei sistemi
    
- Gestione sicura delle password
    
- Monitoraggio dei comportamenti normali dei sistemi
    
- Analisi dei log
    
- Verifica dei backup e dei test di ripristino
    

L’obiettivo è creare un ambiente controllato e prevedibile, nel quale eventuali anomalie possano essere individuate rapidamente.

---

### 11. Il CSIRT (Computer Security Incident Response Team)

Il CSIRT è il gruppo di persone responsabile della risposta agli incidenti informatici. Deve essere costituito prima che si verifichino incidenti e deve avere competenze, esperienza, responsabilità e autorità chiaramente definite.

Il CSIRT svolge un ruolo centrale nella gestione dei sistemi informativi e nella riduzione dei rischi, monitorando vulnerabilità e promuovendo la consapevolezza della sicurezza.

Esso rappresenta inoltre il punto di riferimento per audit, autorità giudiziarie e gestione degli incidenti.

Tra le competenze richieste rientrano conoscenze tecniche, competenze giuridiche, capacità relazionali e disponibilità di strumenti adeguati.

Il CSIRT deve mantenere contatti con figure legali, forze dell’ordine, provider Internet e media, oltre a gestire dati sensibili anche in contesti offline, garantendone la cifratura quando necessario.

---

### 12. Struttura del team CSIRT e ruoli

La struttura del CSIRT prevede diversi ruoli specifici.

Il team leader organizza il lavoro ed è responsabile del team.

L’incident leader gestisce uno specifico incidente e coordina la squadra coinvolta.

Gli incaricati alla gestione collaborano alle attività operative.

I membri associati partecipano alla gestione pur non essendo parte stabile del team.

Il referente IT coordina le comunicazioni con il dipartimento tecnico.

Il referente legale gestisce gli aspetti giuridici e le comunicazioni con l’ufficio legale.

Il referente di informatica forense si occupa della raccolta delle prove.

Il referente delle pubbliche relazioni gestisce la comunicazione verso l’esterno.

Il management aziendale valuta l’impatto dell’incidente e l’operato del team.

Lo standard definisce anche le responsabilità di ciascun ruolo nelle diverse fasi operative, come valutazione iniziale, risposta, raccolta evidenze, comunicazioni e soluzione definitiva.

Questa struttura organizzativa garantisce chiarezza e coordinamento durante la gestione degli incidenti.

---

### 13. Sintesi finale

La lezione ha introdotto il concetto di valutazione del rischio nella sicurezza informatica, analizzando le principali minacce, le tecniche di attacco e gli approcci organizzativi alla gestione degli incidenti.

È stata evidenziata l’importanza della prevenzione, della pianificazione e della definizione dei ruoli, oltre alla centralità del CSIRT nella risposta agli incidenti.

La gestione del rischio richiede una visione integrata che includa tecnologia, organizzazione e fattore umano, con l’obiettivo di mantenere il rischio entro livelli tollerabili e controllati.