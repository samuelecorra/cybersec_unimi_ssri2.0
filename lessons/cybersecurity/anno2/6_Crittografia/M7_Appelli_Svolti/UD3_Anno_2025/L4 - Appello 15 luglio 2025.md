# Appello del 15 luglio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 15/07/2025, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1 - Cifrari a sostituzione su parole binarie di n bit.md`](../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1%20-%20Cifrari%20a%20sostituzione%20su%20parole%20binarie%20di%20n%20bit.md) — cifrari a sostituzione binari, spazio chiavi
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà funzioni hash
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — parametri RSA
> - [`../../M3_Cifrature_Asimmetriche/UD2/L5 - Sicurezza di RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L5%20-%20Sicurezza%20di%20RSA.md) — sicurezza e attacchi RSA
> - [`../../M3_Cifrature_Asimmetriche/UD5_Approfondimenti_Esame/L1 - Double-RSA - analisi di sicurezza.md`](../../M3_Cifrature_Asimmetriche/UD5_Approfondimenti_Esame/L1%20-%20Double-RSA%20-%20analisi%20di%20sicurezza.md) — Double-RSA
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir

---

## Domanda 1 — Cifratura classica: sostituzione su parole binarie (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.**
>
> In un cifrario a sostituzione stringhe di 3 bit sono sostituite da altri blocchi di 3 bit, dove ciascun blocco viene sostituito con il blocco ottenuto sommando 1. Per esempio il blocco $000 \to 001$; $001 \to 010$, $010 \to 011$ etc.
>
> a. (5) Quanti cifrari a sostituzione esistono per parole binarie di lunghezza 3?
>
> b. (10) Se si scrive il cifrario ottenuto in forma tabellare, qual è la dimensione della tabella in bit? Generalizzare la risposta al caso di parole formate da $n$ bit.
>
> c. (15) Se il cifrario opera su un messaggio $m = (m_1, m_2, m_3)$ e con una chiave $k = (k_1, k_2, k_3)$ si ottiene il testo cifrato $c = (m_1 \oplus m_2 \oplus k_1,\ m_2 \oplus k_2,\ m_3 \oplus k_3)$, dove $\oplus$ è lo XOR:
>
> i. Quante sostituzioni sono possibili con questo cifrario?
>
> ii. Cifrare il messaggio $M_1 = 011001$ con chiave $k = (1,1,0)$, e decifrare il cifrato ottenuto.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1 - Cifrari a sostituzione su parole binarie di n bit.md`](../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1%20-%20Cifrari%20a%20sostituzione%20su%20parole%20binarie%20di%20n%20bit.md) — conteggio cifrari a sostituzione

### Soluzione dettagliata

#### Parte a — Numero di cifrari a sostituzione su parole di 3 bit (5 punti)

Un cifrario a sostituzione su parole di 3 bit è una **biiezione** (permutazione) sull'insieme $\{0,1\}^3 = \{000, 001, 010, 011, 100, 101, 110, 111\}$.

Questo insieme ha $2^3 = 8$ elementi.

Il numero di permutazioni di un insieme di $N$ elementi è $N!$.

Per parole di 3 bit: $N = 2^3 = 8$, quindi il numero di cifrari a sostituzione è:

$$8! = 40320$$

> 📌 In generale, per parole di $n$ bit, il numero di cifrari a sostituzione è $(2^n)!$.

#### Parte b — Dimensione della tabella e generalizzazione (10 punti)

**Rappresentazione tabellare del cifrario.** La tabella elenca, per ciascuno degli 8 input possibili, il corrispondente output. Il cifrario dato ("somma 1 modulo $2^3$") in forma tabellare è:

| Input $m$ | Output $E(m)$ |
|:---:|:---:|
| 000 | 001 |
| 001 | 010 |
| 010 | 011 |
| 011 | 100 |
| 100 | 101 |
| 101 | 110 |
| 110 | 111 |
| 111 | 000 |

**Dimensione della tabella in bit.** La domanda chiede quanti **bit** servono per memorizzare questa tabella. Il punto chiave è che la **colonna degli input è implicita** e non va memorizzata: se si concorda di elencare le righe nell'**ordine canonico** (riga $i$ = input $i$, cioè $000, 001, 010, \ldots, 111$), sapere "a quale riga siamo" equivale già a conoscere l'input. Basta quindi memorizzare la sola **colonna degli output**:

- numero di righe: $2^3 = 8$ (un output per ciascun input);
- bit per riga (solo output): $3$.

