# Appello del 16 febbraio 2023 — Soluzione completa

> 📌 **Informazioni appello:** 16/02/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 35 + 15 + 25).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigen%C3%A8re.md) — cifrario di Vigenère
> - [`../../M1_Crittografia_Classica/UD3/L5_1 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_1%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigen%C3%A8re.md) — indici di coincidenza
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — modalità del DES
> - [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — El-Gamal

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

- [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigen%C3%A8re.md) — schema di Vigenère
- [`../../M1_Crittografia_Classica/UD3/L5_2 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_2%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigen%C3%A8re.md) — Kasiski e indici di coincidenza

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

---

## Domanda 2 — Cifrario a blocchi basato su hash e XOR (35 punti)

### Traccia originale

> **(35 punti)** Considera il seguente cifrario a blocchi basato su una funzione hash $h$ e sullo XOR. $h$ produce hash di 64 bit e la chiave simmetrica $k$ è di pari lunghezza. Sia $m = m_1 \| m_2$ (concatenazione), con $m_1, m_2$ di 64 bit; analogamente $k = k_1 \| k_2$. La cifratura $E_k(m) = c_1 \| c_2$ è:
> $$c_1 = m_1 \oplus h(m_2) \oplus k_1, \qquad c_2 = m_2 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura conoscendo la chiave $k$.
>
> b. (15 punti) Considerazioni sulla sicurezza (sugg. chosen plaintext, con $m_1$ e $m_2$ scelti opportunamente).
>
> c. (10 punti) Con $h = {}$ modulo 16, numeri ridotti a messaggi di 32 bit e hash di 16 bit, cifrare $m = \{0101010101010101\}\{1\}^{16}$ con $k_1 = k_2 = 0000111111110000$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20%E2%80%93%20introduzione.md) — struttura dei cifrari a blocchi
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Propriet%C3%A0.md) — proprietà delle funzioni hash

### Soluzione dettagliata

#### Parte a — Decifratura conoscendo $k$ (10 punti)

Si invertono le equazioni nell'ordine giusto. Dalla seconda:
$$c_2 = m_2 \oplus c_1 \oplus k_2 \;\Rightarrow\; \boxed{m_2 = c_2 \oplus c_1 \oplus k_2}$$
($c_1$ è già noto perché fa parte del ciphertext). Recuperato $m_2$, si calcola $h(m_2)$ e dalla prima:
$$c_1 = m_1 \oplus h(m_2) \oplus k_1 \;\Rightarrow\; \boxed{m_1 = c_1 \oplus h(m_2) \oplus k_1}$$

L'ordine è essenziale: prima $m_2$ (che non dipende dall'hash), poi $m_1$ (che richiede $h(m_2)$).

#### Parte b — Sicurezza e attacco chosen-plaintext (15 punti)

Il cifrario è **deterministico** (nessun IV/nonce): lo stesso plaintext dà sempre lo stesso ciphertext, quindi **non è semanticamente sicuro** (un avversario riconosce messaggi ripetuti).

Peggio, è **lineare/malleabile** rispetto allo XOR. Sotto **chosen plaintext** si recupera materiale di chiave:

- **Recupero immediato di $k_2$.** Da una singola coppia nota $(m, c)$:
$$k_2 = c_2 \oplus m_2 \oplus c_1$$
quindi il mascheramento del secondo blocco è completamente compromesso.

- **Recupero di $h(m_2) \oplus k_1$ per ogni $m_2$.** Scegliendo due plaintext con **lo stesso $m_2$** e $m_1 \neq m_1'$:
$$c_1 \oplus c_1' = (m_1 \oplus h(m_2)\oplus k_1) \oplus (m_1' \oplus h(m_2)\oplus k_1) = m_1 \oplus m_1'$$
cioè il primo blocco si comporta come un **cifrario a flusso a XOR**: flippare un bit di $m_1$ flippa lo stesso bit di $c_1$ (e, via $c_2 = m_2 \oplus c_1 \oplus k_2$, anche di $c_2$). Da una coppia con un dato $m_2$ l'avversario ricava la maschera $K' = h(m_2)\oplus k_1 = c_1 \oplus m_1$ e da quel momento cifra/decifra **qualsiasi** messaggio con quello stesso $m_2$.

> ⚠️ Conclusioni: cifrario deterministico, malleabile, con $k_2$ ricavabile da una sola coppia e maschera del primo blocco ricavabile per ogni valore di $m_2$. Non offre alcuna sicurezza CPA. Le costruzioni reali usano un IV/nonce casuale e una funzione pseudocasuale non lineare (cifrari a blocchi veri come AES in modalità sicura).

