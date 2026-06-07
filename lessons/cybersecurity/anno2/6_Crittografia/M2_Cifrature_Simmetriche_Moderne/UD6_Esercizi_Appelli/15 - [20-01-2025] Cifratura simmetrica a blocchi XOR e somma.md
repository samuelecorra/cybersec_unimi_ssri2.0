# Cifratura simmetrica a blocchi XOR e somma — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 gennaio 2025**, Domanda 1 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 gennaio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L1%20-%20Appello%2020%20gennaio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura simmetrica (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (5) Discutere sicurezza condizionata e incondizionata.
>
> b. (5) Descrivere un cifrario avente sicurezza incondizionata.
>
> c. (20) Considera il semplice cifrario a blocchi $C = (P \oplus K_0) \boxplus K_1$ dove $P$ è l'input in chiaro a 64 bit, $K_0$ e $K_1$ sono le due metà di una chiave segreta a 128 bit $K$ (ovvero $K_0$ e $K_1$ sono ciascuna di 64 bit e $K = K_0 \| K_1$), e $C$ è l'output cifrato a 64 bit. $\oplus$ indica l'addizione bit per bit modulo due (XOR) e $\boxplus$ indica l'addizione modulo $2^{64}$.
>
> i. Derivare l'equazione di decrittazione.
>
> ii. Supponiamo che un avversario acquisisca due testi in chiaro $P$ e $P'$ e i corrispondenti cifrati $C$ e $C'$, crittografati con la stessa chiave $K = K_0 \| K_1$. È possibile risolvere per $K_0$?

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — definizione di sicurezza condizionata vs. incondizionata
- [`../../M1_Crittografia_Classica/UD1/L4 - Proprietà di Sicurezza.md`](../../M1_Crittografia_Classica/UD1/L4%20-%20Proprietà%20di%20Sicurezza.md) — one-time pad e sicurezza perfetta

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):** un cifrario è condizionatamente sicuro se il costo computazionale richiesto per romperlo (anche da un avversario con risorse illimitate di tempo) supera le risorse ragionevolmente disponibili, oppure se il tempo necessario per rompere il cifrario supera il periodo di utilità del messaggio. La sicurezza è legata alla difficoltà di problemi computazionali (es. fattorizzazione, logaritmo discreto). Se si disponesse di potenza computazionale sufficiente, il sistema sarebbe violabile.

