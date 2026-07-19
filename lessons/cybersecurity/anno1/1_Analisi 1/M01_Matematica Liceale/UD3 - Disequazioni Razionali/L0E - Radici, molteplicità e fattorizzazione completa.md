# **M1 UD3 Lezione 0E - Radici, molteplicità e fattorizzazione completa**

### **1. Dalle radici alla struttura del polinomio**

Una fattorizzazione completa non elenca soltanto gli zeri: mostra quante volte compaiono, come cambia il segno e quali fattori restano irriducibili nell'insieme numerico scelto. Queste informazioni verranno usate direttamente nelle disequazioni polinomiali e razionali.

### **2. Molteplicità di uno zero**

Un numero $a$ è uno zero di **molteplicità $m$** del polinomio $P$ se:

$$
P(x)=(x-a)^mQ(x),
$$

dove:

$$
Q(a)\ne0.
$$

Equivalentemente, $(x-a)^m$ divide $P$, ma $(x-a)^{m+1}$ non lo divide.

- $m=1$: radice semplice;
- $m=2$: radice doppia;
- $m=3$: radice tripla.

Per esempio:

$$
P(x)=5(x-2)^3(x+1)^2
$$

ha la radice $2$ di molteplicità $3$ e la radice $-1$ di molteplicità $2$.

### **3. Molteplicità e segno**

Vicino a $x=a$, il fattore $Q(x)$ mantiene il segno di $Q(a)$ perché non si annulla in $a$. Il comportamento del segno è quindi determinato da $(x-a)^m$.

- Se $m$ è dispari, $(x-a)^m$ cambia segno attraversando $a$.
- Se $m$ è pari, $(x-a)^m$ è positivo da entrambi i lati e il segno non cambia.

Perciò il grafico attraversa l'asse $x$ in corrispondenza di una radice di molteplicità dispari e lo tocca senza attraversarlo in corrispondenza di una radice di molteplicità pari.

<!-- TODO FIGURA:
Grafico locale di un polinomio presso una radice semplice a: il grafico attraversa l'asse x
e il segno passa da negativo a positivo. Evidenziare il fattore dominante x-a.
Obiettivo didattico: collegare molteplicità dispari e cambio di segno.
-->

<!-- TODO FIGURA:
Grafico locale di un polinomio presso una radice doppia a: il grafico è tangente all'asse x
e resta dallo stesso lato. Evidenziare il comportamento di (x-a)^2.
Obiettivo didattico: collegare molteplicità pari e assenza di cambio di segno.
-->

<!-- TODO FIGURA:
Confronto in quattro pannelli tra (x-a), (x-a)^2, (x-a)^3 e (x-a)^4.
Indicare attraversamento o contatto e segno sui due lati di a.
Obiettivo didattico: distinguere molteplicità pari e dispari oltre i casi 1 e 2.
-->

La stabilità del segno di $Q$ è una proprietà delle funzioni polinomiali che verrà formalizzata nel modulo sulla continuità. Qui la usiamo nella sua forma operativa.

### **4. Anticipazione del criterio con le derivate**

Quando saranno disponibili le derivate, la molteplicità $m$ potrà essere riconosciuta mediante:

$$
P(a)=P'(a)=\cdots=P^{(m-1)}(a)=0,
$$

$$
P^{(m)}(a)\ne0.
$$

Questo criterio è soltanto anticipato: la sua giustificazione naturale usa la formula di Taylor e sarà ripresa nel calcolo differenziale.

### **5. Procedura completa di fattorizzazione**

Per fattorizzare un polinomio conviene seguire un ordine stabile:

1. ordinare le potenze e ridurre i termini simili;
2. inserire mentalmente i coefficienti nulli;
3. raccogliere eventuali fattori comuni;
4. riconoscere prodotti notevoli;
5. tentare raggruppamenti;
6. usare sostituzioni come $t=x^2$ o $t=x^3$;
7. elencare le radici razionali candidate;
8. verificare i candidati con $P(a)$;
9. applicare Ruffini a ogni radice trovata;
10. ripetere sul quoziente;
11. fattorizzare i fattori quadratici mediante il discriminante;
12. dichiarare l'insieme numerico e i fattori irriducibili rimasti;
13. controllare moltiplicando i fattori.

### **6. Esempio di secondo grado**

Fattorizziamo:

$$
6x^2-x-2.
$$

Il discriminante è:

$$
\Delta=(-1)^2-4\cdot6\cdot(-2)=49.
$$

Le radici sono $\frac23$ e $-\frac12$. Conservando il coefficiente direttivo:

$$
6x^2-x-2=6\left(x-\frac23\right)\left(x+\frac12\right).
$$

Equivalentemente:

$$
6x^2-x-2=(3x-2)(2x+1).
$$

### **7. Esempio di terzo grado**

