# **Lezione 4: Lo spam**

---

### **1. Le origini dello spam**

Negli **anni ’90**, la posta elettronica iniziò a essere utilizzata anche per scopi commerciali, ma non sempre in modo lecito o gradito.  
Prima di allora, le e-mail indesiderate consistevano solo in **“catene di Sant’Antonio”**: messaggi inviati manualmente da utenti reali, senza tentativi di falsificare il mittente, che si propagavano spontaneamente attraverso il passaparola.

Il vero **spamming commerciale** nasce nel **1994**, con il celebre messaggio dei **“Green Card Lawyers”**, inviato dagli avvocati **Lawrence Canter** e **Martha Siegel**.  
Il loro messaggio pubblicizzava la **lotteria per la Green Card** (permesso di soggiorno USA) e fu inviato **automaticamente a centinaia di gruppi Usenet** usando un programma dedicato.

Sebbene non vi fosse ancora falsificazione del mittente, questo evento è considerato **l’inizio ufficiale dello spam moderno**.  
Canter e Siegel divennero i **primi “marketer di Internet”** e anche i primi a essere banditi da diverse comunità online.

---

### **2. L’evoluzione tecnica dello spam**

L’anno successivo, **Jeff Slaton**, soprannominato **“the Spam King”**, portò lo spamming a un nuovo livello tecnico.  
Egli riuscì a:

- automatizzare la raccolta degli indirizzi e-mail dagli archivi pubblici di **Usenet**;
    
- inviare milioni di messaggi al giorno;
    
- nascondere parzialmente la provenienza dei messaggi.
    

Slaton affermò di poter raggiungere fino a **8 milioni di persone**, inaugurando così l’era dello spam di massa.

Da lì in poi, lo spam si diffuse rapidamente in tutto il mondo, diventando un problema **economico e tecnico** di proporzioni enormi.  
Secondo un **rapporto Symantec del 2012**, la percentuale di spam era già intorno al **70% di tutte le e-mail inviate**:

- Italia → 69,6%
    
- USA → 69,9%
    
- Gran Bretagna → 69,3%
    
- Arabia Saudita → 75,5% (il paese più colpito)
    

---

### **3. Come gli spammer trovano gli indirizzi**

Il primo ostacolo per chi invia spam è **reperire gli indirizzi dei destinatari**.  
Tuttavia, anche questo problema è stato rapidamente superato.

- Gli indirizzi dei **server SMTP** sono facilmente individuabili consultando i **record MX** (Mail Exchange) nei file di zona del **DNS**.
    
- Gli indirizzi e-mail degli utenti possono essere raccolti da **newsgroup, forum, siti web, elenchi pubblici o database trafugati**.
    

I primi amministratori di rete cercarono di difendersi creando **liste nere locali (killfile)**, cioè elenchi di indirizzi IP da cui **non accettare connessioni SMTP**.  
Era una soluzione artigianale ma efficace per bloccare i primi spammer.

---

### **4. L’era degli “open relay”**

Gli spammer presto scoprirono come **aggirare i killfile**, sfruttando una funzionalità presente in molti server SMTP: l’**open relay**.

#### **Cos’è un open relay**

Un **open relay** è un server SMTP configurato in modo da **inoltrare messaggi per conto di chiunque**, senza verificare l’identità del mittente.  
In origine, questa funzione era utile per **semplificare la cooperazione tra reti universitarie**, ma divenne presto un’arma per lo spam.

#### **Esempio: il caso di Sendmail**

Il server **Sendmail**, discendente dell’originario _DeliverMail_ di ARPANET, fino alla **Versione 5** accettava di inoltrare posta per qualsiasi client.  
Questo significava che:

> uno spammer poteva inviare il suo messaggio al server SMTP di qualcun altro, che a sua volta lo avrebbe inoltrato ai destinatari finali, mascherando l’origine reale.

---

### **5. Le contromisure e i nuovi problemi**

Disattivare l’open relay divenne la **prima misura di difesa** contro lo spam.  
Tuttavia, la soluzione non era priva di conseguenze.

Infatti:

- includere nelle **blacklist** i server SMTP che facevano open relay in buona fede rischiava di **bloccare anche messaggi legittimi**;
    
- disabilitare completamente l’open relay richiedeva **un intervento tecnico accurato** e spesso complesso.
    

Per la prima volta la comunità Internet si rese conto che **un piccolo problema tecnico** (una semplice opzione di configurazione) poteva trasformarsi in **una crisi organizzativa globale**.

---

### **6. Nuove tecniche di recapito dello spam**

All’inizio degli anni 2000, comparvero **tre nuove tecniche di invio** che resero lo spam ancora più difficile da bloccare.

#### **a. Relay multi-hop**

- I provider Internet usano più server SMTP: alcuni per la posta interna, altri per la posta verso l’esterno.
    
- Gli spammer sfruttano questa architettura inviando lo spam a un **server interno** compromesso, che poi lo inoltra agli **MTA di confine**.
    
- Poiché il messaggio sembra provenire da un utente autorizzato, il server esterno lo **accetta e inoltra**.
    

Questo metodo aggira il controllo diretto sugli open relay e sfrutta la **fiducia interna** tra i server dello stesso dominio.

---

#### **b. Dynamic addressing**

- Alcuni provider assegnano ai clienti **indirizzi IP dinamici**, validi solo per la durata della connessione.
    
- Quando un indirizzo IP finisce su una blacklist, lo spammer può **disconnettersi e riconnettersi**, ottenendo un **nuovo IP pulito**.
    
- Questo sistema è costoso ma estremamente efficace: il messaggio parte da un IP sempre nuovo e quindi difficilmente tracciabile.
    

Il metodo è detto **“no relay”**, poiché non si appoggia a server intermedi.

---

#### **c. Open proxy**

Molte aziende o enti usano **proxy server** per condividere la connessione Internet tra più computer interni.  
Tuttavia, quando questi proxy sono **mal configurati**, possono accettare connessioni anche da host esterni.  
Uno spammer può così:

- collegarsi al proxy di un’altra organizzazione;
    
- usarlo per spedire il proprio spam, **dissimulando completamente l’origine** del messaggio.
    

Alcuni malware, come il virus **Sobig.a (2003)**, installavano **proxy nascosti** direttamente sui computer infetti, permettendo agli spammer di **creare reti di proxy zombie** dedicate allo spam.

---

### **7. L’impatto dello spam**

Lo spam è oggi una delle **maggiori fonti di traffico inutile su Internet**.  
Provoca:

- **spreco di banda e di spazio di archiviazione**,
    
- **rallentamento dei server di posta**,
    
- **rischi di sicurezza** (phishing, malware, truffe),
    
- e un enorme **spreco di tempo umano** nel filtrare messaggi indesiderati.
    

---

### **8. Conclusione**

Dalla prima “catena di Sant’Antonio” fino ai moderni attacchi automatizzati, lo spam è passato da fenomeno fastidioso a **problema sistemico** di Internet.  
Le sue radici sono tecniche (come l’open relay e i proxy vulnerabili), ma anche economiche e sociali:  
finché inviare milioni di messaggi costerà meno di un centesimo, **lo spam resterà conveniente per qualcuno**.

Oggi la lotta allo spam si combatte con **filtri intelligenti, controlli DNS, autenticazioni SPF/DKIM, e reti anti-botnet**, ma la lezione storica rimane:

> la sicurezza di Internet dipende dalla **corretta configurazione dei suoi protocolli più semplici**.