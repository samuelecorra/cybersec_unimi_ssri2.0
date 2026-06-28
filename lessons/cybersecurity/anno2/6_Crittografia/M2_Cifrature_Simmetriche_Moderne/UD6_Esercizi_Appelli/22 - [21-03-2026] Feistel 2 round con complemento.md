# Feistel 2 round con $f(x,K)=K\oplus\bar{x}$ — Esercizio d'esame

> 🗂️ **Fonte:** appello del **21 marzo 2026**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 21 marzo 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L3%20-%20Appello%2021%20marzo%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Rete di Feistel a 2 round (35 punti)

### Traccia originale

> **(35 punti)** Rete di Feistel a 2 fasi, chiave $K$ lunga metà del blocco, funzione round $f(x,K) = K \oplus \bar{x}$ (dove $\bar{x}$ è il complemento bit a bit).
>
> i. (10) Illustrare come si ottiene $C = L_2 R_2$ da $M = L_0 R_0$.
>
> ii. (10) Dimostrare che la decifratura è possibile partendo da $C = L_2 R_2$.
>
> iii. (5) Cifrare $10101001$ con $K=1010$.
>
> iv. (10) Considerazioni sulla sicurezza.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Reti di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Reti%20di%20Feistel.md) — reti di Feistel

### Soluzione dettagliata

#### Parte i — Cifratura $L_0 R_0 \to L_2 R_2$ (10 punti)

**Round 1** (con $f(x,K) = K \oplus \bar{x}$):

$$L_1 = R_0$$
$$R_1 = L_0 \oplus f(R_0, K) = L_0 \oplus K \oplus \bar{R}_0$$

**Round 2:**

$$L_2 = R_1 = L_0 \oplus K \oplus \bar{R}_0$$
$$R_2 = L_1 \oplus f(R_1, K) = R_0 \oplus K \oplus \bar{R}_1$$

#### Parte ii — Correttezza della decifratura (10 punti)

Dato $C = L_2 \| R_2$, si recupera $(L_1, R_1)$ invertendo il round 2:

$$R_1 = L_2 \quad \text{(dalla relazione } L_2 = R_1\text{)}$$

$$L_1 = R_2 \oplus f(L_2, K) = R_2 \oplus K \oplus \bar{L}_2$$

**Verifica:** $R_2 = L_1 \oplus K \oplus \bar{R}_1 = L_1 \oplus K \oplus \bar{L}_2$, quindi $R_2 \oplus K \oplus \bar{L}_2 = L_1 \oplus (K\oplus K) \oplus (\bar{L}_2\oplus\bar{L}_2) = L_1$. $\checkmark$

Si recupera poi $(L_0, R_0)$ invertendo il round 1:

$$R_0 = L_1$$
$$L_0 = R_1 \oplus f(L_1, K) = R_1 \oplus K \oplus \bar{L}_1$$

**Verifica:** $R_1 = L_0 \oplus K \oplus \bar{R}_0 = L_0 \oplus K \oplus \bar{L}_1$, quindi $R_1 \oplus K \oplus \bar{L}_1 = L_0$. $\checkmark$

> 📌 La decifratura Feistel usa la stessa struttura della cifratura con round applicati in ordine inverso, senza necessità di invertire $f$.

#### Parte iii — Cifratura di $10101001$ con $K=1010$ (5 punti)

$M = L_0 \| R_0 = 1010 \| 1001$, $K = 1010$.

**Round 1:**
$$L_1 = R_0 = 1001$$
$$\bar{R}_0 = \overline{1001} = 0110$$
$$R_1 = 1010 \oplus 1010 \oplus 0110 = 0000 \oplus 0110 = \mathbf{0110}$$

**Round 2:**
$$L_2 = R_1 = \mathbf{0110}$$
$$\bar{R}_1 = \overline{0110} = 1001$$
$$R_2 = 1001 \oplus 1010 \oplus 1001 = 0011 \oplus 1001 = \mathbf{1010}$$

**Ciphertext:** $C = L_2 \| R_2 = \mathbf{0110\ 1010}$.

#### Parte iv — Sicurezza (10 punti)

**1. Funzione round lineare (affine):**

$$f(x,K) = K \oplus \bar{x} = K \oplus \mathbf{1}^n \oplus x$$

È un'operazione **affine** (XOR con costante), completamente lineare. I cifrari robusti richiedono non-linearità (es. S-box AES) per resistere alla crittoanalisi lineare. Questa funzione è completamente lineare.

**2. Vulnerabilità KPA critica:**

Da $L_2 = L_0 \oplus K \oplus \bar{R}_0$, con una coppia $(M, C)$ nota:

$$\boxed{K = L_0 \oplus L_2 \oplus \bar{R}_0}$$

**Un solo plaintext-ciphertext noto rivela completamente la chiave $K$.**

**3. Soli 2 round:**

DES usa 16 round, Blowfish 16, AES 10–14. Con 2 round la diffusione dei bit è insufficiente.

**Riepilogo:**

| Proprietà | Valore |
|---|---|
| Linearità di $f$ | Affine: vulnerabile a crittoanalisi lineare |
| Round | 2: diffusione insufficiente |
| Sicurezza KPA | **Nulla**: 1 coppia rivela $K$ |
| Sicurezza COA | $2^{\|K\|}$ brute-force |

> ⚠️ Questo cifrario è **completamente insicuro contro KPA**. La formula $K = L_0 \oplus L_2 \oplus \bar{R}_0$ mostra che la linearità di $f$ rispetto a $K$ consente di determinare la chiave con un singolo esempio noto.
