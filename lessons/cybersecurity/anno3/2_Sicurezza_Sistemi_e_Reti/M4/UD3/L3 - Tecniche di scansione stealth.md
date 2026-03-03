## **Lezione 3: Tecniche di scansione stealth**

### **1. Introduzione — obiettivo della lezione**

Questa lezione spiega le tecniche di _stealth scanning_: metodi che cercano di raccogliere informazioni sulle porte e i servizi riducendo la probabilità di essere rilevati o loggati. L’approccio è Feynman-style: spieghiamo il principio fisico/protocolare, mostriamo il comportamento osservabile nella rete, forniamo comandi pratici e discutiamo limiti, falsi positivi e contromisure.

### **2. Panoramica concettuale**

#### **2.1 Che significa “stealth”**

Stealth non significa invisibile al 100%, ma ridurre la visibilità rispetto a una `connect()` normale: evitare handshakes completi, mimare traffico legittimo o sfruttare differenze di implementazione degli stack TCP/IP per ottenere risposte informative senza aprire sessioni tracciabili.

#### **2.2 Trade-off**

- **Pros:** minore traccia nei log, possibilità di aggirare firewall semplici, meno rumore per IDS.
    
- **Cons:** tecniche più delicate, soggette a false positive/negative, dipendono da implementazioni diverse degli OS e dai comportamenti dei firewall; talvolta lente e non affidabili in presenza di packet-loss.
    

### **3. SYN/ACK stealth e inverse mapping**

#### **3.1 Principio**

Invece di inviare un SYN (come in un SYN scan standard), si inviano pacchetti con flag diversi (es. SYN/ACK) e si interpreta la reazione inverse: alcune implementazioni rispondono diversamente se la porta è chiusa o aperta. L’idea è ottenere informazioni **“inverse”**: usare risposte tipiche delle porte **chiuse** per dedurre lo stato delle porte aperte.

#### **3.2 Comportamento osservabile**

- Per una porta **chiusa** il target risponde tipicamente con **RST**.
    
- Per una porta **aperta** spesso **non risponde** a pacchetti SYN/ACK non attesi (comportamento usato come segnale di apertura).  
    Questo ribalta la logica classica: la **assenza** di risposta può indicare `open` in questo tipo di probe.
    

#### **3.3 Limiti**

Perdita di pacchetti e filtraggio possono generare falsi positivi. Inoltre, molti IDS sono addestrati a riconoscere probe insolite come SYN/ACK anomali.

### **4. ACK scan (mappatura firewall)**

#### **4.1 Scopo**

Non pensare all’ACK scan per rilevare porte aperte: serve a determinare **se esiste un filtro/firewall** e la sua natura (stateful vs stateless).

#### **4.2 Meccanica e interpretazione**

- Si invia un pacchetto **TCP ACK** a una porta target.
    
- Risposte possibili:
    
    - **RST** → la porta è raggiungibile ma _unfiltered_ (cioè il firewall non sta bloccando).
        
    - **Nessuna risposta** o **ICMP unreachable** → la porta è _filtered_ (bloccata da firewall).  
        Questo permette di mappare la presenza di filtri senza aprire connessioni.
        

#### **4.3 Uso pratico (nmap)**

`nmap -sA <target>` — utile come passo diagnostico per capire se un firewall è presente.

### **5. Window scan (sfruttare valori TCP window)**

#### **5.1 Principio**

Simile all’ACK scan ma sfrutta un dettaglio implementativo: il valore del campo TCP **Window** nel RST di risposta può differire se la porta è `open` oppure `closed` su alcuni sistemi.

#### **5.2 Interpretazione pratica**

- RST con **Window non-zero** → spesso indica **open** (implementazioni specifiche usano window > 0 per alcune risposte).
    
- RST con **Window zero** → indica **closed**.
    
- Nessuna risposta o ICMP → **filtered**.  
    Non tutti gli OS distinguono: su sistemi che non espongono questa differenza il risultato sarà `closed` o ambiguo.
    

#### **5.3 Avvertenza**

Metodo dipendente dall’implementazione: va usato come test secondario più che principale.

