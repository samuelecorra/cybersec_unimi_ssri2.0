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

### **2. Definizione di potenza con esponente razionale**

Sia:

$$
r=\frac pq\in\mathbb{Q},\qquad q\ge1,\qquad \gcd(p,q)=1
$$

La frazione deve essere ridotta ai minimi termini. Per $a>0$ si definisce:

$$
a^{p/q}=\left(\sqrt[q]{a}\right)^p=\sqrt[q]{a^p}
$$

Se $p<0$, si richiede $a\ne0$ e si usa il reciproco. La base positiva garantisce che rappresentazioni equivalenti dello stesso numero razionale diano lo stesso risultato e che le leggi delle potenze siano coerenti.

---

### **3. Dominio: il punto più importante della lezione**

Per basi non positive occorre distinguere con precisione.

---

#### **3.1 Caso $q$ pari**

Se $q$ è **pari**, una base negativa non è ammessa nei reali:

$$
a^{p/q}\text{ è reale soltanto per }a\ge0
$$

Motivo: compare una **radice di indice pari**.

Esempi:

- $x^{1/2} = \sqrt{x}$ → $x \ge 0$
    
- $x^{3/2} = (\sqrt{x})^3$ → $x \ge 0$
    

---

#### **3.2 Caso $q$ dispari**

Se $q$ è **dispari**, la definizione radicale è reale anche per $a<0$. Per esempio:

$$
(-8)^{2/3}=\left(\sqrt[3]{-8}\right)^2=4
$$

La base $a=0$ è ammessa soltanto per $p/q>0$. Infatti $0^0$ non è definito in questo contesto e un esponente negativo richiederebbe il reciproco di zero.

> ⚠️ La riduzione di $p/q$ precede l'analisi del dominio. La stessa frazione razionale non deve produrre definizioni diverse a seconda di come è scritta.

---

### **4. Coerenza con le proprietà delle potenze**

Con questa definizione, le proprietà fondamentali restano valide quando tutte le espressioni coinvolte sono definite. Per evitare ambiguità, le formule generali di questa sezione sono enunciate per basi positive.

---

#### **4.1 Prodotto**

Se $a>0$, $b>0$:

$$  
a^r \cdot b^r = (ab)^r  
$$

---

#### **4.2 Quoziente**

Se $a>0$, $b>0$:

$$  
\frac{a^r}{b^r} = \left(\frac{a}{b}\right)^r  
$$

---

#### **4.3 Potenza di una potenza**

$$
\left(a^r\right)^s=a^{rs},\qquad a>0
$$

Questa proprietà è il **cuore concettuale** di tutta la teoria delle potenze.

---

### **5. Esempi strutturali (non solo calcolo)**

#### **5.1 Riscrittura**

$$  
16^{3/4} = \left(16^{1/4}\right)^3 = 2^3 = 8  
$$

---

#### **5.2 Semplificazione**

$$  
x^{5/2}=x^2\cdot x^{1/2},\qquad x\ge0
$$

utile per:

- studio del segno;
    
- limiti;
    
- confronti asintotici.
    

---

#### **5.3 Forma radicale vs forma esponenziale**

$$  
\sqrt[3]{x^2} = x^{2/3}  
$$

La forma esponenziale è spesso più maneggevole in Analisi, soprattutto per basi positive, perché:

- è più maneggevole nei limiti;
    
- si generalizza agli esponenti reali;
    
- dialoga con logaritmi ed esponenziali.
    

---

### **6. Attenzione: semplificazioni non sempre lecite**

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

### **7. Potenze razionali e segno**

Per $a>0$:

- $a^{p/q}>0$ per ogni esponente razionale.

Se $a=0$ e l'esponente è positivo, il valore è $0$. Per una base negativa ammessa, il segno dipende dal numeratore ridotto $p$: è positivo se $p$ è pari e negativo se $p$ è dispari.
    

Questo è cruciale per:

- studio del segno;
    
- disequazioni;
    
- limiti.
    

---

### **8. Collegamento con lo studio del dominio**

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

### **9. Interpretazione grafica qualitativa**

Per $r\in\mathbb{Q}$, le funzioni:

$$  
f(x)=x^r
$$

(considerate dapprima per $x>0$) sono crescenti se $r>0$, decrescenti se $r<0$ e costanti se $r=0$. Il punto $x=0$ appartiene al dominio nel caso $r>0$, ma non nel caso $r<0$. La possibilità di estendere il dominio a valori negativi dipende dalla forma ridotta dell'esponente, come discusso nella sezione 3.

La continuità e la concavità dipendono dal dominio e dal valore dell'esponente; verranno giustificate nei moduli sulle funzioni, sui limiti e sulle derivate.

Senza ancora formalizzarlo, stai già vedendo:

- comportamento vicino a 0;
    
- comportamento all’infinito;
    
- differenze tra crescita sublineare e superlineare.
    

---

### **10. Preparazione diretta alla L4**

Questa lezione è l’ultimo tassello prima di:

👉 **Potenze con esponente reale**

Dove:

- l’esponente non è più una frazione;
    
- servirà la continuità;
    
- entreranno in gioco limiti e logaritmi;
    
- le potenze diventeranno vere **funzioni analitiche**.
    

---

### **11. Checklist finale (livello Analisi 1)**

Devi saper fare:

1. Definire $a^{p/q}$ in modo coerente
    
2. Capire il ruolo del denominatore $q$
    
3. Determinare il dominio corretto
    
4. Applicare le proprietà delle potenze **con controllo del dominio**
    
5. Passare tra forma radicale ed esponenziale
    
6. Evitare semplificazioni scorrette ($\sqrt{x^2}$)
    
7. Usare le potenze razionali nello studio di funzioni
