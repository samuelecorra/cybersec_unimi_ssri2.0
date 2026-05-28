# **M2 UD1 Lezione 2 - Realizzazione del sottosistema di I/O (parte 1)**

---

### **1. Introduzione**

Questa lezione introduce alcuni aspetti relativi alla **realizzazione del sottosistema di ingresso/uscita**, cioè al modo in cui i principi generali di gestione delle periferiche vengono tradotti in componenti concreti del sistema operativo.

Gli aspetti principali sono:

- gestione e ordinamento delle richieste di I/O;
- strutture di supporto alla comunicazione con le periferiche;
- bufferizzazione;
- caching;
- spooling;
- locking dei dispositivi;
- gestione degli errori.

> 📌 Il sottosistema di I/O deve trasformare periferiche lente, diverse e spesso condivise in risorse gestibili in modo ordinato, efficiente e affidabile.

---

### **2. Schedulazione delle richieste di I/O**

Per mantenere una gestione efficiente delle periferiche è spesso opportuno raccogliere e ordinare le richieste provenienti dai vari processi.

La **schedulazione delle richieste di I/O** ha l'obiettivo di mantenere e gestire la coda delle richieste per una periferica, scegliendo l'ordine di esecuzione più opportuno per massimizzare l'efficienza d'uso della risorsa.

#### **2.1. Obiettivi**

La schedulazione permette di:

- ottimizzare l'uso della periferica;
- ridurre tempi di attesa e tempi morti;
- evitare conflitti tra richieste;
- servire le richieste secondo criteri coerenti con le esigenze del sistema.

#### **2.2. Politiche possibili**

Le politiche possono essere analoghe a quelle usate per la schedulazione dei processi:

- **FIFO**, in cui le richieste vengono servite nell'ordine di arrivo;
- **priorità**, in cui le richieste più importanti vengono servite prima;
- **scadenze**, in cui hanno precedenza le richieste con vincoli temporali;
- politiche miste o specifiche per la periferica.

> 💡 Ordinare le richieste non serve solo a essere equi: serve anche a usare meglio periferiche lente o con tempi di accesso molto variabili.

---

### **3. Bufferizzazione**

La **bufferizzazione** consiste nell'immagazzinare temporaneamente i dati trasferiti con una periferica.

Il suo scopo principale è adattare le velocità della sorgente e della destinazione della comunicazione, soprattutto quando una delle due è molto più veloce dell'altra.

#### **3.1. Adattamento delle velocità**

Se l'unità centrale è più veloce della periferica, il processo può scrivere i dati in un buffer in memoria centrale. Successivamente il sottosistema di I/O provvede a inoltrarli alla periferica con la velocità compatibile con il dispositivo.

Viceversa, la periferica può depositare le proprie risposte in un'area tampone, da cui il sistema operativo estrae i dati e li consegna al processo richiedente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Adattamento delle dimensioni**

La bufferizzazione consente anche di adattare le dimensioni dei dati trasferiti.

Alcune periferiche lavorano a carattere, altre a blocchi. Il buffer permette di trasformare più trasferimenti piccoli in un trasferimento più grande, oppure di suddividere un blocco in unità più piccole visibili al processo.

#### **3.3. Semantica della copia**

Un ulteriore vantaggio è il supporto alla **semantica della copia**.

I dati in trasferimento vengono "fotografati" al momento della richiesta di I/O. Anche se successivamente il processo modifica le proprie strutture dati, la periferica trasferirà i valori presenti nel buffer al momento della richiesta.

> 📌 La bufferizzazione disaccoppia processo e periferica: adatta velocità, dimensioni dei dati e momento effettivo del trasferimento.

---

### **4. Caching**

Il **caching** consiste nel conservare all'interno del sistema di elaborazione una copia dei dati letti da una periferica.

Quando un processo richiede un'informazione, il sistema operativo controlla prima se essa è già presente nella cache.

#### **4.1. Cache hit**

Se l'informazione è presente nella cache, viene restituita direttamente al processo senza accedere fisicamente alla periferica.

Questo riduce il tempo di accesso, perché una lettura in memoria centrale è molto più veloce di un accesso al canale e alla periferica.

#### **4.2. Cache miss**

Se l'informazione non è presente nella cache, il sistema operativo:

1. accede fisicamente alla periferica;
2. recupera il dato richiesto;
3. salva una copia nella cache;
4. restituisce l'informazione al processo.

In questo modo, richieste successive dello stesso dato potranno essere servite più rapidamente.

#### **4.3. Coerenza della cache**

Il sistema deve garantire che l'informazione posta nella cache sia aggiornata rispetto a quella memorizzata nella periferica.

Se il contenuto della periferica cambia, la copia in cache deve essere invalidata o aggiornata. In caso contrario, successive letture potrebbero restituire dati obsoleti.

> ⚠️ La cache migliora le prestazioni solo se il sistema mantiene la coerenza tra copia in memoria e dato reale sulla periferica.

---

### **5. Spooling**

