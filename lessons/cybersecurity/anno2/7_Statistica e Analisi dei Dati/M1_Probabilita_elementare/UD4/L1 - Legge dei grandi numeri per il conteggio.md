# **M1 UD4 Lezione 1 - Legge dei grandi numeri per il conteggio**

### **1. Introduzione**

La **Legge dei grandi numeri** descrive il comportamento medio di un esperimento aleatorio ripetuto molte volte.  
Essa afferma che, al crescere del numero di prove, la **frequenza empirica** di un evento tende alla sua **probabilità teorica**.  
In questa lezione analizziamo la **prima formulazione** della legge, quella che lega **frequenze e probabilità**, e vedremo come questa relazione giustifica la possibilità di stimare probabilità anche sconosciute mediante **simulazione Monte Carlo**.

---

### **2. Due formulazioni della Legge dei grandi numeri**

La legge può essere espressa in due modi:

1. **Formulazione semplice:** collega la frequenza empirica alla probabilità.
    
2. **Formulazione generale:** collega la media aritmetica al valore atteso (che studieremo nella prossima lezione).

La prima può essere vista come **caso particolare** della seconda.

---

### **3. Frequenza empirica**

Supponiamo di lanciare una moneta $n$ volte.  
Contiamo:

- $k$ = numero di volte in cui esce **Testa**,
    
- $n$ = numero totale di lanci.

Definiamo la **frazione di conteggio** (o **frequenza empirica**) come:

$$  
f = \frac{k}{n}  
$$

Essa rappresenta la proporzione di successi osservati rispetto al numero totale di prove.

---

### **4. Enunciato della legge dei grandi numeri (I formulazione)**

La **Legge dei grandi numeri per il conteggio** afferma che:

> Al tendere di $n$ all’infinito, la frequenza empirica $f$ di un evento tende alla sua probabilità $p$.

Formalmente:

$$  
\lim_{n \to \infty} f = p  
$$

In altri termini, ripetendo un esperimento un numero sempre maggiore di volte, la proporzione degli esiti favorevoli si stabilizza intorno al valore della probabilità teorica.

---

### **5. Esempio – Lancio di una moneta bilanciata**

Per una moneta equa:

- eventi possibili: $T$ (Testa) e $C$ (Croce),
    
- probabilità teorica di Testa: $p = \frac{1}{2}$.

Sperimentalmente, al crescere del numero di lanci:

$$  
\frac{k}{n} \approx \frac{1}{2}  
$$


![](imgs/Pasted%20image%2020251216164635.png)

Vediamo con 100:

![](imgs/Pasted%20image%2020251216164657.png)

Già con $n = 100$, il numero di Teste si aggira intorno a 50;  
con $n = 10,000$, si stabilizza vicino a 5 000, con fluttuazioni sempre più piccole in proporzione.

---

### **6. Osservazione: il termine “circa”**

È importante notare che il termine **“circa”** è essenziale:  
la proporzione $k/n$ non sarà _mai esattamente_ uguale a $p$, ma solo **approssimata**.  
La differenza relativa (la fluttuazione) diminuisce man mano che $n$ cresce:

- $n = 100 \Rightarrow$ circa 50 Teste
    
- $n = 1,000 \Rightarrow$ circa 500 Teste
    
- $n = 10,000 \Rightarrow$ circa 5 000 Teste


![](imgs/Pasted%20image%2020251216164743.png)

Solo con un numero **infinito** di prove l’uguaglianza sarebbe perfetta.

---

### **7. Forma numerica**

Se la probabilità teorica di un evento è $p$, il **numero atteso di occorrenze** su $n$ prove è:

$$  
k = p \cdot n  
$$

---

### **8. Significato concettuale**

La Legge dei grandi numeri esprime una verità fondamentale:

> Quando il numero di osservazioni è sufficientemente grande,  
> l’indeterminazione tipica dei fenomeni casuali **si annulla**,  
> e il comportamento medio diventa **deterministico**.

È grazie a questo principio che la probabilità diventa uno strumento predittivo e non solo descrittivo.

---

### **9. Esempio pratico – Le assicurazioni**

Le compagnie assicurative sfruttano la Legge dei grandi numeri per calcolare i premi.  
Supponiamo che:

- la probabilità di un incidente per un singolo automobilista sia $p = 1/1000$,
    
