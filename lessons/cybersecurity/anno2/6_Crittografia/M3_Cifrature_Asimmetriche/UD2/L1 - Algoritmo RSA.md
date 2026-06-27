# **Lezione 1: Algoritmo RSA**

### **1. Introduzione e contesto storico**

L’**algoritmo RSA** è il più celebre schema di **cifratura a chiave pubblica**.  
È stato proposto nel **1977** da **Rivest, Shamir e Adleman** del **MIT**, dai cui cognomi deriva il nome **RSA**.

RSA rappresenta una pietra miliare della **crittografia moderna**, perché consente di:

- cifrare e decifrare messaggi senza condividere una chiave segreta,
- firmare digitalmente documenti e messaggi,
- scambiare in sicurezza chiavi per cifrature simmetriche.

---

### **2. Fondamento matematico**

L’algoritmo RSA è basato su operazioni di **esponenziazione modulare** in un **campo finito**:

$$
C \equiv M^e \pmod{n}
$$

dove:

- $M$ è il messaggio da cifrare (convertito in un intero),
- $e$ è l’esponente di cifratura,
- $n$ è il modulo, cioè il prodotto di due numeri primi $p$ e $q$.

L’esponenziazione richiede circa $O((\log n)^3)$ operazioni,  
mentre la **sicurezza** dipende dalla **difficoltà della fattorizzazione** di grandi numeri $n = p \cdot q$,  
che è un problema computazionalmente **duro**, stimato in $O(e^{\sqrt{\log n \cdot \log(\log n)}})$ operazioni.

Non preoccupatevi di questi valori, verranno spiegati con calma ma vanno subito menzionati per dare un’idea di ciò che ci aspetta con RSA a 2048 bit, che è lo standard attuale per la sicurezza.

---

### Approfondimento: Complessità della fattorizzazione in RSA

#### Da dove viene $e^{\sqrt{\log n \cdot \log( \log n)}}$

Per fattorizzare i numeri enormi che compongono le chiavi RSA, si utilizza il **Crivello Quadratico** (Quadratic Sieve), che è l’algoritmo più veloce conosciuto per fattorizzare numeri grandi.

Il crivello quadratico fattorizza $n$ cercando numeri **B-smooth**, ovvero numeri i cui fattori primi sono tutti minori di $B$.

$B$ è un parametro scelto in base alla dimensione di $n$ per bilanciare le sue due fasi.

Queste ultime hanno costi opposti: la fase di setacciatura costa circa $e^{\log n / \log B}$ (diminuisce al crescere di $B$), mentre la fase di algebra lineare costa circa $B$ (aumenta al crescere di $B$). Il valore ottimale di $B$ si ottiene bilanciando i due costi, imponendo $e^{\log n / \log B} \approx B$: risolvendo questa equazione si ottiene $B^* = e^{\sqrt{\log n}}$. Tenendo conto della densità reale dei numeri smooth (funzione di Dickman), la stima si raffina in $B^* = e^{\sqrt{\log n \cdot \log \log n}}$, da cui la complessità finale del crivello. Ecco spiegata la complessità di fattorizzazione (è hard, a patto che $n$ sia abbastanza grande - nel 2026 si prevede che 1024 bit non siano più sufficienti, mentre 2048 bit lo saranno ancora per qualche anno).

---

#### Calcolo concreto per $n$ a 2048 bit

**Dati di partenza:**

$$n \approx 2^{2048}$$

**Step 1 — calcola $\log n$ (logaritmo naturale):**

$$\log n = 2048 \cdot \log 2 \approx 2048 \times 0.693 \approx 1419$$

**Step 2 — calcola $\log \log n$:**

$$\log(\log n) = \log(1419) \approx 7.26$$

**Step 3 — moltiplica e prendi la radice:**

$$\sqrt{\log n \cdot \log \log n} = \sqrt{1419 \times 7.26} = \sqrt{10302} \approx 101.5$$

