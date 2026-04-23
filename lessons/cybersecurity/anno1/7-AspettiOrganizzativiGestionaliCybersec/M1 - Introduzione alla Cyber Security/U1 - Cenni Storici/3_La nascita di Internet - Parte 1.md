Nella **Lezione 2** abbiamo seguito il percorso evolutivo delle reti di telecomunicazione: dai cavi in rame del telegrafo e del telefono, alle onde radio, fino ai satelliti e alla fibra ottica. Era una prospettiva più tecnologica: come trasmettere sempre più informazioni, sempre più lontano, con sempre meno limiti.  
Con questa lezione, invece, il focus si sposta sul passo decisivo: **la nascita delle reti informatiche**, cioè la possibilità di collegare tra loro non solo persone che parlano, ma direttamente i **computer**, aprendo la strada a quello che diventerà Internet. Qui entrano in gioco nuove esigenze, nuove paure (la Guerra Fredda), e nuovi concetti come il **packet switching** e ARPANET.

---

## La nascita di ARPANET

Negli anni ’50, in piena Guerra Fredda, gli Stati Uniti reagiscono al lancio dello Sputnik sovietico con un progetto di avanguardia tecnologica. Nel 1958 il presidente Eisenhower autorizza la nascita dell’**ARPA** (Advanced Research Project Agency, ovvero Agenzia per i Progetti di Ricerca Avanzata), che in futuro diventerà DARPA (Defense). Lo scopo non è solo di ricerca, ma anche militare: garantire che gli USA restino un passo avanti rispetto ai rivali sovietici.

Uno dei problemi più pressanti era quello delle comunicazioni: in caso di attacco nucleare, un sistema basato su un centro unico (un quartier generale, un nodo centrale) sarebbe stato troppo fragile. Bastava distruggere quel punto e l’intera rete cadeva. Da qui, nel 1965, nasce il concetto di **packet switching**, cioè un metodo per spezzare le informazioni in “pacchetti” che possono viaggiare separatamente sulla rete e ricomporsi alla destinazione. Una rete distribuita, senza un unico punto vitale, molto più resiliente.

---

### La timeline degli anni ’60–’70

- **1967**: Larry Roberts presenta il progetto **ARPANET**, la prima vera rete di computer distribuita. Il principale scopo di tale rete era quello di connettere varie strutture accademiche/universitarie in un unico "reticolo";
- **1969**: viene inviato il primo messaggio tramite ARPANET. È famoso perché si bloccò dopo le prime lettere (“LO” invece di “LOGIN”), ma resta l’atto di nascita di Internet.
    
- **1971**: la rete conta già 23 computer collegati.
    
- # 1972: lo snodo chiave

Nel **1972** nasce l’**International Networking Working Group (INWG)**: un gruppo internazionale di ricercatori e pionieri dell’informatica che si dà un compito preciso—**studiare e proporre standard e protocolli** per far dialogare reti e calcolatori eterogenei. Nato come **gruppo informale** per affrontare i problemi tecnici dell’**interconnessione di reti diverse**, nello stesso anno viene riconosciuto come **sottocomitato dell’IFIP** (la Federazione Internazionale per l’Elaborazione delle Informazioni): segno che il lavoro non è più solo ricerca “artigianale”, ma diventa **normazione** vera e propria.

Sempre nel 1972 succedono due cose molto pratiche per gli utenti:

1. si comincia a **distinguere gli indirizzi dei vari utenti che usano la stessa macchina** collegata in rete. In un’epoca in cui molti accedono **allo stesso computer** (mainframe o timesharing), serve un modo chiaro per dire _“la casella dell’utente X sulla macchina Y”_.
    
2. **Ray Tomlinson** introduce la **chiocciola “@”** proprio per questo: l’indirizzo assume la forma **utente@host**, leggibile come “utente _presso_ macchina”. Con la stessa innovazione invia anche la **prima e-mail**: nasce un servizio che si diffonderà fulmineamente perché risolve un bisogno immediato—comunicare **asincrono**, scritto, tra persone su macchine diverse.
    

Intanto, **ARPANET sbarca in Europa** nel 1972: i primi nodi oltreoceano (UK e Norvegia) trasformano una rete USA in una rete **transnazionale**, accelerando la necessità di regole condivise.

Infine, le **idee maturate nell’INWG** confluiscono nel progetto del **“Protocollo per l’intercomunicazione di reti di pacchetti”**: è la base del **TCP/IP** (Transmission Control Protocol/Internet Protocol), ovvero un insieme di protocolli di comunicazione che costituisce la spina dorsale di Internet, permettendo ai computer e ai dispositivi di scambiare dati

Fu proposto da **Bob Kahn e Vint Cerf**. L’idea cardine è che reti diverse possano **interoperare** grazie a un protocollo comune (IP per l’instradamento dei pacchetti, TCP per l’affidabilità), senza dover cambiare la tecnologia delle singole sottoreti: è il vero **collante** dell’Internet che nascerà.

### Sintesi accademica ma chiara

