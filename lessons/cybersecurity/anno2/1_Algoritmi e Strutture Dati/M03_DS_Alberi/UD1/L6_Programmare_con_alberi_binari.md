## **Lezione 6: Programmare con gli alberi binari**

---

### **1. Obiettivo della lezione**

In questa lezione applichiamo quanto appreso sugli **alberi binari** per risolvere due problemi computazionali, utilizzando **gli operatori e gli algoritmi di visita**.  
Si tratta delle stesse sfide affrontate nella lezione _“Programmare con gli alberi”_ del Modulo 3, ma qui adattate alla struttura binaria.

L’obiettivo è mostrare come le visite **anticipata (previsita)** e **differita (postvisita)** si adattino perfettamente a questo tipo di albero, mantenendo **complessità ottimale**.

---

### **2. Problema 1 – Profondità di un albero binario**

#### **Definizione**

La **profondità di un albero binario** è il **massimo livello delle sue foglie**.  
Formalmente, è la lunghezza del cammino più lungo dalla radice fino a una foglia.

#### **Idea risolutiva**

La profondità può essere definita **ricorsivamente** come segue:

$$
P(u) =
\begin{cases}
0, & \text{se } u \text{ è una foglia} \\
1 + \max\{ P(v) \mid v \text{ è figlio sinistro o destro di } u \}, & \text{altrimenti}
\end{cases}
$$


Questo schema indica l’utilizzo di una **binvisita differita (postvisita)**:  
l’informazione si propaga **dalle foglie verso la radice**.

---

### **3. Implementazione in C**

```c
int profondita(nodo u, binalbero T) {
    int p1, p2, max;

    if (sinistrovuoto(u, T) && destrovuoto(u, T))
        return (0);

    else {
        if (!sinistrovuoto(u, T))
            p1 = profondita(figliosinistro(u, T), T);

        if (!destrovuoto(u, T))
            p2 = profondita(figliodestro(u, T), T);

        max = (p1 > p2 ? p1 : p2);
        return (max + 1);
    }
}
```

#### **Spiegazione logica**

- Se il nodo `u` è una **foglia**, la profondità locale è 0.
    
- Altrimenti:
    
    1. Calcola la profondità del figlio sinistro (`p1`) e di quello destro (`p2`);
        
    2. Se uno dei due non esiste, viene ignorato;
        
    3. Si prende il **massimo tra i due**;
        
    4. Si ritorna `max + 1` per tener conto del livello corrente.
        

#### **Osservazioni**

- La funzione segue una **visita bottom-up**.
    
- L’informazione fluisce dai figli verso il padre.
    
- Complessità complessiva: ogni nodo è visitato una sola volta.
    

$$  
T(n) = O(n)  
$$

✅ **Conclusione:** l’algoritmo è ottimo.

---

### **4. Problema 2 – Distanza dei nodi dalla radice**

#### **Definizione**

La **distanza (d(u))** di un nodo (u) dalla radice (r) in un albero binario è data dal **livello** del nodo, ossia il numero di archi che separano (u) da (r).

#### **Definizione ricorsiva**

$$
d(u) =
\begin{cases}
0, & \text{se } u \text{ è la radice} \\
1 + d(\text{padre}(u)), & \text{altrimenti}
\end{cases}
$$


Questa definizione suggerisce l’uso di una **binvisita anticipata (previsita)**:  
l’informazione si propaga **dalla radice verso le foglie**.

---

### **5. Implementazione in C**

```c
void distanzaNodo(nodo u, binalbero T, int *D) {

    if (u == binradice(T))
        D[u] = 0;
    else
        D[u] = 1 + D[binpadre(u)];

    if (!sinistrovuoto(u, T))
        distanzaNodo(figliosinistro(u, T), T, D);

    if (!destrovuoto(u, T))
        distanzaNodo(figliodestro(u, T), T, D);
}
```

#### **Spiegazione logica**

- Se il nodo è la **radice**, la sua distanza è 0.
    
- Per ogni altro nodo:
    
    - la distanza si ottiene aggiungendo 1 alla distanza del padre;
        
    - poi si prosegue ricorsivamente verso il figlio sinistro e destro.
        
- Si assume che sia possibile accedere al vettore delle distanze `D` tramite l’indice corrispondente al nodo `u`.
    

---

### **6. Analisi della complessità**

In entrambi i problemi:

- Gli algoritmi modificano lo schema generale di visita per l’albero binario.
    
- Ogni chiamata esegue **solo operazioni a costo costante** (con la realizzazione p/sx/dx).
    
- Ogni nodo viene visitato **una sola volta**.
    

$$  
T(n) = O(n)  
$$

> 🧠 Entrambi gli algoritmi sono **ottimi**, perché raggiungono la complessità minima possibile:  
> ogni nodo va visitato almeno una volta, quindi ( \Omega(n) ).

---

### **7. Sintesi finale**

|Problema|Tipo di visita|Direzione dell’informazione|Complessità|Osservazioni|
|---|---|---|---|---|
|**Profondità dell’albero binario**|Postvisita|Dalle foglie alla radice|O(n)|Calcola il livello massimo|
|**Distanza dei nodi dalla radice**|Previsita|Dalla radice alle foglie|O(n)|Calcola tutti i livelli|

---

> 💡 **Conclusione:**  
> Gli alberi binari, pur nella loro semplicità strutturale, offrono una **simmetria logica** che rende le operazioni di propagazione — verso l’alto o verso il basso — perfettamente naturali.  
> Capire _quando_ usare una previsita o una postvisita è il primo passo per progettare **algoritmi efficienti e scalabili sugli alberi complessi**.