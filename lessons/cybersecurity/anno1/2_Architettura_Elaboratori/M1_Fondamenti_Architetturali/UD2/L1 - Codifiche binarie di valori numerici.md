## ***Lezione 1: Codifiche binarie di valori numerici***

---

> 📌 Questa lezione rielabora integralmente le pagine 16–30 di `M1doc.pdf`.

### **1. Introduzione: dal “mondo continuo” al “mondo discreto”**

Nell’Unità 1 hai visto il salto concettuale decisivo:

- dal **continuo** dell’analogico
    
- al **discreto** del digitale,
    
- e, in particolare, alla rappresentazione **binaria**.
    

Ora iniziamo a fare davvero **Architettura degli Elaboratori**:  
come si rappresentano, esattamente, i **numeri** all’interno della macchina?

Punto chiave:

> Prima di essere testi, immagini, suoni… **per il computer tutto è (o diventa) numero intero**.

- Un carattere è associato a un **codice numerico** (es. ASCII, Unicode),
    
- un pixel è descritto da **tre numeri interi** (RGB),
    
- un campione audio è un **numero intero** che rappresenta un’ampiezza,
    
- un indirizzo di memoria è un **numero intero**,
    
- un opcode di istruzione è un **numero intero**.
    

Gli informatici, alla radice, sono **matematici del discreto**:  
giocano con insiemi finiti, combinazioni di simboli, numeri interi, codifiche.

---

### **2. Tutto diventa numero intero**

Tipi di informazione che un calcolatore deve rappresentare:

- **Numeri**: valori, contatori, indici, indirizzi, timestamp…
    
- **Testi**: lettere, cifre, punteggiatura, simboli speciali.
    
- **Immagini**: matrici di pixel (es. $1280 \times 1024$), ogni pixel descritto da uno o più numeri.
    
- **Suoni**: sequenze di campioni, ciascuno un numero che indica la pressione dell’aria in un certo istante.
    
- **Video**: sequenze di immagini + suono, quindi una grande combinazione di numeri.
    

Tutto questo, a un certo livello, viene ricondotto a **sequenze finite di numeri interi**, che poi vengono tradotti in **sequenze di bit**.

Per partire in modo rigoroso, cominciamo dai **numeri interi positivi**.

---

### **3. Sistemi di numerazione posizionale**

Per capire il binario, è fondamentale avere ben chiaro come funziona il sistema che usiamo quotidianamente: il **decimale posizionale**.

L’idea generale è sempre la stessa:

> un numero è una **somma pesata** delle sue cifre,  
> e i pesi sono **potenze della base**.

#### **3.1. Notazione decimale (base 10)**

Il sistema decimale:

- usa **10 cifre**: $0,1,2,\dots,9$,
    
- è **posizionale**: la posizione determina il peso,
    
- i pesi sono **potenze di 10**.
    

Esempio del PDF:

$$
(1273)_{10}=1\cdot10^3+2\cdot10^2+7\cdot10^1+3\cdot10^0.
$$

In generale, se $d_i$ sono cifre comprese tra $0$ e $B-1$,

$$
(d_{n-1}\ldots d_1d_0)_B=\sum_{i=0}^{n-1}d_iB^i.
$$
    

Questa logica è ciò che vogliamo **copiare** in altri sistemi di numerazione, cambiando solo:

- la **base** (10 → 2, 16, …),
    
- l’insieme di **cifre ammesse**.
    

---

#### **3.2. Notazione binaria (base 2)**

Nel sistema binario:

- le cifre possibili sono solo **0 e 1**,
    
- la base è **2**,
    
- i pesi sono potenze di 2: $2^0, 2^1, 2^2, \dots$
    

Esempio del PDF:

$$
(10010110)_2=1\cdot2^7+0\cdot2^6+0\cdot2^5+1\cdot2^4+0\cdot2^3+1\cdot2^2+1\cdot2^1+0\cdot2^0.
$$

cioè:

- $1\cdot128$,
- $1\cdot16$,
- $1\cdot4$,
- $1\cdot2$.
    

Somma:  
$$128+16+4+2=150_{10}.$$

Quindi:

