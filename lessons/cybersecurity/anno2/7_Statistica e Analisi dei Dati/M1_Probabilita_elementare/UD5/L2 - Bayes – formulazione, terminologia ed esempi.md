# **M1 UD5 Lezione 2 - Il Teorema di Bayes - formulazione, terminologia ed esempi**

### **1. Introduzione**

In questa lezione analizziamo in modo completo la **formulazione generale del Teorema di Bayes**, la sua **nomenclatura formale**, e due esempi emblematici:

- un **canale di comunicazione**,
    
- e il celebre **Sushi Delight problem** di Clifford Pickover.

Il teorema viene presentato come **meccanismo di aggiornamento della conoscenza**: da una probabilità iniziale (_a priori_) si passa a una probabilità aggiornata (_a posteriori_) in base a nuove **evidenze**.

---

### **2. Formulazione generale del Teorema di Bayes**

Supponiamo di avere un insieme di **cause o ipotesi alternative**, $C_1, C_2, \dots, C_n$,  
che possono dare origine a un determinato **effetto** $E$.

- Le probabilità **a priori** delle cause sono:  
    $$  
    P(C_1), P(C_2), \dots, P(C_n)  
    $$
    
- Le probabilità **condizionate** dell’effetto dato ciascuna causa sono:  
    $$  
    P(E|C_1), P(E|C_2), \dots, P(E|C_n)  
    $$


Dopo aver osservato l’effetto $E$, possiamo **aggiornare** la probabilità di ciascuna causa in base alla legge:

$$  
P(C_k|E) = \frac{P(C_k) \cdot P(E|C_k)}{\sum_i P(C_i) \cdot P(E|C_i)}  
$$

Il **denominatore** assicura che la somma totale delle probabilità aggiornate sia 1  
e viene chiamato **fattore di rinormalizzazione**.

---

### **3. Dalle cause agli effetti**

Nella pratica, spesso si parla di **cause ed effetti** per semplicità.  
Si tratta di relazioni **fisiche e logiche** in cui una causa $C_k$ “implica” un effetto $E$.

Esempi tipici:

- La **presenza di fuoco** implica, con alta probabilità, la **presenza di fumo**.
    
- Una **malattia** implica, con una certa probabilità, la **comparsa di un sintomo**.

Ma Bayes può applicarsi anche a **relazioni puramente logiche**, dove non esiste un nesso temporale o fisico, bensì un **aggiornamento della fiducia** nelle ipotesi a fronte di nuovi dati.

---

### **4. Implicazione logica tra ipotesi e dati**

Nel linguaggio generale del teorema:

- le **ipotesi** vengono indicate come $H_1, H_2, \dots, H_n$,
    
- le **informazioni osservate** come $D$.

Ciascuna ipotesi $H_k$ ha una probabilità **a priori** $P(H_k)$,  
che viene aggiornata in una probabilità **a posteriori** $P(H_k|D)$  
dopo aver osservato l’informazione $D$.

$$  
P(H_k|D) = \frac{P(H_k) \cdot P(D|H_k)}{P(D)}  
$$

Da cui, sviluppando sempre $P(D)$, ottengo la formulazione completa ed esaustiva del teorema con la sommatoria:

$$  
P(H_k|D) = \frac{P(H_k) \cdot P(D|H_k)}{\sum_i P(H_i) \cdot P(D|H_i)}  
$$

---

### **5. Terminologia**


![](imgs/Pasted%20image%2020251213111557.png)


| **Simbolo**     | **Nome**                                  | **Significato**                                     |
| --------------- | ----------------------------------------- | --------------------------------------------------- |
| $P(H_k)$        | **Probabilità a priori dell'ipotesi**     | Fiducia iniziale nell’ipotesi $H_k$, prima dei dati |
| $P(H_k \mid D)$ | **Probabilità a posteriori dell'ipotesi** |                                                     |
| $P(D \mid H_k)$ | **Verosimiglianza**                       |                                                     |
| $P(D)$          | **Evidenza o normalizzazione**            | Fattore che garantisce $\sum_k P(H_k \mid D) = 1$   |

