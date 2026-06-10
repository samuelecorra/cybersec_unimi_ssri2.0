# **Lezione 3: Attacchi al DES**

---

### **1. Tipologie di attacco**

Gli attacchi al **Data Encryption Standard (DES)** si dividono in due grandi categorie:

- **Attacchi a forza bruta**, basati sulla ricerca esaustiva di tutte le chiavi possibili.
- **Analisi crittografica**, basata su vulnerabilità strutturali dell’algoritmo.

Le principali tecniche note sono:

- **Criptoanalisi differenziale** (Biham e Shamir, 1990)
- **Criptoanalisi lineare** (Matsui, 1993)

---

### **2. Attacchi a forza bruta**

Il DES utilizza chiavi di **56 bit**, quindi esistono:

$$
2^{56} \approx 7,2 \times 10^{16}
$$

chiavi possibili.

#### **Attacco known plaintext**

Si dispone di uno o più **coppie testo in chiaro–testo cifrato**:

- Si calcola per tutte le $2^{56}$ chiavi possibili $y_k = DES_k(x)$.
- Si confrontano i risultati con il testo cifrato noto.
- In media, servono $2^{55}$ prove.

#### **Attacco chosen plaintext**

Si sceglie un testo in chiaro $x$ e si calcolano:

$$
y_k = DES_k(x)
$$

per ogni possibile chiave $k$, costruendo una **tabella ($y_k$, $k$)**.  
Quando si ottiene un nuovo testo cifrato $y$, basta cercarlo nella tabella per risalire alla chiave.

- **Tempo di ricerca:** logaritmico o costante. Logaritmico perché, se la tabella è ordinata, si può usare una ricerca binaria. Costante se si utilizza una struttura dati come una hash table.
- **Precomputazione:** $2^{56}$ cifrature.
- **Memoria:** $2^{56}$ voci.

---

### **3. Time–Space Trade-Off**

#### **3.1 Idea generale**

Si cerca un compromesso tra **tempo di calcolo** e **spazio di memoria**.

L'approccio consente un tempo ridotto rispetto alla ricerca esaustiva, e uno spazio di memoria ridotto rispetto al mantenimento di una tabella completa. Il costo sta nell'aver bisogno di più tempo per la precomputazione e nella possibilità di falsi positivi, che richiedono ulteriori verifiche.

![](imgs/Pasted%20image%2020260222105443.png)

Si definisce una funzione di riduzione:

$$
F: \{0,1\}^{64} \rightarrow \{0,1\}^{56}
$$

e si costruisce una catena di valori:

$$
X_{(1,0)} \to X_{(1,1)} = F(DES_{X_{(1,0)}}(x)) \to X_{(1,2)} \to \dots \to X_{(1,t)}
$$

Vengono create $m$ catene, ognuna di lunghezza $t$, formando una tabella di dimensioni $m \times t$.  
Ogni cella contiene un valore intermedio di 56 bit.

Tali elementi possono essere interpretati come:

- cyphertext ridotti;
- chiavi

Formalmente, come indica il docente, per ogni stringa iniziale, calcolata con la funzione di riduzione, tutta la riga si compone nel seguente modo:

$$X(i,j) = F(DES_{X(i,j-1)}(x)) per j > 0$$

Tradotto in linguaggio naturale: per calcolare tutte le varie voci delle colonne successive alla prima, si applica la funzione di riduzione al risultato della cifratura DES con chiave corrispondente alla voce nella tabella precedente, sull'immediata sinistra.

IMPORTANTE: la tabella non è un database completo di tutte le chiavi, è un **campionamento probabilistico** dello spazio delle chiavi, e le m righe servono ad alzare la probabilità di successo dell'attacco!

#### **3.2 Ricerca**

Ricordiamo che lo scopo della tabella appena costruita è quello di poter risalire alla chiave a partire dalla coppia chiaro–cifrato, dal momento che operiamo nell'ottica di un chosen plaintext attack.

Ma finora abbiamo solo computato, senza chiederci come questa concatenazione messa in tabella possa essere utile per attaccare il DES.

Supponiamo di ottenere un testo cifrato $y$ a partire da un testo in chiaro $x$. Si calcola la riduzione $F(y)$, che quindi è coerente con le voci in tabella.

