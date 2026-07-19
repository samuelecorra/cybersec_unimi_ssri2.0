# **M1 UD1 Lezione 2 - Equazioni e disequazioni di primo grado**

### **1. Introduzione concettuale**

Le **equazioni** e **disequazioni di primo grado** costituiscono il primo vero esempio di **modello matematico lineare**. I principi che rendono leciti i passaggi sono sviluppati in [L0 - Equivalenza, equazioni e sistemi](L0%20-%20Equivalenza,%20equazioni%20e%20sistemi.md); qui li applichiamo al caso lineare.
Non sono solo “calcoli”: rappresentano **vincoli**, **relazioni**, **condizioni di ammissibilità**.

Dal punto di vista dell’ingegneria e dell’analisi:

- un’equazione lineare rappresenta una **condizione di equilibrio esatto**;
    
- una disequazione lineare rappresenta una **condizione di tolleranza**, **sicurezza**, **fattibilità**.
    

Questa lezione va quindi letta come **ponte tra algebra e geometria**, e come base per:

- sistemi lineari,
    
- ottimizzazione vincolata,
    
- analisi dei segni,
    
- studio delle funzioni.
    

---

### **2. Equazioni di primo grado in una variabile**

#### **2.1 Definizione**

Un'equazione lineare in una variabile reale $x$ ha la forma:

$$  
ax + b = 0  
$$

con $a,b\in\mathbb{R}$. È propriamente **di primo grado** soltanto se $a\ne0$; il caso $a=0$ deve essere separato perché l'equazione perde l'incognita.

---

#### **2.2 Risoluzione algebrica**

Se $a\ne0$, l'equazione si risolve isolando l'incognita:

$$  
ax + b = 0  
\quad \Longrightarrow \quad  
ax = -b  
\quad \Longrightarrow \quad  
x = -\frac{b}{a}  
$$

La divisione per $a$ è lecita proprio perché in questo caso $a\ne0$. La classificazione completa è:

$$
\begin{cases}
a\ne0 &\Rightarrow S=\left\{-\dfrac ba\right\} \\
a=0,\ b\ne0 &\Rightarrow S=\varnothing \\
a=0,\ b=0 &\Rightarrow S=\mathbb{R}
\end{cases}
$$

Nel secondo caso si ottiene l'uguaglianza falsa $b=0$; nel terzo l'identità $0=0$.

> ⚠️ Non si può scrivere subito $x=-b/a$: prima bisogna distinguere il caso $a=0$.

#### **2.3 Esempio parametrico**

Consideriamo:

$$
(m-2)x=m^2-4
$$

Se $m\ne2$, possiamo dividere per $m-2$ e fattorizzare il secondo membro:

$$
x=\frac{(m-2)(m+2)}{m-2}=m+2
$$

Se $m=2$, l'equazione originaria diventa $0=0$, quindi ogni $x\in\mathbb{R}$ è soluzione. La formula $x=m+2$ non vale in questo caso, perché è stata ottenuta dividendo per zero.

---

#### **2.3 Interpretazione geometrica**

Consideriamo la funzione associata:

$$  
f(x) = ax + b  
$$

- l’equazione $ax+b=0$ equivale a cercare gli **zeri** della funzione;
    
- geometricamente: il punto in cui la retta interseca l’asse $x$.
    

Se $x_0 = -\frac{b}{a}$, allora il punto di intersezione è:

$$  
(x_0, 0)  
$$

Questa interpretazione è cruciale perché:

- anticipa lo **studio del segno**
    
- anticipa lo **studio delle funzioni**
    
- anticipa i **metodi grafici per problemi più complessi**
    

---

### **3. Equazioni di primo grado in due variabili**

#### **3.1 Forma generale**

Un’equazione di primo grado in due variabili è:

$$  
ax + by + c = 0  
$$

con $(a,b) \neq (0,0)$.

Come visto nella lezione precedente, essa rappresenta una **retta nel piano cartesiano**.

---

#### **3.2 Insieme delle soluzioni**

Una equazione in due variabili **non ha una soluzione unica**, ma **infinitamente molte**:

$$  
{(x,y) \in \mathbb{R}^2 \mid ax + by + c = 0}  
$$

cioè **tutti i punti della retta**.

Questo è un punto concettuale fondamentale:

- 1 equazione + 1 incognita → 1 soluzione
    
- 1 equazione + 2 incognite → infinito di soluzioni
    
- 2 equazioni + 2 incognite → punto (se compatibili)
    

---

### **4. Disequazioni di primo grado in una variabile**

#### **4.1 Definizione**

Una disequazione lineare ha una delle forme:

$$  
ax + b > 0, \quad ax + b \ge 0, \quad ax + b < 0, \quad ax + b \le 0  
$$

con $a,b\in\mathbb{R}$. Se $a=0$, la disequazione è costante e può essere sempre vera oppure impossibile.

---

#### **4.2 Metodo risolutivo**

