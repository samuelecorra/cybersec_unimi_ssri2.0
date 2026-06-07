# Appello del 19 settembre 2025 — Soluzione completa

> 📌 **Informazioni appello:** 19/09/2025, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
>
> - [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — classificazione degli attacchi
> - [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — sicurezza del cifrario di Hill
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L3 - Attacchi al DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L3%20-%20Attacchi%20al%20DES.md) — sicurezza DES
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — meet-in-the-middle per Double DES
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X e known plaintext
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](<../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md>) — schema DSS
> - [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — riutilizzo del nonce e sicurezza DSS
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir

---

## Domanda 1 — Cifrari classici: classificazione attacchi e resistenza (25 punti)

### Traccia originale

> **(25 punti) Cifrari classici.**
>
> a. (5 punti) Dare una classificazione dei possibili attacchi in base alla conoscenza dell'avversario.
>
> b. (20 punti) Secondo la classificazione precedente dire a quali tipi di attacchi resistono i seguenti cifrari, motivando la risposta con un esempio:
>
> i. Cifrario a sostituzione
>
> ii. Cifrario Playfair
>
> iii. Cifrario Hill
>
> iv. DES
>
> v. RSA

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — modelli di attacco
- [`../../M1_Crittografia_Classica/UD2/L5 - Crittoanalisi dei cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L5%20-%20Crittoanalisi%20dei%20cifrari%20a%20sostituzione.md) — attacchi ai cifrari classici

### Soluzione dettagliata

#### Parte a — Classificazione degli attacchi (5 punti)

La classificazione standard degli attacchi crittografici si basa sul tipo di accesso che l'avversario ha ai dati:

**1. Ciphertext-Only Attack (COA):**

L'avversario conosce solo uno o più testi cifrati $C_1, C_2, \ldots$ e nessun testo in chiaro corrispondente. Deve determinare il testo in chiaro o la chiave.

_Esempio:_ analisi delle frequenze su testo cifrato monoalfabetico.

**2. Known Plaintext Attack (KPA):**

L'avversario conosce alcune coppie $(P_i, C_i)$ dove $C_i = E_k(P_i)$, tutte cifrate con la stessa chiave. Usa queste coppie per determinare $k$ o per decifrare altri testi.

_Esempio:_ durante la Seconda Guerra Mondiale, i crittografi alleati conoscevano parti del formato standard dei messaggi Enigma (saluti, "HH" come identificatore) e le usavano come "crib".

**3. Chosen Plaintext Attack (CPA):**

L'avversario può scegliere messaggi in chiaro arbitrari e ottenere i corrispondenti testi cifrati (accesso a un oracolo di cifratura). È più potente del KPA perché può testare ipotesi specifiche.

_Esempio:_ attacchi adaptativi su blocchi di ECB, acronimo che significa Electronic Codebook, un modo di operare dei cifrari a blocchi che è vulnerabile a CPA.

**4. Chosen Ciphertext Attack (CCA):**

L'avversario può scegliere testi cifrati arbitrari e ottenere i corrispondenti testi in chiaro decifrati (accesso a un oracolo di decifratura). È il modello di attacco più forte. Attenzione: si presuppone che non possa però chiedere la decrittazione del cyphertext challenge, ovvero proprio di quello che desidera attaccare; l'attacco si appoggia sulla decrittazione di testi *simili*, con tecniche come il padding, che gli forniscono indizi sulla chiave.

_Esempio:_ padding oracle attack su TLS (POODLE, BEAST).

> 📌 La gerarchia di potenza è: CCA $>$ CPA $>$ KPA $>$ COA. Un cifrario sicuro contro CCA è sicuro contro tutti gli attacchi più deboli. La sicurezza semantica moderna richiede resistenza almeno a CPA.

#### Parte b — Resistenza agli attacchi dei vari cifrari (20 punti)

**i. Cifrario a sostituzione monoalfabetica:**

COA = CypherText Only Attack, acronimo equivalente al Known CipherText Attack (KCA) usato in alcuni testi. Qui si assume che l'avversario abbia solo accesso a testi cifrati.

| Attacco | Resiste? | Motivazione                                                                                                                                                                                   |
| ------- | -------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| COA     | ✗ NO     | L'analisi delle frequenze permette di identificare le lettere più comuni (in italiano: 'a', 'e', 'i', 'o') e ricostruire la chiave. Con testo sufficiente (~50 lettere), il cifrario è rotto. |
| KPA     | ✗ NO     | Ogni coppia nota rivela direttamente un elemento della tabella di sostituzione. Con 26 coppie note, la chiave è completamente determinata.                                                    |
| CPA     | ✗ NO     | Il cifrario è deterministico: cifrare le 26 lettere rivela immediatamente l'intera tabella di sostituzione.                                                                                   |

**ii. Cifrario Playfair:**

Il cifrario Playfair cifra bigrammi (coppie di lettere) usando una matrice 5×5 costruita dalla chiave.

| Attacco | Resiste?   | Motivazione                                                                                                                                                                                                                                                                                                                                                                              |
| ------- | ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| COA     | Parziale ✗ | Resiste all'analisi delle frequenze monogramma (le frequenze dei bigrammi cifrati non corrispondono direttamente a quelle dei bigrammi in chiaro), ma è vulnerabile all'analisi delle frequenze dei **bigrammi** cifrati. In italiano ci sono bigrammi molto comuni (es. "al", "di", "la") che lasciano tracce statistiche. Con testo sufficiente (alcune centinaia di lettere) è rotto. |
| KPA     | ✗ NO       | Ogni bigramma noto (coppia chiaro→cifrato) vincola le posizioni relative di quattro lettere nella griglia (stessa riga, stessa colonna o rettangolo). Per ricostruire la matrice basta un numero limitato di bigrammi ben distribuiti — dell'ordine di alcune decine, molto meno dei 600 possibili — e comunque solo a meno delle simmetrie cicliche: il cifrario cade con poco known-plaintext. **Vedi la nota sul conteggio dei bigrammi sotto la tabella.**                                                                                                                                                                                                                                                 |
| CPA     | ✗ NO       | Cifrare tutti i bigrammi rivela l'intera struttura.                                                                                                                                                                                                                                                                                                                                      |

> 📌 **Conteggio dei bigrammi (precisazione).** I bigrammi di testo in chiaro sono coppie di lettere **distinte** — i doppioni vengono separati inserendo una X (es. _palla_ → _pa-lx-la_) — quindi sono $25 \times 24 = 600$ ordinati, ovvero $\binom{25}{2} = 300$ non ordinati: **non** $25 \times 25 / 2 = 312{,}5$ (formula errata, che conterebbe anche le "coppie" con lettera ripetuta — impossibili in Playfair — applicando un improprio fattore $1/2$). Per *tabulare* l'intera funzione di cifratura servirebbero al più tutti i $600$ (o $300$) bigrammi, ma per *ricostruire la griglia* ne basta un numero molto minore: ogni bigramma fissa relazioni di riga/colonna fra quattro lettere, e poche decine ben scelte connettono tutte le 25 lettere. La matrice è inoltre recuperabile **solo a meno degli shift ciclici** di righe e colonne: traslando ciclicamente tutte le righe e/o tutte le colonne si ottengono $5 \times 5 = 25$ griglie distinte che cifrano in modo identico. Lo spazio delle chiavi *effettivo* è quindi $25!/25 = 24! \approx 6{,}2 \times 10^{23}$, non i $25! \approx 1{,}5 \times 10^{25}$ ingenui.

**iii. Cifrario Hill:**

| Attacco | Resiste?   | Motivazione                                                                                                                                                                                                                                 |
| ------- | ---------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| COA     | Parziale ✗ | Resiste all'analisi delle frequenze sulle singole lettere (è polialfabetico), ma l'analisi statistica dei blocchi di $m$ lettere può rivelare strutture. Con testo sufficiente, la distribuzione dei blocchi cifrati può essere analizzata. |
| KPA     | ✗ NO       | Con $m$ coppie (blocco plaintext, blocco ciphertext) linearmente indipendenti, si risolve $K = C \cdot P^{-1} \pmod{26}$ e si trova la chiave. Per $m=2$, bastano 2 coppie.                                                                 |
| CPA     | ✗ NO       | L'avversario sceglie i $m$ vettori della base standard come plaintext ($e_1, e_2, \ldots, e_m$), ottenendo direttamente le colonne di $K$. La chiave è trovata con $m$ query.                                                               |

**iv. DES:**

| Attacco | Resiste?            | Motivazione                                                                                                                                                                                                                               |
| ------- | ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| COA     | ✓ SÌ (con riserve)  | Con 56 bit di chiave, il brute force richiede $2^{55}$ operazioni in media. DES-56 fu rotto nel 1998 in 22 ore con hardware dedicato (Deep Crack). Attacchi teorici come la crittoanalisi differenziale richiedono $2^{47}$ testi scelti. |
| KPA     | ✓ SÌ (con riserve)  | La crittoanalisi lineare richiede $2^{43}$ coppie (plaintext, ciphertext) note. È teoricamente rompibile con KPA massivo, ma impraticabile nel 1997. Oggi con hardware moderno è fattibile.                                               |
| CPA     | ✓ SÌ (teoricamente) | La crittoanalisi differenziale (Biham & Shamir, 1990) richiede $2^{47}$ testi scelti, troppi per essere pratici. DES resiste a CPA pratico.                                                                                               |
| CCA     | ✓ SÌ                | Nessun attacco CCA pratico noto per DES.                                                                                                                                                                                                  |

_Nota:_ con hardware moderno il brute force su DES-56 è fattibile. DES è obsoleto per la sicurezza moderna.

**v. RSA:**

| Attacco | Resiste?           | Motivazione                                                                                                                                                                                                                             |
| ------- | ------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| COA     | ✓ SÌ               | La sicurezza si basa sulla difficoltà della fattorizzazione di $n = pq$. Per $n$ di 2048 bit, non è noto alcun algoritmo polinomiale di fattorizzazione classico.                                                                       |
| KPA     | ✓ SÌ (con padding) | Con textbook RSA deterministico, un KPA permette un attacco a dizionario se lo spazio dei messaggi è piccolo (si veda L4, D3b). Con OAEP, RSA resiste anche a KPA e CPA.                                                                |
| CPA     | ✓ SÌ (con OAEP)    | Textbook RSA è deterministico ed è vulnerabile a CPA (l'avversario può cifrare qualsiasi plaintext e confrontare). RSA-OAEP è semanticamente sicuro contro CPA.                                                                         |
| CCA     | ✓ SÌ (con OAEP)    | RSA-OAEP è sicuro contro CCA2 (chosen ciphertext attack adattivo) sotto l'assunzione RSA nel random oracle model. Textbook RSA è vulnerabile a CCA (es. multiplicative homomorphism: $E(m_1 \cdot m_2) = E(m_1) \cdot E(m_2) \bmod n$). |

> ⚠️ Il classico esempio di vulnerabilità CCA di RSA: dato $C = M^e \bmod n$, l'avversario sceglie un $r$ casuale, calcola $C' = r^e \cdot C \bmod n = (rM)^e \bmod n$, ottiene la decifratura $M' = C'^d = rM$ dall'oracolo, e recupera $M = M' \cdot r^{-1} \bmod n$. OAEP elimina questa vulnerabilità.

---

## Domanda 2 — Cifratura simmetrica: DES doppio e AES-X (40 punti)

### Traccia originale

> **(40 punti) Cifratura simmetrica.**
>
> a. (10) Si descriva l'attacco meet in the middle per DES doppio.
>
> b. (30) Si consideri AES-X una versione modificata di AES che utilizza anche lo XOR: Siano $k_1$ e $k_2$ due chiavi AES di 128 bit, la cifratura di un blocco $m$ avviene così:
> $$AESX_{k_1,k_2}(m) = AES_{k_1}(m) \oplus k_2$$
>
> i. (10) Si descrivano le caratteristiche di AES-X.
>
> ii. (20) Si consideri un attacco di tipo known plaintext. Si discuta la sicurezza di tale sistema sia su AES standard che su AES-X.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — MITM per Double DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X

### Soluzione dettagliata

#### Parte a — Meet-in-the-Middle per DES doppio (10 punti)

(Trattazione completa in L5, Domanda 2b. Si riporta per completezza.)

**Double DES:** $C = DES_{K_2}(DES_{K_1}(P))$.

Definiamo $X = DES_{K_1}(P) = DES_{K_2}^{-1}(C)$.

**Attacco MITM:**

1. **Forward:** per ogni $K \in \{0,1\}^{56}$, calcola $X_K = DES_K(P)$ e memorizza in tabella $T$.
2. **Backward:** per ogni $K' \in \{0,1\}^{56}$, calcola $Y_{K'} = DES_{K'}^{-1}(C)$ e cerca in $T$.
3. Se $Y_{K'} = X_K$, il candidato è $(K_1, K_2) = (K, K')$.

**Complessità:** Tempo $O(2^{57})$, Spazio $O(2^{56})$. Sicurezza effettiva: 57 bit (invece dei 112 attesi).

#### Parte b.i — Caratteristiche di AES-X (10 punti)

**Schema AES-X:**

$$AESX_{k_1,k_2}(m) = AES_{k_1}(m) \oplus k_2$$

AES-X è una variante di AES che applica un'operazione di **key whitening** in uscita: dopo la cifratura AES standard con chiave $k_1$, si esegue uno XOR con una seconda chiave $k_2$.

**Caratteristiche:**

1. **Chiave totale:** $k_1 \| k_2$ = 256 bit (due chiavi AES da 128 bit ciascuna). Lo spazio delle chiavi è $2^{256}$.

2. **Struttura:** AES-X è un esempio di **key whitening** (o outer whitening). Il whitening è l'idea di aggiungere della chiave prima o dopo il cifrario principale tramite operazioni semplici (XOR) per rendere più costosi gli attacchi.

3. **Invertibilità:** la decifratura è:

$$m = AES_{k_1}^{-1}(AESX_{k_1,k_2}(m) \oplus k_2) = AES_{k_1}^{-1}(AES_{k_1}(m))$$

ovvero:

$$AESX^{-1}_{k_1,k_2}(c) = AES_{k_1}^{-1}(c \oplus k_2)$$

4. **Efficienza:** il costo computazionale è quasi identico ad AES (una cifratura AES + uno XOR), trascurabile il costo aggiuntivo.

5. **Relazione con AES XEXX/XEX:** la struttura è correlata alle modalità XEX (XOR-Encrypt-XOR) usate in crittografia authenticated encryption.

> 💡 Il key whitening è usato anche nel DES: DESX applica XOR della chiave prima e dopo DES, aumentando la sicurezza contro brute force senza aumentare significativamente il costo computazionale.

#### Parte b.ii — Sicurezza di AES standard e AES-X contro known plaintext attack (20 punti)

**Sicurezza di AES standard contro KPA:**

Sia $C = AES_{k_1}(m)$ con $k_1$ di 128 bit.

**KPA su AES:** l'avversario conosce coppie $(m_i, C_i)$. Per trovare $k_1$, deve eseguire una ricerca esaustiva su $2^{128}$ possibili chiavi. Anche un attacco a dizionario richiede $2^{128}$ valutazioni.

**Sicurezza stimata:** $2^{128}$ operazioni per brute force. Nessun attacco KPA pratico noto contro AES con chiave di 128 bit. AES è considerato sicuro contro KPA.

**Sicurezza di AES-X contro KPA:**

Sia $C = AES_{k_1}(m) \oplus k_2$ con $k_1, k_2$ entrambi di 128 bit.

**Attacco KPA su AES-X:**

Dall'equazione di cifratura: $k_2 = AES_{k_1}(m) \oplus C$ (per qualsiasi coppia $(m, C)$ nota).

**Strategia dell'attacco:**

**Fase 1 — Guess di $k_1$:** per ogni possibile $k_1 \in \{0,1\}^{128}$:

- Calcola $AES_{k_1}(m_1)$ per il primo plaintext $m_1$ noto.
- Calcola il candidato: $k_2^* = AES_{k_1}(m_1) \oplus C_1$.

**Fase 2 — Verifica con una seconda coppia:** verifica se $AES_{k_1}(m_2) \oplus k_2^* = C_2$ per una seconda coppia $(m_2, C_2)$ nota.

**Analisi della complessità:**

- In Fase 1 si calcolano $2^{128}$ candidati $(k_1, k_2^*)$.
- In Fase 2 si verificano i candidati: ogni coppia elimina circa $2^{128} - 1$ falsi positivi.
- Con due coppie note, il numero di falsi positivi attesi è $2^{256} / 2^{128} = 2^{128}$.
- Con tre coppie: $2^{256} / 2^{192} = 2^{64}$ falsi positivi.
- Con quattro coppie: $2^{256}/2^{256} \approx 1$ → unica soluzione.

**Quindi l'attacco KPA su AES-X richiede $O(2^{128})$ operazioni, non $O(2^{256})$.**

> ⚠️ **Sorprendente:** AES-X con chiave da 256 bit (128+128) non offre sicurezza superiore a AES con chiave da 128 bit contro KPA! La seconda chiave $k_2$ può essere calcolata immediatamente da qualsiasi coppia nota, riducendo il problema a trovare $k_1$ su $2^{128}$ possibilità.

**Perché succede?**

L'operazione finale $\oplus k_2$ è lineare rispetto a $k_2$: dato $(m, C)$, la relazione $k_2 = AES_{k_1}(m) \oplus C$ determina $k_2$ univocamente una volta fissato $k_1$. Quindi la ricerca non è su $\{k_1\} \times \{k_2\}$ ($2^{256}$ possibilità) ma su $\{k_1\}$ ($2^{128}$ possibilità).

**Confronto riassuntivo:**

| Sistema                | KPA security (bit) | Spazio chiave (bit) |
| ---------------------- | ------------------ | ------------------- |
| AES-128                | 128                | 128                 |
| AES-256                | 256                | 256                 |
| AES-X (k1=128, k2=128) | **128**            | 256                 |

> 💡 Per aumentare effettivamente la sicurezza oltre AES-128, il key whitening dovrebbe essere applicato sia prima che dopo la cifratura (XEX: $k_2 \oplus AES_{k_1}(m \oplus k_3)$), oppure usare chiave AES-256 nativa. AES-X con whitening solo in uscita non aiuta contro KPA.

---

## Domanda 3 — Firma DSS (15 punti)

### Traccia originale

> **(15 punti) Firma DSS.**
>
> a. (10) Descrivere in dettaglio le fasi di generazione e verifica della firma DSS.
>
> b. (5) Cosa succede se viene firmato lo stesso messaggio in due diverse occasioni? Qual è la differenza con RSA?

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](<../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md>) — schema DSS completo
- [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — nonce riutilizzato
- [`../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1 - DSA - firma (a,0) e caso delta=0.md`](<../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1%20-%20DSA%20-%20firma%20(a%2C0)%20e%20caso%20delta%3D0.md>) — vulnerabilità DSS

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

Il **Digital Signature Standard (DSS)**, basato sull'algoritmo **DSA (Digital Signature Algorithm)**, è il standard NIST per le firme digitali (FIPS 186).

**Parametri del sistema (pubblici):**

- $p$: primo grande di lunghezza $L$ bit ($L = 1024, 2048, 3072$).
- $q$: primo di 160/256 bit tale che $q \mid (p-1)$.
- $g$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$: $g = h^{(p-1)/q} \bmod p$ per qualche $h > 1$.
- $H$: funzione hash crittografica (SHA-256 o superiore).

**Chiavi dell'utente:**

- **Chiave privata:** $x \in \{1, \ldots, q-1\}$ scelto casualmente.
- **Chiave pubblica:** $y = g^x \bmod p$.

**Fase di firma (signing):**

Dato un messaggio $M$, il firmatario procede come segue:

1. Sceglie un **nonce segreto** $k \in \{1, \ldots, q-1\}$ **casuale e unico** per ogni firma.
2. Calcola $r = (g^k \bmod p) \bmod q$. Se $r = 0$, ricomincia con un nuovo $k$.
3. Calcola $s = k^{-1}(H(M) + x \cdot r) \bmod q$. Se $s = 0$, ricomincia.
4. La firma è la coppia $(r, s)$.

**Fase di verifica:**

Dato il messaggio $M$ e la firma $(r, s)$, il verificatore con chiave pubblica $y$:

1. Verifica che $0 < r < q$ e $0 < s < q$. Se no, firma invalida.
2. Calcola $w = s^{-1} \bmod q$.
3. Calcola $u_1 = H(M) \cdot w \bmod q$.
4. Calcola $u_2 = r \cdot w \bmod q$.
5. Calcola $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$.
6. Accetta la firma se e solo se $v = r$.

**Correttezza della verifica:**

$$g^{u_1} \cdot y^{u_2} = g^{H(M)w} \cdot g^{xrw} = g^{w(H(M)+xr)} = g^{k^{-1}(H(M)+xr) \cdot (H(M)+xr)^{-1}(H(M)+xr)}$$

Semplificando: $g^{u_1} y^{u_2} = g^{(H(M)+xr)/s \cdot s / (H(M)+xr)} \cdot \ldots$

Più direttamente: dalla definizione di $s = k^{-1}(H(M)+xr) \bmod q$, si ha $k = s^{-1}(H(M)+xr) \bmod q = w(H(M)+xr) = w \cdot H(M) + w \cdot xr = u_1 + u_2 x$. Quindi:

$$v = (g^{u_1} g^{xu_2} \bmod p) \bmod q = (g^{u_1+xu_2} \bmod p) \bmod q = (g^k \bmod p) \bmod q = r \checkmark$$

> 📌 Il valore $k$ deve essere scelto diverso per ogni firma e tenuto segreto. Se $k$ è noto, la chiave privata $x$ si calcola immediatamente da $s$: $x = r^{-1}(sk - H(M)) \bmod q$.

#### Parte b — Firma dello stesso messaggio due volte; confronto con RSA (5 punti)

**Cosa succede se lo stesso messaggio viene firmato due volte in DSS:**

**Caso 1 — stesso nonce $k$ (errore critico):**

Se lo stesso $k$ viene usato per due firme (anche di messaggi diversi $M$ e $M'$), si generano $(r, s)$ e $(r', s')$ con $r = r'$ (poiché $r = (g^k \bmod p) \bmod q$ dipende solo da $k$).

Un avversario che osserva le due firme vede $r = r'$ e calcola:

$$s - s' = k^{-1}(H(M) - H(M')) \bmod q$$

$$k = (s - s')^{-1}(H(M) - H(M')) \bmod q$$

E poi:

$$x = r^{-1}(sk - H(M)) \bmod q$$

**La chiave privata $x$ è completamente compromessa!**

> ⚠️ Questo attacco reale ha violato la chiave privata di Sony nella PlayStation 3 (2010): Sony usava un valore di $k$ costante per tutte le firme, permettendo a fail0verflow di estrarre la chiave privata del firmware.

**Caso 2 — nonce $k$ diverso (corretto):**

Se $k$ è scelto casualmente e in modo diverso per ogni firma, le due firme dello stesso messaggio $M$ produrranno firme diverse (poiché $r$ dipende da $k$):

$(r_1, s_1) \neq (r_2, s_2)$ anche se $M_1 = M_2$.

Entrambe le firme sono valide ma diverse. Non si rivela alcuna informazione sulla chiave privata.

**Confronto con RSA:**

| Proprietà                             | DSS/DSA                                     | RSA (con padding)                                      |
| ------------------------------------- | ------------------------------------------- | ------------------------------------------------------ |
| Determinismo                          | NON deterministico (dipende da $k$ casuale) | Deterministico (textbook RSA) o randomizzato (RSA-PSS) |
| Firmare stesso messaggio due volte    | Produce firme diverse (se $k$ diverso)      | Produce stessa firma (textbook RSA deterministico)     |
| Vulnerabilità al riutilizzo del nonce | Critica: rivela $x$ se $k$ riusato          | N/A (no nonce in RSA)                                  |
| Sicurezza basata su                   | Logaritmo discreto                          | Fattorizzazione                                        |

**Differenza chiave:** RSA è deterministico (textbook RSA produce sempre la stessa firma per lo stesso messaggio), mentre DSS è probabilistico (firme diverse anche per lo stesso messaggio, grazie al nonce). La natura probabilistica di DSS è sia una caratteristica (mascheramento del messaggio) sia una vulnerabilità (dipende dalla qualità del generatore di numeri casuali).

> 💡 RSA-PSS (Probabilistic Signature Scheme) introduce randomizzazione anche in RSA per evitare la deterministica vulnerabile, avvicinando RSA alla struttura probabilistica di DSS.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti). Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k,n)$.
>
> b. (10 punti) Descrivere l'applicazione dello schema $(2,3)$ considerando $\mathbb{Z}_{11}$ e le share $P(1)=3$, $P(2)=5$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir completo

### Soluzione dettagliata

#### Parte a — Schema $(k,n)$: distribuzione e ricostruzione (10 punti)

(Trattazione completa già in L2, D4a e L3, D4b. Si veda lì per i dettagli. Sintesi:)

**Distribuzione:** dealer sceglie polinomio $f(x) = s + a_1 x + \ldots + a_{k-1} x^{k-1} \pmod p$ con $a_i$ casuali e $f(0) = s$. Partecipante $P_i$ riceve $s_i = f(i) \pmod p$.

**Ricostruzione:** $k$ partecipanti $(i_1, s_{i_1}), \ldots, (i_k, s_{i_k})$ usano interpolazione di Lagrange:

$$s = f(0) = \sum_{j=1}^{k} s_{i_j} \prod_{\substack{l=1\\l\neq j}}^{k} \frac{-i_l}{i_j - i_l} \pmod{p}$$

#### Parte b — Schema $(2,3)$ in $\mathbb{Z}_{11}$, share $P(1)=3$, $P(2)=5$ (10 punti)

**Dati:**

- Campo: $\mathbb{Z}_{11}$ ($p=11$)
- Share: $(1, 3)$ e $(2, 5)$

**Interpolazione di Lagrange:**

$$f(0) = 3 \cdot \lambda_1 + 5 \cdot \lambda_2 \pmod{11}$$

$$\lambda_1 = \frac{0 - 2}{1 - 2} = \frac{-2}{-1} = 2 \pmod{11}$$

$$\lambda_2 = \frac{0 - 1}{2 - 1} = \frac{-1}{1} = -1 \equiv 10 \pmod{11}$$

$$f(0) = 3 \cdot 2 + 5 \cdot 10 = 6 + 50 = 56 \pmod{11}$$

$56 = 5 \cdot 11 + 1$, quindi $56 \bmod 11 = 1$.

**Il segreto è $s = 1$.**

**Verifica:** Il polinomio è $f(x) = 1 + a_1 x$. Da $f(1) = 1 + a_1 = 3 \Rightarrow a_1 = 2$. Quindi $f(x) = 1 + 2x$.

- $f(1) = 1 + 2 = 3$ ✓
- $f(2) = 1 + 4 = 5$ ✓
- $f(0) = 1$ ✓

> 💡 **Confronto con appelli precedenti:** la coppia $(P(1)=3, P(2)=4)$ in $\mathbb{Z}_{11}$ degli appelli L2 e L5 dà segreto $s=2$. Qui $(P(1)=3, P(2)=5)$ in $\mathbb{Z}_{11}$ dà segreto $s=1$. Il polinomio $f(x) = 1 + 2x$ ha coefficiente angolare 2 (quindi le share crescono di 2 a ogni step), mentre $f(x)=2+x$ ha coefficiente angolare 1. La variazione di una singola share (da 4 a 5) cambia completamente il segreto recuperato.

---

## Pattern e osservazioni per l'esame

Questo è l'appello di fine sessione autunnale ed è il più difficile e vario del 2025:

**Struttura:**

- D1 (25 punti) è insolita: classificazione completa degli attacchi + analisi sistematica di 5 cifrari diversi. Richiede conoscenza trasversale di tutti i moduli M1-M3.
- D2 (40 punti, la più pesante del 2025!) combina MITM per Double DES + analisi completa di AES-X. La parte b.ii è la più tecnica: dimostrare che AES-X ha sicurezza KPA equivalente ad AES-128.
- D3 (15 punti) su DSS: domanda tecnica sulla generazione della firma + vulnerabilità critica del riutilizzo del nonce.
- D4 (20 punti) è il Secret Sharing più "facile" del 2025 per via del punteggio ridotto.

**Consigli pratici:**

- Per D1: preparare una tabella pre-costruita con i 5 cifrari × 4 attacchi. È l'unico modo per rispondere in modo organizzato e completo.
- Per AES-X: il punto cruciale è che $k_2 = C \oplus AES_{k_1}(m)$ è immediato una volta fissato $k_1$. Quindi cercare $(k_1, k_2)$ si riduce a cercare solo $k_1$ su $2^{128}$ possibilità.
- Per DSS: il messaggio chiave è: **nonce $k$ riusato = chiave privata compromessa**. Citare il caso Sony PS3 è un ottimo esempio pratico.
- Per Secret Sharing: la coppia $(P(1)=3, P(2)=5)$ in $\mathbb{Z}_{11}$ è diversa da $(P(1)=3, P(2)=4)$ degli altri appelli. Attenzione a leggere bene i dati.

**Pattern generale 2025:**
Questo appello chiude il ciclo e mostra la tendenza del professore a esaminare trasversalmente: la D1 richiede di collegare concetti di M1, M2, M3. Chi ha studiato i moduli in modo isolato senza collegare la "mappa dei cifrari" troverà difficile organizzare la risposta in modo soddisfacente.
