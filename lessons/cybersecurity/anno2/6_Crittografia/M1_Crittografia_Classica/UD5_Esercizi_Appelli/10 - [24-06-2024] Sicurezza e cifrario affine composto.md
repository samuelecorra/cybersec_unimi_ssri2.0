# Sicurezza e cifrario affine composto — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 giugno 2024**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 giugno 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L3%20-%20Appello%2024%20giugno%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — sicurezza perfetta di Shannon, OTP
- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — definizione, composizione, invertibilità

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):**

Un sistema crittografico è **condizionatamente sicuro** se un avversario computazionalmente limitato (con risorse di calcolo e/o tempo polinomiali) non può rompere il sistema in modo efficiente. La sicurezza dipende dalla difficoltà computazionale di problemi matematici (fattorizzazione, logaritmo discreto). Se l'avversario disponesse di potenza di calcolo illimitata, il sistema potrebbe essere rotto.

Formalmente: il costo computazionale dell'attacco supera le risorse disponibili per il tempo di utilità del messaggio.

**Sicurezza incondizionata (teorica dell'informazione / perfetta):**

Un sistema crittografico è **incondizionatamente sicuro** (o **perfettamente sicuro**) se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro a partire dal testo cifrato. Formalmente (secondo Shannon, 1949):

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

ovvero il testo cifrato non fornisce alcuna informazione probabilistica sul testo in chiaro: le distribuzioni di $P$ e $C$ sono stocasticamente indipendenti.

> 📌 Il **teorema di Shannon** afferma che la perfect secrecy richiede che lo spazio delle chiavi sia almeno grande quanto lo spazio dei messaggi: $|\mathcal{K}| \geq |\mathcal{M}|$. Ne consegue che le chiavi non possono essere riusate.

#### Parte b — Cifrario incondizionatamente sicuro (5 punti)

Il cifrario con sicurezza incondizionata per eccellenza è il **One-Time Pad (OTP)**, proposto da Vernam nel 1917 e dimostrato perfettamente sicuro da Shannon nel 1949.

**Schema:**
- Sia $m \in \{0,1\}^n$ il messaggio in chiaro di $n$ bit.
- Sia $k \in \{0,1\}^n$ la chiave, scelta uniformemente a caso, della stessa lunghezza del messaggio, usata **una sola volta**.
- **Cifratura:** $c = m \oplus k$
- **Decifratura:** $m = c \oplus k$

**Dimostrazione di perfect secrecy:**

Per ogni testo cifrato $c$ e per ogni coppia di testi in chiaro $m_1 \neq m_2$:

$$P(C = c \mid M = m_1) = P(K = m_1 \oplus c) = \frac{1}{2^n}$$

$$P(C = c \mid M = m_2) = P(K = m_2 \oplus c) = \frac{1}{2^n}$$

Quindi $P(C = c \mid M = m_1) = P(C = c \mid M = m_2)$ per ogni $m_1, m_2$ e ogni $c$: osservare il ciphertext non cambia la distribuzione di probabilità sul plaintext.

**Limiti pratici:** la chiave deve essere lunga quanto il messaggio, non riusabile, e distribuita in modo sicuro. Questi vincoli rendono l'OTP impraticabile in molti scenari reali.

#### Parte c — Doppia cifratura affine (10 punti)

**Definizione del cifrario affine:**

Il cifrario affine cifra un carattere $x \in \mathbb{Z}_{26}$ con chiave $(a, b)$ come:

$$E_{(a,b)}(x) = ax + b \bmod 26 \quad \text{con } \operatorname{mcd}(a, 26) = 1$$

La condizione $\operatorname{mcd}(a, 26) = 1$ garantisce l'invertibilità (8 valori possibili per $a$: 1,3,5,7,9,11,15,17,19,21,23,25).

**Analisi della composizione:**

Applichiamo prima $E_{k_2}$ e poi $E_{k_1}$ (la notazione del testo indica $E_{k_1}(E_{k_2}(\text{pt}))$):

$$E_{k_2}(x) = a_2 x + b_2 \bmod 26$$

$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 \bmod 26$$

$$= a_1 a_2 x + a_1 b_2 + b_1 \bmod 26$$

$$= (a_1 a_2) x + (a_1 b_2 + b_1) \bmod 26$$

Il risultato è ancora un cifrario affine con chiave equivalente:

$$\boxed{k_{\text{eq}} = (a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)}$$

**Risposta alla domanda: la doppia cifratura NON aumenta la robustezza.**

La composizione di due cifrari affini è ancora un cifrario affine. Ciò significa che:

1. **Chiusura algebrica:** la famiglia dei cifrari affini è **chiusa** rispetto alla composizione. Qualsiasi composizione di $n$ cifrari affini è equivalente a un singolo cifrario affine. Non si esce mai dalla famiglia.

2. **Nessuna espansione dello spazio delle chiavi effettivo:** anche se usiamo due chiavi $(a_1, b_1)$ e $(a_2, b_2)$, la sicurezza del doppio cifrario è quella di un cifrario affine con un'unica chiave equivalente $(a_1 a_2 \bmod 26, a_1 b_2 + b_1 \bmod 26)$. Un attaccante che rompe il cifrario affine singolo rompe automaticamente anche il doppio cifrario.

3. **Quantificazione:** lo spazio delle chiavi di un singolo cifrario affine ha $8 \times 26 = 208$ chiavi valide. Quello del doppio cifrario sembra $(8 \times 26)^2 \approx 43000$ coppie di chiavi, ma le chiavi equivalenti rimangono al più 208 (lo spazio dell'immagine). In realtà non tutte le 208 chiavi equivalenti sono raggiungibili: dipende da quali prodotti $a_1 a_2 \bmod 26$ siano anch'essi invertibili modulo 26, il che è verificato se $\operatorname{mcd}(a_1, 26) = \operatorname{mcd}(a_2, 26) = 1$.

> ✅ **Conclusione:** la doppia applicazione del cifrario affine con chiavi diverse è algebricamente equivalente a una singola applicazione con una chiave composta. Non aumenta la robustezza: l'analisi crittografica (ad esempio l'analisi delle frequenze) applicabile al cifrario affine semplice è altrettanto applicabile al doppio cifrario.

> 💡 Questo fenomeno (chiusura per composizione) si verifica anche per il cifrario di Vigenère e per altri cifrari lineari. È per questo che i cifrari moderni combinano operazioni NON lineari (S-box) con permutazioni lineari: la non-linearità impedisce la chiusura sotto composizione e aumenta davvero la sicurezza ad ogni round aggiuntivo.