**Step 4 — calcola la complessità:**

$$e^{101.5} \approx 10^{44}$$

**Confronto:**

| Quantità                                   | Ordine di grandezza |
| ------------------------------------------ | ------------------- |
| Operazioni per fattorizzare $n$ a 2048 bit | $\approx 10^{44}$   |
| Atomi nell'universo osservabile            | $\approx 10^{80}$   |
| Età dell'universo in nanosecondi           | $\approx 10^{27}$   |

$10^{44}$ è già abbondantemente fuori dalla portata di qualunque computer fisicamente realizzabile: anche usando ogni atomo dell'universo come unità computazionale, non basterebbe. Ecco perché RSA a 2048 bit è considerato sicuro.

---

### **3. Generazione delle chiavi RSA**

Ogni utente genera la propria coppia di chiavi seguendo questi passaggi:

![](imgs/telegram-cloud-photo-size-4-5859653904214200187-x.jpg)

1. Si scelgono **due numeri primi grandi** $p$ e $q$.
2. Si calcola il **modulo**:
   $$
   n = p \cdot q
   $$
3. Si calcola **φ(n)** (funzione di Eulero):
   $$
   \varphi(n) = (p-1)(q-1)
   $$
4. Si sceglie un **esponente di cifratura $e$** tale che:

   $$
   \operatorname{mcd}(e, \varphi(n)) = 1
   $$

   Questo primo vincolo di coprimità ci garantisce l'inverso moltiplicativo di $e$ modulo $\varphi(n)$, che sarà l’esponente di decifratura $d$.
   Ma non è l’unico vincolo: infatti $e$ deve essere **minore di $\varphi(n)$** e **maggiore di 1**, perché $e = 1$ sarebbe banalmente insicuro ($C = M^1 = M$) e $e \geq \varphi(n)$ non avrebbe senso come elemento di $\mathbb{Z}_{\varphi(n)}$.

   > ⚠️ RSA richiede anche che il messaggio $M$ soddisfi $M < n$: la cifratura calcola $M^e \bmod n$, quindi se $M \geq n$ si perde informazione (si lavora già sulla classe di equivalenza $M \bmod n$). Quando il messaggio supera $n$, si spezza in **blocchi** ciascuno di dimensione inferiore a $n$ e si cifra ogni blocco separatamente.

5. Si calcola l’**esponente di decifratura $d$** come inverso moltiplicativo di $e$ modulo $\varphi(n)$:
   $$
   e \cdot d \equiv 1 \pmod{\varphi(n)}
   $$

**Chiave pubblica:** $(n, e)$  
**Chiave privata:** $(n, d)$

Vedremo in avanti che è possibile dimostrare che esiste sempre $d$ che soddisfa la relazione di inverso.

---

### **4. Cifratura e decifratura**

#### **Cifratura**

Quando Bob vuole inviare un messaggio $M$ ad Alice:

1. Recupera la chiave pubblica di Alice $(n, e)$.
2. Calcola il testo cifrato:
   $$
   C = M^e \bmod n
   $$

![](imgs/telegram-cloud-photo-size-4-5859653904214200190-y.jpg)

#### **Decifratura**

Quando Alice riceve $C$, usa la propria chiave privata $(n, d)$ per ottenere:

$$
M = C^d \bmod n
$$

Solo Alice può decifrare, perché solo lei conosce $d$.

![](imgs/telegram-cloud-photo-size-4-5859653904214200191-y.jpg)

---

### **5. Esempio 1**

![](imgs/telegram-cloud-photo-size-4-5859653904214200192-y.jpg)

#### **Dati**

- $p = 47$, $q = 71$
- $n = 47 \cdot 71 = 3337$
- $\varphi(n) = (46)(70) = 3220$

Scelta:

$$
e = 79,\quad \operatorname{mcd}(79, 3220) = 1
$$

