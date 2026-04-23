Nella L4 abbiamo messo a fuoco l’“infrastruttura” del Web: come si localizzano le risorse (URL), come client e server dialogano (HTTP) e qual è il linguaggio che compone le pagine (HTML). Oggi facciamo il passo complementare: **dalla nascita della rete ai primi incidenti e attacchi storici**, per capire _quando_ compaiono le minacce, _come_ si diffondono e _perché_ iniziano a produrre impatti reali su sistemi e società. È esattamente l’obiettivo dichiarato della lezione: “esamineremo i primi incidenti ed attacchi della storia per iniziare a comprenderne le ragioni”.

---

# Timeline “Primi incidenti e attacchi”

**Anni ’70**

- **1972 – Creeper**: è considerato il _primo malware_ della storia; un “worm” che segna l’idea di codice capace di spostarsi tra macchine.
    
    
    
- **1978 – Primo spam**: il 1° maggio parte un’email collettiva che invita alla presentazione di un nuovo processore; è il primo caso documentato di _invio massivo indesiderato_.
    
    
    

**Anni ’80**

- **1982 – Elk Cloner**: primo virus diffuso in modo incontrollato; scritto dal quindicenne **Rich Skrenta**, si propaga via **floppy disk**.
    
    
    
- **1982 – Trans-Siberian Pipeline**: episodio simbolo della _cyber war_ nascente; l’azione contro il gasdotto sovietico entra nella cronologia come primo attacco “di guerra” in ambito informatico.
    
    
    
- **1988 – Morris Worm**: spartiacque per impatto e scala (qui lo registriamo nella timeline; l’analisi tecnica arriverà più avanti nella lezione).
    
    
    

**Idea-chiave della timeline**: in pochi anni si passa da **esperimenti di laboratorio** (Creeper) a **fenomeni sociali** (spam), a **propagazione su supporti fisici** (Elk Cloner), fino a **impatti geopolitici** (Trans-Siberian) e **incidenti su larga scala** (Morris).


---

- **Creeper (1972)** apre la storia del malware come _worm_ ante litteram. Non anticipiamo ancora le definizioni formali: qui conta il fatto storico — è il “probabile primo” esempio di codice malevolo in circolazione.
    
    
    
- **Spam (1978)**: l’email “broadcast” del 1° maggio inaugura la pratica dello _spamming_. La lezione lo definisce come invio verso indirizzi multipli e generici di messaggi ripetuti/monotematici che diventano **indesiderati** (tipicamente commerciali).
    
    
    
- **Virus su ARPANET**: i primi virus nascono _in parallelo_ allo sviluppo della rete, anche con finalità di **test dell’efficacia del network** — dato storico importante per capire che la frontiera tra “esperimento” e “minaccia” è stata inizialmente labile.
    
    
    
- **Elk Cloner (1982)**: caso scolastico di **diffusione incontrollata** via supporti rimovibili; memorizza due concetti chiave per lo studio: _autore giovanissimo_ e _vettore di propagazione_ (floppy).
    
    
    
- **Cyber war: Trans-Siberian Pipeline (1982)**: viene qualificato come **primo attacco di guerra cyber** della storia; lo teniamo come riferimento cronologico (i dettagli tecnici e operativi arriveranno nelle slide dedicate).
    

---
### Virus

Un virus informatico è un frammento di codice che non può funzionare autonomamente. Ha bisogno di un programma ospite nel quale inserirsi per poter essere eseguito. La sua attivazione avviene solo quando l’utente lancia il programma infetto. Questo meccanismo fa sì che il virus si diffonda insieme ai file contaminati, ad esempio quando vengono copiati o condivisi. La caratteristica essenziale del virus è quindi la **dipendenza da un eseguibile esterno** per manifestarsi.

### Worm

