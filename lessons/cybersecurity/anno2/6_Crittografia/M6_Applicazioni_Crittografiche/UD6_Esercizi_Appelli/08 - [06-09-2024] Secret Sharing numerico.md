# Secret Sharing numerico — Esercizio d'esame

> 🗂️ **Fonte:** appello del **6 settembre 2024**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 6 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L6%20-%20Appello%206%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing numerico (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> a. (10 punti) Siano dati i seguenti share in uno schema $(4, 4)$: $P_1 = 6$, $P_2 = 5$, $P_3 = 4$, $P_4 = 9$. Ricostruire il segreto, sapendo che si opera in $\mathbb{Z}_{13}$.
>
> b. (15 punti) Per uno schema $(2, 3)$ in $\mathbb{Z}_{11}$, ricostruire il segreto avendo le due share $P_1 = 3$, $P_2 = 5$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — secret sharing di Shamir, interpolazione di Lagrange

### Soluzione dettagliata

#### Parte a — Schema $(4, 4)$ in $\mathbb{Z}_{13}$ con share $P_1=6, P_2=5, P_3=4, P_4=9$ (10 punti)

Nello schema $(n,n)$ con somma modulare, il segreto si ricostruisce sommando tutte le $n$ share:

$$S = P_1 + P_2 + P_3 + P_4 \bmod 13$$

$$S = 6 + 5 + 4 + 9 \bmod 13 = 24 \bmod 13$$

$$24 = 1 \cdot 13 + 11 \Rightarrow 24 \bmod 13 = 11$$

**Segreto ricostruito:** $\boxed{S = 11}$

**Verifica della coerenza:** $6 + 5 + 4 + 9 = 24$ e $24 \bmod 13 = 11$ ✓

#### Parte b — Schema $(2, 3)$ in $\mathbb{Z}_{11}$ con share $P_1=3$ (cioè $s(1)=3$) e $P_2=5$ (cioè $s(2)=5$) (15 punti)

Lo schema $(2,3)$ di Shamir usa un polinomio di grado $k-1=1$:

$$f(x) = a_0 + a_1 x \bmod 11$$

dove $a_0 = S$ è il segreto. Le share sono $s(i) = f(i)$.

**Dati:** $s(1) = f(1) = 3$ e $s(2) = f(2) = 5$.

**Metodo 1: Sistema lineare (più rapido per $k=2$):**

$$\begin{cases} f(1) = a_0 + a_1 = 3 \pmod{11} \\ f(2) = a_0 + 2a_1 = 5 \pmod{11} \end{cases}$$

Sottraiamo la prima dalla seconda:

$$a_1 = 5 - 3 = 2 \pmod{11}$$

Dalla prima equazione:

$$a_0 = 3 - a_1 = 3 - 2 = 1 \pmod{11}$$

**Segreto:** $S = a_0 = 1$.

**Metodo 2: Interpolazione di Lagrange:**

$$S = f(0) = s(1) \cdot L_1(0) + s(2) \cdot L_2(0) \bmod 11$$

$$L_1(0) = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \bmod 11$$

$$L_2(0) = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \bmod 11$$

$$S = 3 \cdot 2 + 5 \cdot 10 \bmod 11 = 6 + 50 \bmod 11 = 56 \bmod 11$$

$$56 = 5 \cdot 11 + 1 \Rightarrow 56 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$ (entrambi i metodi concordano ✓)

**Verifica:** $f(x) = 1 + 2x$:
- $f(1) = 1 + 2 = 3$ ✓
- $f(2) = 1 + 4 = 5$ ✓
- $f(3) = 1 + 6 = 7$ (share del terzo partecipante, non fornita ma coerente)

> 💡 Per schemi $(2,n)$ il metodo del sistema lineare è molto più veloce dell'interpolazione di Lagrange generale. Per schemi con $k \geq 3$ si deve usare l'interpolazione di Lagrange con inversioni modulari.
