# **M1 UD5 Lezione 4 - Bayes iterativo e aggiornamento della conoscenza**

### **1. Introduzione**

Questa lezione mostra come il **Teorema di Bayes** possa essere applicato **in modo reiterato**, ogni volta che arrivano nuovi dati.  
L’idea fondamentale è che la **posterior di una fase diventa la prior della successiva**, permettendo un aggiornamento continuo della conoscenza.  
Attraverso esempi successivi, vedremo come la probabilità di un’ipotesi si modifichi man mano che si accumulano osservazioni — un processo analogo all’**apprendimento incrementale**.

---

### **2. Richiamo del Teorema di Bayes**

Se abbiamo $n$ ipotesi alternative $H_1, H_2, \dots, H_n$  
con probabilità a priori $P(H_1), P(H_2), \dots, P(H_n)$  
e se ogni ipotesi implica un’informazione $D$ con probabilità $P(D|H_k)$,  
allora, dopo aver osservato che $D$ si è verificato, possiamo aggiornare la conoscenza:

$$  
P(H_k|D) = \frac{P(H_k)P(D|H_k)}{\sum_i P(H_i)P(D|H_i)}  
$$

---

### **3. Bayes come meccanismo iterativo**

Ogni applicazione del Teorema di Bayes produce una **nuova distribuzione di probabilità a posteriori**, che può essere utilizzata come **prior** per il passo successivo.

Questo ciclo di aggiornamento segue lo schema:

$$  
\text{Prior} \xrightarrow[\text{+ Dati (Evidenza)}]{\text{Modello generativo}} \text{Posterior}  
$$

Quando nuovi dati diventano disponibili, il processo riparte, aggiornando ulteriormente le stime.

---

### **4. Esempio intuitivo: tre monete**

Riprendiamo l’esperimento classico con tre monete $A$, $B$, $C$:

- $P(T|A)=1$,
    
- $P(T|B)=1/2$,
    
- $P(T|C)=0$.

Supponiamo di estrarre una moneta a caso e di ottenere:

- Testa al primo lancio,
    
- Testa al secondo,
    
- Testa al terzo, e così via.

Più lunga è la sequenza ininterrotta di Teste, più cresce la nostra convinzione che la moneta estratta sia la **A**.  
Ogni nuovo lancio rappresenta un **nuovo dato**, e ogni volta aggiorniamo la nostra conoscenza.

---

### **5. La probabilità come stato di conoscenza**

La probabilità esprime il **grado di fiducia soggettivo** basato sulle informazioni disponibili.  
Ogni nuovo dato modifica quello stato di conoscenza.  
Per visualizzare questo concetto, consideriamo tre osservatori — **Elena**, **Franco** e **Giulia** — che si passano la moneta e aggiornano progressivamente le loro stime.

---

### **6. Fase di Elena**

Elena ha tre monete: A, B, C, ciascuna con pari probabilità di essere estratta.  
$$  
P_E(A) = P_E(B) = P_E(C) = \tfrac{1}{3}  
$$

---

### **7. Fase di Franco**

Franco riceve la moneta e osserva un primo lancio con risultato **Testa**.  
Applica il Teorema di Bayes:

$$  
P(A|T) = \frac{(1/3)(1)}{1/2} = \tfrac{2}{3}, \quad  
P(B|T) = \frac{(1/3)(1/2)}{1/2} = \tfrac{1}{3}, \quad  
P(C|T) = 0  
$$

Ora Franco comunica a Giulia che la moneta ha probabilità $\tfrac{2}{3}$ di essere A e $\tfrac{1}{3}$ di essere B.  
Per Giulia, queste diventano le **prior** del suo ragionamento.

---

### **8. Fase di Giulia**

Giulia riceve la moneta con:  
$$  
P_F(A)=\tfrac{2}{3}, \quad P_F(B)=\tfrac{1}{3}  
$$

Calcola la probabilità che al **prossimo lancio** esca Testa:  
$$  
P_F(T) = P_F(A)P(T|A) + P_F(B)P(T|B) = \tfrac{2}{3}(1) + \tfrac{1}{3}(\tfrac{1}{2}) = \tfrac{5}{6}  
$$

Ottiene Testa.  
Aggiorna nuovamente:

$$  
P(A|T) = \frac{(2/3)(1)}{5/6} = \tfrac{4}{5}, \quad  
P(B|T) = \tfrac{1}{5}  
$$

---

### **9. Terzo passaggio**

Ora la nuova previsione per il lancio successivo diventa:  
$$  
P_G(T) = \tfrac{4}{5}(1) + \tfrac{1}{5}(\tfrac{1}{2}) = \tfrac{9}{10}  
$$

Le stime successive di $P(A)$ e $P(T)$ evolvono così:

|Passaggio|$P(A)$|$P(T)$|
|---|---|---|
|Elena|$\tfrac{1}{3}$|$\tfrac{1}{2}$|
|Franco|$\tfrac{2}{3}$|$\tfrac{5}{6}$|
|Giulia|$\tfrac{4}{5}$|$\tfrac{9}{10}$|

