Questo modulo conclude il percorso di **Computer Forensics** con un approccio pratico e interdisciplinare: la **simulazione dibattimentale**.  
Dopo aver studiato le tecniche di acquisizione e analisi dei dati digitali — su disco, rete, dispositivi mobili ed embedded — lo studente è chiamato a **mettere in pratica le metodologie apprese** riproducendo le dinamiche di un vero **processo penale con prova digitale**.

L’attività mira a far comprendere le **criticità metodologiche e giuridiche** che emergono nel passaggio dal laboratorio all’aula di tribunale: come viene presentata la prova informatica, come si redige una perizia, come si difende la catena di custodia e come si argomentano le evidenze tecniche davanti al giudice.

> La simulazione dibattimentale è il punto di incontro tra **tecnica e diritto**: l’occasione per dimostrare che una prova digitale non vale per ciò che mostra, ma per **come è stata ottenuta, documentata e difesa**.


---

# **UD1 – L’informatico forense e le parti del processo**

### **Introduzione**

Questa unità didattica introduce il **ruolo professionale dell’informatico forense** all’interno del procedimento giudiziario, chiarendo la sua posizione rispetto alle **parti del processo**: giudice, pubblico ministero, difesa, periti e consulenti tecnici.

L’obiettivo è comprendere **come le competenze tecniche si integrino con la dimensione giuridica**, e in che modo l’informatico forense diventi il ponte tra il linguaggio della tecnologia e quello del diritto.  
Viene analizzata la distinzione tra **perizia** (atto d’ufficio disposto dal giudice) e **consulenza tecnica di parte**, così come le responsabilità deontologiche e probatorie del consulente.

> L’informatico forense non è solo un tecnico, ma un **interprete della verità digitale**: la sua efficacia dipende dalla capacità di tradurre un dato binario in un **fatto giuridicamente rilevante**.


---

## **Lezione 1: Un caso di alibi informatico (Parte I)**

### **1. Introduzione**

Questa lezione apre la simulazione dibattimentale con un **caso reale di alibi informatico**, volto a illustrare il ruolo dell’**informatica forense** nel processo penale e il rapporto tra le parti coinvolte: **pubblico ministero, difesa, parte civile e giudice**.  
L’obiettivo è mostrare come la **prova digitale** — se correttamente acquisita, analizzata e interpretata — possa **stabilire la compatibilità o incompatibilità di un alibi** con i dati oggettivi raccolti.

Il caso analizzato riguarda il **personal computer di Paolo Tizio**, oggetto di sequestro e successiva perizia, al fine di stabilire **gli orari in cui il PC fu effettivamente utilizzato** in prossimità del suo omicidio.

---

### **2. I quesiti posti dal giudice**

Al consulente tecnico d’ufficio (CTU) vengono affidati tre quesiti fondamentali:

1. **Analizzare forensicamente il personal computer di Paolo Tizio**, per determinare gli orari di utilizzo e le operazioni effettuate.
    
2. **Localizzare il dispositivo di telefonia mobile** dell’imputato e **ricostruire le aree di copertura** delle celle a cui si è collegato.
    
3. **Integrare i risultati** delle due analisi — informatica e telefonica — per **costruire una sequenza spazio-temporale unitaria** degli eventi.
    

> Si tratta di un esempio emblematico in cui la tecnologia diventa strumento diretto per la **ricostruzione del comportamento umano**.

---

### **3. Analisi forense del computer**

L’analisi del PC di Tizio si è concentrata sulla **timeline forense**, ovvero il registro cronologico che documenta, in ordine crescente di tempo, **tutte le operazioni di creazione, modifica e lettura dei file** presenti sul sistema.  
Questa funzione consente di ricostruire con precisione **l’attività dell’utente nel tempo**, fornendo un vero e proprio **giornale di bordo digitale**.

---

### **4. Il trattamento del reperto informatico**

Nel verbale della Polizia Giudiziaria si legge:

