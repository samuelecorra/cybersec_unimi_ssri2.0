
# **Lezione 1: Aritmetica su curve ellittiche**

### **1. Introduzione storica**

I **crittosistemi su curve ellittiche (ECC)** furono proposti **indipendentemente** da:

- **Victor Miller (1986)**
- **Neal Koblitz (1987)**

L’idea di fondo è costruire un **analogo dei crittosistemi modulari classici** (El-Gamal, DH), sostituendo il gruppo $\mathbb{Z}_p^*$ con il **gruppo dei punti di una curva ellittica**. Tutte le operazioni (addizione dei punti, moltiplicazione scalare) rimpiazzano la moltiplicazione e l’elevazione a potenza modulare — e la "divisione discreta" (logaritmo) su questo nuovo gruppo è intrinsecamente più difficile.

> 💡 **Il nome "ellittica" non viene dalla forma.** Le curve ellittiche non sono ellissi: il nome deriva storicamente dagli *integrali ellittici* (calcolo della lunghezza d’arco di un’ellisse), che portano a questi tipi di equazioni cubiche. La forma grafica è molto diversa da un’ellisse.

#### **Vantaggio principale: chiavi molto più corte a parità di sicurezza**

Il miglior algoritmo noto per il ECDLP (logaritmo discreto su curve ellittiche) è di complessità **esponenziale** $O(\sqrt{p})$ — molto peggio del NFS sub-esponenziale usato per $\mathbb{Z}_p^*$. Questo si traduce direttamente in chiavi più corte:

| Sicurezza equivalente | RSA / El-Gamal | ECC |
|---|---|---|
| ~80 bit | 1024 bit | **160 bit** |
| ~128 bit | 3072 bit | **256 bit** |
| ~256 bit | 15360 bit | **512 bit** |

> 📌 Chiavi più corte significano meno memoria, meno banda, e operazioni crittografiche più veloci — cruciale per dispositivi embedded, smartcard e comunicazioni mobili.
    

---

### **2. Definizione di curva ellittica**

Una **curva ellittica** è l’insieme dei punti $(x, y)$ che soddisfano un’equazione cubica del tipo:

$$  
y^2 + axy + by = x^3 + cx^2 + dx + e  
$$

A essa si aggiunge un **punto speciale all’infinito** $O$, detto **punto zero** o **punto neutro**.

> 📌 **Perché serve il punto all’infinito.** Perché il gruppo sia chiuso, ogni "linea" deve incontrare la curva in esattamente tre punti (contati con molteplicità). Una linea verticale ne incontra due ($P$ e $-P$) — il terzo è $O$, il punto all’infinito. Senza $O$ l’operazione di addizione non sarebbe definita per $P + (-P)$, e il gruppo non avrebbe un elemento neutro. In termini proiettivi, $O$ è il punto $[0:1:0]$ del piano proiettivo $\mathbb{P}^2$.

---

### **3. Esempi di curve ellittiche reali**

In crittografia si usa la **forma di Weierstrass ridotta** (per campi con caratteristica $> 3$):

$$
y^2 = x^3 + ax + b
$$

Due esempi classici su $\mathbb{R}$:

$$  
y^2 = x^3 + x + 1 \qquad \text{e} \qquad y^2 = x^3 - x  
$$

![](imgs/Pasted%20image%2020260613075951.png)

> 💡 **Forma geometrica su $\mathbb{R}$.** La curva è sempre **simmetrica rispetto all’asse $x$** (per ogni punto $(x,y)$ sulla curva, anche $(x,-y)$ vi appartiene — questo diventa l’inverso additivo). Può avere una o due componenti connesse, a seconda dei segni. La condizione $4a^3 + 27b^2 \neq 0$ (che vedremo in sezione 6) garantisce che la curva sia **liscia** — senza cuspidi né auto-intersezioni che romperebbero la struttura di gruppo.

---

### **4. Operazioni sui punti — costruzione geometrica**

Le curve ellittiche formano un **gruppo abeliano** con l’operazione di **addizione di punti**. La regola di addizione si capisce meglio geometricamente prima di passare alle formule algebriche.

> 💡 **Regola della corda e della tangente.** Questa è la costruzione fondamentale — va capita bene perché tutto il resto ne deriva.