L'idea di fondo è che se siamo stati fortunati, l'ipotesi migliore è che questo valore appena arrivatoci sia presente nell'ultima colonna della tabella.
Quindi confrontiamo:

- se è presente nell’ultima colonna, CIO' NON SIGNIFICA AUTOMATICAMENTE CHE SIAMO IN CORRISPONDENZA DI UNA SOLUZIONE. Infatti, la funzione di riduzione non è iniettiva, perché ad esempio potrebbe banalmente togliere gli ultimi 8 bit dal valore originale di 64bit, e quindi più valori diversi potrebbero essere ridotti allo stesso valore. Per la precisione, dal momento che perdiamo 8bit, ogni valore può avere fino a $2^8 = 256$ preimmagini. Quindi, se è presente nell'ultima colonna, non siamo contenti!  Andiamo quindi a verificare la corrispondenza precisa con $DES_{X(i,t-1)}(x)$, ovvero con il risultato della cifratura DES con la chiave che corrisponde alla voce nella colonna sulla sinistra dell’ultima colonna, e se c’è corrispondenza, abbiamo trovato la chiave;
- se è presente nell'ultima colonna ma non c'è corrispondenza, vuol dire che abbiamo preso un'abbaglio causato dalla non iniettività di $F$, e quindi dobbiamo continuare a cercare, andando a controllare la colonna precedente, e così via iterativamente, applicando $DES$ e $F$ fino a risalire alla prima colonna. Se arriviamo alla prima colonna senza trovare corrispondenza, vuol dire che non abbiamo trovato la chiave.

#### **3.3 Ottimizzazione**

Finora abbiamo esposto questo modello da un punto di vista meramente empirico, quindi era relativamente facile vedere il funzionamento ma carpire la convenienza non lo era altrettanto. Vediamo ora perché questo modello è conveniente.

Si memorizzano solo le **prime e ultime colonne** di ciascuna catena.  
Questa scelta è motivata dal fatto che, basandoci sul caso fortuito in cui $F(y)$ compare nella colonna finale, si può ricostruire la chiave candidata efficientemente.

In un primo scenario prendiamo in esame il caso fortuito:

1. Conserviamo solo le colonne $0$ e $t$ 
2. Supponiamo di trovare $F(y)$ in $t$ ad una certa riga e che quindi $F(y)=X(i,t)$
3. Deduciamo che allora la chiave candidata è sicuramente $X(i,t-1)$, perché è l’unica chiave che può portare a $X(i,t)$ attraverso la cifratura DES e la funzione di riduzione $F$.
4. Non avendo salvato tutte le colonne tra prima e ultima, abbiamo comunque una scappatoia, ovvero partire da $X(i,0)$ e calcolare iterativamente tutte le riduzioni fino alla suddetta $X(i,t-1)$, e verificare se $DES_{X(i,t-1)}(x)$ è uguale a $y$. Se è così, abbiamo trovato la chiave, altrimenti abbiamo preso un abbaglio.

5. **In caso di abbaglio: ricerca nelle colonne precedenti senza ricalcolarle.**

   Supponiamo di non trovare corrispondenza nella colonna $t$ (o di aver trovato $F(y)=X(i,t)$ ma la verifica $DES_{X(i,t-1)}(x)=y$ sia fallita). A questo punto vorremmo cercare se $F(y)$ si trova nella colonna $t-1$, ma quella colonna non è stata memorizzata.

   Il trucco chiave è il seguente: **invece di ricalcolare la colonna $t-1$**, si sfrutta la struttura della catena. Se $F(y)$ fosse effettivamente nella colonna $t-1$, ovvero $F(y) = X(j, t-1)$ per qualche riga $j$, allora applicando un ulteriore passo della catena si otterrebbe:

   $$
   F\!\left(DES_{F(y)}(x)\right) = X(j,\, t)
   $$

   ovvero il risultato sarebbe nella colonna $t$ (quella memorizzata). Quindi, **invece di cercare $F(y)$ nella colonna $t-1$, cerchiamo $F(DES_{F(y)}(x))$ nella colonna $t$**, che è già disponibile in memoria.

   - Se troviamo $F(DES_{F(y)}(x)) = X(j,t)$, la chiave candidata è $X(j,t-2)$: la calcoliamo ripartendo da $X(j,0)$ e percorrendo la catena fino al penultimo elemento.
   - Se anche questa ricerca fallisce, ripetiamo il ragionamento per la colonna $t-2$, calcolando $y_2 = F(DES_{y_1}(x))$ con $y_1 = F(DES_{F(y)}(x))$, e cercando $y_2$ nella colonna $t$.

   Il procedimento si itera per tutte le $t$ colonne, computando ad ogni passo:

   $$
   y_{j+1} = F\!\left(DES_{y_j}(x)\right)
   $$

   e cercando $y_{j+1}$ nella colonna $t$. Il numero massimo di passi è $t$, dunque il costo di ricerca per una catena è $O(t)$, ovvero lineare rispetto al numero di cifrature, senza mai dover materializzare le colonne intermedie.

