# Appello del 24 giugno 2024 — Soluzione completa

> 📌 **Informazioni appello:** 24/06/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — sicurezza condizionata/incondizionata, OTP
> - [`../../M1_Crittografia_Classica/UD2/L2 - Cifrario affine.md`](../../M1_Crittografia_Classica/UD2/L2%20-%20Cifrario%20affine.md) — cifrario affine: composizione, chiusura
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel, calcolo passo-passo
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — correttezza RSA, fattorizzazione da φ(n), calcolo d
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md) — DSS: generazione/verifica firma, caso s=0

---

## Domanda 1 — Cifratura classica: sicurezza e cifrario affine composto (20 punti)

### Traccia originale

> **(20 punti) Cifratura classica.**
>
> a. (5) Dare una definizione di sicurezza condizionata e incondizionata.
>
> b. (5) Descrivere un cifrario incondizionatamente sicuro.
>
> c. (10) Si consideri per un cifrario affine, l'applicazione in sequenza della cifratura con chiave $k_1 = (a_1, b_1)$ e $k_2 = (a_2, b_2)$, $E_{k_1}(E_{k_2}(\text{plaintext})) = \text{ciphertext}$. Tale doppia cifratura aumenta la robustezza del cifrario? (giustificare la risposta)

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — sicurezza perfetta di Shannon, OTP
- [`../../M1_Crittografia_Classica/UD2/L2 - Cifrario affine.md`](../../M1_Crittografia_Classica/UD2/L2%20-%20Cifrario%20affine.md) — definizione, composizione, invertibilità

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):**

Un sistema crittografico è **condizionatamente sicuro** se un avversario computazionalmente limitato (con risorse di calcolo e/o tempo polinomiali) non può rompere il sistema in modo efficiente. La sicurezza dipende dalla difficoltà computazionale di problemi matematici (fattorizzazione, logaritmo discreto). Se l'avversario disponesse di potenza di calcolo illimitata, il sistema potrebbe essere rotto.

Formalmente: il costo computazionale dell'attacco supera le risorse disponibili per il tempo di utilità del messaggio.

