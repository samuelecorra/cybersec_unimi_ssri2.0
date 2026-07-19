# **M1 UD3 Lezione 0B - Prodotti notevoli e fattorizzazione di base**

### **1. Sviluppare e fattorizzare**

Sviluppare significa trasformare un prodotto in una somma mediante la proprietà distributiva. **Fattorizzare** significa compiere il percorso inverso: scrivere un polinomio come prodotto di polinomi più semplici.

La fattorizzazione rende visibili gli zeri, le molteplicità e il segno. Non è quindi un esercizio puramente formale: è lo strumento che permette di risolvere equazioni, disequazioni e limiti con cancellazioni.

### **2. Raccoglimento totale**

Se tutti i termini hanno un fattore comune, lo si raccoglie usando la distributività al contrario:

$$
AB+AC=A(B+C).
$$

Esempio:

$$
6x^4-9x^3+3x^2=3x^2(2x^2-3x+1).
$$

Il fattore comune massimo è $3x^2$. Prima di cercare tecniche più sofisticate conviene controllare sempre se esiste un raccoglimento totale.

> ⚠️ Il fattore raccolto non deve andare perso. Scrivere $6x^4-9x^3+3x^2=2x^2-3x+1$ cambia il polinomio.

### **3. Raccoglimento parziale e raggruppamento**

Quando non esiste un fattore comune a tutti i termini, può essere utile raggrupparli:

$$
x^3+3x^2+2x+6.
$$

Raggruppiamo i primi due e gli ultimi due:

$$
x^2(x+3)+2(x+3).
$$

Ora compare il fattore comune $x+3$:

$$
x^3+3x^2+2x+6=(x+3)(x^2+2).
$$

Il raggruppamento è efficace quando produce lo stesso fattore tra parentesi.

### **4. Quadrato di un binomio**

Dalla distributività:

$$
(a+b)^2=(a+b)(a+b)=a^2+2ab+b^2,
$$

$$
(a-b)^2=a^2-2ab+b^2.
$$

Nel riconoscimento inverso devono essere presenti due quadrati e il doppio prodotto delle loro basi.

Esempio:

$$
9x^2-12x+4=(3x)^2-2\cdot3x\cdot2+2^2=(3x-2)^2.
$$

Invece $9x^2-12x+5$ non è il quadrato di $3x-2$, perché il termine noto dovrebbe essere $4$.

### **5. Differenza di quadrati**

Moltiplicando somma e differenza:

$$
(a-b)(a+b)=a^2-b^2.
$$

Quindi:

$$
a^2-b^2=(a-b)(a+b).
$$

Esempi:

$$
x^2-25=(x-5)(x+5),
$$

$$
4x^6-9=(2x^3-3)(2x^3+3).
$$

La somma $a^2+b^2$ non si fattorizza in fattori lineari reali con la stessa formula.

### **6. Cubo di un binomio**

Applicando il binomio di Newton per $n=3$:

$$
(a+b)^3=a^3+3a^2b+3ab^2+b^3,
$$

$$
(a-b)^3=a^3-3a^2b+3ab^2-b^3.
$$

Esempio di riconoscimento:

$$
8x^3-36x^2+54x-27=(2x-3)^3.
$$

I coefficienti centrali non sono arbitrari: devono essere i tripli prodotti previsti dalla formula.

### **7. Somma e differenza di cubi**

Le identità sono:

$$
a^3-b^3=(a-b)(a^2+ab+b^2),
$$

$$
a^3+b^3=(a+b)(a^2-ab+b^2).
$$

La verifica si ottiene moltiplicando i fattori e osservando la cancellazione dei termini misti.

Esempi:

$$
x^3-8=(x-2)(x^2+2x+4),
$$

$$
27x^3+1=(3x+1)(9x^2-3x+1).
$$

> 💡 Nel secondo fattore il segno del termine misto è opposto a quello del binomio lineare.

### **8. Quadrato di un trinomio**

Per tre termini vale:

$$
(a+b+c)^2=a^2+b^2+c^2+2ab+2ac+2bc.
$$

Per esempio:

$$
(x+y-1)^2=x^2+y^2+1+2xy-2x-2y.
$$

Il riconoscimento inverso richiede tre quadrati e tutti e tre i doppi prodotti con i segni corretti.

### **9. Trinomio di secondo grado**

Se il trinomio monico:

$$
x^2+sx+p
$$