La nascita di ARPANET non fu un evento isolato, ma l’incontro tra esigenze militari, progresso tecnologico e ricerca scientifica. L’idea rivoluzionaria fu che i computer potessero comunicare non più in maniera rigida e centralizzata, ma in modo flessibile, con messaggi spezzati in pacchetti che trovano la strada migliore attraverso la rete. In pochi anni, da un progetto militare statunitense, si passa a una rete accademica internazionale, con regole comuni (protocolli) e strumenti di comunicazione nuovi (email). In questa fase vediamo gettate le fondamenta concettuali e tecniche di quello che oggi chiamiamo Internet.

---
### Packet Switching: l’idea di base

Immagina di voler inviare un lungo messaggio attraverso una rete (ad esempio, una lettera molto lunga). Nella **commutazione di pacchetto**, quel messaggio non viaggia intero, ma viene **spezzettato in tante piccole unità**, dette **pacchetti**.

Ogni pacchetto porta con sé:

- **Indirizzo sorgente** (da dove parte),
    
- **Indirizzo destinazione** (dove deve arrivare),
    
- **Numero totale** dei pacchetti che compongono il messaggio,
    
- **Numero di sequenza** (per sapere in che ordine riassemblarli).
    
![Pasted image 20250909151821.png](../../imgs/Pasted%20image%2020250909151821.png)
In questo modo, anche se i pacchetti non viaggiano insieme o nello stesso ordine, una volta arrivati a destinazione possono essere **ricomposti correttamente**.

---

### Perché fu inventato?

Negli anni ’60, in piena **guerra fredda**, si cercava un sistema di comunicazione che resistesse anche ad attacchi nucleari. Le reti tradizionali, basate su commutazione di circuito (come la telefonia), erano fragili: se cadeva un nodo centrale, cadeva tutta la rete.  
Il packet switching, invece, **non ha un centro unico**: ogni pacchetto può seguire strade diverse per arrivare a destinazione. È come avere tanti percorsi alternativi: se una strada è bloccata, i pacchetti ne scelgono un’altra. Questo rende la rete **robusta e ridondante**.

---

### L’instradamento

Il viaggio dei pacchetti non è casuale: ogni nodo della rete consulta una **tabella di routing**, che gli dice qual è la prossima uscita migliore per far avanzare il pacchetto verso la destinazione.  
L’obiettivo è scegliere **il percorso più breve e veloce** possibile. È come quando un navigatore GPS ricalcola la rotta se trovi traffico: i pacchetti fanno lo stesso, decidendo passo dopo passo la strada più efficiente.

---

### Sicurezza e affidabilità

Alla fine, tutti i pacchetti arrivano al destinatario e vengono riassemblati.

- Se ne manca uno, viene **richiesto di nuovo**.
    
- Se l’ordine è sbagliato, il numero di sequenza permette di **ricomporre correttamente**.
    
- Quando tutto è a posto, viene inviata una **conferma di ricezione**.
    

Questo meccanismo introduce una **capacità di ridondanza e resilienza** che le reti a commutazione di circuito non avevano. È la base del funzionamento di Internet ancora oggi.

---

Perfetto, questa è una delle parti più dense e delicate della lezione: **l’instradamento (routing)**. Le immagini mostrano sia il **grafico con reti e router (R1, R2, R3)** sia la **tabella di routing** del router R2. Procediamo con calma, scomponendo tutto in step logici, così che tu possa arrivarci senza salti.

---

## 1. Concetto di base: cosa fa un router

Un router è un **dispositivo elettronico** che collega più reti diverse (o sottoreti).  
Il suo lavoro è: quando riceve un pacchetto, deve **decidere a quale interfaccia inviarlo**, cioè attraverso quale “porta” farlo uscire per andare nella direzione giusta.

👉 Ma attenzione: il router **conosce solo le reti a cui è direttamente collegato**. Per tutte le altre reti deve affidarsi a regole contenute in una **tabella di routing**.

---

## 2. La situazione in figura
![Pasted image 20250909152622.png](../../imgs/Pasted%20image%2020250909152622.png)
- Abbiamo 5 reti (Rete 1, 2, 3, 4, 5).
    
- Ci sono 3 router: **R1, R2, R3**.
    
- Il router che analizziamo è **R2**.
    
    - Ha **due interfacce**:
        
        - **eth0** (collegata a Rete 2, indirizzo 2.2)
            
        - **eth1** (collegata a Rete 3, indirizzo 3.1)
            

Quindi R2 **vede direttamente solo Rete 2 e Rete 3**.  
Se un pacchetto deve andare a Rete 1, Rete 4 o Rete 5, non può arrivarci direttamente → serve passare da altri router.

---

## 3. La tabella di routing

Guarda la tabella: ogni riga rappresenta una **regola di instradamento**.

|#|Indirizzo di Rete|Gateway|Interfaccia|
|---|---|---|---|
|0|Rete 2|0.0|eth0|
|1|Rete 3|0.0|eth1|
|2|Rete 1|2.1|eth0|
|3|Rete 4|3.2|eth1|
|4|Rete 5|3.2|eth1|
|5|0.0|0.0|eth0|

Ora smontiamola pezzo per pezzo.

---