**Sicurezza incondizionata (teorica dell'informazione / perfetta):**

Un sistema crittografico è **incondizionatamente sicuro** (o **perfettamente sicuro**) se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro a partire dal testo cifrato. Formalmente (secondo Shannon, 1949):

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

ovvero il testo cifrato non fornisce alcuna informazione probabilistica sul testo in chiaro: le distribuzioni di $P$ e $C$ sono stocasticamente indipendenti.

> 📌 Il **teorema di Shannon** afferma che la perfect secrecy richiede che lo spazio delle chiavi sia almeno grande quanto lo spazio dei messaggi: $|\mathcal{K}| \geq |\mathcal{M}|$. Ne consegue che le chiavi non possono essere riusate.

#### Parte b — Cifrario incondizionatamente sicuro (5 punti)

Il cifrario con sicurezza incondizionata per eccellenza è il **One-Time Pad (OTP)**, proposto da Vernam nel 1917 e dimostrato perfettamente sicuro da Shannon nel 1949.

**Schema:**
- Sia $m \in \{0,1\}^n$ il messaggio in chiaro di $n$ bit.
- Sia $k \in \{0,1\}^n$ la chiave, scelta uniformemente a caso, della stessa lunghezza del messaggio, usata **una sola volta**.
- **Cifratura:** $c = m \oplus k$
- **Decifratura:** $m = c \oplus k$

**Dimostrazione di perfect secrecy:**

Per ogni testo cifrato $c$ e per ogni coppia di testi in chiaro $m_1 \neq m_2$:

$$P(C = c \mid M = m_1) = P(K = m_1 \oplus c) = \frac{1}{2^n}$$

$$P(C = c \mid M = m_2) = P(K = m_2 \oplus c) = \frac{1}{2^n}$$

Quindi $P(C = c \mid M = m_1) = P(C = c \mid M = m_2)$ per ogni $m_1, m_2$ e ogni $c$: osservare il ciphertext non cambia la distribuzione di probabilità sul plaintext.

**Limiti pratici:** la chiave deve essere lunga quanto il messaggio, non riusabile, e distribuita in modo sicuro. Questi vincoli rendono l'OTP impraticabile in molti scenari reali.

#### Parte c — Doppia cifratura affine (10 punti)

**Definizione del cifrario affine:**

Il cifrario affine cifra un carattere $x \in \mathbb{Z}_{26}$ con chiave $(a, b)$ come:

$$E_{(a,b)}(x) = ax + b \bmod 26 \quad \text{con } \mcd(a, 26) = 1$$

La condizione $\mcd(a, 26) = 1$ garantisce l'invertibilità (8 valori possibili per $a$: 1,3,5,7,9,11,15,17,19,21,23,25).

**Analisi della composizione:**

Applichiamo prima $E_{k_2}$ e poi $E_{k_1}$ (la notazione del testo indica $E_{k_1}(E_{k_2}(\text{pt}))$):

$$E_{k_2}(x) = a_2 x + b_2 \bmod 26$$

$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 \bmod 26$$

$$= a_1 a_2 x + a_1 b_2 + b_1 \bmod 26$$

$$= (a_1 a_2) x + (a_1 b_2 + b_1) \bmod 26$$

Il risultato è ancora un cifrario affine con chiave equivalente:

$$\boxed{k_{\text{eq}} = (a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)}$$

**Risposta alla domanda: la doppia cifratura NON aumenta la robustezza.**

La composizione di due cifrari affini è ancora un cifrario affine. Ciò significa che:

1. **Chiusura algebrica:** la famiglia dei cifrari affini è **chiusa** rispetto alla composizione. Qualsiasi composizione di $n$ cifrari affini è equivalente a un singolo cifrario affine. Non si esce mai dalla famiglia.

2. **Nessuna espansione dello spazio delle chiavi effettivo:** anche se usiamo due chiavi $(a_1, b_1)$ e $(a_2, b_2)$, la sicurezza del doppio cifrario è quella di un cifrario affine con un'unica chiave equivalente $(a_1 a_2 \bmod 26, a_1 b_2 + b_1 \bmod 26)$. Un attaccante che rompe il cifrario affine singolo rompe automaticamente anche il doppio cifrario.

3. **Quantificazione:** lo spazio delle chiavi di un singolo cifrario affine ha $8 \times 26 = 208$ chiavi valide. Quello del doppio cifrario sembra $(8 \times 26)^2 \approx 43000$ coppie di chiavi, ma le chiavi equivalenti rimangono al più 208 (lo spazio dell'immagine). In realtà non tutte le 208 chiavi equivalenti sono raggiungibili: dipende da quali prodotti $a_1 a_2 \bmod 26$ siano anch'essi invertibili modulo 26, il che è verificato se $\mcd(a_1, 26) = \mcd(a_2, 26) = 1$.

> ✅ **Conclusione:** la doppia applicazione del cifrario affine con chiavi diverse è algebricamente equivalente a una singola applicazione con una chiave composta. Non aumenta la robustezza: l'analisi crittografica (ad esempio l'analisi delle frequenze) applicabile al cifrario affine semplice è altrettanto applicabile al doppio cifrario.

> 💡 Questo fenomeno (chiusura per composizione) si verifica anche per il cifrario di Vigenère e per altri cifrari lineari. È per questo che i cifrari moderni combinano operazioni NON lineari (S-box) con permutazioni lineari: la non-linearità impedisce la chiusura sotto composizione e aumenta davvero la sicurezza ad ogni round aggiuntivo.

---

## Domanda 2 — Strutture di Feistel a 2 round con funzione aritmetica (30 punti)

### Traccia originale

> **(30 punti) Strutture di Feistel.**
> Si consideri un cifrario a blocchi su 8 bit che si basa sul funzionamento di DES con soli due round e utilizzi una struttura di Feistel, dove la funzione $f$ per il round $i$-esimo è definita come:
> $$f_i(k, x) = (2 \cdot i \cdot k) \cdot x \bmod 16 \quad \text{per } i = 1, 2$$
> dove $k$ è la chiave che appartiene a $\mathbb{Z}_{16}$ e $x$ indica il valore corrispondente in decimale del blocco binario.
>
> a. (20) Se $K = 7$ (0111) e il plaintext è 0010 1000, mostrare come si ottiene il corrispondente ciphertext illustrando le computazioni intermedie.
>
> b. (10) Fare considerazioni sulla sicurezza del cifrario proposto.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel, round, inversione
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — notazione DES

### Soluzione dettagliata

#### Parte a — Cifratura passo-passo (20 punti)

**Parametri:**
- Blocco: 8 bit → diviso in due metà da 4 bit ciascuna
- Plaintext: $M = $ 0010 1000 → $L_0 = $ 0010, $R_0 = $ 1000
- Chiave: $K = 7$ (in decimale, 0111 in binario)
- Funzione: $f_i(k, x) = (2 \cdot i \cdot k) \cdot x \bmod 16$

Convertiamo i valori in decimale:
- $L_0 = 0010_2 = 2_{10}$
- $R_0 = 1000_2 = 8_{10}$

**Round 1 ($i = 1$):**

Calcolo della funzione di round:
$$f_1(K, R_0) = (2 \cdot 1 \cdot K) \cdot R_0 \bmod 16 = (2 \cdot 1 \cdot 7) \cdot 8 \bmod 16$$
$$= 14 \cdot 8 \bmod 16 = 112 \bmod 16$$
$$112 = 7 \cdot 16 = 112 \Rightarrow 112 \bmod 16 = 0$$

Quindi $f_1(7, 8) = 0$.

Aggiornamento dello stato Feistel:
$$L_1 = R_0 = 8$$
$$R_1 = L_0 \oplus f_1(K, R_0) = 2 \oplus 0$$

> ⚠️ Attenzione: $\oplus$ nella struttura Feistel è XOR bit a bit, non somma aritmetica.

$$R_1 = 2 \oplus 0 = 2$$

(Poiché $f_1 = 0 = 0000_2$ e $L_0 = 2 = 0010_2$: $0010 \oplus 0000 = 0010 = 2$)

Stato dopo il Round 1: $L_1 = 8$ (1000), $R_1 = 2$ (0010)

**Round 2 ($i = 2$):**

Calcolo della funzione di round:
$$f_2(K, R_1) = (2 \cdot 2 \cdot K) \cdot R_1 \bmod 16 = (4 \cdot 7) \cdot 2 \bmod 16$$
$$= 28 \cdot 2 \bmod 16$$
$$28 \bmod 16 = 12 \Rightarrow 12 \cdot 2 = 24 \bmod 16 = 8$$

Quindi $f_2(7, 2) = 8$.

Aggiornamento dello stato Feistel:
$$L_2 = R_1 = 2$$
$$R_2 = L_1 \oplus f_2(K, R_1) = 8 \oplus 8$$

$$8 = 1000_2, \quad 8 = 1000_2$$
$$1000 \oplus 1000 = 0000 = 0$$

Quindi $R_2 = 0$.

Stato dopo il Round 2: $L_2 = 2$ (0010), $R_2 = 0$ (0000)

**Ciphertext:**

$$C = L_2 \| R_2 = 0010\ 0000$$

> 📌 **Riepilogo delle computazioni:**
>
> | Fase | L | R | f(K, R) |
> |------|---|---|---------|
> | Plaintext | 0010 (2) | 1000 (8) | — |
> | Dopo Round 1 | 1000 (8) | 0010 (2) | $f_1 = 0$ |
> | Dopo Round 2 | 0010 (2) | 0000 (0) | $f_2 = 8$ |
> | **Ciphertext** | **0010** | **0000** | — |

**Ciphertext finale:** $C = $ **0010 0000**

#### Parte b — Considerazioni sulla sicurezza (10 punti)

Il cifrario proposto presenta **gravi debolezze** che lo rendono crittograficamente insicuro.

**1. Funzione di round lineare:**

La funzione $f_i(k, x) = (2ik) \cdot x \bmod 16$ è una funzione **lineare** (moltiplicazione modulare) in $x$ e in $k$. I cifrari moderni sicuri richiedono funzioni di round non lineari (come le S-box di DES/AES) per resistere alla crittoanalisi lineare.

**2. Problema con $K = 0$ o $f_i = 0$:**

Se $K = 0$ oppure se $2ik \equiv 0 \pmod{16}$ (ad esempio $K = 8$ al round 1: $2 \cdot 1 \cdot 8 = 16 \equiv 0$), allora $f_i = 0$ per qualsiasi valore di $x$. In questo caso il XOR diventa identità e il round non aggiunge confusione.

**3. Chiave a 4 bit:**

$K \in \mathbb{Z}_{16}$ ha solo 16 valori possibili. Un attacco a forza bruta richiede al massimo 16 tentativi, un numero insignificante.

**4. Soli 2 round:**

Anche se la funzione fosse sicura, 2 round non sono sufficienti per garantire sufficiente diffusione e confusione. AES usa 10+ round, DES 16 round.

**5. Determinismo e diffusione nulla in certi casi:**

Come visto nell'esempio, $f_1 = 0$ (con $K=7$) significa che il Round 1 scambia semplicemente L e R senza alterare i valori. Con una funzione di round che annulla se stessa frequentemente, il cifrario si avvicina a una semplice permutazione.

**6. Attacco di crittoanalisi lineare immediato:**

Poiché $f_i$ è lineare, la relazione tra plaintext e ciphertext è descrivibile da equazioni lineari modulo 16. Un attacco lineare sistematico richiederebbe pochissime coppie (plaintext, ciphertext) per recuperare la chiave.

> ⚠️ Questo cifrario è un esempio **pedagogico** e non dovrebbe mai essere usato in applicazioni reali. Il suo principale scopo è illustrare il funzionamento della struttura di Feistel in modo semplificato.

---

## Domanda 3 — Cifratura asimmetrica RSA (30 punti)

### Traccia originale

> **(30 punti) Cifratura asimmetrica.**
>
> a. (10) Si dimostri la correttezza di RSA.
>
> b. (10) Si consideri il crittosistema RSA con $n = 1363$. Si è scoperto che $\varphi(n) = 1288$. Usare questa informazione per fattorizzare $n$.
>
> c. (10 punti) Considerando come modulo $N = 55$ e chiave pubblica $e = 7$.
> i. Calcolare la chiave privata $d$ corrispondente, mostrando i calcoli.
> ii. Decifrare il messaggio $C = 3$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA: setup, correttezza, attacchi
- [`../../M1_Crittografia_Classica/UD1/L2 - Teoria dei numeri.md`](../../M1_Crittografia_Classica/UD1/L2%20-%20Teoria%20dei%20numeri.md) — algoritmo di Euclide esteso, teorema cinese dei resti

### Soluzione dettagliata

#### Parte a — Dimostrazione della correttezza di RSA (10 punti)

**Setup RSA:**
- Scegliere due primi distinti $p$ e $q$; calcolare $n = pq$ e $\varphi(n) = (p-1)(q-1)$.
- Scegliere $e$ con $1 < e < \varphi(n)$ e $\mcd(e, \varphi(n)) = 1$.
- Calcolare $d$ tale che $ed \equiv 1 \pmod{\varphi(n)}$.
- **Chiave pubblica:** $(n, e)$; **chiave privata:** $(n, d)$.
- **Cifratura:** $C = M^e \bmod n$
- **Decifratura:** $M' = C^d \bmod n$

**Enunciato da dimostrare:** $M' = C^d \equiv M^{ed} \equiv M \pmod{n}$ per ogni $M$ con $0 \leq M < n$.

**Dimostrazione:**

Poiché $ed \equiv 1 \pmod{\varphi(n)}$, esiste un intero $k$ tale che $ed = 1 + k\varphi(n) = 1 + k(p-1)(q-1)$.

**Caso 1: $\mcd(M, n) = 1$**

Per il teorema di Eulero: $M^{\varphi(n)} \equiv 1 \pmod{n}$ quando $\mcd(M, n) = 1$. Quindi:

$$M^{ed} = M^{1 + k\varphi(n)} = M \cdot (M^{\varphi(n)})^k \equiv M \cdot 1^k = M \pmod{n}$$

**Caso 2: $\mcd(M, n) \neq 1$**

Poiché $n = pq$ con $p, q$ primi, se $\mcd(M, n) \neq 1$ allora $p \mid M$ oppure $q \mid M$ (o entrambi, ma $M < n = pq$ esclude entrambi contemporaneamente se $M \neq 0$).

Supponiamo $p \mid M$ ma $q \nmid M$.

- **Modulo $p$:** $M \equiv 0 \pmod{p}$, quindi $M^{ed} \equiv 0 \equiv M \pmod{p}$.
- **Modulo $q$:** $\mcd(M, q) = 1$, quindi per il piccolo teorema di Fermat: $M^{q-1} \equiv 1 \pmod{q}$. Poiché $ed = 1 + k(p-1)(q-1)$, si ha:

$$M^{ed} = M \cdot (M^{q-1})^{k(p-1)} \equiv M \cdot 1 = M \pmod{q}$$

- **Ricombinazione (CRT):** abbiamo $M^{ed} \equiv M \pmod{p}$ e $M^{ed} \equiv M \pmod{q}$. Poiché $\mcd(p, q) = 1$, per il teorema cinese dei resti:

$$M^{ed} \equiv M \pmod{pq = n}$$

**Conclusione:** in tutti i casi, $C^d = (M^e)^d = M^{ed} \equiv M \pmod{n}$. La decifratura RSA è corretta. $\blacksquare$

#### Parte b — Fattorizzare $n = 1363$ sapendo $\varphi(n) = 1288$ (10 punti)

**Setup:**

Sappiamo che $n = pq$ con $p, q$ primi distinti. Quindi:
$$\varphi(n) = (p-1)(q-1) = pq - p - q + 1 = n - (p+q) + 1$$

Da cui:
$$p + q = n - \varphi(n) + 1 = 1363 - 1288 + 1 = 76$$

Ora abbiamo un sistema di due equazioni in due incognite:

$$\begin{cases} p + q = 76 \\ p \cdot q = 1363 \end{cases}$$

Sostituiamo $q = 76 - p$ nella seconda equazione:

$$p(76 - p) = 1363$$
$$76p - p^2 = 1363$$
$$p^2 - 76p + 1363 = 0$$

Applichiamo la formula quadratica:

$$p = \frac{76 \pm \sqrt{76^2 - 4 \cdot 1363}}{2} = \frac{76 \pm \sqrt{5776 - 5452}}{2} = \frac{76 \pm \sqrt{324}}{2} = \frac{76 \pm 18}{2}$$

Le due soluzioni:
$$p = \frac{76 + 18}{2} = \frac{94}{2} = 47 \quad \text{oppure} \quad p = \frac{76 - 18}{2} = \frac{58}{2} = 29$$

**Verifica:**
- $47 \times 29 = 1363$ ✓
- $47 + 29 = 76$ ✓
- $\varphi(1363) = (47-1)(29-1) = 46 \times 28 = 1288$ ✓

**Fattorizzazione:** $\boxed{n = 1363 = 47 \times 29}$

> ⚠️ Questa dimostrazione mostra perché $\varphi(n)$ deve essere tenuto segreto in RSA: chiunque conosca $n$ e $\varphi(n)$ può fattorizzare $n$ risolvendo un sistema di equazioni di grado 2, senza usare algoritmi di fattorizzazione avanzati.

#### Parte c — Calcolo della chiave privata e decifratura (10 punti)

**Parametri:** $N = 55$, $e = 7$.

**Identificazione dei fattori di $N$:**
$$55 = 5 \times 11$$
$$\varphi(55) = (5-1)(11-1) = 4 \times 10 = 40$$

**Parte c.i — Calcolo di $d$ tale che $ed \equiv 1 \pmod{40}$:**

Dobbiamo trovare $d$ tale che $7d \equiv 1 \pmod{40}$.

Usiamo l'**algoritmo di Euclide esteso** su $\mcd(7, 40)$:

**Algoritmo di Euclide:**
$$40 = 5 \cdot 7 + 5$$
$$7 = 1 \cdot 5 + 2$$
$$5 = 2 \cdot 2 + 1$$
$$2 = 2 \cdot 1 + 0$$

$\mcd(7, 40) = 1$ ✓ (l'inverso esiste)

**Back-substitution (Euclide esteso):**

Partiamo dall'ultimo resto non nullo e risaliamo:
$$1 = 5 - 2 \cdot 2 \quad \text{(dall'ultima riga)}$$

Sostituiamo $2 = 7 - 1 \cdot 5$:
$$1 = 5 - 2 \cdot (7 - 1 \cdot 5) = 5 - 2 \cdot 7 + 2 \cdot 5 = 3 \cdot 5 - 2 \cdot 7$$

Sostituiamo $5 = 40 - 5 \cdot 7$:
$$1 = 3 \cdot (40 - 5 \cdot 7) - 2 \cdot 7 = 3 \cdot 40 - 15 \cdot 7 - 2 \cdot 7 = 3 \cdot 40 - 17 \cdot 7$$

Quindi:
$$1 \equiv -17 \cdot 7 \pmod{40}$$
$$7 \cdot (-17) \equiv 1 \pmod{40}$$
$$d = -17 \bmod 40 = 40 - 17 = 23$$

**Verifica:** $7 \times 23 = 161 = 4 \times 40 + 1 \equiv 1 \pmod{40}$ ✓

**Chiave privata:** $d = 23$.

**Parte c.ii — Decifrare il messaggio $C = 3$:**

La decifratura RSA è $M = C^d \bmod N = 3^{23} \bmod 55$.

Usiamo l'esponenziazione rapida (square-and-multiply). La rappresentazione binaria di $23 = 10111_2$.

Calcoliamo le potenze di 3 modulo 55 necessarie:
$$3^1 = 3$$
$$3^2 = 9$$
$$3^4 = 9^2 = 81 \bmod 55 = 81 - 55 = 26$$
$$3^8 = 26^2 = 676 \bmod 55$$
$$676 = 12 \cdot 55 + 16 \Rightarrow 676 \bmod 55 = 16$$
$$3^{16} = 16^2 = 256 \bmod 55$$
$$256 = 4 \cdot 55 + 36 \Rightarrow 256 \bmod 55 = 36$$

Ora $23 = 16 + 4 + 2 + 1$, quindi:
$$3^{23} = 3^{16} \cdot 3^4 \cdot 3^2 \cdot 3^1 \bmod 55$$
$$= 36 \cdot 26 \cdot 9 \cdot 3 \bmod 55$$

Calcolo passo-passo:
$$36 \cdot 26 = 936 \bmod 55$$
$$936 = 17 \cdot 55 + 1 \Rightarrow 936 \bmod 55 = 1$$

$$1 \cdot 9 = 9$$

$$9 \cdot 3 = 27$$

**Messaggio decifrato:** $M = 3^{23} \bmod 55 = 27$

**Verifica (cifratura di 27):**
$$C = 27^7 \bmod 55$$
$$27^2 = 729 \bmod 55 = 729 - 13 \cdot 55 = 729 - 715 = 14$$
$$27^4 = 14^2 = 196 \bmod 55 = 196 - 3 \cdot 55 = 196 - 165 = 31$$
$$27^7 = 27^4 \cdot 27^2 \cdot 27^1 = 31 \cdot 14 \cdot 27 \bmod 55$$
$$31 \cdot 14 = 434 \bmod 55 = 434 - 7 \cdot 55 = 434 - 385 = 49$$
$$49 \cdot 27 = 1323 \bmod 55 = 1323 - 24 \cdot 55 = 1323 - 1320 = 3$$ ✓

**Messaggio decifrato:** $\boxed{M = 27}$.

---

## Domanda 4 — Firma digitale DSS (20 punti)

### Traccia originale

> **(20 punti) Firma digitale.**
>
> a. (10 punti) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (10 punti) Dimostrare che in caso si ottenga per un certo valore $a$ la firma $(a, 0)$, allora il sistema DSA potrebbe essere rotto.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md) — DSS completo

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

**Parametri del sistema (si rimanda all'Appello L2 per descrizione dettagliata):**
- $p$: primo grande; $q$: primo divisore di $p-1$, $|q|=160$ bit
- $g$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$
- $x$: chiave privata, $0 < x < q$; $y = g^x \bmod p$: chiave pubblica

**Generazione della firma di $m$:**
1. Scegliere $k$ casuale con $0 < k < q$.
2. Calcolare $r = (g^k \bmod p) \bmod q$. Se $r = 0$: rifare con nuovo $k$.
3. Calcolare $s = k^{-1}(H(m) + xr) \bmod q$. Se $s = 0$: rifare con nuovo $k$.
4. Firma: $(r, s)$.

**Verifica della firma $(r, s)$ di $m$:**
1. Verificare $0 < r < q$ e $0 < s < q$.
2. $w = s^{-1} \bmod q$
3. $u_1 = H(m) \cdot w \bmod q$
4. $u_2 = r \cdot w \bmod q$
5. $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$
6. Firma valida $\iff v = r$.

#### Parte b — Firma $(a, 0)$ rompe DSA (10 punti)

Sia la firma del messaggio $m$ la coppia $(r, s) = (a, 0)$ con $a \neq 0$.

**La componente $s$ è definita come:**

$$s = k^{-1}(H(m) + xr) \bmod q$$

Avere $s = 0$ significa:

$$k^{-1}(H(m) + xr) \equiv 0 \pmod{q}$$

Poiché $k^{-1}$ esiste (è invertibile modulo $q$ primo), possiamo moltiplicare entrambi i lati per $k$:

$$H(m) + xr \equiv 0 \pmod{q}$$

$$xr \equiv -H(m) \pmod{q}$$

Ora $r = a \neq 0$. Poiché $q$ è primo e $0 < r < q$, si ha $\mcd(r, q) = 1$, quindi $r$ è invertibile modulo $q$. Moltiplicando entrambi i lati per $r^{-1} \bmod q$:

$$x \equiv -H(m) \cdot r^{-1} \pmod{q}$$

**L'avversario può calcolare $x$ direttamente:**

Conoscendo $(r, s) = (a, 0)$, $m$ (o $H(m)$), e i parametri pubblici $p, q, g, y$, un avversario può:
1. Calcolare $r^{-1} \bmod q$ (operazione banale con Euclide esteso).
2. Calcolare $H(m)$.
3. Calcolare $x = -H(m) \cdot r^{-1} \bmod q$.

Questo rivela la chiave privata $x$, compromettendo l'intero sistema: l'avversario può ora generare firme valide per qualsiasi messaggio.

> ✅ **Conclusione:** la firma $(a, 0)$ espone direttamente la chiave privata $x$ tramite una semplice operazione aritmetica modulare. Pertanto il protocollo DSA richiede che se $s = 0$ (o $r = 0$), la firma venga scartata e rigenerata con un nuovo $k$ casuale.

---

## Pattern e osservazioni per l'esame

Questo è il primo appello della sessione estiva (giugno 2024). Alcune osservazioni:

- **D1 (20 punti):** la domanda c sulla composizione del cifrario affine è tipica: richiede di mostrare la chiusura algebrica con un calcolo esplicito della chiave equivalente. La risposta deve affermare chiaramente che la doppia cifratura NON aumenta la sicurezza.

- **D2 (30 punti):** Feistel a 2 round con funzione aritmetica. La funzione usa la moltiplicazione modulare (non XOR). Fare attenzione che il XOR della struttura Feistel è bit-a-bit, non moltiplicazione. I calcoli mod 16 possono portare a $f = 0$ in certi round.

- **D3 (30 punti):** domanda RSA molto completa. La parte b (fattorizzazione da $\varphi(n)$) è un classico: sistema $p+q$ e $pq$ noti → equazione quadratica. La parte c richiede Euclide esteso completo con back-substitution.

- **D4 (20 punti):** la parte b sul caso $(a, 0)$ è identica concettualmente al caso $s=0$ dell'appello L2 (febbraio). La dimostrazione deve essere algebrica: da $s=0$ si ricava $x$ esplicitamente.

**Consigli per la gestione del tempo:**
- D2 e D3 valgono 30 punti ciascuna: dedicarsi con priorità.
- Per D3b (fattorizzazione): impostare il sistema $p+q = n - \varphi(n) + 1$ e risolvere l'equazione quadratica in modo meccanico.
- Per D3c.i (Euclide esteso): mostrare ogni passaggio dell'algoritmo e poi la back-substitution.
