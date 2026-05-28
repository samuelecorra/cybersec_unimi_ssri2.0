# **M4 UD3 Lezione 2 - Chiamata di procedura remota (RPC)**

---

### **1. Introduzione**

Questa lezione presenta la prima tecnica per gestire la computazione in un ambiente distribuito: la **chiamata di procedura remota**, o **Remote Procedure Call** (**RPC**).

La RPC permette a un processo di attivare una procedura che non si trova sulla stessa macchina, ma su una macchina remota della rete.

Gli aspetti principali sono:

- obiettivi della RPC;
- realizzazione mediante messaggi;
- demoni e stub;
- esecuzione della chiamata;
- problemi di rappresentazione dei dati;
- semantica della chiamata;
- uso nei file system distribuiti;
- estensione a oggetti tramite RMI.

> 📌 La RPC permette di eseguire una procedura remota come se fosse una normale procedura locale.

---

### **2. Obiettivo della RPC**

La chiamata di procedura remota ha come obiettivo l'esecuzione di una procedura su una macchina remota rispetto a quella su cui si trova il processo chiamante.

La procedura remota viene usata perche' sulla macchina remota si trovano:

- risorse informative necessarie;
- risorse fisiche necessarie;
- software specifico;
- servizi non disponibili localmente.

Il resto del processo rimane sulla macchina originaria.

Solo l'elaborazione relativa alla procedura viene attivata in remoto.

> 💡 Con la RPC non si sposta tutto il processo: si sposta l'attivazione di una procedura.

---

### **3. Analogia con la chiamata locale**

Dal punto di vista del processo chiamante, la RPC deve apparire simile a:

- una chiamata di procedura interna al processo;
- una chiamata a una funzione di libreria;
- una chiamata a una funzione del sistema operativo.

Il processo chiamante e' l'entita' attiva: sta eseguendo la computazione e decide di attivare una procedura.

La procedura chiamata e' un'entita' passiva: viene eseguita solo quando qualcuno la invoca.

La differenza fondamentale e' che l'attivazione non avviene nello stesso spazio locale, ma tramite comunicazione tra macchine.

---

### **4. Realizzazione tramite messaggi**

La RPC viene realizzata usando comunicazione tra processi tramite scambio di messaggi strutturati.

Il processo chiamante invia un messaggio alla macchina remota.

Il messaggio contiene:

- identificativo della procedura;
- parametri;
- informazioni di controllo;
- eventuale identificativo della richiesta.

La macchina remota esegue la procedura e invia un messaggio di risposta con:

- risultato;
- stato dell'operazione;
- eventuali errori.

> 📌 La RPC nasconde i messaggi al programmatore, ma internamente viene realizzata proprio tramite messaggi.

---

### **5. Demone remoto**

Sulla macchina remota e' presente un processo di servizio, spesso chiamato **demone**.

Il demone:

- resta in attesa di richieste;
- ascolta su una porta specifica;
- riconosce la richiesta di attivazione;
- lancia la procedura richiesta;
- restituisce eventuali risultati al chiamante.

Il processo locale, quando vuole eseguire una procedura remota, chiede al proprio sistema operativo di connettersi al demone sulla macchina remota.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ Il demone e' il punto di ingresso della macchina remota per le richieste RPC.

---

### **6. Stub e corpo della procedura**

Ogni procedura remota e' associata a una interfaccia, spesso chiamata **stub**.

Lo stub definisce:

- nome della procedura;
- parametri richiesti;
- tipo del valore eventualmente restituito;
- modalita' di conversione dei parametri;
- struttura dei messaggi.

Il corpo della procedura contiene invece il codice effettivo da eseguire.

Quando il demone riceve una richiesta, usa lo stub per interpretarla e attiva il corpo della procedura.

Per ogni procedura remota puo' esistere una interfaccia specifica, cosi' da attivare individualmente le diverse procedure disponibili.

> 💡 Lo stub e' il terminale remotizzato della procedura: permette di invocare codice remoto con una forma simile alla chiamata locale.

---

### **7. Esecuzione di una RPC**

L'esecuzione di una chiamata di procedura remota puo' essere descritta in piu' passi.

#### **7.1. Richiesta del processo applicativo**

