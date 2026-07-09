## **Lezione 4: Architetture e Scenari**

### **1. Obiettivo della lezione**

Nella lezione precedente sono stati introdotti i **proxy firewall** e il ruolo del **bastion host**, cioè un host particolarmente protetto su cui possono essere eseguiti proxy e gateway.

Questa lezione studia alcune architetture in cui packet filter, proxy e bastion host vengono combinati per costruire una protezione perimetrale più robusta.

L'obiettivo non è scegliere un prodotto "migliore" in astratto, ma capire:

- dove posizionare i componenti;
- quali percorsi di traffico sono consentiti;
- che cosa succede se un componente viene compromesso;
- quale livello di protezione è proporzionato agli asset da difendere.

> 📌 Un'architettura firewall non è solo un insieme di regole: è una scelta di topologia, di fiducia e di separazione tra zone.

---

### **2. Screened host firewall single-homed**

Nel modello **screened host firewall single-homed** c'è un **packet filter**, spesso chiamato anche **screening router**, e un **bastion host** con una sola interfaccia di rete.

Il bastion host ospita il proxy. Il packet filter ha il compito di indirizzare il traffico verso il bastion host quando il servizio deve essere mediato dal proxy.

![](imgs/Pasted%20image%2020260709155702.png)

Il packet filter può permettere:

- pacchetti provenienti dall'esterno e diretti al bastion host, perché il bastion host fa da proxy;
- pacchetti provenienti dall'esterno e diretti a un server pubblico che non si ritiene necessario proteggere tramite proxy, per esempio un web server esposto;
- pacchetti provenienti dal bastion host e diretti verso l'esterno, perché sono generati o rilanciati dal proxy per conto degli utenti interni.

In questa configurazione, il traffico rilevante può essere controllato due volte:

1. dal packet filter;
2. dal proxy sul bastion host.

Se il packet filter viene compromesso, il bastion host rimane comunque una difesa per la rete interna. Tuttavia questa difesa vale solo per il traffico che viene effettivamente fatto passare dal bastion host.

> ⚠️ Nel modello single-homed il bastion host non separa fisicamente due reti: avendo una sola interfaccia, può essere aggirato se il traffico viene indirizzato direttamente verso la rete interna e il packet filter non lo blocca più correttamente.

Il limite centrale è quindi topologico: il bastion host difende logicamente, ma non impone da solo che ogni pacchetto attraversi fisicamente due segmenti separati.

---

### **3. Screened host firewall dual-homed**

Una variante più robusta è il **dual-homed bastion host**, in cui il bastion host dispone di due interfacce di rete.

Questa configurazione separa meglio la rete esterna dalla rete interna: il traffico che arriva dall'esterno deve raggiungere il bastion host e poi essere inoltrato attraverso l'altra interfaccia solo se il proxy o il servizio lo consentono.

![](imgs/Pasted%20image%2020260709155730.png)

Il vantaggio rispetto al single-homed è che, anche se il packet filter viene compromesso, il traffico non può raggiungere direttamente la rete interna senza attraversare il bastion host. Per violare la separazione, l'attaccante dovrebbe compromettere o alterare anche il funzionamento del bastion host.

Questo aumenta la sicurezza, ma introduce costi:

- maggiore complessità di configurazione;
- più attenzione al routing;
- prestazioni potenzialmente inferiori;
- maggiore criticità del bastion host, che diventa un punto obbligato del percorso.

> 📌 Il dual-homed bastion host trasforma il proxy in un vincolo architetturale più forte: non è solo un servizio consigliato, ma un passaggio obbligato.

---

### **4. Screened subnet firewall**

Il modello **screened subnet firewall** introduce una rete intermedia tra Internet e la rete privata interna. Questa rete è spesso chiamata **rete perimetrale** o **DMZ**.

L'architettura usa tipicamente due packet filter:

- un router/firewall esterno, tra Internet e rete perimetrale;
- un router/firewall interno, tra rete perimetrale e rete privata.

![](imgs/Pasted%20image%2020260709155746.png)

Nella rete perimetrale possono trovarsi:

- bastion host;
- web server pubblici;
- mail server;
- application gateway;
- altri servizi esposti ma controllati.

