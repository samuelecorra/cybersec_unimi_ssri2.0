# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **15 luglio 2025**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 15 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L4%20-%20Appello%2015%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione nel caso $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_7$ e ricostruire il segreto condiviso, sapendo le share in possesso di Alice sono $s_1=6,\ s_2=4,\ s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_7$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir, variante (n,n)

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$: distribuzione e ricostruzione (10 punti)

(Si veda L2 Domanda 4a per la trattazione completa. Sintesi:)

**Distribuzione:** dealer sceglie $f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod{p}$ con $a_i$ casuali e $f(0)=s$. Partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** con $k$ share $(i_j, s_{i_j})$:

$$s = \sum_{j=1}^{k} s_{i_j} \cdot \lambda_j \pmod{p}, \quad \lambda_j = \prod_{\substack{l=1\\l\neq j}}^{k} \frac{-i_l}{i_j - i_l} \pmod{p}$$

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_7$: ricostruzione con le 3 share di Alice (10 punti)

**Dati:**
- Schema $(3,3)$: threshold $k=3$, partecipanti $n=3$ (tutti e 3 necessari)
- Campo: $\mathbb{Z}_7$ ($p=7$)
- Share: $s_1 = f(1) = 6$, $s_2 = f(2) = 4$, $s_3 = f(3) = 2$
- Punti: $(1, 6)$, $(2, 4)$, $(3, 2)$

Con $k=3$, il polinomio è di grado $k-1=2$: $f(x) = s + a_1 x + a_2 x^2$.

**Interpolazione di Lagrange per trovare $f(0)$:**

$$f(0) = 6 \cdot \lambda_1 + 4 \cdot \lambda_2 + 2 \cdot \lambda_3 \pmod{7}$$

**Calcolo di $\lambda_1$** (punto $x_1=1$, valutiamo in $x=0$):

$$\lambda_1 = \frac{(0-2)(0-3)}{(1-2)(1-3)} = \frac{(-2)(-3)}{(-1)(-2)} = \frac{6}{2} = 3 \pmod{7}$$

In $\mathbb{Z}_7$: $\frac{6}{2} = 6 \cdot 2^{-1}$. Inverso di 2 in $\mathbb{Z}_7$: $2 \cdot 4 = 8 \equiv 1 \pmod 7$, quindi $2^{-1} = 4$.

$$\lambda_1 = 6 \cdot 4 = 24 \bmod 7 = 3$$

**Calcolo di $\lambda_2$** (punto $x_2=2$, valutiamo in $x=0$):

$$\lambda_2 = \frac{(0-1)(0-3)}{(2-1)(2-3)} = \frac{(-1)(-3)}{(1)(-1)} = \frac{3}{-1} = -3 \equiv 4 \pmod{7}$$

**Calcolo di $\lambda_3$** (punto $x_3=3$, valutiamo in $x=0$):

$$\lambda_3 = \frac{(0-1)(0-2)}{(3-1)(3-2)} = \frac{(-1)(-2)}{(2)(1)} = \frac{2}{2} = 1 \pmod{7}$$

**Calcolo del segreto:**

$$f(0) = 6 \cdot 3 + 4 \cdot 4 + 2 \cdot 1 \pmod{7}$$

$$= 18 + 16 + 2 \pmod{7} = 36 \pmod{7}$$

$36 = 5 \cdot 7 + 1$, quindi $36 \bmod 7 = 1$.

**Il segreto è $s = 1$.**

**Verifica:** Proviamo a trovare il polinomio $f(x) = 1 + a_1 x + a_2 x^2 \pmod 7$ che soddisfa i tre punti:

- $f(1) = 1 + a_1 + a_2 = 6 \Rightarrow a_1 + a_2 = 5 \pmod 7$
- $f(2) = 1 + 2a_1 + 4a_2 = 4 \Rightarrow 2a_1 + 4a_2 = 3 \pmod 7$
- $f(3) = 1 + 3a_1 + 9a_2 = 2 \Rightarrow 3a_1 + 2a_2 = 1 \pmod 7$ (poiché $9 \bmod 7 = 2$)

Dalla prima: $a_1 = 5 - a_2$. Sostituendo nella seconda:
$2(5 - a_2) + 4a_2 = 3 \Rightarrow 10 - 2a_2 + 4a_2 = 3 \Rightarrow 10 + 2a_2 = 3 \Rightarrow 2a_2 = 3 - 10 = -7 \equiv 0 \pmod 7 \Rightarrow a_2 = 0$.

Quindi $a_1 = 5$ e il polinomio è $f(x) = 1 + 5x \pmod 7$.

Verifica: $f(1)=6$ ✓, $f(2)=11 \bmod 7=4$ ✓, $f(3)=16 \bmod 7=2$ ✓.

Il polinomio è lineare (grado 1), non grado 2 come atteso per $(3,3)$: questo è consistente — un polinomio di grado $\leq k-1$ è valido.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_7$: ricostruzione con share $s(1)=3$, $s(2)=4$ (10 punti)

**Dati:**
- Campo: $\mathbb{Z}_7$ ($p=7$)
- Share: $(1, 3)$ e $(2, 4)$

**Interpolazione di Lagrange:**

$$f(0) = 3 \cdot \lambda_1 + 4 \cdot \lambda_2 \pmod 7$$

$$\lambda_1 = \frac{0-2}{1-2} = \frac{-2}{-1} = 2 \pmod 7$$

$$\lambda_2 = \frac{0-1}{2-1} = \frac{-1}{1} = -1 \equiv 6 \pmod 7$$

$$f(0) = 3 \cdot 2 + 4 \cdot 6 = 6 + 24 = 30 \pmod 7$$

$30 = 4 \cdot 7 + 2$, quindi $30 \bmod 7 = 2$.

**Il segreto è $s = 2$.**

**Verifica:** $f(x) = 2 + a_1 x$. Da $f(1) = 2 + a_1 = 3 \Rightarrow a_1 = 1$. Polinomio: $f(x) = 2 + x$.
- $f(1) = 3$ ✓
- $f(2) = 4$ ✓

> 💡 Nota: la coppia di share $(s(1)=3, s(2)=4)$ è identica all'appello del 10/02/2025 e dell'appello del 05/09/2025, ma con modulo diverso ($\mathbb{Z}_7$ invece di $\mathbb{Z}_{11}$). Il segreto cambia (2 invece di 1). Questo mostra l'importanza del campo nel calcolo degli inversi di Lagrange.