> “Prima di lasciare i locali si è provveduto allo spegnimento del personal computer collocato nella stanza interessata dall’episodio delittuoso.  
> Detto computer recava aperti il programma con il file _Parere bilanci di previsione 2003.doc_ e la cartella _Comune di Alberona_.  
> All’atto dello spegnimento, mediante il comando _Arresta sistema_, il programma andava in crash (‘Impossibile chiudere...’) e veniva selezionata l’opzione _Termina adesso_.  
> Il sistema si arrestava mentre l’orologio indicava le 22:19.”

Questo intervento, sebbene in buona fede, **ha alterato il contenuto di sistema** e **compromesso parzialmente la scena digitale**: l’arresto forzato del programma Word ha modificato file temporanei e log di sistema, cancellando tracce preziose.

---

### **5. Conseguenze dell’errore procedurale**

L’analisi successiva ha evidenziato che:

- **282 file** presentavano data di **ultimo accesso successiva alle 14:00** del 13 maggio 2003;
    
- **91 file** risultavano **modificati dopo le 14:00** dello stesso giorno.
    

Questi eventi hanno reso impossibile determinare con esattezza le operazioni effettivamente svolte da Paolo Tizio nelle ore precedenti al delitto.

> In altre parole, lo spegnimento improprio del computer ha **inquinato la prova digitale**: un esempio classico di come la mancanza di competenze tecniche nella fase di sequestro possa compromettere un’intera indagine.

---

### **6. Ricostruzione della timeline**

Dall’analisi forense dei file sono stati identificati alcuni eventi chiave:

|Percorso file|Operazione|Data e ora|
|---|---|---|
|`Documents and Settings\cd\Dati applicazioni\Microsoft\Word\Salvataggio automatico di Parere bilancio Previsione 2003.asd`|Salvataggio automatico|13/05/2003 – 13:28|
|`Impostazioni locali\Temp\~WRF0001.tmp`|File temporaneo Word|13/05/2003 – 13:29|
|`WINDOWS\PCHEALTH\HELPCTR\DataColl\CollectedData_6427.xml`|Raccolta dati sistema|13/05/2003 – 13:33|
|`[orphan]\RP281\RestorePointSize`|Punto di ripristino|13/05/2003 – 13:48|

Tutti questi dati confermano che il computer **è stato utilizzato con continuità fino alle 13:46 circa**, orario immediatamente **anteriore all’omicidio** di Tizio.

---

### **7. Attendibilità dell’orario**

La Polizia Postale ha verificato la precisione dell’orologio di sistema, riscontrando un **anticipo medio di 16,5 secondi** rispetto all’orario reale.  
Pertanto, gli orari indicati nei file devono essere **corretti sottraendo 16 secondi**, per ottenere un allineamento cronologico attendibile.

Questo passaggio è fondamentale in ambito forense, poiché anche **scostamenti di pochi secondi** possono alterare la compatibilità di un alibi con altri eventi registrati (ad esempio, una chiamata o un accesso di rete).

---

### **8. Conclusioni della prima parte**

L’analisi della timeline consente di affermare che:

- il **personal computer di Paolo Tizio** è stato utilizzato fino alle **13:46** del 13 maggio 2003;
    
- ciò avvenne **prima dell’orario stimato del delitto**, rendendo il dato compatibile con un **alibi parziale**;
    
- tuttavia, l’intervento della Polizia Giudiziaria **ha compromesso la completezza delle evidenze**, impedendo di ricostruire l’attività successiva delle ore 14:00–22:00.
    

> Questo caso insegna che **una prova digitale è fragile quanto precisa**: basta un gesto errato — come un click su “Termina adesso” — per cancellare definitivamente la verità di un sistema informatico.

---

### **9. Sintesi finale**

|Aspetto|Elemento emerso|
|---|---|
|**Tipo di caso**|Alibi informatico su PC dell’imputato|
|**Quesiti tecnici**|Analisi file system + localizzazione cella|
|**Errore procedurale**|Spegnimento manuale con crash del programma|
|**Risultati**|Attività sul PC fino alle 13:46 (prima del delitto)|
|**Scostamento orario**|16,5 secondi in anticipo|
|**Valore forense**|Parziale, a causa di contaminazione del reperto|

---