Il router esterno filtra il traffico tra Internet e la rete perimetrale secondo la policy di accesso pubblico. Il router interno protegge invece la rete privata sia da Internet sia dalla stessa rete perimetrale.

Questo è un punto essenziale: la DMZ non deve diventare un ponte libero verso la rete interna. Anche se un server in DMZ viene compromesso, l'attaccante non deve poter entrare automaticamente nella rete privata.

> 📌 La screened subnet aggiunge una zona cuscinetto: i servizi esposti non stanno direttamente nella LAN interna, ma in un'area controllata da due filtri.

Rispetto allo screened host, la screened subnet offre una segmentazione più forte. La sua complessità è maggiore, ma consente di separare meglio servizi pubblici, bastion host e sistemi interni.

---

### **5. FTP bounce attack**

L'**FTP bounce attack** è un esempio classico che mostra perché, in alcuni casi, un firewall deve comprendere ciò che avviene nel protocollo applicativo e non limitarsi agli header dei pacchetti.

L'attacco si basa sul comando `PORT` del protocollo FTP.

#### **5.1. Comando PORT**

Nel protocollo FTP attivo, il client può indicare al server FTP dove aprire la connessione dati tramite un comando della forma:

`PORT h1,h2,h3,h4,p1,p2`

dove:

- `h1,h2,h3,h4` rappresentano i quattro ottetti dell'indirizzo IP;
- `p1,p2` rappresentano la porta su cui il client dichiara di voler ricevere la connessione dati;
- la porta effettiva è calcolata come $256 \cdot p_1 + p_2$.

Per esempio, `PORT 159,149,10,5,4,1` indica l'indirizzo `159.149.10.5` e la porta $256 \cdot 4 + 1 = 1025$.

![](imgs/Pasted%20image%2020260709155916.png)

#### **5.2. Scenario**

Lo scenario didattico è il seguente:

- dall'esterno sono ammesse connessioni verso un FTP server;
- dall'esterno sono ammesse connessioni verso un web server;
- un Telnet server è accessibile solo dall'interno, per esempio per diagnostica;
- Telnet non dovrebbe essere esposto pubblicamente e, in generale, non dovrebbe essere usato in ambienti moderni perché insicuro.

![](imgs/Pasted%20image%2020260709155952.png)

L'attaccante sfrutta il server FTP come intermediario. Non attacca direttamente il Telnet server, perché il firewall non lo permetterebbe. Induce invece il server FTP, che è autorizzato a comunicare, ad aprire una connessione verso il Telnet server interno.

#### **5.3. Sequenza dell'attacco**

La sequenza è:

1. l'attaccante si collega al server FTP, anche in modalità anonima se consentita;
2. invia il comando `PORT 159,149,10,8,0,23`;
3. con questo comando dichiara che la connessione dati dovrà essere aperta verso `159.149.10.8` sulla porta $23$;
4. invia un comando di trasferimento, per esempio `RETR`;
5. il server FTP apre la connessione dati verso `159.149.10.8:23`, cioè verso il Telnet server;
6. la protezione perimetrale viene aggirata perché la connessione parte da un server ammesso dalla policy.

Un firewall semplice vede traffico apparentemente legittimo: una sessione FTP verso un server autorizzato e poi una connessione aperta dal server FTP. Non leggendo il payload FTP, non riconosce l'abuso del comando `PORT`.

Un **FTP proxy**, invece, può capire che il comando `PORT` sta chiedendo al server FTP di connettersi a un host o a una porta non coerente con la sessione attesa. In questo caso può terminare la connessione o bloccare il comando.

> 📌 Il valore didattico dell'FTP bounce è mostrare che un uso formalmente valido di un protocollo può diventare un attacco se il firewall non comprende la semantica dei comandi applicativi.

L'attacco è noto e mitigato da molti anni, ma resta utile per spiegare perché proxy applicativi, ALG e controlli sul payload possono essere necessari.

---

### **6. Scelta dell'architettura firewall**

In teoria, la soluzione più sicura è quella che opera al livello più alto possibile, con filtraggio più profondo e più consapevolezza applicativa.

Il problema è che maggiore profondità significa anche:

- maggiore consumo computazionale;
- più latenza;
- minore trasparenza;
- più complessità operativa;
- maggiore rischio di errori di configurazione.

Nella pratica, gli utenti e i clienti tendono spesso a privilegiare prestazioni e trasparenza rispetto a sicurezza e lentezza. Questo è particolarmente critico perché oggi quasi ogni applicazione rilevante dipende dalla rete: un firewall sottodimensionato o mal configurato può degradare l'intero servizio.

> ⚠️ Il firewall più potente non è automaticamente la scelta migliore: la protezione deve essere proporzionata a ciò che si deve difendere e al contesto operativo.

La scelta corretta non è necessariamente un singolo prodotto. Può essere un'architettura composta da più componenti, pensata in base a:

- vulnerabilità degli asset da proteggere;
- vulnerabilità dei firewall stessi;
- posizione dei servizi pubblici;
- traffico interno verso l'esterno;
- rischio di insider threat;
- competenze disponibili per configurare e mantenere i prodotti.

#### **6.1. Insider threat e traffico in uscita**

Il firewall non serve solo a bloccare traffico in ingresso. Deve anche contribuire a osservare e limitare il traffico dall'interno verso l'esterno.

Questo è importante nei casi di **insider threat**, in cui soggetti interni o account compromessi possono mal configurare strumenti di sicurezza, aprire canali non previsti o filtrare dati sensibili verso l'esterno.

> 📌 Una buona architettura firewall controlla anche l'uscita: l'esfiltrazione dati è spesso un problema di traffico interno che diventa esterno.

#### **6.2. Vendor singolo o più vendor**

Usare prodotti di un unico vendor semplifica gestione, formazione e integrazione. Tuttavia può introdurre un rischio sistemico: se una vulnerabilità riguarda una piattaforma o una famiglia di prodotti, può ripresentarsi in più punti dell'architettura.

Usare più vendor può ridurre la dipendenza da un'unica base software, ma aumenta la complessità:

- servono competenze su più prodotti;
- la configurazione coerente è più difficile;
- troubleshooting e auditing diventano più onerosi.

La scelta deve quindi bilanciare diversificazione e governabilità.

#### **6.3. Promesse commerciali**

I firewall sono strumenti centrali nella sicurezza perimetrale, ma restano software e hardware configurabili, quindi imperfetti.

Diffidare dei prodotti presentati come capaci di "fare tutto" è una regola sana: spesso la promessa commerciale semplifica problemi architetturali che restano reali.

> ⚠️ Nessun firewall elimina la necessità di progettare correttamente segmentazione, policy, monitoraggio, hardening e gestione delle vulnerabilità.

---

### **7. Stealth firewall**

Un caso particolare è lo **stealth firewall**, cioè un firewall privo di indirizzo IP visibile sulla rete.

L'idea è renderlo non direttamente attaccabile come host di rete. Il dispositivo viene collocato nel punto di transito dei pacchetti e intercetta fisicamente il traffico, per esempio tramite interfacce in modalità promiscua.

![](imgs/Pasted%20image%2020260709160045.png)

Il vantaggio è che l'attaccante esterno fatica a individuare e indirizzare direttamente il firewall: non c'è un IP a cui connettersi o contro cui lanciare attacchi di rete ordinari.

Questa caratteristica lo distingue dai proxy: un proxy è esplicito nella comunicazione e proprio per questo può diventare un bersaglio evidente. Lo stealth firewall, invece, cerca di filtrare senza rivelare la propria presenza.

> 📌 Lo stealth firewall riduce l'esposizione diretta del dispositivo di filtraggio, ma non sostituisce una policy corretta né elimina i problemi di configurazione.

---

### **8. Evasioni storiche dei packet filter**

I packet filter hanno avuto nel tempo diverse tecniche di evasione, molte delle quali oggi sono note e mitigate. Restano però utili per capire quali proprietà un firewall deve controllare.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.1. Frammentazione IP**

Un problema classico riguarda la frammentazione. Le informazioni TCP o UDP rilevanti per il filtraggio, come porte e flag, si trovano nel primo frammento. I frammenti successivi possono non contenere l'header di trasporto completo.

Se un vecchio packet filter controllava solo il frammento iniziale o gestiva male i frammenti successivi, un attaccante poteva costruire pacchetti frammentati per attraversare il filtro senza che la policy venisse applicata correttamente.

