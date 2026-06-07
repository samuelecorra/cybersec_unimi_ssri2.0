# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **19 settembre 2025**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 19 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L6%20-%20Appello%2019%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti). Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Descrivere l'applicazione dello schema $(2,3)$ considerando $\mathbb{Z}_{11}$ e le share $P(1)=3$, $P(2)=5$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$: distribuzione e ricostruzione (10 punti)

(Trattazione completa già in L2, D4a e L3, D4b. Si veda lì per i dettagli. Sintesi:)

**Distribuzione:** dealer sceglie polinomio $f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod p$ con $a_i$ casuali e $f(0) = s$. Partecipante $P_i$ riceve $s_i = f(i) \pmod p$.

**Ricostruzione:** $k$ partecipanti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$ usano interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{-i_l}{i_j - i_l} \pmod{p}$$

#### Parte b — Schema $(2,3)$ in $\mathbb{Z}_{11}$, share $P(1)=3$, $P(2)=5$ (10 punti)

**Dati:**

- Campo: $\mathbb{Z}_{11}$ ($p=11$)
- Share: $(1, 3)$ e $(2, 5)$

**Interpolazione di Lagrange:**

$$f(0) = 3 \cdot \lambda_1 + 5 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 5 \cdot 10 = 6 + 50 = 56 \pmod{11}$$

$56 = 5 \cdot 11 + 1$, quindi $56 \bmod 11 = 1$.

**Il segreto è $s = 1$.**

**Verifica:** Il polinomio è $f(x) = 1 + a_1 x$. Da $f(1) = 1 + a_1 = 3 \Rightarrow a_1 = 2$. Quindi $f(x) = 1 + 2x$.

- $f(1) = 1 + 2 = 3$ ✓
- $f(2) = 1 + 4 = 5$ ✓
- $f(0) = 1$ ✓

> 💡 **Confronto con appelli precedenti:** la coppia $(P(1)=3, P(2)=4)$ in $\mathbb{Z}_{11}$ degli appelli L2 e L5 dà segreto $s=2$. Qui $(P(1)=3, P(2)=5)$ in $\mathbb{Z}_{11}$ dà segreto $s=1$. Il polinomio $f(x) = 1 + 2x$ ha coefficiente angolare 2 (quindi le share crescono di 2 a ogni step), mentre $f(x)=2+x$ ha coefficiente angolare 1. La variazione di una singola share (da 4 a 5) cambia completamente il segreto recuperato.