> In questa prima parte, l’attenzione è posta sul **metodo di acquisizione e validazione del dato informatico**.  
> Nella seconda parte del caso, l’analisi si estenderà alla **localizzazione del cellulare**, per integrare la dimensione **spaziale** con quella **temporale**, ricostruendo la verità digitale nella sua totalità.


---

## **Lezione 2: Un caso di alibi informatico (Parte II)**

### **1. Introduzione**

Questa seconda parte del caso di **alibi informatico** completa l’analisi avviata con l’esame del computer di Paolo Tizio, introducendo ora la **localizzazione del suo telefono cellulare** e la ricostruzione della **sequenza spazio-temporale** degli eventi.

L’obiettivo è comprendere **come la correlazione tra i dati informatici e quelli telefonici** consenta di valutare l’attendibilità di un alibi in sede dibattimentale.  
In questo caso, si ricostruisce **il percorso del dispositivo mobile** nelle ore immediatamente successive all’omicidio, mediante l’analisi delle **celle BTS (Base Transceiver Station)**.

---

### **2. Le parti nel processo civile**

Prima di addentrarsi nell’analisi tecnica, il prof. Caccavella richiama brevemente le **parti processuali** coinvolte in un procedimento civile:

- **Parte attrice**, che promuove l’azione giudiziaria.
    
- **Parte convenuta**, che subisce la domanda.
    
- **Giudice**, che decide sulla controversia.
    

In ambito penale, accanto a queste figure troviamo anche:

- il **Pubblico Ministero**, titolare dell’azione penale;
    
- il **Consulente Tecnico d’Ufficio (CTU)**, nominato dal giudice;
    
- i **Consulenti Tecnici di Parte (CTP)**, incaricati da accusa o difesa.
    

> L’informatico forense può agire in entrambe le sfere, ma la **sua responsabilità è sempre verso la verità tecnica**.

---

### **3. Localizzazione del dispositivo di telefonia mobile**

L’indagine prosegue con la **ricostruzione del movimento del cellulare** intestato all’imputato, sulla base dei tabulati forniti dal gestore e delle celle radiomobili (BTS) a cui il dispositivo si è connesso.

#### **Struttura della rete GSM**

Per interpretare i dati, è necessario ricordare gli elementi fondamentali del sistema:

- **MSC (Mobile Services Switching Center):** centro di commutazione dei servizi mobili;
    
- **BSS (Base Station Subsystem):** sottosistema radio della rete;
    
- **BSC (Base Station Controller):** controllore che coordina più stazioni radio;
    
- **BTS (Base Transceiver Station):** l’antenna che gestisce le comunicazioni radio in una determinata area.
    

> Le BTS rappresentano il punto di riferimento principale per la geolocalizzazione indiretta dei dispositivi mobili.

---

### **4. Copertura e handover**

Il territorio è suddiviso in **celle**, ciascuna servita da una BTS.  
Quando un telefono si sposta, può passare da una cella all’altra mediante il processo di **handover**, cioè il trasferimento automatico della connessione per mantenere attiva la comunicazione.

Esistono due principali tipologie:

- **Handover di salvataggio**, dovuto al calo del segnale radio o all’aumento della distanza;
    
- **Handover di confinamento**, quando una BTS è sovraccarica e il sistema sposta automaticamente l’utente verso una cella vicina.
    

> L’handover, se analizzato correttamente, può fornire informazioni sui movimenti fisici dell’utente durante un intervallo di tempo.

---

### **5. Tabella di localizzazione del cellulare**

|Data|Ora|Zona|Cella|
|---|---|---|---|
|13/05/03|12:40|FG Brindisi|Via Ximenes 30|
|13/05/03|13:42|FG Brindisi|Via Piave|
|13/05/03|14:13|BA Alberona – S. Angelo Scalo||
|13/05/03|14:29|FG Tertiveri – Montelaterone||

Questi dati delineano un **percorso progressivo di spostamento** dal centro urbano di Brindisi verso la provincia di Foggia, in un tempo coerente con un viaggio in automobile.

---

### **6. Linee di handover e geografia del movimento**

