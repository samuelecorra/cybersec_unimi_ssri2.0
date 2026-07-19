# **M8 UD5 Lezione 4 - Teorema degli zeri, valori intermedi e metodo di bisezione**

### **1. Obiettivo della lezione**

Primo blocco di **teoremi globali**: risultati che valgono per funzioni continue su un **intervallo** e che parlano dell'intero grafico, non di un punto. Il teorema degli zeri garantisce soluzioni alle equazioni; il teorema dei valori intermedi descrive l'immagine; il metodo di bisezione — già usato operativamente in M07 (UD3/L2) — riceve qui la sua giustificazione analitica e la stima dell'errore; chiudiamo con il punto fisso elementare e con le applicazioni numeriche.

---
### **2. Teorema degli zeri**

**Teorema (degli zeri, o di Bolzano).** Sia $f:[a,b]\to\mathbb{R}$ **continua** su $[a,b]$ con

$$
f(a)\,f(b)<0
$$

(valori di segno opposto agli estremi). Allora esiste almeno un $c\in(a,b)$ tale che $f(c)=0$.

*Dimostrazione (per bisezione e completezza).* Supponiamo $f(a)<0<f(b)$ (l'altro caso è simmetrico). Costruiamo per ricorrenza una successione di intervalli $[a_n,b_n]$:

- $[a_0,b_0]=[a,b]$;
- dato $[a_n,b_n]$ con $f(a_n)\le 0$ e $f(b_n)\ge 0$, sia $m_n=\dfrac{a_n+b_n}{2}$ il punto medio. Se $f(m_n)=0$ abbiamo finito ($c=m_n$). Se $f(m_n)>0$ poniamo $[a_{n+1},b_{n+1}]=[a_n,m_n]$; se $f(m_n)<0$ poniamo $[a_{n+1},b_{n+1}]=[m_n,b_n]$.

Se il procedimento non termina, produce intervalli **inscatolati** ($[a_{n+1},b_{n+1}]\subseteq[a_n,b_n]$) con ampiezza

$$
b_n-a_n = \frac{b-a}{2^n} \longrightarrow 0
$$

e con l'**invariante** $f(a_n)\le 0$, $f(b_n)\ge 0$ per ogni $n$. La successione $(a_n)$ è crescente e maggiorata (da $b$), quindi converge a un $c$ per il teorema delle monotone (M05); da $b_n=a_n+(b_n-a_n)$ segue $b_n\to c+0=c$, e $c\in[a,b]$.

Ora entra la **continuità**, tramite il criterio sequenziale (Lezione 1): $f(a_n)\to f(c)$ e $f(b_n)\to f(c)$. Passando al limite nelle disuguaglianze dell'invariante (permanenza delle disuguaglianze larghe, M05):

$$
f(c)=\lim f(a_n)\le 0
\qquad\text{e}\qquad
f(c)=\lim f(b_n)\ge 0
$$

quindi $f(c)=0$. Infine $c\in(a,b)$ perché $f(a)\neq 0\neq f(b)$. $\square$

**Le ipotesi sono tutte necessarie:**

- niente continuità: $f(x)=\begin{cases}-1, & x\le 0\\ 1, & x>0\end{cases}$ su $[-1,1]$ cambia segno ma non si annulla mai (salta lo zero);
- niente intervallo: $f(x)=\frac{1}{x}$ su $[-1,1]\setminus\{0\}$ è continua sul suo dominio, $f(-1)f(1)<0$, ma non ha zeri: il dominio bucato lascia scappare la soluzione;
- niente cambio di segno: $f(x)=x^2+1$ su $[-1,1]$ non si annulla (ovvio, ma segnala che il teorema dà una condizione **sufficiente**, non necessaria: $x^2$ su $[-1,1]$ si annulla senza cambiare segno).

<!-- TODO FIGURA:
Grafico di una funzione continua su [a,b] con f(a)<0 e f(b)>0 che attraversa l'asse x; sotto, la sequenza dei primi tre intervalli di bisezione [a_0,b_0], [a_1,b_1], [a_2,b_2] disegnati come segmenti orizzontali sempre più corti che si stringono attorno allo zero c, con i punti medi etichettati e il segno di f in ciascun estremo.
Obiettivo didattico: visualizzare la dimostrazione per bisezione: invariante dei segni agli estremi e ampiezze dimezzate che convergono allo zero.
-->

---
### **3. Teorema dei valori intermedi**

**Teorema.** Sia $f$ continua su un intervallo $I$ e siano $x_1,x_2\in I$ con $f(x_1)<y<f(x_2)$. Allora esiste $c$ **strettamente compreso** tra $x_1$ e $x_2$ tale che

$$
f(c)=y
$$

*Dimostrazione.* Sia $g(x)=f(x)-y$, continua (algebra, Lezione 2). Sull'intervallo di estremi $x_1,x_2$: $g(x_1)=f(x_1)-y<0$ e $g(x_2)>0$, quindi $g(x_1)g(x_2)<0$ e il teorema degli zeri fornisce $c$ tra $x_1$ e $x_2$ con $g(c)=0$, cioè $f(c)=y$. $\square$

**Corollario (l'immagine continua di un intervallo è un intervallo).** Se $f$ è continua sull'intervallo $I$, allora $f(I)$ è un intervallo: infatti presi due valori $y_1<y_2$ in $f(I)$, ogni $y\in(y_1,y_2)$ è assunto per il teorema, quindi $f(I)$ contiene ogni punto compreso tra due suoi punti — che è la definizione di intervallo (M02).

Applicazioni immediate:

- ogni **polinomio di grado dispari** ha almeno una radice reale: per $x\to\pm\infty$ diverge con segni opposti (UD2/L2), quindi assume valori di segno opposto e si applica il teorema degli zeri su un intervallo abbastanza grande;
- l'equazione $x=\cos x$ ha soluzione in $(0,\frac{\pi}{2})$: $g(x)=x-\cos x$ è continua, $g(0)=-1<0$, $g(\frac{\pi}{2})=\frac{\pi}{2}>0$;
- una funzione continua su un intervallo che non si annulla mai ha **segno costante** (altrimenti il teorema degli zeri produrrebbe uno zero): è la giustificazione del "metodo dei segni" usato per lo studio del segno in M07/UD3.

> ⚠️ Il viceversa del teorema dei valori intermedi è **falso**: esistono funzioni non continue che soddisfano la proprietà dei valori intermedi. Esempio: $f(x)=\sin\frac 1x$ per $x\neq 0$, $f(0)=0$ assume su ogni intervallo contenente $0$ tutti i valori di $[-1,1]$, pur essendo discontinua in $0$. La proprietà dei valori intermedi **non** caratterizza la continuità.

---
### **4. Il metodo di bisezione come algoritmo**

La dimostrazione del punto 2 è un **algoritmo** effettivo, lo stesso presentato operativamente in M07/UD3/L2, che qui completiamo con la teoria dell'errore.

**Ipotesi**: $f$ continua su $[a,b]$, $f(a)f(b)<0$. **Iterazione**: a ogni passo si valuta $f$ nel punto medio e si conserva la metà che mantiene il cambio di segno. **Garanzie**:

1. **convergenza**: gli estremi convergono a uno zero $c$ (è la dimostrazione del punto 2);
2. **stima dell'errore a priori**: dopo $n$ dimezzamenti, $c\in[a_n,b_n]$ con

$$
b_n-a_n=\frac{b-a}{2^n}
$$

e se si sceglie come approssimazione il punto medio $m_n$, l'errore è al più metà dell'ampiezza corrente:

$$
|m_n-c| \le \frac{b-a}{2^{\,n+1}}
$$

3. **criterio di arresto**: per garantire errore $\le\tau$ bastano $n$ iterazioni con $\frac{b-a}{2^{n+1}}\le\tau$, cioè

$$
n \ \ge\ \log_2\frac{b-a}{\tau}-1
$$

Ogni iterazione **guadagna una cifra binaria** (circa $0{,}3$ cifre decimali): convergenza lenta ma inarrestabile e senza ipotesi extra oltre continuità e cambio di segno.

#### **4.1 Lettura numerico-informatica**

- **Robustezza contro velocità**: la bisezione non richiede derivate né stime di pendenza (metodi più rapidi, come Newton, arriveranno dopo M9-M10) ed è immune a oscillazioni della funzione: per questo è il "fallback" dei risolutori numerici;
- **convergenza numerica $\neq$ convergenza matematica**: in aritmetica floating point la valutazione di $f(m_n)$ è affetta da **errore di arrotondamento**; sotto la soglia in cui $|f(m_n)|$ è comparabile col rumore di macchina, il segno calcolato può essere sbagliato e stringere l'intervallo non aggiunge informazione. I criteri di arresto pratici combinano ampiezza dell'intervallo, valore di $|f|$ e un tetto di iterazioni;
- il test $f(a)f(b)<0$ si implementa confrontando i **segni** (ad esempio con la funzione `sign`), non moltiplicando i valori: il prodotto di due numeri molto piccoli può andare in **underflow** a zero, e quello di due grandi in **overflow**, falsando il test;
- la **continuità è l'ipotesi che il calcolatore non può verificare**: applicare la bisezione a una funzione con un salto (o a una razionale attraverso un asintoto verticale: $\frac 1x$ su $[-1,2]$) produce una "radice" spuria vicino alla discontinuità. Il teorema degli zeri è il certificato che separa il calcolo dal miraggio; la continuità è anche il modello della **sensibilità locale**: input vicini producono output vicini, che è ciò che rende affidabile valutare $f$ in un punto medio "sporcato" dall'arrotondamento.

---
### **5. Punto fisso elementare**

**Teorema (del punto fisso su un intervallo).** Sia $f:[a,b]\to[a,b]$ continua (l'immagine resta dentro $[a,b]$). Allora esiste $c\in[a,b]$ con

$$
f(c)=c
$$

*Dimostrazione.* Sia $g(x)=f(x)-x$, continua. Poiché $f(a)\in[a,b]$, $g(a)=f(a)-a\ge 0$; analogamente $g(b)=f(b)-b\le 0$. Se una delle due è zero, l'estremo corrispondente è punto fisso. Altrimenti $g(a)>0>g(b)$ e il teorema degli zeri dà $c\in(a,b)$ con $g(c)=0$. $\square$

Interpretazione grafica: il grafico di $f$, confinato nel quadrato $[a,b]\times[a,b]$, deve attraversare la diagonale $y=x$. Il teorema **generale** delle contrazioni (esistenza, unicità e convergenza delle iterate) appartiene a corsi successivi: qui ci basta l'esistenza.

<!-- TODO FIGURA:
Quadrato [a,b]x[a,b] con la diagonale y=x tratteggiata e il grafico di una funzione continua che parte sopra la diagonale in x=a e finisce sotto in x=b, con il punto di attraversamento evidenziato come punto fisso c.
Obiettivo didattico: mostrare che una funzione continua che manda un intervallo in sé deve intersecare la diagonale: il punto fisso come zero di f(x)-x.
-->

---
### **6. Errori frequenti**

- ❌ Applicare il teorema degli zeri senza continuità **su tutto** l'intervallo (un solo punto di salto lo invalida), o su un dominio che non è un intervallo (buchi).
- ❌ Dedurre l'**unicità** dello zero: il teorema dà l'esistenza; l'unicità richiede ipotesi in più (ad es. stretta monotonia).
- ❌ Usare il cambio di segno come condizione **necessaria**: $x^2$ si annulla senza cambiare segno.
- ❌ Credere che la proprietà dei valori intermedi implichi la continuità (controesempio $\sin\frac 1x$ completata in $0$).
- ❌ Nella bisezione, dimenticare che la stima $\frac{b-a}{2^{n+1}}$ riguarda la **distanza da uno zero**, non il valore $|f(m_n)|$: i due criteri di arresto misurano cose diverse.
- ❌ Applicare la bisezione attraverso un asintoto verticale e fidarsi del risultato.

---
### **7. Esercizi**

**Esercizio 1.** Dimostra che $x^5+x-1=0$ ha almeno una soluzione reale e localizzala in un intervallo di ampiezza $\frac 14$.

*Soluzione.* $f(x)=x^5+x-1$ continua; $f(0)=-1<0$, $f(1)=1>0$: zero in $(0,1)$. Bisezione: $f(\frac 12)=\frac{1}{32}-\frac 12<0$ → $(\frac 12,1)$; $f(\frac 34)=\frac{243}{1024}-\frac 14>0$ → zero in $(\frac 12,\frac 34)$, ampiezza $\frac 14$. (Qui lo zero è anche unico: $f$ è strettamente crescente perché somma di crescenti — argomento di monotonia, senza derivate.)

**Esercizio 2.** Quante iterazioni di bisezione servono per approssimare uno zero in $[0,1]$ con errore $\le 10^{-6}$ usando il punto medio?

*Soluzione.* $\frac{1}{2^{n+1}}\le 10^{-6}\iff 2^{n+1}\ge 10^{6}\iff n+1\ge\log_2 10^6\approx 19{,}93$: bastano $n=19$ iterazioni ($2^{20}=1\,048\,576\ge 10^6$).

**Esercizio 3.** Sia $f$ continua su $[0,2]$ con $f(0)=f(2)$. Dimostra che esiste $c\in[0,1]$ con $f(c)=f(c+1)$.

*Soluzione.* $g(x)=f(x)-f(x+1)$ è continua su $[0,1]$ e $g(0)+g(1) = f(0)-f(1)+f(1)-f(2)=0$: $g(0)$ e $g(1)$ sono opposti. Se uno è nullo, fine; altrimenti hanno segni opposti e il teorema degli zeri conclude. (È il "lemma della corda", caso discreto del teorema di Rolle che arriverà in M10.)

**Esercizio 4.** Dimostra che ogni $f:[0,1]\to[0,1]$ continua e **decrescente** ha esattamente un punto fisso.

*Soluzione.* Esistenza dal punto 5. Unicità: se $c_1<c_2$ fossero due punti fissi, la decrescenza darebbe $c_1=f(c_1)\ge f(c_2)=c_2$, assurdo.

---
### **8. Riepilogo**

> ✅ Teorema degli zeri: continua su $[a,b]$ + cambio di segno $\Rightarrow$ zero interno; dimostrato per bisezione con intervalli dimezzati, monotone di M05 e criterio sequenziale; ipotesi tutte necessarie (salto, buco, segno). Valori intermedi: si riduce agli zeri con $g=f-y$; immagine di intervallo $=$ intervallo; la proprietà non caratterizza la continuità. Bisezione: errore $\le\frac{b-a}{2^{n+1}}$ col punto medio, un bit per iterazione, criteri di arresto espliciti; in floating point contano arrotondamento, overflow/underflow del test dei segni e il divieto di attraversare discontinuità. Punto fisso: $f:[a,b]\to[a,b]$ continua interseca la diagonale (via $g(x)=f(x)-x$).
