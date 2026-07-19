## ***Lezione 3: Esempi di programmi in linguaggio macchina LC-2***

---

> 📌 Questa lezione rielabora integralmente le pagine 45–57 di `M2doc.pdf`.

### **1. Primo programma: indirizzi, dati e indirezione**

Il primo esempio occupa sette celle a partire da `x30F6`. La notazione simbolica serve qui soltanto a leggere il significato delle parole macchina.

| Indirizzo | Parola | Lettura simbolica | Effetto |
| --- | --- | --- | --- |
| `x30F6` | `xE2F4` | `LEA R1,x30F4` | `R1←x30F4` |
| `x30F7` | `x146E` | `ADD R2,R1,#14` | `R2←x3102` |
| `x30F8` | `x34F4` | `ST R2,x30F4` | `M[x30F4]←x3102` |
| `x30F9` | `x54A0` | `AND R2,R2,#0` | `R2←0`, `CC←Z` |
| `x30FA` | `x14A5` | `ADD R2,R2,#5` | `R2←5`, `CC←P` |
| `x30FB` | `x744E` | `STR R2,R1,#14` | `M[x3102]←5` |
| `x30FC` | `xA6F4` | `LDI R3,x30F4` | `R3←M[M[x30F4]]=5` |

> ⚠️ Il valore caricato dalla prima `LEA` è `x30F4`, non l’indirizzo del programma `x30F6`. Da qui `x30F4+#14=x3102`: confondere i due indirizzi porterebbe al risultato errato `x3104`.

#### **1.1 Decodifica della prima istruzione**

```text
xE2F4 = 1110 001 0 1111 0100
         LEA  R1   pgoffset9
```

I 7 bit alti del PC nella pagina `x3000–x31FF`, concatenati con `0 1111 0100`, producono `x30F4`. La forma `LEA R1,x30F4` è un’abbreviazione esplicativa: in un sorgente si userebbe normalmente un’etichetta.

#### **1.2 Costruzione di un puntatore**

Le prime tre istruzioni costruiscono e memorizzano il puntatore `x3102`:

$$
R1=x30F4,
\qquad R2=x30F4+x000E=x3102,
\qquad M[x30F4]=x3102.
$$

Le tre successive azzerano `R2`, vi collocano 5 e memorizzano quel valore all’indirizzo base+indice `x3102`. L’ultima istruzione segue il puntatore depositato in `x30F4` e recupera il 5 in `R3`.

Questo esempio distingue chiaramente:

- **indirizzo** `x3102`;
- **cella che contiene l’indirizzo** `x30F4`;
- **valore puntato** `M[x3102]=x0005`.

### **2. Secondo programma: somma di un vettore terminato da zero**

Il programma parte da `x3000`; il vettore è collocato da `x3008` e la cella risultato è `x300D`.

| Indirizzo | Parola macchina | Assembly esplicativo |
| --- | --- | --- |
| `x3000` | `xE008` | `LEA R0,table` |
| `x3001` | `x54A0` | `AND R2,R2,#0` |
| `x3002` | `x6200` | `loop LDR R1,R0,#0` |
| `x3003` | `x0407` | `BRZ finish` |
| `x3004` | `x1481` | `ADD R2,R2,R1` |
| `x3005` | `x1021` | `ADD R0,R0,#1` |
| `x3006` | `x0E02` | `BRNZP loop` |
| `x3007` | `x340D` | `finish ST R2,result` |

> ⚠️ Una schermata del vecchio simulatore nelle pagine successive mostra per errore `x1221`, cioè `ADD R1,R0,#1`, alla posizione `x3005`. Il listato, la codifica dettagliata e le schermate funzionanti riportano `x1021`, cioè `ADD R0,R0,#1`: è questa la forma corretta, perché deve avanzare il puntatore in `R0`.

Il vettore di esempio è:

| Indirizzo | Esadecimale | Interpretazione decimale con segno |
| --- | --- | --- |
| `x3008` | `x0006` | 6 |
| `x3009` | `x0014` | 20 |
| `x300A` | `xE400` | $-7168$ |
| `x300B` | `x7800` | 30720 |
| `x300C` | `x0000` | terminatore |
| `x300D` | spazio risultato | — |

#### **2.1 Inizializzazione**

`R0` diventa il puntatore al primo elemento (`x3008`). `R2` è azzerato e usato come accumulatore.

#### **2.2 Corpo del ciclo**

`LDR R1,R0,#0` legge l’elemento corrente e aggiorna i codici di condizione. Se vale zero, `BRZ finish` salta alla memorizzazione finale. Altrimenti il valore viene sommato a `R2`, `R0` avanza di una cella e `BRNZP loop` torna incondizionatamente a `x3002`.

La maschera del branch è confrontata con `CC`; poiché uno tra N, Z e P è attivo, `BRNZP` è sempre preso.

#### **2.3 Traccia numerica**

| Elemento letto | Accumulatore dopo `ADD` | Puntatore successivo |
| --- | --- | --- |
| `x0006` | `x0006` | `x3009` |
| `x0014` | `x001A` | `x300A` |
| `xE400` | `xE41A` | `x300B` |
| `x7800` | `x5C1A` | `x300C` |
| `x0000` | nessuna somma | resta `x300C` |

In decimale:

$$
6+20-7168+30720=23578=x5C1A.
$$

Il risultato rientra nell’intervallo con segno a 16 bit e viene scritto in `M[x300D]`.

#### **2.4 Corrispondenza con un algoritmo ad alto livello**

```c
sum = 0;
p = table;
while (*p != 0) {
    sum = sum + *p;
    p = p + 1;
}
result = sum;
```

Il valore zero è una **sentinella** e non partecipa alla somma. Di conseguenza questo formato non potrebbe rappresentare un vettore che debba contenere uno zero come elemento ordinario senza affiancare, per esempio, una lunghezza esplicita.

> ✅ Il primo programma rende concreti puntatori e indirezione; il secondo costruisce puntatore, accumulatore, test, ciclo e sentinella usando soltanto primitive dell’ISA.
