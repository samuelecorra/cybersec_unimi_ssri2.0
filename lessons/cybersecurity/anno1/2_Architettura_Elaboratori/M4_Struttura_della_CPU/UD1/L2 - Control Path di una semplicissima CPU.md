## ***Lezione 2: Control Path di una semplicissima CPU***

---

> 📌 Questa lezione rielabora integralmente le pagine 8–15 di `M4doc.pdf`, ricostruendo passo per passo il fetch comune e l'esecuzione di `LOAD`, `STORE`, `ADD` e `BRZ` nella CPU YM-0. La pagina 15 del PDF è priva di contenuto didattico.

### **1. Dal comando elementare alla sequenza di controllo**

Il **control path** è l'insieme delle sequenze temporizzate di segnali che fanno compiere al datapath un lavoro significativo. Ogni stato di controllo $S_i$ attiva contemporaneamente un gruppo compatibile di comandi; al passo successivo, il circuito passa a un nuovo stato e attiva un altro gruppo.

Una sequenza deve rispettare tre vincoli:

- il dato sorgente deve essere disponibile prima del campionamento;
- non devono verificarsi contese sui bus;
- memoria e registri devono disporre del tempo necessario per stabilizzare o acquisire i segnali.

Ogni istruzione segue il ciclo generale:

$$
\text{fetch}\longrightarrow\text{decode}\longrightarrow\text{execute}\longrightarrow\text{fetch successivo}.
$$

In YM-0 il fetch occupa gli stati da $S_0$ a $S_4$ ed è identico per tutte le istruzioni. Dopo la decodifica, l'opcode seleziona la specifica sequenza di esecuzione.

### **2. Fetch dell'istruzione**

#### **2.1. Stato $S_0$: trasferire il `PC` nel `MAR`**

Comandi:

```text
PCout1, ALUpass, MARin
```

Il `PC` pilota il primo bus interno, l'ALU lascia passare `A` e `MAR` campiona il risultato:

$$
MAR\leftarrow PC.
$$

#### **2.2. Stati $S_1$ e $S_2$: leggere la memoria**

Comandi:

```text
S1: MARoe, MEMR, ALUnop
S2: MARoe, MEMR, MDRsample, ALUnop
```

`MARoe` mantiene l'indirizzo sul bus esterno e `MEMR` richiede la lettura. L'operazione è conservata per due passi perché la memoria non risponde istantaneamente. In $S_2$, quando il dato è stabile, `MDRsample` lo acquisisce:

$$
MDR\leftarrow M[MAR].
$$

> 💡 La ripetizione di `MARoe` e `MEMR` non significa leggere due volte. Significa mantenere valida una singola transazione abbastanza a lungo da rispettare il tempo di risposta della memoria.

#### **2.3. Stato $S_3$: caricare l'istruzione**

Comandi:

```text
MDRout1, ALUpass, IRin
```

Il valore letto passa da `MDR` a `IR`:

$$
IR\leftarrow MDR.
$$

#### **2.4. Stato $S_4$: avanzare il `PC`**

Comandi:

```text
PCout1, ALUinc, PCin
```

Il nuovo valore è:

$$
PC\leftarrow PC+1.
$$

Il `PC` è contemporaneamente sorgente e destinazione. Per evitare che il valore appena aggiornato ricircoli nell'ALU causando più incrementi nello stesso passo, il registro deve essere non trasparente, per esempio realizzato con flip-flop edge-triggered o master-slave. Il vecchio valore resta disponibile durante il calcolo e il nuovo viene acquisito soltanto sul fronte previsto.

### **3. Decode**

Concluso $S_4$, `IR` contiene l'istruzione e `PC` punta già alla parola successiva. La CU esamina i due bit più significativi:

| `IR[15:14]` | Istruzione | Primo stato di esecuzione |
| --- | --- | --- |
| `00` | `LOAD` | $S_5$ della sequenza `LOAD` |
| `01` | `STORE` | $S_5$ della sequenza `STORE` |
| `10` | `ADD` | $S_5$ della sequenza `ADD` |
| `11` | `BRZ` | $S_5$ della sequenza `BRZ` |

Gli stessi nomi $S_i$ indicano posizioni temporali all'interno del cammino selezionato. La CU non esegue tutte le righe: sceglie il ramo corrispondente all'opcode.

### **4. Esecuzione di `LOAD`**

Obiettivo:

$$
R0\leftarrow M[IR[13:0]].
$$

| Stato | Comandi | Effetto |
| --- | --- | --- |
| $S_5$ | `IRout1, ALUpass, MARin` | $MAR\leftarrow IR[13:0]$ |
| $S_6$ | `MARoe, MEMR, ALUnop` | avvio lettura |
| $S_7$ | `MARoe, MEMR, MDRsample, ALUnop` | $MDR\leftarrow M[MAR]$ |
| $S_8$ | `MDRout1, ALUpass, R0in` | $R0\leftarrow MDR$ |

Dopo $S_8$ l'istruzione è conclusa e il controllo torna a $S_0$ per prelevare quella successiva.

