
## **Lezione 1: Algoritmi per lo String Matching**

### **1. Introduzione**

In questa lezione vengono presentati due algoritmi di tipo **Backtrack** per risolvere il **problema dello String Matching**, cioè il riconoscimento di una sottostringa (pattern) all’interno di una stringa più lunga (testo).

#### **Definizione del problema**

Siano:

- $T = t_1t_2\dots t_n$ la **stringa testo** di lunghezza $n$
    
- $P = p_1p_2\dots p_m$ la **stringa pattern** di lunghezza $m$ (con $m < n$)
    

Il problema dello **String Matching** consiste nel determinare **se esiste almeno un indice $k$** tale che:

$$  
p_j = t_{k + j - 1} \quad \text{per ogni } j = 1, \dots, m  
$$

In altre parole, vogliamo sapere **se $P$ compare come sottostringa in $T$**, e in quale posizione.

---

### **2. Algoritmo di Ricerca Bruta**

L’**algoritmo di ricerca bruta** rappresenta l’approccio più diretto e intuitivo al problema.  
È un tipico esempio di **backtracking elementare**, in cui ogni volta che un confronto fallisce, si torna indietro nel testo di una posizione e si riprova da lì.

#### **Idea di base**

- Si tenta di allineare il pattern $P$ con il testo $T$ a partire dalla prima posizione.
    
- Si confrontano, uno per uno, i caratteri di $P$ con quelli di $T$.
    
- Se i caratteri corrispondono, si continua; se non corrispondono, si **fa backtrack** e si riparte dal carattere successivo del testo.
    

---

#### **Codice C – Ricerca Bruta**

```c
int ricercabruta(char *P, char *T, int n, int m) {
    int i, j, k;
    i = j = k = 1;
    while (i <= n && j <= m) {
        if (T[i] == P[j]) {
            i++;
            j++;
        } else {
            k++;
            i = k;
            j = 1;
        }
    }
    return (j > m ? k : i);
}
```

**Spiegazione:**

- Se $T[i] == P[j]$, i due caratteri coincidono e si prosegue nel confronto.
    
- Se $T[i] \neq P[j]$, si **torna indietro** al carattere successivo nel testo ($k+1$) e si ricomincia dal primo carattere del pattern.
    

---

#### **Interpretazione grafica**

L’algoritmo può essere visto come **una serie di sovrapposizioni successive** del pattern $P$ sopra il testo $T$:

$$  
\begin{aligned}  
T &= 1\ 0\ 1\ 1\ 0\ 0\ 1\ 0\ 1\ 0\ 1\ 1\ 0\ 1\ 0\ 1\ 1\ 0\ 1\ 1\ 0\ 1\ 1 \  
P &= 1\ 0\ 1\ 1\ 0\ 1\ 1\ 0  
\end{aligned}  
$$

Ogni volta che il confronto fallisce, il pattern viene **traslato di una posizione a destra** nel testo.

---

#### **Analisi della complessità**

Nel caso peggiore, per ogni posizione del testo vengono confrontati quasi tutti i caratteri del pattern (cioè $m-1$).

- Il cursore $k$ può assumere fino a $(n - m + 1)$ valori ⇒ $O(n)$
    
- Per ogni valore di $k$ si possono fare fino a $m$ confronti ⇒ $O(m)$
    

**Complessità complessiva:**  
$$  
O(nm)  
$$

---

### **3. Efficienza del backtrack**

Il metodo appena visto è **corretto ma inefficiente**: ogni volta che un confronto fallisce, l’algoritmo **ignora tutto ciò che ha già appreso** nei confronti precedenti.  
Si può quindi pensare a un **backtrack più “intelligente”**, che eviti di ripetere i controlli già effettuati.

#### **Esempio di backtrack intelligente**

Supponiamo di aver già riconosciuto i primi $j-1$ caratteri del pattern prima di un errore.  
Invece di tornare indietro fino all’inizio, possiamo sfruttare le informazioni sui **prefissi e suffissi** già confrontati per capire **quanto possiamo effettivamente retrocedere**.

Questo principio è alla base dell’**algoritmo di Knuth–Morris–Pratt (KMP)**.

---

### **4. Calcolo dell’indice di Backtrack**

Per rendere il backtrack efficiente, si costruisce un **vettore ausiliario `back[]`**, tale che:

$$  
back[j] = \max{h : h \le j - 2 \ \text{e} \ P[1..h-1] = P[j - h + 1..j - 1]}  
$$

In altre parole, `back[j]` indica **dove riprendere il confronto** nel pattern in caso di mismatch alla posizione $j$.

- $back[1] = 0$
    
- Il confronto si fa **solo con il pattern $P$**, non con il testo $T$  
    (questo permette di calcolare `back[]` **una volta sola** prima della ricerca).
    

---

### **5. Algoritmo di Knuth–Morris–Pratt (KMP)**

#### **Idea di base**

L’algoritmo KMP evita i backtrack inutili sfruttando il vettore `back[]`:  
quando c’è un errore, invece di tornare al primo carattere del pattern, si salta alla posizione più opportuna calcolata in base ai prefissi già noti.

---

#### **Codice C – KMP**

```c
int KMP(char *P, char *T, int n, int m) {
    int i, j, *back;
    calcola_back(P, back, m);
    i = j = 1;
    while (i <= n && j <= m) {
        if (j == 0 || T[i] == P[j]) {
            i++;
            j++;
        } else {
            j = back[j];
        }
    }
    return (j > m ? i - m : i);
}
```

**Funzionamento:**

- Se $T[i]$ e $P[j]$ coincidono, si prosegue.
    
- In caso contrario, si **aggiorna $j$** con il valore `back[j]`, evitando di ricominciare da zero.
    
- L’indice $i - m$ restituisce la **posizione iniziale dell’occorrenza trovata**.
    

---

#### **Codice C – Calcolo del vettore `back`**

```c
void calcola_back(char *P, int *back, int m) {
    int j, h;
    back[1] = 0; j = 1; h = 0;
    while (j <= m) {
        if (h == 0 || P[j] == P[h]) {
            j++; h++;
            back[j] = (P[j] == P[h] ? back[h] : h);
        } else {
            h = back[h];
        }
    }
}
```

**Nota:**  
Il vettore `back` è calcolato **una sola volta**, poiché dipende unicamente dal pattern $P$.

---

### **6. Analisi della complessità**

L’algoritmo KMP richiede:

- $O(m)$ operazioni per il calcolo del vettore `back[]`;
    
- $O(n)$ operazioni per il ciclo principale di ricerca.
    

**Complessità complessiva:**

$$  
O(n + m)  
$$

Questo rappresenta la **complessità ottima** per il problema dello string matching, che ha un limite inferiore $\Omega(n + m)$, dato dalla dimensione stessa dell’input.

---

### **7. In sintesi**

- Sono stati presentati **due algoritmi di tipo Backtrack** per il problema dello String Matching:
    
    - la **ricerca bruta**, semplice ma inefficiente ($O(nm)$),
        
    - e l’**algoritmo di Knuth–Morris–Pratt (KMP)**, 
    che riduce drasticamente i backtrack inutili ($O(n + m)$).
        
- L’algoritmo KMP rappresenta un esempio perfetto di **backtracking intelligente**, in cui si sfrutta l’informazione parziale già acquisita per **non ripetere confronti** e ottenere la massima efficienza possibile.