## 4. Le regole dirette (#0 e #1)

- **Riga 0: Rete 2 → Gateway 0.0, Interfaccia eth0**  
    Significa che se il pacchetto è destinato a un host della Rete 2, R2 non deve fare altro che mandarlo fuori dall’interfaccia **eth0** (perché è già direttamente collegato a Rete 2).  
    `Gateway 0.0` vuol dire “nessun salto intermedio richiesto”.
    
- **Riga 1: Rete 3 → Gateway 0.0, Interfaccia eth1**  
    Stesso discorso per Rete 3: basta uscire da **eth1**.
    

👉 Queste righe servono per reti **direttamente adiacenti** al router.

---

## 5. Le regole indirette (#2, #3, #4)

Qui entra in gioco il concetto di **Gateway (Next Hop)**: se una rete non è direttamente connessa, il pacchetto deve passare da un altro router.
![Pasted image 20250909152848.png](../../imgs/Pasted%20image%2020250909152848.png)
- **Riga 2: Rete 1 → Gateway 2.1, Interfaccia eth0**  
    Rete 1 non è direttamente collegata a R2. Però R2 sa che per raggiungerla deve inoltrare il pacchetto al router R1 (che ha indirizzo 2.1 nella Rete 2). Quindi manda tutto su **eth0** verso R1.
    
- **Riga 3: Rete 4 → Gateway 3.2, Interfaccia eth1**  
    Stesso ragionamento: Rete 4 è raggiungibile solo passando da R3 (indirizzo 3.2 in Rete 3). Quindi R2 manda a R3, usando **eth1**.
    
- **Riga 4: Rete 5 → Gateway 3.2, Interfaccia eth1**  
    Anche Rete 5 è dietro R3. Quindi ancora una volta pacchetti instradati verso il gateway 3.2, tramite **eth1**.
    

---

## 6. La regola di default gateway ![Pasted image 20250909154041.png](../../imgs/Pasted%20image%2020250909154041.png#5)

- **Riga 5: Indirizzo 0.0 → Gateway 0.0, Interfaccia eth0**  
    Questa è la **default route**.  
    Significa: se nessuna regola precedente corrisponde, il pacchetto viene mandato di default su **eth0**.  
    È come dire: “se non sai dove andare, prendi questa uscita”.
    

---

## 7. Come viene scelta la regola giusta

Il router quando riceve un pacchetto:

1. Guarda l’indirizzo di destinazione.
    
2. Scorre la tabella dall’alto verso il basso.
    
3. Applica la prima regola che corrisponde.
    
4. Se più regole potrebbero corrispondere, si usa la **Longest Prefix Matching** → cioè la regola più specifica (con più bit in comune con l’indirizzo).
5. Se invece non viene trovata nessuna regola corrispondente, allora il pacchetto non può essere instradato
    

---

## 8. Esempio concreto

Supponiamo che R2 riceva un pacchetto con destinazione **indirizzo 1.2 (appartenente a Rete 1)**.  ![Pasted image 20250909152848.png](../../imgs/Pasted%20image%2020250909152848.png)
Come viene instradato?

- Il router consulta la tabella.
    
