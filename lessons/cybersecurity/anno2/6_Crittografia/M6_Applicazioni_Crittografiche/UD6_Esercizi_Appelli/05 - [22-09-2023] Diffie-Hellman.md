# Diffie-Hellman — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 settembre 2023**, Domanda 4 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 settembre 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L8%20-%20Appello%2022%20settembre%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
