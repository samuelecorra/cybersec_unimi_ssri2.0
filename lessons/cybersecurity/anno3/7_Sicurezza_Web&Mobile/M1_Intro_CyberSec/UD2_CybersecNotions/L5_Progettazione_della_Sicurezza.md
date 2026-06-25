# L5 — Progettazione della Sicurezza: Prevenzione, Rilevamento e Reazione

---

### **1. Punto di Partenza: Cosa NON È la Sicurezza Informatica**

Prima di capire come pianificare la sicurezza, è utile chiarire cosa la sicurezza informatica **non è** — per evitare false certezze.

#### **1.1. La Sicurezza Non È Crittografia**

La crittografia è una scienza esatta: ha dimostrato, ad esempio, che violare RSA (algoritmo di cifratura a chiave asimmetrica) è possibile solo in tempo polinomiale con le risorse computazionali attuali — quindi praticamente inviolabile. Tuttavia, **quando la crittografia viene applicata in pratica**, entra in contatto con persone e macchine: altri anelli della catena che costituiscono il processo di sicurezza. Se uno di questi anelli è debole (es. un sistema di autenticazione vulnerabile), l'intero sistema cade, indipendentemente dalla robustezza crittografica.

#### **1.2. La Sicurezza Non È l'Utilizzo delle Password**

Le password sono un sistema di autenticazione intrinsecamente debole:

- Gli utenti scelgono password facili da ricordare → vulnerabili ad **attacchi a dizionario** o ad **attacchi a forza bruta**.
- In sistemi condivisi, il database delle password potrebbe essere accessibile, permettendo **attacchi a forza bruta offline**.

Problemi aperti nella gestione delle password:
- Come scegliere una password robusta?
- Come ricordarla senza annotarla su un foglio o in un file sul computer (entrambi potenzialmente accessibili a un attaccante)?
- Ha senso usare la stessa password per tutti i servizi o è meglio usarne di diverse per ciascuno?

#### **1.3. La Sicurezza Non È un Firewall**

Filtrare i pacchetti a livello di trasporto non garantisce l'eliminazione di tutti i possibili malware, specialmente durante la navigazione Internet. Il firewall è uno strumento necessario ma **non sufficiente**.

---

### **2. Cosa È Allora la Sicurezza Informatica**

> 📌 La sicurezza informatica è una **proprietà trasversale a più livelli architetturali**: sistema operativo, rete, livello applicativo, ecc. Non è un predicato booleano ("il sistema è sicuro: sì/no"), ma un processo continuo di riduzione del rischio.

Caratteristiche fondamentali:

- È **costosa**: richiede risorse computazionali, risorse di gestione, formazione del personale e un cambiamento di mentalità.
- Rimane un **campo aperto** anche per i colossi dell'informatica.
- Spesso richiederebbe il **ridisegno di sistemi preesistenti**, il che non è sempre fattibile — principalmente per motivi di costo.

---

### **3. Come Si Fa Sicurezza: Prevenzione, Rilevamento, Reazione**

La sicurezza va **pianificata** e si ottiene combinando tre fasi distinte ma complementari.

#### **3.1. Prevenzione**

Obiettivo: rendere il sistema non vulnerabile ab initio, riducendo la superficie d'attacco.

Esempi di misure preventive:
- **Crittografia dei dati a riposo:** cifrare file riservati per garantire la confidenzialità anche in caso di accesso fisico non autorizzato.
- **Crittografia delle comunicazioni:** usare protocolli sicuri (PGP, SSH, SSL/TLS) quando si trasmette su canali insicuri.
- **Backup e disaster recovery:** garantire la disponibilità dei dati anche dopo eventi catastrofici.
- **Controllo degli accessi:** assicurare che solo gli utenti autorizzati accedano alle risorse — non solo tramite password, ma anche tramite tecniche di autenticazione più robuste (es. autenticazione a più fattori, dati biometrici).
- **Segmentazione della rete:** suddividere la rete da proteggere in sotto-aree a diverso livello di sicurezza, tramite router e switch opportunamente configurati.
- **Aggiornamento e patch management:** verificare regolarmente la pubblicazione di bug noti negli applicativi installati e mantenere aggiornate le configurazioni dei server.
- **Filtraggio del traffico:** usare firewall, router di screening e sistemi analoghi per limitare il traffico in entrata e in uscita.

