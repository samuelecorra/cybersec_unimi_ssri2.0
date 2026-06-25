# L2 — Proprietà di Sicurezza: CIA, Autenticazione e Non Ripudio

---

### **1. Obiettivo della Lezione**

Nella lezione precedente abbiamo dato una definizione informale di sicurezza informatica come *prevenzione e protezione contro l'accesso, la distruzione e l'alterazione di risorse da parte di utenti non autorizzati*. L'obiettivo di questa lezione è **formalizzare** tale definizione attraverso le **proprietà di sicurezza** che un sistema informatico deve soddisfare per essere considerato sicuro.

> 📌 **Definizione semiformale:** La sicurezza informatica è l'abilità di un sistema di proteggere informazioni e risorse rispetto alle nozioni di **CIA** — acronimo di **Confidentiality** (Confidenzialità), **Integrity** (Integrità), **Availability** (Disponibilità).

---

### **2. Le Tre Proprietà CIA**

#### **2.1. Confidenzialità (Confidentiality)**

**Garantire confidenzialità** significa assicurare che le informazioni non siano accessibili a utenti non autorizzati.

È fondamentale distinguere tre termini spesso confusi tra loro:

| Termine | Definizione |
|---------|-------------|
| **Confidenzialità** (o *segretezza*) | Le informazioni non sono accessibili a utenti non autorizzati. |
| **Privatezza** (*privacy*) | Gli utenti possono controllare *quali* informazioni su di loro vengono raccolte, *chi* le usa e *per quale scopo*. Va usato solo quando l'informazione fa riferimento a individui specifici. |
| **Anonimato** | Il diritto dell'individuo di non rivelare la propria identità: non è possibile risalire all'autore di una certa azione. |

> ⚠️ **Privatezza ≠ Confidenzialità:** Sono termini non sinonimi. La confidenzialità riguarda la protezione generica di dati; la privacy riguarda specificamente il controllo che un individuo esercita sui propri dati personali.

> ⚠️ **Contesto normativo — Europa vs. USA:** In Europa (GDPR e normative analoghe) i dati personali sono privati per legge: un supermercato che raccoglie i tuoi dati tramite tessera fedeltà non può rivenderli. Negli USA la normativa è diversa: i dati personali appartengono a chi li ha raccolti.

**Approfondimento sull'anonimato:**

- Se la *privatezza* è il diritto di rilasciare o meno informazioni che riguardano un individuo, l'*anonimato* è il diritto di rilasciare o meno la propria **identità**.
- Si distingue tra:
  - **Anonimato commerciale** — es. acquisti senza registrazione.
  - **Anonimato sanitario** — es. accesso a test medici senza nome.
  - **Pseudo-anonimato** — uso di uno pseudonimo o nome falso; nella navigazione Internet rimane comunque possibile risalire all'indirizzo IP utilizzato e quindi all'identità reale.
- Esiste un dibattito aperto nella comunità se l'anonimato nella navigazione Internet sia davvero un diritto. È un problema difficile da garantire nel mondo digitale e sarà approfondito nella parte del corso dedicata alla sicurezza web.

---

#### **2.2. Integrità (Integrity)**

**Garantire integrità** significa assicurare che le informazioni non siano **alterabili da utenti non autorizzati in maniera invisibile agli utenti autorizzati**.

In altri termini:
- Chi è **autorizzato** deve poter modificare la risorsa.
- Chi **non è autorizzato** non deve poterla modificare senza che questo sia rilevabile.
- Un utente autorizzato deve essere in grado di **riconoscere** che la risorsa è stata manomessa.

**Esempio — Alice, Bob e Trudy:**
Alice invia un messaggio a Bob su un canale insicuro. Trudy, terza parte non fidata, intercetta il messaggio e lo modifica fingendo di essere Alice. Bob deve ricevere il messaggio e **rilevare** che è stato alterato da un soggetto non autorizzato.

> ⚠️ **Integrità ≠ Autenticazione:** Quando si parla di integrità non si specifica l'identità dell'autore originale del messaggio — quello è il problema dell'autenticazione (trattato a breve). L'integrità riguarda solo la rilevabilità di modifiche non autorizzate.

Un termine spesso usato come sinonimo di *violazione dell'integrità* è **falsificazione**.

---

#### **2.3. Disponibilità (Availability)**

**Garantire disponibilità** significa assicurare che le informazioni e le risorse siano **disponibili agli utenti autorizzati sempre** — ovvero che il sistema sia operativo e funzionante in ogni momento.

La violazione della disponibilità corrisponde agli attacchi di tipo **Denial of Service (DoS)**, come quello già visto per il sito di Francesco Totti: il sistema era perfettamente integro e confidenziale, ma inaccessibile.

---

### **3. Conflitti tra le Proprietà CIA**

> ⚠️ Le tre proprietà CIA possono **entrare in conflitto** tra loro. Un sistema non deve necessariamente garantirle tutte e tre contemporaneamente: dipende dal contesto e dalla tipologia del sistema.

