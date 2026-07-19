## ***Lezione 1: Data Path di una semplicissima CPU***

---

> 📌 Questa lezione rielabora integralmente le pagine 1–7 di `M4doc.pdf`: definisce la CPU didattica YM-0, la sua ISA, i registri, i bus interni, l'ALU e tutti i comandi elementari del datapath.

### **1. Dall'architettura visibile al programma alla struttura interna**

Nei moduli precedenti la CPU è stata osservata soprattutto dall'esterno: insieme delle istruzioni, registri accessibili al programmatore e scambi sul bus. Ora occorre aprire il blocco CPU e capire come i suoi componenti cooperino per realizzare concretamente ogni istruzione.

Il punto di partenza è il **datapath**, cioè l'insieme dei percorsi attraverso i quali scorrono dati, indirizzi e risultati all'interno del processore. Ne fanno parte:

- i registri;
- i bus interni;
- l'ALU;
- i collegamenti verso il bus dati e il bus indirizzi esterni.

Il datapath, da solo, non decide che cosa fare. Esegue operazioni elementari quando riceve i segnali della **Control Unit** (CU). La parte che genera nel giusto ordine tali segnali sarà studiata come **control path**.

### **2. La macchina didattica YM-0**

Per rendere visibile ogni passaggio, il PDF introduce una CPU deliberatamente minimale chiamata **YM-0**, versione zero di una macchina didattica. È una macchina di tipo RISC (*Reduced Instruction Set Computer*) estremamente semplice: possiede quattro sole istruzioni e un unico registro general purpose.

Le sue caratteristiche sono:

| Proprietà | Valore | Conseguenza |
| --- | --- | --- |
| Ampiezza del bus dati | 16 bit | ogni cella di memoria contiene una parola da 16 bit |
| Ampiezza del bus indirizzi | 14 bit | sono indirizzabili $2^{14}=16\,384=16\text{ K}$ celle |
| Registro general purpose | `R0`, 16 bit | è l'unico operando implicito delle istruzioni aritmetiche e di trasferimento |
| Registro di stato | `Z`, 1 bit | vale 1 se l'ultimo risultato prodotto dall'ALU è zero |
| Opcode | 2 bit | permette $2^2=4$ istruzioni |
| Modi di indirizzamento | immediato e diretto | l'operando è nell'istruzione oppure nella cella il cui indirizzo compare nell'istruzione |

> 💡 Una macchina così piccola non è pensata per essere utile in pratica. Serve a mostrare senza dettagli superflui come un'istruzione ISA diventi una sequenza di trasferimenti tra registri.

### **3. Formato e semantica delle quattro istruzioni**

#### **3.1. `LOAD` — opcode `00`**

`LOAD` usa l'indirizzamento diretto. I due bit più significativi contengono l'opcode e i restanti 14 bit l'indirizzo `ind`:

| 15–14 | 13–0 |
| --- | --- |
| `00` | `ind` |

La semantica è:

$$
R0 \leftarrow M[\text{ind}].
$$

L'istruzione occupa una sola parola: l'indirizzo della cella sorgente è già incorporato nei suoi 16 bit.

#### **3.2. `STORE` — opcode `01`**

Anche `STORE` usa l'indirizzamento diretto e occupa una parola:

| 15–14 | 13–0 |
| --- | --- |
| `01` | `ind` |

La semantica è:

$$
M[\text{ind}] \leftarrow R0.
$$

#### **3.3. `ADD` — opcode `10`**

`ADD` usa l'indirizzamento immediato, ma il valore da 16 bit non può stare nella stessa parola dell'opcode. L'istruzione occupa quindi **due parole consecutive**:

| Prima parola | Seconda parola |
| --- | --- |
| opcode `10` e bit restanti a 0 | valore immediato `val` su 16 bit |

La semantica è:

$$
R0 \leftarrow R0+\text{val}.
$$

Durante l'esecuzione la CPU deve leggere la seconda parola e incrementare nuovamente il `PC`. La somma è effettuata sui 16 bit disponibili; un eventuale riporto oltre il bit più significativo non può essere conservato, perché YM-0 non possiede un flag di carry.

#### **3.4. `BRZ` — opcode `11`**

`BRZ`, *branch if zero*, contiene direttamente l'indirizzo di destinazione:

| 15–14 | 13–0 |
| --- | --- |
| `11` | `ind` |

La sua semantica è condizionale:

$$
PC \leftarrow
\begin{cases}
\text{ind}, & Z=1,\\
PC, & Z=0.
\end{cases}
$$

Il salto usa dunque il risultato memorizzato nel flag `Z` dall'ultima operazione aritmetica pertinente.

> ⚠️ L'opcode indica l'operazione, non la lunghezza: `LOAD`, `STORE` e `BRZ` occupano una parola, mentre `ADD` ne occupa due.

### **4. I registri interni**

La CPU contiene registri con ruoli e larghezze differenti:

| Registro | Bit | Funzione |
| --- | ---: | --- |
| `R0` | 16 | unico registro general purpose |
| `PC` | 14 | indirizzo della prossima parola di istruzione |
| `IR` | 16 | istruzione corrente: opcode nei bit 15–14, campo indirizzo nei bit 13–0 |
| `MAR` | 14 | indirizzo da presentare alla memoria |
| `MDR` | 16 | dato in transito tra CPU e memoria |
| `Z` | 1 | condition code: ultimo risultato nullo |