possiede due numeri $u,v$ tali che:

$$
u+v=s,\qquad uv=p,
$$

allora:

$$
x^2+sx+p=(x+u)(x+v).
$$

Infatti:

$$
(x+u)(x+v)=x^2+(u+v)x+uv.
$$

Esempio:

$$
x^2-x-12=(x-4)(x+3),
$$

perché $-4+3=-1$ e $(-4)\cdot3=-12$.

Per un trinomio non monico si può usare la formula risolutiva e poi scrivere:

$$
ax^2+bx+c=a(x-x_1)(x-x_2),
$$

quando le radici reali $x_1,x_2$ esistono.

### **10. Sostituzioni che rivelano la struttura**

Un polinomio può essere quadratico rispetto a una potenza di $x$. Consideriamo:

$$
x^4-5x^2+4.
$$

Poniamo:

$$
t=x^2.
$$

Otteniamo:

$$
t^2-5t+4=(t-1)(t-4).
$$

Tornando a $x$:

$$
x^4-5x^2+4=(x^2-1)(x^2-4).
$$

Applichiamo due differenze di quadrati:

$$
x^4-5x^2+4=(x-1)(x+1)(x-2)(x+2).
$$

La sostituzione è un mezzo di riconoscimento; alla fine bisogna sempre tornare alla variabile originale.

### **11. Tecniche consecutive**

Fattorizziamo completamente:

$$
2x^5-8x^3+6x.
$$

Raccogliamo $2x$:

$$
2x(x^4-4x^2+3).
$$

Poniamo mentalmente $t=x^2$:

$$
t^2-4t+3=(t-1)(t-3).
$$

Quindi:

$$
2x(x^2-1)(x^2-3).
$$

Scomponiamo la differenza di quadrati:

$$
2x(x-1)(x+1)(x^2-3).
$$

Su $\mathbb{R}$ possiamo proseguire:

$$
x^2-3=(x-\sqrt{3})(x+\sqrt{3}),
$$

mentre su $\mathbb{Q}$ il fattore $x^2-3$ è irriducibile.

### **12. Raggruppamento dopo un riordino**

Talvolta i termini devono essere riordinati. Per esempio:

$$
x^3-x^2-4x+4.
$$

Raggruppiamo:

$$
x^2(x-1)-4(x-1),
$$

quindi:

$$
(x-1)(x^2-4).
$$

Infine:

$$
x^3-x^2-4x+4=(x-1)(x-2)(x+2).
$$

### **13. Procedura di riconoscimento**

Quando si deve fattorizzare, conviene controllare nell'ordine:

1. riduzione dei termini simili e ordinamento;
2. fattore comune totale;
3. prodotti notevoli evidenti;
4. raggruppamenti;
5. struttura quadratica in $x^m$;
6. ricerca di radici e divisione, che saranno sviluppate nelle lezioni successive.

<!-- TODO FIGURA:
Schema a flusso dell'algoritmo preliminare di fattorizzazione: ordinare, raccogliere,
riconoscere prodotti notevoli, raggruppare, effettuare una sostituzione, cercare radici.
Obiettivo didattico: mostrare che le tecniche vanno tentate in un ordine ragionato.
-->

### **14. Controllo mediante moltiplicazione**

Ogni fattorizzazione deve poter essere verificata sviluppando il prodotto. Per esempio:

$$
(x-1)(x-2)(x+2)
$$

diventa:

$$
(x-1)(x^2-4)=x^3-x^2-4x+4.
$$

Il controllo individua coefficienti persi, segni errati e fattori mancanti.

### **15. Errori frequenti**

- $a^2+b^2$ non è $(a+b)(a-b)$.
- In $(a-b)^2$ il termine misto è $-2ab$, non $-ab$.
- La differenza di cubi contiene $a^2+ab+b^2$, non $a^2-ab+b^2$.
- Una sostituzione come $t=x^2$ non autorizza a dimenticare di tornare a $x$.
- La fattorizzazione dipende dall'insieme numerico dichiarato.
- Il coefficiente direttivo non deve scomparire.

### **16. Riepilogo**

> ✅ Fattorizzare significa riconoscere una struttura moltiplicativa nascosta e renderla esplicita.

Raccoglimenti, prodotti notevoli, raggruppamenti e sostituzioni costituiscono il primo livello. Quando non bastano, useremo divisione, Ruffini e teorema del fattore.
