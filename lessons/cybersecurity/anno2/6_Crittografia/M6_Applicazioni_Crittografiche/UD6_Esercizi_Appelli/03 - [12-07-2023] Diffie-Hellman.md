# Diffie-Hellman — Esercizio d'esame

> 🗂️ **Fonte:** appello del **12 luglio 2023**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 12 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L5%20-%20Appello%2012%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
