# **M4 UD3 Lezione 1 - Distribuzione della computazione**

---

### **1. Introduzione**

Questa lezione introduce il problema della **computazione in un sistema distribuito**, cioe' in un sistema composto da piu' calcolatori connessi in rete.

L'obiettivo e' capire perche' e come distribuire la computazione tra piu' macchine.

La distribuzione della computazione permette di:

- aumentare la capacita' di elaborazione;
- ridurre la latenza dei processi;
- avvicinare la computazione alle risorse;
- migliorare la tolleranza ai guasti;
- sfruttare dati e periferiche distribuite.

> 📌 Distribuire la computazione significa decidere dove eseguire parti di un'applicazione all'interno dell'architettura distribuita.

---

### **2. Parallelismo e capacita' di elaborazione**

In un sistema distribuito sono disponibili piu' sistemi di elaborazione operanti in parallelo.

Questo permette di aumentare la capacita' di elaborazione nell'unita' di tempo.

Una computazione puo' essere suddivisa tra piu' calcolatori, ottenendo:

- maggiore velocita' complessiva;
- riduzione del tempo di risposta;
- diminuzione della latenza dei singoli processi;
- maggiore capacita' di rispondere rapidamente a richieste esterne.

Se piu' processori lavorano contemporaneamente su parti diverse del problema, il sistema puo' completare piu' lavoro nello stesso intervallo di tempo.

---

### **3. Costi e scalabilita'**

L'aumento della capacita' di elaborazione in un sistema distribuito puo' essere ottenuto a costi contenuti.

Invece di acquistare un sistema multiprocessore complesso e costoso, si possono aggiungere sistemi convenzionali e interconnetterli in rete.

Questo offre:

- costo globale inferiore;
- maggiore modularita';
- possibilita' di crescita progressiva;
- maggiore scalabilita' rispetto a sistemi multiprocessore tradizionali.

> 💡 Nei sistemi distribuiti si puo' aumentare la potenza aggiungendo nodi convenzionali, senza sostituire l'intera architettura.

---

### **4. Computazione vicino alle risorse**

La distribuzione della computazione e' utile anche per accedere in modo efficiente a risorse informative o fisiche allocate nel sistema distribuito.

Se una risorsa si trova su una macchina diversa da quella su cui si trova il processo, possono esserci due strategie:

- controllare la risorsa da remoto;
- spostare la computazione sulla macchina in cui si trova la risorsa.

Spesso la seconda soluzione e' piu' efficiente.

In alcuni casi diventa addirittura necessaria, per esempio quando una periferica deve essere controllata direttamente dalla macchina su cui e' installata tramite le funzioni del sistema operativo locale.

> ⚠️ Alcune risorse fisiche non possono essere controllate efficientemente da remoto: conviene spostare l'elaborazione vicino alla risorsa.

---

### **5. Computazione vicino ai dati**

Un'altra motivazione importante riguarda le grandi quantita' di dati.

Se i dati da elaborare sono molto grandi, trasferirli attraverso la rete puo' essere inefficiente.

In questi casi e' spesso piu' conveniente spostare il codice o il processo verso la macchina che contiene i dati.

Il principio e':

$$
\text{se i dati sono troppo grandi da spostare, sposta la computazione}
$$

Questo riduce:

- traffico di rete;
- tempi di trasferimento;
- sovraccarico sui canali di comunicazione;
- rischi legati alla protezione o riservatezza dei dati.

---

### **6. Tolleranza ai guasti**

La distribuzione della computazione e' utile anche per la **tolleranza ai guasti**.

Se una macchina si guasta, i processi in esecuzione su quella macchina non possono proseguire.

In un sistema distribuito con mobilita' delle applicazioni, il sistema puo':

1. rilevare il guasto;
2. individuare un'altra macchina disponibile;
3. riattivare o spostare la computazione;
4. proseguire l'esecuzione.

Anche un guasto nel sistema di interconnessione puo' essere gestito spostando la computazione vicino alle informazioni o alle risorse ancora raggiungibili.

> ✅ La mobilita' della computazione permette di continuare l'elaborazione anche quando una macchina o un collegamento non sono piu' disponibili.

---

### **7. Obiettivo generale**

L'obiettivo della distribuzione della computazione e':

