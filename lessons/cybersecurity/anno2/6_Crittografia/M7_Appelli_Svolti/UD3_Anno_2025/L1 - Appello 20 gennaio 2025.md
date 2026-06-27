# Appello del 20 gennaio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 20/01/2025, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — sicurezza condizionata/incondizionata e struttura dei cifrari a blocchi
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — struttura Feistel e simmetria decifratura
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza delle funzioni hash
> - [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — cifratura e decifratura El-Gamal

---

## Domanda 1 — Cifratura simmetrica (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (5) Discutere sicurezza condizionata e incondizionata.
>
> b. (5) Descrivere un cifrario avente sicurezza incondizionata.
>
> c. (20) Considera il semplice cifrario a blocchi $C = (P \oplus K_0) \boxplus K_1$ dove $P$ è l'input in chiaro a 64 bit, $K_0$ e $K_1$ sono le due metà di una chiave segreta a 128 bit $K$ (ovvero $K_0$ e $K_1$ sono ciascuna di 64 bit e $K = K_0 \| K_1$), e $C$ è l'output cifrato a 64 bit. $\oplus$ indica l'addizione bit per bit modulo due (XOR) e $\boxplus$ indica l'addizione modulo $2^{64}$.
>
> i. Derivare l'equazione di decrittazione.
>
> ii. Supponiamo che un avversario acquisisca due testi in chiaro $P$ e $P'$ e i corrispondenti cifrati $C$ e $C'$, crittografati con la stessa chiave $K = K_0 \| K_1$. È possibile risolvere per $K_0$?

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — definizione di sicurezza condizionata vs. incondizionata
- [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — one-time pad e sicurezza perfetta

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):** un cifrario è condizionatamente sicuro se il costo computazionale richiesto per romperlo (anche da un avversario con risorse illimitate di tempo) supera le risorse ragionevolmente disponibili, oppure se il tempo necessario per rompere il cifrario supera il periodo di utilità del messaggio. La sicurezza è legata alla difficoltà di problemi computazionali (es. fattorizzazione, logaritmo discreto). Se si disponesse di potenza computazionale sufficiente, il sistema sarebbe violabile.

**Sicurezza incondizionata (teorica dell'informazione):** un cifrario è incondizionatamente sicuro se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro a partire dal testo cifrato. Formalmente, ciò significa che la distribuzione di probabilità di $P$ è indipendente da $C$:

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

Questa definizione, dovuta a Shannon, è nota come **perfect secrecy** (segretezza perfetta).

> 📌 Il teorema di Shannon afferma che la perfect secrecy richiede che lo spazio delle chiavi sia almeno grande quanto lo spazio dei messaggi: $|\mathcal{K}| \geq |\mathcal{M}|$.

#### Parte b — Cifrario con sicurezza incondizionata (5 punti)

Il cifrario con sicurezza incondizionata per eccellenza è il **One-Time Pad (OTP)**, proposto da Vernam nel 1917 e dimostrato perfettamente sicuro da Shannon nel 1949.

**Schema dell'OTP:**
- Sia $m \in \{0,1\}^n$ il messaggio in chiaro di $n$ bit.
- Sia $k \in \{0,1\}^n$ la chiave, scelta uniformemente a caso, della stessa lunghezza del messaggio, usata una sola volta.
- **Cifratura:** $c = m \oplus k$
- **Decifratura:** $m = c \oplus k$

**Proprietà di sicurezza:**

Per ogni testo cifrato $c$ e per ogni coppia di testi in chiaro $m_1, m_2$, esiste esattamente una chiave $k_1 = m_1 \oplus c$ che mappa $m_1$ in $c$ e una chiave $k_2 = m_2 \oplus c$ che mappa $m_2$ in $c$. Quindi:

$$P(C = c \mid M = m_1) = P(C = c \mid M = m_2) = \frac{1}{2^n}$$

Osservare $c$ non fornisce alcuna informazione su $m$.

**Limiti pratici:** la chiave deve essere lunga quanto il messaggio, non può essere riusata, e deve essere distribuita in modo sicuro. Per questo l'OTP non è pratico nella maggior parte degli scenari reali.

#### Parte c.i — Equazione di decrittazione (10 punti)

Il cifrario è definito come:

$$C = (P \oplus K_0) \boxplus K_1$$

dove $\oplus$ è XOR (addizione bit a bit modulo 2) e $\boxplus$ è l'addizione modulo $2^{64}$.

Per ricavare l'equazione di decifratura partiamo da $C$ e vogliamo ottenere $P$.

**Passo 1:** Eliminiamo $K_1$ applicando la sottrazione modulo $2^{64}$. Il contrario di $\boxplus K_1$ è $\boxminus K_1$, cioè la sottrazione modulo $2^{64}$:

$$C \boxminus K_1 = (P \oplus K_0) \boxplus K_1 \boxminus K_1 = P \oplus K_0$$

**Passo 2:** Eliminiamo $K_0$ applicando di nuovo XOR con $K_0$ (ricordando che $x \oplus K_0 \oplus K_0 = x$ per ogni $x$):

$$(C \boxminus K_1) \oplus K_0 = (P \oplus K_0) \oplus K_0 = P$$

**Equazione di decifratura:**

$$\boxed{P = (C \boxminus K_1) \oplus K_0}$$

> 📌 Notare che l'operazione $\boxminus$ è la sottrazione modulo $2^{64}$, che equivale a sommare il complemento a 2 del sottraendo: $C \boxminus K_1 = C \boxplus (\overline{K_1} + 1)$ in aritmetica modulo $2^{64}$.

#### Parte c.ii — Attacco con due coppie note (plaintext, ciphertext) (10 punti)

L'avversario conosce: $(P, C)$ e $(P', C')$ con la stessa chiave $K = K_0 \| K_1$.

Scriviamo le due equazioni di cifratura:

$$C = (P \oplus K_0) \boxplus K_1 \quad \text{(1)}$$

$$C' = (P' \oplus K_0) \boxplus K_1 \quad \text{(2)}$$

Sottraiamo (2) da (1) in aritmetica modulo $2^{64}$:

$$C \boxminus C' = [(P \oplus K_0) \boxplus K_1] \boxminus [(P' \oplus K_0) \boxplus K_1]$$