👉 Si nota una **convergenza progressiva** verso l’ipotesi A e verso probabilità quasi certe di Testa.

---

### **10. Nuova notazione**

In generale, possiamo indicare l’informazione disponibile con simboli come:

- $I_E$ → informazioni di Elena
    
- $I_F$ → informazioni di Franco
    
- $I_G$ → informazioni di Giulia

Così, le probabilità condizionali si scrivono:

$$  
P(A|I_E), \quad P(A|I_F), \quad P(A|I_G)  
$$

Oppure, più sinteticamente:  
$$  
P(A|(*,*)), ; P(A|(T,*)), ; P(A|(T,T))  
$$  
per indicare la sequenza di osservazioni.

---

### **11. Esempio esteso – monete sbilanciate**

Ora consideriamo tre monete con probabilità diverse di Testa:  
$$  
P(T|A)=\tfrac{3}{4}, \quad P(T|B)=\tfrac{1}{2}, \quad P(T|C)=\tfrac{1}{4}  
$$

---

#### **Prima del primo lancio**

$$  
P(A|(*,*)) = P(B|(*,*)) = P(C|(*,*)) = \tfrac{1}{3}  
$$

La probabilità di Testa è:  
$$  
P(T) = \tfrac{1}{3}(\tfrac{3}{4}+\tfrac{1}{2}+\tfrac{1}{4}) = \tfrac{1}{2}  
$$

---

#### **Dopo il primo lancio (esce Testa)**

$$  
P(A|(T,_)) = \frac{(1/3)(3/4)}{1/2} = \tfrac{1}{2}, \quad  
P(B|(T,_)) = \tfrac{1}{3}, \quad  
P(C|(T,*)) = \tfrac{1}{6}  
$$

La previsione aggiornata:  
$$  
P(T|(T,*)) = \tfrac{1}{2}(\tfrac{3}{4}) + \tfrac{1}{3}(\tfrac{1}{2}) + \tfrac{1}{6}(\tfrac{1}{4}) = \tfrac{7}{12}  
$$

---

#### **Dopo il secondo lancio (esce di nuovo Testa)**

$$  
P(A|(T,T)) = \frac{(1/2)(3/4)}{7/12} = \tfrac{9}{14}, \quad  
P(B|(T,T)) = \tfrac{4}{14}, \quad  
P(C|(T,T)) = \tfrac{1}{14}  
$$

Nuova previsione:  
$$  
P(T|(T,T)) = \tfrac{9}{14}(\tfrac{3}{4}) + \tfrac{4}{14}(\tfrac{1}{2}) + \tfrac{1}{14}(\tfrac{1}{4}) = \tfrac{9}{14}  
$$

---

### **12. Confronto fra metodo iterativo e metodo diretto**

Se invece ci avessero fornito subito tutti i dati — due Teste consecutive —  
avremmo potuto calcolare direttamente:

$$  
P((T,T)|A) = (\tfrac{3}{4})^2 = \tfrac{9}{16}, \quad  
P((T,T)|B) = (\tfrac{1}{2})^2 = \tfrac{4}{16}, \quad  
P((T,T)|C) = (\tfrac{1}{4})^2 = \tfrac{1}{16}  
$$

$$  
P((T,T)) = \tfrac{1}{3}(\tfrac{9+4+1}{16}) = \tfrac{14}{48}  
$$

$$  
P(A|(T,T)) = \frac{(1/3)(9/16)}{(1/3)(14/16)} = \tfrac{9}{14}  
$$

➡️ **Lo stesso risultato del metodo iterativo.**

---

### **13. Significato**

Il risultato mostra la **consistenza del metodo bayesiano**:  
otteniamo la stessa posterior sia che elaboriamo i dati progressivamente,  
sia che li elaboriamo tutti in una volta.

Questo riflette l’idea di **apprendimento incrementale coerente**:  
il modello “impara” man mano che riceve dati, ma non si contraddice.

---

### **14. Sintesi concettuale**

|**Concetto**|**Significato / Formula**|
|---|---|
|Bayes iterativo|La posterior diventa prior al passo successivo|
|Probabilità come conoscenza|Ogni nuova informazione aggiorna lo stato di fiducia|
|Coerenza bayesiana|$P(H|
|Esempio 3 monete|Dimostra la consistenza del metodo|
|Apprendimento dinamico|Il modello si “nutre” dei dati e si adatta progressivamente|

---

### **15. Conclusione**

Il **Teorema di Bayes** non è solo un metodo di calcolo,  
ma un **modello cognitivo dell’apprendimento**:  
trasforma i dati in conoscenza aggiornata, in modo coerente e incrementale.

La **posterior** diventa la **prior** del passo successivo,  
realizzando un ciclo continuo di aggiornamento - 
il cuore stesso della **statistica bayesiana moderna**.