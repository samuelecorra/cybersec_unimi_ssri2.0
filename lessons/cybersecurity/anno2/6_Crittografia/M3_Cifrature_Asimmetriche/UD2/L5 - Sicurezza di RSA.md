# **Lezione 5: Sicurezza di RSA**

### **1. Introduzione**

> 💡 **Modello di minaccia.** Si assume che l'avversario conosca la **chiave pubblica** $(n, e)$ e possa osservare i **testi cifrati** $C = M^e \bmod n$ a piacere. Non conosce $d$, $p$, $q$, né il messaggio $M$. La domanda è: cosa riesce comunque a fare?

La sicurezza del sistema **RSA** si analizza su due fronti distinti:

1. **Sicurezza della generazione delle chiavi** — l'avversario tenta di ricavare $d$ dalla chiave pubblica, senza decifrare messaggi specifici.

2. **Sicurezza della cifratura** — l'avversario tenta di recuperare $M$ dal crittotesto $C$, eventualmente sfruttando proprietà strutturali di RSA.

Entrambi dipendono dalla difficoltà computazionale di alcune operazioni matematiche, in particolare la **fattorizzazione di grandi numeri**. Tuttavia, come vedremo, esistono attacchi che *non* richiedono di fattorizzare $n$: sfruttano invece errori di implementazione o proprietà algebriche di RSA "testbook" (non padded).

---

### **2. Sicurezza della generazione delle chiavi**

#### **Scenario**

L’avversario conosce la **chiave pubblica** $(n, e)$  
e vuole risalire alla **chiave privata** $d$.

Poiché:  
$$  
d = e^{-1} \bmod ((p-1)(q-1))  
$$

per ottenere $d$, deve conoscere $p$ e $q$ oppure $\varphi(n)$.

---

#### **Attacco 1 – Fattorizzazione di n**

Se l’attaccante riuscisse a **fattorizzare $n$**, potrebbe:

1. Calcolare $p$ e $q$
    
2. Calcolare $\varphi(n) = (p - 1)(q - 1)$
    
3. Calcolare $d = e^{-1} \bmod \varphi(n)$
    

Da qui, la **sicurezza di RSA** dipende direttamente dalla **difficoltà di fattorizzare $n = p \cdot q$**.

---

#### **Attacco 2 – Calcolo diretto di $\varphi(n)$**

Se un avversario potesse calcolare $\varphi(n)$, sarebbe comunque in grado di **ricavare $p$ e $q$** in tempo polinomiale, tramite la seguente riduzione algebrica.

**Derivazione dell’equazione quadratica:**

Si parte dal sistema:

$$  
\begin{cases}  
n = p \cdot q \\  
\varphi(n) = (p-1)(q-1) = pq - p - q + 1 = n - (p + q) + 1
\end{cases}  
$$

Dalla seconda equazione si estrae la **somma** $p + q$:

$$
p + q = n - \varphi(n) + 1
$$

Ora si conoscono sia la **somma** $p + q$ che il **prodotto** $p \cdot q = n$: due numeri che soddisfano queste relazioni sono le radici di un’equazione quadratica con coefficienti noti.

$$
t^2 - (p+q)\,t + pq = 0 \quad\Longrightarrow\quad t^2 - \bigl(n - \varphi(n) + 1\bigr)\,t + n = 0
$$

Le due radici di questa equazione sono esattamente $p$ e $q$.

> 📌 Quindi calcolare $\varphi(n)$ è **almeno altrettanto difficile** quanto fattorizzare $n$: chi ottiene $\varphi(n)$ ottiene automaticamente la fattorizzazione. I due problemi sono computazionalmente equivalenti.

**Esempio numerico:**

$$  
n = 84{,}773{,}093, \qquad \varphi(n) = 84{,}754{,}668  
$$

$$
p + q = 84{,}773{,}093 - 84{,}754{,}668 + 1 = 18{,}426
$$

Equazione: $t^2 - 18{,}426\,t + 84{,}773{,}093 = 0$.  
Discriminante: $18{,}426^2 - 4 \cdot 84{,}773{,}093 = 339{,}317{,}476 - 339{,}092{,}372 = 225{,}104$.  
$\sqrt{225{,}104} = 474.5\ldots \approx 474$ → radici: $p = \frac{18{,}426 + 474}{2} = 9450$… 