Un registro a $n$ bit è formato da $n$ elementi di memoria, per esempio flip-flop D. I suoi ingressi e le sue uscite sono governati da segnali di controllo:

- `Rin` abilita il campionamento dell'**Input Internal Bus**;
- `Rout1` abilita l'uscita sul primo bus interno;
- `Rout2` abilita l'uscita sul secondo bus interno.

Quando un'uscita non è abilitata, il relativo driver si trova in alta impedenza `Z`. Ciò permette a più registri di essere collegati allo stesso bus, purché la CU ne abiliti uno solo alla volta.

> ⚠️ Il simbolo `Z` ha due significati distinti, riconoscibili dal contesto: il registro `Z` è il flag di risultato nullo; lo stato elettrico `Z` è l'alta impedenza di un'uscita tri-state.

### **5. I tre bus interni e l'ALU**

YM-0 impiega tre bus interni:

1. **Output Internal Bus 1**, che trasporta il primo operando;
2. **Output Internal Bus 2**, che trasporta il secondo operando;
3. **Input Internal Bus**, che distribuisce il risultato verso il registro destinazione.

I primi due alimentano gli ingressi `A` e `B` dell'ALU. L'uscita `O` dell'ALU alimenta l'Input Internal Bus. Un trasferimento non è quindi un collegamento diretto tra due registri: il dato esce da un registro, attraversa un bus e l'ALU, quindi entra nel registro destinazione.

La piccola ALU usa un comando `C` da 2 bit:

| `C` | Operazione | Relazione |
| --- | --- | --- |
| `00` | `NOP` | nessuna operazione utile |
| `01` | `PASS` | $O=A$ |
| `10` | `INC` | $O=A+1$ |
| `11` | `ADD` | $O=A+B$ |

Con `ADD`, l'ALU produce anche il segnale `F`, che vale 1 quando il risultato `O` è zero. Se nello stesso passo viene attivato `Zsample`, quel segnale viene memorizzato nel registro `Z`.

Per esempio, il trasferimento concettuale

$$
R0 \leftarrow PC
$$

richiederebbe l'abilitazione di `PCout1`, la selezione `ALUpass` e il campionamento `R0in`, supponendo larghezze compatibili. La CU deve mantenere coerenti tutti e tre i segnali nello stesso intervallo temporale.

### **6. Il ruolo speciale di `MAR` e `MDR`**

`MAR` e `MDR` collegano il datapath ai bus esterni.

#### **6.1. `MAR`**

`MAR` riceve un indirizzo dal bus interno tramite `MARin`. Con `MARoe` (*output enable*) lo presenta sul bus indirizzi esterno. Separare il registro dal bus consente di mantenere stabile l'indirizzo durante l'intero accesso in memoria.

#### **6.2. `MDR`**

`MDR` deve comunicare in entrambe le direzioni e dispone perciò di quattro comandi:

| Comando | Effetto |
| --- | --- |
| `MDRin` | campiona il valore proveniente dall'Input Internal Bus |
| `MDRout1` | invia il contenuto sul primo bus interno |
| `MDRsample` | campiona il bus dati esterno |
| `MDRoe` | pilota il bus dati esterno con il contenuto di `MDR` |

In una lettura, la memoria pilota il bus dati e `MDRsample` acquisisce il valore. In una scrittura, `MDRoe` fa l'opposto: è la CPU a pilotare il bus dati.

### **7. Inventario dei comandi del datapath**

Il control path può usare i seguenti segnali elementari:

| Destinazione | Comandi |
| --- | --- |
| Condition code | `Zsample` |
| `R0` | `R0in`, `R0out1`, `R0out2` |
| `PC` | `PCin`, `PCout1` |
| `IR` | `IRin`, `IRout1` |
| `MAR` | `MARin`, `MARoe` |
| `MDR` | `MDRin`, `MDRout1`, `MDRsample`, `MDRoe` |
| ALU | `ALUnop`, `ALUpass`, `ALUinc`, `ALUadd` |
| Memoria | `MEMR`, `MEMW` |

`MEMR` chiede alla memoria una lettura; `MEMW` chiede una scrittura. Sono segnali del bus di controllo esterno, mentre gli altri governano componenti interni alla CPU.

> 📌 Il datapath definisce **quali trasferimenti elementari sono fisicamente possibili**. La CU deve combinarli in sequenze temporalmente corrette per realizzare fetch ed esecuzione delle istruzioni.

### **8. Errori concettuali da evitare**

- Il `PC` non contiene l'istruzione, ma l'indirizzo della prossima parola da prelevare.
- `IR` conserva l'istruzione mentre `MAR` conserva l'indirizzo usato nell'accesso corrente.
- `MDR` è un registro di transito: non sostituisce la memoria né `R0`.
- Un segnale `out` non copia da solo il dato nel destinatario: servono anche l'operazione dell'ALU e il corrispondente segnale `in`.
- Due registri non devono pilotare simultaneamente lo stesso bus interno, salvo che la progettazione lo preveda esplicitamente.
- Il flag `Z` non si aggiorna continuamente: cambia soltanto quando la CU attiva `Zsample`.

> ✅ YM-0 riduce la CPU a ciò che è essenziale: una ISA di quattro istruzioni, registri specializzati, tre bus interni, una piccola ALU e segnali elementari. Questa base permette di descrivere nel dettaglio il control path della prossima lezione.