#### **8.2. Porte inferiori a 1024**

Alcuni vecchi sistemi di filtraggio controllavano soprattutto le porte inferiori a `1024`, storicamente associate ai servizi privilegiati.

Questa assunzione è fragile: molte applicazioni usano porte alte, e un malware può cercare canali di comunicazione su porte non tradizionali.

#### **8.3. NAT, trojan e indirizzi manipolati**

Un ulteriore problema riguarda traffico generato da trojan interni o da applicazioni malevole che cercano di sfruttare NAT, port forwarding o modifiche dell'indirizzamento per comunicare con l'esterno.

Se la policy consente troppo traffico in uscita, un malware interno può aprire canali verso l'attaccante anche quando il traffico in ingresso è ben filtrato.

> ⚠️ Molti attacchi non chiedono più di entrare: aspettano che un host interno apra una connessione verso l'esterno.

---

### **9. Best practice di configurazione**

La scelta architetturale può essere guidata da alcune regole pragmatiche.

#### **9.1. Usare proxy quando serve controllo applicativo**

Un proxy rompe il percorso di rete diretto e introduce un livello di mediazione più forte, soprattutto quando serve analizzare comandi o dati applicativi.

Il costo è che alcune applicazioni potrebbero non funzionare senza adattamenti o senza un proxy specifico.

#### **9.2. Preferire filtraggio stateful rispetto a stateless**

Un firewall stateful usa informazioni di stato della connessione e può prendere decisioni più robuste rispetto a un filtro puramente statico.

Non basta però avere stato: quando il problema è nel payload applicativo, serve anche un proxy o un modulo application-aware.

#### **9.3. Default deny e apertura minima**

La politica più sicura è negare tutto per impostazione predefinita e abilitare solo ciò che è strettamente necessario.

Lo stesso principio vale dinamicamente: le porte o le aperture temporanee devono essere chiuse appena non servono più.

Questo riduce anche la capacità dei trojan interni di comunicare con l'esterno. Se il malware non può aprire il canale che desidera, resta isolato o comunque molto meno efficace.

#### **9.4. Hardening e patching del firewall**

Il sistema operativo e il software del firewall devono essere sicuri, aggiornati e ridotti all'essenziale.

Il patching è necessario perché corregge vulnerabilità note, ma va gestito con competenza: una patch può introdurre nuovi bug o nuove vulnerabilità non ancora comprese. In ambienti critici, gli aggiornamenti vanno valutati, testati e poi applicati con procedure controllate.

> ⚠️ Non applicare patch lascia vulnerabilità note; applicarle senza controllo può introdurre instabilità o regressioni. La risposta corretta è gestione del rischio, non improvvisazione.

#### **9.5. Usare stack protocollari sicuri e comprovati**

Non ha senso cercare di proteggere seriamente traffico basato su stack protocollari notoriamente vulnerabili o progettati male.

Il firewall deve filtrare traffico che appartiene a protocolli sicuri, aggiornati e ben compresi. Se il protocollo sottostante è intrinsecamente debole, il firewall può ridurre il rischio ma non trasformarlo in un protocollo sicuro.

> ✅ Una buona configurazione firewall combina segmentazione, default deny, stato, proxy dove necessario, hardening del sistema e protocolli affidabili.

---

### **10. Conclusione**

Le architetture viste mostrano una progressione:

- lo **screened host single-homed** introduce un bastion host, ma non impone una separazione fisica forte;
- lo **screened host dual-homed** rende il bastion host un passaggio più obbligato;
- la **screened subnet** crea una DMZ e separa servizi esposti e rete privata;
- i **proxy** migliorano il controllo applicativo, ma aumentano complessità e latenza;
- lo **stealth firewall** riduce l'esposizione diretta del dispositivo di filtraggio.

Non esiste una soluzione perfetta e universale. Esiste un'architettura coerente con il rischio, con gli asset da proteggere, con il traffico da supportare e con le competenze disponibili per mantenerla.

> 📌 La sicurezza perimetrale efficace non nasce dal firewall "più avanzato", ma da una progettazione proporzionata, verificabile e mantenibile.
