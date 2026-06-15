# **M1 UD3 Lezione 5 - Criteri di divisibilità**

### **1. L'idea generale**

Dato un numero $x$ con cifre $a_n a_{n-1} \ldots a_1 a_0$ (in base 10), si ha:

$$x = a_0 \cdot 10^0 + a_1 \cdot 10^1 + a_2 \cdot 10^2 + \cdots + a_n \cdot 10^n$$

Per verificare la divisibilità di $x$ per un divisore $d$, si calcola $x \bmod d$. Grazie alla compatibilità della congruenza con la moltiplicazione:

$$x \equiv r_0 a_0 + r_1 a_1 + r_2 a_2 + \cdots + r_n a_n \pmod{d}$$

dove $r_k = 10^k \bmod d$ è il resto della $k$-esima potenza di $10$ diviso $d$.

$x$ è divisibile per $d$ se e solo se questa somma è congrua a $0$ modulo $d$.

---

### **2. Divisibilità per 3 e per 9**

$$10 \equiv 1 \pmod{3} \quad \text{e} \quad 10 \equiv 1 \pmod{9}$$

Quindi $10^k \equiv 1^k = 1$ per ogni $k$.

> 📌 **Criterio per 3:** $x$ è divisibile per $3$ se e solo se la **somma delle sue cifre** è divisibile per $3$.

> 📌 **Criterio per 9:** $x$ è divisibile per $9$ se e solo se la **somma delle sue cifre** è divisibile per $9$.

$$x \equiv a_0 + a_1 + \cdots + a_n \pmod{3} \quad \text{(e modulo 9)}$$

**Esempio:** $x = 123456$. Somma cifre: $1+2+3+4+5+6 = 21$. $21 = 3 \cdot 7$ divisibile per $3$ ma non per $9$. Quindi $3 \mid 123456$ ma $9 \nmid 123456$.

---

### **3. Divisibilità per 2ⁿ e per 5ⁿ**

$$10 = 2 \cdot 5, \quad 10^k = 2^k \cdot 5^k$$

Quindi $10^k \equiv 0 \pmod{2^n}$ per $k \geq n$, e analogamente per $5^n$.

> 📌 **Criterio per $2^n$:** $x$ è divisibile per $2^n$ se e solo se il **numero formato dalle ultime $n$ cifre** di $x$ è divisibile per $2^n$.

> 📌 **Criterio per $5^n$:** $x$ è divisibile per $5^n$ se e solo se il **numero formato dalle ultime $n$ cifre** di $x$ è divisibile per $5^n$.

**Casi particolari:**
- Divisibilità per $2$: guarda l'ultima cifra (pari o dispari)
- Divisibilità per $4$: guarda le ultime 2 cifre
- Divisibilità per $8$: guarda le ultime 3 cifre
- Divisibilità per $5$: ultima cifra in $\{0, 5\}$
- Divisibilità per $25$: ultime 2 cifre formano un multiplo di $25$

---

### **4. Divisibilità per 11**

$$10 \equiv -1 \pmod{11}$$

Quindi $10^k \equiv (-1)^k \pmod{11}$.

$$x \equiv a_0 - a_1 + a_2 - a_3 + \cdots \pmod{11}$$

