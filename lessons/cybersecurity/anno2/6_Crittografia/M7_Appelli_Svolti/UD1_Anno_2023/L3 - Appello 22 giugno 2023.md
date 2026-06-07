# Appello del 22 giugno 2023 — Soluzione completa

> 📌 **Informazioni appello:** 22/06/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (30 + 20 + 25 + 25).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario affine
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — modalità del DES
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA
> - [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — firma DSS

---

## Domanda 1 — Cifratura simmetrica: sicurezza e cifrario affine (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (5) Dare una definizione di sicurezza condizionata ed incondizionata.
>
> b. (5) Descrivere un cifrario incondizionatamente sicuro.
>
> c. (10) Per un cifrario affine con chiave $k=(a,b)$ si sa che $E_{(a,b)}(\text{O}) = \text{Z}$ e $E_{(a,b)}(\text{T}) = \text{I}$. Trovare la chiave utilizzata e applicarla alla cifratura di "CIAO".
>
> d. (10) Sempre per un cifrario affine, l'applicazione in sequenza con chiavi $k_1=(a_1,b_1)$ e $k_2=(a_2,b_2)$, $E_{k_1}(E_{k_2}(\text{plaintext}))$, aumenta la robustezza del cifrario? (giustificare)

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — sicurezza condizionata/incondizionata
- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario affine

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

- **Sicurezza incondizionata (perfetta):** il cifrario resiste a un avversario con **potenza di calcolo illimitata**. Formalmente, il ciphertext non dà alcuna informazione sul plaintext: $\Pr[M = m \mid C = c] = \Pr[M = m]$ per ogni $m, c$ (Shannon). Richiede chiave lunga almeno quanto il messaggio.
- **Sicurezza condizionata (computazionale):** il cifrario resiste a un avversario con **risorse di calcolo limitate** (tempo polinomiale). La sicurezza dipende dalla difficoltà pratica di un problema (forza bruta, fattorizzazione, log discreto): teoricamente violabile, ma non in tempo ragionevole.

#### Parte b — Un cifrario incondizionatamente sicuro (5 punti)

Il **One-Time Pad (OTP)** di Vernam: $c_i = m_i \oplus k_i$ con chiave $k$ **casuale, lunga quanto il messaggio e usata una sola volta**. Per ogni ciphertext, ogni plaintext è equiprobabile (esiste una chiave che lo produce), quindi il ciphertext non rivela nulla: sicurezza perfetta dimostrata da Shannon. Limiti pratici: distribuzione e gestione di chiavi enormi, mai riusabili.

#### Parte c — Trovare la chiave affine e cifrare "CIAO" (10 punti)

Cifrario affine: $E_{(a,b)}(x) = (a x + b) \bmod 26$, con $\gcd(a,26)=1$. Codifica A=0, …, Z=25. Dati: O=14, Z=25, T=19, I=8.

$$\begin{cases} 14a + b \equiv 25 \pmod{26} \\ 19a + b \equiv 8 \pmod{26} \end{cases}$$

Sottraendo la prima dalla seconda: $5a \equiv 8 - 25 = -17 \equiv 9 \pmod{26}$. L'inverso di 5 mod 26 è 21 (perché $5\cdot21 = 105 \equiv 1$), quindi:
$$a \equiv 21 \cdot 9 = 189 \equiv 189 - 7\cdot26 = 7 \pmod{26}$$
Da $14a + b \equiv 25$: $b \equiv 25 - 14\cdot7 = 25 - 98 = -73 \equiv -73 + 3\cdot26 = 5 \pmod{26}$.

**Chiave:** $k = (a,b) = (7,5)$, con $\gcd(7,26)=1$ (valida). Verifica: $E(\text{O}) = 7\cdot14+5 = 103 \equiv 25 = \text{Z}$ ✓; $E(\text{T}) = 7\cdot19+5 = 138 \equiv 8 = \text{I}$ ✓.

**Cifratura di "CIAO"** (C=2, I=8, A=0, O=14):
$$E(2) = 7\cdot2+5 = 19 = \text{T}, \quad E(8) = 7\cdot8+5 = 61 \equiv 9 = \text{J}$$
$$E(0) = 5 = \text{F}, \quad E(14) = 7\cdot14+5 = 103 \equiv 25 = \text{Z}$$

**"CIAO" → "TJFZ"**.

#### Parte d — La doppia cifratura affine aumenta la robustezza? (10 punti)

**No.** Componendo due cifrari affini:
$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 = (a_1 a_2)\,x + (a_1 b_2 + b_1) \pmod{26}$$
che è **ancora un cifrario affine** con chiave $(a', b') = (a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)$.

L'insieme dei cifrari affini è **chiuso rispetto alla composizione** (forma un gruppo): la doppia cifratura non produce nulla che un singolo affine non possa già produrre. Lo spazio delle chiavi resta quello di un affine ($\varphi(26)\cdot26 = 12 \cdot 26 = 312$ chiavi), quindi la doppia cifratura **non aumenta** la robustezza e resta banalmente violabile (2 coppie note bastano).

> 💡 È lo stesso motivo per cui il "doppio Cesare" è un Cesare e per cui servì il **3DES** e non il "2DES": comporre cifrari aiuta solo se il cifrario **non è chiuso** rispetto alla composizione.

---

## Domanda 2 — Modalità operative del DES (20 punti)

### Traccia originale

> **(20 punti) DES.** Discutere le modalità di cifratura di DES, evidenziandone vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalit%C3%A0%20operative%20del%20DES.md) — modalità operative

