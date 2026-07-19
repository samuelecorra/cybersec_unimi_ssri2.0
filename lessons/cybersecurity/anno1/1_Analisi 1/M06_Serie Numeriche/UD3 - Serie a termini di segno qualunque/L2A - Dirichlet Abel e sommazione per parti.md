# **M6 UD3 Lezione 2A - Dirichlet, Abel e sommazione per parti**

### **1. Identità di sommazione per parti**

Siano $a_n,b_n$ successioni e, per indici $p\leq n$, poniamo

$$
A_n=\sum_{k=p}^{n}a_k,
\qquad A_{p-1}=0.
$$

Poiché $a_n=A_n-A_{n-1}$,

$$
\sum_{n=p}^{q}a_nb_n
=A_qb_q+
\sum_{n=p}^{q-1}A_n(b_n-b_{n+1}).
$$

**Dimostrazione.** Sostituendo $a_n$,

$$
\sum_{n=p}^{q}(A_n-A_{n-1})b_n
=\sum_{n=p}^{q}A_nb_n-\sum_{n=p}^{q}A_{n-1}b_n.
$$

Nel secondo termine cambiamo indice; i termini interni si accoppiano e restano il bordo $A_qb_q$ e le differenze $A_n(b_n-b_{n+1})$. $\square$

È l'analogo discreto dell'integrazione per parti, che sarà formalizzata in M11. Gli indici e i termini di bordo sono parte essenziale dell'identità.

### **2. Criterio di Dirichlet**

> **Teorema.** Se le somme parziali

$$
A_N=\sum_{n=n_0}^{N}a_n
$$

sono limitate e $(b_n)$ è monotona con $b_n\to0$, allora $\sum a_nb_n$ converge.

**Dimostrazione.** A meno di un cambio di segno e di termini finiti, supponiamo $b_n\geq0$ e decrescente. Se $|A_N|\leq M$, le somme locali

$$
C_n=\sum_{k=p}^{n}a_k=A_n-A_{p-1}
$$

soddisfano $|C_n|\leq2M$. Applicando la sommazione per parti alla coda da $p$ a $q$,

$$
\left|\sum_{n=p}^{q}a_nb_n\right|
\leq2M b_q+2M\sum_{n=p}^{q-1}(b_n-b_{n+1})
\leq4M b_p.
$$

Poiché $b_p\to0$, le code soddisfano Cauchy. $\square$

### **3. Criterio di Abel**

> **Teorema.** Se $\sum a_n$ converge e $(b_n)$ è monotona e limitata, allora $\sum a_nb_n$ converge.

**Dimostrazione.** Una successione monotona e limitata converge a un limite $L$. Scriviamo

$$
b_n=L+(b_n-L).
$$

La serie $L\sum a_n$ converge. Le somme parziali di $(a_n)$ sono limitate e $(b_n-L)$ è monotona verso $0$, quindi $\sum a_n(b_n-L)$ converge per Dirichlet. La somma delle due serie converge. $\square$

Questo è il criterio di Abel per serie numeriche, distinto dal futuro teorema di Abel sulle serie di potenze.

### **4. Somme trigonometriche limitate**

Per $\theta\notin2\pi\mathbb Z$,

$$
\sum_{n=1}^{N}e^{in\theta}
=e^{i\theta}\frac{1-e^{iN\theta}}{1-e^{i\theta}}.
$$

Pertanto

$$
\left|\sum_{n=1}^{N}e^{in\theta}\right|
\leq\frac2{|1-e^{i\theta}|},
$$

uniformemente in $N$. Parte reale e parte immaginaria mostrano che le somme parziali di $\cos(n\theta)$ e $\sin(n\theta)$ sono limitate.

Dirichlet implica quindi, per $\theta\notin2\pi\mathbb Z$,

$$
\sum_{n=1}^{\infty}\frac{\sin(n\theta)}n
\quad\text{e}\quad
\sum_{n=1}^{\infty}\frac{\cos(n\theta)}n
$$

convergenti. Se $\theta\in2\pi\mathbb Z$, la prima è nulla e la seconda coincide con l'armonica, quindi diverge.

### **5. Serie geometriche trigonometriche**

Per $|r|<1$, la serie complessa

$$
\sum_{n=0}^{\infty}(re^{i\theta})^n
=\frac1{1-re^{i\theta}}
$$

converge assolutamente. Separando parti reale e immaginaria,

$$
\sum_{n=0}^{\infty}r^n\cos(n\theta)
=\frac{1-r\cos\theta}{1-2r\cos\theta+r^2},
$$

$$
\sum_{n=0}^{\infty}r^n\sin(n\theta)
=\frac{r\sin\theta}{1-2r\cos\theta+r^2}.
$$

Il richiamo usa la [forma esponenziale complessa](../../M04_Numeri%20Complessi/UD2%20-%20Forma%20trigonometrica%20ed%20esponenziale/L3%20-%20Forma%20esponenziale.md) certificata in M04, senza riaprire la teoria complessa e senza anticipare Fourier.

### **6. Confronto tra Dirichlet e Abel**

Dirichlet richiede somme parziali limitate di $a_n$ e un fattore monotono che tende a zero. Abel parte da una serie $\sum a_n$ già convergente e permette un fattore monotono limitato, non necessariamente nullo. La prova di Abel riduce il problema a Dirichlet separando il limite di $b_n$.

Nessuno dei due criteri implica in generale convergenza assoluta.

### **7. Errori frequenti**

1. Usare Dirichlet senza provare la limitatezza delle somme parziali di $a_n$.
2. Sostituire «monotona» con «limitata» per $b_n$.
3. Omettere $b_n\to0$ in Dirichlet.
4. Confondere il criterio numerico di Abel con il teorema sulle serie di potenze.
5. Perdere $A_qb_q$ nella sommazione per parti.
6. Affermare la limitatezza delle somme trigonometriche quando $\theta\in2\pi\mathbb Z$.
7. Concludere convergenza assoluta da Dirichlet.

### **8. Esercizi**

1. Derivare la sommazione per parti controllando i casi $q=p$ e $q=p+1$.
2. Dimostrare Dirichlet direttamente con il criterio di Cauchy.
3. Applicare Dirichlet a $\sum(-1)^n/\sqrt n$.
4. Studiare $\sum\sin(n\theta)/n^p$ per $p>0$ e $\theta\notin2\pi\mathbb Z$.
5. Classificare la convergenza assoluta delle serie trigonometriche della sezione 4.
6. Dimostrare Abel a partire da Dirichlet.
7. Calcolare le due somme geometriche trigonometriche separando parte reale e immaginaria.
8. Fornire un esempio in cui Abel si applichi ma il confronto assoluto non concluda.
9. Spiegare perché $\sum\cos(n\theta)/n$ diverge per $\theta=0$.
10. Confrontare Leibniz e Dirichlet sull'armonica alternata.

### **9. Riepilogo**

> ✅ La sommazione per parti trasferisce la cancellazione dalle somme parziali di un fattore alle differenze dell'altro. Dirichlet e Abel formalizzano questo meccanismo e trattano serie oscillanti non necessariamente assolute.
