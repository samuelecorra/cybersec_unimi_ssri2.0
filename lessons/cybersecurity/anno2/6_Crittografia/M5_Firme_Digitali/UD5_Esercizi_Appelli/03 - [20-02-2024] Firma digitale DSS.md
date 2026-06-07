# Firma digitale DSS — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 febbraio 2024**, Domanda 4 — Modulo M5 (Firme Digitali).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 febbraio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L2%20-%20Appello%2020%20febbraio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Firma digitale DSS (25 punti)

### Traccia originale

> **(25 punti) Firma digitale DSS.**
>
> a. (5) Descrivere i parametri dell'algoritmo di firma DSS.
>
> b. (10) Descrivere la procedura di generazione e verifica della firma di un messaggio m.
>
> c. (10) Cosa succede se durante la generazione della firma $(r, s)$ di un messaggio $m$ si produce un valore $s = 0$. È vero che bisogna generare un nuovo valore casuale $k$ e ricalcolare la firma? Giustificare la risposta.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — parametri, generazione e verifica DSS
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — fondamento matematico

### Soluzione dettagliata

#### Parte a — Parametri dell'algoritmo DSS (5 punti)

Il **Digital Signature Standard (DSS)** usa l'algoritmo **DSA (Digital Signature Algorithm)**. I parametri si dividono in:

**Parametri globali (condivisi da tutti gli utenti):**

- $p$: primo di lunghezza $L$ bit con $512 \leq L \leq 1024$ (multiplo di 64); deve valere $p-1 \equiv 0 \pmod{q}$.
- $q$: primo di 160 bit, divisore di $p-1$.
- $g$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$, calcolato come $g = h^{(p-1)/q} \bmod p$ con $h$ tale che $h^{(p-1)/q} \not\equiv 1 \pmod{p}$.

**Parametri per utente:**

- $x$: chiave privata, intero casuale con $0 < x < q$.
- $y$: chiave pubblica, $y = g^x \bmod p$.

> 📌 La sicurezza del DSA si basa sulla difficoltà del **problema del logaritmo discreto** in $\mathbb{Z}_p^*$ (trovare $x$ da $y = g^x \bmod p$) e nella sua variante nel sottogruppo di ordine $q$.

#### Parte b — Generazione e verifica della firma (10 punti)

**Generazione della firma di un messaggio $m$:**

1. Scegliere un numero casuale $k$ con $0 < k < q$ (segreto, usa-e-getta: diverso per ogni firma).
2. Calcolare $r = (g^k \bmod p) \bmod q$.
   - Se $r = 0$, scegliere un nuovo $k$ e ricominciare.
3. Calcolare $k^{-1} \bmod q$ (inverso di $k$ modulo $q$, che esiste poiché $q$ è primo e $0 < k < q$).
4. Calcolare $s = k^{-1}(H(m) + xr) \bmod q$, dove $H$ è SHA-1.
   - Se $s = 0$, scegliere un nuovo $k$ e ricominciare.
5. La firma è la coppia $(r, s)$.

**Verifica della firma $(r, s)$ su messaggio $m$ con chiave pubblica $y$:**

1. Verificare $0 < r < q$ e $0 < s < q$; se una delle condizioni non vale, la firma è invalida.
2. Calcolare $w = s^{-1} \bmod q$.
3. Calcolare $u_1 = H(m) \cdot w \bmod q$.
4. Calcolare $u_2 = r \cdot w \bmod q$.
5. Calcolare $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$.
6. La firma è valida se e solo se $v = r$.

**Correttezza della verifica:**

$$v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q = (g^{H(m) \cdot w} \cdot g^{x \cdot r \cdot w} \bmod p) \bmod q$$

$$= (g^{w(H(m) + xr)} \bmod p) \bmod q$$

Poiché $s = k^{-1}(H(m) + xr) \bmod q$, si ha $w = s^{-1} = k(H(m) + xr)^{-1} \bmod q$, quindi:

$$w(H(m) + xr) \equiv k \pmod{q}$$

Dunque:

$$v = (g^k \bmod p) \bmod q = r$$

La verifica è corretta. $\blacksquare$

#### Parte c — Cosa succede se s=0 (10 punti)

**Risposta: sì, bisogna scegliere un nuovo $k$ e ricalcolare la firma. La giustificazione è duplice: matematica e di sicurezza.**

**Motivo matematico:**

La firma è la coppia $(r, s)$ con $s = k^{-1}(H(m) + xr) \bmod q$.

Se $s = 0$, la firma $(r, 0)$ non è una firma valida nel senso del protocollo, poiché durante la verifica si deve calcolare $w = s^{-1} \bmod q$, e $s = 0$ non ha inverso modulo $q$. La verifica fallirebbe (o produrrebbe un errore di divisione per zero). Pertanto la firma $(r, 0)$ è intrinsecamente inutilizzabile.

**Motivo di sicurezza:**

Supponiamo (per assurdo) che si accettasse $s = 0$ come firma valida e che la verifica fosse modificata per gestire questo caso. Allora:

$$s = k^{-1}(H(m) + xr) \equiv 0 \pmod{q}$$

Questo implica $H(m) + xr \equiv 0 \pmod{q}$, ovvero $xr \equiv -H(m) \pmod{q}$. Se $r \neq 0$ e $r$ è invertibile modulo $q$ (poiché $\gcd(r, q) = 1$ con alta probabilità), si potrebbe ricavare:

$$x \equiv -H(m) \cdot r^{-1} \pmod{q}$$

La chiave privata $x$ sarebbe **direttamente esposta** a chiunque conosca la firma $(r, 0)$, il messaggio $m$ e i parametri pubblici. L'intero sistema crittografico sarebbe compromesso.

**Probabilità che $s = 0$:**

$s = 0$ se e solo se $H(m) + xr \equiv 0 \pmod{q}$, evento che ha probabilità circa $1/q \approx 2^{-160}$, quindi estremamente raro in pratica. Tuttavia il protocollo deve gestire correttamente anche questo caso.

> ✅ **Conclusione:** se $s = 0$ (o anche $r = 0$), è obbligatorio generare un nuovo $k$ casuale e ricalcolare la firma. Questo garantisce che la firma sia valida sintatticamente (entrambi i componenti non nulli) e che non vengano esposti parametri segreti.

> ⚠️ Una vulnerabilità analoga esiste con $k$ riusato: se la stessa $k$ viene usata per firmare due messaggi diversi $m_1$ e $m_2$, si producono due firme $(r, s_1)$ e $(r, s_2)$ con lo stesso $r$. Da queste due firme si può calcolare $k$ e poi $x$. Questo è il motivo per cui $k$ deve essere generato casualmente e in modo fresco per ogni firma.
