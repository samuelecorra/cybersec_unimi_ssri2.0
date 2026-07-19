# **M5 UD1 Lezione 1 - Introduzione alle successioni**

### **1. Un processo discreto**

Una successione registra l'evoluzione di una grandezza a istanti discreti: iterazioni di un algoritmo, capitale dopo ogni periodo, errore dopo ogni passo o somme parziali. L'indice non varia con continuità.

Adottiamo la convenzione già fissata:

$$
\mathbb N=\{0,1,2,\ldots\}.
$$

> 📌 Una **successione reale** è una funzione $a:\mathbb N\to\mathbb R$. Il termine di indice $n$ è $a_n=a(n)$.

---

### **2. Notazioni e oggetti distinti**

Scriviamo

$$
(a_n)_{n\in\mathbb N},\qquad (a_n),\qquad a_0,a_1,a_2,\ldots
$$

Occorre distinguere:

- la successione, cioè la funzione completa;
- l'indice $n$;
- il termine $a_n$;
- l'immagine $a(\mathbb N)=\{a_n:n\in\mathbb N\}$;
- una formula che descrive la successione.

Per esempio,

$$
a_n=(-1)^n
$$

ha infiniti termini ma immagine finita

$$
a(\mathbb N)=\{-1,1\}.
$$

La successione non coincide quindi con il proprio insieme immagine: ordine e ripetizioni fanno parte dei dati.

> ⚠️ Se una formula contiene $1/n$, si deve dichiarare $n\ge1$ oppure assegnare separatamente $a_0$.

---

### **3. Modi di definizione**

#### **3.1 Formula esplicita e definizione per casi**

Una formula esplicita calcola direttamente il termine:

$$
a_n=\frac{n}{n+1}.
$$

Una definizione per casi può dipendere dalla parità:

$$
b_n=
\begin{cases}
1/n, & n\ge1\text{ pari},\\
-1/n, & n\text{ dispari},\\
0, & n=0.
\end{cases}
$$

#### **3.2 Ricorrenza**

Una ricorrenza richiede dati iniziali e regola:

$$
a_0=1,\qquad a_{n+1}=2a_n.
$$

Per ricorrenze di ordine due servono due dati:

$$
a_0=0,\quad a_1=1,\quad a_{n+2}=pa_{n+1}+qa_n.
$$

Ben definitezza e unicità seguono per induzione se la regola produce un unico termine ammesso a partire dai precedenti.

#### **3.3 Campionamento, somme e prodotti**

Da una funzione $f$ si ottiene $a_n=f(n)$. Date quantità $u_k$, si definiscono

$$
s_n=\sum_{k=0}^n u_k,
\qquad
p_n=\prod_{k=0}^n u_k.
$$

Le somme parziali preparano M06, ma qui sono studiate soltanto come successioni.

---

### **4. Famiglie fondamentali**

- Costante: $a_n=c$.
- Aritmetica: $a_n=a_0+nd$.
- Geometrica: $a_n=a_0q^n$.
- Potenza: $a_n=n^\alpha$ per $n\ge1$.
- Reciproca: $a_n=n^{-\alpha}$, $\alpha>0$.
- Fattoriale: $a_n=n!$.
- Binomiale: $a_n=\binom{n}{k}$ con $k$ fissato.
- Alternata: $a_n=(-1)^nu_n$.
- Periodica: esiste $p\ge1$ con $a_{n+p}=a_n$.

Esempi con radicali o logaritmi richiedono il dominio:

$$
\sqrt{n+1}-\sqrt n,
\qquad
\ln(n+1).
$$

---

### **5. Rappresentazioni**

Una successione può essere presentata mediante formula, tabella o grafico dei punti

$$
(n,a_n),\qquad n\in\mathbb N.
$$

Il grafico è discreto: congiungere i punti introduce un'interpolazione che non appartiene alla successione.

<!-- TODO FIGURA: piano con asse orizzontale n naturale e asse verticale a_n, punti discreti delle successioni n/(n+1), (-1)^n e una costante, senza segmenti continui; obiettivo didattico: distinguere grafico discreto, ordine dei termini e insieme immagine. -->

---

### **6. Successioni complesse**

Come estensione di M04, una successione complessa è

$$
z:\mathbb N\to\mathbb C.
$$

Si dirà $z_n\to z$ se

$$
|z_n-z|\to0.
$$

Equivalentemente,

$$
\operatorname{Re}(z_n)\to\operatorname{Re}(z),
\qquad
\operatorname{Im}(z_n)\to\operatorname{Im}(z).
$$

La prova seguirà dall'identità

$$
|\operatorname{Re}w|,|\operatorname{Im}w|\le|w|
\le|\operatorname{Re}w|+|\operatorname{Im}w|.
$$

Il percorso principale di M05 resta reale; non si introducono olomorfia o analisi complessa.

---

### **7. Prime classificazioni**

Una successione può essere costante, periodica, monotona, limitata, convergente, divergente a infinito oppure oscillante. Queste proprietà non sono equivalenti:

- $(-1)^n$ è limitata e periodica, ma non converge;
- $n$ è crescente, ma non limitata;
- $1/(n+1)$ è positiva, decrescente e convergente;
- $(-1)^nn$ oscilla ed è illimitata.

Il termine **divergente** significa soltanto non convergente a un reale; non è sinonimo di "tende a infinito".

---

### **8. Errori frequenti**

1. Confondere $n$ con $a_n$.
2. Confondere successione e immagine.
3. Usare $a_n=1/n$ a partire da $n=0$.
4. Considerare il grafico una curva continua.
5. Omettere dati iniziali in una ricorrenza.
6. Dire che una successione oscillante è necessariamente limitata.

---

### **9. Esercizi**

1. Determina i primi sei termini e l'immagine di $a_n=(-1)^n$.
2. Scrivi una successione con immagine $\{0,1\}$ che non sia periodica.
3. Definisci per casi la successione $0,1,-1,1/2,-1/2,\ldots$.
4. Verifica per induzione l'esistenza e l'unicità della ricorrenza $a_0=1$, $a_{n+1}=\sqrt{a_n+3}$.
5. Trasforma $a_{n+1}=3a_n-2$, $a_0=4$, in formula esplicita.
6. Costruisci somme parziali e prodotti parziali di $u_k=1/(k+1)$.
7. Classifica $n$, $(-1)^n$, $2^{-n}$ e $n\sin(\pi n/2)$ senza ancora calcolarne formalmente i limiti.
8. Dimostra l'equivalenza tra convergenza complessa e convergenza delle due componenti, assumendo l'algebra dei limiti reali.

---

### **10. Riepilogo**

> ✅ Una successione è una funzione su $\mathbb N$, non un insieme di valori. La variabile è discreta, i dati iniziali sono parte delle ricorrenze e il comportamento definitivo sarà formalizzato mediante limiti.
