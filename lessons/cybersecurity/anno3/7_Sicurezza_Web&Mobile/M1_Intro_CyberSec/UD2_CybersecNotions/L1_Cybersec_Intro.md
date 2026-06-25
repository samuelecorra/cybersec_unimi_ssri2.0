# L1 — Introduzione alla Sicurezza Informatica

---

### **1. Domande di Partenza**

Questa lezione si propone di rispondere ad alcune domande fondamentali strettamente correlate al problema della sicurezza informatica:

- Quali sono i **pericoli che affliggono i sistemi digitali** rispetto al mondo reale?
- Perché la sicurezza informatica **non è un problema esclusivamente militare** ma riguarda chiunque utilizzi tecnologia?

Per rispondervi, analizziamo una serie di attacchi e incidenti informatici reali, selezionati da notizie e quotidiani degli ultimi anni, che coprono scenari eterogenei ma tutti riconducibili al dominio della sicurezza informatica.

---

### **2. Casi Reali di Crimine Informatico**

#### **2.1. Furto di Dispositivi Fisici**

Un primo esempio di crimine informatico è il **furto di apparecchiature elettroniche** — computer portatili, smartphone, navigatori GPS.

Alcuni dati esemplificativi:

- **2008 — USA:** oltre **100.000 furti di computer portatili** registrati.
- **2012 — Londra:** in media **350 dispositivi mobili** (smartphone o tablet) rubati ogni giorno.
- **2006–2008 — USA:** i furti di navigatori GPS sono cresciuti del **700%**.

> ⚠️ Il danno non è solo economico (valore del dispositivo). Il danno principale risiede spesso nella **perdita o compromissione dei dati** contenuti nel dispositivo: fotografie personali, file di lavoro, informazioni confidenziali.

**Caso eclatante:** Un dipendente dell'associazione dei militari veterani americani (US Department of Veterans Affairs) portò a casa il computer aziendale, contenente **26,5 milioni di record** con informazioni strettamente riservate sui veterani: numeri di previdenza sociale (*Social Security Number*), indirizzi e altri dati sensibili. Oltre al rilascio di dati confidenziali, l'incidente avrebbe avuto conseguenze ancora più gravi in assenza di un backup aggiornato.

---

#### **2.2. Internet come Canale Insicuro — Il Problema della Comunicazione Sicura**

Internet è la **rete mondiale di reti di computer** che consente a miliardi di dispositivi di comunicare. È oggi il mezzo di comunicazione più diffuso al mondo.

Tuttavia, trattandosi di un **canale condiviso** tra milioni di utenti simultanei, è per definizione un **canale insicuro**.

Formalizziamo il problema con il classico modello **Alice e Bob**: due utenti vogliono comunicare su Internet. Il problema di sicurezza si articola in tre sotto-domande:

1. Possono comunicare senza che **nessuno legga** i loro messaggi?
2. Possono comunicare senza che **nessuno modifichi** i loro messaggi?
3. Possono avere la **certezza di parlare effettivamente l'uno con l'altro** e non con un impostore?

> 💡 **Analogia con il mondo fisico:** Nel mondo reale basta chiudere la busta: se la busta è ancora sigillata, nessuno ha letto o modificato il contenuto. Se Bob riconosce la firma di Alice, è certo che sia lei l'autrice. Il problema è trasportare queste garanzie nel mondo digitale — e questo è uno dei temi centrali del corso (Modulo 3: comunicazione sicura su canale insicuro).

---

#### **2.3. Denial of Service (DoS) — Indisponibilità di un Servizio**

Un'altra categoria di attacco riguarda la **mancanza di disponibilità** di un servizio per gli utenti autorizzati: si tratta del **Denial of Service (DoS)**, uno degli attacchi più comuni ai server web.

**Esempio reale (Repubblica, dicembre 2009):** Dopo essere rimasto online per poche ore, il sito del capitano della Roma **Francesco Totti** — largamente pubblicizzato — fu oscurato e rimase inaccessibile per diversi giorni. L'attacco impedì agli utenti di partecipare ai forum e alle discussioni che erano stati annunciati.

> 📌 Il DoS non sottrae né altera dati: colpisce la **disponibilità** del servizio, impedendo l'accesso a utenti legittimi. Sarà approfondito nella parte del corso dedicata alla sicurezza dei sistemi web.

---

#### **2.4. Perdita di Dati per Eventi Catastrofici — Attacco alle Torri Gemelle (11 settembre 2001)**

L'immagine del **World Trade Center** dopo l'attacco dell'11 settembre 2001 illustra un'altra categoria di incidente: l'**evento doloso inatteso** con impatto devastante sui dati.