Esempi di conflitto tra **confidenzialità e disponibilità**:

- Se non permetto a nessuno di leggere un'informazione, garantisco la confidenzialità al massimo, ma la risorsa non è disponibile nemmeno agli utenti autorizzati.
- Se uso un sistema centralizzato per controllare gli accessi, è più facile garantire la confidenzialità, ma il collo di bottiglia rallenta i tempi di risposta, compromettendo la disponibilità.

> 💡 Per questo la definizione CIA rimane **semiformale**: a seconda del contesto, andremo a specificare quali proprietà devono essere garantite e con quale priorità.

---

### **4. Proprietà Aggiuntive: Autenticazione e Non Ripudio**

Oltre alle tre proprietà CIA, esistono altre proprietà di sicurezza frequentemente richieste.

#### **4.1. Autenticazione**

**Garantire autenticazione** significa assicurare che i soggetti siano **effettivamente chi affermano di essere**.

Quando parleremo di protocolli crittografici, distingueremo tra:
- **Autenticazione mutua** (*mutual authentication*): entrambi i soggetti (mittente e destinatario) si autenticano a vicenda.
- **Autenticazione unilaterale** (*one-way authentication*): solo una delle due parti si autentica con l'altra (es. il client si autentica al server, ma non viceversa).

#### **4.2. Non Ripudio**

**Garantire non ripudio** significa assicurare che:
- Il **mittente** di un messaggio non possa negare di averlo spedito.
- Il **destinatario** non possa negare di averlo ricevuto.

> 💡 È l'equivalente digitale della **firma autografa** su un documento cartaceo: chi firma non può in seguito sostenere di non aver firmato.

---

### **5. Safety e Reliability (Cenni)**

A volte vengono considerate proprietà di sicurezza anche **safety** e **reliability** (affidabilità), legate più alla correttezza del software che alla protezione da attacchi esterni — come nell'esempio del bug di Ariane 5 visto nella lezione precedente.

- **Safety:** accorgimenti per eliminare la produzione di danni irreparabili al sistema (es. gestione corretta delle eccezioni).
- **Reliability:** prevenire eventi che possano produrre danni di qualsiasi gravità al sistema.

Questi aspetti non saranno approfonditi nel corso, che si concentra sulle proprietà CIA, autenticazione e non ripudio.

---

### **6. La Sicurezza Non È un Concetto Assoluto**

Anche disponendo di tutte le tecnologie per garantire ciascuna proprietà di sicurezza (crittografia, IDS, firewall, ecc.), **non si può dichiarare un sistema assolutamente sicuro**.

Su questo punto è illuminante citare **Bruce Schneier** — esperto di sicurezza informatica — da un articolo e dal suo libro pubblicato nel 2000:

> 📌 **"La sicurezza non è un prodotto, ma un processo."**

Non esiste un sistema garantito al 100%. Come nell'esempio delle Torri Gemelle: anche avendo attivato le migliori tecniche di disaster recovery con backup regolari, se tutti i server si trovano nello stesso edificio, un evento catastrofico inatteso vanifica ogni misura. La sicurezza dipende sempre dal **contesto**: da chi ci si vuole proteggere, da cosa, e con quale livello di rischio accettabile.

> 📌 **"La sicurezza è una catena e la sua resistenza è determinata dall'anello più debole."**

Supponiamo che Alice e Bob cifrino tutte le loro comunicazioni con chiavi lunghissime e crittograficamente robuste — quindi inattaccabili. Ma Alice, per accedere al proprio client di posta, usa come password il proprio nome di battesimo: *alice*. In questo caso, un attaccante non deve nemmeno tentare di rompere la crittografia: può semplicemente eseguire un **attacco a forza bruta** sulle credenziali e accedere all'account di Alice. L'intera catena di sicurezza cade per l'anello più debole — il sistema di autenticazione.

> ⚠️ Non basta installare un firewall o un IDS per considerarsi al sicuro. Se il modulo di controllo degli accessi usa meccanismi semplici e attaccabili, l'intero sistema rimane vulnerabile indipendentemente dalla robustezza degli altri componenti.

Il costo della sicurezza è un altro fattore determinante: spesso il costo aggiuntivo per garantire un certo livello di sicurezza supera il valore dei dati che si intende proteggere. In ambito commerciale (rispetto a quello militare), questo trade-off è sempre presente e va valutato esplicitamente.

---

### **7. Riepilogo**

> ✅ La sicurezza informatica si definisce in termini di proprietà che il sistema deve garantire. Le tre fondamentali sono **Confidenzialità**, **Integrità** e **Disponibilità** (CIA). Proprietà aggiuntive includono **Autenticazione** e **Non Ripudio**. Le proprietà possono entrare in conflitto tra loro, e la sicurezza è sempre relativa al contesto: non esiste sicurezza assoluta. La robustezza di un sistema è quella del suo anello più debole.
