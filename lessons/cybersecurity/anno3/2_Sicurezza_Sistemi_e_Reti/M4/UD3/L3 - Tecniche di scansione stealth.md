## **Lezione 3: Tecniche di scansione stealth**

### **1. Introduzione**

Le tecniche di **stealth scanning** cercano di raccogliere informazioni su host, porte e filtri riducendo la probabilità di essere rilevate. Non sono davvero invisibili: rendono solo più difficile il lavoro di firewall, IDS e sistemi di logging rispetto a una scansione TCP `connect()` tradizionale.

L'idea comune è evitare o alterare il normale three-way handshake, usando pacchetti con flag anomali, frammenti IP o host intermedi. In questo modo lo scanner prova a ottenere informazioni senza aprire sessioni complete e senza produrre log applicativi evidenti.

> 📌 "Stealth" non significa "non rilevabile": significa meno evidente, più ambiguo o più difficile da correlare.

![](imgs/Pasted%20image%2020260709010335.png)

---

### **2. SYN/ACK Scan**

#### **2.1. Idea di base**

Una prima tecnica usa pacchetti `SYN/ACK` al posto dei normali `SYN`. I pacchetti `SYN` sono facili da riconoscere come inizio di una scansione o di una connessione; un `SYN/ACK`, invece, può sembrare parte di un handshake già in corso.

L'obiettivo è sfruttare la risposta prevista dallo stack TCP quando riceve un pacchetto inatteso:

- se la porta è **chiusa**, il target risponde con `RST`;

- se la porta è **aperta**, il pacchetto può essere scartato senza risposta, perché non corrisponde a una connessione iniziata correttamente.

![](imgs/Pasted%20image%2020260709010352.png)

Questa logica è "inversa" rispetto alla SYN scan classica: l'informazione forte arriva dalla risposta delle porte chiuse, mentre la mancata risposta può indicare apertura o filtraggio.

#### **2.2. Interpretazione**

|Risposta al `SYN/ACK`|Interpretazione possibile|
|---|---|
|`RST`|Porta chiusa|
|Nessuna risposta|Porta aperta oppure pacchetto filtrato/perso|
|ICMP unreachable|Traffico filtrato|

> ⚠️ Questa tecnica è vulnerabile ai falsi positivi: se un pacchetto viene perso o filtrato, l'assenza di risposta può essere scambiata per porta aperta.

---

### **3. ACK Scan**

#### **3.1. Scopo**

L'**ACK scan** non serve principalmente a distinguere porta aperta e chiusa. Serve a capire se il traffico verso una porta è filtrato o meno e, in particolare, a ricavare informazioni sulla presenza e sul comportamento di un firewall.

Lo scanner invia un pacchetto TCP con flag `ACK` verso una porta del target.

![](imgs/Pasted%20image%2020260709010420.png)

#### **3.2. Interpretazione**

|Risposta|Interpretazione|
|---|---|
|`RST`|Traffico non filtrato: il pacchetto ha raggiunto lo stack TCP|
|Nessuna risposta|Traffico probabilmente filtrato|
|ICMP unreachable / administratively prohibited|Traffico filtrato|

Un firewall stateless semplice potrebbe lasciar passare un `ACK` perché sembra appartenere a una connessione già avviata. Un firewall stateful, invece, può verificare se quella connessione esiste davvero e bloccare il pacchetto se è fuori stato.

> 📌 L'ACK scan mappa i filtri più che i servizi: dice se il pacchetto passa, non se dietro quella porta c'è un servizio applicativo.

---

### **4. Window Scan**

#### **4.1. Principio**

Il **Window scan** è una variante dell'ACK scan. Anche qui si inviano pacchetti `ACK`, ma si osserva un dettaglio in più: il valore del campo TCP **Window** nel pacchetto `RST` di risposta.

Su alcuni sistemi operativi, storicamente, le risposte `RST` generate da porte aperte e chiuse presentavano valori diversi:

- porta **aperta**: `RST` con window size positiva;

- porta **chiusa**: `RST` con window size pari a zero.

![](imgs/Pasted%20image%2020260709010448.png)

Questa differenza implementativa permette allo scanner di inferire lo stato della porta anche quando l'ACK scan ordinario direbbe solo `unfiltered`.

#### **4.2. Limiti**

Il metodo dipende fortemente dallo stack TCP/IP del sistema operativo. Se il sistema non usa questa differenza nel campo Window, il risultato è ambiguo o inutile. Inoltre:

- nessuna risposta può indicare filtro o perdita;

- ICMP unreachable suggerisce filtraggio;

- IDS moderni possono riconoscere sequenze anomale di ACK.

