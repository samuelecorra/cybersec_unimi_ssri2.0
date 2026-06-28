# Appello dell'8 gennaio 2026 — Soluzione completa

> 📌 **Informazioni appello:** 08/01/2026, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
>
> - [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigenère.md) — schema di Vigenère
> - [`../../M1_Crittografia_Classica/UD3/L5_2 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_2%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigenère.md) — indici IC e MIC
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1 - Cifrari non-standard XOR+hash - decifratura e CPA attack.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1%20-%20Cifrari%20non-standard%20XOR+hash%20-%20decifratura%20e%20CPA%20attack.md) — cifrario XOR+hash
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](<../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md>) — schema DSS
> - [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — riutilizzo nonce DSS
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir

---

## Domanda 1 — Vigenère: IC e MIC (25 punti)

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

- [`../../M1_Crittografia_Classica/UD3/L3 - Il Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L3%20-%20Il%20Cifrario%20di%20Vigenère.md) — schema
- [`../../M1_Crittografia_Classica/UD3/L5_2 - Crittoanalisi del Cifrario di Vigenère.md`](../../M1_Crittografia_Classica/UD3/L5_2%20-%20Crittoanalisi%20del%20Cifrario%20di%20Vigenère.md) — IC e MIC

### Soluzione dettagliata

#### Parte a — Caratteristiche del cifrario di Vigenère (5 punti)

Il cifrario di **Vigenère** è un cifrario a sostituzione **polialfabetica**: usa una parola chiave $K = k_1 k_2 \ldots k_d$ di lunghezza $d$ e cifra la $i$-esima lettera del messaggio sommandole (modulo 26) la lettera di chiave $k_{(i \bmod d)}$:

$$c_i = (m_i + k_{(i \bmod d)}) \bmod 26, \qquad m_i = (c_i - k_{(i \bmod d)}) \bmod 26$$

Caratteristiche principali:

- È equivalente a **$d$ cifrari di Cesare** applicati ciclicamente (uno per ciascuna posizione della chiave).
- **Appiattisce le frequenze**: una stessa lettera in chiaro produce lettere cifrate diverse a seconda della posizione, quindi resiste all'analisi delle frequenze monogramma.
- È **periodico** di periodo $d$: questa è la sua debolezza, perché una volta noto $d$ il cifrario si scompone in $d$ Cesari risolvibili con l'analisi delle frequenze.
- Per $d=1$ degenera nel cifrario di Cesare; per $d$ pari alla lunghezza del messaggio e chiave casuale non riusata diventa un **one-time pad** (sicurezza perfetta).

#### Parte b — Indici di coincidenza nella crittoanalisi (10 punti)

**Indice di coincidenza (IC).** È la probabilità che due lettere estratte a caso (senza reinserimento) da un testo siano uguali:

$$\text{IC} = \frac{\sum_{i=1}^{26} n_i (n_i - 1)}{N(N-1)}$$

dove $n_i$ è il numero di occorrenze della $i$-esima lettera e $N$ la lunghezza del testo. Valori di riferimento: per una lingua naturale $\text{IC} \approx 0{,}065$–$0{,}075$; per un testo casuale uniforme $\text{IC} \approx 1/26 \approx 0{,}038$.

**Uso nella crittoanalisi di Vigenère:** serve a **trovare la lunghezza della chiave $d$**. Si raggruppano i caratteri cifrati prendendone uno ogni $g$. Quando $g = d$, ciascun gruppo è cifrato con un singolo Cesare, conserva le frequenze della lingua e ha $\text{IC} \approx 0{,}065$; quando $g \neq d$ i gruppi sono "mescolati" e $\text{IC} \approx 0{,}038$. Il $g$ che fa risalire l'IC al valore della lingua è la lunghezza della chiave.

**Indice di mutua coincidenza (MIC).** Misura la probabilità che una lettera estratta a caso da un testo $X$ e una da un testo $Y$ coincidano:

$$\text{MIC}(X,Y) = \frac{\sum_{i=1}^{26} n_i^X \, n_i^Y}{N_X \, N_Y}$$

