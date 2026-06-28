# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **21 marzo 2026**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 21 marzo 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L3%20-%20Appello%2021%20marzo%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (5 punti) Spiegare le proprietà di sicurezza dello schema di Shamir: cosa garantisce la condizione "meno di $k$ share non rivelano nulla del segreto"?
>
> b. (15 punti) Si considera uno schema $(2,4)$ in $\mathbb{Z}_{11}$ dove la funzione è $f(x) = s + 3x \pmod{11}$ con $f(0) = s$. Calcolare gli share $s_1, s_2, s_3, s_4$ e ricostruire $s$ a partire da $s_1 = f(1)$ e $s_3 = f(3)$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Sicurezza perfetta di Shamir (5 punti)

Lo schema di Shamir è **information-theoretically secure**: con meno di $k$ share, la distribuzione del segreto $s$ è uniforme su $\mathbb{Z}_p$, indipendentemente dagli share osservati.

**Motivazione formale:** un polinomio di grado $k-1$ è determinato da esattamente $k$ punti. Con $k-1$ share $(x_1, f(x_1)), \ldots, (x_{k-1}, f(x_{k-1}))$, esistono esattamente $p$ polinomi di grado $k-1$ che passano per quei punti (uno per ogni valore possibile di $f(0) = s \in \mathbb{Z}_p$). Nessun avversario computazionalmente illimitato può restringere ulteriormente l'insieme dei candidati.

> 📌 A differenza degli schemi computazionalmente sicuri, la sicurezza di Shamir non dipende dalla difficoltà di alcun problema matematico, ma dalla struttura algebrica dei polinomi su campi finiti.

#### Parte b — Schema $(2,4)$ in $\mathbb{Z}_{11}$, $f(x) = s + 3x$ (15 punti)

**Calcolo degli share:**

Il dealer distribuisce $f(1), f(2), f(3), f(4)$ in $\mathbb{Z}_{11}$:

| $i$ | $f(i) = s + 3i \bmod 11$ |
|---|---|
| 1 | $s + 3$ |
| 2 | $s + 6$ |
| 3 | $s + 9$ |
| 4 | $s + 12 \bmod 11 = s + 1$ |

Il segreto $s = f(0)$ non è distribuito direttamente.

---

**Ricostruzione di $s$ con $s_1 = f(1)$ e $s_3 = f(3)$:**

Dati i due share $(x_1, y_1) = (1, s_1)$ e $(x_2, y_2) = (3, s_3)$, si applica l'interpolazione di Lagrange per valutare in $x=0$:

$$s = f(0) = y_1 \cdot \lambda_1 + y_2 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - x_2}{x_1 - x_2} = \frac{0 - 3}{1 - 3} = \frac{-3}{-2} = \frac{3}{2} \pmod{11}$$

$2^{-1} \bmod 11 = 6$ (poiché $2 \cdot 6 = 12 \equiv 1$), quindi:
$$\lambda_1 = 3 \cdot 6 = 18 \bmod 11 = 7$$

$$\lambda_2 = \frac{0 - x_1}{x_2 - x_1} = \frac{0 - 1}{3 - 1} = \frac{-1}{2} = \frac{10}{2} \pmod{11}$$

$$\lambda_2 = 10 \cdot 6 = 60 \bmod 11 = 5$$

Quindi:
$$s = f(0) = 7 \cdot s_1 + 5 \cdot s_3 \pmod{11}$$

**Verifica con il polinomio noto** $f(x) = s + 3x$:
- $s_1 = f(1) = s + 3$
- $s_3 = f(3) = s + 9$

$$7(s+3) + 5(s+9) = 7s + 21 + 5s + 45 = 12s + 66 \pmod{11}$$
$$= s + 0 \pmod{11} = s \checkmark$$

(poiché $12 \equiv 1$ e $66 = 6 \cdot 11 \equiv 0$ in $\mathbb{Z}_{11}$)

**Esempio concreto con $s = 7$:**

$$s_1 = 7 + 3 = 10, \quad s_3 = 7 + 9 = 16 \bmod 11 = 5$$

$$\text{Ricostruzione: } 7 \cdot 10 + 5 \cdot 5 = 70 + 25 = 95 \bmod 11 = 7 \checkmark$$

$(95 = 8 \cdot 11 + 7)$

> ✅ Con soli 2 degli $n=4$ share è possibile ricostruire esattamente $s$. Con un solo share, il segreto rimane perfettamente nascosto.
