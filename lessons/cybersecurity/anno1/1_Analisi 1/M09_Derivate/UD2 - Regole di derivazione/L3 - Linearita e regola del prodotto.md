# **M9 UD2 Lezione 3 - Linearità e regola del prodotto**

### **Somma, differenza e prodotto di funzioni**

### **1. Perché servono le regole di derivazione**

Nei video precedenti abbiamo calcolato le derivate usando **la definizione**:

$$
f'(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}
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
f'(x)=p'(x)+q'(x)
$$

In parole semplici: **la derivata di una somma è la somma delle derivate**.

---
### **3. Perché la regola della somma è vera**

Partiamo dalla definizione:

$$
f'(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}
$$

Poiché $f(x)=p(x)+q(x)$, allora:

$$
f(x+h)=p(x+h)+q(x+h)
$$

Sostituendo:

$$
f'(x)=\lim_{h\to 0}\frac{p(x+h)+q(x+h)-p(x)-q(x)}{h}
$$

Riorganizziamo i termini:

$$
f'(x)=\lim_{h\to 0}\left(\frac{p(x+h)-p(x)}{h}+\frac{q(x+h)-q(x)}{h}\right)
$$

Ora riconosciamo due rapporti incrementali:

$$
\lim_{h\to 0}\frac{p(x+h)-p(x)}{h}=p'(x)
$$

$$
\lim_{h\to 0}\frac{q(x+h)-q(x)}{h}=q'(x)
$$

Quindi:
$$
f'(x)=p'(x)+q'(x)
$$

---
### **4. Esempi sulla somma**

#### **Esempio 1**

$$
f(x)=x+e^x
$$

Allora:
$$
f'(x)=\frac{d}{dx}(x)+\frac{d}{dx}(e^x)=1+e^x
$$

#### **Esempio 2**

$$
f(x)=x^{2013}+\sin x
$$

Allora:

$$
f'(x)=2013\,x^{2012}+\cos x
$$

---
### **5. Regola della differenza**

Se:
$$
f(x)=p(x)-q(x)
$$

allora:
$$
f'(x)=p'(x)-q'(x)
$$

Cioè: **la derivata di una differenza è la differenza delle derivate**.

> 📌 Somma, differenza e multipli si riassumono in un unico enunciato, la **linearità della derivazione**: se $p$ e $q$ sono derivabili in $x_0$ e $\alpha,\beta\in\mathbb{R}$, allora
>
> $$
> (\alpha p+\beta q)'(x_0) = \alpha\,p'(x_0)+\beta\,q'(x_0)
> $$
>
> La dimostrazione è la stessa della somma: il rapporto incrementale di $\alpha p+\beta q$ si spezza in $\alpha\cdot$(rapporto di $p$)$\;+\;\beta\cdot$(rapporto di $q$), e si conclude con l'algebra dei limiti di M08. In particolare $(\lambda f)'=\lambda f'$: le costanti moltiplicative "escono" dalla derivata.

#### **Esempio**

$$
f(x)=\ln x-x^3
$$

Allora:
$$
f'(x)=\frac{1}{x}-3x^2
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
f'(x)=p'(x) \cdot q(x)+p(x) \cdot q'(x)
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
f'(x)=(2x)\sin x+x^2\cos x
$$
#### **Esempio 2**

$$
f(x)=e^x\cos x
$$

Derivata del primo: $e^x$

Derivata del secondo: $-\sin x$

Quindi:
$$
f'(x)=e^x\cos x+e^x(-\sin x)
$$

cioè:
$$
f'(x)=e^x\cos x-e^x\sin x
$$

---
### **8. Perché la regola del prodotto è vera**

Partiamo dalla definizione:

$$
f'(x)=\lim_{h\to 0}\frac{f(x+h)-f(x)}{h}
$$

Con $f(x)=p(x)q(x)$ abbiamo:

$$
	f(x+h)=p(x+h) \cdot q(x+h)
$$

Quindi:

$$
f'(x)=\lim_{h\to 0}\frac{p(x+h)q(x+h)-p(x)q(x)}{h}
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

- $p$ è derivabile in $x$, quindi è **continua** in $x$ per il teorema della UD1, Lezione 4 (è esattamente qui che quel teorema serve): allora

$$
p(x+h)\to p(x)
$$

- il primo rapporto incrementale tende a:
$$
\frac{q(x+h)-q(x)}{h}\to q'(x)
$$

- $q(x)$ non dipende da $h$, quindi resta tale
    
- il secondo rapporto incrementale tende a:
$$
\frac{p(x+h)-p(x)}{h}\to p'(x)
$$
Quindi nel limite otteniamo:

$$
f'(x)=p(x) \cdot q'(x)+q(x) \cdot p'(x)
$$

che è la stessa cosa di:

$$
f'(x)=p'(x) \cdot q(x)+p(x) \cdot q'(x)
$$

---
### **9. Estensioni: tre fattori, potenze e formula di Leibniz**

Applicando due volte la regola del prodotto:

$$
(fgh)' = f'gh + fg'h + fgh'
$$

("si deriva un fattore alla volta e si somma"). Con $f=g$ si ottiene $(f^2)'=2ff'$, e per induzione $(f^n)'=n f^{\,n-1} f'$ per ogni $n\in\mathbb{N}$ — un'anteprima della regola della catena. Esiste anche la generalizzazione della regola del prodotto alle derivate $n$-esime, la **formula di Leibniz** $(fg)^{(n)}=\sum_{k=0}^{n}\binom{n}{k}f^{(k)}g^{(n-k)}$, che enunciamo e dimostriamo come approfondimento nella UD3, Lezione 5, dopo aver definito le derivate successive.

> ⚠️ Errori tipici sul prodotto: derivare "fattore per fattore" ($(fg)'\neq f'g'$ — controesempio immediato: $f=g=x$ dà $(x^2)'=2x$ mentre $f'g'=1$); dimenticare uno dei due addendi; sbagliare l'ordine nel caso di tre fattori.

---
### **10. Anticipazione**

Nella prossima lezione vedremo come derivare:

- il **reciproco** di una funzione
    
- il **rapporto** tra due funzioni
  
cioè arriveremo alla regola del quoziente.

---