**Uso:** una volta noto $d$, serve a **trovare lo scarto relativo tra le sotto-chiavi**. Confrontando due colonne e facendo scorrere una rispetto all'altra, il MIC è massimo (≈ IC della lingua) quando lo scorrimento corrisponde alla differenza tra le due lettere di chiave, permettendo di ricostruire la chiave a meno di uno shift globale.

#### Parte c — IC di "massaggiatore" (5 punti)

Parola: m-a-s-s-a-g-g-i-a-t-o-r-e, $N = 13$.

Conteggio occorrenze: $m=1,\ a=3,\ s=2,\ g=2,\ i=1,\ t=1,\ o=1,\ r=1,\ e=1$.

$$\sum_i n_i(n_i-1) = 0 + 3\cdot2 + 2\cdot1 + 2\cdot1 + 0 + 0 + 0 + 0 + 0 = 6 + 2 + 2 = 10$$

$$\text{IC} = \frac{10}{13 \cdot 12} = \frac{10}{156} \approx 0{,}064$$

> 💡 IC ≈ 0,064 è molto vicino al valore atteso per la lingua italiana (≈ 0,074). La parola è corta e non ancora statisticamente rappresentativa, ma le tre 'a' e le lettere doppie (ss, gg) già rispecchiano il pattern linguistico.

#### Parte d — MIC di "tetto" e "gessetto" (5 punti)

$X = $ "tetto", $N_X = 5$: $t=2,\ e=1,\ o=1$.

$Y = $ "gessetto", $N_Y = 8$: $g=1,\ e=2,\ s=2,\ t=2,\ o=1$.

Lettere presenti in **entrambi** i testi: $e$, $t$, $o$.

$$\text{MIC} = \frac{n_e^X n_e^Y + n_t^X n_t^Y + n_o^X n_o^Y}{N_X N_Y} = \frac{1\cdot2 + 2\cdot2 + 1\cdot1}{5 \cdot 8} = \frac{2 + 4 + 1}{40} = \frac{7}{40} = 0{,}175$$

> 💡 MIC = 0,175 è molto superiore al valore atteso per lingua naturale (≈ 0,065). Questo suggerisce che i due testi potrebbero essere stati cifrati con la stessa lettera di chiave (stesso Cesare), oppure semplicemente che le due parole brevi condividono molte lettere comuni ('t' in particolare).

---

## Domanda 2 — Cifrario a blocchi XOR e hash (35 punti)

### Traccia originale

> **(35 punti)** Considera il seguente cifrario a blocchi basato su una funzione hash $h$ e sullo XOR. Assumiamo che $h$ produca valori di hash lunghi 64 bit e una chiave simmetrica $k$ di 128 bit. Sia $m$ un messaggio plaintext composto da due parti $m_1 m_2$, entrambe di lunghezza 64 bit, cioè $m = m_1 \cdot m_2$, dove $\cdot$ denota concatenazione. Si divida anche la chiave $k$ in due parti $k = k_1 k_2$ ciascuna di 64 bit.
>
> La cifratura $E_k(m) = c_1 c_2$ funziona in questo modo:
>
> $$c_1 = m_1 \oplus h(m_2) \oplus k_1$$
> $$c_2 = m_2 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura nell'ipotesi di conoscere la chiave $k$.
>
> b. (15 punti) Fare considerazioni sulla sicurezza di questo cifrario e considerare un attacco di tipo chosen plaintext, scegliendo opportunamente $m_1$ e $m_2$.
>
> c. (10 punti) Nel caso $h$ sia la funzione complemento a bit, che inverte il valore di ogni singolo bit, e i numeri siano ridotti a messaggi di 32 bit e hash di 16 bit, cifrare $m = \{01010101\ 01010101\}\{1\}^{16}$ con chiave $k_1 = k_2 = 00001111\ 11110000$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1 - Cifrari non-standard XOR+hash - decifratura e CPA attack.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1%20-%20Cifrari%20non-standard%20XOR+hash%20-%20decifratura%20e%20CPA%20attack.md) — analisi completa di questo schema

### Soluzione dettagliata

