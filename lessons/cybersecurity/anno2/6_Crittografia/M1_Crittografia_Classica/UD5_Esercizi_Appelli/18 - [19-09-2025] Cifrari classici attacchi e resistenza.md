# Cifrari classici attacchi e resistenza — Esercizio d'esame

> 🗂️ **Fonte:** appello del **19 settembre 2025**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 19 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L6%20-%20Appello%2019%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
