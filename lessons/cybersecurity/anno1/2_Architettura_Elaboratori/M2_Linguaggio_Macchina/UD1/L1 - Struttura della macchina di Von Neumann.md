## ***Lezione 1: Struttura della macchina di Von Neumann***

---

> 📌 Questa lezione rielabora integralmente le pagine 1–8 di `M2doc.pdf`.

### **1. Dal programma alla macchina che lo esegue**

Nel 1945 John von Neumann formalizzò un modello di calcolatore nel quale **istruzioni e dati condividono la stessa memoria**. Il modello è composto da quattro blocchi funzionali:

| Blocco | Compito essenziale |
| --- | --- |
| **CPU** | interpreta ed esegue le istruzioni |
| **memoria di lavoro** | conserva temporaneamente programmi e dati |
| **interfacce di input/output** | collegano la rappresentazione interna al mondo esterno |
| **bus di sistema** | trasporta indirizzi, dati e segnali di controllo |

La CPU è il **master** del sistema: decide quale operazione avviare e coordina gli altri blocchi. Memoria e interfacce rispondono alle sue richieste come dispositivi **slave**. “Master” non significa che la CPU produca da sola dati e programmi: senza memoria e I/O non avrebbe né istruzioni da eseguire né un modo per ricevere input e restituire risultati.

> 💡 Il calcolatore è quindi un sistema cooperativo: la CPU dirige, la memoria conserva, le interfacce traducono e il bus mette in comunicazione tutti i componenti.

### **2. CPU e ciclo di esecuzione**

La **Central Processing Unit** è l’unità che esegue il programma. Il suo lavoro si ripete secondo il ciclo:

1. **fetch**: preleva dalla memoria l’istruzione indicata dal **Program Counter**;
2. **decode**: riconosce l’operazione e individua gli operandi;
3. **execute**: attiva i circuiti necessari, trasferisce o elabora i dati e registra il risultato.

Il **PC** identifica l’indirizzo della prossima istruzione da prelevare. Nel normale flusso sequenziale avanza, mentre un salto o una chiamata a sottoprogramma può caricarvi un indirizzo diverso.

Programmi, istruzioni e dati sono tutti rappresentati da sequenze di bit. È il **contesto d’uso** a stabilire se una parola binaria debba essere interpretata come istruzione, numero, carattere o indirizzo.

### **3. Velocità di esecuzione e IPS**

Una misura elementare delle prestazioni è il numero di **Instructions Per Second**:

$$
\mathrm{IPS}=\text{numero di processori o core}\cdot \text{frequenza di clock}\cdot \mathrm{IPC}
$$

dove IPC è il numero medio di istruzioni completate per ciclo. Il PDF propone, come stima puramente teorica per un processore con 24 core, frequenza di picco di circa $6\ \mathrm{GHz}$ e IPC assunto pari a 1:

$$
24\cdot 6\cdot 10^9\approx 144\cdot 10^9\ \mathrm{IPS}=144\ \mathrm{GIPS}.
$$

> ⚠️ Questa non è una prestazione reale garantita: frequenza, IPC, numero di core utilizzabili, tipo di istruzioni e accessi alla memoria variano durante l’esecuzione.

### **4. Memoria di lavoro**

La memoria centrale contiene il programma in esecuzione e i dati su cui esso opera. È organizzata come un insieme ordinato di **celle**, ciascuna identificata da un indirizzo e capace di contenere una parola di ampiezza fissata.

La memoria di lavoro è normalmente **volatile**: se manca l’alimentazione, il contenuto si perde. Questo la distingue dalle memorie permanenti usate per conservare programmi e dati nel tempo.

Per accedere a una cella la CPU deve specificare:

- l’**indirizzo** della cella;
- il **dato** da leggere o scrivere;
- l’**operazione** richiesta, per esempio lettura o scrittura.

Una lettura può essere descritta così: la CPU pone l’indirizzo sul bus, attiva il comando di lettura e la memoria restituisce la parola contenuta. In scrittura, la CPU presenta anche il dato e attiva il comando corrispondente.

### **5. Perché servono le interfacce di I/O**

Il mondo esterno non usa un unico tipo di segnale: una tastiera chiude contatti, uno schermo emette luce, un disco controlla fenomeni magnetici o elettrici, una rete usa segnali secondo uno specifico protocollo. La CPU, invece, opera con parole binarie e un insieme regolare di comandi.

L’**interfaccia di I/O** traduce fra questi due domini. Il PDF la paragona a **Giano bifronte**:

- verso CPU e bus espone un comportamento digitale standardizzato;
- verso la periferica gestisce segnali, tempi e protocolli specifici del dispositivo.

Non è quindi corretto dire che la periferica “non usa bit” in assoluto: molti dispositivi sono digitali. Il punto è che la CPU non deve conoscere direttamente ogni dettaglio fisico o protocollare della periferica.

### **6. Registri di una generica interfaccia**

Una tipica interfaccia contiene un’unità di controllo e alcuni registri visibili alla CPU:

| Registro | Direzione prevalente | Funzione |
| --- | --- | --- |
| **control** | CPU $\rightarrow$ interfaccia | seleziona operazioni e modalità |
| **status** | interfaccia $\rightarrow$ CPU | segnala pronto, occupato, errore o evento |
| **data out** | CPU $\rightarrow$ periferica | contiene il dato da inviare |
| **data in** | periferica $\rightarrow$ CPU | contiene il dato ricevuto |

L’unità di controllo dell’interfaccia sincronizza questi registri con la periferica. Dal punto di vista della CPU, accedere a un registro di I/O può assomigliare a un normale accesso a memoria: questa astrazione permette di usare meccanismi regolari anche per dispositivi fisicamente molto diversi.

> ✅ Nel modello di von Neumann istruzioni e dati sono parole in memoria; la CPU le elabora mediante fetch–decode–execute, mentre bus e interfacce rendono possibile il dialogo con memoria e mondo esterno.
