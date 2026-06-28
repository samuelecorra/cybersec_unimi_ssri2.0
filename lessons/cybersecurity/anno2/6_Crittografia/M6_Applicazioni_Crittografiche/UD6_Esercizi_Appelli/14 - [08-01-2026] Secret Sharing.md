# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello dell'**8 gennaio 2026**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello dell'8 gennaio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L1%20-%20Appello%208%20gennaio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (15 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$ (10 punti)

**Distribuzione:** dealer sceglie $p$ primo $> n$, segreto $s \in \mathbb{Z}_p$, polinomio casuale di grado $k-1$:

$$f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod{p}, \quad f(0) = s$$

Il partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi $k$ partecipanti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$ usano interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{0 - i_l}{i_j - i_l} \pmod{p}$$

Con meno di $k$ share, il segreto è perfettamente nascosto (sicurezza perfetta alla Shannon).

#### Parte b — Schema $(2,3)$ in $\mathbb{Z}_{11}$, $s(1)=3$, $s(2)=4$ (15 punti)

**Dati:** $p=11$, share $(1,3)$ e $(2,4)$.

$$f(0) = 3 \cdot \lambda_1 + 4 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0-2}{1-2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0-1}{2-1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 4 \cdot 10 = 6 + 40 = 46 \pmod{11}$$

$46 = 4 \cdot 11 + 2$, quindi $46 \bmod 11 = 2$.

**Il segreto è $s = 2$.**

**Verifica:** $f(x) = 2 + x$ (da $f(1)=3 \Rightarrow a_1=1$).
- $f(1) = 2+1 = 3$ ✓
- $f(2) = 2+2 = 4$ ✓
- $f(0) = 2$ ✓

> 💡 Le share $(1,3),(2,4)$ in $\mathbb{Z}_{11}$ → $s=2$, polinomio $f(x)=2+x$. Confronto: $(1,3),(2,5)$ dell'appello 19/09/2025 → $s=1$ con $f(x)=1+2x$. La formula rapida con indici 1 e 2: $s = 2s_1 + 10 s_2 \bmod 11 = 2\cdot3 + 10\cdot4 = 46 \bmod 11 = 2$.