Le **BTS rilevanti** a Brindisi sono:

- **Via Piave (13:42)**
    
- **Via Ximenes (12:40)**
    
- **Via Gramsci 11 – Studio Tizio**
    

Successivamente, il dispositivo si collega alle celle di:

- **Alberona – S. Angelo Scalo (14:13)**
    
- **Biccari – Località Migliorini**
    
- **Tertiveri – Montelaterone (14:29)**
    

#### **Handover di sconfinamento**

L’analisi dimostra che, al momento della chiamata delle **14:13**, il dispositivo percorreva la **Strada Provinciale 64 tra Ripalta e Tertiveri**, nei pressi della **Stazione di Civitella Ripalta**.  
Ciò evidenzia un **handover di sconfinamento**, ossia un passaggio fra celle non adiacenti, tipico di aree collinari o di confine di copertura.

---

### **7. Ricostruzione spazio-temporale del tragitto**

La correlazione dei dati consente di costruire una **timeline di percorrenza**, utile per verificare la compatibilità dell’alibi con i tempi fisici di spostamento.

|Tratta|Tempo stimato|Distanza|
|---|---|---|
|**Brindisi (Via Gramsci 11)** → **Via Fossombroni – Via Catalani**|3 min|800 m|
|**Via Fossombroni – Via Catalani** → **Biccari – Tricase**|22 min|20 km|
|**Biccari – Tricase** → **Ripalta (BTS S. Angelo Scalo)**|13 min|13 km|

---

### **8. Tabella di percorrenza a ritroso**

|Luogo|Orario stimato|
|---|---|
|Ingresso BTS S. Angelo Scalo – Ripalta|14:13|
|Biccari – Tricase|14:00|
|Brindisi – Via Fossombroni/Catalani|13:38|
|Studio Paolo Tizio|13:35|

L’insieme dei dati mostra che il cellulare **lascia Brindisi intorno alle 13:35–13:38**, e si connette alle celle di **Ripalta e Tertiveri** entro le **14:13–14:29**, con un percorso di circa **54 km**.

---

### **9. Tabella spazio-temporale complessiva**

|Luogo|Evento|Ora|Distanza cumulata|
|---|---|---|---|
|Studio Paolo Tizio|L’imputato esce dallo studio|13:35|0 km|
|Brindisi – Via Fossombroni/Catalani|Chiamata telefonica|13:38|1 km|
|Studio Paolo Tizio|Ultima attività PC (Word)|13:46|~8 km|
|Biccari – Tricase|Ingresso cella BTS|14:00|21 km|
|Ripalta – S. Angelo Scalo|Chiamata telefonica|14:13|34 km|
|Tertiveri – Montelaterone|Chiamata telefonica|14:29|54 km|

> L’alibi di Tizio viene così verificato incrociando **tempi, distanze e connessioni telefoniche**.  
> L’analisi mostra che gli spostamenti sono **compatibili con una fuga immediata da Brindisi verso Foggia** subito dopo l’omicidio.

---

### **10. Considerazioni finali**

- Le **linee di handover** dovrebbero essere determinate anche tramite **misuratori di campo elettromagnetico**, per validare sperimentalmente la copertura delle celle.
    
- La **documentazione del traffico telefonico** ha valore inizialmente **indiziario**, non probatorio.
    
- È sempre necessario cercare **riscontri ulteriori** (video, GPS, testimonianze, cronotachigrafi, registrazioni di telepass, ecc.).
    
- La **sinergia tra prova digitale e prova ambientale** è ciò che consente al giudice di valutare la veridicità di un alibi.
    

---

### **11. In sintesi**

|Aspetto|Descrizione|
|---|---|
|**Obiettivo**|Correlare la posizione del cellulare con gli orari del PC|
|**Tecnica usata**|Analisi delle celle GSM e handover|
|**Risultato**|Percorso compatibile con spostamento Brindisi → Foggia|
|**Valore giuridico**|Indiziario, da integrare con altre prove|
|**Criticità**|Copertura irregolare, possibili errori di cella|
|**Lezione metodologica**|La prova tecnica deve essere sempre verificata sul campo|

