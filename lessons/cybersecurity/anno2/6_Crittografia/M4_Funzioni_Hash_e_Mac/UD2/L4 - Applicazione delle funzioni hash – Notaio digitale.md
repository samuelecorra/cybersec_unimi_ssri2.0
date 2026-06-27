## **Lezione 4: Applicazione delle funzioni hash – Notaio digitale**

### **1. Introduzione**

In questa lezione vengono studiate alcune applicazioni delle funzioni hash, concentrandosi in modo particolare sulla realizzazione di un servizio di **notaio digitale**.

Il servizio considerato è un servizio di:

> **digital time stamping**

cioè di **marcatura temporale digitale** dei documenti.

L'idea è sostituire, almeno in parte, alcune funzioni svolte nel mondo reale da figure o meccanismi come:

- notaio;
- servizio postale;
- registro di protocollo;
- deposito brevettuale;
- pubblicazione su giornale.

Il problema da risolvere è:

> dimostrare in modo affidabile che un certo documento esisteva in un certo momento.

Le funzioni hash sono centrali perché permettono di legare una prova temporale a un documento senza dover necessariamente inviare o rivelare il documento stesso.

> 📌 Il notaio digitale usa il digest del documento come impronta compatta e verificabile: chiunque possieda il documento può ricalcolare il digest e confrontarlo con quello marcato temporalmente.

---

### **2. Marca Temporale**

Una **marca temporale** è un'informazione aggiunta a un documento o associata a un documento.

Serve a provare che il documento:

- esisteva prima di un certo istante;
- oppure è stato prodotto dopo un certo istante;
- non è stato modificato rispetto all'impronta registrata.

Nel transcript, la marca temporale viene descritta come:

> un'aggiunta a un documento che prova che il documento stesso è stato prodotto prima o dopo un fissato momento.

Il punto essenziale è che non basta dire:

> questo documento contiene una data.

Chiunque può scrivere una data dentro un file.

Serve invece una prova verificabile da terzi.

> ⚠️ Una data scritta nel documento non è una marca temporale affidabile. La marca deve essere prodotta o vincolata da un meccanismo esterno e verificabile.

---

### **3. Marcature Temporali nel Mondo Reale**

Nel mondo reale esistono diversi modi per ottenere una data associata a un documento.

#### **3.1. Deposito presso un Notaio**

Un soggetto può depositare un documento presso un notaio.

Il notaio attesta:

- chi ha depositato il documento;
- quando lo ha depositato;
- quale documento è stato depositato.

Questo fornisce una data certa, ma ha costi e tempi pratici.

#### **3.2. Invio Postale a Sé Stessi**

Un metodo informale consiste nell'inviare il documento a sé stessi tramite posta.

Il bollo o annullo postale fornisce una data.

Il ragionamento è:

> se il documento era nella busta timbrata il giorno indicato, allora esisteva almeno a quella data.

Naturalmente questo metodo ha limiti probatori e pratici.

#### **3.3. Deposito di una Domanda di Brevetto**

Una domanda di brevetto associa un contenuto tecnico a una data di deposito.

Questo può servire a dimostrare che una certa idea o invenzione era stata formalizzata entro una certa data.

#### **3.4. Pubblicazione su un Giornale**

Pubblicare un documento o un suo riferimento su un giornale rende pubblica l'esistenza di quel contenuto a una certa data.

#### **3.5. Registro di Protocollo**

Un'organizzazione può usare un registro di protocollo per associare documenti a numeri progressivi e date.

#### **3.6. Foto con Quotidiano**

Un esempio intuitivo è la fotografia di un oggetto o di una persona accanto al quotidiano del giorno.

Questo metodo è spesso citato per dimostrare che una certa scena è successiva alla pubblicazione di quel giornale.

> 💡 Tutti questi esempi hanno lo stesso scopo: collegare un documento o un evento a una data verificabile.

---

### **4. Prima e Dopo una Data**

Il transcript sottolinea una distinzione importante.

In genere è facile provare che un documento è stato prodotto **dopo** una certa data.

