# **M1 UD2 Lezione 2 - Relazioni di equivalenza e d'ordine**

### **1. Relazioni di equivalenza**

> 📌 **Definizione:** Una relazione $\sim$ su $A$ è una **relazione di equivalenza** se è:
> 1. **Riflessiva**: $\forall a \in A:\; a \sim a$
> 2. **Simmetrica**: $a \sim b \Rightarrow b \sim a$
> 3. **Transitiva**: $(a \sim b \text{ e } b \sim c) \Rightarrow a \sim c$

Le relazioni di equivalenza formalizzano il concetto di "essere dello stesso tipo" o "avere la stessa proprietà rilevante", generalizzando l'uguaglianza.

**Esempi:**

- **Congruenza modulo $n$**: $a \equiv b \pmod{n}$ (riflessiva, simmetrica, transitiva ✓)
- **Similitudine di triangoli**: due triangoli sono simili se hanno gli stessi angoli ✓
- **Parallelismo tra rette** (se si include la convenzione che una retta è parallela a sé stessa) ✓
- **Avere la stessa prima lettera** (su parole di un dizionario) ✓

---

### **2. Classi di equivalenza**

> 📌 **Definizione:** Dato $a \in A$, la **classe di equivalenza** di $a$ rispetto a $\sim$ è:
> $$[a] = \{x \in A : x \sim a\}$$

Si legge "la classe di $a$". Tutti gli elementi di $[a]$ sono equivalenti tra loro.

**Proprietà fondamentali delle classi:**

1. Ogni elemento appartiene alla propria classe: $a \in [a]$ (per la riflessività)
2. Se $a \sim b$, allora $[a] = [b]$ (le classi coincidono)
3. Se $a \not\sim b$, allora $[a] \cap [b] = \emptyset$ (le classi sono disgiunte)

In altre parole: due classi di equivalenza sono o **identiche** o **disgiunte**. Non possono sovrapporsi parzialmente.

**Esempio:** Relazione su $\mathbb{N}^2$ definita da $(a,b) \sim (a',b')$ se e solo se $b = b'$ (stesso secondo elemento).

- La classe di $(3, 2)$ è $\{(x, 2) : x \in \mathbb{N}\} = [(3,2)]$
- La classe di $(7, 5)$ è $\{(x, 5) : x \in \mathbb{N}\}$
- Sono disgiunte (secondo componente diverso)

---

### **3. Partizioni e insieme quoziente**

> 📌 **Definizione:** Una **partizione** di $A$ è una famiglia di sottoinsiemi non vuoti $\{A_i\}_{i \in I}$ di $A$ tali che:
> - Sono a due a due disgiunti: $i \neq j \Rightarrow A_i \cap A_j = \emptyset$
> - La loro unione è $A$: $\bigcup_{i \in I} A_i = A$

> 📌 **Teorema:** Le classi di equivalenza di una relazione di equivalenza $\sim$ su $A$ formano una **partizione** di $A$. Viceversa, ogni partizione di $A$ determina una relazione di equivalenza (due elementi sono equivalenti se appartengono alla stessa parte).

**L'insieme quoziente** $A/\!\sim$ è l'insieme di tutte le classi di equivalenza:

$$A/\!\sim\; = \{[a] : a \in A\}$$

> 💡 L'insieme quoziente "appiattisce" la struttura di $A$: ogni classe diventa un singolo punto. Ad esempio, $\mathbb{Z}/\equiv_n$ ha esattamente $n$ elementi: le classi $[0], [1], \ldots, [n-1]$.

---

### **4. Esercizi sulle relazioni di equivalenza**

**Esercizio 1:** $x^2 = y^2$ su $\mathbb{Z}$

- Riflessiva: $a^2 = a^2$ ✓
- Simmetrica: $a^2 = b^2 \Rightarrow b^2 = a^2$ ✓
- Transitiva: $a^2 = b^2$ e $b^2 = c^2$ $\Rightarrow$ $a^2 = c^2$ ✓
- **È una relazione di equivalenza.** Le classi sono: $\{0\}$ (classe di $0$) e $\{n, -n\}$ per ogni $n > 0$.

**Esercizio 2:** Punti del piano con la stessa ascissa, cioè $(x_1, y_1) \sim (x_2, y_2) \Leftrightarrow x_1 = x_2$