Un worm è invece un programma autonomo, capace di funzionare senza legarsi a un file ospite. È un software eseguibile che si replica e si diffonde automaticamente, sfruttando le reti di comunicazione e le vulnerabilità dei sistemi. A differenza del virus, non richiede azioni da parte dell’utente per propagarsi: può duplicarsi da solo e colonizzare rapidamente numerosi dispositivi. La sua forza è proprio l’**indipendenza e la velocità di diffusione**.

### Differenza chiave

La differenza cruciale tra virus e worm sta dunque nell’architettura del codice: il virus è parassitario e necessita di un ospite, mentre il worm è autonomo e auto-propagante. Questa distinzione inaugura la prima classificazione delle minacce informatiche e spiega perché, con la diffusione di Internet, i worm siano diventati particolarmente pericolosi e difficili da contenere.

---
### Trojan

Il termine _cavallo di Troia_ richiama l’idea del programma che si presenta come innocuo o utile, ma che in realtà nasconde al suo interno un fine malevolo. A differenza di virus e worm, il Trojan non punta principalmente all’autopropagazione, ma a **infiltrarsi inosservato** in un sistema per portare a termine azioni dannose.

Le azioni tipiche comprendono:

- eliminazione o blocco di dati,
    
- modifica non autorizzata delle informazioni,
    
- copia nascosta dei file,
    
- riduzione delle prestazioni del sistema o della rete, fino al loro collasso.
    

Molti Trojan agiscono come strumenti di spionaggio: leggono le password, registrano i tasti premuti (keylogging), intercettano comunicazioni o aprono la porta ad altri malware, trasformando il computer in una macchina controllata da remoto. In alcuni casi possono persino prendere in ostaggio l’intero sistema, preludio a forme di ricatto come i ransomware.

Spesso vengono diffusi mascherati da software legittimi o versioni di prova di programmi, così da raccogliere informazioni sensibili senza che l’utente sospetti nulla. La caratteristica chiave, quindi, è l’**inganno**: il Trojan non si diffonde autonomamente, ma convince la vittima ad aprirgli la strada.

---
### Cyberwarfare

Con il termine _cyberwarfare_ si indica la guerra cibernetica, cioè l’impiego dello spazio informatico come vero e proprio **scenario di conflitto militare**. Dopo i domini tradizionali — terra, mare, aria e spazio — il cyberspazio viene infatti riconosciuto come il **quinto dominio operativo**. La sua peculiarità è di rappresentare oggi uno degli strumenti più efficaci e meno costosi per condurre azioni di guerra, con conseguenze che possono essere devastanti quanto quelle di un attacco convenzionale.

Tutte le nazioni considerano ormai strategiche sia le capacità difensive sia quelle offensive nel cyberspazio. La guerra informatica si inserisce in un contesto di conflitti internazionali complessi, in cui attacchi digitali possono colpire infrastrutture critiche, reti militari, sistemi di comunicazione, apparati industriali e persino manipolare l’opinione pubblica attraverso campagne di disinformazione.

Un documento ufficiale di carattere istituzionale — redatto congiuntamente da Senato, Camera dei deputati e Ministero degli Affari Esteri — sottolinea come anche l’Italia e l’Alleanza Atlantica abbiano riconosciuto, negli ultimi quindici anni, l’importanza della difesa cibernetica. L’approccio della NATO si è evoluto fino a stabilire che un attacco cyber possa essere equiparato, per gravità, a un attacco armato tradizionale. In questo caso, può scattare la **difesa collettiva prevista dall’articolo 5 del Trattato di Washington**, con l’obbligo per gli Stati membri di reagire come se uno di loro fosse stato colpito da un attacco militare diretto.

Per raggiungere questi obiettivi, la NATO ha istituito **comitati, agenzie specializzate e centri operativi**, integrando il dominio cibernetico nelle operazioni militari e nello sviluppo delle capacità difensive dei Paesi membri. Parallelamente, sono stati costruiti partenariati strategici con l’Unione Europea e con il settore privato, poiché la sicurezza delle reti digitali non riguarda solo l’ambito militare, ma l’intero tessuto economico e civile.

