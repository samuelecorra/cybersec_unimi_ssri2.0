# **M1 UD1 Lezione 6 - MCD, algoritmo euclideo e mcm**

### **1. Il Massimo Comune Divisore**

> 📌 **Definizione:** Il **Massimo Comune Divisore** (MCD) di due interi $a$ e $b$, non entrambi nulli, è il più grande intero positivo che divide sia $a$ che $b$:
> $$\text{MCD}(a, b) = \max\{d \in \mathbb{N} : d \mid a \text{ e } d \mid b\}$$

**Casi particolari:**
- $\text{MCD}(n, 0) = n$ per ogni $n > 0$ (tutti i divisori di $n$ dividono anche $0$, e il più grande è $n$ stesso)
- $\text{MCD}(0, 0)$ **non è definito** (ogni intero divide $0$, quindi non esiste il massimo)

**Definizione:** $a$ e $b$ sono **coprimi** (o **primi tra loro**) se $\text{MCD}(a, b) = 1$.

---

### **2. Calcolo del MCD tramite fattorizzazione**

Se si conoscono le fattorizzazioni in primi:

$$a = p_1^{\alpha_1} p_2^{\alpha_2} \cdots p_k^{\alpha_k}, \qquad b = p_1^{\beta_1} p_2^{\beta_2} \cdots p_k^{\beta_k}$$

(con esponenti $0$ per i primi che non compaiono), allora:

$$\text{MCD}(a, b) = p_1^{\min(\alpha_1,\beta_1)} p_2^{\min(\alpha_2,\beta_2)} \cdots p_k^{\min(\alpha_k,\beta_k)}$$

**Esempio:** $\text{MCD}(420, 154)$

$$420 = 2^2 \cdot 3 \cdot 5 \cdot 7, \qquad 154 = 2 \cdot 7 \cdot 11$$

$$\text{MCD}(420, 154) = 2^{\min(2,1)} \cdot 3^{\min(1,0)} \cdot 5^{\min(1,0)} \cdot 7^{\min(1,1)} \cdot 11^{\min(0,1)} = 2^1 \cdot 7^1 = 14$$

> ⚠️ Questo metodo richiede la fattorizzazione dei due numeri, che può essere molto costosa per numeri grandi. L'algoritmo euclideo è preferibile nella pratica.

---

### **3. L'algoritmo euclideo**

L'**algoritmo euclideo** calcola il MCD senza bisogno di fattorizzare, usando ripetutamente la divisione con resto.

> 📌 **Base dell'algoritmo:** $\text{MCD}(a, b) = \text{MCD}(b, a \bmod b)$

**Motivazione:** Se $d \mid a$ e $d \mid b$, allora $d \mid (a - qb) = r$. Viceversa, se $d \mid b$ e $d \mid r$, allora $d \mid (bq + r) = a$. Quindi i divisori comuni di $(a, b)$ coincidono con quelli di $(b, r)$.

