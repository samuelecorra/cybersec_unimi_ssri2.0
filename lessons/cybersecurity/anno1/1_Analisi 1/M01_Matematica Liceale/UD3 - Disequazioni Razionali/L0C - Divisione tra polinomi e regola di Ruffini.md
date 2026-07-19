# **M1 UD3 Lezione 0C - Divisione tra polinomi e regola di Ruffini**

### **1. Dalla divisione tra interi alla divisione tra polinomi**

La divisione tra polinomi generalizza la divisione con resto tra numeri interi. Il grado prende il posto della grandezza numerica: si continua a sottrarre multipli del divisore finché il resto ha grado minore del divisore.

Questo algoritmo serve a verificare divisibilità, estrarre fattori, semplificare frazioni razionali e preparare il calcolo dei limiti.

### **2. Teorema della divisione euclidea**

Siano $A(x)$ e $B(x)$ due polinomi a coefficienti reali, con $B(x)\ne0$. Esistono e sono unici due polinomi $Q(x)$ e $R(x)$ tali che:

$$
A(x)=B(x)Q(x)+R(x),
$$

dove:

$$
R(x)=0
$$

oppure:

$$
\deg R<\deg B.
$$

La terminologia è:

- $A$: dividendo;
- $B$: divisore;
- $Q$: quoziente;
- $R$: resto.

Se $R=0$, la divisione è **esatta** e si dice che $B$ divide $A$:

$$
B\mid A.
$$

In tal caso $A=BQ$ e $B$ è un fattore di $A$.

### **3. Perché quoziente e resto esistono**

Se $\deg A<\deg B$, basta scegliere $Q=0$ e $R=A$.

Supponiamo invece che:

$$
\deg A=m\ge n=\deg B.
$$

Siano $a_mx^m$ e $b_nx^n$ i termini direttivi. Il monomio:

$$
\frac{a_m}{b_n}x^{m-n}
$$

moltiplicato per $B$ produce lo stesso termine direttivo di $A$. Sottraendo:

$$
A(x)-\frac{a_m}{b_n}x^{m-n}B(x),
$$

il termine di grado $m$ si cancella e il grado diminuisce. Ripetendo il procedimento, dopo un numero finito di passi si ottiene un resto di grado minore di $n$ oppure nullo. La somma dei monomi usati forma $Q$.

### **4. Perché quoziente e resto sono unici**

Supponiamo che esistano due decomposizioni:

$$
A=BQ_1+R_1
$$

e:

$$
A=BQ_2+R_2,
$$

con $\deg R_1<\deg B$ e $\deg R_2<\deg B$ quando i resti non sono nulli.

Sottraendo le identità:

$$
B(Q_1-Q_2)=R_2-R_1.
$$

Se $Q_1-Q_2$ fosse non nullo, il membro sinistro avrebbe grado almeno $\deg B$, mentre il membro destro avrebbe grado minore di $\deg B$. È impossibile. Quindi $Q_1=Q_2$ e, di conseguenza, $R_1=R_2$.

> 📌 L'unicità rende sensato parlare del quoziente e del resto, non di una possibile scelta fra molte.

### **5. Algoritmo della divisione ordinaria**

Il procedimento operativo è:

1. ordinare dividendo e divisore per potenze decrescenti;
2. inserire i termini mancanti con coefficiente zero;
3. dividere il termine direttivo del resto corrente per quello del divisore;
4. inserire il risultato nel quoziente;
5. moltiplicare il divisore per quel termine;
6. sottrarre;
7. ripetere finché il grado del resto è minore del grado del divisore.

Alla fine si controlla sempre:

$$
A=BQ+R.
$$

### **6. Esempio con termini mancanti e resto non nullo**

Dividiamo:

$$
A(x)=2x^4-3x^2+5x-1
$$

per:

$$
B(x)=x^2-x+1.
$$

Scriviamo il termine mancante:

