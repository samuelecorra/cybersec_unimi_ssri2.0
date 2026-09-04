# Soluzione della prima prova in itinere — 23 novembre 2024

Prova di **Algoritmi e Strutture Dati**, durata dichiarata: 1 ora e 30 minuti. La traccia comprende cinque domande di teoria e tre esercizi.

### **1. Domande di teoria**

#### **1.1. Visite di un albero binario**

> **Riferimento di teoria:** [M03 — Visite di alberi](../../M03_DS_Alberi/UD1/L2_Alberi_Algoritmi_visita.md).

In **preordine** si visita radice, sottoalbero sinistro, sottoalbero destro; in **inordine** sinistro, radice, destro; in **postordine** sinistro, destro, radice.

Consideriamo l'albero con queste relazioni: $A$ ha figli $B,C$; $B$ ha figli $D,E$; $C$ ha figli $F,G$; $D$ ha figli $H,I$; $H$ ha figlio sinistro $J$. Il cammino $A,B,D,H,J$ contiene quattro archi, quindi l'altezza è 4.

| Visita | Ordine |
|---|---|
| Preordine | $A,B,D,H,J,I,E,C,F,G$ |
| Inordine | $J,H,D,I,B,E,A,F,C,G$ |
| Postordine | $J,H,I,D,E,B,F,G,C,A$ |

#### **1.2. Elemento più frequente**

**Vettore ordinato.** Gli elementi uguali sono contigui: una scansione mantiene valore corrente, lunghezza della sequenza corrente e massimo. Tempo $\Theta(n)$, spazio $\Theta(1)$; la lettura di tutto l'input dà anche il limite inferiore $\Omega(n)$.

**Vettore non ordinato.** Nel modello basato sui confronti, si può ordinare e poi scandire: $\Theta(n\log n)$ tempo. Con una tabella hash delle frequenze si ottengono $\Theta(n)$ tempo atteso e $\Theta(n)$ spazio; il caso pessimo è $O(n^2)$ senza garanzie sulle collisioni. Un dizionario bilanciato garantisce $O(n\log n)$.

#### **1.3. Componente connessa**

> **Riferimento di teoria:** [M04 — Proprietà dei grafi](../../M04_DS_Reticolari/UD2/L3_Proprieta_grafo.md).

In un grafo non orientato, una componente connessa è un sottografo connesso **massimale**: ogni coppia di suoi vertici è collegata da una catena e non è possibile aggiungervi un altro vertice conservando la connessione. Le componenti partizionano $V$ e si trovano avviando DFS o BFS da ogni vertice non ancora visitato in $O(|V|+|E|)$.

#### **1.4. Tipo di dato coda**

> **Riferimento di teoria:** [M02 — Code](../../M02_DS_Lineari/UD3/L1/L1_Code.md).

La coda è FIFO: `INCODA(x,Q)` inserisce in fondo, `LEGGICODA(Q)` osserva l'elemento in testa e `FUORICODA(Q)` lo elimina; `CREACODA` crea la coda vuota e `CODAVUOTA` la riconosce. Lettura ed estrazione richiedono coda non vuota. Una lista con puntatori a testa e coda realizza inserimento ed estrazione in $\Theta(1)$.

#### **1.5. `FONDI` in MFSET**

> **Riferimento di teoria:** [M05 — MFSET](../../M05_DS_Orizzontali/UD1/L3_MFSET.md).

`FONDI(x,y,S)` sostituisce le due componenti disgiunte contenenti $x$ e $y$ con la loro unione. Nella foresta di alberi radicati si calcolano $r_x=TROVA(x)$ e $r_y=TROVA(y)$; se sono diversi, si rende figlia la radice dell'albero più piccolo di quella dell'albero più grande (**unione per dimensione**, oppure per rango). Con compressione dei cammini, una sequenza di operazioni costa tempo ammortizzato $O(\alpha(n))$ per operazione.