> 💡 L'intuizione geometrica è: invece di "andare a sinistra" nella tabella (colonne intermedie non memorizzate), si fa "avanzare la catena" di un passo in più e si continua a interrogare solo la colonna destra, l'unica salvata. Ogni passo di allungamento corrisponde a scalare di una posizione verso sinistra nell'originale.

> ⚠️ Questo funziona perché la funzione di riduzione $F$ è deterministica: dato $y_j$, il valore $y_{j+1}$ è sempre lo stesso indipendentemente da quale riga si trovi nella tabella. Se la ricerca fallisce per tutti i $t$ passi, la chiave non è nella tabella e bisogna ricorrere alle altre $T-1$ tabelle indipendenti.

---

ATTENZIONE: anche trovando svariati match all'interno della tabella così costruita, non abbiamo alcun tipo di garanzia sul fatto che la chiave associata sia quella giusta.

Questo sempre a causa del codominio della funzione di riduzione $F$, che non è iniettiva, e quindi più chiavi diverse possono portare allo stesso valore ridotto.

Ma allora non siamo del tutto soddisfatti, si potrebbe ottimizzare ancora l'attacco...
Costruire una tabella completa sarebbe molto oneroso, non ci conviene, ma possiamo costruire più tabelle indipendenti, ognuna con parametri diversi, in modo da aumentare la probabilità di successo dell'attacco.

Operando così, avremo solo una perdita in termini di tempo di precomputazione, ma guadagneremo in termini di probabilità di successo, che sarà più alta rispetto a una singola tabella. Ecco l'etimologia del nome "time–space trade-off": si sacrifica tempo di precomputazione per guadagnare spazio (memoria) e tempo di ricerca.

Senza entrare nei dettagli matematici di come sono stati ricavate le seguenti affermazioni, vediamo adesso quali sono i parametri ottimali per massimizzare l'efficacia dell'attacco:

Dato $N=2^{56}$, ovvero lo spazio delle chiavi, se:

(promemoria: il simbolo $\approx$ indica che stiamo trascurando costanti moltiplicative, quindi stiamo esprimendo una relazione di ordine di grandezza)

$$
m \cdot t^2 \approx N
$$

allora la probabilità che la chiave sia presente in almeno una delle tabelle è circa:

$$
0,8*m*t/N
$$

La scelta ottimale prevede:

$$
m \approx N^{1/3}, \quad t \approx N^{1/3}, \quad T \approx N^{1/3}
$$

Con questa configurazione, otteniamo:

- **Complessità in Spazio:** $numero\_tabelle \cdot numero\_colonne \cdot numero\_righe \cdot dimensione\_cella$
	
	che si traduce in:
	
	$T \cdot t \cdot m \cdot 56$
	
	$N^{1/3} \cdot 2 \cdot N^{1/3} \cdot 56 = 112 \cdot N^{2/3}$

E infine ricaviamo i tempi che ci interessano:

- **Tempo di precomputazione:** per costruire le tabelle bisogna riempire ogni cella, quindi i fattori coinvolti sono il numero di tabelle e le dimensioni di ciascuna. E' un investimento che si fa offline, quindi una volta sola prima ancora che arrivi il cyphertext di nostro interesse:

	 $T \cdot m \cdot t = N^{1/3} \cdot N^{1/3} \cdot N^{1/3} = N$