Il processo applicativo sulla macchina client richiede al kernel l'esecuzione remota di una procedura \(X\).

Dal punto di vista del programma, questa richiesta appare come una chiamata normale.

#### **7.2. Individuazione della procedura remota**

Il kernel client deve capire dove si trova la procedura desiderata e come contattarla.

Per questo puo' interrogare un componente di accoppiamento tra client e procedura remota.

Questo componente associa:

- procedura richiesta;
- macchina remota;
- porta su cui ascolta il demone responsabile.

#### **7.3. Risposta con la porta**

L'accoppiatore risponde al kernel client indicando la porta su cui inviare la richiesta.

A questo punto il kernel sa come raggiungere il demone corretto.

#### **7.4. Invio della richiesta**

Il kernel client costruisce il messaggio RPC e lo invia alla porta indicata sulla macchina remota.

Il demone remoto riceve il messaggio e attiva la procedura richiesta.

#### **7.5. Esecuzione remota**

La procedura viene eseguita sulla macchina remota, usando le risorse locali della macchina remota.

#### **7.6. Ritorno del risultato**

Se la procedura produce un risultato, questo viene inviato tramite un messaggio di risposta al kernel della macchina client.

Il kernel consegna infine il risultato al processo chiamante.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Vantaggi della RPC**

Il vantaggio principale della RPC e' la trasparenza.

L'accesso a funzioni remote viene presentato come accesso a funzioni locali.

La RPC nasconde:

- trasferimento dei parametri;
- costruzione dei messaggi;
- attivazione remota;
- ricezione dei risultati;
- dettagli della rete;
- interazione con il demone.

Questo semplifica la programmazione distribuita e permette di usare risorse remote senza gestire esplicitamente tutta la comunicazione.

---

### **9. Rappresentazione dei dati**

Un problema fondamentale e' la rappresentazione dei dati.

Macchine diverse possono usare convenzioni diverse per rappresentare:

- numeri interi;
- numeri reali;
- caratteri;
- stringhe;
- strutture;
- ordinamento dei byte.

Quando i parametri vengono inviati da una macchina all'altra, devono essere rappresentati in modo comprensibile da entrambe.

Per questo si usa spesso una **rappresentazione esterna dei dati**, cioe' uno standard comune.

Il processo e':

1. conversione dal formato locale al formato esterno;
2. invio sulla rete;
3. conversione dal formato esterno al formato locale della macchina remota.

> ⚠️ Senza una rappresentazione esterna comune, la procedura remota potrebbe interpretare in modo errato i parametri ricevuti.

---

### **10. Semantica della chiamata remota**

Un altro problema e' definire la semantica della chiamata.

In una chiamata locale, se la procedura viene invocata, viene eseguita nel processo.

In una chiamata remota, invece, possono verificarsi:

- perdita della richiesta;
- perdita della risposta;
- ritrasmissione;
- esecuzione duplicata;
- crash del client;
- crash del server.

Occorre quindi stabilire cosa significa "eseguire" una RPC.

---

### **11. Semantica al piu' una volta**

Con la semantica **al piu' una volta**, la procedura remota viene eseguita:

- zero volte;
- oppure una sola volta.

Non viene mai eseguita piu' di una volta.

Questa semantica richiede uno storico delle richieste.

Lo storico permette al server di capire se una richiesta e' gia' stata servita.

Se arriva una richiesta duplicata, il server evita di rieseguire la procedura.

> 📌 Con la semantica al piu' una volta, il chiamante deve eventualmente accorgersi del mancato completamento e ripetere la richiesta.

---

### **12. Semantica esattamente una volta**

Con la semantica **esattamente una volta**, si vuole garantire che la procedura venga eseguita una e una sola volta.

Questa semantica e' piu' forte.

Richiede:

- unicita' delle richieste;
- rilevazione dei duplicati;
- garanzia di esecuzione;
- gestione affidabile delle risposte;
- eventuale recupero in caso di errore.

In pratica, e' piu' complessa da realizzare, soprattutto in presenza di guasti.

> ⚠️ "Esattamente una volta" e' l'obiettivo piu' naturale per il programmatore, ma e' difficile da garantire in un ambiente distribuito reale.

---

### **13. RPC nei file system distribuiti**

