# **M1 UD4 Lezione 2 - Legge dei grandi numeri per il valore atteso**

### **1. Introduzione**

Questa lezione approfondisce la **seconda formulazione** della legge dei grandi numeri, che collega la **media empirica** di una quantità aleatoria al suo **valore atteso**.  
La prima formulazione — vista nella lezione precedente — trattava il rapporto fra **frequenze** e **probabilità**; ora, invece, il concetto si estende a **quantità numeriche associate a eventi aleatori**, come guadagni, perdite o tempi di attesa.

---

### **2. Due formulazioni della legge dei grandi numeri**

1. **Prima formulazione:**  
    Al crescere del numero di prove $n$, la **frequenza empirica** $f$ di un evento tende alla sua probabilità $p$:  

$$  
    \lim_{n \to \infty} f = p  
    $$
    
2. **Seconda formulazione:**  
    Al crescere del numero di prove $n$, la **media empirica** $m$ di una quantità associata a un evento tende al suo **valore atteso**:  

$$  
    \lim_{n \to \infty} m = \langle x \rangle  
    $$


La prima è un **caso particolare** della seconda, in cui la quantità aleatoria assume solo i valori 1 (evento accaduto) e 0 (evento non accaduto).

---

### **3. Quantità associate a un evento aleatorio**

Ogni evento aleatorio può avere associata una **quantità numerica di interesse**, positiva o negativa:

- un **guadagno o perdita** in una scommessa,
    
- uno **spostamento** in un gioco,
    
- un **tempo di attesa** in un processo casuale.

Esempio:  
Nel lancio di una moneta:

- se esce **Testa (A)**, il giocatore guadagna $x(A) > 0$;
    
- se esce **Croce ($\neg A$)**, perde $x(\neg A) < 0$.

Con probabilità $p$ e $q = 1 - p$, le quantità possono essere rappresentate così:

|Evento|Probabilità|Guadagno|
|---|---|---|
|$A$|$p$|$x(A) > 0$|
|$\neg A$|$q$|$x(\neg A) < 0$|

---

### **4. Definizione di valore atteso**

Il **valore atteso** di una quantità aleatoria $x$ è la **somma pesata** dei suoi possibili valori, ciascuno moltiplicato per la relativa probabilità:

$$  
\langle x \rangle = x(A) \cdot p + x(\neg A) \cdot (1 - p)  
$$

È anche detto **speranza matematica** o **expected value**, e si indica comunemente con $E(x)$.

---

### **5. Esempio – Moneta bilanciata**

Supponiamo che:

- la moneta sia bilanciata ($p = q = \tfrac{1}{2}$),
    
- $x(T) = 7$ euro (vincita),
    
- $x(C) = -3$ euro (perdita).

Allora:

$$  
\langle x \rangle = (7)\tfrac{1}{2} + (-3)\tfrac{1}{2} = 2  
$$

Il valore atteso della scommessa è di **+2 euro**, cioè in media ogni giocata porta un guadagno di 2 euro.


![](imgs/Pasted%20image%2020251216165851.png)


Con cento lanci, già le fluttuazioni si attenuano:


![](imgs/Pasted%20image%2020251216165906.png)

---

### **6. Media aritmetica e media empirica**

Data una sequenza di $n$ risultati numerici:

$$  
x_1, x_2, \dots, x_n  
$$

la **media aritmetica** (o **media empirica**) è definita come:

$$  
m = \frac{1}{n} \sum_{i=1}^{n} x_i  
$$

Essa rappresenta la **media sperimentale** dei risultati ottenuti dopo $n$ prove.

---

### **7. Legge dei grandi numeri – seconda formulazione**

La **Legge dei grandi numeri per il valore atteso** afferma che:

> Al crescere del numero di prove $n$, la media empirica $m$ di una quantità aleatoria tende al suo valore atteso $\langle x \rangle$.

Formalmente:

$$  
\lim_{n \to \infty} m = \langle x \rangle  
$$

---

### **8. Esempio empirico – Scommessa con moneta**

Nel caso precedente ($p = q = 1/2$, $\langle x \rangle = 2$):

- se lanciamo la moneta molte volte,
    
- annotiamo ogni guadagno o perdita,
    
- e calcoliamo la media empirica dei risultati,

osserveremo che $m$ tende progressivamente a 2.

Più grande è $n$, **minori sono le fluttuazioni** attorno al valore atteso.

---

### **9. Esempio numerico**

Con $p = 1/2$ e $\langle x \rangle = 2$:

- per $n = 100$ lanci → guadagno totale $\approx 200$ euro;
    
- per $n = 1,000$ lanci → guadagno totale $\approx 2,000$ euro;
    
- per $n = 10,000$ lanci → guadagno totale $\approx 20,000$ euro.

$$  
t = \langle x \rangle \cdot n  
$$

Le oscillazioni sono inevitabili, ma la **fluttuazione relativa** diminuisce man mano che $n$ cresce.

---

### **10. Dal caso aleatorio al determinismo**

La legge mostra come, con un numero elevato di prove,  
le oscillazioni casuali tendano a **compensarsi**,  
e il comportamento medio del sistema diventi **prevedibile** e **deterministico**.