#### Parte c — Esempio numerico ($h = \bmod\,16$, blocchi a 16 bit) (10 punti)

Parametri ridotti: messaggio 32 bit $= m_1 \| m_2$ con $m_1, m_2$ di 16 bit, hash a 16 bit, $k_1 = k_2$ di 16 bit.

$$m_1 = 0101010101010101, \quad m_2 = 1111111111111111, \quad k_1 = k_2 = 0000111111110000$$

**Hash:** $h(m_2) = m_2 \bmod 16$. In binario "modulo 16" significa prendere i 4 bit meno significativi: $m_2 = \texttt{1111}\,1111\,1111\,\mathbf{1111}$, quindi $h(m_2) = 15 = 0000000000001111$.

**Primo blocco:** $c_1 = m_1 \oplus h(m_2) \oplus k_1$

```
  m1     = 0101 0101 0101 0101
  h(m2)  = 0000 0000 0000 1111
  k1     = 0000 1111 1111 0000
  ---------------------- XOR
  c1     = 0101 1010 1010 1010
```

**Secondo blocco:** $c_2 = m_2 \oplus c_1 \oplus k_2$

```
  m2     = 1111 1111 1111 1111
  c1     = 0101 1010 1010 1010
  k2     = 0000 1111 1111 0000
  ---------------------- XOR
  c2     = 1010 1010 1010 0101
```

**Ciphertext:** $C = c_1 \| c_2 = 0101101010101010 \; 1010101010100101$.

> ✅ Verifica (decifratura): $m_2 = c_2 \oplus c_1 \oplus k_2 = 1111111111111111$ ✓; $h(m_2)=0000000000001111$; $m_1 = c_1 \oplus h(m_2) \oplus k_1 = 0101010101010101$ ✓.

---

## Domanda 3 — Modalità operative del DES (15 punti)

### Traccia originale

> **(15 punti) Modalità di DES.** Si discutano le modalità di cifratura evidenziandone vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — ECB, CBC, CFB, OFB, CTR

### Soluzione dettagliata

Un cifrario a blocchi come DES cifra blocchi di 64 bit; le **modalità operative** definiscono come concatenare la cifratura di più blocchi.