Si procede come per un’equazione, **prestando attenzione al segno di $a$**.

Caso base:

$$  
ax + b > 0  
$$

Isolando $x$:

- se $a > 0$:
    

$$  
x > -\frac{b}{a}  
$$

- se $a < 0$ (attenzione: il verso cambia):
    

$$  
x < -\frac{b}{a}  
$$

Se $a=0$, non si divide. Per esempio, $0x+3>0$ è vera per ogni reale, mentre $0x-2\ge0$ non ha soluzioni. Nei problemi parametrici il caso in cui il coefficiente di $x$ si annulla va sempre trattato per primo.

---

#### **4.3 Interpretazione sulla retta reale**

La soluzione di una disequazione di primo grado è sempre:

- una **semiretta**
    
- oppure l’intera retta reale
    
- oppure l’insieme vuoto
    

Esempi tipici:

- $x > \alpha$ → semiretta destra
    
- $x < \alpha$ → semiretta sinistra
    
- $x \ge \alpha$, $x \le \alpha$ → estremi inclusi
    

Questo è il primo esempio concreto di **insieme soluzione infinito ma strutturato**.

---

### **5. Disequazioni di primo grado in due variabili**

#### **5.1 Forma generale**

Una disequazione lineare in due variabili è:

$$  
ax + by + c > 0  
$$

(o $\ge, <, \le$).

---

#### **5.2 Interpretazione geometrica: semipiani**

L’equazione associata:

$$  
ax + by + c = 0  
$$

divide il piano in **due semipiani**.

La disequazione seleziona **uno dei due**.

Procedura concettuale:

1. tracci la retta $ax+by+c=0$;
    
2. scegli un punto di prova (tipicamente l’origine $(0,0)$ se non appartiene alla retta);
    
3. verifichi il segno dell’espressione;
    
4. determini quale lato è soluzione.
    

---

#### **5.3 Inclusione o esclusione del bordo**

- $>$ oppure $<$ → **retta esclusa**
    
- $\ge$ oppure $\le$ → **retta inclusa**
    

Questo dettaglio diventerà fondamentale nello studio:

- dei domini di funzioni
    
- delle regioni di integrazione
    
- dei problemi di ottimizzazione vincolata
    

---

### **6. Sistemi di disequazioni lineari**

Un sistema di disequazioni è un’intersezione di semipiani.

Esempio concettuale:

$$
\begin{cases}
a_1x+b_1y+c_1\ge0 \\
a_2x+b_2y+c_2\le0
\end{cases}
$$

Geometricamente:

- ogni disequazione definisce un semipiano;
    
- il sistema definisce la **regione comune**.
    

Questa regione può essere:

- vuota,
    
- limitata,
    
- illimitata.

In una variabile vale la stessa logica: si risolve ogni disequazione e si prende l'**intersezione** degli insiemi soluzione, mai la loro unione, salvo che il testo presenti esplicitamente un'alternativa logica.
    

È il primo esempio di **insieme ammissibile**, concetto chiave in:

- programmazione lineare,
    
- economia,
    
- controllo,
    
- ottimizzazione.
    

---

### **7. Collegamento con lo studio del segno**

Ogni disequazione del tipo:

$$  
ax+b > 0  
$$

equivale a studiare il segno della funzione lineare:

$$  
f(x) = ax+b  
$$

Lo schema mentale corretto è:

- trovare lo zero $x_0 = -\frac{b}{a}$
    
- studiare il segno a sinistra e a destra
    
- dedurre l’insieme soluzione
    

Questo metodo sarà **generalizzato** per:

- polinomi
    
- razionali
    
- funzioni complesse
    

---

### **8. Interpretazione ingegneristica**

In contesti applicativi:

- equazioni → **condizioni ideali**
    
- disequazioni → **vincoli reali**
    

Esempi concettuali:

- stress $\le$ soglia di sicurezza
    
- corrente $\ge$ valore minimo
    
- errore $\le$ tolleranza
    

Matematicamente: **insiemi definiti da disequazioni lineari**.

---

### **9. Checklist finale (da ingegneria)**

Devi saper fare **senza esitazione**:

1. Risolvere equazioni di primo grado
    
2. Interpretare la soluzione come zero di una funzione
    
3. Risolvere disequazioni lineari con cambio di verso
    
4. Rappresentare soluzioni sulla retta reale
    
5. Interpretare disequazioni in due variabili come semipiani
    
6. Capire cosa rappresenta un sistema di disequazioni
    
7. Collegare tutto allo studio del segno e ai vincoli
    

---

### **10. Ponte verso le prossime lezioni**

Questa lezione prepara direttamente:

- **parabole** (equazioni di secondo grado);
    
- **disequazioni di secondo grado**;
    
- **sistemi lineari**;
    
- **domini delle funzioni**;
    
- **limiti e continuità** (insiemi di definizione).
    

Le equazioni e disequazioni lineari sono il **linguaggio minimo** per descrivere regioni, vincoli e comportamenti locali.
