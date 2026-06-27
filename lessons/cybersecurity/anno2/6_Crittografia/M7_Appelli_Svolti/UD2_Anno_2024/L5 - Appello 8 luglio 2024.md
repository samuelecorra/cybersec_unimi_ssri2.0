# Appello dell'8 luglio 2024 — Soluzione completa

> 📌 **Informazioni appello:** 08/07/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrari a sostituzione su blocchi, dimensione chiave, cifratura "KOSTI"
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L3 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L3%20-%20Modalità%20operative%20del%20DES.md) — modalità ECB, CBC, CFB, OFB, CTR
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA naive, double-RSA, sicurezza
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — secret sharing (3,3) e (2,3) con calcoli numerici

---

## Domanda 1 — Cifratura simmetrica a blocchi e cifratura "KOSTI" (35 punti)

### Traccia originale

> **(35 punti) Cifratura simmetrica.**
>
> a. (10 punti) Discutere la seguente affermazione: "In un generico algoritmo di cifratura a sostituzione a blocchi di $n$ bit, le dimensioni della chiave sono $n \cdot 2^n$".
>
> b. (10 punti) Si consideri il seguente cifrario:
> [cifrario che prende blocchi binari di 3 bit, esegue una conversione in ottale, esegue un mapping $\pi$, e restituisce blocchi binari di 3 bit]. Si indichino le caratteristiche del cifrario, specificando quanti possibili cifrari diversi è possibile ottenere cambiando il mapping.
>
> c. (15 punti) Si abbia un linguaggio con 8 lettere: A, B, I, K, M, O, S, T dove si usa la rappresentazione decimale A = 0, B = 1, I = 2, K = 3, M = 4, O = 5, S = 6, T = 7. Per cifrare una lettera in questo linguaggio, si converte la lettera in forma binaria, si applica lo schema in figura [il mapping $\pi$], e si ottiene una nuova lettera corrispondente. Cifrare la parola "KOSTI". Si faccia qualche considerazione sulla robustezza di questo cifrario.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — spazio delle chiavi, sostituzione su blocchi
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — cifrari a sostituzione generali

### Soluzione dettagliata

#### Parte a — Dimensioni della chiave in un cifrario a sostituzione su $n$ bit (10 punti)

**Analisi dell'affermazione "le dimensioni della chiave sono $n \cdot 2^n$":**

Un cifrario a sostituzione generico su blocchi di $n$ bit è una **permutazione** dell'insieme $\{0,1\}^n$ di $2^n$ elementi. Per specificare completamente quale delle $(2^n)!$ permutazioni si sta usando, si può adottare la rappresentazione tabellare.

**La tabella di sostituzione** ha:
- $2^n$ righe (una per ogni possibile input)
- Ogni riga contiene il valore di output, che è un blocco di $n$ bit

Quindi la tabella occupa $2^n \times n$ bit $= n \cdot 2^n$ bit.

**L'affermazione è corretta in un senso preciso:**

$n \cdot 2^n$ bit è la dimensione della **rappresentazione tabellare** del cifrario (la "chiave" intesa come tabella completa di sostituzione). Questa è la quantità di informazione necessaria per memorizzare completamente il cifrario.

**Tuttavia, questa non è la dimensione della chiave nel senso tradizionale:**

La chiave ideale dovrebbe essere un indice compatto che identifica una delle $(2^n)!$ permutazioni. La dimensione ottimale della chiave sarebbe $\lceil \log_2((2^n)!) \rceil$ bit. Per $n$ grande:

$$\log_2((2^n)!) \approx 2^n \cdot n - 2^n \cdot \log_2 e \approx n \cdot 2^n$$

(per la formula di Stirling: $\log(m!) \approx m\log m - m\log e$). Quindi asintoticamente le due misure coincidono, ma la tabella è una rappresentazione ridondante (include elementi dell'output direttamente, non solo l'indice della permutazione).

**Esempio con $n = 3$:**

- Blocchi: $2^3 = 8$ possibili valori (da 000 a 111)
- Dimensione tabella: $3 \times 8 = 24$ bit
- Numero di permutazioni: $8! = 40320$
- Bit per identificare una permutazione: $\lceil \log_2(40320) \rceil = 16$ bit