**ECB (Electronic Codebook):** $c_i = E_k(m_i)$. *Vantaggi:* semplice, parallelizzabile, accesso casuale. *Svantaggi:* deterministica — blocchi in chiaro uguali danno blocchi cifrati uguali, quindi rivela pattern (il classico esempio dell'immagine "pinguino" ancora riconoscibile). Da evitare.

**CBC (Cipher Block Chaining):** $c_i = E_k(m_i \oplus c_{i-1})$, $c_0 = IV$. *Vantaggi:* nasconde i pattern (con IV casuale ogni cifratura è diversa), errore di trasmissione si propaga in modo limitato. *Svantaggi:* cifratura sequenziale (non parallelizzabile), serve un IV imprevedibile; vulnerabile a padding oracle se mal implementata.

**CFB (Cipher Feedback):** trasforma il cifrario a blocchi in cifrario a flusso: $c_i = m_i \oplus E_k(c_{i-1})$. *Vantaggi:* non serve padding, adatto a stream. *Svantaggi:* cifratura sequenziale; un errore si propaga.

**OFB (Output Feedback):** keystream indipendente dal plaintext: $w_i = E_k(w_{i-1})$, $c_i = m_i \oplus w_i$. *Vantaggi:* niente padding, errori non si propagano (errore di bit → solo quel bit). *Svantaggi:* **keystream riutilizzabile** se si riusa l'IV (problema del two-time-pad); sequenziale nella generazione del keystream.

**CTR (Counter):** $c_i = m_i \oplus E_k(\text{nonce} \| i)$. *Vantaggi:* parallelizzabile, accesso casuale, niente padding. *Svantaggi:* catastrofico riusare la coppia (chiave, contatore).

> 📌 Regola pratica: **mai ECB** per dati strutturati; usare modalità con IV casuale (CBC) o a flusso (CTR), e **non riutilizzare mai IV/contatore/keystream** con la stessa chiave.

---

## Domanda 4 — Algoritmo di El-Gamal (25 punti)

### Traccia originale

> **(25 punti) Algoritmo di El-Gamal.**
>
> a. (10) Descrivere la procedura di cifratura e decifratura di un messaggio $m$.
>
> b. (15) Si consideri la cifratura nel campo $\mathbb{Z}_{11}$ con generatore $6$ e sia $3$ la chiave pubblica di Alice. Cifrare e decifrare il messaggio $4$ per Alice con $k = 5$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — schema completo
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — fondamento di sicurezza

### Soluzione dettagliata

#### Parte a — Procedura di cifratura e decifratura (10 punti)

**Parametri pubblici:** un primo $p$, un generatore $g$ di $\mathbb{Z}_p^*$. **Chiavi di Alice:** privata $\alpha \in \{1,\ldots,p-2\}$, pubblica $\beta = g^\alpha \bmod p$. La chiave pubblica pubblicata è $(p, g, \beta)$.

**Cifratura (Bob, messaggio $M$):** sceglie un numero casuale $k$ e calcola la coppia
$$c_1 = g^k \bmod p, \qquad c_2 = M \cdot \beta^k \bmod p$$
e invia $(c_1, c_2)$.

**Decifratura (Alice):** usando la privata $\alpha$,
$$M = c_2 \cdot (c_1^\alpha)^{-1} \bmod p$$

**Correttezza:** $c_2 \cdot (c_1^\alpha)^{-1} = M\beta^k (g^{k\alpha})^{-1} = M g^{\alpha k} g^{-\alpha k} = M$, poiché $\beta^k = g^{\alpha k} = c_1^\alpha$. La sicurezza poggia sul **problema del logaritmo discreto** (da $\beta = g^\alpha$ è difficile ricavare $\alpha$).

#### Parte b — Esempio numerico in $\mathbb{Z}_{11}$, $g = 6$, $\beta = 3$, $M = 4$, $k = 5$ (15 punti)

Parametri: $p = 11$, $g = 6$, chiave pubblica di Alice $\beta = 3$.

**Cifratura di $M = 4$ con $k = 5$:**

$$c_1 = g^k \bmod p = 6^5 \bmod 11$$
Calcolo: $6^2 = 36 \equiv 3$, $6^4 = 3^2 = 9$, $6^5 = 6^4 \cdot 6 = 9 \cdot 6 = 54 \equiv 54 - 44 = 10 \pmod{11}$. Quindi $c_1 = 10$.

$$c_2 = M \cdot \beta^k \bmod p = 4 \cdot 3^5 \bmod 11$$
Calcolo: $3^2 = 9$, $3^4 = 81 \equiv 4$, $3^5 = 3^4 \cdot 3 = 4 \cdot 3 = 12 \equiv 1 \pmod{11}$. Quindi $c_2 = 4 \cdot 1 = 4$.

**Ciphertext:** $(c_1, c_2) = (10, 4)$.

**Decifratura.** Per applicare $M = c_2 (c_1^\alpha)^{-1}$ serve la chiave **privata** $\alpha$ di Alice, cioè il logaritmo discreto $\alpha = \log_6 3 \bmod 11$. Cerchiamo $\alpha$ con $6^\alpha \equiv 3$:
$$6^1=6,\; 6^2=3 \pmod{11}$$
quindi $\alpha = 2$.

> 💡 Nota: la traccia fornisce solo la pubblica $\beta=3$; per decifrare numericamente si ricava la privata risolvendo il piccolo logaritmo discreto ($g=6$ ha periodo breve in $\mathbb{Z}_{11}^*$, qui $\alpha=2$). Su parametri reali questo passo è computazionalmente intrattabile (è proprio ciò che garantisce la sicurezza).

Decifratura con $\alpha = 2$:
$$c_1^\alpha = 10^2 = 100 \equiv 1 \pmod{11}, \qquad (c_1^\alpha)^{-1} = 1^{-1} = 1$$
$$M = c_2 \cdot 1 = 4 \cdot 1 = 4 \pmod{11}$$

**Messaggio recuperato:** $M = 4$ ✓.

---

## Pattern e osservazioni per l'esame

Appello della sessione invernale (febbraio 2023).

- **D1 (25):** Vigenère. Memorizzare le **formule** di IC ($\sum n_i(n_i-1)/N(N-1)$) e MIC ($\sum n_i^X n_i^Y / N_X N_Y$) e il loro uso (IC → lunghezza chiave; MIC → allineamento delle sotto-chiavi). I calcoli su parole brevi sono solo esercizi meccanici.
- **D2 (35):** cifrario a blocchi hash+XOR. Schema ricorrente (cfr. 07/07/2023 e 10/02/2025): decifratura invertendo nell'ordine giusto, e attacco basato sulla **linearità XOR** (deterministico/malleabile, recupero di $k_2$ e della maschera). Attenzione ai conti bit-a-bit nel punto numerico.
- **D3 (15):** modalità DES — descrizione comparata ECB/CBC/CFB/OFB/CTR con pro e contro. Domanda descrittiva ricorrente.
- **D4 (25):** El-Gamal numerico. Verificare sempre i conti delle potenze modulari; per decifrare serve la privata $\alpha$ (qui ricavabile come piccolo logaritmo discreto, $\alpha = \log_6 3 = 2$).
