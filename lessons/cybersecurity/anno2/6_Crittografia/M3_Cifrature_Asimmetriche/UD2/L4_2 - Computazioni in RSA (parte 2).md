# **Lezione 4: Computazioni in RSA (parte 2)**

### **1. Obiettivo**

Per completare l’implementazione dell’algoritmo **RSA**, dopo aver generato i numeri primi $p$ e $q$, bisogna:

- Calcolare **$e$** (esponente pubblico) e **$d$** (esponente privato)
    
- Verificare che:  
    $$  
    \mcd(e, \varphi(n)) = 1  
    $$
    
- Calcolare:  
    $$  
    d = e^{-1} \bmod \varphi(n)  
    $$
    

Dove $\varphi(n) = (p-1)(q-1)$ è la funzione di Eulero.

---

### **2. Algoritmo esteso di Euclide**

> 💡 **Perché ci serve e da dove viene.** Per RSA dobbiamo calcolare $d = e^{-1} \bmod \varphi(n)$, cioè trovare un intero $d$ tale che $e \cdot d \equiv 1 \pmod{\varphi(n)}$. L’algoritmo di Euclide classico calcola solo il $\mcd$; la versione *estesa* fa di più: trova anche i due interi $x$ e $y$ della cosiddetta **identità di Bézout**, cioè esprime il $\mcd$ come combinazione lineare intera di $a$ e $n$. Quando il $\mcd$ vale $1$, il coefficiente $x$ è esattamente l’inverso che cerchiamo.

L’**algoritmo esteso di Euclide** permette di:

- Calcolare il **$\mcd(a, n)$**
    
- Trovare **l’inverso moltiplicativo** di $a$ modulo $n$, se $\mcd(a,n)=1$
    

Restituisce tre valori $(d, x, y)$ tali che:

$$  
d = \mcd(a, n) = a \cdot x + n \cdot y  
$$

> 📌 **Identità di Bézout.** Questa scrittura $d = ax + ny$ si chiama identità di Bézout e vale sempre: il $\mcd$ di due interi si può sempre esprimere come loro combinazione lineare (con coefficienti interi, anche negativi). Riducendo entrambi i lati modulo $n$: $d \equiv ax \pmod{n}$. Se $d = 1$, allora $1 \equiv ax \pmod{n}$, cioè $x$ è proprio l’inverso di $a$ modulo $n$.

---

#### **Algoritmo (versione ricorsiva)**

```plaintext
Euclide-esteso(a, n)
  if n = 0 then return (a, 1, 0)
  (d’, x’, y’) ← Euclide-esteso(n, a mod n)
  (d, x, y) ← (d’, y’, x’ - ⌊a/n⌋ · y’)
  return (d, x, y)
```

> 📌 **Come funziona il ritorno indietro.** La chiamata ricorsiva scende esattamente come l’Euclide classico (riducendo $a \bmod n$ ad ogni passo), finché $n = 0$: a quel punto il $\mcd$ è $a$ stesso, e i coefficienti banali sono $x=1, y=0$ (perché $a \cdot 1 + 0 \cdot 0 = a$). Poi, risalendo, ogni livello *ricostruisce* i propri $x$ e $y$ da quelli del livello sottostante tramite la formula $x = y’$, $y = x’ - \lfloor a/n \rfloor \cdot y’$. Questa formula deriva dalla sostituzione dell’identità $a = \lfloor a/n \rfloor \cdot n + (a \bmod n)$ nell’identità di Bézout del livello inferiore.

#### **Complessità**

Ha lo stesso tempo di esecuzione asintotico dell’algoritmo classico di Euclide, ovvero $O((\log n)^2)$.

---

### **3. Esempio: Euclide esteso (99, 78)**

> 💡 **Come leggere la tabella.** Ogni riga corrisponde a una chiamata ricorsiva. La **discesa** (dall'alto verso il basso) applica Euclide classico: $(a, n) \to (n,\, a \bmod n)$. La **risalita** (dal basso verso l'alto) ricostruisce $x$ e $y$ usando la formula del pseudocodice. L'ultima riga è il caso base ($n=0$): restituisce sempre $(a, 1, 0)$.