#### **Caso 1 — $P_1 \neq P_2$: regola della corda**

1. Traccia la retta (la "corda") passante per $P_1$ e $P_2$.
2. Una curva cubica liscia interseca ogni retta in esattamente **3 punti** (contati con molteplicità). La retta ne tocca già due ($P_1$ e $P_2$), quindi incontra la curva in un terzo punto $R$.
3. Rifletti $R$ rispetto all’asse $x$ (cambia segno alla $y$): il punto risultante è $P_1 + P_2$.

![](imgs/Pasted%20image%2020260613080137.png)

#### **Caso 2 — $P_1 = P_2 = P$: regola della tangente**

1. Traccia la **tangente** alla curva nel punto $P$ (il "limite" della corda quando $P_2 \to P_1$).
2. La tangente incontra la curva in un secondo punto $R$ (la tangente "conta" $P$ due volte, quindi il terzo punto è separato).
3. Rifletti $R$ rispetto all’asse $x$: il punto risultante è $2P$.

![](imgs/Pasted%20image%2020260613080151.png)

#### **Caso 3 — retta verticale ($y_1 = -y_2$)**

La retta verticale incontra la curva in $P_1$, $P_2 = -P_1$, e "all’infinito" nel punto $O$. Quindi:

$$
P + (-P) = O
$$

$O$ si comporta da **elemento neutro**: $P + O = P$ (la retta per $P$ e $O$ è verticale, il terzo punto è $-P$, e la sua riflessione è $P$ stesso).

![](imgs/Pasted%20image%2020260613080322.png)

---

### **5. Identità additiva e moltiplicazione scalare**

Le principali proprietà del gruppo:

- **Elemento neutro:** $P + O = O + P = P$

- **Inverso additivo:** $-P = (x, -y \bmod p)$ — la riflessione rispetto all’asse $x$

- **Duplicazione:** $2P = P + P$ (caso tangente)

- **Moltiplicazione scalare** (ripetuta somma):
$$
kP = \underbrace{P + P + \dots + P}_{k \text{ volte}}
$$

![](imgs/Pasted%20image%2020260613080344.png)

> 📌 **Connessione con ECDLP.** $kP$ si calcola efficientemente con l’algoritmo **double-and-add** (analogo di square-and-multiply): $O(\log k)$ operazioni. Dato $Q = kP$ e il punto $P$, trovare $k$ è il **Problema del Logaritmo Discreto su Curva Ellittica (ECDLP)**. Non esiste algoritmo sub-esponenziale noto per questo problema su curve ben scelte — da qui la forza crittografica di ECC.
    

---

### **6. Curve ellittiche su campi finiti $\mathbb{Z}_p$**

Per la crittografia si lavora su **campi finiti**, non su $\mathbb{R}$. Una **curva ellittica su $\mathbb{Z}_p$** (con $p$ primo e $p > 3$) è definita dall’equazione:

$$  
y^2 \equiv x^3 + ax + b \pmod{p}  
$$

con le condizioni:

- $a, b \in \mathbb{Z}_p$
- $4a^3 + 27b^2 \not\equiv 0 \pmod{p}$ — condizione di **non singolarità**

> 📌 **Perché $4a^3 + 27b^2 \neq 0$?** Questa quantità è il **discriminante** del polinomio cubico $f(x) = x^3 + ax + b$. Se il discriminante è zero, il polinomio ha una radice ripetuta — la curva ha un **punto singolare** (cuspide o nodo). In un punto singolare la tangente non è definita in modo unico, la regola della tangente si rompe, e la struttura di gruppo smette di funzionare. La condizione garantisce che la curva sia liscia in ogni punto, cioè che il gruppo sia ben definito.

> 💡 Su $\mathbb{Z}_p$, la curva non è più una forma geometrica continua ma un **insieme finito di punti** (con coordinate intere modulo $p$). L’intuizione geometrica della corda e della tangente vale ancora — solo che distanze e pendenze si calcolano con l’aritmetica modulare, usando inversioni modulo $p$ al posto delle divisioni reali.

Si include sempre il **punto all’infinito $O$** come elemento neutro del gruppo.

---

