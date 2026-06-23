# **Lezione 4: Lo spam**

---

### **1. Le origini dello spam**

Fino alla prima metà degli anni '90 di spam su Internet non si parlava: esistevano i messaggi di posta elettronica con contenuto simile alle cosiddette **catene di Sant'Antonio**, ma questi venivano inviati con le stesse modalità della posta ordinaria, senza alcun tentativo di automatizzazione o falsificazione del mittente.

La data di inizio dello spamming viene fatta risalire da molti al **1994**, l'anno in cui due avvocati — **Lawrence Canter** e **Martha Siegel**, che in seguito divennero noti come esperti di Internet marketing — ebbero l'idea di inviare contemporaneamente a **decine di migliaia di persone** un messaggio in cui offrivano i propri servizi legali per l'ottenimento della cosiddetta **green card** (la carta verde per la residenza permanente negli Stati Uniti).

Dal punto di vista tecnico, la novità del messaggio dei _Green Card Lawyers_ stava soprattutto nell'invio contemporaneo a decine di migliaia di persone attraverso gli **indirizzi registrati nei gruppi USENET**, che erano gruppi di discussione allora molto in voga su Internet. **Non nella dissimulazione del mittente**: Canter e Siegel non nascosero la loro identità.

L'anno successivo entrò nel giro degli spammer **"Jh" Dayton**, che divenne noto come **"The Spam King"**. Egli fu il primo ad avere l'idea di inviare messaggi non solo a tantissime persone, ma anche **dissimulando abilmente l'identità del mittente**, in modo da non permettere ai destinatari di risalire alla fonte. In questo modo era possibile inviare anche messaggi truffaldini o destinati al cosiddetto **phishing** di informazioni su Internet. Dayton si vantò a un certo punto della sua carriera di essere in grado di raggiungere **più di 8 milioni di persone alla volta**.

---

### **2. La portata attuale del fenomeno**

Lo spam è oggi uno dei problemi più grandi di Internet. Secondo uno studio recente, la percentuale di spam sul totale dei messaggi di posta elettronica nei paesi occidentali è intorno al **70%**. In alcuni paesi raggiunge punte superiori al 70% del totale dei messaggi inviati:

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Italia** → 69,6%
- **USA** → 69,9%
- **Gran Bretagna** → 69,3%
- **Arabia Saudita** → 75,5%

---

### **3. I due problemi dello spammer**

Uno spammer deve risolvere **due problemi**:

1. **Procurarsi molti indirizzi di posta elettronica** dei destinatari.
2. **Trovare un server SMTP disponibile a recapitare i messaggi** di spam a quegli indirizzi.

Il secondo problema — trovare server SMTP — non è in realtà un grande ostacolo, perché i server SMTP hanno un particolare tipo di record, i **record MX**, presenti nei file di zona del DNS. Attraverso la consultazione di questi file di zona, gli spammer possono raccogliere gli indirizzi IP di un gran numero di server SMTP a cui rivolgersi.

Il primo problema — reperire gli indirizzi email degli utenti — è più complesso e verrà approfondito a parte.

---

### **4. Il kill file e i suoi limiti**

La prima forma di difesa adottata dai server SMTP fu il **kill file**: un elenco di indirizzi IP da cui non si desideravano ricevere messaggi. Era una configurazione semplice aggiunta al server SMTP per bloccare i mittenti noti.

Il kill file è però un'**arma spuntata**.

Nulla nel protocollo SMTP obbliga un server ad inviare messaggi soltanto al server SMTP destinatario corrispondente alla destinazione finale del messaggio. È possibilissimo che un server SMTP accetti messaggi di posta elettronica anche quando l'indirizzo del destinatario finale non è riconosciuto localmente: quel server fungerà da **relay**, occupandosi di inoltrarli al server di destinazione finale.

Quando un server SMTP non esegue nessun tipo di controllo e accetta messaggi diretti ovunque per poi inoltrarli al corretto SMTP di destinazione, si dice che opera in **open relay**.

---

### **5. Il caso Sendmail e l'open relay**

Molti programmi che implementavano il protocollo SMTP — tra cui **Sendmail**, il più famoso, discendente dall'implementazione originale detta **DeliverMail** del protocollo SMTP su ARPANET — venivano attivati **di default con una configurazione open relay**, cioè erano in grado di accettare messaggi diretti a chiunque, per poi inoltrarli al server SMTP di destinazione finale. Questo avveniva fino alla **versione 5** di Sendmail.

Gli spammer non devono far altro che identificare un server Sendmail vecchio stile configurato in open relay, passargli i propri messaggi di spam, e quello si occuperà di inoltrarli agli SMTP di destinazione finale.

> ⚠️ Con questa tecnica il **blacklisting diventa molto difficile**: lo spammer può servirsi di volta in volta di diversi server intermedi. È molto difficile bloccare tutti i possibili server configurati in relay, anche perché quelli hanno il compito legittimo di inoltrare i messaggi degli utenti normali.

