## **Lezione 4: Funzioni continue e limiti delle funzioni elementari**
### **1. Riprendiamo il filo del discorso**


In questa lezione proseguiamo il lavoro sui limiti iniziato nei video precedenti, concentrandoci su due idee chiave: il concetto di funzione continua e il modo in cui la continuità rende il calcolo di molti limiti praticamente immediato. L’idea di fondo è semplice: quando una funzione è continua in un punto, il limite in quel punto non è un “mistero”, ma coincide esattamente con il valore della funzione lì.

---
### **2. Primo esempio: quando “non succede nulla di strano”**

Consideriamo la funzione $f:\mathbb{R}\to\mathbb{R}$ definita da $f(x)=x+1$. Il suo grafico è una retta non passante per l’origine. 

![](imgs/Pasted%20image%2020251218130527.png)

Guardando il grafico, oppure ripensando alla definizione di limite, è immediato convincersi che

$$
\lim_{x\to 1} f(x)=2.
$$
  
Infatti, quando x si avvicina a 1, sia da sinistra sia da destra, i valori della funzione, cioè le ordinate dei punti sul grafico, si avvicinano al valore 2. La cosa interessante è che 2 è anche il valore che la funzione assume nel punto:

$$
f(1)=1+1=2.
$$

Quindi in $x=1$ non accade nulla di “anomalo”: il valore della funzione è esattamente quello che il limite “suggerisce”.

---
### **3. Secondo esempio: limite esiste, ma la funzione “fa la furba” nel punto**

Ora immaginiamo un secondo grafico, simile al primo vicino a $x=1$, ma con un dettaglio diverso: avvicinandoci a $x=1$ da sinistra e da destra, la funzione continua a “tendere” a 2, quindi ancora

$$
\lim_{x\to 1} f(x)=2,
$$
  
però questa volta il valore della funzione nel punto è diverso, ad esempio

$$
f(1)=3.
$$
![](imgs/Pasted%20image%2020251218130634.png)

Graficamente significa che sul grafico c’è un punto “piazzato” a quota 3 in corrispondenza di $x=1$, mentre la curva vicino a $x=1$ si avvicina a quota 2. Quindi qui il limite esiste ed è finito, ma non coincide con il valore assunto dalla funzione nel punto.

---
### **4. Il confronto tra i due esempi: continuità vs discontinuità**

Nei due esempi succede una cosa molto istruttiva: in entrambi i casi il limite per $x\to 1$ esiste ed è finito, ma nel primo caso coincide con il valore della funzione nel punto, nel secondo no. L’esperienza suggerisce che la prima situazione è quella “normale”, mentre la seconda è più “patologica”.

Da qui nasce la definizione: nel primo caso la funzione è continua nel punto, nel secondo caso è discontinua.

---
### **5. Definizione di funzione continua in un punto**

Sia $f:A\subseteq\mathbb{R}\to\mathbb{R}$ e sia $x_0$ un punto di accumulazione di $A$. Si dice che $f$ è continua in $x_0$ se

$$
\lim_{x\to x_0} f(x)=f(x_0)
$$

In altre parole, la funzione è continua in $x_0$ quando è definita in $x_0$ e lì vale esattamente ciò che ci aspettiamo guardando come si comporta quando ci avviciniamo a $x_0$. È la formalizzazione matematica dell’idea “in quel punto non succede nulla di strano”.

---
### **6. Continuità su un intervallo: il criterio della penna**

Se una funzione non è continua solo in un punto, ma in tutti i punti di un intervallo, diciamo che è continua su quell’intervallo. Graficamente questo si traduce in un criterio intuitivo molto famoso: su quell’intervallo il grafico si può tracciare senza staccare la penna dal foglio, facendo un unico tratto continuo.

![](imgs/Pasted%20image%2020251218130841.png)

Se invece nell’intervallo c’è un punto in cui siamo obbligati a staccare la penna, allora in quel punto la funzione non è continua e l’intervallo non è un intervallo di continuità.

  ![](imgs/Pasted%20image%2020251218130855.png)

Quando il grafico presenta un salto netto, si parla di discontinuità di prima specie o “a salto”.

---
### **7. Continuità sul dominio e continuità automatica nei punti isolati**

Quando una funzione è continua in tutti i punti del suo dominio, si dice semplicemente che è continua, senza specificare altro. Inoltre, se nel dominio ci sono punti isolati, in quei punti la funzione risulta automaticamente continua, perché non c’è un “intorno” pieno di punti del dominio con cui confrontarsi.

---
### **8. Funzioni elementari continue nel loro dominio naturale**

È un fatto fondamentale, dimostrabile, che le principali funzioni elementari sono continue in tutti i punti del loro dominio naturale. In particolare:

- le potenze $x^n$ (ad esempio $x^2$, $x^3$, ecc.)
    
- le esponenziali $a^x$ (ad esempio $2^x$, $e^x$)
    
- i logaritmi $\log_a x$
    
- le goniometriche $\sin x$, $\cos x$  

Questa informazione è potentissima, perché trasforma moltissimi limiti in semplici sostituzioni.

---

### **9. Esempi: continuità = limite uguale alla sostituzione**

Se ci chiedono
$$\lim_{x\to 1} x^2$$

sapendo che $x^2$ è continua, possiamo dire subito:
$$
\lim_{x\to 1} x^2 = 1^2 = 1
$$

Se ci chiedono
  
$$
\lim_{x\to 0} e^x
$$
  