> ⚠️ Il Window scan non è una regola universale di TCP: sfrutta comportamenti storici di alcune implementazioni.

---

### **5. FIN, NULL e Xmas Scan**

#### **5.1. Flag anomali**

Altre tecniche stealth usano pacchetti TCP con combinazioni di flag non tipiche dell'apertura di una connessione:

- **FIN scan**: invia un pacchetto con solo `FIN`;

- **NULL scan**: invia un pacchetto senza flag TCP attivi;

- **Xmas scan**: accende più flag, tipicamente `FIN`, `PSH` e `URG`, così chiamata perché il pacchetto è "illuminato" da molti flag come un albero di Natale.

![](imgs/Pasted%20image%2020260709010509.png)

#### **5.2. Interpretazione secondo lo standard**

Secondo il comportamento previsto per TCP:

- una porta **chiusa** dovrebbe rispondere con `RST`;

- una porta **aperta** dovrebbe scartare il pacchetto senza risposta, perché non è un segmento valido per una connessione in stato `LISTEN`.

|Risposta|Interpretazione possibile|
|---|---|
|`RST`|Porta chiusa|
|Nessuna risposta|Porta aperta oppure filtrata|
|ICMP unreachable|Filtrata|

Queste scansioni possono superare firewall stateless che filtrano soprattutto `SYN`, perché non sembrano tentativi normali di apertura di connessione.

#### **5.3. Limiti pratici**

Il problema principale è l'ambiguità:

- assenza di risposta può significare porta aperta, pacchetto filtrato o perdita;

- alcuni sistemi operativi non seguono rigorosamente la specifica e rispondono sempre con `RST`;

- IDS moderni riconoscono facilmente pattern FIN/NULL/Xmas ripetuti.

> 📌 FIN/NULL/Xmas sono utili soprattutto per capire la logica degli stack e dei filtri. Nella pratica moderna non vanno considerati invisibili.

---

### **6. Fragmentation Scan**

#### **6.1. Idea**

La **fragmentation scan** frammenta i pacchetti di probe in piccoli datagrammi IP. L'obiettivo è rendere più difficile l'ispezione da parte di firewall o IDS che analizzano i pacchetti singolarmente senza riassemblare correttamente i frammenti.

Per esempio, i flag TCP o parte dell'header possono essere distribuiti su più frammenti. Se il dispositivo di sicurezza non mantiene stato o non ricostruisce il pacchetto, può non riconoscere la scansione.

#### **6.2. Vantaggi**

- Aumenta la difficoltà di rilevamento basato su signature semplici.

- Può aggirare sistemi che non bufferizzano o riassemblano sequenze di frammenti.

- Può rendere meno immediata la classificazione del probe.

#### **6.3. Svantaggi**

- È più lenta.

- È sensibile a packet loss e riordinamento.

- Può produrre risultati poco affidabili.

- Alcuni firewall, sniffer o stack vulnerabili possono comportarsi male o andare in crash.

- Firewall/IDS moderni riassemblano e normalizzano i frammenti, neutralizzando la tecnica.

> ⚠️ La frammentazione è una tecnica di evasione fragile: se il difensore normalizza il traffico prima dell'ispezione, il vantaggio dello scanner scompare.

---

### **7. Idle Scan**

#### **7.1. Concetto generale**

L'**idle scan** è una tecnica stealth più sofisticata perché usa un terzo host, detto **zombie**, come intermediario. Lo scanner reale non comunica direttamente con la vittima in modo riconoscibile: costruisce pacchetti spoofati fingendo che provengano dallo zombie.

Lo zombie deve avere due proprietà:

- essere poco attivo, cioè non generare traffico proprio che alteri le misure;

- usare un campo IP **Identification** (`IP ID`) prevedibile o incrementale.

L'IP ID viene usato perché molti sistemi lo incrementano quando inviano pacchetti. Osservando quanto aumenta, lo scanner può dedurre se lo zombie ha inviato pacchetti a seguito dell'interazione con la vittima.

![](imgs/Pasted%20image%2020260709010549.png)

#### **7.2. Porta aperta sulla vittima**

Caso in cui la porta target è aperta:

1. lo scanner interroga lo zombie, per esempio con un `SYN/ACK`, e osserva il `RST` di risposta con un certo `IP ID`, per esempio `550`;

2. lo scanner invia alla vittima un `SYN` spoofato, usando come IP sorgente l'indirizzo dello zombie;

3. la vittima vede un `SYN` apparentemente proveniente dallo zombie e, se la porta è aperta, risponde allo zombie con `SYN/ACK`;

