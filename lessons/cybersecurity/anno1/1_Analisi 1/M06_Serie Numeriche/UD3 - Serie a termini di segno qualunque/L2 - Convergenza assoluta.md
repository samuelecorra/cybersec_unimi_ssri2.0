# **M6 UD3 Lezione 2 - Convergenza assoluta e condizionata**

### **1. Definizioni**

La serie reale o complessa $\sum a_n$ converge **assolutamente** se

$$
\sum|a_n|
$$

converge. Converge **condizionatamente** se $\sum a_n$ converge, ma $\sum|a_n|$ diverge.

Le tre classificazioni sono distinte:

- convergenza assoluta;
- convergenza condizionata;
- divergenza della serie originale.

### **2. Assoluta implica ordinaria**

> **Teorema.** Se $\sum|a_n|$ converge, allora $\sum a_n$ converge.

**Dimostrazione.** Per $q>p$,

$$
\left|\sum_{n=p+1}^{q}a_n\right|
\leq
\sum_{n=p+1}^{q}|a_n|.
$$

Il membro destro è arbitrariamente piccolo per $p,q$ grandi, perché la serie dei moduli soddisfa Cauchy. Anche $\sum a_n$ soddisfa Cauchy e converge per completezza di $\mathbb R$ o $\mathbb C$. $\square$

Il converso è falso: l'armonica alternata converge, mentre la serie dei moduli è armonica e diverge.

<!-- TODO FIGURA: confrontare tre successioni di somme parziali: una serie positiva assolutamente convergente, la stessa con segni alterni, e l'armonica alternata condizionata; affiancare l'accumulo delle somme dei moduli. Obiettivo didattico: distinguere robustezza assoluta e convergenza dovuta a cancellazioni. -->

### **3. Parte positiva e parte negativa**

Per una successione reale definiamo

$$
a_n^+=\max\{a_n,0\},
\qquad
a_n^-=\max\{-a_n,0\}.
$$

Allora

$$
a_n=a_n^+-a_n^-,
\qquad
|a_n|=a_n^++a_n^-.
$$

Le due serie $\sum a_n^+$ e $\sum a_n^-$ hanno termini non negativi.

- $\sum a_n$ converge assolutamente se e solo se entrambe convergono.
- Se una converge e l'altra diverge, le somme parziali di $\sum a_n$ tendono rispettivamente a $+\infty$ o a $-\infty$ secondo la parte divergente.
- Se $\sum a_n$ converge condizionatamente, entrambe le parti divergono a $+\infty$: quantità positive e negative infinite si compensano nell'ordine dato.
- Se entrambe divergono, la serie originale può convergere condizionatamente oppure divergere per oscillazione; serve studiare l'ordine delle cancellazioni.

Per giustificare il terzo punto, se una delle due parti convergesse, dall'identità tra le somme parziali $S_N=P_N-N_N$ e dalla convergenza di $(S_N)$ seguirebbe anche la convergenza dell'altra. Convergerebbero allora entrambe e $\sum|a_n|=\sum a_n^++\sum a_n^-$ sarebbe convergente, contro l'ipotesi di convergenza condizionata.

### **4. Stabilità e riordinamenti**

La convergenza assoluta è robusta rispetto a cambi di segno, raggruppamenti e permutazioni: il controllo delle ampiezze impedisce che una coda riordinata accumuli massa significativa. La dimostrazione completa dell'invarianza sotto ogni permutazione è in UD3/L2B.

La convergenza condizionata dipende invece dall'ordine. Il teorema di Riemann mostrerà che un riordinamento può cambiare la somma o distruggere la convergenza.

### **5. Criteri applicati ai moduli**

Confronto, rapporto e radice si applicano a $|a_n|$. Per esempio,

$$
\sum_{n=1}^{\infty}(-1)^n\frac{3^n}{n!}
$$

converge assolutamente perché

$$
\frac{3^{n+1}/(n+1)!}{3^n/n!}=\frac3{n+1}\to0.
$$

Per

$$
\sum_{n=1}^{\infty}\frac{\sin n}{n^2},
$$

$$
\left|\frac{\sin n}{n^2}\right|\leq\frac1{n^2},
$$

quindi la convergenza è assoluta.

### **6. Famiglia alternata parametrica**

Studiamo

$$
\sum_{n=1}^{\infty}\frac{(-1)^{n-1}}{n^\alpha}.
$$

- Se $\alpha\leq0$, il termine generale non tende a zero: diverge.
- Se $\alpha>0$, $1/n^\alpha$ decresce a zero: converge per Leibniz.
- Se $\alpha>1$, la serie dei moduli è una $p$-serie convergente: convergenza assoluta.
- Se $0<\alpha\leq1$, la serie dei moduli diverge: convergenza condizionata.

Questa classificazione include esplicitamente i casi di frontiera $\alpha=0$ e $\alpha=1$.

### **7. Famiglia geometrico-armonica**

Per $x\in\mathbb R$ consideriamo

$$
\sum_{n=1}^{\infty}\frac{x^n}{n}.
$$

- Se $|x|<1$, converge assolutamente per il rapporto.
- Se $|x|>1$, il termine non tende a zero.
- Per $x=1$, è la serie armonica e diverge.
- Per $x=-1$, è l'armonica alternata e converge condizionatamente.

Non si attribuisce qui una formula alla somma come funzione di $x$: ciò appartiene alle serie di potenze e al logaritmo nei moduli successivi.

### **8. Errori frequenti**

1. Dire «non assoluta» e concludere automaticamente «condizionata» senza provare la convergenza ordinaria.
2. Applicare rapporto o radice senza valori assoluti.
3. Confondere $a_n^-$ con $\min\{a_n,0\}$: per convenzione è non negativa.
4. Dedurre convergenza delle parti positiva e negativa dalla sola convergenza ordinaria.
5. Riordinare una serie condizionata come se fosse assoluta.
6. Omettere i casi di frontiera in una classificazione parametrica.

### **9. Esercizi**

1. Dimostrare «assoluta implica ordinaria» con Cauchy.
2. Dimostrare la caratterizzazione tramite $a_n^+$ e $a_n^-$.
3. Classificare $\sum(-1)^n/n^\alpha$ per ogni $\alpha\in\mathbb R$.
4. Classificare $\sum x^n/n$ per ogni $x\in\mathbb R$.
5. Studiare $\sum(-1)^n(2n+1)/(n^3+1)$.
6. Studiare l'assoluta convergenza di $\sum\cos n/n^p$ per $p>1$.
7. Fornire un esempio in cui entrambe le parti positive e negative divergano e la serie originale oscilli.
8. Spiegare perché modifiche finite preservano la convergenza condizionata.
9. Costruire una serie convergente ma non assolutamente convergente diversa dall'armonica alternata.
10. **Problema trasversale d'esame.** Per $\alpha,x\in\mathbb R$ classificare

$$
\sum_{n=1}^{\infty}\frac{(-1)^{n-1}x^n}{n^\alpha}.
$$

Verificare nell'ordine: condizione necessaria; termine dominante; criterio scelto e relative ipotesi; convergenza assoluta o condizionata; casi $|x|<1$, $|x|>1$, $x=1$ e $x=-1$; stima del resto quando si applica Leibniz; ragione per cui rapporto e radice sono inconcludenti sui punti $|x|=1$.

### **10. Riepilogo**

> ✅ La convergenza assoluta controlla la massa totale e implica convergenza tramite Cauchy. La convergenza condizionata nasce invece da cancellazioni tra parti positiva e negativa entrambe infinite ed è sensibile all'ordine dei termini.
