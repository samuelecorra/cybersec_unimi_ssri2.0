## **Lezione 2: Limiti per $x$ tendente a infinito**

### **1. Che cosa significa “limite per $x$ tendente a più infinito”**

In questa seconda lezione della nostra introduzione al calcolo dei limiti, ci occupiamo dei **limiti per $x$ tendente a più infinito**.

La prima cosa da chiarire è **che tipo di domanda stiamo facendo** quando scriviamo:

$$

\lim_{x \to +\infty} f(x)

$$

Questa scrittura non chiede di sostituire un numero al posto di $x$, perché **$+\infty$ non è un numero**.

La domanda è invece:

> Che cosa succede ai valori della funzione quando la $x$ diventa sempre più grande?

In termini grafici, ci stiamo chiedendo **che cosa fanno le ordinate (le $y$)** dei punti del grafico **mentre ci spostiamo sempre più a destra sull’asse $x$**.

---
### **2. Primo scenario: la funzione cresce senza limite**

Guardando il grafico di una funzione qualsiasi, fare il limite per $x \to +\infty$ significa osservare cosa succede alle $y$ **mano a mano che $x$ cresce**.

Nel primo caso possibile, accade che:

- più $x$ diventa grande,
    
- più le $y$ diventano grandi.

![](imgs/Pasted%20image%2020251218110157.png)

Detto in modo informale:

> a $x$ grandissime corrispondono $y$ grandissime.

Quando questo succede, si dice che:

$$

\lim_{x \to +\infty} f(x) = +\infty

$$

---
### **3. Esempi tipici di limite $+\infty$**

Un esempio classico è una **parabola con concavità verso l’alto**, come $y = x^2$.

Se facciamo crescere $x$:

- le $y$ crescono sempre di più,
    
- senza mai fermarsi.

![](imgs/Pasted%20image%2020251218110257.png)

In generale, **molte funzioni che da un certo punto in poi continuano a crescere** hanno limite $+\infty$ per $x \to +\infty$.

---
### **4. Crescita non monotona: il punto chiave**

Attenzione però: **non è necessario che la funzione cresca sempre**.

Esistono funzioni che:

- crescono,
    
- poi decrescono,
    
- poi crescono di nuovo,
    
- e così via, anche infinite volte,

![](imgs/Pasted%20image%2020251218110340.png)

ma che hanno comunque la proprietà fondamentale che **per $x$ molto grandi le $y$ diventano molto grandi**.

Quindi il requisito non è la crescita “ordinata”, ma il comportamento globale per $x$ grandi.

---
### **5. L’idea fondamentale (anticipazione della definizione formale)**

La condizione chiave è la seguente:  

Per **ogni quota fissata $M$ sull’asse $y$**, anche molto grande,

deve esistere un valore $x_0$ tale che:

![](imgs/Pasted%20image%2020251218110413.png)  

$$

x > x_0 \Rightarrow f(x) > M

$$

In altre parole:

- qualunque altezza $M$ io scelga,
    
- prima o poi, andando abbastanza a destra,
    
- la funzione rimane **sempre sopra** quella quota.

Questo concetto verrà formalizzato più avanti, ma **l’idea intuitiva è già completa**.

---
### **6. Secondo scenario: la funzione scende senza limite**

Passiamo ora al caso simmetrico.

Esistono funzioni per cui:

- quando $x$ diventa molto grande,
    
- le $y$ diventano sempre più piccole,
    
- cioè sempre più negative. Sono quelle verdi:

![](imgs/Pasted%20image%2020251218110520.png)

In modo informale:

> a $x$ grandissime corrispondono $y$ molto piccole.

In questo caso si scrive:
$$

\lim_{x \to +\infty} f(x) = -\infty

$$

---
### **7. Anche qui: non serve decrescere “bene”**

Anche in questo caso:

- molte funzioni che hanno limite $-\infty$ sono **decrescenti da un certo punto in poi**,
    
- ma non è un requisito necessario.

Esistono funzioni che:

- oscillano,
    
- cambiano andamento infinite volte,

