# **Lezione 2: Struttura della cifratura AES**

---

### **1. Parametri principali dell’AES**

L’AES è un **cifrario a blocchi** con architettura **SP-Network** (_Substitution–Permutation Network_).  
Opera su blocchi di **128 bit**, suddivisi in una **matrice di 4×4 byte** detta **state**.

Ogni blocco è composto da **Nb = 4 word**, ciascuna da **32 bit (4 byte)**.  
La chiave può avere diverse lunghezze:

![](imgs/Pasted%20image%2020260222172644.png)

![](imgs/Pasted%20image%2020260610215234.png)

> Ogni **word** corrisponde a 32 bit = 4 byte.

---

### **2. Spazio delle chiavi e sicurezza**

#### **Numero di chiavi possibili**

- **AES-128:** $2^{128} \approx 3.4 \times 10^{38}$ chiavi  
    → anche testando $2^{55}$ chiavi/s servirebbero **149.000 miliardi di anni** per provarle tutte.
    
- **AES-192:** $2^{192} \approx 6.2 \times 10^{57}$ chiavi
    
- **AES-256:** $2^{256} \approx 1.1 \times 10^{77}$ chiavi
    

➡️ Si stima che **AES resterà sicuro per almeno i prossimi 20 anni**, anche contro attacchi quantistici parziali.

---

### **3. Struttura della chiave**

La chiave è una matrice di byte di **4 righe (perché ogni riga è fissata a una 4byte-word) e Nk colonne**. Abbiamo già visto prima i valori di Nk, ma ripropongo:

Nk = number of 32bit words in the key, e quindi:

$$  
Nk = \frac{\text{lunghezza chiave in bit}}{\text{lunghezza word in bit}} = \frac{\text{lunghezza chiave in bit}}{32}
$$

| Chiave  | Byte totali | Nk  |
| ------- | ----------- | --- |
| 128 bit | 16 byte     | 4   |
| 192 bit | 24 byte     | 6   |
| 256 bit | 32 byte     | 8   |

Ergo, per AES 128 avremo:

![](imgs/Pasted%20image%2020260222173029.png)

**Esempio di chiave (128 bit):**

$$  
\begin{bmatrix}  
K_{0,0} & K_{0,1} & K_{0,2} & K_{0,3} \\  
K_{1,0} & K_{1,1} & K_{1,2} & K_{1,3} \\  
K_{2,0} & K_{2,1} & K_{2,2} & K_{2,3} \\  
K_{3,0} & K_{3,1} & K_{3,2} & K_{3,3}  
\end{bmatrix}  
$$

LA CHIAVE VEDE I PRIMI 4 BYTE NELLA 1A COLONNA, I SECONDI 4 NELLA SECONDA ETC...

---

### **4. Blocco di input e matrice di stato**

Il **blocco di testo in chiaro** (128 bit = 16 byte) è rappresentato anch’esso come una matrice 4×Nb ergo 4x4

![](imgs/Pasted%20image%2020260222173130.png)

$$  
\text{State} =  
\begin{bmatrix}  
S_{0,0} & S_{0,1} & S_{0,2} & S_{0,3} \\  
S_{1,0} & S_{1,1} & S_{1,2} & S_{1,3} \\  
S_{2,0} & S_{2,1} & S_{2,2} & S_{2,3} \\  
S_{3,0} & S_{3,1} & S_{3,2} & S_{3,3}  
\end{bmatrix}  
$$

Ogni colonna rappresenta una **word** (32 bit). QUINDI I BYTE SONO ORDINATI COLONNA X COLONNA!

L’input viene copiato nella **matrice di stato**, su cui avvengono tutte le operazioni.  
Al termine della cifratura, la **matrice di stato finale** viene copiata nel **blocco di output** (testo cifrato).

$$
\begin{aligned}
S_{r,c} &\leftarrow in_{r+4c} \\\\
out_{r+4c} &\leftarrow S_{r,c}
\end{aligned}
$$

dove:  
$$  
0 \le r < 4, \quad 0 \le c < Nb  
$$

