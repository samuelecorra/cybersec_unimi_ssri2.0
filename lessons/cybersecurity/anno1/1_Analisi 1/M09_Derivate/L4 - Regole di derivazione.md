## **Lezione 4: Regole di derivazione**

### **Somma, differenza e prodotto di funzioni**

### **1. Perché servono le regole di derivazione**

Nei video precedenti abbiamo calcolato le derivate usando **la definizione**:

$$

f’(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}

$$

Questo metodo è perfetto per capire **che cos’è** una derivata, ma diventa rapidamente impraticabile: appena la funzione è un minimo complicata, il rapporto incrementale produce conti lunghissimi.

Per questo si introducono le **regole di derivazione**: una volta memorizzate, derivare diventa un’operazione rapida e quasi meccanica.

---
### **2. Regola della somma**

Supponiamo che:

$$

f(x)=p(x)+q(x)

$$

Allora, dove le derivate esistono, vale:

$$

f’(x)=p’(x)+q’(x)

$$

In parole semplici: **la derivata di una somma è la somma delle derivate**.

---
### **3. Perché la regola della somma è vera**

Partiamo dalla definizione:

$$

f’(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}

$$

Poiché $f(x)=p(x)+q(x)$, allora:

$$

f(x+h)=p(x+h)+q(x+h)

$$

Sostituendo:

$$

f’(x)=\lim_{h\to 0}\frac{p(x+h)+q(x+h)-p(x)-q(x)}{h}

$$

Riorganizziamo i termini:

$$

f’(x)=\lim_{h\to 0}\left(\frac{p(x+h)-p(x)}{h}+\frac{q(x+h)-q(x)}{h}\right)

$$

Ora riconosciamo due rapporti incrementali:

$$

\lim_{h\to 0}\frac{p(x+h)-p(x)}{h}=p’(x)

$$

$$

\lim_{h\to 0}\frac{q(x+h)-q(x)}{h}=q’(x)

$$

Quindi:
$$

f’(x)=p’(x)+q’(x)

$$

---
### **4. Esempi sulla somma**

#### **Esempio 1**

$$

f(x)=x+e^x

$$

Allora:
$$

f’(x)=\frac{d}{dx}(x)+\frac{d}{dx}(e^x)=1+e^x

$$

#### **Esempio 2**

$$

f(x)=x^{2013}+\sin x

$$

Allora:

$$

f’(x)=2013,x^{2012}+\cos x

$$

---
### **5. Regola della differenza**

Se:
$$

f(x)=p(x)-q(x)

$$

allora:
$$

f’(x)=p’(x)-q’(x)

$$

Cioè: **la derivata di una differenza è la differenza delle derivate**.

#### **Esempio**

$$

f(x)=\ln x-x^3

$$

Allora:
$$

f’(x)=\frac{1}{x}-3x^2

$$

con la precisazione che $\ln x$ (e quindi la derivata intesa come derivata di $\ln x$) vale solo per:
$$

x>0

$$

---
### **6. Regola del prodotto**

Supponiamo ora che:

$$

f(x)=p(x) \cdot q(x)

$$

Allora la derivata è:

$$

f’(x)=p’(x) \cdot q(x)+p(x) \cdot q’(x)

$$

In parole semplici:

> **derivi la prima e lasci la seconda, più lasci la prima e derivi la seconda**

---
### **7. Esempi sul prodotto**

#### **Esempio 1**

$$

f(x)=x^2\sin x

$$

Allora:
$$

f’(x)=(2x)\sin x+x^2\cos x

$$
#### **Esempio 2**

$$

f(x)=e^x\cos x

$$

Derivata del primo: $e^x$

Derivata del secondo: $-\sin x$

Quindi:
$$

f’(x)=e^x\cos x+e^x(-\sin x)

$$

cioè:
$$

f’(x)=e^x\cos x-e^x\sin x

$$

---
### **8. Perché la regola del prodotto è vera**

Partiamo dalla definizione:

$$

f’(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}

$$

Con $f(x)=p(x)q(x)$ abbiamo:

$$

	f(x+h)=p(x+h) \cdot q(x+h)

$$

Quindi:

$$

f’(x)=\lim_{h\to 0}\frac{p(x+h)q(x+h)-p(x)q(x)}{h}

$$

Qui arriva la “mossa furba”: **aggiungiamo e togliamo lo stesso termine**:

$$

p(x+h)q(x)

$$

così il numeratore diventa:

$$

p(x+h)q(x+h)-p(x+h)q(x)+p(x+h)q(x)-p(x)q(x)

$$

Ora raggruppiamo:

- nei primi due termini raccogliamo $p(x+h)$
    
- negli ultimi due raccogliamo $q(x)$  

Otteniamo:

$$

p(x+h)\big(q(x+h)-q(x)\big)+q(x)\big(p(x+h)-p(x)\big)

$$

Dividiamo per $h$:

$$

\frac{p(x+h)\big(q(x+h)-q(x)\big)}{h}+\frac{q(x)\big(p(x+h)-p(x)\big)}{h}

$$

Scriviamolo in modo più leggibile:

$$

p(x+h)\cdot\frac{q(x+h)-q(x)}{h}

+

q(x)\cdot\frac{p(x+h)-p(x)}{h}

$$

Ora facciamo tendere $h$ a $0$.

- se $p$ è almeno continua, allora:

$$

p(x+h)\to p(x)

$$

- il primo rapporto incrementale tende a:
$$

\frac{q(x+h)-q(x)}{h}\to q’(x)

$$

- $q(x)$ non dipende da $h$, quindi resta tale
    
- il secondo rapporto incrementale tende a:
$$

\frac{p(x+h)-p(x)}{h}\to p’(x)

$$
Quindi nel limite otteniamo:

$$

f’(x)=p(x) \cdot q’(x)+q(x) \cdot p’(x)

$$

che è la stessa cosa di:

$$

f’(x)=p’(x) \cdot q(x)+p(x) \cdot q’(x)

$$

---
### **9. Anticipazione**

Nel prossimo video vedremo come derivare:

- il **reciproco** di una funzione
    
- il **rapporto** tra due funzioni
  
cioè arriveremo alla regola del quoziente.

---