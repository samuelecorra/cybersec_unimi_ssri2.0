# **M1 UD1 Lezione 1 - Le rette**

### **1. Introduzione**

Una **retta** nel piano cartesiano è l’insieme dei punti $(x,y)$ che soddisfano un’equazione **lineare** in $x$ e $y$.  
È uno degli oggetti più importanti dell’Analisi perché:

- descrive relazioni di **proporzionalità affine**;
    
- è la base per parlare di **approssimazione locale** (tangente, linearizzazione);
    
- compare ovunque in geometria analitica, fisica, ingegneria (caratteristiche lineari, modelli locali, controllo, fitting).
    

L’obiettivo qui è dominarla in modo **da studente di ingegneria**: forme, significato dei parametri, trasformazioni tra forme, casi limite (verticale), parallelismo, perpendicolarità, distanze, proiezioni, angoli.

---

### **2. Cos’è una retta: idea geometrica e algebra**

Una retta nel piano è un oggetto 1-dimensionale: basta **un parametro reale** per descriverne tutti i punti.

Un modo “ingegneristico” di vederla è:

- scegli un punto $P_0=(x_0,y_0)$ sulla retta;
    
- scegli un **vettore direzione** $\vec{v}=(a,b)\neq(0,0)$;
    
- allora ogni punto della retta si ottiene come
    

$$  
(x,y) = (x_0,y_0) + t(a,b), \quad t\in\mathbb{R}.  
$$

Questa è la **forma parametrica** (fondamentale, perché evita casi speciali e ti fa ragionare con i vettori).

---

### **3. Le forme principali dell’equazione di una retta**

In pratica userai varie forme a seconda del contesto. Devi saperle **riconoscere**, **convertire**, e soprattutto capire cosa significano i parametri.

---

## **3.1 Forma esplicita (forma “pendenza-intercetta”)**

La forma più usata in Analisi è:

$$  
y = mx + q  
$$

- $m$ si chiama **coefficiente angolare** (slope).
    
- $q$ si chiama **intercetta sull’asse $y$** o **quota all’origine** (ordinata all’origine).
    

#### **Interpretazione ingegneristica di $m$**

$m$ è un rapporto tra variazioni:

$$  
m = \frac{\Delta y}{\Delta x}.  
$$

Quindi descrive la **pendenza**: quanto cambia $y$ quando $x$ aumenta di 1.

Se prendi due punti distinti $P_1(x_1,y_1)$ e $P_2(x_2,y_2)$ con $x_1\neq x_2$, allora:

$$  
m = \frac{y_2-y_1}{x_2-x_1}.  
$$

#### **Interpretazione di $q$**

Metti $x=0$:

$$  
y = m\cdot 0 + q \Rightarrow y=q.  
$$

Quindi $q$ è la quota in cui la retta taglia l’asse $y$.

---

## **3.2 Forma implicita (o generale)**

La forma più generale (e più robusta) è:

$$  
ax + by + c = 0  
$$

con $(a,b)\neq(0,0)$.

Questa forma è potentissima perché:

- include anche le rette verticali (che la forma esplicita non può descrivere);
    
- si collega direttamente a **normali**, **distanze**, **proiezioni**.
    

#### **Relazione tra forma implicita e esplicita**

Se $b\neq 0$:

$$  
ax + by + c = 0  
\Rightarrow  
by = -ax - c  
\Rightarrow  
y = -\frac{a}{b}x - \frac{c}{b}.  
$$

Quindi, quando esiste la forma esplicita:

$$  
m = -\frac{a}{b},  
\quad  
q = -\frac{c}{b}.  
$$

**Osservazione cruciale:** se $b=0$, allora non puoi dividere per $b$ e la retta è verticale.

---

## **3.3 Retta verticale**

Una retta verticale ha equazione:

$$  
x = k  
$$

Non è scrivibile come $y=mx+q$ perché avrebbe pendenza “infinita” (in termini di $\Delta y / \Delta x$, il denominatore tende a 0).

