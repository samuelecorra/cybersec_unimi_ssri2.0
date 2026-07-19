## ***Lezione 2: Circuiti sommatori***

---

> 📌 Questa lezione rielabora integralmente le pagine 31–36 di `M4doc.pdf`: half-adder, full-adder, ripple-carry, carry look-ahead e circuito unico per somma e sottrazione in complemento a 2.

### **1. Il problema della somma binaria**

La somma di due bit non produce sempre un solo bit. Quando entrambi valgono 1:

$$
1+1=10_2,
$$

occorrono un bit di somma, pari a 0, e un riporto, pari a 1. Per parole di più bit, il riporto generato in una posizione deve contribuire alla posizione successiva.

Indicheremo con:

- $A_i$ e $B_i$ i bit degli operandi nella posizione $i$;
- $R_i$ il riporto **in ingresso** alla posizione $i$;
- $S_i$ il bit di somma;
- $R_{i+1}$ il riporto prodotto verso la posizione successiva.

Nel PDF la lettera $R$ è usata per il riporto; non va confusa con l'intera parola risultato dell'ALU.

### **2. Half-adder**

L'**half-adder**, o semisommatore, somma soltanto $A_i$ e $B_i$. Le equazioni sono:

$$
S_i=A_i\oplus B_i,
$$

$$
R_{i+1}=A_iB_i.
$$

| $A_i$ | $B_i$ | $S_i$ | $R_{i+1}$ |
| ---: | ---: | ---: | ---: |
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |

Il circuito è sufficiente per il bit meno significativo solo quando non esiste un riporto iniziale. Non può invece essere usato nelle altre posizioni, perché manca l'ingresso $R_i$.

### **3. Full-adder**

Il **full-adder**, o sommatore completo, considera tre bit in ingresso. Il bit di somma è:

$$
S_i=A_i\oplus B_i\oplus R_i.
$$

Il riporto in uscita vale 1 quando almeno due dei tre ingressi valgono 1:

$$
R_{i+1}=A_iB_i+A_iR_i+B_iR_i.
$$

| $A_i$ | $B_i$ | $R_i$ | $S_i$ | $R_{i+1}$ |
| ---: | ---: | ---: | ---: | ---: |
| 0 | 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 | 0 |
| 0 | 1 | 0 | 1 | 0 |
| 0 | 1 | 1 | 0 | 1 |
| 1 | 0 | 0 | 1 | 0 |
| 1 | 0 | 1 | 0 | 1 |
| 1 | 1 | 0 | 0 | 1 |
| 1 | 1 | 1 | 1 | 1 |

Un full-adder può essere costruito combinando due half-adder e una porta OR, oppure sintetizzando direttamente le funzioni booleane.

### **4. Ripple-carry adder**

Per sommare due parole da $n$ bit si collegano $n$ full-adder in cascata. Il riporto di ciascuna cella diventa l'ingresso della successiva:

$$
R_{i+1}\longrightarrow R_{i+1}\text{ della cella successiva}.
$$

Nella posizione meno significativa si impone $R_0=0$. Sarebbe possibile usare un half-adder nella prima posizione, ma replicare la stessa cella full-adder rende il circuito più regolare.

Esempio, $0111_2+0001_2$:

| Posizione | $A_i$ | $B_i$ | Riporto in ingresso | $S_i$ | Riporto in uscita |
| ---: | ---: | ---: | ---: | ---: | ---: |
| 0 | 1 | 1 | 0 | 0 | 1 |
| 1 | 1 | 0 | 1 | 0 | 1 |
| 2 | 1 | 0 | 1 | 0 | 1 |
| 3 | 0 | 0 | 1 | 1 | 0 |

Il risultato è `1000`. Per determinarne il bit più significativo, il riporto deve propagarsi dalla cella 0 fino alla cella 3: da qui il nome **ripple-carry**, propagazione a catena del riporto.

### **5. Ritardo del ripple-carry**

Un full-adder ottimizzato può produrre il riporto attraverso circa due livelli di porte. Nel caso peggiore, il riporto attraversa tutte le $n$ celle; la profondità critica cresce quindi linearmente:

$$
T_{RCA}\propto 2n.
$$

Per il confronto riportato nel PDF, un sommatore ripple-carry a 8 bit richiede circa 16 livelli logici. Aumentare il numero di bit aumenta direttamente il tempo necessario prima che il risultato sia stabile.

> ⚠️ Tutti i bit $A_i$ e $B_i$ sono disponibili subito, ma il bit di somma più significativo dipende dal riporto proveniente dalle posizioni inferiori. È questa dipendenza, non la scrittura “in parallelo” degli operandi, a determinare il cammino critico.