> ⚠️ I valori esatti dell’esempio originale sono $p = 9539$, $q = 8887$ (il calcolo preciso richiede aritmetica intera esatta).

---

#### **Attacco 3 – Calcolo di d**

Se un avversario potesse **calcolare direttamente $d$** da $(n, e)$, potrebbe anche **fattorizzare $n$** — e viceversa. Il ragionamento è il seguente.

> 💡 **Idea del collegamento.** Se conosci $d$ e $e$, sai che $ed \equiv 1 \pmod{\varphi(n)}$, cioè $ed - 1$ è un multiplo di $\varphi(n)$. Dalla conoscenza di un multiplo di $\varphi(n)$ si può ricavare la fattorizzazione di $n$ tramite un algoritmo randomizzato: si sceglie un elemento casuale $a \in \mathbb{Z}_n^*$ e si calcola la sequenza $a^{(ed-1)/2^i} \bmod n$ finché non si trova una radice quadrata non banale di $1$. Una radice non banale di $1$ modulo $n = pq$ rivela un fattore di $n$ tramite il $\operatorname{mcd}$.

Un algoritmo in grado di calcolare $d$ può essere usato come base per un **algoritmo di tipo Las Vegas** che fattorizza $n$ con probabilità almeno $1/2$ ad ogni tentativo.

> 📌 **Cos'è un algoritmo Las Vegas?** È un algoritmo randomizzato che ha due possibili esiti: restituisce la risposta **corretta** oppure dichiara **fallimento** (non dà mai una risposta sbagliata). Ripetendo l'algoritmo un numero costante di volte, la probabilità di successo diventa arbitrariamente alta.

---

#### **Equivalenza**

In sintesi, i tre problemi seguenti sono computazionalmente equivalenti:

$$  
\text{Fattorizzare } n \;\Longleftrightarrow\; \text{Calcolare } \varphi(n) \;\Longleftrightarrow\; \text{Calcolare } d  
$$

> ✅ Rompere RSA nella sua generazione di chiavi equivale a fattorizzare $n$: nessuno dei tre problemi è più facile degli altri. Quindi la sicurezza dell'intera struttura chiave si riduce a un unico assunto: **fattorizzare $n$ è difficile**.

---

### **3. Progresso degli attacchi di fattorizzazione**

> 💡 Le sfide **RSA-XXX** sono record storici pubblicati dal laboratorio RSA Security: ogni numero è il prodotto di due primi segreti di dimensione bilanciata, e il premio spettava a chi lo fattorizzava per primo. Permettono di misurare concretamente quanto è avanzata la crittanalisi nel tempo.

| Anno | Sfida | Cifre dec. | Bit | Risorse | Algoritmo |
|---|---|---|---|---|---|
| 1994 | RSA-129 | 129 | ~425 | 1600 computer, 8 mesi | QS |
| 1999 | RSA-155 | 155 | 512 | 300 workstation + CRAY, 5 mesi | GNFS |
| 2003 | RSA-174 | 174 | 576 | cluster distribuito | GNFS |
| 2005 | RSA-193 | 193 | 640 | 30× Opteron 2.2 GHz, 5 mesi | GNFS |
| 2009 | RSA-768 | 232 | **768** | centinaia di CPU, 2 anni | GNFS |
| 2020 | RSA-250 | 250 | **829** | ~2700 CPU·anno | GNFS |

> ⚠️ **Aggiornamento critico.** RSA-**768** è stato fattorizzato nel **2009**: le chiavi a 768 bit non sono mai state sicure per uso reale e vanno considerate definitivamente rotte. RSA-**1024** non è ancora stato fattorizzato pubblicamente, ma la comunità crittografica lo considera a rischio nel medio termine e NIST lo ha deprecato dal 2013. Lo standard minimo attuale è **2048 bit**.

Dove:

- **QS** = Quadratic Sieve — efficiente per numeri piccoli (~< 110 cifre decimali)
- **GNFS** = General Number Field Sieve — il più efficiente algoritmo classico noto per la fattorizzazione di interi grandi; complessità sub-esponenziale $L_n[1/3, 1.923]$

