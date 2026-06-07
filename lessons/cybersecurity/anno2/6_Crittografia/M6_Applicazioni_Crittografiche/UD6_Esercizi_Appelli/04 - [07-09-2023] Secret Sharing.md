# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **7 settembre 2023**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 7 settembre 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L6%20-%20Appello%207%20settembre%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto, con $s_1=6, s_2=4, s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto, con $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — interpolazione di Lagrange in $\mathbb{Z}_p$
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Scenari%20e%20applicazioni.md) — scenari

### Soluzione dettagliata

#### Parte a — Distribuzione e ricostruzione $(k,n)$ (10 punti)

Schema soglia $(k,n)$ di **Shamir**: il segreto $S$ è diviso in $n$ share; **qualsiasi $k$** share ricostruiscono $S$, mentre $k-1$ o meno non rivelano nulla (sicurezza perfetta).

**Distribuzione (dealer):** in $\mathbb{Z}_p$ ($p$ primo, $p>n$, $p>S$) pone $a_0 = S$, sceglie casualmente $a_1,\ldots,a_{k-1}$ e definisce $f(x) = a_0 + a_1 x + \cdots + a_{k-1}x^{k-1} \bmod p$; calcola le share $s_i = f(i)$ per $i=1,\ldots,n$ e le distribuisce.

**Ricostruzione:** dati $k$ punti $(i, s_i)$, il polinomio di grado $k-1$ è univocamente determinato; si recupera $S = f(0)$ con l'**interpolazione di Lagrange**:
$$S = \sum_{j=1}^{k} s_{i_j} \prod_{l \neq j} \frac{0 - i_l}{i_j - i_l} \bmod p$$

> 📌 Il caso $(n,n)$ è il sottocaso **additivo**: $S = (s_1 + \cdots + s_n)\bmod p$.

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_{11}$ (10 punti)

Con $k=n=3$ è uno schema $(n,n)$ **additivo**: $S = s_1+s_2+s_3 \bmod 11 = 6+4+2 = 12 \equiv 1$.

**Segreto:** $\boxed{S = 1}$. Verifica: $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_{11}$ (10 punti)

Polinomio di grado 1: $f(x) = a_0 + a_1 x$, $a_0 = S$. Punti $(1,3)$, $(2,4)$.

**Lagrange in $x=0$:** $L_1(0) = \frac{0-2}{1-2} = 2$, $L_2(0) = \frac{0-1}{2-1} = -1 \equiv 10$. Quindi
$$S = 3\cdot2 + 4\cdot10 = 6 + 40 = 46 \equiv 2 \pmod{11}$$

**Sistema lineare (verifica):** $a_0+a_1=3$, $a_0+2a_1=4$ → $a_1=1$, $a_0=2$, cioè $f(x)=2+x$, $S=f(0)=2$. Controllo: $f(1)=3$ ✓, $f(2)=4$ ✓.

**Segreto:** $\boxed{S = 2}$.
