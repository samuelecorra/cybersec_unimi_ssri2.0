## ***Lezione 2: Control Unit microprogrammata***

---

> 📌 Questa lezione rielabora integralmente le pagine 21–26 di `M4doc.pdf`: struttura della CU microprogrammata, formato delle microistruzioni, microsequenziamento e microprogramma completo di YM-0.

### **1. Il controllo come programma interno**

Una Control Unit **microprogrammata** non sintetizza ogni segnale mediante un'apposita equazione cablata. Memorizza in una ROM le configurazioni di segnali necessarie e le legge in sequenza.

Ogni parola della ROM è una **microistruzione**. Il termine va interpretato con precisione:

- è *micro* perché riguarda operazioni interne alla CPU, non istruzioni direttamente visibili al programmatore;
- è un'istruzione perché specifica comandi da eseguire e come scegliere la successiva;
- la sequenza completa è un **microprogramma** che implementa l'ISA della macchina.

La CU riceve ancora opcode e flag `Z` e produce gli stessi segnali del datapath. Cambia soltanto il meccanismo che li genera.

### **2. Componenti della CU microprogrammata**

La struttura comprende:

| Componente | Funzione |
| --- | --- |
| ROM di microprogramma | contiene le microistruzioni |
| Microprogram Counter, o micro-PC | indirizza la microistruzione corrente |
| Registro di microistruzione | conserva la parola letta mentre i segnali vengono applicati |
| Generatore del prossimo indirizzo | calcola il successivo microindirizzo usando campi di salto, opcode e flag |

In assenza di salti, il micro-PC avanza sequenzialmente:

$$
\mu PC\leftarrow\mu PC+1.
$$

Un microsalto può invece caricare un indirizzo esplicito. Il generatore del prossimo indirizzo svolge quindi un ruolo analogo, su scala interna, al circuito che aggiorna il `PC` del programma.

### **3. Formato concettuale della microistruzione**

La parola di microprogramma contiene due classi di campi:

| Campo | Significato |
| --- | --- |
| Microcomandi | bit che attivano `PCout1`, `MARin`, `MEMR`, operazioni ALU e tutte le altre uscite della CU |
| Controllo del flusso | tipo di salto e indirizzo di destinazione |

Nella forma più diretta, detta **microcodice orizzontale**, ogni microcomando dispone di un proprio bit. Più segnali possono così essere attivati in parallelo nella stessa riga, proprio come nelle tabelle del control path.

Il PDF usa questi comandi di salto:

| Comando | Condizione |
| --- | --- |
| `J` | salto incondizionato |
| `JNZ` | salto se il flag `Z` non è attivo, cioè `Z=0` |
| `J15` | salto se il bit 15 di `IR` vale 1 |
| `J14` | salto se il bit 14 di `IR` vale 1 |

Ogni comando di salto è accompagnato dall'indirizzo della microistruzione destinazione.

### **4. Microprogramma di fetch**

Le prime cinque righe implementano il fetch comune:

| Microindirizzo | Microcomandi | Controllo successivo |
| ---: | --- | --- |
| 0 | `PCout1, ALUpass, MARin` | sequenziale |
| 1 | `MARoe, MEMR, ALUnop` | sequenziale |
| 2 | `MARoe, MEMR, MDRsample, ALUnop` | sequenziale |
| 3 | `MDRout1, ALUpass, IRin` | sequenziale |
| 4 | `PCout1, ALUinc, PCin` | `J14 10` |

Alla riga 4, dopo aver incrementato `PC`, si esamina `IR[14]`:

- se vale 1, l'istruzione è `STORE` (`01`) oppure `BRZ` (`11`) e si salta alla riga 10;
- se vale 0, l'istruzione è `LOAD` (`00`) oppure `ADD` (`10`) e si prosegue alla riga 5.

### **5. Dispatch delle quattro istruzioni**

#### **5.1. Ramo `IR[14]=0`: `LOAD` o `ADD`**

La riga 5 esegue `J15 15`:

- `IR[15]=0` identifica `LOAD`, quindi si prosegue sequenzialmente alla riga 6;
- `IR[15]=1` identifica `ADD`, quindi si salta alla riga 15.

#### **5.2. Ramo `IR[14]=1`: `STORE` o `BRZ`**

La riga 10 esegue `J15 20`:

- `IR[15]=0` identifica `STORE`, quindi si prosegue alla riga 11;
- `IR[15]=1` identifica `BRZ`, quindi si salta alla riga 20.

Questa è una decodifica ad albero dei due bit di opcode. Non occorre un decoder one-hot esterno: sono i microsalti condizionati sui bit di `IR` a scegliere il corpo corretto.

### **6. Corpi del microprogramma**

#### **6.1. `LOAD`, righe 6–9**

