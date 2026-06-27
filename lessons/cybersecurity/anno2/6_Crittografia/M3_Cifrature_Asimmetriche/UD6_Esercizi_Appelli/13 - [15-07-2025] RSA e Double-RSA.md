# RSA e Double-RSA — Esercizio d'esame

> 🗂️ **Fonte:** appello del **15 luglio 2025**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 15 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L4%20-%20Appello%2015%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
4. Si sceglie l'**esponente pubblico** $e$ tale che $1 < e < \phi(n)$ e $\mcd(e, \phi(n)) = 1$ (tipicamente $e = 65537 = 2^{16}+1$).
5. Si calcola l'**esponente privato** $d$ tale che $d \cdot e \equiv 1 \pmod{\phi(n)}$ (inverso moltiplicativo di $e$ modulo $\phi(n)$, calcolabile con l'algoritmo di Euclide esteso).

**Chiave pubblica:** $(n, e)$

**Chiave privata:** $(n, d)$ (o equivalentemente $(p, q, d)$)

**Cifratura:** $C = M^e \bmod n$

**Decifratura:** $M = C^d \bmod n$

**Correttezza:** per il teorema di Eulero, $M^{e \cdot d} = M^{1 + k\phi(n)} \equiv M \pmod{n}$ per $\mcd(M, n) = 1$.

#### Parte b — Utilizzo "naive" di RSA e insicurezza con pochi messaggi (5 punti)

**Uso naive:** corrisponde all'applicazione diretta del metodo di esponenziazione modulare presentato in L4_1: prendere $M$ e calcolare

$$C = M^e \bmod n$$

usando il ciclo elementare `a ← (a · M) mod n` ripetuto $e$ volte (o gli algoritmi equivalenti left-to-right / right-to-left), senza nessun'altra operazione sul messaggio prima della cifratura. Non si aggiunge padding, non si randomizza: $M$ entra così com'è nella funzione RSA.

> 📌 Come evidenziato in L4_1, il metodo naive con esponente $e$ da 512+ bit è computazionalmente insostenibile ($\approx 2^{512}$ moltiplicazioni). Nella pratica si usano left-to-right o right-to-left, ma il risultato matematico è identico: $C = M^e \bmod n$ senza modifiche a $M$. Il termine "naive" si riferisce dunque all'**assenza di precauzioni** sul messaggio, non all'algoritmo scelto per il calcolo.

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

Se Alice e Bob usano lo stesso modulo $n$ ma esponenti diversi $e_1, e_2$ con $\mcd(e_1, e_2) = 1$, e lo stesso messaggio $M$ viene cifrato con entrambe le chiavi:

$$C_1 = M^{e_1} \bmod n, \quad C_2 = M^{e_2} \bmod n$$

Allora un avversario calcola $s_1, s_2$ tali che $s_1 e_1 + s_2 e_2 = 1$ (Bézout), e:

$$M = C_1^{s_1} \cdot C_2^{s_2} \bmod n$$

**Conclusione sulla sicurezza di Double-RSA:**

> ⚠️ Double-RSA con lo stesso modulo non aumenta la sicurezza rispetto a RSA singolo: l'esponente composto $e_1 \cdot e_2$ è più grande, ma se i singoli esponenti sono piccoli, possono emergere vulnerabilità specifiche (small exponent attack). Se i moduli sono diversi, si applicano attacchi correlati. In entrambi i casi, Double-RSA non è raccomandato perché non offre sicurezza dimostrabile superiore a RSA con un singolo esponente ben scelto.
