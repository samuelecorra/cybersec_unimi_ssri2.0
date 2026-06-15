# **M1 UD3 Lezione 1 - Congruenza modulo n**

### **1. Divisione con resto in ℤ**

Prima di definire la congruenza, estendiamo la divisione euclidea agli interi.

> 📌 **Teorema della divisione euclidea in $\mathbb{Z}$:** Per ogni $x \in \mathbb{Z}$ e $y \in \mathbb{Z}$ con $y \neq 0$, esistono unici $q \in \mathbb{Z}$ e $r \in \mathbb{N}$ tali che:
> $$x = qy + r, \qquad 0 \leq r < |y|$$

La differenza rispetto alla divisione in $\mathbb{N}$: il quoziente $q$ può essere negativo, ma il **resto $r$ è sempre non negativo** (tra $0$ e $|y|-1$).

**Esempi:**
- $17 = 3 \cdot 5 + 2$: quoziente $3$, resto $2$
- $-17 = (-4) \cdot 5 + 3$: quoziente $-4$, resto $3$ (non $-2$, perché il resto deve essere $\geq 0$)

> ⚠️ Molti linguaggi di programmazione usano la divisione con resto "troncata verso zero" (ad es. in C, $-17 \% 5 = -2$), che non corrisponde alla divisione euclidea quando il dividendo è negativo. In matematica il resto è sempre $\geq 0$.

---

### **2. Definizione di congruenza**

> 📌 **Definizione:** Dati $a, b \in \mathbb{Z}$ e $n \in \mathbb{N}$ con $n \geq 1$, si dice che $a$ è **congruo** a $b$ modulo $n$, e si scrive $a \equiv b \pmod{n}$, se:
> $$a \bmod n = b \bmod n$$
> (cioè $a$ e $b$ hanno lo stesso resto nella divisione per $n$).

**Caratterizzazione equivalente:**

$$a \equiv b \pmod{n} \iff n \mid (a - b)$$

*Dimostrazione:* Se $a = qn + r$ e $b = q'n + r$ (stesso resto), allora $a - b = (q-q')n$, dunque $n \mid (a-b)$. Viceversa, se $n \mid (a-b)$, allora $a$ e $b$ hanno lo stesso resto.

> 💡 La congruenza modulo $n$ è una **relazione di equivalenza** su $\mathbb{Z}$ (riflessiva, simmetrica, transitiva), come dimostrato nella UD2. Partiziona $\mathbb{Z}$ in $n$ classi: $[0], [1], \ldots, [n-1]$.

---

### **3. Esempi pratici di congruenza**

La congruenza emerge naturalmente in molti contesti quotidiani:

**Orologio:** Il quadrante di un orologio lavora modulo $12$. Le ore $3$ e $15$ indicano la stessa posizione perché $15 \equiv 3 \pmod{12}$.

**Giorni della settimana:** I giorni si ripetono modulo $7$. Se oggi è lunedì (giorno $1$), tra $20$ giorni sarà: $20 \bmod 7 = 6$, cioè sabato.

**Angoli:** Gli angoli in gradi si possono considerare modulo $360$. $450° \equiv 90° \pmod{360}$.

---

### **4. Esercizi di verifica**

**Esercizio 1:** $98 \equiv 7 \pmod{13}$?

$$98 - 7 = 91 = 7 \cdot 13 \quad \Rightarrow \quad 13 \mid 91 \quad \Rightarrow \quad 98 \equiv 7 \pmod{13} \quad ✓$$

In alternativa: $98 = 7 \cdot 13 + 7$, quindi $98 \bmod 13 = 7$. ✓

**Esercizio 2:** $12 \equiv 85 \pmod{7}$?

$$12 \bmod 7 = 5, \qquad 85 \bmod 7 = ?$$
$$85 = 12 \cdot 7 + 1 \quad \Rightarrow \quad 85 \bmod 7 = 1$$
$$5 \neq 1 \quad \Rightarrow \quad 12 \not\equiv 85 \pmod{7} \quad ✗$$

**Esercizio 3:** $77 \equiv 5 \pmod{72}$?

$$77 - 5 = 72 = 1 \cdot 72 \quad \Rightarrow \quad 72 \mid 72 \quad ✓$$

---

### **5. Proprietà della congruenza**

Le due proprietà di compatibilità fondamentali:

> 📌 Se $a \equiv b \pmod{n}$ e $c \equiv d \pmod{n}$, allora:
> - $a + c \equiv b + d \pmod{n}$
> - $a \cdot c \equiv b \cdot d \pmod{n}$

*Dimostrazione per la somma:* $n \mid (a-b)$ e $n \mid (c-d)$, quindi $n \mid (a-b)+(c-d) = (a+c)-(b+d)$.

*Dimostrazione per il prodotto:* $ac - bd = ac - bc + bc - bd = c(a-b) + b(c-d)$. Poiché $n \mid (a-b)$ e $n \mid (c-d)$, si ha $n \mid ac - bd$.

> ⚠️ La congruenza è compatibile con la somma e il prodotto, ma **non con la divisione** in generale. Dividere entrambi i membri di una congruenza per un comune divisore può alterare il modulo (vedremo questo nelle congruenze lineari).

---

> ✅ La congruenza $a \equiv b \pmod{n}$ equivale a $n \mid (a-b)$ e a "stesso resto nella divisione per $n$". È una relazione di equivalenza che partiziona $\mathbb{Z}$ in $n$ classi. È compatibile con somma e prodotto: si possono sostituire i valori con i loro resti senza alterare il risultato delle operazioni.