Alcune aziende con sede in una delle torri **persero tutti i propri dati** perché i server di backup si trovavano nella stessa torre o al massimo in un ufficio adiacente — non in una sede geograficamente remota. Il problema non era l'assenza di backup, ma il fatto che le politiche di **disaster recovery** non prevedevano un evento di tale portata, rendendo il backup geograficamente remoto non parte del piano di sicurezza.

**Evento analogo per conseguenze:** il **terremoto de L'Aquila** (2009) — evento non doloso ma ugualmente distruttivo — ha prodotto conseguenze simili per le organizzazioni locali prive di backup remoti.

---

#### **2.5. Furto di Credenziali — Account Gmail (2009)**

Un caso del 2009 riguardante il **furto di username e password** di account Gmail dimostra come anche le credenziali di autenticazione siano un bersaglio primario dei criminali informatici, non soltanto i dati contenuti nei dispositivi fisici.

---

#### **2.6. Bug del Software — Esplosione del Razzo Ariane 5 (4 giugno 1996)**

Anche i **difetti nel software** rientrano a pieno titolo nei problemi di sicurezza informatica, con conseguenze che possono essere fisicamente devastanti.

**Il caso Ariane 5:** Il razzo europeo Ariane 5, lanciato il 4 giugno 1996, si disintegrò dopo soli **39 secondi** dal decollo. La causa fu un bug nel software ereditato da **Ariane 4**, di cui Ariane 5 riutilizzava parte del codice.

Meccanismo del fallimento:
1. Un valore a **64 bit in virgola mobile** (rappresentante la velocità orizzontale rispetto alla piattaforma di lancio) veniva convertito in un **intero a 16 bit con segno**.
2. Il valore era troppo grande per essere rappresentato in 16 bit: la conversione generò un'**eccezione**.
3. L'eccezione, per ragioni di efficienza, **non veniva gestita** nel codice.
4. L'eccezione non gestita scatenò una **cascata di reazioni** nel sistema di controllo, causando una deviazione della traiettoria.
5. Le forze aerodinamiche conseguenti distrussero il razzo.

> ⚠️ Questo esempio mostra come un bug apparentemente banale — una conversione di tipo senza controllo dell'overflow — possa avere conseguenze catastrofiche in sistemi critici. Il riuso di codice senza adeguata verifica delle nuove condizioni operative è una delle cause più insidiose di vulnerabilità software.

---

### **3. Obiettivi degli Attacchi Informatici — Sintesi**

Dopo questa carrellata di casi reali, possiamo identificare i **tre bersagli principali** degli attacchi a sistemi informatici:

| Bersaglio | Obiettivo dell'attaccante |
|-----------|--------------------------|
| **Hardware** | Distruggere o rubare le apparecchiature fisiche |
| **Software** | Rubare il software oppure modificarlo (es. installare malware che rilasci informazioni confidenziali) |
| **Dati** | Cancellare, leggere o modificare dati — anche sensibili — da parte di soggetti non autorizzati |

---

### **4. Definizione Informale di Sicurezza Informatica**

> 📌 **Definizione:** Nel linguaggio comune, il termine *sicurezza* indica l'**assenza di rischio o pericolo**. In ambito informatico, la **sicurezza informatica** riguarda la **prevenzione e la protezione** contro l'accesso, la distruzione o la modifica di **risorse o informazioni** da parte di **utenti non autorizzati**.

Questa definizione copre le tre categorie di crimini informatici descritte:
- Attacchi all'**hardware** (distruzione/furto fisico).
- Attacchi al **software** (modifica o furto del codice).
- Attacchi ai **dati** (lettura, modifica o cancellazione non autorizzata).

---

### **5. Perché la Sicurezza Informatica Ci Riguarda Tutti**

La domanda iniziale — *"perché ci interessa, non è roba da militari?"* — trova risposta nel mutato ruolo strategico dell'informazione nella società contemporanea.

L'**informazione** è oggi una **risorsa strategica** per qualsiasi organizzazione — pubblica o privata. Un uso imprevisto o non autorizzato dei dati può avere **conseguenze economiche disastrose**, non solo per il singolo utente colpito, ma per l'intera organizzazione.

**Esempio concreto:** Supponiamo che vengano trafugati migliaia di numeri di carta di credito dal sistema di un circuito come *CartaSì*:
- Il **singolo titolare** subisce un danno diretto (uso fraudolento della carta).
- Il **circuito CartaSì** subisce un danno d'immagine: una quota di utenti deciderà di non appoggiarsi più a quel circuito, con conseguenze economiche significative.

> ✅ **Riepilogo lezione:** I crimini informatici colpiscono hardware, software e dati. Garantire la sicurezza informatica significa prevenire e proteggere contro l'accesso non autorizzato a qualsiasi tipo di risorsa digitale. Il tema non è prerogativa militare: l'informazione è oggi un asset strategico per chiunque, e la sua compromissione produce danni economici e reputazionali su scala individuale e organizzativa.
