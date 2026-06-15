# **M1 UD3 Lezione 7 - Esercizi sulle congruenze**

### **1. Esercizio 1 — Verifica di una congruenza di potenze**

**Problema:** È vero che $443 \equiv 84^{66} \pmod{15}$?

**Svolgimento:**

Prima riduciamo i coefficienti in $\mathbb{Z}_{15}$:

$$443 \bmod 15 = 443 - 29 \cdot 15 = 443 - 435 = 8$$
$$84 \bmod 15 = 84 - 5 \cdot 15 = 84 - 75 = 9$$

Quindi la domanda diventa: $8 \equiv 9^{66} \pmod{15}$?

Calcoliamo $9^{66} \bmod 15$:

$$9^2 = 81 \equiv 81 - 5 \cdot 15 = 6 \pmod{15}$$
$$9^{66} = (9^2)^{33} \equiv 6^{33} \pmod{15}$$
$$6^2 = 36 \equiv 6 \pmod{15} \quad \Rightarrow \quad 6^k \equiv 6 \pmod{15} \text{ per ogni } k \geq 1$$
$$6^{33} \equiv 6 \pmod{15}$$

Poiché $8 \not\equiv 6 \pmod{15}$, la risposta è **NO**.

---

**Problema collegato:** Calcolare se $14 \equiv 83^{129} \pmod{8}$.

Riduciamo:
$$83 \equiv 3 \pmod{8}$$

$\varphi(8) = 4$ (i coprimi con 8 sono $1, 3, 5, 7$).

$\text{MCD}(3, 8) = 1$, vale Eulero-Fermat: $3^4 \equiv 1 \pmod 8$.

$$129 = 4 \cdot 32 + 1 \quad \Rightarrow \quad 83^{129} \equiv 3^{129} = (3^4)^{32} \cdot 3 \equiv 1^{32} \cdot 3 = 3 \pmod{8}$$

$$14 \bmod 8 = 6$$

Poiché $6 \neq 3$, $14 \not\equiv 83^{129} \pmod{8}$.

---

### **2. Esercizio 2 — Applicazione del teorema di Fermat**

**Problema:** Sia $z \in \mathbb{Z}$ con $\text{MCD}(z, 47) = 1$. Quanto vale $z^{95} \bmod 47$?

**Svolgimento:**

$47$ è primo. Per il teorema di Fermat:

$$z^{46} \equiv 1 \pmod{47}$$

Dividiamo $95$ per $46$:

$$95 = 46 \cdot 2 + 3$$

Quindi:

$$z^{95} = z^{46 \cdot 2} \cdot z^3 = (z^{46})^2 \cdot z^3 \equiv 1^2 \cdot z^3 = z^3 \pmod{47}$$

**Risposta:** $z^{95} \equiv z^3 \pmod{47}$.

---

### **3. Esercizio 3 — Sistema di congruenze**

**Problema:** Risolvere il sistema:

$$\begin{cases} 32x \equiv 4356 \pmod{5} \\ 12x \equiv 319 \pmod{7} \end{cases}$$

**Svolgimento:**

**Equazione 1:** Ridurre i coefficienti.

$$32 \bmod 5 = 2, \qquad 4356 \bmod 5 = 1 \quad (\text{somma cifre: } 4+3+5+6=18, 18 \bmod 5 = 3... \text{ ricalcolo: } 4356 = 871 \cdot 5 + 1)$$

La prima equazione diventa $2x \equiv 1 \pmod{5}$.

$2^{-1}$ in $\mathbb{Z}_5$: $2 \cdot 3 = 6 \equiv 1 \pmod{5}$, quindi $2^{-1} \equiv 3$.

$$x \equiv 3 \cdot 1 = 3 \pmod{5}$$

**Equazione 2:** Ridurre i coefficienti.

$$12 \bmod 7 = 5, \qquad 319 \bmod 7 = ?$$
$$319 = 45 \cdot 7 + 4 \quad \Rightarrow \quad 319 \equiv 4 \pmod{7}$$

La seconda equazione diventa $5x \equiv 4 \pmod{7}$.

