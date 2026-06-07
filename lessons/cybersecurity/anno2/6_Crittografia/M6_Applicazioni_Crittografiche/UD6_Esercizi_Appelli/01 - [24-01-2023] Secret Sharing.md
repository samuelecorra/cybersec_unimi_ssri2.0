# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 gennaio 2023**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 gennaio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L1%20-%20Appello%2024%20gennaio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che le share in possesso di Alice sono $s_1=6$, $s_2=4$, $s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir e interpolazione di Lagrange in $\mathbb{Z}_p$
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Scenari%20e%20applicazioni.md) — scenari del secret sharing

### Soluzione dettagliata

#### Parte a — Distribuzione e ricostruzione in uno schema $(k,n)$ (10 punti)

In uno **schema soglia $(k,n)$** un segreto $S$ è diviso in $n$ share distribuite a $n$ partecipanti, in modo che:

- **qualsiasi** $k$ share permettano di ricostruire $S$;
- **qualsiasi** $k-1$ (o meno) share non rivelino **alcuna** informazione su $S$.

**Schema di Shamir — Distribuzione (dealer):**

1. Si lavora nel campo finito $\mathbb{Z}_p$ con $p$ primo, $p > n$ e $p > S$.
2. Il dealer pone $a_0 = S$ e sceglie casualmente i coefficienti $a_1, \ldots, a_{k-1} \in \mathbb{Z}_p$, definendo il **polinomio di grado $k-1$**:
$$f(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{k-1}x^{k-1} \bmod p$$
3. Calcola le $n$ share come valutazioni del polinomio in punti distinti non nulli: $s_i = f(i) \bmod p$, per $i = 1, \ldots, n$.
4. Distribuisce in modo sicuro la share $s_i$ all'$i$-esimo partecipante; il polinomio (e quindi $S=f(0)$) resta segreto.

**Ricostruzione:** dati $k$ punti $(i, s_i)$, il polinomio $f$ di grado $k-1$ è univocamente determinato (un polinomio di grado $k-1$ è individuato da $k$ punti). Si ricostruisce $S = f(0)$ tramite **interpolazione di Lagrange** in $\mathbb{Z}_p$:
$$S = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1 \\ l\ne j}}^{k} \frac{0 - i_l}{i_j - i_l} \bmod p$$

> 📌 La sicurezza è **perfetta** (incondizionata): con solo $k-1$ share, per ogni possibile valore del segreto esiste esattamente un polinomio compatibile, quindi le $k-1$ share non restringono i candidati. Il caso $(n,n)$ è il sottocaso additivo: $S = (s_1 + \cdots + s_n)\bmod p$.

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_{11}$ con $s_1=6, s_2=4, s_3=2$ (10 punti)

Nello schema **$(n,n)$** con $n=k=3$ tutte le share sono necessarie e la condivisione è quella **additiva**: il segreto è la somma modulare delle share.

$$S = s_1 + s_2 + s_3 \bmod 11 = 6 + 4 + 2 \bmod 11 = 12 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$

**Verifica di coerenza:** se il dealer fissa $S=1$ e sceglie $s_1=6, s_2=4$, allora $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓

> 📌 Nello schema $(n,n)$ la ricostruzione è una semplice somma modulare: l'interpolazione di Lagrange serve solo quando $k < n$.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_{11}$ con $s(1)=3, s(2)=4$ (10 punti)

Lo schema $(2,3)$ di Shamir usa un polinomio di grado $k-1 = 1$: $f(x) = a_0 + a_1 x \bmod 11$, con $a_0 = S$.

**Dati:** punti $(1, 3)$ e $(2, 4)$.

**Metodo 1 — Interpolazione di Lagrange in $x=0$:**

$$L_1(0) = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2, \qquad L_2(0) = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$S = s(1)\,L_1(0) + s(2)\,L_2(0) = 3\cdot 2 + 4\cdot 10 = 6 + 40 = 46 \bmod 11 = 2$$

**Segreto ricostruito:** $\boxed{S = 2}$

**Metodo 2 — Sistema lineare (alternativo):**

$$\begin{cases} a_0 + a_1 = 3 \pmod{11} \\ a_0 + 2a_1 = 4 \pmod{11} \end{cases}$$

Sottraendo: $a_1 = 1$; dalla prima $a_0 = 3 - 1 = 2$. Quindi $f(x) = 2 + x$ e $S = f(0) = 2$.

**Verifica:** $f(1) = 3$ ✓, $f(2) = 4$ ✓. La terza share (non richiesta) sarebbe $s(3) = f(3) = 5$, e qualsiasi 2 delle 3 share ricostruiscono $S=2$.