- Riflessiva ✓, Simmetrica ✓, Transitiva ✓ → **Relazione di equivalenza**
- Le classi sono le **rette verticali** $\{(k, y) : y \in \mathbb{R}\}$ per ogni $k \in \mathbb{R}$
- L'insieme quoziente è in corrispondenza con $\mathbb{R}$ (identificato tramite l'ascissa)

**Esercizio 3:** $x$ coprimo con $y$ su $\mathbb{N}$ (cioè $\text{MCD}(x,y) = 1$)

- Riflessiva? $\text{MCD}(a,a) = a$. Solo per $a=1$. ✗
- Simmetrica? $\text{MCD}(a,b) = \text{MCD}(b,a)$ ✓
- Non è una relazione di equivalenza (manca la riflessività, e anche la transitività: $\text{MCD}(2,3)=1$ e $\text{MCD}(3,4)=1$ ma $\text{MCD}(2,4)=2 \neq 1$).

---

### **5. Relazioni d'ordine**

> 📌 **Definizione:** Una relazione $\preceq$ su $A$ è una **relazione d'ordine** (o **ordine parziale**) se è:
> 1. **Riflessiva**: $a \preceq a$
> 2. **Antisimmetrica**: $(a \preceq b \text{ e } b \preceq a) \Rightarrow a = b$
> 3. **Transitiva**: $(a \preceq b \text{ e } b \preceq c) \Rightarrow a \preceq c$

Si usa la notazione $a \prec b$ per dire che $a \preceq b$ e $a \neq b$ (ordine stretto).

**Esempi:**
- $\leq$ su $\mathbb{N}$ è una relazione d'ordine ✓
- Divisibilità $\mid$ su $\mathbb{N}$ è una relazione d'ordine ✓ (riflessiva, antisimmetrica su $\mathbb{N}$, transitiva)
- Inclusione insiemistica $\subseteq$ è una relazione d'ordine ✓

---

### **6. Ordine totale vs ordine parziale**

> 📌 **Definizione:** Un ordine $\preceq$ su $A$ è **totale** (o **lineare**) se:
> $$\forall a, b \in A:\quad a \preceq b \text{ oppure } b \preceq a$$

Se l'ordine non è totale (esistono coppie di elementi non confrontabili), si dice **parziale**.

**Esempi:**
- $\leq$ su $\mathbb{N}$: ordine **totale** (dati $a, b$, o $a \leq b$ o $b \leq a$)
- Divisibilità su $\mathbb{N}$: ordine **parziale** ($3 \nmid 4$ e $4 \nmid 3$: 3 e 4 non sono confrontabili)
- Inclusione $\subseteq$ su $\mathcal{P}(A)$: ordine parziale in generale

> 💡 L'ordine totale è quello "intuitivo" dei numeri: ogni coppia di elementi si può confrontare. L'ordine parziale è più sottile: esistono elementi "incomparabili". Nella teoria dei poset (insiemi parzialmente ordinati) si studiano strutture come reticoli, catene, anticatene.

---

### **7. Esercizi sulle relazioni d'ordine**

**Esercizio 1:** $x = y^2$ su $\mathbb{N}$

- Riflessiva? $a = a^2$ solo per $a = 0, 1$. ✗ → Non è riflessiva
- Antisimmetrica? Se $a = b^2$ e $b = a^2$, allora $a = a^4$, dunque $a(a^3-1)=0$, cioè $a=0$ o $a=1$, e allora $b=0$ o $b=1$ rispettivamente. ✓
- **Non è una relazione d'ordine** (manca la riflessività)

**Esercizio 2:** Divisibilità $\mid$ su $\mathbb{N}$

- Riflessiva ✓, Antisimmetrica ✓ (su $\mathbb{N}$), Transitiva ✓
- **È un ordine parziale** (ma non totale: $3 \nmid 4$ e $4 \nmid 3$)

---

> ✅ Le relazioni di equivalenza (riflessiva + simmetrica + transitiva) partizionano l'insieme in classi disgiunte che coprono tutto l'insieme. L'insieme quoziente raccoglie le classi come elementi. Le relazioni d'ordine (riflessiva + antisimmetrica + transitiva) possono essere totali (ogni coppia è confrontabile, come $\leq$ su $\mathbb{N}$) o parziali (come la divisibilità).