Per esempio, se in una foto compare il giornale del 10 marzo, la foto non può essere stata scattata prima della disponibilità di quel giornale.

Quindi si dimostra:

> il documento è successivo a quella data.

È invece più difficile provare che un documento è stato prodotto **prima** di una certa data.

Per dimostrarlo serve una traccia esterna che attesti che il documento, o almeno la sua impronta, esisteva già.

Il servizio di digital timestamping serve proprio a ottenere questa prova.

> 📌 Il problema interessante della marcatura temporale è dimostrare l'esistenza precedente: il documento esisteva già prima di un certo momento.

---

### **5. Servizio di Digital Timestamping**

Un servizio di **digital timestamping** deve permettere a un utente di ottenere una prova verificabile.

Dato un documento $D$, l'utente vuole poter dimostrare in futuro che:

$$
D
$$

esisteva al tempo:

$$
t
$$

Un servizio di questo tipo dovrebbe garantire:

- integrità del documento attestato;
- data certa;
- verificabilità pubblica;
- ridotta fiducia nella terza parte;
- protezione della riservatezza del documento;
- efficienza nella comunicazione e nella memorizzazione.

Le funzioni hash servono soprattutto per:

- ridurre la dimensione dell'informazione inviata;
- nascondere il contenuto del documento;
- legare la marca temporale a un'impronta univoca del documento.

---

### **6. Soluzione Elementare con Autorità Fidata**

La prima soluzione possibile è affidarsi completamente a un'autorità fidata.

Chiamiamola:

> **Time Stamping Authority**

o più semplicemente autorità affidata.

#### **6.1. Protocollo Base**

Alice vuole marcare temporalmente un documento $D$.

La procedura elementare è:

1. Alice invia il documento $D$ all'autorità affidata.
2. L'autorità registra data e ora di ricezione.
3. L'autorità crea un certificato contenente:
   - identità di Alice;
   - documento $D$ o suo riferimento;
   - data;
   - ora.
4. L'autorità firma digitalmente il certificato.
5. Alice conserva il certificato firmato.

In forma astratta, l'autorità produce:

$$
\operatorname{Sign}_{TSA}(\text{Alice}, D, t)
$$

dove:

- $\operatorname{Sign}_{TSA}$ indica la firma digitale dell'autorità;
- $t$ è la marca temporale;
- $D$ è il documento.

#### **6.2. Verifica**

In seguito, Alice può presentare:

- il documento $D$;
- il certificato firmato dall'autorità.

Chi verifica controlla:

- la firma digitale dell'autorità;
- la data riportata;
- la corrispondenza con il documento.

Se tutto è valido, si accetta che l'autorità abbia visto il documento alla data indicata.

#### **6.3. Problemi della Soluzione Elementare**

Questa soluzione ha però vari problemi.

Il primo problema è la **dimensione del documento**.

Se $D$ è grande, trasmetterlo all'autorità può essere costoso.

Il secondo problema è la **memorizzazione**.

L'autorità dovrebbe archiviare documenti potenzialmente molto grandi.

Il terzo problema è la **riservatezza**.

Il contenuto del documento viene rivelato all'autorità.

Il quarto problema è la **fiducia totale**.

L'autorità potrebbe comportarsi in modo scorretto o negligente.

> ⚠️ Una soluzione basata sull'invio del documento intero richiede troppa fiducia nell'autorità e non protegge la privacy del contenuto.

---

### **7. Soluzione con Hash del Documento**

Per ridurre i problemi della soluzione elementare si usa una funzione hash.

Alice non invia il documento $D$.

Calcola invece:

$$
h_D = H(D)
$$

e invia all'autorità solo il digest:

$$
h_D
$$

#### **7.1. Protocollo**

La procedura diventa:

1. Alice calcola $h_D = H(D)$.
2. Alice invia $h_D$ all'autorità affidata.
3. L'autorità registra data e ora.
4. L'autorità firma un certificato contenente:
   - identità di Alice;
   - digest $h_D$;
   - data e ora $t$.
5. Alice conserva documento e certificato.

Il certificato può essere scritto come:

$$
\operatorname{Sign}_{TSA}(\text{Alice}, h_D, t)
$$

#### **7.2. Vantaggi**

Questa soluzione risolve diversi problemi.

Il primo vantaggio è la **riduzione della comunicazione**.

Il digest ha lunghezza fissata, per esempio:

- $128$ bit nel caso di MD5;
- $160$ bit nel caso di SHA-1;
- $256$ bit nel caso di SHA-256.

Quindi anche se il documento è enorme, l'informazione inviata è piccola.

Il secondo vantaggio è la **riduzione della memorizzazione**.

L'autorità può conservare digest e certificati, non copie integrali dei documenti.

Il terzo vantaggio è la **riservatezza**.

L'autorità vede solo il digest.

Se la funzione hash è one-way, dal digest non può ricostruire il documento.

Il quarto vantaggio è la **verificabilità**.

Chiunque abbia il documento $D$ può calcolare:

$$
H(D)
$$

e controllare che coincida con il digest firmato.

> 📌 L'hash permette di marcare temporalmente il documento senza rivelarlo e senza trasmetterlo integralmente.

#### **7.3. Proprietà Richieste alla Funzione Hash**

Questa soluzione richiede che la funzione hash abbia almeno:

- resistenza alla preimmagine;
- resistenza alla seconda preimmagine;
- resistenza alle collisioni.

La resistenza alla seconda preimmagine è importante perché Alice non deve poter ottenere una marca su $D$ e poi presentare un documento diverso $D'$ con lo stesso digest:

$$
H(D') = H(D)
$$

La resistenza alle collisioni è importante perché un attaccante non deve poter costruire due documenti diversi con lo stesso digest e usare la marca temporale in modo ambiguo.

---

### **8. Problema della Fiducia nell'Autorità**

Anche usando gli hash resta un problema:

> l'autorità affidata potrebbe comportarsi male.

Per esempio potrebbe:

- antedatare una marca temporale;
- postdatare una marca temporale;
- firmare certificati incoerenti;
- perdere registrazioni;
- negare di aver ricevuto una richiesta;
- accordarsi con un utente malevolo;
- creare marche temporali non coerenti con l'ordine reale delle richieste.

Il transcript sottolinea che non conviene fidarsi completamente di una terza parte.

Per ridurre il problema vengono presentate due famiglie di soluzioni:

- protocolli distribuiti, senza autorità affidata unica;
- protocolli con autorità affidata ma con collegamenti tra marche, in modo da ridurre la possibilità di imbrogliare.

---

### **9. Protocollo Distribuito**

La prima soluzione alternativa è un protocollo distribuito.

Non esiste una singola autorità affidata.

Si usano invece $K$ partecipanti.

#### **9.1. Idea Generale**

Alice vuole ottenere una marca temporale per il documento $D$.

Calcola:

$$
h_D = H(D)
$$

Poi usa $h_D$ come input a un generatore pseudocasuale.

Il generatore seleziona:

$$
K
$$

partecipanti allo schema.

Alice invia a questi $K$ partecipanti la richiesta di marca temporale.

Ogni partecipante risponde con una marca, cioè con una testimonianza firmata.

#### **9.2. Perché la Scelta Dipende dal Documento**

Il fatto che i partecipanti siano scelti in funzione di:

$$
h_D
$$

è importante.

La selezione non è completamente controllata da Alice.

Dipende dal documento stesso.

Questo rende più difficile per Alice scegliere solo partecipanti corrotti o complici.

> 💡 Il digest del documento diventa una sorgente deterministica per selezionare testimoni difficili da predire o manipolare.

#### **9.3. Vantaggio**

Se $K$ è abbastanza grande, Alice dovrebbe corrompere tutti i $K$ partecipanti per falsificare in modo convincente la marca temporale.

Questo distribuisce la fiducia.

Non c'è un unico soggetto che può tradire il sistema.

#### **9.4. Svantaggi**

Il protocollo distribuito ha però problemi pratici.

Il primo è la necessità di contattare molte persone o molti server.

Alice deve ottenere risposte da più partecipanti.

Questo può essere:

- lento;
- complesso;
- fragile se alcuni partecipanti non sono disponibili.

Il secondo problema riguarda le firme digitali.

Al momento della verifica, potrebbe essere passato molto tempo.

La chiave privata di un partecipante potrebbe essere stata compromessa.

Oppure l'algoritmo di firma usato potrebbe essere stato rotto.

In quel caso la validità storica della marca diventa più difficile da valutare.

> ⚠️ Un protocollo distribuito riduce la fiducia in una singola autorità, ma aumenta complessità, dipendenze operative e problemi di verifica nel lungo periodo.

---

### **10. Protocollo con Autorità Collegata nel Tempo**

La seconda famiglia di soluzioni mantiene un'autorità affidata, ma limita la sua capacità di imbrogliare.

L'idea è:

> collegare tra loro tutte le richieste di marcatura temporale ricevute in intervalli successivi.

Il servizio riceve richieste in intervalli temporali prefissati.

Per ogni intervallo:

- raccoglie le richieste;
- costruisce un albero di hash;
- calcola una radice;
- collega la radice con le radici degli intervalli precedenti;
- invia a ogni utente una marca temporale verificabile.

Questo impedisce all'autorità di modificare una richiesta o inserirne una retroattivamente senza alterare valori pubblicamente verificabili.

---

### **11. Albero di Hash per un Intervallo**

Supponiamo che in un certo intervallo di tempo arrivino otto richieste.

Le richieste contengono digest:

$$
h_1, h_2, h_3, h_4, h_5, h_6, h_7, h_8
$$

Questi valori diventano le foglie di un albero binario.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.1. Primo Livello**

Si calcolano hash a coppie:

$$
h_{12} = H(h_1 \parallel h_2)
$$

$$
h_{34} = H(h_3 \parallel h_4)
$$

$$
h_{56} = H(h_5 \parallel h_6)
$$

$$
h_{78} = H(h_7 \parallel h_8)
$$

#### **11.2. Secondo Livello**

Poi:

$$
h_{14} = H(h_{12} \parallel h_{34})
$$

e:

$$
h_{58} = H(h_{56} \parallel h_{78})
$$

#### **11.3. Radice**

Infine si calcola:

$$
h_{18} = H(h_{14} \parallel h_{58})
$$

Questo valore è la radice dell'albero.

La radice dipende da tutti i valori nelle foglie.

Se cambia una sola foglia, cambia il cammino verso la radice e, salvo collisioni, cambia la radice.

> 📌 La radice dell'albero è un'impronta compatta di tutte le richieste ricevute in quell'intervallo di tempo.

---

### **12. Superhash e Collegamento tra Intervalli**

Il transcript introduce il concetto di **superhash**.

Ogni intervallo produce una radice.

Le radici degli intervalli successivi vengono collegate tra loro in una catena.

Supponiamo che:

- $S_1$ sia il superhash del primo intervallo;
- $S_2$ quello del secondo;
- $S_3$ quello del terzo.

Ogni nuovo valore può dipendere:

- dalla radice dell'albero dell'intervallo corrente;
- dal superhash dell'intervallo precedente.

In forma astratta:

$$
S_i = H(S_{i-1} \parallel r_i)
$$

dove $r_i$ è la radice dell'albero dell'intervallo $i$.

In questo modo ogni superhash dipende da tutta la storia precedente.

> 📌 Collegare le radici nel tempo impedisce all'autorità di riscrivere retroattivamente un intervallo senza modificare anche tutti i valori successivi.

---

### **13. Contenuto della Marca Temporale**

Ogni richiesta riceve una marca temporale.

La marca deve contenere abbastanza informazioni per permettere la verifica.

Nel transcript, una marca include:

- identificativo dell'utente;
- digest ricevuto, per esempio $h_i$;
- data e ora della richiesta;
- radice dell'albero dell'intervallo;
- informazioni necessarie per verificare l'inclusione del digest nell'albero;
- valori di collegamento con l'intervallo precedente e con quello appena calcolato;
- firma digitale del time stamping service.

In forma concettuale:

$$
\operatorname{Sign}_{TSS}(\text{utente}, h_i, t, r, \text{path}, S_{i-1}, S_i)
$$

dove:

- $TSS$ è il time stamping service;
- $r$ è la radice dell'albero;
- $\text{path}$ contiene i valori necessari a ricostruire la radice;
- $S_{i-1}$ e $S_i$ collegano l'intervallo alla catena temporale.

> 📌 La marca temporale non contiene tutto l'albero: contiene solo il cammino di verifica necessario per la richiesta dell'utente.

---

### **14. Esempio di Verifica per la Terza Richiesta**

Il transcript propone l'esempio della terza richiesta in un intervallo con otto foglie.

Supponiamo che Alice abbia inviato:

$$
h_3
$$

La radice è:

$$
h_{18}
$$

Per verificare che $h_3$ sia incluso nell'albero, Alice non ha bisogno di tutti i valori.

Ha bisogno dei valori fratelli lungo il cammino verso la radice.

#### **14.1. Primo Passo**

Alice conosce $h_3$.

Per calcolare:

$$
h_{34}
$$

deve ricevere:

$$
h_4
$$

Infatti:

$$
h_{34} = H(h_3 \parallel h_4)
$$

#### **14.2. Secondo Passo**

Per calcolare:

$$
h_{14}
$$

deve ricevere:

$$
h_{12}
$$

Infatti:

$$
h_{14} = H(h_{12} \parallel h_{34})
$$

#### **14.3. Terzo Passo**

Per calcolare la radice:

$$
h_{18}
$$

deve ricevere:

$$
h_{58}
$$

Infatti:

$$
h_{18} = H(h_{14} \parallel h_{58})
$$

Quindi per verificare la terza richiesta sono sufficienti:

- $h_4$;
- $h_{12}$;
- $h_{58}$.

Questi valori costituiscono il cammino di autenticazione della foglia $h_3$.

Alice ricalcola la radice e la confronta con la radice firmata nella marca temporale.

Se coincidono, Alice sa che $h_3$ è stato effettivamente usato per costruire quell'albero.

> ✅ La verifica dell'inclusione richiede solo un numero logaritmico di valori rispetto al numero totale di richieste dell'intervallo.

---

### **15. Perché l'Autorità non Può Imbrogliare Facilmente**

Una volta fissata e firmata la radice dell'albero, l'autorità non può modificare liberamente le richieste.

#### **15.1. Non Può Inserire Retroattivamente un Nuovo Valore**

Se l'autorità prova ad aggiungere un nuovo digest in un intervallo passato, la struttura dell'albero cambia.

Cambiano:

- foglie;
- nodi intermedi;
- radice.

La radice non coinciderebbe più con quella firmata o pubblicata.

#### **15.2. Non Può Cambiare l'Ordine Temporale**

Se l'ordine delle richieste è parte della struttura dell'albero, cambiare l'ordine delle foglie cambia i valori intermedi.

Anche in questo caso cambia la radice.

#### **15.3. Non Può Modificare un Singolo Digest**

Se cambia anche un solo valore:

$$
h_i
$$

allora cambia il cammino fino alla radice.

Per mantenere la stessa radice dopo una modifica, l'autorità dovrebbe trovare una collisione nella funzione hash.

#### **15.4. Dipendenza dalla Resistenza alle Collisioni**

La sicurezza dello schema dipende dalla resistenza alle collisioni della funzione hash.

Se fosse possibile modificare una foglia o un nodo e mantenere la stessa radice, allora si sarebbe trovata una collisione:

$$
H(x) = H(x')
$$

con:

$$
x \ne x'
$$

> ⚠️ Il meccanismo dell'albero hash è sicuro solo finché la funzione hash usata è resistente alle collisioni.

---

### **16. Pubblicazione dei Superhash**

Per ridurre ulteriormente la possibilità di manipolazione, il servizio può pubblicare periodicamente i superhash.

La pubblicazione rende i valori:

- visibili;
- verificabili;
- difficili da modificare retroattivamente.

Esempi di pubblicazione:

- giornali;
- rete;
- supporti pubblici;
- archivi accessibili;
- bollettini periodici.

Se un superhash è pubblicato in una fonte esterna, l'autorità non può sostituirlo silenziosamente con un altro valore.

---

### **17. Implementazioni Pratiche**

Il transcript cita implementazioni pratiche e commerciali di questi schemi.

#### **17.1. Surety Digital Notary**

Un esempio è il **Digital Notary** di Surety.

Il sistema usa una funzione hash con digest di:

$$
288 \text{ bit}
$$

ottenuta tramite uso combinato di:

- MD5;
- SHA.

Il sistema usa una struttura ad albero.

L'unità temporale corrisponde a:

$$
1 \text{ secondo}
$$

Ogni documento riceve un numero seriale.

Il superhash viene pubblicato in posti accessibili, per esempio:

- giornali;
- rete.

Lo scopo della pubblicazione è rendere pubblica e non modificabile la sequenza delle marche.

#### **17.2. PGP Digital Timestamping Service**

Il transcript cita anche il **PGP Digital Timestamping Service**.

Questo servizio firma ogni documento o richiesta ricevuta.

Le marche emesse possono essere esaminate pubblicamente.

Ogni giorno viene pubblicato:

- il numero seriale dell'ultima firma effettuata;
- l'insieme delle marche emesse nella giornata.

Questa pubblicazione permette controlli esterni sulla coerenza del servizio.

> 📌 Le implementazioni pratiche rafforzano il modello teorico pubblicando valori di controllo in luoghi accessibili e difficili da alterare retroattivamente.

---

### **18. Proprietà di Sicurezza Ottenute**

Un buon servizio di digital timestamping basato su hash può fornire varie proprietà.

#### **18.1. Integrità**

Se il documento cambia, cambia il digest.

Quindi la marca temporale non verifica più.

#### **18.2. Riservatezza del Documento**

L'autorità riceve solo:

$$
H(D)
$$

Non riceve necessariamente:

$$
D
$$

Quindi il contenuto resta privato.

#### **18.3. Data Certa**

La firma dell'autorità e il collegamento nella struttura temporale attestano che la richiesta è stata inclusa in un certo intervallo.

#### **18.4. Non Alterabilità della Storia**

La catena di superhash rende difficile modificare eventi passati senza alterare anche valori successivi.

#### **18.5. Verificabilità**

Un verificatore può controllare:

- il digest del documento;
- la firma del TSS;
- il cammino nell'albero;
- la radice;
- il collegamento con i superhash pubblicati.

---

### **19. Limiti e Assunzioni**

Il sistema non è magico.

Funziona sotto alcune assunzioni.

#### **19.1. Funzione Hash Sicura**

La funzione hash deve essere resistente alle collisioni.

Se la funzione hash viene rotta, l'attaccante potrebbe tentare di sostituire documenti o nodi mantenendo lo stesso digest.

#### **19.2. Firma Digitale Sicura**

La firma del TSS deve essere verificabile e non falsificabile.

Se la chiave privata del TSS viene compromessa, bisogna gestire revoca, rinnovo e validità storica delle marche.

#### **19.3. Pubblicazione Affidabile**

La pubblicazione dei superhash deve essere accessibile e difficilmente alterabile.

Se il TSS controlla completamente anche il canale di pubblicazione, la garanzia si indebolisce.

#### **19.4. Gestione del Tempo**

Il TSS deve avere una fonte temporale affidabile.

Se l'orologio del servizio è manipolabile, la marca temporale perde significato.

> ⚠️ La sicurezza del timestamping combina hash, firme digitali, pubblicazione esterna e gestione affidabile del tempo.

---

### **20. Collegamento con le Lezioni Precedenti**

Le lezioni precedenti hanno introdotto:

- costruzione delle funzioni hash;
- MD4 e MD5;
- SHA-1;
- resistenza alle collisioni;
- attacco del compleanno;
- digest a lunghezza fissata.

Il notaio digitale mostra un'applicazione diretta di queste proprietà.

La proprietà cruciale è la resistenza alle collisioni.

Se un documento $D$ è marcato tramite:

$$
h_D = H(D)
$$

Alice non deve poter trovare un documento diverso $D'$ tale che:

$$
H(D') = h_D
$$

Altrimenti potrebbe ottenere una marca temporale per un documento e poi usarla per un altro.

Inoltre, nella struttura ad albero, il TSS non deve poter modificare le foglie mantenendo la stessa radice.

Anche questo richiederebbe collisioni.

---

### **21. Errori Concettuali da Evitare**

#### **21.1. Pensare che il TSS Debba Conoscere il Documento**

Non è necessario.

Il TSS può marcare il digest.

#### **21.2. Pensare che il Digest Sia una Cifratura**

Il digest non è una cifratura del documento.

È un'impronta.

Non deve essere invertibile.

#### **21.3. Pensare che la Firma del TSS da Sola Risolva Tutto**

Una firma su singole richieste non impedisce necessariamente antedatazioni o comportamenti scorretti dell'autorità.

Il collegamento tra richieste e intervalli riduce questo rischio.

#### **21.4. Confondere Albero di Hash e Catena Temporale**

L'albero collega le richieste dentro un intervallo.

La catena di superhash collega intervalli diversi nel tempo.

#### **21.5. Dimenticare la Dipendenza dalle Collisioni**

La radice dell'albero protegge le foglie solo se la funzione hash è resistente alle collisioni.

---

### **22. Conclusione**

Il digital timestamping è una delle applicazioni più significative delle funzioni hash.

Permette di dimostrare che un documento esisteva in un certo momento senza rivelare il documento stesso.

La soluzione più semplice consiste nel far firmare a un'autorità il documento e la data, ma questa soluzione è inefficiente e non protegge la riservatezza.

Usando un hash, Alice invia solo il digest:

$$
H(D)
$$

e l'autorità firma il digest con la data.

Per ridurre la fiducia nell'autorità, si possono usare:

- protocolli distribuiti con più testimoni;
- protocolli centralizzati con alberi di hash e superhash concatenati.

Nel protocollo con albero di hash, tutte le richieste di un intervallo vengono collegate in una radice.

Le radici vengono poi concatenate nel tempo.

La sicurezza dipende dalla resistenza alle collisioni della funzione hash e dalla validità della firma del time stamping service.

> ✅ Un servizio di notaio digitale sicuro usa l'hash per rappresentare il documento, l'albero di hash per collegare molte richieste e il superhash per collegare gli intervalli nel tempo.

---

### **23. Recap per l'Esame**

> ✅ Il digital timestamping serve a dimostrare che un documento esisteva prima o dopo un certo istante.

> ✅ Nel mondo reale esistono analoghi come notaio, servizio postale, brevetti, giornali e registri di protocollo.

> ✅ È più facile provare che qualcosa è successivo a una data che provare che esisteva prima di una data.

> ✅ Inviare il documento intero a un'autorità fidata crea problemi di comunicazione, memorizzazione e privacy.

> ✅ Inviare $H(D)$ risolve questi problemi: il digest è piccolo e non rivela il contenuto del documento.

> ✅ Un protocollo distribuito seleziona $K$ partecipanti usando il digest del documento e ottiene più testimonianze.

> ✅ Il protocollo distribuito riduce la fiducia in una singola autorità, ma richiede molti partecipanti e firme valide nel tempo.

> ✅ Un TSS centralizzato può ridurre la possibilità di imbrogliare collegando tutte le richieste in alberi di hash.

> ✅ In un albero di hash, la radice dipende da tutte le foglie.

> ✅ Per verificare una foglia basta conoscere i valori fratelli lungo il cammino verso la radice.

> ✅ I superhash collegano tra loro intervalli temporali successivi.

> ✅ Modificare una richiesta senza cambiare la radice richiederebbe trovare una collisione della funzione hash.

> ✅ Surety Digital Notary e PGP Digital Timestamping Service sono esempi pratici citati nella lezione.

> ⚠️ La sicurezza del servizio dipende dalla resistenza alle collisioni della funzione hash e dalla sicurezza della firma del TSS.
