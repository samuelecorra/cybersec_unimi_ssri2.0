# **M1 UD5 Lezione 2 - Equazioni e disequazioni logaritmiche**

### **1. Introduzione**

Un’equazione o disequazione logaritmica contiene l’incognita dentro l’argomento di uno o più logaritmi.

Esempi:

$$
\log_2(x-1)=3
$$

$$
\log_3(x+2)\le \log_3(5-x)
$$

Il punto decisivo è che il logaritmo non è definito per ogni valore reale dell’argomento.

> 📌 In ogni esercizio logaritmico il primo passo è sempre scrivere le condizioni di esistenza degli argomenti.

Solo dopo aver fissato il dominio si possono usare le proprietà dei logaritmi o eliminare il logaritmo tramite la funzione esponenziale inversa.

---

### **2. Condizioni di esistenza**

Ogni logaritmo:

$$
\log_a A(x)
$$

richiede:

$$
a>0,\quad a\neq 1,\quad A(x)>0
$$

Di solito la base è data e valida; la vera condizione da imporre è:

$$
A(x)>0
$$

Se compaiono più logaritmi, bisogna imporre la positività di tutti gli argomenti.

Per esempio:

$$
\log_2(x-1)+\log_2(5-x)
$$

richiede:

$$
\begin{cases}
x-1>0 \\
5-x>0
\end{cases}
$$

cioè:

$$
1<x<5
$$

---

### **3. Equazioni del tipo $\log_a A(x)=c$**

Partiamo dalla forma più semplice:

$$
\log_a A(x)=c
$$

Per definizione di logaritmo:

$$
A(x)=a^c
$$

con la condizione:

$$
A(x)>0
$$

In realtà, se $A(x)=a^c$, la positività è automaticamente soddisfatta, perché $a^c>0$. Tuttavia è buona abitudine scrivere comunque il dominio all’inizio.

---

### **4. Esempio base**

Risolviamo:

$$
\log_2(x-3)=4
$$

Condizione di esistenza:

$$
x-3>0
$$

quindi:

$$
x>3
$$

Passiamo alla forma esponenziale:

$$
x-3=2^4
$$

cioè:

$$
x-3=16
$$

quindi:

$$
x=19
$$

La soluzione rispetta il dominio, perché $19>3$.

---

### **5. Equazioni con logaritmi nella stessa base**

Se:

$$
\log_a A(x)=\log_a B(x)
$$

con:

$$
a>0,\quad a\neq 1
$$

allora, poiché il logaritmo è iniettivo:

$$
A(x)=B(x)
$$

ma con le condizioni:

$$
\begin{cases}
A(x)>0 \\
B(x)>0
\end{cases}
$$

> ⚠️ Non basta risolvere $A(x)=B(x)$: bisogna conservare il dominio dei logaritmi originali.

---

### **6. Esempio con due logaritmi**

Risolviamo:

$$
\log_3(x+1)=\log_3(7-2x)
$$

Dominio:

$$
\begin{cases}
x+1>0 \\
7-2x>0
\end{cases}
$$

cioè:

$$
\begin{cases}
x>-1 \\
x<\frac{7}{2}
\end{cases}
$$

quindi:

$$
-1<x<\frac{7}{2}
$$

Uguagliamo gli argomenti:

$$
x+1=7-2x
$$

quindi:

$$
3x=6
$$

e:

$$
x=2
$$

Poiché:

$$
-1<2<\frac{7}{2}
$$

la soluzione è:

$$
x=2
$$

---

### **7. Uso delle proprietà per ridurre l’equazione**

Risolviamo:

$$
\log_2 x+\log_2(x-2)=3
$$

Dominio:

$$
\begin{cases}
x>0 \\
x-2>0
\end{cases}
$$

quindi:

$$
x>2
$$

Usiamo la proprietà del prodotto:

$$
\log_2[x(x-2)]=3
$$

Passiamo alla forma esponenziale:

$$
x(x-2)=2^3
$$

cioè:

$$
x^2-2x=8
$$

Portiamo tutto a sinistra:

$$
x^2-2x-8=0
$$

Fattorizziamo:

$$
(x-4)(x+2)=0
$$

Otteniamo:

$$
x=4\quad \text{oppure}\quad x=-2
$$

Il dominio richiede $x>2$, quindi scartiamo $x=-2$.

La soluzione è:

$$
x=4
$$

---

### **8. Disequazioni logaritmiche con base maggiore di 1**

Se:

$$
a>1
$$

allora $\log_a x$ è crescente. Quindi:

$$
\log_a A(x)<\log_a B(x)
$$

è equivalente a:

$$
A(x)<B(x)
$$

con:

$$
\begin{cases}
A(x)>0 \\
B(x)>0
\end{cases}
$$

Il verso resta uguale.

---

### **9. Esempio con base maggiore di 1**

Risolviamo:

$$
\log_2(x-1)\le \log_2(5-x)
$$

Dominio:

$$
\begin{cases}
x-1>0 \\
5-x>0
\end{cases}
$$

quindi:

$$
1<x<5
$$

Poiché la base $2$ è maggiore di $1$, conserviamo il verso:

$$
x-1\le 5-x
$$

quindi:

$$
2x\le 6
$$

e:

$$
x\le 3
$$

Intersechiamo con il dominio:

$$
1<x\le 3
$$

La soluzione è:

$$
(1,3]
$$