| Indirizzo | Microcomandi | Prossimo indirizzo |
| ---: | --- | --- |
| 6 | `IRout1, ALUpass, MARin` | 7 |
| 7 | `MARoe, MEMR, ALUnop` | 8 |
| 8 | `MARoe, MEMR, MDRsample, ALUnop` | 9 |
| 9 | `MDRout1, ALUpass, R0in` | `J 0` |

La riga 9 conclude il caricamento e torna incondizionatamente all'inizio del fetch.

#### **6.2. `STORE`, righe 11–14**

| Indirizzo | Microcomandi | Prossimo indirizzo |
| ---: | --- | --- |
| 11 | `IRout1, ALUpass, MARin` | 12 |
| 12 | `R0out1, ALUpass, MDRin` | 13 |
| 13 | `MARoe, MEMW, MDRoe, ALUnop` | 14 |
| 14 | `MARoe, MEMW, MDRoe, ALUnop` | `J 0` |

#### **6.3. `ADD`, righe 15–19**

| Indirizzo | Microcomandi | Prossimo indirizzo |
| ---: | --- | --- |
| 15 | `PCout1, ALUpass, MARin` | 16 |
| 16 | `MARoe, MEMR, ALUnop` | 17 |
| 17 | `MARoe, MEMR, MDRsample, ALUnop` | 18 |
| 18 | `PCout1, ALUinc, PCin` | 19 |
| 19 | `MDRout1, R0out2, ALUadd, R0in, Zsample` | `J 0` |

#### **6.4. `BRZ`, righe 20–21**

| Indirizzo | Microcomandi | Prossimo indirizzo |
| ---: | --- | --- |
| 20 | nessun trasferimento | `JNZ 0` |
| 21 | `IRout1, ALUpass, PCin` | `J 0` |

Alla riga 20:

- se `Z=0`, `JNZ` torna direttamente al fetch e il salto macchina non viene eseguito;
- se `Z=1`, la condizione `JNZ` è falsa, si passa alla riga 21 e l'indirizzo contenuto in `IR` viene caricato nel `PC`.

> ⚠️ `JNZ` è un **microsalto** basato sul flag della CPU; non è una quinta istruzione dell'ISA di YM-0.

### **7. Traccia di esecuzione esemplificativa**

Per un'istruzione `ADD` (`IR[15:14]=10`), la successione dei microindirizzi è:

$$
0\to1\to2\to3\to4\to5\to15\to16\to17\to18\to19\to0.
$$

Alla riga 4, `IR[14]=0`, quindi non si salta a 10. Alla riga 5, `IR[15]=1`, quindi si salta a 15. Dopo il corpo di `ADD`, la riga 19 torna a 0.

Per un `BRZ` preso:

$$
0\to1\to2\to3\to4\to10\to20\to21\to0.
$$

Per un `BRZ` non preso:

$$
0\to1\to2\to3\to4\to10\to20\to0.
$$

Queste tracce mostrano che il microprogramma controlla sia le micro-operazioni sia il proprio flusso interno.

### **8. Vantaggi della microprogrammazione**

La struttura circuitale è regolare: all'aumentare della complessità dell'ISA cresce soprattutto la ROM, non una rete combinatoria irregolare. I principali vantaggi sono:

- maggiore facilità di progettazione per ISA complesse;
- descrizione vicina a un linguaggio di programmazione o micro-assembly;
- possibilità di usare strumenti di traduzione e verifica;
- più semplice modifica o estensione del microprogramma rispetto alla riprogettazione della rete cablata;
- particolare convenienza per macchine CISC con istruzioni lunghe e molti modi di indirizzamento.

Queste proprietà spiegarono la notevole diffusione delle CU microprogrammate, in particolare negli anni Ottanta.

### **9. Svantaggi e confronto con la CU cablata**

Ogni passo richiede di indirizzare e leggere una parola della ROM di controllo. Tale accesso si aggiunge al tempo di propagazione e rende in generale la soluzione più lenta di una rete cablata ottimizzata.

| Aspetto | CU cablata | CU microprogrammata |
| --- | --- | --- |
| Rappresentazione del controllo | equazioni e porte logiche | parole in ROM |
| Velocità | generalmente maggiore | penalizzata dalla lettura del microcodice |
| Modificabilità | bassa | maggiore |
| Regolarità del progetto | diminuisce con la complessità | elevata |
| Ambito storico favorevole | RISC e controlli semplici | CISC e controlli complessi |

Il progresso dell'integrazione ha reso nuovamente praticabili CU cablate molto complesse. La distinzione non va però letta come una regola assoluta: una CPU reale può combinare logica cablata, tabelle e microcodice.

> ✅ Il microprogramma di YM-0 contiene l'intero control path: fetch, decodifica ad albero, corpi delle quattro istruzioni e ritorno al fetch. La ROM sostituisce la sintesi di molte funzioni booleane con una sequenza esplicita di microistruzioni.
