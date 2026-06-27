# Appello del 5 luglio 2024 — Soluzione completa

> 📌 **Informazioni appello:** 05/07/2024, punteggio totale 100/100, 4 domande.
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — sicurezza condizionata/incondizionata, OTP
> - [`../../M1_Crittografia_Classica/UD2/L2 - Cifrario affine.md`](../../M1_Crittografia_Classica/UD2/L2%20-%20Cifrario%20affine.md) — composizione cifrari affini
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel con funzione XOR
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA: correttezza, fattorizzazione da φ(n), Euclide esteso
> - [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — secret sharing: schema (n,n) e (k,n)

---

## Domanda 1 — Cifratura classica: sicurezza e cifrario affine composto (20 punti)

### Traccia originale

> **(20 punti) Cifratura classica.**
>
> a. (5) Dare una definizione di sicurezza condizionata e incondizionata.
>
> b. (5) Descrivere un cifrario incondizionatamente sicuro.
>
> c. (10) Si consideri per un cifrario affine, l'applicazione in sequenza della cifratura con chiave $k_1 = (a_1, b_1)$ e $k_2 = (a_2, b_2)$, $E_{k_1}(E_{k_2}(\text{plaintext})) = \text{ciphertext}$. Tale doppia cifratura aumenta la robustezza del cifrario? (giustificare la risposta)

### Soluzione dettagliata

> 📌 Le parti a e b di questa domanda sono identiche all'Appello del 24 giugno 2024 (L3, Domanda 1). Si rimanda a quella soluzione completa. Qui viene riportata la soluzione per completezza.

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):**

Un sistema è **condizionatamente sicuro** se è computazionalmente infattibile per un avversario con risorse di calcolo polinomiali rompere il sistema. La sicurezza si basa sulla difficoltà di problemi matematici (fattorizzazione, logaritmo discreto). Un avversario con risorse illimitate potrebbe teoricamente rompere il sistema.

**Sicurezza incondizionata (perfetta, information-theoretic):**

Un sistema è **incondizionatamente sicuro** se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro dal testo cifrato. Formalmente (Shannon, 1949):

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

Il teorema di Shannon afferma che la perfect secrecy richiede $|\mathcal{K}| \geq |\mathcal{M}|$.

#### Parte b — Cifrario incondizionatamente sicuro (5 punti)

Il **One-Time Pad (OTP)** è l'esempio canonico di cifrario incondizionatamente sicuro:
- Messaggio $m \in \{0,1\}^n$; chiave $k \in \{0,1\}^n$ uniforme, usata una volta sola.
- Cifratura: $c = m \oplus k$; decifratura: $m = c \oplus k$.
- Perfect secrecy: per ogni $c$, $m_1$, $m_2$ si ha $P(C=c|M=m_1) = P(C=c|M=m_2) = 2^{-n}$.
- Limite pratico: chiave lunga quanto il messaggio, non riusabile.

#### Parte c — Doppia cifratura affine (10 punti)

La doppia cifratura affine $E_{k_1}(E_{k_2}(x))$ si calcola come:

$$E_{k_2}(x) = a_2 x + b_2 \bmod 26$$
$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 = (a_1 a_2)x + (a_1 b_2 + b_1) \bmod 26$$

Il risultato è ancora un cifrario affine con chiave equivalente $(a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)$.

