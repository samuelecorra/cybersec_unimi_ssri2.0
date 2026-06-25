# **Lezione 2: Aritmetica modulare e numeri primi**

### **1. Numeri primi**

Un numero intero $p > 1$ è detto **primo** se e solo se i suoi unici divisori positivi sono **1 e se stesso**.  
Esempi di numeri primi:  
$2, 3, 5, 7, 11, 13, 17, 19, \dots$

#### **Teorema fondamentale dell’aritmetica**

Ogni intero composto $n > 1$ può essere scritto in modo **unico** (a meno dell’ordine dei fattori) come prodotto di potenze di numeri primi:

$$
n = p_1^{e_1} \cdot p_2^{e_2} \cdot \dots \cdot p_k^{e_k}
$$

dove ogni $p_i$ è primo e ogni $e_i$ è un intero positivo.

---

### **2. Teorema della divisione**

Per ogni coppia di numeri $a \in \mathbb{Z}$ e $n \in \mathbb{Z}$, con $n \ne 0$, esiste un’unica coppia $(q, r)$ tale che:

$$
a = q \cdot n + r, \quad \text{con } 0 \le r \le |n| - 1
$$

> 💡 Il teorema generale ammette $n \in \mathbb{Z}$, con $n \ne 0$, ma nell’aritmetica modulare il modulo è sempre un intero positivo per definizione: $\mathbb{Z}/n\mathbb{Z}$ richiede $n > 0$. Per questo la restrizione a $n \in \mathbb{N}$ è una semplificazione didattica difendibile, non un errore concettuale. La condizione $n \ne 0$ resta però logicamente obbligatoria, perché la divisione per zero non è definita e l’unicità di $(q, r)$ verrebbe meno. Il valore assoluto nel vincolo su $r$ è essenziale nel caso generale, perché consente di gestire anche divisori negativi.

Il valore $r$ è detto **resto** o **$a \bmod n$**.

#### **Esempi**

- $a = 11, n = 7 \Rightarrow 11 = 1 \cdot 7 + 4 \Rightarrow 11 \bmod 7 = 4$
- $a = -11, n = 7 \Rightarrow -11 = (-2) \cdot 7 + 3 \Rightarrow -11 \bmod 7 = 3$

---

### **3. Congruenze modulo n**

Due numeri $a$ e $b$ sono **congruenti modulo $n$** se lasciano lo stesso resto nella divisione per $n$:

$$
a \equiv b \pmod{n} \;\iff\; a \bmod n = b \bmod n \;\iff\; n \mid (a - b)
$$

> 📌 Il simbolo $\mid$ si legge "divide": $n \mid (a-b)$ significa che $a-b$ è un multiplo intero di $n$. Le tre condizioni nella definizione sono equivalenti — scegliere $a-b$ o $b-a$ è indifferente, perché se $n$ divide $a-b$ divide anche $-(a-b) = b-a$.

#### **Proprietà fondamentali delle congruenze**

1. **Simmetria**: $a \equiv b \pmod{n} \iff b \equiv a \pmod{n}$ — discende direttamente dalla definizione: se $n \mid (a-b)$ allora $n \mid (b-a)$.

2. **Transitività**: se $a \equiv b \pmod{n}$ e $b \equiv c \pmod{n}$, allora $a \equiv c \pmod{n}$.

3. **Compatibilità con le operazioni**: se $a \equiv b \pmod{n}$ e $c \equiv d \pmod{n}$, allora:
   $$
   a + c \equiv b + d \pmod{n} \qquad a \cdot c \equiv b \cdot d \pmod{n}
   $$

#### **Esempi**

- $73 \equiv 4 \pmod{23}$  
   poiché $73 \bmod 23 = 4$
- $21 \equiv -9 \pmod{10}$  
   poiché $21 - (-9) = 30$ è multiplo di 10

---

### **4. Classi di resto e insieme $Z_n$**

Per com'è definito, l'operatore di modulo riesce a "mappare" tutti gli interi in un insieme più piccolo. Più precisamente, scegliendo un certo $n$, sarà possibile mappare tutti gli interi nell'insieme dei numeri compresi tra $0$ ed $n-1$.

L’insieme dei numeri **congruenti tra loro modulo $n$ e che quindi forniscono lo stesso resto** forma una **classe di resto**.

Questa classe generica, individuata da $a \mod n$ sarà individuata da tutti quei numeri scrivibili come:

$$
[a]_n = { a + k n \ \text{per qualsiasi} \ k \in \mathbb{Z} }
$$

Come rappresentante della classe si usa il più piccolo intero non negativo compreso in essa.

Definiamo il qualsiasi numero $b$ appartenente alla classe di resto come:

$$
b \in [a]_n \iff b=a+kn \iff b-a=kn \iff n \mid (b-a) \iff b \equiv a \pmod n
$$

Il sistema completo delle classi di resto modulo $n$ è:

$$
Z_n = { [0]_n, [1]_n, [2]_n, \dots, [n-1]_n }
$$

#### **Esempio per $n=4$**

$$
Z_4 = { [0]_4, [1]_4, [2]_4, [3]_4 }
$$

dove:

- $[0]_4 = {\dots, -8, -4, 0, 4, 8, \dots}$
- $[1]_4 = {\dots, -7, -3, 1, 5, 9, \dots}$
- $[2]_4 = {\dots, -6, -2, 2, 6, 10, \dots}$
- $[3]_4 = {\dots, -5, -1, 3, 7, 11, \dots}$

---

### **5. Massimo Comune Divisore / Greatest Common Divisor (gcd)**

Il numero $d$ è il **massimo comune divisore** di $a$ e $n$ se:

- $d$ divide sia $a$ che $n$,
- e ogni altro divisore comune di $a$ e $n$ divide $d$.

$$
d = \gcd(a, n)
$$

Può essere espresso come combinazione lineare:

$$
d = a \cdot x + n \cdot y
$$

È facile ricavare il gcd di due interi positivi se si esprimono entrambi come prodotto di numeri primi (possibile dal teorema fondamentale dell’aritmetica). Scriviamo quindi le due espressioni equivalenti:

$$
n = p_{i_1}^{e_1} \cdot p_{i_2}^{e_2} \cdot \dots \cdot p_{i_k}^{e_k}
\qquad
a = p_{j_1}^{f_1} \cdot p_{j_2}^{f_2} \cdot \dots \cdot p_{j_k}^{f_k}
$$

Allineando le fattorizzazioni sugli stessi primi $p_1, p_2, \dots, p_k$ e usando esponente $0$ per i primi assenti in uno dei due numeri:

$$
\gcd(a, n) = \prod_{\ell = 1}^{k} p_\ell^{\min(e_\ell, f_\ell)}
$$

Il **massimo comun divisore** di due interi positivi si trova quindi fattorizzandoli entrambi in fattori primi e prendendo i fattori con l’esponente minore. Se un primo compare solo in uno dei due numeri, nell’altro ha esponente $0$, quindi il minimo è $0$ e quel fattore non entra davvero nel gcd.

> 💡 Per il **minimo comune multiplo** la regola non è prendere solo fattori “esclusivi”: si prendono tutti i primi che compaiono in almeno una delle due fattorizzazioni, inclusi quelli comuni, ma con l’esponente maggiore. In formula: $\operatorname{lcm}(a, n) = \prod_{\ell = 1}^{k} p_\ell^{\max(e_\ell, f_\ell)}$.

---

#### **Proprietà principali**

- $\gcd(a, n) = \gcd(a, -n) = \gcd(-a, n) = \gcd(|a|, |b|)$
- $\gcd(a, 0) = |a|$
- Se $\gcd(a, n) = 1$, allora $a$ e $n$ sono **relativamente primi, coprimi, primi tra loro**

---

### **6. Algoritmo di Euclide**

Il **metodo di Euclide** (circa 300 a.C.) permette di calcolare $\gcd(a, b)$ in modo efficiente:

```plaintext
Euclide(a, b):
  if b = 0 then return a
  else return Euclide(b, a mod b)
```

#### **Esempio 1**

$$
\text{Euclide}(30, 21) = \text{Euclide}(21, 9) = \text{Euclide}(9, 3) = \text{Euclide}(3, 0) = 3
$$

#### **Esempio 2**

$$
\text{Euclide}(4864, 3458) =
\text{Euclide}(3458, 1406) =
\text{Euclide}(1406, 646) =
\text{Euclide}(646, 114) =
\text{Euclide}(114, 76) =
\text{Euclide}(76, 38) =
\text{Euclide}(38, 0) = 38
$$

#### **Complessità**

Assumiamo, senza perdita di generalità, che $a \ge b$. L’algoritmo di Euclide trasforma a ogni passo la coppia:

$$
(a, b)
\longrightarrow
(b, a \bmod b)
$$

Il numero di iterazioni è al massimo dell’ordine di $\log_2 a$. L’intuizione è che, procedendo con le divisioni successive, i numeri coinvolti diminuiscono rapidamente: dopo ogni chiamata si lavora con un resto, quindi con un numero più piccolo del divisore precedente. In una stima standard, questo porta a un numero massimo di chiamate proporzionale al numero di bit necessari per rappresentare $a$, cioè $\log a$.

Per ogni chiamata bisogna calcolare il resto della divisione:

$$
a = q \cdot b + r
$$

cioè bisogna determinare $a \bmod b = r$. Se si usa un’analisi elementare, questo calcolo può essere visto come una sequenza di sottrazioni: al massimo $\log a$ sottrazioni, ciascuna delle quali richiede circa $\log a$ operazioni su bit. Quindi una singola chiamata costa:

$$
O((\log a)^2)
$$

operazioni su bit.

Combinando questa stima con il numero massimo di chiamate, si ottiene una prima stima complessiva:

$$
O(\log_2 a) \cdot O((\log a)^2) = O((\log a)^3)
$$

Che sia base 2 o logaritmo naturale quindi in base $e$, il risultato finale non cambia: la complessità è sempre cubica in $\log a$.

Questa però non è la stima più precisa. Con un’analisi più accurata del comportamento delle divisioni successive, si dimostra che l’algoritmo di Euclide richiede complessivamente:

$$
O((\log a)^2)
$$