ma che comunque, per $x$ molto grandi, **scendono sempre più in basso**.

---

### **8. Condizione intuitiva per il limite $-\infty$**

Il criterio intuitivo è simmetrico a quello visto prima.

Per **ogni quota $M$ sull’asse $y$**, anche molto piccola,

deve esistere un valore $x_0$ tale che:
$$

x > x_0 \Rightarrow f(x) < M

$$

  ![](imgs/Pasted%20image%2020251218110640.png)

Cioè:

- fissata qualunque altezza $M$ (anche molto negativa),
    
- da un certo punto in poi la funzione resta **sempre sotto** quella quota.

Se questo vale per qualunque $M$, allora il limite è $-\infty$.

---
### **9. Terzo scenario: la funzione si avvicina a un valore finito**

C’è poi un terzo comportamento molto importante.

Può accadere che:

- facendo crescere $x$,
    
- le $y$ **non esplodano** né verso $+\infty$ né verso $-\infty$,
    
- ma si avvicinino sempre di più a un valore preciso $L$.

In questo caso si scrive:

$$

\lim_{x \to +\infty} f(x) = L

$$

---
### **10. Come appare questo caso sul grafico**

Dal punto di vista grafico, possono succedere due cose:

1. Il grafico **si “sdraia”** sempre di più all’altezza $y = L$
    
2. Il grafico **oscilla attorno a $L$**, ma con oscillazioni sempre più piccole

![](imgs/Pasted%20image%2020251218110742.png)

In entrambi i casi, l’importante è che **le $y$ si avvicinino a $L$**.

Il valore $L$ può essere:

- positivo,
    
- negativo,
    
- oppure anche $0$.

---
### **11. Caso particolare: limite uguale a zero**

Quando $L = 0$, significa che:

- per $x$ molto grandi,
    
- il grafico si avvicina sempre di più all’asse delle ascisse.

Questo avvicinamento può avvenire:

- dall’alto,
    
- dal basso,
    
- oppure oscillando attorno all’asse $x$.

---
### **12. Quarto scenario: il limite non esiste**

Se la funzione **non rientra in nessuno dei casi precedenti**, allora diciamo che:

$$

\lim_{x \to +\infty} f(x) \quad \text{non esiste}

$$

![](imgs/Pasted%20image%2020251218110835.png)

Un esempio tipico è dato da funzioni come:

- $\sin x$,
    
- $\cos x$.
  
Queste funzioni:

- oscillano tra $-1$ e $1$,
    
- ma **le oscillazioni non si smorzano**.  

Non esiste quindi un valore verso cui la funzione tende.

---
### **13. Cosa possiamo dire in questi casi**

Per funzioni come seno e coseno possiamo dire solo che:

- i valori della funzione restano compresi in un intervallo,
    
- ma **non convergono** a un numero preciso.

E questo è il motivo per cui il limite **non esiste**.

---
### **14. Limiti per $x \to -\infty$**

Tutto ciò che abbiamo detto per $x \to +\infty$ vale **in modo perfettamente simmetrico** per:

$$

\lim_{x \to -\infty} f(x)

$$

L’unica differenza è che:

- invece di muoverci verso destra sull’asse $x$,
    
- ci muoviamo sempre più **a sinistra** = x sempre più piccola!

![](imgs/Pasted%20image%2020251218110931.png)

Gli scenari possibili sono **gli stessi quattro**:

1. limite $+\infty$
    
2. limite $-\infty$
    
3. limite finito $L$
    
4. limite non esistente

---
### **15. Conclusione dell’introduzione ai limiti**

Con questa lezione si chiude la nostra **introduzione intuitiva al concetto di limite**.

Per ora è fondamentale che:

- tu abbia capito **che cosa significa “fare un limite”**,
    
- tu sappia **interpretarlo graficamente**,
    
- sia chiaro cosa succede:
    
    - quando $x$ tende a un numero,
        
    - quando $x$ tende a $\pm\infty$.

Le **definizioni formali $\varepsilon$–$\delta$** arriveranno più avanti.