Esempio: dalle componenti $\{1,2\}$ di dimensione 2 e $\{3,4,5\}$ di dimensione 3, `FONDI(1,4,S)` collega la radice della prima alla radice della seconda e produce $\{1,2,3,4,5\}$.

### **2. Esercizio 1 — HeapSort decrescente**

**Traccia.** Ordinare $[9,29,22,7,96,87,3,11,86,75,32,63,0,74,46,53]$, mostrando costruzione e ripristini.

> **Riferimento di teoria:** [M03 — HeapSort](../../M03_DS_Alberi/UD2/L2_HeapSort_e_SelectionSort.md).

Per ottenere direttamente l'ordine decrescente si costruisce un min-heap. Applicando `restauraheap` bottom-up agli indici da 7 a 0 si ottiene:

| Indice | Heap in rappresentazione per livelli |
|---:|---|
| 7 | $[9,29,22,7,96,87,3,11,86,75,32,63,0,74,46,53]$ |
| 6 | $[9,29,22,7,96,87,3,11,86,75,32,63,0,74,46,53]$ |
| 5 | $[9,29,22,7,96,0,3,11,86,75,32,63,87,74,46,53]$ |
| 4 | $[9,29,22,7,32,0,3,11,86,75,96,63,87,74,46,53]$ |
| 3 | $[9,29,22,7,32,0,3,11,86,75,96,63,87,74,46,53]$ |
| 2 | $[9,29,0,7,32,22,3,11,86,75,96,63,87,74,46,53]$ |
| 1 | $[9,7,0,11,32,22,3,29,86,75,96,63,87,74,46,53]$ |
| 0 | $[0,7,3,11,32,22,9,29,86,75,96,63,87,74,46,53]$ |

Nella fase di ordinamento, la barra separa lo heap attivo dalla coda già ordinata.

| Minimo collocato | Stato dopo `restauraheap` |
|---:|---|
| 0 | $[3,7,9,11,32,22,46,29,86,75,96,63,87,74,53]\mid[0]$ |
| 3 | $[7,11,9,29,32,22,46,53,86,75,96,63,87,74]\mid[3,0]$ |
| 7 | $[9,11,22,29,32,63,46,53,86,75,96,74,87]\mid[7,3,0]$ |
| 9 | $[11,29,22,53,32,63,46,87,86,75,96,74]\mid[9,7,3,0]$ |
| 11 | $[22,29,46,53,32,63,74,87,86,75,96]\mid[11,9,7,3,0]$ |
| 22 | $[29,32,46,53,75,63,74,87,86,96]\mid[22,11,9,7,3,0]$ |
| 29 | $[32,53,46,86,75,63,74,87,96]\mid[29,22,11,9,7,3,0]$ |
| 32 | $[46,53,63,86,75,96,74,87]\mid[32,29,22,11,9,7,3,0]$ |
| 46 | $[53,75,63,86,87,96,74]\mid[46,32,29,22,11,9,7,3,0]$ |
| 53 | $[63,75,74,86,87,96]\mid[53,46,32,29,22,11,9,7,3,0]$ |
| 63 | $[74,75,96,86,87]\mid[63,53,46,32,29,22,11,9,7,3,0]$ |
| 74 | $[75,86,96,87]\mid[74,63,53,46,32,29,22,11,9,7,3,0]$ |
| 75 | $[86,87,96]\mid[75,74,63,53,46,32,29,22,11,9,7,3,0]$ |
| 86 | $[87,96]\mid[86,75,74,63,53,46,32,29,22,11,9,7,3,0]$ |
| 87 | $[96]\mid[87,86,75,74,63,53,46,32,29,22,11,9,7,3,0]$ |

Il risultato è

$$
\boxed{[96,87,86,75,74,63,53,46,32,29,22,11,9,7,3,0]}.
$$

La costruzione bottom-up costa $\Theta(n)$; le $n-1$ estrazioni costano $O(\log n)$ ciascuna. Il tempo totale è $\Theta(n\log n)$ e lo spazio ausiliario è $O(1)$.

