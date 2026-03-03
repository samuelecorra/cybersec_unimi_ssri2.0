
## **Lezione 1: Code**

---

### **1. Introduzione**

Con questa lezione entriamo nel mondo della **coda**, una struttura dati lineare che segue la politica **FIFO – First In, First Out**.  
Il primo elemento che entra è anche il primo che esce, proprio come nella fila di persone davanti a uno sportello.

$$  
\text{a}_1 \longrightarrow \text{a}_2 \longrightarrow \text{a}_3 \longrightarrow \dots  
$$

> Le code servono a modellare sistemi dove l’ordine di arrivo è fondamentale: sportelli, reti di comunicazione, CPU scheduler, server, ecc.

---

### **2. Definizione**

Una **coda** è una sequenza di elementi dello stesso tipo, nella quale è possibile:

- **aggiungere** un elemento alla **fine** (operazione di accodamento);
    
- **rimuovere** un elemento dall’**inizio** (operazione di estrazione).
    

Politica di accesso:  

$$  
\text{FIFO} = \text{First In, First Out}  
$$

Esempio intuitivo:  
la **coda di attesa** in mensa o in un centro servizi — il primo arrivato è il primo servito.

---

### **3. Specifica sintattica**

```text
creacoda:     () → coda
codavuota:    (coda) → booleano
leggicoda:    (coda) → tipoelem
fuoricoda:    (coda) → coda
incoda:       (tipoelem, coda) → coda
```

Dove:

- `Λ` rappresenta la coda vuota
    
- `Q` è una coda di elementi $a_i$ di tipo `tipoelem`
    
- `b` è un valore booleano
    

---

### **4. Specifica semantica**

```text
creacoda() = Q'
post: Q' = Λ
```

```text
codavuota(Q) = b
post: b è vero sse Q = Λ
```

```text
leggicoda(Q) = a
pre: Q = a₁,…,aₙ con n ≥ 1
post: a = a₁
```

```text
fuoricoda(Q) = Q'
pre: Q = a₁,…,aₙ con n ≥ 1
post:
    Q' = a₂,…,aₙ   se n > 1
    Q' = Λ          se n = 1
```

```text
incoda(a, Q) = Q'
pre: Q = a₁,…,aₙ con n ≥ 0
post: Q' = a₁,…,aₙ,a
```

---

### **5. Realizzazione con liste**

Una coda può essere facilmente costruita a partire dal tipo di dato **lista**, sfruttando i suoi operatori di base.

|Operatore coda|Operatore lista equivalente|Complessità|
|---|---|---|
|`creacoda`|`crealista`|O(1)|
|`codavuota(Q)`|`listavuota(Q)`|O(1)|
|`leggicoda(Q)`|`leggilista(primolista(Q), Q)`|O(1)|
|`fuoricoda(Q)`|`canclista(primolista(Q), Q)`|O(1)|
|`incoda(a, Q)`|`inslista(a, succlista(ultimolista(Q), Q), Q)`|O(1) se lista bidirezionale|

> L’uso delle **liste doppiamente collegate** consente un’inserzione in fondo in tempo costante.  
> In caso di lista semplicemente collegata, l’operazione `incoda` richiede $O(n)$.

---

### **6. Realizzazione con vettore circolare**

Un’altra possibilità è implementare la coda **senza puntatori**, tramite un **array circolare** di dimensione massima `maxlung`.

#### **Concetto di vettore circolare**

Un vettore circolare è un array in cui:

- la **posizione 0** è considerata il **successore** della posizione `maxlung - 1`;
    
- due cursori (`testa` e `fondo`) indicano rispettivamente **inizio** e **fine** della coda.
    

In questo modo, quando si raggiunge la fine dell’array, si “ricomincia” da zero senza spreco di spazio.

---

#### **Gestione con due cursori: `testa` e `fondo`**

- **Inserimento**: si incrementa il cursore `fondo` di 1 modulo `maxlung`
    
- **Cancellazione**: si incrementa il cursore `testa` di 1 modulo `maxlung`
    

$$
\text{nuovo indice} = (\text{indice attuale} + 1) \bmod \text{maxlung}
$$

---

#### **Gestione con cursore `testa` e lunghezza `lung`**

Se:

- `testa = i`
    
- `lung = n`
    

Allora l’ultimo elemento si trova in posizione:

$$  
(i + n - 1) \bmod \text{maxlung}  
$$

- **Cancellazione:** `testa++`, `lung--`
    
- **Inserzione:** scrittura in `(i + n - 1) % maxlung`, poi `lung++`
    

---

### **7. Implementazione in C**

```c
#define maxlung 100

typedef struct _coda {
    tipoelem elementi[maxlung];
    int testa;
    int lung;
} coda;

void creacoda(coda *Q) {
    Q->testa = Q->lung = 0;
}

boolean codavuota(coda *Q) {
    return (Q->lung == 0);
}

tipoelem leggicoda(coda *Q) {
    if (!codavuota(Q))
        return Q->elementi[Q->testa];
}

void fuoricoda(coda *Q) {
    if (!codavuota(Q)) {
        Q->testa = (Q->testa + 1) % maxlung;
        Q->lung--;
    }
}

void incoda(tipoelem a, coda *Q) {
    if (Q->lung == maxlung)
        printf("Errore! Coda piena\n");
    else {
        Q->elementi[(Q->testa + Q->lung) % maxlung] = a;
        Q->lung++;
    }
}
```

---

### **8. Analisi e osservazioni**

- Tutti gli operatori rispettano le pre/postcondizioni definite nella specifica semantica
    
- Ogni operazione ha costo **O(1)**
    
- Il limite principale è la **dimensione fissa** della coda
    
- Un valore eccessivo di `maxlung` può causare **spreco di memoria**
    

---

### **9. In sintesi**

|Aspetto|Descrizione|
|---|---|
|**Politica**|FIFO (First In, First Out)|
|**Implementazioni**|Con liste o vettore circolare|
|**Costo operazioni**|$O(1)$|
|**Applicazioni**|Sistemi di attesa, reti di code, process scheduling|

> La coda è la struttura della **giustizia informatica**:  
> chi arriva prima, viene servito per primo.  
> Da essa derivano sistemi di simulazione, scheduler di CPU e code di rete — pilastri dell’elaborazione moderna.


---
