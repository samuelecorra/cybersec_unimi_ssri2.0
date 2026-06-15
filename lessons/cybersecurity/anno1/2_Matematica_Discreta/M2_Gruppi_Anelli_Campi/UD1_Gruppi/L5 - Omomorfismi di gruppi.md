# **M2 UD1 Lezione 5 - Omomorfismi di gruppi**

### **1. Definizione di omomorfismo**

> 📌 **Definizione:** Dati due gruppi $(G, \circledast)$ e $(H, \bullet)$, un'applicazione $f: G \to H$ è un **omomorfismo di gruppi** se:
> $$\forall a, b \in G: \quad f(a \circledast b) = f(a) \bullet f(b)$$

L'omomorfismo "rispetta" l'operazione: il risultato di operare in $G$ e poi mappare è lo stesso che mappare i fattori e poi operare in $H$.

---

### **2. Tipi di omomorfismi**

| Nome | Proprietà | Significato |
|------|-----------|-------------|
| **Monomorfismo** | $f$ iniettiva | struttura di $G$ si "immerge" in $H$ |
| **Epimorfismo** | $f$ suriettiva | ogni elemento di $H$ è immagine |
| **Isomorfismo** | $f$ biiettiva | $G$ e $H$ hanno struttura identica |
| **Endomorfismo** | $G = H$ | omomorfismo di un gruppo in sé stesso |
| **Automorfismo** | $G = H$ e $f$ biiettiva | isomorfismo di un gruppo con sé stesso |

> 💡 Due gruppi isomorfi sono "lo stesso gruppo" a meno di rinominare gli elementi. Gruppi con ordini diversi non possono essere isomorfi.

---

### **3. Proprietà degli omomorfismi**

#### **3.1. Il neutro va nel neutro**

> 📌 **Proprietà 1:** Se $f: G \to H$ è omomorfismo, $u_G$ è il neutro di $G$ e $u_H$ è il neutro di $H$, allora:
> $$f(u_G) = u_H$$

*Dimostrazione:* Per ogni $a \in G$:
$$f(a) = f(a \circledast u_G) = f(a) \bullet f(u_G)$$
Quindi $f(a) \bullet f(u_G) = f(a) \bullet u_H$, e per la legge di cancellazione (valida in un gruppo): $f(u_G) = u_H$. $\square$

#### **3.2. L'inverso va nell'inverso**

> 📌 **Proprietà 2:** Per ogni $g \in G$:
> $$f(g^{-1}) = [f(g)]^{-1}$$

*Dimostrazione:*
$$f(g) \bullet f(g^{-1}) = f(g \circledast g^{-1}) = f(u_G) = u_H$$
Quindi $f(g^{-1})$ è l'inverso di $f(g)$ in $H$, cioè $f(g^{-1}) = [f(g)]^{-1}$. $\square$

#### **3.3. I sottogruppi vanno in sottogruppi**

> 📌 **Proprietà 3:** Se $K \leq G$ è un sottogruppo di $G$, allora $f(K) \leq H$ è un sottogruppo di $H$.

---

### **4. Immagine di un omomorfismo**

> 📌 **Definizione:** L'**immagine** di $f$ è:
> $$\text{Im}(f) = f(G) = \{h \in H \mid \exists g \in G: f(g) = h\}$$

> 📌 **Teorema:** $\text{Im}(f)$ è un **sottogruppo** di $H$.

*Dimostrazione:* Siano $h = f(g)$ e $h' = f(g')$ due elementi di $\text{Im}(f)$. Allora:
$$h' \bullet h^{-1} = f(g') \bullet f(g)^{-1} = f(g') \bullet f(g^{-1}) = f(g' \circledast g^{-1}) \in f(G)$$
Per il criterio dei sottogruppi, $\text{Im}(f) \leq H$. $\square$

---

### **5. Nucleo di un omomorfismo**

> 📌 **Definizione:** Il **nucleo** (o **kernel**) di $f$ è:
> $$\ker(f) = \{g \in G \mid f(g) = u_H\}$$

> 📌 **Teorema:** $\ker(f)$ è un **sottogruppo** di $G$.

*Dimostrazione:* Siano $g, g' \in \ker(f)$. Allora:
$$f(g' \circledast g^{-1}) = f(g') \bullet f(g^{-1}) = u_H \bullet u_H^{-1} = u_H \bullet u_H = u_H$$
Quindi $g' \circledast g^{-1} \in \ker(f)$. Per il criterio dei sottogruppi, $\ker(f) \leq G$. $\square$

---

### **6. Legame tra gli ordini**

> 📌 **Teorema:**
> $$|\ker(f)| \times |f(G)| = |G|$$

Questo è un caso particolare del teorema di Lagrange applicato al nucleo come sottogruppo.

> 💡 Se $|G| = n$ e si vuole classificare gli omomorfismi $f: G \to H$, occorre trovare le fattorizzazioni $n = s \times r$ con $s = |\ker(f)|$ e $r = |f(G)|$. Per ciascuna fattorizzazione valida occorre poi costruire concretamente la mappa.

---

### **7. Esempio: omomorfismi da $(\mathbb{Z}_6, +)$ a $(\mathbb{Z}_5^*, \times)$**

**Dati:** $|G| = |\mathbb{Z}_6| = 6$, $|H| = |\mathbb{Z}_5^*| = 4$.

Per il teorema: $|\ker(f)| \times |f(G)| = 6$.

Le fattorizzazioni $6 = s \times r$ con $r \mid 4$ (poiché $f(G)$ deve essere sottogruppo di $H$ di ordine che divide $4$) sono:

- $r = 1$, $s = 6$: omomorfismo **triviale** $f(g) = 1$ per ogni $g$.
- $r = 2$, $s = 3$: $|f(G)| = 2$, quindi $f(G) = \{[1], [4]\}$ (l'unico sottogruppo di ordine $2$ in $\mathbb{Z}_5^*$).
- $r = 6$, $s = 1$: non possibile poiché $r = 6 \nmid 4$.
- $r = 3$, $s = 2$: non possibile poiché $\mathbb{Z}_5^*$ non ha sottogruppi di ordine $3$ (non divide $4$).

**Omomorfismo con $r = 2$:**

$\ker(f)$ ha ordine $3$, quindi $\ker(f) = \{[0], [2], [4]\}$ (l'unico sottogruppo di ordine $3$ in $\mathbb{Z}_6$).

L'omomorfismo manda i **multipli di $2$** in $[1]$ e gli **elementi non nel nucleo** in $[4]$:

$$f([0]) = f([2]) = f([4]) = [1], \qquad f([1]) = f([3]) = f([5]) = [4]$$

**Verifica:** $f([1] + [5]) = f([0]) = [1] = [4] \times [4] = f([1]) \times f([5])$. ✓

---

> ✅ Un omomorfismo $f: G \to H$ rispetta le operazioni: manda il neutro nel neutro e gli inversi negli inversi. L'immagine e il nucleo sono entrambi sottogruppi. La relazione $|\ker(f)| \cdot |f(G)| = |G|$ vincola le fattorizzazioni possibili per classificare tutti gli omomorfismi tra gruppi finiti.