operazioni su bit. Questo non è strettamente pertinente per il corso di crittografia, ma è interessante notare che l’algoritmo di Euclide è più efficiente di quanto si possa intuire da una stima elementare. Per maggiori informazioni: [https://en.wikipedia.org/wiki/Euclidean_algorithm#Complexity](https://en.wikipedia.org/wiki/Euclidean_algorithm#Complexity) oppure [https://people.engr.tamu.edu/andreas-klappenecker/alg/euclid.pdf](https://people.engr.tamu.edu/andreas-klappenecker/alg/euclid.pdf)

> ✅ In sintesi, una stima elementare porta a $O((\log a)^3)$, mentre l’analisi migliore mostra che Euclide è più efficiente e lavora in $O((\log a)^2)$ operazioni su bit.

#### **Funzionamento dell’algoritmo**

Il motivo per cui l’algoritmo di Euclide funziona è la seguente identità:

$$
\gcd(a, b) = \gcd(b, a \bmod b)
$$

valida per tutti gli interi $a \ge 0$ e $b > 0$.

Per capirla senza saltare passaggi, partiamo dalla divisione euclidea di $a$ per $b$. Poiché $b > 0$, possiamo scrivere:

$$
a = k \cdot b + r
$$

dove $k$ è il quoziente intero e $r$ è il resto della divisione. Per definizione:

$$
r = a \bmod b
$$

quindi la stessa uguaglianza può essere riscritta come:

$$
a = k \cdot b + (a \bmod b)
$$

oppure, isolando il resto:

$$
a \bmod b = a - k \cdot b
$$

Ora sia $d = \gcd(a, b)$. Dire che $d$ è il massimo comun divisore di $a$ e $b$ significa prima di tutto che $d$ divide sia $a$ sia $b$:

$$
d \mid a
\qquad
d \mid b
$$

Se $d \mid b$, allora $d$ divide anche ogni multiplo di $b$, quindi divide anche $k \cdot b$:

$$
d \mid k \cdot b
$$

Poiché $d$ divide $a$ e divide $k \cdot b$, allora divide anche la loro differenza:

$$
d \mid (a - k \cdot b)
$$

ma $a - k \cdot b$ è proprio $a \bmod b$. Quindi:

$$
d \mid (a \bmod b)
$$

Questo dimostra che ogni divisore comune di $a$ e $b$ è anche un divisore comune di $b$ e $a \bmod b$.

Serve però anche il ragionamento inverso. Supponiamo che un certo intero $d$ divida sia $b$ sia $a \bmod b$:

$$
d \mid b
\qquad
d \mid (a \bmod b)
$$

Allora $d$ divide anche $k \cdot b$, perché $k \cdot b$ è un multiplo di $b$. Inoltre divide $a \bmod b$. Di conseguenza divide anche la loro somma:

$$
d \mid \bigl(k \cdot b + (a \bmod b)\bigr)
$$

ma dalla divisione euclidea sappiamo che:

$$
k \cdot b + (a \bmod b) = a
$$

quindi $d \mid a$. Questo mostra che ogni divisore comune di $b$ e $a \bmod b$ è anche un divisore comune di $a$ e $b$.

A questo punto i due insiemi di divisori comuni coincidono:

$$
\{\text{divisori comuni di } a \text{ e } b\}
=
\{\text{divisori comuni di } b \text{ e } a \bmod b\}
$$

Se due coppie di numeri hanno esattamente gli stessi divisori comuni, allora hanno anche lo stesso massimo comun divisore. Per questo:

$$
\gcd(a, b) = \gcd(b, a \bmod b)
$$

L’algoritmo di Euclide applica ripetutamente questa identità: sostituisce la coppia $(a, b)$ con la coppia più piccola $(b, a \bmod b)$, senza cambiare il valore del massimo comun divisore. Quando il secondo numero diventa $0$, il risultato è immediato, perché:

$$
\gcd(a, 0) = |a|
$$

> ✅ In sintesi, Euclide funziona perché passare da $(a, b)$ a $(b, a \bmod b)$ non cambia l’insieme dei divisori comuni; cambia solo la dimensione dei numeri, rendendo il calcolo sempre più semplice.

---

### **7. L’insieme $Z_n^*$ e gli inversi**

Si definisce:

$$
Z_n^* = { [a]_n \mid 0 < a < n, \ \gcd(a, n) = 1 }
$$

Ovvero l'insieme dei rappresentanti delle classi di resto modulo $n$ che sono **primi con $n$**. L'insieme dei coprimi, potremmo chiamarlo.

Gli elementi di $Z_n^*$ **hanno un inverso moltiplicativo modulo $n$**.

#### **Esempi**

- $Z_4^* = { [1]_4, [3]_4 }$
- $Z_8^* = { [1]_8, [3]_8, [5]_8, [7]_8 }$

Solo questi elementi in $Z_8$ hanno inverso rispetto alla moltiplicazione.

![](imgs/Pasted%20image%2020260612130002.png)

---

### **8. La funzione di Eulero**

La **funzione toziente** (o **funzione di Eulero**) φ(n) indica il **numero di interi minori di n che sono primi con n**, che essenzialmente coincide proprio con la cardinalità di $Z_n$\*

#### **Esempi**

- $\varphi(37) = 36$
- $\varphi(35) = 24$

#### **Proprietà**

- Se $p$ è primo, allora $\varphi(p) = p - 1$
- Se $p, q$ sono primi, allora $\varphi(pq) = (p - 1)(q - 1)$
- In generale, se
  $$
  n = p_1^{e_1} p_2^{e_2} \dots p_k^{e_k}
  $$
  allora:
  $$
  \varphi(n) = n \cdot \left(1 - \frac{1}{p_1}\right) \cdot \left(1 - \frac{1}{p_2}\right) \cdot \dots \cdot \left(1 - \frac{1}{p_k}\right)
  $$

---

### **9. Teorema di Eulero**

Per ogni $a \in Z_n^*$, vale:

$$
a^{\varphi(n)} \equiv 1 \pmod{n}
$$

#### **Esempi**

- $3^4 = 81 \equiv 1 \pmod{10}$
- $2^{10} = 1024 \equiv 1 \pmod{11}$

---

### **10. Teorema di Fermat**

Se $p$ è primo e $a \in Z_p^*$, allora:

$$
a^{p-1} \equiv 1 \pmod{p}
$$

e, in forma più generale:

$$
a^p \equiv a \pmod{p}
$$

#### **Esempi**

- $7^{18} \equiv 1 \pmod{19}$
- $10^5 \equiv 10 \pmod{5} \equiv 0 \pmod{5}$

---

### **11. Correttezza della decifratura RSA**

Per l’algoritmo RSA valgono le seguenti condizioni:

$$
\begin{cases}
N = p \cdot q \\\\
\varphi(N) = (p-1)(q-1) \\\\
e \cdot d \equiv 1 \pmod{\varphi(N)}
\end{cases}
$$

Per ogni messaggio $M$ primo con $N$:

$$
C^d \bmod N = M
$$

Infatti:

$$
C^d = (M^e)^d = M^{ed} = M^{1 + k\varphi(N)} = M \cdot (M^{\varphi(N)})^k
$$

Ma per il teorema di Eulero quella quantità elevata a fi di n è = 1, quindi tutta la parentesi va ad 1 e l'esponente k non cambia le cose, ergo otteniamo M\*1 e quindi:

$$
M \cdot (M^{\varphi(N)})^k \equiv M \pmod{N}
$$

Ma questo M mod N non è altro che $M$ in quanto avevamo presupposto $0 \leq M < n$.

Se invece $M$ **non è primo con $N$**, non possiamo applicare direttamente il teorema di Eulero modulo $N$, perché $M \notin Z_N^*$. Questo non significa però che RSA smetta di funzionare: bisogna separare il ragionamento sui due fattori primi di $N$.

Ricordiamo che:

$$
N = p \cdot q
$$

dove $p$ e $q$ sono primi distinti. Se $\gcd(M, N) \ne 1$, allora $M$ condivide almeno un fattore primo con $N$: quindi $M$ è multiplo di $p$, oppure è multiplo di $q$.

Consideriamo il caso in cui $M$ sia multiplo di $p$. Allora esiste un intero $c$ tale che:

$$
M = c \cdot p
$$

Se $0 \le M < N$ e $M$ è multiplo di $p$ ma non è $0$, allora $M$ non può essere anche multiplo di $q$, altrimenti sarebbe multiplo di $p \cdot q = N$. Quindi, in questo caso:

$$
\gcd(M, q) = 1
$$

Possiamo allora applicare il teorema di Eulero modulo $q$:

$$
M^{\varphi(q)} \equiv 1 \pmod q
$$

Poiché:

$$
\varphi(N) = \varphi(pq) = (p-1)(q-1) = \varphi(p)\varphi(q)
$$

si ha:

$$
M^{\varphi(N)}
=
M^{\varphi(p)\varphi(q)}
=
\left(M^{\varphi(q)}\right)^{\varphi(p)}
$$

Dato che $M^{\varphi(q)} \equiv 1 \pmod q$, elevare questa quantità a $\varphi(p)$ non cambia la congruenza:

$$
\left(M^{\varphi(q)}\right)^{\varphi(p)} \equiv 1^{\varphi(p)} \equiv 1 \pmod q
$$

quindi:

$$
M^{\varphi(N)} \equiv 1 \pmod q
$$

Questo significa che esiste un intero $k$ tale che:

$$
M^{\varphi(N)} = 1 + kq
$$

Moltiplichiamo ora entrambi i membri per $M$. Poiché $M = cp$, otteniamo:

$$
M^{\varphi(N)+1}
=
M(1 + kq)
=
M + Mkq
=
M + cpkq
$$

ma $p \cdot q = N$, quindi:

$$
M^{\varphi(N)+1}
=
M + ckN
$$

Questo vuol dire che $M^{\varphi(N)+1}$ differisce da $M$ per un multiplo di $N$, quindi:

$$
M^{\varphi(N)+1} \equiv M \pmod N
$$

Il ragionamento è simmetrico se $M$ è multiplo di $q$: si lavora modulo $p$ e si ottiene lo stesso risultato.

Nel caso RSA l’esponente effettivo della decifratura non è semplicemente $\varphi(N)+1$, ma:

$$
ed = 1 + h\varphi(N)
$$

per un certo intero $h$, perché $e \cdot d \equiv 1 \pmod{\varphi(N)}$. Lo stesso ragionamento si applica quindi a:

$$
M^{ed}
=
M^{1 + h\varphi(N)}
=
M \cdot \left(M^{\varphi(N)}\right)^h
$$

e porta alla congruenza:

$$
M^{ed} \equiv M \pmod N
$$

> ✅ Anche quando $M$ non è invertibile modulo $N$, la decifratura RSA resta corretta: non si usa Eulero direttamente modulo $N$, ma si ragiona separatamente rispetto ai fattori primi $p$ e $q$, ottenendo comunque $M^{ed} \equiv M \pmod N$.

---

### **12. Sintesi finale**

Abbiamo introdotto i concetti matematici su cui si basa RSA:

- **Numeri primi e aritmetica modulare**
- **Algoritmo di Euclide** e **gcd**
- **Insieme $Z_n^*$ e funzione di Eulero**
- **Teoremi di Fermat e di Eulero**, che assicurano la **correttezza della decifratura RSA**

Questi strumenti consentono di comprendere **perché RSA funziona** e **su quali proprietà matematiche** si fonda la sua sicurezza.

---