$$\text{dimensione} = 2^3 \times 3 = 8 \times 3 = 24 \text{ bit}$$

> 💡 **Perché non si contano anche le colonne di input?** Perché l'input è ricavabile dalla **posizione della riga**: memorizzarlo sarebbe ridondante (non aggiunge informazione). Se invece si volessero memorizzare *esplicitamente entrambe le colonne* (input **e** output), servirebbero $2^3 \times (3 + 3) = 8 \times 6 = 48$ bit; ma i 24 bit della colonna input si ricavano gratis dall'ordinamento, perciò la risposta compatta e attesa è **24 bit** (sola colonna output).

**Generalizzazione a parole di $n$ bit:**

- numero di righe: $2^n$ (tutti i possibili input);
- bit per riga: $n$ (ogni output è di $n$ bit).

$$\boxed{\text{dimensione tabella} = n \cdot 2^n \text{ bit}}$$

**Verifica con $n=3$:** $3 \cdot 2^3 = 3 \cdot 8 = 24$ bit ✓ (e $2n \cdot 2^n = 48$ bit nella variante che memorizza anche la colonna input).

> 💡 Per confronto, una chiave DES (56 bit) rappresenta un'S-box parziale, mentre la specifica completa delle 8 S-box del DES richiede molto più spazio ($8 \times 64 \times 4 = 2048$ bit, ma non tutte le S-box del DES sono permutazioni complete).

#### Parte c — Cifrario XOR a blocchi (15 punti)

Il cifrario opera su blocchi di 3 bit con chiave di 3 bit, secondo le regole:

$$c = (m_1 \oplus m_2 \oplus k_1,\ m_2 \oplus k_2,\ m_3 \oplus k_3)$$

#### Parte c.i — Numero di sostituzioni possibili (5 punti)

La chiave $k = (k_1, k_2, k_3)$ con $k_i \in \{0,1\}$ ha $2^3 = 8$ possibili valori.

Ogni diverso valore di $k$ produce una diversa funzione di cifratura (ovvero una diversa sostituzione/permutazione sui blocchi di 3 bit).

**Numero di sostituzioni possibili = numero di chiavi distinte = $2^3 = 8$.**

> ⚠️ Questo numero (8) è molto minore del numero totale di permutazioni possibili su 3 bit ($8! = 40320$). Il cifrario XOR strutturato realizza solo un sottoinsieme molto limitato di tutte le possibili permutazioni, il che lo rende crittograficamente debole.

#### Parte c.ii — Cifratura e decifratura (10 punti)

**Messaggio:** $M_1 = 011001$ (6 bit = 2 blocchi da 3 bit)
- Primo blocco: $m = (0, 1, 1)$
- Secondo blocco: $m' = (0, 0, 1)$

**Chiave:** $k = (k_1, k_2, k_3) = (1, 1, 0)$

**Cifratura del primo blocco $(0, 1, 1)$:**

$$c_1 = m_1 \oplus m_2 \oplus k_1 = 0 \oplus 1 \oplus 1 = 0$$

$$c_2 = m_2 \oplus k_2 = 1 \oplus 1 = 0$$

$$c_3 = m_3 \oplus k_3 = 1 \oplus 0 = 1$$

Primo blocco cifrato: $(0, 0, 1) = $ **001**

**Cifratura del secondo blocco $(0, 0, 1)$:**

$$c_1 = m_1 \oplus m_2 \oplus k_1 = 0 \oplus 0 \oplus 1 = 1$$

$$c_2 = m_2 \oplus k_2 = 0 \oplus 1 = 1$$

$$c_3 = m_3 \oplus k_3 = 1 \oplus 0 = 1$$

Secondo blocco cifrato: $(1, 1, 1) = $ **111**

**Testo cifrato:** $C = 001111$

**Decifratura del ciphertext $C = 001111$:**

Dobbiamo invertire la funzione di cifratura. Dall'equazione:

$$c_1 = m_1 \oplus m_2 \oplus k_1$$
$$c_2 = m_2 \oplus k_2$$
$$c_3 = m_3 \oplus k_3$$

**Recupero di $m$:** da queste equazioni, poiché XOR è la propria inversa:

$$m_3 = c_3 \oplus k_3$$

$$m_2 = c_2 \oplus k_2$$

$$m_1 = c_1 \oplus m_2 \oplus k_1 = c_1 \oplus (c_2 \oplus k_2) \oplus k_1$$

