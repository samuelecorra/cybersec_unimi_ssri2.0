# Parametri RSA e cenni ECC — Esercizio d'esame

> 🗂️ **Fonte:** appello del **12 luglio 2023**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 12 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L5%20-%20Appello%2012%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
4. **Esponente pubblico:** $e$ con $1 < e < \varphi(n)$ e $\gcd(e, \varphi(n)) = 1$ (spesso $e = 65537$, primo e con pochi bit a 1 per cifratura veloce).
5. **Esponente privato:** $d \equiv e^{-1} \pmod{\varphi(n)}$, calcolato con l'algoritmo di Euclide esteso.
6. **Chiavi:** pubblica $(n, e)$, privata $(n, d)$. Si **distruggono** $p, q, \varphi(n)$ (o si custodiscono segreti): chi li conosce calcola $d$ e rompe il sistema.

> 📌 Cifratura $c = m^e \bmod n$, decifratura $m = c^d \bmod n$. La sicurezza richiede primi grandi e casuali e un buon padding (OAEP) per la sicurezza semantica.

#### Parte b — Cifratura asimmetrica su curve ellittiche (5 punti)

La crittografia a chiave pubblica si può istanziare su **curve ellittiche** $E(\mathbb{F}_p)$: il gruppo moltiplicativo $\mathbb{Z}_p^*$ è sostituito dal **gruppo additivo dei punti** della curva, e l'esponenziazione $g^x$ dalla **moltiplicazione scalare** $xG$ (con $G$ punto base). La sicurezza poggia sul **problema del logaritmo discreto ellittico (ECDLP)**: dato $G$ e $Q = xG$, trovare $x$.

Vantaggio principale: l'ECDLP è più difficile del DLP/della fattorizzazione a parità di dimensione, quindi servono **chiavi molto più corte** (≈ 256 bit EC ≈ 3072 bit RSA), con risparmio di banda e calcolo — ideale per dispositivi vincolati. Schemi tipici: ECDH (scambio chiave), ECDSA (firma), ECIES (cifratura ibrida).