| $a$ | $n$ | $d$ | $x$ | $y$ |
|---|---|---|---|---|
| 99 | 78 | 3 | **-11** | **14** |
| 78 | 21 | 3 | 3 | -11 |
| 21 | 15 | 3 | -2 | 3 |
| 15 | 6 | 3 | 1 | -2 |
| 6 | 3 | 3 | 0 | 1 |
| **3** | **0** | **3** | **1** | **0** |

> 📌 **Verifica della risalita (due passi espliciti).**
>
> **Base:** riga $(3, 0)$ → restituisce $(3, 1, 0)$: $3 \cdot 1 + 0 \cdot 0 = 3$ ✓
>
> **Riga $(6, 3)$:** $a=6$, $n=3$, $\lfloor 6/3 \rfloor = 2$. Dalla riga sotto: $(d', x', y') = (3, 1, 0)$.
> $x = y' = 0$, $\quad y = x' - 2 \cdot y' = 1 - 0 = 1$.
> Verifica: $6 \cdot 0 + 3 \cdot 1 = 3$ ✓
>
> **Riga $(15, 6)$:** $\lfloor 15/6 \rfloor = 2$. Dalla riga sotto: $(3, 0, 1)$.
> $x = 1$, $\quad y = 0 - 2 \cdot 1 = -2$.
> Verifica: $15 \cdot 1 + 6 \cdot (-2) = 15 - 12 = 3$ ✓
>
> Continuando la risalita si arriva a $x = -11$, $y = 14$ per la coppia originale $(99, 78)$.

Risultato finale:

$$
\mcd(99, 78) = 3, \qquad 99 \cdot (-11) + 78 \cdot 14 = -1089 + 1092 = 3
$$

---

### **4. Soluzione di una congruenza $a x \equiv b \pmod{n}$**

> 💡 **Intuizione.** La congruenza $ax \equiv b \pmod{n}$ equivale a chiedere: esiste un intero $x$ tale che $ax - b$ sia un multiplo di $n$? Riscrivendo: $ax - kn = b$ per qualche intero $k$, cioè $ax + n(-k) = b$. Il lato sinistro è una combinazione lineare di $a$ e $n$, e — per il teorema di Bézout — l’insieme di tutti i valori che questa combinazione può assumere è esattamente l’insieme dei multipli di $\mcd(a, n)$. Quindi la congruenza è risolvibile se e solo se $\mcd(a,n)$ divide $b$.

#### **Condizioni di esistenza**

La congruenza $a x \equiv b \pmod{n}$ ammette soluzioni **se e solo se**:

$$  
g = \mcd(a, n) \mid b  
$$

Se $g \mid b$, allora esistono **$g$ soluzioni distinte** modulo $n$, equi-spaziate di $n/g$:

$$  
x_i = x’ \cdot \frac{b}{g} + i \cdot \frac{n}{g}, \quad i = 0, 1, \dots, g-1  
$$

dove $x’$ viene da **Euclide-esteso$(a, n)$**, che restituisce $(g, x’, y’)$ con $g = ax’ + ny’$. Moltiplicando entrambi i lati per $b/g$ si ottiene la soluzione particolare $x’ \cdot (b/g)$.

> ⚠️ **Attenzione al numero di soluzioni.** Si dice "$g$ soluzioni distinte modulo $n$" ma distinte modulo $n/g$: se $g > 1$, le soluzioni non sono uniche modulo $n$ ma si ripetono ogni $n/g$.

---

### **5. Caso particolare: $a x \equiv 1 \pmod{n}$**

> 💡 **Raccordo con RSA.** Questo è esattamente il caso che ci serve: vogliamo trovare $d$ tale che $e \cdot d \equiv 1 \pmod{\varphi(n)}$. La condizione di esistenza diventa $\mcd(e, \varphi(n)) = 1$, e questa è precisamente la condizione che verifichiamo nella generazione delle chiavi RSA (passo 5 della sezione 9). Se non è soddisfatta, si cambia $e$ e si riprova.

La congruenza $ax \equiv 1 \pmod{n}$ ha **soluzione unica** modulo $n$ se e solo se $\mcd(a, n) = 1$.

In tal caso Euclide esteso restituisce $x’$ tale che:

$$  
a \cdot x’ + n \cdot y = 1  
$$

Riducendo modulo $n$: $a \cdot x’ \equiv 1 \pmod{n}$. Quindi $x’$ è l’**inverso moltiplicativo** di $a$:

$$  
a^{-1} \bmod n = x’ \bmod n  
$$

> ⚠️ Se Euclide esteso restituisce un $x’$ negativo, basta aggiungere $n$ per ottenere il rappresentante positivo canonico: $a^{-1} = x’ + n$.

---

### **6. Esempi di inverso moltiplicativo**

#### **Esempio 1 – modulo 8**

> 💡 $8 = 2^3$ non è primo: gli unici numeri in $[1,7]$ coprimi con $8$ sono quelli **dispari** (i pari condividono il fattore $2$ con $8$, quindi $\mcd > 1$ e l'inverso non esiste).

| $a$ | $\mcd(a,8)$ | $a^{-1} \pmod{8}$ |
|---|---|---|
| 1 | 1 | 1 |
| 2 | 2 | — (non esiste) |
| 3 | 1 | 3 |
| 4 | 4 | — (non esiste) |
| 5 | 1 | 5 |
| 6 | 2 | — (non esiste) |
| 7 | 1 | 7 |

Si nota che gli inversi esistenti sono **auto-inversi** ($3 \cdot 3 = 9 \equiv 1$, $5 \cdot 5 = 25 \equiv 1$, $7 \cdot 7 = 49 \equiv 1$): un fatto tipico di moduli non primi.

---

#### **Esempio 2 – modulo 7**

> 💡 $7$ è primo: per il piccolo teorema di Fermat, ogni $a \in [1, 6]$ soddisfa $\mcd(a,7)=1$, quindi tutti hanno inverso. In un campo $\mathbb{Z}_p$ con $p$ primo l'inverso esiste sempre per ogni elemento non nullo.

| $a$ | $a^{-1} \pmod{7}$ | Verifica |
|---|---|---|
| 1 | 1 | $1 \cdot 1 = 1 \equiv 1$ |
| 2 | 4 | $2 \cdot 4 = 8 \equiv 1$ |
| 3 | 5 | $3 \cdot 5 = 15 \equiv 1$ |
| 4 | 2 | $4 \cdot 2 = 8 \equiv 1$ |
| 5 | 3 | $5 \cdot 3 = 15 \equiv 1$ |
| 6 | 6 | $6 \cdot 6 = 36 \equiv 1$ |

---

#### **Esempio 3 – calcolo di $5^{-1} \pmod{7}$ tramite Euclide esteso**

Applichiamo Euclide esteso a $(a, n) = (5, 7)$:

| $a$ | $n$ | $d$ | $x$ | $y$ |
|---|---|---|---|---|
| 5 | 7 | 1 | **3** | **-2** |
| 7 | 5 | 1 | -2 | 3 |
| 5 | 2 | 1 | 1 | -2 |
| 2 | 1 | 1 | 0 | 1 |
| 1 | 0 | 1 | 1 | 0 |

Identità di Bézout: $1 = 5 \cdot 3 + 7 \cdot (-2) = 15 - 14 = 1$ ✓

Quindi $x' = 3$, e:

$$
5^{-1} \equiv 3 \pmod{7}
$$

> ⚠️ Notare che la prima riga ha $(a,n) = (5,7)$, non $(7,5)$: l'algoritmo accetta gli argomenti in qualsiasi ordine e restituisce il coefficiente di $a$ come $x$. Se avessimo chiamato Euclide-esteso$(7,5)$, avremmo ottenuto $x=-2$ (coefficiente di $7$) e $y=3$ (coefficiente di $5$), e sarebbe stato $y$ il nostro inverso.

---

### **7. Sistemi di congruenze e Teorema Cinese del Resto (CRT)**

> 💡 **Perché il CRT è rilevante in RSA.** Quando decifriamo un messaggio $c$ con $m = c^d \bmod n$ (dove $n = pq$), stiamo lavorando con un modulo $n$ enorme — tipicamente 2048 bit. Il CRT ci permette di **spezzare questo calcolo** in due operazioni con numeri di metà dimensione:
>
> $$m_p = c^{d \bmod (p-1)} \bmod p, \qquad m_q = c^{d \bmod (q-1)} \bmod q$$
>
> poi si ricombinano $m_p$ e $m_q$ con il CRT per ottenere $m \bmod n$. Lavorare modulo $p$ e $q$ (numeri da ~1024 bit) invece che modulo $n$ (2048 bit) riduce il costo di ciascuna elevazione a potenza di circa **4 volte** (la moltiplicazione modulare scala con il quadrato della lunghezza). Con due operazioni parallele il guadagno complessivo è circa **4×**, e questa ottimizzazione è usata in tutte le implementazioni RSA di produzione.

Se $n = p \cdot q$ con $p, q$ primi distinti, una congruenza modulo $n$ può essere **decomposta** in due congruenze indipendenti modulo $p$ e $q$, risolte separatamente e poi ricombinate.

#### **Teorema Cinese del Resto**

Siano $m_1, m_2, \dots, m_t$ interi positivi e **a coppie coprimi** ($\mcd(m_i, m_j)=1$ per $i \ne j$).

Allora il sistema:

$$  
\begin{cases}  
x \equiv a_1 \pmod{m_1} \\  
x \equiv a_2 \pmod{m_2} \\  
\vdots \\  
x \equiv a_t \pmod{m_t}  
\end{cases}  
$$

ha **esattamente una soluzione** modulo $M = m_1 m_2 \cdots m_t$, data da:

$$  
x = \sum_{i=1}^{t} a_i \cdot M_i \cdot y_i \pmod{M}  
$$

dove:

$$  
M_i = \frac{M}{m_i} \quad \text{(prodotto di tutti i moduli tranne } m_i\text{)}, \qquad y_i = M_i^{-1} \bmod m_i  
$$

> 📌 **Come si legge la formula.** Ogni termine $a_i M_i y_i$ contribuisce alla soluzione solo per il modulo $m_i$: poiché $M_i$ è divisibile per tutti gli $m_j$ con $j \ne i$, quel termine è $\equiv 0 \pmod{m_j}$ per $j \ne i$, mentre vale $a_i \cdot M_i \cdot y_i \equiv a_i \cdot 1 = a_i \pmod{m_i}$ (perché $M_i y_i \equiv 1 \pmod{m_i}$ per costruzione di $y_i$). La somma soddisfa quindi tutte le congruenze contemporaneamente.

---

### **8. Esempio di CRT**

Sistema:

$$  
\begin{cases}  
x \equiv 2 \pmod{5} \\  
x \equiv 3 \pmod{13}  
\end{cases}  
$$

**Passo 1 — calcolare $M$, $M_1$, $M_2$:**

$$
M = 5 \cdot 13 = 65, \qquad M_1 = \frac{65}{5} = 13, \qquad M_2 = \frac{65}{13} = 5
$$

**Passo 2 — trovare $y_1 = M_1^{-1} \bmod m_1$ e $y_2 = M_2^{-1} \bmod m_2$:**

- $y_1 = 13^{-1} \bmod 5$: poiché $13 \equiv 3 \pmod{5}$, cerchiamo $3^{-1} \bmod 5$. Prova: $3 \cdot 2 = 6 \equiv 1 \pmod{5}$. Quindi $y_1 = 2$.

- $y_2 = 5^{-1} \bmod 13$: cerchiamo $5^{-1} \bmod 13$. Prova: $5 \cdot 8 = 40 = 3 \cdot 13 + 1 \equiv 1 \pmod{13}$. Quindi $y_2 = 8$.

> 💡 In pratica si usa Euclide esteso per trovare $y_1$ e $y_2$; per moduli piccoli come questi si può anche procedere per tentativi.

**Passo 3 — combinare con la formula del CRT:**

$$  
x = a_1 M_1 y_1 + a_2 M_2 y_2 = (2)(13)(2) + (3)(5)(8) = 52 + 120 = 172
$$

**Passo 4 — ridurre modulo $M = 65$:**

$$
172 = 2 \cdot 65 + 42 \quad \Rightarrow \quad x \equiv 42 \pmod{65}
$$

**Verifica:**

$$
42 = 8 \cdot 5 + 2 \;\Rightarrow\; 42 \equiv 2 \pmod{5} \checkmark \qquad 42 = 3 \cdot 13 + 3 \;\Rightarrow\; 42 \equiv 3 \pmod{13} \checkmark
$$

---

### **9. Generazione delle chiavi RSA**

> 💡 **Il filo narrativo.** Abbiamo ora tutti gli strumenti che servono: test di Miller–Rabin per generare $p$ e $q$, Euclide esteso per calcolare $d = e^{-1} \bmod \varphi(n)$, e CRT per accelerare la decifratura. Il processo di generazione li mette insieme nell’ordine seguente.

1. **Input:** lunghezza $L$ del modulo (tipicamente 2048 o 4096 bit).

2. **Genera** due numeri primi distinti $p$ e $q$, ciascuno di lunghezza $L/2$ bit, usando un generatore probabilistico (es. Miller–Rabin ripetuto). Avere due primi di pari lunghezza bilancia la sicurezza: se uno fosse molto più piccolo dell’altro, la fattorizzazione di $n$ diventerebbe più facile.

3. **Calcola** $n = p \cdot q$ e $\varphi(n) = (p-1)(q-1)$.

4. **Scegli** casualmente un intero $e$ con $1 < e < \varphi(n)$. In pratica si usano valori standard come $e = 65537$ (primo di Fermat $2^{16}+1$) per efficienza.

5. **Controlla** $\mcd(e,\, \varphi(n)) = 1$ tramite Euclide classico.
    - Se la condizione vale: calcola $d = e^{-1} \bmod \varphi(n)$ tramite **Euclide esteso**.
    - Altrimenti: torna al passo 4 e prova un altro $e$.

6. **Output:** chiave pubblica $(e, n)$, chiave privata $(d, n)$. I valori $p$, $q$, $\varphi(n)$ vengono eliminati o conservati in modo sicuro (solo per l’ottimizzazione CRT in decifratura).

> ⚠️ **Perché eliminare $p$ e $q$ dopo la generazione?** Chi conosce $p$ e $q$ può ricalcolare $\varphi(n)$ e quindi $d$ in tempo lineare. La sicurezza di RSA si basa sull’ipotesi che fattorizzare $n$ sia computazionalmente difficile: se $p$ e $q$ venissero compromessi, l’intera chiave privata sarebbe esposta.

---

### **10. Sintesi finale**

> ✅ **Riepilogo dei blocchi costruttivi di RSA.**

- L’**algoritmo di Euclide esteso** è il cuore della generazione delle chiavi: calcola $d = e^{-1} \bmod \varphi(n)$ in tempo $O((\log n)^2)$, lo stesso dell’Euclide classico.

- La **congruenza lineare** $ex \equiv 1 \pmod{\varphi(n)}$ ha soluzione unica se e solo se $\mcd(e, \varphi(n)) = 1$, condizione verificata a ogni tentativo di scelta di $e$.

- Il **Teorema Cinese del Resto** non è solo un risultato teorico: è l’ottimizzazione che rende la decifratura RSA circa **4 volte più veloce** in tutte le implementazioni reali, sfruttando il fatto che $n = pq$.

- Con $p$, $q$, $e$, $d$ in mano, RSA è completamente operativo: cifratura $c = m^e \bmod n$, decifratura $m = c^d \bmod n$, entrambe eseguibili con left-to-right o right-to-left in tempo polinomiale nella lunghezza della chiave.