**Decifratura primo blocco** $c = (0, 0, 1)$, chiave $(1, 1, 0)$:

$$m_3 = 1 \oplus 0 = 1$$
$$m_2 = 0 \oplus 1 = 1$$
$$m_1 = 0 \oplus 1 \oplus 1 = 0$$

Primo blocco decrittato: $(0, 1, 1) = $ **011** ✓

**Decifratura secondo blocco** $c = (1, 1, 1)$, chiave $(1, 1, 0)$:

$$m_3 = 1 \oplus 0 = 1$$
$$m_2 = 1 \oplus 1 = 0$$
$$m_1 = 1 \oplus 0 \oplus 1 = 0$$

Secondo blocco decrittato: $(0, 0, 1) = $ **001** ✓

**Messaggio recuperato:** $M_1 = 011001$ ✓

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

Poiché $\operatorname{mcd}(2, 10) = 2$ e $2 \mid 2$, ci sono $\operatorname{mcd}(2,10) = 2$ soluzioni in $\mathbb{Z}_{10}$: $x' = 1$ e $x' = 6$.

La soluzione diversa da 6 è $x' = 1$.

**Verifica:** $H(1) = 2 \cdot 1 + 8 \bmod 10 = 10 \bmod 10 = 0 = H(6)$ ✓

**Collisione trovata:** $H(6) = H(1) = 0$, con $6 \neq 1$.

**Perché manca la second preimage resistance:**