sapendo che $e^x$ è continua, otteniamo immediatamente:

$$
\lim_{x\to 0} e^x = e^0 = 1.
$$

Quindi il messaggio è: quando la funzione è continua nel punto, il limite si calcola brutalmente sostituendo.

---
### **10. Dove diventano interessanti i limiti**

Se una funzione è continua, i limiti “non sono un problema”. Per questo i limiti diventano davvero interessanti soprattutto in due situazioni:

- nei punti in cui la funzione non è continua, quindi la sostituzione non basta
    
- nei punti di accumulazione del dominio che non appartengono al dominio (ad esempio un estremo aperto, o un punto escluso)

---
### **11. Operazioni che preservano la continuità**

Un fatto altrettanto importante è che tutte le funzioni ottenute tramite somma, prodotto, quoziente e composizione di funzioni elementari risultano continue nel loro dominio naturale.

Esempi tipici:

- $e^{x^2}$, ottenuta componendo una potenza e un’esponenziale, è continua per ogni $x\in\mathbb{R}$
    
- un polinomio come $3x^2-x-1$ è continuo su $\mathbb{R}$
    
- una funzione goniometrica come $\frac{\sin x}{\cos x}$ è continua dove è definita, cioè dove $\cos x\neq 0$

Quindi se devo fare il limite in un punto dove la funzione è definita e so che è continua, torno sempre alla sostituzione.

---
### **12. Grafici da memorizzare: perché aiutano con i limiti**

A questo punto vale la pena ricordare alcuni grafici fondamentali, perché nella risoluzione dei limiti capita spesso di dover “intuire” come si comporta una funzione.

---
### **13. La funzione** $f(x)=x$

Il grafico è la retta bisettrice del primo e del terzo quadrante. È immediato che:

  ![](imgs/Pasted%20image%2020251218132312.png)

$$
\lim_{x\to +\infty} x = +\infty,\qquad \lim_{x\to -\infty} x = -\infty.
$$

---
### **14. Potenze con esponente pari:** $x^n$ **con** $n$ **pari**

Per $n$ pari (come $x^2$, $x^4$, $x^6$,$\dots$) il grafico è simmetrico rispetto all’asse y e “sale” sia a destra che a sinistra. Quindi:

  ![](imgs/Pasted%20image%2020251218132351.png)

$$
\lim_{x\to +\infty} x^n = +\infty,\qquad \lim_{x\to -\infty} x^n = +\infty.
$$

---
### **15. Potenze con esponente dispari:** $x^n$ **con** n **dispari e** $n>1$

Per $n$ dispari (come $x^3$, $x^5$,$\dots$) il grafico va su a destra e giù a sinistra, quindi:

![](imgs/Pasted%20image%2020251218132445.png)

$$
\lim_{x\to +\infty} x^n = +\infty,\qquad \lim_{x\to -\infty} x^n = -\infty.
$$

---
### **16. La funzione** $f(x)=\frac{1}{x}$

Il grafico ha due rami e non è definita in $x=0$.

![](imgs/Pasted%20image%2020251218132456.png)

All’infinito:

$$
\lim_{x\to +\infty} \frac{1}{x} = 0^+,\qquad \lim_{x\to -\infty} \frac{1}{x} = 0^-.
$$
  

Vicino a 0 succede qualcosa di diverso a seconda del lato:

$$
\lim_{x\to 0^+} \frac{1}{x} = +\infty,\qquad \lim_{x\to 0^-} \frac{1}{x} = -\infty.
$$
  
E poiché limite destro e sinistro sono diversi, il limite per $x\to 0$ senza specificare il lato non esiste.

---
### **17. Funzioni esponenziali:** $a^x$

Se $a>1$, il grafico cresce:

$$
\lim_{x\to +\infty} a^x = +\infty,\qquad \lim_{x\to -\infty} a^x = 0^+.
$$

![](imgs/Pasted%20image%2020251218132647.png)

Se $0<a<1$, la situazione è simmetrica:

$$
\lim_{x\to +\infty} a^x = 0^+,\qquad \lim_{x\to -\infty} a^x = +\infty.
$$

---

### **18. Funzioni logaritmiche:** $\log_a x$

Per $a>1$:

$$
\lim_{x\to +\infty} \log_a x = +\infty,\qquad \lim_{x\to 0^+} \log_a x = -\infty.
$$

![](imgs/Pasted%20image%2020251218132715.png)

Per $0<a<1$ il grafico è “ribaltato”:

$$
\lim_{x\to +\infty} \log_a x = -\infty,\qquad \lim_{x\to 0^+} \log_a x = +\infty.
$$

---
### **19. Funzioni goniometriche:** $\sin x$ **e** $\cos x$

$\sin x$ e $\cos x$ oscillano tra -1 e 1 in modo periodico, senza smorzarsi. Per questo:

$$
\lim_{x\to +\infty} \sin x \text{ non esiste},\qquad \lim_{x\to +\infty} \cos x \text{ non esiste},
$$

e lo stesso vale per $x\to -\infty$.

![](imgs/Pasted%20image%2020251218132836.png)

---

### **20. Chiusura della lezione**

Il punto chiave di questa lezione è che la continuità rende il calcolo dei limiti semplice: se la funzione è continua nel punto, il limite coincide con il valore della funzione. Per questo molti limiti si risolvono sostituendo direttamente. I casi davvero interessanti sono quelli in cui la funzione non è continua o quando il punto verso cui tende x è un punto di accumulazione del dominio ma non appartiene al dominio.