Consideriamo:

$$
P(x)=x^3-4x^2+x+6.
$$

I candidati interi sono $\pm1,\pm2,\pm3,\pm6$. Poiché $P(2)=0$, dividiamo per $x-2$:

$$
P(x)=(x-2)(x^2-2x-3).
$$

Il trinomio si fattorizza:

$$
x^2-2x-3=(x-3)(x+1).
$$

Quindi:

$$
P(x)=(x-2)(x-3)(x+1).
$$

### **8. Esempio di quarto grado**

Per:

$$
x^4-5x^2+4
$$

poniamo $t=x^2$:

$$
t^2-5t+4=(t-1)(t-4).
$$

Tornando a $x$:

$$
x^4-5x^2+4=(x^2-1)(x^2-4),
$$

e quindi:

$$
x^4-5x^2+4=(x-1)(x+1)(x-2)(x+2).
$$

### **9. Grado superiore mediante sostituzione**

Fattorizziamo su $\mathbb{R}$:

$$
x^6-9x^3+8.
$$

Poniamo $t=x^3$:

$$
t^2-9t+8=(t-1)(t-8).
$$

Quindi:

$$
x^6-9x^3+8=(x^3-1)(x^3-8).
$$

Usando la differenza di cubi:

$$
x^6-9x^3+8
=(x-1)(x^2+x+1)(x-2)(x^2+2x+4).
$$

I due fattori quadratici hanno discriminante negativo e sono irriducibili su $\mathbb{R}$.

### **10. Esempio con radici multiple**

Il polinomio:

$$
P(x)=(x-1)^3(x+2)^2
$$

ha grado $5$. Lo zero $1$ ha molteplicità dispari $3$, quindi il segno cambia; lo zero $-2$ ha molteplicità pari $2$, quindi il segno non cambia.

Non è necessario sviluppare il prodotto per studiarne il segno: la forma fattorizzata contiene già tutte le informazioni utili.

### **11. Fattori irriducibili su $\mathbb{R}$**

Consideriamo:

$$
x^4+5x^2+4.
$$

Con $t=x^2$:

$$
t^2+5t+4=(t+1)(t+4).
$$

Quindi:

$$
x^4+5x^2+4=(x^2+1)(x^2+4).
$$

Su $\mathbb{R}$ entrambi i fattori quadratici sono irriducibili, perché hanno discriminante negativo.

### **12. L'insieme numerico conta**

La stessa espressione può essere irriducibile in un insieme e fattorizzabile in un altro.

Su $\mathbb{Q}$:

$$
x^2-2
$$

è irriducibile, perché le sue radici $\pm\sqrt2$ non sono razionali. Su $\mathbb{R}$:

$$
x^2-2=(x-\sqrt2)(x+\sqrt2).
$$

Su $\mathbb{R}$:

$$
x^2+1
$$

è irriducibile. Su $\mathbb{C}$:

$$
x^2+1=(x-i)(x+i).
$$

Il teorema fondamentale dell'algebra assicura che su $\mathbb{C}$ ogni polinomio non costante si fattorizza completamente in fattori lineari, contando le molteplicità. Per polinomi reali, le radici non reali compaiono in coppie coniugate; per questo su $\mathbb{R}$ restano fattori lineari e quadratici irriducibili. La costruzione e il calcolo delle radici complesse appartengono al modulo M04.

### **13. Studio del segno da una fattorizzazione**

Studiamo:

$$
P(x)=-(x+2)(x-1)^2(x-3)^3(x^2+1).
$$

Gli zeri reali ordinati sono:

$$
-2<1<3.
$$

Le molteplicità sono rispettivamente $1$, $2$ e $3$. Il fattore $x^2+1$ è sempre positivo e non introduce zeri reali.

Per $x>3$, tutti i fattori espliciti sono positivi ma il coefficiente iniziale è negativo, quindi $P(x)<0$. Attraversando $3$ il segno cambia; attraversando $1$ non cambia; attraversando $-2$ cambia di nuovo. Otteniamo:

$$
\begin{array}{c|ccccccc}
x & (-\infty,-2) & -2 & (-2,1) & 1 & (1,3) & 3 & (3,+\infty) \\
\hline
P(x) & - & 0 & + & 0 & + & 0 & -
\end{array}
$$

Quindi:

$$
P(x)\ge0
$$

per:

$$
x\in[-2,3].
$$

Il punto $1$ è incluso e non spezza l'intervallo soluzione, perché è uno zero di molteplicità pari.

### **14. Esercizio misto completo**

Consideriamo:

$$
P(x)=x^5-3x^4+x^3+x^2+4.
$$

Vogliamo cercare una radice, applicare Ruffini, fattorizzare, determinare le molteplicità e studiare il segno.

