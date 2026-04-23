L’obiettivo della sezione è costruire una tassonomia chiara delle principali tipologie di minacce informatiche, mettendo in relazione per ciascuna: la _famiglia_ di attacco, le _tecniche_ o gli _esempi concreti_ e, soprattutto, i _rischi_ che comportano.

### Perché serve una classificazione

Il mondo delle cyber minacce è in continua evoluzione, e qualsiasi schema è per definizione parziale e temporaneo. Tuttavia, classificare gli attacchi permette di riconoscere pattern ricorrenti, capire i meccanismi di funzionamento e quindi progettare contromisure più mirate. È come catalogare i virus in biologia: anche se ne esistono infinite varianti, avere categorie (influenzali, batteriofagi, retrovirus…) facilita prevenzione e risposta.

### Le 5 grandi famiglie individuate

1. **Negazione di servizio (DoS/DDoS)**
    
    - Obiettivo: saturare le risorse di un sistema o bloccare un servizio.
        
    - Tecniche: attacchi diretti o distribuiti tramite botnet.
        
    - Rischi: minano la continuità operativa (business continuity) e, in casi estremi, la salute pubblica (si pensi al blocco di sistemi di controllo in centrali, ospedali o impianti idrici).
        
2. **Social Engineering**
    
    - Obiettivo: manipolare l’essere umano, forzandolo a rivelare informazioni sensibili.
        
    - Tecniche: phishing, baiting, pretexting, scareware.
        
    - Rischi: violazione della privacy, furto di dati personali e aziendali, danni economici.
        
3. **Attacchi opportunistici**
    
    - Obiettivo: sfruttare vulnerabilità comuni o ancora non corrette.
        
    - Tecniche: ransomware come _WannaCry_ o _NotPetya_.
        
    - Rischi: perdite economiche immediate, estorsione tramite cifratura dei dati.
        
    - Caratteristica: elevato grado di automazione → ritorno rapido per l’attaccante.
        
4. **Attacchi persistenti (APT – Advanced Persistent Threat)**
    
    - Obiettivo: spionaggio a lungo termine, sabotaggio mirato o impatti strategici.
        
    - Tecniche: malware complessi e risorse ingenti (es. _Stuxnet_).
        
    - Rischi: business continuity, interessi economici e anche salute pubblica.
        
    - Caratteristica: alti investimenti, spesso da parte di Stati o grandi organizzazioni.
        
5. **Attacchi legati al mondo criptovalutario**
    
    - Obiettivo: sfruttare risorse di calcolo delle vittime per generare criptovaluta.
        
    - Tecniche: cryptojacking e varianti.
        
    - Rischi: soprattutto economici (consumo di risorse, degrado prestazioni, perdite indirette).
        
    - Caratteristica: diffusione crescente, favorita dall’anonimato e dal ruolo delle crypto nei mercati _underground_.


---
### DoS e DDoS: negazione del servizio

Il **Denial of Service (DoS)** è un attacco informatico che mira a **rendere inutilizzabile un servizio online**. Il principio è semplice: un sistema informatico, come un server che ospita un sito web, ha risorse limitate (CPU, RAM, banda di rete). L’attaccante invia richieste massive o malformate in modo da saturare queste risorse. Il risultato è che il sistema non riesce più a rispondere agli utenti legittimi: il servizio risulta rallentato o completamente bloccato.

Il **Distributed Denial of Service (DDoS)** rappresenta l’evoluzione del DoS. Invece di un singolo computer, l’attaccante sfrutta una **rete di macchine compromesse** (botnet), spesso composta da migliaia di dispositivi infetti (PC, server, persino dispositivi IoT). Ognuno invia traffico verso il bersaglio, generando un flusso enorme di dati che **sommerge il server obiettivo**. Questo lo manda rapidamente in **sovraccarico**, provocando il malfunzionamento o l’interruzione completa del servizio.

---

### Differenze principali tra DoS e DDoS

- **DoS**: un solo attaccante che genera traffico contro il bersaglio; relativamente più semplice da individuare e bloccare.
    
- **DDoS**: migliaia di macchine distribuite che inviano traffico contemporaneamente; molto più difficile da rilevare e mitigare, perché il traffico proviene da sorgenti diverse e apparentemente legittime.
    

---

### Gravità e contesto legale

In Italia, nonostante le **conseguenze potenzialmente devastanti** (ad esempio blocco dei sistemi di un ospedale, di un aeroporto o di un servizio bancario online), l’attacco DoS/DDoS **non ha ancora una fattispecie specifica nel codice penale**. Viene quindi inquadrato in reati informatici più generici, pur essendo considerato a livello pratico un atto capace di mettere a rischio sia la **business continuity** delle aziende, sia la **salute pubblica** in caso di servizi critici.