In sintesi, la cyberwarfare mostra come il cyberspazio sia ormai un’arena in cui si esercitano potere, deterrenza e strategia geopolitica, al pari dei tradizionali scenari di guerra fisica.


---

### Trans-Siberian Pipeline: il primo cyber-sabotaggio della Guerra Fredda

Siamo in **Siberia, giugno 1982**. Un satellite americano rileva una gigantesca esplosione lungo il gasdotto Trans-Siberian Pipeline. L’episodio passerà alla storia come il primo caso documentato di _cyber sabotage_ in un contesto di conflitto geopolitico. Nonostante non sia del tutto chiaro se l’esplosione sia stata provocata direttamente dall’attacco, è certo che una catena di eventi scatenati da un’operazione della **CIA** abbia portato a quel disastro.

#### Il contesto della Guerra Fredda

Negli anni ’70 e ’80 gli Stati Uniti erano impegnati a colpire i punti deboli dell’economia sovietica. L’URSS dipendeva dalle tecnologie occidentali, e per procurarsele mise in campo una vera macchina di spionaggio industriale. Nel **1970 il KGB creò il Direttorato T**, una sezione incaricata di rubare innovazioni scientifiche e tecnologiche dall’Occidente.

All’interno di questo apparato operava il **colonnello Vladimir Vetrov**, che in seguito tradì Mosca passando informazioni all’intelligence francese e poi a quella americana. Tra il 1981 e il 1982 Vetrov consegnò oltre **4.000 documenti segreti**, inclusi elenchi dettagliati delle tecnologie ricercate dall’URSS per ammodernare i propri sistemi industriali e militari.

#### La “lista della spesa” sovietica e il software SCADA

Tra i materiali desiderati c’era anche il **software SCADA (Supervisory Control And Data Acquisition)**, fondamentale per gestire e automatizzare i gasdotti, inclusa la nuova infrastruttura siberiana. A causa delle pressioni americane, i Paesi europei si rifiutavano di venderlo ai sovietici. Ma grazie alle informazioni di Vetrov, il KGB riuscì a procurarsi una copia presso una società canadese.

Quello che i sovietici non sapevano era che quel software era stato **già compromesso dalla CIA**. Nascosto al suo interno vi era un **Trojan** progettato per attivarsi dopo una fase di funzionamento regolare.

#### Il sabotaggio digitale

Come raccontato da Thomas C. Reed, ex segretario dell’Aeronautica degli Stati Uniti, il codice malevolo era programmato per manipolare i parametri di sicurezza del gasdotto. Dopo un periodo iniziale in cui il sistema SCADA operava normalmente, il Trojan impartì comandi che spinsero **valvole, turbine e pompe** ad aumentare la pressione nelle condutture oltre i limiti tollerabili da giunzioni e saldature.

Il risultato fu la **rottura catastrofica** di un tratto di pipeline, con un’esplosione visibile persino dai satelliti americani.

#### Significato storico

L’attacco al Trans-Siberian Pipeline è un esempio emblematico di come il cyberspazio sia stato usato sin dagli albori come **arma strategica**. Non si trattava di un semplice malware, ma di un’operazione di guerra ibrida, in cui intelligence, ingegneria del software e obiettivi geopolitici si intrecciavano. Era la dimostrazione che un computer poteva diventare l’innesco di un’arma esplosiva.

---
### Morris Worm (1988) — anatomia dell’incidente

**Contesto e intenti.**  
Il 2 novembre 1988 Robert Tappan Morris, studente alla Cornell, rilascia un programma pensato per misurare “quanto fosse grande e insicura” la rete allora esistente. Il codice è un **worm**: si replica e si diffonde tra macchine connesse senza richiedere azioni dell’utente.

**Catena di diffusione (alto livello).**

1. Il worm raggiunge una macchina bersaglio su rete.
    
2. Sfrutta vulnerabilità del sistema **UNIX** per ottenere esecuzione di codice.
    