- e che siano assicurati $n = 1,000,000$ automobilisti.

Allora il numero atteso di incidenti è:

$$  
k = p n = 1000  
$$

L’assicurazione sa che in media si verificheranno circa 1 000 incidenti all’anno e può quindi **distribuire il rischio** su tutti gli assicurati.  
In questo modo trasforma un evento incerto a livello individuale in un evento **quasi certo** a livello collettivo.

---

### **10. La simulazione Monte Carlo**

La **simulazione Monte Carlo** è un metodo che sfrutta proprio la Legge dei grandi numeri per **stimare una probabilità ignota** mediante esperimenti casuali.

Se $f = k/n$ tende a $p$ quando $n$ è grande,  
vale anche il **converso**:  
$p$ può essere stimato con buona approssimazione da $f$,  
purché il numero di prove sia sufficientemente elevato.

Questo principio consente di stimare probabilità, aree o valori attesi anche quando il calcolo analitico è impossibile.

---

### **11. Esempio – Calcolo di π con la simulazione Monte Carlo**

Si consideri un **cerchio inscritto in un quadrato** di lato 1.

![](imgs/Pasted%20image%2020251216164958.png)

La probabilità che un punto generato casualmente nel quadrato **cada dentro il cerchio** è il rapporto delle aree, da cui discende:

$$  
p = \frac{\pi}{4}  
$$

Non conoscendo $\pi$, possiamo stimarlo empiricamente:

![](imgs/Pasted%20image%2020251216165130.png)

1. Generiamo $n$ punti casuali $(x, y)$ nel quadrato $[0,1] \times [0,1]$.
    
2. Contiamo $k$, il numero di punti che soddisfano $x^2 + y^2 < 1$.
    
3. La frequenza empirica $f = k/n$ approssima $p$.
    
4. Da ciò otteniamo l’approssimazione, isolando ovviamente pigreco:

$$  
\pi \approx 4 \frac{k}{n}  
$$

Aumentando $n$, l’approssimazione di $\pi$ migliora.

![](imgs/Pasted%20image%2020251216165154.png)


![](imgs/Pasted%20image%2020251216165217.png)

---

### **12. Regola pratica sull’accuratezza**

Esperimenti e simulazioni mostrano una relazione empirica tra **numero di prove** e **precisione**:

|Numero di prove $n$|Accuratezza tipica di $f$ rispetto a $p$|
|---|---|
|$10^2$ (100)|1ª cifra decimale corretta|
|$10^4$ (10 000)|2ª cifra decimale corretta|
|$10^6$ (1 000 000)|3ª cifra decimale corretta|

In generale, **ogni due ordini di grandezza in più di $n$** fanno guadagnare **una cifra decimale** di precisione. Lasciamo però aperto il vero problema, ovvero quello di stabilire quale numero $n$ di ripetizioni occorra effettivamente per ottenere l'accuratezza desiderata nella stima della probabilità.
Ritorneremo in futuro sull'argomento delle fluttuazioni.

---

### **13. Note storiche**

- Il termine _Monte Carlo_ fu coniato negli anni ’40, durante il **Progetto Manhattan**, da **John von Neumann**, **Enrico Fermi** e **Stanislaw Ulam**, alludendo al celebre casinò.
    
- L’idea che la frequenza relativa tenda alla probabilità teorica risale invece a **Jacob Bernoulli (1654–1705)**, che formulò la **definizione frequentista di probabilità**:
    
    > “La probabilità di un evento è il limite della frequenza relativa dei casi favorevoli su un numero infinito di prove.”

---

### **14. Sintesi concettuale**

|**Concetto**|**Formula**|
|---|---|
|Frequenza empirica|$f = \dfrac{k}{n}$|
|Legge dei grandi numeri (I)|$\lim_{n \to \infty} f = p$|
|Numero atteso di occorrenze|$k = p n$|
|Simulazione Monte Carlo|$\pi \approx 4 \dfrac{k}{n}$|
|Accuratezza empirica|ogni $10^2$ prove → +1 cifra decimale|

---

### **15. Conclusione**

La **Legge dei grandi numeri per il conteggio** stabilisce il legame tra mondo teorico e sperimentale della probabilità.  
Essa giustifica la possibilità di stimare eventi aleatori mediante simulazioni, fornendo il fondamento concettuale dell’intera **statistica sperimentale** e dei metodi Monte Carlo.