> 📌 **Prospettiva quantistica.** L'algoritmo di **Shor** (1994) fattorizza in tempo polinomiale su un computer quantistico. Con qubit sufficienti e tolleranza agli errori, RSA di qualsiasi dimensione classica sarebbe rotto. Per questo NIST ha avviato la standardizzazione di algoritmi **post-quantum** (CRYSTALS-Kyber, CRYSTALS-Dilithium, SPHINCS+), approvati nel 2024.
    

---

### **4. Lunghezza del modulo n**

Per garantire sicurezza, i numeri primi $p$ e $q$ devono avere **dimensioni simili** (bilanciamento tra $p$ e $q$ previene attacchi di fattorizzazione speciali come p-1 method di Pollard) e sufficientemente grandi.

> ⚠️ **Tabella originale del docente (anno ~2000–2005) — non più valida.** Le raccomandazioni sotto sono quelle attuali (NIST SP 800-131A rev.2, 2019–2024):

| Contesto | Raccomandazione docente (obsoleta) | Standard attuale (NIST 2024) |
|---|---|---|
| Uso personale / breve termine | ≥ 768 bit ❌ rotto dal 2009 | **≥ 2048 bit** |
| Uso aziendale / medio termine | ≥ 1024 bit ⚠️ deprecato dal 2013 | **≥ 2048 bit** |
| CA / lungo termine (>2030) | ≥ 2048 bit | **≥ 3072–4096 bit** |
| Sistemi post-quantum | — | **CRYSTALS-Kyber / ML-KEM** |

> 💡 **Perché dimensioni simili tra $p$ e $q$?** Se $p \ll q$, allora $p \approx \sqrt{n}$ e può essere trovato con il metodo di Fermat o con l'algoritmo $p-1$ di Pollard in tempi molto ridotti. La regola pratica è che $p$ e $q$ abbiano esattamente la stessa lunghezza in bit (es. entrambi 1024 bit per un modulo a 2048 bit).

---

### **5. Sicurezza della cifratura RSA**

#### **Scenario**

L’avversario conosce:  
$$  
(n, e) \quad \text{e} \quad C = M^e \bmod n  
$$  
e vuole ottenere $M$.

#### **Attacco 1 – Fattorizzazione di n**

1. Fattorizza $n$
    
2. Calcola $\varphi(n) = (p-1)(q-1)$
    
3. Trova $d = e^{-1} \bmod \varphi(n)$
    
4. Decifra $C$ calcolando $M = C^d \bmod n$
    

---

> ⚠️ **Problema aperto.** Non si sa se **calcolare $M$ da $C$ e $(n, e)$** (invertire RSA) sia equivalente alla fattorizzazione di $n$. Potrebbe esistere un algoritmo che decifra RSA **senza** fattorizzare — e se sì, RSA sarebbe insicuro anche se la fattorizzazione restasse difficile. A differenza della sicurezza della generazione delle chiavi (dove l'equivalenza è dimostrata), qui l'equivalenza è solo *congetturata* e costituisce uno dei problemi aperti fondamentali della crittografia teorica. In pratica si assume che le due difficoltà siano legate, ma nessuna prova formale esiste.

---

### **6. Attacchi non basati sulla fattorizzazione**

RSA può essere vulnerabile anche a **strategie matematiche o implementative** non legate direttamente alla fattorizzazione.

---

#### **a) Chosen Ciphertext Attack (CCA)**

> 💡 **Scenario.** L'attaccante intercetta un crittotesto $C = M^e \bmod n$ e vuole trovare $M$. Non può decifrare $C$ direttamente (non ha $d$), ma supponiamo che abbia accesso a un **oracolo di decifratura** — ad esempio un server che decifra qualsiasi messaggio *diverso da $C$* (caso realistico: un server di firma digitale che firma messaggi a richiesta). L'attacco sfrutta la **malleabilità** di RSA testbook.

**Costruzione dell'attacco:**

L'attaccante sceglie un intero casuale $x \in \mathbb{Z}_n^*$ e costruisce un crittotesto modificato:

$$  
C' = C \cdot x^e \bmod n  
$$

$C'$ è diverso da $C$ (l'oracolo lo accetta), ma è correlato a $C$. L'attaccante sottomette $C'$ all'oracolo e ottiene:

$$  
M' = (C')^d \bmod n  
$$

