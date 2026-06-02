# **M4 UD3 Lezione 1 - Distribuzione della computazione**

---

### **1. Introduzione**

Questa lezione introduce il problema della **computazione in un sistema distribuito**, cioè in un sistema composto da più calcolatori connessi in rete.

L'obiettivo è capire perché e come distribuire la computazione tra più macchine.

La distribuzione della computazione permette di:

- aumentare la capacità di elaborazione;
- ridurre la latenza dei processi;
- avvicinare la computazione alle risorse;
- migliorare la tolleranza ai guasti;
- sfruttare dati e periferiche distribuite.

> 📌 Distribuire la computazione significa decidere dove eseguire parti di un'applicazione all'interno dell'architettura distribuita.

---

### **2. Parallelismo e capacità di elaborazione**

In un sistema distribuito sono disponibili più sistemi di elaborazione operanti in parallelo.

Questo permette di aumentare la capacità di elaborazione nell'unità di tempo.

Una computazione può essere suddivisa tra più calcolatori, ottenendo:

- maggiore velocità complessiva;
- riduzione del tempo di risposta;
- diminuzione della latenza dei singoli processi;
- maggiore capacità di rispondere rapidamente a richieste esterne.

Se più processori lavorano contemporaneamente su parti diverse del problema, il sistema può completare più lavoro nello stesso intervallo di tempo.

Un esempio reale è una **render farm** usata per produrre film animati o effetti visivi.

Ogni fotogramma, oppure ogni porzione di una scena, può essere renderizzato da un nodo diverso. Il risultato finale viene poi ricomposto, riducendo drasticamente il tempo necessario rispetto all'esecuzione su una sola macchina.

> 💡 Il parallelismo distribuito è efficace quando il lavoro può essere diviso in parti abbastanza indipendenti, come fotogrammi, blocchi di dati o richieste separate.

---

### **3. Costi e scalabilità**

L'aumento della capacità di elaborazione in un sistema distribuito può essere ottenuto a costi contenuti.

Invece di acquistare un sistema multiprocessore complesso e costoso, si possono aggiungere sistemi convenzionali e interconnetterli in rete.

Questo offre:

- costo globale inferiore;
- maggiore modularità;
- possibilità di crescita progressiva;
- maggiore scalabilità rispetto a sistemi multiprocessore tradizionali.

> 💡 Nei sistemi distribuiti si può aumentare la potenza aggiungendo nodi convenzionali, senza sostituire l'intera architettura.

Un esempio reale è un servizio web eseguito su un cluster cloud con più server applicativi.

Quando aumentano gli utenti, il gestore può aggiungere nuove istanze al cluster; quando il traffico diminuisce, può ridurle. In questo modo la capacità cresce in modo progressivo, senza dover acquistare fin dall'inizio un unico sistema molto grande e sovradimensionato.

---

### **4. Computazione vicino alle risorse**

La distribuzione della computazione è utile anche per accedere in modo efficiente a risorse informative o fisiche allocate nel sistema distribuito.

Se una risorsa si trova su una macchina diversa da quella su cui si trova il processo, possono esserci due strategie:

- controllare la risorsa da remoto;
- spostare la computazione sulla macchina in cui si trova la risorsa.

Spesso la seconda soluzione è più efficiente.

In alcuni casi diventa addirittura necessaria, per esempio quando una periferica deve essere controllata direttamente dalla macchina su cui è installata tramite le funzioni del sistema operativo locale.

> ⚠️ Alcune risorse fisiche non possono essere controllate efficientemente da remoto: conviene spostare l'elaborazione vicino alla risorsa.

Un esempio reale è un sistema industriale con sensori e attuatori controllati da un computer locale di fabbrica.

Se un'applicazione deve reagire in pochi millisecondi alla lettura di un sensore o comandare direttamente un braccio robotico, è più sicuro ed efficiente eseguire la logica di controllo sul nodo vicino alla macchina fisica, invece di inviare ogni misura a un server remoto e attendere la risposta.

---

### **5. Computazione vicino ai dati**

Un'altra motivazione importante riguarda le grandi quantità di dati.

Se i dati da elaborare sono molto grandi, trasferirli attraverso la rete può essere inefficiente.

In questi casi è spesso più conveniente spostare il codice o il processo verso la macchina che contiene i dati.

Il principio è:

$$
\text{se i dati sono troppo grandi da spostare, sposta la computazione}
$$

Questo riduce:

- traffico di rete;
- tempi di trasferimento;
- sovraccarico sui canali di comunicazione;
- rischi legati alla protezione o riservatezza dei dati.

N.B.: Dovrebbe subito accendersi una lampadina: i moderni Large Language Models (LLM) sono un esempio affine a questo principio. In senso stretto, non si sposta la computazione verso il prompt dell'utente, che è piccolo; si sposta invece la richiesta verso il nodo che contiene lo stato enorme del modello, cioè i suoi parametri. È più facile mandare il prompt di ChatGPT a un data center di OpenAI che scaricare tutto il modello sul proprio computer, anche perché tutti quei miliardi di parametri servono solo per produrre una risposta a una domanda specifica.

---

### **6. Tolleranza ai guasti**

La distribuzione della computazione è utile anche per la **tolleranza ai guasti**.

Se una macchina si guasta, i processi in esecuzione su quella macchina non possono proseguire.

In un sistema distribuito con mobilità delle applicazioni, il sistema può:

1. rilevare il guasto;
2. individuare un'altra macchina disponibile;
3. riattivare o spostare la computazione;
4. proseguire l'esecuzione.

Anche un guasto nel sistema di interconnessione può essere gestito spostando la computazione vicino alle informazioni o alle risorse ancora raggiungibili.

> ✅ La mobilità della computazione permette di continuare l'elaborazione anche quando una macchina o un collegamento non sono più disponibili.