### **7. Addizione di punti in $\mathbb{Z}_p$ — formule algebriche**

> 💡 **Da dove vengono queste formule.** La pendenza $\lambda$ è la pendenza della retta coinvolta (corda o tangente). Le coordinate di $P_3$ si ottengono sostituendo la retta $y = \lambda(x - x_1) + y_1$ nell’equazione della curva $y^2 = x^3 + ax + b$ e usando il **teorema di Vieta**: se $x_1, x_2, x_3$ sono le tre radici del polinomio cubico risultante, allora $x_1 + x_2 + x_3 = \lambda^2$ (coefficiente del termine quadratico col segno), da cui $x_3 = \lambda^2 - x_1 - x_2$. Poi $y_3$ si ricava dalla retta stessa, riflessa.

Data una curva $E_p(a, b)$ e due punti $P_1 = (x_1, y_1)$, $P_2 = (x_2, y_2)$, l’addizione $P_3 = P_1 + P_2$ è:

$$  
\begin{cases}  
P_3 = O & \text{se } x_1 = x_2 \text{ e } y_1 \equiv -y_2 \pmod{p} \\[6pt]
\lambda = \dfrac{y_2 - y_1}{x_2 - x_1} \bmod p & \text{se } P_1 \ne P_2 \quad \text{(pendenza della secante)} \\[6pt]
\lambda = \dfrac{3x_1^2 + a}{2y_1} \bmod p & \text{se } P_1 = P_2 \quad \text{(pendenza della tangente)}
\end{cases}  
$$

> 📌 **Nota sulla divisione modulare.** In $\mathbb{Z}_p$, $\dfrac{a}{b} \bmod p$ non è una divisione reale ma $a \cdot b^{-1} \bmod p$, dove $b^{-1}$ si trova con Euclide esteso. Questo è il passo che trasforma la geometria continua in aritmetica modulare.

> 📌 **Pendenza della tangente — da dove viene $\dfrac{3x_1^2+a}{2y_1}$?** Si deriva implicitamente $y^2 = x^3 + ax + b$ rispetto a $x$: $2y \cdot y’ = 3x^2 + a$, da cui $y’ = \dfrac{3x^2+a}{2y}$. Questa è la pendenza della tangente nel punto $(x_1, y_1)$, calcolata su $\mathbb{Z}_p$ con divisione modulare.

Poi, in entrambi i casi:

$$
x_3 = \lambda^2 - x_1 - x_2 \bmod p
$$

$$
y_3 = \lambda(x_1 - x_3) - y_1 \bmod p
$$

---

### **8. Proprietà del gruppo $(E_p(a,b), +)$**

- $P + O = P$
    
- $O = -O$
    
- $-(x_1, y_1) = (x_1, -y_1)$
    
- $(E_p(a,b), +)$ è un **gruppo abeliano**
    

---

### **9. Esempio: curva $E_{23}(1,1)$**

Curva:

$$  
y^2 = x^3 + x + 1 \pmod{23}  
$$

Condizione di validità:

$$  
4a^3 + 27b^2 = 4\cdot 1^3 + 27\cdot 1^2 = 31 \equiv 8 \ne 0 \pmod{23}  
$$

✅ La curva è quindi **valida**.

#### **Punti appartenenti a $E_{23}(1,1)$**

$(0,1)$, $(0,22)$, $(1,7)$, $(1,16)$, $(3,10)$, $(3,13)$, $(4,0)$, $(5,4)$, $(5,19)$,  
$(6,4)$, $(6,19)$, $(7,11)$, $(7,12)$, $(9,7)$, $(9,16)$, $(11,3)$, $(11,20)$, $(12,4)$,  
$(12,19)$, $(13,7)$, $(13,16)$, $(17,3)$, $(17,20)$, $(18,3)$, $(18,20)$, $(19,5)$, $(19,18)$.

---

### **10. Costruzione dei punti**

Per ogni $x \in \{0, 1, \dots, 22\}$ si calcola $f(x) = x^3 + x + 1 \bmod 23$ e si verifica se è un **residuo quadratico** modulo $23$.