**Perché questo rivela $M$:**

$$  
M' = (C \cdot x^e)^d \bmod n = C^d \cdot (x^e)^d \bmod n = M \cdot x^{ed} \bmod n \equiv M \cdot x \pmod{n}
$$

(usando $x^{ed} \equiv x \pmod{n}$ per il teorema di Fermat-Eulero). Quindi:

$$  
M = M' \cdot x^{-1} \bmod n  
$$

> 📌 **Proprietà omomorfica di RSA.** Questo attacco funziona perché RSA soddisfa $(M_1 M_2)^e \equiv M_1^e \cdot M_2^e \pmod{n}$: la cifratura del prodotto è il prodotto delle cifrature. Questa proprietà è un difetto strutturale di RSA **testbook** (non padded). La contromisura è usare **OAEP** (Optimal Asymmetric Encryption Padding), che rompe la malleabilità aggiungendo randomizzazione al messaggio prima della cifratura.

---

#### **b) Common Modulus Attack**

> 💡 **Scenario.** Un'autorità genera un solo modulo $n$ e lo distribuisce a più utenti, assegnando a ciascuno un esponente pubblico diverso ($e_1, e_2, \ldots$). Sembra efficiente, ma è un errore fatale: se lo stesso messaggio $M$ viene cifrato per due utenti diversi con lo stesso $n$, chiunque osservi entrambi i crittotesti può recuperare $M$ **senza conoscere** $d_1$ o $d_2$.

**Condizione:** $\operatorname{mcd}(e_1, e_2) = 1$ (quasi sempre vera se $e_1, e_2$ sono scelti come numeri primi distinti).

Sia:

$$  
\begin{cases}  
C_1 = M^{e_1} \bmod n \\  
C_2 = M^{e_2} \bmod n  
\end{cases}  
$$

**Perché funziona — il collegamento con Bézout:**

Poiché $\operatorname{mcd}(e_1, e_2) = 1$, per l'identità di Bézout esistono interi $x, y$ (con uno eventualmente negativo) tali che:

$$  
e_1 x + e_2 y = 1  
$$

Si trovano con Euclide esteso. Ora si calcola:

$$  
C_1^x \cdot C_2^y \bmod n = (M^{e_1})^x \cdot (M^{e_2})^y = M^{e_1 x + e_2 y} = M^1 = M  
$$

> ⚠️ Se $y < 0$ (cosa comune), $C_2^y$ non è direttamente calcolabile. Si usa invece $C_2^{-1} \bmod n$ (inverso moltiplicativo di $C_2$, calcolabile con Euclide esteso) elevato a $|y|$.

> ✅ **Lezione pratica.** Non condividere mai il modulo $n$ tra utenti diversi. Ogni coppia di chiavi RSA deve avere il proprio $n$ generato indipendentemente.

---

#### **c) Low Exponent Attack**

> 💡 **Scenario.** Si usa $e = 3$ (esponente piccolo, cifratura rapida) e lo stesso messaggio $M$ viene cifrato con tre chiavi pubbliche **distinte** $(e=3, n_1)$, $(e=3, n_2)$, $(e=3, n_3)$. I tre moduli sono diversi (chiavi di tre destinatari distinti), quindi ogni cifratura è separata. Ma l'attacco sfrutta il fatto che **tutti e tre alzano $M$ alla stessa potenza $3$**.

**Condizione critica per l'attacco:**

$$
M < \min(n_1, n_2, n_3)^{1/3} \qquad \text{(equivalente a } M^3 < n_1 n_2 n_3\text{)}
$$

Questa condizione è soddisfatta quando $M$ è "piccolo" rispetto ai moduli — tipico se $M$ è un messaggio non padded e i moduli sono grandi.

**Costruzione:**

$$  
\begin{cases}  
C_1 \equiv M^3 \pmod{n_1} \\  
C_2 \equiv M^3 \pmod{n_2} \\  
C_3 \equiv M^3 \pmod{n_3}  
\end{cases}  
$$

Si applica il **CRT** al sistema, ottenendo l'unico $x$ modulo $N = n_1 n_2 n_3$ tale che:

$$
x \equiv C_i \pmod{n_i} \quad \text{per } i = 1,2,3
$$

