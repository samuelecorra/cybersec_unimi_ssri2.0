# **Lezione 2: Crittosistemi su curve ellittiche**

### **1. Introduzione**

Le **curve ellittiche** non si limitano alla teoria matematica:
possono essere utilizzate per costruire **sistemi crittografici pratici**, analoghi a quelli già visti per RSA ed El-Gamal, ma con **livelli di sicurezza equivalenti a chiavi molto più corte**.

> 💡 **L'idea di fondo è una sostituzione.** In El-Gamal classico lavoravamo nel gruppo moltiplicativo $\mathbb{Z}_p^*$: gli elementi erano interi, l'operazione era la moltiplicazione modulare, e l'"elevazione a potenza" $g^k$ era la ripetizione di quella moltiplicazione. In ECC sostituiamo quel gruppo con il **gruppo dei punti di una curva ellittica**: gli elementi sono punti $(x,y)$, l'operazione è l'addizione di punti (corda-tangente), e l'"elevazione a potenza" $k \cdot G$ diventa la somma ripetuta — la moltiplicazione scalare. Tutto il resto (struttura della chiave pubblica, cifratura, decifratura, segreto condiviso) funziona esattamente come prima, con questa sostituzione.

Due esempi principali di applicazione:

1. **Analogo di Diffie–Hellman su curve ellittiche (ECDH)** — protocollo per scambio di chiave
2. **Crittosistema di El-Gamal su curve ellittiche (ECEG)** — cifratura asimmetrica completa

> ⚠️ **Messaggio come punto della curva.** Un dettaglio pratico non banale: per cifrare un messaggio $M$ con ECEG, $M$ deve essere codificato come un punto $P_M$ sulla curva — non è un intero arbitrario come in RSA/El-Gamal classico. La codifica (come trovare un punto con ascissa legata a $M$) richiede un procedimento apposito che qui si omette; l'importante è che il messaggio venga trattato come elemento del gruppo di punti.

---

### **2. Problemi computazionali di base**

La sicurezza della crittografia su curve ellittiche si fonda su due problemi ritenuti **computazionalmente difficili**:

#### **a) Logaritmo discreto su curve ellittiche (ECDLP)**

> 💡 **Asimmetria fondamentale.** Dato il punto generatore $G$ e uno scalare $x$, calcolare $Z = x \cdot G$ è **facile**: si usa l'algoritmo double-and-add in $O(\log x)$ addizioni di punti. Il problema inverso — dato $G$ e $Z$, trovare $x$ — non ha algoritmo sub-esponenziale noto per curve ben scelte. Il miglior algoritmo generale è Pollard's rho con complessità $O(\sqrt{|G|})$: esponenziale nella metà dei bit. Per curve a 256 bit, $\sqrt{2^{256}} = 2^{128}$ operazioni — computazionalmente proibitivo.

Dato un punto generatore $G$ di una curva $E_p(a,b)$ e un punto $Z$ appartenente alla curva, il problema consiste nel trovare l'intero $x$ tale che:

$$
x \cdot G = Z
$$