Un esempio reale è un cluster Kubernetes che esegue servizi applicativi replicati.

Se un nodo fisico si guasta, i container che erano in esecuzione su quel nodo possono essere riavviati su altre macchine disponibili. L'utente può percepire al massimo un rallentamento o una breve interruzione, ma il servizio non dipende più dalla sopravvivenza di una singola macchina.

---

### **7. Obiettivo generale**

L'obiettivo della distribuzione della computazione è:

> spostare la computazione sulla macchina che contiene le risorse più adatte per raggiungere l'obiettivo applicativo.

Le risorse adatte possono essere:

- processori meno carichi;
- dati locali;
- periferiche specifiche;
- software disponibile solo su un nodo;
- hardware specializzato;
- ambiente più affidabile.

La scelta del nodo di esecuzione deve quindi considerare sia prestazioni sia disponibilità delle risorse.

---

### **8. Tecniche di distribuzione della computazione**

Esistono diverse tecniche per distribuire la computazione in un ambiente distribuito:

- chiamata di procedura remota;
- migrazione o allocazione dei processi;
- agenti mobili.

Queste tecniche differiscono per il livello di mobilità:

- con RPC si sposta l'attivazione di una procedura;
- con la migrazione dei processi si sposta un processo o parte della sua esecuzione;
- con gli agenti mobili si sposta un'entità autonoma capace di decidere dove operare.

---

### **9. Chiamata di procedura remota**

La **Remote Procedure Call** permette a un processo di attivare una procedura su una macchina remota.

Il processo locale invoca la procedura come se fosse locale, ma l'esecuzione avviene su un altro nodo.

Questa tecnica è utile quando:

- la procedura deve usare una risorsa remota;
- il software necessario è disponibile su un'altra macchina;
- i dati si trovano in remoto;
- non conviene spostare grandi quantità di dati.

![](imgs/Pasted%20image%2020260602034933.png)

> 📌 Con la RPC non migra tutto il processo: viene attivata remotamente l'operazione richiesta.

---

### **10. Migrazione e allocazione dei processi**

Un'altra tecnica consiste nello spostare la computazione allocando il processo sulla macchina più adatta.

In questo caso si può fisicamente spostare il processo da una macchina a un'altra.

La migrazione può servire per:

- raggiungere una risorsa locale;
- bilanciare il carico;
- sfruttare un processore più libero;
- aggirare un guasto;
- eseguire il processo dove sono presenti i dati.

Questa tecnica richiede la gestione dello stato del processo e del suo ambiente di esecuzione.

> ⚠️ Spostare un processo ha un costo: conviene solo se il vantaggio ottenuto supera il tempo di migrazione.

---

### **11. Agenti mobili**

Gli **agenti mobili** sono entità computazionali capaci di spostarsi autonomamente nell'ambiente distribuito.

Un agente mobile può:

- decidere dove andare;
- cercare le risorse necessarie;
- spostarsi verso tali risorse;
- eseguire localmente la computazione;
- eventualmente tornare con un risultato.

Questa tecnica è utile quando la computazione deve adattarsi dinamicamente alla posizione delle risorse.

> 💡 Gli agenti mobili rappresentano una forma più autonoma di distribuzione della computazione.

---

### **12. Tecniche di supporto**

Oltre alle tecniche di distribuzione, servono meccanismi di supporto.

In particolare, un sistema distribuito deve gestire:

- comunicazione tra processi distribuiti;
- sincronizzazione tra processi distribuiti;
- cooperazione tra processi;
- concorrenza nell'accesso alle risorse;
- coordinamento dell'evoluzione delle computazioni.

#### **12.1. Comunicazione**

I processi distribuiti devono scambiarsi informazioni tramite:

- messaggi;
- chiamate remote;
- protocolli di rete;
- canali di comunicazione.

#### **12.2. Sincronizzazione**

I processi distribuiti possono dover:

- concorrere per l'uso di una risorsa;
- attendere eventi prodotti da altri processi;
- coordinare fasi diverse della computazione;
- mantenere consistenza tra dati distribuiti.

La sincronizzazione è più complessa che in un singolo sistema, perché non esiste necessariamente un'unica memoria condivisa o un orologio globale.

---

### **13. Sintesi**

| Aspetto                  | Descrizione                                                          |
| ------------------------ | -------------------------------------------------------------------- |
| **Parallelismo**         | Più calcolatori aumentano la capacità di elaborazione                |
| **Costi**                | Si aggiungono sistemi convenzionali invece di grandi multiprocessori |
| **Scalabilità**          | Il sistema cresce aggiungendo nodi                                   |
| **Risorse locali**       | Conviene spostare computazione vicino a periferiche o servizi        |
| **Dati grandi**          | Conviene spostare computazione invece di trasferire grandi dataset   |
| **Tolleranza ai guasti** | La computazione può riprendere su altri nodi                         |
| **RPC**                  | Attivazione remota di procedure                                      |
| **Migrazione processi**  | Spostamento del processo verso il nodo più adatto                    |
| **Agenti mobili**        | Computazione autonoma che si sposta tra nodi                         |
| **Supporto**             | Comunicazione e sincronizzazione distribuite                         |

---

### **14. Conclusione**

La distribuzione della computazione sfrutta il parallelismo di un ambiente composto da più calcolatori connessi in rete.

Permette di aumentare la velocità di elaborazione, ridurre la latenza, usare in modo efficiente risorse fisiche e informative distribuite e migliorare la tolleranza ai guasti.

Le tecniche principali sono la chiamata di procedura remota, la migrazione o allocazione dei processi e gli agenti mobili.

Queste tecniche richiedono però adeguati meccanismi di comunicazione e sincronizzazione tra processi distribuiti.
