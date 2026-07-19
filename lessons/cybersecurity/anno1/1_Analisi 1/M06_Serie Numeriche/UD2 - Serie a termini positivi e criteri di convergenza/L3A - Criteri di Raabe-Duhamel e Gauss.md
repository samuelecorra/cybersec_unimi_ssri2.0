# **M6 UD2 Lezione 3A - Criteri di Raabe-Duhamel e Gauss**

### **1. Oltre il rapporto ordinario**

Per molte serie positive il rapporto tende a $1$, ma la velocità con cui vi tende distingue convergenza e divergenza. Raabe-Duhamel confronta la correzione di primo ordine con quella delle $p$-serie.

### **2. Criterio di Raabe-Duhamel**

Sia $a_n>0$ definitivamente e supponiamo che esista

$$
R=\lim_{n\to\infty}n\left(\frac{a_n}{a_{n+1}}-1\right).
$$

Allora:

- se $R>1$, $\sum a_n$ converge;
- se $R<1$, $\sum a_n$ diverge;
- se $R=1$, il criterio è inconcludente.

Sono ammessi anche limiti infiniti con l'interpretazione naturale.

### **3. Giustificazione mediante confronto**

Per $b_n=1/n^p$,

$$
\frac{b_n}{b_{n+1}}=\left(1+\frac1n\right)^p
=1+\frac pn+o\left(\frac1n\right).
$$

Se $R>1$, scegliamo $p$ con $1<p<R$. Definitivamente

$$
\frac{a_n}{a_{n+1}}>\frac{b_n}{b_{n+1}}.
$$

Ne segue

$$
\frac{a_{n+1}}{b_{n+1}}<\frac{a_n}{b_n};
$$

il rapporto $a_n/b_n$ è definitivamente limitato e $a_n\leq C/n^p$. La $p$-serie converge, quindi converge $\sum a_n$.

Se $R<1$, scegliamo $p$ con $R<p<1$. Le disuguaglianze si invertono e $a_n\geq C/n^p$ definitivamente; la $p$-serie diverge, dunque diverge $\sum a_n$. $\square$

La relazione asintotica usata per $(1+1/n)^p$ è un limite di successione già coordinato in M05; non deriva da una manipolazione formale del simbolo $o$.

### **4. Esempi**

Per $a_n=1/n^p$,

$$
n\left(\frac{a_n}{a_{n+1}}-1\right)
=n\left[\left(1+\frac1n\right)^p-1\right]\to p.
$$

Raabe ritrova convergenza per $p>1$, divergenza per $p<1$ e resta inconcludente per l'armonica.

Per

$$
a_n=\frac{n!}{(\alpha+1)(\alpha+2)\cdots(\alpha+n)},
$$

assumiamo $\alpha>-1$, così che tutti i fattori al denominatore siano positivi. Allora

$$
\frac{a_n}{a_{n+1}}=\frac{n+1+\alpha}{n+1}
=1+\frac{\alpha}{n+1},
$$

quindi $R=\alpha$. Nel dominio dichiarato la serie converge per $\alpha>1$ e diverge per $-1<\alpha<1$; il caso $\alpha=1$ va studiato separatamente. La restrizione evita valori interi negativi che annullerebbero un fattore e casi in cui la positività richiesta non è stata stabilita.

### **5. Criterio di Gauss**

Come approfondimento, supponiamo

$$
\frac{a_n}{a_{n+1}}
=1+\frac{\alpha}{n}+O\left(\frac1{n^{1+\delta}}\right),
\qquad \delta>0,
$$

con $a_n>0$ definitivamente. Allora

$$
a_n\sim\frac{C}{n^\alpha}
$$

per una costante $C>0$. Di conseguenza la serie converge se $\alpha>1$ e diverge se $\alpha\leq1$.

**Idea rigorosa.** Prendendo logaritmi,

$$
\ln\frac{a_n}{a_{n+1}}
=\frac{\alpha}{n}+O\left(\frac1{n^{1+\eta}}\right),
$$

con $\eta=\min\{1,\delta\}>0$. Sommando, il resto $O(n^{-1-\eta})$ produce una serie convergente, mentre

$$
\sum_{k=N}^{n}\frac1k=\ln n+C_0+o(1).
$$

Si ottiene $\ln a_n=-\alpha\ln n+C_1+o(1)$ e quindi l'equivalenza. L'uso dell'asintotica armonica è classificato come approfondimento; il criterio principale di Raabe non dipende da questa prova.

Gauss aggiunge valore nel caso di frontiera $R=1$: il controllo del resto nel rapporto permette di concludere divergenza quando $\alpha=1$.

### **6. Limiti del criterio**

Raabe e Gauss richiedono positività definitiva e rapporti definiti. Non sono criteri universali e non sostituiscono condensazione o Dirichlet. Se $R=1$ e manca l'espansione controllata di Gauss, serie come

$$
\sum\frac1{n(\ln n)^p}
$$

richiedono condensazione.

### **7. Errori frequenti**

1. Applicare Raabe a termini di segno variabile senza passare ai moduli per l'assoluta.
2. Concludere quando $R=1$.
3. Confondere $a_n/a_{n+1}$ con il rapporto inverso.
4. Omettere il fattore $n$.
5. Usare Gauss senza un resto $O(n^{-1-\delta})$ controllato.
6. Presentare l'idea logaritmica di Gauss come prova elementare di Stirling.

### **8. Esercizi**

1. Applicare Raabe a $\sum1/n^p$.
2. Studiare $\sum n!/[3\cdot4\cdots(n+2)]$.
3. Costruire due serie con $R=1$, una convergente e una divergente.
4. Spiegare perché $\sum1/[n(\ln n)^2]$ non è decisa da Raabe.
5. Verificare l'espansione richiesta da Gauss per $a_n=1/n$.
6. Mostrare come il confronto con una $p$-serie entra nella prova di Raabe.
7. Studiare la convergenza assoluta di $\sum(-1)^n a_n$ quando il modulo soddisfa $R>1$.
8. Confrontare rapporto ordinario, Raabe e condensazione su tre esempi scelti.

### **9. Riepilogo**

> ✅ Raabe misura la correzione di ordine $1/n$ quando il rapporto tende a $1$; Gauss, con un resto più preciso, risolve anche alcuni casi di frontiera. Sono criteri avanzati con ipotesi più forti, non scorciatoie universali.