> 📌 **Come verificare se $f(x)$ è un quadrato in $\mathbb{Z}_p$.** Per il criterio di Eulero: $f(x)$ è un quadrato mod $p$ se e solo se $f(x)^{(p-1)/2} \equiv 1 \pmod{p}$. Per $p=23$: si calcola $f(x)^{11} \bmod 23$. Se vale $1$ → ci sono due soluzioni $\pm y$; se vale $-1$ ($\equiv 22$) → nessuna soluzione; se vale $0$ → l'unica soluzione è $y=0$ (punto con tangente verticale).

Se $f(x)$ è un quadrato, si ottengono **due punti** simmetrici:

$$
(x,\, y) \quad \text{e} \quad (x,\, -y \bmod 23) = (x,\, 23-y)
$$

Questo spiega la simmetria nella lista: ogni coppia $(y_1, y_2)$ con $y_1 + y_2 = 23$ (es. $(1,22)$, $(7,16)$, $(10,13)$, …) corrisponde a un unico valore di $x$.

---

### **11. Esempio di addizione in $E_{23}(1,1)$**

Sommiamo $P_1 = (3,10)$ e $P_2 = (9,7)$ — due punti distinti, si usa la formula della secante.

**Calcolo di $\lambda$** (pendenza della retta passante per $P_1$ e $P_2$):

$$
\lambda = \frac{y_2 - y_1}{x_2 - x_1} \bmod 23 = \frac{7-10}{9-3} \bmod 23 = \frac{-3}{6} \bmod 23
$$

La divisione modulare richiede l'inverso di $6$: $6 \cdot 4 = 24 \equiv 1 \pmod{23}$, quindi $6^{-1} = 4$.

$$
\lambda = (-3) \cdot 4 \bmod 23 = -12 \bmod 23 = 11
$$

**Calcolo di $x_3$ e $y_3$:**

$$
x_3 = \lambda^2 - x_1 - x_2 \bmod 23 = 121 - 3 - 9 = 109 \equiv 109 - 4\cdot23 = 17 \pmod{23}
$$

$$
y_3 = \lambda(x_1 - x_3) - y_1 \bmod 23 = 11(3-17) - 10 = 11\cdot(-14) - 10 = -164 \equiv -164 + 8\cdot23 = 20 \pmod{23}
$$

$$
\boxed{P_1 + P_2 = (17,\; 20)}
$$

Verifica: $(17, 20)$ compare nella lista dei punti di $E_{23}(1,1)$ ✓

---

### **12. Esempio di duplicazione in $E_{23}(1,1)$**

> ⚠️ **Correzione rispetto alle note originali.** L'esempio usa $P_1 = (3,10)$, non $(17,20)$ come indicato nel titolo originale: le coordinate $x_1=3, y_1=10$ appaiono esplicitamente nei calcoli. Il titolo era errato.

Calcoliamo $2P_1$ con $P_1 = (3,10)$ — punto uguale a sé stesso, si usa la formula della tangente.

**Calcolo di $\lambda$** (pendenza della tangente in $P_1$, con $a=1$):

$$
\lambda = \frac{3x_1^2 + a}{2y_1} \bmod 23 = \frac{3\cdot9 + 1}{2\cdot10} \bmod 23 = \frac{28}{20} \bmod 23
$$

- Numeratore: $28 \bmod 23 = 5$
- Denominatore: $20^{-1} \bmod 23$ — cerchiamo $20x \equiv 1 \pmod{23}$. Poiché $20 \equiv -3$, si ha $(-3)x \equiv 1$, cioè $x \equiv -1/3 \equiv -8 \equiv 15 \pmod{23}$. Verifica: $20\cdot15 = 300 = 13\cdot23 + 1$ ✓
- $\lambda = 5 \cdot 15 = 75 \equiv 75 - 3\cdot23 = 6 \pmod{23}$

**Calcolo di $x_3$ e $y_3$** (nella formula $x_1 = x_2 = 3$ poiché duplichiamo):

$$
x_3 = \lambda^2 - x_1 - x_2 \bmod 23 = 36 - 3 - 3 = 30 \equiv 7 \pmod{23}
$$

$$
y_3 = \lambda(x_1 - x_3) - y_1 \bmod 23 = 6(3-7) - 10 = -24 - 10 = -34 \equiv -34 + 2\cdot23 = 12 \pmod{23}
$$

