# L4 — Vulnerabilità, Minacce e Attacchi nel Cyberspazio

---

### **1. Tre Concetti Fondamentali: Vulnerabilità, Minaccia, Attacco**

Prima di classificare le minacce specifiche del cyberspazio, è essenziale distinguere con precisione tre termini che vengono spesso confusi.

> 📌 **Vulnerabilità:** una **debolezza del sistema** che potrebbe permettere violazioni alla sicurezza e causare danni. Esempi: protezione della rete inadeguata, dipendenza da un'unica fonte di alimentazione, assenza di controllo degli accessi.

> 📌 **Minaccia:** una **circostanza o evento** che potrebbe causare violazioni alla sicurezza. Esempi: un furto fisico, il malcontento di un dipendente.

> 📌 **Attacco:** un **evento deliberato** che sfrutta una vulnerabilità del sistema per violarne la sicurezza.

**Analogia — La diga di montagna:**
- La **crepa nella diga** è la *vulnerabilità*.
- Il fatto che un pezzo di montagna possa cadere nel bacino, provocando un innalzamento dell'acqua e un eventuale traboccamento (come nel caso della diga del Vajont), è la *minaccia*.
- L'inserimento di una carica di dinamite ai piedi della montagna per provocare intenzionalmente il crollo è l'*attacco*.

---

### **2. Attacchi Dolosi e Non Dolosi**

Gli attacchi si classificano in due grandi categorie:

#### **2.1. Attacchi Non Dolosi**

Non esiste volontà esplicita. Le cause includono:
- **Errore umano** — es. bug introdotto involontariamente durante la scrittura del codice.
- **Errori hardware/software** — spesso a loro volta causati da errori umani nella fase di progettazione o programmazione.
- **Disastri naturali** — es. il terremoto dell'Aquila, che ha causato la perdita di dati aziendali non adeguatamente replicati in sedi remote.

#### **2.2. Attacchi Dolosi**

Esiste un'intenzione deliberata. Esempi:
- Intrusione in un sistema dopo aver sottratto le credenziali di accesso.
- Sabotaggio di un sistema.
- Modifica o falsificazione dei dati.
- Ricerca fraudolenta di dati riservati.

---

### **3. Minacce del Cyberspazio: Analogie e Novità rispetto al Mondo Reale**

Alcune minacce informatiche sono le stesse del mondo fisico: esistono leggi, non tutti le rispettano, e alcuni individui le violano per impossessarsi illecitamente di risorse economiche. Anche i crimini informatici hanno spesso una motivazione di **guadagno economico**.

Tuttavia, il cyberspazio presenta delle **caratteristiche intrinseche** che generano categorie di minacce del tutto nuove.

#### **3.1. Automazione**

L'automazione è una delle caratteristiche che distingue maggiormente le minacce informatiche da quelle del mondo reale:

- **Scalabilità degli attacchi economici:** è possibile sottrarre frazioni minime di valuta (anche pochi centesimi) a un numero enorme di utenti. Individualmente il danno per la vittima è quasi impercettibile, ma aggregato su migliaia di bersagli diventa un guadagno significativo per l'attaccante.
- **Persistenza non rilevata:** alcuni malware non lasciano tracce visibili; la vittima non si accorge dell'attacco, che può quindi continuare nel tempo indisturbato.
- **Profilazione degli utenti:** la raccolta e l'analisi automatica di grandi quantità di dati permette di profilare le abitudini degli utenti — una violazione diretta della privacy.
- **Replica di attacchi a bassa probabilità:** anche un attacco con probabilità di successo molto bassa diventa redditizio se replicato automaticamente migliaia di volte.

#### **3.2. Assenza di Confini Geografici**

Internet non ha confini naturali: chiunque, da qualsiasi parte del mondo, potrebbe voler attaccare il sistema da proteggere. Questo genera ulteriori problemi:

- **Incertezza giurisdizionale:** non è sempre chiaro di chi sia la competenza su un crimine informatico transnazionale.
- **Conflitti tra legislazioni nazionali:** le leggi variano da paese a paese e sono spesso in conflitto tra loro. Il caso di Onel de Guzmán (virus I Love You) ne è l'esempio emblematico: il reato non era punito nelle Filippine.
- **Insufficienza delle leggi internazionali:** ad oggi le norme internazionali sul crimine informatico non sono ancora adeguate; il fatto che un crimine venga perseguito o meno dipende spesso dalla bravura degli avvocati coinvolti, oltre che dalla fortuna.

#### **3.3. Disponibilità Pubblica delle Tecniche di Attacco**

I dettagli su come compiere un attacco informatico sono spesso **facilmente e rapidamente reperibili sul web**: chiunque può pubblicare su un sito o forum la descrizione di un attacco già sperimentato, corredata dallo script che lo esegue. Questo implica che:

- Non è necessario essere un esperto per lanciare un attacco: basta essere uno *script kiddie* ed eseguire il codice scaricato dalla rete.
- La barriera d'ingresso al crimine informatico si è drasticamente abbassata rispetto ai crimini fisici.

---

### **4. Vulnerabilità Intrinseche del Cyberspazio**

Oltre alle minacce legate al comportamento degli attaccanti, il cyberspazio presenta **vulnerabilità strutturali** radicate nelle sue caratteristiche tecniche.

#### **4.1. Internet come Canale di Comunicazione Insicuro**

L'utilizzo di Internet come mezzo di comunicazione è la prima e più rilevante vulnerabilità intrinseca. Questo argomento sarà approfondito nel modulo dedicato ai protocolli di comunicazione sicura. Le implicazioni immediate sono:

- Il sistema da difendere diventa **estremamente grande, complesso e distribuito** — non centralizzato, dunque più difficile da gestire.
- La rete è eterogenea: vi partecipano host e reti con caratteristiche diverse.
- È possibile **interagire involontariamente con host malevoli** — es. aprire una sessione di protocollo con un server ostile senza saperlo, oppure scaricare software infetto da un sito non fidato.

#### **4.2. Monocultura Tecnologica**

Nonostante il cyberspazio sia composto da milioni di utenti, l'hardware, i sistemi operativi, il software e le applicazioni utilizzate sono spesso **le stesse su larga scala** — una condizione definita *monocultura tecnologica*.

Le aziende tendono ad adottare componenti *Commercial Off The Shelf* (COTS), personalizzandoli superficialmente ma mantenendo un core tecnologico comune. La conseguenza è che **lo stesso attacco può essere replicato senza modifiche** su un numero enorme di bersagli.

#### **4.3. Pressione sul Time-to-Market e Costo della Sicurezza**

Nel mercato attuale, il primato competitivo spinge le aziende a **rilasciare i prodotti il prima possibile**, spesso prima che il processo di testing sia completo. Il debugging avviene frequentemente quando il prodotto è già in uso dagli utenti finali, grazie al loro feedback.

A questo si aggiunge il fatto che la sicurezza ha un costo diretto:
- **Misure preventive** (antivirus, firewall, IDS, ecc.) richiedono investimenti economici.
- Come conseguenza, molti sistemi vengono distribuiti con **bug non corretti** e **senza misure preventive adeguate**, risultando significativamente più vulnerabili.

> ⚠️ Alcuni linguaggi di programmazione sono intrinsecamente più predisposti a certi tipi di bug rispetto ad altri (es. gestione manuale della memoria in C/C++ → buffer overflow). Sistemi scritti in tali linguaggi senza adeguati controlli sono più esposti ad attacchi.

---

### **5. Riepilogo**

> ✅ **Vulnerabilità** è la debolezza del sistema; **minaccia** è l'evento che potrebbe sfruttarla; **attacco** è l'evento deliberato che la sfrutta. Gli attacchi si dividono in dolosi (intenzionali) e non dolosi (errori, disastri). Il cyberspazio introduce minacce nuove rispetto al mondo reale — automazione, assenza di confini geografici, disponibilità pubblica delle tecniche d'attacco — e presenta vulnerabilità strutturali proprie: il canale insicuro di Internet, la monocultura tecnologica e la pressione economica che penalizza gli investimenti in sicurezza.