È questo principio che consente di applicare la probabilità a fenomeni reali — ad esempio, nella **statistica** o nel **calcolo attuariale**.

---

### **11. Esempio – Gioco del lotto**

Nel gioco del lotto vengono estratti 5 numeri su 90.

#### **Caso 1: singolo numero**

- Probabilità di vincita: $p = \frac{5}{90} = \frac{1}{18}$
    
- Vincita: +11 €, perdita: –1 €

$$  
\langle x \rangle = 11 \cdot \frac{1}{18} + (-1)\frac{17}{18} = -\frac{1}{3}  
$$

👉 In media, si perdono **1 euro ogni 3 giocati**.

---

#### **Caso 2: ambo**

- Numero di coppie possibili: $4005$
    
- Numero di coppie estratte: $10$
    
- Probabilità: $p = \frac{10}{4005} \approx \frac{1}{400}$
    
- Vincita: +250 €, perdita: –1 €

$$  
\langle x \rangle = 250\cdot\frac{1}{400} + (-1)\cdot\frac{399}{400} = -\frac{149}{400}  
$$

👉 In media, si perdono **149 euro ogni 400 giocati**.

---

#### **Caso 3: Superenalotto**

- $p = \frac{1}{622,614,630}$
    
- Vincita: 62.3 milioni €
    
- Perdita: –1 €

$$  
\langle x \rangle = (62.3M)p + (-1)(1 - p) \approx -0.9  
$$

👉 Anche con montepremi molto alti, si perdono mediamente **9 euro ogni 10 giocati**.

---

### **12. Interpretazione geometrica del valore atteso**

Il valore atteso può essere interpretato come un **baricentro** su un asse, dove:

- le **probabilità** agiscono come **pesi**,
    
- e i **guadagni o perdite** come **distanze** dall’origine.

$$  
\langle x \rangle = x(A)p + x(\neg A)q  
$$

- Se il baricentro è **positivo**, il gioco è **favorevole** al giocatore.
    
- Se è **negativo**, è **favorevole al banco**.
    
- Se si trova esattamente in **0**, la scommessa è **equa**.


![](imgs/Pasted%20image%2020251216165951.png)

---

### **13. Condizione di equità**

Per una moneta con puntate $u$ (giocatore 1 Ugo su croce) e $v$ (giocatore 2 Vito su testa), dobbiamo realizzare la condizione:

$$  
\langle x \rangle = x(A)p + x(\neg A)q  
$$
e porre il valore atteso = 0. Ricordiamo che in questo frangente:

- Vito ha probabilità $P(T) = p$ di vincere la posta $u$ di Ugo,
- Ugo ha probabilità $P(C) = q = 1 - p$ di vincere la posta di $v$ su Vito.

$$  
x(Testa) \cdot p + x(Croce) \cdot q = 0  
$$
Ora sostituiamo le due x con le puntate rispettive, tenendo conto che siamo dal punto di vista di Vito, e quindi x di Testa si traduce in vincita positiva u, mentre x di Croce muta in perdita di v, con segno negativo:

$$  
u \cdot p + (-v) \cdot q = 0  
$$

Già che ci siamo convertiamo q in funzione di p:

$$  
0 = u p + (-v)(1-p)  
$$
Spostiamo a sinistra la parte "negativa":

$$  
v(1-p)   = u p
$$
leggiamo da dx a sx:

$$  
up = v(1-p)
$$
da cui:

$$  
\frac{u}{v} = \frac{1-p}{p}  
$$

La **scommessa è equa** quando ciascuno punta in proporzione alla propria probabilità di vincere.

---

### **14. Generalizzazione del valore atteso**

Per più di due eventi possibili $A_1, A_2, \dots, A_r$ con esiti $x(A_i)$ e probabilità $P(A_i)$:

$$  
\langle x \rangle = \sum_{i=1}^{r} x(A_i) \cdot P(A_i)  
$$

È la formula generale per qualunque **variabile aleatoria discreta**.

---

### **15. Dalla seconda alla prima formulazione**

La prima legge dei grandi numeri si ricava dalla seconda ponendo:

$$  
x(T) = 1, \quad x(C) = 0  
$$

Allora:

$$  
\langle x \rangle = 1 \cdot p + 0 \cdot q = p  
$$

e la media empirica $m = f = k/n$.  
Quindi:

$$  
\lim_{n \to \infty} f = p  
$$

ovvero la **prima formulazione** è un **caso particolare** della seconda.

---

### **16. Sintesi concettuale**

|**Concetto**|**Formula o Significato**|
|---|---|
|Valore atteso (speranza matematica)|$\langle x \rangle = \sum_i x_i P(x_i)$|
|Media empirica|$m = \frac{1}{n}\sum_i x_i$|
|Legge dei grandi numeri (II)|$\lim_{n \to \infty} m = \langle x \rangle$|
|Condizione di equità|$\langle x \rangle = 0$|
|Gioco equo|Vincite e perdite pesate si compensano|

---

### **17. Conclusione**

La **Legge dei grandi numeri per il valore atteso** estende quella sulle frequenze, rendendo quantitativo il concetto di “media stabilizzata”.  
Il **valore atteso** diventa così una misura universale di sintesi per ogni fenomeno aleatorio — dalla scommessa alla statistica, fino alla fisica e all’economia.