# Feistel 2 round mod 15 — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 settembre 2024**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L7%20-%20Appello%2020%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Rete di Feistel a 2 round con funzione aritmetica mod 15 (35 punti)

### Traccia originale

> **(35 punti) Cifratura simmetrica.**
> Si consideri una rete di Feistel a 2 fasi in cui la chiave è lunga la metà del blocco e la funzione round è definita come $f_i(x, K) = (2 \cdot x \cdot K) \bmod 15$, per $i = 1, 2$, dove $K$ appartiene a $\mathbb{Z}_{15}$.
>
> i. (10) Si illustri come si ottiene il messaggio cifrato $C = L_2 R_2$ dalla cifratura del messaggio $M = L_0 R_0$.
>
> ii. (15) Si cifri il messaggio 1010 1100 con $K = 7$.
>
> iii. (10) Si facciano considerazioni sulla sicurezza, considerando il valore numerico della chiave.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — analogia DES

### Soluzione dettagliata

#### Parte i — Schema generale di cifratura Feistel 2 round (10 punti)

**Struttura della rete di Feistel a 2 round:**

Il messaggio $M = L_0 R_0$ è diviso in due metà di eguale lunghezza (4 bit ciascuna nel nostro caso, dato che il blocco è di 8 bit e la chiave è lunga la metà del blocco).

La funzione round $f_i(x, K) = (2 \cdot x \cdot K) \bmod 15$ dipende dal valore decimale di $x$ (metà destra del blocco) e dalla chiave $K \in \mathbb{Z}_{15}$.

**Round 1:**

$$L_1 = R_0$$
$$R_1 = L_0 \oplus f_1(R_0, K) = L_0 \oplus [(2 \cdot R_0 \cdot K) \bmod 15]$$

**Round 2:**

$$L_2 = R_1$$
$$R_2 = L_1 \oplus f_2(R_1, K) = R_0 \oplus [(2 \cdot R_1 \cdot K) \bmod 15]$$

**Ciphertext:**

$$C = L_2 R_2 = R_1\ R_2$$

dove:
- $R_1 = L_0 \oplus [(2 \cdot \text{dec}(R_0) \cdot K) \bmod 15]$ (con $\oplus$ su 4 bit e $(2 \cdot \text{dec}(R_0) \cdot K) \bmod 15$ come valore intero da riconvertire in 4 bit)
- $R_2 = R_0 \oplus [(2 \cdot \text{dec}(R_1) \cdot K) \bmod 15]$

> ⚠️ Attenzione all'interpretazione della funzione $f$: il XOR è bit-a-bit su 4 bit, mentre il calcolo $(2xK) \bmod 15$ è su interi. Il risultato di $f$ (un valore in $\{0,...,14\}$, quindi a 4 bit) viene poi XORato con $L$ (anch'esso a 4 bit).

#### Parte ii — Cifratura di 1010 1100 con $K = 7$ (15 punti)

**Dati:**
- Plaintext: $M = $ 1010 1100
- $L_0 = $ 1010 $= 10_{10}$
- $R_0 = $ 1100 $= 12_{10}$
- $K = 7 \in \mathbb{Z}_{15}$

**Round 1:**

Calcolo di $f_1(R_0, K) = (2 \cdot R_0 \cdot K) \bmod 15 = (2 \cdot 12 \cdot 7) \bmod 15$:

$$2 \cdot 12 = 24$$
$$24 \cdot 7 = 168$$
$$168 \bmod 15 = 168 - 11 \cdot 15 = 168 - 165 = 3$$

Quindi $f_1(12, 7) = 3 = 0011_2$.

Aggiornamento Feistel:
$$L_1 = R_0 = 1100$$
$$R_1 = L_0 \oplus f_1(R_0, K) = 1010 \oplus 0011$$

$$1010$$
$$0011$$
$$\overline{1001}$$

Quindi $R_1 = 1001 = 9_{10}$.

Stato dopo Round 1: $L_1 = 1100$ (12), $R_1 = 1001$ (9)

**Round 2:**

Calcolo di $f_2(R_1, K) = (2 \cdot R_1 \cdot K) \bmod 15 = (2 \cdot 9 \cdot 7) \bmod 15$:

$$2 \cdot 9 = 18$$
$$18 \cdot 7 = 126$$
$$126 \bmod 15 = 126 - 8 \cdot 15 = 126 - 120 = 6$$

Quindi $f_2(9, 7) = 6 = 0110_2$.

Aggiornamento Feistel:
$$L_2 = R_1 = 1001$$
$$R_2 = L_1 \oplus f_2(R_1, K) = 1100 \oplus 0110$$