### **6. FIN / NULL / Xmas scans (flag-trick scans)**

#### **6.1 Idea semplice**

Si inviano pacchetti con flag atipici:

- **FIN**: solo FIN=1.
    
- **NULL**: nessun flag.
    
- **Xmas**: FIN, PSH e URG a 1 (luccicante come un albero di Natale).
    

Secondo la specifica TCP, host _chiusi_ rispondono con **RST**, mentre host _aperti_ dovrebbero **non rispondere** a questi pacchetti isolati. Quindi: RST → `closed`, nessuna risposta → `open|filtered`.

#### **6.2 Vantaggi**

- Non si instaurano sessioni TCP complete (meno log).
    
- Possono bypassare firewall o filtri che bloccano SYN/ACK ma non questi tipi di pacchetti.
    

#### **6.3 Limiti pratici**

- Alcuni OS (es. Windows) non rispettano lo standard e rispondono diversamente: rendono queste scansioni inefficaci su tali target.
    
- Packet loss o filtri possono generare ambiguità (open|filtered).
    
- IDS moderni spesso rilevano questi pattern.
    

#### **6.4 Comando nmap**

- FIN: `nmap -sF <target>`
    
- NULL: `nmap -sN <target>`
    
- Xmas: `nmap -sX <target>`
    

### **7. Fragmentation scan (evasione via frammentazione IP)**

#### **7.1 Principio**

Si frammentano le probe in piccoli datagrammi IP in modo che il contenuto utile (es. i flag TCP) sia spezzettato. Alcuni IDS/firewall che analizzano pacchetti sul singolo frammento non riescono a ricostruire il contesto e quindi non riconoscono la probe come malevola.

#### **7.2 Vantaggi**

- Aumenta la difficoltà per il detection e il blocking basati su signature.
    
- Può aggirare filtri superficiali che non riassemblano i frammenti.
    

#### **7.3 Svantaggi**

- Lento e soggetto a packet loss.
    
- Alcuni firewall o IDS possono andare in crash se non progettati per gestire frammenti malevoli.
    
- Non affidabile su tutte le piattaforme; può essere controproducente su reti con frammentation filtering.
    

#### **7.4 Esempio**

`nmap --mtu 16 -sS <target>` (forza piccole unità di MTU; attenzione alla legittimità e agli effetti collaterali).

### **8. Idle (zombie) scan — anonimato completo**

#### **8.1 Concetto chiave**

L’idle scan permette di eseguire una scansione **che appare provenire da uno “zombie”** (terzo host innocuo) invece che dallo scanner reale. Si basa sul comportamento del campo IP **ID** (IP identification): molti sistemi incrementano l’IP ID per ogni pacchetto inviato.

Requisiti per lo zombie:

- Debe essere _idle_ (non invia traffico che altera l’IP ID frequentemente).
    
- Deve avere un IP ID prevedibile/incrementale.
    

#### **8.2 Procedura passo-passo (semplificata)**

1. Lo scanner interroga lo zombie per ottenere l’IP ID corrente (es. inviando SYN/ACK e osservando il RST con IPID = X).
    
2. Lo scanner invia una probe verso la **vittima** ma **spoofa** l’indirizzo sorgente come quello dello zombie (es. SYN indirizzato al target con src=IP_zombie).
    
3. Se la porta target è **open**, il target risponderà con SYN/ACK allo zombie. Lo zombie, non avendo inviato un SYN, risponderà con RST e incrementerà il proprio IP ID.
    
4. Lo scanner interroga di nuovo lo zombie per leggere l’IP ID. Se l’IP ID è aumentato di 2 (o di 1 a seconda della sequenza), lo scanner deduce che il target ha inviato un pacchetto allo zombie: la porta è `open`. Se non è aumentato, la porta è `closed`.
    

#### **8.3 Vantaggi**

- Scansione praticamente **anonima**: il traffico verso la vittima sembra provenire dallo zombie.
    
- Nessuna connessione diretta tra scanner e target: ottima per evitare contromisure.
    

#### **8.4 Limiti e controindicazioni**

- Richiede uno zombie con IP ID prevedibile e poco traffico.
    