$$10010110_2=150_{10}.$$

---

#### **3.3. Notazione esadecimale (base 16)**

L’esadecimale è molto usato in informatica perché si **aggancia bene al binario** (ogni cifra esadecimale = 4 bit).

- Base: **16**
    
- Cifre:
    
    - da $0$ a $9$ con il solito significato,
        
    - poi $A=10$, $B=11$, $C=12$, $D=13$, $E=14$, $F=15$.
        

Esempio del PDF:

$$
(A59F)_{16}=10\cdot16^3+5\cdot16^2+9\cdot16+15=42399_{10}.
$$

Collegamento col binario (4 bit per cifra esadecimale):

- $A_{16}=1010_2$,
- $5_{16}=0101_2$,
- $9_{16}=1001_2$,
- $F_{16}=1111_2$.
    

Quindi:

$$A59F_{16}=1010\ 0101\ 1001\ 1111_2.$$

Il raggruppamento inverso si effettua da destra in gruppi di quattro bit, aggiungendo eventualmente zeri non significativi a sinistra. Per esempio $111010_2=0011\ 1010_2=3A_{16}$.

---

### **4. Quante cifre (o bit) servono per rappresentare un numero?**

Ogni sistema numerico ha due ingredienti:

- una **base** $B$,
    
- un certo numero di **cifre** disponibili.
    

Più c’è base “potente”, meno cifre servono per rappresentare un certo numero.

---

#### **4.1. In decimale**

Con $c$ cifre in base 10 posso rappresentare tutti i numeri interi da:

- minimo: $0$
    
- massimo: $10^c - 1$
    

Esempi:

- con $2$ cifre: da $0$ a $99$ ($10^2 - 1$),
    
- con $3$ cifre: da $0$ a $999$ ($10^3 - 1$), e così via.
    

Se ho un numero $N$ e voglio sapere quante cifre servono per scriverlo in decimale (per $N \ge 1$):

$$  
c = \left\lfloor \log_{10} N \right\rfloor + 1  
$$

Una forma equivalente, valida anche quando $N$ è una potenza esatta della base, è

$$
c=\left\lceil\log_{10}(N+1)\right\rceil.
$$

> ⚠️ La formula $\lceil\log_{10}N\rceil$ riportata nel PDF non è equivalente alla precedente quando $N$ è una potenza di $10$: per $N=1000$ darebbe $3$, ma servono $4$ cifre. Il valore $N=0$ costituisce inoltre un caso separato e richiede una cifra.

Esempio: $N = 512$

- $\log_{10} 512 \approx 2.709$
    
- $\lfloor 2.709 \rfloor = 2$
    
- $c = 2 + 1 = 3$ cifre → infatti $512$ ha 3 cifre.
    

---

#### **4.2. In binario**

Con $b$ bit posso rappresentare tutti i numeri interi da:

- minimo: $0$
    
- massimo: $2^b - 1$
    

Esempi:

- con $3$ bit: $0 \dots 7$ (da $000_2$ a $111_2$),
    
- con $8$ bit: $0 \dots 255$ (da $00000000_2$ a $11111111_2$).
    

Se ho un numero $N$ e voglio sapere quanti bit servono per rappresentarlo in binario (per $N \ge 1$):

$$  
b = \left\lfloor \log_2 N \right\rfloor + 1  
$$

Esempio: $N = 1000$

- $\log_2 1000 \approx 9.97$
    
- $\lfloor 9.97 \rfloor = 9$
    
- $b = 9 + 1 = 10$ bit.
    

Quindi per rappresentare $1000_{10}$ in binario servono **10 bit**.

| $N$ | cifre decimali | bit binari |
| ---: | ---: | ---: |
| $9$ | $1$ | $4$ |
| $99$ | $2$ | $7$ |
| $255$ | $3$ | $8$ |
| $1000$ | $4$ | $10$ |

In modo equivalente, per $N\geq1$,

$$
b=\left\lceil\log_2(N+1)\right\rceil.
$$

Anche qui $\lceil\log_2N\rceil$, forma abbreviata riportata nel PDF, fallisce sulle potenze esatte di due: $N=8$ richiede quattro bit (`1000`), non tre. La formula con $N+1$ oppure $\lfloor\log_2N\rfloor+1$ evita il caso limite.

