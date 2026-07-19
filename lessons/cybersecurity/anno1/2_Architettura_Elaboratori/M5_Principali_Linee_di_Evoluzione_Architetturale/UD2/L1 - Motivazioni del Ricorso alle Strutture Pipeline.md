## ***Lezione 1: Motivazioni del ricorso alle strutture pipeline***

---

> 📌 Questa lezione rielabora integralmente le pagine 33–38 di `M5doc.pdf`: fasi di esecuzione, sovrapposizione a catena di montaggio, throughput, latenza, stadi e struttura interna di una CPU pipeline.

### **1. Le fasi di un'istruzione**

Una CPU tradizionale completa ogni istruzione attraverso più fasi:

1. **Fetch** (`F`): preleva l'istruzione dalla memoria;
2. **Decode** (`D`): interpreta opcode e operandi;
3. **Execute** (`E`): svolge l'operazione logica, aritmetica o di accesso;
4. **Write Back** (`WB`): rende definitivo il risultato in un registro o nella destinazione prevista.

Le risorse coinvolte non coincidono completamente:

- il fetch usa `PC`, bus e memoria;
- il decode usa soprattutto `IR` e logica di decodifica;
- l'execute usa ALU, registri e talvolta memoria;
- il write back usa i registri destinazione o il percorso di scrittura.

Una CPU non pipeline attende che un'istruzione attraversi tutte le fasi prima di iniziare il fetch della successiva.

### **2. Il modello non pipeline**

Con quattro fasi di durata ideale $T$, la sequenza di due istruzioni è:

| Intervallo | Risorsa attiva | Istruzione |
| ---: | --- | --- |
| 1 | Fetch | $I_0$ |
| 2 | Decode | $I_0$ |
| 3 | Execute | $I_0$ |
| 4 | Write Back | $I_0$ |
| 5 | Fetch | $I_1$ |
| 6 | Decode | $I_1$ |
| 7 | Execute | $I_1$ |
| 8 | Write Back | $I_1$ |

Molte risorse restano inattive. Mentre l'ALU esegue $I_0$, il circuito di fetch potrebbe già lavorare su un'altra istruzione.

### **3. L'idea della pipeline**

Una **pipeline** divide la CPU in operatori, o **stadi**, capaci di funzionare in parallelo su istruzioni differenti. È l'equivalente di una catena di montaggio:

- ogni stadio svolge una sola fase;
- al termine dell'intervallo passa il proprio lavoro allo stadio successivo;
- contemporaneamente riceve una nuova istruzione dallo stadio precedente.

Con quattro stadi:

| Ciclo | Fetch | Decode | Execute | Write Back |
| ---: | --- | --- | --- | --- |
| 1 | $I_0$ | — | — | — |
| 2 | $I_1$ | $I_0$ | — | — |
| 3 | $I_2$ | $I_1$ | $I_0$ | — |
| 4 | $I_3$ | $I_2$ | $I_1$ | $I_0$ |
| 5 | $I_4$ | $I_3$ | $I_2$ | $I_1$ |

I primi cicli costituiscono il **transitorio di riempimento**. Dal ciclo 4 tutti gli operatori lavorano; da quel momento, nel caso ideale, si completa una istruzione per ciclo.

### **4. Unità di controllo degli stadi**

Il modello del PDF assegna a ogni fase una unità di controllo:

| Sigla | Unità | Compito principale |
| --- | --- | --- |
| `FCU` | Fetch Control Unit | prelievo e aggiornamento del `PC` |
| `DCU` | Decode Control Unit | decodifica e preparazione degli operandi |
| `ECU` | Execute Control Unit | esecuzione mediante ALU e risorse dedicate |
| `WBCU` | Write Back Control Unit | scrittura definitiva dei risultati |

Fra gli stadi servono registri pipeline che conservino istruzione, operandi e segnali di controllo al passaggio di ciclo. Il PDF distribuisce inoltre:

- `PC`, `MAR` e `MDR` nell'area di fetch;
- `IR` nella decodifica;
- ALU e stack pointer nell'esecuzione;
- GPR nel write back.