Poiché $M^3 < N$ (condizione critica sopra), non c'è riduzione modulare: il CRT restituisce **esattamente** $M^3$ come intero, non $M^3 \bmod N$. Quindi:

$$  
M = \lfloor x^{1/3} \rfloor  
$$

> 📌 **Il punto chiave.** Se $M^3 \ge N$ la riduzione modulare avrebbe "avvolto" il valore e il CRT avrebbe dato $M^3 \bmod N \ne M^3$: l'attacco fallirebbe. È proprio l'assenza di padding a mantenere $M$ abbastanza piccolo da rendere possibile l'attacco. Con **OAEP**, il messaggio viene prima espanso con bit casuali fino a riempire $n$: $M_{\text{padded}} \approx n$, e $(M_{\text{padded}})^3 \gg n_1 n_2 n_3$, rendendo il CRT inutilizzabile.

---

### **7. Informazioni parziali sul messaggio**

Ci si chiede: è possibile ottenere **informazioni parziali su $M$** conoscendo solo $C = M^e \bmod n$, senza invertire completamente RSA?

La risposta è: no — ogni singolo bit di informazione su $M$ è computazionalmente difficile quanto invertire RSA per intero. I due predicati seguenti lo formalizzano.

#### **Predicato “mezzo intervallo”**

Definiamo:

$$  
\text{half}_{n,e}(C) =  
\begin{cases}  
0 & \text{se } M < \frac{n}{2} \\  
1 & \text{se } M \ge \frac{n}{2}  
\end{cases}  
$$

> 💡 **Intuizione.** Se potessimo valutare $\text{half}_{n,e}(\cdot)$ efficientemente, potremmo fare **binary search** su $M$: sappiamo che $M \in [0, n)$; se $\text{half} = 0$ lo restriamo a $[0, n/2)$, altrimenti a $[n/2, n)$; moltiplichiamo $C$ per $2^e \bmod n$ (che cifra $2M \bmod n$) e ripetiamo. In $\log_2 n \approx 2048$ passi recupereremmo $M$ completamente. Quindi un oracolo per $\text{half}$ permetterebbe di invertire RSA in tempo polinomiale.

Determinare $\text{half}_{n,e}(C)$ è quindi **computazionalmente equivalente a invertire RSA**: se uno è facile, lo è anche l'altro.

---

#### **Predicato di parità**

Definiamo:

$$  
\text{par}_{n,e}(C) = \text{LSB}(M) = M \bmod 2  
$$

cioè il **bit meno significativo** di $M$ (0 se $M$ è pari, 1 se dispari).

I due predicati sono **computazionalmente equivalenti** tramite la riduzione:

$$  
\text{par}_{n,e}(C) = \text{half}_{n,e}\!\left(C \cdot (2^{-1})^e \bmod n\right)  
$$

> 📌 **Come si legge questa identità.** Il valore $2^{-1} \bmod n$ è l'inverso di $2$ modulo $n$ (esiste perché $n$ è dispari). Moltiplicare $C$ per $(2^{-1})^e$ equivale a cifrare $M \cdot 2^{-1} \bmod n = M/2$ (nel senso modulare). Sapere se $M/2$ cade nella metà inferiore o superiore di $[0,n)$ equivale a sapere se $M$ è pari o dispari — cioè il suo LSB. Quindi chi sa calcolare $\text{half}$ sa anche calcolare $\text{par}$, e viceversa (con una riduzione simmetrica). Entrambi sono difficili quanto rompere RSA.

---

### **8. Attacchi alle implementazioni hardware (Side-Channel)**

> 💡 **Idea di fondo.** Un algoritmo matematicamente sicuro può essere rotto osservando **come viene eseguito fisicamente**: il tempo che impiega, la corrente che consuma, le radiazioni elettromagnetiche che emette. Questi sono i **side-channel attack**: non attaccano la matematica di RSA, ma l’implementazione concreta su hardware reale.

#### **Timing Attack** *(Kocher, 1997)*

> 📌 **Come funziona.** Nell’algoritmo left-to-right (square-and-multiply), ogni bit $y_i$ di $d$ determina se dopo il quadrato si esegue anche una moltiplicazione per $x$. La moltiplicazione richiede tempo leggermente diverso dal solo quadrato — differenza misurabile in nanosecondi. Un attaccante che misura il tempo di migliaia di decifraure può, tramite analisi statistica, ricostruire i bit di $d$ **uno alla volta**, dal più significativo al meno significativo.

