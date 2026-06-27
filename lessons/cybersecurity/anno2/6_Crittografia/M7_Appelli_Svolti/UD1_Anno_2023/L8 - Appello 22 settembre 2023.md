# Appello del 22 settembre 2023 — Soluzione completa

> 📌 **Informazioni appello:** 22/09/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (20 + 40 + 20 + 20).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — cifrario di Hill
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — meet-in-the-middle
> - [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X
> - [`../../M6_Applicazioni_Crittografiche/UD1/L1 - Protocollo Diffie-Hellman.md`](../../M6_Applicazioni_Crittografiche/UD1/L1%20-%20Protocollo%20Diffie-Hellman.md) — Diffie-Hellman

---

## Domanda 1 — Cifratura classica: cifrario di Hill (20 punti)

### Traccia originale

> **(20 punti) Cifratura classica.**
>
> a. (10) Discutere le caratteristiche e i possibili attacchi al cifrario di Hill.
>
> b. (10) Si consideri la matrice $\begin{pmatrix} 1 & 3 \\ 1 & 4 \end{pmatrix}$. Se la matrice è valida (provarlo) cifrare e decifrare la parola "ciao".

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md`](../../M1_Crittografia_Classica/UD3/L2%20-%20Il%20Cifrario%20di%20Hill.md) — Hill: cifratura, inversione, attacchi
- [`../../M3_Cifrature_Asimmetriche/UD2/L2 - Aritmetica modulare e numeri primi.md`](../../M3_Cifrature_Asimmetriche/UD2/L2%20-%20Aritmetica%20modulare%20e%20numeri%20primi.md) — inversi mod 26

### Soluzione dettagliata

#### Parte a — Caratteristiche e attacchi al Hill (10 punti)

Il **cifrario di Hill** cifra blocchi di $d$ lettere con una matrice chiave $K$ invertibile mod 26: $\mathbf{c} = K\mathbf{p}\bmod26$, $\mathbf{p} = K^{-1}\mathbf{c}\bmod26$. Requisito di validità: $\operatorname{mcd}(\det K, 26) = 1$.

**Caratteristiche/vantaggi:** poligrammico → nasconde le frequenze delle singole lettere (per $d\ge2$); semplice ed efficiente.

**Attacchi/svantaggi:** è **lineare**, quindi cade sotto **known-plaintext**: con $d$ coppie (blocco chiaro, cifrato) linearmente indipendenti si risolve $K = C P^{-1}\bmod26$ (per $d=2$ bastano 2 coppie). In **chosen-plaintext** si scelgono i vettori della base standard e si leggono direttamente le colonne di $K$. Resiste solo parzialmente al ciphertext-only (analisi statistica dei blocchi). Nessuna non-linearità → inadatto a usi moderni.

#### Parte b — Validità, cifratura e decifratura di "ciao" (10 punti)

**Validità:** $\det K = 1\cdot4 - 3\cdot1 = 1$. Poiché $\operatorname{mcd}(1, 26) = 1$, la matrice è **valida** (invertibile mod 26), con $\det^{-1} = 1$.

**Cifratura** di "ciao" (c=2, i=8, a=0, o=14), blocchi $(2,8)$ e $(0,14)$:
$$K\begin{pmatrix}2\\8\end{pmatrix} = \begin{pmatrix}1\cdot2+3\cdot8\\ 1\cdot2+4\cdot8\end{pmatrix} = \begin{pmatrix}26\\34\end{pmatrix} \equiv \begin{pmatrix}0\\8\end{pmatrix} = (\text{A},\text{I})$$
$$K\begin{pmatrix}0\\14\end{pmatrix} = \begin{pmatrix}1\cdot0+3\cdot14\\ 1\cdot0+4\cdot14\end{pmatrix} = \begin{pmatrix}42\\56\end{pmatrix} \equiv \begin{pmatrix}16\\4\end{pmatrix} = (\text{Q},\text{E})$$

**Cifrato:** "ciao" → **AIQE**.

**Inversa di $K$:** $K^{-1} = \det^{-1}\begin{pmatrix}4 & -3 \\ -1 & 1\end{pmatrix} = \begin{pmatrix}4 & 23 \\ 25 & 1\end{pmatrix} \pmod{26}$.

**Decifratura** di AIQE, blocchi $(0,8)$ e $(16,4)$:
$$K^{-1}\begin{pmatrix}0\\8\end{pmatrix} = \begin{pmatrix}4\cdot0+23\cdot8\\ 25\cdot0+1\cdot8\end{pmatrix} = \begin{pmatrix}184\\8\end{pmatrix} \equiv \begin{pmatrix}2\\8\end{pmatrix} = (\text{C},\text{I})$$
$$K^{-1}\begin{pmatrix}16\\4\end{pmatrix} = \begin{pmatrix}4\cdot16+23\cdot4\\ 25\cdot16+1\cdot4\end{pmatrix} = \begin{pmatrix}156\\404\end{pmatrix} \equiv \begin{pmatrix}0\\14\end{pmatrix} = (\text{A},\text{O})$$

**Chiaro recuperato:** **ciao** ✓.

---

## Domanda 2 — Cifratura simmetrica: meet-in-the-middle e AES-X (40 punti)

### Traccia originale

> **(40 punti) Cifratura simmetrica.**
>
> a. (10) Descrivere l'attacco meet-in-the-middle per il DES doppio.
>
> b. (30) AES-X è una versione modificata di AES con XOR: con $k_1, k_2$ chiavi AES di 128 bit, $\text{AESX}_{k_1,k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2$. i. (10) Descrivere le caratteristiche di AES-X. ii. (20) Discutere la sicurezza rispetto a un attacco known-plaintext, sia su AES standard sia su AES-X.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — Double DES e meet-in-the-middle
- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X e key whitening

### Soluzione dettagliata

#### Parte a — Meet-in-the-middle sul Double DES (10 punti)

Il **Double DES** cifra $C = E_{k_2}(E_{k_1}(M))$ con due chiavi da 56 bit (112 bit totali). Ci si aspetterebbe sicurezza $2^{112}$, ma l'attacco **meet-in-the-middle** la riduce a $\sim 2^{57}$.

Idea: data una coppia nota $(M, C)$, vale $E_{k_1}(M) = D_{k_2}(C)$ (il "valore intermedio"). L'avversario:
1. calcola $E_{k_1}(M)$ per **tutte** le $2^{56}$ chiavi $k_1$ e le memorizza in tabella (valore → $k_1$);
2. calcola $D_{k_2}(C)$ per **tutte** le $2^{56}$ chiavi $k_2$ e cerca una **collisione** in tabella.

Ogni collisione $E_{k_1}(M) = D_{k_2}(C)$ fornisce una candidata $(k_1, k_2)$; con una o due coppie aggiuntive si elimina ogni falso positivo. Costo: $\sim 2^{56}$ cifrature + $2^{56}$ decifrature ($\approx 2^{57}$) e $\sim 2^{56}$ memoria, invece di $2^{112}$. È il motivo per cui il Double DES è inutile e si è passati al **Triple DES**.

#### Parte b.i — Caratteristiche di AES-X (10 punti)

AES-X applica un **key whitening** in **uscita**: cifra con AES sotto $k_1$ e poi XORa l'output con una seconda chiave $k_2$:
$$\text{AESX}_{k_1,k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2$$
La chiave totale è di $128 + 128 = 256$ bit. La decifratura è $\text{AES}^{-1}_{k_1}(c \oplus k_2)$. È analogo alla costruzione **DESX**, ma con whitening **solo in uscita** (DESX whitening sia in ingresso sia in uscita). L'obiettivo *apparente* è aumentare la sicurezza alla forza bruta portandola da $2^{128}$ a $2^{256}$.

#### Parte b.ii — Sicurezza known-plaintext: AES vs AES-X (20 punti)

**AES standard.** Sotto known-plaintext, l'unico attacco generico è la **forza bruta sulla chiave** $k_1$: provare le $2^{128}$ chiavi finché $\text{AES}_{k_1}(m) = c$ su una coppia nota (con una seconda coppia si conferma). Sicurezza $\approx 2^{128}$, intrattabile.

**AES-X.** L'aggiunta di $k_2$ **non aumenta** la sicurezza contro il known-plaintext, perché il whitening è solo in uscita ed è **lineare**. Con **due** coppie note $(m, c)$ e $(m', c')$:
$$c \oplus c' = (\text{AES}_{k_1}(m)\oplus k_2) \oplus (\text{AES}_{k_1}(m')\oplus k_2) = \text{AES}_{k_1}(m) \oplus \text{AES}_{k_1}(m')$$
il termine $k_2$ **si cancella**. L'avversario fa quindi forza bruta **solo su $k_1$** ($2^{128}$): per ogni candidata verifica se $\text{AES}_{k_1}(m)\oplus\text{AES}_{k_1}(m') = c\oplus c'$. Trovata $k_1$, ricava $k_2 = c \oplus \text{AES}_{k_1}(m)$.

**Conclusione:** la sicurezza KPA di AES-X resta $\approx 2^{128}$ (come AES), **non** $2^{256}$: il whitening in **sola uscita** è trasparente a un KPA con $\ge 2$ coppie. Per ottenere un guadagno reale (come in DESX) serve whitening **anche in ingresso** ($\text{AES}_{k_1}(m \oplus k_0) \oplus k_2$), che impedisce la cancellazione lineare di $k_2$.

> ⚠️ Lezione: aggiungere uno XOR con una chiave **lineare** in un solo punto non aggiunge sicurezza contro avversari che possono combinare più osservazioni. La non-linearità e il posizionamento del whitening (ingresso *e* uscita) sono essenziali.

---

## Domanda 3 — Cifratura asimmetrica RSA (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica RSA.**
>
> a. (10) Discutere l'equivalenza o meno tra il problema RSA e la fattorizzazione.
>
> b. (10) Discutere i metodi per ottimizzare il calcolo esponenziale nella fase di cifratura.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L5 - Sicurezza di RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L5%20-%20Sicurezza%20di%20RSA.md) — RSA vs fattorizzazione
- [`../../M3_Cifrature_Asimmetriche/UD2/L4_1 - Computazioni in RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L4_1%20-%20Computazioni%20in%20RSA.md) — esponenziazione modulare efficiente