### **6. Generate e propagate**

Per ridurre l'attesa, si riscrive l'equazione del riporto:

$$
R_{i+1}=A_iB_i+(A_i+B_i)R_i.
$$

Si definiscono:

$$
G_i=A_iB_i,
$$

$$
P_i=A_i+B_i.
$$

`G` significa **generate**: se $A_i=B_i=1$, la posizione genera un riporto indipendentemente da $R_i$.

`P` significa **propagate**: se almeno uno tra $A_i$ e $B_i$ vale 1, un riporto in ingresso viene propagato in uscita. Con le definizioni adottate dal PDF:

$$
R_{i+1}=G_i+P_iR_i.
$$

> 📌 Esiste anche una convenzione diffusa che definisce $P_i=A_i\oplus B_i$. Qui si mantiene la convenzione OR del PDF; entrambe possono essere corrette se usate con equazioni coerenti.

### **7. Carry look-ahead**

Sostituendo ricorsivamente i riporti precedenti si ottiene:

$$
R_{i+1}=G_i+P_iG_{i-1}+P_iP_{i-1}G_{i-2}+\cdots+P_iP_{i-1}\cdots P_0R_0.
$$

Per esempio:

$$
R_1=G_0+P_0R_0,
$$

$$
R_2=G_1+P_1G_0+P_1P_0R_0,
$$

$$
R_3=G_2+P_2G_1+P_2P_1G_0+P_2P_1P_0R_0.
$$

Il **carry look-ahead adder** (CLA) calcola questi termini direttamente, senza aspettare che il riporto attraversi in sequenza ogni full-adder. La realizzazione ideale usa:

1. un livello per calcolare $G_i$ e $P_i$;
2. un livello di porte AND per i prodotti;
3. un livello OR per sommarli logicamente.

Il confronto teorico del PDF è quindi:

| Sommatore a 8 bit | Livelli logici indicativi |
| --- | ---: |
| Ripple-carry | 16 |
| Carry look-ahead | 3 |

### **8. Limiti tecnologici del look-ahead**

L'espansione algebrica sembra rendere il ritardo indipendente da $n$, ma richiede porte con molti ingressi e un numero crescente di termini. Una porta fisica non può avere fan-in arbitrario senza penalità di area, capacità e ritardo.

Per questo i CLA reali vengono organizzati in blocchi, storicamente spesso da 4 o 8 bit. Ogni blocco produce segnali di generate e propagate di gruppo; un livello gerarchico calcola poi i riporti tra blocchi. Si conserva così gran parte del vantaggio senza pretendere porte irrealistiche.

### **9. Un unico circuito per somma e sottrazione**

In complemento a 2:

$$
A-B=A+(\overline{B}+1).
$$

Un segnale di controllo `ADD/SUB` viene collegato:

- a una XOR posta davanti a ogni bit $B_i$;
- al riporto iniziale $R_0$.

Il circuito calcola:

$$
A+(B\oplus SUB)+SUB.
$$

#### **9.1. Caso `SUB=0`**

$$
B\oplus0=B,\qquad R_0=0,
$$

quindi:

$$
A+B.
$$

#### **9.2. Caso `SUB=1`**

$$
B\oplus1=\overline{B},\qquad R_0=1,
$$

quindi:

$$
A+\overline{B}+1=A-B.
$$

Il complemento a 2 di `B` viene prodotto **al volo**: non serve un secondo circuito aritmetico dedicato.

Esempio su 4 bit, $5-3$:

$$
0101+(0011\oplus1111)+1
=0101+1100+1
=0010,
$$

dove il riporto oltre i 4 bit viene scartato. Il risultato `0010` rappresenta 2.

### **10. Riporto e overflow non sono la stessa cosa**

Il riporto finale è significativo per l'aritmetica senza segno. Nell'aritmetica in complemento a 2, invece, l'overflow con segno si verifica quando gli operandi hanno lo stesso segno e il risultato ha segno opposto. Equivalentemente, può essere rilevato confrontando il riporto entrante e uscente dal bit di segno.

Esempio su 4 bit:

$$
0111+0001=1000.
$$

Come configurazioni senza segno è $7+1=8$, perfettamente rappresentabile. In complemento a 2, però, `1000` vale $-8$: la somma di due positivi ha prodotto un negativo, quindi c'è overflow con segno.

> ✅ Il ripple-carry privilegia regolarità e semplicità; il carry look-ahead anticipa i riporti mediante le funzioni generate/propagate; l'inversione controllata di `B` e il carry iniziale permettono allo stesso sommatore di realizzare anche la sottrazione in complemento a 2.