### Soluzione dettagliata

Le **modalità operative** definiscono come usare il cifrario a blocchi DES (blocco 64 bit) su messaggi di lunghezza arbitraria.

- **ECB:** $c_i = E_k(m_i)$. *Pro:* semplice, parallelizzabile, accesso casuale. *Contro:* deterministica, rivela pattern (blocchi uguali → cifrati uguali). **Da evitare.**
- **CBC:** $c_i = E_k(m_i \oplus c_{i-1})$, $c_0 = IV$. *Pro:* nasconde i pattern con IV casuale. *Contro:* sequenziale; serve un IV imprevedibile.
- **CFB:** $c_i = m_i \oplus E_k(c_{i-1})$. *Pro:* a flusso, niente padding. *Contro:* sequenziale; propagazione errore.
- **OFB:** $w_i = E_k(w_{i-1})$, $c_i = m_i \oplus w_i$. *Pro:* a flusso, errori di bit non si propagano. *Contro:* keystream riusabile se si riusa l'IV (two-time-pad).
- **CTR:** $c_i = m_i \oplus E_k(\text{nonce}\,\|\,i)$. *Pro:* parallelizzabile, accesso casuale. *Contro:* mai riusare (chiave, contatore).

> 📌 Linea guida: mai ECB per dati strutturati; usare CBC/CTR con IV/nonce casuale e mai ripetuto sotto la stessa chiave.

---

## Domanda 3 — Cifratura asimmetrica RSA (25 punti)

### Traccia originale

> **(25 punti) Cifratura asimmetrica.**
>
> a. (10) Si dimostri la correttezza di RSA.
>
> b. (15) Si consideri il crittosistema RSA con $n = 1363$. Si è scoperto che $\varphi(n) = 1288$. Usare questa informazione per fattorizzare $n$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — schema RSA
- [`../../M3_Cifrature_Asimmetriche/UD2/L3 - Fattorizzazione e test di primalità.md`](../../M3_Cifrature_Asimmetriche/UD2/L3%20-%20Fattorizzazione%20e%20test%20di%20primalit%C3%A0.md) — fattorizzazione

### Soluzione dettagliata

#### Parte a — Correttezza di RSA (10 punti)

Setup: $n = pq$ ($p,q$ primi distinti), $\varphi(n) = (p-1)(q-1)$, esponente pubblico $e$ con $\gcd(e,\varphi(n))=1$, esponente privato $d \equiv e^{-1} \pmod{\varphi(n)}$. Cifratura $c = m^e \bmod n$, decifratura $m' = c^d \bmod n$. Va dimostrato $m' = m$.

