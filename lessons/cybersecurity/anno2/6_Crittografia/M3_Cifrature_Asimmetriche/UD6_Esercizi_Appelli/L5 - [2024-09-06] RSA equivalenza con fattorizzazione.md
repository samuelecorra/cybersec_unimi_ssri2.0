# RSA equivalenza con fattorizzazione — Esercizio d'esame

> 🗂️ **Fonte:** appello del **6 settembre 2024**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 6 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L6%20-%20Appello%206%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — RSA: equivalenza con fattorizzazione e ottimizzazione (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica RSA.**
>
> a. (10) Discutere l'equivalenza o meno tra il problema RSA e la fattorizzazione dei numeri primi.
>
> b. (10) Discutere i metodi per ottimizzare il calcolo esponenziale nella fase di cifratura.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — sicurezza RSA, esponenziazione rapida

### Soluzione dettagliata

#### Parte a — Equivalenza tra problema RSA e fattorizzazione (10 punti)

**Il problema RSA (RSA Problem):**

Dato il modulo $n$, l'esponente pubblico $e$, e il ciphertext $C = M^e \bmod n$, trovare $M$.

**Il problema della fattorizzazione (Integer Factorization Problem):**

Dato $n = pq$, trovare $p$ e $q$.

**Relazione tra i due problemi:**

**Fattorizzare → Rompere RSA:**

Se si riesce a fattorizzare $n = pq$, si possono calcolare $\varphi(n) = (p-1)(q-1)$ e quindi la chiave privata $d = e^{-1} \bmod \varphi(n)$ tramite l'algoritmo di Euclide esteso. Avendo $d$, si può decifrare qualsiasi ciphertext: $M = C^d \bmod n$.

Quindi: **fattorizzare $n$ implica rompere RSA.**

**Rompere RSA → Fattorizzare:**

La direzione opposta è più sottile. È noto che:
- Se si conosce $\varphi(n)$ (la funzione di Eulero), si può fattorizzare $n$ (come mostrato nell'Appello L3, D3b).
- Se si conosce $d$ (la chiave privata), si può calcolare $ed - 1 = k\varphi(n)$ per qualche $k$, e da lì fattorizzare $n$ con alta probabilità usando tecniche algebriche.

Quindi: **rompere RSA (trovare $d$ o $\varphi(n)$) implica poter fattorizzare $n$.**

**Conclusione sull'equivalenza:**

Rompere RSA **nell'accezione di trovare la chiave privata $d$** è computazionalmente equivalente a fattorizzare $n$: ognuno dei due problemi si riduce polinomialmente all'altro.

**Tuttavia**, rompere RSA **nell'accezione più debole di decriptare un singolo ciphertext** (recuperare $M$ da $C$ senza necessariamente trovare $d$) potrebbe essere più facile della fattorizzazione. Non è noto se il problema RSA (in questa forma debole) sia computazionalmente equivalente alla fattorizzazione:
- Non si sa se esistano algoritmi che riescano a decifrare messaggi RSA senza fattorizzare $n$.
- La congettura è che siano equivalenti, ma non è dimostrata.

> ⚠️ Quindi l'affermazione "la sicurezza di RSA si basa sulla difficoltà della fattorizzazione" è sostanzialmente corretta ma non precisissima: la sicurezza si basa sulla difficoltà del problema RSA, che è almeno difficile quanto la fattorizzazione (ma potrebbero non essere esattamente equivalenti nel senso più forte).

> 📌 Dal punto di vista pratico: se qualcuno riuscisse a fattorizzare efficientemente numeri di 2048+ bit, RSA sarebbe completamente compromesso. Viceversa, non si conoscono attacchi a RSA che non richiedano (implicitamente o esplicitamente) la fattorizzazione.

#### Parte b — Ottimizzazione del calcolo esponenziale in RSA (10 punti)

**Problema:** calcolare $M^e \bmod n$ (cifratura) o $C^d \bmod n$ (decifratura) con $e$, $d$ dell'ordine di $2^{1024}$ o più. La moltiplicazione ripetuta richiederebbe $e$ (o $d$) passi: computazionalmente impossibile.

**1. Esponenziazione rapida (Square-and-Multiply / Fast Exponentiation):**

L'algoritmo principale per ridurre il numero di moltiplicazioni da $O(e)$ a $O(\log e)$.

**Algoritmo (versione bit-by-bit, left-to-right):**

```
Input: base M, esponente e, modulo n
Scrivi e in binario: e = (b_k b_{k-1} ... b_1 b_0)_2

result = 1
Per i = k downto 0:
    result = result^2 mod n   // square
    if b_i == 1:
        result = result * M mod n  // multiply
Output: result
```

**Complessità:** richiede al massimo $2 \log_2 e$ moltiplicazioni modulari (al più $\log_2 e$ operazioni di square + al più $\log_2 e$ operazioni di multiply). Per $e \approx 2^{1024}$, questo è $\approx 2048$ moltiplicazioni invece di $2^{1024}$.

**Esempio (già visto negli appelli precedenti):** $3^{23} \bmod 55$ con $23 = 10111_2$: calcolo in $\lfloor \log_2 23 \rfloor = 4$ passi di square + operazioni di multiply.

**2. Scelta di $e$ piccolo (esponente pubblico ridotto):**

Per la cifratura (che usa $e$), scegliere un $e$ piccolo riduce il costo. Il valore standard è $e = 65537 = 2^{16} + 1$, che ha solo 2 bit a 1 nella rappresentazione binaria. La cifratura richiede 16 operazioni di square + 1 di multiply = 17 operazioni totali.

> ⚠️ L'uso di $e$ molto piccolo (come $e=3$) può essere vulnerabile in certi scenari (broadcast attack, low-exponent attack). $e=65537$ è un buon compromesso tra efficienza e sicurezza.

**3. Teorema Cinese dei Resti (CRT) per la decifratura:**

La decifratura usa $d$ che è dell'ordine di $\varphi(n) \approx n$, quindi potenzialmente molto lento. Usando il CRT, si divide il calcolo modulo $n$ in due calcoli modulo $p$ e $q$:

$$M_p = C^{d_p} \bmod p \quad \text{con } d_p = d \bmod (p-1)$$
$$M_q = C^{d_q} \bmod q \quad \text{con } d_q = d \bmod (q-1)$$

Poi si combina con il CRT per trovare $M \bmod n$.

**Vantaggi del CRT:**
- $p$ e $q$ sono circa la metà della dimensione di $n$ (in bit), quindi $d_p$ e $d_q$ sono circa metà di $d$.
- L'esponenziazione modulo un numero di $n/2$ bit è circa 4 volte più veloce di quella modulo $n$ (il costo è quadratico nella dimensione).
- Complessivamente, il CRT accelera la decifratura di un fattore ~4.

**4. Uso delle finestre (Windowed Exponentiation):**

Invece di elaborare un bit alla volta, si elaborano $w$ bit alla volta (window di dimensione $w$). Si precalcolano $2^w$ multipli di $M$ e si usano per ridurre il numero di operazioni di multiply. Per $w=4$, si risparmiano circa il 25% delle moltiplicazioni rispetto al metodo standard.

**5. Montgomery Multiplication:**

Tecnica specializzata per eseguire moltiplicazioni modulari senza divisioni esplicite (che sono costose). Converte i numeri in "forma di Montgomery" dove la riduzione modulare diventa uno shift. Molto usata in implementazioni hardware e software ottimizzate.

> ✅ **Riepilogo delle ottimizzazioni:**
> 1. Square-and-multiply: $O(\log e)$ invece di $O(e)$ moltiplicazioni
> 2. $e = 65537$: cifratura velocissima (17 operazioni)
> 3. CRT per decifratura: accelerazione 4×
> 4. Windowed exponentiation: ulteriore riduzione del 20-30%
> 5. Montgomery multiplication: riduzione modulare efficiente