4. lo zombie non ha iniziato alcuna connessione, quindi risponde alla vittima con `RST`;

5. questa risposta incrementa l'IP ID dello zombie;

6. lo scanner interroga di nuovo lo zombie e osserva che l'IP ID è aumentato più del normale, tipicamente di due unità rispetto alla prima osservazione.

L'incremento extra indica che lo zombie ha ricevuto un `SYN/ACK` dalla vittima e ha risposto con `RST`. Quindi la porta interrogata sulla vittima era aperta.

#### **7.3. Porta chiusa sulla vittima**

Se la porta target è chiusa:

1. lo scanner misura l'IP ID iniziale dello zombie;

2. invia alla vittima un `SYN` spoofato con sorgente zombie;

3. la vittima risponde allo zombie con `RST`, perché la porta è chiusa;

4. lo zombie ignora il `RST` e non genera una nuova risposta;

5. quando lo scanner interroga di nuovo lo zombie, l'IP ID è aumentato solo per la normale risposta data allo scanner.

In questo caso non c'è incremento extra: lo scanner deduce che la porta è chiusa.

#### **7.4. Porta filtrata**

Se il traffico verso la vittima è filtrato, il `SYN` spoofato non produce alcuna risposta utile verso lo zombie. Dal punto di vista dello scanner, il risultato può assomigliare al caso della porta chiusa: l'IP ID dello zombie cresce solo per le interazioni dirette con lo scanner.

Per questo idle scan può distinguere bene il caso **open**, ma può lasciare ambiguità tra **closed** e **filtered**.

![](imgs/Pasted%20image%2020260709010708.png)

#### **7.5. Vantaggi e limiti**

Vantaggi:

- la vittima vede traffico proveniente dallo zombie, non dallo scanner reale;

- non c'è una conversazione diretta riconoscibile tra scanner e vittima;

- può aggirare alcune forme di logging basate solo sull'IP sorgente.

Limiti:

- richiede uno zombie realmente idle;

- richiede IP ID prevedibile;

- traffico casuale dello zombie altera la misura;

- molti sistemi moderni randomizzano o gestiscono l'IP ID per flusso;

- IDS e sistemi di correlazione possono comunque rilevare anomalie.

> 📌 L'idle scan è potente perché trasforma una variazione indiretta dell'IP ID in informazione sullo stato della porta della vittima.

---

### **8. Rilevazione e contromisure**

#### **8.1. Rilevare scansioni stealth**

I difensori possono cercare:

- pacchetti `SYN/ACK` inattesi;

- sequenze di `ACK` non associate a connessioni esistenti;

- pattern FIN/NULL/Xmas;

- frammentazione anomala;

- molte risposte `RST` verso sorgenti sospette;

- variazioni anomale di IP ID su host che potrebbero essere usati come zombie.

La correlazione temporale è essenziale: un singolo pacchetto può sembrare innocuo, ma una sequenza sistematica su più porte o più host rivela la scansione.

#### **8.2. Contromisure**

Le difese principali sono:

- usare firewall **stateful**, che scartano pacchetti fuori stato;

- normalizzare e riassemblare i frammenti prima dell'ispezione;

- filtrare combinazioni di flag TCP anomale;

- randomizzare o rendere non globale l'IP ID;

- centralizzare log e correlare eventi;

- usare IDS/IPS con signature e analisi comportamentale;

- applicare rate limiting e blocchi dinamici;

- usare honeypot o tarpit per osservare scanner e rallentarli.

---

### **9. Sintesi finale**

|Tecnica|Idea|Segnale principale|Limite|
|---|---|---|---|
|SYN/ACK scan|Usare `SYN/ACK` invece di `SYN`|`RST` indica porta chiusa|Nessuna risposta ambigua|
|ACK scan|Mappare filtri/firewall|`RST` = unfiltered|Non distingue open/closed|
|Window scan|Leggere TCP Window nel `RST`|Window positiva vs zero|Dipende dall'OS|
|FIN/NULL/Xmas|Usare flag anomali|`RST` = closed, silenzio = open/filtered|OS e firewall moderni riducono affidabilità|
|Fragmentation scan|Spezzare probe in frammenti IP|Evasione di filtri semplici|Fragile, lenta, normalizzabile|
|Idle scan|Usare zombie e IP ID|Incremento extra dell'IP ID|Richiede zombie idle e IP ID prevedibile|

> ✅ Punto d'esame: le scansioni stealth sfruttano ambiguità delle specifiche o differenze di implementazione. Il loro risultato va sempre interpretato probabilisticamente: assenza di risposta, `RST`, window size e IP ID sono indizi, non verità assolute.
