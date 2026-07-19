## ***Lezione 1: Control Unit cablata***

---

> 📌 Questa lezione rielabora integralmente le pagine 16–20 di `M4doc.pdf`: ingressi e uscite della Control Unit, contatore e decodificatori di stato, sintesi booleana dei comandi, vantaggi e limiti della realizzazione cablata.

### **1. Che cosa deve fare la Control Unit**

La **Control Unit** riceve informazioni sullo stato corrente della CPU e produce tutti i segnali che governano datapath e bus esterni. Nel caso di YM-0 gli ingressi logici rilevanti sono:

- l'opcode in `IR`, decodificato nei segnali one-hot `L`, `S`, `A`, `B` per `LOAD`, `STORE`, `ADD`, `BRZ`;
- il condition code `Z`;
- lo stato temporale corrente $S_0,S_1,\ldots,S_9$.

Le uscite sono i comandi già incontrati: abilitazioni `in` e `out` dei registri, selezione dell'operazione ALU, campionamento del flag, `MEMR` e `MEMW`.

Una CU **cablata**, o *hardwired*, realizza ciascuna uscita come una funzione booleana diretta degli ingressi. Non legge una sequenza da una memoria: la sequenza è incorporata nella rete di porte logiche.

### **2. Decodifica dell'opcode**

I due bit `IR[15:14]` entrano in un decoder $2\rightarrow4$, che rende attiva una sola linea:

| `IR[15:14]` | `L` | `S` | `A` | `B` |
| --- | ---: | ---: | ---: | ---: |
| `00` | 1 | 0 | 0 | 0 |
| `01` | 0 | 1 | 0 | 0 |
| `10` | 0 | 0 | 1 | 0 |
| `11` | 0 | 0 | 0 | 1 |

Essendo one-hot, vale sempre:

$$
L+S+A+B=1,
$$

dove `+` indica l'OR booleano. I prodotti, come $AZ$, indicano invece un AND.

### **3. Generazione degli stati temporali**

Un contatore sincronizzato dal clock rappresenta il passo corrente. Le sue uscite binarie vengono decodificate in linee one-hot $S_0,\ldots,S_9$. Una sola linea di stato è attiva in ogni intervallo.

Il contatore non deve necessariamente procedere sempre fino a 9:

- al termine di ogni istruzione viene riportato a $S_0$;
- dopo il fetch, la logica usa l'opcode per scegliere il ramo di esecuzione;
- `BRZ` considera anche `Z`.

La combinazione tra linea di stato e istruzione attiva identifica in modo univoco la riga della tabella di controllo da realizzare.

### **4. Dalla tabella temporale alle equazioni booleane**

Per sintetizzare un'uscita, si raccolgono tutte le condizioni nelle quali quel comando deve essere attivo e se ne calcola l'OR. Il PDF mostra diverse equazioni esemplificative.

#### **4.1. Uscita del `PC`**

`PCout1` serve sempre in $S_0$ e $S_4$, e inoltre in $S_5$ e $S_8$ soltanto durante `ADD`:

$$
PCout1=S_0+S_4+(S_5+S_8)A.
$$

L'espressione rende visibile la condivisione del fetch: i primi due termini non dipendono dall'opcode.

#### **4.2. Abilitazione esterna del `MAR`**

Il PDF ricava:

$$
MARoe=S_1+S_2+S_6(L+A)+S_7(L+S+A)+S_8S.
$$

Interpretazione:

- in $S_1$ e $S_2$ tutte le istruzioni leggono la parola corrente;
- in $S_6$ `LOAD` e `ADD` avviano una lettura;
- in $S_7$ `LOAD`, `STORE` e `ADD` mantengono valido l'indirizzo;
- in $S_8$ soltanto `STORE` continua la scrittura.

#### **4.3. Uscita del registro istruzione**

L'indirizzo presente in `IR` viene inviato al datapath nello stato $S_5$ per `LOAD`, `STORE` e per `BRZ` solo quando il salto è preso:

$$
IRout1=S_5(L+S+BZ).
$$

Il prodotto $BZ$ evita di caricare il `PC` quando `Z=0`.

#### **4.4. Controllo dell'ALU**

