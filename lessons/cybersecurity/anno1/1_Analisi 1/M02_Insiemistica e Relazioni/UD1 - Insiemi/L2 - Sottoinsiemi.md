# **M2 UD1 Lezione 2 - Sottoinsiemi**

### **1. Introduzione**

Il concetto di sottoinsieme formalizza l’idea che tutti gli elementi di un insieme siano contenuti in un altro insieme.

Per esempio, i numeri naturali sono anche numeri interi:

$$
\mathbb{N}\subseteq\mathbb{Z}
$$

Questa relazione di contenimento è una delle strutture più usate in Analisi: domini più piccoli, intervalli contenuti in altri intervalli, insiemi di soluzioni, classi di funzioni.

---

### **2. Definizione di sottoinsieme**

Si dice che $A$ è un **sottoinsieme** di $B$ se ogni elemento di $A$ appartiene anche a $B$.

Si scrive:

$$
A\subseteq B
$$

e significa:

$$
\forall x,\ x\in A \Rightarrow x\in B
$$

Esempio:

$$
\{1,2\}\subseteq\{1,2,3\}
$$

perché ogni elemento del primo insieme appartiene al secondo.

---

### **3. Inclusione propria**

Se:

$$
A\subseteq B
$$

ma:

$$
A\neq B
$$

allora $A$ è un **sottoinsieme proprio** di $B$.

Si può scrivere:

$$
A\subset B
$$

Esempio:

$$
\{1,2\}\subset\{1,2,3\}
$$

perché $3$ appartiene al secondo insieme ma non al primo.

---

### **4. Insieme vuoto e insieme stesso**

Per ogni insieme $A$ vale:

$$
\varnothing\subseteq A
$$

perché non esiste alcun elemento dell’insieme vuoto che violi la condizione di appartenenza ad $A$.

Inoltre:

$$
A\subseteq A
$$

perché ogni elemento di $A$ appartiene ad $A$.

> 💡 L’inclusione $\varnothing\subseteq A$ è spesso controintuitiva, ma è vera perché una proprietà universale sugli elementi dell’insieme vuoto non trova controesempi.

---

### **5. Dimostrare un’inclusione**

Per dimostrare:

$$
A\subseteq B
$$

si prende un elemento generico $x\in A$ e si mostra che necessariamente $x\in B$.

Esempio:

Dimostriamo:

$$
\{x\in\mathbb{R}: x>2\}\subseteq\{x\in\mathbb{R}: x>0\}
$$

Prendiamo $x>2$. Allora certamente:

$$
x>0
$$

quindi ogni elemento del primo insieme appartiene al secondo.

---

### **6. Uguaglianza tramite doppia inclusione**

Per dimostrare:

$$
A=B
$$

si può dimostrare:

$$
A\subseteq B
$$

e:

$$
B\subseteq A
$$

Questa tecnica si chiama **doppia inclusione**.

È molto utile quando gli insiemi sono descritti da proprietà diverse ma rappresentano gli stessi elementi.

---

### **7. Insieme delle parti**

L’insieme di tutti i sottoinsiemi di $A$ si chiama **insieme delle parti** e si indica con:

$$
\mathcal{P}(A)
$$

Se:

$$
A=\{1,2\}
$$

allora:

$$
\mathcal{P}(A)=\{\varnothing,\{1\},\{2\},\{1,2\}\}
$$

Se $A$ ha $n$ elementi, allora $\mathcal{P}(A)$ ha:

$$
2^n
$$

elementi.

---

### **8. Esempio con intervalli**

Consideriamo:

$$
A=(0,1)
$$

e:

$$
B=[0,1]
$$

Allora:

$$
A\subseteq B
$$

perché se:

$$
0<x<1
$$

allora certamente:

$$
0\le x\le 1
$$

L’inclusione è propria, perché:

$$
0\in B
$$

ma:

$$
0\notin A
$$

---

### **9. Errori tipici**

#### **9.1 Confondere appartenenza e inclusione**

Se:

$$
A=\{1,2,3\}
$$

allora:

$$
1\in A
$$

ma:

$$
\{1\}\subseteq A
$$

Il numero $1$ è un elemento; l’insieme $\{1\}$ è un sottoinsieme.

#### **9.2 Scrivere $\varnothing\in A$ senza motivo**

Il fatto che:

$$
\varnothing\subseteq A
$$

non significa che:

$$
\varnothing\in A
$$

L’insieme vuoto appartiene ad $A$ solo se è esplicitamente uno degli elementi di $A$.

---

### **10. Riepilogo**

> ✅ $A\subseteq B$ significa che ogni elemento di $A$ è anche elemento di $B$.

Punti essenziali:

- l’inclusione confronta insiemi;
- l’appartenenza confronta elemento e insieme;
- $\varnothing\subseteq A$ per ogni $A$;
- $A=B$ si può dimostrare con doppia inclusione;
- $\mathcal{P}(A)$ è l’insieme di tutti i sottoinsiemi di $A$.
