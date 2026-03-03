# UD**2: Dizionari**

## **Introduzione**

Dopo aver studiato gli insiemi come forma di organizzazione orizzontale dei dati, questa unità introduce un nuovo tipo di struttura: il **dizionario**.  
Il dizionario è una collezione di elementi in cui ogni informazione è associata a una **chiave** univoca, che ne consente l’identificazione diretta.  
Attraverso di esso è possibile **ricercare**, **inserire** o **cancellare** rapidamente un elemento in base alla sua chiave, rendendolo uno strumento fondamentale in moltissimi algoritmi e applicazioni reali.

## **Obiettivi dell’unità**

L’obiettivo principale è comprendere **come sono strutturate le informazioni** all’interno di un dizionario e quali **operatori** ne definiscono il comportamento.  
In particolare, si imparerà a:

- rappresentare un dizionario tramite **vettore ordinato**, utilizzando il **metodo di ricerca binaria** per individuare gli elementi in modo efficiente;
    
- comprendere come costruire un dizionario basato su **tabella hash**, un approccio molto diffuso per la gestione di grandi quantità di dati;
    
- analizzare il **problema delle collisioni**, che si verifica quando due chiavi vengono mappate nella stessa posizione della tabella, e studiare le principali **tecniche di scansione** per risolverlo.


---

## **Lezione 1: Dizionari con vettori ordinati**

---

### **1. Introduzione**

Il **dizionario** è una particolare forma del tipo di dato **insieme**, nella quale ogni elemento è identificato da una **chiave** univoca.  
Attraverso un dizionario è possibile:

- **verificare l’appartenenza** di una chiave,
    
- **inserire** una nuova chiave,
    
- **cancellare** una chiave esistente.
    

In altre parole, un dizionario rappresenta una **collezione di coppie chiave–valore**, o nel caso più semplice, un insieme di chiavi su cui si possono eseguire operazioni di ricerca, inserimento e cancellazione.

---

### **2. Specifica e realizzazione**

Poiché il dizionario è un caso particolare del tipo di dato **insieme**, la **specifica degli operatori** è analoga, limitatamente a:

- **creazione**,
    
- **inserimento**,
    
- **verifica di appartenenza**,
    
- **cancellazione**.
    

Le principali modalità di **realizzazione** di un dizionario sono due:

1. **Tramite vettore ordinato**
    
2. **Tramite tabella hash**
    

In questa lezione si analizza la prima: il dizionario realizzato con **vettore ordinato**.

---

### **3. Dizionario con vettore ordinato**

In questa realizzazione, le chiavi vengono memorizzate in un **vettore** con le seguenti caratteristiche:

- Le chiavi sono **ordinate in modo crescente**.
    
- Le posizioni sono **contigue** in memoria.
    
- Un **cursore** (detto “ultimo”) punta all’ultima posizione occupata.
    

Questo approccio consente di implementare gli operatori del dizionario in modo analogo a quelli dell’insieme, ma con la possibilità di utilizzare una **ricerca più efficiente** grazie all’ordinamento.

---

### **4. Ricerca binaria**

L’ordinamento delle chiavi permette di verificare l’appartenenza di una chiave k in modo molto più efficiente rispetto alla ricerca lineare.

#### **Principio**

Si considera la chiave mediana v del vettore:

- Se ( v < k ), la chiave cercata si trova **nella metà destra** del vettore.
    
- Se ( v > k ), la chiave cercata si trova **nella metà sinistra**.
    
- Se ( v = k ), la chiave è **trovata** e la ricerca termina.
    

Il processo viene **ripetuto ricorsivamente** sulla metà in cui la chiave potrebbe trovarsi.

#### **Esempio**

Cercare la chiave 11 nella sequenza ordinata:

```
1 3 5 7 9 11 13 17 19
```

1. Mediano = 9 → 11 > 9 ⇒ cerca a destra
    
2. Nuovo mediano = 13 → 11 < 13 ⇒ cerca a sinistra
    
3. Nuovo mediano = 11 → trovata → **ricerca terminata**
    

---

### **5. Codice C – Ricerca binaria**

```c
#define TRUE 1
#define FALSE 0
#define DIM 100

typedef short boolean;

typedef struct diz {
    tipoelem chiavi[DIM];
    int ultimo;
} dizionario;

boolean ricbin(dizionario *D, chiave k, int i, int j) {
    int m;

    if (i > j)
        return FALSE;
    else {
        m = (i + j) / 2;

        if (k == D->chiavi[m])
            return TRUE;
        else if (k < D->chiavi[m])
            return ricbin(D, k, i, m - 1);
        else
            return ricbin(D, k, m + 1, j);
    }
}
```

---

### **6. Complessità della ricerca binaria**

Per determinare la complessità della funzione `ricbin`, si analizza il numero di **confronti** fra la chiave cercata e la chiave mediana.  
Se il numero degli elementi è $n = 2^p$, il numero di livelli di ricerca è $p = \log n$.

Pertanto:

$$  
C(n) = O(\log n)  
$$

La **ricerca binaria** ha quindi **complessità logaritmica**, molto più efficiente della ricerca lineare $O(n)$.

---

### **7. Operatore `appartiene`**

L’operatore `appartiene` del dizionario utilizza internamente l’algoritmo di **ricerca binaria** per determinare la presenza di una chiave.

Di conseguenza, la sua complessità è **$O(log n)$**, a differenza del corrispondente operatore sugli insiemi, che richiede **$O(n)$**.

Se le chiavi sono **uniformemente distribuite**, la ricerca può essere ulteriormente ottimizzata con un metodo chiamato **ricerca interpolata**, in cui l’indice mediano viene calcolato come:

$$  
m = i + \frac{(k - D[i]) \cdot (j - i)}{D[j] - D[i]}  
$$

In questo caso, la complessità può ridursi fino a:

$$  
O(\log \log n)  
$$

---

### **8. Sintesi finale**

- È stato introdotto il **tipo di dato “dizionario”**, una struttura basata su chiavi.
    
- È stata presentata la **realizzazione tramite vettore ordinato**.
    
- È stato studiato l’algoritmo di **ricerca binaria**, con complessità $O(\log n)$.
    
- È stata accennata la **ricerca interpolata**, che in certi casi riduce la complessità a $O(\log \log n)$.
    
- Questa realizzazione risulta **molto efficiente** quando si applica frequentemente l’operatore `appartiene`.