> spostare la computazione sulla macchina che contiene le risorse piu' adatte per raggiungere l'obiettivo applicativo.

Le risorse adatte possono essere:

- processori meno carichi;
- dati locali;
- periferiche specifiche;
- software disponibile solo su un nodo;
- hardware specializzato;
- ambiente piu' affidabile.

La scelta del nodo di esecuzione deve quindi considerare sia prestazioni sia disponibilita' delle risorse.

---

### **8. Tecniche di distribuzione della computazione**

Esistono diverse tecniche per distribuire la computazione in un ambiente distribuito:

- chiamata di procedura remota;
- migrazione o allocazione dei processi;
- agenti mobili.

Queste tecniche differiscono per il livello di mobilita':

- con RPC si sposta l'attivazione di una procedura;
- con la migrazione dei processi si sposta un processo o parte della sua esecuzione;
- con gli agenti mobili si sposta un'entita' autonoma capace di decidere dove operare.

---

### **9. Chiamata di procedura remota**

La **Remote Procedure Call** permette a un processo di attivare una procedura su una macchina remota.

Il processo locale invoca la procedura come se fosse locale, ma l'esecuzione avviene su un altro nodo.

Questa tecnica e' utile quando:

- la procedura deve usare una risorsa remota;
- il software necessario e' disponibile su un'altra macchina;
- i dati si trovano in remoto;
- non conviene spostare grandi quantita' di dati.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Con la RPC non migra tutto il processo: viene attivata remotamente l'operazione richiesta.

---

### **10. Migrazione e allocazione dei processi**

Un'altra tecnica consiste nello spostare la computazione allocando il processo sulla macchina piu' adatta.

In questo caso si puo' fisicamente spostare il processo da una macchina a un'altra.

La migrazione puo' servire per:

- raggiungere una risorsa locale;
- bilanciare il carico;
- sfruttare un processore piu' libero;
- aggirare un guasto;
- eseguire il processo dove sono presenti i dati.

Questa tecnica richiede la gestione dello stato del processo e del suo ambiente di esecuzione.

> ⚠️ Spostare un processo ha un costo: conviene solo se il vantaggio ottenuto supera il tempo di migrazione.

---

### **11. Agenti mobili**

Gli **agenti mobili** sono entita' computazionali capaci di spostarsi autonomamente nell'ambiente distribuito.

Un agente mobile puo':

- decidere dove andare;
- cercare le risorse necessarie;
- spostarsi verso tali risorse;
- eseguire localmente la computazione;
- eventualmente tornare con un risultato.

Questa tecnica e' utile quando la computazione deve adattarsi dinamicamente alla posizione delle risorse.

> 💡 Gli agenti mobili rappresentano una forma piu' autonoma di distribuzione della computazione.

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

La sincronizzazione e' piu' complessa che in un singolo sistema, perche' non esiste necessariamente un'unica memoria condivisa o un orologio globale.

---

### **13. Sintesi**

| Aspetto | Descrizione |
|---|---|
| **Parallelismo** | Piu' calcolatori aumentano la capacita' di elaborazione |
| **Costi** | Si aggiungono sistemi convenzionali invece di grandi multiprocessori |
| **Scalabilita'** | Il sistema cresce aggiungendo nodi |
| **Risorse locali** | Conviene spostare computazione vicino a periferiche o servizi |
| **Dati grandi** | Conviene spostare computazione invece di trasferire grandi dataset |
| **Tolleranza ai guasti** | La computazione puo' riprendere su altri nodi |
| **RPC** | Attivazione remota di procedure |
| **Migrazione processi** | Spostamento del processo verso il nodo piu' adatto |
| **Agenti mobili** | Computazione autonoma che si sposta tra nodi |
| **Supporto** | Comunicazione e sincronizzazione distribuite |

---

### **14. Conclusione**

La distribuzione della computazione sfrutta il parallelismo di un ambiente composto da piu' calcolatori connessi in rete.

Permette di aumentare la velocita' di elaborazione, ridurre la latenza, usare in modo efficiente risorse fisiche e informative distribuite e migliorare la tolleranza ai guasti.

Le tecniche principali sono la chiamata di procedura remota, la migrazione o allocazione dei processi e gli agenti mobili.

Queste tecniche richiedono pero' adeguati meccanismi di comunicazione e sincronizzazione tra processi distribuiti.