Come calcolare velocemente il operatorname{mcd} per verificare la coprimalità? Si può usare l’**algoritmo di Euclide**. Ma in sede d'esame si può anche dare per garantito che il prof abbia scelto $p$ e $q$ tali che $e$ sia coprimo con $\varphi(n)$ per "costruzione". In poche parole computiamo e basta.

Calcolo di $d$:

$$
79 \cdot 1019 \equiv 1 \pmod{3220}
$$

Qui abbiamo trovato che $d = 1019$. Il calcolo può essere fatto con l’**algoritmo di Euclide esteso**, che permette di trovare l’inverso moltiplicativo di $e$ modulo $\varphi(n)$.

Ecco il calcolo passo passo:

> 💡 Ad ogni passaggio si divide il numero maggiore per il minore e si tiene il resto. Si va avanti finché il resto non è $0$: l'ultimo resto non-nullo è il MCD.

$$
\begin{aligned}
3220 &= 79 \cdot 40 + 60 \\\\
79 &= 60 \cdot 1 + 19 \\\\
60 &= 19 \cdot 3 + 3 \\\\
19 &= 3 \cdot 6 + 1 \\\\
3 &= 1 \cdot 3 + 0
\end{aligned}
$$

Scritto in forma più leggibile:

$$
\begin{aligned}
3220 / 79 &= 40 \text{ resto } 60 \\\\
79 / 60 &= 1 \text{ resto } 19 \\\\
60 / 19 &= 3 \text{ resto } 3 \\\\
19 / 3 &= 6 \text{ resto } 1 \\\\
3 / 1 &= 3 \text{ resto } 0
\end{aligned}
$$

Il MCD è $1$ (ultimo resto non-nullo), confermando che $79$ è invertibile mod $3220$.

##### **Back-substitution: ricavare $d = 1019$**

Si risale dal basso verso l'alto esprimendo $1$ come combinazione lineare di $79$ e $3220$.

**Passo 1** — dalla riga $19 = 3 \cdot 6 + 1$, isola $1$:
$$1 = 19 - 3 \cdot 6$$

**Passo 2** — sostituisci $3 = 60 - 19 \cdot 3$ (dalla riga precedente):
$$1 = 19 - (60 - 19 \cdot 3) \cdot 6 = 19 \cdot 19 - 60 \cdot 6$$

**Passo 3** — sostituisci $19 = 79 - 60 \cdot 1$:
$$1 = 19 \cdot (79 - 60) - 60 \cdot 6 = 19 \cdot 79 - 19 \cdot 60 - 60 \cdot 6 = 19 \cdot 79 - 60 \cdot 25$$

**Passo 4** — sostituisci $60 = 3220 - 79 \cdot 40$:
$$1 = 19 \cdot 79 - (3220 - 79 \cdot 40) \cdot 25 = 19 \cdot 79 + 1000 \cdot 79 - 25 \cdot 3220$$

$$\boxed{1 = 1019 \cdot 79 - 25 \cdot 3220}$$

Riducendo modulo $3220$, il termine $-25 \cdot 3220 \equiv 0$, quindi:

$$79 \cdot 1019 \equiv 1 \pmod{3220} \implies d = 1019$$

Dunque:

- **Chiave pubblica:** $(n=3337, e=79)$
- **Chiave privata:** $(n=3337, d=1019)$

---