- **Tempo di ricerca:** si suppone il worst case, come di consueto - dovendo scorrere al massimo tutte le colonne all'indietro per tutte le tabelle, si ha:

	$T \cdot t = N^{1/3} \cdot N^{1/3} =N^{2/3}$


---

### **4. Chiavi deboli e semideboli**

#### **4.1 Chiavi deboli**

Una chiave $k$ è **debole** se, per ogni testo $x$:

$$
DES_k(DES_k(x)) = x
$$

cioè la doppia cifratura con la stessa chiave restituisce il testo originale.  
Questo avviene quando **tutte le sottochiavi generate sono uguali**.

![](imgs/Pasted%20image%2020260222111111.png)

Le 4 chiavi deboli note (in esadecimale):

```
0101 0101 0101 0101
FEFE FEFE FEFE FEFE
1F1F 1F1F 0E0E 0E0E
E0E0 E0E0 F1F1 F1F1
```

Ovviamente in hex sono a 64bit in quanto si tiene conto degli 8bit di padding originali. Tolti questi 8bit durante la PC1 (vedere Lezione 1, punto 9) rimarranno 56bit
che si dividono tra parte sx e dx come segue:

- entrambe a 0 o entrambe a 1 (prima e seconda chiave deboli)
- una parte a 0 e l'altra a 1 (terza e quarta chiave deboli)

Ma perché queste? Perché dopo lo spezzamento in due metà, la schedulazione prosegue sulle singole metà con un left shift che non ha nessun effetto, e itera fino alla sedicesima sempre mediante left shift, quindi tutte le sottochiavi generate sono uguali alla chiave originale, e quindi si ha la proprietà di debolezza.

---

#### **4.2 Chiavi semideboli**

Due chiavi $k$ e $k'$ sono **semideboli** se, per ogni testo $x$:

