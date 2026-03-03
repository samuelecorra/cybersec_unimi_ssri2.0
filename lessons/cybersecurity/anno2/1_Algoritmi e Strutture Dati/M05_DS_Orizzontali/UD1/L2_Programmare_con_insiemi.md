## **Lezione 2: Programmare con gli insiemi**

---

### **1. Sommario e obiettivo della lezione**

L’obiettivo di questa lezione è **utilizzare gli operatori degli insiemi** per risolvere un **problema computazionale**, applicando lo schema già presentato nella lezione _“Soluzione di un semplice problema computazionale”_ del **Modulo 1, Unità Didattica 1**.

#### **Problema proposto**

Dato un **vettore di n posizioni** contenente valori binari (0 o 1), si richiede di:

- scrivere **due procedure** per convertire il vettore nel corrispondente insieme;
    
- realizzare l’insieme in **due modi diversi**:
    
    1. tramite **lista non ordinata**,
        
    2. tramite **lista ordinata**.
        

---

### **2. Complessità del problema**

#### **Dimensione dell’input**

Per costruire un insieme a partire da un vettore di dimensione ( n ):

- qualsiasi algoritmo deve **scandire tutto il vettore** per verificare l’appartenenza di ciascun elemento;
    
- ciò impone un limite inferiore di complessità pari a:
    
    $$  
    \Omega(n)  
    $$
    

#### **Eventi contabili**

Per creare l’insieme occorrono **almeno n inserimenti**.  
La complessità dipende dal tipo di lista utilizzata:

- **Liste non ordinate:**  
    $$  
    \Omega(n)  
    $$
    
- **Liste ordinate:**  
    $$  
    \Omega(n^2)  
    $$
    

---

### **3. Codice della procedura principale**

```c
insieme * creaInsieme(insieme *V, int n) {
    int i;
    insieme *A;

    creainsieme(A);

    for (i = 0; i < n; i++)
        if (V[i])
            inserisci(i, A);

    return (A);
}
```

L’**implementazione dell’operatore `inserisci`** determina la **complessità complessiva** della procedura `creaInsieme`.

---

### **4. Implementazione dell’operatore `inserisci`**

#### **Versione per lista non ordinata**

```c
void inserisci(tipoelem x, insieme A) {
    inslista(x, primolista(A), A);
}
```

#### **Versione per lista ordinata**

```c
void inserisci(tipoelem x, insieme A) {
    posizione p = primolista(A);

    while (leggilista(p, A) < x && !finelista(succlista(p, A), A))
        p = succlista(p, A);

    inslista(x, p, A);
}
```

---

### **5. Analisi della complessità**

La complessità della procedura `creaInsieme` dipende da quella dell’operatore `inserisci`.

| **Tipo di lista**  | **Complessità di `inserisci`** | **Complessità di `creaInsieme`** |
| ------------------ | ------------------------------ | -------------------------------- |
| Lista non ordinata | $O(1)$                         | $O(n)$                           |
| Lista ordinata     | $O(n)$                         | $O(n²)$                          |

In entrambi i casi, gli algoritmi risultano **ottimi** rispetto ai limiti inferiori teorici determinati.

---

### **6. Sintesi finale**

- Sono stati analizzati **due problemi simili sugli insiemi**, basati su strutture dati diverse.
    
- È stata studiata la **complessità del problema** usando due metodi distinti:
    
    - la **dimensione dei dati** $Ω(n)$,
        
    - gli **eventi contabili** $Ω(n)$ o $Ω(n²)$ a seconda della struttura).
        
- È stata evidenziata una **forte analogia tra la gestione degli insiemi e delle liste**, sia a livello concettuale che implementativo.