---

### **10. Disequazioni logaritmiche con base tra 0 e 1**

Se:

$$
0<a<1
$$

allora $\log_a x$ è decrescente. Quindi:

$$
\log_a A(x)<\log_a B(x)
$$

è equivalente a:

$$
A(x)>B(x)
$$

con:

$$
\begin{cases}
A(x)>0 \\
B(x)>0
\end{cases}
$$

Il verso si inverte.

> ⚠️ La base compresa tra $0$ e $1$ è una delle principali fonti di errori nelle disequazioni logaritmiche.

---

### **11. Esempio con base frazionaria**

Risolviamo:

$$
\log_{\frac{1}{3}}(x+4)> \log_{\frac{1}{3}}(2x-1)
$$

Dominio:

$$
\begin{cases}
x+4>0 \\
2x-1>0
\end{cases}
$$

cioè:

$$
\begin{cases}
x>-4 \\
x>\frac{1}{2}
\end{cases}
$$

quindi:

$$
x>\frac{1}{2}
$$

La base:

$$
\frac{1}{3}
$$

è compresa tra $0$ e $1$, quindi il verso si inverte:

$$
x+4<2x-1
$$

Da cui:

$$
x>5
$$

Intersechiamo con il dominio:

$$
x>5
$$

La soluzione è:

$$
(5,+\infty)
$$

---

### **12. Disequazioni del tipo $\log_a A(x)\le c$**

Si può riscrivere la costante come logaritmo:

$$
c=\log_a(a^c)
$$

Quindi:

$$
\log_a A(x)\le c
$$

diventa:

$$
\log_a A(x)\le \log_a(a^c)
$$

Se $a>1$:

$$
A(x)\le a^c
$$

Se $0<a<1$:

$$
A(x)\ge a^c
$$

In entrambi i casi bisogna imporre:

$$
A(x)>0
$$

---

### **13. Esempio con costante**

Risolviamo:

$$
\log_5(2x-3)<2
$$

Dominio:

$$
2x-3>0
$$

quindi:

$$
x>\frac{3}{2}
$$

Scriviamo:

$$
2=\log_5 25
$$

Poiché $5>1$, conserviamo il verso:

$$
2x-3<25
$$

quindi:

$$
2x<28
$$

e:

$$
x<14
$$

Intersechiamo:

$$
\frac{3}{2}<x<14
$$

La soluzione è:

$$
\left(\frac{3}{2},14\right)
$$

---

### **14. Equazioni con cambio di variabile**

Alcune equazioni diventano più semplici ponendo:

$$
t=\log_a x
$$

con:

$$
x>0
$$

Esempio:

$$
(\log_2 x)^2-3\log_2 x+2=0
$$

Poniamo:

$$
t=\log_2 x
$$

Otteniamo:

$$
t^2-3t+2=0
$$

Fattorizziamo:

$$
(t-1)(t-2)=0
$$

Quindi:

$$
t=1\quad \text{oppure}\quad t=2
$$

Torniamo a $x$:

$$
\log_2 x=1 \Rightarrow x=2
$$

$$
\log_2 x=2 \Rightarrow x=4
$$

La soluzione è:

$$
x\in\{2,4\}
$$

#### **14.1 Equazione mista esponenziale-logaritmica**

Risolviamo:

$$
\log_2(2^x-1)=x-1
$$

Il dominio richiede $2^x-1>0$, quindi $x>0$. Applicando l'esponenziale in base $2$ a entrambi i membri:

$$
2^x-1=2^{x-1}
$$

Poiché $2^x=2\cdot2^{x-1}$:

$$
2^{x-1}=1=2^0
$$

e dunque $x=1$, che appartiene al dominio e verifica l'equazione originaria.

#### **14.2 Un parametro nell'argomento**

Per ogni $m\in\mathbb{R}$, la disequazione:

$$
\log_2(x-m)>1
$$

ha dominio $x>m$. Poiché la base è maggiore di $1$:

$$
x-m>2
$$

quindi:

$$
S_m=(m+2,+\infty)
$$

La condizione finale implica già il dominio, ma averlo scritto prima giustifica l'applicazione della monotonia.

---

### **15. Errori tipici**

#### **15.1 Applicare proprietà senza dominio**

Da:

$$
\log(x^2-1)=\log(x-1)+\log(x+1)
$$

la trasformazione è lecita solo se:

$$
x-1>0,\quad x+1>0
$$

cioè:

$$
x>1
$$

Non basta:

$$
x^2-1>0
$$

#### **15.2 Dimenticare l’inversione del verso**

Se la base è:

$$
0<a<1
$$

la funzione logaritmica è decrescente, quindi il verso si inverte.

#### **15.3 Accettare soluzioni fuori dominio**

Le soluzioni ottenute dopo manipolazioni algebriche vanno sempre verificate nel dominio originale.

---

### **16. Riepilogo**

> ✅ Le equazioni e disequazioni logaritmiche si risolvono combinando dominio, proprietà dei logaritmi e monotonia della base.

Schema operativo:

- imporre la positività di ogni argomento;
- usare le proprietà dei logaritmi solo dove sono lecite;
- ridurre a uguaglianze o disuguaglianze tra argomenti;
- conservare il verso se $a>1$;
- invertire il verso se $0<a<1$;
- intersecare sempre con il dominio iniziale;
- verificare eventuali soluzioni isolate.
