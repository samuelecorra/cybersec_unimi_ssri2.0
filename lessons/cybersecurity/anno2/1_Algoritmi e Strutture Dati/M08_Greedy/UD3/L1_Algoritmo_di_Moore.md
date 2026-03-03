
## **Lezione 1: Algoritmo di Moore**

### **1. Introduzione**

In questa lezione affrontiamo il **problema dello scheduling di programmi**, una tipica applicazione della **tecnica greedy** nel campo dell’ottimizzazione temporale.

#### **Definizione del problema**

Si considerino $n$ programmi  
$$  
p_1, p_2, \dots, p_n  
$$  
ognuno caratterizzato da:

- una **durata di esecuzione** $t_i$ (unità di tempo necessarie),
    
- una **scadenza** $d_i$ (tempo entro il quale il programma deve essere completato).
    

L’obiettivo è **determinare un ordine di esecuzione $S$** che **minimizzi il numero di programmi in ritardo**, ossia quelli per cui il completamento avviene dopo la scadenza $d_i$.

---

### **2. Esempio introduttivo**

Supponiamo di avere quattro programmi:

|Programma|$t_i$|$d_i$|
|---|---|---|
|$p_1$|2|4|
|$p_2$|3|6|
|$p_3$|3|8|
|$p_4$|4|9|

Se li eseguiamo nell’ordine naturale $(p_1, p_2, p_3, p_4)$, il tempo cumulativo cresce come segue:

$$  
0 \to 2 \to 5 \to 8 \to 12  
$$

In questo caso, alcuni programmi (ad esempio $p_3$ e $p_4$) terminano oltre la loro scadenza, quindi risultano **in ritardo**.

Il nostro obiettivo è **trovare un ordine alternativo** che riduca al minimo il numero di programmi “scaduti”.

---

### **3. Descrizione dell’algoritmo di Moore**

L’**algoritmo di Moore (Hodgson-Moore)** è un algoritmo greedy che trova la **sequenza ottima** di programmi minimizzando il numero di ritardi.

#### **Idea generale**

1. **Ordinare i programmi** in ordine **non decrescente rispetto alla scadenza** $d_i$.  
    $$ d_1 \le d_2 \le \dots \le d_n $$
    
2. **Costruire progressivamente la sequenza** $S$:
    
    - si eseguono i programmi uno alla volta, rispettando l’ordine ordinato;
        
    - si calcola il tempo cumulativo di completamento $s_i = \sum_{j=1}^{i} t_j$;
        
    - quando si trova un programma $p'$ che risulta **in ritardo** ($s_i > d_i$),  
        si **rimuove dalla sequenza il programma $p''$ con durata $t_{p''}$ più lunga tra quelli già inseriti**.
        
3. Si ripete il processo finché nessun programma risulta in ritardo.
    
4. La sequenza risultante $S^*$ è **ottima**, e gli eventuali programmi rimossi possono essere **aggiunti in coda** in qualunque ordine (poiché ormai saranno comunque in ritardo).
    

---

### **4. Esempio passo passo**

Consideriamo nuovamente i quattro programmi precedenti.

1. Ordiniamo per $d_i$: la sequenza resta $(p_1, p_2, p_3, p_4)$.
    
2. Calcoliamo il tempo cumulativo:
    

|Programma|$t_i$|$d_i$|Tempo cumulativo $s_i$|Ritardo|
|---|---|---|---|---|
|$p_1$|2|4|2|—|
|$p_2$|3|6|5|—|
|$p_3$|3|8|8|—|
|$p_4$|4|9|12|✅ in ritardo|

Poiché $p_4$ non rispetta la scadenza, eliminiamo **il programma più lungo tra quelli già pianificati**, cioè $p_4$ stesso.

La nuova sequenza ottima diventa:

$$  
S^* = (p_1, p_2, p_3)  
$$

I programmi rimossi (nel caso, $p_4$) possono essere messi in coda.  
Questa nuova sequenza garantisce **un solo programma in ritardo**, che è la soluzione ottima.

---

### **5. Analisi di complessità della versione base**

- Calcolo dei tempi cumulativi $s_i$: $O(n)$
    
- Confronto tra $s_i$ e $d_i$ e individuazione del programma da rimuovere: $O(n)$
    
- Nel **caso pessimo**, si possono avere fino a $O(n)$ eliminazioni.
    

**Conclusione:**  
La versione base dell’algoritmo ha complessità:

$$  
O(n^2)  
$$

---

### **6. Miglioramento con heap (coda di priorità)**

Per rendere più efficiente la selezione e la rimozione del programma più lungo, si utilizza una **coda di priorità (heap)** in cui le chiavi corrispondono alle durate $t_i$.

#### **Funzionamento migliorato**

1. Ogni programma $p_i$ viene inserito nella coda di priorità $Q$ con chiave $t_i$.
    
2. Si aggiorna il tempo cumulativo $T = \sum t_i$.
    
3. Se $T \ge d_i$, significa che il programma $p_i$ è in ritardo:
    
    - si estrae da $Q$ il programma $p_j$ con **durata massima** (operazione `cancellamax`);
        
    - si aggiorna $T = T - t_j$;
        
    - si segna $p_j$ come “rimosso” (in ritardo).
        

---

### **7. Codice in C**

```c
int moore(int *d, int *t, int n, boolean *r) {
    prioricoda Q;
    int i, j, k = 0, T = 0;
    creaprioricoda(Q);

    for (i = 0; i < n; i++)
        r[i] = false;

    { ordina pi per di non decrescenti };

    for (i = 0; i < n; i++) {
        inserisci(i, t[i], Q);
        T += t[i];
        if (T >= d[i]) {
            j = max(Q);
            cancellamax(Q);
            T -= t[j];
            r[j] = true;
            k++;
        }
    }
    return k;
}
```

**Significato delle variabili:**

- `d[i]`: scadenza del programma $p_i$
    
- `t[i]`: durata del programma $p_i$
    
- `r[i]`: vettore booleano che indica se $p_i$ è stato rimosso (in ritardo)
    
- `Q`: heap che gestisce i tempi di esecuzione
    
- `T`: tempo cumulativo attuale
    
- `k`: numero di programmi rimossi
    

---

### **8. Analisi della complessità**

- Ordinamento iniziale per scadenza: $O(n \log n)$
    
- Inserimento e cancellazione nella coda di priorità: ciascuno $O(\log n)$
    
- Eseguendo $n$ iterazioni del ciclo principale, si ottiene:
    

$$  
O(n \log n)  
$$

Infine, la costruzione della soluzione $S^*$ dai dati del vettore $r[,]$ costa $O(n)$.

**Complessità complessiva:**

$$  
O(n \log n)  
$$

---

### **9. Conclusioni**

In sintesi:

- È stato studiato l’**algoritmo di Moore**, utilizzato per **minimizzare il numero di programmi in ritardo** in uno scheduling su singola risorsa.
    
- L’algoritmo applica la **tecnica greedy** scegliendo localmente la soluzione migliore (rimuovere il job più lungo quando si genera un ritardo).
    
- La **versione base** ha complessità $O(n^2)$, mentre la **versione ottimizzata** con heap raggiunge $O(n \log n)$.
    
- La struttura dati **heap modificata** garantisce l’efficienza necessaria per applicazioni reali di scheduling in sistemi operativi o ambienti di calcolo concorrente.