- Rete 2 e Rete 3 non c’entrano (#0 e #1 scartate).
    
- Alla riga #2 trova “Rete 1 → Gateway 2.1, eth0”. Perfetto!
    
- Il pacchetto esce da **eth0** e va verso l’indirizzo 2.1 (cioè R1).
    
- R1 poi saprà cosa farne (perché è direttamente collegato a Rete 1).
    

---

## 9. Importanza del concetto

Questa logica si applica in tutti i router moderni:

- ogni pacchetto porta con sé un indirizzo di destinazione,
    
- ogni router decide **il prossimo passo** (non tutta la strada fino alla destinazione, solo il passo successivo),
    
- e tutto questo viene fatto basandosi sulla **tabella di routing**.
    

È grazie a questo meccanismo che Internet funziona: miliardi di pacchetti ogni secondo trovano la loro strada grazie a regole di questo tipo.

---

Andiamo dritti al punto: **come si riempie la tabella di instradamento** e cosa cambia tra **routing statico** e **routing dinamico** (RIP/OSPF). Userò l’esempio della slide (R1–R2–R3 con Reti 1..5) così ogni pezzo resta agganciato al disegno.

---

# 1) Routing statico — “scrivo io le regole, a mano”

- **Cos’è**: le rotte si inseriscono **manualmente** sul router (es. su **R2**:  
    `ip route Rete1 via 2.1 dev eth0`, `ip route Rete4 via 3.2 dev eth1`, ecc.).
    
- **Quando va bene**: reti **piccole/stabili**, pochi percorsi, topologia che **non cambia** di frequente.
    
- **Pro**:
    
    - Zero “chiacchiere” tra router (nessun traffico di aggiornamento).
        
    - Totale controllo dell’operatore: ogni rotta è **esplicita**.
        
    - Utile a bordo rete (es. un **default route** verso l’ISP (Internet Service Provider)).
        
- **Contro**:
    
    - **Scalabilità pessima**: se aggiungi/rompi un link, devi cambiare **a mano** tutte le rotte coinvolte.
        
    - **Niente autoriparazione**: se cade il collegamento verso R3, R2 continua a spedire verso 3.2 finché qualcuno non corregge la rotta.
        
    - **Errore umano** dietro l’angolo.
        

💡 **Nel nostro schema**: la tabella di R2 che vedi in slide è proprio l’esempio di **routing statico**:

- Rete 2 → `Gateway 0.0`, `eth0` (diretta)
    
- Rete 3 → `Gateway 0.0`, `eth1` (diretta)
    
- Rete 1 → **via 2.1**, `eth0` (passa da R1)
    
- Rete 4/5 → **via 3.2**, `eth1` (passa da R3)
    
- `0.0` → default (se non corrisponde a nulla, usa `eth0`)
    

Un trucco utile: le **“floating static”** → rotte statiche di **backup** con priorità più bassa (AD (Administrative Distance) più alto) che entrano in gioco solo se fallisce la principale.
L'AD è un **valore numerico** che indica quanto una rotta statica o dinamica è considerata _attendibile_ da un router, **quando esistono più percorsi verso la stessa destinazione**.

- Più basso è il valore di AD → più “credibile” è quella rotta.
    
- Se ci sono più rotte verso la stessa rete, il router sceglie quella con l’AD minore.

---

# 2) Routing dinamico — “i router imparano da soli”

- **Cos’è**: i router **collaborano** scambiandosi informazioni sui percorsi tramite **protocolli**. La tabella si aggiorna **automaticamente** quando cambiano link o costi.
    
- **Perché serve**: appena la rete cresce o cambia, si vuole **convergenza automatica** (autoriparazione) e scelta del **miglior percorso** senza toccare decine di apparati.
    
- **Costo**: un po’ di **CPU/RAM** sui router e qualche **pacchetto di segnalazione** sulla rete.
    

Ne esistono vari tipi. Nella slide compaiono i due “classici” accademici:

---

## 2a) RIP (Routing Information Protocol) — _distance vector_ (semplice ma limitato)

- **Logica**: ogni router dice ai **vicini** “quante **hop** (salti) mi separano da ogni rete”.
    
- **Metrica**: numero di **hop**.
    
- **Limite**: **max 15 hop** (16 = infinito). Reti grandi? Non adatto.
    
- **Aggiornamenti**: periodici (tipicamente **30 s**) → **convergenza lenta**; per mitigare problemi storici (es. _count to infinity_) usa **split horizon**, **poison reverse**, **hold-down**.
    
- **Versioni**:
    
    - **RIPv1** (storico, classful, senza maschere/CIDR)
        
    - **RIPv2** (classless, porta maschere, autenticazione).
        
- **Uso moderno**: didattica, piccole reti semplici.
    

🔗 **Nel nostro schema**: con RIP, R2 impara da R1 “Rete 1 a 1 hop via me (2.1)” e da R3 “Rete 4 e Rete 5 a 1 hop via me (3.2)”. Se cade il link R2–R3, R2 riceve dagli aggiornamenti che le rotte verso Rete 4/5 non sono più valide e le rimuove: **autoriparazione** (lenta).

---

## 2b) OSPF (Open Shortest Path First) — _link state_ (potente e veloce)

- **Logica**: ogni router scopre lo **stato dei propri link** (adiacenze) e **inonda LSAs** (Link State Advertisements). Tutti costruiscono **la stessa mappa** della topologia e calcolano il **cammino più breve** con l'algoritmo di **Dijkstra (SPF)**.
    
- **Metrica**: **costo** (di solito funzione della **larghezza di banda**).
    
- **Convergenza**: **rapida** (aggiorna solo quando cambia qualcosa).
    
- **Scalabilità**: alta. Supporta **aree** (backbone **Area 0** + aree interne), **summarization**, **ECMP** (più percorsi uguali), **autenticazione**, **VLSM/CIDR**.
    
- **Trasporto**: protocollo IP **89** (non TCP/UDP).
    
- **Varianti**: **OSPFv2** (IPv4), **OSPFv3** (IPv6).
    
- **Uso moderno**: la scelta tipica per **reti enterprise / campus / ISP interni**.
    

🔗 **Nel nostro schema**: con OSPF, R2 conosce **l’intera mini-topologia** (chi è collegato a chi) e calcola in autonomia che per **Rete 1** conviene uscire **eth0 → R1 (2.1)**, per **Rete 4/5** **eth1 → R3 (3.2)**. Se aggiungi un nuovo link (es. R2–R4), OSPF lo “vede”, ricalcola i costi, e **aggiorna** la tabella in pochi istanti.

---

# 3) Statico vs Dinamico — come scelgo?

|Criterio|Statico|Dinamico (RIP/OSPF)|
|---|---|---|
|**Dimensione rete**|Piccola, semplice|Media/grande, variabile|
|**Cambiano i link?**|Raramente → ok|Spesso → serve _convergenza_|
|**Resilienza**|Manuale (rischio down)|Automatica (failover)|
|**Overhead**|Zero traffico di protocollo|Qualche pacchetto + CPU|
|**Controllo**|Totale, chirurgico|Policy possibili, ma automatismo|
|**Didattica**|Ottimo per capire|Fondamentale per la vita reale|

**Regola pratica**: usa **statiche** ai margini (default verso ISP, rotte “puntuali” o **floating static** di backup), e **OSPF** nel “core” della tua rete. **RIP** solo come esercizio o in reti davvero minime.

---

# 4) Due micro-scenari sul tuo disegno (per fissare)

**A. Crolla il link R2–R3 (eth1 giù)**

- _Statico_: le righe “Rete 4 via 3.2” e “Rete 5 via 3.2” restano… ma **non funzionano**. Qualcuno deve **cancellarle** o modificarle.
    
- _RIP_: dopo i timer di aggiornamento, i vicini annunciano che quelle reti non sono più raggiungibili → R2 **pulisce** la tabella (lento).
    
- _OSPF_: R2 riceve LSAs che indicano il link giù, ricalcola SPF → **converge** velocemente (se esiste un percorso alternativo, lo usa).
    

**B. Aggiungi un nuovo collegamento R2–R1 ad alta banda**

- _Statico_: devi **ritoccare le rotte** per sfruttarlo.
    
- _RIP_: la metrica è “hop”: se i salti sono gli stessi, **non** sfrutta la banda maggiore.
    
- _OSPF_: il **costo** dipende dalla **banda** → sceglierà il **percorso migliore** automaticamente.
    

---

# 5) Due termini che cadono sempre

- **Convergenza**: istante in cui **tutti i router** hanno tabelle coerenti dopo un cambio. (OSPF → rapida; RIP → lenta).
    
- **Administrative Distance (AD)**: “fiducia” predefinita nelle sorgenti di rotta (più **basso** = più **credibile**). Indicativo: **static 1**, **OSPF 110**, **RIP 120**. Serve anche per le **floating static**.
    

---

## TL;DR (ripasso lampo)

- **Statico**: scrivo io le rotte; semplice ma non si adatta.
    
- **RIP** (_distance vector_): dice “quanti hop”; facile, **15 hop max**, lento a convergere.
    
- **OSPF** (_link state_): conosce la mappa, calcola il **cammino minimo** (Dijkstra), **scala** e converge **veloce**.
    
- In pratica: **OSPF** per la rete “vera”; **statiche** mirate come contorno; **RIP** per studiare.

---
## 🌍 Dalla nascita di Internet alla sua prima formalizzazione (1983)

Dopo aver visto come **Arpanet** sia nata in piena Guerra Fredda come rete sperimentale, basata sulla commutazione di pacchetto per garantire la resilienza e la ridondanza delle comunicazioni militari e accademiche, arriviamo agli anni **’80**, quando la rete cresce in maniera così evidente da richiedere un salto qualitativo.

Nel **1983** avvengono tre eventi fondamentali che segnano il passaggio da Arpanet a quello che oggi chiamiamo **Internet**:

---

#### 1. La divisione di Arpanet in Milnet e Arpanet

- Per motivi di **sicurezza nazionale**, il Dipartimento della Difesa degli Stati Uniti decise di separare le due principali anime di Arpanet:
    
    - **Milnet** → rete adibita esclusivamente alle comunicazioni e ai programmi di ricerca di natura **militare**.
        
    - **Arpanet** → rete che rimane a disposizione delle **università** e dei **centri di ricerca civili**, mantenendo lo spirito di collaborazione accademica internazionale.
        

Questa scelta è importante perché rappresenta la **prima grande frammentazione funzionale della rete**, una suddivisione in “domini” con finalità diverse, ma sempre comunicanti grazie al linguaggio comune dei protocolli.

---

#### 2. Arpanet diventa Internet

- Nello stesso anno, Arpanet non è più solo un progetto militare-accademico: ingloba altre piccole reti locali che si erano sviluppate parallelamente.
    
- Questo processo di **federazione di reti** porta alla nascita ufficiale di **Internet**, termine che deriva da _Interconnected Networks_, ossia **reti interconnesse**.
    
- È la prima volta che si riconosce formalmente il concetto di rete delle reti, dove sistemi eterogenei, pur essendo indipendenti, possono comunicare grazie a protocolli comuni.
    

---

#### 3. L’adozione del TCP/IP come standard obbligatorio

- Sempre nel 1983 viene stabilito che chiunque voglia partecipare alla rete deve adottare il **protocollo TCP/IP** (_Transmission Control Protocol / Internet Protocol_).
    
- Questa decisione è rivoluzionaria: da quel momento in poi, non importa quale fosse l’hardware, il sistema operativo o la tipologia della rete, l’unico requisito per essere “parte del tutto” è parlare TCP/IP.
    
- In altre parole, **nasce l’universalità tecnica di Internet**: ogni dispositivo può entrare nella rete mondiale purché segua le stesse regole di comunicazione.
    

---

#### 4. La nascita del DNS (Domain Name System)

- Sempre nel 1983 viene introdotto un meccanismo destinato a cambiare per sempre l’usabilità della rete: il **Domain Name System (DNS)**.
    
- Prima del DNS, per raggiungere un nodo della rete si usavano esclusivamente **indirizzi numerici** (IP addresses), sequenze di cifre difficili da ricordare e gestire.
    
- Il DNS introduce invece i **nomi di dominio**: stringhe testuali facilmente memorizzabili (es. `mit.edu`, `stanford.edu`, e più tardi i suffissi `.com`, `.org`, `.it`).
    
- Questo non è un dettaglio: è il passaggio che rende Internet **user-friendly**, avvicinandola sempre più al grande pubblico.
    

---

#### 5. Dimensioni ancora ridotte

- Nonostante la svolta epocale, all’epoca la rete era ancora molto piccola: si contavano meno di **mille indirizzi complessivi**.
    
- Un numero che oggi appare quasi ridicolo se confrontato con i miliardi di dispositivi connessi, ma che nel 1983 rappresentava un **laboratorio globale** pronto ad esplodere.
    

---

### 🔑 In sintesi

Il 1983 è l’anno in cui Internet compie il salto da **rete sperimentale** a **infrastruttura globale**:

- si differenziano le sue anime (militare e civile),
    
- si unificano i protocolli con l’adozione del TCP/IP,
    
- si semplifica l’uso grazie al DNS,
    
- e si formalizza il nome con cui ancora oggi la conosciamo: **Internet**.
    

Si tratta quindi di un vero **anno spartiacque**, paragonabile a un “atto di nascita” ufficiale della rete moderna.

---

Ora passiamo in rassegna gli ultimi due concetti fondamentali: IP e protocollo TCP/IP.


---

# Perché servono gli indirizzi IP (univoci)

Internet è una rete di reti. Perché due macchine possano parlarsi, **ognuna deve avere un identificatore univoco** nell’ambito in cui comunica: l’**indirizzo IP** (Internet Protocol).  
“Univoco” significa: nessun dubbio su _chi_ sia il destinatario. Nel mondo pubblico l’univocità è garantita da enti di registrazione (storicamente **InterNIC**; oggi la catena è **IANA/ICANN → RIR** regionali come RIPE/ARIN/APNIC…; ma il senso della slide è: c’è un registro globale che evita duplicati).

> Nota: all’interno di reti private (casa, azienda, VPN come Hamachi) puoi riusare gli stessi indirizzi “privati” perché **non sono visibili su Internet**; il collegamento col mondo pubblico lo fa il **NAT** del router o il software VPN.

---

# IPv4: 32 bit in “decimale puntato”
![Pasted image 20250909162321.png](../../imgs/Pasted%20image%2020250909162321.png)
Nella figura c’è **172.16.254.1**. Sotto vedi i **bit**: ogni blocco tra i punti è un **ottetto = 8 bit = 1 byte**.  
Totale: **4 ottetti × 8 bit = 32 bit**.

- 172 → 10101100
    
- 16 → 00010000
    
- 254 → 11111110
    
- 1 → 00000001
    

Questa è la “targhetta” che identifica un’interfaccia di rete. Con 32 bit abbiamo ≈ **4,29 miliardi** di possibili indirizzi: sembravano tantissimi, ma Internet li ha **esauriti** (motivo per cui è nato IPv6).

---

## 1. La rete vista come “condominio”

Immagina Internet come una **città gigante**.

- La **rete** è come **il palazzo o la via** (contesto più grande).
    
- L’**host** è come **l’appartamento specifico o il campanello** dentro quel palazzo.
    

Esempio:

- **172.16.254.0/24** → è il “palazzo” (rete).
    
- **172.16.254.1** → è l’host, cioè un singolo dispositivo dentro quel palazzo.
    
- Altri host dello stesso palazzo sarebbero **172.16.254.2**, **172.16.254.3**, … fino a .254.
    

---

## 2. Cos’è una sottorete

Una **sottorete (subnet)** è una **suddivisione logica** di una rete più grande.  
Serve a:

- organizzare meglio gli indirizzi,
    
- isolare gruppi di dispositivi,
    
- ridurre traffico inutile (perché il broadcast rimane confinato dentro quella sottorete).
    

👉 In pratica, una sottorete è una **piccola “rete locale” ritagliata da una rete più grande**, come se in un palazzo dividessi gli inquilini per piano.

---
Ecco perché si ricorre a...
## Rete vs Host (subnet mask / prefisso)

Un indirizzo IPv4 si divide logicamente in:

- **prefisso di rete** (quale sottorete),
    
- **parte host** (quale dispositivo dentro quella sottorete).
    

Questa divisione è indicata dalla **mask**.
# Subnet mask (maschera di sottorete)

La **mask** è una sequenza di 32 bit che serve a dire:

- **quali bit appartengono alla rete (prefisso di rete)**,
    
- **quali bit appartengono all’host (parte host)**.
    

Esempio:

- Mask **255.255.255.0** in binario = **11111111.11111111.11111111.00000000**.
    
- Qui i primi 24 bit sono **1** → parte “rete”.
    
- Gli ultimi 8 bit sono **0** → parte “host”.
    

---

# 3. CIDR e notazione “slash”

Invece di scrivere ogni volta tutta la maschera (255.255.255.0), si usa la notazione **CIDR (Classless Inter-Domain Routing)**:

- **/24** significa “24 bit riservati alla rete, il resto agli host”.
    
- Quindi 172.16.254.1/**24** = mask 255.255.255.0
    
- In quella sottorete gli host possibili sono **2^(8) − 2 = 254** (si tolgono rete e broadcast in IPv4 classico).

---

### Subnetting

“Subnettare” = **suddividere** un blocco più grande in più reti più piccole aumentando i bit di prefisso.  
Esempio: da **172.16.0.0/16** puoi fare 256 sottoreti **/24** (172.16.0.0/24, 172.16.1.0/24, …).  
Serve per organizzare traffico, sicurezza, HVAC, VLAN, ecc.

## Indirizzi privati (riusabili) + NAT (Network Address Translation

Per non sprecare IP pubblici, IPv4 ha riservato dei **blocchi privati** riusabili ovunque:

- 10.0.0.0/8
    
- 172.16.0.0/12 (da 172.16.0.0 a 172.31.255.255)
    
- 192.168.0.0/16
    

I tuoi PC/console in casa, o la **VPN Hamachi** per Minecraft, tipicamente ricevono **un IP privato** su una rete virtuale. Per uscire su Internet “vero”, il traffico passa da **NAT** (traduce molti IP privati in 1 IP pubblico).

---

# IPv6: 128 bit in “esadecimale a gruppi”

Per superare i limiti di IPv4 è nato **IPv6**: **128 bit** (praticamente infinito per i nostri scopi).  
La figura mostra un esempio:  
![Pasted image 20250909162509.png](../../imgs/Pasted%20image%2020250909162509.png)
**2001:0DB8:AC10:FE01:0000:0000:0000:0000**

- È scritto in **esadecimale** (0–9, A–F).
    
- È diviso in **8 gruppi** da **16 bit** (chiamati _hextet_), separati da **due punti**.
    

### Regole di abbreviazione (vedi frecce nella slide)

- Zeri **iniziali** in un gruppo si possono **omettere**: `0DB8` → `DB8`.
    
- Una sequenza di gruppi **tutti zero** si può comprimere con **`::`**, **una sola volta** per indirizzo.
    
- Quindi l’esempio diventa:  
    **2001:db8:ac10:fe01::**  
    (case-insensitive; `::` sostituisce `0000:0000:0000:0000`).
    

### Struttura tipica

Spessissimo i primi **/64** bit sono **prefisso di rete**, e i secondi **/64** sono **Interface ID** (spesso autogenerato: SLAAC). Non esistono broadcast; si usa **multicast**.  
Classi utili da riconoscere:

- **Global Unicast**: 2000::/3 (gli “pubblici” di IPv6).
    
- **Unique Local (ULA)**: fc00::/7 (simil “privati” di IPv6).
    
- **Link-local**: fe80::/10 (valido solo sul link locale, indispensabile per l’autoconfigurazione).
    

### Perché è meglio (anche per te che giochi)

- **Niente NAT obbligatorio**: ogni nodo può avere un **IP pubblico** end-to-end (utile per giochi/peer-to-peer).
    
- **Spazio enorme**: niente ansia da “porte chiuse”/traduzioni strane.
    
- **Autoconfigurazione** semplificata (SLAAC) + DHCPv6 se serve.
    

---

# Collegamento “Minecraft/Hamachi”

Quando usavi **Hamachi**:

- ti assegnava **un IP privato su una rete virtuale** (overlay) condivisa con la tua Eriketta;
    
- il traffico tra voi viaggiava dentro il **tunnel VPN**, come se foste sulla **stessa LAN**, anche da città diverse;
    
- Hamachi poteva usare IPv4 e/o IPv6: magari vedevi indirizzi **192.168.x**, **25.x**, oppure stringhe tipo **2001:*** → sono solo **due formati diversi dello stesso concetto**: un’etichetta univoca per raggiungervi dentro quella rete virtuale.
    

---

# IPv4 vs IPv6 — quadro mentale in 30 secondi

|Aspetto|IPv4|IPv6|
|---|---|---|
|Lunghezza|32 bit (4×8)|128 bit (8×16)|
|Notazione|decimale puntato (es. 172.16.254.1)|esadecimale a gruppi (es. 2001:db8:ac10:fe01::)|
|Spazio|~4,3 miliardi|astronomico|
|Privati|sì (RFC1918) + NAT|ULA (fc00::/7); NAT non necessario|
|Broadcast|sì|no (multicast)|
|Config|DHCPv4/statica|SLAAC, DHCPv6, statica|
|Usabilità|onnipresente|sempre più diffuso (ISP/OS/servizi già pronti)|

---

# Due micro-esempi per fissare

1. **Capire un /24 IPv4**  
    172.16.254.0/24 → rete;  
    host validi: 172.16.254.1–172.16.254.254;  
    172.16.254.255 è broadcast.  
    Se vedi **172.16.254.1/24**, sai che appartiene a quella sottorete.
    
2. **Comprimere un IPv6**  
    2001:0db8:0000:0000:0000:0000:0000:0042 →  
    2001:db8::42  
    (zeri iniziali rimossi; `::` una sola volta).
    

---

# Cosa porterai all’esame (e nella vita)

- **IPv4**: 32 bit, dotted-decimal, concetto di **mask/prefisso**, **subnetting**, **privati** + **NAT**.
    
- **IPv6**: 128 bit, hextet, regole di **compressione**, classi base (global/ULA/link-local), **/64** come prefisso tipico, niente broadcast, **multicast**.
    
- **Univocità**: dal registro globale per gli IP pubblici; riuso locale per i privati/VPN.
    


---
# 1. Il contesto generale: TCP/IP

Dalla slide e dal testo vediamo subito due blocchi distinti:

- **Protocollo IP**  
    – È il **protocollo di interconnessione delle reti** (Inter-Networking Protocol).  
    – Il suo compito è collegare **reti eterogenee** (diverse tecnologie, prestazioni, gestioni).  
    – È un protocollo **a pacchetti senza connessione**: significa che invia datagrammi senza alcuna garanzia che arrivino, né nell’ordine giusto, né che non vengano persi.  
    – In altre parole: **IP consegna al meglio delle possibilità, ma non promette affidabilità**.
    
- **Protocollo TCP**  
    – Transmission Control Protocol.  
    – Si colloca al **livello di trasporto** (sopra IP, che è al livello di rete).  
    – Serve a **rendere affidabile la comunicazione**: controlla errori, ordina i pacchetti, ritrasmette se serve.  
    – Fa in modo che due host (client e server) possano instaurare una connessione sicura e stabile, associata a indirizzo + porta → la cosiddetta **socket**.
    

---

## 2. Il grafico: Handshake a tre livelli (3-way handshake)

Il disegno mostra la sequenza di messaggi tra due host: **Client** (a sinistra) e **Server** (a destra), con tre frecce verticali che stabiliscono la connessione:
![Pasted image 20250909164925.png](../../imgs/Pasted%20image%2020250909164925.png)
### a) Primo passo: `SYN, seq = x` (Client → Server)

- **SYN** = synchronize.
    
- Il client vuole iniziare la comunicazione e manda un messaggio speciale con un **numero di sequenza iniziale**.
    
- `seq = x` significa: “il primo byte che ti invierò avrà numero x”.  
    Questo serve a numerare tutti i byte che viaggeranno da client a server.
    

👉 In parole semplici: il client bussa alla porta del server e gli dice: “Ciao, vorrei parlare con te. Parto da numero x”.

---

### b) Secondo passo: `SYN-ACK, seq = y, ack = x+1` (Server → Client)

- **SYN-ACK** = il server risponde con un SYN (per proporre anche lui un numero di sequenza iniziale) + un ACK (acknowledgment, cioè conferma di ricezione).
    
- `seq = y` → il server sceglie il proprio numero iniziale y (da server verso client).
    
- `ack = x+1` → il server conferma: “Ho ricevuto il tuo SYN con numero x, ora aspetto il prossimo, cioè x+1”.
    

👉 Tradotto: il server dice: “Ho ricevuto la tua richiesta, ok! Io comincio dal numero y. Inoltre ho capito che il tuo inizio era x, quindi da te aspetto il prossimo byte numerato x+1”.

---

### c) Terzo passo: `ACK, seq = x+1, ack = y+1` (Client → Server)

- **ACK** = conferma finale.
    
- `seq = x+1` → il client ora conferma che il suo contatore parte da x+1 (perché x era il SYN).
    
- `ack = y+1` → il client dice: “Ho ricevuto il tuo SYN con numero y, quindi ora da te mi aspetto y+1”.
    

👉 Significato: il client chiude la stretta di mano: “Ok, so che tu parti da y e io da x. Possiamo iniziare a scambiarci dati”.

---

### d) Dopo i tre passi

- La connessione è “stabilita”.
    
- Nella slide, sotto la terza freccia, c’è scritto **[data]**: da qui in poi possono fluire i dati veri e propri, numerati e confermati.
    

---

## 3. Cosa significano in pratica le variabili (x, y, seq, ack)

- **x** = numero iniziale scelto dal client.
    
- **y** = numero iniziale scelto dal server.
    
- **seq** = sequence number: “il primo byte che sto inviando in questo segmento ha questo numero”.
    
- **ack** = acknowledgment number: “ti confermo che ho ricevuto tutto fino a numero N-1, e adesso aspetto N”.
    
- **x+1, y+1** → il +1 serve perché il pacchetto SYN e il pacchetto SYN-ACK valgono come “occupazione” di un numero nella sequenza.
    

---

## 4. Riassunto “da esame”

- **IP**: collega le reti, ma non garantisce affidabilità.
    
- **TCP**: aggiunge affidabilità, ordine e controllo.
    
- **3-way handshake**:
    
    1. Client → Server: `SYN, seq=x`
        
    2. Server → Client: `SYN-ACK, seq=y, ack=x+1`
        
    3. Client → Server: `ACK, seq=x+1, ack=y+1`
        
- Dopo di questo, dati ordinati e affidabili possono scorrere nella connessione.