### **3. Esercizio 2 — BST completo e cancellazioni**

**Traccia.** Etichettare un albero binario completo di 10 nodi come BST e cancellare $17,15,32$ nell'ordine.

Le chiavi ordinate sono $7,12,15,17,18,21,23,32,33,37$. Per ottenere un BST, si assegnano nell'ordine della visita simmetrica alle posizioni dell'albero completo. Ne risultano i livelli:

$$
[23];\quad[17,33];\quad[12,21,32,37];\quad[7,15,18].
$$

Nell'ultimo livello, 7 e 15 sono i figli di 12, mentre 18 è il figlio sinistro di 21. La visita inordine restituisce esattamente la sequenza crescente.

Per la cancellazione con due figli adottiamo il **successore**:

1. cancellando 17, lo sostituisce 18 e si elimina la precedente foglia 18 sotto 21;
2. 15 è una foglia e viene scollegata;
3. 32 è una foglia e viene scollegata.

Il BST finale è descritto dalle relazioni:

| Nodo | Figlio sinistro | Figlio destro |
|---:|---:|---:|
| 23 | 18 | 33 |
| 18 | 12 | 21 |
| 12 | 7 | — |
| 33 | — | 37 |
| 7, 21, 37 | — | — |

La visita inordine finale è $7,12,18,21,23,33,37$, quindi l'invariante BST è preservato.

### **4. Esercizio 3 — Rappresentazioni e DFS del grafo**

**Traccia.** Discutere matrice, liste e vettori di adiacenza e riportare l'ordine DFS di nodi e archi.

> **Riferimenti di teoria:** [M04 — Realizzazioni dei grafi](../../M04_DS_Reticolari/UD1/L2_Grafi_specifica_e_realizzazioni.md), [M04 — DFS](../../M04_DS_Reticolari/UD2/L1_Esplorazione_grafo.md).

Gli archi non orientati della figura sono

$$
\{\{1,2\},\{1,3\},\{1,4\},\{2,3\},\{3,4\},\{3,5\},\{3,6\},\{4,6\},\{5,6\}\}.
$$

La matrice di adiacenza è simmetrica e richiede $\Theta(|V|^2)$ spazio:

|   | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---:|---:|---:|---:|---:|---:|
| 1 | 0 | 1 | 1 | 1 | 0 | 0 |
| 2 | 1 | 0 | 1 | 0 | 0 | 0 |
| 3 | 1 | 1 | 0 | 1 | 1 | 1 |
| 4 | 1 | 0 | 1 | 0 | 0 | 1 |
| 5 | 0 | 0 | 1 | 0 | 0 | 1 |
| 6 | 0 | 0 | 1 | 1 | 1 | 0 |

Le liste ordinate sono: $1:(2,3,4)$, $2:(1,3)$, $3:(1,2,4,5,6)$, $4:(1,3,6)$, $5:(3,6)$, $6:(3,4,5)$. Richiedono $\Theta(|V|+|E|)$ spazio. In forma compatta a vettori, concatenando le liste:

$$
ADJ=[2,3,4,1,3,1,2,4,5,6,1,3,6,3,6,3,4,5],
$$

con vettore degli offset $START=[0,3,5,10,13,15,18]$.

La traccia non indica sorgente e ordine delle adiacenze; assumiamo partenza da 1 e liste crescenti. La DFS scopre

$$
\boxed{1,2,3,4,6,5}
$$

e gli archi dell'albero DFS sono $(1,2),(2,3),(3,4),(4,6),(6,5)$. Considerando ogni arco fisico una sola volta, l'ordine di primo esame è

$$
(1,2),(2,3),(3,1),(3,4),(4,1),(4,6),(6,3),(6,5),(5,3).
$$

### **5. Traccia originale**

[PDF della prima prova in itinere del 23 novembre 2024](2024-11-23-ppi.pdf)