In forma implicita diventa:

$$  
x-k=0  
$$

quindi $a=1$, $b=0$, $c=-k$.

---

## **3.4 Forma punto-pendenza**

Se conosci un punto $P_0(x_0,y_0)$ e il coefficiente angolare $m$, la retta è:

$$  
y-y_0 = m(x-x_0).  
$$

È una delle forme più usate per costruire rette a partire da informazioni geometriche.

---

## **3.5 Forma per due punti**

Se hai due punti distinti $P_1(x_1,y_1)$ e $P_2(x_2,y_2)$:

- se $x_1\neq x_2$, prima trovi
    

$$  
m = \frac{y_2-y_1}{x_2-x_1},  
$$

poi usi la forma punto-pendenza.

- se $x_1=x_2$, la retta è verticale:
    

$$  
x=x_1.  
$$

---

## **3.6 Forma parametrica (vettoriale)**

Come detto:

$$  
\begin{cases}  
x = x_0 + at \  
y = y_0 + bt  
\end{cases}  
\quad t\in\mathbb{R}.  
$$

Qui $(a,b)$ è un vettore direzione.  
Se $a\neq 0$, allora la pendenza è:

$$  
m = \frac{b}{a}.  
$$

Se $a=0$, la retta è verticale.

---

## **3.7 Forma normale (utile per distanze)**

La retta implicita:

$$  
ax+by+c=0  
$$

ha come vettore normale:

$$  
\vec{n} = (a,b).  
$$

Se vuoi la forma normalizzata (con normale unitario), dividi per $\sqrt{a^2+b^2}$:

$$  
\frac{a}{\sqrt{a^2+b^2}}x + \frac{b}{\sqrt{a^2+b^2}}y + \frac{c}{\sqrt{a^2+b^2}} = 0.  
$$

Questa forma è quella che “dialoga” direttamente con la distanza punto-retta.

---

### **4. Geometria del coefficiente angolare**

#### **4.1 Collegamento con l’angolo**

Se una retta non è verticale, forma un angolo $\theta$ con l’asse $x$ e vale:

$$  
m = \tan(\theta).  
$$

Quindi:

- $m>0$ → retta crescente;
    
- $m<0$ → retta decrescente;
    
- $m=0$ → retta orizzontale;
    
- $|m|$ grande → retta “quasi verticale”.
    

---

### **5. Parallelismo e perpendicolarità**

#### **5.1 Rette parallele**

Due rette non verticali sono parallele se hanno la stessa pendenza:

$$  
m_1 = m_2.  
$$

In forma implicita:

$$  
a_1x+b_1y+c_1=0,  
\quad  
a_2x+b_2y+c_2=0  
$$

sono parallele se i vettori normali sono proporzionali:

$$  
(a_1,b_1) = \lambda (a_2,b_2).  
$$

---

#### **5.2 Rette perpendicolari**

Due rette non verticali sono perpendicolari se:

$$  
m_1 m_2 = -1.  
$$

In forma implicita: sono perpendicolari se i vettori normali sono ortogonali **ai vettori direzione** equivalenti, oppure più direttamente:

Se una retta ha normale $\vec{n}_1=(a_1,b_1)$ e l’altra $\vec{n}_2=(a_2,b_2)$, allora sono perpendicolari quando una direzione è parallela all’altra normale.  
In pratica, più facile ricordare:

- parallele ↔ normali proporzionali
    
- perpendicolari ↔ direzioni ortogonali
    

Se usi vettori direzione $\vec{v}_1=(a_1,b_1)$ e $\vec{v}_2=(a_2,b_2)$:

$$  
\vec{v}_1\cdot \vec{v}_2 = 0.  
$$

---

### **6. Intersezione tra due rette**

Prendi due rette in forma implicita:

$$  
\begin{cases}  
a_1x + b_1y + c_1 = 0 \  
a_2x + b_2y + c_2 = 0  
\end{cases}  
$$

