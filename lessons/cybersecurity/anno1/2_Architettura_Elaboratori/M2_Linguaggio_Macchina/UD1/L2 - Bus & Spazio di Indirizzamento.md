## ***Lezione 2: Bus e spazio di indirizzamento***

---

> 📌 Questa lezione rielabora integralmente le pagine 9–12 di `M2doc.pdf`.

### **1. Il bus di sistema**

Il **bus di sistema** è il canale condiviso che collega CPU, memoria e interfacce di I/O. Non è un unico filo: è un insieme di linee raggruppate in tre sezioni con ruoli distinti.

| Sezione | Informazione trasportata | Direzione tipica |
| --- | --- | --- |
| **data bus** | parole e istruzioni | bidirezionale |
| **address bus** | indirizzo della cella o del registro selezionato | dalla CPU verso gli slave |
| **control bus** | comandi, temporizzazione e risposte | dipende dal segnale |

Durante una lettura, per esempio, la CPU seleziona una sorgente mediante l’address bus, richiede `READ` sul control bus e riceve la parola sul data bus.

### **2. Data bus e parallelismo**

Se il data bus ha ampiezza $d$, può trasportare contemporaneamente una parola di $d$ bit. In una macchina coerente, $d$ è spesso legato alla dimensione naturale della parola elaborata dalla CPU.

Un bus più largo può trasferire più bit per operazione, ma la banda effettiva dipende anche dalla frequenza, dal protocollo e dai tempi di attesa:

$$
\text{banda teorica}=d\cdot \text{trasferimenti al secondo}.
$$

Nel tempo sono state comuni ampiezze di 8, 16, 32, 64 e, in alcuni collegamenti o unità vettoriali, 128 bit. Aumentare $d$ non rende automaticamente più veloce ogni programma: conta che il resto dell’architettura possa sfruttare il parallelismo disponibile.

### **3. Address bus e cardinalità dello spazio**

Con $a$ linee di indirizzo sono disponibili $2^a$ configurazioni, quindi si possono distinguere fino a:

$$
N=2^a
$$

celle o unità indirizzabili. Questo insieme è lo **spazio di indirizzamento**.

Esempio: con 32 bit di indirizzo esistono $2^{32}=4\,294\,967\,296$ indirizzi. Se ogni indirizzo seleziona un byte, lo spazio vale $4\ \mathrm{GiB}$; se seleziona una parola, la quantità di byte è maggiore e dipende dalla parola. Per questo “32 bit = 4 GB” richiede sempre l’ipotesi di memoria **byte-addressable**.

### **4. Spazio indirizzabile e memoria installata**

Lo spazio di indirizzamento è una **capacità architetturale**, non la quantità di RAM realmente presente. Una macchina può avere, per esempio, uno spazio di $4\ \mathrm{GiB}$ ma soltanto $2\ \mathrm{GiB}$ di memoria fisica installata. Gli indirizzi restanti possono essere non implementati oppure destinati ad altri dispositivi.

Un address bus più ampio consente potenzialmente:

- più celle di memoria;
- programmi e insiemi di dati più grandi;
- più regioni assegnabili a RAM, ROM e I/O.

> ⚠️ L’ampiezza del data bus stabilisce quanti bit viaggiano insieme; quella dell’address bus stabilisce quante posizioni diverse possono essere selezionate. Sono proprietà indipendenti.

### **5. Control bus**

Le linee di controllo non costituiscono normalmente un numero binario unico, come accade per indirizzo e dato. Ogni linea ha una semantica propria. Fra i segnali tipici compaiono:

- `READ` e `WRITE`;
- `RESET`;
- `CLOCK` o segnali di sincronizzazione;
- richieste e riconoscimenti di interruzione;
- segnali di pronto, attesa o errore.

Alcune linee vanno dalla CPU agli slave, per esempio un comando di lettura; altre vanno dagli slave alla CPU, per esempio la segnalazione che un dato è disponibile. La complessità del control bus dipende dalle interazioni che l’architettura deve supportare.

> ✅ Address, data e control bus rispondono rispettivamente alle domande “dove?”, “che cosa?” e “con quale operazione e temporizzazione?”.
