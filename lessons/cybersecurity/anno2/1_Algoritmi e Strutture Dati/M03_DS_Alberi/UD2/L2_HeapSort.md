## **Lezione 2: HeapSort**

---

### **1. Introduzione**

Con questa lezione concludiamo il Modulo 3, esplorando uno degli algoritmi di ordinamento più eleganti e potenti mai ideati: **HeapSort**.  
È il perfetto esempio di come una **buona struttura dati** – lo **heap** – possa trasformare un algoritmo semplice e inefficiente in uno **ottimo**, raggiungendo la **complessità minima teorica** per l’ordinamento basato su confronti.

Gli obiettivi principali di questa lezione sono:

- Comprendere **il funzionamento dell’HeapSort**.
    
- Analizzare **il ruolo dello heap** nella riduzione della complessità.
    
- Confrontare la sua efficienza con altri algoritmi, come **SelectionSort**.
    

---

### **2. Richiamo – SelectionSort**

L’algoritmo **SelectionSort** ordina un vettore di $n$ elementi selezionando, a ogni passo, **il minimo** tra quelli rimanenti e posizionandolo nella parte ordinata.

#### **Codice**

```c
void SelectionSort(int *A, int n) {
    int i, m, t;
    for (i = 0; i < n; i++) {
        m = min(A, i, n - 1);
        t = A[i];
        A[i] = A[m];
        A[m] = t;
    }
}

int min(int *A, int j, int k) {
    int i, m;
    m = j;
    for (i = j + 1; i <= k; i++)
        m = (A[i] < A[m] ? i : m);
    return m;
}
```

#### **Complessità**

$$  
O(n^2)  
$$

SelectionSort è facile da capire, ma inefficiente per grandi insiemi di dati.

---

### **3. Da SelectionSort a HeapSort**

L’idea di HeapSort nasce proprio da qui:  
se in SelectionSort sostituiamo la ricerca del minimo con una **estrazione efficiente** dallo **heap**, otteniamo un algoritmo che ordina con **complessità ottima**.

#### **Versione base dell’algoritmo**

```c
void HeapSort_base(int *A, int n) {
    prioricoda C;
    int i;

    creaprioricoda(C);
    for (i = 0; i < n; i++)
        inserisci(A[i], C);

    for (i = 0; i < n; i++) {
        A[i] = min(C);
        cancellamin(C);
    }
}
```

#### **Analisi della complessità**

- Ogni inserimento e cancellazione costa $O(\log n)$.
    
- L’intero algoritmo richiede quindi:
    

$$  
O(n \log n)  
$$

> Questo valore coincide con la **complessità minima teorica** per l’ordinamento ($\Omega(n \log n)$), dimostrata tramite gli **alberi di decisione**.

HeapSort è dunque **ottimo**, ma nella versione base comporta uno **spreco di memoria**, poiché utilizza una coda di priorità separata.

---

### **4. Ottimizzazione – Ordinamento sullo stesso vettore**

Per eliminare il doppio trasferimento di dati, possiamo implementare HeapSort **direttamente sul vettore di input**, senza strutture ausiliarie.

#### **Idea operativa**

1. Si costruisce lo **heap** all’interno del vettore $A$.
    
2. Si estrae ogni volta il **massimo** (non il minimo) per metterlo in fondo al vettore.
    
3. Dopo ogni estrazione, si ristabilisce la **proprietà dello heap** tramite la funzione `restauraheap`.
    

Poiché vogliamo un **ordinamento crescente**, invertiamo la **proprietà 3** degli heap:

> “Ogni padre è maggiore dei propri figli.”

La **radice** conterrà sempre il valore massimo.

---

### **5. La funzione `restauraheap`**

Serve a **ristabilire la proprietà dello heap** dopo l’estrazione del massimo.

#### **Logica della funzione**

1. Si parte dalla nuova radice (ex ultima foglia a destra).
    
2. Si confronta il suo valore con i figli.
    
3. Se la proprietà “padre > figli” è violata, si esegue uno **scambio** con il figlio maggiore.
    
4. Si ripete la discesa fino a ristabilire la condizione di heap.
    

#### **Esempio grafico**

![[Pasted image 20251018184546.png]]

---

### **6. Implementazione completa**

```c
void HeapSort(int *A, int n) {
    int i;
    tipoelem temp;

    costruisciheap(A, n - 1);

    for (i = n - 1; i > 0; i--) {
        temp = A[i];
        A[i] = A[0];
        A[0] = temp;

        restauraheap(A, 0, i - 1);
    }
}
```

---

#### **Funzione `costruisciheap`**

Costruisce l’heap in $O(n)$, applicando `restauraheap` ai sottoalberi in ordine inverso, partendo dalle ultime posizioni non foglia.

#### **Complessità complessiva**

La costruzione iniziale è $O(n)$;  
poi vengono effettuate $(n - 1)$ chiamate a `restauraheap`, ciascuna in $O(\log n)$:

$$  
T(n) = O(n) + (n - 1) \cdot O(\log n) = O(n \log n)  
$$

---

### **7. Conclusione**

HeapSort è un algoritmo di **ordinamento ottimo**, ideato da **Williams (1964)**.  
Rappresenta il **primo esempio storico** di algoritmo che raggiunge la **complessità minima teorica** grazie all’uso intelligente di una **struttura dati avanzata**.

---

### **8. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**Struttura base**|Heap binario|
|**Proprietà chiave**|Padre maggiore dei figli|
|**Operazioni principali**|`costruisciheap`, `restauraheap`, scambio radice ↔ ultima posizione|
|**Complessità**|$O(n \log n)$|
|**Ottimalità**|Coincide con $\Omega(n \log n)$|
|**Autore**|J. Williams (1964)|

---

> 🧠 **In sintesi:**  
> HeapSort dimostra il potere delle **strutture dati** nel determinare l’efficienza degli algoritmi.  
> È il momento in cui la programmazione smette di essere solo “scrittura di codice” e diventa **ingegneria della complessità**.