---

# Social Engineering

Il **social engineering** è un tipo di attacco che non punta direttamente alle vulnerabilità tecniche dei sistemi, ma sfrutta le **debolezze psicologiche e comportamentali delle persone**. In altre parole, invece di “forzare” un software, l’attaccante inganna l’utente affinché sia lui stesso a fornire i dati sensibili o ad aprire la porta al malware.

L’idea di fondo è semplice: è molto più facile spingere una persona a rivelare una password o a cliccare un link pericoloso che tentare di violare direttamente un sistema informatico ben protetto.  
Per questo motivo, il social engineering rappresenta una delle tecniche più diffuse e al tempo stesso più insidiose, perché bypassa la tecnologia e colpisce la **fiducia, la paura o l’abitudine** dell’essere umano.

---

## Tecniche principali di Social Engineering

1. **Baiting**
    
    - Significa “adescare” la vittima con qualcosa di appetibile, ad esempio un file gratuito o una chiavetta USB lasciata intenzionalmente in un luogo pubblico.
        
    - La curiosità spinge la persona a scaricare o inserire il dispositivo, installando così un malware.
        
2. **Phishing**
    
    - È la tecnica più comune: si inviano e-mail o messaggi che imitano comunicazioni ufficiali (banche, servizi online, aziende note).
        
    - Lo scopo è spingere la vittima a inserire credenziali personali, dati bancari o altre informazioni riservate in una pagina web falsa, ma graficamente identica a quella originale.
        
3. **Pretexting**
    
    - L’attaccante costruisce un **“pretesto” credibile**: finge di essere un tecnico, un collega o un’autorità che ha bisogno di informazioni specifiche.
        
    - La vittima, convinta della legittimità della richiesta, fornisce dati che altrimenti non avrebbe mai rivelato.
        
4. **Scareware**
    
    - Qui si gioca sulla paura: l’utente riceve un falso avviso di sicurezza (“Il tuo computer è infetto!”) che lo spinge a scaricare un software apparentemente risolutivo.
        
    - In realtà, il programma è il malware stesso che compromette il sistema.
        

---

## Rischi principali

Gli attacchi di social engineering colpiscono soprattutto tre aree:

- **Privacy**: sottrazione di dati personali e sensibili.
    
- **Economia**: furto di credenziali bancarie, numeri di carte di credito, frodi economiche.
    
- **Reputazione**: danni a persone o organizzazioni, che possono perdere fiducia da parte di clienti e partner.
    

---

## Perché è così pericoloso?

La forza del social engineering sta nel fatto che **non esiste un antivirus per l’essere umano**.  
Le persone, a differenza dei sistemi, sono vulnerabili a emozioni, automatismi e trappole cognitive. La protezione quindi non passa solo dalla tecnologia, ma da **educazione, consapevolezza e formazione degli utenti**.


---
# Identità digitale
L'Identità Digitale è l'insieme dei dati e delle informazioni, o attributi, che definiscono un individuo e costituiscono la rappresentazione virtuale dell'identità reale utilizzabile durante interazioni elettroniche.

# Furto d’identità digitale

## 1) Definizione

Il **furto d’identità digitale** è l’atto con cui un soggetto si appropria, senza consenso, di dati personali o credenziali informatiche di un altro individuo, per **sostituirsi a lui** e compiere azioni online.  
È uno dei reati più diffusi nel panorama del cybercrime, perché sfrutta la vulnerabilità delle informazioni digitali e la facilità con cui possono essere copiate, cedute o sottratte.

---

## 2) Riferimenti normativi

Nel **Codice Penale italiano**, il furto d’identità digitale viene ricondotto a due norme principali:

- **Art. 494 – Sostituzione di persona**  
    Punisce chi attribuisce a sé o ad altri un falso nome, stato o qualità, con l’intento di ingannare. Applicabile anche all’ambito digitale (es. creare un profilo social falso con nome e foto di un’altra persona).
    
- **Art. 640 – Truffa informatica**  
    Punisce chi, alterando un sistema informatico o manipolando dati, procura a sé o ad altri un ingiusto profitto con altrui danno. È la norma tipica per frodi economiche online collegate al furto d’identità.
    

---

## 3) Modalità operative tipiche

- **Sostituzione diretta**: l’attaccante crea account falsi usando le generalità e i dati personali della vittima.
    
- **Frodi informatiche**: con le credenziali rubate, effettua operazioni bancarie o acquisti a nome della vittima.
    