Poiché $ed \equiv 1 \pmod{\varphi(n)}$, esiste un intero $t$ con $ed = 1 + t\,\varphi(n)$. Quindi
$$c^d = m^{ed} = m^{1 + t\varphi(n)} = m \cdot \big(m^{\varphi(n)}\big)^t \pmod n$$

- **Se $\gcd(m,n)=1$:** per il teorema di Eulero $m^{\varphi(n)} \equiv 1 \pmod n$, quindi $m^{ed} \equiv m \cdot 1^t = m$.
- **Caso generale (anche $\gcd(m,n)>1$):** si dimostra modulo $p$ e $q$ separatamente e si conclude col **Teorema Cinese del Resto**. Modulo $p$: se $p \nmid m$, per il piccolo teorema di Fermat $m^{p-1}\equiv1$, e $m^{ed} = m\cdot m^{t(p-1)(q-1)} \equiv m \pmod p$; se $p \mid m$ allora $m \equiv 0$ e $m^{ed}\equiv 0 \equiv m \pmod p$. In entrambi i casi $m^{ed}\equiv m \pmod p$, e analogamente $\pmod q$. Poiché $p,q$ sono coprimi, per il CRT $m^{ed} \equiv m \pmod{pq=n}$.

Quindi $c^d \equiv m \pmod n$: RSA è corretto. $\blacksquare$

#### Parte b — Fattorizzare $n=1363$ noto $\varphi(n)=1288$ (15 punti)

Conoscere $\varphi(n)$ insieme a $n$ permette di fattorizzare immediatamente. Da $n = pq$ e $\varphi(n) = (p-1)(q-1) = pq - (p+q) + 1$:

$$p + q = n - \varphi(n) + 1 = 1363 - 1288 + 1 = 76$$

Quindi $p, q$ sono le radici dell'equazione di secondo grado $x^2 - (p+q)x + pq = 0$:
$$x^2 - 76x + 1363 = 0$$
$$x = \frac{76 \pm \sqrt{76^2 - 4\cdot1363}}{2} = \frac{76 \pm \sqrt{5776 - 5452}}{2} = \frac{76 \pm \sqrt{324}}{2} = \frac{76 \pm 18}{2}$$

$$p = \frac{76 + 18}{2} = 47, \qquad q = \frac{76 - 18}{2} = 29$$

**Fattorizzazione:** $n = 1363 = 47 \times 29$. Verifica: $47\cdot29 = 1363$ ✓, $\varphi = 46\cdot28 = 1288$ ✓.

> ⚠️ Questo mostra che **calcolare $\varphi(n)$ è equivalente a fattorizzare $n$**: chi conosce $\varphi(n)$ rompe RSA. Per questo $\varphi(n)$ (e quindi $d$) deve restare segreto: la sua segretezza è la sicurezza stessa di RSA.

---

## Domanda 4 — Firma digitale DSS (25 punti)

### Traccia originale