$$C \boxminus C' = (P \oplus K_0) \boxminus (P' \oplus K_0) \quad \text{(3)}$$

> ⚠️ **Trappola classica:** non è possibile semplificare ulteriormente in modo diretto perché le operazioni $\oplus$ e $\boxplus$ non sono distributive tra loro. La sottrazione modulo $2^{64}$ si cancella, ma il risultato $(P \oplus K_0) \boxminus (P' \oplus K_0)$ non si semplifica in $P \boxminus P'$ a causa dell'interazione tra XOR e addizione modulare.

**Conclusione:** l'equazione (3) fornisce una relazione in cui $K_0$ compare due volte, ma in un modo che **non permette** di isolare $K_0$ algebricamente in forma chiusa:

$$C \boxminus C' = (P \oplus K_0) \boxminus (P' \oplus K_0)$$

Il membro destro non si semplifica perché l'XOR di valori diversi con $K_0$ crea carry diversi nell'addizione modulare. Con due coppie note non è possibile risolvere direttamente per $K_0$ in forma chiusa. Tuttavia, si noti che il numero di candidati per $K_0$ è ancora $2^{64}$ (l'equazione fornisce un vincolo che riduce lo spazio ma non lo risolve univocamente senza ulteriori informazioni).

> 💡 **Intuizione:** la composizione XOR (non lineare rispetto all'addizione mod $2^{64}$) e addizione modulare (non lineare rispetto allo XOR) crea un sistema di equazioni misto che non è risolubile con semplice algebra lineare. Questo è esattamente il principio su cui si basano i cifrari a blocchi moderni.

---

## Domanda 2 — DES: simmetria cifratura/decifratura (20 punti)

### Traccia originale

> **(20 punti) DES.**
>
> a. (20) Nell'ambito del cifrario DES, provare che la decifratura può essere effettuata applicando l'algoritmo di cifratura al testo cifrato con le chiavi schedulate in ordine inverso.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — struttura Feistel del DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — proprietà di invertibilità dei cifrari di Feistel

### Soluzione dettagliata

#### Struttura del DES e notazione

Il DES è un cifrario di Feistel con 16 round. Denotiamo:
- $IP$: permutazione iniziale; $IP^{-1}$: permutazione finale (inversa di $IP$)
- $(L_i, R_i)$: metà sinistra e destra dopo il round $i$
- $F$: funzione di round (le S-box + permutazione $P$)
- $K_i$ ($i = 1, \ldots, 16$): sottochiavi schedulate dalla chiave principale

**Cifratura:** Dato il plaintext $M$, dopo $IP$ si ottiene $(L_0, R_0)$. Per ogni round $i = 1, \ldots, 16$:

$$L_i = R_{i-1}$$

$$R_i = L_{i-1} \oplus F(R_{i-1}, K_i)$$

Il ciphertext è $C = IP^{-1}(R_{16} \| L_{16})$ (notare lo swap finale: le due metà vengono scambiate prima di $IP^{-1}$).

#### Dimostrazione della simmetria

Vogliamo mostrare che applicare il DES a $C$ con le chiavi nell'ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce $M$.

**Passo 1:** Partiamo dal ciphertext $C = IP^{-1}(R_{16} \| L_{16})$. Applichiamo $IP$:

$$IP(C) = R_{16} \| L_{16} = (L_{16}', R_{16}') \quad \text{dove } L_{16}' = R_{16},\ R_{16}' = L_{16}$$

Chiamiamo questa coppia il punto di partenza della decifratura: $(L_0^D, R_0^D) = (R_{16}, L_{16})$.

**Passo 2:** Eseguiamo il primo round di decifratura con chiave $K_{16}$:

$$L_1^D = R_0^D = L_{16}$$

$$R_1^D = L_0^D \oplus F(R_0^D, K_{16}) = R_{16} \oplus F(L_{16}, K_{16})$$

Ma dall'ultimo round di cifratura sappiamo che:

$$R_{16} = L_{15} \oplus F(R_{15}, K_{16})$$

e $L_{16} = R_{15}$. Quindi:

$$R_1^D = [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(L_{16}, K_{16})$$

$$= [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(R_{15}, K_{16})$$

$$= L_{15} \oplus [F(R_{15}, K_{16}) \oplus F(R_{15}, K_{16})]$$

$$= L_{15} \oplus 0 = L_{15}$$

Quindi dopo il primo round di decifratura: $(L_1^D, R_1^D) = (L_{16}, L_{15}) = (R_{15}, L_{15})$.

**Passo 3:** Per induzione, si verifica che dopo il round $j$ di decifratura con chiave $K_{16-j+1}$:

$$(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$$

**Verifica del caso generale:** supponiamo $(L_{j-1}^D, R_{j-1}^D) = (R_{16-j+1}, L_{16-j+1})$. Allora:

$$L_j^D = R_{j-1}^D = L_{16-j+1} = R_{16-j}$$

$$R_j^D = L_{j-1}^D \oplus F(R_{j-1}^D, K_{16-j+1})$$

$$= R_{16-j+1} \oplus F(L_{16-j+1}, K_{16-j+1})$$

Dall'equazione di cifratura del round $(16-j+1)$: $R_{16-j+1} = L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})$ e $L_{16-j+1} = R_{16-j}$, quindi:

$$R_j^D = [L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})] \oplus F(R_{16-j}, K_{16-j+1}) = L_{16-j}$$

Quindi $(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$. L'induzione regge.

**Passo 4:** Dopo 16 round di decifratura:

$$(L_{16}^D, R_{16}^D) = (R_0, L_0)$$

Prima di applicare $IP^{-1}$, il DES esegue uno swap (output del 16° round è $R_{16}^D \| L_{16}^D = L_0 \| R_0$). Applicando $IP^{-1}$:

$$IP^{-1}(L_0 \| R_0) = IP^{-1}(IP(M)) = M$$

**Conclusione:** la decifratura del DES con chiavi in ordine inverso $K_{16}, \ldots, K_1$ produce esattamente il plaintext $M$ di partenza. $\blacksquare$

> 📌 Questa proprietà vale per tutti i cifrari di Feistel con $F$ qualsiasi: la struttura Feistel garantisce l'invertibilità indipendentemente dalla funzione $F$, che non deve essere biiezione.

---

## Domanda 3 — Funzioni hash (20 punti)

### Traccia originale

> **(20 punti) Funzioni hash.**
>
> a. (10 punti) Illustrare le proprietà di sicurezza delle funzioni hash.
>
> b. (10 punti) Dimostrare che la funzione hash $H(x) = 5x + 12 \bmod 20$ non ha la proprietà di resistenza debole alle collisioni e trovare inoltre una collisione per $H(6)$.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno e second preimage

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

Una funzione hash crittografica $H: \{0,1\}^* \to \{0,1\}^n$ deve soddisfare tre proprietà di sicurezza:

**1. Resistenza alla preimmagine (One-wayness / Preimage resistance):**

Data un'immagine $h$, deve essere computazionalmente difficile trovare un $x$ tale che $H(x) = h$.

$$\forall h,\quad \text{difficile trovare } x \text{ t.c. } H(x) = h$$

Questo garantisce che dall'hash non si possa risalire al messaggio originale.

**2. Resistenza debole alle collisioni (Second preimage resistance / Weak collision resistance):**

Dato un messaggio $x$, deve essere computazionalmente difficile trovare un messaggio diverso $x' \neq x$ tale che $H(x') = H(x)$.

$$\forall x,\quad \text{difficile trovare } x' \neq x \text{ t.c. } H(x') = H(x)$$

Questa proprietà protegge l'integrità: un avversario non può modificare un messaggio firmato mantenendo lo stesso hash.

**3. Resistenza forte alle collisioni (Strong collision resistance / Collision resistance):**

Deve essere computazionalmente difficile trovare **qualsiasi** coppia $(x, x')$ con $x \neq x'$ tale che $H(x) = H(x')$.

$$\text{difficile trovare } (x, x') \text{ con } x \neq x' \text{ t.c. } H(x) = H(x')$$

> 📌 La resistenza forte alle collisioni implica la resistenza debole, che a sua volta implica la one-wayness (in senso generale). La gerarchia è: collision resistance $\Rightarrow$ second preimage resistance $\Rightarrow$ preimage resistance.

> ⚠️ Il paradosso del compleanno mostra che trovare una collisione (strong) richiede solo $O(2^{n/2})$ tentativi (invece di $O(2^n)$), il che è il motivo per cui gli hash moderni usano output da 256 o 512 bit.

#### Parte b — $H(x) = 5x + 12 \bmod 20$ non ha second preimage resistance (10 punti)

**Calcolo di $H(6)$:**

$$H(6) = 5 \cdot 6 + 12 \bmod 20 = 30 + 12 \bmod 20 = 42 \bmod 20 = 2$$

**Trovare una collisione per $H(6) = 2$:**

Dobbiamo trovare $x' \neq 6$ tale che $H(x') = 2$, ovvero:

$$5x' + 12 \equiv 2 \pmod{20}$$

$$5x' \equiv 2 - 12 \equiv -10 \equiv 10 \pmod{20}$$

$$5x' \equiv 10 \pmod{20}$$

Dividiamo ambo i membri per 5. Poiché $\operatorname{mcd}(5, 20) = 5$ e $5 \mid 10$, l'equazione ha esattamente $\operatorname{mcd}(5,20) = 5$ soluzioni distinte in $\mathbb{Z}_{20}$.

Cerchiamo manualmente i valori $x' \in \{0, 1, \ldots, 19\}$ tali che $5x' \bmod 20 = 10$:

| $x'$ | $5x' \bmod 20$ |
|------|---------------|
| 0 | 0 |
| 1 | 5 |
| 2 | 10 ✓ |
| 3 | 15 |
| 4 | 0 |
| 6 | 10 (il valore di partenza) |
| 8 | 0 |
| 10 | 10 ✓ |
| 14 | 10 ✓ |
| 18 | 10 ✓ |

Verifica: $H(2) = 5 \cdot 2 + 12 \bmod 20 = 10 + 12 \bmod 20 = 22 \bmod 20 = 2$ ✓

**Collisione trovata:** $H(6) = H(2) = 2$, con $6 \neq 2$.

**Dimostrazione che la proprietà manca in generale:**

La funzione $H(x) = 5x + 12 \bmod 20$ è lineare, quindi iniettiva solo se $\operatorname{mcd}(5, 20) = 1$. Ma $\operatorname{mcd}(5, 20) = 5 \neq 1$, quindi $H$ non è iniettiva: esiste una classe di residui modulo $20/5 = 4$ che producono lo stesso output. Ogni valore nell'immagine di $H$ ha esattamente 5 preimmagini, il che rende banalmente semplice trovare second preimages (e collisioni).

> ⚠️ Per avere second preimage resistance, una funzione hash deve avere un output molto più grande dell'input, non essere lineare, e non avere struttura algebrica sfruttabile. Questa funzione viola tutte e tre queste caratteristiche.

---

## Domanda 4 — El-Gamal (30 punti)

### Traccia originale

> **(30 punti) El Gamal.** Si consideri un cifrario di El-Gamal con i seguenti parametri: primo $p = 11$, generatore $g = 2$ e numero scelto per la chiave privata di Alice $\alpha = 7$.
>
> a. (5 punti) Esplicitare la chiave pubblica di Alice.
>
> b. (10 punti) Mostrare cosa Bob spedisce ad Alice volendo cifrare il messaggio $M = 5$ e avendo scelto come numero casuale per la cifratura $k = 2$.
>
> c. (10 punti) Mostrare come Alice riesce a decifrare il messaggio.
>
> d. (5 punti) Fare cenni all'utilizzo di ELGamal per cifratura ellittica.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — schema completo El-Gamal
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — fondamento matematico
- [`../../M3_Cifrature_Asimmetriche/UD4/L2 - Crittosistemi su curve ellittiche.md`](../../M3_Cifrature_Asimmetriche/UD4/L2%20-%20Crittosistemi%20su%20curve%20ellittiche.md) — versione su curve ellittiche

### Soluzione dettagliata

#### Setup del sistema El-Gamal

**Parametri pubblici:**
- Primo: $p = 11$
- Generatore del gruppo $\mathbb{Z}_{11}^*$: $g = 2$
- Chiave privata di Alice: $\alpha = 7$

> 📌 Verifichiamo che $g = 2$ sia effettivamente un generatore di $\mathbb{Z}_{11}^*$. L'ordine del gruppo è $p - 1 = 10$. Calcoliamo le potenze di 2 modulo 11:
> $2^1=2,\ 2^2=4,\ 2^3=8,\ 2^4=5,\ 2^5=10,\ 2^6=9,\ 2^7=7,\ 2^8=3,\ 2^9=6,\ 2^{10}=1$
> Tutti i 10 elementi di $\mathbb{Z}_{11}^*$ sono generati: $g = 2$ è un generatore (radice primitiva).

#### Parte a — Chiave pubblica di Alice (5 punti)

La chiave pubblica di Alice è $\beta = g^\alpha \bmod p$:

$$\beta = 2^7 \bmod 11$$

Calcolo passo-passo:
$$2^1 = 2$$
$$2^2 = 4$$
$$2^4 = 4^2 = 16 \bmod 11 = 5$$
$$2^7 = 2^4 \cdot 2^2 \cdot 2^1 = 5 \cdot 4 \cdot 2 \bmod 11 = 40 \bmod 11 = 7$$

**Chiave pubblica di Alice:** $\beta = 7$

La chiave pubblica di Alice è la tripla $(p, g, \beta) = (11, 2, 7)$.

#### Parte b — Cifratura da parte di Bob (10 punti)

Bob vuole cifrare $M = 5$ usando la chiave pubblica di Alice $(p=11, g=2, \beta=7)$ e il numero casuale $k = 2$.

**Schema El-Gamal:** il ciphertext è la coppia $(c_1, c_2)$ dove:

$$c_1 = g^k \bmod p = 2^2 \bmod 11 = 4$$

$$c_2 = M \cdot \beta^k \bmod p = 5 \cdot 7^2 \bmod 11$$

Calcoliamo $7^2 \bmod 11$:

$$7^2 = 49 \bmod 11 = 49 - 4 \cdot 11 = 49 - 44 = 5$$

Quindi:

$$c_2 = 5 \cdot 5 \bmod 11 = 25 \bmod 11 = 3$$

**Bob spedisce ad Alice il ciphertext:** $(c_1, c_2) = (4, 3)$

> 📌 Il numero casuale $k$ è scelto da Bob ed è segreto. Alice non conosce $k$, ma grazie alla sua chiave privata $\alpha$ può comunque decifrare. La coppia $(c_1, c_2)$ non rivela nulla su $M$ a chi non conosce $\alpha$ (difficoltà del logaritmo discreto).

#### Parte c — Decifratura da parte di Alice (10 punti)

Alice riceve $(c_1, c_2) = (4, 3)$ e conosce la chiave privata $\alpha = 7$.

**Schema di decifratura El-Gamal:** per recuperare $M$, Alice calcola:

$$M = c_2 \cdot (c_1^\alpha)^{-1} \bmod p$$

**Passo 1:** Calcolo di $c_1^\alpha \bmod p$:

$$c_1^\alpha = 4^7 \bmod 11$$

Usando l'esponenziazione rapida:
$$4^1 = 4$$
$$4^2 = 16 \bmod 11 = 5$$
$$4^4 = 5^2 = 25 \bmod 11 = 3$$
$$4^7 = 4^4 \cdot 4^2 \cdot 4^1 = 3 \cdot 5 \cdot 4 \bmod 11 = 60 \bmod 11$$
$$60 = 5 \cdot 11 + 5 \Rightarrow 60 \bmod 11 = 5$$

Quindi $c_1^\alpha = 4^7 \bmod 11 = 5$.

**Passo 2:** Calcolo dell'inverso $(c_1^\alpha)^{-1} \bmod 11 = 5^{-1} \bmod 11$:

Applicando il piccolo teorema di Fermat: $5^{-1} \equiv 5^{11-2} = 5^9 \bmod 11$.

> 📌 **Piccolo teorema di Fermat.** Se $p$ è primo e $a$ non è divisibile per $p$, allora $a^{p-1} \equiv 1 \pmod p$. Per ottenere la formula dell'inverso, si riscrive $a^{p-1}$ come $a \cdot a^{p-2}$: quindi $a \cdot a^{p-2} \equiv 1 \pmod p$. Per definizione di inverso moltiplicativo, l'elemento che moltiplicato per $a$ dà $1$ modulo $p$ è $a^{-1}$; dunque $a^{-1} \equiv a^{p-2} \pmod p$.

$$5^2 = 25 \bmod 11 = 3$$
$$5^4 = 3^2 = 9$$
$$5^8 = 9^2 = 81 \bmod 11 = 4$$
$$5^9 = 5^8 \cdot 5 = 4 \cdot 5 = 20 \bmod 11 = 9$$

Verifica: $5 \cdot 9 = 45 \bmod 11 = 1$ ✓ Quindi $5^{-1} \bmod 11 = 9$.

**Passo 3:** Calcolo di $M$:

$$M = c_2 \cdot (c_1^\alpha)^{-1} \bmod 11 = 3 \cdot 9 \bmod 11 = 27 \bmod 11 = 5$$

**Messaggio recuperato:** $M = 5$ ✓

**Perché funziona?** La correttezza si verifica algebricamente:

$$c_2 \cdot (c_1^\alpha)^{-1} = M \cdot \beta^k \cdot (g^{k\alpha})^{-1} = M \cdot g^{k\alpha} \cdot g^{-k\alpha} = M$$

poiché $\beta = g^\alpha$, quindi $\beta^k = g^{k\alpha} = c_1^\alpha$.

#### Parte d — El-Gamal su curve ellittiche (5 punti)

Il crittosistema El-Gamal può essere trasportato su curve ellittiche sostituendo il gruppo moltiplicativo $\mathbb{Z}_p^*$ con il gruppo dei punti su una curva ellittica $E(\mathbb{F}_p)$.

**Adattamento del sistema:**

| El-Gamal classico | El-Gamal su curve ellittiche |
|-------------------|------------------------------|
| Gruppo $\mathbb{Z}_p^*$ (moltiplicativo) | Gruppo $E(\mathbb{F}_p)$ (additivo) |
| Generatore $g \in \mathbb{Z}_p^*$ | Punto base $G \in E(\mathbb{F}_p)$ |
| Chiave privata $\alpha \in \mathbb{Z}$ | Chiave privata $\alpha \in \mathbb{Z}$ |
| Chiave pubblica $\beta = g^\alpha$ | Chiave pubblica $Q = \alpha G$ (moltiplicazione scalare) |
| Cifratura: $(g^k,\ M \cdot \beta^k)$ | Cifratura: $(kG,\ M + k Q)$ dove $M$ è un punto |
| Decifratura: $c_2 \cdot (c_1^\alpha)^{-1}$ | Decifratura: $c_2 - \alpha c_1$ |

**Il problema del logaritmo discreto su curve ellittiche (ECDLP):**

La sicurezza si basa sulla difficoltà di trovare $\alpha$ dato $G$ e $Q = \alpha G$. Il problema ECDLP è ritenuto computazionalmente più difficile del logaritmo discreto classico per la stessa dimensione del gruppo, il che permette l'uso di chiavi più corte (256 bit EC $\approx$ 3072 bit RSA) a parità di sicurezza.

> 💡 La versione EC di El-Gamal richiede che il messaggio $M$ venga prima codificato come un punto sulla curva ellittica, operazione non banale in generale. Nella pratica si usano schemi ibridi (ECIES) dove EC fornisce lo scambio di chiave e si usa AES per cifrare il messaggio.

---

## Pattern e osservazioni per l'esame

Questo appello (sessione invernale, prima sessione dell'anno) mostra alcune caratteristiche tipiche:

**Struttura delle domande:**
- D1 e D4 (30 punti ciascuna) sono le domande più pesanti: D1 con teoria + calcolo su cifrario custom, D4 con calcolo numerico El-Gamal. Investire proporzionalmente più tempo.
- D2 (20 punti) richiede una dimostrazione formale della struttura DES: è una domanda di tipo "teorema", che va eseguita con notazione rigorosa e passaggi espliciti. Non basta descrivere informalmente.
- D3 (20 punti) combina teoria sulle proprietà hash + esempio concreto di violazione. Formulare chiaramente le definizioni prima di procedere all'esempio.

**Consigli pratici:**
- Per El-Gamal: prima verificare sempre che $g$ sia un generatore di $\mathbb{Z}_p^*$ (calcolare le potenze). Poi eseguire i calcoli passo-passo mostrando ogni moltiplicazione modulare.
- Per la dimostrazione DES: usare la notazione $(L_i, R_i)$ in modo consistente e mostrare l'induzione. Non fermarsi a un singolo round.
- Per le funzioni hash "giocattolo": prima enunciare le tre proprietà, poi mostrare quale viene violata trovando esplicitamente la collisione.
- La parte d su El-Gamal ellittico è tipicamente una domanda da 5 punti "bonus teorici": rispondere in modo sintetico ma preciso usando la tabella di corrispondenza.
