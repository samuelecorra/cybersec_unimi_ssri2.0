## **Lezione 14: Formula di Taylor con resto di Peano**

### **1. Perché la formula di Taylor è fondamentale**

I **polinomi** svolgono un ruolo centrale nello studio delle funzioni reali, perché coinvolgono solo le operazioni elementari di **somma e prodotto**.

Per questo motivo:

- limiti,
    
- derivate,
    
- integrali

che coinvolgono **solo polinomi** sono in genere **facili da trattare**.

La **formula di Taylor** è fondamentale perché permette di **approssimare localmente funzioni complesse tramite polinomi**, rendendo così più semplici molti problemi di Analisi.

---
### **2. Ipotesi per applicare la formula**

Sia $f(x)$ una funzione definita in un intorno di $0$, ad esempio $(-\delta, \delta)$.

Supponiamo che:

- $f$ sia **derivabile $n-1$ volte** in $(-\delta, \delta)$;
    
- esista la **derivata $n$-esima in $x = 0$**.

---
### **3. Formula di Taylor con resto di Peano**

Se valgono le ipotesi precedenti, allora per $x \to 0$ vale:

$$

f(x) = T_n(x) + o(x^n)

$$

dove $T_n(x)$ è il **polinomio di Taylor di ordine $n$** dato da:

$$
T_n(x) = f(0) + f’(0)x + \frac{f’’(0)}{2!}x^2 + \cdots + \frac{f^{(n)}(0)}{n!}x^n

$$
In forma compatta:
$$

T_n(x) = \sum_{k=0}^{n} \frac{f^{(k)}(0)}{k!}x^k

$$

---
### **4. Significato della formula**

La formula ci dice che:

- $T_n(x)$ è il **polinomio che approssima la funzione**;
    
- $o(x^n)$ è l’**errore di approssimazione**, che diventa trascurabile per $x \to 0$.

Quando la formula è scritta così, l’errore $o(x^n)$ prende il nome di **resto di Peano**.

---
### **5. Miglioramento dell’approssimazione**

Al crescere di $n$:

- il polinomio contiene più termini;
    
- l’errore $o(x^n)$ diventa **sempre più piccolo**.

Questo significa che **aumentando l’ordine del polinomio**, l’approssimazione migliora in un intorno sempre più preciso del punto considerato.

---
### **6. Polinomi di Taylor delle funzioni elementari**

Gli sviluppi di Taylor delle funzioni elementari (esponenziale, seno, coseno, logaritmo, ecc.) possono essere:

- calcolati applicando direttamente la formula;
    
- **memorizzati** tramite una tabella riassuntiva.

Per esempio, per $e^x$:

- approssimazione al secondo ordine:

$$

e^x = 1 + x + \frac{x^2}{2} + o(x^2)

$$

- approssimazione al terzo ordine:

$$

e^x = 1 + x + \frac{x^2}{2} + \frac{x^3}{6} + o(x^3)

$$

---
### **7. Uso della formula nei limiti**

La formula di Taylor è particolarmente utile nella risoluzione dei **limiti con forme indeterminate**, dove una funzione “scomoda” impedisce una semplificazione diretta.

---
### **8. Esempio di limite risolto con Taylor**

Consideriamo un limite del tipo:

$$

\lim_{x \to 0} \frac{\sin x - x}{x^3}

$$

Siamo davanti a una forma indeterminata $0/0$.

Usiamo lo sviluppo di Taylor del seno al terzo ordine:

$$

\sin x = x - \frac{x^3}{6} + o(x^3)

$$
Sostituendo:

$$
\frac{(x - \frac{x^3}{6} + o(x^3)) - x}{x^3} =

\frac{-\frac{x^3}{6} + o(x^3)}{x^3}
$$

Dividendo:
$$

-\frac{1}{6} + \frac{o(x^3)}{x^3}

$$

Poiché:
$$

\frac{o(x^3)}{x^3} \to 0

$$

il limite vale:
$$

\boxed{-\frac{1}{6}}

$$

---
### **9. Costruzione di nuovi sviluppi**

Una volta noti gli sviluppi delle funzioni elementari, è possibile ottenere quelli di funzioni più complesse:

- **somme**
    
- **prodotti**
    
- **composizioni**

lavorando **direttamente sui polinomi**, senza ricalcolare ogni volta tutte le derivate.

---
### **10. Esempio: somma di funzioni**

Per sviluppare al quarto ordine:
$$

\sin x + \cos x

$$
si usano gli sviluppi:
$$

\sin x = x - \frac{x^3}{6} + o(x^4)

$$

$$

\cos x = 1 - \frac{x^2}{2} + o(x^4)

$$
Sommandoli:
$$

1 + x - \frac{x^2}{2} - \frac{x^3}{6} + o(x^4)

$$

---
### **11. Esempio: prodotto di funzioni**

Per sviluppare al quarto ordine:
$$

\sin x \cos x

$$

si moltiplicano gli sviluppi troncati:
$$

(x - \frac{x^3}{6})(1 - \frac{x^2}{2})

$$
I termini rilevanti sono:
$$

x - \frac{x^3}{2} - \frac{x^3}{6}

$$
cioè:
$$

x - \frac{2x^3}{3} + o(x^4)

$$

Tutti i termini di grado superiore vengono **assorbiti dal piccolo-o**.

---
### **12. Funzioni composte**

Per funzioni come:
$$

\sin(3x)

$$
si prende lo sviluppo del seno e si sostituisce $x$ con $3x$:

$$

\sin(3x) = 3x - \frac{(3x)^3}{6} + o(x^4)

$$
cioè:
$$

\sin(3x) = 3x - \frac{9}{2}x^3 + o(x^4)

$$

---
### **13. Idea chiave da portare a casa**

- Taylor permette di **trasformare funzioni in polinomi**
    
- Il resto di Peano misura l’**errore**
    
- Nei limiti contano solo i **termini di grado più basso**
    
- Le funzioni più complesse si trattano **operando sugli sviluppi**
