# Secret Sharing — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 luglio 2024**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L4%20-%20Appello%205%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(n, n)$.
>
> b. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k, n)$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir: (t,n) secret sharing
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Scenari%20e%20applicazioni.md) — concetto di secret sharing

### Soluzione dettagliata

#### Parte a — Schema $(n, n)$ di secret sharing (10 punti)

Uno **schema $(n, n)$** (o schema a soglia $n$ su $n$) è un sistema di secret sharing in cui il segreto $S$ viene distribuito tra $n$ partecipanti, e sono necessari **tutti gli $n$** partecipanti per ricostruirlo. Qualsiasi sottoinsieme di $n-1$ o meno partecipanti non ottiene alcuna informazione su $S$.

**Schema basato sull'XOR (o su $\mathbb{Z}_p$):**

**Fase di distribuzione:**
1. Il dealer sceglie un segreto $S \in \mathbb{Z}_p$ (dove $p$ è un numero primo abbastanza grande da contenere $S$).
2. Il dealer genera $n-1$ valori casuali $s_1, s_2, \ldots, s_{n-1}$ uniformemente distribuiti in $\mathbb{Z}_p$.
3. Il dealer calcola l'ultima share come:
$$s_n = S - s_1 - s_2 - \cdots - s_{n-1} \bmod p$$
4. La share $s_i$ viene consegnata privatamente al partecipante $i$, per $i = 1, \ldots, n$.

**Fase di ricostruzione:**

Tutti gli $n$ partecipanti si riuniscono e sommano le loro share:

$$s_1 + s_2 + \cdots + s_n \equiv S \pmod{p}$$

**Sicurezza:** qualsiasi insieme di $n-1$ partecipanti non ha informazioni su $S$: avendo $n-1$ share, ci sono $p$ possibili valori per la share mancante, e quindi $p$ possibili segreti ugualmente probabili.

> 📌 Questo schema (XOR generalizzato o somma modulare) è **perfetto** (information-theoretically secure) ma funziona solo con soglia $n$: tutti i partecipanti devono essere presenti. Se anche solo uno non è disponibile, il segreto non è recuperabile.

**Variante con XOR:**

Se $S \in \{0,1\}^n$, si usa XOR invece della somma modulare:
- $s_1, \ldots, s_{n-1}$ casuali; $s_n = S \oplus s_1 \oplus \cdots \oplus s_{n-1}$
- Ricostruzione: $s_1 \oplus s_2 \oplus \cdots \oplus s_n = S$

#### Parte b — Schema $(k, n)$ di Shamir (10 punti)

Uno **schema $(k, n)$** (o schema a soglia $k$ su $n$, con $k \leq n$) distribuisce il segreto $S$ tra $n$ partecipanti, e qualsiasi **sottoinsieme di almeno $k$** partecipanti può ricostruire $S$. Qualsiasi sottoinsieme di $k-1$ o meno partecipanti non ottiene alcuna informazione su $S$.

**Schema di Shamir (1979):**

Si basa sul fatto che un polinomio di grado $k-1$ è univocamente determinato da $k$ punti.

**Fase di distribuzione:**

1. Scegliere un primo $p > \max(S, n)$.
2. Il dealer sceglie un polinomio di grado $k-1$ a coefficienti casuali in $\mathbb{Z}_p$:
$$f(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{k-1} x^{k-1} \bmod p$$
dove $a_0 = S$ (il segreto è il termine noto del polinomio).
3. I coefficienti $a_1, \ldots, a_{k-1}$ sono scelti uniformemente a caso in $\mathbb{Z}_p$.
4. La share del partecipante $i$ è il punto $(i, f(i) \bmod p)$, consegnato privatamente.

**Fase di ricostruzione:**

Con $k$ share $(x_1, y_1), (x_2, y_2), \ldots, (x_k, y_k)$, si usa **l'interpolazione di Lagrange** per recuperare $f(0) = S$:

$$S = f(0) = \sum_{i=1}^{k} y_i \cdot L_i(0) \bmod p$$

dove i **polinomi di Lagrange base** sono:

$$L_i(0) = \prod_{\substack{j=1 \\ j \neq i}}^{k} \frac{0 - x_j}{x_i - x_j} = \prod_{\substack{j=1 \\ j \neq i}}^{k} \frac{-x_j}{x_i - x_j} \bmod p$$

Tutte le divisioni si intendono come moltiplicazioni per l'inverso modulare in $\mathbb{Z}_p$.

**Sicurezza dello schema di Shamir:**

Lo schema è **perfettamente sicuro** (information-theoretically secure): qualsiasi insieme di $k-1$ partecipanti non ha informazioni su $S$. Formalmente, per ogni valore di segreto $S'$, esiste un unico polinomio di grado $k-1$ che passa per i $k-1$ punti noti e ha $f(0) = S'$.

> 📌 **Proprietà fondamentale:** un polinomio di grado $k-1$ è univocamente determinato da $k$ punti (teorema di interpolazione di Lagrange). Con $k-1$ punti esistono infiniti polinomi di grado $k-1$ compatibili, uno per ogni possibile segreto.

**Confronto tra $(n,n)$ e $(k,n)$:**

| Proprietà | Schema $(n,n)$ | Schema $(k,n)$ di Shamir |
|-----------|---------------|--------------------------|
| Partecipanti necessari | Tutti $n$ | Qualsiasi $k \geq k$ |
| Struttura matematica | Somma/XOR modulare | Polinomio di grado $k-1$ |
| Flessibilità | Nessuna (tutti devono partecipare) | Alta (qualsiasi sottoinsieme di $k$) |
| Sicurezza | Perfetta | Perfetta |
| Overhead | Minimo | Interpolazione di Lagrange |

> ⚠️ Nello schema di Shamir, i calcoli della ricostruzione coinvolgono inversioni modulari (per le divisioni nei termini di Lagrange). È fondamentale eseguire tutti i calcoli in $\mathbb{Z}_p$ con $p$ primo.