---

### **12. Conclusione**

La seconda parte del caso di alibi informatico dimostra **la potenza e i limiti delle prove digitali**.  
Solo integrando i dati del computer, del cellulare e del territorio si può ottenere una **ricostruzione coerente e difendibile in sede giudiziaria**.

> L’informatica forense, quando applicata correttamente, non accusa e non assolve: **illumina i fatti**.  
> È compito del giudice, poi, attribuire a quella luce il giusto peso probatorio.

---

# **UD2 – Le indagini**

Questa unità didattica approfondisce il tema delle **indagini giudiziarie**, con particolare attenzione alle **modalità operative e ai poteri dell’Autorità Giudiziaria** nel corso della fase istruttoria.  
L’obiettivo è comprendere **come si raccolgono, analizzano e valutano le prove** — in particolare quelle digitali — nel rispetto delle norme procedurali che garantiscono la loro **validità e utilizzabilità processuale**.

Si esamina il ruolo dei soggetti coinvolti (pubblico ministero, polizia giudiziaria, consulenti tecnici), il rapporto tra **atto d’indagine e garanzie difensive**, e le tecniche investigative applicabili ai reati informatici.  
Particolare rilievo assume la distinzione tra **indagini preliminari, accertamenti urgenti e perizie tecniche**, nonché la disciplina delle **catene di custodia e conservazione dei reperti informatici**.

> L’unità insegna che un’indagine efficace non è solo quella che scopre la verità, ma quella che la **rende dimostrabile e incontestabile in giudizio**.


---

## **Lezione 1: Le indagini**

### **1. Introduzione**

La lezione apre con un richiamo al **meccanismo d’avvio del procedimento penale** e ai principali soggetti che vi prendono parte: **parte offesa**, **Pubblico Ministero (PM)**, **Polizia Giudiziaria (PG)** e **consulenti tecnici**.  
L’obiettivo è comprendere **come nasce una notizia di reato** e in che modo l’informatica forense può supportare l’Autorità Giudiziaria nella **fase investigativa**.

> L’informatica forense entra nelle indagini non come disciplina accessoria, ma come **strumento di accertamento tecnico diretto**, capace di fornire elementi oggettivi e verificabili.

---

### **2. La notizia di reato**

Il procedimento penale prende avvio con la **notizia di reato**, ossia la comunicazione di un fatto che potrebbe costituire reato.  
Da questo momento operano i seguenti soggetti:

- **La parte offesa**, ossia colui che subisce il danno derivante dal reato.
    
- **Il Pubblico Ministero (PM)**, che dirige le indagini e coordina la Polizia Giudiziaria.
    
- **La Polizia Giudiziaria (PG)**, che svolge gli accertamenti preliminari e può procedere a sequestri, perquisizioni e raccolta di prove digitali.
    
- **Il consulente tecnico** del PM o delle parti, chiamato a fornire competenze specialistiche in campo informatico.
    

> Fin dall’origine, la qualità delle indagini dipende dall’**integrazione tra competenza giuridica e tecnica**.

---

### **3. Caso 1 – “La Multinazionale”**

Il primo caso reale analizzato riguarda una **grande azienda internazionale** che scopre una grave violazione interna:

- Un **dirigente si dimette improvvisamente**, senza preavviso.
    
- Un collega nota che, pochi giorni prima, aveva **copiato l’intera banca dati aziendale** su un disco esterno.
    
- L’azienda avvia un’indagine interna e si rivolge all’informatica forense per **verificare il furto e ricostruire l’accaduto**.
    

#### **Fasi di accertamento tecnico**

Durante l’indagine emergono gravi carenze organizzative:

- Assenza di log e tracciamenti.
    
- Mancanza di profili individuali di accesso.
    
- Scarsa sensibilità dei dipendenti al concetto di “segreto industriale”.
    
- Sicurezza informatica **totalmente delegata a un fornitore esterno** (outsourcing).
    

> In assenza di controlli strutturati, ogni indagine forense si riduce a una **ricostruzione postuma e incompleta**.

---

### **4. Cosa insegna il caso**

