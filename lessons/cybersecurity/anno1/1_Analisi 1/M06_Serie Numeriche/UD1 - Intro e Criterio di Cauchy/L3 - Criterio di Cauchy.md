# **M6 UD1 Lezione 3 - Criterio di Cauchy per le serie**

### **1. Enunciato**

Sia $\sum_{n=n_0}^{\infty}a_n$ una serie reale o complessa. Essa converge se e solo se

$$
\forall\varepsilon>0\ \exists N\geq n_0\ \forall q>p\geq N:
\left|\sum_{n=p+1}^{q}a_n\right|<\varepsilon.
$$

Il criterio controlla ogni somma finita contenuta sufficientemente avanti nella coda. Non richiede di conoscere la somma $S$.

<!-- TODO FIGURA: successione delle somme parziali S_N con una coda contenuta in un disco o intervallo di diametro epsilon; indicare due indici p<q, la differenza S_q-S_p e la corrispondente somma di coda. Obiettivo didattico: derivare il criterio di Cauchy per serie da quello per successioni. -->

### **2. Dimostrazione e completezza**

Poniamo

$$
S_N=\sum_{n=n_0}^{N}a_n.
$$

Per $q>p$,

$$
S_q-S_p=\sum_{n=p+1}^{q}a_n.
$$

La condizione dell'enunciato è dunque esattamente la condizione studiata nella lezione sulle [successioni di Cauchy](../../M05_Successioni/UD2%20-%20Limiti%20e%20teoremi/L3%20-%20Successioni%20di%20Cauchy.md), applicata a $(S_N)$. In $\mathbb R$ e in $\mathbb C$, che sono completi, una successione è convergente se e solo se è di Cauchy. Ne segue l'equivalenza. $\square$

In $\mathbb Q$ la stessa condizione garantisce un limite reale, ma non necessariamente razionale: la sufficienza dipende dalla completezza dello spazio ambiente.

### **3. Differenza dalla condizione $a_n\to0$**

Prendendo $q=p+1$, il criterio implica

$$
|a_{p+1}|<\varepsilon
$$

definitivamente, quindi $a_n\to0$. Il criterio è però più forte: controlla somme con un numero arbitrario di addendi.

Per l'armonica, scegliendo $p=n$ e $q=2n$,

$$
\sum_{k=n+1}^{2n}\frac1k
\geq n\frac1{2n}=\frac12.
$$

La coda non può essere resa minore di $\varepsilon=1/2$, benché ogni singolo termine tenda a zero.

### **4. Applicazione alla geometrica**

Per $|q|<1$ e $m>n$,

$$
\left|\sum_{k=n+1}^{m}q^k\right|
\leq\sum_{k=n+1}^{m}|q|^k
\leq\frac{|q|^{n+1}}{1-|q|}.
$$

Il maggiorante tende a zero; il criterio di Cauchy prova la convergenza senza calcolare preventivamente la somma.

### **5. Criterio di Cauchy assoluto**

Se

$$
\sum_{n=n_0}^{\infty}|a_n|
$$

converge, allora per $q>p$ grandi

$$
\left|\sum_{n=p+1}^{q}a_n\right|
\leq\sum_{n=p+1}^{q}|a_n|<\varepsilon.
$$

Quindi $\sum a_n$ soddisfa Cauchy e converge. Questa è la prova centrale del teorema «convergenza assoluta implica convergenza», che sarà sviluppato anche mediante parti positiva e negativa in UD3.

### **6. Code e resti**

Se la serie converge a $S$, fissato $p=N$ e lasciando $q\to\infty$ nella differenza delle somme parziali si ottiene

$$
R_N=S-S_N=\lim_{q\to\infty}\sum_{n=N+1}^{q}a_n.
$$

Una stima uniforme sulle code finite passa quindi al resto. Per esempio, dalla maggiorazione geometrica segue

$$
|R_N|\leq\frac{|q|^{N+1}}{1-|q|},
$$

che è utile anche quando i termini hanno argomenti o segni variabili.

### **7. Uso corretto del criterio**

Il criterio di Cauchy è una caratterizzazione, ma non sempre è lo strumento più rapido. Confronto, rapporto, radice, Leibniz e Dirichlet sono metodi per verificare la piccolezza delle code sotto ipotesi riconoscibili.

Per dimostrare divergenza mediante Cauchy bisogna trovare un $\varepsilon_0>0$ tale che per ogni $N$ esistano $q>p\geq N$ con

$$
\left|\sum_{n=p+1}^{q}a_n\right|\geq\varepsilon_0.
$$

### **8. Errori frequenti**

1. Controllare soltanto $|a_n|<\varepsilon$.
2. Lasciare che $N$ dipenda da $p$ o $q$.
3. Verificare una sola lunghezza di coda invece di ogni $q>p$.
4. Dimenticare il ruolo della completezza nella sufficienza.
5. Confondere la coda finita del criterio con il resto infinito già valutato.
6. Usare il criterio per una serie complessa senza il modulo.

### **9. Esercizi**

1. Derivare il criterio a partire dalla definizione di successione di Cauchy.
2. Dimostrare con Cauchy la convergenza di $\sum 3^{-n}$.
3. Dimostrare con Cauchy la divergenza dell'armonica scegliendo indici adatti.
4. Mostrare che $\sum(-1)^n$ fallisce Cauchy con code di un solo termine.
5. Verificare che $\sum1/[n(n+1)]$ è di Cauchy mediante la forma telescopica.
6. Dimostrare che la convergenza assoluta implica convergenza anche per serie complesse.
7. Formulare la negazione completa del criterio con tutti i quantificatori.
8. Spiegare perché il controllo delle sole code $\sum_{k=n+1}^{2n}a_k$ non è in generale sufficiente per provare convergenza.
9. Deduci una stima del resto da una maggiorazione uniforme delle code finite.

### **10. Riepilogo**

> ✅ Cauchy traduce la convergenza della serie nel controllo uniforme di tutte le code finite. La condizione $a_n\to0$ ne è soltanto una conseguenza necessaria e perde l'informazione sull'accumulo di molti termini.
