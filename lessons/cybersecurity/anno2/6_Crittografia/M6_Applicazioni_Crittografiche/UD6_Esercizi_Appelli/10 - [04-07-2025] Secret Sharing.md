# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **4 luglio 2025**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 4 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L3%20-%20Appello%204%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti). Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(n,n)$.
>
> b. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> c. (10 punti) Descrivere l'applicazione dello schema $(2,3)$ considerando $\mathbb{Z}_{19}$ e il segreto $s=3$. (scegliere dei coefficienti casuali)

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema completo di Shamir

### Soluzione dettagliata

#### Parte a — Schema $(n,n)$: distribuzione e ricostruzione (10 punti)

Nello schema $(n,n)$ il segreto $s$ è diviso tra $n$ partecipanti e **tutti** gli $n$ partecipanti sono necessari per ricostruirlo (nessun sottoinsieme più piccolo funziona).

**Fase di distribuzione:**

1. Si sceglie un primo $p > s$ e $p > n$.
2. Si generano $n-1$ valori casuali $r_1, r_2, \ldots, r_{n-1}$ uniformemente in $\mathbb{Z}_p$.
3. Si calcola l'$n$-esima share come:

$$r_n = s \oplus r_1 \oplus r_2 \oplus \ldots \oplus r_{n-1} \pmod{p}$$

(oppure, nella variante additiva: $r_n = s - r_1 - r_2 - \ldots - r_{n-1} \pmod{p}$)

4. Ogni partecipante $P_i$ riceve la share $r_i$ in modo privato.

**Fase di ricostruzione:**

Tutti e $n$ i partecipanti si riuniscono e combinano le loro share:

$$s = r_1 \oplus r_2 \oplus \ldots \oplus r_n \pmod{p}$$

(nella variante additiva: $s = r_1 + r_2 + \ldots + r_n \pmod{p}$)

**Sicurezza:** con $n-1$ share (un partecipante assente), il segreto è completamente nascosto: per qualsiasi valore di $s' \in \mathbb{Z}_p$, esiste esattamente un valore della share mancante che produce $s'$. Il sistema ha sicurezza perfetta (information-theoretic).

> 📌 Lo schema $(n,n)$ è il caso limite del one-time pad distribuito tra $n$ partecipanti.

#### Parte b — Schema $(k,n)$ di Shamir: distribuzione e ricostruzione (10 punti)

(Si veda la soluzione completa in L2, Domanda 4a. Qui sintetizzo per completezza.)

**Distribuzione:** il dealer sceglie un polinomio $f(x)$ di grado $k-1$ su $\mathbb{Z}_p$ con $f(0) = s$:

$$f(x) = s + a_1 x + a_2 x^2 + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

Il partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi $k$ partecipanti usano interpolazione di Lagrange per calcolare $s = f(0)$.

#### Parte c — Applicazione pratica schema $(2,3)$ in $\mathbb{Z}_{19}$, segreto $s=3$ (10 punti)

**Setup:**
- Schema $(2,3)$: $k=2$ (threshold), $n=3$ (partecipanti)
- Campo: $\mathbb{Z}_{19}$ ($p=19$)
- Segreto: $s=3$
- Polinomio di grado $k-1=1$: $f(x) = 3 + a_1 x \pmod{19}$

**Scelta del coefficiente casuale:** sia $a_1 = 5$ (esempio di coefficiente scelto casualmente).

$$f(x) = 3 + 5x \pmod{19}$$

**Calcolo delle share per i 3 partecipanti:**

$$s_1 = f(1) = 3 + 5 \cdot 1 = 8 \pmod{19} = 8$$

$$s_2 = f(2) = 3 + 5 \cdot 2 = 3 + 10 = 13 \pmod{19} = 13$$

$$s_3 = f(3) = 3 + 5 \cdot 3 = 3 + 15 = 18 \pmod{19} = 18$$

**Distribuzione:**
- $P_1$ riceve $(1, 8)$
- $P_2$ riceve $(2, 13)$
- $P_3$ riceve $(3, 18)$

**Ricostruzione con $P_1$ e $P_2$ (usando le share $(1,8)$ e $(2,13)$):**

Con $k=2$:

$$s = f(0) = s_1 \cdot \lambda_1 + s_2 \cdot \lambda_2 \pmod{19}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{19}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 18 \pmod{19}$$

$$s = 8 \cdot 2 + 13 \cdot 18 = 16 + 234 = 250 \pmod{19}$$

$250 = 13 \cdot 19 + 3$, quindi $250 \bmod 19 = 3$ ✓

**Il segreto è $s = 3$** (verificato).

**Ricostruzione alternativa con $P_1$ e $P_3$ (verifica):**

Con punti $(1,8)$ e $(3,18)$:

$$\lambda_1 = \frac{0-3}{1-3} = \frac{-3}{-2} = 3 \cdot 2^{-1} \pmod{19}$$

$2^{-1} \pmod{19}$: $2 \cdot 10 = 20 \equiv 1 \pmod{19}$, quindi $2^{-1} = 10$.

$$\lambda_1 = 3 \cdot 10 = 30 \bmod 19 = 11$$

$$\lambda_3 = \frac{0-1}{3-1} = \frac{-1}{2} = (-1) \cdot 2^{-1} = (-1) \cdot 10 = -10 \equiv 9 \pmod{19}$$

$$s = 8 \cdot 11 + 18 \cdot 9 = 88 + 162 = 250 \pmod{19} = 3 \checkmark$$
