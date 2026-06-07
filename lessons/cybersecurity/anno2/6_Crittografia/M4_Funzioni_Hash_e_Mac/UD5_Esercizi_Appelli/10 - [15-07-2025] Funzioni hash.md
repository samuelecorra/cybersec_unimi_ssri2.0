# Funzioni hash — Esercizio d'esame

> 🗂️ **Fonte:** appello del **15 luglio 2025**, Domanda 2 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 15 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L4%20-%20Appello%2015%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Funzioni hash (20 punti)

### Traccia originale

> **(20 punti) Funzioni hash.**
>
> a. (10 punti) Illustrare le proprietà di sicurezza delle funzioni hash.
>
> b. (10 punti) Dimostrare che la funzione hash $H(x) = 2x + 8 \bmod 10$ non ha la proprietà di resistenza debole alle collisioni, mostrando come sia facile trovare una collisione per $H(6)$.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

(Identica alla Domanda 3a dell'appello del 20/01/2025; si veda L1 per la trattazione completa.)

Le tre proprietà fondamentali sono:

**1. Resistenza alla preimmagine (One-wayness):** dato $h = H(x)$, è computazionalmente difficile trovare $x$.

**2. Resistenza debole alle collisioni (Second preimage resistance):** dato $x$, è computazionalmente difficile trovare $x' \neq x$ con $H(x') = H(x)$.

**3. Resistenza forte alle collisioni (Collision resistance):** è computazionalmente difficile trovare qualsiasi coppia $(x, x')$ con $x \neq x'$ e $H(x) = H(x')$.

Le tre proprietà sono ordinate per forza decrescente: collision resistance $\Rightarrow$ second preimage resistance $\Rightarrow$ preimage resistance.

#### Parte b — $H(x) = 2x + 8 \bmod 10$ non ha second preimage resistance (10 punti)

**Calcolo di $H(6)$:**

$$H(6) = 2 \cdot 6 + 8 \bmod 10 = 12 + 8 \bmod 10 = 20 \bmod 10 = 0$$

**Trovare una collisione:** dobbiamo trovare $x' \neq 6$ tale che $H(x') = 0$:

$$2x' + 8 \equiv 0 \pmod{10}$$

$$2x' \equiv -8 \equiv 2 \pmod{10}$$

$$x' \equiv 1 \pmod{5}$$

Poiché $\gcd(2, 10) = 2$ e $2 \mid 2$, ci sono $\gcd(2,10) = 2$ soluzioni in $\mathbb{Z}_{10}$: $x' = 1$ e $x' = 6$.

La soluzione diversa da 6 è $x' = 1$.

**Verifica:** $H(1) = 2 \cdot 1 + 8 \bmod 10 = 10 \bmod 10 = 0 = H(6)$ ✓

**Collisione trovata:** $H(6) = H(1) = 0$, con $6 \neq 1$.

**Perché manca la second preimage resistance:**

$H(x) = 2x + 8 \bmod 10$ non è una biiezione in $\mathbb{Z}_{10}$ perché $\gcd(2, 10) = 2 \neq 1$: la funzione è 2-a-1 (ogni valore dell'immagine ha esattamente due preimmagini, che differiscono di 5). Trovare una seconda preimmagine per qualsiasi valore è banale.

In generale:

$$H(x) = H(x + 5) \quad \forall x \in \mathbb{Z}_{10}$$

Dato $x$, la seconda preimmagine è sempre $x' = (x + 5) \bmod 10$.

> ⚠️ Nota: questa stessa osservazione si applica all'appello del 20/01/2025 (con $H(x)=5x+12 \bmod 20$): funzioni hash lineari con coefficiente non coprimo al modulo hanno sempre collisioni strutturali banali.