> 📌 **Criterio per 11:** $x$ è divisibile per $11$ se e solo se la **somma alternata delle cifre** (con segni alternati, partendo dall'unità con segno $+$) è divisibile per $11$.

**Esempio:** $x = 918082$.

Cifre da destra: $2, 8, 0, 8, 1, 9$.

Somma alternata: $2 - 8 + 0 - 8 + 1 - 9 = -22$.

$-22 \equiv 0 \pmod{11}$, quindi $11 \mid 918082$. ✓

---

### **5. Divisibilità per 7**

Le potenze di $10$ modulo $7$:

$$10^0 \equiv 1, \quad 10^1 \equiv 3, \quad 10^2 \equiv 2, \quad 10^3 \equiv 6, \quad 10^4 \equiv 4, \quad 10^5 \equiv 5, \quad 10^6 \equiv 1, \ldots$$

La sequenza è periodica di periodo $6$: $1, 3, 2, 6, 4, 5, 1, 3, 2, \ldots$

Usando i rappresentanti negativi più piccoli in valore assoluto: $1, 3, 2, -1, -3, -2$ (ripetuto).

$$x \equiv a_0 + 3a_1 + 2a_2 - a_3 - 3a_4 - 2a_5 + a_6 + \cdots \pmod{7}$$

> ⚠️ Il criterio per 7 è meno immediato degli altri poiché richiede di pesare le cifre con coefficienti variabili ($1, 3, 2, -1, -3, -2$ in ciclo). Non è comunemente memorizzato, ma si deriva sistematicamente.

---

### **6. Divisibilità per qualsiasi d: schema generale**

Per qualunque divisore $d$, si possono calcolare i resti $r_k = 10^k \bmod d$ e applicare la formula:

$$x \equiv \sum_{k=0}^{n} r_k a_k \pmod{d}$$

La successione dei resti è **periodica** (poiché i valori sono limitati a $\{0, 1, \ldots, d-1\}$, il periodo è al più $d$).

> ⚠️ Questo schema funziona bene quando il periodo è breve (come per $3, 9, 11$) o quando molti resti sono $0$ (come per $2^n, 5^n$). Per $d = 19$, il periodo è $18$: si ottengono ben $18$ coefficienti diversi, rendendo il criterio poco pratico.

---

### **7. Metodo alternativo per divisori con periodo lungo**

Per divisori come $19$, $13$, $31$, quando la sequenza dei resti è lunga, esiste un metodo iterativo più efficiente.

**Chiave:** Si cerca un moltiplicatore $k$ tale che $10k \equiv \pm 1 \pmod{d}$, con $\text{MCD}(k, d) = 1$.

**Teorema usato:** Se $d \mid bc$ e $\text{MCD}(d, b) = 1$, allora $d \mid c$. Quindi $d \mid x$ se e solo se $d \mid kx$ (con $k$ coprimo con $d$).

#### **7.1. Criterio iterativo per 19**

$20 \equiv 1 \pmod{19}$, quindi moltiplicando $x = 10n + a_0$ per $2$:

$$2x = 20n + 2a_0 \equiv n + 2a_0 \pmod{19}$$

L'algoritmo è: rimuovi l'ultima cifra, aggiungi al resto il doppio della cifra rimossa. Ripeti finché il risultato è piccolo, poi verifica la divisibilità per $19$.

#### **7.2. Esempio: divisibilità per 13**

$13 \cdot 3 = 39$ e $40 \equiv 1 \pmod{39}$. Poiché $\text{MCD}(4, 39) = 1$: moltiplicare per $4$.

$x = 10n + a_0 \Rightarrow 4x = 40n + 4a_0 \equiv n + 4a_0 \pmod{39}$.

Algoritmo: rimuovi l'ultima cifra e aggiungi al resto il suo quadruplo.

**Esempio su $7438927$:**

| Numero | Ultima cifra | Operazione | Risultato |
|--------|-------------|------------|-----------|
| 7438927 | 7 | $743892 + 4 \cdot 7 = 743892 + 28$ | 743920 |
| 743920 | 0 | $74392 + 4 \cdot 0$ | 74392 |
| 74392 | 2 | $7439 + 4 \cdot 2 = 7439 + 8$ | 7447 |
| 7447 | 7 | $744 + 4 \cdot 7 = 744 + 28$ | 772 |
| 772 | 2 | $77 + 4 \cdot 2 = 77 + 8$ | 85 |

$85$ non è divisibile per $39$ ($85 = 2 \cdot 39 + 7$), quindi $7438927$ non è divisibile per $13$.

#### **7.3. Esempio: divisibilità per 31**

$30 \equiv -1 \pmod{31}$, $\text{MCD}(3, 31) = 1$: moltiplicare per $3$.

$3x = 30n + 3a_0 \equiv -n + 3a_0 \pmod{31}$.

Algoritmo: rimuovi l'ultima cifra, sottrai il resto e aggiungi il triplo della cifra.

**Esempio su $7438927$:**

| Numero | Ultima cifra | Operazione | Risultato |
|--------|-------------|------------|-----------|
| 7438927 | 7 | $-743892 + 3 \cdot 7 = -743892 + 21$ | $-743871$ |
| −743871 | 1 | $74387 + 3 \cdot 1 = -74387 + 3$ | $-74384$ |
| −74384 | 4 | $-7438 + 3 \cdot 4 = -7438 + 12$ | $-7426$ |
| −7426 | 6 | $-742 + 3 \cdot 6 = -742 + 18$ | $-724$ |
| −724 | 4 | $-72 + 3 \cdot 4 = -72 + 12$ | $-60$ |

$-60$ non è divisibile per $31$ ($60 = 1 \cdot 31 + 29$), quindi $7438927$ non è divisibile per $31$.

> 💡 Il segno di $x$ non influenza la divisibilità: se $31 \mid x$ allora $31 \mid (-x)$. Quindi si può ignorare il segno nel test finale.

---

> ✅ I criteri di divisibilità si basano sulle proprietà delle potenze di $10$ modulo $d$. Per $3$ e $9$: somma delle cifre. Per $2^n$ e $5^n$: ultime $n$ cifre. Per $11$: somma alternata. Per $7$: pesi $1,3,2,-1,-3,-2$ ciclici. Per divisori con periodo lungo (come $13$, $19$, $31$): metodo iterativo basato su un moltiplicatore $k$ che porta $10k \equiv \pm 1 \pmod{d}$.