I candidati interi sono $\pm1,\pm2,\pm4$. Calcoliamo:

$$
P(2)=32-48+8+4+4=0.
$$

Quindi $x-2$ è un fattore. Ruffini fornisce:

$$
P(x)=(x-2)(x^4-x^3-x^2-x-2).
$$

Il quoziente si annulla ancora in $2$:

$$
x^4-x^3-x^2-x-2=(x-2)(x^3+x^2+x+1).
$$

Raccogliendo parzialmente:

$$
x^3+x^2+x+1=x^2(x+1)+(x+1)=(x+1)(x^2+1).
$$

Pertanto:

$$
P(x)=(x-2)^2(x+1)(x^2+1).
$$

Lo zero $2$ ha molteplicità $2$, lo zero $-1$ è semplice e $x^2+1$ è sempre positivo su $\mathbb{R}$. Il segno cambia soltanto in $-1$:

$$
P(x)<0\quad\text{per }x<-1,
$$

$$
P(x)>0\quad\text{per }x>-1,\ x\ne2.
$$

Negli zeri il valore è nullo. Quindi:

$$
P(x)\ge0\iff x\in[-1,+\infty).
$$

### **15. Funzioni razionali e fattori comuni**

Per una funzione:

$$
f(x)=\frac{P(x)}{Q(x)},
$$

la fattorizzazione consente di trovare:

- il dominio, escludendo gli zeri di $Q$;
- gli zeri della funzione, annullando $P$ nei punti del dominio;
- il segno, combinando i segni dei fattori;
- eventuali fattori comuni semplificabili.

Consideriamo:

$$
f(x)=\frac{x^2-1}{x-1}.
$$

Il dominio originale è:

$$
\mathbb{R}\setminus\{1\}.
$$

Poiché:

$$
x^2-1=(x-1)(x+1),
$$

per $x\ne1$ vale:

$$
f(x)=x+1.
$$

La funzione originaria non coincide globalmente con $x+1$, perché in $x=1$ non è definita. Nei moduli sui limiti e sulla continuità questo punto sarà interpretato come una discontinuità eliminabile.

<!-- TODO FIGURA:
Grafico della retta y=x+1 con un punto vuoto in (1,2), corrispondente alla funzione
(x^2-1)/(x-1) con dominio x diverso da 1.
Obiettivo didattico: distinguere la formula semplificata dalla funzione originaria.
-->

> ⚠️ Cancellare un fattore non reinserisce nel dominio lo zero che annullava il denominatore originale.

### **16. Approfondimento: massimo comune divisore**

Un polinomio $D$ è un divisore comune di $A$ e $B$ se divide entrambi. Il **massimo comune divisore**, indicato con $\gcd(A,B)$, è il divisore comune di grado massimo, scelto monico per eliminare l'ambiguità dovuta alle costanti non nulle.

Due polinomi sono **coprimi** se il loro massimo comune divisore è $1$.

L'algoritmo euclideo usa divisioni successive:

$$
A=BQ_1+R_1,
$$

$$
B=R_1Q_2+R_2,
$$

e così via. L'ultimo resto non nullo, reso monico, è il massimo comune divisore.

Esempio:

$$
A(x)=x^3-1,\qquad B(x)=x^2-1.
$$

Dividendo:

$$
x^3-1=x(x^2-1)+(x-1).
$$

Poi:

$$
x^2-1=(x+1)(x-1).
$$

Quindi:

$$
\gcd(x^3-1,x^2-1)=x-1.
$$

Questo approfondimento spiega in modo sistematico come individuare fattori comuni nelle frazioni razionali.

### **17. Errori frequenti**

- Dimenticare i coefficienti zero in Ruffini o nella divisione.
- Usare Ruffini con un divisore non lineare.
- Usare $a$ invece di $-a$ per il divisore $x+a$.
- Credere che ogni divisore del termine noto sia una radice.
- Non verificare i candidati razionali.
- Concludere che l'assenza di radici razionali implichi l'assenza di radici reali.
- Perdere il coefficiente direttivo durante la fattorizzazione.
- Ignorare la molteplicità e alternare sempre il segno.
- Dichiarare su $\mathbb{R}$ una fattorizzazione che usa $i$.
- Cancellare un fattore comune e reinserire il relativo punto nel dominio.
- Confondere un'identità di fattorizzazione con un'equazione da risolvere.
- Omettere il controllo finale mediante moltiplicazione.

### **18. Riepilogo**

> ✅ La fattorizzazione completa rende espliciti zeri, molteplicità, segno e fattori irriducibili.

Il procedimento combina raccoglimenti, prodotti notevoli, sostituzioni, ricerca di radici, Ruffini e discriminante. L'insieme numerico deve essere dichiarato e il dominio originario di una funzione razionale deve essere conservato dopo ogni semplificazione.
