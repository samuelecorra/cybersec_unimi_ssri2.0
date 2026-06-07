# Secret Sharing numerico — Esercizio d'esame

> 🗂️ **Fonte:** appello del **8 luglio 2024**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 8 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L5%20-%20Appello%208%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing numerico (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Per uno schema $(3, 3)$ in $\mathbb{Z}_{11}$ e ricostruire il segreto condiviso, sapendo le share in possesso di Alice sono $s_1 = 6$, $s_2 = 4$, $s_3 = 2$.
>
> b. (10 punti) Per uno schema $(2, 3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1) = 3$ e $s(2) = 4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — interpolazione di Lagrange in $\mathbb{Z}_p$

### Soluzione dettagliata

#### Parte a — Schema $(3, 3)$ in $\mathbb{Z}_{11}$: ricostruzione con $s_1=6, s_2=4, s_3=2$ (10 punti)

Nello schema $(n, n)$ con somma modulare, il segreto si ricostruisce sommando tutte le $n$ share:

$$S = s_1 + s_2 + \cdots + s_n \bmod p$$

Con $n = 3$, $p = 11$, $s_1 = 6$, $s_2 = 4$, $s_3 = 2$:

$$S = 6 + 4 + 2 \bmod 11 = 12 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$

**Verifica della coerenza:** Se il dealer avesse distribuito il segreto $S = 1$ scegliendo $s_1 = 6$, $s_2 = 4$, allora $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓

> 📌 Lo schema $(n,n)$ è molto semplice: la ricostruzione è una semplice somma modulare. Non richiede interpolazione di Lagrange, che è necessaria solo per schemi $(k,n)$ con $k < n$.

#### Parte b — Schema $(2, 3)$ in $\mathbb{Z}_{11}$: ricostruzione con $s(1)=3, s(2)=4$ (10 punti)

Lo schema $(2, 3)$ di Shamir usa un polinomio di grado $k-1 = 1$:

$$f(x) = a_0 + a_1 x \bmod 11$$

dove $a_0 = S$ è il segreto da trovare.

**Dati:** i punti $(x_1, y_1) = (1, 3)$ e $(x_2, y_2) = (2, 4)$.

**Metodo 1: Interpolazione di Lagrange per trovare $f(0) = S$:**

$$S = f(0) = y_1 \cdot L_1(0) + y_2 \cdot L_2(0) \bmod 11$$

dove:

$$L_1(0) = \frac{0 - x_2}{x_1 - x_2} = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2$$

$$L_2(0) = \frac{0 - x_1}{x_2 - x_1} = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

Quindi:

$$S = y_1 \cdot L_1(0) + y_2 \cdot L_2(0) = 3 \cdot 2 + 4 \cdot 10 \bmod 11$$

$$= 6 + 40 \bmod 11 = 46 \bmod 11$$

$$46 = 4 \cdot 11 + 2 \Rightarrow 46 \bmod 11 = 2$$

**Segreto ricostruito:** $\boxed{S = 2}$

**Metodo 2: Risoluzione del sistema lineare (alternativo):**

Il polinomio è $f(x) = a_0 + a_1 x \bmod 11$. Dalle due share:

$$\begin{cases} f(1) = a_0 + a_1 = 3 \pmod{11} \\ f(2) = a_0 + 2a_1 = 4 \pmod{11} \end{cases}$$

Sottraiamo la prima dalla seconda:

$$a_1 = 4 - 3 = 1 \pmod{11}$$

Dalla prima equazione:

$$a_0 = 3 - a_1 = 3 - 1 = 2 \pmod{11}$$

Quindi $f(x) = 2 + x$ e $S = f(0) = a_0 = 2$ ✓

**Verifica:** $f(1) = 2 + 1 = 3$ ✓; $f(2) = 2 + 2 = 4$ ✓

**Per trovare la terza share $s(3)$** (non richiesto dalla traccia, ma utile per comprendere lo schema $(2,3)$):

$$s(3) = f(3) = 2 + 3 = 5 \pmod{11}$$

Con qualsiasi due delle tre share $(s(1)=3, s(2)=4, s(3)=5)$ si può ricostruire il segreto $S = 2$.