$$
\boxed{2(3,10) = (7,\;12)}
$$

Verifica: $(7,12)$ compare nella lista dei punti di $E_{23}(1,1)$ ✓

---

### **13. Numero di punti su una curva ellittica**

> 💡 **Perché ci interessa sapere quanti punti ha la curva?** L’ordine del gruppo, cioè $|E_p(a,b)|$, determina la "taglia" del gruppo su cui gira l’ECDLP. Più grande è il gruppo, più difficile è trovare $x$ dato $P$ e $xP$. Per scegliere una curva crittograficamente sicura bisogna garantire che $|E_p(a,b)|$ sia abbastanza grande e abbia un fattore primo abbastanza grande (in modo che il sottogruppo usato sia difficile da attaccare con Baby-step Giant-step o Pohlig-Hellman).

Il numero di punti $|E_p(a,b)|$ su una curva ellittica $E_p(a,b)$ soddisfa il **teorema di Hasse**:

$$  
p + 1 - 2\sqrt{p} \le |E_p(a,b)| \le p + 1 + 2\sqrt{p}  
$$

> 📌 **Cosa dice il teorema in pratica.** Il numero di punti è "vicino a $p$": la deviazione non supera mai $2\sqrt{p}$. Per una curva su $\mathbb{F}_p$ con $p \approx 2^{256}$, la finestra è $[p+1-2^{129}, p+1+2^{129}]$, quindi $|E_p(a,b)| \approx 2^{256}$. Questo è fondamentale perché garantisce che, per $p$ grande, il gruppo ha ordine dello stesso ordine di grandezza di $p$ — abbastanza elementi da rendere il Pollard’s rho impraticabile.

> 💡 **Il termine $p+1$.** L’intuizione del "+1" viene dal punto all’infinito $\mathcal{O}$: su una curva generica su $\mathbb{F}_p$ ci si aspetterebbero circa $p$ punti affini (uno per ogni $x$, se l’equazione ha soluzione), più il punto all’infinito. Il teorema di Hasse formalizza che questa stima approssimata è sempre vera a meno di un errore di $2\sqrt{p}$.

Il conteggio esatto può essere effettuato con l’**algoritmo di Schoof** (1985), di complessità $O((\log p)^8)$ operazioni su bit — polinomiale in $\log p$, quindi efficiente in pratica.

---

### **14. Sintesi finale**

> ✅ **Schema rapido per l’esame.**

- Le **curve ellittiche** sono descritte dall’equazione di Weierstrass $y^2 = x^3 + ax + b$ su un campo finito $\mathbb{F}_p$, con condizione $4a^3 + 27b^2 \neq 0 \pmod{p}$ per garantire l’assenza di punti singolari.

- I punti della curva formano un **gruppo abeliano** con l’addizione definita geometricamente (corda-tangente): il punto all’infinito $\mathcal{O}$ è l’elemento neutro, e ogni punto ha un inverso nel suo simmetrico rispetto all’asse $x$.

- La **moltiplicazione scalare** $k \cdot P = \underbrace{P + P + \cdots + P}_{k}$ si calcola efficientemente con l’algoritmo **double-and-add** (analogo dello square-and-multiply di RSA), in $O(\log k)$ operazioni.

- Il problema **ECDLP**: dato $P$ e $Q = kP$, trovare $k$. Il miglior algoritmo noto (Pollard’s rho) ha complessità $O(\sqrt{|G|})$ — esponenziale nella metà dei bit — molto peggio del NFS su $\mathbb{Z}_p^*$. Questo è il motivo per cui ECC richiede chiavi molto più corte a parità di sicurezza:

| Sicurezza (bit) | RSA / El-Gamal | ECC |
|---|---|---|
| 80 | 1024 bit | 160 bit |
| 128 | 3072 bit | 256 bit |
| 256 | 15360 bit | 512 bit |

- Il **teorema di Hasse** garantisce $|E_p(a,b)| \approx p$: il gruppo ha ordine comparabile a $p$, quindi scegliere $p \approx 2^{256}$ assicura un gruppo con $\approx 2^{256}$ elementi — abbastanza da rendere Pollard’s rho infeasibile.