> **Nota importante:** probabilità e verosimiglianza non sono sinonimi.
> 
> - Se fissiamo $H$, $P(D|H)$ rappresenta la **probabilità dei dati**.
>     
> - Se fissiamo $D$, la stessa espressione rappresenta la **verosimiglianza dell’ipotesi**.

---

### **6. Bayes come meccanismo di aggiornamento della conoscenza**

Il Teorema di Bayes funziona come un **meccanismo di revisione razionale**:

- parte da una conoscenza iniziale (prior),
    
- incorpora nuovi dati (evidenza),
    
- produce una nuova conoscenza aggiornata (posterior).

$$  
\text{a priori} \xrightarrow[\text{verosimiglianza}]{\text{+ dati}} \text{a posteriori}  
$$

Volendo riscrivere l'intera relazione bayesiana usando solo la nomenclatura in linguaggio naturale, otteniamo:
$$
\text{posterior} \;=\; \frac{\text{prior} \cdot \text{verosimiglianza}}{\text{evidenza}}
$$

È un modello universale per l’apprendimento basato sull’esperienza.

---

### **7. Esempio 1 – Canale di comunicazione**

Supponiamo un canale binario in cui si trasmettono bit ($t=0$ oppure $t=1$)  
con proporzioni $P(t=0)=p$ e $P(t=1)=q=1-p$.

A causa del rumore:

- il bit può essere ricevuto correttamente con probabilità $P(r=0|t=0)$ o $P(r=1|t=1)$,
    
- oppure invertito a causa di errore trasmissivo con $P(r=1|t=0)$ o $P(r=0|t=1)$.

---

#### **7.1. Probabilità diretta**

Calcoliamo la probabilità complessiva di ricevere un “1”.
Ciò si fa applicando sia somma che prodotto, tenendo conto sia di caso giusto che di caso con errore trasmissivo:
$$  
P(r=1) = P(t=1) \cdot P(r=1|t=1) + P(t=0) \cdot P(r=1|t=0)  
$$
E' facilmente ricavabile anche consultando l'albero:

![](imgs/Pasted%20image%2020251213112229.png)

---

#### **7.2. Probabilità inversa**

Ora vogliamo sapere:

> “Ricevuto un 1, qual è la probabilità che sia stato effettivamente trasmesso un 1?”

![](imgs/Pasted%20image%2020251213113227.png)

Applicando Bayes:

$$  
P(t=1|r=1) = \frac{P(t=1)P(r=1|t=1)}{P(r=1)}  
$$
Dove chiaramente il denominatore lo abbiamo appena sviluppato nel passaggio poco più in alto.

---

#### **7.3. Esempio numerico**

Dati:

- $P(t=1)=0.60$ ⇒ $P(t=0)=0.40$
    
- $P(r=1|t=1)=0.95$
    
- $P(r=0|t=0)=0.95$ ⇒ $P(r=1|t=0)=0.05$

Allora:

$$  
P(r=1) = (0.60)(0.95) + (0.40)(0.05) = 0.59  
$$

$$  
P(t=1|r=1) = \frac{(0.60)(0.95)}{0.59} \approx 0.97  
$$

---

#### **7.4. Interpretazione**

- **A priori:** la probabilità che venga trasmesso un 1 è 60%.
    
- **A posteriori:** dopo aver ricevuto un 1, la probabilità che fosse effettivamente un 1 sale al 97%.

Questo esempio mostra chiaramente la potenza del Teorema di Bayes nel **correggere la conoscenza iniziale** alla luce di nuove osservazioni.

---

### **8. Esempio 2 – Il problema del Sushi Delight**

Un pesce è posto in un vaso opaco.  
Ha la stessa probabilità di essere un **piranha (P)** o un **pesce rosso (R)**.  
Un amante del sushi getta un altro **piranha** nella vasca.  
Poi, **uno dei due pesci viene estratto a caso**, e si scopre che è un **piranha**.

Domanda:

> Qual è la probabilità che anche il pesce originario fosse un piranha?

---

#### **8.1. Costruzione dell’albero**

![](imgs/Pasted%20image%2020251213113829.png)

1. Primo pesce:
    
    - $P(P) = \frac{1}{2}$, $P(R) = \frac{1}{2}$
    
2. Dopo l’aggiunta del secondo piranha, nel vaso ci sono:
    
    - caso 1: (P, P)
        
    - caso 2: (R, P)
    
3. Pesco un pesce a caso e risulta **piranha**.

---

#### **8.2. Calcolo delle probabilità**

- **Caso/Cammino 1 (P, P), si estrae per forza un Piranha:** probabilità = $\frac{1}{2} \cdot 1 \cdot 1 = \frac{1}{2}$
    
- **Caso/Cammino 2 (R, P), estratto Piranha:** probabilità = $\frac{1}{2} \cdot 1 \cdot \frac{1}{2} = \frac{1}{4}$
- **Caso/Cammino 3 (R, P), estratto Pesce Rosso:** probabilità = $\frac{1}{2} \cdot 1 \cdot \frac{1}{2} = \frac{1}{4}$

MA ATTENZIONE: IL CAMMINO 3 VA SCARTATO, IN QUANTO L'EVIDENZA DICE CHE ABBIAMO ESTRATTO UN PIRANHA.

Con questo divieto, non possiamo non intervenire: infatti bisogna procedere con una rinormalizzazione, in quanto l'evidenza è la sommatoria di tutti gli scenari in cui si verifica il Dato, ovvero tutti gli scenari di Piranha estratto, ovvero i soli casi compatibili con l’osservazione.

Vi propongo una scrittura totalmente in linguaggio naturale, a partire da:

$$
\text{posterior} \;=\; \frac{\text{prior} \cdot \text{verosimiglianza}}{\text{evidenza}}
$$

Iniziamo a tradurre:

$$  
P(\text{L'originale era un Piranha} \mid \text{pescato Piranha}) = \frac{P(\text{L'originale era un Piranha}) \cdot P(\text{pescato piranha | L'originale era un Piranha})}{P(\text{pescato Piranha})} 
$$

Ora possiamo aiutarci traducendo il tutto in termini dei vari cammini

$$  
P({cammino1} \mid \text{pescato P}) = \frac{P(cammino1)}{P(cammino1) + P(cammino2)} = \frac{1/2 \cdot 1 \cdot 1}{(1/2 \cdot 1 \cdot 1) + (\frac{1}{2} \cdot 1 \cdot \frac{1}{2}) } = \frac{1/2}{1/2 + 1/4} =   
$$

E semplificando, infine:

$$
\frac{1/2}{3/4} = \frac{2}{3}
$$

---

### **9. Interpretazione del Sushi Delight**

Dopo aver osservato che il pesce estratto è un piranha:

- c’è il **66%** di probabilità che anche l’altro fosse un piranha,
    
- e solo il **33%** che fosse un pesce rosso.

Questo esempio mostra come Bayes consenta di **rivedere la conoscenza a priori** alla luce di un’evidenza osservata.

---

### **10. Nota storica**

Il teorema fu formulato da **Thomas Bayes (1701–1761)** e pubblicato postumo nel 1763,  
nell’articolo _“Essay Towards Solving a Problem in the Doctrine of Chances”_  
sulle _Philosophical Transactions of the Royal Society of London_.

Oggi rappresenta il fondamento della **statistica bayesiana** e dei moderni **modelli di apprendimento automatico**.

![](imgs/Pasted%20image%2020251213115008.png)

---
### **11. Conclusione**

Il **Teorema di Bayes** fornisce un modello logico per l’apprendimento dall’esperienza.  
Partendo da ipotesi iniziali e osservazioni empiriche, consente di calcolare in modo rigoroso quanto un’evidenza **modifichi la nostra fiducia** nelle varie spiegazioni possibili.  
È uno strumento universale per ogni forma di **inferenza razionale** - dal canale di comunicazione alla vasca di pesci.