L'unica soluzione è **disabilitare la funzionalità open relay**: configurare il server SMTP in modo che accetti soltanto messaggi diretti ai propri utenti e non messaggi da mandare ad altri server SMTP. Questa operazione è stata compiuta, ma ha richiesto molti anni. Nei primi anni 2000 c'erano ancora tanti server configurati in open relay; poi direttive di configurazione all'interno delle organizzazioni — compresa l'Università di Milano — hanno fatto sì che il numero di server mail con open relay diminuisse molto.

> ⚠️ Attenzione però: se qualcuno attiva un server SMTP sul proprio portatile — ad esempio un MacOS che ce l'ha installato ma sebbene disattivato — con il rischio di attivare un relay, e se questo Sendmail viene identificato dagli spammer, diventerà la loro preda preferita per farsi inoltrare messaggi di posta elettronica. La **configurazione open relay è considerata oggi poco meno che un crimine** dagli esperti di sicurezza.

---

### **6. Nuove tecniche di aggiramento (anni 2000)**

All'inizio degli anni 2000, la battaglia contro la configurazione open relay poteva dirsi sostanzialmente vinta. Ma gli spammer trovarono rapidamente modi nuovi di aggirare il problema.

#### **a. Relay multi-hop tramite border SMTP**

I provider Internet dispongono di più server SMTP: alcuni inoltrano posta solo all'interno della rete del provider, altri anche verso l'esterno — i cosiddetti **border SMTP server** (server SMTP di confine). Questi ultimi devono necessariamente accettare alcune forme di relay, perché devono accettare posta proveniente da altri server SMTP interni per poi inoltrarla verso l'esterno.

Lo spammer sfrutta questo schema: ha accesso a un server di posta interno, il quale utilizzerà il server di confine per recapitare il messaggio verso l'esterno. Siccome il server di confine non è sempre lo stesso, il blacklisting da parte degli SMTP di destinazione diventa meno efficace.

#### **b. Indirizzamento dinamico**

Un ulteriore fattore tecnico che rende molto difficile il blacklisting è l'**indirizzamento dinamico**. Anche senza appoggiarsi a un Sendmail relay intermedio, uno spammer può configurare un proprio SMTP sulla sua stessa macchina. Quella macchina, essendo collegata a un provider, riceverà ogni volta che si collega un **indirizzo IP diverso** tramite DHCP. L'SMTP destinatario troverà difficile creare kill file capaci di intercettare queste macchine: uno spammer su un portatile che si collega ciclicamente a Internet su provider diversi — o anche sullo stesso provider — compare ogni volta sotto IP dinamici sempre diversi.

#### **c. Open proxy e proxy virali**

Molte organizzazioni usano **proxy server** per la navigazione degli utenti interni. Questi proxy sono a volte **malconfigurati** e accettano connessioni anche da host esterni: uno spammer può dichiarare come proprio proxy quello di un'azienda esterna, inviare posta elettronica attraverso di esso e in questo modo mascherare completamente la provenienza della posta.

Se il proxy non è disponibile, i **virus possono portarlo in giro**. Nel 2003 ci fu il famoso virus **Sbig**, che installava nei computer vittime un proxy: in questo modo creava proxy aggiuntivi che consentivano agli spammer di appoggiarsi su questi **proxy virali** per mandare i loro messaggi a server SMTP ignari.

---

### **7. La causa radice: le vulnerabilità strutturali di SMTP**

Il problema del protocollo SMTP di **non verificare** — o meglio, di verificare in modo molto indiretto attraverso query DNS — l'identità del mittente del traffico, e il **rapporto tra mittente del traffico e mittente dichiarato** dei messaggi, è stato uno degli elementi fondamentali che ha consentito lo sviluppo su larga scala dello spamming.

> 📌 Finché SMTP non prevede un meccanismo affidabile di autenticazione del mittente, lo spam rimarrà strutturalmente difficile da eradicare: le soluzioni basate su blacklist, relay filtering e proxy control sono difese parziali contro un problema che ha radici nel protocollo stesso.

---

### **8. Conclusione**

Dalla prima "catena di Sant'Antonio" fino ai moderni attacchi automatizzati, lo spam è passato da fenomeno fastidioso a **problema sistemico** di Internet. Le sue radici sono tecniche (open relay, proxy vulnerabili, indirizzamento dinamico), ma anche economiche e sociali: finché inviare milioni di messaggi costa meno di un centesimo, lo spam resta conveniente per qualcuno.

Oggi la lotta allo spam si combatte con **filtri intelligenti, controlli DNS, autenticazioni SPF/DKIM e reti anti-botnet**, ma la lezione storica rimane: la sicurezza di Internet dipende dalla **corretta configurazione dei suoi protocolli più semplici**.