![](imgs/Pasted%20image%2020260222173403.png)

**Notazione `r + 4c`** AES lavora internamente con una matrice di stato 4×4, ma i byte in ingresso e in uscita sono **array lineari**. Nel diagramma input e output appaiono come matrici 4×4, ma è solo una rappresentazione visiva per mostrare la corrispondenza con la state matrix — la necessità stessa della formula `r + 4c` conferma che sotto sono flat array. La formula converte una posizione bidimensionale (riga `r`, colonna `c`) in un indice lineare, perché AES riempie la matrice **per colonne**: prima scorre tutta la colonna 0 (indici 0–3), poi la colonna 1 (4–7), e così via. Per raggiungere la cella `[r, c]` bisogna quindi saltare `c` colonne intere (4 byte ciascuna) più `r` righe → `r + 4c`.

---

### **5. Rappresentazione dei byte**

Nell’AES, il **byte** è l’unità di base su cui avvengono le operazioni.  
Ogni byte è rappresentato come un **polinomio di grado ≤ 7** con coefficienti binari (0 o 1):

$$  
\{b_7b_6b_5b_4b_3b_2b_1b_0\}  
\longleftrightarrow  
b_7x^7 + b_6x^6 + b_5x^5 + b_4x^4 + b_3x^3 + b_2x^2 + b_1x + b_0  
$$

Esempio:  
$$  
\{11010100\} \rightarrow x^7 + x^6 + x^4 + x^2 + 1 = \{d4\}_{16}  
$$

I valori dei byte sono quindi espressi in **esadecimale** (da 00 a FF).

---

### **6. Operazioni sui byte nel campo finito GF(2⁸)**

**Ripasso: campi finiti e GF(2⁸)** Un **campo finito** (o campo di Galois) è un insieme con un numero finito di elementi in cui sono definite addizione e moltiplicazione, e ogni elemento non nullo ha il suo inverso — le operazioni non "escono mai" dall'insieme. Il caso più semplice è GF(2) = {0, 1}, dove l'addizione è XOR e la moltiplicazione è AND. AES lavora però su byte, quindi serve un campo con 2⁸ = **256 elementi**: GF(2⁸). Gli elementi non sono interi normali da 0 a 255, ma **polinomi a coefficienti in {0,1}** di grado al massimo 7 — ogni bit del byte rappresenta un coefficiente. Ad esempio `0x57` = `01010111` corrisponde al polinomio x⁶ + x⁴ + x² + x + 1. Le operazioni si fanno su questi polinomi **modulo un polinomio irriducibile** fisso (in AES è x⁸ + x⁴ + x³ + x + 1), che garantisce che il risultato stia sempre dentro i 256 elementi del campo — esattamente come il modulo in aritmetica classica tiene i numeri dentro un certo range.

Un polinomio irriducibile è un polinomio che non può essere fattorizzato in polinomi di grado inferiore con coefficienti nel campo.

Esempio in $GF(2)$:

$x^2 + x = x(x+1)$ → riducibile, si fattorizza

$x^2 + x + 1$ → irriducibile, non ha radici in $\{0,1\}$ e non si fattorizza

Come verifichi che non ha radici? Provi tutti gli elementi del campo:

$x=0 \rightarrow 0 + 0 + 1 = 1 \neq 0$
$x=1 \rightarrow 1 + 1 + 1 = 1 \neq 0$ (perché $2 \mod 2 = 0$)

Nessuna radice → irriducibile.

![](imgs/Pasted%20image%2020260610143716.png)

![](imgs/Pasted%20image%2020260610143735.png)

![](imgs/Pasted%20image%2020260610144423.png)

![](imgs/Pasted%20image%2020260610143753.png)

---

L’AES esegue le sue operazioni nel Galois Field **campo finito $GF(2^8)$**, che contiene 256 elementi.

#### **Addizione**

È definita come la **somma modulo 2 dei coefficienti** (cioè XOR bit a bit):

$$  
\{57\} \oplus \{83\} = \{d4\}  
$$

