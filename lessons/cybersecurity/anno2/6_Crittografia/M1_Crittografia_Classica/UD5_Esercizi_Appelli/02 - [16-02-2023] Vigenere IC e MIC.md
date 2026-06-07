# Vigenere IC e MIC — Esercizio d'esame

> 🗂️ **Fonte:** appello del **16 febbraio 2023**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 16 febbraio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L2%20-%20Appello%2016%20febbraio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Vigenère: caratteristiche e indici di coincidenza (25 punti)

### Traccia originale

> **(25 punti) Vigenère.**
>
> a. (5 punti) Si descrivano le caratteristiche del cifrario di Vigenère.
>
> b. (10 punti) Descrivere il significato e l'utilizzo degli indici di coincidenza e mutua coincidenza nella crittoanalisi del cifrario di Vigenère.
>
> c. (5 punti) Si calcoli IC della parola "gassoso".
>
> d. (5 punti) Si calcoli MIC delle parole "setto" e "mattinata".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigenère.md) — schema di Vigenère
- [`../../M1_Crittografia_Classica/UD3/L5_2 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_2%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigenère.md) — Kasiski e indici di coincidenza

### Soluzione dettagliata

#### Parte a — Caratteristiche del cifrario di Vigenère (5 punti)

Il cifrario di **Vigenère** è un cifrario a sostituzione **polialfabetica**: usa una parola chiave $K = k_1 k_2 \ldots k_d$ di lunghezza $d$ e cifra la $i$-esima lettera del messaggio sommandole (modulo 26) la lettera di chiave $k_{(i \bmod d)}$:

$$c_i = (m_i + k_{(i \bmod d)}) \bmod 26, \qquad m_i = (c_i - k_{(i \bmod d)}) \bmod 26$$

Caratteristiche principali:

- È equivalente a **$d$ cifrari di Cesare** applicati ciclicamente (uno per ciascuna posizione della chiave).
- **Appiattisce le frequenze**: una stessa lettera in chiaro produce lettere cifrate diverse a seconda della posizione, quindi resiste all'analisi delle frequenze monogramma (a differenza dei cifrari monoalfabetici).
- È **periodico** di periodo $d$: questa è la sua debolezza, perché una volta noto $d$ il cifrario si scompone in $d$ Cesari risolvibili con l'analisi delle frequenze.
- Per $d=1$ degenera nel cifrario di Cesare; per $d$ pari alla lunghezza del messaggio e chiave casuale non riusata diventa un **one-time pad** (sicurezza perfetta).

#### Parte b — Indici di coincidenza nella crittoanalisi (10 punti)

**Indice di coincidenza (IC).** È la probabilità che due lettere estratte a caso (senza reinserimento) da un testo siano uguali:

$$\text{IC} = \frac{\sum_{i=1}^{26} n_i (n_i - 1)}{N(N-1)}$$

dove $n_i$ è il numero di occorrenze della $i$-esima lettera e $N$ la lunghezza del testo. Valori di riferimento: per una lingua naturale (italiano/inglese) $\text{IC} \approx 0{,}065$–$0{,}075$; per un testo **casuale uniforme** $\text{IC} \approx 1/26 \approx 0{,}038$.

**Uso nella crittoanalisi di Vigenère:** serve a **trovare la lunghezza della chiave $d$**. Si raggruppano i caratteri cifrati prendendone uno ogni $g$ (ipotesi di periodo $g$). Quando $g$ è il vero periodo $d$, ciascun gruppo è cifrato con un singolo Cesare, quindi conserva le frequenze della lingua e ha $\text{IC} \approx 0{,}065$; quando $g \neq d$ i gruppi sono "mescolati" e $\text{IC} \approx 0{,}038$. Il $g$ che fa risalire l'IC al valore della lingua è la lunghezza della chiave.

**Indice di mutua coincidenza (MIC).** Misura la probabilità che una lettera estratta a caso da un testo $X$ e una da un testo $Y$ coincidano:

$$\text{MIC}(X,Y) = \frac{\sum_{i=1}^{26} n_i^X \, n_i^Y}{N_X \, N_Y}$$

**Uso:** una volta noto $d$, serve a **trovare lo scarto relativo tra le sotto-chiavi**. Confrontando due colonne (sotto-cifrari) e facendo scorrere una rispetto all'altra, il MIC è massimo (≈ IC della lingua) quando lo scorrimento corrisponde alla differenza tra le due lettere di chiave: questo allinea i due alfabeti e permette di ricostruire la chiave a meno di uno shift globale (poi risolto con l'analisi delle frequenze).

#### Parte c — IC di "gassoso" (5 punti)

Parola "gassoso", $N = 7$. Conteggio lettere: $g=1,\ a=1,\ s=3,\ o=2$.

$$\sum_i n_i(n_i-1) = \underbrace{0}_{g} + \underbrace{0}_{a} + \underbrace{3\cdot2}_{s=6} + \underbrace{2\cdot1}_{o=2} = 8$$

$$\text{IC} = \frac{8}{7 \cdot 6} = \frac{8}{42} = \frac{4}{21} \approx 0{,}190$$

> 💡 IC alto (0,19 ≫ 0,065) perché la parola è cortissima e dominata da poche lettere ripetute (la "s"): su testi brevi l'IC è poco significativo, lo strumento ha senso su testi lunghi.

#### Parte d — MIC di "setto" e "mattinata" (5 punti)

$X = $ "setto", $N_X = 5$: $s=1,\ e=1,\ t=2,\ o=1$.
$Y = $ "mattinata", $N_Y = 9$: $m=1,\ a=3,\ t=3,\ i=1,\ n=1$.

L'unica lettera presente in **entrambe** è la **t** ($n_t^X = 2$, $n_t^Y = 3$); per tutte le altre uno dei due conteggi è nullo. Quindi:

$$\text{MIC} = \frac{\sum_i n_i^X n_i^Y}{N_X N_Y} = \frac{n_t^X \cdot n_t^Y}{5 \cdot 9} = \frac{2 \cdot 3}{45} = \frac{6}{45} = \frac{2}{15} \approx 0{,}133$$