- Se lo zombie non è veramente idle, il rumore rompe l’analisi.
    
- Molti sistemi moderni usano IP ID casuale o per flusso, rendendo la tecnica inefficace.
    
- Alcuni IDS correlano i pattern e rilevano comportamenti sospetti anche in presenza di idle scan.
    

#### **8.5 Comando (nmap)**

`nmap -sI <zombie> <target>` — usa lo zombie specificato.

### **9. Rilevazione di stealth scan e contromisure**

#### **9.1 Come rilevarli**

- **Pattern anomali**: sequenze di pacchetti con flag inusuali (FIN/NULL/Xmas), SYN/ACK dal nulla, frammentazione irregolare.
    
- **Correlazione temporale**: richieste sparse ma sistematiche su molte porte o IP → tipico di low-and-slow scans.
    
- **Anomalie IP ID**: variazioni sospette nei campi IPID (utile se si controlla un potenziale zombie).
    
- **IDS signature + behavioural analysis**: combinare signature (es. Suricata/ Snort) con modelli di comportamento (SIEM).
    

#### **9.2 Contromisure pratiche**

- **Firewall stateful**: bloccare pacchetti non attesi e rate-limitare probe sospette.
    
- **Riassemblaggio e normalizzazione**: firewall/IDS che riassemblano frammenti e normalizzano il traffico prima di ispezionarlo.
    
- **Honeypot/Tarpit**: deviare gli scanner su ambienti controllati che raccolgono informazioni senza impatto alla produzione.
    
- **Hardening OS**: aggiornare gli stack TCP/IP per evitare implementazioni ambigue (es. IP ID prevedibile).
    
- **Logging e correlazione**: centralizzare eventi e impostare regole per blocchi dinamici (fail2ban, ipset).
    

### **10. Best practice operative per chi difende (Feynman checklist)**

#### **10.1 Non fidarti di un singolo segnale**

Combina evidenze: risposta RST, assenza di risposta, comportamento del window, ICMP, pattern temporale.

#### **10.2 Normalizza prima di analizzare**

Assicurati che i dispositivi di sicurezza facciano riassemblaggio dei frammenti e rimuovano evasion attempts.

#### **10.3 Riduci superficie informativa**

Elimina banner, disabilita servizi non necessari, non esporre host inutilmente.

#### **10.4 Monitora e rispondi automaticamente**

Regole automatiche per bloccare scansioni ripetute e per inviare alert contestuali agli operatori.

### **11. Esempi pratici (comandi e note interpretative)**

- **SYN/ACK stealth (nmap)** — non esiste un’opzione diretta in nmap per inviare SYN/ACK puro come probe, ma si possono usare pacchetti grezzi con `hping3` per esperimenti (solo in laboratorio):  
    `hping3 -S -p 80 --ack <target>` (es. costruire pacchetti atipici; uso con cautela).
    
- **ACK scan (nmap)**: `nmap -sA <target>` → mappa firewall.
    
- **Window scan (nmap)**: `nmap -sW <target>` → sfrutta il campo window per dedurre open/closed su sistemi vulnerabili.
    
- **FIN/NULL/Xmas**: `nmap -sF` / `nmap -sN` / `nmap -sX <target>` → utile per bypassare filtri semplici (attenzione alle incompatibilità OS).
    
- **Fragmentation**: `nmap -f <target>` o `--mtu` per aggiustare frammentazione; test in lab.
    
- **Idle scan**: `nmap -sI <zombie> <target>` → anonimizza la scansione (richiede zombie idoneo).
    

### **12. Sintesi e raccomandazioni finali**

- Le tecniche stealth sono potenti ma fragili: funzionano solo contro determinati stack/filtri e sono sensibili a packet loss e rumore di rete.
    
- Per il difensore: assumere che l’attaccante utilizzi stealth e progettare rilevamento multilivello (normalizzazione, correlazione, rate limiting).
    
- Per lo studente/praticante: testare queste tecniche **solo** in ambienti controllati e con permesso; padroneggiare interpretazione dei risultati è molto più importante che imparare i comandi.