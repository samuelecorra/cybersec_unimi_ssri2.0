## **Lezione 4: Programmare con gli alberi**

---

### **1. Obiettivo della lezione**

Questa lezione mostra **come utilizzare gli operatori degli alberi** e gli **algoritmi di visita** per risolvere due problemi computazionali reali, applicando lo stesso schema logico introdotto nel **Modulo 1 – “Soluzione di un semplice problema computazionale”**.

Affronteremo due problemi classici:

1. **Calcolo della profondità di un albero ordinato**
    
2. **Calcolo della distanza di ogni nodo dalla radice**
    

In entrambi i casi, lo scopo è mostrare **come scegliere la visita corretta** (postvisita o previsita) in base alla direzione con cui l’informazione deve propagarsi:

- **dalle foglie verso la radice** → postvisita
    
- **dalla radice verso le foglie** → previsita
    

---

### **2. Problema 1 – La profondità di un albero**

#### **Definizione**

La **profondità di un albero ordinato** è il **massimo livello** delle sue foglie, ovvero la lunghezza del cammino più lungo dalla radice a una foglia.

#### **Complessità del problema**

Per determinare il livello massimo, bisogna:

- identificare ogni foglia (`foglia(u, T)`),
    
- effettuando almeno **un confronto per nodo**.
    

Applicando il **metodo degli eventi contabili**, segue che:

$$  
\Omega(n)  
$$

è la **complessità minima** del problema.  
Ogni algoritmo deve eseguire almeno **n confronti**.

---

### **3. Idea di algoritmo**

Definiamo ricorsivamente la **profondità locale** $p(u)$ del sottoalbero radicato in $u$:

$$
p(u)=
\begin{cases}
0, & \text{se } u \text{ è una foglia}\\
1+\max\{\,p(v)\mid v \text{ è figlio di } u\,\}, & \text{altrimenti}
\end{cases}
$$

Poiché la profondità si propaga **dalle foglie verso la radice**, conviene usare uno schema di **postvisita**.

---

### **4. Implementazione in C**

```c
int maxProf(nodo u, albero T) {

    nodo v;
    int max, tmp;

    if (foglia(u, T))
        return (0);

    else {
        v = primofiglio(u, T);
        max = 0;
        while (!finefratelli(v, T)) {
            tmp = maxProf(v, T);
            max = (tmp > max ? tmp : max);
            v = succfratello(v, T);
        }
        return (max + 1);
    }
}
```

#### **Spiegazione passo passo**

1. Se il nodo `u` è una foglia → restituisce 0 (profondità nulla).
    
2. Altrimenti:
    
    - calcola ricorsivamente la profondità di ogni figlio,
        
    - seleziona la massima,
        
    - restituisce `max + 1`, cioè il livello corrente.
        

---

### **5. Analisi della complessità**

- L’algoritmo adatta **lo schema di postvisita** per un albero ordinato.
    
- Ogni chiamata ricorsiva esegue **solo operazioni a costo costante** (se l’albero è realizzato con puntatori padre/primofiglio/fratello).
    
- Ogni nodo è visitato una sola volta.
    

$$  
T(n) = O(n)  
$$

✅ **Conclusione:** l’algoritmo è **ottimo**.

---

### **6. Problema 2 – Distanza dei nodi dalla radice**

#### **Definizione**

La **distanza $d(u)$ di un nodo $u$ dalla radice $r$** in un albero ordinato è data dal **livello** del nodo $u$ in $T$.

In forma ricorsiva:

$$
d(u)=
\begin{cases}
0, & \text{se } u \text{ è la radice}\\
1 + d(\text{padre}(u)), & \text{altrimenti}
\end{cases}
$$


---

### **7. Complessità del problema**

Per calcolare la distanza anche di un solo nodo, nel caso peggiore è necessario **visitare l’intero albero**.  
Per il **metodo della dimensione dell’input**, ogni algoritmo deve visitare **n nodi**.

$$  
\Omega(n)  
$$

Quindi, la complessità minima del problema è **lineare**.

---

### **8. Idea di algoritmo**

La relazione ricorsiva $d(u) = 1 + d(\text{padre}(u))$ suggerisce un approccio **top-down**:  
l’informazione si propaga **dalla radice verso i figli**.  
→ Quindi serve una **previsita**.

---

### **9. Implementazione in C**

```c
void distanzaNodo(nodo u, albero T, int *D) {

    nodo v;

    if (u == radice(T))
        D[u] = 0;
    else
        D[u] = 1 + D[padre(u)];

    v = primofiglio(u, T);
    while (!finefratelli(v, T)) {
        distanzaNodo(v, T, D);
        v = succfratello(v, T);
    }
}
```

#### **Spiegazione logica**

- Se il nodo è la radice → distanza 0.
    
- Altrimenti → distanza = 1 + distanza del padre.
    
- Si effettua una **previsita**, trasmettendo l’informazione verso i figli.
    
- Si assume di poter accedere al vettore delle distanze $D$ tramite l’indice del nodo $u$.
    

---

### **10. Analisi della complessità**

- Ad ogni chiamata vengono fatte **operazioni a costo costante**.
    
- Ogni nodo viene visitato una sola volta.
    

$$  
T(n) = O(n)  
$$

✅ **Conclusione:** anche questo algoritmo è **ottimo**.

---

### **11. Sintesi conclusiva**

|Problema|Direzione informazione|Tipo di visita|Complessità|Osservazioni|
|---|---|---|---|---|
|**Profondità dell’albero**|Foglie → Radice|Postvisita|O(n)|Accumula dati dal basso verso l’alto|
|**Distanza dei nodi**|Radice → Foglie|Previsita|O(n)|Propaga informazioni top-down|

---

> 🧠 **Concetto chiave:**  
> quando l’informazione risale, si usa una **postvisita**;  
> quando scende, una **previsita**.
> 
> Comprendere la direzione logica dell’elaborazione è essenziale per progettare **algoritmi ricorsivi ottimali sugli alberi**.