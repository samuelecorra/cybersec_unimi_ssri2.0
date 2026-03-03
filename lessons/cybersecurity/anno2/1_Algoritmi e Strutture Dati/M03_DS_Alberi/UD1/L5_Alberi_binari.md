## **Lezione 5: Alberi Binari**

---

### **1. Introduzione**

Con questa lezione ci concentriamo su una struttura fondamentale in informatica: **l’albero binario**.  
Si tratta di un caso particolare di albero ordinato, in cui **ogni nodo può avere al più due figli**, distinti come **figlio sinistro** e **figlio destro**.

Questa distinzione è ciò che rende i binari così potenti: permette di rappresentare in modo naturale operazioni logiche, strutture di decisione, e — come vedremo nei moduli successivi — basi di algoritmi cruciali come la **ricerca binaria**, gli **heap**, e le **espressioni aritmetiche**.

---

### **2. Definizione formale**

Un **albero binario** è una struttura $T = (N, r)$ dove:

- $N$ è l’insieme dei nodi;
    
- $r$ è la radice;
    
- ogni nodo $u \in N$ ha **al più due figli**, detti **sinistro** e **destro**.
    

> In simboli:  
> $\forall u \in N,\ |\text{figli}(u)| \leq 2$

#### **Osservazione**

Due alberi binari che contengono gli stessi nodi e la stessa struttura possono **differire** se, in uno di essi, un certo nodo $u$ è il **figlio sinistro** del nodo $v$, mentre nell’altro è il **figlio destro**.  
Questo mostra come **l’ordine** (sinistra/destra) sia parte integrante della definizione.

---

### **3. Esempio illustrativo**

Immaginiamo tre alberi $T_1$, $T_2$, $T_3$ con gli stessi nodi:

```
T1              T2              T3
  1               1               1
 /|\             / \             / \
2 3 4           2   3           3   2
     \             \               \
      5             4               4
                     \
                      5               5
```

- Considerati come **alberi ordinati**, $T_1 = T_2 = T_3$.
    
- Tuttavia:
    
    - $T_1$ **non è binario**, perché il nodo 1 ha tre figli.
        
    - $T_2$ e $T_3$ **sono binari**, ma differiscono nella posizione del nodo 2 (figlio sinistro in $T_2$, destro in $T_3$).
        

---

### **4. Specifica degli operatori**

Un albero binario condivide molti operatori con l’albero ordinato:

- `creabinalbero`
    
- `binalberovuoto`
    
- `binradice`
    
- `binpadre`
    
- `cancsottobinalbero`
    
- `legginodo`
    
- `scrivinodo`
    

> Questi operatori sono **sintatticamente e semanticamente identici** alle loro controparti per gli alberi ordinati.  
> Per questo non è necessario ridefinirli completamente.

---

### **5. Nuovi operatori specifici**

Poiché ogni nodo ha al più due figli, introduciamo operatori mirati:

|Operatore|Descrizione|Tipo di ritorno|
|---|---|---|
|`figliosinistro(u, T)`|Restituisce il figlio sinistro del nodo `u`|nodo|
|`figliodestro(u, T)`|Restituisce il figlio destro del nodo `u`|nodo|
|`sinistrovuoto(u, T)`|Verifica se il figlio sinistro è assente|booleano|
|`destrovuoto(u, T)`|Verifica se il figlio destro è assente|booleano|
|`costrbinalbero(T, U)`|Costruisce un nuovo albero binario avente $T$ e $U$ come sottoalberi sinistro e destro|binalbero|

---

### **6. Definizione dell’operatore `costrbinalbero`**

#### **Specifica logica**

```text
costrbinalbero(T, U) = T'
post: T' è ottenuto introducendo un nuovo nodo u,
che diventa la radice di T',
con sottoalbero sinistro T e destro U.
```

#### **Condizioni speciali**

- Se $T = \Lambda$, allora `u` **non ha figlio sinistro**.
    
- Se $U = \Lambda$, allora `u` **non ha figlio destro**.
    

> L’operatore `foglia` è **ridondante**, poiché un nodo è foglia se e solo se `sinistrovuoto(u, T)` e `destrovuoto(u, T)` sono entrambi veri.

---

### **7. Schema generale di visita**