#### Parte a — Decifratura (10 punti)

Dati $c_1$, $c_2$ e la chiave $k = k_1 \| k_2$.

**Passo 1 — Recupero di $m_2$:** dall'equazione $c_2 = m_2 \oplus c_1 \oplus k_2$:

$$\boxed{m_2 = c_2 \oplus c_1 \oplus k_2}$$

**Passo 2 — Recupero di $m_1$:** ora che $m_2$ è noto, si calcola $h(m_2)$ e dall'equazione $c_1 = m_1 \oplus h(m_2) \oplus k_1$:

$$\boxed{m_1 = c_1 \oplus h(m_2) \oplus k_1}$$

**Algoritmo di decifratura:**
1. $m_2 \leftarrow c_2 \oplus c_1 \oplus k_2$
2. $m_1 \leftarrow c_1 \oplus h(m_2) \oplus k_1$

> 📌 La decifratura è sempre univoca (dato $k$, ogni ciphertext ha esattamente un plaintext), indipendentemente da $h$. Questo è garantito dalla struttura XOR.

#### Parte b — Sicurezza e attacco CPA (15 punti)

**Considerazioni generali:** il cifrario ha struttura simile a una rete di Feistel semplificata, ma $c_1$ dipende da $m_2$ e non da $m_1$, il che è una vulnerabilità strutturale.

**Attacco Chosen Plaintext:**

Scegliamo $m = \mathbf{0}^{64} \| m_2$ (prima metà nulla, seconda qualsiasi). Allora:

$$c_1 = 0^{64} \oplus h(m_2) \oplus k_1 = h(m_2) \oplus k_1$$

Poiché $m_2$ è noto all'avversario (lo ha scelto), può calcolare $h(m_2)$. Quindi:

$$k_1 = c_1 \oplus h(m_2)$$

Con $k_1$ noto, dall'equazione $c_2 = m_2 \oplus c_1 \oplus k_2$:

$$k_2 = c_2 \oplus m_2 \oplus c_1$$

**Con un solo messaggio scelto, l'avversario recupera l'intera chiave $k = k_1 \| k_2$.**

> ⚠️ Questo cifrario è insicuro contro CPA. La vulnerabilità deriva dalla linearità di $c_1$ rispetto a $k_1$ (operazione XOR) e dalla calcolabilità di $h(m_2)$ da parte dell'avversario.

**Riepilogo dell'attacco:**
1. Scegli $m = 0^{64} \| m_2$ per qualsiasi $m_2$.
2. Ottieni $(c_1, c_2)$.
3. Calcola $h(m_2)$ (noto perché $m_2$ è stato scelto).
4. $k_1 = c_1 \oplus h(m_2)$.
5. $k_2 = c_2 \oplus m_2 \oplus c_1$.

#### Parte c — Cifratura numerica con complemento a bit (10 punti)

**Parametri ridotti:** messaggi di 32 bit totali → $m_1, m_2$ di 16 bit ciascuno, hash di 16 bit, $h = \overline{x}$ (NOT bit a bit).

- $m_1 = 0101010101010101$ (16 bit)
- $m_2 = \underbrace{11\ldots1}_{16} = 1111111111111111$ (16 bit, $\{1\}^{16}$)
- $k_1 = k_2 = 0000111111110000$ (16 bit)

**Calcolo di $h(m_2)$:**

$$h(m_2) = \overline{1111111111111111} = 0000000000000000$$

**Calcolo di $c_1$:**

$$c_1 = m_1 \oplus h(m_2) \oplus k_1 = 0101010101010101 \oplus 0000000000000000 \oplus 0000111111110000$$

```
  0101010101010101
⊕ 0000111111110000
= 0101101010100101
```

$$c_1 = 0101101010100101$$

**Calcolo di $c_2$:**

$$c_2 = m_2 \oplus c_1 \oplus k_2$$

Prima: $m_2 \oplus c_1$:
```
  1111111111111111
⊕ 0101101010100101
= 1010010101011010
```

Poi: $(m_2 \oplus c_1) \oplus k_2$:
```
  1010010101011010
⊕ 0000111111110000
= 1010101010101010
```

