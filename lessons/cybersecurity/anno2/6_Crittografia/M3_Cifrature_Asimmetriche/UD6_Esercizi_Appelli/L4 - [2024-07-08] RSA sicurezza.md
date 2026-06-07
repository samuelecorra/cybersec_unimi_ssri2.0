# RSA sicurezza — Esercizio d'esame

> 🗂️ **Fonte:** appello del **8 luglio 2024**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 8 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L5%20-%20Appello%208%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
4. Scegliere l'esponente pubblico $e$ con $1 < e < \varphi(n)$ e $\gcd(e, \varphi(n)) = 1$. Valori comuni: $e = 65537 = 2^{16}+1$ (primo, piccolo, molte operazioni sono XOR shift).
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

Un caso interessante è quando due utenti hanno lo stesso modulo $n$ ma esponenti diversi $e_1$ e $e_2$ (con $\gcd(e_1, e_2) = 1$). Se lo stesso messaggio $M$ viene cifrato con entrambe le chiavi pubbliche producendo $C_1 = M^{e_1} \bmod n$ e $C_2 = M^{e_2} \bmod n$, allora (usando l'identità di Bézout: $\exists a, b: ae_1 + be_2 = 1$):

$$M = C_1^a \cdot C_2^b \bmod n$$

Questo permette di recuperare $M$ senza conoscere nessuna chiave privata (uno degli esponenti $a$ o $b$ potrebbe essere negativo, richiedendo il calcolo dell'inverso modulare).

**4. Sicurezza nel caso generale (moduli diversi):**

Se $e_1$ e $e_2$ appartengono a utenti con moduli diversi $n_1$ e $n_2$, allora $C(M) = (M^{e_1} \bmod n_1)^{e_2} \bmod n_2$. In questo caso il double-RSA è equivalente a cifrare il ciphertext del primo RSA con il secondo RSA. Ciò non aumenta la sicurezza: chi conosce $d_2$ può recuperare $M^{e_1} \bmod n_1$ (il "messaggio" del secondo RSA), e poi potrebbe potenzialmente attaccare il primo RSA.

**5. Non aumenta la sicurezza:**

In generale, il double-RSA non aggiunge sicurezza rispetto a un singolo RSA ben configurato. I problemi del RSA naive (determinismo, spazio messaggi piccolo) rimangono. Il double-RSA aumenta il costo computazionale (due esponenziazioni invece di una) senza un corrispondente aumento di sicurezza.

> ⚠️ La corretta soluzione per aumentare la sicurezza di RSA non è usare RSA due volte, ma usare padding probabilistico (OAEP), scegliere parametri adeguati (chiavi lunghe), e per la firma digitale usare schemi come RSA-PSS.