In aritmetica normale `1 + 1 = 2`, c'è riporto e esci dall'insieme. In GF(2) `1 + 1 = 0` — il riporto non esiste perché i coefficienti sono modulo 2, e XOR cattura esattamente questo: due bit uguali si annullano.

_Conseguenza diretta: in GF(2⁸) non esistono riporti — sommare due byte è letteralmente uno XOR bit a bit, perché ogni coefficiente del polinomio è modulo 2 e non "trabocca" mai sul bit successivo._

---

In forma polinomiale, la somma di poco fa diventa:

$$  
( x^6 + x^4 + x^2 + x + 1 ) \oplus ( x^7 + x + 1 ) = x^7 + x^6 + x^4 + x^2  
$$

---

#### **Moltiplicazione**

La moltiplicazione tra due elementi di $GF(2^8)$ corrisponde alla **moltiplicazione dei polinomi modulo un polinomio irriducibile** di grado 8 (ce ne sono 30, e in aritmetica modulare sono l'analogo dei numeri primi in aritmetica classica).

Il polinomio irriducibile scelto da AES è:

$$  
m(x) = x^8 + x^4 + x^3 + x + 1  
$$

Esempio:  
$$  
\{57\} \cdot \{83\} = \{c1\}  
$$

---

##### **Algoritmo generico di moltiplicazione in GF(2⁸)**

Per calcolare $a(x) \cdot b(x) \bmod m(x)$ in $GF(2^8)$:

**Fase 1 — Moltiplicazione polinomiale**

Moltiplica $a(x)$ e $b(x)$ nel modo classico, ma con **coefficienti modulo 2** (XOR, niente riporti). Il risultato $p(x)$ può avere dunque un grado fino a 14.

**Fase 2 — Riduzione iterativa**

Finché $\deg(p) \geq 8$, individua il termine di grado massimo $x^k$ ($k \geq 8$) e applica:

$$p(x) \;\leftarrow\; p(x) \oplus x^{k-8} \cdot m(x)$$

Poiché $x^{k-8} \cdot m(x)$ ha esattamente grado $k$, lo XOR annulla $x^k$ e introduce solo termini di grado inferiore. Ripetere finché $\deg(p) < 8$.

> 💡 In GF(2), $m(x) = 0$ per definizione del campo, quindi $x^8 = x^4 + x^3 + x + 1$. Ogni riduzione "abbassa" il grado sostituendo $x^k$ con $x^{k-8}(x^4 + x^3 + x + 1)$.

---

##### **Esecuzione su $\{57\} \cdot \{83\}$**

**Conversione in polinomi:**

$$\{57\}_{16} = 0101\;0111_2 \;\longrightarrow\; x^6 + x^4 + x^2 + x + 1$$

$$\{83\}_{16} = 1000\;0011_2 \;\longrightarrow\; x^7 + x + 1$$

**Fase 1 — Prodotto:**

Sviluppo $(x^6 + x^4 + x^2 + x + 1)(x^7 + x + 1)$:

| $a(x)$ | $\times\; x^7$ | $\times\; x$ | $\times\; 1$ |
|--------|---------------|-------------|-------------|
| $x^6$  | $x^{13}$      | $x^7$       | $x^6$       |
| $x^4$  | $x^{11}$      | $x^5$       | $x^4$       |
| $x^2$  | $x^9$         | $x^3$       | $x^2$       |
| $x$    | $x^8$         | $x^2$       | $x$         |
| $1$    | $x^7$         | $x$         | $1$         |

Raccogliendo e annullando i gradi duplicati (mod 2: $x^7 \oplus x^7 = 0$, $x^2 \oplus x^2 = 0$, $x \oplus x = 0$):
	
$$p(x) = x^{13} + x^{11} + x^9 + x^8 + x^6 + x^5 + x^4 + x^3 + 1$$

**Fase 2 — Riduzione mod $m(x) = x^8 + x^4 + x^3 + x + 1$:**

**Passo 1** — $\deg = 13$, uso $x^5 \cdot m(x) = x^{13} + x^9 + x^8 + x^6 + x^5$:

$$\bigl(x^{13} + x^{11} + x^9 + x^8 + x^6 + x^5 + x^4 + x^3 + 1\bigr)$$
$$\oplus \bigl(x^{13} + x^9 + x^8 + x^6 + x^5\bigr)$$
$$= x^{11} + x^4 + x^3 + 1$$

**Passo 2** — $\deg = 11$, uso $x^3 \cdot m(x) = x^{11} + x^7 + x^6 + x^4 + x^3$:

$$\bigl(x^{11} + x^4 + x^3 + 1\bigr) \oplus \bigl(x^{11} + x^7 + x^6 + x^4 + x^3\bigr) = x^7 + x^6 + 1$$

$\deg = 7 < 8$ → riduzione completata.

**Risultato:**

$$x^7 + x^6 + 1 = 1100\;0001_2 = \{c1\}_{16} \;\checkmark$$

> ✅ Due passi di riduzione bastano: il prodotto di due byte (grado ≤ 7) ha grado ≤ 14, quindi occorrono al più 7 riduzioni (una per ogni grado da 14 a 8), ma nella pratica spesso bastano 1–3 passi come in questo esempio.

---

![](imgs/Pasted%20image%2020260222174241.png)

Le operazioni di moltiplicazione sono associative e ogni elemento (tranne 0) ha un **inverso moltiplicativo**.

---

### **7. Polinomi con coefficienti in GF(2⁸)**

Una **word** da 4 byte, $[a_0, a_1, a_2, a_3]$, può essere interpretata come un **polinomio di grado ≤ 3** con coefficienti in $GF(2^8)$:

$$  
a(x) = a_3x^3 + a_2x^2 + a_1x + a_0  
$$

Analogamente per un’altra word:  
$$  
b(x) = b_3x^3 + b_2x^2 + b_1x + b_0  
$$

Le **addizioni** e **moltiplicazioni** tra word avvengono modulo $x^4 + 1$ (vedere appendice seguente per capirne il motivo).

Partiamo dall'addizione, che è molto più easy e concisa, senza bisogno di rimaneggiamenti complessi:

$$  
a(x)+b(x) = (a_3 \oplus b_3) +(a_2 \oplus b_2) +(a_1 \oplus b_1) +(a_0 \oplus b_0)
$$

#### **7.1. Calcolo dei coefficienti $c_i$: la convoluzione polinomiale**

Il prodotto di due polinomi di grado $\leq 3$ è in generale un polinomio di grado $\leq 6$:

$$
c(x) = a(x) \cdot b(x) \mod (x^4+1) = c_6x^6 + c_5x^5 + c_4x^4 + c_3x^3 + c_2x^2 + c_1x + c_0
$$

Ogni coefficiente $c_i$ si ottiene **sommando (XOR) tutte le coppie di fattori $a_j \cdot b_k$ tali che $j + k = i$**:

$$
c_i = \bigoplus_{j+k=i} a_j \cdot b_k
$$

In soldoni, otterremo:

$$
\begin{aligned}
c_0 &= a_0 \cdot b_0 \\\\
c_1 &= (a_1 \cdot b_0) \oplus (a_0 \cdot b_1) \\\\
c_2 &= (a_2 \cdot b_0) \oplus (a_1 \cdot b_1) \oplus (a_0 \cdot b_2) \\\\
c_3 &= (a_3 \cdot b_0) \oplus (a_2 \cdot b_1) \oplus (a_1 \cdot b_2) \oplus (a_0 \cdot b_3) \\\\
c_4 &= (a_3 \cdot b_1) \oplus (a_2 \cdot b_2) \oplus (a_1 \cdot b_3) \\\\
c_5 &= (a_3 \cdot b_2) \oplus (a_2 \cdot b_3) \\\\
c_6 &= a_3 \cdot b_3
\end{aligned}
$$

Questa è la classica **convoluzione** di due sequenze finanziate — lo stesso meccanismo che si usa per moltiplicare manualmente due numeri cifra per cifra. Ogni prodotto $a_j \cdot b_k$ qui va inteso come **moltiplicazione in $GF(2^8)$** (cioè con il suo polinomio irriducibile $m(x)$), non come moltiplicazione intera.

---

> 📌 **Perché il modulo $x^4 + 1$?**
> Il modulo si sceglie di grado 4 perché le word hanno esattamente 4 byte (4 coefficienti, gradi 0–3): per restare all'interno dello spazio a 4 coefficienti, occorre ridurre il polinomio prodotto (grado ≤ 6) a grado ≤ 3, e ciò richiede un modulo di grado **4**.
> Il +1 invece non è arbitrario: con $x^4 + 1$ si ottiene la regola di riduzione $x^4 \equiv -1 \equiv 1$ in $GF(2)$ (poiché in caratteristica 2, $-1 = 1$), quindi $x^{4+k} \equiv x^k$ (moltiplicando ad ambi i membri per $x^k$). Questo significa che i termini di grado 4, 5, 6 **si ripiegano semplicemente** sulle posizioni 0, 1, 2, rendendo la riduzione banale: $d_i = c_i \oplus c_{i+4}$. Ovvero, all'atto pratico...

Applicando la riduzione modulo $x^4 + 1$ (con $x^4 \equiv 1$):

$$
d_0 = c_0 \oplus c_4, \quad d_1 = c_1 \oplus c_5, \quad d_2 = c_2 \oplus c_6, \quad d_3 = c_3
$$

> **Nota:** $x^4+1$ non è irriducibile su $GF(2)$ (si fattorizza come $(x+1)^4$), quindi l'algebra sulle word forma un **anello**, non un campo — ma per MixColumns è sufficiente.

---

Ora che abbiamo le equazioni dei 4  $d_i$, ci basta riscriverli sostituendo i vari $c_i$ per come li abbiamo calcolati prima.

Quello che otteniamo è il seguente sistema:

$$
\begin{cases}
d_0 = (a_0 \cdot b_0) \oplus (a_3 \cdot b_1) \oplus (a_2 \cdot b_2) \oplus (a_1 \cdot b_3) \\\\
d_1 = (a_1 \cdot b_0) \oplus (a_0 \cdot b_1) \oplus (a_3 \cdot b_2) \oplus (a_2 \cdot b_3) \\\\
d_2 = (a_2 \cdot b_0) \oplus (a_1 \cdot b_1) \oplus (a_0 \cdot b_2) \oplus (a_3 \cdot b_3) \\\\
d_3 = (a_3 \cdot b_0) \oplus (a_2 \cdot b_1) \oplus (a_1 \cdot b_2) \oplus (a_0 \cdot b_3)
\end{cases}
$$

#### **7.2. Dalla formula al sistema: la matrice circolante**

Il sistema nei $d_i$ ha una struttura che si può leggere in modo algoritmico. Fissata una riga, ad esempio:

$$
d_0 = (a_0 \cdot b_0) \oplus (a_3 \cdot b_1) \oplus (a_2 \cdot b_2) \oplus (a_1 \cdot b_3)
$$

i pedici di $a$ che compaiono sono, nell'ordine delle colonne $b_0, b_1, b_2, b_3$: $0, 3, 2, 1$. Nella riga successiva ($d_1$) diventano $1, 0, 3, 2$ — uno **shift ciclico** di 1. Ogni riga è la precedente shiftata a destra di una posizione.

Questo pattern è quello di una **matrice circolante**: una matrice in cui la prima riga definisce tutto e le successive si ottengono per rotazione. Il risultato è:

$$
\begin{bmatrix} d_0 \\ d_1 \\ d_2 \\ d_3 \end{bmatrix}
=
\begin{bmatrix}
a_0 & a_3 & a_2 & a_1 \\
a_1 & a_0 & a_3 & a_2 \\
a_2 & a_1 & a_0 & a_3 \\
a_3 & a_2 & a_1 & a_0
\end{bmatrix}
\begin{bmatrix} b_0 \\ b_1 \\ b_2 \\ b_3 \end{bmatrix}
$$

> 💡 **Come costruire la matrice senza memorizzarla**: la prima colonna (o riga) è sempre $[a_0, a_1, a_2, a_3]^T$; ogni riga successiva è lo shift ciclico verso destra della precedente. Non serve ricordarsi la matrice — basta conoscere la regola di shift.

> ✅ Il vantaggio pratico è enorme: MixColumns si riduce a **moltiplicare un vettore di byte per una matrice circolante**, operazione parallelizzabile ed efficiente in hardware.

---

### **8. Implicazioni della matrice + $x^4+1$**

Prima di procedere, è necessario chiarire il collegamento tra i due livelli di aritmetica che abbiamo visto:

> 📌 I prodotti $a_j \cdot b_k$ che compaiono nel sistema dei $d_i$ **sono moltiplicazioni in $GF(2^8)$** — cioè esattamente l’aritmetica byte-level della sezione 6. I due livelli si annidano: i coefficienti del polinomio-word sono byte di $GF(2^8)$, e la loro moltiplicazione scalare usa l’aritmetica di $GF(2^8)$. L’anello $GF(2^8)[x]/(x^4+1)$ vive quindi sopra $GF(2^8)$.

Detto questo, rimane un problema strutturale: $x^4+1$ **non è irriducibile** su $GF(2)$ — si fattorizza come $(x+1)^4$. Questo significa che $GF(2^8)[x]/(x^4+1)$ è un **anello**, non un campo, e in un anello **non tutti gli elementi hanno inverso moltiplicativo**: esistono divisori di zero.

La condizione esatta perché un polinomio $a(x) \in GF(2^8)[x]$ abbia inverso in questo anello è:

$$
\operatorname{mcd}(a(x),\ x^4+1) = 1 \quad \text{in } GF(2^8)[x]
$$

Solo i polinomi **coprimi** con $x^4+1$ sono invertibili. Questo vincolo diventa concreto quando, nella prossima lezione, incontreremo una delle fasi di cifratura di AES chiamata **MixColumns**: essa opera su ogni colonna della matrice di stato (che è esattamente una word da 4 byte) moltiplicandola — nell'anello $GF(2^8)[x]/(x^4+1)$ — per un polinomio fisso $a(x)$. Poiché AES deve essere invertibile (serve la decifratura), quel polinomio $a(x)$ deve avere un inverso nell'anello, e quindi deve soddisfare la condizione di coprimalità. AES sceglie:

$$
a(x) = \{03\}x^3 + \{01\}x^2 + \{01\}x + \{02\}
$$

$$
a^{-1}(x) = \{0b\}x^3 + \{0d\}x^2 + \{09\}x + \{0e\}
$$

> 💡 Questi polinomi soddisfano $\operatorname{mcd}(a(x), x^4+1) = 1$ in $GF(2^8)[x]$, garantendo l’invertibilità. Sono stati inoltre scelti perché massimizzano la **diffusione dei bit**: la matrice circolante che inducono ha **branch number = 5** (il massimo possibile per una trasformazione 4×4), il che significa che ogni bit di output dipende da tutti e 4 i byte di input — effetto valanga ottimale.

---

### **9. Proprietà riassuntive**

- AES opera su **byte e word (4 byte)**.
    
- Tutte le trasformazioni avvengono sulla **matrice di stato 4×4**.
    
- Ogni byte è un elemento di **$GF(2^8)$**, rappresentato come un polinomio binario.
    
- Ogni word è un polinomio di **grado < 4** con coefficienti in **$GF(2^8)$**.
    
- Tutte le moltiplicazioni sono eseguite **modulo $x^8 + x^4 + x^3 + x + 1$**.
    

---

### **10. Sintesi finale**

In questa lezione abbiamo:

- definito i **parametri fondamentali** dell’AES (Nk, Nb, Nr);
    
- introdotto la rappresentazione dei **byte** e delle **word**;
    
- descritto le operazioni nel campo finito $GF(2^8)$;
    
- visto come le **moltiplicazioni e addizioni** vengano eseguite a livello di polinomi.
    

👉 Nella prossima lezione analizzeremo nel dettaglio le **operazioni interne di ogni round**:  
**SubBytes, ShiftRows, MixColumns e AddRoundKey.**