- **Account takeover**: prende possesso di un account reale (es. e-mail, social, home banking) e lo utilizza per truffare contatti, diffondere malware o fare spionaggio.
    
- **Data breach**: sfrutta grandi violazioni di database online per ottenere in massa credenziali e dati personali, poi rivenduti nel dark web.
    

---

## 4) Impatti

- **Economici**: svuotamento conti, acquisti fraudolenti, richieste di credito a nome della vittima.
    
- **Sociali**: diffamazione, uso improprio dell’immagine o reputazione.
    
- **Legali**: la vittima può trovarsi coinvolta in indagini per atti mai commessi.
    

---

## 5) Perché è pericoloso

Il furto d’identità digitale è subdolo perché spesso la vittima **non si accorge subito** di essere stata colpita:

- può passare del tempo prima che emergano operazioni sospette,
    
- nel frattempo l’attaccante può sfruttare più volte i dati sottratti, anche rivendendoli a terzi.
    

---

👉 In sintesi: il furto d’identità digitale è la forma più diretta di manipolazione informatica della persona, perché priva l’individuo della propria identità online e lo espone a conseguenze economiche, sociali e legali.


---
# APT – Advanced Persistent Threat
![Pasted image 20250909203415.png](../../imgs/Pasted%20image%2020250909203415.png)

## 1) Definizione

Un’**APT** è un attacco informatico caratterizzato da tre elementi fondamentali:

- **Advanced** → utilizza tecniche sofisticate, spesso combinando malware custom, exploit di vulnerabilità _zero-day_ e strategie di social engineering avanzato.
    
- **Persistent** → non si tratta di un colpo rapido, ma di un’intrusione a lungo termine: l’attaccante rimane nascosto nei sistemi della vittima per settimane, mesi o anni, monitorando e raccogliendo informazioni senza farsi scoprire.
    
- **Threat** → è condotto da avversari con competenze elevate e grandi risorse economiche, spesso **gruppi sponsorizzati da Stati** o organizzazioni criminali di alto livello.
    

---

## 2) Obiettivi

Le APT non mirano a un guadagno immediato, ma a risultati di tipo **strategico**:

- **Spionaggio industriale** → sottrarre proprietà intellettuale, brevetti, know-how.
    
- **Spionaggio politico/diplomatico** → rubare informazioni su governi, negoziati, politica estera.
    
- **Sabotaggio** → alterare o danneggiare sistemi critici (es. impianti energetici, trasporti).
    
- **Pre-positioning** → inserirsi nei sistemi per poterli colpire in futuro, in caso di conflitto geopolitico.
    

---

## 3) Caratteristiche distintive

- **Risorse elevate**: richiedono team di esperti, infrastrutture dedicate e finanziamenti consistenti.
    
- **Target selezionati**: non colpiscono indiscriminatamente, ma bersagli ad alto valore.
    
- **Attacchi multi-fase**: iniziano spesso con phishing mirato (_spear-phishing_), poi sfruttano exploit e backdoor, quindi si muovono lateralmente nei sistemi compromessi.
    
- **Lunga durata**: la persistenza è il tratto più caratteristico → si privilegia la discrezione al clamore.
    

---

## 4) Esempi concreti

- **Stuxnet (2010)**: malware sviluppato congiuntamente da USA e Israele per sabotare le centrifughe nucleari iraniane. È considerato il primo esempio di vera “cyber-arma” APT.
    
- **APT28 (Fancy Bear, Russia)**: attacchi a istituzioni governative, NATO, campagne elettorali.
    
- **APT29 (Cozy Bear, Russia)**: noto per il caso SolarWinds, infiltrazioni a lungo termine in fornitori IT globali.
    
- **Hafnium (Cina)**: sfruttamento delle vulnerabilità di Microsoft Exchange nel 2021.
    

---

## 5) Rischi e impatti

- **Business continuity**: paralisi dei sistemi aziendali strategici.
    
- **Economici**: perdita di know-how, segreti industriali e vantaggi competitivi.
    
- **Politici**: esposizione di documenti riservati, crisi diplomatiche.
    
- **Sociali**: in caso di attacchi a ospedali o utility, conseguenze dirette sulla popolazione.
    

---

👉 In sintesi: le APT sono la forma più avanzata di cyberattacco, il cui scopo non è arricchirsi velocemente ma **spiare, sabotare o destabilizzare** con precisione chirurgica. Sono l’equivalente, nel mondo digitale, delle **operazioni di intelligence tradizionali**, con l’aggiunta che possono colpire da remoto, in modo invisibile e persistente.


---
# Cryptojacking

![Pasted image 20250909203518.png](../../imgs/Pasted%20image%2020250909203518.png)

