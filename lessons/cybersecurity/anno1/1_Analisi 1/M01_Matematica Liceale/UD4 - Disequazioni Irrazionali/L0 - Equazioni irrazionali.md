# **M1 UD4 Lezione 0 - Equazioni irrazionali**

### **1. Che cosa rende irrazionale un'equazione**

Un'equazione è detta **irrazionale** quando l'incognita compare nel radicando di almeno una radice. Il punto centrale non è eliminare il simbolo di radice il più rapidamente possibile, ma controllare quali trasformazioni siano equivalenti e quali producano soltanto candidati.

Per le radici pari intervengono dominio e non negatività; per le radici dispari l'elevamento alla potenza corrispondente è invece reversibile su tutto $\mathbb{R}$.

### **2. Perché il quadrato può aggiungere soluzioni**

Da $A=B$ segue sempre $A^2=B^2$, ma il viceversa equivale ad $A=\pm B$. Per esempio:

$$
x=-1\implies x^2=1
$$

mentre $x^2=1$ ammette anche $x=1$. Pertanto, se si eleva al quadrato senza condizioni di segno, il risultato è in generale una condizione necessaria ma non sufficiente.

Se invece $A\ge0$ e $B\ge0$, la funzione quadrato è strettamente crescente su $[0,+\infty)$ e vale:

$$
A=B\iff A^2=B^2
$$

### **3. Procedura per una radice pari isolata**

Per un'equazione del tipo:

$$
\sqrt{A(x)}=B(x)
$$

si ha l'equivalenza:

$$
\sqrt{A(x)}=B(x)
\iff
\begin{cases}
B(x)\ge0 \\
A(x)=B(x)^2
\end{cases}
$$

La condizione $A(x)\ge0$ è automaticamente conseguenza di $A(x)=B(x)^2$, ma conviene determinarla prima per conoscere il dominio originario. La procedura completa è:

1. determinare il dominio;
2. isolare una radice;
3. imporre che il membro opposto abbia il segno compatibile;
4. elevare alla potenza corrispondente;
5. risolvere l'equazione ottenuta;
6. verificare i candidati nell'equazione originaria.

### **4. Esempio con soluzione estranea**

Risolviamo:

$$
\sqrt{x+6}=x
$$

Il dominio richiede $x\ge-6$, ma l'uguaglianza impone anche $x\ge0$. Elevando al quadrato:

$$
x+6=x^2
$$

quindi:

$$
x^2-x-6=0
$$

e:

$$
(x-3)(x+2)=0
$$

I candidati sono $3$ e $-2$. Il secondo non soddisfa $x\ge0$ e infatti $\sqrt4\ne-2$. Pertanto:

$$
S=\{3\}
$$

### **5. Una radice pari non isolata**

Consideriamo:

$$
\sqrt{x+1}+1=x
$$

Isoliamo la radice:

$$
\sqrt{x+1}=x-1
$$

Il dominio impone $x\ge-1$ e la non negatività del secondo membro impone $x\ge1$. Ora il quadrato è equivalente:

$$
x+1=(x-1)^2
$$

da cui:

$$
x^2-3x=0
$$

I candidati sono $x=0$ e $x=3$, ma l'intersezione con $x\ge1$ lascia soltanto $x=3$, che verifica l'equazione originaria.

### **6. Due radicali ed elevamenti successivi**

Risolviamo:

$$
\sqrt{x+1}+\sqrt{x-2}=3
$$

Il dominio è $x\ge2$. Isoliamo il secondo radicale:

$$
\sqrt{x+1}=3-\sqrt{x-2}
$$

Il secondo membro deve essere non negativo, quindi $\sqrt{x-2}\le3$, ossia $x\le11$. Elevando al quadrato:

$$
x+1=9-6\sqrt{x-2}+x-2
$$

e dunque:

$$
\sqrt{x-2}=1
$$

Elevando nuovamente:

$$
x-2=1
$$

si ottiene $x=3$. La verifica dà $\sqrt4+\sqrt1=3$, quindi:

$$
S=\{3\}
$$

Ogni elevamento pari può generare nuovi candidati; la verifica finale resta obbligatoria anche quando sono state imposte condizioni di segno intermedie.

### **7. Radici di indice dispari**

La funzione $t\mapsto t^{2k+1}$ è strettamente crescente e biiettiva su $\mathbb{R}$. Pertanto:

$$
\sqrt[2k+1]{A(x)}=B(x)
\iff
A(x)=B(x)^{2k+1}
$$

purché le espressioni interne siano definite. Per esempio:

$$
\sqrt[3]{2x-1}=x
$$

equivale a:

$$
2x-1=x^3
$$

ossia:

$$
x^3-2x+1=0
$$

Poiché $x=1$ è una radice:

$$
x^3-2x+1=(x-1)(x^2+x-1)
$$

e tutte le radici reali del polinomio sono soluzioni dell'equazione irrazionale, senza candidati estranei prodotti dall'elevamento al cubo.

### **8. Equazione con parametro**

Consideriamo:

$$
\sqrt{x+1}=m
$$

La radice è non negativa, quindi se $m<0$ non esistono soluzioni. Se $m\ge0$, il quadrato è equivalente e fornisce:

$$
x+1=m^2
$$

quindi:

$$
x=m^2-1
$$

Tale valore appartiene sempre al dominio perché $m^2-1\ge-1$. Pertanto:

$$
S_m=
\begin{cases}
\varnothing & m<0 \\
\{m^2-1\} & m\ge0
\end{cases}
$$

### **9. Esercizio misto con dominio e verifica**

Risolviamo:

$$
\frac{\sqrt{x+3}}{x-1}=1
$$

Il dominio originario è:

$$
x\ge-3,\qquad x\ne1
$$

Moltiplicando per $x-1$, non nullo nel dominio:

$$
\sqrt{x+3}=x-1
$$

Poiché il primo membro è non negativo, serve $x\ge1$; insieme all'esclusione si ha $x>1$. Elevando al quadrato:

$$
x+3=(x-1)^2
$$

e quindi:

$$
x^2-3x-2=0
$$

I candidati sono:

$$
x=\frac{3\pm\sqrt{17}}{2}
$$

Soltanto il valore con il segno $+$ è maggiore di $1$. La verifica nell'equazione originaria conferma:

$$
S=\left\{\frac{3+\sqrt{17}}{2}\right\}
$$

### **10. Errori frequenti**

- Elevare al quadrato prima di isolare il radicale.
- Dimenticare che il membro opposto a una radice pari deve essere non negativo.
- Considerare equivalente un passaggio che produce soltanto candidati.
- Aggiungere $A(x)\ge0$ come condizione alle radici dispari, perdendo soluzioni.
- Dimenticare vincoli provenienti da denominatori presenti nel radicando o fuori da esso.
- Verificare soltanto nell'equazione trasformata e non in quella originaria.

### **11. Riepilogo**

> ✅ Con radici pari, dominio, segno e verifica fanno parte della soluzione. Con radici dispari, l'elevamento alla potenza corrispondente è una trasformazione equivalente sui reali.

Le due lezioni successive applicano gli stessi principi alle disequazioni, dove la monotonia e il segno del secondo membro determinano anche il verso del confronto.
