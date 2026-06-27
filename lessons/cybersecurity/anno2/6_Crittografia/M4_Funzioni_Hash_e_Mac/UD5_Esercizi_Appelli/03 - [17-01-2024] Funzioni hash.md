# Funzioni hash — Esercizio d'esame

> 🗂️ **Fonte:** appello del **17 gennaio 2024**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 17 gennaio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L1%20-%20Appello%2017%20gennaio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Funzioni hash (20 punti)

### Traccia originale

> **(20 punti) Funzioni hash.**
>
> a. (10 punti) Illustrare le proprietà di sicurezza delle funzioni hash.
>
> b. (10 punti) Dimostrare che la funzione hash $H(x) = 2x + 15 \bmod 20$ non ha la proprietà di resistenza debole alle collisioni, mostrando come sia facile trovare una collisione per $H(6)$.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno e second preimage

### Soluzione dettagliata

#### Parte a — Proprietà di sicurezza delle funzioni hash (10 punti)

Una funzione hash crittografica $H: \{0,1\}^* \to \{0,1\}^n$ deve soddisfare tre proprietà di sicurezza fondamentali:

**1. Resistenza alla preimmagine (One-wayness / Preimage resistance):**

Data un'immagine $h$, deve essere computazionalmente difficile trovare un $x$ tale che $H(x) = h$.

$$\forall h,\quad \text{difficile trovare } x \text{ t.c. } H(x) = h$$

Questo garantisce la non invertibilità: dall'hash non si può risalire al messaggio originale.

**2. Resistenza debole alle collisioni (Second preimage resistance):**

Dato un messaggio $x$, deve essere computazionalmente difficile trovare un messaggio diverso $x' \neq x$ tale che $H(x') = H(x)$.

$$\forall x,\quad \text{difficile trovare } x' \neq x \text{ t.c. } H(x') = H(x)$$

Questa proprietà protegge l'integrità: un avversario non può sostituire un messaggio autenticato con uno diverso mantenendo lo stesso hash.

**3. Resistenza forte alle collisioni (Strong collision resistance):**

Deve essere computazionalmente difficile trovare **qualsiasi** coppia $(x, x')$ con $x \neq x'$ tale che $H(x) = H(x')$.

$$\text{difficile trovare } (x, x') \text{ con } x \neq x' \text{ t.c. } H(x) = H(x')$$

> 📌 **Gerarchia delle proprietà:** la resistenza forte alle collisioni implica la resistenza debole (second preimage), che a sua volta implica la resistenza alla preimmagine. Rompere la preimage non implica rompere le collisioni, ma rompere le collisioni non implica rompere la second preimage. Formalmente: strong collision resistance $\Rightarrow$ second preimage resistance $\Rightarrow$ preimage resistance.

> ⚠️ Il **paradosso del compleanno** mostra che trovare una collisione forte richiede solo $O(2^{n/2})$ tentativi (non $O(2^n)$). Per questo motivo gli hash moderni usano almeno 256 bit di output (SHA-256, SHA-3).

#### Parte b — $H(x) = 2x + 15 \bmod 20$ manca di second preimage resistance (10 punti)

**Calcolo di $H(6)$:**

$$H(6) = 2 \cdot 6 + 15 \bmod 20 = 12 + 15 \bmod 20 = 27 \bmod 20 = 7$$

**Trovare una second preimage per $H(6) = 7$:**

Dobbiamo trovare $x' \neq 6$ tale che $H(x') = 7$, ovvero:

$$2x' + 15 \equiv 7 \pmod{20}$$

$$2x' \equiv 7 - 15 \equiv -8 \equiv 12 \pmod{20}$$

$$2x' \equiv 12 \pmod{20}$$

Dividiamo entrambi i membri per 2. Poiché $\mcd(2, 20) = 2$ e $2 \mid 12$, l'equazione ha esattamente $\mcd(2, 20) = 2$ soluzioni in $\mathbb{Z}_{20}$.

Dividiamo tutto per 2: $x' \equiv 6 \pmod{10}$.

Le soluzioni in $\{0, 1, \ldots, 19\}$ sono: $x' = 6$ e $x' = 16$.

**Second preimage trovata:** $x' = 16$.

**Verifica:**
$$H(16) = 2 \cdot 16 + 15 \bmod 20 = 32 + 15 \bmod 20 = 47 \bmod 20 = 7 = H(6) \checkmark$$

**Collisione:** $H(6) = H(16) = 7$, con $6 \neq 16$.

**Perché la proprietà manca strutturalmente:**

La funzione $H(x) = 2x + 15 \bmod 20$ è **affine** (lineare con traslazione). Poiché $\mcd(2, 20) = 2 \neq 1$, la funzione lineare $x \mapsto 2x \bmod 20$ non è iniettiva: il suo kernel ha dimensione 2, quindi ogni elemento dell'immagine ha esattamente 2 preimmagini. Trovare una second preimage si riduce a risolvere una semplice equazione lineare congruenziale, operazione computazionalmente banale.

> ⚠️ Una funzione hash deve essere non lineare, non invertibile, e con output ben distribuito. Una funzione affine modulo un intero composto viola tutte queste caratteristiche e non può essere usata in crittografia.