$H(x) = 2x + 8 \bmod 10$ non è una biiezione in $\mathbb{Z}_{10}$ perché $\operatorname{mcd}(2, 10) = 2 \neq 1$: la funzione è 2-a-1 (ogni valore dell'immagine ha esattamente due preimmagini, che differiscono di 5). Trovare una seconda preimmagine per qualsiasi valore è banale.

In generale:

$$H(x) = H(x + 5) \quad \forall x \in \mathbb{Z}_{10}$$

Dato $x$, la seconda preimmagine è sempre $x' = (x + 5) \bmod 10$.

> ⚠️ Nota: questa stessa osservazione si applica all'appello del 20/01/2025 (con $H(x)=5x+12 \bmod 20$): funzioni hash lineari con coefficiente non coprimo al modulo hanno sempre collisioni strutturali banali.

---

## Domanda 3 — Cifratura asimmetrica RSA e Double-RSA (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica.**
>
> a. (5 punti) Si descriva la definizione dei parametri in RSA.
>
> b. (5 punti) Si discuta l'utilizzo "naive" di RSA e la sua insicurezza nel caso known plaintext (quando ad esempio i messaggi da cifrare siano pochi).
>
> c. (10 punti) Si consideri l'applicazione di Double-RSA, che definisce la cifratura di un messaggio $M$, utilizzando le due chiavi pubbliche $e_1$ ed $e_2$ diverse attribuite a ciascun utente:
> $$C(M) = ((M)^{e_1})^{e_2}$$
> Si facciano considerazioni sulla sicurezza di questo cifrario.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — parametri e algoritmo RSA
- [`../../M3_Cifrature_Asimmetriche/UD2/L5 - Sicurezza di RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L5%20-%20Sicurezza%20di%20RSA.md) — attacchi RSA
- [`../../M3_Cifrature_Asimmetriche/UD5_Approfondimenti_Esame/L1 - Double-RSA - analisi di sicurezza.md`](../../M3_Cifrature_Asimmetriche/UD5_Approfondimenti_Esame/L1%20-%20Double-RSA%20-%20analisi%20di%20sicurezza.md) — Double-RSA

### Soluzione dettagliata

#### Parte a — Definizione dei parametri RSA (5 punti)

RSA (Rivest, Shamir, Adleman, 1977) è il crittosistema asimmetrico più diffuso.

**Generazione dei parametri:**

1. Si scelgono due numeri primi grandi e distinti $p$ e $q$ (in pratica, $\geq 1024$ bit ciascuno).
2. Si calcola il **modulo**: $n = p \cdot q$.
3. Si calcola la **funzione di Eulero**: $\phi(n) = (p-1)(q-1)$.
4. Si sceglie l'**esponente pubblico** $e$ tale che $1 < e < \phi(n)$ e $\operatorname{mcd}(e, \phi(n)) = 1$ (tipicamente $e = 65537 = 2^{16}+1$).
5. Si calcola l'**esponente privato** $d$ tale che $d \cdot e \equiv 1 \pmod{\phi(n)}$ (inverso moltiplicativo di $e$ modulo $\phi(n)$, calcolabile con l'algoritmo di Euclide esteso).

**Chiave pubblica:** $(n, e)$

**Chiave privata:** $(n, d)$ (o equivalentemente $(p, q, d)$)

**Cifratura:** $C = M^e \bmod n$

**Decifratura:** $M = C^d \bmod n$

**Correttezza:** per il teorema di Eulero, $M^{e \cdot d} = M^{1 + k\phi(n)} \equiv M \pmod{n}$ per $\operatorname{mcd}(M, n) = 1$.

#### Parte b — Utilizzo "naive" di RSA e insicurezza con pochi messaggi (5 punti)

**Uso naive:** consiste nel cifrare messaggi direttamente con RSA senza padding randomizzato (RSA deterministic, noto anche come "textbook RSA").

**Insicurezza con pochi messaggi possibili:**

Se lo spazio dei messaggi è piccolo (es. $M \in \{0, 1, 2, \ldots, N_{\max}\}$ con $N_{\max}$ piccolo), un avversario che conosce la chiave pubblica $(n, e)$ può:

1. Costruire un **dizionario** pre-calcolando $C_i = i^e \bmod n$ per ogni $i \in \{0, \ldots, N_{\max}\}$.
2. Dato il ciphertext $C$, cercare nel dizionario il valore $i$ tale che $C_i = C$.

Poiché il cifrario è **deterministico** (lo stesso $M$ produce sempre lo stesso $C$), l'avversario identifica $M$ con costo $O(N_{\max})$.

**Esempio tipico:** messaggi binari (sì/no), voti (1-30), codici di stato, identificatori piccoli.

> ⚠️ Questo attacco è un **attacco a dizionario** o **known plaintext attack strutturato**: non rompe RSA matematicamente, ma sfrutta la piccola cardinalità dello spazio dei messaggi e il determinismo del cifrario. La soluzione standard è usare OAEP (Optimal Asymmetric Encryption Padding) che randomizza ogni cifratura.

#### Parte c — Double-RSA: analisi di sicurezza (10 punti)

**Schema Double-RSA:**

$$C(M) = ((M)^{e_1})^{e_2} = M^{e_1 \cdot e_2} \bmod n$$

(Assumiamo che entrambe le cifrature usino lo stesso modulo $n$; se usassero moduli diversi, l'analisi cambia e il prodotto non si semplifica così.)

**Analisi della sicurezza:**

**Caso 1 — stesso modulo $n$:**

$$C = M^{e_1 \cdot e_2} \bmod n$$

Double-RSA con lo stesso modulo è equivalente a RSA standard con esponente $e = e_1 \cdot e_2$. La sicurezza dipende dalla dimensione di $e_1 \cdot e_2$:

- Se $e_1$ ed $e_2$ sono piccoli (es. $e_1 = e_2 = 3$), allora $e = 9$, che è molto piccolo. Un esponente pubblico piccolo combinato con messaggi piccoli permette l'**attacco con la radice $e$-esima**: se $M^e < n$, allora $C = M^e$ senza riduzione modulare, e l'avversario calcola $M = C^{1/e}$ direttamente con la radice intera.

- **Attacco di Hastad:** se lo stesso messaggio $M$ viene cifrato con lo stesso esponente $e$ ma moduli diversi $n_1, n_2, \ldots, n_e$, allora con il Teorema Cinese dei Resti si recupera $M^e$ e quindi $M$ calcolando la radice $e$-esima su $\mathbb{Z}$.

**Caso 2 — attacco Common Modulus:**

Se Alice e Bob usano lo stesso modulo $n$ ma esponenti diversi $e_1, e_2$ con $\operatorname{mcd}(e_1, e_2) = 1$, e lo stesso messaggio $M$ viene cifrato con entrambe le chiavi:

$$C_1 = M^{e_1} \bmod n, \quad C_2 = M^{e_2} \bmod n$$

Allora un avversario calcola $s_1, s_2$ tali che $s_1 e_1 + s_2 e_2 = 1$ (Bézout), e:

$$M = C_1^{s_1} \cdot C_2^{s_2} \bmod n$$

**Conclusione sulla sicurezza di Double-RSA:**

> ⚠️ Double-RSA con lo stesso modulo non aumenta la sicurezza rispetto a RSA singolo: l'esponente composto $e_1 \cdot e_2$ è più grande, ma se i singoli esponenti sono piccoli, possono emergere vulnerabilità specifiche (small exponent attack). Se i moduli sono diversi, si applicano attacchi correlati. In entrambi i casi, Double-RSA non è raccomandato perché non offre sicurezza dimostrabile superiore a RSA con un singolo esponente ben scelto.

---

## Domanda 4 — Secret Sharing (30 punti)

### Traccia originale

> **(30 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione nel caso $(k,n)$.
>
> b. (10 punti) Per uno schema $(3,3)$ in $\mathbb{Z}_7$ e ricostruire il segreto condiviso, sapendo le share in possesso di Alice sono $s_1=6,\ s_2=4,\ s_3=2$.
>
> c. (10 punti) Per uno schema $(2,3)$ in $\mathbb{Z}_7$ ricostruire il segreto condiviso, sapendo che $s(1)=3$ e $s(2)=4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir, variante (n,n)

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$: distribuzione e ricostruzione (10 punti)

(Si veda L2 Domanda 4a per la trattazione completa. Sintesi:)

**Distribuzione:** dealer sceglie $f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod{p}$ con $a_i$ casuali e $f(0)=s$. Partecipante $P_i$ riceve $s_i = f(i) \pmod{p}$.

**Ricostruzione:** con $k$ share $(i_j, s_{i_j})$:

$$s = \sum_{j=1}^{k} s_{i_j} \cdot \lambda_j \pmod{p}, \quad \lambda_j = \prod_{\substack{l=1\\l\neq j}}^{k} \frac{-i_l}{i_j - i_l} \pmod{p}$$

#### Parte b — Schema $(3,3)$ in $\mathbb{Z}_7$: ricostruzione con le 3 share di Alice (10 punti)

**Dati:**
- Schema $(3,3)$: threshold $k=3$, partecipanti $n=3$ (tutti e 3 necessari)
- Campo: $\mathbb{Z}_7$ ($p=7$)
- Share: $s_1 = f(1) = 6$, $s_2 = f(2) = 4$, $s_3 = f(3) = 2$
- Punti: $(1, 6)$, $(2, 4)$, $(3, 2)$

Con $k=3$, il polinomio è di grado $k-1=2$: $f(x) = s + a_1 x + a_2 x^2$.

**Interpolazione di Lagrange per trovare $f(0)$:**

$$f(0) = 6 \cdot \lambda_1 + 4 \cdot \lambda_2 + 2 \cdot \lambda_3 \pmod{7}$$

**Calcolo di $\lambda_1$** (punto $x_1=1$, valutiamo in $x=0$):

$$\lambda_1 = \frac{(0-2)(0-3)}{(1-2)(1-3)} = \frac{(-2)(-3)}{(-1)(-2)} = \frac{6}{2} = 3 \pmod{7}$$

In $\mathbb{Z}_7$: $\frac{6}{2} = 6 \cdot 2^{-1}$. Inverso di 2 in $\mathbb{Z}_7$: $2 \cdot 4 = 8 \equiv 1 \pmod 7$, quindi $2^{-1} = 4$.

$$\lambda_1 = 6 \cdot 4 = 24 \bmod 7 = 3$$

**Calcolo di $\lambda_2$** (punto $x_2=2$, valutiamo in $x=0$):

$$\lambda_2 = \frac{(0-1)(0-3)}{(2-1)(2-3)} = \frac{(-1)(-3)}{(1)(-1)} = \frac{3}{-1} = -3 \equiv 4 \pmod{7}$$

**Calcolo di $\lambda_3$** (punto $x_3=3$, valutiamo in $x=0$):

$$\lambda_3 = \frac{(0-1)(0-2)}{(3-1)(3-2)} = \frac{(-1)(-2)}{(2)(1)} = \frac{2}{2} = 1 \pmod{7}$$

**Calcolo del segreto:**

$$f(0) = 6 \cdot 3 + 4 \cdot 4 + 2 \cdot 1 \pmod{7}$$

$$= 18 + 16 + 2 \pmod{7} = 36 \pmod{7}$$

$36 = 5 \cdot 7 + 1$, quindi $36 \bmod 7 = 1$.

**Il segreto è $s = 1$.**

**Verifica:** Proviamo a trovare il polinomio $f(x) = 1 + a_1 x + a_2 x^2 \pmod 7$ che soddisfa i tre punti:

- $f(1) = 1 + a_1 + a_2 = 6 \Rightarrow a_1 + a_2 = 5 \pmod 7$
- $f(2) = 1 + 2a_1 + 4a_2 = 4 \Rightarrow 2a_1 + 4a_2 = 3 \pmod 7$
- $f(3) = 1 + 3a_1 + 9a_2 = 2 \Rightarrow 3a_1 + 2a_2 = 1 \pmod 7$ (poiché $9 \bmod 7 = 2$)

Dalla prima: $a_1 = 5 - a_2$. Sostituendo nella seconda:
$2(5 - a_2) + 4a_2 = 3 \Rightarrow 10 - 2a_2 + 4a_2 = 3 \Rightarrow 10 + 2a_2 = 3 \Rightarrow 2a_2 = 3 - 10 = -7 \equiv 0 \pmod 7 \Rightarrow a_2 = 0$.

Quindi $a_1 = 5$ e il polinomio è $f(x) = 1 + 5x \pmod 7$.

Verifica: $f(1)=6$ ✓, $f(2)=11 \bmod 7=4$ ✓, $f(3)=16 \bmod 7=2$ ✓.

Il polinomio è lineare (grado 1), non grado 2 come atteso per $(3,3)$: questo è consistente — un polinomio di grado $\leq k-1$ è valido.

#### Parte c — Schema $(2,3)$ in $\mathbb{Z}_7$: ricostruzione con share $s(1)=3$, $s(2)=4$ (10 punti)

**Dati:**
- Campo: $\mathbb{Z}_7$ ($p=7$)
- Share: $(1, 3)$ e $(2, 4)$

**Interpolazione di Lagrange:**

$$f(0) = 3 \cdot \lambda_1 + 4 \cdot \lambda_2 \pmod 7$$

$$\lambda_1 = \frac{0-2}{1-2} = \frac{-2}{-1} = 2 \pmod 7$$

$$\lambda_2 = \frac{0-1}{2-1} = \frac{-1}{1} = -1 \equiv 6 \pmod 7$$

$$f(0) = 3 \cdot 2 + 4 \cdot 6 = 6 + 24 = 30 \pmod 7$$

$30 = 4 \cdot 7 + 2$, quindi $30 \bmod 7 = 2$.

**Il segreto è $s = 2$.**

**Verifica:** $f(x) = 2 + a_1 x$. Da $f(1) = 2 + a_1 = 3 \Rightarrow a_1 = 1$. Polinomio: $f(x) = 2 + x$.
- $f(1) = 3$ ✓
- $f(2) = 4$ ✓

> 💡 Nota: la coppia di share $(s(1)=3, s(2)=4)$ è identica all'appello del 10/02/2025 e dell'appello del 05/09/2025, ma con modulo diverso ($\mathbb{Z}_7$ invece di $\mathbb{Z}_{11}$). Il segreto cambia (2 invece di 1). Questo mostra l'importanza del campo nel calcolo degli inversi di Lagrange.

---

## Pattern e osservazioni per l'esame

Questo appello (seconda sessione estiva) è caratterizzato da una domanda D1 inusuale:

**Struttura:**
- D1 (30 punti) è su cifrari classici "binari": conteggio di permutazioni e spazio delle chiavi. Richiede ragionamento combinatorio più che calcolo algebrico.
- D2 (20 punti) è la solita analisi delle funzioni hash con funzione giocattolo: identico schema all'appello di gennaio.
- D3 (20 punti) su RSA è mix teoria (parametri) + analisi sicurezza (naive use + Double-RSA).
- D4 (30 punti) su Secret Sharing è il più pesante: tre sotto-domande, una delle quali su schema $(3,3)$ più insolito del solito $(2,3)$.

**Consigli pratici:**
- Per le sostituzioni binarie: la formula chiave è $(2^n)!$ per il numero di permutazioni e $n \cdot 2^n$ per la dimensione della tabella. Memorizzarle.
- Per la hash con $H(x)=2x+8 \bmod 10$: il trucco è che il coefficiente 2 non è coprimo con 10, quindi la funzione non è iniettiva. Trovare la seconda preimmagine è banale.
- Per Double-RSA: il punto critico è notare che $((M)^{e_1})^{e_2} = M^{e_1 e_2}$: si riduce a RSA con esponente prodotto, il che può essere vulnerabile.
- Per il Lagrange in $\mathbb{Z}_7$ con $k=3$: il calcolo richiede trovare 3 coefficienti. Procedere sistematicamente.