La divisione è concettuale: alcuni registri e bus devono essere raggiungibili da più stadi.

### **5. Throughput e latenza**

La pipeline migliora il **throughput**, non necessariamente la latenza della singola istruzione.

- **Latenza**: tempo da quando una istruzione entra nel fetch a quando termina il write back.
- **Throughput**: numero di istruzioni completate per unità di tempo.

Una singola istruzione continua ad attraversare quattro stadi e può impiegare lo stesso tempo, o leggermente di più per i registri intermedi. Il vantaggio nasce perché più istruzioni sono contemporaneamente in lavorazione.

> ⚠️ Dire “pipeline quattro volte più veloce” non significa che una singola `ADD` finisca in un quarto del tempo. Significa che, a regime e in condizioni ideali, il sistema può consegnare risultati con frequenza fino a quattro volte maggiore.

### **6. Instruction Completion Rate**

Il PDF usa l'**Instruction Completion Rate** (`ICR`), misurabile per esempio in MIPS. Con $NS$ stadi perfettamente bilanciati:

$$
ICR_{pipeline,ideale}=NS\cdot ICR_{normale}.
$$

Nel modello a quattro stadi:

$$
ICR_{pipeline,ideale}=4\,ICR_{normale}.
$$

Per $N$ istruzioni, trascurando ritardi aggiuntivi:

$$
T_{nonpipe}=N\cdot NS\cdot T,
$$

$$
T_{pipe}=(NS+N-1)T.
$$

Lo speedup è:

$$
S(N)=\frac{N\,NS}{NS+N-1},
$$

e tende a $NS$ soltanto per $N$ grande.

### **7. Perché il guadagno reale è inferiore**

Le fasi non hanno durata identica. Il periodo della pipeline è imposto dallo stadio più lento, più l'overhead dei registri intermedi:

$$
T_{clock}\ge\max(T_F,T_D,T_E,T_{WB})+T_{registro}.
$$

Inoltre, dipendenze e contese possono introdurre attese. Per questo:

$$
ICR_{pipeline}<NS\cdot ICR_{normale}.
$$

Il PDF riporta un intervallo storico indicativo di miglioramento fra circa 2 e 8:

| Organizzazione | Guadagno indicativo riportato |
| --- | --- |
| Pipeline a 4 stadi ben bilanciata | circa $3\times$–$4\times$ |
| Pipeline a 5–6 stadi | circa $4\times$–$6\times$ |
| Superpipeline con almeno 10 stadi | fino a circa $8\times$ su codice favorevole |

Non è una legge universale: il guadagno dipende da bilanciamento, programma, memoria e frequenza delle dipendenze.

### **8. Struttura e condivisione delle risorse**

Il diagramma del PDF quadripartisce la CPU in FCU, DCU, ECU e WBCU, ma evidenzia che i confini non sono assoluti. Fetch ed execute possono entrambi aver bisogno di bus e memoria; execute e write back possono dover usare percorsi comuni.

Per funzionare correttamente, il progetto deve quindi:

- separare o arbitrare le risorse condivise;
- sincronizzare il trasferimento tra stadi;
- impedire che un'istruzione usi dati non ancora prodotti;
- scartare il lavoro speculativo divenuto inutile;
- mantenere l'ordine architetturalmente richiesto dei risultati.

### **9. Miglioramento architetturale**

La pipeline non richiede necessariamente una tecnologia di transistor più veloce. Riorganizza il lavoro disponibile per aumentare l'utilizzo simultaneo delle parti della CPU. È quindi uno **sforzo di progetto architetturale**, analogo alla gerarchia di memoria: si ottengono prestazioni migliori modificando lo schema di funzionamento.

> ✅ Dopo il riempimento, una pipeline ideale completa una istruzione a ogni avanzamento. La latenza individuale resta simile, ma il throughput cresce grazie alla sovrapposizione delle fasi; sbilanciamento e conflitti rendono il guadagno reale inferiore al numero di stadi.
