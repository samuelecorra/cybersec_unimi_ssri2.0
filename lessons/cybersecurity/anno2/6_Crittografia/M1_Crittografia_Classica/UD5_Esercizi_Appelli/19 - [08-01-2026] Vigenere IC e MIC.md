# Vigenère IC e MIC — Esercizio d'esame

> 🗂️ **Fonte:** appello dell'**8 gennaio 2026**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello dell'8 gennaio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L1%20-%20Appello%208%20gennaio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Vigenère: caratteristiche e indici di coincidenza (25 punti)

### Traccia originale

> **(25 punti) Vigenère.**
>
> a. (5 punti) Si descrivano le caratteristiche del cifrario di Vigenère.
>
> b. (10 punti) Descrivere il significato e l'utilizzo degli indici di coincidenza e mutua coincidenza nella crittoanalisi del cifrario di Vigenère.
>
> c. (5 punti) Si calcoli IC della parola "massaggiatore".
>
> d. (5 punti) Si calcoli MIC delle parole "tetto" e "gessetto".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigenère.md) — schema di Vigenère
- [`../../M1_Crittografia_Classica/UD3/L5_2 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_2%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigenère.md) — IC e MIC

### Soluzione dettagliata

#### Parte a — Caratteristiche del cifrario di Vigenère (5 punti)

Il cifrario di **Vigenère** è un cifrario a sostituzione **polialfabetica**: usa una parola chiave $K = k_1 k_2 \ldots k_d$ di lunghezza $d$ e cifra la $i$-esima lettera del messaggio sommandole (modulo 26) la lettera di chiave $k_{(i \bmod d)}$:

$$c_i = (m_i + k_{(i \bmod d)}) \bmod 26, \qquad m_i = (c_i - k_{(i \bmod d)}) \bmod 26$$

Caratteristiche principali:

- È equivalente a **$d$ cifrari di Cesare** applicati ciclicamente.
- **Appiattisce le frequenze**: la stessa lettera in chiaro produce lettere cifrate diverse a seconda della posizione, resistendo all'analisi delle frequenze monogramma.
- È **periodico** di periodo $d$: questa è la sua debolezza — noto $d$, il cifrario si scompone in $d$ Cesari risolvibili.
- Per $d=1$ degenera nel cifrario di Cesare; per $d = |M|$ con chiave casuale non riusata diventa un one-time pad (sicurezza perfetta).

#### Parte b — Indici di coincidenza nella crittoanalisi (10 punti)

**Indice di coincidenza (IC).** Probabilità che due lettere estratte a caso da un testo siano uguali:

$$\text{IC} = \frac{\sum_{i=1}^{26} n_i (n_i - 1)}{N(N-1)}$$

Valori di riferimento: lingua naturale $\approx 0{,}065$–$0{,}075$; testo casuale uniforme $\approx 1/26 \approx 0{,}038$.

**Uso:** trovare la lunghezza della chiave $d$. Si raggruppano i caratteri cifrati prendendone uno ogni $g$. Quando $g = d$, ogni gruppo è cifrato con un singolo Cesare → $\text{IC} \approx 0{,}065$. Quando $g \neq d$ i gruppi sono "mescolati" → $\text{IC} \approx 0{,}038$. Il $g$ corretto è la lunghezza della chiave.

**Indice di mutua coincidenza (MIC).** Probabilità che una lettera da $X$ e una da $Y$ coincidano:

$$\text{MIC}(X,Y) = \frac{\sum_{i=1}^{26} n_i^X \, n_i^Y}{N_X \, N_Y}$$

**Uso:** nota $d$, trovare lo scarto relativo tra le sotto-chiavi. Confrontando due colonne (sotto-cifrari) e facendo scorrere una rispetto all'altra, il MIC è massimo ($\approx \text{IC}$ della lingua) quando lo scorrimento corrisponde alla differenza tra le due lettere di chiave.

#### Parte c — IC di "massaggiatore" (5 punti)

Parola: m-a-s-s-a-g-g-i-a-t-o-r-e, $N = 13$.

Conteggio: $m=1,\ a=3,\ s=2,\ g=2,\ i=1,\ t=1,\ o=1,\ r=1,\ e=1$.

$$\sum_i n_i(n_i-1) = 0 + 3\cdot2 + 2\cdot1 + 2\cdot1 + 0+0+0+0+0 = 6+2+2 = 10$$

$$\text{IC} = \frac{10}{13 \cdot 12} = \frac{10}{156} \approx 0{,}064$$

> 💡 IC ≈ 0,064 è molto vicino al valore atteso per l'italiano (~0,074): le triple 'a' e i doppi 'ss','gg' rispecchiano già il pattern linguistico nonostante la parola breve.

#### Parte d — MIC di "tetto" e "gessetto" (5 punti)

$X = $ "tetto", $N_X = 5$: $t=2,\ e=1,\ o=1$.

$Y = $ "gessetto", $N_Y = 8$: $g=1,\ e=2,\ s=2,\ t=2,\ o=1$.

Lettere comuni: $e$, $t$, $o$.

$$\text{MIC} = \frac{1\cdot2 + 2\cdot2 + 1\cdot1}{5 \cdot 8} = \frac{2+4+1}{40} = \frac{7}{40} = 0{,}175$$

> 💡 MIC = 0,175 ≫ 0,065: le due parole condividono molte lettere ('t' in particolare), suggerendo che potrebbero essere state cifrate con la stessa lettera di chiave.