È un sistema lineare $2\times2$.

Definiamo il determinante:

$$  
\Delta = a_1b_2 - a_2b_1.  
$$

- Se $\Delta \neq 0$ → esiste **un solo punto di intersezione**.
    
- Se $\Delta = 0$ → rette **parallele o coincidenti**.
    

Quando $\Delta\neq 0$, la soluzione è:

$$  
x = \frac{b_1c_2 - b_2c_1}{\Delta},  
\quad  
y = \frac{a_2c_1 - a_1c_2}{\Delta}.  
$$

---

### **7. Distanze fondamentali (livello ingegneria)**

## **7.1 Distanza punto-retta**

Data la retta:

$$  
ax + by + c = 0  
$$

e un punto $P(x_0,y_0)$, la distanza è:

$$  
d(P,\ell) = \frac{|ax_0 + by_0 + c|}{\sqrt{a^2+b^2}}.  
$$

Questa formula è centrale perché:

- appare in geometria analitica avanzata;
    
- appare in regressione, fitting, ottimizzazione (errore geometrico);
    
- è la base per proiezioni e minimi quadrati in geometria.
    

---

## **7.2 Distanza tra rette parallele**

Due rette parallele:

$$  
ax + by + c_1 = 0,  
\quad  
ax + by + c_2 = 0  
$$

hanno distanza:

$$  
d = \frac{|c_2-c_1|}{\sqrt{a^2+b^2}}.  
$$

---

### **8. Proiezione ortogonale di un punto su una retta**

Data la retta:

$$  
ax+by+c=0  
$$

e il punto $P(x_0,y_0)$, il piede della perpendicolare $H(x_H,y_H)$ (proiezione ortogonale) si può calcolare in modo vettoriale.

Definisci:

$$  
k = \frac{ax_0+by_0+c}{a^2+b^2}.  
$$

Allora:

$$  
x_H = x_0 - ak,  
\quad  
y_H = y_0 - bk.  
$$

Questa formula è estremamente utile: è il modo più “pulito” per trovare il punto più vicino alla retta.

---

### **9. Rette come oggetti vettoriali: direzione e normale**

Se hai una retta implicita:

$$  
ax+by+c=0,  
$$

- un vettore **normale** è $\vec{n}=(a,b)$
    
- un vettore **direzione** è per esempio:
    

$$  
\vec{v}=(b,-a)  
$$

perché:

$$  
(a,b)\cdot(b,-a)=ab-ab=0.  
$$

Questo collegamento è il ponte tra:

- geometria analitica classica
    
- algebra lineare
    
- geometria computazionale
    

---

### **10. Checklist finale: cosa devi saper fare senza pensarci**

Devi essere in grado di:

1. Passare tra forme:
    
    - $y=mx+q \leftrightarrow ax+by+c=0$
        
2. Capire cosa significano:
    
    - $m$ come $\Delta y/\Delta x$
        
    - $q$ come intercetta
        
3. Gestire casi limite:
    
    - rette verticali $x=k$
        
4. Costruire una retta da:
    
    - due punti
        
    - un punto e una pendenza
        
    - un punto e una direzione
        
5. Verificare:
    
    - parallelismo
        
    - perpendicolarità
        
6. Trovare:
    
    - intersezione tra due rette (sistema)
        
7. Calcolare:
    
    - distanza punto-retta
        
    - distanza tra parallele
        
    - proiezione ortogonale di un punto su una retta
        

---

### **11. Nota didattica importante (ponte verso Analisi)**

Questa lezione non è “solo geometria”: è la base per capire perché, in Analisi, la **derivata** è una pendenza.

Quando arriverai alle derivate, la retta diventa:

- “la miglior approssimazione locale” di una funzione
    
- il modello lineare che descrive il comportamento infinitesimo
    

Quindi padroneggiare le rette significa già prepararsi a dominare:

- tangenti
    
- linearizzazione
    
- differenziali
    
- ottimizzazione locale