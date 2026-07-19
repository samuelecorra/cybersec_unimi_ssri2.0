## ***Lezione 1: Linguaggio assembly della LC-2***

---

> 📌 Questa lezione rielabora integralmente le pagine 58–62 di `M2doc.pdf`; la pagina 62 è priva di nuovi contenuti.

### **1. Perché introdurre un linguaggio simbolico**

Una parola macchina come `1110 000 0 0000 1000` è direttamente eseguibile, ma è difficile da ricordare, modificare e verificare. L’**assembly** associa mnemonici e nomi simbolici agli stessi campi binari:

```text
1110 000 0 0000 1000   →   LEA R0, table
```

Per le istruzioni reali la corrispondenza è sostanzialmente **uno a uno**: un’istruzione assembly genera una parola macchina LC-2. Le direttive dell’assembler costituiscono l’eccezione, perché organizzano il file e i dati senza essere eseguite dalla CPU.

### **2. Anatomia di una riga**

Una riga può contenere quattro campi:

```text
[Label]    Opcode    Operandi    ; Commento
```

- la **label** è un nome facoltativo associato all’indirizzo della riga;
- l’**opcode** è il mnemonico dell’istruzione o il nome di una direttiva;
- gli **operandi** dipendono dal formato scelto;
- dopo `;` si trova un commento ignorato dall’assembler.

Le etichette eliminano la necessità di ricalcolare manualmente indirizzi e campi di pagina ogni volta che il programma cambia. Per esempio `BRZ finish` significa “codifica nel branch l’indirizzo associato a `finish`”.

### **3. Notazione delle costanti**

Nel sorgente LC-2 il prefisso rende esplicita la base:

| Prefisso | Base | Esempio |
| --- | --- | --- |
| `b` | binaria | `b1010` |
| `x` | esadecimale | `x3000` |
| `#` | decimale | `#15`, `#-1` |

Il prefisso appartiene alla sintassi dell’assembler, non alla parola memorizzata: in memoria rimangono soltanto bit.

### **4. Direttive o pseudo-operazioni**

Le direttive iniziano con un punto e sono elaborate in traduzione.

| Direttiva | Effetto |
| --- | --- |
| `.orig x3000` | fissa l’indirizzo iniziale del blocco |
| `.fill valore` | emette una parola contenente il valore |
| `.blkw n` | riserva un blocco di $n$ parole |
| `.stringz "testo"` | emette un carattere per parola e uno zero finale |
| `.end` | conclude il sorgente da assemblare |

`.fill`, `.blkw` e `.stringz` occupano memoria ma non rappresentano operazioni da eseguire. Codice e dati convivono nello stesso spazio, coerentemente con il modello di von Neumann.

> ⚠️ `.end` non arresta la CPU: termina il file sorgente. L’arresto durante l’esecuzione si ottiene con il servizio `TRAP x25`.

### **5. Il programma di somma riscritto in assembly**

```asm
        .orig x3000
        LEA   R0, table       ; R0 punta al primo elemento
        AND   R2, R2, #0      ; accumulatore = 0

loop    LDR   R1, R0, #0      ; legge *R0 e aggiorna CC
        BRZ   finish          ; zero: sentinella raggiunta
        ADD   R2, R2, R1      ; accumula l'elemento
        ADD   R0, R0, #1      ; passa alla parola successiva
        BRNZP loop            ; salto incondizionato

finish  ST    R2, result      ; salva la somma

table   .blkw #4              ; quattro elementi del vettore
        .fill #0              ; terminatore
result  .blkw #1              ; cella destinata al risultato
        .end
```

Partendo da `x3000`, le otto istruzioni occupano `x3000–x3007`; `table` vale quindi `x3008`, lo zero è a `x300C` e `result` a `x300D`. L’assembler ricava questi indirizzi automaticamente.

### **6. Che cosa traduce l’assembler**

Per una riga come `AND R2,R2,#0`, registri e immediato sono già noti e la traduzione è diretta:

```text
0101 010 010 1 00000 = x54A0
```

Per `LEA R0,table`, invece, il valore di `table` dipende dalla posizione di tutte le righe precedenti. Questo problema dei riferimenti in avanti conduce al processo a due passate studiato nella lezione successiva.

> ✅ L’assembly conserva con precisione le operazioni della macchina, ma sostituisce campi binari fragili con mnemonici, label, commenti e direttive verificabili dall’assembler.
