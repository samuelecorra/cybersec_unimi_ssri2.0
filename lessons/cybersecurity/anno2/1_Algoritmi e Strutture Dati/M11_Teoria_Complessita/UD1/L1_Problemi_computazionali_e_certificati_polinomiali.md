Siamo finalmente arrivati all’ultimo modulo del corso — il punto di arrivo naturale di tutto il percorso sugli **algoritmi e le tecniche di progettazione**: la **Teoria della Complessità**.

Dopo aver imparato _come costruire algoritmi_ (con Greedy, Backtrack, Divide et Impera, Programmazione Dinamica, ecc.), ora il nostro obiettivo è **capire quanto “difficile” sia un problema** e **quanto efficientemente possa essere risolto**.

La Teoria della Complessità si occupa quindi di **classificare i problemi computazionali** in base alla quantità di risorse necessarie per risolverli — tipicamente **tempo** e **spazio**.  
È lo strumento che permette di distinguere tra:

- **Problemi facili (polinomiali)**, risolvibili in tempo ragionevole da un algoritmo deterministico, e
    
- **Problemi difficili (NP, NP-completi o NP-duri)**, per i quali non è noto alcun algoritmo efficiente e la cui risoluzione cresce in modo esplosivo con la dimensione dell’input.
    

Il modulo si propone di:

1. **Comprendere la distinzione tra problemi facili e difficili**, introducendo le principali **classi di complessità** (P, NP, NP-completi).
    
2. **Apprendere gli strumenti teorici** per classificare i problemi, come il **concetto di riduzione polinomiale** e il **teorema di Cook-Levin**.
    
3. Capire **perché alcuni problemi non potranno mai essere risolti efficientemente**, fornendo le basi teoriche che spiegano i limiti della computazione.
    

In sintesi, questo modulo rappresenta il passaggio **dalla progettazione alla consapevolezza**:  
non solo come costruire algoritmi, ma **quando e perché** certi problemi _non possono essere risolti meglio_.  
È il momento di chiudere il cerchio — comprendendo il confine tra ciò che è **computabile** e ciò che rimane **inaccessibile alla computazione efficiente**.


---

## **Lezione 1: Problemi computazionali e certificati polinomiali**

### **1. Introduzione**

In questa lezione vengono introdotti i **fondamenti della teoria della complessità**, con l’obiettivo di comprendere **quando un problema è facile o difficile** da risolvere.  
Come caso di studio iniziale viene presentato il **problema del Domino Limitato**, utile per visualizzare il concetto di complessità di calcolo e di esplosione combinatoria.

---

### **2. Il problema del Domino Limitato**

#### **Definizione**

Dati:

- un intero positivo $n$
    
- un insieme finito $D$ di $m$ **tipi di tessere orientate**
    

si vuole stabilire se sia possibile **ricoprire un’area quadrata di lato $n$** con copie delle tessere di $D$, rispettando le seguenti condizioni:

1. Nessuna tessera può essere ruotata.
    
2. Una specifica tessera $d \in D$ deve occupare la posizione in basso a sinistra.
    
3. Due tessere adiacenti devono avere i **lati dello stesso colore**.
    

Ogni **tessera orientata** è un quadrato di lato unitario diviso in quattro triangoli colorati; ogni quarto ha un colore che rappresenta il vincolo di compatibilità.

---

#### **Esempio**

Considerando $m = n = 3$, il numero di possibili configurazioni è enorme.  
Un algoritmo che provi **tutte le combinazioni possibili** dovrebbe generare:

$$  
m^{(n^2 - 1)}  
$$

combinazioni diverse.

Questa crescita è **superpolinomiale**, il che rende il problema **computazionalmente intrattabile** per valori anche moderati di $n$.

---

### **3. Problemi difficili**

Il **Domino Limitato** è un esempio di **problema difficile**, per il quale **non è noto un algoritmo migliore** di quello che prova **tutte le soluzioni possibili**.  
Questo tipo di problema appartiene a una **classe di problemi computazionalmente correlati**, per i quali non si conosce un metodo di risoluzione efficiente (cioè in tempo polinomiale).

