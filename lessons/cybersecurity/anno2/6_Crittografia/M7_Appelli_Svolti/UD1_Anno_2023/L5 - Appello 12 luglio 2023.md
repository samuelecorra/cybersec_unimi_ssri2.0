# Appello del 12 luglio 2023 — Soluzione completa

> 📌 **Informazioni appello:** 12/07/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 30 + 20 + 25).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — cifrario di Hill
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — strutture di Feistel
> - [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA
> - [`../../M6_Applicazioni_Crittografiche/UD1/L1 - Protocollo Diffie-Hellman.md`](../../M6_Applicazioni_Crittografiche/UD1/L1%20-%20Protocollo%20Diffie-Hellman.md) — Diffie-Hellman

---

## Domanda 1 — Cifrario di Hill (25 punti)

### Traccia originale

> **(25 punti) Cifrario di Hill.**
>
> a. (10 punti) Descrivere il cifrario di Hill, discutendo vantaggi e svantaggi.
>
> b. (15 punti) i. (5) Con la corrispondenza usuale alfabeto↔$\{0,\ldots,25\}$, sia $K = \begin{pmatrix} k_{11} & k_{12} \\ k_{21} & k_{22} \end{pmatrix}$ la chiave e "$m_1 m_2 m_3 m_4$" il plaintext: esprimere matematicamente il cifrato "$c_1 c_2 c_3 c_4$". ii. (10) Sia "GNBL" il chiaro e "TZMJ" il cifrato: determinare la chiave.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — Hill: cifratura, inversione, attacchi
- [`../../M3_Cifrature_Asimmetriche/UD2/L2 - Aritmetica modulare e numeri primi.md`](../../M3_Cifrature_Asimmetriche/UD2/L2%20-%20Aritmetica%20modulare%20e%20numeri%20primi.md) — inversi e determinanti mod 26

### Soluzione dettagliata

#### Parte a — Descrizione, vantaggi e svantaggi (10 punti)

Il **cifrario di Hill** (Lester Hill, 1929) è un cifrario a sostituzione **poligrammica** basato sull'algebra lineare mod 26. La chiave è una matrice $K$ di dimensione $d\times d$ invertibile modulo 26; il plaintext è diviso in blocchi $\mathbf{p}$ di $d$ lettere e cifrato come $\mathbf{c} = K\mathbf{p} \bmod 26$; si decifra con $\mathbf{p} = K^{-1}\mathbf{c} \bmod 26$.

**Vantaggi:** cifra $d$ lettere alla volta, quindi **nasconde le frequenze delle singole lettere** (per $d\ge2$ già appiattisce le statistiche monogramma); semplice ed efficiente (sole moltiplicazioni di matrici).

**Svantaggi:** è **lineare**, quindi totalmente vulnerabile al **known-plaintext**: con $d$ coppie (blocco chiaro, blocco cifrato) linearmente indipendenti si risolve $K = C\,P^{-1}\bmod 26$. La chiave deve essere invertibile mod 26 ($\operatorname{mcd}(\det K, 26)=1$), vincolo non banale. Nessuna confusione non lineare → inadatto come cifrario moderno.

#### Parte b.i — Espressione matematica del cifrato (5 punti)

Il plaintext "$m_1 m_2 m_3 m_4$" si divide in due blocchi da 2 lettere: $(m_1, m_2)$ e $(m_3, m_4)$. Applicando $\mathbf{c} = K\mathbf{p} \bmod 26$:

$$\begin{pmatrix} c_1 \\ c_2 \end{pmatrix} = \begin{pmatrix} k_{11} & k_{12} \\ k_{21} & k_{22} \end{pmatrix}\begin{pmatrix} m_1 \\ m_2 \end{pmatrix} \bmod 26, \qquad \begin{pmatrix} c_3 \\ c_4 \end{pmatrix} = \begin{pmatrix} k_{11} & k_{12} \\ k_{21} & k_{22} \end{pmatrix}\begin{pmatrix} m_3 \\ m_4 \end{pmatrix} \bmod 26$$

cioè, esplicitamente:
$$c_1 = (k_{11}m_1 + k_{12}m_2)\bmod 26, \quad c_2 = (k_{21}m_1 + k_{22}m_2)\bmod 26$$
$$c_3 = (k_{11}m_3 + k_{12}m_4)\bmod 26, \quad c_4 = (k_{21}m_3 + k_{22}m_4)\bmod 26$$

#### Parte b.ii — Determinare la chiave da GNBL → TZMJ (10 punti)

Codifica (A=0,…,Z=25): GNBL = G(6), N(13), B(1), L(11); TZMJ = T(19), Z(25), M(12), J(9).

Blocchi: plaintext $(6,13)$ e $(1,11)$; ciphertext $(19,25)$ e $(12,9)$. In forma matriciale, con le colonne uguali ai blocchi:
$$P = \begin{pmatrix} 6 & 1 \\ 13 & 11 \end{pmatrix}, \qquad C = \begin{pmatrix} 19 & 12 \\ 25 & 9 \end{pmatrix}, \qquad C = K P \bmod 26 \;\Rightarrow\; K = C\,P^{-1}\bmod 26$$

**Inversa di $P$ mod 26.** $\det P = 6\cdot11 - 1\cdot13 = 66 - 13 = 53 \equiv 1 \pmod{26}$ (invertibile, $\det^{-1}=1$):
$$P^{-1} = \det^{-1}\begin{pmatrix} 11 & -1 \\ -13 & 6 \end{pmatrix} = \begin{pmatrix} 11 & 25 \\ 13 & 6 \end{pmatrix} \pmod{26}$$

**Calcolo di $K = C P^{-1} \bmod 26$:**
$$K_{11} = 19\cdot11 + 12\cdot13 = 209+156 = 365 \equiv 1$$
$$K_{12} = 19\cdot25 + 12\cdot6 = 475+72 = 547 \equiv 1$$
$$K_{21} = 25\cdot11 + 9\cdot13 = 275+117 = 392 \equiv 2$$
$$K_{22} = 25\cdot25 + 9\cdot6 = 625+54 = 679 \equiv 3 \pmod{26}$$

$$\boxed{K = \begin{pmatrix} 1 & 1 \\ 2 & 3 \end{pmatrix}}$$

**Verifica:** $K\begin{pmatrix}6\\13\end{pmatrix} = \begin{pmatrix}19\\ 12+39=51\equiv25\end{pmatrix} = (19,25) = $ TZ ✓; $K\begin{pmatrix}1\\11\end{pmatrix} = \begin{pmatrix}12\\ 2+33=35\equiv9\end{pmatrix} = (12,9) = $ MJ ✓.

---

## Domanda 2 — Cifratura simmetrica: rete di Feistel a 2 round (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (10) Descrivere l'utilizzo delle strutture di Feistel, ad esempio in DES.
>
> b. (20) Rete di Feistel a 2 fasi, chiave 8 bit, blocco 16 bit. Chiave come numero decimale (0–255), input come due numeri decimali. Derivazione sottochiave $K_i = K + 75 i \pmod{256}$, $0\le i\le1$. Funzione $f(K_i, R_i) = 127\,(K_i + R_i) \pmod{256}$, con $R_i$ rappresentazione decimale degli 8 bit di destra. Cifrare il messaggio $(86, 83)$ con chiave $89$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — Feistel nel DES

### Soluzione dettagliata

#### Parte a — Strutture di Feistel in DES (10 punti)

Una **rete di Feistel** cifra un blocco diviso in due metà $(L, R)$ iterando, per ogni round $i$:
$$L_i = R_{i-1}, \qquad R_i = L_{i-1} \oplus f(R_{i-1}, K_i)$$
dove $f$ è la funzione di round e $K_i$ la sottochiave del round.

**Proprietà chiave:** la decifratura usa **la stessa struttura** percorrendo le sottochiavi in ordine inverso, **senza dover invertire $f$**. Questo perché ogni round è un'involuzione strutturale: ciò permette di usare funzioni $f$ arbitrarie (anche non invertibili), spostando tutta la sicurezza su confusione/diffusione di $f$ e sul numero di round.

**Nel DES:** blocco 64 bit (metà da 32), **16 round**, sottochiavi da 48 bit generate dal *key schedule*; la $f$ di DES combina espansione, XOR con la sottochiave, **S-box** (non linearità/confusione) e permutazione (diffusione). La struttura di Feistel rende cifratura e decifratura quasi identiche in hardware.

#### Parte b — Cifratura numerica di $(86,83)$ con $K=89$ (20 punti)

Blocco $16$ bit $= L\,\|\,R$ con $L, R$ di 8 bit. Plaintext: $L_0 = 86$, $R_0 = 83$. Chiave $K = 89$.

**Sottochiavi** ($K_i = K + 75i \bmod 256$): $\;K_0 = 89$, $\;K_1 = 89 + 75 = 164$.

**Round 1** ($i=0$, sottochiave $K_0=89$, $R_0 = 83$):
$$f(K_0, R_0) = 127\,(89 + 83) \bmod 256 = 127 \cdot 172 \bmod 256 = 21844 \bmod 256 = 84$$
$$L_1 = R_0 = 83$$
$$R_1 = L_0 \oplus f = 86 \oplus 84 = \texttt{01010110} \oplus \texttt{01010100} = \texttt{00000010} = 2$$

**Round 2** ($i=1$, sottochiave $K_1=164$, $R_1 = 2$):
$$f(K_1, R_1) = 127\,(164 + 2) \bmod 256 = 127 \cdot 166 \bmod 256 = 21082 \bmod 256 = 90$$
$$L_2 = R_1 = 2$$
$$R_2 = L_1 \oplus f = 83 \oplus 90 = \texttt{01010011} \oplus \texttt{01011010} = \texttt{00001001} = 9$$

**Ciphertext:** $C = (L_2, R_2) = (2, 9)$, ovvero in binario $\texttt{00000010}\,\texttt{00001001}$.

> 📌 **Riepilogo:**
>
> | Fase | $L$ | $R$ | $f(K_i,R)$ |
> |------|-----|-----|------------|
> | Plaintext | 86 | 83 | — |
> | Dopo Round 1 ($K_0=89$) | 83 | 2 | $127\cdot172 \bmod 256 = 84$ |
> | Dopo Round 2 ($K_1=164$) | 2 | 9 | $127\cdot166 \bmod 256 = 90$ |

> ⚠️ Sicurezza: $f$ è **affine** ($127(K+R)\bmod256$) → lineare, vulnerabile alla crittoanalisi lineare; chiave a 8 bit (256 valori) → forza bruta immediata; solo 2 round. Cifrario puramente didattico.

---

## Domanda 3 — Cifratura asimmetrica: parametri RSA e cenni ECC (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica.**
>
> a. (15) Discutere le fasi di generazione dei parametri in RSA.
>
> b. (5) Cenni sulla possibilità di applicare la cifratura asimmetrica su curve ellittiche.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — generazione chiavi RSA
- [`../../M3_Cifrature_Asimmetriche/UD4/L2 - Crittosistemi su curve ellittiche.md`](../../M3_Cifrature_Asimmetriche/UD4/L2%20-%20Crittosistemi%20su%20curve%20ellittiche.md) — ECC

### Soluzione dettagliata

#### Parte a — Generazione dei parametri RSA (15 punti)

1. **Scelta dei primi:** si generano due primi grandi e distinti $p, q$ (es. 1024 bit ciascuno per $n$ a 2048 bit), tramite generazione casuale + test di primalità probabilistici (Miller-Rabin). Devono essere imprevedibili e non troppo vicini (altrimenti la fattorizzazione di Fermat li trova).
2. **Modulo:** $n = pq$. È pubblico; la sua fattorizzazione è il segreto su cui poggia tutto.
3. **Funzione di Eulero:** $\varphi(n) = (p-1)(q-1)$ (segreta).
4. **Esponente pubblico:** $e$ con $1 < e < \varphi(n)$ e $\operatorname{mcd}(e, \varphi(n)) = 1$ (spesso $e = 65537$, primo e con pochi bit a 1 per cifratura veloce).
5. **Esponente privato:** $d \equiv e^{-1} \pmod{\varphi(n)}$, calcolato con l'algoritmo di Euclide esteso.
6. **Chiavi:** pubblica $(n, e)$, privata $(n, d)$. Si **distruggono** $p, q, \varphi(n)$ (o si custodiscono segreti): chi li conosce calcola $d$ e rompe il sistema.

> 📌 Cifratura $c = m^e \bmod n$, decifratura $m = c^d \bmod n$. La sicurezza richiede primi grandi e casuali e un buon padding (OAEP) per la sicurezza semantica.

#### Parte b — Cifratura asimmetrica su curve ellittiche (5 punti)

La crittografia a chiave pubblica si può istanziare su **curve ellittiche** $E(\mathbb{F}_p)$: il gruppo moltiplicativo $\mathbb{Z}_p^*$ è sostituito dal **gruppo additivo dei punti** della curva, e l'esponenziazione $g^x$ dalla **moltiplicazione scalare** $xG$ (con $G$ punto base). La sicurezza poggia sul **problema del logaritmo discreto ellittico (ECDLP)**: dato $G$ e $Q = xG$, trovare $x$.

Vantaggio principale: l'ECDLP è più difficile del DLP/della fattorizzazione a parità di dimensione, quindi servono **chiavi molto più corte** (≈ 256 bit EC ≈ 3072 bit RSA), con risparmio di banda e calcolo — ideale per dispositivi vincolati. Schemi tipici: ECDH (scambio chiave), ECDSA (firma), ECIES (cifratura ibrida).

---

## Domanda 4 — Diffie-Hellman (25 punti)

### Traccia originale

> **(25 punti) Diffie-Hellman.**
>
> a. (10) Sia $q=29$ il primo comune e $g=7$. i. Chiave pubblica di Alice se la privata è 5. ii. Chiave pubblica di Bob se la privata è 12. iii. Chiave segreta condivisa.
>
> b. (15) Mostrare un possibile attacco "man in the middle", con esempio numerico.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD1/L1 - Protocollo Diffie-Hellman.md`](../../M6_Applicazioni_Crittografiche/UD1/L1%20-%20Protocollo%20Diffie-Hellman.md) — protocollo DH
- [`../../M6_Applicazioni_Crittografiche/UD1/L2 - Scelta dei parametri.md`](../../M6_Applicazioni_Crittografiche/UD1/L2%20-%20Scelta%20dei%20parametri.md) — scelta di $g$ e $q$

### Soluzione dettagliata

#### Parte a — Calcolo numerico ($q=29$, $g=7$) (10 punti)

**i. Chiave pubblica di Alice** ($a = 5$): $A = g^a \bmod 29 = 7^5 \bmod 29$.
$7^2 = 49 \equiv 20$, $7^4 = 20^2 = 400 \equiv 400 - 13\cdot29 = 23$, $7^5 = 23\cdot7 = 161 \equiv 161 - 5\cdot29 = 16$. **$A = 16$**.

**ii. Chiave pubblica di Bob** ($b = 12$): $B = 7^{12} \bmod 29$.
$7^8 = (7^4)^2 = 23^2 = 529 \equiv 529 - 18\cdot29 = 7$, $7^{12} = 7^8\cdot7^4 = 7\cdot23 = 161 \equiv 16$. **$B = 16$**.

**iii. Chiave segreta condivisa:** $S = g^{ab} \bmod 29 = B^a = A^b$.
$S = 16^5 \bmod 29$. Poiché $16 = 7^5$, $16^5 = 7^{25}$; e $7^{60} = 7^{ab}$. Conviene calcolare l'ordine di 7: $7^7 = 7^4\cdot7^2\cdot7 = 23\cdot20\cdot7 \equiv 1 \pmod{29}$, quindi $\text{ord}(7) = 7$ e gli esponenti si riducono mod 7. Allora $S = 7^{ab} = 7^{60} = 7^{60 \bmod 7} = 7^{4} = 23$. **$S = 23$**.

> ⚠️ **Osservazione di sicurezza:** qui $g=7$ **non è una radice primitiva** mod 29: ha ordine 7, quindi genera un sottogruppo di soli 7 elementi $\{1,7,20,24,23,16,25\}$. La chiave condivisa può assumere solo 7 valori → un avversario la indovina con $\le 7$ tentativi (per questo $A = B = 16$ "per caso"). In DH reale $g$ va scelto generatore di un sottogruppo grande di ordine primo.

#### Parte b — Attacco man-in-the-middle (15 punti)

DH **non autenticato** è vulnerabile al MITM: Mallory si interpone e stabilisce **due** segreti distinti, uno con Alice e uno con Bob, decifrando e ricifrando tutto il traffico.

**Esempio numerico** (riusando $q=29$, $g=7$, e — per illustrare — un $g$ con ordine pieno per chiarezza; il meccanismo è identico):

1. Alice invia $A = g^a$ verso Bob; **Mallory** lo intercetta.
2. Mallory sceglie $m_1$ e invia ad Alice il proprio $M_1 = g^{m_1}$ (spacciandolo per "Bob"); sceglie $m_2$ e invia a Bob $M_2 = g^{m_2}$ (spacciandolo per "Alice").
3. Bob invia $B = g^b$; Mallory lo intercetta.
4. **Segreti stabiliti:** Alice calcola $S_A = M_1^{a} = g^{m_1 a}$; Mallory calcola lo stesso $S_A = A^{m_1}$. Bob calcola $S_B = M_2^{b} = g^{m_2 b}$; Mallory calcola lo stesso $S_B = B^{m_2}$.

Esempio: $a=5 \Rightarrow A = 16$; Mallory sceglie $m_1 = 3$, invia $M_1 = 7^3 = 24$ ad Alice. Alice crede sia Bob e calcola $S_A = M_1^{a} = 24^5 \bmod 29$; Mallory calcola $S_A = A^{m_1} = 16^3 \bmod 29$ (stesso valore). Specularmente con Bob. Mallory ora condivide $S_A$ con Alice e $S_B$ con Bob: legge e altera tutti i messaggi senza che nessuno se ne accorga.

> 📌 **Contromisura:** autenticare gli scambi (firme/certificati, es. station-to-station, o DH autenticato in TLS): Alice e Bob firmano i propri valori pubblici con chiavi la cui autenticità è garantita da una CA, impedendo a Mallory di sostituirli.

---

## Pattern e osservazioni per l'esame

Appello della sessione estiva (luglio 2023).

- **D1 (25):** Hill. Saper esprimere $\mathbf{c}=K\mathbf{p}\bmod26$ a blocchi e risolvere il **known-plaintext** $K = C P^{-1}$ (calcolo di $\det$ e inversa mod 26). Qui $K = \begin{pmatrix}1&1\\2&3\end{pmatrix}$.
- **D2 (30):** Feistel a 2 round. Ricordare lo schema ($L_i=R_{i-1}$, $R_i=L_{i-1}\oplus f$, $C=L_2R_2$ senza swap) e che la decifratura riusa la struttura senza invertire $f$. Conti su interi mod 256 + XOR a 8 bit → $C=(2,9)$.
- **D3 (20):** generazione parametri RSA (primi, $n$, $\varphi$, $e$, $d$) + cenni ECC (ECDLP, chiavi corte).
- **D4 (25):** Diffie-Hellman numerico + MITM. Attenzione: $g=7$ ha ordine 7 mod 29 (non primitiva) → sottogruppo piccolo, chiave condivisa $=23$. Il MITM si batte solo autenticando gli scambi.