### Soluzione dettagliata

#### Parte a — Problema RSA vs fattorizzazione (10 punti)

Il **problema RSA**: dati $(n, e, c)$ con $c = m^e \bmod n$, trovare $m$ (cioè calcolare radici $e$-esime mod $n$). La **fattorizzazione**: dato $n=pq$, trovare $p, q$.

- **Fattorizzazione $\Rightarrow$ RSA:** se so fattorizzare $n$, calcolo $\varphi(n)=(p-1)(q-1)$, quindi $d = e^{-1}\bmod\varphi(n)$ e $m = c^d$. Quindi **rompere la fattorizzazione rompe RSA**. (E conoscere $\varphi(n)$ equivale a fattorizzare, via $p+q = n-\varphi(n)+1$.)
- **RSA $\Rightarrow$ fattorizzazione?** **Non è dimostrato.** Non si conosce una riduzione che, dato un oracolo che risolve il problema RSA per *ogni* $e$, fattorizzi $n$. È quindi *concepibile* (anche se ritenuto improbabile) che esista un metodo per estrarre radici $e$-esime senza fattorizzare. 

**Conclusione:** la fattorizzazione è **almeno tanto difficile** quanto il problema RSA (fattorizzare basta a rompere RSA), ma **l'equivalenza nelle due direzioni non è provata**: rompere RSA *potrebbe* essere più facile che fattorizzare. In pratica i migliori attacchi noti passano comunque dalla fattorizzazione, quindi la sicurezza si basa su di essa con chiavi adeguate (≥ 2048 bit).

