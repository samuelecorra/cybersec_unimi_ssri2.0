# **L1 – H(x) = DES_k(x): analisi one-wayness**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 1 appello (250704) con peso di **15 punti**.
>
> **Studia prima:**
> - [UD1 / L2 – Proprietà delle funzioni hash](../UD1/L2%20-%20Proprietà.md) → definizione formale di one-wayness (pre-image resistance), second pre-image resistance, collision resistance
> - [M2 – UD2 / L1 – Struttura del DES](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) → DES è un cifrario a blocchi invertibile: dato il ciphertext e la chiave, si ottiene il plaintext in modo esatto

---

### **1. Il problema: usare DES come funzione hash**

La domanda che il professore pone all'esame è:

> Sia $H(x) = \text{DES}_k(x)$ per un certo valore di $k$. Analizzare la proprietà di **one-wayness** di questa funzione nei tre casi:
> 1. $k$ può variare (è pubblicamente scelto dall'avversario)
> 2. $k$ è fisso e **noto** all'avversario
> 3. $k$ è fisso e **ignoto** all'avversario

Per rispondere, bisogna applicare la definizione di one-wayness a questi tre scenari, ragionando su **se e come** l'avversario può invertire $H$.

> 📌 **Definizione operativa di one-wayness:** $H$ è one-way se, dato $y = H(x)$, è **computazionalmente impraticabile** trovare un $x'$ tale che $H(x') = y$. Non serve trovare l'$x$ originale: basta trovare *un qualsiasi* pre-image.

---

### **2. Proprietà di DES rilevanti per l'analisi**

Prima di procedere, richiamiamo le proprietà di DES che influenzano questa analisi:

| Proprietà | Descrizione |
|-----------|-------------|
| **Invertibilità** | DES è un cifrario a blocchi: dato $(y, k)$, si calcola esattamente $x = \text{DES}_k^{-1}(y)$ |
| **Dimensioni** | Input e output: 64 bit ciascuno. Quindi $H$ non comprime (non è una vera hash!) |
| **Chiave** | 56 bit effettivi → $2^{56} \approx 7{,}2 \times 10^{16}$ chiavi possibili |
| **Sicurezza della chiave** | Trovare $k$ da coppie $(x, \text{DES}_k(x))$ richiede in media $2^{55}$ operazioni |

> ⚠️ Nota tecnica: una vera funzione hash deve **comprimere** l'input (output più corto dell'input). Poiché $\text{DES}_k$ mappa 64 bit → 64 bit, $H(x) = \text{DES}_k(x)$ non è una funzione hash nel senso pieno del termine. L'esame chiede comunque di analizzarne la one-wayness nelle tre condizioni.

---

### **3. Caso 1 — k variabile (l'avversario può scegliere k liberamente)**

**Scenario:** la chiave $k$ non è fissa. L'avversario può scegliere qualsiasi $k$ di sua preferenza prima di tentare l'inversione.

**Analisi:**

Dato $y = \text{DES}_{k_0}(x)$ per qualche $k_0$ incognito, l'avversario sceglie **liberamente** un $k'$ qualunque e calcola:

$$x' = \text{DES}_{k'}^{-1}(y)$$

Verifica: $\text{DES}_{k'}(x') = y$? Sì per costruzione.

> 📌 **Conclusione: H non è one-way** quando $k$ è variabile.

L'avversario non deve trovare il $k_0$ originale: gli basta scegliere **qualsiasi** $k'$ e invertire con quello. La one-wayness richiede che sia difficile trovare **un qualsiasi pre-image** di $y$ — ma qui l'avversario ne genera uno banalmente con qualunque chiave a sua scelta.

> 💡 **Intuizione:** se la chiave può essere scelta liberamente dall'avversario, il problema si riduce a "invertire DES con chiave nota", che è banale per costruzione.

---

### **4. Caso 2 — k fisso e noto all'avversario**

**Scenario:** la chiave $k$ è fissa per tutte le computazioni hash, ed è **pubblicamente nota** (ad esempio, è un parametro del sistema).

**Analisi:**

Dato $y = \text{DES}_k(x)$, l'avversario conosce $k$ e può calcolare direttamente:

$$x = \text{DES}_k^{-1}(y)$$

Questa operazione richiede esattamente **un'operazione di decifratura DES** — computazionalmente trascurabile.

> 📌 **Conclusione: H non è one-way** quando $k$ è fisso e noto.

DES è un cifrario invertibile by design. Conoscere $k$ significa poter invertire $H$ con una singola operazione. Non c'è nessuna barriera computazionale.

> ⚠️ Questo è il motivo per cui le funzioni hash crittografiche **non** usano cifrari simmetrici con chiave nota come costruzione diretta: la invertibilità del cifrario distrugge la one-wayness.

---

### **5. Caso 3 — k fisso e ignoto all'avversario**

**Scenario:** la chiave $k$ è fissa per tutte le computazioni hash, ma **non è nota** all'avversario (è mantenuta segreta dal sistema che usa $H$).

**Analisi:**

Dato $y = \text{DES}_k(x)$, l'avversario non conosce $k$. Per invertire $H$ deve:

1. Trovare la chiave $k$ (attacco alla chiave DES): richiede in media $2^{55}$ operazioni di cifratura/decifratura DES.
2. Oppure trovare direttamente una coppia $(x', k')$ tale che $\text{DES}_{k'}(x') = y$ — ma questo richiede conoscere o indovinare $k'$, che riporta al punto 1.

Poiché $2^{55} \approx 3{,}6 \times 10^{16}$ operazioni sono computazionalmente impraticabili (nel 2025 richiedono giorni anche con hardware dedicato), $H$ è **presumibilmente** one-way.

> 📌 **Conclusione: H è presumibilmente one-way** quando $k$ è fisso e ignoto.

La parola "presumibilmente" è importante: la one-wayness non è dimostrata in modo assoluto, ma si riduce alla difficoltà di rompere DES, che è computazionalmente accettata come difficile per $k$ a 56 bit (e facilmente rafforzabile con 3DES o AES).

> 💡 **Intuizione:** se $k$ è segreto, l'avversario non può invertire DES senza prima trovare $k$. Trovare $k$ richiede $2^{55}$ operazioni — questo è il costo dell'inversione di $H$ in questo caso.

---

### **6. Tabella riassuntiva**

| Caso | Scenario | One-way? | Motivo | Costo per invertire |
|------|----------|----------|--------|---------------------|
| **1** | $k$ variabile, scelto dall'avversario | ❌ No | Basta scegliere qualsiasi $k'$ e calcolare $\text{DES}_{k'}^{-1}(y)$ | O(1) |
| **2** | $k$ fisso e noto | ❌ No | DES è invertibile: $x = \text{DES}_k^{-1}(y)$ direttamente | O(1) |
| **3** | $k$ fisso e ignoto | ✅ Presumibilmente sì | Richiederebbe trovare $k$: $\approx 2^{55}$ operazioni DES | $O(2^{55})$ |

---

### **7. Considerazioni aggiuntive: second pre-image e collision resistance**

Il professore potrebbe estendere la domanda alle altre proprietà hash. Analisi rapida:

**Second pre-image resistance** (dato $x$, trovare $x' \ne x$ con $H(x') = H(x')$):
- Caso 1 (k variabile): non vale — scegli $k'$ diverso da $k_0$, ottieni un $x'$ diverso con hash uguale
- Caso 2 (k noto): non vale — $H$ è una biiezione per ogni $k$ fisso, quindi dato $x$ non esiste $x' \ne x$ con stesso hash (DES è una permutazione su 64 bit). Paradossalmente, la second pre-image resistance **vale** per $k$ noto!
- Caso 3 (k ignoto): come il caso 2 rispetto alla struttura matematica

**Collision resistance:** DES mappa 64 bit → 64 bit con chiave fissa è una **biiezione** → non esistono collisioni per $k$ fisso. La collision resistance vale (banalmente) per $k$ fisso.

> 💡 Questo mostra un paradosso: $H(x) = \text{DES}_k(x)$ con $k$ fisso noto soddisfa second pre-image e collision resistance, ma NON è one-way. Ciò è coerente con la teoria: le proprietà hash sono logicamente indipendenti, non c'è un ordine di implicazione automatico tra tutti e tre.

---

### **8. Esercizio svolto stile esame (15 punti)**

#### **Testo (Appello 250704)**

> Sia $H(x) = \text{DES}_k(x)$ per un certo $k$. Analizzare la proprietà di one-wayness nei tre casi: (i) $k$ può variare; (ii) $k$ è fisso e noto; (iii) $k$ è fisso e ignoto. *(15 pt)*

---

#### **Soluzione:**

**(i) $k$ variabile:** $H$ **non è one-way**. Dato $y$, l'avversario sceglie qualunque $k'$ e calcola $x' = \text{DES}_{k'}^{-1}(y)$: per definizione $\text{DES}_{k'}(x') = y$, quindi $x'$ è un pre-image valido. Il costo è O(1).

**(ii) $k$ fisso e noto:** $H$ **non è one-way**. Dato $y$ e $k$ noto, l'avversario calcola $x = \text{DES}_k^{-1}(y)$ in un'operazione. DES è invertibile by design: la conoscenza della chiave elimina qualsiasi barriera computazionale.

**(iii) $k$ fisso e ignoto:** $H$ è **presumibilmente one-way**. Dato $y$, per trovare $x$ tale che $\text{DES}_k(x) = y$ bisogna conoscere $k$. Trovare $k$ richiede in media $2^{55}$ operazioni di ricerca esaustiva, che è computazionalmente impraticabile. La one-wayness di $H$ si riduce alla difficoltà di rompere DES.

> ✅ **Recap:** la one-wayness di $H(x) = \text{DES}_k(x)$ dipende interamente dalla disponibilità di $k$. Con $k$ noto o variabile, DES è banalmente invertibile. Solo con $k$ segreto e fisso la funzione offre resistenza all'inversione, grazie alla difficoltà di trovare la chiave DES (≈ $2^{55}$ operazioni).