> 📌 **Promemoria — esponenziazione modulare (square and multiply)**
>
> Davanti a $M^e \bmod n$ con $e$ enorme, **non** si calcola la potenza intera. Si usa la proprietà che caratterizza il prodotto in aritmetica modulare, declinata per quando i fattori sono uguali e quindi si eleva a potenza:
>
> $$a \cdot b \bmod n = (a \bmod n) \cdot (b \bmod n) \bmod n$$
>
> Da cui segue che, se $a = b$:
>
> $$a^2 \bmod n = (a \bmod n)^2 \bmod n$$
>
> L'algoritmo che si usa per calcolare $M^e \bmod n$ si chiama **square and multiply** e exploita la rappresentazione binaria di $e$:
>
> Se infatti scriviamo $e$ in binario, possiamo calcolare $M^e$ come prodotto di potenze di $M$ corrispondenti ai bit a 1 della rappresentazione binaria di $e$. Per fare ciò ci basta una variabile $r$ che inizialmente vale $1$ e che viene aggiornata per ogni bit di $e$:
>
> - per ogni bit: $r \leftarrow r^2 \bmod n$
> - se il bit è $1$: $r \leftarrow r \cdot M \bmod n$

**Esempio — $688^{79} \bmod 3337$:** $79 = (1001111)_2$, bit da sinistra: $1,0,0,1,1,1,1$.

| Bit | $r^2 \bmod 3337$ | $\times 688 \bmod 3337$ (se bit=1) | $r$             |
| --- | ---------------- | ---------------------------------- | --------------- |
| 1   | $1$              | $688$                              | $688$           |
| 0   | $2827$           | —                                  | $2827$          |
| 0   | $3270$           | —                                  | $3270$          |
| 1   | $2540$           | $1570$                             | $1570$          |
| 1   | $1083$           | $1601$                             | $1601$          |
| 1   | $2727$           | $2539$                             | $2539$          |
| 1   | $2899$           | $\mathbf{1570}$                    | $\mathbf{1570}$ |

> ⚠️ Il docente in sede d'esame userà numeri nettamente più semplici cosicché gli studenti con la sola calcolatrice riescano a fare i calcoli senza problemi. In questo esempio, il docente ha scelto numeri più grandi per mostrare come funziona l’algoritmo, ma in sede d’esame non si useranno numeri così grandi, perché anche solo fare 2827^2 e poi continuare a premere iterativamente -3337 sulla calcolatrice sarebbe troppo dispendioso (provare per credere).

#### **Cifratura**

$$
C = 688^{79} \bmod 3337 = 1570
$$

![](imgs/telegram-cloud-photo-size-4-5861905704027885195-y.jpg)

#### **Decifratura**

$$
M = 1570^{1019} \bmod 3337 = 688
$$

![](imgs/telegram-cloud-photo-size-4-5861905704027885197-y.jpg)

✅ Il messaggio originale è recuperato correttamente.

---

### **6. Esempio 2 - il problema con mess > n**

![](imgs/telegram-cloud-photo-size-4-5861905704027885200-y.jpg)

#### **Dati**

- $p = 11$, $q = 5$
- $n = 11 \cdot 5 = 55$
- $\varphi(n) = (10)(4) = 40$
- Scelta $e = 3$, $\operatorname{mcd}(3, 40) = 1$
- Calcolo $d = 27$, perché:
  $$
  3 \cdot 27 \equiv 1 \pmod{40}
  $$

**Chiavi:**

- Pubblica: $(n=55, e=3)$
- Privata: $(n=55, d=27)$

#### **Cifratura**

$$
C = 5718^{3} \bmod 55 = 47
$$

![](imgs/telegram-cloud-photo-size-4-5861905704027885208-y.jpg)

#### **Decifratura**

$$
M = 47^{27} \bmod 55 = 53 \neq 5718
$$

Non è l'originale, è l'originale $\mod 55$

Questo accade poiché Bob non ha rispettato la regola di spedire come messaggio un numero $mess$ che rispettasse la diseguaglianza:

$$
0 < mess < n
$$

---

### **7. Soluzione alle dimensioni inadeguate con blocchi binari**

Quando il messaggio è troppo grande - e quindi supera $n$, dobbiamo prendere un accorgimento.

L'idea è scomporre il messaggio in dei blocchi più piccoli $b_i$ tali che:

$$
\forall \ b_i \rightarrow \ < mod\_considerato
$$