#### Parte b — Ottimizzazione dell'esponenziazione (10 punti)

La cifratura $c = m^e \bmod n$ richiede esponenziazione modulare con esponenti/moduli grandi. Tecniche principali:

1. **Square-and-multiply (esponenziazione binaria):** invece di $e$ moltiplicazioni, si scorre la rappresentazione binaria di $e$: si eleva al quadrato ad ogni bit e si moltiplica per $m$ quando il bit è 1. Costo $O(\log e)$ moltiplicazioni modulari invece di $O(e)$.
2. **Esponente pubblico piccolo:** scegliere $e = 65537 = 2^{16}+1$ (primo, solo due bit a 1) rende la **cifratura** velocissima (17 moltiplicazioni). Non si sceglie $e$ piccolissimo come 3 senza padding per evitare attacchi.
3. **CRT per la decifratura:** la **decifratura** $m = c^d \bmod n$ (con $d$ grande) si accelera col **Teorema Cinese del Resto**: si calcolano $m_p = c^{d \bmod (p-1)} \bmod p$ e $m_q = c^{d \bmod (q-1)} \bmod q$ e si ricombinano. Lavorare modulo $p$ e $q$ (metà delle cifre) dà uno speedup teorico $\sim 4\times$.
4. **Riduzione di Montgomery:** evita le divisioni costose nelle riduzioni modulari ripetute, rendendo efficienti le moltiplicazioni mod $n$.