$$
A(x)=2x^4+0x^3-3x^2+5x-1.
$$

Il primo termine del quoziente è:

$$
\frac{2x^4}{x^2}=2x^2.
$$

Sottraiamo $2x^2B(x)$:

$$
\left(2x^4+0x^3-3x^2+5x-1\right)
-
\left(2x^4-2x^3+2x^2\right),
$$

ottenendo:

$$
2x^3-5x^2+5x-1.
$$

Ora:

$$
\frac{2x^3}{x^2}=2x.
$$

Sottraendo $2xB(x)=2x^3-2x^2+2x$ resta:

$$
-3x^2+3x-1.
$$

Infine:

$$
\frac{-3x^2}{x^2}=-3.
$$

Sottraendo $-3B(x)=-3x^2+3x-3$ otteniamo il resto $2$. Quindi:

$$
Q(x)=2x^2+2x-3,\qquad R(x)=2.
$$

Controllo:

$$
(x^2-x+1)(2x^2+2x-3)+2=2x^4-3x^2+5x-1.
$$

### **7. Esempio di divisione esatta**

Dividiamo:

$$
x^3-6x^2+11x-6
$$

per $x-1$. L'algoritmo produce:

$$
Q(x)=x^2-5x+6,\qquad R(x)=0.
$$

Infatti:

$$
x^3-6x^2+11x-6=(x-1)(x^2-5x+6).
$$

Il divisore è quindi un fattore del dividendo.

### **8. Coefficienti frazionari**

Dividiamo $x^2+1$ per $2x-1$. Il primo termine è:

$$
\frac{x^2}{2x}=\frac{1}{2}x.
$$

Dopo la prima sottrazione resta $\frac{1}{2}x+1$. Il termine successivo è $\frac14$. Si ottiene:

$$
x^2+1=(2x-1)\left(\frac12x+\frac14\right)+\frac54.
$$

La presenza di frazioni non modifica l'algoritmo.

### **9. Divisore di grado maggiore**

Se:

$$
A(x)=x^2+1,
$$

$$
B(x)=x^3-x,
$$

allora $\deg A<\deg B$. La divisione termina immediatamente:

$$
Q(x)=0,\qquad R(x)=x^2+1.
$$

### **10. La regola di Ruffini**

La regola di Ruffini è una forma sintetica della divisione per un divisore monico lineare:

$$
x-a.
$$

Poiché il divisore ha una struttura fissa, è sufficiente lavorare con i coefficienti del dividendo. I numeri ottenuti, tranne l'ultimo, sono i coefficienti del quoziente; l'ultimo è il resto.

> ⚠️ Ruffini nella forma ordinaria non si applica direttamente a divisori di grado superiore a uno, come $x^2+1$, né a un divisore lineare non monico senza un adattamento ulteriore.

### **11. Come costruire la tabella di Ruffini**

Per dividere:

$$
P(x)=a_nx^n+\cdots+a_1x+a_0
$$

per $x-a$:

1. si scrivono tutti i coefficienti, inclusi gli zeri;
2. si scrive $a$ a sinistra;
3. si abbassa il primo coefficiente;
4. lo si moltiplica per $a$ e si somma al coefficiente successivo;
5. si ripete fino all'ultimo coefficiente.

### **12. Ruffini con resto nullo**

Dividiamo $x^3-6x^2+11x-6$ per $x-1$. Usiamo $a=1$:

$$
\begin{array}{r|rrrr}
1 & 1 & -6 & 11 & -6 \\
  &   & 1 & -5 & 6 \\
\hline
  & 1 & -5 & 6 & 0
\end{array}
$$

Il quoziente è:

$$
x^2-5x+6
$$

e il resto è $0$.

Sono esattamente il quoziente e il resto ottenuti con la divisione ordinaria nella sezione 7; il confronto mostra che Ruffini comprime lo stesso algoritmo.

### **13. Ruffini con termine mancante e resto non nullo**