---

### **5. Conversione tra binario e decimale**

#### **5.1. Da binario a decimale: notazione posizionale**

Metodo:

1. prendi il numero binario,
    
2. assegna l’indice alle cifre da destra verso sinistra ($2^0, 2^1, 2^2, \dots$),
    
3. somma $b_i \cdot 2^i$ per tutte le posizioni.
    

Formula generale:

Se il numero è  
$$b_{n-1} b_{n-2} \dots b_1 b_0$$  
allora:

$$  
(b_{n-1} b_{n-2} \dots b_1 b_0)_2  
= \sum_{i=0}^{n-1} b_i \cdot 2^i  
$$

Esempio breve del PDF:

$$
1011_2=1\cdot8+0\cdot4+1\cdot2+1\cdot1=11_{10}.
$$

Un secondo esempio:

$$  
101101_2 = 1\cdot 2^5 + 0\cdot 2^4 + 1\cdot 2^3 + 1\cdot 2^2 + 0\cdot 2^1 + 1\cdot 2^0 = 45_{10}  
$$

---

#### **5.2. Da decimale a binario: divisione per 2 con raccolta resti**

Metodo:

1. dividi il numero $N$ per 2,
    
2. annota il **resto** (0 o 1),
    
3. sostituisci $N$ con il **quoziente**,
    
4. ripeti finché il quoziente diventa 0,
    
5. il numero binario è dato dai resti letti **dal basso verso l’alto**.
    

Esempio: convertiamo $1017_{10}$ in binario.

Costruiamo la tabella:

- $1017 : 2 = 508$ resto $1$
    
- $508 : 2 = 254$ resto $0$
    
- $254 : 2 = 127$ resto $0$
    
- $127 : 2 = 63$ resto $1$
    
- $63 : 2 = 31$ resto $1$
    
- $31 : 2 = 15$ resto $1$
    
- $15 : 2 = 7$ resto $1$
    
- $7 : 2 = 3$ resto $1$
    
- $3 : 2 = 1$ resto $1$
    
- $1 : 2 = 0$ resto $1$
    

Scrivendo i resti **dal basso verso l’alto**:

$$  
1017_{10} = 1111111001_2  
$$

(Se vuoi puoi verificare rifacendo la somma posizionale.)

---

### **6. Multipli binari: KB, KiB e l’ambiguità storica**

#### **6.1. I prefissi nel Sistema Internazionale (SI)**

Nel SI (fisica, matematica), i prefissi sono **decimali**:

- $1,\text{kilo} = 10^3$,
    
- $1,\text{mega} = 10^6$,
    
- $1,\text{giga} = 10^9$,  
    ecc.
    

Quindi:

- $1,\text{kB} = 10^3,\text{byte}$,
    
- $1,\text{MB} = 10^6,\text{byte}$,
    
- $1,\text{GB} = 10^9,\text{byte}$.
    

---

#### **6.2. L’hardware, però, ragiona in base 2**

Le memorie sono fatte di **celle indirizzabili**, e gli indirizzi sono numeri binari → **potenze di 2**.

Per questo, storicamente, nel mondo dei calcolatori si è usato:

- $1,\text{KB} \approx 2^{10} = 1024,\text{byte}$,
    
- $1,\text{MB} \approx 2^{20}$,
    
- $1,\text{GB} \approx 2^{30}$.
    

Questa è una comodità matematica per l’hardware, ma entra **in conflitto** con le definizioni decimali del SI.

---

#### **6.3. La confusione concreta: l’hard disk “da 500 GB”**

Quando compri un disco da “500 GB”:

- il produttore intende:  
    $$500 \cdot 10^9 \text{ byte}$$
    
- il sistema operativo, invece, spesso mostra la capacità in **potenze di 2** (GiB):
    

$$  
\text{GiB} = \frac{500 \cdot 10^9}{2^{30}} \approx 465  
$$

Ed è per questo che “spariscono” circa $35$ “giga” agli occhi dell’utente.

---

#### **6.4. Le unità IEC: KiB, MiB, GiB**