$$
DES_k(DES_{k'}(x)) = x
$$

Le loro sottochiavi si ripetono in coppie (solo due pattern diversi, ciascuno usato otto volte).  
Esistono **6 coppie di chiavi semideboli**.

![](imgs/Pasted%20image%2020260222111139.png)

Tolti gli 8 bit di parità dalla PC1, le 6 coppie si spiegano con un ragionamento analogo a quello delle chiavi deboli, ma con una variante cruciale.

Ciascuna delle due metà da 28 bit ha una struttura a **periodo 2**: i bit si alternano regolarmente (del tipo `0101...01` oppure `1010...10` su 28 posizioni), quindi una rotazione di un numero **pari** di posizioni la lascia invariata, mentre una rotazione di un numero **dispari** la porta nel proprio complemento.

Analizzando la sequenza di shift della key schedule DES:

$$1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1$$

gli shift cumulativi risultanti sono: $1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28$.  
Di questi, **otto sono dispari** (round 1 e 9–15) e **otto sono pari** (round 2–8 e 16). Pertanto la key schedule produce esattamente **due distinte configurazioni** per ogni metà — chiamiamole $A$ e $B = \bar{A}$ — ciascuna usata per generare 8 delle 16 sottochiavi. In concreto, la sequenza di stati delle metà è:

$$[B, A, A, A, A, A, A, A,\ B, B, B, B, B, B, B, A]$$

La chiave gemella $k'$ ha le medesime due metà **scambiate nei rispettivi stati iniziali**: quella che per $k$ partiva da $A$ parte ora da $B$ e viceversa. Il suo calendario di stati risulta:

$$[A, B, B, B, B, B, B, B,\ A, A, A, A, A, A, A, B]$$

che è esattamente il calendario di $k$ letto **al contrario**. Poiché il DES in decifratura usa le sottochiavi nell'ordine inverso, $DES_{k'}(\cdot)$ si comporta esattamente come $DES_k^{-1}(\cdot)$, da cui:

$$DES_k(DES_{k'}(x)) = DES_k\!\left(DES_k^{-1}(x)\right) = x$$

Le **6 coppie** nascono dalle diverse combinazioni di pattern a corto periodo ammissibili per le metà $C$ e $D$ dopo la PC1: con più configurazioni compatibili con questo stesso meccanismo (non solo la stretta alternanza bit-a-bit, ma anche varianti che producono identicamente due soli stati sotto lo schedule di shift del DES), si ottengono 6 coppie distinte anziché una sola.

---

#### **4.3 Proprietà del complemento**

Se $x$, $y$ e $k$ sono rispettivamente testo in chiaro, testo cifrato e chiave, allora vale:

$$
DES_k(x) = y \Rightarrow DES_{\bar{k}}(\bar{x}) = \bar{y}
$$

dove $\bar{x}$ indica il **complemento bit per bit** di $x$.

![](imgs/Pasted%20image%2020260222111220.png)

Questa simmetria riduce lo spazio effettivo di ricerca: in un **chosen plaintext attack**, basta analizzare **$2^{55}$ chiavi** anziché $2^{56}$.

---

### **5. Efficacia pratica degli attacchi**

#### **5.1 Stime di ricerca esaustiva**

| Velocità                     | Tempo per testare metà chiavi ($2^{55}$) |
| ---------------------------- | ---------------------------------------- |
| 1 cifratura/μs               | ~1142 anni                               |
| 1 milione cifrature/μs       | ~10 ore                                  |
| CPU 500 MHz (1 chiave/ciclo) | ~834 giorni                              |

#### **5.2 DES Challenges**

- **1997:** RSA lancia una competizione con premio di $10.000.
  - Progetto **DESCHALL** (Rocke Verser): 70.000 computer coinvolti.
  - Messaggio trovato in **39 giorni**.
  - Testate ~24% delle chiavi.
- **1998:** la **EFF (Electronic Frontier Foundation)** realizza **Deep Crack**, macchina dedicata da $\$250.000$ (56h tempo d'attacco)

![](imgs/Pasted%20image%2020260222111712.png)

- **1999:** collaborazione **Distributed.net + EFF**,
  - 100.000 computer testano **245 miliardi di chiavi/sec**.
  - Tempo totale: **22 ore e 15 minuti**.

---

### **6. Deep Crack – architettura**

- **Clock:** 40 MHz
- **Cicli per decifratura:** 16
- **Chiavi testate/secondo per unità:**
  $$
  \frac{40.000.000}{16} = 2.500.000
  $$
- **Unità totali:** 24  
   → $24 \times 2.500.000 = 60.000.000$ chiavi/s  
   → Tutte le chiavi in ~13.900 giorni (~38 anni)

![](imgs/Pasted%20image%2020260222111611.png)

#### **Board finale:**

- 64 processori (32 per lato) su scheda 40×40 cm.
- Totale: $64 \times 60.000.000 = 3,84 \times 10^9$ chiavi/s.
- Tutte le chiavi esplorate in ~218 giorni.

![](imgs/Pasted%20image%2020260222111631.png)

💥 **Nel 1998**: la chiave fu trovata in **56 ore effettive**.

---

### **7. Caratteristiche e criticità del DES**

#### **Effetto valanga**

- Due testi che differiscono per 1 bit → differenza di ~34 bit nel testo cifrato.
- Due testi uguali ma cifrati con chiavi che differiscono per 1 bit → differenza di ~35 bit.

#### **Criteri progettuali delle S-box**

- **Strict Avalanche:** invertendo 1 bit in input, ciascun bit di output cambia con probabilità ½.
- **Bit Independence:** i bit di output cambiano indipendentemente.
- **Guaranteed Avalanche:** se cambia un bit in input, almeno $1 < g < 6$ bit in output cambiano.

#### **Critiche**

- **Chiave di soli 56 bit:** probabilmente scelta dalla **NSA** per poter controllare la sicurezza.
- **S-box opache:** inizialmente si sospettava la presenza di una “trapdoor”, poi esclusa.
- **Numero di round (16):** scelto in modo da garantire una distribuzione statistica quasi casuale.

---

### **8. Sintesi finale**

Abbiamo visto:

- Gli **attacchi principali** al DES (forza bruta e analitici).
- Il principio del **time–space trade-off**.
- L’esistenza di **chiavi deboli e semideboli**.
- La **proprietà del complemento**.
- Le **dimostrazioni pratiche** dell’insufficienza della chiave a 56 bit (Deep Crack e DES Challenge).

👉 Il DES, pur essendo un capolavoro di progettazione per la sua epoca, è oggi considerato **inadeguato** per la sicurezza moderna, sostituito definitivamente dal **Triple DES** e, in seguito, dall’**AES**.
