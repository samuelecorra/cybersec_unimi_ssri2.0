# **Obiettivi del modulo 3**

## **Lezione 1: Alberi — Specifiche**

---

### **1. Idee chiave e motivazione**

Un **albero** è la prima vera struttura **gerarchica** che incontriamo: i dati non stanno più in fila (come nelle liste), ma **si ramificano**.  
Pensalo così: c’è una **radice** da cui tutto parte, poi nodi che possono avere **figli**, e nodi che non hanno figli (le **foglie**).  
Per lavorare bene con gli alberi servono due cose:

- un **vocabolario preciso** (radice, padre, figli, fratelli, livello, sottoalbero);
    
- una **specifica formale** degli **operatori** (creazione, interrogazione, selezione, modifica).
    

---

### **2. Definizione formale di albero ordinato**

Sia dato un insieme finito e **ordinato** di nodi. Se l’insieme non è vuoto:

- un nodo speciale è scelto come **radice**;
    
- i rimanenti nodi sono partizionati in **sottoinsiemi disgiunti** (i **sottoalberi** della radice), ognuno con il proprio ordinamento interno.
    

Una struttura così costruita è un **albero ordinato** (o semplicemente **albero**).

---

### **3. Terminologia essenziale**

- **Radice (root):** l’unico nodo senza padre.
    
- **Padre (parent):** il nodo immediatamente sopra un certo nodo.
    
- **Figli (children):** i nodi immediatamente sotto un certo nodo.
    
- **Fratelli (siblings):** figli dello stesso padre.
    
- **Foglia (leaf):** nodo senza figli.
    
- **Livello (depth):** radice a livello 0; i suoi figli a livello 1; e così via.
    
- **Sottoalbero:** un albero i cui nodi sono tutti discendenti di un certo nodo (incluso il nodo stesso come nuova radice).
    
- **Albero vuoto:** indicato con Λ.
    

---

### **4. Specifica sintattica degli operatori**

#### **Creazione**

```
creaalbero: () → albero
```

#### **Interrogazione (predicati)**

```
alberovuoto: (albero) → booleano
foglia:      (nodo, albero) → booleano
finefratelli:(nodo, albero) → booleano
```

#### **Selezione (navigazione)**

```
radice:       (albero) → nodo
padre:        (nodo, albero) → nodo
primofiglio:  (nodo, albero) → nodo
succfratello: (nodo, albero) → nodo
```

#### **Modifica (costruzione/aggiornamento)**

```
insradice:       (nodo, albero) → albero
inssottoalbero:  (nodo, nodo, albero, albero) → albero
cancsottoalbero: (nodo, albero) → albero
```

> Nota pratica: **finefratelli** può usare una _sentinella_ `s` per marcare “non ci sono più fratelli”.

---

### **5. Specifica semantica — Creazione e interrogazione**

#### **creaalbero**

```
creaalbero() = T'
post: T' = Λ  (albero vuoto)
```

#### **alberovuoto**

```
alberovuoto(T) = b
post: b è vero se T = Λ; falso altrimenti
```

#### **foglia**

```
foglia(u, T) = b
pre: T ≠ Λ, u è un nodo di T
post: b è vero sse u non è padre di alcun nodo in T
```

#### **finefratelli** _(con sentinella)_

```
finefratelli(u, T) = b
pre: T ≠ Λ, u è un nodo di T oppure u = s (sentinella)
post: b è vero sse u = s; falso altrimenti
```

---

### **6. Specifica semantica — Selezione (navigazione)**

#### **radice**

```
radice(T) = u
pre: T ≠ Λ
post: u è il nodo radice di T
```

#### **padre**

```
padre(u, T) = v
pre: T ≠ Λ, u ∈ T, u ≠ radice(T)
post: v è il padre di u in T
```

#### **primofiglio**

```
primofiglio(u, T) = v
pre: T ≠ Λ, u ∈ T, foglia(u, T) = falso
post: v è il primo figlio di u secondo l’ordine dei figli
```

#### **succfratello**

```
succfratello(u, T) = v
pre: T ≠ Λ, u ∈ T
post: v è il fratello che segue u nell’ordine; se u è l’ultimo fratello allora v = s (sentinella)
```

---

### **7. Specifica semantica — Modifica (costruzione e aggiornamento)**

#### **insradice**

```
insradice(u, T) = T'
pre: T = Λ
post: T' è l’albero contenente solo u, con u = radice(T')
```

#### **inssottoalbero**

```
inssottoalbero(u, v, T, U) = T'
pre: T ≠ Λ, U ≠ Λ; u, v ∈ T; (v è figlio di u) oppure (v = u)
post:
  se v = u    → T' si ottiene aggiungendo U come primo figlio di u
  se v ≠ u    → la radice di U diventa il fratello che segue u tra i figli del padre di u
```

> Intuizione: puoi “innestare” un sottoalbero **come figlio** (caso v = u) oppure **come fratello successivo** (caso v ≠ u), rispettando l’ordine dei figli.

#### **cancsottoalbero**

```
cancsottoalbero(u, T) = T'
pre: T ≠ Λ, u ∈ T
post:
  T' si ottiene eliminando il sottoalbero con radice u
  se u = radice(T) allora T' = Λ
```

---

### **8. Esempi operativi mini**

#### **Albero di esempio (struttura fittizia)**

```text
        r
      /   \
     a     b
    / \     \
   c   d     z
             / \
            x   v
```

- `foglia(c, T)` è vero; `foglia(a, T)` è falso.
    
- `padre(d, T) = a` ; `radice(T) = r`.
    
- `primofiglio(r, T) = a` ; `succfratello(a, T) = b`.
    

#### **Innesti e cancellazioni (intuizione)**

- `inssottoalbero(a, a, T, U)` → **U** diventa **primo figlio** di `a`.
    
- `inssottoalbero(a, c, T, U)` → la radice di **U** diventa il **fratello** che **segue** `a` tra i figli dello **stesso padre**.
    
- `cancsottoalbero(a, T)` → elimina `a` e tutto ciò che sta sotto `a`.
    

---

### **9. Competenze operative da consolidare subito**

- **Riconoscere** su un disegno di albero: radice, padre, figli, fratelli, foglie, livelli.
    
- **Usare correttamente** i predicati: `alberovuoto`, `foglia`, `finefratelli`.
    
- **Navigare** l’albero con: `radice`, `padre`, `primofiglio`, `succfratello`.
    
- **Costruire/aggiornare** un albero con: `insradice`, `inssottoalbero`, `cancsottoalbero`.
    
- **Mantenere l’ordine** dei figli (albero **ordinato**) quando si inseriscono fratelli o figli.
    

---

> Metodo Feynman, takeaway: un albero è un **insieme di sottoalberi** appesi a una radice.  
> Se sai “parlare” con i suoi operatori (predicati, selezione, modifica), sai **navigarlo, costruirlo e trasformarlo** in modo rigoroso. Da qui, visite e algoritmi verranno naturali.