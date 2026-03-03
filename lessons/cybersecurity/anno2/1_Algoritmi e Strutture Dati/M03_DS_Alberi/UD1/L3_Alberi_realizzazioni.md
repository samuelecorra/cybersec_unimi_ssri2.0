## **Lezione 3: Alberi – Realizzazioni**

---

### **1. Introduzione**

Dopo aver studiato la **specifica logica** del tipo di dato albero e i suoi **algoritmi di visita**, passiamo ora a un aspetto fondamentale: **come realizzare concretamente un albero in memoria**.

In informatica, la “realizzazione” di una struttura dati è ciò che traduce il concetto astratto in **una disposizione fisica di celle e puntatori**.  
Esistono diversi modi per rappresentare un albero, e ciascuno offre vantaggi e svantaggi in base alle operazioni da eseguire più frequentemente.

Le tre realizzazioni classiche sono:

1. **Vettore dei padri**
    
2. **Liste dei figli**
    
3. **Puntatori padre / primofiglio / fratello (p/p/f)**
    

---

### **2. Realizzazione con vettore dei padri**

#### **Descrizione**

- Si numerano i nodi dell’albero da $0$ a $n - 1$.
    
- Si crea un **vettore T** in cui ogni posizione rappresenta un nodo.
    
- L’elemento $T[u]$ contiene **il cursore del padre** del nodo $u$.
    
- Se $u$ è la radice, allora $T[u] = 0$ (o un valore nullo).
    

$$
T[u] = 
\begin{cases}
v, & \text{se } v \text{ è il padre di } u \\
0, & \text{se } u \text{ è la radice}
\end{cases}
$$


---

#### **Osservazioni**

- Questa rappresentazione permette di **risalire facilmente** dai nodi alle radici.  
    L’operatore `padre(u, T)` ha **complessità O(1)**.
    
- Tuttavia, è **difficile scendere** verso i figli:
    
    - trovare tutti i figli di un nodo richiede di **scandire tutto il vettore**;
        
    - anche determinare il livello o cancellare un sottoalbero è complesso.
        
- La relazione fra fratelli non è esplicita: è nota solo se imposta manualmente.
    

> **Conclusione:** questa rappresentazione è semplice ma poco efficiente per operazioni discendenti.  
> Non è una buona realizzazione per alberi dinamici.

---

### **3. Realizzazione con liste dei figli**

#### **Descrizione**

- Anche qui i nodi sono numerati da $0$ a $n - 1$.
    
- Si costruisce un **vettore di puntatori a lista**, in cui ogni cella contiene la lista dei figli del nodo corrispondente.
    
- Per ogni nodo $u$, si associa una lista $L_u$ dei suoi figli immediati.
    
- Il vettore $T$ contiene i puntatori a queste liste.
    

$$  
T[u] = L_u  
$$

---

#### **Esempio operativo**

Se il nodo $u = 3$ ha come figli i nodi $1$ e $5$, allora la sua lista dei figli sarà:

$$  
L_3 = [1, 5]  
$$

e nel vettore avremo $T[3] = L_3$.

---

#### **Operatori equivalenti**

Possiamo ridefinire gli operatori dell’albero in funzione di quelli della lista:

- $primofiglio(u, T) = leggilista(primolista(L_u), L_u)$
    
- $succfratello(u, T)$ → scorrimento successivo nella lista $L_u$
    

---

#### **Vantaggi**

- Accesso rapido e ordinato a tutti i figli di un nodo.
    
- È immediata la nozione di **precedenza fra fratelli** (grazie all’ordine della lista).
    

#### **Svantaggi**

- L’operatore `padre(u, T)` richiede di cercare in tutte le liste: **O(n)** nel caso peggiore.
    
- Anche `succfratello(u, T)` può richiedere un attraversamento multiplo.
    

> **Conclusione:** migliora l’accesso discendente ma peggiora quello ascendente.  
> È un modello bilanciato solo in parte, utile per strutture statiche.

---

### **4. Realizzazione con puntatori padre / primofiglio / fratello (p/p/f)**

#### **Descrizione**

Per rendere efficienti **tutti** gli operatori principali, si introduce in ogni nodo una **tripla di puntatori**:

1. **padre:** riferimento al nodo padre;
    
2. **primofiglio:** riferimento al primo dei figli;
    
3. **fratello:** riferimento al fratello successivo (se esiste).
    

Ogni nodo dunque “sa”:

- chi lo ha generato (padre),
    
- chi è il suo primo discendente,
    
- e chi viene dopo di lui nella stessa generazione.
    

---

#### **Vantaggi**

- Tutti gli operatori principali (`padre`, `primofiglio`, `succfratello`) hanno **complessità O(1)**.
    
- Le operazioni di inserimento o spostamento di sottoalberi diventano dirette e veloci.
    

#### **Svantaggi**

- L’unico operatore più costoso è `cancsottoalbero(u, T)`:
    
    - deve rimuovere ricorsivamente tutto il sottoalbero,
        
    - e aggiornare i puntatori;
        
    - complessità **O(n)** nel caso peggiore.
        

> **Conclusione:** è la **migliore realizzazione** generale per alberi dinamici.  
> Equilibra accesso rapido, semplicità e efficienza complessiva.

---

### **5. Nuovi operatori definiti**

#### **Lettura del valore di un nodo**

```c
legginodo: (nodo, albero) → tipoelem
legginodo(u, T) = a
pre: T ≠ Λ, u ∈ T
post: a è il valore memorizzato nel nodo u
```

#### **Scrittura del valore di un nodo**

```c
scrivinodo: (tipoelem, nodo, albero) → albero
scrivinodo(a, u, T) = T'
pre: T ≠ Λ, u ∈ T
post: T' è ottenuto da T scrivendo il valore a nel nodo u
```

---

### **6. Considerazioni finali**

|Realizzazione|Vantaggi|Svantaggi|Complessità media|
|---|---|---|---|
|**Vettore dei padri**|Rapida risalita verso la radice|Difficoltà nel visitare i figli|$O(n)$ discendente|
|**Liste dei figli**|Accesso ordinato ai figli|Ricerca del padre costosa|$O(n)$ ascendente|
|**Puntatori p/p/f**|Tutti gli operatori $O(1)$|Solo `cancsottoalbero` è $O(n)$|Ottimale|

---

> In sintesi, la rappresentazione **p/p/f** è quella di riferimento nei moderni linguaggi e nei sistemi di archiviazione gerarchica.  
> Combina flessibilità, efficienza e simmetria logica: ogni nodo è, allo stesso tempo, **figlio, padre e fratello**.