Dividiamo:

$$
P(x)=2x^3-x+4
$$

per $x-2$. Il coefficiente di $x^2$ è zero:

$$
\begin{array}{r|rrrr}
2 & 2 & 0 & -1 & 4 \\
  &   & 4 & 8 & 14 \\
\hline
  & 2 & 4 & 7 & 18
\end{array}
$$

Quindi:

$$
P(x)=(x-2)(2x^2+4x+7)+18.
$$

Il resto coincide con:

$$
P(2)=2\cdot2^3-2+4=18.
$$

### **14. Divisore $x+a$**

Il divisore $x+3$ deve essere scritto come:

$$
x-(-3).
$$

Nella tabella si usa quindi $a=-3$, non $3$. Dividiamo:

$$
x^3+2x^2-5x-6
$$

per $x+3$:

$$
\begin{array}{r|rrrr}
-3 & 1 & 2 & -5 & -6 \\
   &   & -3 & 3 & 6 \\
\hline
   & 1 & -1 & -2 & 0
\end{array}
$$

Otteniamo:

$$
x^3+2x^2-5x-6=(x+3)(x^2-x-2).
$$

### **15. Ruffini con coefficienti frazionari**

Dividiamo:

$$
P(x)=x^3-\frac12x+1
$$

per $x-\frac12$. Inseriamo il coefficiente nullo di $x^2$:

$$
\begin{array}{r|rrrr}
\frac12 & 1 & 0 & -\frac12 & 1 \\
        &   & \frac12 & \frac14 & -\frac18 \\
\hline
        & 1 & \frac12 & -\frac14 & \frac78
\end{array}
$$

Quindi:

$$
Q(x)=x^2+\frac12x-\frac14,\qquad R=\frac78.
$$

### **16. Applicazioni ripetute**

Consideriamo:

$$
P(x)=x^4-5x^2+4.
$$

Verifichiamo che $1$ è una radice e dividiamo per $x-1$:

$$
P(x)=(x-1)(x^3+x^2-4x-4).
$$

Il quoziente si annulla in $x=-1$, quindi dividiamo per $x+1$:

$$
x^3+x^2-4x-4=(x+1)(x^2-4).
$$

Infine:

$$
x^2-4=(x-2)(x+2).
$$

Pertanto:

$$
x^4-5x^2+4=(x-1)(x+1)(x-2)(x+2).
$$

### **17. Perché Ruffini funziona**

Scriviamo il quoziente come:

$$
Q(x)=b_{n-1}x^{n-1}+\cdots+b_1x+b_0
$$

e il resto come $r$. Dall'identità:

$$
P(x)=(x-a)Q(x)+r
$$

confrontiamo i coefficienti. Si ottengono le ricorrenze:

$$
b_{n-1}=a_n,
$$

$$
b_{k-1}=a_k+ab_k,
$$

e infine:

$$
r=a_0+ab_0.
$$

Sono esattamente le operazioni della tabella: abbassare, moltiplicare per $a$, sommare. Ruffini non è quindi un trucco separato, ma la divisione ordinaria compressa nei coefficienti.

### **18. Errori frequenti**

- Dimenticare uno zero per un termine mancante sposta tutti i coefficienti.
- Per $x+a$ si usa $-a$ nella tabella.
- L'ultimo numero è il resto, non il termine noto del quoziente.
- Se il resto è non nullo, $x-a$ non è un fattore.
- Ruffini non sostituisce la divisione per un divisore quadratico o di grado superiore.
- Una divisione va controllata ricostruendo $A=BQ+R$.

### **19. Riepilogo**

> ✅ La divisione euclidea produce unici quoziente e resto. Ruffini è la sua versione sintetica per il divisore $x-a$.

La prossima lezione dimostrerà perché l'ultima casella di Ruffini coincide con $P(a)$ e quando il resto nullo equivale alla presenza del fattore $x-a$.
