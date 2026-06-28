# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 febbraio 2026**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 febbraio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L2%20-%20Appello%2024%20febbraio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione nel caso $(k,n)$.
>
> b. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=5$ e $s(2)=7$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$ (10 punti)

**Distribuzione:** dealer sceglie $p$ primo $> n$, segreto $s \in \mathbb{Z}_p$, polinomio casuale di grado $k-1$:

$$f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod{p}, \quad f(0) = s$$

Partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi $k$ partecipanti usano interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l\neq j}} \frac{-i_l}{i_j - i_l} \pmod{p}$$

Con meno di $k$ share: segreto perfettamente nascosto.

#### Parte b — $(2,3)$ in $\mathbb{Z}_{11}$, $s(1)=5$, $s(2)=7$ (10 punti)

**Dati:** $p=11$, share $(1,5)$ e $(2,7)$.

$$\lambda_1 = \frac{0-2}{1-2} = 2, \qquad \lambda_2 = \frac{0-1}{2-1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 5 \cdot 2 + 7 \cdot 10 = 10 + 70 = 80 \pmod{11}$$

$80 = 7 \cdot 11 + 3$, quindi $80 \bmod 11 = 3$.

**Il segreto è $s = 3$.**

**Verifica:** $f(x) = 3 + 2x$ (da $f(1) = 5 \Rightarrow a_1 = 2$).
- $f(1) = 3+2 = 5$ ✓
- $f(2) = 3+4 = 7$ ✓
- $f(0) = 3$ ✓

> 💡 Formula rapida per indici 1 e 2 in $\mathbb{Z}_{11}$: $s = 2s_1 + 10s_2 \bmod 11 = 2\cdot5 + 10\cdot7 = 10+70 = 80 \bmod 11 = 3$.