Lo **spooling** è una tecnica usata per periferiche lente o che devono essere condivise in mutua esclusione, come le stampanti.

Lo scopo è bufferizzare l'output destinato a una periferica, separando completamente la richiesta di scrittura vista dal processo dall'effettiva scrittura fisica eseguita dal sistema operativo.

#### **5.1. Funzionamento**

Quando un processo vuole scrivere su una periferica lenta o dedicata, non scrive direttamente sul dispositivo. Produce invece l'uscita desiderata e la deposita in un'area di memoria centrale o, se troppo grande, in una memoria di massa chiamata **area di spooling**.

Per il processo, l'operazione di scrittura è completata quando i dati sono stati memorizzati nell'area di spooling.

Successivamente, il sistema operativo preleva i dati dall'area di spooling e li invia fisicamente alla periferica.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Vantaggi**

Lo spooling consente di:

- evitare che il processo resti sospeso a lungo in attesa della periferica;
- gestire periferiche lente;
- accodare richieste di più processi;
- separare la velocità del processo dalla velocità reale del dispositivo;
- centralizzare l'uso di periferiche che richiedono mutua esclusione.

> 📌 Lo spooling fa vedere al processo una scrittura già completata, mentre la scrittura fisica viene completata successivamente dal sistema operativo.

---

### **6. Locking dei dispositivi**

Alcune periferiche devono essere usate in **mutua esclusione**. In questi casi si usa un meccanismo di **locking**, cioè di prenotazione del dispositivo.

#### **6.1. Prenotazione**

Quando un processo richiede una periferica dedicata:

1. prova a prenotarla;
2. se la periferica è occupata, viene sospeso;
3. viene inserito in una coda di attesa;
4. quando arriva il suo turno, torna nello stato di ready-to-run;
5. la periferica gli viene assegnata finché non termina l'uso.

Al termine dell'operazione, il processo rilascia la periferica, che può essere assegnata a un altro processo già in coda o a un processo che la richiederà successivamente.

> ⚠️ Il locking è necessario per periferiche non condivisibili: senza mutua esclusione, richieste concorrenti potrebbero produrre output corrotti o comportamenti incoerenti.

---

### **7. Gestione degli errori**

Un insieme fondamentale di funzioni del sottosistema di I/O riguarda il riconoscimento e il trattamento degli errori.

Gli errori possono essere:

- **malfunzionamenti transitori**, che possono scomparire ripetendo l'operazione;
- **guasti permanenti**, che rendono impossibile completare correttamente l'operazione.

#### **7.1. Malfunzionamenti transitori**

Un malfunzionamento temporaneo può riguardare il canale di comunicazione, il cavo, l'interfaccia o la periferica.

Il sistema operativo deve riconoscere che l'operazione non è andata a buon fine analizzando le informazioni restituite dall'interfaccia. Può quindi:

1. ripetere l'operazione richiesta;
2. resettare il canale di comunicazione;
3. riconfigurare la periferica;
4. tentare nuovamente il trasferimento.

Se dopo una sequenza di ripetizioni e riconfigurazioni il malfunzionamento persiste, l'errore deve essere trattato come guasto permanente.

#### **7.2. Guasti permanenti**

I guasti permanenti includono situazioni fisiche non risolvibili tramite semplice ripetizione del comando:

- cavo di connessione staccato;
- periferica spenta;
- periferica rotta;
- interfaccia fisica di comunicazione guasta;
- canale di comunicazione non funzionante.

In questi casi ripetere indefinitamente il comando non risolve il problema.

#### **7.3. Segnalazione al processo**

Quando viene rilevato un guasto permanente, il sistema operativo deve:

- ricordare che la periferica è guasta;
- evitare ulteriori tentativi inutili finché il dispositivo o il canale non vengono riparati;
- segnalare l'evento al processo che aveva richiesto l'operazione.

Se l'applicazione prevede una gestione propria dell'errore, può decidere di continuare con prestazioni degradate o senza quella periferica. Se invece la periferica è vitale o l'applicazione non gestisce il caso, il sistema operativo può abortire il processo e segnalare l'errore all'utente.

> ✅ Una gestione robusta dell'I/O deve distinguere tra errori recuperabili e guasti permanenti, evitando sia l'abbandono prematuro sia tentativi infiniti inutili.

---

### **8. Sintesi finale**

Le funzioni principali della realizzazione del sottosistema di I/O sono:

- **schedulazione**, per ordinare le richieste e usare meglio le periferiche;
- **bufferizzazione**, per adattare velocità, dimensioni e semantica dei dati trasferiti;
- **caching**, per ridurre accessi ripetuti a periferiche lente;
- **spooling**, per disaccoppiare processi e periferiche lente o dedicate;
- **locking**, per garantire mutua esclusione sui dispositivi non condivisibili;
- **gestione degli errori**, per riconoscere malfunzionamenti transitori e guasti permanenti.

Queste tecniche costituiscono la base funzionale del sottosistema di I/O e preparano l'analisi delle strutture dati del kernel e delle prestazioni nella parte successiva.