**La doppia cifratura NON aumenta la robustezza** perché:
1. La famiglia dei cifrari affini è **chiusa** rispetto alla composizione: la composizione di due cifrari affini è ancora un cifrario affine.
2. Qualsiasi attacco al cifrario affine singolo si applica al doppio cifrario, poiché sono crittograficamente equivalenti.
3. Lo spazio delle chiavi effettivo non cresce: rimane al più il numero di chiavi affini valide (208 per l'alfabeto da 26 lettere).

---

## Domanda 2 — Strutture di Feistel a 2 round con funzione XOR (30 punti)

### Traccia originale

> **(30 punti) Strutture di Feistel.**
> Si consideri un cifrario a blocchi su 8 bit che si basa sul funzionamento di DES con soli due round e utilizza una struttura di Feistel, dove la funzione $f$ per il round $i$-esimo è definita come:
> $$f_i(k, x) = (2 \cdot i \cdot k \bmod 16) \oplus x \quad \text{per } i = 1, 2$$
> dove $k$ è la chiave che appartiene a $\mathbb{Z}_{16}$ e $x$ indica il blocco binario.
>
> a. (20) Se $K = 7$ (0111) e il plaintext è 0010 1000, mostrare come si ottiene il corrispondente ciphertext illustrando le computazioni intermedie.
>
> b. (10) Fare considerazioni sulla sicurezza del cifrario proposto.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura di Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — analogia con DES

### Soluzione dettagliata

> ⚠️ **Differenza cruciale rispetto all'Appello 24/06/2024:** la funzione di round è diversa. Nell'appello del 24/06 era $f_i(k,x) = (2ik) \cdot x \bmod 16$ (moltiplicazione). In questo appello (05/07) è $f_i(k,x) = (2ik \bmod 16) \oplus x$ (il fattore dipendente da $k$ viene calcolato modulo 16, poi il risultato fa XOR con $x$). Le computazioni sono differenti.

#### Parte a — Cifratura passo-passo (20 punti)

**Parametri:**
- Blocco 8 bit: $L_0 = $ 0010, $R_0 = $ 1000
- Chiave: $K = 7$ (decimale), cioè $0111_2$
- $L_0 = 0010_2 = 2_{10}$, $R_0 = 1000_2 = 8_{10}$

**Round 1 ($i = 1$):**

Calcolo del fattore chiave per il round:
$$2 \cdot 1 \cdot K \bmod 16 = 2 \cdot 7 \bmod 16 = 14 \bmod 16 = 14$$

La funzione di round è (XOR tra il fattore chiave e $R_0$):
$$f_1(K, R_0) = 14 \oplus R_0 = 14 \oplus 8$$

Convertiamo in binario:
$$14 = 1110_2, \quad 8 = 1000_2$$
$$1110 \oplus 1000 = 0110 = 6_{10}$$

Quindi $f_1(K, R_0) = 6$.

Aggiornamento Feistel:
$$L_1 = R_0 = 8$$
$$R_1 = L_0 \oplus f_1(K, R_0) = 2 \oplus 6$$

$$2 = 0010_2, \quad 6 = 0110_2$$
$$0010 \oplus 0110 = 0100 = 4_{10}$$

Stato dopo il Round 1: $L_1 = 8$ (1000), $R_1 = 4$ (0100)

**Round 2 ($i = 2$):**

Calcolo del fattore chiave per il round:
$$2 \cdot 2 \cdot K \bmod 16 = 4 \cdot 7 \bmod 16 = 28 \bmod 16 = 12$$

La funzione di round è (XOR tra il fattore chiave e $R_1$):
$$f_2(K, R_1) = 12 \oplus R_1 = 12 \oplus 4$$

$$12 = 1100_2, \quad 4 = 0100_2$$
$$1100 \oplus 0100 = 1000 = 8_{10}$$

Quindi $f_2(K, R_1) = 8$.

Aggiornamento Feistel:
$$L_2 = R_1 = 4$$
$$R_2 = L_1 \oplus f_2(K, R_1) = 8 \oplus 8$$

$$8 = 1000_2, \quad 8 = 1000_2$$
$$1000 \oplus 1000 = 0000 = 0_{10}$$

Stato dopo il Round 2: $L_2 = 4$ (0100), $R_2 = 0$ (0000)

**Ciphertext:**

$$C = L_2 \| R_2 = 0100\ 0000$$

> 📌 **Riepilogo delle computazioni:**
>
> | Fase | L (bin) | L (dec) | R (bin) | R (dec) | fattore chiave | f(K,R) |
> |------|---------|---------|---------|---------|---------------|--------|
> | Plaintext | 0010 | 2 | 1000 | 8 | — | — |
> | Round 1 | 1000 | 8 | 0100 | 4 | $2\cdot1\cdot7 \bmod 16 = 14$ | $14 \oplus 8 = 6$ |
> | Round 2 | 0100 | 4 | 0000 | 0 | $2\cdot2\cdot7 \bmod 16 = 12$ | $12 \oplus 4 = 8$ |

**Ciphertext finale:** $C = $ **0100 0000**

#### Parte b — Considerazioni sulla sicurezza (10 punti)

Il cifrario proposto presenta le stesse debolezze fondamentali dell'analoga versione con moltiplicazione (Appello 24/06):

**1. Funzione di round non sicura:**

La funzione $f_i(k, x) = (2ik \bmod 16) \oplus x$ è **lineare** sia in $k$ che in $x$: lo XOR è un'operazione lineare e il fattore $(2ik \bmod 16)$ è lineare in $k$. Quindi la relazione tra plaintext e ciphertext è descrivibile da equazioni lineari, rendendo il cifrario vulnerabile alla **crittoanalisi lineare**.

**2. Chiave a 4 bit:**

$K \in \mathbb{Z}_{16}$ ha soli 16 valori possibili. La forza bruta richiede al massimo 16 tentativi, che è un numero irrisorio.

**3. Soli 2 round:**

Il numero di round è insufficiente per garantire la diffusione richiesta dal criterio dell'avalanche effect (ogni bit del ciphertext deve dipendere da ogni bit del plaintext e della chiave). Con 2 round su 8 bit totali, la diffusione è incompleta.

**4. Problema con certi valori di $K$:**

Se $K = 0$: il fattore chiave è sempre 0, quindi $f_i(0, x) = 0 \oplus x = x$, che riduce il round a $R_{i} = L_{i-1} \oplus R_{i-1}$ (non aggiunge confusione sulla chiave).
Se $K = 8$: $2 \cdot 2 \cdot 8 \bmod 16 = 32 \bmod 16 = 0$, quindi al round 2 la funzione diventa $f_2 = x$ (keyless).

**5. Dipendenza lineare tra round:**

I fattori chiave dei due round sono $14$ e $12$ (per $K=7$): sono entrambi determinati deterministicamente da $K$. Non c'è schedule di chiave indipendente per i due round.

> 💡 I cifrari a blocchi moderni risolvono queste debolezze con: (a) funzioni di round non lineari (S-box), (b) schedule di chiave complessa, (c) molti round (10-16+), (d) chiavi lunghe (128-256 bit).

---

## Domanda 3 — Cifratura asimmetrica RSA (30 punti)

### Traccia originale

> **(30 punti) Cifratura asimmetrica.**
>
> a. (10) Si dimostri la correttezza di RSA.
>
> b. (10) Si consideri il crittosistema RSA con $n = 1363$. Si è scoperto che $\varphi(n) = 1288$. Usare questa informazione per fattorizzare $n$.
>
> c. (10) Considerando come modulo $N = 55$ e chiave pubblica $e = 7$.
> i. Calcolare la chiave privata $d$ corrispondente, mostrando i calcoli.
> ii. Decifrare il messaggio $C = 3$.

### Soluzione dettagliata

> 📌 Le parti a, b e c di questa domanda sono **identiche** all'Appello del 24 giugno 2024 (L3, Domanda 3). Si rimanda a quella soluzione dettagliata. Di seguito sono riportati i risultati finali con riferimento ai calcoli.

**Parte a:** La correttezza RSA si dimostra usando il teorema di Eulero ($M^{ed} \equiv M \pmod{n}$ quando $\mcd(M,n)=1$) e il teorema cinese dei resti per il caso $\mcd(M,n)>1$. Vedi L3 Domanda 3 per la dimostrazione completa.

**Parte b:** Fattorizzazione di $n=1363$ con $\varphi(n)=1288$:

$$p + q = n - \varphi(n) + 1 = 1363 - 1288 + 1 = 76$$
$$pq = 1363$$

Sistema → equazione quadratica $p^2 - 76p + 1363 = 0$ → discriminante $\sqrt{76^2 - 4\cdot 1363} = \sqrt{324} = 18$.

$$p = \frac{76 + 18}{2} = 47, \quad q = \frac{76 - 18}{2} = 29$$

**Fattorizzazione:** $\boxed{n = 47 \times 29}$.

**Parte c:** Con $N=55=5\times11$, $e=7$, $\varphi(55)=40$.

Euclide esteso su $\mcd(7,40)$:
$$40 = 5\cdot7+5;\quad 7=1\cdot5+2;\quad 5=2\cdot2+1$$
Back-sub: $1 = 3\cdot40 - 17\cdot7 \Rightarrow d = -17 \bmod 40 = 23$.

**Chiave privata:** $d = 23$.

Decifratura: $M = 3^{23} \bmod 55$. Calcolo via square-and-multiply:
- $3^2=9$, $3^4=26$, $3^8=16$, $3^{16}=36$
- $3^{23} = 36 \cdot 26 \cdot 9 \cdot 3 \bmod 55 = 936 \cdot 27 \bmod 55 = 1 \cdot 27 = 27$

**Messaggio decifrato:** $M = 27$.

---

## Domanda 4 — Secret Sharing (20 punti)

### Traccia originale

> **(20 punti) Secret Sharing.**
>
> a. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(n, n)$.
>
> b. (10 punti) Descrivere le fasi di distribuzione e ricostruzione del segreto per uno schema $(k, n)$.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — schema di Shamir: (t,n) secret sharing
- [`../../M6_Applicazioni_Crittografiche/UD3/L1 - Introduzione al Secret Sharing.md`](../../M6_Applicazioni_Crittografiche/UD3/L1%20-%20Introduzione%20al%20Secret%20Sharing.md) — concetto di secret sharing

### Soluzione dettagliata

#### Parte a — Schema $(n, n)$ di secret sharing (10 punti)

Uno **schema $(n, n)$** (o schema a soglia $n$ su $n$) è un sistema di secret sharing in cui il segreto $S$ viene distribuito tra $n$ partecipanti, e sono necessari **tutti gli $n$** partecipanti per ricostruirlo. Qualsiasi sottoinsieme di $n-1$ o meno partecipanti non ottiene alcuna informazione su $S$.

**Schema basato sull'XOR (o su $\mathbb{Z}_p$):**

**Fase di distribuzione:**
1. Il dealer sceglie un segreto $S \in \mathbb{Z}_p$ (dove $p$ è un numero primo abbastanza grande da contenere $S$).
2. Il dealer genera $n-1$ valori casuali $s_1, s_2, \ldots, s_{n-1}$ uniformemente distribuiti in $\mathbb{Z}_p$.
3. Il dealer calcola l'ultima share come:
$$s_n = S - s_1 - s_2 - \cdots - s_{n-1} \bmod p$$
4. La share $s_i$ viene consegnata privatamente al partecipante $i$, per $i = 1, \ldots, n$.

**Fase di ricostruzione:**

Tutti gli $n$ partecipanti si riuniscono e sommano le loro share:

$$s_1 + s_2 + \cdots + s_n \equiv S \pmod{p}$$

**Sicurezza:** qualsiasi insieme di $n-1$ partecipanti non ha informazioni su $S$: avendo $n-1$ share, ci sono $p$ possibili valori per la share mancante, e quindi $p$ possibili segreti ugualmente probabili.

> 📌 Questo schema (XOR generalizzato o somma modulare) è **perfetto** (information-theoretically secure) ma funziona solo con soglia $n$: tutti i partecipanti devono essere presenti. Se anche solo uno non è disponibile, il segreto non è recuperabile.

**Variante con XOR:**

Se $S \in \{0,1\}^n$, si usa XOR invece della somma modulare:
- $s_1, \ldots, s_{n-1}$ casuali; $s_n = S \oplus s_1 \oplus \cdots \oplus s_{n-1}$
- Ricostruzione: $s_1 \oplus s_2 \oplus \cdots \oplus s_n = S$

#### Parte b — Schema $(k, n)$ di Shamir (10 punti)

Uno **schema $(k, n)$** (o schema a soglia $k$ su $n$, con $k \leq n$) distribuisce il segreto $S$ tra $n$ partecipanti, e qualsiasi **sottoinsieme di almeno $k$** partecipanti può ricostruire $S$. Qualsiasi sottoinsieme di $k-1$ o meno partecipanti non ottiene alcuna informazione su $S$.

**Schema di Shamir (1979):**

Si basa sul fatto che un polinomio di grado $k-1$ è univocamente determinato da $k$ punti.

**Fase di distribuzione:**

1. Scegliere un primo $p > \max(S, n)$.
2. Il dealer sceglie un polinomio di grado $k-1$ a coefficienti casuali in $\mathbb{Z}_p$:
$$f(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{k-1} x^{k-1} \bmod p$$
dove $a_0 = S$ (il segreto è il termine noto del polinomio).
3. I coefficienti $a_1, \ldots, a_{k-1}$ sono scelti uniformemente a caso in $\mathbb{Z}_p$.
4. La share del partecipante $i$ è il punto $(i, f(i) \bmod p)$, consegnato privatamente.

**Fase di ricostruzione:**

Con $k$ share $(x_1, y_1), (x_2, y_2), \ldots, (x_k, y_k)$, si usa **l'interpolazione di Lagrange** per recuperare $f(0) = S$:

$$S = f(0) = \sum_{i=1}^{k} y_i \cdot L_i(0) \bmod p$$

dove i **polinomi di Lagrange base** sono:

$$L_i(0) = \prod_{\substack{j=1 \\ j \neq i}}^{k} \frac{0 - x_j}{x_i - x_j} = \prod_{\substack{j=1 \\ j \neq i}}^{k} \frac{-x_j}{x_i - x_j} \bmod p$$

Tutte le divisioni si intendono come moltiplicazioni per l'inverso modulare in $\mathbb{Z}_p$.

**Sicurezza dello schema di Shamir:**

Lo schema è **perfettamente sicuro** (information-theoretically secure): qualsiasi insieme di $k-1$ partecipanti non ha informazioni su $S$. Formalmente, per ogni valore di segreto $S'$, esiste un unico polinomio di grado $k-1$ che passa per i $k-1$ punti noti e ha $f(0) = S'$.

> 📌 **Proprietà fondamentale:** un polinomio di grado $k-1$ è univocamente determinato da $k$ punti (teorema di interpolazione di Lagrange). Con $k-1$ punti esistono infiniti polinomi di grado $k-1$ compatibili, uno per ogni possibile segreto.

**Confronto tra $(n,n)$ e $(k,n)$:**

| Proprietà | Schema $(n,n)$ | Schema $(k,n)$ di Shamir |
|-----------|---------------|--------------------------|
| Partecipanti necessari | Tutti $n$ | Qualsiasi $k \geq k$ |
| Struttura matematica | Somma/XOR modulare | Polinomio di grado $k-1$ |
| Flessibilità | Nessuna (tutti devono partecipare) | Alta (qualsiasi sottoinsieme di $k$) |
| Sicurezza | Perfetta | Perfetta |
| Overhead | Minimo | Interpolazione di Lagrange |

> ⚠️ Nello schema di Shamir, i calcoli della ricostruzione coinvolgono inversioni modulari (per le divisioni nei termini di Lagrange). È fondamentale eseguire tutti i calcoli in $\mathbb{Z}_p$ con $p$ primo.

---

## Pattern e osservazioni per l'esame

L'appello del 5 luglio 2024 è praticamente gemello di quello del 24 giugno 2024. Differenze principali:

- **D2 (30 punti):** la funzione di round cambia da moltiplicazione a XOR con la chiave. La struttura Feistel rimane identica, ma i calcoli sono diversi. Fare attenzione alla distinzione: in questo appello il fattore $2ik \bmod 16$ fa XOR con $x$, non è moltiplicato per $x$.

- **D4 (20 punti):** in questo appello la D4 è teorica (descrizione schema $(n,n)$ e $(k,n)$), mentre nel 24/06 era D4 firma DSS. Il secret sharing compare nella sessione estiva: preparare sia la teoria che i calcoli di interpolazione di Lagrange.

- **D1 e D3:** identiche al 24/06/2024.

**Consigli specifici per questo appello:**

1. Per D2: la distinzione tra $f(k,x) = (2ik)x \bmod 16$ e $f(k,x) = (2ik \bmod 16) \oplus x$ è fondamentale. Leggere la traccia con attenzione.

2. Per D4: schema $(n,n)$ è semplice (somma modulare). Schema $(k,n)$ richiede di conoscere l'interpolazione di Lagrange con inversioni modulari. Esercitarsi con esempi numerici (vedi L5 e L6 di questa UD).

3. La sessione estiva (giugno-luglio) tende a riproporre gli stessi argomenti con piccole variazioni. Confrontare sempre gli appelli adiacenti per rilevare le differenze.