D'altronde questo ci è già congeniale con il fatto che possiamo rappresentare qualsiasi numero in binario. Esso viene **suddiviso in blocchi** e ogni blocco viene cifrato come un numero:

Esempio: convertiamo in bin...

$$
5718 = 001010011110010
$$

$$
\phantom{5718 = {}}\ \underbrace{00101}_{5}\ \underbrace{00111}_{7}\ \underbrace{10010}_{18}
$$

La dimensione del blocco si sceglie come $k = \lfloor \log_2 n \rfloor$ bit, in modo che ogni blocco rappresenti un numero strettamente minore del modulo $n$. Con $n = 55$: $\lfloor \log_2 55 \rfloor = 5,781 \implies 5$, quindi blocchi da **5 bit** (valore massimo $2^5 - 1 = 31 < 55$ ✓). La stringa di 15 bit si divide così in 3 blocchi da 5 bit: $00101_2 = 5$, $00111_2 = 7$, $10010_2 = 18$.

Si cifra ciascun blocco con la chiave pubblica $(e, n) = (3, 55)$:

$$
\begin{aligned}
5^3 \bmod 55 &= 125 \bmod 55 = 15 \\
7^3 \bmod 55 &= 343 \bmod 55 = 13 \\
18^3 \bmod 55 &= 5832 \bmod 55 = 2
\end{aligned}
$$

Cifrato finale: **15, 13, 2**

Decifratura:

$$
\begin{aligned}
15^{27} \bmod 55 &= 5 \\
13^{27} \bmod 55 &= 7 \\
2^{27} \bmod 55 &= 18
\end{aligned}
$$

Anche qui usiamo l’**algoritmo square and multiply** per calcolare le potenze modulari.
Ad esempio, per calcolare $15^{27} \bmod 55$:

$27$ in binario: $11011_2 = 16 + 8 + 2 + 1$ quindi similmente a prima dobbiamo calcolare le potenze di $15$ corrispondenti ai bit a 1 della rappresentazione binaria di $27$ e moltiplicarle tra loro, riducendo modulo $55$ ad ogni passo.

$$
\begin{aligned}
15^1 \bmod 55 &= 15 \\
15^2 \bmod 55 &= 15^2 = 225 \bmod 55 = 5 \\
\end{aligned}
$$

Per i successivi, ci è congeniale usare i risultati precedenti:

$$
\begin{aligned}
15^4 \bmod 55 &= (15^2 \bmod 55)^2 \bmod 55 = 5^2 \bmod 55 = 25 \\
15^8 \bmod 55 &= (15^4 \bmod 55)^2 \bmod 55 = 25^2 \bmod 55 = 20 \\
15^{16} \bmod 55 &= (15^8 \bmod 55)^2 \bmod 55 = 20^2 \bmod 55 = 15
\end{aligned}
$$

Ricostruiamo i 5 blocchi corrispondenti ai bit a 1 di $27 = 11011_2$:

$$
15^{27} \bmod 55 = 15^{16} \cdot 15^{8} \cdot 15^{2} \cdot 15^{1} \bmod 55 = 15 \cdot 20  \cdot 5 \cdot 15 \bmod 55 =
$$

$$
(300 \bmod 55) \cdot (75 \bmod 55) = 25 \cdot 20 \bmod 55 = 500 \bmod 55 = 5
$$

Ergo anche gli altri due blocchi sono decifrabili correttamente come visto per il primo blocco.

---

### **8. Sintesi finale**

- **RSA** è un **cifrario a blocchi**, in cui il messaggio è un numero compreso tra $0$ e $n-1$.
- La **funzione one-way** utilizzata è l’**esponenziazione modulare**.
- La **sicurezza** dell’algoritmo si fonda sulla **difficoltà della fattorizzazione** di grandi numeri composti.
- È utilizzato sia per **cifratura**, sia per **firme digitali** e **scambio sicuro di chiavi**.
  $$