**Schema:** Si esegue una sequenza di divisioni con resto fino ad ottenere resto $0$. Il **penultimo** divisore (o equivalentemente il **resto non nullo nell'ultima divisione con resto non nullo**) è il MCD.

$$a = b q_1 + r_1 \qquad (r_1 < b)$$
$$b = r_1 q_2 + r_2 \qquad (r_2 < r_1)$$
$$r_1 = r_2 q_3 + r_3 \qquad (r_3 < r_2)$$
$$\vdots$$
$$r_{k-2} = r_{k-1} q_k + r_k$$
$$r_{k-1} = r_k q_{k+1} + 0$$

Allora $\text{MCD}(a, b) = r_k$.

**Esempio:** $\text{MCD}(420, 154)$

$$420 = 154 \cdot 2 + 112$$
$$154 = 112 \cdot 1 + 42$$
$$112 = 42 \cdot 2 + 28$$
$$42 = 28 \cdot 1 + 14$$
$$28 = 14 \cdot 2 + 0$$

Quindi $\text{MCD}(420, 154) = 14$. ✓

---

### **4. Identità di Bézout**

> 📌 **Teorema di Bézout:** Se $d = \text{MCD}(a, b)$, allora esistono $x, y \in \mathbb{Z}$ tali che:
> $$d = ax + by$$

Nota: $x$ e $y$ sono interi, in generale non entrambi positivi.

**Calcolo dei coefficienti di Bézout:** Si risale l'algoritmo euclideo sostituendo progressivamente.

**Esempio:** $\text{MCD}(420, 154) = 14$. Trovare $x, y$ tali che $14 = 420x + 154y$.

Dall'algoritmo euclideo:

$$14 = 42 - 28 \cdot 1$$
$$= 42 - (112 - 42 \cdot 2) \cdot 1 = 42 \cdot 3 - 112$$
$$= (154 - 112 \cdot 1) \cdot 3 - 112 = 154 \cdot 3 - 112 \cdot 4$$
$$= 154 \cdot 3 - (420 - 154 \cdot 2) \cdot 4 = 154 \cdot 11 - 420 \cdot 4$$

Quindi $14 = 420 \cdot (-4) + 154 \cdot 11$, cioè $x = -4$, $y = 11$.

**Verifica:** $420 \cdot (-4) + 154 \cdot 11 = -1680 + 1694 = 14$. ✓

> 💡 I coefficienti di Bézout non sono unici: se $(x_0, y_0)$ è una soluzione, allora anche $(x_0 + \frac{b}{d}t,\, y_0 - \frac{a}{d}t)$ è soluzione per ogni $t \in \mathbb{Z}$. Bézout è fondamentale per risolvere le congruenze lineari.

---

### **5. Il Minimo Comune Multiplo**

> 📌 **Definizione:** Il **Minimo Comune Multiplo** (mcm) di $a$ e $b$ è il più piccolo intero positivo multiplo sia di $a$ che di $b$.

**Calcolo via fattorizzazione:** Si prendono i **massimi** esponenti (anziché i minimi per il MCD):

$$\text{mcm}(a, b) = p_1^{\max(\alpha_1,\beta_1)} p_2^{\max(\alpha_2,\beta_2)} \cdots p_k^{\max(\alpha_k,\beta_k)}$$

**Esempio:** $\text{mcm}(420, 154)$

$$420 = 2^2 \cdot 3 \cdot 5 \cdot 7, \qquad 154 = 2 \cdot 7 \cdot 11$$

$$\text{mcm}(420, 154) = 2^2 \cdot 3 \cdot 5 \cdot 7 \cdot 11 = 4620$$

**Relazione fondamentale MCD–mcm:**

$$\text{MCD}(a, b) \cdot \text{mcm}(a, b) = a \cdot b$$

**Verifica:** $14 \cdot 4620 = 64680 = 420 \cdot 154$. ✓

Questa formula permette di calcolare il mcm una volta noto il MCD:

$$\text{mcm}(a, b) = \frac{a \cdot b}{\text{MCD}(a, b)}$$

> ⚠️ Questa formula con il prodotto $a \cdot b$ può causare overflow numerico per valori grandi. In pratica è preferibile calcolare prima $a / \text{MCD}(a,b)$ e poi moltiplicare per $b$.

---

> ✅ Il MCD di $a$ e $b$ è il massimo divisore comune (con $\text{MCD}(n,0)=n$, $\text{MCD}(0,0)$ non definito). L'algoritmo euclideo lo calcola in $O(\log \min(a,b))$ passi. L'identità di Bézout garantisce $d = ax + by$ con $x,y \in \mathbb{Z}$, calcolabili risalendo l'algoritmo. Il mcm si ottiene dai massimi esponenti nella fattorizzazione o dalla formula $\text{mcm}(a,b) = ab/\text{MCD}(a,b)$.