Se l’azienda avesse adottato **misure di sicurezza idonee**, come previsto dalla normativa sulla privacy (oggi Reg. UE 679/2016 – GDPR), avrebbe ottenuto vantaggi decisivi:

- **Elementi di riscontro** per accertare il comportamento del dipendente.
    
- **Limitazione del danno** e del furto di dati.
    
- **Tracciabilità delle operazioni** eseguite.
    

#### **Conclusioni del caso**

1. Le misure di sicurezza non sono solo un obbligo normativo, ma un’**opportunità di tutela** per l’azienda.
    
2. Registrare traffico di rete e accessi può rivelarsi **fondamentale a posteriori**.
    
3. È rischioso **delegare interamente la sicurezza informatica** a soggetti esterni senza controllo diretto.
    
4. Come per i bilanci aziendali esistono revisori contabili, anche in ambito informatico servirebbe **un revisore della sicurezza**, che garantisca indipendenza e trasparenza.
    

---

### **5. Caso 2 – “La Perfetta”**

Il secondo caso riguarda un’altra **multinazionale**, questa volta con **oltre 1000 dipendenti** e un **sistema di sicurezza informatica eccellente**.  
Nonostante ciò, l’azienda denuncia **fughe di notizie riservate**: email interne alla dirigenza risultano trapelate all’esterno.

#### **Accertamenti svolti**

- Struttura informatica **complessa e gerarchica**, con più sedi distribuite a livello mondiale.
    
- Sicurezza elevata sia **fisica** che **logica**.
    
- Cultura aziendale della riservatezza molto alta.
    
- Architettura a **struttura ad albero**: un servizio centrale coordinava i referenti locali nei vari Stati.
    
- Il sistema di posta elettronica era basato su **Lotus Domino (Notes)**, dotato di **crittografia forte** e autenticazione a più fattori (utente + password + chiave).
    

> Un sistema apparentemente inattaccabile, ma la vulnerabilità, come spesso accade, si annida **dove il controllo è massimo**.

---

### **6. L’anello debole: gli amministratori di sistema**

Durante l’analisi si scopre che:

- Gli **amministratori di sistema** possedevano **autorizzazione in lettura su tutte le caselle email**.
    
- I **log di sistema non segnalavano alcuna anomalia**, perché le operazioni erano svolte con credenziali regolari.
    

In pratica, **chi gestiva la sicurezza** aveva anche **il potere di violarla senza lasciare tracce**.

#### **Considerazioni**

- Non trascurare la **sicurezza fisica**: anche un portatile o un backup offline possono diventare punti di fuga.
    
- Diffidare dei tecnici “onnipotenti”, ossia coloro che detengono privilegi assoluti.
    
- La sicurezza totale non esiste: serve **una catena di controllo incrociato** che impedisca abusi interni.
    

---

### **7. Conclusioni generali**

|Lezione appresa|Descrizione sintetica|
|---|---|
|**Sicurezza e normativa**|Adottare misure adeguate non è burocrazia, ma prevenzione.|
|**Logging e tracciabilità**|Registrare traffico e accessi è la base per ogni prova forense.|
|**Controllo incrociato**|Come per i revisori dei bilanci, serve un “certificatore della sicurezza”.|
|**Outsourcing consapevole**|Delegare non significa rinunciare al controllo.|
|**Amministratori di sistema**|Sono un potenziale punto di vulnerabilità, da monitorare costantemente.|

---

### **8. In sintesi**

- La **notizia di reato** nasce spesso da una violazione aziendale o da una segnalazione interna.
    
- La **Polizia Giudiziaria** ha il compito di raccogliere prove nel rispetto della catena di custodia.
    
- Il **Pubblico Ministero** coordina le indagini e può avvalersi di consulenti tecnici.
    
- L’**informatica forense** fornisce il supporto tecnico-scientifico necessario per tradurre un evento informatico in **prova processuale**.
    

> La lezione mostra che la vera forza dell’indagine non risiede nella tecnologia, ma nella **metodologia e nella responsabilità** di chi la conduce.

---

# **UD3 – Il processo**

