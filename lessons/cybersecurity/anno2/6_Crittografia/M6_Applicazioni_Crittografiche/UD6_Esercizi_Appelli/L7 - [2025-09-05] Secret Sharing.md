# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 settembre 2025**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L5%20-%20Appello%205%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> c. (10 punti). Siano dati i seguenti share in uno schema $(4,4)$: $P_1=5,\ P_2=3,\ P_3=4,\ P_4=9$. Ricostruire il segreto, sapendo che si opera in $\mathbb{Z}_{13}$.
>
> d. (15 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$, ricostruire il segreto avendo le due share $P_1=3,\ P_2=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema (n,n) e (k,n)

### Soluzione dettagliata

#### Parte c — Schema $(4,4)$ in $\mathbb{Z}_{13}$: ricostruzione (10 punti)

**Schema $(4,4)$:** tutti e 4 i partecipanti partecipano. Come descritto in L3 (Domanda 4a), nello schema $(n,n)$ le share sono generate con il meccanismo additivo:

$$s = P_1 + P_2 + P_3 + P_4 \pmod{p}$$

(Oppure con XOR: $s = P_1 \oplus P_2 \oplus P_3 \oplus P_4$; usiamo la versione additiva modulo $p$.)

**Calcolo del segreto:**

$$s = P_1 + P_2 + P_3 + P_4 \pmod{13}$$

$$= 5 + 3 + 4 + 9 \pmod{13}$$

$$= 21 \pmod{13}$$

$21 = 1 \cdot 13 + 8$, quindi $21 \bmod 13 = 8$.

**Il segreto è $s = 8$.**

> 📌 Nello schema $(n,n)$ additivo, la ricostruzione è semplicissima: somma di tutte le share modulo $p$. Non serve l'interpolazione di Lagrange.

**Verifica della coerenza:** se il segreto fosse stato distribuito con il metodo $(n,n)$ additivo, la share $P_4$ sarebbe stata calcolata come $P_4 = s - P_1 - P_2 - P_3 \bmod p = 8 - 5 - 3 - 4 \bmod 13 = -4 \bmod 13 = 9$ ✓ (coerente con $P_4 = 9$).

#### Parte d — Schema $(2,3)$ in $\mathbb{Z}_{11}$: ricostruzione con $P_1=3$, $P_2=4$ (15 punti)

**Dati:**
- Schema $(2,3)$: threshold $k=2$, partecipanti $n=3$
- Campo: $\mathbb{Z}_{11}$ ($p=11$)
- Share: $(1, P_1) = (1, 3)$ e $(2, P_2) = (2, 4)$

Con $k=2$, il polinomio è di grado 1: $f(x) = s + a_1 x \pmod{11}$.

**Interpolazione di Lagrange:**

$$f(0) = P_1 \cdot \lambda_1 + P_2 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 4 \cdot 10 = 6 + 40 = 46 \pmod{11}$$

$46 = 4 \cdot 11 + 2$, quindi $46 \bmod 11 = 2$.

**Il segreto è $s = 2$.**

> 💡 Stessa coppia di share $(1,3)$ e $(2,4)$ come nell'appello del 10/02/2025 (D4b) e dell'appello del 15/07/2025 (D4c, in $\mathbb{Z}_7$). Il segreto dipende dal campo: in $\mathbb{Z}_{11}$ è 2, in $\mathbb{Z}_7$ è 2, in $\mathbb{Z}_{11}$ del 10/02 è pure 2. Questo perché la retta passante per $(1,3)$ e $(2,4)$ ha equazione $f(x) = 2 + x$ su qualsiasi campo in cui le operazioni siano consistenti.

**Verifica:** $f(x) = 2 + x$. $f(1) = 3$ ✓, $f(2) = 4$ ✓, $f(0) = 2$ ✓.