## 1) Definizione

Il **cryptojacking** è una tecnica di attacco in cui l’aggressore sfrutta in modo occulto le **risorse hardware** (CPU, GPU, RAM, banda) della vittima per **minare criptovalute**.  
L’obiettivo non è rubare dati o bloccare sistemi, ma **guadagnare potenza di calcolo gratuita** a spese degli altri.

👉 È paragonabile a un “furto di energia digitale”: il criminale non colpisce direttamente la vittima con danni immediati, ma la usa come “centrale mineraria” inconsapevole.

---

## 2) Modalità operative

Il cryptojacking può avvenire in due forme principali:

- **File-based**: la vittima scarica ed esegue un malware (spesso mascherato da software legittimo o allegato di phishing) che installa un miner permanente.
    
- **In-browser**: tramite uno script nascosto in siti web compromessi o pubblicità malevole, che avvia il mining non appena l’utente visita la pagina. In questo caso non serve installare nulla: basta aprire il sito.
    

---

## 3) Effetti sulla vittima

- **Degrado prestazioni**: i computer diventano più lenti, consumano più batteria, si surriscaldano.
    
- **Costi energetici**: l’aumento di consumo elettrico può incidere sulle bollette (soprattutto su larga scala aziendale).
    
- **Rischi indiretti**: pur non mirando a dati o denaro direttamente, un sistema già compromesso da cryptojacking può diventare **porta d’ingresso per ulteriori attacchi**.
    

---

## 4) Perché è diffuso

- **Basso rischio per l’attaccante**: più discreto di ransomware o phishing, perché la vittima spesso non si accorge di nulla o sottovaluta i rallentamenti.
    
- **Guadagni potenzialmente alti**: con centinaia o migliaia di macchine infette, si crea una “mining farm” distribuita a costo zero.
    
- **Anonimato**: i proventi delle criptovalute (es. Monero, preferita per la privacy) rendono difficile rintracciare i criminali.
    

---

## 5) Difese possibili

- **Monitoraggio delle performance**: CPU/GPU costantemente alte senza motivo possono segnalare infezione.
    
- **Aggiornamenti e patch**: riducono le vulnerabilità sfruttabili.
    
- **Blocchi lato browser**: estensioni anti-mining e sistemi di filtraggio possono prevenire cryptojacking in-browser.
    
- **Soluzioni EDR/antimalware**: individuano processi anomali di mining.
    

---

👉 In sintesi: il cryptojacking è un attacco “silenzioso”, che non mira a rubare dati ma a **sfruttare la potenza di calcolo della vittima** per profitto. È insidioso perché spesso passa inosservato, ma su larga scala può generare danni economici e tecnici rilevanti.


---
# Phishing

![Pasted image 20250909203625.png](../../imgs/Pasted%20image%2020250909203625.png)
## 1) Definizione

Il **phishing** è una **truffa informatica** che sfrutta comunicazioni apparentemente legittime (e-mail, SMS, messaggi su social o chat) per indurre la vittima a:

- rivelare credenziali di accesso,
    
- inserire dati sensibili (carte di credito, codici OTP),
    
- scaricare allegati o cliccare link malevoli che installano malware.
    

Il termine richiama la metafora della “pesca”: l’attaccante lancia un’esca e aspetta che qualcuno abbocchi.

---

## 2) Modalità operative tipiche

- **Email falsificate**: messaggi che imitano perfettamente banche, servizi postali, aziende note. Spesso usano loghi reali e un linguaggio studiato.
    
- **Siti clone**: il link porta a un portale graficamente identico all’originale (home banking, e-commerce, portali istituzionali), ma gestito dall’attaccante.
    
- **Messaggi di urgenza**: frasi come “il tuo account sarà bloccato” o “pagamento sospeso” servono a spingere la vittima ad agire subito, senza riflettere.
    
- **Spear phishing**: versione mirata, studiata su misura per una vittima specifica (es. un manager), con riferimenti personalizzati che aumentano la credibilità.
    
- **Smishing e vishing**: varianti tramite SMS (smishing) o chiamate vocali (vishing), sempre con la logica dell’inganno.
    

---

## 3) Aspetti giuridici

In Italia, il phishing è **reato penale** e può rientrare in più articoli del Codice Penale:

- **Truffa** (art. 640) → per l’inganno finalizzato a un profitto economico.
    
- **Frode informatica** (art. 640-ter) → per l’uso di strumenti informatici volti a procurare un profitto illecito.
    
- **Trattamento illecito di dati** (art. 167 Codice Privacy) → per l’uso e la diffusione indebita di dati personali sottratti.
    

---

## 4) Perché funziona

