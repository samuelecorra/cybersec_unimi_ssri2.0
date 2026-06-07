# Cifrari classici attacchi e resistenza — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 gennaio 2023**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 gennaio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L1%20-%20Appello%2024%20gennaio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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

La classificazione standard degli attacchi crittografici si basa sul **tipo di accesso** che l'avversario ha ai dati, in ordine crescente di potenza:

**1. Ciphertext-Only Attack (COA):**

L'avversario conosce solo uno o più testi cifrati $C_1, C_2, \ldots$ e nessun testo in chiaro corrispondente. Deve determinare il testo in chiaro o la chiave.

_Esempio:_ analisi delle frequenze su testo cifrato monoalfabetico.

**2. Known Plaintext Attack (KPA):**

L'avversario conosce alcune coppie $(P_i, C_i)$ con $C_i = E_k(P_i)$, tutte cifrate con la stessa chiave, e le usa per determinare $k$ o decifrare altri testi.

_Esempio:_ i "crib" alleati su Enigma nella Seconda Guerra Mondiale (porzioni note del formato dei messaggi).

**3. Chosen Plaintext Attack (CPA):**

L'avversario può scegliere messaggi in chiaro arbitrari e ottenerne i cifrati (oracolo di cifratura). Più potente del KPA perché testa ipotesi mirate.

_Esempio:_ attacchi adattivi su blocchi in modalità ECB.

**4. Chosen Ciphertext Attack (CCA):**

L'avversario può scegliere testi cifrati arbitrari e ottenerne la decifratura (oracolo di decifratura). È il modello più forte.

_Esempio:_ padding oracle attack (POODLE, BEAST).

> 📌 Gerarchia di potenza: $\text{CCA} > \text{CPA} > \text{KPA} > \text{COA}$. Un cifrario sicuro contro CCA lo è anche contro tutti i modelli più deboli. La sicurezza semantica moderna richiede resistenza almeno a CPA.

#### Parte b — Resistenza agli attacchi dei vari cifrari (20 punti)

**i. Cifrario a sostituzione monoalfabetica:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✗ NO | L'analisi delle frequenze identifica le lettere più comuni e ricostruisce la tabella di sostituzione. Con ~50 lettere di testo il cifrario è rotto. |
| KPA | ✗ NO | Ogni coppia nota rivela un'entry della tabella; con 26 coppie la chiave è completa. |
| CPA | ✗ NO | È deterministico: cifrare le 26 lettere rivela l'intera tabella. |

**ii. Cifrario Playfair:**

Cifra **bigrammi** tramite una matrice $5\times5$ derivata dalla chiave.

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | Parziale ✗ | Resiste all'analisi delle frequenze sui singoli caratteri, ma è vulnerabile all'analisi delle frequenze dei **bigrammi** cifrati. Con qualche centinaio di lettere è rotto. |
| KPA | ✗ NO | Ogni bigramma noto vincola la matrice; abbastanza coppie la determinano completamente. |
| CPA | ✗ NO | Cifrare bigrammi scelti rivela la struttura della matrice. |

**iii. Cifrario Hill:**

Cifra blocchi di $m$ lettere come $\mathbf{c} = K\mathbf{p} \bmod 26$, con $K$ matrice $m\times m$ invertibile.

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | Parziale ✗ | Essendo poligrammico nasconde le frequenze delle singole lettere, ma l'analisi statistica dei blocchi può rivelare struttura con testo sufficiente. |
| KPA | ✗ NO | Con $m$ coppie (blocco chiaro, blocco cifrato) linearmente indipendenti si risolve $K = C\,P^{-1} \pmod{26}$. Per $m=2$ bastano 2 coppie. |
| CPA | ✗ NO | Scegliendo come plaintext i vettori della base standard $e_1,\dots,e_m$ si ottengono direttamente le colonne di $K$: chiave trovata in $m$ query. |

**iv. DES:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✓ SÌ (con riserve) | Chiave a 56 bit: brute force in $2^{55}$ in media. Rotto nel 1998 (Deep Crack, 22 ore). Nessun attacco statistico pratico in solo COA. |
| KPA | ✓ SÌ (con riserve) | La crittoanalisi lineare richiede $\approx 2^{43}$ coppie note: teoricamente rompibile, impraticabile all'epoca. |
| CPA | ✓ SÌ (teoricamente) | La crittoanalisi differenziale richiede $\approx 2^{47}$ testi scelti: troppi per essere pratici. |
| CCA | ✓ SÌ | Nessun attacco CCA pratico noto. |

_Nota:_ il limite reale di DES è la chiave a 56 bit (brute force oggi fattibile), non un difetto strutturale. È comunque obsoleto.

**v. RSA:**

| Attacco | Resiste? | Motivazione |
| ------- | -------- | ----------- |
| COA | ✓ SÌ | La sicurezza poggia sulla difficoltà di fattorizzare $n=pq$: per $n$ di 2048 bit non si conoscono algoritmi classici polinomiali. |
| KPA | ✓ SÌ (con padding) | Il textbook RSA deterministico è vulnerabile ad attacco a dizionario su spazi di messaggi piccoli; con OAEP RSA resiste. |
| CPA | ✓ SÌ (con OAEP) | Textbook RSA è deterministico → vulnerabile a CPA; RSA-OAEP è semanticamente sicuro. |
| CCA | ✓ SÌ (con OAEP) | Textbook RSA è malleabile (omomorfismo moltiplicativo): $E(m_1)E(m_2)=E(m_1 m_2)$. OAEP elimina la vulnerabilità. |

> ⚠️ Vulnerabilità CCA del textbook RSA: dato $C=M^e \bmod n$, l'avversario sceglie $r$, calcola $C'=r^e C \bmod n=(rM)^e$, ottiene dall'oracolo $M'=C'^d=rM$ e recupera $M = M' r^{-1} \bmod n$.
