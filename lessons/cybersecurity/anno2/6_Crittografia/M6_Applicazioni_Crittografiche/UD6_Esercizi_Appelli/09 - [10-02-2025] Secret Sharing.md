# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **10 febbraio 2025**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 10 febbraio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L2%20-%20Appello%2010%20febbraio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> a. (10 punti). Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (15 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1) = 3$ e $s(2) = 4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$ di Shamir: distribuzione e ricostruzione (10 punti)

Lo schema di secret sharing di Shamir $(k, n)$ permette di dividere un segreto $s$ tra $n$ partecipanti in modo che qualsiasi $k$ di essi possano ricostruire $s$, mentre nessun gruppo di $k-1$ partecipanti ottiene alcuna informazione su $s$.

**Fase di distribuzione (da parte del dealer):**

1. Si sceglie un primo $p$ tale che $p > s$ e $p > n$ (tipicamente $p$ grande).
2. Si sceglie un polinomio $f(x)$ di grado $k-1$ su $\mathbb{Z}_p$ con termine costante uguale al segreto:

$$f(x) = s + a_1 x + a_2 x^2 + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

dove $a_1, \ldots, a_{k-1} \in \mathbb{Z}_p$ sono scelti uniformemente a caso.

3. Ogni partecipante $P_i$ ($i = 1, \ldots, n$) riceve la share $s_i = f(i) \bmod p$ in modo privato e sicuro.

**Proprietà di sicurezza:** un polinomio di grado $k-1$ è univocamente determinato da $k$ punti. Con $k-1$ share, i restanti valori del polinomio sono completamente indeterminati (qualsiasi segreto $s$ è consistente con le $k-1$ share note).

**Fase di ricostruzione:**

Quando almeno $k$ partecipanti $P_{i_1}, \ldots, P_{i_k}$ si riuniscono, usano le loro share $(i_j, s_{i_j})$ per ricostruire il polinomio $f$ tramite **interpolazione di Lagrange** in $\mathbb{Z}_p$:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \cdot \lambda_j \pmod{p}$$

dove i **coefficienti di Lagrange** sono:

$$\lambda_j = \prod_{\substack{l=1 \\ l \neq j}}^{k} \frac{-i_l}{i_j - i_l} \pmod{p} = \prod_{\substack{l=1 \\ l \neq j}}^{k} (-i_l) \cdot (i_j - i_l)^{-1} \pmod{p}$$

Il segreto $s = f(0)$ viene recuperato senza mai ricostruire esplicitamente l'intero polinomio.

#### Parte b — Ricostruzione numerica in $\mathbb{Z}_{11}$, schema $(2,3)$ (15 punti)

**Dati:**
- Schema $(2,3)$: threshold $k=2$, partecipanti $n=3$
- Campo: $\mathbb{Z}_{11}$ (primo $p = 11$)
- Share note: $s(1) = 3$, $s(2) = 4$

Con $k=2$, il polinomio segreto è di grado $k-1 = 1$:

$$f(x) = s + a_1 x \pmod{11}$$

e dobbiamo trovare $s = f(0)$.

**Interpolazione di Lagrange con i punti $(1, 3)$ e $(2, 4)$:**

$$f(0) = s(1) \cdot \lambda_1 + s(2) \cdot \lambda_2 \pmod{11}$$

**Calcolo di $\lambda_1$** (termine di Lagrange per il punto $x_1 = 1$, valutato in $x = 0$):

$$\lambda_1 = \frac{0 - x_2}{x_1 - x_2} = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

Verifica: $-2 \bmod 11 = 9$ e $-1 \bmod 11 = 10$.

$$\lambda_1 = 9 \cdot 10^{-1} \pmod{11}$$

Inverso di 10 in $\mathbb{Z}_{11}$: $10 \cdot 10 = 100 \equiv 1 \pmod{11}$, quindi $10^{-1} = 10$.

$$\lambda_1 = 9 \cdot 10 = 90 \bmod 11 = 90 - 8 \cdot 11 = 90 - 88 = 2$$

**Calcolo di $\lambda_2$** (termine di Lagrange per il punto $x_2 = 2$, valutato in $x = 0$):

$$\lambda_2 = \frac{0 - x_1}{x_2 - x_1} = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

**Calcolo del segreto:**

$$s = f(0) = s(1) \cdot \lambda_1 + s(2) \cdot \lambda_2 \pmod{11}$$

$$= 3 \cdot 2 + 4 \cdot 10 \pmod{11}$$

$$= 6 + 40 \pmod{11}$$

$$= 46 \pmod{11}$$

$$= 46 - 4 \cdot 11 = 46 - 44 = 2$$

**Il segreto condiviso è $s = 2$.**

**Verifica:** il polinomio lineare è $f(x) = 2 + a_1 x$. Sostituendo i punti noti:
- $f(1) = 2 + a_1 \equiv 3 \pmod{11} \Rightarrow a_1 = 1$
- $f(2) = 2 + 2 \cdot 1 = 4 \equiv 4 \pmod{11}$ ✓

Il polinomio è $f(x) = 2 + x \pmod{11}$ e $f(0) = 2$. Confermato.

> 📌 La formula generale di Lagrange per $k=2$ con punti $(x_1, y_1)$ e $(x_2, y_2)$ valutata in $x=0$ si riduce a:
> $$f(0) = y_1 \cdot \frac{-x_2}{x_1 - x_2} + y_2 \cdot \frac{-x_1}{x_2 - x_1} \pmod{p}$$