> **(25 punti) Firma digitale.**
>
> a. (10 punti) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (5) Cosa succede se viene firmato lo stesso messaggio in due diverse occasioni? Qual è la differenza con RSA?
>
> c. (10 punti) Dimostrare che se si ottiene per un certo valore $a$ la firma $(a, 0)$, allora il sistema DSA potrebbe essere rotto.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — schema DSS
- [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — nonce e attacchi
- [`../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1 - DSA - firma (a,0) e caso delta=0.md`](../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1%20-%20DSA%20-%20firma%20%28a%2C0%29%20e%20caso%20delta%3D0.md) — caso firma $(a,0)$

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

**Parametri pubblici:** primo $p$ ($L$ bit), primo $q$ (160/256 bit) con $q \mid (p-1)$, generatore $g = h^{(p-1)/q}\bmod p$ del sottogruppo di ordine $q$, hash $H$. **Chiavi:** privata $x \in \{1,\ldots,q-1\}$, pubblica $y = g^x \bmod p$.

**Firma di $M$:**
1. Scegliere un **nonce** $k \in \{1,\ldots,q-1\}$ casuale e unico.
2. $r = (g^k \bmod p) \bmod q$ (se $r=0$ ripetere).
3. $s = k^{-1}\big(H(M) + x\,r\big) \bmod q$ (se $s=0$ ripetere).
4. Firma $= (r,s)$.

**Verifica** (con $y$): $w = s^{-1}\bmod q$; $u_1 = H(M)w \bmod q$; $u_2 = r w \bmod q$; $v = (g^{u_1}y^{u_2}\bmod p)\bmod q$. **Accetta** se $v = r$.

**Correttezza:** da $s=k^{-1}(H(M)+xr)$ segue $k = w(H(M)+xr) = u_1 + x u_2 \pmod q$, quindi $v = (g^{u_1+xu_2}\bmod p)\bmod q = (g^k \bmod p)\bmod q = r$.

#### Parte b — Stesso messaggio firmato due volte; confronto con RSA (5 punti)

DSS è **probabilistico**: dipende dal nonce $k$. Con $k$ diverso, due firme dello stesso $M$ sono **diverse** ($r$ dipende da $k$) ed entrambe valide, senza rivelare nulla. Se invece si **riusa $k$**, allora $r=r'$ e l'avversario calcola $k=(s-s')^{-1}(H(M)-H(M'))$ e poi $x = r^{-1}(sk-H(M))$, **compromettendo la chiave privata** (è l'attacco PS3/Sony del 2010).

**Differenza con RSA:** il textbook RSA è **deterministico** — firmare lo stesso $M$ dà sempre la stessa firma $\sigma = M^d \bmod n$, senza nonce. DSS invece produce firme diverse ad ogni esecuzione (grazie a $k$).

#### Parte c — La firma $(a,0)$ rompe DSA (10 punti)

Supponiamo che per un messaggio $M$ si ottenga una firma con **seconda componente nulla**, cioè $(r,s) = (a, 0)$.

Per definizione $s = k^{-1}\big(H(M) + x\,r\big) \bmod q$. Imponendo $s = 0$ e ricordando che $k^{-1} \neq 0 \pmod q$ (q primo, $k\neq0$):
$$k^{-1}\big(H(M) + x\,r\big) \equiv 0 \pmod q \;\Longrightarrow\; H(M) + x\,r \equiv 0 \pmod q$$

Con $r = a$ (noto e, in DSA, $\neq 0$ e quindi invertibile mod $q$), si risolve per la **chiave privata**:
$$\boxed{x \equiv -H(M)\,a^{-1} \pmod q}$$

Tutto ciò che compare a destra è pubblico ($H(M)$, $a$, $q$): chiunque osservi la firma $(a,0)$ del messaggio $M$ **ricava la chiave privata $x$** e può falsificare qualunque firma. Il sistema è completamente rotto.

> ⚠️ Per questo lo standard DSA impone esplicitamente di **scartare e rigenerare** la firma se $r = 0$ **o** $s = 0$: il caso $s=0$ degenera nella rivelazione della chiave. È strettamente legato al caso $\delta = H(M)+xr = 0$.

---

## Pattern e osservazioni per l'esame

Appello della sessione estiva (giugno 2023).

- **D1 (30):** definizioni di sicurezza (incondizionata = Shannon/OTP; condizionata = computazionale) + cifrario affine. Saper risolvere il sistema $2\times2$ mod 26 per la chiave, e ricordare che **comporre due affini dà ancora un affine** (nessun guadagno di sicurezza — motivazione del 3DES).
- **D2 (20):** modalità DES, descrittiva.
- **D3 (25):** correttezza RSA (Eulero + CRT) e il trucco $p+q = n-\varphi(n)+1$ per fattorizzare da $\varphi(n)$ — mostra l'equivalenza $\varphi(n) \Leftrightarrow$ fattorizzazione.
- **D4 (25):** DSS standard + nonce + il caso $(a,0)$: imporre $s=0$ dà $x = -H(M)\,r^{-1}\bmod q$. Quesito ricorrente (cfr. lezione M5/UD4-L1 e appelli 2024).
