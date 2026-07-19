## ***Lezione 1: Chip di memoria***

---

> 📌 Questa lezione rielabora integralmente le pagine 7–18 di `M3doc.pdf`, inclusi gli schemi della SRAM $4\times3$ e del chip di memoria generico.

### **1. Che cos’è un chip di memoria**

“Chip” non è un acronimo: significa letteralmente frammento o scheggia. In elettronica indica informalmente il piccolo pezzo di silicio, il **die**, sul quale è realizzato un circuito integrato. I chip di memoria sono i componenti fisici con cui si costruisce la memoria di lavoro.

La CPU richiede una memoria elettronica abbastanza rapida da sostenere ripetuti cicli di fetch e accessi ai dati. Nella memoria di lavoro devono poter cambiare sia le variabili sia il codice del programma in esecuzione; occorre pertanto una memoria a lettura e scrittura.

### **2. Significato di Random Access Memory**

In **RAM**, “random access” non significa che la CPU scelga indirizzi casuali. La CPU specifica esattamente la cella mediante il bus indirizzi. Il termine indica invece che il tempo di accesso a una cella non dipende dalla cella visitata prima: ogni posizione è direttamente raggiungibile con tempi sostanzialmente uniformi.

Le due famiglie introdotte nel PDF sono:

- **SRAM**, Static Random Access Memory;
- **DRAM**, Dynamic Random Access Memory.

### **3. Una SRAM didattica da quattro parole di tre bit**

Consideriamo un componente con $4$ parole, ognuna larga $3$ bit. La capacità complessiva è:

$$
4\cdot3=12\ \text{bit}.
$$

Ogni bit è conservato in un bistabile di tipo D; servono quindi dodici bistabili, disposti in quattro righe da tre. Per selezionare una riga occorrono due linee di indirizzo, perché:

$$
2^2=4.
$$

Le linee $A_1,A_0$ alimentano un decoder $2\rightarrow4$. Le quattro uscite corrispondono ai mintermini:

| $A_1A_0$ | Linea selezionata | Mintermine attivo |
| --- | ---: | --- |
| `00` | 0 | $\overline{A_1}\,\overline{A_0}$ |
| `01` | 1 | $\overline{A_1}A_0$ |
| `10` | 2 | $A_1\overline{A_0}$ |
| `11` | 3 | $A_1A_0$ |

Una e una sola uscita del decoder vale `1`. Essa individua la parola interessata.

### **4. Scrittura e lettura**

In scrittura, il segnale **Write Enable** (`WE`) abilita i tre bistabili D della sola riga selezionata. I bit presenti su `Di2`, `Di1`, `Di0` vengono campionati insieme, perciò l’intera parola è scritta in parallelo.

In lettura, per ogni colonna una porta OR raccoglie le uscite condizionate delle quattro righe. Poiché una sola riga è selezionata, su `D2`, `D1`, `D0` compare la parola contenuta nella cella indirizzata.

> ⚠️ Il decoder seleziona la parola; `WE` stabilisce se quella selezione debba modificare i bistabili. Un indirizzo valido, da solo, non deve provocare una scrittura.

### **5. Chip Select e bus dati bidirezionale**

Il segnale **Chip Select** (`CS`) abilita l’intero integrato:

- con `CS=0` il chip non legge, non scrive e non deve pilotare il bus;
- con `CS=1` il chip può eseguire l’operazione richiesta.

Senza un’ulteriore modifica, le porte OR di uscita continuerebbero a imporre un valore anche quando il chip non è selezionato. Collegando più memorie allo stesso bus si avrebbe contesa. Inoltre servirebbero pin distinti per dati in ingresso e in uscita.

Si inserisce perciò un buffer tri-state per ogni bit di uscita. Il buffer pilota il bus soltanto quando il chip è selezionato e l’operazione è una lettura:

$$
OE_{\text{interno}}=CS\land\overline{WE}.
$$

Negli altri casi l’uscita è in alta impedenza. Gli stessi fili diventano così un bus dati bidirezionale: durante la scrittura sono pilotati dalla CPU, durante la lettura dal chip. Per una parola da 8 bit, ciò riduce i pin dati da 16 separati a 8 condivisi, con vantaggi di area, costo e contenitore.

### **6. SRAM: proprietà, vantaggi e limiti**

La SRAM conserva il dato in un circuito bistabile finché è alimentata e non richiede refresh.

| Vantaggi | Limiti |
| --- | --- |
| accesso molto rapido | costo per bit elevato |
| dato stabile senza refresh | bassa densità |
| logica di accesso diretta | più transistor e più area per bit |
| tempi di accesso regolari | consumo e costo poco sostenibili su capacità di molti GB |

Per queste proprietà si usa soprattutto in cache L1/L2/L3 e buffer veloci. Il PDF cita anche i registri interni a CPU e GPU: il collegamento concettuale è la memorizzazione statica ad alta velocità, anche se un registro architetturale è normalmente realizzato con celle dedicate e non come un comune chip SRAM indirizzato.

### **7. DRAM e refresh dinamico**

In una DRAM ogni bit è rappresentato dalla carica di un condensatore controllato da un transistor: condensatore carico per `1`, scarico per `0`. La carica si disperde, quindi ogni cella deve essere periodicamente letta e riscritta. Questo **refresh dinamico** dà il nome alla tecnologia.

| Vantaggi DRAM | Limiti DRAM |
| --- | --- |
| altissima densità | accesso più lento della SRAM |
| basso costo per bit | refresh periodico obbligatorio |
| scalabilità a GB e TB | logica di controllo più complessa |
| buon consumo per bit su grandi capacità | maggiore sensibilità a disturbi e variazioni |

La DRAM è quindi adatta alla memoria principale, ai moduli DIMM e alle famiglie SDRAM/DDR; la SRAM è preferita nelle piccole memorie in cui domina la velocità.

### **8. Interfaccia di un chip RAM generico**

Un chip generico presenta:

| Segnale | Direzione | Funzione |
| --- | --- | --- |
| `MA` (*Memory Address*) | ingresso | seleziona una fra $2^{m_a}$ celle |
| `MD` (*Memory Data*) | bidirezionale | trasferisce una parola di $m_d$ bit |
| `CS` | ingresso | abilita il chip |
| `R/W` oppure `WE` | ingresso | determina il senso dell’operazione |
| `OE` opzionale | ingresso | abilita separatamente gli stadi di uscita |
| `Ready` opzionale | uscita | segnala che il trasferimento può completarsi |

Il numero di pin di indirizzo dipende dalla profondità, quello dei pin dati dalla larghezza della parola. Un chip $1\text{K}\times8$, per esempio, contiene $1024=2^{10}$ parole da 8 bit: richiede 10 pin di indirizzo e 8 pin dati.

### **9. Perché serve anche la ROM**

La RAM è volatile. All’accensione, tuttavia, il calcolatore deve già trovare almeno il programma iniziale di **bootstrap**. Inoltre un sistema embedded può eseguire stabilmente lo stesso firmware. Serve quindi una memoria non volatile leggibile senza che il contenuto debba essere caricato prima: la **ROM**, Read Only Memory.

> ✅ SRAM e DRAM risolvono con compromessi diversi il problema della memoria di lavoro; CS, indirizzo, dati e segnali di controllo forniscono l’interfaccia regolare necessaria a combinare più chip in un banco.
