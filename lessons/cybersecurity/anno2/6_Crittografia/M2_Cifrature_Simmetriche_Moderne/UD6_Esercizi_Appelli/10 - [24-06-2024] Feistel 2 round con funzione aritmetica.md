# Feistel 2 round con funzione aritmetica — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 giugno 2024**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 giugno 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L3%20-%20Appello%2024%20giugno%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — notazione DES

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