$5^{-1}$ in $\mathbb{Z}_7$: $5 \cdot 3 = 15 \equiv 1 \pmod{7}$, quindi $5^{-1} \equiv 3$.

$$x \equiv 3 \cdot 4 = 12 \equiv 5 \pmod{7}$$

**Sistema ridotto:**

$$\begin{cases} x \equiv 3 \pmod{5} \\ x \equiv 5 \pmod{7} \end{cases}$$

$\text{MCD}(5, 7) = 1$: per il TCR esiste un'unica soluzione modulo $35$.

Dalla prima: $x = 3 + 5k$. Sostituiamo nella seconda:

$$3 + 5k \equiv 5 \pmod{7} \quad \Rightarrow \quad 5k \equiv 2 \pmod{7}$$

$$5^{-1} \equiv 3 \pmod{7} \quad \Rightarrow \quad k \equiv 3 \cdot 2 = 6 \pmod{7}$$

$$x = 3 + 5 \cdot 6 = 33$$

**Verifica:** $33 \bmod 5 = 3$ ✓, $33 \bmod 7 = 5$ ✓.

**Soluzione:** $x \equiv 33 \pmod{35}$, cioè $x \in \{33, 68, 103, \ldots\}$.

---

### **4. Esercizio 4 — Ore dopo**

**Problema:** Che ore sono 539 ore dopo le 14?

**Svolgimento:**

Un orologio lavora modulo 24:

$$539 \bmod 24 = ?$$

$$539 = 22 \cdot 24 + 11 \quad \Rightarrow \quad 539 \equiv 11 \pmod{24}$$

Ore: $14 + 11 = 25 \equiv 1 \pmod{24}$.

**Risposta:** Sono le **01:00** (1 di notte).

---

### **5. Esercizio 5 — Sistema con parametro**

**Problema:** Determinare il più piccolo $k$ positivo per cui il sistema:

$$\begin{cases} x \equiv 7 \pmod{5} \\ x \equiv 10 \pmod{3} \\ x \equiv k \pmod{15} \end{cases}$$

ammette soluzioni, e indicare la più piccola soluzione positiva.

**Svolgimento:**

Riduciamo le prime due:

$$x \equiv 7 \equiv 2 \pmod{5}$$
$$x \equiv 10 \equiv 1 \pmod{3}$$

Dalla prima: $x = 2 + 5h$. Sostituiamo nella seconda:

$$2 + 5h \equiv 1 \pmod{3} \quad \Rightarrow \quad 5h \equiv -1 \equiv 2 \pmod{3}$$

$5 \equiv 2 \pmod 3$, quindi $2h \equiv 2 \pmod 3$, cioè $h \equiv 1 \pmod 3$.

$$x = 2 + 5(1 + 3j) = 7 + 15j$$

Quindi $x \equiv 7 \pmod{15}$.

**Condizione sulla terza equazione:** $x \equiv k \pmod{15}$ deve essere compatibile con $x \equiv 7 \pmod{15}$.

Poiché tutte e tre le congruenze devono essere soddisfatte simultaneamente e $\text{mcm}(5, 3, 15) = 15$, si deve avere $k \equiv 7 \pmod{15}$.

Il più piccolo $k$ positivo è $k = 7$.

**Soluzione minima positiva:** $x = 7 + 15 \cdot 0 = 7$.

**Verifica:** $7 \bmod 5 = 2 \equiv 7 \pmod 5$ ✓, $7 \bmod 3 = 1 \equiv 10 \pmod 3$ ✓, $7 \bmod 15 = 7$ ✓.

---

### **6. Esercizio 6 — Criteri di divisibilità per 23**

**Problema:** Sapendo che $23 \cdot 3 = 69$ e $70 \equiv 1 \pmod{69}$, costruire un algoritmo di divisibilità per 23 e verificare se $23156$ è divisibile per 23.

**Soluzione:**

$69 = 70 - 1 \equiv 0 \pmod{69}$, quindi $70 \equiv 1 \pmod{69}$.

$\text{MCD}(7, 23) = 1$, quindi possiamo moltiplicare per $7$:

$$7x \equiv 0 \pmod{23} \iff x \equiv 0 \pmod{23}$$