### **5. Esecuzione di `STORE`**

Obiettivo:

$$
M[IR[13:0]]\leftarrow R0.
$$

| Stato | Comandi | Effetto |
| --- | --- | --- |
| $S_5$ | `IRout1, ALUpass, MARin` | $MAR\leftarrow IR[13:0]$ |
| $S_6$ | `R0out1, ALUpass, MDRin` | $MDR\leftarrow R0$ |
| $S_7$ | `MARoe, MEMW, MDRoe, ALUnop` | indirizzo e dato sono presentati alla memoria |
| $S_8$ | `MARoe, MEMW, MDRoe, ALUnop` | i segnali restano stabili per completare la scrittura |

In $S_7$ e $S_8$, `MAR` pilota il bus indirizzi, `MDR` il bus dati e `MEMW` ordina alla memoria di campionare. Anche qui la duplicazione serve a soddisfare i tempi del dispositivo.

> ⚠️ In lettura è la memoria a pilotare il bus dati e la CPU attiva `MDRsample`; in scrittura è `MDRoe` a far pilotare il bus dalla CPU. Scambiare i due ruoli causerebbe una transazione errata o una contesa elettrica.

### **6. Esecuzione di `ADD` immediata**

Quando il fetch termina, `PC` punta alla seconda parola dell'istruzione, che contiene l'operando immediato. Occorre leggerla, avanzare ancora il `PC` e sommarla a `R0`:

| Stato | Comandi | Effetto |
| --- | --- | --- |
| $S_5$ | `PCout1, ALUpass, MARin` | $MAR\leftarrow PC$ |
| $S_6$ | `MARoe, MEMR, ALUnop` | avvio lettura dell'immediato |
| $S_7$ | `MARoe, MEMR, MDRsample, ALUnop` | $MDR\leftarrow M[MAR]$ |
| $S_8$ | `PCout1, ALUinc, PCin` | $PC\leftarrow PC+1$ |
| $S_9$ | `MDRout1, R0out2, ALUadd, R0in, Zsample` | $R0\leftarrow MDR+R0$ e aggiornamento di `Z` |

In $S_9$ i due operandi devono essere disponibili contemporaneamente: `MDR` sul bus 1 e `R0` sul bus 2. L'ALU somma i valori, `R0in` acquisisce il risultato e `Zsample` memorizza l'indicazione di risultato nullo.

Esempio: se `R0` contiene `0xFFFD` e l'immediato è `0x0003`, sui 16 bit il risultato è `0x0000`; il riporto finale è scartato e `Z` diventa 1.

### **7. Esecuzione di `BRZ`**

`BRZ` è la sola istruzione condizionale. In $S_5$ la CU consulta il flag:

$$
\begin{cases}
IRout1,\ ALUpass,\ PCin, & Z=1,\\
\text{nessuna micro-operazione}, & Z=0.
\end{cases}
$$

Se `Z=1`:

$$
PC\leftarrow IR[13:0].
$$

Se `Z=0`, il `PC` conserva il valore già incrementato durante il fetch. In entrambi i casi il controllo torna poi a $S_0$.

> 💡 Un salto non preso non richiede di “saltare oltre” l'istruzione: il fetch ha già portato il `PC` alla parola seguente. Basta non sovrascriverlo.

### **8. Tabella riassuntiva dei cammini**

| Fase | Stati | Trasferimenti principali |
| --- | --- | --- |
| Fetch comune | $S_0$–$S_4$ | $MAR\leftarrow PC$; $MDR\leftarrow M[MAR]$; $IR\leftarrow MDR$; $PC\leftarrow PC+1$ |
| `LOAD` | $S_5$–$S_8$ | $MAR\leftarrow IR$; $MDR\leftarrow M[MAR]$; $R0\leftarrow MDR$ |
| `STORE` | $S_5$–$S_8$ | $MAR\leftarrow IR$; $MDR\leftarrow R0$; $M[MAR]\leftarrow MDR$ |
| `ADD` | $S_5$–$S_9$ | lettura seconda parola; $PC\leftarrow PC+1$; $R0\leftarrow R0+MDR$ |
| `BRZ` | $S_5$ | se `Z=1`, $PC\leftarrow IR[13:0]$ |

### **9. Dal diagramma temporale alla Control Unit**

Queste tabelle sono già una specifica completa del comportamento della CU: per ogni combinazione di fase, opcode e flag indicano quali uscite debbano valere 1. Restano due modi principali per trasformare la specifica in hardware:

- una CU **cablata**, che sintetizza direttamente funzioni booleane dei segnali di ingresso;
- una CU **microprogrammata**, che memorizza le righe di controllo come microistruzioni.

Le due soluzioni producono gli stessi comandi e devono quindi realizzare lo stesso control path; cambia il modo in cui la sequenza viene rappresentata e generata.

> ✅ Fetch, decode ed execute non sono descrizioni astratte: in YM-0 corrispondono a precise sequenze di abilitazioni, operazioni ALU e campionamenti. Il corretto ordinamento temporale è ciò che trasforma i singoli componenti in una CPU funzionante.