Questa unità didattica affronta le **dinamiche fondamentali del processo penale**, con l’obiettivo di comprendere **come si sviluppa il giudizio** a partire dalle indagini preliminari fino alla sentenza.  
Viene analizzato il **ruolo delle parti processuali**, la distinzione tra **fasi procedurali** (indagini, udienza preliminare, dibattimento, impugnazioni) e il modo in cui la **prova tecnica** — in particolare quella informatica — si inserisce nel contraddittorio tra accusa e difesa.

L’attenzione si concentra su come l’informatico forense presenti i risultati del proprio lavoro in aula, interagendo con il giudice, i periti e i consulenti delle parti.  
Comprendere il processo significa quindi capire **non solo come si forma la prova**, ma **come essa diventa convincimento del giudice**.

> L’informatica forense trova nel processo penale la sua vera prova di legittimità: una competenza tecnica diventa **scienza giuridicamente riconosciuta solo quando resiste al contraddittorio**.


---

## **Lezione 1: Il processo – Il caso del terrorista mancato**

### **1. Introduzione**

La lezione affronta le **principali tipologie di accertamento tecnico** nel procedimento penale, analizzandole attraverso un **caso reale di informatica forense giudiziaria** noto come _“il terrorista mancato”_.  
L’obiettivo è mostrare come la **prova digitale**, se mal gestita o male presentata, possa condurre a **errori processuali** anche gravi, e come il giudice possa, ai sensi dell’art. 507 c.p.p., **integrare le prove d’ufficio** per accertare la verità.

> Il caso dimostra che nel processo penale la competenza tecnica non basta: occorre **metodo, tracciabilità e chiarezza probatoria**, perché la giustizia non si basa su ciò che è vero, ma su ciò che è **dimostrabile**.

---

### **2. Le forme di accertamento tecnico nel procedimento penale**

Nel sistema processuale italiano, gli **accertamenti tecnici** sono tre:

1. **Accertamento tecnico disposto dalla Procura**, durante la fase delle **indagini preliminari**, utile a orientare le indagini ma **non ripetibile in giudizio** se non nel contraddittorio.
    
2. **Incidente probatorio**, in cui l’atto tecnico viene **anticipato al dibattimento** per garantire la partecipazione delle parti.
    
3. **Perizia**, disposta dal **giudice in sede dibattimentale**, quando ritiene necessaria la valutazione di un esperto.
    

Queste tre forme determinano il modo in cui la **prova tecnica entra nel processo** e ne condizionano il **valore giuridico**.

---

### **3. Il caso del terrorista mancato – Le indagini**

Il caso nasce da una **notizia di reato** relativa al **defacement di un sito web istituzionale**, ovvero la manomissione della sua homepage con messaggi di propaganda estremista.

#### **Le prime indagini**

- L’Autorità Giudiziaria richiede al **provider del sito** i **file di log** del server compromesso.
    
- Dall’analisi emergono:
    
    - le **modalità di accesso abusivo** e di danneggiamento informatico;
        
    - un **indirizzo IP di provenienza**, riconducibile a un utente che aveva usato un **anonymizer** (strumento di anonimizzazione online).
        
- Sulla base di questi elementi viene disposto il **sequestro del computer** dell’indagato.
    

Dopo questa prima fase, però, **l’attività investigativa si interrompe**: i log vengono acquisiti, ma non analizzati con rigore forense né verificati nella loro integrità.

> “Poi, il nulla…” — commenta il docente — evidenziando come la catena investigativa si sia spezzata proprio nella fase più delicata: quella della **validazione della prova digitale**.

---

### **4. Il processo e l’esame delle parti**

Il procedimento entra nella fase dibattimentale:

- Viene sentita la **parte offesa** (il titolare del sito web).
    
- Successivamente, vengono ascoltati **gli agenti della Polizia Giudiziaria (PG)**, i quali fanno ripetuto riferimento ai **file di log** come prova delle connessioni abusive.
    
- Si scopre che tali file sono **conservati su un floppy disk**, consegnato al Pubblico Ministero.
    

