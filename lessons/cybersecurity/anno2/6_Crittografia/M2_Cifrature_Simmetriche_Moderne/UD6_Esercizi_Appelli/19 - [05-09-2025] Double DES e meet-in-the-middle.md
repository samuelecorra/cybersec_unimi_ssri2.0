# Double DES e meet-in-the-middle — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 settembre 2025**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L5%20-%20Appello%205%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Double DES e attacco meet-in-the-middle (20 punti)

### Traccia originale

> **(20 punti) Descrivere ed analizzare il DES doppio.**
>
> a. (10 punti) Descrivere il DES doppio.
>
> b. (10 punti) Illustrare come rompere il DES doppio mediante un attacco di tipo known plaintext e analizzare la complessità (tempo, spazio) dell'attacco.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — Double DES completo

### Soluzione dettagliata

#### Parte a — DES doppio (10 punti)

Il **Double DES** (2DES) è una semplice estensione del DES che applica il cifrario due volte con due chiavi distinte, nel tentativo di aumentare la sicurezza rispetto al DES singolo (che ha una chiave di soli 56 bit, vulnerabile a brute force).

**Schema:**

$$\text{Cifratura: } C = DES_{K_2}(DES_{K_1}(P))$$

$$\text{Decifratura: } P = DES_{K_1}^{-1}(DES_{K_2}^{-1}(C))$$

**Chiave:** la chiave composta è $(K_1, K_2)$ con $K_1, K_2$ ciascuna di 56 bit, per una chiave effettiva totale di **112 bit**.

**Motivazione:** con 112 bit di chiave, ci si aspettava una sicurezza di $2^{112}$ operazioni per il brute force. Invece, come vedremo, la sicurezza effettiva è solo $2^{56}$.

> ⚠️ Il Double DES non è mai stato adottato come standard. Al suo posto si usa il Triple DES (3DES) che ha dimostrato resistere all'attacco meet-in-the-middle in modo più efficace.

#### Parte b — Attacco meet-in-the-middle (10 punti)

L'attacco **meet-in-the-middle** (MITM) fu proposto da Diffie e Hellman nel 1977 per dimostrare l'inefficacia del Double DES.

**Setup dell'attacco:**

L'avversario conosce almeno una coppia $(P, C)$ con $C = DES_{K_2}(DES_{K_1}(P))$.

**Idea chiave:** definiamo il valore intermedio:

$$X = DES_{K_1}(P) = DES_{K_2}^{-1}(C)$$

La stessa quantità $X$ può essere calcolata sia partendo da $P$ (cifrando con $K_1$) sia partendo da $C$ (decifrando con $K_2$). Quindi si cerca la corrispondenza "nel mezzo" ($X$).

**Fasi dell'attacco:**

**Fase 1 — Forward pass (da $P$):**

Per ogni possibile chiave $K \in \{0,1\}^{56}$ ($2^{56}$ chiavi totali):
- Calcola $X_K = DES_K(P)$
- Memorizza la coppia $(X_K, K)$ in una tabella $T$ indicizzata per valore di $X_K$.

Costo: $2^{56}$ cifrature DES, memorizzazione di $2^{56}$ entry da 64+56 bit.

**Fase 2 — Backward pass (da $C$):**

Per ogni possibile chiave $K' \in \{0,1\}^{56}$ ($2^{56}$ chiavi totali):
- Calcola $Y_{K'} = DES_{K'}^{-1}(C)$
- Cerca $Y_{K'}$ nella tabella $T$

Se $Y_{K'} = X_K$ per qualche $K$, allora la coppia $(K, K')$ è un candidato per la chiave $(K_1, K_2)$.

Costo: $2^{56}$ decifrature DES + $2^{56}$ ricerche in tabella (O(1) per ricerca hash).

**Fase 3 — Verifica:**

Per ogni coppia candidata $(K, K')$, si verifica con ulteriori coppie note $(P_i, C_i)$ che $DES_{K'}(DES_K(P_i)) = C_i$. I falsi positivi vengono eliminati.

Il numero atteso di falsi positivi è $2^{112} / 2^{64} = 2^{48}$ (molto alto con una sola coppia). Con 2-3 coppie note, i falsi positivi diventano trascurabili.

**Analisi della complessità:**

| Risorsa | Double DES (atteso naive) | Double DES (attacco MITM) | DES singolo |
|---------|--------------------------|--------------------------|-------------|
| Tempo | $2^{112}$ | $\mathbf{2^{57}}$ (circa $2 \times 2^{56}$) | $2^{56}$ |
| Spazio | $O(1)$ | $\mathbf{2^{56} \times 120\ \text{bit}}$ | $O(1)$ |

> 📌 L'attacco MITM rompe il Double DES con circa $2 \cdot 2^{56} = 2^{57}$ operazioni DES, ovvero con lo stesso costo (a meno di un fattore 2) del brute force su DES singolo. La raddoppiatura della chiave non raddoppia la sicurezza in bit: porta da 56 a 57 bit di sicurezza effettiva.

> ⚠️ Trade-off tempo/spazio: l'attacco richiede $2^{56}$ entry in memoria, circa $2^{56} \times 15\ \text{bytes} \approx 10^{18}$ bytes = $10^9$ GB. Con hardware moderno è impraticabile, ma il punto teorico è che la sicurezza è molto inferiore ai 112 bit attesi.