```c
void binvisita(nodo u, binalbero T) {
    { esame anticipato di u → previsita };
    if (!sinistrovuoto(u, T))
        binvisita(figliosinistro(u, T), T);
    { esame simmetrico di u → invisita };
    if (!destrovuoto(u, T))
        binvisita(figliodestro(u, T), T);
    { esame differito di u → postvisita };
}
```

---

### **8. Tipi di visita**

L’ordine di esame del nodo determina il **tipo di visita**:

|Tipo di visita|Momento dell’esame|Equivalente su alberi ordinati|
|---|---|---|
|**Anticipata**|prima dei figli|Previsita|
|**Simmetrica**|tra figlio sinistro e destro|Invisita|
|**Differita**|dopo entrambi i figli|Postvisita|

> Tutte le visite hanno **complessità O(n)**, come nel caso degli alberi ordinati, poiché ogni nodo è esaminato esattamente una volta.

---

### **9. Realizzazione con puntatori**

Per rappresentare un albero binario in memoria si usa la realizzazione **con puntatori p/sx/dx**, dove:

Ogni nodo contiene:

- il **valore** del nodo;
    
- un **puntatore al padre**;
    
- un **puntatore al figlio sinistro** (`sx`);
    
- un **puntatore al figlio destro** (`dx`).
    

#### **Struttura in C**

```c
typedef struct _bincella {
    tipoelem valore;
    struct _bincella *sx, *dx, *padre;
} bincella;

typedef struct _bincella *binalbero;
typedef struct _bincella *nodo;
```

---

### **10. Principali operatori in C**

```c
void creabinalbero(binalbero T) {
    T = NULL;
}

boolean binalberovuoto(binalbero T) {
    return (T == NULL);
}

nodo binradice(binalbero T) {
    if (!binalberovuoto(T))
        return (T);
}

nodo binpadre(nodo u, binalbero T) {
    if (!binalberovuoto(T))
        return (u->padre);
}

nodo figliosinistro(nodo u, binalbero T) {
    if (!binalberovuoto(T))
        return (u->sx);
}

nodo figliodestro(nodo u, binalbero T) {
    if (!binalberovuoto(T))
        return (u->dx);
}
```

---

### **11. Funzioni ausiliarie**

```c
boolean sinistrovuoto(nodo u, binalbero T) {
    if (!binalberovuoto(T))
        return (u->sx == NULL);
}

boolean destrovuoto(nodo u, binalbero T) {
    if (!binalberovuoto(T))
        return (u->dx == NULL);
}

binalbero costrbinalbero(binalbero T, binalbero U) {
    nodo u;
    u = malloc(sizeof(bincella));
    u->padre = NULL;
    u->sx = T;
    u->dx = U;

    if (!binalberovuoto(T))
        T->padre = u;
    if (!binalberovuoto(U))
        U->padre = u;

    return (u);
}
```

---

### **12. Analisi della complessità**

- Tutti gli operatori presentati hanno **complessità costante** $O(1)$.
    
- L’unico più costoso è `cancsottobinalbero`, che deve:
    
    - sistemare i puntatori alla radice del sottoalbero da cancellare;
        
    - liberare la memoria di tutte le celle, effettuando una **binvisita**.  
        → **Complessità totale: O(n)**.
        

> ⚠️ Attenzione: molti operatori **non verificano la precondizione** $T \neq \Lambda$, lasciando all’utente il controllo dell’integrità dei dati.

---

### **13. Sintesi finale**

|Aspetto|Albero Binario|Differenze rispetto ad Albero Ordinato|
|---|---|---|
|Struttura|Ogni nodo ha al più 2 figli (sx, dx)|Numero arbitrario di figli|
|Visite|Previsita, Invisita, Postvisita|Stesse tipologie|
|Implementazione|Puntatori p/sx/dx|Puntatori padre/primofiglio/fratello|
|Complessità|Tutte le operazioni $O(1)$ (eccetto cancellazione)|Simile|
|Utilità|Base per strutture più avanzate (BST, heap, ecc.)|Generale|

---

> 🧠 **In sintesi:**  
> L’albero binario è il passo che connette la teoria degli alberi generali alla programmazione algoritmica concreta.  
> La sua rigidità (due figli esatti) è ciò che consente la nascita delle strutture dati più efficienti e diffuse dell’informatica moderna.