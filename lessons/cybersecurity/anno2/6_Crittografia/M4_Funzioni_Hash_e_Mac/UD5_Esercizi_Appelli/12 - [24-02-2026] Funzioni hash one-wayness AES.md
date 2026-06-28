# Funzioni hash: attacco a compleanno e one-wayness di $H(x)=\text{AES}_k(x)$ — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 febbraio 2026**, Domanda 2 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 febbraio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L2%20-%20Appello%2024%20febbraio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Funzioni Hash (30 punti)

### Traccia originale

> **(30 punti) Funzioni Hash.**
>
> a. (15 punti) Si descriva l'attacco a compleanno e le sue implicazioni per le funzioni hash.
>
> b. (15 punti) Sia $E$ un cifrario simmetrico robusto. Si discuta la proprietà di one-wayness per $H(x) = \text{AES}_k(x)$ per un certo $k$, considerando:
>
> - $k$ fisso e conosciuto
> - $k$ fisso e non conosciuto

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L3 - Sicurezza e attacchi.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L3%20-%20Sicurezza%20e%20attacchi.md) — attacco a compleanno
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà di sicurezza

### Soluzione dettagliata

#### Parte a — Attacco a compleanno e implicazioni (15 punti)

Per una funzione $H: \{0,1\}^* \to \{0,1\}^m$, trovare una **collisione** (due input distinti con stesso hash) richiede solo $O(2^{m/2})$ tentativi, non $O(2^m)$.

**Algoritmo:** genera $\approx 2^{m/2}$ messaggi casuali e i loro hash, poi cerca coppie con lo stesso output. Dal paradosso del compleanno, la probabilità di collisione supera 50% con $n \approx 1{,}17 \cdot 2^{m/2}$ valori.

**Implicazioni:**

| Output $m$ | Sicurezza preimage | Sicurezza collisione | Sicuro? |
|---|---|---|---|
| 64 bit | $2^{64}$ | $2^{32}$ | ✗ pratico |
| 128 bit | $2^{128}$ | $2^{64}$ | Borderline |
| 256 bit | $2^{256}$ | $2^{128}$ | ✓ |

> ⚠️ L'attacco a compleanno rende necessario raddoppiare l'output per mantenere il livello di sicurezza contro le collisioni. SHA-256 è il minimo raccomandato.

#### Parte b — One-wayness di $H(x) = \text{AES}_k(x)$ (15 punti)

**Caratteristiche:** AES-128 → blocchi di 128 bit. $H: \{0,1\}^{128} \to \{0,1\}^{128}$. Per $k$ fissa, $\text{AES}_k$ è una **permutazione** (biiezione): nessuna compressione, dominio = codominio, esiste un algoritmo di inversione pubblico.

**$k$ fisso e conosciuto:**

$\text{AES}_k$ è una permutazione nota con inverse pubblicamente calcolabile:

$$x = \text{AES}_k^{-1}(h) \quad \text{(O(1) operazioni)}$$

→ $H$ **NON è one-way** con $k$ noto.

**$k$ fisso e non conosciuto:**

Per trovare $x$ da $h = \text{AES}_k(x)$: l'avversario deve trovare $k$ (ricerca esaustiva su $2^{128}$ chiavi), poi inversa. Senza coppie $(x^*,h^*)$ aggiuntive, non è verificabile quale $k'$ sia corretto.

Complessità: $O(2^{128})$ operazioni.

→ $H$ **è computazionalmente one-way** con $k$ non noto, livello di sicurezza $2^{128}$.

**Riepilogo:**

| Caso | One-way? | Motivazione |
|---|---|---|
| $k$ noto | ✗ NO | $x = \text{AES}_k^{-1}(h)$ in O(1) |
| $k$ non noto | ✓ SÌ (comp.) | Brute-force su $k$: $2^{128}$ operazioni |

> 💡 Il caso $k$ non noto è alla base della costruzione **Davies-Meyer**: si usa il blocco del messaggio come chiave di AES, rendendo l'inversione equivalente a trovare la chiave AES — difficile per AES sicuro.
