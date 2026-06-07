# DES simmetria cifratura-decifratura — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 gennaio 2025**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 gennaio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L1%20-%20Appello%2020%20gennaio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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

Il DES è un cifrario di Feistel con 16 round. Denotiamo:
- $IP$: permutazione iniziale; $IP^{-1}$: permutazione finale (inversa di $IP$)
- $(L_i, R_i)$: metà sinistra e destra dopo il round $i$
- $F$: funzione di round (le S-box + permutazione $P$)
- $K_i$ ($i = 1, \ldots, 16$): sottochiavi schedulate dalla chiave principale

**Cifratura:** Dato il plaintext $M$, dopo $IP$ si ottiene $(L_0, R_0)$. Per ogni round $i = 1, \ldots, 16$:

$$L_i = R_{i-1}$$

$$R_i = L_{i-1} \oplus F(R_{i-1}, K_i)$$

Il ciphertext è $C = IP^{-1}(R_{16} \| L_{16})$ (notare lo swap finale: le due metà vengono scambiate prima di $IP^{-1}$).

#### Dimostrazione della simmetria

Vogliamo mostrare che applicare il DES a $C$ con le chiavi nell'ordine inverso $K_{16}, K_{15}, \ldots, K_1$ produce $M$.

**Passo 1:** Partiamo dal ciphertext $C = IP^{-1}(R_{16} \| L_{16})$. Applichiamo $IP$:

$$IP(C) = R_{16} \| L_{16} = (L_{16}', R_{16}') \quad \text{dove } L_{16}' = R_{16},\ R_{16}' = L_{16}$$

Chiamiamo questa coppia il punto di partenza della decifratura: $(L_0^D, R_0^D) = (R_{16}, L_{16})$.

**Passo 2:** Eseguiamo il primo round di decifratura con chiave $K_{16}$:

$$L_1^D = R_0^D = L_{16}$$

$$R_1^D = L_0^D \oplus F(R_0^D, K_{16}) = R_{16} \oplus F(L_{16}, K_{16})$$

Ma dall'ultimo round di cifratura sappiamo che:

$$R_{16} = L_{15} \oplus F(R_{15}, K_{16})$$

e $L_{16} = R_{15}$. Quindi:

$$R_1^D = [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(L_{16}, K_{16})$$

$$= [L_{15} \oplus F(R_{15}, K_{16})] \oplus F(R_{15}, K_{16})$$

$$= L_{15} \oplus [F(R_{15}, K_{16}) \oplus F(R_{15}, K_{16})]$$

$$= L_{15} \oplus 0 = L_{15}$$

Quindi dopo il primo round di decifratura: $(L_1^D, R_1^D) = (L_{16}, L_{15}) = (R_{15}, L_{15})$.

**Passo 3:** Per induzione, si verifica che dopo il round $j$ di decifratura con chiave $K_{16-j+1}$:

$$(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$$

**Verifica del caso generale:** supponiamo $(L_{j-1}^D, R_{j-1}^D) = (R_{16-j+1}, L_{16-j+1})$. Allora:

$$L_j^D = R_{j-1}^D = L_{16-j+1} = R_{16-j}$$

$$R_j^D = L_{j-1}^D \oplus F(R_{j-1}^D, K_{16-j+1})$$

$$= R_{16-j+1} \oplus F(L_{16-j+1}, K_{16-j+1})$$

Dall'equazione di cifratura del round $(16-j+1)$: $R_{16-j+1} = L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})$ e $L_{16-j+1} = R_{16-j}$, quindi:

$$R_j^D = [L_{16-j} \oplus F(R_{16-j}, K_{16-j+1})] \oplus F(R_{16-j}, K_{16-j+1}) = L_{16-j}$$

Quindi $(L_j^D, R_j^D) = (R_{16-j}, L_{16-j})$. L'induzione regge.

**Passo 4:** Dopo 16 round di decifratura:

$$(L_{16}^D, R_{16}^D) = (R_0, L_0)$$

Prima di applicare $IP^{-1}$, il DES esegue uno swap (output del 16° round è $R_{16}^D \| L_{16}^D = L_0 \| R_0$). Applicando $IP^{-1}$:

$$IP^{-1}(L_0 \| R_0) = IP^{-1}(IP(M)) = M$$

**Conclusione:** la decifratura del DES con chiavi in ordine inverso $K_{16}, \ldots, K_1$ produce esattamente il plaintext $M$ di partenza. $\blacksquare$

> 📌 Questa proprietà vale per tutti i cifrari di Feistel con $F$ qualsiasi: la struttura Feistel garantisce l'invertibilità indipendentemente dalla funzione $F$, che non deve essere biiezione.