Poiché $70 \equiv 1 \pmod{69}$ e il modulo è $23 \cdot 3 = 69$... riscriviamo: moltiplicare $x$ per 7 non cambia la divisibilità per 23.

**Algoritmo:** Dato $x = 10n + a_0$, si ha $7x = 70n + 7a_0 \equiv n + 7a_0 \pmod{69}$.

Si rimuove l'ultima cifra e si aggiunge al resto il suo settuplo; si ripete finché il risultato è piccolo.

**Verifica di $23156$:**

$$23156 = 2315 \cdot 10 + 6 \quad \Rightarrow \quad 2315 + 7 \cdot 6 = 2315 + 42 = 2357$$
$$2357 = 235 \cdot 10 + 7 \quad \Rightarrow \quad 235 + 7 \cdot 7 = 235 + 49 = 284$$
$$284 = 28 \cdot 10 + 4 \quad \Rightarrow \quad 28 + 7 \cdot 4 = 28 + 28 = 56$$

$56 \bmod 23 = 56 - 2 \cdot 23 = 10 \neq 0$.

**$23156$ non è divisibile per 23.**

---

### **7. Esercizio 7 — Criteri di divisibilità per 660**

**Problema:** Stabilire, usando i criteri di divisibilità, se $N = 718850$ è divisibile per 660, e indicare tutti i divisori di 660 per cui $N$ è divisibile.

**Fattorizzazione:** $660 = 2^2 \cdot 3 \cdot 5 \cdot 11 = 4 \cdot 3 \cdot 5 \cdot 11$.

**Criteri applicati a $N = 718850$:**

- **Per 2:** Ultima cifra $= 0$ (pari). $2 \mid N$. ✓
- **Per 4:** Ultime 2 cifre $= 50$. $50 = 12 \cdot 4 + 2$. $4 \nmid N$. ✗
- **Per 3:** Somma cifre $= 7+1+8+8+5+0 = 29$. $29 \bmod 3 = 2$. $3 \nmid N$. ✗
- **Per 5:** Ultima cifra $= 0$. $5 \mid N$. ✓
- **Per 11:** Somma alternata $= 0 - 5 + 8 - 8 + 1 - 7 = -11$. $11 \mid N$. ✓

**Divisori di 660 per cui $N$ è divisibile:** Si controllano sistematicamente i divisori di $660 = 2^2 \cdot 3 \cdot 5 \cdot 11$.

$N$ è divisibile per $2$ e $5$ e $11$, ma non per $4$ né per $3$.

Divisori di 660 che dividono $N$: $1, 2, 5, 10, 11, 22, 55, 110$.

$N = 718850 = 2 \cdot 5 \cdot 11 \cdot 6535 = 110 \cdot 6535$.

**$660 \nmid N$** poiché $4 \nmid N$ e $3 \nmid N$.

---

### **8. Esercizio 8 — Quadrato perfetto**

**Problema:** Dimostrare che un numero le cui cifre in ordine crescente sono $1, 1, 2, 4, 4$ più un numero sconosciuto di zeri non può essere un quadrato perfetto.

**Soluzione:**

La somma delle cifre del numero è $1+1+2+4+4 = 12$ (gli zeri non contribuiscono).

$12 \equiv 0 \pmod{3}$, quindi il numero è divisibile per $3$.

Ma la somma delle cifre è $12$ che non è divisibile per $9$, quindi il numero è divisibile per $3$ ma **non per $9$**.

> 📌 Se $n$ è divisibile per $3$ ma non per $9$, e se $n = m^2$, allora $3 \mid m$ (poiché $3 \mid m^2$). Ma allora $9 \mid m^2 = n$, contraddizione.

Quindi il numero non può essere un quadrato perfetto. $\square$

---

> ✅ Le congruenze si risolvono riducendo coefficienti, usando Fermat/Eulero-Fermat per ridurre gli esponenti, applicando il TCR per sistemi con moduli coprimi. I criteri di divisibilità permettono di verificare rapidamente divisori come $4$, $3$, $5$, $11$ sulle cifre. Un numero divisibile per $3$ ma non per $9$ non può essere un quadrato perfetto.