$$1100$$
$$0110$$
$$\overline{1010}$$

Quindi $R_2 = 1010 = 10_{10}$.

Stato dopo Round 2: $L_2 = 1001$ (9), $R_2 = 1010$ (10)

**Ciphertext:**

$$C = L_2 R_2 = 1001\ 1010$$

> 📌 **Riepilogo delle computazioni:**
>
> | Fase | L (bin) | L (dec) | R (bin) | R (dec) | f(R,K) |
> |------|---------|---------|---------|---------|--------|
> | Plaintext | 1010 | 10 | 1100 | 12 | — |
> | Dopo Round 1 | 1100 | 12 | 1001 | 9 | $(2\cdot12\cdot7)\bmod15=3$ |
> | Dopo Round 2 | 1001 | 9 | 1010 | 10 | $(2\cdot9\cdot7)\bmod15=6$ |

**Ciphertext finale:** $C = $ **1001 1010**

#### Parte iii — Considerazioni sulla sicurezza e il valore numerico della chiave (10 punti)

La funzione di round è $f(x, K) = (2xK) \bmod 15$.

**Analisi per valori specifici di $K$:**

**1. $K = 0$:**
$$f(x, 0) = (2 \cdot x \cdot 0) \bmod 15 = 0$$

Per qualsiasi $x$, $f = 0$. Il XOR con 0 è l'identità. Il round non cifra nulla: $R_i = L_{i-1}$ e $L_i = R_{i-1}$. Entrambi i round riducono il cifrario a una semplice trasposizione. Sicurezza **nulla**: la chiave è immediatamente rivelata se $K=0$.

**2. $K = 5$:**
$$f(x, 5) = (2x \cdot 5) \bmod 15 = (10x) \bmod 15$$

$10$ e $15$ hanno $\gcd(10, 15) = 5$. L'immagine di $x \mapsto 10x \bmod 15$ è $\{0, 5, 10\}$ (solo multipli di 5): molti valori di $x$ producono lo stesso $f$. Grande perdita di informazione.

**3. $K = 3$:**
$$f(x, 3) = (6x) \bmod 15 = (6x) \bmod 15$$

$\gcd(6, 15) = 3$. L'immagine è $\{0, 3, 6, 9, 12\}$: solo 5 valori su 16 possibili (per $x \in \{0,...,15\}$ non tutti i valori di $f$ sono raggiungibili).

**4. $K = 7$ (il caso dell'esame):**
$$f(x, 7) = (14x) \bmod 15$$

$\gcd(14, 15) = 1$: la funzione $x \mapsto 14x \bmod 15$ è una **biiezione** su $\mathbb{Z}_{15}$ (tutti i valori $0$-$14$ sono raggiunti esattamente una volta). Questo è il caso migliore per la funzione di round.

**5. Problema con $\mathbb{Z}_{15}$ (non primo):**

$15 = 3 \times 5$ non è primo. Questo significa che $\mathbb{Z}_{15}$ non è un campo: ci sono divisori di zero ($3 \cdot 5 = 15 \equiv 0$). Per certi valori di $K$ e $x$, $f(x, K) = 0$ anche con $x, K \neq 0$, il che crea "buchi" nella funzione di round e riduce la sicurezza.

**Debolezze generali:**

1. **Funzione di round lineare:** $f$ è moltiplicazione modulare → relazione lineare tra plaintext e ciphertext → vulnerabile alla crittoanalisi lineare.

2. **Chiave piccola:** $K \in \mathbb{Z}_{15}$ ha solo 15 valori. Un attacco a forza bruta richiede al massimo 15 tentativi.

3. **Solo 2 round:** insufficiente per garantire diffusione e confusione adeguate.

4. **Modulo 15 non primo:** causa debolezze strutturali (divisori di zero, non iniettività per certi $K$).

**Chiavi "sicure" vs "insicure":**

La sicurezza dipende fortemente dal valore di $K$:
- $K \in \{1, 2, 4, 7, 8, 11, 13, 14\}$ (coprimi con 15): $f$ è biiezione su $\mathbb{Z}_{15}$ → comportamento migliore.
- $K \in \{3, 5, 6, 9, 10, 12\}$ (non coprimi con 15): $f$ non è biiezione → perdita di informazione.
- $K = 0$: nessuna cifratura.

> ⚠️ Questo cifrario è pedagogicamente interessante ma crittograficamente insicuro. La dipendenza del livello di sicurezza dal valore numerico specifico della chiave è una caratteristica profondamente indesiderabile in un cifrario reale.