- Analizza il **tempo di esecuzione** dell’esponenziazione modulare su dispositivi reali (smartcard, HSM, server TLS).
- Permette di ricostruire i bit di $d$ statisticamente, anche senza accesso fisico diretto (basta misurare il tempo di risposta di un server remoto).
- **Contromisura:** *blinding* — prima di decifrare, si moltiplica $C$ per un valore casuale $r^e$, ottenendo $C’ = C \cdot r^e$; si decifra $C’$ ottenendo $M’ = M \cdot r$; si rimuove $r$ dividendo. Ogni operazione usa un esponente "mascherato" diverso, rendendo inutili le misurazioni temporali.

#### **Power Attack** *(Kocher, 1999)*

> 📌 **Come funziona.** La corrente consumata da una smartcard durante la moltiplicazione modulare è misurabile con una sonda sul pin di alimentazione. La **Simple Power Analysis (SPA)** legge il tracciato di potenza di una singola decifratura e riconosce visivamente i pattern "quadrato" vs "quadrato+moltiplica" — rivelando direttamente i bit di $d$. La **Differential Power Analysis (DPA)** usa analisi statistica su molte tracce per estrarre $d$ anche in presenza di rumore.

- Analizza la **potenza consumata** dal dispositivo durante la decifratura.
- SPA: basta una singola traccia se il dispositivo non è protetto.
- DPA: funziona anche con rumore, usando centinaia di tracce e correlazione statistica.
- **Contromisura:** blinding (come per il timing attack) + implementazioni a potenza costante (che eseguono sempre sia il quadrato che la moltiplicazione, scartando il risultato quando il bit è 0).
    

---

### **9. Sintesi finale**

> ✅ **Mappa degli attacchi a RSA.**

| Livello | Attacco | Condizione che lo abilita | Contromisura |
|---|---|---|---|
| Generazione chiavi | Fattorizzazione di $n$ | Chiavi troppo corte | Modulo ≥ 2048 bit |
| Generazione chiavi | Calcolo di $\varphi(n)$ o $d$ | Equivalente a fattorizzare | (stessa) |
| Cifratura | CCA / malleabilità | RSA testbook senza padding | OAEP |
| Cifratura | Common Modulus | $n$ condiviso tra utenti | $n$ unico per ogni coppia di chiavi |
| Cifratura | Low Exponent ($e=3$) | Stesso $M$ non padded per 3+ destinatari | OAEP / padding randomizzato |
| Hardware | Timing attack | Square-and-multiply con tempi differenti | Blinding |
| Hardware | Power attack (SPA/DPA) | Consumo misurabile | Blinding + potenza costante |

**Tre punti fermi:**

1. La **sicurezza matematica** di RSA si riduce alla difficoltà di fattorizzare $n$: generazione chiavi e cifratura sono entrambe sicure **se e solo se** la fattorizzazione è difficile — con la riserva che l’equivalenza per la cifratura è congetturata, non dimostrata.

2. Lo standard attuale è **minimo 2048 bit**; 768 e 1024 bit sono insicuri o deprecati. Per orizzonti temporali post-2030 si raccomanda 3072–4096 bit, e per sicurezza a lungo termine si guarda ai sistemi **post-quantum** (ML-KEM, ML-DSA).

3. RSA **testbook** (senza padding) è insicuro in pratica anche con chiavi lunghe: CCA, Common Modulus e Low Exponent lo rompono senza fattorizzare. L’unica variante sicura è **RSA-OAEP** per cifratura e **RSA-PSS** per firma.

---

> 💡 **Prossimo passo per l’esame:** ora conosci CCA, Common Modulus e Low Exponent. Gli appelli 240708 e 250715 chiedono di analizzare la variante **Double-RSA** — due cifrature RSA in cascata con esponenti diversi — e spiegarne la sicurezza effettiva (**10 punti**). Vai a [UD5 / L1 – Double-RSA: analisi di sicurezza](../UD5_Approfondimenti_Esame/L1%20-%20Double-RSA%20-%20analisi%20di%20sicurezza.md) per la dimostrazione completa di equivalenza con RSA singolo.