$$c_2 = 1010101010101010$$

**Risultato:** $(c_1, c_2) = (0101101010100101,\ 1010101010101010)$.

> 💡 $c_2 = 1010101010101010$ è il pattern alternato 10 ripetuto 8 volte — risultato elegante della struttura XOR con il complemento.

---

## Domanda 3 — Firma DSS (15 punti)

### Traccia originale

> **(15 punti) Firma DSS.**
>
> a. (10) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (5) Cosa succede se viene firmato lo stesso messaggio in due diverse occasioni? Qual è la differenza con RSA?

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](<../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md>) — schema DSS completo
- [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — nonce riutilizzato

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

**Parametri del sistema (pubblici):**

- $p$: primo grande ($L = 1024, 2048, 3072$ bit).
- $q$: primo di 160/256 bit tale che $q \mid (p-1)$.
- $g = h^{(p-1)/q} \bmod p$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$.
- $H$: funzione hash crittografica (SHA-256 o superiore).

**Chiavi dell'utente:**

- Chiave privata: $x \in \{1, \ldots, q-1\}$ casuale.
- Chiave pubblica: $y = g^x \bmod p$.

**Firma di $M$:**

1. Scegli **nonce segreto** $k \in \{1, \ldots, q-1\}$ **casuale e unico**.
2. $r = (g^k \bmod p) \bmod q$. Se $r = 0$, ricomincia.
3. $s = k^{-1}(H(M) + x \cdot r) \bmod q$. Se $s = 0$, ricomincia.
4. Firma: $(r, s)$.

**Verifica di $(M, r, s)$ con chiave pubblica $y$:**

1. Verifica $0 < r < q$ e $0 < s < q$.
2. $w = s^{-1} \bmod q$.
3. $u_1 = H(M) \cdot w \bmod q$.
4. $u_2 = r \cdot w \bmod q$.
5. $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$.
6. Accetta se e solo se $v = r$.

**Correttezza:** dalla definizione $s = k^{-1}(H(M)+xr)$ si ricava $k = w(H(M)+xr) = u_1 + xu_2$, quindi $g^{u_1}y^{u_2} = g^{u_1+xu_2} = g^k$ e $v = (g^k \bmod p) \bmod q = r$. $\checkmark$

> 📌 Il nonce $k$ deve essere scelto diverso per ogni firma e tenuto segreto. Se $k$ è noto, la chiave privata $x$ si calcola immediatamente: $x = r^{-1}(sk - H(M)) \bmod q$.

#### Parte b — Firma dello stesso messaggio due volte; confronto con RSA (5 punti)

**Caso 1 — nonce $k$ riusato (errore critico):**

Se lo stesso $k$ viene usato per due firme (anche di messaggi diversi $M$ e $M'$), le firme $(r,s)$ e $(r',s')$ hanno $r = r'$ (poiché $r$ dipende solo da $k$). Un avversario calcola:

$$k = (s - s')^{-1}(H(M) - H(M')) \bmod q \qquad \Rightarrow \qquad x = r^{-1}(sk - H(M)) \bmod q$$

**La chiave privata è completamente compromessa.**

> ⚠️ Questo attacco reale ha violato la chiave privata di Sony nella PlayStation 3 (2010): Sony usava un valore di $k$ costante, permettendo di estrarre la chiave privata del firmware.

**Caso 2 — nonce $k$ diverso (corretto):**

Se $k$ è scelto casualmente e diverso per ogni firma, le due firme dello stesso messaggio $M$ produrranno $(r_1, s_1) \neq (r_2, s_2)$ (poiché $r$ dipende da $k$). Entrambe le firme sono valide ma diverse, senza rivelare informazioni sulla chiave privata.

**Confronto con RSA:**

| Proprietà | DSS/DSA | RSA (textbook) |
|---|---|---|
| Determinismo | NON deterministico (dipende da $k$) | Deterministico |
| Stesso messaggio firmato due volte | Firme diverse (se $k$ diverso) | Stessa firma |
| Vulnerabilità nonce riusato | Critica: rivela $x$ | N/A (no nonce) |
| Sicurezza basata su | Logaritmo discreto | Fattorizzazione |

RSA (textbook) è deterministico: la stessa chiave e lo stesso messaggio producono sempre la stessa firma. Questo è sia più semplice sia una vulnerabilità (attacchi a dizionario). RSA-PSS introduce randomizzazione analoga a DSS.

---

## Domanda 4 — Secret Sharing (25 punti)

### Traccia originale

> **(25 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (15 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$: distribuzione e ricostruzione (10 punti)

**Distribuzione:** il dealer sceglie un primo $p > n$ e un segreto $s \in \mathbb{Z}_p$. Costruisce un polinomio casuale di grado $k-1$:

$$f(x) = s + a_1 x + a_2 x^2 + \ldots + a_{k-1} x^{k-1} \pmod{p}$$

con $a_1, \ldots, a_{k-1}$ scelti casualmente in $\mathbb{Z}_p$ e $f(0) = s$. Il partecipante $P_i$ riceve la share $s_i = f(i) \pmod{p}$.

**Ricostruzione:** qualsiasi sottoinsieme di $k$ partecipanti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$ può recuperare $s = f(0)$ tramite interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{0 - i_l}{i_j - i_l} \pmod{p}$$

Con meno di $k$ share, il segreto è **indistinguibile** da qualsiasi valore in $\mathbb{Z}_p$ (sicurezza perfetta alla Shannon).

#### Parte b — Schema $(2,3)$ in $\mathbb{Z}_{11}$, share $s(1)=3$, $s(2)=4$ (15 punti)

**Dati:** $p=11$, share $(1,3)$ e $(2,4)$.

**Interpolazione di Lagrange per trovare $f(0)$:**

$$f(0) = 3 \cdot \lambda_1 + 4 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 4 \cdot 10 = 6 + 40 = 46 \pmod{11}$$

$46 = 4 \cdot 11 + 2$, quindi $46 \bmod 11 = 2$.

**Il segreto è $s = 2$.**

**Verifica:** il polinomio è $f(x) = 2 + a_1 x$. Da $f(1) = 2 + a_1 = 3 \Rightarrow a_1 = 1$. Quindi $f(x) = 2 + x$.

- $f(1) = 2 + 1 = 3$ ✓
- $f(2) = 2 + 2 = 4$ ✓
- $f(0) = 2$ ✓

> 💡 **Confronto con appelli precedenti:** le share $(1,3),(2,4)$ in $\mathbb{Z}_{11}$ danno $s=2$ con polinomio $f(x)=2+x$ (coefficiente angolare 1). Nelle share $(1,3),(2,5)$ dell'appello 19/09/2025 si aveva $f(x)=1+2x$ con $s=1$. La variazione di una sola share (4→5) cambia sia il segreto sia il coefficiente angolare.

---

## Pattern e osservazioni per l'esame

**Struttura:**

- D1 (25 punti): Vigenère completo (teoria + IC numerico + MIC numerico). La struttura è identica all'appello 10/02/2025 (L2) e 16/02/2023 (UD1 L2), solo le parole cambiano.
- D2 (35 punti, la più pesante): cifrario XOR+hash — struttura identica all'appello 10/02/2025 (UD3 L2). Il professore ripropone lo stesso schema, stesso esempio numerico nella parte c.
- D3 (15 punti): DSS — identica all'appello 19/09/2025 (L6, D3). Risposta tipo applicabile direttamente.
- D4 (25 punti): Secret Sharing numerico con $\mathbb{Z}_{11}$, share diverse. La procedura è sempre la stessa.

**Consigli:**

- Per D2 parte c: il complemento a bit di $1^{16}$ è $0^{16}$, il che semplifica drasticamente il calcolo di $c_1$ (l'XOR con $h(m_2)=0^{16}$ è neutro).
- Per D4: con $\lambda_1=2$ e $\lambda_2=10$ in $\mathbb{Z}_{11}$ (valori fissi per le share agli indici 1 e 2), il calcolo si riduce a $f(0) = 2 s_1 + 10 s_2 \pmod{11}$.