Il phishing si basa su due leve psicologiche fondamentali:

- **urgenza e paura**: la vittima ha la sensazione che debba agire subito, pena la perdita di un servizio o di denaro;
    
- **fiducia e abitudine**: il messaggio appare familiare e convincente, quindi l’utente non attiva pensiero critico.
    

---

## 5) Impatti

- **Economici**: svuotamento conti correnti, acquisti non autorizzati, frodi finanziarie.
    
- **Reputazionali**: perdita di fiducia verso istituzioni o aziende impersonate.
    
- **Diffusione malware**: gli allegati di phishing spesso installano trojan o ransomware.
    

---

## 6) Difese

- **Formazione e consapevolezza**: l’utente deve imparare a riconoscere segnali sospetti (errori di grammatica, URL strani, richieste insolite).
    
- **Autenticazione forte (MFA)**: anche se la password viene rubata, l’accesso è bloccato senza il secondo fattore.
    
- **Filtri antispam e antiphishing**: strumenti che intercettano mail sospette prima che arrivino all’utente.
    
- **Controllo URL e certificati**: verificare sempre la correttezza dell’indirizzo del sito.
    

---

👉 In sintesi: il phishing non è un attacco tecnologico sofisticato, ma funziona perché sfrutta le **debolezze umane**. È la perfetta dimostrazione che nella cybersecurity “il punto più debole è l’uomo”.


---
# Ransomware

## 1) Definizione

Il **ransomware** è un tipo di malware che, una volta installato in un sistema, **blocca l’accesso ai dati o all’intero dispositivo** e chiede un **riscatto** (in inglese _ransom_) per ripristinarne la disponibilità.  
Di solito il pagamento viene richiesto in criptovalute (es. Bitcoin, Monero), per garantire l’anonimato dei criminali.

---

## 2) Modalità operative

Il ransomware segue uno schema tipico:

1. **Infezione iniziale** → spesso tramite phishing (allegati infetti, link malevoli), exploit kit o vulnerabilità non patchate.
    
2. **Esecuzione** → il malware si installa e si diffonde nella rete aziendale, spesso sfruttando condivisioni interne o vulnerabilità di protocolli.
    
3. **Cifratura** → i file vengono resi illeggibili con algoritmi crittografici forti.
    
4. **Richiesta di riscatto** → appare un messaggio che informa la vittima dell’attacco e chiede il pagamento.
    

---

## 3) Evoluzione delle strategie

- **Primo stadio**: ransomware che cifrava i file e chiedeva il riscatto (es. _CryptoLocker_).
    
- **Secondo stadio**: ransomware + furto dati → i criminali minacciano di diffondere i dati rubati se non si paga (doppia estorsione).
    
- **Oggi**: ransomware-as-a-service → piattaforme criminali che forniscono a terzi il software e l’infrastruttura per condurre attacchi, in cambio di una percentuale sul riscatto.
    

---

## 4) Impatti

- **Economici**: perdita diretta di denaro (pagamento del riscatto, mancati ricavi durante il fermo), costi di ripristino e bonifica.
    
- **Reputazionali**: perdita di fiducia da parte di clienti e partner.
    
- **Sociali**: in caso di attacchi a ospedali, utility o PA, il danno può tradursi in interruzione di servizi vitali e rischi per la salute pubblica.
    

---

## 5) Aspetti giuridici

Il ransomware costituisce reato e può essere ricondotto a più fattispecie del **Codice Penale italiano**:

- **Art. 615-ter** → accesso abusivo a sistema informatico.
    
- **Art. 615-quinquies** → diffusione di malware.
    
- **Art. 629** → estorsione.
    

---

## 6) Difese e strategie di mitigazione

- **Backup regolari** (anche offline o su cloud sicuri): l’unica difesa definitiva è poter ripristinare i dati senza pagare il riscatto.
    
- **Patch management**: mantenere i sistemi aggiornati riduce le vulnerabilità sfruttabili.
    
- **Segmentazione di rete**: per limitare la diffusione del malware all’interno di un’organizzazione.
    
- **Awareness e formazione**: il phishing è il canale principale di infezione, quindi l’utente deve essere addestrato a riconoscere segnali sospetti.
    
- **Incident Response Plan**: avere un piano di risposta rapido per isolare macchine compromesse e avviare i protocolli di emergenza.
    

---

👉 In sintesi: il ransomware rappresenta la forma più “industriale” del cybercrime, perché unisce tecniche tecniche sofisticate a un modello di business criminale collaudato. È uno degli esempi più chiari di come la cybersecurity non sia più solo una questione tecnica, ma **un problema economico, sociale e legale**.