# **M2 UD1 Lezione 6 - Teorema di Lagrange**

### **1. Enunciato**

> 📌 **Teorema di Lagrange:** Sia $(G, \circledast)$ un gruppo finito di ordine $n$ e $B \leq G$ un sottogruppo di ordine $r$. Allora $r \mid n$.

In altre parole, **l'ordine di ogni sottogruppo divide l'ordine del gruppo**.

---

### **2. Laterali (laterale destro)**

La dimostrazione si basa sul concetto di laterale.

> 📌 **Definizione:** Dato il sottogruppo $B = \{u, b_1, b_2, \ldots, b_{r-1}\}$ e un elemento $a \in G$, il **laterale destro** di $B$ relativo ad $a$ è:
> $$B \circledast a = \{u \circledast a, \; b_1 \circledast a, \; b_2 \circledast a, \; \ldots, \; b_{r-1} \circledast a\}$$

---

### **3. Dimostrazione del teorema**

La dimostrazione procede in quattro passi.

#### **Passo 1: Ogni laterale ha esattamente $r$ elementi**

Gli elementi del laterale $B \circledast a = \{b \circledast a \mid b \in B\}$ sono tutti distinti.

*Dimostrazione:* Supponiamo $b_h \circledast a = b_k \circledast a$. Per la legge di cancellazione (valida in ogni gruppo, moltiplicando a destra per $a^{-1}$): $b_h = b_k$. Quindi elementi diversi di $B$ danno elementi diversi in $B \circledast a$, e $|B \circledast a| = |B| = r$. $\square$

#### **Passo 2: Due laterali sono o uguali o disgiunti**

Se $B \circledast a_i \cap B \circledast a_j \neq \emptyset$ allora $B \circledast a_i = B \circledast a_j$.

*Dimostrazione:* Sia $b_h \circledast a_i = b_k \circledast a_j$ (un elemento in comune). Allora per qualunque $b_l \in B$:
$$b_l \circledast a_i = (b_l \circledast b_h^{-1}) \circledast (b_h \circledast a_i) = (b_l \circledast b_h^{-1}) \circledast (b_k \circledast a_j)$$
Poiché $B$ è chiuso rispetto alle operazioni, $b_l \circledast b_h^{-1} \circledast b_k \in B$, quindi $b_l \circledast a_i \in B \circledast a_j$.

Dunque $B \circledast a_i \subseteq B \circledast a_j$; per simmetria $B \circledast a_j \subseteq B \circledast a_i$, cioè i due laterali coincidono. $\square$

#### **Passo 3: Ogni $a \in G$ appartiene ad almeno un laterale**

Per ogni $a \in G$: $a = u \circledast a \in B \circledast a$.

Quindi ogni elemento di $G$ è contenuto in qualche laterale.

#### **Passo 4: I laterali partizionano $G$**

I laterali distinti sono **disgiunti** (Passo 2), coprono tutto $G$ (Passo 3) e ciascuno ha $r$ elementi (Passo 1).

Sia $s$ il numero di laterali distinti. Allora:
$$n = |G| = r \cdot s$$

Quindi $r \mid n$. $\square$

---

### **4. Indice**

> 📌 **Definizione:** Il numero $s$ di laterali distinti di $B$ in $G$ si chiama **indice** di $B$ in $G$ e si scrive $[G : B]$.

Dalla dimostrazione: $n = r \cdot s$, cioè $[G : B] = |G| / |B|$.

---

### **5. Conseguenze del teorema di Lagrange**

> 📌 **Corollario 1:** Il **periodo di ogni elemento** $a \in G$ divide $|G|$.

*Dimostrazione:* Il periodo di $a$ è l'ordine del sottogruppo ciclico $\langle a \rangle$, che divide $|G|$ per Lagrange. $\square$

> 📌 **Corollario 2:** Un gruppo di ordine **primo** non ha sottogruppi propri.

*Dimostrazione:* Se $|G| = p$ primo, gli unici divisori di $p$ sono $1$ e $p$: l'unico sottogruppo di ordine $1$ è $\{u\}$, quello di ordine $p$ è $G$ stesso. $\square$

> 📌 **Corollario 3:** Ogni gruppo di ordine primo è **ciclico** (e ogni elemento non neutro è un generatore).

*Dimostrazione:* Sia $a \neq u$. Il sottogruppo $\langle a \rangle$ ha ordine $> 1$ e divide $p$, quindi $|\langle a \rangle| = p$, cioè $\langle a \rangle = G$. $\square$

> ⚠️ Il **viceversa** del teorema di Lagrange è **falso** in generale: non è detto che, per ogni divisore $d$ di $|G|$, esista un sottogruppo di ordine $d$. Il gruppo alternante $A_4$ (ordine $12$) non ha sottogruppi di ordine $6$.

---

### **6. Laterali sinistri e sottogruppi normali**

Nei **gruppi non abeliani** il laterale sinistro $a \circledast B$ può essere diverso dal laterale destro $B \circledast a$.

> 📌 **Definizione:** Un sottogruppo $B \leq G$ è **normale** (si scrive $B \trianglelefteq G$) se:
> $$\forall a \in G: \quad a \circledast B = B \circledast a$$

Nei **gruppi abeliani** ogni sottogruppo è normale (poiché $a \circledast b = b \circledast a$).

I sottogruppi normali giocano un ruolo fondamentale nella costruzione dei **gruppi quoziente** (teoria dei gruppi avanzata).

---

> ✅ Il teorema di Lagrange afferma che l'ordine di qualunque sottogruppo divide l'ordine del gruppo. La dimostrazione costruisce i laterali destri, che partizionano $G$ in classi equipotenti. Conseguenze: il periodo di ogni elemento divide $|G|$; i gruppi di ordine primo sono ciclici e privi di sottogruppi propri. Il viceversa di Lagrange non vale in generale.
