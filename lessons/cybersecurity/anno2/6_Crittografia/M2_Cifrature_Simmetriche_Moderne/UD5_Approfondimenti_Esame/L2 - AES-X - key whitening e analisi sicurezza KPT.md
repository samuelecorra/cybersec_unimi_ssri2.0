# **L2 – AES-X: key whitening e analisi sicurezza KPT**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 2 appelli (230922 e 250919) con peso di **20 punti** ciascuno.
>
> **Studia prima:**
> - [UD3 / L1 – AES: Origine e caratteristiche](../UD3/L1%20-%20AES%20%28Advanced%20Encryption%20Standard%29.md) → storia, struttura SP-network, 4 operazioni per round
> - [UD3 / L2 – Struttura della cifratura AES](../UD3/L2%20-%20Struttura%20della%20cifratura%20AES.md) → matrice di stato, AddRoundKey (XOR con sottochiave), GF(2⁸)

---

### **1. Definizione di AES-X (key whitening)**

**AES-X** è una variante di AES che aggiunge una seconda chiave indipendente tramite XOR **dopo** la cifratura AES standard:

$$
\boxed{\text{AES-X}_{k_1, k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2}
$$

dove:
- $m \in \{0,1\}^{128}$ è il blocco di plaintext (128 bit),
- $k_1 \in \{0,1\}^{128}$ è la chiave AES standard (usata per i round interni),
- $k_2 \in \{0,1\}^{128}$ è la seconda chiave (applicata via XOR al ciphertext finale),
- $c = \text{AES}_{k_1}(m) \oplus k_2$ è il ciphertext.

#### **Struttura visiva**

```
m ──► [ AES con chiave k₁ ] ──► AES_k1(m) ──► ⊕ k₂ ──► c
```

> 📌 Questo schema si chiama **key whitening**: aggiungere un XOR con una chiave prima o dopo un cifrario è una tecnica storica usata per rafforzare cifrari deboli (es. DESX = DES con whitening). AES-X è la variante AES con whitening solo *post-cifratura*.

---

### **2. Caratteristiche di AES-X**

| Proprietà | AES standard | AES-X |
|-----------|-------------|-------|
| Dimensione chiave | 128 bit ($k_1$) | 256 bit ($k_1$ + $k_2$) |
| Blocco | 128 bit | 128 bit |
| Operazioni interne | 10 round Rijndael | 10 round Rijndael + 1 XOR finale |
| Chiavi apparenti | $2^{128}$ | $2^{256}$ |
| Chiavi effettive (vedi §4) | $2^{128}$ | $2^{128}$ (sotto KPT) |

> ⚠️ La dimensione della chiave è 256 bit, ma **l'effettiva sicurezza sotto known-plaintext attack non supera quella di AES standard** (128 bit). Questo è il punto critico che il professore chiede di dimostrare.

---

### **3. Decifratura di AES-X**

Dato il ciphertext $c = \text{AES}_{k_1}(m) \oplus k_2$:

1. Rimuovi il whitening: $\text{AES}_{k_1}(m) = c \oplus k_2$
2. Decifra con AES inverso: $m = \text{AES}^{-1}_{k_1}(c \oplus k_2)$

$$
\boxed{m = \text{AES}^{-1}_{k_1}(c \oplus k_2)}
$$

---

### **4. Analisi sicurezza sotto Known-Plaintext Attack (KPT)**

#### **Scenario**

L'avversario conosce una o più coppie $(m, c)$ dove $c = \text{AES}_{k_1}(m) \oplus k_2$.

**Domanda:** AES-X è più sicuro di AES standard sotto KPT?

#### **Dimostrazione che $k_2$ non aggiunge sicurezza**

**Osservazione chiave:** data una singola coppia $(m, c)$ nota, per ogni valore ipotetico di $k_1$ l'avversario può calcolare immediatamente $k_2$ corrispondente:

$$
k_2 = c \oplus \text{AES}_{k_1}(m)
$$

Questo significa che il problema si riduce a:

> *Trovare $k_1$ tale che $c \oplus \text{AES}_{k_1}(m)$ sia una chiave AES valida.*

Qualsiasi valore di $k_1$ produce un $k_2$ valido (ogni stringa da 128 bit è una chiave AES accettabile). Quindi:

- L'avversario deve cercare su **$2^{128}$** valori di $k_1$.
- Per ogni $k_1$ testato, calcola $k_2 = c \oplus \text{AES}_{k_1}(m)$ con un'operazione.
- Verifica la coppia $(k_1, k_2)$ su un secondo known-plaintext per confermare.

**Complessità:** $O(2^{128})$ — **identica** a un attacco bruteforce su AES standard con chiave da 128 bit.

> 📌 **Conclusione:** AES-X ha la stessa resistenza di AES standard sotto KPT. La chiave $k_2$ da 128 bit **non aggiunge alcuna sicurezza** perché è univocamente determinata da $k_1$ e dalla coppia nota $(m, c)$.

#### **Confronto formale**

| Attacco | AES (128 bit) | AES-X (256 bit apparenti) |
|---------|--------------|--------------------------|
| Bruteforce su chiave | $O(2^{128})$ operazioni | $O(2^{128})$ operazioni |
| Known-plaintext exhaustive | Cerca $k$ t.c. $\text{AES}_k(m) = c$ | Cerca $k_1$ t.c. $c \oplus \text{AES}_{k_1}(m) = k_2$ (qualsiasi $k_2$) |
| Numero di coppie per conferma | 2 | 2 |
| Sicurezza effettiva | 128 bit | **128 bit** |

> ⚠️ **Errore comune:** pensare che 256 bit di chiave implichi $2^{256}$ sicurezza. La struttura di AES-X consente di "scindere" la ricerca: si cerca su $2^{128}$ per $k_1$ e $k_2$ è gratuita. La sicurezza non si moltiplica, si somma (in modo degenere).

---

### **5. Confronto con DESX (whitening su DES)**

La tecnica di key whitening fu introdotta da **Rivest** con DESX:

$$
\text{DESX}_{k_0, k_1, k_2}(m) = k_2 \oplus \text{DES}_{k_1}(m \oplus k_0)
$$

DESX aggiunge XOR sia **prima** che **dopo** DES. La sicurezza di DESX sotto KPT è dell'ordine di $2^{120}$ (vs $2^{56}$ di DES puro), poiché il pre-whitening $m \oplus k_0$ impedisce la fattorizzazione della ricerca come in AES-X.

> 💡 **Perché DESX funziona meglio di AES-X:** DESX ha whitening in ingresso ($k_0$) che oscura il plaintext; l'attaccante non può calcolare direttamente l'input al DES da una coppia $(m, c)$ nota senza conoscere $k_0$. AES-X ha whitening **solo in uscita**, lasciando $m$ direttamente visibile come input ad AES.

---

### **6. Esercizio svolto stile esame (20 punti)**

#### **Testo (Appello 230922 / 250919)**

> Sia dato il cifrario AES-X definito da:
> $$\text{AES-X}_{k_1,k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2$$
> con $k_1, k_2$ chiavi AES indipendenti da 128 bit.
>
> (a) Descrivere le caratteristiche principali di AES-X. *(10 pt)*
>
> (b) Discutere la sicurezza di AES-X rispetto ad AES standard sotto known-plaintext attack. *(20 pt)*

---

#### **Soluzione (a) — Caratteristiche:**

AES-X è un cifrario a blocchi da 128 bit che estende AES standard aggiungendo una fase di **key whitening** post-cifratura. La chiave totale è di 256 bit ($k_1 \| k_2$). Internamente usa i 10 round standard di Rijndael applicati con $k_1$, poi XOR il risultato con $k_2$. La decifratura si ottiene XORando $c$ con $k_2$ e poi applicando AES inverso con $k_1$:
$$m = \text{AES}^{-1}_{k_1}(c \oplus k_2)$$

---

#### **Soluzione (b) — Analisi sicurezza KPT:**

Sia $(m, c)$ una coppia known-plaintext. Per definizione: $c = \text{AES}_{k_1}(m) \oplus k_2$.

**Osservazione:** per qualsiasi tentativo di $k_1$, il valore di $k_2$ è immediatamente calcolabile:
$$k_2 = c \oplus \text{AES}_{k_1}(m)$$

Questo riduce il problema a una ricerca unidimensionale su $k_1$: si devono testare $2^{128}$ valori possibili di $k_1$, e per ognuno si verifica la coppia candidata $(k_1, k_2)$ su una seconda coppia known-plaintext.

**Complessità:** $O(2^{128})$ — identica all'attacco bruteforce su AES standard (chiave 128 bit).

**Conclusione:** nonostante la chiave complessiva sia di 256 bit, AES-X non offre maggiore sicurezza rispetto ad AES standard sotto known-plaintext attack. Il whitening post-cifratura non impedisce la fattorizzazione della ricerca, poiché $k_2$ è una funzione diretta di $k_1$ e della coppia $(m, c)$ nota. AES standard con chiave da 128 bit e AES-X hanno la stessa resistenza KPT di $2^{128}$ operazioni.

> ✅ **Recap:** AES-X aggiunge XOR con $k_2$ al ciphertext. Questo sembra raddoppiare la chiave (256 bit), ma sotto KPT $k_2$ è determinata da $k_1$ e dalla coppia nota $(m,c)$. La sicurezza rimane $2^{128}$, identica ad AES standard. Il whitening è efficace solo se applicato **anche all'ingresso** (come in DESX).
