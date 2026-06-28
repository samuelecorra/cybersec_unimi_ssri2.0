# Funzioni hash: one-wayness di $H(x)=\text{DES}_k(x)$ — Esercizio d'esame

> 🗂️ **Fonte:** appello del **21 marzo 2026**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 21 marzo 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L3%20-%20Appello%2021%20marzo%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Funzioni Hash e One-Wayness (25 punti)

### Traccia originale

> **(25 punti) Funzioni Hash.**
>
> a. (10 punti) Descrivere le principali proprietà di sicurezza delle funzioni hash crittografiche.
>
> b. (15 punti) Sia $H(x) = \text{DES}_k(x)$ per $k$ nota. Discutere la proprietà di one-wayness.
> Ripetere l'analisi per $k$ non nota. Come cambia la sicurezza?

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — one-wayness, preimage, collision resistance
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacchi e complessità

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

Una funzione hash crittografica $H: \{0,1\}^* \to \{0,1\}^m$ deve soddisfare:

**1. One-wayness (resistenza alla preimage):**
Dato $h = H(x)$, trovare $x'$ tale che $H(x') = h$ deve richiedere $O(2^m)$ operazioni.

**2. Second-preimage resistance:**
Dato $x$, trovare $x' \neq x$ tale che $H(x') = H(x)$ deve richiedere $O(2^m)$ operazioni.

**3. Collision resistance:**
Trovare qualsiasi coppia $(x, x')$ con $x \neq x'$ e $H(x) = H(x')$ deve richiedere $O(2^{m/2})$ operazioni (limite imposto dal paradosso del compleanno).

**4. Effetto valanga:**
Una singola modifica di bit nell'input deve alterare in modo imprevedibile circa metà dei bit dell'output.

| Proprietà | Complessità attaccante |
|---|---|
| One-wayness | $O(2^m)$ |
| Second-preimage | $O(2^m)$ |
| Collision | $O(2^{m/2})$ |

#### Parte b — One-wayness di $H(x) = \text{DES}_k(x)$ (15 punti)

**Caratteristiche di DES:** blocchi di 64 bit, chiave di 56 bit. Per $k$ fissa, $\text{DES}_k$ è una **permutazione** (biiezione): dominio = codominio = $\{0,1\}^{64}$, esiste $\text{DES}_k^{-1}$ calcolabile in tempo polinomiale.

---

**Caso 1: $k$ nota (fissa e conosciuta)**

L'inversa di $\text{DES}_k$ è pubblicamente calcolabile:
$$x = \text{DES}_k^{-1}(h) \quad \text{in } O(1) \text{ operazioni DES}$$

Dato qualsiasi hash $h$, il preimage $x$ si trova immediatamente invertendo DES con la chiave nota.

→ $H$ **NON è one-way** con $k$ nota.

---

**Caso 2: $k$ non nota (fissa ma segreta)**

L'avversario conosce $h = \text{DES}_k(x)$ ma non conosce $k$. Per trovare $x$:

- **Ricerca esaustiva** su tutte le $2^{56}$ chiavi DES possibili.
- Per ciascuna $k'$: calcolare $\text{DES}_{k'}^{-1}(h)$, verificare plausibilità.
- Complessità: $O(2^{56})$ operazioni.

> ⚠️ $2^{56} \approx 7{,}2 \times 10^{16}$. Con hardware specializzato (es. cluster FPGA), DES a 56 bit è **praticamente rompibile** in meno di 24 ore al giorno d'oggi (già dimostrato nel 1998 con EFF Deep Crack). Quindi $H(x) = \text{DES}_k(x)$ con $k$ ignota non è sicuro in modo assoluto, ma è **computazionalmente one-way** rispetto alla sola definizione formale.

---

**Confronto con AES:**

| Parametro | DES | AES-128 |
|---|---|---|
| Lunghezza chiave | 56 bit | 128 bit |
| $k$ nota: one-way? | ✗ NO | ✗ NO |
| $k$ non nota: one-way? | Marginale ($2^{56}$) | ✓ SÌ ($2^{128}$) |
| Sicurezza pratica con $k$ ignota | Rotta dal 1998 | Sicura oggi |

**Riepilogo:**

| Caso | One-way? | Motivazione |
|---|---|---|
| $k$ nota | ✗ NO | $x = \text{DES}_k^{-1}(h)$ in O(1) |
| $k$ non nota | Formalmente sì, praticamente no | Brute-force: $2^{56}$ — fattibile con risorse moderne |

> 💡 Questo esercizio mostra perché DES (56-bit) è insicuro anche come building block per funzioni hash: la chiave troppo corta vanifica la one-wayness anche nel caso in cui $k$ è segreta. AES-128 con chiave ignota offre $2^{128}$ — sicuro per standard attuali.
