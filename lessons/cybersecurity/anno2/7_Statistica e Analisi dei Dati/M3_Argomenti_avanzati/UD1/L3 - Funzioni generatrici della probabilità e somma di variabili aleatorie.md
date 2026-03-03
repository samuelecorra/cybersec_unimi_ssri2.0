# **M3 UD1 Lezione 3 - Funzioni generatrici della probabilità e somma di variabili aleatorie**

### **1. Introduzione**

Abbiamo visto che alla **somma di variabili aleatorie** corrisponde la **convoluzione** delle loro distribuzioni.  
Tuttavia, la convoluzione **non sempre è facile da calcolare**, soprattutto quando le distribuzioni non hanno una forma semplice.

Per questo motivo, si introduce un approccio alternativo: le **funzioni generatrici della probabilità**, che permettono di ottenere la distribuzione della variabile somma **senza calcolare direttamente la convoluzione**.

---

### **2. Ripasso del procedimento discreto**

Nel caso di due variabili discrete $i$ e $j$, indipendenti e distribuite secondo $X(i)$ e $Y(j)$, la distribuzione della somma $k = i + j$ si ottiene così:

1. Si costruisce la **tabella delle coppie di valori** $(i, j)$.
    
2. Si calcolano le probabilità con  
    $$  
    P(i, j) = X(i) \cdot Y(j)  
    $$
    
3. Per ogni $k$, si **sommano le probabilità** delle coppie che verificano $i + j = k$.

Questo porta alla formula della convoluzione discreta, ma da questo punto è possibile **seguire un’altra strada** - quella delle **funzioni generatrici**.

---

### **3. Funzione generatrice: idea di base**

L’idea è quella di costruire una **funzione polinomiale** che “contenga” le probabilità come coefficienti.  
Il suo aspetto formale non ci interessa: serve solo come “**filo per stendere il bucato**”, cioè come supporto per manipolare in modo più comodo le distribuzioni.

I vantaggi sono:

- manipolazione più semplice,
    
- possibilità di evitare il calcolo diretto della convoluzione,
    
- maggiore efficienza nei casi complessi.

---

### **4. Esempio: distribuzione discreta con tre valori**

Supponiamo che la variabile $i$ possa assumere tre valori con probabilità:

$$  
X(0) = a, \quad X(1) = b, \quad X(2) = c  
$$

e che la variabile $j$ abbia **la stessa distribuzione**:

$$  
Y(0) = a, \quad Y(1) = b, \quad Y(2) = c  
$$

Vogliamo trovare la distribuzione $Z(k)$ della somma $k = i + j$.

---

### **5. Costruzione della funzione generatrice**

Alla distribuzione $X(i)$ associamo un **polinomio** nella variabile muta $u$, dove ogni probabilità è il coefficiente di un termine di grado $i$:

$$  
G_X(u) = X(0)u^0 + X(1)u^1 + X(2)u^2 = a + bu + cu^2  
$$

Questa è la **funzione generatrice delle probabilità (FGP)** di $X$.

Analogamente, per $Y$:

$$  
G_Y(u) = a + bu + cu^2  
$$

---

### **6. Dalla funzione generatrice alla distribuzione**

La funzione generatrice permette anche di **ricostruire le probabilità** originarie:  
i coefficienti del polinomio sono proprio i valori $X(i)$.

$$  
G_X(u) = a + bu + cu^2  
\quad \Rightarrow \quad  
\begin{cases}  
X(0) = a \\\\  
X(1) = b \\\\  
X(2) = c  
\end{cases}  
$$

---

### **7. Prodotto delle funzioni generatrici**

Calcoliamo ora il **prodotto** delle due funzioni generatrici:

$$  
G_X(u)G_Y(u) = (a + bu + cu^2)^2  
$$

Questo prodotto contiene tutti i termini ottenuti combinando i monomi di $G_X$ e $G_Y$.

Ogni termine del prodotto ha grado pari alla **somma degli indici** delle due variabili, cioè $i + j = k$.

---

### **8. Calcolo esplicito del prodotto**

Sviluppando:

$$  
G_X(u)G_Y(u) = a^2 + 2ab \cdot u + (2ac + b^2)u^2 + 2bc \cdot u^3 + c^2u^4  
$$

Questa nuova funzione generatrice, che indichiamo con $G_Z(u)$, **rappresenta la distribuzione della somma** $k = i + j$.

$$  
G_Z(u) = G_X(u)G_Y(u)  
$$

---

### **9. Estrazione delle probabilità da $G_Z(u)$**

I coefficienti del polinomio $G_Z(u)$ sono le probabilità di ciascun valore $k$:

$$  
\begin{cases}  
Z(0) = a^2 \\\\  
Z(1) = 2ab \\\\  
Z(2) = 2ac + b^2 \\\\  
Z(3) = 2bc \\\\  
Z(4) = c^2  
\end{cases}  
$$

---

### **10. Schema generale del procedimento**

Per ottenere la distribuzione della somma $Z(k)$:

1. Costruire la funzione generatrice $G_X(u)$ da $X(i)$.
    
2. Costruire la funzione generatrice $G_Y(u)$ da $Y(j)$.
    
3. Calcolare il prodotto $G_Z(u) = G_X(u) , G_Y(u)$.
    
4. Estrarre i coefficienti di $G_Z(u)$ → otteniamo $Z(k)$.

---

### **11. Notazione compatta**

Scriviamo:

$$  
i \sim X, \quad j \sim Y, \quad k = i + j  
$$

Allora:

$$  
k \sim Z \equiv X \ast Y \quad \text{e} \quad Z \longleftrightarrow G_Z = G_X G_Y  
$$

In altre parole:

- la **somma delle variabili** corrisponde alla **convoluzione delle distribuzioni**,
    
- che a sua volta corrisponde al **prodotto delle funzioni generatrici**.

---

### **12. Vantaggi del metodo**

Sebbene il metodo delle funzioni generatrici possa sembrare più laborioso nei casi semplici, presenta enormi vantaggi:

- Se le variabili sono **identicamente distribuite**, invece di calcolare più convoluzioni, si eleva la funzione generatrice alla **potenza corrispondente**:  
    $$  
    G_Z(u) = [G_X(u)]^n  
    $$
    
- Talvolta non serve estrarre i coefficienti: se si riconosce la **forma funzionale** della generatrice, si possono determinare **direttamente i parametri** della distribuzione.

---

### **13. Metodo formale per ricavare i coefficienti**

Dalla funzione generatrice $G_X(u)$ si possono ottenere i coefficienti $X(k)$ in due modi:

1. **Lettura diretta dei coefficienti** (nei casi semplici).
    
2. **Derivazione ripetuta**, utile nei casi più generali:

$$  
X(k) = \frac{1}{k!} \left. \frac{d^k G_X(u)}{du^k} \right|_{u=0}  
$$

---

### **14. Giustificazione del metodo**

Esempio:

$$  
G_X(u) = a + bu + cu^2  
$$

- Per $k = 0$:
    
    - nessuna derivata, $G_X(0) = a$, quindi $X(0) = a$
    
- Per $k = 1$:
    
    - derivata prima $G'_X(u) = b + 2cu$, $G'_X(0) = b$, quindi $X(1) = b$
    
- Per $k = 2$:
    
    - derivata seconda $G''_X(u) = 2c$, quindi $X(2) = \frac{2c}{2!} = c$

Questo funziona perché derivando:

- si eliminano i termini di grado inferiore,
    
- si isola il coefficiente desiderato,
    
- ponendo $u=0$ si eliminano i termini rimanenti.

---

### **15. Sintesi finale**

**Definizione:**  
La funzione generatrice di una distribuzione $X(i)$ è il polinomio i cui coefficienti sono le probabilità $X(i)$.

**Proprietà fondamentale:**  
Il prodotto delle funzioni generatrici delle distribuzioni corrisponde alla funzione generatrice della somma delle variabili aleatorie.

**Utilizzo pratico:**

- Le funzioni generatrici semplificano il calcolo di somme di variabili.
    
- Possono essere utilizzate anche per derivare momenti e altre proprietà statistiche.