Esempi tipici di questa classe includono:

---

#### **a) Problema della Colorazione (versione decisionale)**

Dato un **grafo non orientato** e un intero $k$, si chiede:

> È possibile colorare i nodi del grafo usando **al più $k$ colori**, in modo che nodi adiacenti abbiano colori diversi?

---

#### **b) Problema della Cricca (versione decisionale)**

Dato un grafo non orientato e un intero $k$, si chiede:

> Esiste un sottoinsieme di **almeno $k$ nodi** tutti **mutuamente adiacenti**?

---

#### **c) Problema del Commesso Viaggiatore (TSP – versione decisionale)**

Date:

- $n$ città e le distanze tra ciascuna coppia,
    
- un valore intero $k$,
    

si chiede:

> Esiste un percorso che partendo da una città, visita **tutte le altre una sola volta** e ritorna alla città di partenza, percorrendo una **distanza complessiva non superiore a $k$**?

---

### **4. Osservazioni generali**

Si può osservare che:

- Tutti i problemi elencati appartengono **alla stessa classe di difficoltà computazionale**.
    
- Ognuno di essi può essere risolto solo in **tempo superpolinomiale**, e **nessuno** è noto per essere risolubile in tempo polinomiale.
    
- Se si trovasse un algoritmo polinomiale per **uno solo** di questi problemi, automaticamente **tutti gli altri** sarebbero risolvibili in tempo polinomiale.
    
- Tuttavia, **non è mai stato dimostrato** che un tale algoritmo esista.
    

Questa osservazione rappresenta il nucleo della **questione P vs NP**, uno dei problemi aperti più celebri dell’informatica teorica.

---

### **5. Problemi decisionali**

I problemi sopra sono stati formulati in **forma decisionale**, cioè con una **risposta SÌ o NO**, a seconda che l’input soddisfi o meno una certa proprietà.  
Questo tipo di formulazione è fondamentale per la **classificazione teorica dei problemi**, in quanto semplifica il confronto tra le diverse classi di complessità.

---

### **6. Certificato polinomiale**

Un tratto comune a tutti i problemi “difficili” è che, anche se **trovare** una soluzione è complicato, **verificare** se una soluzione proposta è corretta è invece **facile**.  
Questa capacità di verifica rapida viene formalizzata dal concetto di **certificato polinomiale**.

#### **Definizione**

Un **certificato polinomiale** è un algoritmo che, data una **soluzione candidata**, ne **verifica la correttezza in tempo polinomiale**, stabilendo se essa costituisce effettivamente una soluzione valida.

---

#### **Esempio – Certificato per il TSP**

Nel problema del Commesso Viaggiatore (versione decisionale):

- una soluzione è rappresentata da **una sequenza di $n$ nodi** che forma un ciclo.
    
- In **tempo lineare** è possibile verificare che:
    
    1. il percorso inizia e termina nello stesso nodo,
        
    2. ogni nodo è visitato **esattamente una volta**,
        
    3. la **somma delle distanze percorse** non supera $k$.
        

Questa verifica costituisce un **certificato polinomiale**.

---

### **7. In sintesi**

- Sono stati introdotti **alcuni problemi computazionali difficili**, come il **Domino Limitato**, la **Colorazione**, la **Cricca** e il **Commesso Viaggiatore**.
    
- Si è mostrato che questi problemi appartengono a una **stessa classe di difficoltà**, legata all’impossibilità (finora) di risolverli in tempo polinomiale.
    
- È stato introdotto il concetto di **certificato polinomiale**, che permette di verificare rapidamente la correttezza di una soluzione proposta, anche quando trovarla è molto complesso.
    

Questa lezione segna l’inizio dello studio sistematico della **complessità computazionale**, che verrà approfondito nelle prossime lezioni attraverso gli strumenti formali di **riduzione**, **non determinismo** e **classi di complessità**.