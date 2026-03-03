## **Lezione 2: Alberi – Algoritmi di visita**

---

### **1. Introduzione**

Dopo aver definito la struttura logica e gli operatori dell’albero, passiamo ora agli **algoritmi di visita**, cioè le procedure che permettono di **esplorare completamente un albero**, toccando **ogni nodo una sola volta**.

La visita è il cuore operativo dell’albero: conoscere il modo corretto di attraversarlo significa poter calcolare, stampare, analizzare o modificare qualsiasi informazione contenuta al suo interno.  
È l’equivalente del ciclo `for` o `while` delle strutture lineari — ma applicato a una **struttura gerarchica**.

---

### **2. Tipi di visita**

Gli alberi si possono visitare secondo tre schemi principali, detti anche **visite ricorsive**:

- **Previsita** → si esamina il nodo corrente **prima** dei figli.
    
- **Postvisita** → si visitano tutti i figli e **poi** il nodo corrente.
    
- **Invisita** → si visita una parte dei figli, **poi** il nodo, e infine i rimanenti (utile per alberi binari).
    

> Ogni nodo viene toccato una sola volta, rispettando la gerarchia e l’ordine dei figli.

---

### **3. Previsita**

#### **Definizione**

La **previsita** consiste nell’esaminare la **radice** $r$ e, nell’ordine, nel compiere la previsita di ciascun sottoalbero figlio $T_1, T_2, \dots, T_k$.

$$  
\text{Previsita}(T) = \text{esamina}(r),\ \text{Previsita}(T_1),\ \dots,\ \text{Previsita}(T_k)  
$$

#### **Idea visiva**

L’ordine segue il flusso **dall’alto verso il basso**: prima il nodo padre, poi i figli in sequenza.

#### **Esempio intuitivo**

1. Esamina la radice.
    
2. Scendi nel primo figlio.
    
3. Continua a esplorare ricorsivamente i figli di ogni nodo.
    

---

#### **Implementazione (pseudocodice)**

```c
void previsita(nodo u, albero *T) {
    nodo v;
    {esame nodo u per previsita};
    if (!foglia(u, T)) {
        v = primofiglio(u, T);
        while (!finefratelli(v, T)) {
            previsita(v, T);
            v = succfratello(v, T);
        }
    }
}
```

---

### **4. Postvisita**

#### **Definizione**

La **postvisita** è l’opposto: prima si visitano i sottoalberi, poi si esamina la radice.

$$  
\text{Postvisita}(T) = \text{Postvisita}(T_1),\ \dots,\ \text{Postvisita}(T_k),\ \text{esamina}(r)  
$$

#### **Idea visiva**

È un approccio **bottom-up**: prima si scende fino alle foglie, poi si risale esaminando i padri.

#### **Implementazione**

```c
void postvisita(nodo u, albero *T) {
    nodo v;
    if (!foglia(u, T)) {
        v = primofiglio(u, T);
        while (!finefratelli(v, T)) {
            postvisita(v, T);
            v = succfratello(v, T);
        }
    }
    {esame nodo u per postvisita};
}
```

---

### **5. Invisita**

#### **Definizione generale**

Si fissa un indice $i \geq 1$:  
si eseguono, nell’ordine, le invisite dei primi $i$ sottoalberi, poi si esamina la radice, e infine si eseguono le invisite dei rimanenti.

$$  
\text{Invisita}(T) = \text{Invisita}(T_1), \dots, \text{Invisita}(T_i), \text{esamina}(r), \text{Invisita}(T_{i+1}), \dots, \text{Invisita}(T_k)  
$$

#### **Caso più comune ($i = 1$)**

Questo schema corrisponde alla **invisita classica per alberi binari**:  
visita del figlio sinistro → nodo → figlio destro.

#### **Implementazione ($i = 1$)**

```c
void invisita(nodo u, albero *T) {
    nodo v;
    if (foglia(u, T))
        {esamina nodo u};
    else {
        v = primofiglio(u, T);
        invisita(v, T);
        {esame del nodo u};
        v = succfratello(v, T);
        while (!finefratelli(v, T)) {
            invisita(v, T);
            v = succfratello(v, T);
        }
    }
}
```

---

### **6. Osservazioni sulle visite**

#### **Ipotesi**

Gli operatori di base (`foglia`, `primofiglio`, `succfratello`, ecc.) hanno **complessità $O(1)$**, ossia tempo costante.

#### **Conseguenza**

Poiché ogni nodo viene visitato **una sola volta**, la complessità complessiva è **lineare** nel numero dei nodi:

$$  
T(n) = O(n)  
$$

#### **Dimostrazione (idea per induzione)**

- **Caso base:** per $n = 1$, l’albero ha solo la radice → una sola operazione.
    
- **Passo induttivo:** per $n > 1$, la visita richiama sé stessa su ciascun sottoalbero, coprendo $n - 1$ nodi in totale.  
    Poiché ogni chiamata comporta un numero costante di operazioni, si ha:
    
    $$  
    T(n) = T(k) + T(n - k - 1) + O(1) \Rightarrow O(n)  
    $$
    

---

### **7. In sintesi operativa**

|Tipo di visita|Ordine di esame|Direzione logica|Applicazioni tipiche|
|---|---|---|---|
|**Previsita**|Nodo → Figli|Top-down|Stampa, analisi strutturale|
|**Postvisita**|Figli → Nodo|Bottom-up|Eliminazione, valutazione espressioni|
|**Invisita**|Sinistra → Nodo → Destra|Intermedia|Alberi binari di ricerca|

---

> Ogni algoritmo di visita è una **passeggiata organizzata** tra i rami dell’albero.  
> Saper scegliere l’ordine giusto di esplorazione è ciò che distingue un semplice programmatore da un **architetto dell’informazione**.