La tabella occupa 24 bit ma basterebbero 16 bit per indicizzare la permutazione desiderata.

**Conclusione:** l'affermazione è sostanzialmente corretta per la rappresentazione tabellare della chiave. La dimensione della tabella è $n \cdot 2^n$ bit, che cresce molto rapidamente con $n$ (es. per $n=64$: circa $10^{21}$ bit), rendendo i cifrari a sostituzione generali impraticabili per $n$ grande. Questo è il motivo per cui i cifrari moderni usano strutture parametrizzate (S-box di dimensioni fisse) invece di tabelle generali.

> 📌 Questa discussione è alla base della **teoria delle chiavi in crittografia**: la lunghezza della chiave determina lo spazio dei cifrari possibili. Per $n=64$ (blocchi DES), $(2^{64})!$ è astronomicamente più grande di $2^{56}$ (lo spazio delle chiavi DES), il che significa che DES esplora una frazione microscopica dei possibili cifrari a sostituzione su 64 bit.

#### Parte b — Caratteristiche del cifrario su 3 bit con mapping $\pi$ (10 punti)

**Struttura del cifrario:**

Il cifrario opera su blocchi di **3 bit**. Il processo è:
1. Input: blocco di 3 bit (da 000 a 111, cioè valori ottali da 0 a 7)
2. Conversione in ottale: ogni blocco di 3 bit corrisponde a una cifra ottale (0-7)
3. Applicazione del mapping $\pi$: una permutazione di $\{0,1,2,3,4,5,6,7\}$
4. Output: il risultato del mapping riconvertito in 3 bit

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Caratteristiche del cifrario:**

- **Dimensione del blocco:** 3 bit
- **Spazio dell'input:** $2^3 = 8$ valori possibili (0-7 in ottale)
- **Struttura:** cifrario a sostituzione semplice (monoalfabetico sui simboli ottali)
- **Chiave:** la permutazione $\pi$ dei simboli $\{0,1,2,3,4,5,6,7\}$
- **Invertibilità:** poiché $\pi$ è una permutazione (biiezione), il cifrario è sempre invertibile.

**Numero di cifrari diversi:**

Cambiando il mapping $\pi$, si può scegliere qualsiasi permutazione dell'insieme $\{0,1,2,3,4,5,6,7\}$. Il numero di permutazioni di 8 elementi è:

$$8! = 8 \times 7 \times 6 \times 5 \times 4 \times 3 \times 2 \times 1 = 40320$$

Quindi esistono **40320 possibili cifrari diversi**, ognuno corrispondente a una diversa permutazione dei simboli ottali.

**Dimensione della chiave:** Per identificare uno dei 40320 mapping, servono $\lceil \log_2(40320) \rceil = 16$ bit. La tabella completa occupa $3 \times 8 = 24$ bit.

#### Parte c — Cifratura della parola "KOSTI" (15 punti)

**Codifica delle lettere:**

| Lettera | Valore decimale | Valore binario (3 bit) |
|---------|----------------|------------------------|
| A | 0 | 000 |
| B | 1 | 001 |
| I | 2 | 010 |
| K | 3 | 011 |
| M | 4 | 100 |
| O | 5 | 101 |
| S | 6 | 110 |
| T | 7 | 111 |

**La parola "KOSTI" in binario:**

| Lettera | Bin |
|---------|-----|
| K | 011 |
| O | 101 |
| S | 110 |
| T | 111 |
| I | 010 |

> ⚠️ Il mapping $\pi$ specifico dipende dalla figura/schema della traccia, che non è visivamente disponibile nel PDF (riferisce a "lo schema in figura"). Dalla struttura della domanda e dal contesto del corso, il cifrario proposto è probabilmente una permutazione fissa dei 3 bit o una S-box semplice fornita in figura.

**Applicando un mapping $\pi$ di esempio** (mapping identità per illustrare il procedimento, da sostituire con il mapping effettivo della figura):

Il procedimento generale è:
1. Convertire ogni lettera nel suo valore binario a 3 bit.
2. Applicare il mapping $\pi$ (prendere il valore ottale, leggere il corrispondente dalla tabella di $\pi$).
3. Riconvertire il risultato nella lettera corrispondente.

**Considerazioni sulla robustezza:**

Il cifrario è **estremamente debole** per i seguenti motivi:

1. **Sostituzione monoalfabetica su 8 simboli:** lo stesso simbolo viene sempre cifrato nello stesso modo. Un'analisi delle frequenze sulle lettere del ciphertext, confrontata con le frequenze dell'alfabeto di 8 lettere, permette di identificare rapidamente il mapping.

2. **Blocco troppo piccolo (3 bit = 8 simboli):** con soli 8 possibili valori di input, c'è un numero molto ridotto di combinazioni. L'intero spazio $\{0,1\}^3$ può essere esplorato in pochi istanti.

3. **Spazio delle chiavi ridottissimo:** anche se ci sono 40320 possibili permutazioni, un attacco a forza bruta che prova tutte le permutazioni richiede al massimo 40320 tentativi, il che è computazionalmente banale.

4. **Nessuna diffusione:** ogni lettera è cifrata indipendentemente. Non c'è dipendenza tra i caratteri del messaggio e del ciphertext.

5. **Vulnerabilità a known plaintext:** se si conosce anche solo una coppia (plaintext-lettera, ciphertext-lettera), si rivela il mapping per quel simbolo. Con 8 coppie note si recupera l'intero mapping.

> ✅ Questo cifrario è utile solo per scopi didattici, per illustrare il concetto di sostituzione su blocchi. Non ha alcuna sicurezza crittografica pratica.

---

## Domanda 2 — Modalità operative del DES (20 punti)

### Traccia originale

> **(20 punti) Cifratura simmetrica.**
>
> a. (10) Si descrivano le modalità di DES delineando vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L3 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L3%20-%20Modalità%20operative%20del%20DES.md) — ECB, CBC, CFB, OFB, CTR

### Soluzione dettagliata

#### Le modalità operative dei cifrari a blocchi

I cifrari a blocchi (DES, AES) cifrano blocchi di dimensione fissa (64 bit per DES). Per messaggi più lunghi, si definiscono le **modalità operative** che specificano come applicare il cifrario a blocchi ripetutamente.

**1. ECB — Electronic Codebook**

**Schema:** ogni blocco $P_i$ viene cifrato indipendentemente con la stessa chiave $K$:
$$C_i = E_K(P_i)$$

**Vantaggi:**
- Semplicità di implementazione.
- Parallelizzabile (cifratura e decifratura).
- Errori in un blocco non si propagano agli altri.

**Svantaggi:**
- **Deterministico:** lo stesso blocco di plaintext produce sempre lo stesso blocco di ciphertext. Un avversario può rilevare pattern ripetuti nel messaggio.
- **Non semanticamente sicuro:** non è sicuro contro attacchi CPA (Chosen Plaintext Attack).
- **Vulnerabile alla sostituzione di blocchi:** un avversario può riordinare, duplicare o sostituire blocchi senza che il destinatario se ne accorga.

> ⚠️ ECB è la modalità meno sicura. Non dovrebbe essere usata in pratica. Il classico esempio è la cifratura ECB di un'immagine bitmap, dove i pattern visivi rimangono riconoscibili nel ciphertext.

**2. CBC — Cipher Block Chaining**

**Schema:** ogni blocco di plaintext viene XORato con il blocco di ciphertext precedente prima di essere cifrato:
$$C_i = E_K(P_i \oplus C_{i-1}) \quad \text{con } C_0 = IV \text{ (vettore di inizializzazione)}$$

**Decifratura:** $P_i = D_K(C_i) \oplus C_{i-1}$

**Vantaggi:**
- **Semanticamente sicuro** (con IV casuale): blocchi identici nel plaintext producono ciphertext diversi.
- **Self-synchronizing:** errori in un blocco di ciphertext influenzano solo due blocchi del plaintext decifrato.

**Svantaggi:**
- **Non parallelizzabile in cifratura** (ogni blocco dipende dal precedente).
- **Parallelizzabile in decifratura** (si può calcolare $D_K(C_i)$ in parallelo e poi XORare con $C_{i-1}$).
- Un errore nel blocco $C_i$ corrode $P_i$ e $P_{i+1}$.
- Richiede IV casuale e non riutilizzabile.

**3. CFB — Cipher Feedback**

