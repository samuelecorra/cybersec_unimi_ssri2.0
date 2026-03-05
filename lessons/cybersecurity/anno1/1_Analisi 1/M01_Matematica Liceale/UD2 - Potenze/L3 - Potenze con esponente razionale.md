# **M1 UD2 Lezione 3 - Potenze con esponente razionale**

### **1. Introduzione: perché gli esponenti razionali sono inevitabili**

Dopo aver introdotto:

- esponenti **interi** ($a^n$),
    
- esponenti **reciproci** ($a^{1/n}$),
    

il passo successivo è naturale e necessario:  
unificare questi due mondi in un’unica definizione coerente.

Gli **esponenti razionali** permettono di:

- descrivere **radici di potenze**;
    
- mantenere valide **tutte le proprietà delle potenze**;
    
- lavorare con funzioni che compaiono ovunque in Analisi, Fisica e Ingegneria.
    

---

## **2. Definizione di potenza con esponente razionale**

Sia $a \ge 0$ e sia:

$$  
r = \frac{p}{q} \in \mathbb{Q}, \quad q \ge 1, \quad \gcd(p,q)=1  
$$

Si definisce:

$$  
a^{\frac{p}{q}} = \left(a^{1/q}\right)^p = \sqrt[q]{a^p}  
$$

Questa definizione è **coerente** con:

- $a^p$ per $p$ intero;
    
- $a^{1/q}$ come radice;
    
- $(a^m)^n = a^{mn}$.
    

---

## **3. Dominio: il punto più importante della lezione**

Il dominio **dipende dalla parità di $q$**.

---

### **3.1 Caso $q$ pari**

Se $q$ è **pari**, allora:

$$  
a^{\frac{p}{q}} \text{ è definito solo per } a \ge 0  
$$

Motivo: compare una **radice di indice pari**.

Esempi:

- $x^{1/2} = \sqrt{x}$ → $x \ge 0$
    
- $x^{3/2} = (\sqrt{x})^3$ → $x \ge 0$
    

---

### **3.2 Caso $q$ dispari**

Se $q$ è **dispari**, la radice $\sqrt[q]{a}$ è definita per ogni $a \in \mathbb{R}$.

Tuttavia, **in Analisi 1** si mantiene comunque la convenzione:

> le potenze con esponente razionale si definiscono **principalmente per $a \ge 0$**,  
> così da poterle estendere in modo continuo agli esponenti reali.

Questo evita ambiguità e mantiene coerenza concettuale.

---

## **4. Coerenza con le proprietà delle potenze**

Con questa definizione, **tutte le proprietà fondamentali restano valide**, purché le espressioni abbiano senso.

---

### **4.1 Prodotto**

Se $a \ge 0$, $b \ge 0$:

$$  
a^r \cdot b^r = (ab)^r  
$$

---

### **4.2 Quoziente**

Se $a \ge 0$, $b > 0$:

$$  
\frac{a^r}{b^r} = \left(\frac{a}{b}\right)^r  
$$

---

### **4.3 Potenza di una potenza**

$$  
\left(a^r\right)^s = a^{rs}  
$$

Questa proprietà è il **cuore concettuale** di tutta la teoria delle potenze.

---

## **5. Esempi strutturali (non solo calcolo)**

### **5.1 Riscrittura**

$$  
16^{3/4} = \left(16^{1/4}\right)^3 = 2^3 = 8  
$$

---

### **5.2 Semplificazione**

$$  
x^{5/2} = x^2 \cdot x^{1/2}  
$$

utile per:

- studio del segno;
    
- limiti;
    
- confronti asintotici.
    

---

### **5.3 Forma radicale vs forma esponenziale**

$$  
\sqrt[3]{x^2} = x^{2/3}  
$$

La forma esponenziale è **preferibile in Analisi** perché:

- è più maneggevole nei limiti;
    
- si generalizza agli esponenti reali;
    
- dialoga con logaritmi ed esponenziali.
    

---

## **6. Attenzione: semplificazioni non sempre lecite**

Un errore classico è scrivere:

$$  
\sqrt{x^2} = x  
$$

che è **falso**.

La forma corretta è:

$$  
\sqrt{x^2} = |x|  
$$

Analogamente:

$$  
(x^2)^{1/2} = |x|  
$$

Questo nasce dal fatto che $a^{1/2}$ è definito come **numero non negativo**.

---

## **7. Potenze razionali e segno**

Per $a \ge 0$:

- $a^{p/q} \ge 0$ sempre
    
- vale $a^{p/q} = 0 \iff a = 0$
    

Se compare una variabile:

- il segno dell’espressione è determinato **dal dominio**, non dall’esponente.
    

Questo è cruciale per:

- studio del segno;
    
- disequazioni;
    
- limiti.
    

---

## **8. Collegamento con lo studio del dominio**

Ogni espressione del tipo:

$$  
(x - \alpha)^{p/q}  
$$

con $q$ pari impone:

$$  
x - \alpha \ge 0  
$$

Questo schema tornerà **in modo sistematico** nello studio delle funzioni.

---

## **9. Interpretazione grafica qualitativa**

Le funzioni:

$$  
f(x) = x^{p/q}  
$$

(per $x \ge 0$) sono:

- crescenti;
    
- continue;
    
- con concavità che dipende dal valore dell’esponente.
    

Senza ancora formalizzarlo, stai già vedendo:

- comportamento vicino a 0;
    
- comportamento all’infinito;
    
- differenze tra crescita sublineare e superlineare.
    

---

## **10. Preparazione diretta alla L4**

Questa lezione è l’ultimo tassello prima di:

👉 **Potenze con esponente reale**

Dove:

- l’esponente non è più una frazione;
    
- servirà la continuità;
    
- entreranno in gioco limiti e logaritmi;
    
- le potenze diventeranno vere **funzioni analitiche**.
    

---

## **11. Checklist finale (livello Analisi 1)**

Devi saper fare:

1. Definire $a^{p/q}$ in modo coerente
    
2. Capire il ruolo del denominatore $q$
    
3. Determinare il dominio corretto
    
4. Applicare le proprietà delle potenze **con controllo del dominio**
    
5. Passare tra forma radicale ed esponenziale
    
6. Evitare semplificazioni scorrette ($\sqrt{x^2}$)
    
7. Usare le potenze razionali nello studio di funzioni