Il giudice, per chiarire la situazione, **dispone una perizia tecnica** sui file di log per verificare la loro provenienza e integrità.

---

### **5. La perizia e i primi dubbi**

Durante la perizia emergono **anomalie**:

- Mancano informazioni sui criteri di acquisizione dei log.
    
- Non esiste **documentazione di catena di custodia**.
    
- La struttura dei file è incoerente con quella tipica dei log generati dal server in questione.
    

Il giudice, insoddisfatto, decide di approfondire ulteriormente la vicenda.  
A questo punto entra in gioco l’**articolo 507 del Codice di Procedura Penale**.

---

### **6. L’articolo 507 c.p.p. – Il potere d’integrazione probatoria del giudice**

L’art. 507 c.p.p. prevede che:

> “Terminata l’acquisizione delle prove, il giudice, se risulta assolutamente necessario, può disporre anche d’ufficio l’assunzione di nuovi mezzi di prova.”

In virtù di questa norma, il giudice **dispone la riapertura dell’istruttoria** e **convoca nuovamente gli agenti di PG** per chiarire la provenienza effettiva dei file di log.

> È una situazione eccezionale ma legittima: il giudice, dinanzi a dubbi tecnici rilevanti, può intervenire per accertare la verità, anche superando l’iniziativa delle parti.

---

### **7. A un passo dalla condanna**

Durante la nuova udienza, gli agenti di PG ammettono che **esistevano altri file di log**, diversi da quelli presenti nel fascicolo del PM, e che **solo questi ultimi** erano stati effettivamente utilizzati per svolgere le indagini.  
Tuttavia, **tali log non erano mai stati depositati** agli atti né sottoposti a verifica.

Il giudice, per dissipare ogni dubbio, dispone una **nuova perizia** sui log “mancanti”.

---

### **8. Il nuovo esame peritale**

L’ulteriore perizia, condotta secondo metodo forense, **smentisce le conclusioni originarie della Polizia Giudiziaria**:

- I log “ufficiali” risultano **manipolati o incompleti**.
    
- Gli accessi attribuiti all’imputato **non corrispondono ai reali orari e indirizzi di rete**.
    
- L’anonymizer usato **non consente l’univoca identificazione dell’utente**.
    

Il risultato è una **rivoluzione probatoria**: la tesi accusatoria crolla per mancanza di rigore metodologico.

---

### **9. Il verdetto**

Alla luce della nuova perizia, il giudice **assolve l’imputato** perché il fatto **non è provato**.  
La sentenza si fonda su tre principi chiave:

1. I file di log, per avere valore probatorio, **devono essere acquisiti e conservati secondo metodologia forense**, con catena di custodia documentata.
    
2. La prova digitale è **ripetibile solo se è verificabile** nei suoi metadati e nella sua origine.
    
3. L’attività tecnica deve essere **collegiale**, coinvolgendo le parti e i consulenti per garantire il contraddittorio.
    

> L’errore non fu nell’analisi informatica, ma nel metodo processuale: un accertamento tecnico non validato non può sostenere una condanna penale.

---

### **10. Riepilogo finale**

|Aspetto|Descrizione|
|---|---|
|**Tipo di reato**|Accesso abusivo e defacement di sito web|
|**Prova chiave**|File di log su supporto floppy disk|
|**Errore iniziale**|Mancanza di catena di custodia e verifica|
|**Articolo chiave**|Art. 507 c.p.p. – Integrazione probatoria d’ufficio|
|**Esito finale**|Assoluzione per mancanza di prova certa|
|**Lezione metodologica**|Il valore della prova digitale dipende dalla sua acquisizione e tracciabilità|

---

### **11. Conclusione**

Il _caso del terrorista mancato_ è uno dei più emblematici nella storia della computer forensics italiana.  
Dimostra che **il processo penale non è un laboratorio scientifico**, ma un luogo di contraddittorio regolato da norme:  
una prova tecnicamente corretta ma processualmente viziata **non è prova**.

> La competenza dell’informatico forense non si misura dalla potenza dei suoi strumenti, ma dalla **credibilità del suo metodo davanti al giudice**.

---