**Schema:** converte il cifrario a blocchi in uno stream cipher. Usa il cifrario per generare un keystream:
$$C_i = P_i \oplus E_K(C_{i-1}) \quad \text{con } C_0 = IV$$

**Decifratura:** $P_i = C_i \oplus E_K(C_{i-1})$

**Vantaggi:**
- Cifra dati di lunghezza arbitraria (non richiede padding).
- Autosincronizzante: un errore si propaga a un numero limitato di blocchi.

**Svantaggi:**
- Non parallelizzabile in cifratura.
- Errori nel ciphertext si propagano per alcuni blocchi.

**4. OFB — Output Feedback**

**Schema:** genera il keystream in anticipo, indipendentemente dal plaintext:
$$O_i = E_K(O_{i-1}), \quad C_i = P_i \oplus O_i \quad \text{con } O_0 = IV$$

**Vantaggi:**
- Errori nel ciphertext non si propagano al plaintext (solo il bit/blocco corrispondente è compromesso).
- Il keystream può essere precalcolato.

**Svantaggi:**
- **Non autosincronizzante:** se un bit del ciphertext viene perso, la sincronizzazione del keystream si rompe.
- Il keystream dipende solo dalla chiave e dall'IV, non dal plaintext.

**5. CTR — Counter Mode**

**Schema:** usa un contatore che viene cifrato per generare il keystream:
$$C_i = P_i \oplus E_K(\text{counter}_i)$$

dove $\text{counter}_i = \text{IV} + i$ (o analoga funzione del contatore).

**Vantaggi:**
- **Completamente parallelizzabile** sia in cifratura che in decifratura.
- Accesso casuale ai blocchi senza cifrare quelli precedenti.
- Precalcolo del keystream possibile.
- Errori non si propagano.

**Svantaggi:**
- Il contatore non deve mai essere riusato con la stessa chiave (altrimenti si riusa il keystream → vulnerabilità come nel one-time pad).

> 📌 **Riepilogo:**

| Modalità | Parallelizzabile enc | Parallelizzabile dec | Propagazione errori | Padding richiesto |
|----------|---------------------|---------------------|--------------------|--------------------|
| ECB | Sì | Sì | No (locale) | Sì |
| CBC | No | Sì | 2 blocchi | Sì |
| CFB | No | Sì (parziale) | Pochi blocchi | No |
| OFB | No (ma precalcolo sì) | Come enc | No | No |
| CTR | Sì | Sì | No | No |

---

## Domanda 3 — Cifratura asimmetrica RSA: sicurezza (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica.**
>
> a. (5 punti) Si descriva la definizione dei parametri in RSA.
>
> b. (10 punti) Si discuta l'utilizzo "naive" di RSA e la sua insicurezza nel caso known plaintext (quando ad esempio i messaggi da cifrare siano pochi).
>
> c. (10 punti) Si consideri l'applicazione di double-RSA, che definisce la cifratura di un messaggio $M$, utilizzando le due chiavi pubbliche $e_1$ ed $e_2$ diverse attribuite a ciascun utente:
> $C(M) = ((M)^{e_1})^{e_2}$
> Si facciano considerazioni sulla sicurezza di questo cifrario.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA, attacchi, sicurezza
- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1 - Cifrari non-standard XOR+hash - decifratura e CPA attack.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1%20-%20Cifrari%20non-standard%20XOR+hash%20-%20decifratura%20e%20CPA%20attack.md) — attacchi CPA e known plaintext

### Soluzione dettagliata

#### Parte a — Parametri di RSA (5 punti)

**Setup del crittosistema RSA:**

1. Scegliere due numeri primi grandi distinti $p$ e $q$ (tipicamente 1024-4096 bit ciascuno, per un modulo di 2048-8192 bit).
2. Calcolare il modulo: $n = p \cdot q$.
3. Calcolare la funzione di Eulero: $\varphi(n) = (p-1)(q-1)$.
4. Scegliere l'esponente pubblico $e$ con $1 < e < \varphi(n)$ e $\operatorname{mcd}(e, \varphi(n)) = 1$. Valori comuni: $e = 65537 = 2^{16}+1$ (primo, piccolo, molte operazioni sono XOR shift).
5. Calcolare l'esponente privato $d$ tale che $ed \equiv 1 \pmod{\varphi(n)}$ (algoritmo di Euclide esteso).