Le RPC sono usate spesso nella realizzazione di file system distribuiti.

Quando un client vuole accedere a un file remoto:

1. invia una richiesta al demone del file server;
2. la richiesta arriva sulla porta associata al servizio;
3. il file server esegue l'operazione richiesta;
4. il risultato viene restituito al client.

Le operazioni possono essere:

- apertura remota;
- lettura remota;
- scrittura remota;
- chiusura remota;
- accesso a metadati.

Il file puo' rimanere sul server e le singole operazioni vengono eseguite remotamente.

In alternativa, in alcuni casi il server puo' inviare dati o porzioni del file al client.

> 💡 Un file system distribuito puo' essere visto come un insieme di operazioni locali trasformate in RPC verso il file server.

---

### **14. Remote Method Invocation**

La RPC nasce in un contesto di programmazione procedurale.

Nei linguaggi orientati agli oggetti, l'estensione naturale e' la **Remote Method Invocation** (**RMI**).

In RMI non si invoca una procedura remota, ma un **metodo** appartenente a un **oggetto remoto**.

Un oggetto remoto contiene:

- dati;
- risorse;
- metodi che definiscono le operazioni lecite su tali risorse.

L'invocazione del metodo remoto permette di accedere alle risorse dell'oggetto remoto tramite i suoi metodi.

---

### **15. RMI in ambienti a oggetti**

Un esempio tipico e' Java.

Un client puo' invocare un metodo di un oggetto che si trova:

- in un'altra macchina virtuale sulla stessa macchina;
- su un computer remoto connesso in rete.

Il client vede localmente uno stub, cioe' una interfaccia standard dell'oggetto remoto.

L'ambiente di esecuzione si occupa di:

- localizzare l'oggetto remoto;
- attivare il metodo;
- trasferire parametri;
- ricevere risultati;
- restituirli al client come se fossero prodotti da un oggetto locale.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **16. Differenza tra RPC e RMI**

La differenza principale e' il livello di astrazione.

Nella RPC:

- si invoca una procedura;
- la gestione della connessione e delle operazioni e' piu' vicina al programmatore;
- il paradigma e' procedurale.

Nella RMI:

- si invoca un metodo;
- la posizione dell'oggetto e' resa meno rilevante;
- l'ambiente di programmazione gestisce in modo piu' trasparente ricerca, attivazione e risultati;
- il paradigma e' orientato agli oggetti.

| Aspetto | RPC | RMI |
|---|---|---|
| Paradigma | Procedurale | Orientato agli oggetti |
| Entita' invocata | Procedura | Metodo di oggetto remoto |
| Astrazione | Minore | Maggiore |
| Gestione localizzazione | Piu' esplicita | Piu' trasparente |
| Esempio | Servizi remoti procedurali | Java RMI |

---

### **17. Sintesi**

| Aspetto | Descrizione |
|---|---|
| **Obiettivo** | Eseguire una procedura su una macchina remota |
| **Processo chiamante** | Entita' attiva che richiede l'esecuzione |
| **Procedura remota** | Entita' passiva attivata su richiesta |
| **Demone** | Processo remoto in ascolto delle richieste |
| **Stub** | Interfaccia remotizzata della procedura |
| **Accoppiatore** | Aiuta a individuare porta e demone della procedura |
| **Messaggi** | Trasportano richiesta, parametri e risultati |
| **Dati** | Richiedono rappresentazione esterna se le macchine sono eterogenee |
| **Semantica al piu' una volta** | Zero o una esecuzione, mai duplicata |
| **Semantica esattamente una volta** | Una sola esecuzione garantita |
| **RMI** | Estensione a oggetti della RPC |

---

### **18. Conclusione**

La RPC consente di attivare procedure remote mantenendo per il processo chiamante una visione simile alla chiamata locale.

La tecnica viene realizzata tramite messaggi, demoni, stub e meccanismi di individuazione della porta del servizio.

I problemi principali riguardano la rappresentazione dei dati e la semantica della chiamata in presenza di guasti.

Nei linguaggi orientati agli oggetti, la stessa idea viene elevata con la Remote Method Invocation, in cui il client invoca metodi di oggetti remoti con un livello maggiore di trasparenza.
