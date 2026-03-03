# **M3 UD1 Lezione 2 - Convoluzione di distribuzioni e densità**

### **1. Introduzione**

Nella lezione precedente abbiamo visto che la **somma di due variabili aleatorie indipendenti** produce una nuova variabile aleatoria, la cui **distribuzione** non coincide con quella delle variabili di partenza.  
In questa lezione generalizziamo il procedimento, introducendo il concetto di **convoluzione**, che permette di calcolare la distribuzione della somma anche quando le variabili non sono uniformi.

---

### **2. Ripasso del procedimento discreto**

Nel caso di due variabili discrete indipendenti $x$ e $y$:

- abbiamo costruito la **tabella delle coppie ordinate** $(x, y)$;
    
- abbiamo calcolato le probabilità $P(x, y) = P(x) \cdot P(y)$;
    
- abbiamo poi **sommato** le probabilità corrispondenti alle coppie con **stessa somma** $z = x + y$.

Esempio:

|y \ x|1|2|3|
|:-:|:-:|:-:|:-:|
|3|(1,3)|(2,3)|(3,3)|
|2|(1,2)|(2,2)|(3,2)|
|1|(1,1)|(2,1)|(3,1)|

Tutte le coppie hanno probabilità:

$$  
P(x y) = P(x) \cdot P(y) = \tfrac{1}{3} \times \tfrac{1}{3} = \tfrac{1}{9}  
$$

---

### **3. Procedimento generale**

Questo metodo può essere espresso in modo **generale**, valido anche per variabili non uniformi.

Date due variabili indipendenti $x$ e $y$, distribuite secondo $X(x)$ e $Y(y)$, la distribuzione della somma $z = x + y$ si ottiene come:

1. Si calcola il **prodotto** $X(x)Y(y)$.
    
2. Per ogni valore fissato di $z$, si **sommano tutti i prodotti** $X(x)Y(y)$ tali che $x + y = z$.

---

### **4. Caso discreto: esempio numerico**

Ad esempio, per $z = 4$ si considerano le coppie che soddisfano $x + y = 4$:

$$  
Z(4) = X(1)Y(3) + X(2)Y(2) + X(3)Y(1)  
$$

---

### **5. Caso continuo**

Nel caso continuo, invece di sommare, **integriamo** su tutti i valori di $x$ compatibili con $y = z - x$.

La formula generale della **convoluzione continua** è:

$$  
Z(z) = \int_{-\infty}^{+\infty} X(x) \cdot Y(z - x) \ dx  
$$

---

### **6. Definizione di Convoluzione**

L’operazione appena descritta si chiama **convoluzione** (simbolo $\ast$ oppure $\circledast$).

In forma compatta si scrive:

$$  
Z = X \ast Y  
$$

La **somma di variabili aleatorie** corrisponde quindi alla **convoluzione delle loro distribuzioni**:

$$  
z = x + y \quad \Longrightarrow \quad Z = X \ast Y  
$$

---

### **7. Notazione sintetica**

Quando diciamo che una variabile aleatoria $x$ è distribuita secondo $X$, scriviamo:

$$  
x \sim X  
$$

Pertanto:

$$  
x \sim X, \quad y \sim Y, \quad z = x + y  
$$

implica:

$$  
z \sim Z \equiv X \ast Y  
$$

---

### **8. Convoluzione discreta e continua**

- **Convoluzione discreta** → somma di probabilità per i valori di $x$ e $y$ tali che $x + y = z$.
    
- **Convoluzione continua** → integrale del prodotto $X(x)Y(z-x)$ su tutti i valori di $x$.

---

### **9. Convoluzione di esponenziali**

Consideriamo due **variabili aleatorie esponenziali** con lo stesso parametro $\lambda$:

$$  
X(x) = \lambda e^{-\lambda x}, \quad Y(y) = \lambda e^{-\lambda y}, \quad x,y \ge 0  
$$

Poiché $x, y > 0$, possiamo limitare l’integrale al dominio $0 \le x \le z$.  
Applicando la formula della convoluzione:

$$  
Z(z) = \int_0^z \lambda e^{-\lambda x} \cdot \lambda e^{-\lambda (z-x)} \ dx  
$$

Semplificando:

$$  
Z(z) = \lambda^2 e^{-\lambda z} \int_0^z dx = \lambda^2 z e^{-\lambda z}  
$$

---

### **10. Densità risultante: distribuzione di Erlang**

La densità ottenuta è una **Erlang** (o **Gamma**) con parametri $(r=2, \lambda)$:

$$  
Z(z) = \lambda^2 z e^{-\lambda z}  
$$

In generale, la **somma di due esponenziali** con lo stesso parametro $\lambda$ è una **Erlang di ordine 2**.

---

### **11. Interpretazione fisica (esempio dei componenti in stand-by)**

Se due componenti hanno vita esponenziale (senza memoria) e vengono predisposti in **stand-by**:

- si accende il primo e si attende il guasto;
    
- subito dopo si accende il secondo.

Allora la **vita complessiva del sistema** segue una **distribuzione Erlang (Gamma)**, cioè la somma di due esponenziali identiche.

---

### **12. Somme successive**

La convoluzione è un’operazione **associativa**, quindi può essere applicata più volte:

$$  
v = x + y + w  
$$

si può calcolare in due passaggi:

1. $z = x + y \Rightarrow Z = X \ast Y$
    
2. $v = z + w \Rightarrow V = Z \ast W$

---

### **13. Somma di $r$ variabili esponenziali**

Se convolviamo **$r$ densità esponenziali** identiche con parametro $\lambda$, otteniamo una **famiglia Erlang** con parametri $(r, \lambda)$:

$$  
Z_r(z) = \frac{\lambda^r z^{r-1}}{(r-1)!} e^{-\lambda z}, \quad z \ge 0  
$$

Per $r = 1$, si riottiene l’esponenziale negativa.

---

### **14. Interpretazione della famiglia Erlang**

L’Erlang di parametro $r$ può essere vista come la **somma di $r$ variabili esponenziali indipendenti**.

Applicazione:

- $r$ componenti identici, con vita esponenziale di parametro $\lambda$;
    
- disposti **in stand-by** (uno alla volta, non in parallelo);
    
- la vita totale del sistema segue una **Erlang$(r \lambda)$**.

---

### **15. Alternative alla convoluzione**

Il calcolo diretto della convoluzione può essere laborioso.  
Per questo si usano metodi alternativi, come le **funzioni generatrici di probabilità** (nelle prossime lezioni).

---

### **16. Sintesi finale**

|      Caso      | Operazione sulle variabili | Operazione sulle distribuzioni | Distribuzione risultante |
| :------------: | :------------------------: | :----------------------------: | :----------------------: |
|   2 uniformi   |        $z = x + y$         |         $Z = X \ast Y$         |       Triangolare        |
| 2 esponenziali |        $z = x + y$         |         $Z = X \ast Y$         |        Erlang(2λ)        |
| r esponenziali |  $z = x_1 + \dots + x_r$   |        $Z = X^{\ast r}$        |        Erlang(rλ)        |