**Chiave pubblica:** $(n, e)$ — distribuita pubblicamente.
**Chiave privata:** $(n, d)$ o $(p, q, d, dp, dq, qinv)$ — tenuta segreta.

**Cifratura:** $C = M^e \bmod n$ (con $0 \leq M < n$).
**Decifratura:** $M = C^d \bmod n$.

I parametri $p$, $q$ e $\varphi(n)$ devono essere tenuti segreti; solo $n$ ed $e$ sono pubblici.

#### Parte b — RSA naive e insicurezza nel caso known plaintext (10 punti)

**RSA naive (textbook RSA):**

L'utilizzo "naive" (o "textbook") di RSA consiste nel cifrare direttamente il messaggio $M$ come $C = M^e \bmod n$, senza alcun padding o randomizzazione.

**Problema 1 — Determinismo:**

RSA naive è **deterministico**: lo stesso messaggio cifrato con la stessa chiave pubblica produce sempre lo stesso ciphertext. Questo viola la sicurezza semantica (IND-CPA, Indistinguishability under Chosen Plaintext Attack).

**Problema 2 — Attacco known plaintext con spazio dei messaggi piccolo:**

Supponiamo che i messaggi possibili siano pochi (es. $M \in \{0, 1, 2, \ldots, N-1\}$ con $N$ piccolo). Un avversario che intercetta il ciphertext $C$ può procedere così:

1. Costruire un dizionario: calcolare $M_i^e \bmod n$ per tutti i possibili messaggi $M_i$.
2. Confrontare $C$ con tutti gli elementi del dizionario.
3. Identificare $M$ quando $M_i^e \equiv C \pmod{n}$.

Questo è un attacco di **forza bruta sul testo in chiaro** (plaintext exhaustive search), reso possibile dal fatto che l'insieme dei messaggi è piccolo e la cifratura è deterministica. Per $N$ messaggi, l'attacco costa $O(N)$ operazioni di esponenziazione modulare.

**Esempio concreto:** se i messaggi sono numeri da 1 a 1000 (es. codici di autenticazione a 3 cifre), l'avversario può calcolare i 1000 ciphertext corrispondenti in pochi secondi e confrontarli con quello intercettato.

**Problema 3 — Cifratura di $M = 0$ o $M = 1$:**

$0^e \bmod n = 0$ e $1^e \bmod n = 1$: il ciphertext rivela direttamente il plaintext in questi casi banali.

**Soluzione — Padding:**

La soluzione standard è usare schemi di padding prima della cifratura:
- **PKCS#1 v1.5:** aggiunge byte di padding pseudo-casuali.
- **OAEP (Optimal Asymmetric Encryption Padding):** padding probabilistico che usa una funzione hash, garantendo sicurezza IND-CCA2.

Con padding probabilistico, lo stesso messaggio produce ciphertext diversi ad ogni cifratura, rendendo inutile l'attacco per dizionario.

#### Parte c — Double-RSA: sicurezza di $C(M) = ((M)^{e_1})^{e_2}$ (10 punti)

**Schema double-RSA:**

$$C(M) = M^{e_1 \cdot e_2} \bmod n$$

(assumendo che $e_1$ e $e_2$ appartengano allo stesso utente e usino lo stesso modulo $n$, o che la notazione indica due esponenziazioni successive con lo stesso modulo)

**Analisi di sicurezza:**

**1. Riduzione a RSA singolo:**

L'esponenziazione doppia $M^{e_1 e_2} \bmod n$ è equivalente a una singola esponenziazione RSA con esponente $e' = e_1 \cdot e_2 \bmod \varphi(n)$.

Quindi la decifratura richiede calcolare $d'$ tale che $e' \cdot d' \equiv 1 \pmod{\varphi(n)}$, ovvero $e_1 e_2 d' \equiv 1 \pmod{\varphi(n)}$.

**2. Caso in cui $e_1$ e $e_2$ sono coprimi con $\varphi(n)$ (ipotesi standard):**

Se entrambi $e_1$ e $e_2$ sono coprimi con $\varphi(n)$ (requisito standard di RSA), allora $e' = e_1 e_2$ è anch'esso invertibile modulo $\varphi(n)$. Il double-RSA non aggiunge sicurezza rispetto al singolo RSA con esponente $e'$.