#### **3.2. Rilevamento**

Obiettivo: verificare che il sistema funzioni come previsto e individuare anomalie o intrusioni.

- **Monitoraggio in tempo reale:** dove possibile, analizzare il comportamento del sistema durante l'esecuzione.
- **Logging:** memorizzare accessi e comportamenti in file di log da analizzare successivamente, quando il monitoraggio in tempo reale non è praticabile.

> ⚠️ Il rilevamento funziona soprattutto per **attacchi già noti**. Un antivirus aggiornato non riconosce un virus zero-day — un malware non ancora catalogato nel database delle definizioni. In quel caso, il sistema subirà l'attacco anche con le difese apparentemente aggiornate.

#### **3.3. Reazione**

Obiettivo: limitare i danni in caso di attacco rilevato e ripristinare il servizio il prima possibile.

Esempi:
- **Piano B predisposto:** nel caso di un sito web sotto attacco DoS, avere un server alternativo pronto a subentrare per soddisfare le richieste degli utenti è una reazione efficace.
- **Comunicazione trasparente:** visualizzare una pagina che comunica l'attacco rilevato e l'imminente ripristino è meglio di nulla, ma non costituisce una vera gestione dell'incidente — il servizio rimane comunque inaccessibile.

> 💡 Il caso del sito di Totti (citato in L1) è emblematico: la pagina di avviso "stiamo lavorando per ripristinare il servizio" non è una reazione adeguata a un DoS. Una reazione efficace richiede infrastruttura ridondante predisposta in anticipo.

---

### **4. Limiti della Pianificazione della Sicurezza**

Anche implementando tutte e tre le fasi, rimangono dei limiti strutturali:

- La **prevenzione riduce** la probabilità di una violazione, ma non la elimina. Riduce anche i danni potenziali, ma spesso gli attaccanti riescono a bypassare proprio il modulo di prevenzione.
- Il **rilevamento in tempo reale** non è sempre possibile e funziona principalmente per minacce già conosciute.
- La **reazione** presuppone di aver rilevato l'attacco — se il rilevamento fallisce, la reazione non si attiva.

> ⚠️ La sicurezza ha un costo che si manifesta su più dimensioni:
> - **Economico:** acquisto e gestione di misure aggiuntive (antivirus, firewall, IDS, ecc.).
> - **Prestazionale:** le misure di sicurezza aumentano il carico computazionale del sistema, riducendone le prestazioni.
> - **Usabilità:** l'utente potrebbe dover inserire le proprie credenziali più volte, usare dati biometrici per servizi critici, ecc. — un onere che spesso genera resistenza.

---

### **5. Bilanciamento Costo/Beneficio della Sicurezza**

> 📌 **Principio generale:** la prevenzione va **bilanciata rispetto al valore delle risorse da proteggere** e al danno che una violazione arrecherebbe. Non ha senso spendere 10.000 € in misure di sicurezza per proteggere dati il cui valore è di 1.000 €.

Questo trade-off è particolarmente rilevante in ambito commerciale, dove la sicurezza compete con altri obiettivi aziendali (time-to-market, costi operativi, esperienza utente).

---

### **6. Riepilogo**

> ✅ La sicurezza informatica non è né crittografia, né password, né un firewall: è un **processo continuo** che si pianifica e si ottiene combinando **prevenzione**, **rilevamento** e **reazione**. È una proprietà multi-livello (SO, rete, applicazione) con costi economici, prestazionali e di usabilità che vanno bilanciati rispetto al valore delle risorse da proteggere. Non esiste sicurezza assoluta: l'obiettivo è ridurre il rischio a un livello accettabile per il contesto specifico.