Poiché il comando ALU è composto da due bit, occorrono due funzioni distinte. Indicando con $C_1$ il bit più significativo e con $C_0$ quello meno significativo, il PDF fornisce:

$$
C_1=S_4+(S_8+S_9)A,
$$

$$
C_0=S_0+S_3+S_5(L+S+A+BZ)+S_6S+S_8L+S_9A.
$$

La coppia $(C_1,C_0)$ seleziona `NOP`, `PASS`, `INC` o `ADD`. Per esempio, nello stato $S_9$ di `ADD` entrambi i bit valgono 1 e viene scelta la somma.

#### **4.5. Comando di lettura della memoria**

`MEMR` è attivo durante il fetch e durante la lettura dell'operando per `LOAD` e `ADD`:

$$
MEMR=S_1+S_2+(S_6+S_7)(L+A).
$$

La stessa procedura consente di ricavare ogni altra uscita, anche quando l'equazione non è esplicitata nel PDF.

> 💡 Sintetizzare una CU cablata equivale a trasformare una tabella di micro-operazioni in un insieme di funzioni booleane minimizzate.

### **5. Esempio di verifica di un'equazione**

Consideriamo `MEMR` nello stato $S_6$.

- Se l'istruzione è `LOAD`, $S_6=1$ e $L=1$: il termine $S_6L$ rende `MEMR=1`.
- Se è `ADD`, $A=1$: anche $S_6A$ rende `MEMR=1`.
- Se è `STORE`, $L=A=0$: `MEMR` resta 0, perché la sequenza deve preparare `MDR`, non leggere memoria.
- Se è `BRZ`, vale ancora $L=A=0$: non occorre alcun accesso dati.

Questo controllo locale è utile per individuare termini mancanti o attivazioni spurie prima di implementare il circuito.

### **6. Struttura complessiva della CU cablata**

La realizzazione può essere letta come quattro blocchi:

1. il registro `IR` fornisce l'opcode;
2. un decoder $2\rightarrow4$ produce `L`, `S`, `A`, `B`;
3. un contatore di stato e il relativo decoder producono $S_0,\ldots,S_9$;
4. una rete combinatoria calcola tutti i comandi del datapath e del bus.

`Z` entra direttamente nella rete combinatoria perché influenza soltanto il ramo condizionale. Il clock aggiorna il contatore e, attraverso i segnali prodotti, sincronizza i trasferimenti tra registri.

### **7. Vantaggi della soluzione cablata**

La CU cablata offre:

- **rapidità**, perché i segnali sono prodotti dalla sola propagazione attraverso porte logiche;
- **efficienza**, perché la rete può essere minimizzata per la specifica ISA;
- **buona adattabilità alle ISA semplici**, tipiche delle impostazioni RISC;
- assenza di una lettura di memoria interna per ciascun passo di controllo.

Per YM-0 il numero di ingressi, stati e uscite è ridotto: la rete resta comprensibile e il progetto è naturale.

### **8. Limiti e crescita della complessità**

La rigidità è il principale svantaggio. Se si modifica l'ISA, si aggiunge un modo di indirizzamento o si cambia la sequenza di una istruzione, possono cambiare numerose equazioni e il circuito deve essere riprogettato.

In una macchina CISC (*Complex Instruction Set Computer*) aumentano:

- il numero di opcode;
- i modi di indirizzamento;
- le condizioni e le eccezioni;
- il numero di passi delle istruzioni;
- le uscite da coordinare.

La rete combinatoria cresce rapidamente fino a diventare difficile da progettare, verificare e modificare. Storicamente questo limite ha favorito la microprogrammazione. Con l'aumento della densità di integrazione, tuttavia, è divenuto possibile cablare unità di controllo molto più complesse.

> ⚠️ “Cablata” non significa priva di stato: il contatore e i registri sono sequenziali. Significa che la corrispondenza tra ingressi e segnali di controllo è realizzata mediante logica hardware, non mediante microistruzioni conservate in una ROM.

> ✅ La CU cablata converte direttamente opcode, flag e stato temporale in comandi. È veloce e adatta a controlli regolari, ma ogni cambiamento architetturale si riflette sul circuito fisico.