**Sicurezza incondizionata (teorica dell'informazione):** un cifrario è incondizionatamente sicuro se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro a partire dal testo cifrato. Formalmente, ciò significa che la distribuzione di probabilità di $P$ è indipendente da $C$:

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

Questa definizione, dovuta a Shannon, è nota come **perfect secrecy** (segretezza perfetta).

> 📌 Il teorema di Shannon afferma che la perfect secrecy richiede che lo spazio delle chiavi sia almeno grande quanto lo spazio dei messaggi: $|\mathcal{K}| \geq |\mathcal{M}|$.

#### Parte b — Cifrario con sicurezza incondizionata (5 punti)

Il cifrario con sicurezza incondizionata per eccellenza è il **One-Time Pad (OTP)**, proposto da Vernam nel 1917 e dimostrato perfettamente sicuro da Shannon nel 1949.

**Schema dell'OTP:**
- Sia $m \in \{0,1\}^n$ il messaggio in chiaro di $n$ bit.
- Sia $k \in \{0,1\}^n$ la chiave, scelta uniformemente a caso, della stessa lunghezza del messaggio, usata una sola volta.
- **Cifratura:** $c = m \oplus k$
- **Decifratura:** $m = c \oplus k$

**Proprietà di sicurezza:**

Per ogni testo cifrato $c$ e per ogni coppia di testi in chiaro $m_1, m_2$, esiste esattamente una chiave $k_1 = m_1 \oplus c$ che mappa $m_1$ in $c$ e una chiave $k_2 = m_2 \oplus c$ che mappa $m_2$ in $c$. Quindi:

$$P(C = c \mid M = m_1) = P(C = c \mid M = m_2) = \frac{1}{2^n}$$

Osservare $c$ non fornisce alcuna informazione su $m$.

**Limiti pratici:** la chiave deve essere lunga quanto il messaggio, non può essere riusata, e deve essere distribuita in modo sicuro. Per questo l'OTP non è pratico nella maggior parte degli scenari reali.

#### Parte c.i — Equazione di decrittazione (10 punti)

Il cifrario è definito come:

$$C = (P \oplus K_0) \boxplus K_1$$

dove $\oplus$ è XOR (addizione bit a bit modulo 2) e $\boxplus$ è l'addizione modulo $2^{64}$.

Per ricavare l'equazione di decifratura partiamo da $C$ e vogliamo ottenere $P$.

**Passo 1:** Eliminiamo $K_1$ applicando la sottrazione modulo $2^{64}$. Il contrario di $\boxplus K_1$ è $\boxminus K_1$, cioè la sottrazione modulo $2^{64}$:

$$C \boxminus K_1 = (P \oplus K_0) \boxplus K_1 \boxminus K_1 = P \oplus K_0$$

**Passo 2:** Eliminiamo $K_0$ applicando di nuovo XOR con $K_0$ (ricordando che $x \oplus K_0 \oplus K_0 = x$ per ogni $x$):

$$(C \boxminus K_1) \oplus K_0 = (P \oplus K_0) \oplus K_0 = P$$

**Equazione di decifratura:**

$$\boxed{P = (C \boxminus K_1) \oplus K_0}$$

> 📌 Notare che l'operazione $\boxminus$ è la sottrazione modulo $2^{64}$, che equivale a sommare il complemento a 2 del sottraendo: $C \boxminus K_1 = C \boxplus (\overline{K_1} + 1)$ in aritmetica modulo $2^{64}$.

#### Parte c.ii — Attacco con due coppie note (plaintext, ciphertext) (10 punti)

L'avversario conosce: $(P, C)$ e $(P', C')$ con la stessa chiave $K = K_0 \| K_1$.

Scriviamo le due equazioni di cifratura:

$$C = (P \oplus K_0) \boxplus K_1 \tag{1}$$

$$C' = (P' \oplus K_0) \boxplus K_1 \tag{2}$$

Sottraiamo (2) da (1) in aritmetica modulo $2^{64}$:

$$C \boxminus C' = [(P \oplus K_0) \boxplus K_1] \boxminus [(P' \oplus K_0) \boxplus K_1]$$

$$C \boxminus C' = (P \oplus K_0) \boxminus (P' \oplus K_0) \tag{3}$$

> ⚠️ **Trappola classica:** non è possibile semplificare ulteriormente in modo diretto perché le operazioni $\oplus$ e $\boxplus$ non sono distributive tra loro. La sottrazione modulo $2^{64}$ si cancella, ma il risultato $(P \oplus K_0) \boxminus (P' \oplus K_0)$ non si semplifica in $P \boxminus P'$ a causa dell'interazione tra XOR e addizione modulare.

**Conclusione:** l'equazione (3) fornisce una relazione in cui $K_0$ compare due volte, ma in un modo che **non permette** di isolare $K_0$ algebricamente in forma chiusa:

$$C \boxminus C' = (P \oplus K_0) \boxminus (P' \oplus K_0)$$

Il membro destro non si semplifica perché l'XOR di valori diversi con $K_0$ crea carry diversi nell'addizione modulare. Con due coppie note non è possibile risolvere direttamente per $K_0$ in forma chiusa. Tuttavia, si noti che il numero di candidati per $K_0$ è ancora $2^{64}$ (l'equazione fornisce un vincolo che riduce lo spazio ma non lo risolve univocamente senza ulteriori informazioni).

> 💡 **Intuizione:** la composizione XOR (non lineare rispetto all'addizione mod $2^{64}$) e addizione modulare (non lineare rispetto allo XOR) crea un sistema di equazioni misto che non è risolubile con semplice algebra lineare. Questo è esattamente il principio su cui si basano i cifrari a blocchi moderni.