**3. Attacco con common modulus:**

Un caso interessante è quando due utenti hanno lo stesso modulo $n$ ma esponenti diversi $e_1$ e $e_2$ (con $\operatorname{mcd}(e_1, e_2) = 1$). Se lo stesso messaggio $M$ viene cifrato con entrambe le chiavi pubbliche producendo $C_1 = M^{e_1} \bmod n$ e $C_2 = M^{e_2} \bmod n$, allora (usando l'identità di Bézout: $\exists a, b: ae_1 + be_2 = 1$):

$$M = C_1^a \cdot C_2^b \bmod n$$

Questo permette di recuperare $M$ senza conoscere nessuna chiave privata (uno degli esponenti $a$ o $b$ potrebbe essere negativo, richiedendo il calcolo dell'inverso modulare).

**4. Sicurezza nel caso generale (moduli diversi):**

Se $e_1$ e $e_2$ appartengono a utenti con moduli diversi $n_1$ e $n_2$, allora $C(M) = (M^{e_1} \bmod n_1)^{e_2} \bmod n_2$. In questo caso il double-RSA è equivalente a cifrare il ciphertext del primo RSA con il secondo RSA. Ciò non aumenta la sicurezza: chi conosce $d_2$ può recuperare $M^{e_1} \bmod n_1$ (il "messaggio" del secondo RSA), e poi potrebbe potenzialmente attaccare il primo RSA.

**5. Non aumenta la sicurezza:**

In generale, il double-RSA non aggiunge sicurezza rispetto a un singolo RSA ben configurato. I problemi del RSA naive (determinismo, spazio messaggi piccolo) rimangono. Il double-RSA aumenta il costo computazionale (due esponenziazioni invece di una) senza un corrispondente aumento di sicurezza.

> ⚠️ La corretta soluzione per aumentare la sicurezza di RSA non è usare RSA due volte, ma usare padding probabilistico (OAEP), scegliere parametri adeguati (chiavi lunghe), e per la firma digitale usare schemi come RSA-PSS.

---

## Domanda 4 — Secret Sharing numerico (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Per uno schema $(3, 3)$ in $\mathbb{Z}_{11}$ e ricostruire il segreto condiviso, sapendo le share in possesso di Alice sono $s_1 = 6$, $s_2 = 4$, $s_3 = 2$.
>
> b. (10 punti) Per uno schema $(2, 3)$ in $\mathbb{Z}_{11}$ ricostruire il segreto condiviso, sapendo che $s(1) = 3$ e $s(2) = 4$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — interpolazione di Lagrange in $\mathbb{Z}_p$

### Soluzione dettagliata

#### Parte a — Schema $(3, 3)$ in $\mathbb{Z}_{11}$: ricostruzione con $s_1=6, s_2=4, s_3=2$ (10 punti)

Nello schema $(n, n)$ con somma modulare, il segreto si ricostruisce sommando tutte le $n$ share:

$$S = s_1 + s_2 + \cdots + s_n \bmod p$$

Con $n = 3$, $p = 11$, $s_1 = 6$, $s_2 = 4$, $s_3 = 2$:

$$S = 6 + 4 + 2 \bmod 11 = 12 \bmod 11 = 1$$

**Segreto ricostruito:** $\boxed{S = 1}$

**Verifica della coerenza:** Se il dealer avesse distribuito il segreto $S = 1$ scegliendo $s_1 = 6$, $s_2 = 4$, allora $s_3 = S - s_1 - s_2 = 1 - 6 - 4 = -9 \equiv 2 \pmod{11}$ ✓

> 📌 Lo schema $(n,n)$ è molto semplice: la ricostruzione è una semplice somma modulare. Non richiede interpolazione di Lagrange, che è necessaria solo per schemi $(k,n)$ con $k < n$.

#### Parte b — Schema $(2, 3)$ in $\mathbb{Z}_{11}$: ricostruzione con $s(1)=3, s(2)=4$ (10 punti)

Lo schema $(2, 3)$ di Shamir usa un polinomio di grado $k-1 = 1$:

$$f(x) = a_0 + a_1 x \bmod 11$$

dove $a_0 = S$ è il segreto da trovare.

**Dati:** i punti $(x_1, y_1) = (1, 3)$ e $(x_2, y_2) = (2, 4)$.

**Metodo 1: Interpolazione di Lagrange per trovare $f(0) = S$:**

$$S = f(0) = y_1 \cdot L_1(0) + y_2 \cdot L_2(0) \bmod 11$$

dove:

$$L_1(0) = \frac{0 - x_2}{x_1 - x_2} = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2$$

$$L_2(0) = \frac{0 - x_1}{x_2 - x_1} = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

Quindi:

$$S = y_1 \cdot L_1(0) + y_2 \cdot L_2(0) = 3 \cdot 2 + 4 \cdot 10 \bmod 11$$

$$= 6 + 40 \bmod 11 = 46 \bmod 11$$

$$46 = 4 \cdot 11 + 2 \Rightarrow 46 \bmod 11 = 2$$

**Segreto ricostruito:** $\boxed{S = 2}$

**Metodo 2: Risoluzione del sistema lineare (alternativo):**

Il polinomio è $f(x) = a_0 + a_1 x \bmod 11$. Dalle due share:

$$\begin{cases} f(1) = a_0 + a_1 = 3 \pmod{11} \\ f(2) = a_0 + 2a_1 = 4 \pmod{11} \end{cases}$$

Sottraiamo la prima dalla seconda:

$$a_1 = 4 - 3 = 1 \pmod{11}$$

Dalla prima equazione:

$$a_0 = 3 - a_1 = 3 - 1 = 2 \pmod{11}$$

Quindi $f(x) = 2 + x$ e $S = f(0) = a_0 = 2$ ✓

**Verifica:** $f(1) = 2 + 1 = 3$ ✓; $f(2) = 2 + 2 = 4$ ✓

**Per trovare la terza share $s(3)$** (non richiesto dalla traccia, ma utile per comprendere lo schema $(2,3)$):

$$s(3) = f(3) = 2 + 3 = 5 \pmod{11}$$

Con qualsiasi due delle tre share $(s(1)=3, s(2)=4, s(3)=5)$ si può ricostruire il segreto $S = 2$.

---

## Pattern e osservazioni per l'esame

Questo appello (8 luglio 2024) ha alcune caratteristiche uniche rispetto agli altri della sessione estiva:

- **D1 (35 punti):** il peso maggiore è sulla cifratura simmetrica a blocchi. La domanda b sulla figura del mapping non è risolvibile senza la figura originale. In esame, avendo la figura, si deve completare la tabella del mapping e applicarla alle lettere. La parte c (cifratura di "KOSTI") segue meccanicamente dalla tabella.

- **D2 (20 punti):** modalità operative del DES. Tipicamente chiesta nelle sessioni estive. Conoscere i 5 schemi principali (ECB, CBC, CFB, OFB, CTR) con i loro vantaggi e svantaggi. La tabella riepilogativa è un ottimo modo per strutturare la risposta.

- **D3 (20 punti):** la domanda su RSA naive e double-RSA è teorica. La parte b richiede di spiegare l'attacco per dizionario nel caso di spazio messaggi piccolo. La parte c richiede di analizzare se $M^{e_1 e_2}$ sia più sicuro di $M^e$: la risposta è no, è equivalente a RSA con esponente composto.

- **D4 (20 punti):** secret sharing numerico. Per il (3,3) è una semplice somma modulare. Per il (2,3) si usa l'interpolazione di Lagrange (o la soluzione del sistema lineare, più semplice per $k=2$). Entrambi i metodi devono essere mostrati con tutti i passaggi.

**Consigli pratici:**

1. Per D4b (schema 2,3): il metodo del sistema lineare è molto più rapido dell'interpolazione di Lagrange completa quando $k=2$. Usare il sistema: scrivere le due equazioni, sottrarre per trovare $a_1$, poi $a_0=S$.

2. Per D3b (RSA naive): strutturare la risposta in: (1) problema del determinismo, (2) attacco per dizionario con procedura esplicita, (3) soluzione tramite padding OAEP.

3. Per D1a (dimensione chiave): essere precisi nella distinzione tra "dimensione della tabella" ($n \cdot 2^n$ bit) e "lunghezza minima della chiave" ($\log_2((2^n)!)$ bit). L'affermazione della traccia si riferisce alla prima.