3. Una volta entrato, si **copia** sulla macchina infetta ed esegue un processo locale.
    
4. Avvia la **scansione** di altri host raggiungibili e ripete il ciclo.
    

**Il controllo “sei già infetto?” e il bug 1/7.**  
Per evitare reinfezioni infinite, il worm **controllava** se sulla macchina fosse già in esecuzione una sua copia. Tuttavia, per aumentare la probabilità di diffusione anche in presenza di risposte inaffidabili o contromisure, l’autore inserì una forzatura: **anche se la macchina risultava già infetta, in circa 1 caso su 7 il worm si installava comunque**.  
Effetto collaterale devastante: su molte macchine si accumularono **più processi identici**, che si replicavano di continuo, fino a **saturare CPU, memoria, tabella dei processi e banda** → sistemi di fatto inutilizzabili.

**Impatto osservato.**  
Il comportamento “aggressivo” portò al **collasso di ~6.000 macchine**, in gran parte appartenenti a istituzioni pubbliche e accademiche, con **danni anche economici** e interruzioni di servizio rilevanti.

**Perché fece così male (fattori abilitanti).**

- Il worm era **autonomo**: non serviva un’azione dell’utente per propagarsi.
    
- La rete dell’epoca era poco segmentata e le pratiche di **patching** e monitoraggio erano immature.
    
- La scelta di **reinfezione probabilistica** (1/7) trasformò un test di diffusione in un **Denial of Service di massa**.
    

**Lezioni tecniche chiave.**

- Nei sistemi autoreplicanti servono **limiti espliciti**: rate-limiting, backoff, kill-switch.
    
- La verifica d’infezione deve essere **idempotente** (reinfezione evitata al 100%).
    
- Hardened defaults: ridurre superfici d’attacco, aggiornare tempestivamente, segmentare la rete.
    
- Osservabilità: **logging** e **telemetria** per riconoscere in fretta pattern di propagazione anomala.
    

**Sintesi in una riga.**  
Il Morris Worm è lo spartiacque che mostra come un worm, anche senza payload distruttivo esplicito, possa **abbattere la disponibilità** su larga scala semplicemente sfruttando la propria **replicazione incontrollata**.


---
### Riepilogo

Questa lezione ha tracciato le prime tappe della storia degli attacchi informatici, mostrandone l’evoluzione e gli impatti.

- **Cronologia degli incidenti**: dal worm Creeper (1972) al primo spam (1978), dal virus Elk Cloner (1982) all’attacco CIA al gasdotto siberiano, fino al Morris Worm (1988). Una linea temporale che segna il passaggio dagli esperimenti pionieristici alle minacce reali e devastanti.
    
- **Virus e Worm**: due archetipi fondamentali. Il virus vive come parassita all’interno di un programma ospite, mentre il worm è autonomo, si replica e si diffonde attraverso la rete senza bisogno dell’intervento umano.
    
- **Trojan**: il “cavallo di Troia” digitale che inganna l’utente, nasconde fini malevoli e apre la strada a furti di dati, keylogging, compromissione dei sistemi e infezioni ulteriori.
    
- **Cyberwarfare**: lo spazio informatico come quinto dominio militare, al pari di terra, mare, aria e spazio. Gli attacchi digitali vengono ormai considerati potenzialmente equivalenti ad attacchi armati e possono attivare meccanismi di difesa collettiva come l’articolo 5 della NATO.
    
- **Attacco al Trans-Siberian Pipeline (1982)**: primo esempio storico di sabotaggio informatico con impatto fisico su larga scala. Il Trojan inserito nel software SCADA dimostra come un’arma digitale possa generare conseguenze esplosive nel mondo reale.
    
- **Morris Worm (1988)**: il primo incidente globale della storia di Internet, con 6.000 sistemi compromessi. Un caso che evidenzia come la sola replicazione incontrollata, senza payload distruttivi, sia sufficiente a paralizzare reti e istituzioni.