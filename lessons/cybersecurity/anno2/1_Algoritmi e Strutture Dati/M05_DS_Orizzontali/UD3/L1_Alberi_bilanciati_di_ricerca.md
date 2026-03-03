
## **Lezione 1: Alberi bilanciati di ricerca**

---

### **1. Introduzione**

Le precedenti realizzazioni del tipo di dato **insieme** — come il vettore booleano, le liste, il vettore ordinato e le tabelle hash — privilegiano l’efficienza di alcuni operatori rispetto ad altri.  
In questa lezione vengono introdotti metodi alternativi che permettono di ottenere una **maggiore efficienza complessiva** per gli operatori fondamentali:

- `appartiene`
- `inserisci`
- `cancella`
- `min` e `cancellamin`

L’obiettivo è costruire strutture che consentano di eseguire tutte queste operazioni in modo bilanciato e veloce.

---

### **2. Albero binario di ricerca**

Gli elementi di un insieme $A$ possono essere memorizzati nei nodi di un **albero binario $B$**, detto **albero binario di ricerca**, che rispetta le seguenti proprietà:

- **P1:** per ogni nodo $u$, tutti gli elementi contenuti nel **sottoalbero sinistro** di $u$ sono **minori** dell’elemento memorizzato in $u$.  
- **P2:** per ogni nodo $u$, tutti gli elementi contenuti nel **sottoalbero destro** di $u$ sono **maggiori** dell’elemento memorizzato in $u$.

La **visita simmetrica** di un albero binario di ricerca restituisce sempre l’insieme degli elementi in **ordine crescente**.

Esempio di visita simmetrica:

```
1, 5, 7, 8, 13, 17, 21, 27, 33, 37, 40, 42
```

---

### **3. Ricerca di un elemento**

Le proprietà $P1$ e $P2$ servono per **agevolare la ricerca** di un elemento nell’albero.

Il principio è analogo a quello della **ricerca binaria**:

1. Si confronta la chiave cercata con l’elemento del nodo corrente.  
2. Se non coincidono, si scende nel **sottoalbero sinistro** se la chiave è minore, oppure nel **sottoalbero destro** se la chiave è maggiore.

La ricerca continua fino a trovare la chiave o raggiungere una foglia.

L’operatore `appartiene`, implementato in questo modo, ha complessità proporzionale all’altezza dell’albero $h$, quindi:

$$
O(h)
$$

---

### **4. Operatori fondamentali**

#### **Operatore `min`**

L’operatore `min` individua il nodo con valore minimo seguendo il **cammino sinistro** a partire dalla radice fino all’ultima foglia disponibile a sinistra.  
Anche in questo caso la complessità è:

$$
O(h)
$$

---

#### **Operatore `inserisci`**

Per inserire una nuova chiave:

1. Si effettua prima una ricerca per determinare **se la chiave è già presente**.  
2. Se non è presente, la ricerca indica **in quale sottoalbero** inserire il nuovo nodo.  
3. L’inserimento avviene come **figlio sinistro o destro** del nodo individuato.

Esempio:  
inserendo la chiave `9`, la ricerca arriva fino al nodo `13`, e l’inserimento avviene come figlio sinistro di `13`.

Anche `inserisci` ha complessità $O(h)$.

---

#### **Operatori `cancella` e `cancellamin`**

Per realizzare `cancella` e `cancellamin` si esegue prima una ricerca per localizzare l’elemento da rimuovere.  
Si distinguono tre casi principali:

1. **Nodo senza figli:** si elimina semplicemente il nodo.  
2. **Nodo con un solo figlio:** si rimuove il nodo, collegando direttamente il figlio al padre.  
3. **Nodo con due figli:** si sostituisce l’elemento da cancellare $x$ con il **successore** $y$, cioè il **minimo elemento** del sottoalbero destro del nodo.  
   Dopo lo scambio, si elimina il nodo contenente $x$ (ora foglia o con un solo figlio).

Anche questi operatori hanno complessità:

$$
O(h)
$$

---

### **5. Esempio: cancellazione di un nodo**

Si consideri l’eliminazione del nodo contenente il valore `33`.  
Il successore $y$ è il minimo del sottoalbero destro (ad esempio `37`).  
Scambiando $x = 33$ con $y = 37$ e poi eliminando $x$, l’albero rimane correttamente ordinato.

Tutti gli operatori precedenti (`appartiene`, `min`, `inserisci`, `cancella`, `cancellamin`) hanno complessità $O(h)$.

Tuttavia, l’altezza massima $h$ **non è necessariamente limitata da $\log_2 n$**, ma può arrivare fino a $n$ nel caso peggiore.  
Infatti, dopo una serie di inserimenti e cancellazioni, l’albero può diventare **sbilanciato** e degenerare in una struttura lineare simile a una lista.

---

### **6. Problema del bilanciamento**

Quando un albero binario di ricerca cresce in modo sbilanciato, la sua efficienza decade:  
invece di $O(\log n)$, gli operatori tornano ad avere complessità $O(n)$.

Per questo motivo è necessario introdurre **tecniche di bilanciamento**, cioè operazioni che modificano la struttura dell’albero mantenendolo quanto più possibile equilibrato.

---

### **7. Esempio di bilanciamento**

Supponiamo di inserire un nuovo nodo `9` che sbilancia l’albero.  
Per ristabilire l’equilibrio si può effettuare una **rotazione** su un nodo (ad esempio sul nodo `8`), ottenendo un albero più bilanciato.

Le rotazioni sono operazioni locali che permettono di mantenere basse le altezze relative dei sottoalberi.

---

### **8. Sintesi finale**

- È stato introdotto il **concetto di albero binario di ricerca**, utile per ottimizzare gli operatori fondamentali del tipo di dato insieme.  
- Tutti gli operatori (`appartiene`, `inserisci`, `cancella`, `min`, `cancellamin`) hanno complessità $O(h)$.  
- Si è evidenziato il **problema del bilanciamento** che può far degenerare l’albero in una lista.  
- Sono state introdotte le **rotazioni** come operazioni base per mantenere l’equilibrio.  
- Le soluzioni strutturate al problema del bilanciamento portano alla definizione di **alberi 2-3** e **B-alberi**.