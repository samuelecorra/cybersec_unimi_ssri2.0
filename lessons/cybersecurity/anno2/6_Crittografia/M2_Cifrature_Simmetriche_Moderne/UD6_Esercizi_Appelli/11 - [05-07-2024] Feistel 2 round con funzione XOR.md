# Feistel 2 round con funzione XOR — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 luglio 2024**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L4%20-%20Appello%205%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Strutture di Feistel a 2 round con funzione XOR (30 punti)

### Traccia originale

> **(30 punti) Strutture di Feistel.**
> Si consideri un cifrario a blocchi su 8 bit che si basa sul funzionamento di DES con soli due round e utilizza una struttura di Feistel, dove la funzione $f$ per il round $i$-esimo è definita come:
> $$f_i(k, x) = (2 \cdot i \cdot k \bmod 16) \oplus x \quad \text{per } i = 1, 2$$
> dove $k$ è la chiave che appartiene a $\mathbb{Z}_{16}$ e $x$ indica il blocco binario.
>
> a. (20) Se $K = 7$ (0111) e il plaintext è 0010 1000, mostrare come si ottiene il corrispondente ciphertext illustrando le computazioni intermedie.
>
> b. (10) Fare considerazioni sulla sicurezza del cifrario proposto.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — analogia con DES

### Soluzione dettagliata

> ⚠️ **Differenza cruciale rispetto all'Appello 24/06/2024:** la funzione di round è diversa. Nell'appello del 24/06 era $f_i(k,x) = (2ik) \cdot x \bmod 16$ (moltiplicazione). In questo appello (05/07) è $f_i(k,x) = (2ik \bmod 16) \oplus x$ (il fattore dipendente da $k$ viene calcolato modulo 16, poi il risultato fa XOR con $x$). Le computazioni sono differenti.

#### Parte a — Cifratura passo-passo (20 punti)

**Parametri:**
- Blocco 8 bit: $L_0 = $ 0010, $R_0 = $ 1000
- Chiave: $K = 7$ (decimale), cioè $0111_2$
- $L_0 = 0010_2 = 2_{10}$, $R_0 = 1000_2 = 8_{10}$

**Round 1 ($i = 1$):**

Calcolo del fattore chiave per il round:
$$2 \cdot 1 \cdot K \bmod 16 = 2 \cdot 7 \bmod 16 = 14 \bmod 16 = 14$$

La funzione di round è (XOR tra il fattore chiave e $R_0$):
$$f_1(K, R_0) = 14 \oplus R_0 = 14 \oplus 8$$

Convertiamo in binario:
$$14 = 1110_2, \quad 8 = 1000_2$$
$$1110 \oplus 1000 = 0110 = 6_{10}$$

Quindi $f_1(K, R_0) = 6$.

Aggiornamento Feistel:
$$L_1 = R_0 = 8$$
$$R_1 = L_0 \oplus f_1(K, R_0) = 2 \oplus 6$$

$$2 = 0010_2, \quad 6 = 0110_2$$
$$0010 \oplus 0110 = 0100 = 4_{10}$$

Stato dopo il Round 1: $L_1 = 8$ (1000), $R_1 = 4$ (0100)

**Round 2 ($i = 2$):**

Calcolo del fattore chiave per il round:
$$2 \cdot 2 \cdot K \bmod 16 = 4 \cdot 7 \bmod 16 = 28 \bmod 16 = 12$$

La funzione di round è (XOR tra il fattore chiave e $R_1$):
$$f_2(K, R_1) = 12 \oplus R_1 = 12 \oplus 4$$

$$12 = 1100_2, \quad 4 = 0100_2$$
$$1100 \oplus 0100 = 1000 = 8_{10}$$

Quindi $f_2(K, R_1) = 8$.

Aggiornamento Feistel:
$$L_2 = R_1 = 4$$
$$R_2 = L_1 \oplus f_2(K, R_1) = 8 \oplus 8$$

$$8 = 1000_2, \quad 8 = 1000_2$$
$$1000 \oplus 1000 = 0000 = 0_{10}$$

Stato dopo il Round 2: $L_2 = 4$ (0100), $R_2 = 0$ (0000)

**Ciphertext:**

$$C = L_2 \| R_2 = 0100\ 0000$$

> 📌 **Riepilogo delle computazioni:**
>
> | Fase | L (bin) | L (dec) | R (bin) | R (dec) | fattore chiave | f(K,R) |
> |------|---------|---------|---------|---------|---------------|--------|
> | Plaintext | 0010 | 2 | 1000 | 8 | — | — |
> | Round 1 | 1000 | 8 | 0100 | 4 | $2\cdot1\cdot7 \bmod 16 = 14$ | $14 \oplus 8 = 6$ |
> | Round 2 | 0100 | 4 | 0000 | 0 | $2\cdot2\cdot7 \bmod 16 = 12$ | $12 \oplus 4 = 8$ |

**Ciphertext finale:** $C = $ **0100 0000**

#### Parte b — Considerazioni sulla sicurezza (10 punti)

Il cifrario proposto presenta le stesse debolezze fondamentali dell'analoga versione con moltiplicazione (Appello 24/06):

**1. Funzione di round non sicura:**

La funzione $f_i(k, x) = (2ik \bmod 16) \oplus x$ è **lineare** sia in $k$ che in $x$: lo XOR è un'operazione lineare e il fattore $(2ik \bmod 16)$ è lineare in $k$. Quindi la relazione tra plaintext e ciphertext è descrivibile da equazioni lineari, rendendo il cifrario vulnerabile alla **crittoanalisi lineare**.

**2. Chiave a 4 bit:**

$K \in \mathbb{Z}_{16}$ ha soli 16 valori possibili. La forza bruta richiede al massimo 16 tentativi, che è un numero irrisorio.

**3. Soli 2 round:**

Il numero di round è insufficiente per garantire la diffusione richiesta dal criterio dell'avalanche effect (ogni bit del ciphertext deve dipendere da ogni bit del plaintext e della chiave). Con 2 round su 8 bit totali, la diffusione è incompleta.

**4. Problema con certi valori di $K$:**

Se $K = 0$: il fattore chiave è sempre 0, quindi $f_i(0, x) = 0 \oplus x = x$, che riduce il round a $R_{i} = L_{i-1} \oplus R_{i-1}$ (non aggiunge confusione sulla chiave).
Se $K = 8$: $2 \cdot 2 \cdot 8 \bmod 16 = 32 \bmod 16 = 0$, quindi al round 2 la funzione diventa $f_2 = x$ (keyless).

**5. Dipendenza lineare tra round:**

I fattori chiave dei due round sono $14$ e $12$ (per $K=7$): sono entrambi determinati deterministicamente da $K$. Non c'è schedule di chiave indipendente per i due round.

> 💡 I cifrari a blocchi moderni risolvono queste debolezze con: (a) funzioni di round non lineari (S-box), (b) schedule di chiave complessa, (c) molti round (10-16+), (d) chiavi lunghe (128-256 bit).
