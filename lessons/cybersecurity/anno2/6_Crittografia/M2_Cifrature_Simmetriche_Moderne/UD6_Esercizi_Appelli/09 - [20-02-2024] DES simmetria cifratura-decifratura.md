# DES simmetria cifratura-decifratura — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 febbraio 2024**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 febbraio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L2%20-%20Appello%2020%20febbraio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — DES: simmetria cifratura/decifratura (20 punti)

### Traccia originale

> **(20 punti) DES.**
>
> a. (20) Nell'ambito del cifrario DES, provare che la decifratura può essere effettuata applicando l'algoritmo di cifratura al testo cifrato con le chiavi schedulate in ordine inverso.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — struttura Feistel del DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — proprietà di invertibilità dei cifrari di Feistel

### Soluzione dettagliata

#### Struttura del DES e notazione

Il DES è un cifrario di Feistel con 16 round. Notazione:
- $IP$: permutazione iniziale; $IP^{-1}$: permutazione finale (inversa di $IP$)
- $(L_i, R_i)$: metà sinistra e destra dopo il round $i$ ($i=0,\ldots,16$)
- $F$: funzione di round (espansione + XOR con sottochiave + S-box + permutazione $P$)
- $K_i$ ($i = 1, \ldots, 16$): sottochiavi schedulate dalla chiave principale

**Equazioni di cifratura (round $i$, $i=1,\ldots,16$):**

$$L_i = R_{i-1}$$
$$R_i = L_{i-1} \oplus F(R_{i-1}, K_i)$$

**Ciphertext:** $C = IP^{-1}(R_{16} \| L_{16})$ (le due metà vengono scambiate prima di $IP^{-1}$).

> ⚠️ Lo **swap finale** (scambio di $L_{16}$ e $R_{16}$ prima di $IP^{-1}$) è cruciale per la dimostrazione. Senza di esso la simmetria non funzionerebbe.

#### Dimostrazione della simmetria (proprietà DES)

**Enunciato:** applicare il DES al ciphertext $C$ con sottochiavi nell'ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce il plaintext $M$.

**Passo 1 — Punto di partenza della decifratura:**

Il ciphertext è $C = IP^{-1}(R_{16} \| L_{16})$. Applicando $IP$ al ciphertext:

$$IP(C) = R_{16} \| L_{16}$$

Definiamo lo stato iniziale della decifratura come $(L_0^D, R_0^D) = (R_{16}, L_{16})$ (notare lo swap: ciò che era la metà destra nella cifratura diventa la sinistra nella decifratura, e viceversa).

**Passo 2 — Primo round di decifratura con $K_{16}$:**

$$L_1^D = R_0^D = L_{16}$$

$$R_1^D = L_0^D \oplus F(R_0^D, K_{16}) = R_{16} \oplus F(L_{16}, K_{16})$$

Dall'ultimo round di cifratura sappiamo:
$$R_{16} = L_{15} \oplus F(R_{15}, K_{16}) \quad \text{e} \quad L_{16} = R_{15}$$

Sostituendo:
$$R_1^D = [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(R_{15}, K_{16}) = L_{15} \oplus [F(R_{15}, K_{16}) \oplus F(R_{15}, K_{16})] = L_{15}$$

Quindi: $(L_1^D, R_1^D) = (L_{16}, L_{15}) = (R_{15}, L_{15})$.

**Passo 3 — Induzione:** dimostriamo che dopo il $j$-esimo round di decifratura (con chiave $K_{16-j+1}$):

$$(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$$

*Caso base $j=1$:* verificato al Passo 2: $(L_1^D, R_1^D) = (R_{15}, L_{15})$ ✓

*Passo induttivo:* supponiamo $(L_{j-1}^D, R_{j-1}^D) = (R_{16-j+1}, L_{16-j+1})$. Allora:

$$L_j^D = R_{j-1}^D = L_{16-j+1} = R_{16-j}$$

$$R_j^D = L_{j-1}^D \oplus F(R_{j-1}^D, K_{16-j+1}) = R_{16-j+1} \oplus F(L_{16-j+1}, K_{16-j+1})$$

Dall'equazione di cifratura del round $(16-j+1)$: $R_{16-j+1} = L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})$ e $L_{16-j+1} = R_{16-j}$, quindi:

$$R_j^D = [L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})] \oplus F(R_{16-j}, K_{16-j+1}) = L_{16-j}$$

Quindi $(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$ ✓ L'induzione regge.

**Passo 4 — Stato dopo 16 round di decifratura ($j=16$):**

$$(L_{16}^D, R_{16}^D) = (R_0, L_0)$$

**Passo 5 — Swap finale e applicazione di $IP^{-1}$:**

Prima di applicare $IP^{-1}$, il DES esegue lo swap: l'output del 16° round viene scambiato, producendo $R_{16}^D \| L_{16}^D = L_0 \| R_0$.

Applicando $IP^{-1}$:

$$IP^{-1}(L_0 \| R_0) = IP^{-1}(IP(M)) = M$$

**Conclusione:** la decifratura DES con chiavi in ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce esattamente il plaintext $M$ originale. $\blacksquare$

> 📌 Questa proprietà è intrinseca alla struttura di Feistel e vale per qualsiasi funzione $F$ (anche non invertibile). L'inversione è garantita dalla struttura, non dalla funzione di round.