> 📌 Riassunto: $e$ piccolo + square-and-multiply per cifrare; CRT + Montgomery per decifrare.

---

## Domanda 4 — Diffie-Hellman (20 punti)

### Traccia originale

> **(20 punti) Diffie-Hellman.**
>
> a. (10) Sia $q=19$ il primo comune e $g=10$. i. Dimostrare che 10 è un generatore (radice primitiva) di $\mathbb{Z}_{19}$. ii. Se Alice ha chiave pubblica 9, qual è la privata? iii. Se Bob ha chiave privata 6, qual è la pubblica? iv. Chiave segreta condivisa.
>
> b. (10) Mostrare un possibile attacco "man in the middle", con esempio numerico.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD1/L1 - Protocollo Diffie-Hellman.md`](../../M6_Applicazioni_Crittografiche/UD1/L1%20-%20Protocollo%20Diffie-Hellman.md) — protocollo DH
- [`../../M6_Applicazioni_Crittografiche/UD5_Approfondimenti_Esame/L1 - Verifica numerica radice primitiva.md`](../../M6_Applicazioni_Crittografiche/UD5_Approfondimenti_Esame/L1%20-%20Verifica%20numerica%20radice%20primitiva.md) — verifica radice primitiva

### Soluzione dettagliata

#### Parte a — Calcolo numerico ($q=19$, $g=10$) (10 punti)

**i. 10 è radice primitiva mod 19.** L'ordine deve essere $\varphi(19) = 18$. Basta verificare che $10^{18/p} \neq 1$ per i primi $p \mid 18$ (cioè $p = 2, 3$):
$$10^{9} \bmod 19: \quad 10^2 = 100 \equiv 5,\; 10^3 = 50 \equiv 12,\; 10^6 = 12^2 = 144 \equiv 11,\; 10^9 = 10^6\cdot10^3 = 11\cdot12 = 132 \equiv 18 \equiv -1$$
$$10^{6} \equiv 11$$
Poiché $10^{9} \equiv -1 \neq 1$ e $10^{6} \equiv 11 \neq 1$, l'ordine non divide né 9 né 6, quindi è esattamente 18: **10 è radice primitiva** (e $10^{18} = (10^9)^2 = (-1)^2 = 1$ ✓). Le potenze generano tutto $\mathbb{Z}_{19}^*$.

**ii. Chiave privata di Alice** (pubblica $A = 9$): cercare $a$ con $10^a \equiv 9 \pmod{19}$ (logaritmo discreto). Elenco delle potenze:
$$10^1{=}10,\,10^2{=}5,\,10^3{=}12,\,10^4{=}6,\,10^5{=}3,\,10^6{=}11,\,10^7{=}15,\,10^8{=}17,\,10^9{=}18,\,10^{10}{=}9$$
Quindi $a = 10$. **Privata di Alice: $a = 10$.**

**iii. Chiave pubblica di Bob** (privata $b = 6$): $B = 10^6 \equiv 11 \pmod{19}$. **Pubblica di Bob: $B = 11$.**

**iv. Chiave segreta condivisa:** $S = g^{ab} = B^a = A^b \bmod 19$.
$$S = B^a = 11^{10} \bmod 19: \quad 11^2 = 121 \equiv 7,\; 11^4 = 7^2 = 49 \equiv 11,\; 11^5 = 11^4\cdot11 = 121 \equiv 7,\; 11^{10} = (11^5)^2 = 7^2 = 49 \equiv 11$$
Verifica con $A^b = 9^6$: $9^2 = 81 \equiv 5,\; 9^3 = 45 \equiv 7,\; 9^6 = 7^2 = 49 \equiv 11$ ✓.
**Chiave condivisa: $S = 11$.**

#### Parte b — Attacco man-in-the-middle (10 punti)

DH non autenticato è vulnerabile: **Mallory** si interpone e stabilisce due segreti distinti, uno con Alice e uno con Bob.

**Esempio numerico** ($q=19$, $g=10$): Alice ha $a=10 \Rightarrow A = 9$; Bob ha $b=6 \Rightarrow B = 11$.

1. Alice invia $A=9$; Mallory lo intercetta e **non** lo inoltra.
2. Mallory sceglie $m=4$, calcola $M = 10^4 = 6$ e lo invia **sia** ad Alice (fingendosi Bob) **sia** a Bob (fingendosi Alice).
3. **Segreti:** Alice calcola $S_A = M^{a} = 6^{10}\bmod19$; Mallory calcola lo stesso $S_A = A^{m} = 9^{4}\bmod19$. ($9^2=5$, $9^4=25\equiv6$; $6^{10}$: $6^2=36\equiv17$, $6^4=17^2=289\equiv4$, $6^5=24\equiv5$, $6^{10}=5^2=25\equiv6$ → $S_A = 6$, coincide con $9^4 = 6$.) 
4. Specularmente, Bob calcola $S_B = M^{b} = 6^{6}\bmod19$ e Mallory $S_B = B^{m} = 11^{4}\bmod19$ (entrambi $= 11^4 = 11$; $6^6 = 6^4\cdot6^2 = 4\cdot17 = 68 \equiv 11$ ✓).

Ora Mallory condivide $S_A = 6$ con Alice e $S_B = 11$ con Bob: decifra, legge/altera e ricifra ogni messaggio, restando invisibile.

> 📌 **Contromisura:** **autenticare** i valori pubblici (firme/certificati di una CA, protocolli tipo Station-to-Station o DH autenticato in TLS): Mallory non può sostituire $A$ e $B$ senza una firma valida.

---

## Pattern e osservazioni per l'esame

Appello della sessione autunnale (settembre 2023).

- **D1 (20):** Hill — provare la validità ($\operatorname{mcd}(\det,26)=1$), poi cifrare/decifrare a blocchi. Qui $\det=1$, "ciao" → **AIQE**.
- **D2 (40):** meet-in-the-middle sul 2DES ($2^{112}\to2^{57}$) e **AES-X**: il whitening in **sola uscita** è lineare e si cancella ($c\oplus c' = \text{AES}_{k_1}(m)\oplus\text{AES}_{k_1}(m')$), quindi KPA resta $2^{128}$ come AES (cfr. 19/09/2025).
- **D3 (20):** RSA vs fattorizzazione (fattorizzazione ⇒ RSA; il viceversa **non** è provato) + ottimizzazioni (square-and-multiply, $e=65537$, CRT, Montgomery).
- **D4 (20):** Diffie-Hellman — verifica radice primitiva (test su $10^{9},10^{6}$), piccolo logaritmo discreto per la privata di Alice ($a=10$), chiave condivisa $S=11$, e MITM battuto solo con autenticazione.