- **Facile:** calcolare $Z = x \cdot G$ conoscendo $x$ (double-and-add, $O(\log x)$)
- **Difficile:** ricavare $x$ conoscendo solo $G$ e $Z$ (Pollard's rho, $O(\sqrt{p})$)

---

#### **b) Problema di Diffie–Hellman su curve ellittiche (ECDHP)**

> 📌 **Stessa gerarchia del caso classico.** Come per CDH in $\mathbb{Z}_p^*$, il ECDHP sta sotto ECDLP: chi sa risolvere ECDLP sa automaticamente risolvere ECDHP (basta estrarre $x$ da $xG$, poi moltiplicare per $y$). La direzione opposta — se ECDHP fosse risolvibile senza risolvere ECDLP — è un problema aperto, come nel caso classico.

Dati tre punti $G$, $x \cdot G$, e $y \cdot G$ su $E_p(a,b)$, il problema consiste nel calcolare:

$$
x \cdot y \cdot G
$$

senza conoscere né $x$ né $y$. Questo calcolo è considerato **computazionalmente intrattabile** sotto l'ipotesi ECDHP.

---

### **3. Crittosistema di El-Gamal su curve ellittiche**

#### **Chiavi**

> 💡 **Analogia con El-Gamal classico.** Il punto generatore $G$ gioca il ruolo di $g$ (base pubblica). La chiave privata $x_A$ gioca il ruolo di $\alpha$ (esponente segreto). La chiave pubblica $P_A = x_A \cdot G$ gioca il ruolo di $\beta = g^\alpha$. Trovare $x_A$ da $P_A$ richiede di risolvere ECDLP — esattamente come trovare $\alpha$ da $\beta$ richiedeva DL in $\mathbb{Z}_p^*$.

Ogni utente dispone di:

| Tipo | Contenuto |
|---|---|
| **Chiave pubblica** | $E_p(a,b),\; G,\; P_A = x_A \cdot G$ |
| **Chiave privata** | $x_A$ |

I parametri della curva $(E_p(a,b), G)$ possono essere **comuni a più utenti** — analoghi ai parametri di dominio $(p, g)$ in El-Gamal classico.

---

### **4. Cifratura (Bob → Alice)**

> 💡 **L'idea del segreto di sessione.** Bob sceglie un nonce casuale $k$ e costruisce il segreto condiviso $k \cdot P_A = k \cdot (x_A G) = x_A \cdot (kG)$. Questo segreto può essere ricostruito da Alice (che conosce $x_A$ e riceve $kG = C_1$), ma non da un avversario che conosce solo $G$, $kG$ e $x_A G$ — per ottenerlo dovrebbe risolvere ECDHP. Il messaggio $P_M$ viene quindi mascherato sommando questo segreto.

Bob vuole inviare un messaggio $P_M$ (punto della curva) ad Alice:

1. Sceglie un intero casuale $k \in [1, p-1]$ — usato una sola volta.

2. Calcola:

$$
C_1 = k \cdot G
$$

$$
C_2 = P_M + k \cdot P_A
$$

3. Il **testo cifrato** è la coppia:

$$
C = (C_1,\; C_2)
$$

> 📌 **Ruolo di $C_1$ e $C_2$.** $C_1 = kG$ è la "busta chiusa" che trasporta il nonce $k$ — aprirla richiederebbe ECDLP. $C_2 = P_M + k P_A$ è il messaggio mascherato: senza il segreto condiviso $k P_A$, $C_2$ è un punto apparentemente casuale sulla curva.

> ⚠️ **Il valore $k$ deve essere segreto e usato una sola volta.** Se lo stesso $k$ viene riutilizzato per due messaggi $P_{M_1}$ e $P_{M_2}$, un avversario che osserva $(C_1, C_{2,1})$ e $(C_1, C_{2,2})$ può calcolare $C_{2,1} - C_{2,2} = P_{M_1} - P_{M_2}$, ottenendo la differenza tra i due plaintext.

---

### **5. Decifratura (Alice)**

> 💡 **L'idea.** Alice riceve $C_1 = kG$ e conosce $x_A$. Moltiplicando: $x_A \cdot C_1 = x_A \cdot (kG) = k \cdot (x_A G) = k P_A$. Ha ricostruito esattamente il segreto condiviso. Sottraendo da $C_2$ cancella il mascheramento e recupera $P_M$.

Quando Alice riceve $(C_1, C_2)$ e usa la propria chiave privata $x_A$:

1. Calcola il segreto condiviso: $x_A \cdot C_1$

2. Recupera il messaggio:

$$
P_M = C_2 - x_A \cdot C_1
$$

**Derivazione algebrica:**

$$
\begin{aligned}
C_2 - x_A \cdot C_1
&= (P_M + k \cdot P_A) - x_A \cdot (k \cdot G) \\
&= P_M + k \cdot (x_A \cdot G) - k \cdot (x_A \cdot G) \\
&= P_M
\end{aligned}
$$

> ✅ Il segreto condiviso $k P_A = x_A C_1$ si azzera esattamente: Alice recupera $P_M$.

---

### **6. Confronto con il sistema El-Gamal classico**

| Componente | El-Gamal classico ($\mathbb{Z}_p^*$) | El-Gamal su curve ellittiche |
|---|---|---|
| Gruppo | $(\mathbb{Z}_p^*, \cdot)$ moltiplicativo | $(E_p(a,b), +)$ additivo su curva |
| Operazione base | Moltiplicazione modulare | Addizione di punti (corda-tangente) |
| "Esponenziazione" | $g^k \bmod p$ (square-and-multiply) | $k \cdot G$ (double-and-add) |
| Chiave privata | $\alpha \in \mathbb{Z}_{p-1}$ | $x_A \in [1, p-1]$ |
| Chiave pubblica | $\beta = g^\alpha \bmod p$ | $P_A = x_A \cdot G$ (punto) |
| Segreto condiviso | $g^{\alpha k} = \beta^k = y_1^\alpha$ | $k P_A = x_A C_1$ (punto) |
| Sicurezza | DL in $\mathbb{Z}_p^*$ → NFS sub-esponenziale | ECDLP → Pollard's rho esponenziale |
| Dimensione crittotesto | $2 \times$ plaintext (in bit) | $2 \times$ plaintext (in punti) |

> ⚠️ **Correzione rispetto alle note originali.** El-Gamal classico è basato sul **logaritmo discreto** in $\mathbb{Z}_p^*$, **non** sulla fattorizzazione (che è il problema di RSA). I due problemi sono distinti e la confusione è un errore concettuale frequente all'esame.

---

### **7. Lunghezza delle chiavi**

> 📌 **Perché ECC permette chiavi più corte.** Il miglior attacco noto a ECDLP (Pollard's rho) ha complessità $O(\sqrt{p})$ — esponenziale nella metà dei bit. Il miglior attacco noto a DL in $\mathbb{Z}_p^*$ (NFS) ha complessità sub-esponenziale $L_p[1/3, 1.923]$. Questo significa che per ottenere la stessa resistenza agli attacchi, il modulo $p$ di RSA/El-Gamal deve essere molto più grande della dimensione della curva ECC.

**Tabella aggiornata (NIST SP 800-57, 2024):**

| Sicurezza (bit) | ECC | RSA / El-Gamal | Chiave simmetrica equivalente |
|---|---|---|---|
| 80 | 160 bit | 1024 bit *(deprecato)* | 80 bit (3DES) |
| 112 | 224 bit | 2048 bit | 112 bit |
| 128 | 256 bit | 3072 bit | 128 bit (AES-128) |
| 192 | 384 bit | 7680 bit | 192 bit (AES-192) |
| 256 | 512 bit | 15360 bit | 256 bit (AES-256) |

> ⚠️ **Valori storici vs. moderni.** Le note originali riportavano dati RSA Labs degli anni 2000 (RSA-1024 come "commerciale", 768 come "personale"). RSA-768 è stato fattorizzato nel 2009; RSA-1024 è considerato deprecato dal NIST dal 2013. Oggi il minimo raccomandato è RSA-2048 / ECC-224, e per applicazioni con sicurezza a lungo termine si usano RSA-3072 / ECC-256 o superiori.

---

### **8. Brevetti e implementazioni**

Le principali aziende che hanno storicamente brevettato o ottimizzato implementazioni ECC includono:

- **Certicom** → moltiplicazione in basi normali (acquisita da BlackBerry nel 2009)
- **Apple Computer** → implementazioni efficienti su $GF(p)$ e $GF(2^m)$
- **Cylink** → moltiplicazione in basi normali

> 💡 **Situazione attuale.** La maggior parte dei brevetti ECC fondamentali (in particolare quelli Certicom/BlackBerry) è scaduta tra il 2017 e il 2021, rendendo ECC liberamente implementabile. Oggi le curve standardizzate più diffuse sono **P-256** (NIST), **Curve25519** (Bernstein, 2006), e **secp256k1** (usata in Bitcoin). Queste curve hanno parametri scelti in modo trasparente e auditabile, a differenza di alcune curve più vecchie su cui vi erano dubbi su potenziali backdoor nei parametri.

---

### **9. Sintesi finale**

> ✅ **Schema rapido per l'esame.**

**Corrispondenza con El-Gamal classico:**

| El-Gamal $\mathbb{Z}_p^*$ | El-Gamal su EC |
|---|---|
| $g^k \bmod p$ | $k \cdot G$ |
| $\beta = g^\alpha$ | $P_A = x_A \cdot G$ |
| $y_1 = g^k$ | $C_1 = k \cdot G$ |
| $y_2 = M \cdot \beta^k$ | $C_2 = P_M + k \cdot P_A$ |
| $z = y_1^\alpha = g^{k\alpha}$ | $x_A \cdot C_1 = k \cdot P_A$ |

**Tre punti chiave:**

1. **Stessa struttura logica di El-Gamal**, con la sostituzione gruppo moltiplicativo → gruppo di punti su curva. Chiave privata = scalare segreto, chiave pubblica = punto.

2. **Sicurezza più forte a chiavi più corte**: ECDLP richiede $O(\sqrt{p})$ con il miglior algoritmo noto (esponenziale), contro $O(L_p[1/3])$ per DL in $\mathbb{Z}_p^*$ (sub-esponenziale). Risultato: ECC-256 ≈ RSA-3072 per sicurezza.

3. **Post-quantum:** ECC è vulnerabile a Shor's algorithm come RSA e El-Gamal. NIST ha standardizzato nel 2024 algoritmi post-quantum (CRYSTALS-Kyber, Dilithium, SPHINCS+) che non si basano su DL o fattorizzazione.