Per risolvere l’ambiguità, la norma IEC 80000-13 introduce prefissi binari **ufficiali**:

- $1,\text{KiB} = 1024,\text{B}$,
    
- $1,\text{MiB} = 1024,\text{KiB} = 2^{20},\text{B}$,
    
- $1,\text{GiB} = 1024,\text{MiB} = 2^{30},\text{B}$.

| unità IEC | byte |
| --- | ---: |
| $1\ \mathrm{KiB}$ | $2^{10}$ |
| $1\ \mathrm{MiB}$ | $2^{20}$ |
| $1\ \mathrm{GiB}$ | $2^{30}$ |
| $1\ \mathrm{TiB}$ | $2^{40}$ |
| $1\ \mathrm{PiB}$ | $2^{50}$ |
| $1\ \mathrm{EiB}$ | $2^{60}$ |
    

Nella pratica, però, molti continuano a usare “KB/MB/GB” con significato binario.

> 📌 Nel testo del corso, quando il contesto architetturale usa la convenzione storica, `KB`, `MB` e `GB` possono indicare potenze di $2$. Nella scrittura non ambigua si devono invece usare `KiB`, `MiB` e `GiB`:
> 
> - $1,\text{KB} = 1024,\text{B}$
>     
> - $1,\text{MB} = 1024,\text{KB}$
>     
> - $1,\text{GB} = 1024,\text{MB}$
>     

---

### **7. Operazioni aritmetiche in binario**

Le operazioni si fanno **esattamente come in decimale**, ma usando solo 0 e 1.

#### **7.1. Somma binaria**

Regole locali:

- $0 + 0 = 0$
    
- $0 + 1 = 1$
    
- $1 + 0 = 1$
    
- $1 + 1 = 0$ con **riporto di 1**
    

Esempio:

$$
\begin{array}{r}
00110101_2\ (53)\\
+\ 10110001_2\ (177)\\ \hline
11100110_2\ (230)
\end{array}
$$

Si procede da destra verso sinistra propagando i riporti. Per esempio, $1+1=10_2$: si scrive $0$ e si riporta $1$ nella colonna successiva.

Con una parola fissata a $b$ bit, il risultato matematico può richiedere $b+1$ bit. In tal caso il riporto oltre il bit più significativo non entra nella parola: per numeri **senza segno** segnala overflow e il risultato conservato è il resto modulo $2^b$.

---

#### **7.2. Sottrazione binaria**

Regole locali, pensando al prestito:

- $1 - 0 = 1$
    
- $1 - 1 = 0$
    
- $0 - 0 = 0$
    
- $0 - 1$ → serve un **prestito** dalla colonna successiva.
    

In base 10, un prestito vale 10;  
in base 2, un prestito vale **2**, perché stai prendendo 1 unità dalla colonna di sinistra che pesa il doppio.

Esempio concettuale:

- vuoi fare $0 - 1$ in una colonna,
    
- chiedi un prestito alla colonna a sinistra,
    
- quella colonna perde 1 (in binario) e ti “dona” $2$,
    
- diventa quindi $(2 + 0) - 1 = 1$.
    

La logica è **identica** alla sottrazione decimale, ma con base $B=2$.

Esempio del PDF:

$$
10010001_2\ (145)-00110000_2\ (48)=01100001_2\ (97).
$$

Il prestito di una unità dalla posizione $i+1$ aggiunge $2$ alla cifra della posizione $i$, perché $1\cdot2^{i+1}=2\cdot2^i$.

---

#### **7.3. Prodotto binario**

Enorme semplificazione:  
in binario, una cifra è solo 0 o 1, quindi:

- $0 \cdot$ (qualcosa) = 0
    
- $1 \cdot$ (qualcosa) = (qualcosa)
    

Quando moltiplichi due numeri binari:

- ogni riga di prodotto parziale è:
    
    - una copia del moltiplicando (se il bit del moltiplicatore è 1),
        
    - una riga di zeri (se il bit è 0),
        
- poi sommi tutti i prodotti parziali con la **somma binaria**.
    

Quindi la moltiplicazione binaria si riduce a:

- **shift** (spostamenti a sinistra),
    
- **somme**.

Esempio completo:

$$
\begin{array}{r}
1101_2\ (13)\\
\times\ 1001_2\ (9)\\ \hline
1101\\
0000\phantom{0}\\
0000\phantom{00}\\
1101\phantom{000}\\ \hline
1110101_2\ (117)
\end{array}
$$
    

---

### **8. Numeri frazionari in notazione posizionale**

Finora abbiamo visto **solo numeri interi**.  
Ma il computer deve gestire anche numeri con parte frazionaria: $18{,}75$, $0{,}3$, $-127{,}3$, ecc.

La buona notizia:

> la notazione posizionale funziona **anche con la virgola**, sia in base 10 che in base 2.

---

#### **8.1. In decimale**

Esempio:

$$  
127{,}3_{10} = 1\cdot 10^2 + 2\cdot 10^1 + 7\cdot 10^0 + 3\cdot 10^{-1}  
$$

cioè:

- parte intera: $1\cdot 100 + 2\cdot 10 + 7\cdot 1 = 127$
    
- parte frazionaria: $3\cdot 10^{-1} = 0{,}3$
    

---

#### **8.2. In binario**

Esempio:

$$
10010{,}110_2
=1\cdot2^4+1\cdot2^1+1\cdot2^{-1}+1\cdot2^{-2}.
$$

Calcolo:

- parte intera: $16+2=18$
    
- parte frazionaria: $\frac12+\frac14=0{,}75$
    

Quindi:

$$
10010{,}110_2=18{,}75_{10}.
$$

La regola è la stessa:

- a sinistra della virgola → potenze **positive** della base,
    
- a destra della virgola → potenze **negative** della base.
    

---

### **9. Il problema: precisione ed estensione**

Con la **virgola fissa** si decide in anticipo quante posizioni sono dedicate alla parte intera e quante alla parte frazionaria. È una soluzione semplice e prevedibile, ma l’ampiezza dell’intervallo rappresentabile e la distanza fra valori adiacenti sono rigidamente legate a tale scelta.

Per rappresentare numeri reali con:

- **alta precisione** (molte cifre dopo la virgola),
    
- **grande estensione** (numeri molto grandi),
    

in notazione posizionale “semplice” servirebbero:

- tanti bit per la parte intera,
    
- tanti bit per la parte frazionaria,
    

cioè una **parola binaria lunghissima**.

Esempi concettuali:

- $0{,}0000001$ → servono molte potenze negative (molti bit dopo la virgola),
    
- $1{,}000,000,000,000$ → servono molte potenze positive (molti bit prima della virgola).
    

Per gestire **entrambi** i casi con un numero fisso di bit nasce l’idea della **virgola mobile**.

---

### **10. Virgola mobile (floating point) – idea concettuale**

Definizione:

> La **virgola mobile** è un metodo per rappresentare numeri reali in cui la posizione della **virgola** non è fissa, ma viene **codificata tramite un esponente**, come nella notazione scientifica.

Forma generale:

$$  
x = m \cdot b^e  
$$

dove:

- $m$ = **mantissa** (o significando): contiene le cifre significative,
    
- $b$ = **base** (nei computer: $b = 2$),
    
- $e$ = **esponente**: intero (positivo o negativo) che “sposta la virgola”.
    

---

#### **10.1. Esempi in notazione scientifica decimale**

- $450000=4{,}5\cdot10^5$
    
- $0{,}00081=8{,}1\cdot10^{-4}$
    

La mantissa conserva le cifre “importanti”, l’esponente indica l’ordine di grandezza.

---

#### **10.2. Esempio concettuale in binario**

In binario:

- base $b = 2$,
    
- rappresentiamo un numero come:  
    $$x = m \cdot 2^e$$
    

La mantissa è un numero binario “normalizzato”, del tipo:

- $1{,}xxxxx\dots_2$ (la prima cifra diversa da zero è 1).

Per esempio:

$$
1101{,}01_2=1{,}10101_2\cdot2^3.
$$
    

L’esponente dice **dove** posizionare la virgola.

Risultato:

- con un numero **fisso** di bit,  
    posso rappresentare numeri molto piccoli e molto grandi,  
    spostando la scala con l’esponente.
    

> 💡 La virgola mobile amplia enormemente l’intervallo di grandezze rappresentabili, ma non elimina il compromesso: con un numero finito di bit, molte frazioni devono essere arrotondate. Nello standard IEEE 754 esistono inoltre zero, subnormali, infiniti e NaN, quindi la forma normalizzata non descrive tutti i casi.

(Lo standard concreto che troverai più avanti è l’IEEE 754, ma qui ci basta l’idea.)

---

### **11. Numeri relativi: come rappresentare il segno**

Finora abbiamo sempre considerato **numeri positivi**.  
Ma in realtà servono anche valori negativi: temperatura sotto 0, saldo in rosso, spostamenti verso sinistra, ecc.

Idea naturale:

> aggiungere un **bit di segno**.

---

#### **11.1. Rappresentazione con modulo e segno**

Un numero relativo può essere visto come:

$$  
\text{numero} = \text{segno} + \text{modulo}  
$$

In binario:

- il **segno** è una informazione binaria → basta **1 bit**,
    
- il **modulo** è un numero intero positivo rappresentato con i bit rimanenti.
    

Convenzione classica:

- bit di segno $= 0$ → numero **positivo**,
    
- bit di segno $= 1$ → numero **negativo**.
    

Struttura con $b$ bit totali:

- $1$ bit → segno,
    
- $b-1$ bit → modulo.
    

Esempio con $b = 4$:

- bit 3 (il più a sinistra) → segno,
    
- bit 2–1–0 → modulo (0–7).
    

Valori rappresentabili:

- modulo massimo con $b-1$ bit: $2^{b-1} - 1$,
    
- intervallo dei numeri relativi con modulo e segno:
    

$$  
-(2^{b-1}-1), \ \dots, \ -1,\ -0,\ +0,\ 1,\ \dots, \ (2^{b-1}-1)  
$$

| bit totali $b$ | minimo | massimo |
| ---: | ---: | ---: |
| $4$ | $-7$ | $+7$ |
| $8$ | $-127$ | $+127$ |
| $16$ | $-32767$ | $+32767$ |
| $32$ | $-2147483647$ | $+2147483647$ |
| $64$ | $-9223372036854775807$ | $+9223372036854775807$ |

Le $2^b$ configurazioni codificano $2^b-1$ valori distinti, perché $+0$ e $-0$ rappresentano lo stesso numero.

---

#### **11.2. Limiti del modulo e segno**

Questa rappresentazione è **semplice**, ma **non è quella usata nei processori reali**, perché:

1. esistono **due zeri**: $+0$ e $-0$,
    
2. le operazioni aritmetiche sono più complesse da implementare.
    

Per questo, nelle architetture reali, si preferisce la rappresentazione in **complemento a due**, che vedrai in una lezione successiva.

Ma capire modulo e segno è importante perché:

- è il modello concettuale più immediato,
    
- ti fa vedere chiaramente come entra in gioco il **bit di segno**.
    

---

### **12. Sintesi finale della lezione**

In questa lezione hai messo insieme parecchi mattoncini fondamentali:

- i **sistemi di numerazione posizionale** (decimale, binario, esadecimale),
    
- il legame tra **bit**, **byte**, **multipli binari** e capacità di memoria,
    
- come **convertire** tra binario e decimale (posizionale e divisione per 2),
    
- come funzionano le **operazioni aritmetiche** in binario (somma, prestito, prodotto),
    
- come si rappresentano **numeri frazionari** e perché serve la **virgola mobile**,
    
- come si introducono i **numeri relativi** con il modello **modulo e segno**.
    

Da qui in poi, ogni volta che sentirai parlare di:

- **parola di $b$ bit**,
    
- **registri**,
    
- **bus dati**,
    
- **overflow**,
    
- **floating point**,
    
- **complemento a due**,
    

stai in realtà manipolando **codifiche binarie di valori numerici**.

> ✅ Una configurazione di bit non possiede da sola un significato numerico: sono la base, il formato e la larghezza fissata a stabilire valore, intervallo, precisione e condizioni di overflow.
