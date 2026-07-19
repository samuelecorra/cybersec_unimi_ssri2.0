# **M1 UD2 Lezione 4 - Potenze con esponente reale**

### **1. Introduzione: perché serve l’esponente reale**

Con gli esponenti:

- interi,
    
- reciproci,
    
- razionali,
    

abbiamo coperto solo **$\mathbb{Q}$**, che è un insieme _denso_ ma **non completo**.  
In Analisi 1, però, lavoriamo su **$\mathbb{R}$**, e quindi la domanda naturale è:

> Che significato ha $a^x$ quando $x$ è un numero reale qualsiasi?

Questa lezione risponde a questa domanda **senza ancora usare esplicitamente i logaritmi**, ma preparando esattamente il terreno per essi.

---

### **2. Idea costruttiva, rinviata ai moduli successivi**

Il principio chiave è il seguente:

> Le potenze con esponente reale vengono caratterizzate come la **naturale estensione continua** delle potenze con esponente razionale.

Questo significa:

- se $x_n \in \mathbb{Q}$ e $x_n \to x \in \mathbb{R}$,
    
- allora vogliamo che $a^{x_n} \to a^x$.
    

Questa è un'anticipazione analitica: successioni, limite e continuità non vengono qui usati come risultati già dimostrati. La costruzione completa sarà possibile soltanto dopo aver sviluppato tali strumenti.

---

### **3. Dominio della base: perché si richiede $a>0$**

Da ora in poi fissiamo:

$$  
a > 0  
$$

Questo vincolo è **fondamentale**, non opzionale.

#### **3.1 Perché $a>0$?**

1. Se $a=0$, $0^x$ non è definito per $x \le 0$.
    
2. Se $a<0$, le potenze razionali considerate singolarmente sono:
    
    - a volte definite,
        
    - a volte no,
        
    - incompatibili con una funzione reale continua definita per ogni esponente reale.
        
3. La funzione $a^x$ deve essere:
    
    - definita per ogni $x \in \mathbb{R}$,
        
    - continua,
        
    - monotona.
        

Una funzione reale $x\mapsto a^x$ definita per ogni $x\in\mathbb{R}$ e dotata delle usuali leggi esponenziali si costruisce quindi per $a>0$. Ciò non nega l'esistenza di singole potenze con base nulla o negativa ed esponente intero o razionale ammissibile, già trattate nelle lezioni precedenti.

---

### **4. Definizione operativa (idea, non ancora formula)**

Per $a>0$ e $x\in\mathbb{R}$, in questo modulo assumiamo l'esponenziale reale come funzione elementare che estende le potenze razionali in modo continuo. L'idea costruttiva è la seguente:

> l’unico valore reale che rende la funzione $x \mapsto a^x$ continua  
> e coincidente con le potenze razionali quando $x \in \mathbb{Q}$.

Se $(q_n)$ è una successione di razionali che tende a $x$, si pone, dopo aver dimostrato che il valore non dipende dalla successione scelta:

$$
a^x=\lim_{n\to+\infty}a^{q_n}
$$

La giustificazione completa richiede completezza dei reali, successioni e limiti e verrà fornita nei moduli dedicati. Qui la caratterizzazione è usata come proprietà operativa dichiarata, non come dimostrazione già conclusa.

Questa caratterizzazione:

- **non è computazionale**,
    
- è **strutturale**,
    
- è tipica dell’Analisi.
    

---

### **5. Proprietà fondamentali delle potenze reali**

Con questa definizione, **tutte le proprietà delle potenze restano valide**.

Per $a>0$, $x,y \in \mathbb{R}$:

---

#### **5.1 Prodotto**

$$  
a^x \cdot a^y = a^{x+y}  
$$

---

#### **5.2 Quoziente**

$$  
\frac{a^x}{a^y} = a^{x-y}  
$$

---

#### **5.3 Potenza di una potenza**

$$  
\left(a^x\right)^y = a^{xy}  
$$

---

#### **5.4 Valori notevoli**

$$  
a^0 = 1,  
\quad  
a^1 = a  
$$

Queste proprietà **non sono più dimostrate con prodotti finiti**. La loro dimostrazione, una volta costruita l'estensione reale, utilizza densità dei razionali, completezza e limiti; in questa lezione sono assunte come proprietà operative dichiarate.

---

### **6. Segno e monotonia**

Per $a>0$:

$$  
a^x > 0 \quad \forall x \in \mathbb{R}  
$$

Inoltre:

- se $a>1$, la funzione $a^x$ è **strettamente crescente**;
    
- se $0<a<1$, la funzione $a^x$ è **strettamente decrescente**.
    

Questa distinzione è cruciale per:

- confronti;
    
- limiti;
    
- studio delle funzioni esponenziali.
    

---

### **7. Continuità della funzione $a^x$**

La funzione:

$$  
f(x) = a^x  
$$

è:

- definita su tutto $\mathbb{R}$;
    
- **continua ovunque**;
    
- senza salti né interruzioni;
    
- sempre positiva.
    

Questa è una delle **funzioni fondamentali dell’Analisi**.

---

### **8. Comportamento agli estremi (intuizione analitica)**

Se $a>1$:

$$  
\lim_{x \to +\infty} a^x = +\infty,  
\quad  
\lim_{x \to -\infty} a^x = 0  
$$

Se $0<a<1$:

$$  
\lim_{x \to +\infty} a^x = 0,  
\quad  
\lim_{x \to -\infty} a^x = +\infty  
$$

Questi risultati saranno **dimostrati rigorosamente** nel modulo sui limiti, ma qui è essenziale **capirne il senso**.

---

### **9. Confronto con le potenze polinomiali**

Una delle idee più importanti (che tornerà in M8):

- le potenze esponenziali **vincono** su ogni potenza polinomiale;
    
- $a^x$ cresce (o decresce) **molto più velocemente** di $x^n$.
    

Questo è il seme del **confronto asintotico**.

---

### **10. Perché non si ottiene una funzione esponenziale reale globale per $a \le 0$**

Riepilogo concettuale:

- $a=0$: l'espressione non è definibile per ogni esponente reale, in particolare per quelli negativi;
    
- $a<0$: non esiste un'estensione reale continua definita per ogni $x\in\mathbb{R}$ e coerente con tutte le usuali leggi delle potenze;
    
- $a>0$: funzione ben definita, continua, monotona
    

L’Analisi sceglie **struttura e coerenza**, non casi speciali.

---

### **11. Anticipazione naturale: il logaritmo**

Da questa lezione nasce spontaneamente una nuova domanda:

> Se $a^x$ è una funzione continua e monotona, esiste una funzione inversa?

La risposta è **sì** ed è il **logaritmo**, che verrà studiato più avanti.

Quindi:

- potenze reali → esponenziali
    
- esponenziali → logaritmi
    
- logaritmi → limiti, derivate, integrali
    

---

### **12. Checklist finale (cerniera dell’Analisi)**

Devi saper:

1. Capire perché serve l’esponente reale
    
2. Sapere che la funzione reale $x\mapsto a^x$ definita per ogni esponente reale richiede $a>0$
    
3. Usare tutte le proprietà delle potenze
    
4. Capire la monotonia in base a $a$
    
5. Capire il comportamento agli estremi
    
6. Collegare potenze, continuità e limiti
    
7. Vedere $a^x$ come **funzione fondamentale**
    

---

### **13. Chiusura di UD2**

Con questa lezione:

- **UD2 (Potenze)** è completa;
    
- hai costruito una base solida per:
    
    - logaritmi,
        
    - limiti,
        
    - esponenziali,
        
    - confronti asintotici.
