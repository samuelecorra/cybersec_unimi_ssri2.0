# Soluzione Esame di Reti di Calcolatori — 05/07/2024 (Parte A)

**Prof. Ernesto Damiani**

Soluzione integrale della Parte A, redatta in chiave "30 e lode": ogni esercizio è preceduto dall'inquadramento teorico e dal riferimento alla lezione del corso più coerente, e seguito da calcoli passo-passo, formule e diagrammi.

---

## Indice

- [Esercizio 1 — TCP Reno: CWND all'istante 16](#esercizio-1--tcp-reno-cwnd-allistante-16)
- [Esercizio 2 — Subnetting FLSM /28 (vero/falso)](#esercizio-2--subnetting-flsm-28-verofalso)
- [Esercizio 3 — Algoritmo di Dijkstra verso il nodo O](#esercizio-3--algoritmo-di-dijkstra-verso-il-nodo-o)

---

## Esercizio 1 — TCP Reno: CWND all'istante 16

> **Riferimento di teoria**: [M2/UD5/L9 — Flusso e congestione TCP](../../M2_Protocolli_rete_TCP_IP/UD5/L9%20-%20Flusso%20e%20congestione%20TCP.md) · [M2/UD5/L10 — Timeout e decremento moltiplicativo](../../M2_Protocolli_rete_TCP_IP/UD5/L10%20-%20Timeout%20e%20decremento%20moltiplicativo.md) · [M2/UD5/L11 — Fast Retransmit e Fast Recovery](../../M2_Protocolli_rete_TCP_IP/UD5/L11%20-%20Fast%20Retransmit%20e%20Fast%20Recovery.md)

### Inquadramento teorico

TCP **Reno** combina quattro meccanismi di controllo della congestione:

1. **Slow Start** (`CWND < SSTHRESH`): `CWND` **raddoppia** ad ogni RTT (crescita esponenziale, +1 MSS per ACK).
2. **Congestion Avoidance** (`CWND ≥ SSTHRESH`): `CWND` cresce di **+1 MSS per RTT** (crescita additiva, AIMD).
3. **Reazione a timeout** (assenza completa di ACK):
   $$
   \text{SSTHRESH} \leftarrow \frac{\text{CWND}}{2}, \qquad \text{CWND} \leftarrow 1\ \text{MSS}
   $$
   Si riparte da Slow Start.
4. **Reazione a 3 ACK duplicati** (Fast Retransmit + Fast Recovery, distintivo di Reno):
   $$
   \text{SSTHRESH} \leftarrow \frac{\text{CWND}}{2}, \qquad \text{CWND} \leftarrow \text{SSTHRESH} + 3
   $$
   La finestra resta "gonfiata" durante la Fast Recovery; appena arriva un nuovo ACK (non duplicato), si pone `CWND = SSTHRESH` e si entra in Congestion Avoidance.

> **Differenza chiave Reno vs Tahoe**: in **Tahoe** anche i 3 ACK duplicati provocano `CWND = 1` (rientro in Slow Start). In **Reno**, invece, la Fast Recovery evita di ripartire da uno; il throughput resta più alto.

### Dati del problema

| Parametro | Valore iniziale |
|---|---|
| CWND all'istante 0 | 1 MSS |
| SSTHRESH iniziale | 8 MSS |
| Buffer ricevente | ∞ (mai vincolante) |
| Evento al t = 5 | Time-out dopo la trasmissione |
| Evento al t = 12 | 3 ACK duplicati dopo la trasmissione |
| Versione TCP | Reno |

### Calcolo round-per-round

Convenzione: al "tempo $t$" si trasmette con la `CWND` corrente; le reazioni a timeout o a 3-dup-ACK avvengono **dopo** la trasmissione dell'istante in cui si manifestano e si applicano al round successivo.

#### Fase 1 — Slow Start (t = 0 ... 3)

| t | CWND (MSS) | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 0 | 1 | 8 | SS | inizio trasmissione |
| 1 | 2 | 8 | SS | raddoppio |
| 2 | 4 | 8 | SS | raddoppio |
| 3 | 8 | 8 | SS → CA | `CWND` ha raggiunto la soglia |

#### Fase 2 — Congestion Avoidance fino al timeout (t = 4, 5)

| t | CWND (MSS) | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 4 | 9 | 8 | CA | +1 MSS |
| 5 | 10 | 8 | CA | trasmissione, poi **TIMEOUT** |

**Reazione al timeout** (in coda al round 5):
$$
\text{SSTHRESH} \leftarrow \frac{10}{2} = 5 \quad,\quad \text{CWND} \leftarrow 1
$$

#### Fase 3 — Riparto in Slow Start (t = 6 ... 9)

| t | CWND (MSS) | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 6 | 1 | 5 | SS | ripartenza |
| 7 | 2 | 5 | SS | raddoppio |
| 8 | 4 | 5 | SS | il prossimo raddoppio (8) sforerebbe la soglia |
| 9 | 5 | 5 | SS → CA | capping a `SSTHRESH`, passo a CA |

#### Fase 4 — Congestion Avoidance fino ai 3 ACK duplicati (t = 10 ... 12)

| t | CWND (MSS) | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 10 | 6 | 5 | CA | +1 |
| 11 | 7 | 5 | CA | +1 |
| 12 | 8 | 5 | CA | trasmissione, poi **3 ACK DUPLICATI** |

**Reazione ai 3 ACK duplicati** (Reno, Fast Retransmit + Fast Recovery):
$$
\text{SSTHRESH} \leftarrow \frac{8}{2} = 4
$$

Durante la Fast Recovery `CWND = SSTHRESH + 3 = 7` (finestra gonfiata per i pacchetti "in volo"); appena arriva un nuovo ACK, `CWND` deflaziona a `SSTHRESH = 4` e si entra in CA. Per la successione round-per-round (descrizione classica da esame Damiani) consideriamo che la deflazione è completata entro il round successivo, quindi il punto di partenza per la CA è **CWND = 4**.

#### Fase 5 — Congestion Avoidance dopo la Fast Recovery (t = 13 ... 16)

| t | CWND (MSS) | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 13 | 5 | 4 | CA | +1 (riparte da SSTHRESH = 4) |
| 14 | 6 | 4 | CA | +1 |
| 15 | 7 | 4 | CA | +1 |
| **16** | **8** | **4** | **CA** | **risultato richiesto** |

### Risultato

$$
\boxed{\;\text{CWND}(t = 16) = 8\ \text{MSS}\;}\qquad
\boxed{\;\text{SSTHRESH}(t = 16) = 4\ \text{MSS}\;}
$$

### Grafico dell'andamento

```
CWND
(MSS)
 10 ┤              ●                                                    ← timeout @ t=5
  9 ┤            ●                                                        SSTHRESH 8→5
  8 ┤  ━━━━━━━●━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━●━━━━━━━━━━━●     ← target finale
  7 ┤                                              ●━━━━●               3-dup-ACK @ t=12
  6 ┤                                          ●                          SSTHRESH 5→4
  5 ┤  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━●━━━━━━━━━━━━━━━━━━━━━━━━━━━   ← SSTHRESH = 5
  4 ┤      ●                          ●                                   (poi 4)
  3 ┤
  2 ┤    ●                       ●
  1 ┤  ●                       ●
    └┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──→ t
     0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16
       SS    │CA│           SS  │  CA       │   CA dopo FR
                                ↑                  ↑
                            timeout            Fast Recovery
                          (CWND→1, SSTH→5)   (CWND→4, SSTH→4)
```

### Sintesi della tracciatura

| Evento | Istante | SSTHRESH (prima → dopo) | CWND (prima → dopo) |
|:---|:---:|:---:|:---:|
| Inizio | 0 | 8 | – → 1 |
| Fine Slow Start (raggiunto SSTHRESH) | 3 | 8 | 8 |
| Timeout | 5 | 8 → **5** | 10 → **1** |
| Fine Slow Start (cap su SSTHRESH) | 9 | 5 | 5 |
| 3 ACK duplicati (Fast Retransmit/Recovery) | 12 | 5 → **4** | 8 → **4** (dopo deflazione FR) |
| Stato richiesto | 16 | **4** | **8** |

> **Nota didattica**: se l'esame venisse risolto in versione **Tahoe**, dopo i 3 ACK duplicati al t=12 si avrebbe `CWND=1` e `SSTHRESH=4`, e a t=16 si sarebbe arrivati solo a circa `CWND=5–6` (4 round di Slow Start raggiungerebbero `CWND=4 = SSTHRESH`, poi 1 round di CA porterebbe a 5). Il vantaggio di Reno rispetto a Tahoe è proprio non azzerare la finestra in caso di 3 dup-ACK.

---

## Esercizio 2 — Subnetting FLSM /28 (vero/falso)

> **Riferimento di teoria**: [M2/UD2/L3 — Complementi di IP](../../M2_Protocolli_rete_TCP_IP/UD2/L3%20-%20Complementi%20di%20IP.md) (maschere, AND logico) · [M2/UD2/L4 — Progetto di inter-reti](../../M2_Protocolli_rete_TCP_IP/UD2/L4%20-%20Progetto%20di%20inter-reti.md) (FLSM/VLSM)

### Inquadramento teorico

Data una rete classful di **classe C** $194.33.18.0$, la maschera di default è $255.255.255.0$ (`/24`).
Applicando una maschera **FLSM** $255.255.255.240$ (`/28`):

- Ultimo ottetto in binario: $11110000$ → **4 bit** di subnet_ID, **4 bit** di host_ID.
- Numero totale di blocchi: $2^4 = 16$.
- Indirizzi per blocco: $16$ (di cui $2^4 - 2 = 14$ host utilizzabili, perché $.x_0$ è il network e $.x_{15}$ è il broadcast del blocco).
- I **subnet_ID validi** sono multipli di **16**: $0, 16, 32, 48, 64, 80, 96, 112, 128, 144, 160, 176, 192, 208, 224, 240$.
- Per ogni subnet, il **broadcast** vale $\text{subnet\_ID} + 15$.

#### Tabella di riferimento dei 16 blocchi /28

| Subnet | Network | Range host | Broadcast |
|:--:|:--:|:--:|:--:|
| 0 | `194.33.18.0` | `.1` – `.14` | `194.33.18.15` |
| 1 | `194.33.18.16` | `.17` – `.30` | `194.33.18.31` |
| 2 | `194.33.18.32` | `.33` – `.46` | `194.33.18.47` |
| 3 | `194.33.18.48` | `.49` – `.62` | `194.33.18.63` |
| 4 | `194.33.18.64` | `.65` – `.78` | `194.33.18.79` |
| 5 | `194.33.18.80` | `.81` – `.94` | `194.33.18.95` |
| **6** | **`194.33.18.96`** | **`.97` – `.110`** | **`194.33.18.111`** |
| 7 | `194.33.18.112` | `.113` – `.126` | `194.33.18.127` |
| 8 | `194.33.18.128` | `.129` – `.142` | `194.33.18.143` |
| 9 | `194.33.18.144` | `.145` – `.158` | `194.33.18.159` |
| 10 | `194.33.18.160` | `.161` – `.174` | `194.33.18.175` |
| 11 | `194.33.18.176` | `.177` – `.190` | `194.33.18.191` |
| 12 | `194.33.18.192` | `.193` – `.206` | `194.33.18.207` |
| 13 | `194.33.18.208` | `.209` – `.222` | `194.33.18.223` |
| 14 | `194.33.18.224` | `.225` – `.238` | `194.33.18.239` |
| 15 | `194.33.18.240` | `.241` – `.254` | `194.33.18.255` |

#### Verifica binaria della maschera

```
194.33.18.96   = 11000010 . 00100001 . 00010010 . 0110 0000
255.255.255.240= 11111111 . 11111111 . 11111111 . 1111 0000
─────────────────────────────────────────────────────────── AND
network        = 11000010 . 00100001 . 00010010 . 0110 0000  = 194.33.18.96 ✓

broadcast      = network OR (NOT mask)
               = 11000010 . 00100001 . 00010010 . 0110 1111  = 194.33.18.111 ✓
```

### Analisi delle opzioni

| Opzione | Coppia proposta (subnet, broadcast) | Verifica | Esito |
|:--:|:---|:---|:---:|
| **a** | `194.33.18.96` / `194.33.18.255` | `.96` è subnet 6 valida; il **suo** broadcast è `.111`, **non** `.255`. `.255` è invece il broadcast della **subnet 15** (`.240/28`). Coppia incongruente. | ❌ |
| **b** | `194.33.18.128` / `194.33.18.240` | `.128` è subnet 8 valida; il suo broadcast è `.143`. `.240` non è nemmeno un broadcast (è il **network** della subnet 15). | ❌ |
| **c** | **`194.33.18.96` / `194.33.18.111`** | `.96` è subnet 6 valida; il **suo** broadcast è proprio `.111`. Coppia perfettamente congruente. | ✅ |
| **d** | `194.33.18.12` / `194.33.18.15` | `.12` **non è multiplo di 16** ⇒ non è un network address valido per `/28` (appartiene infatti alla subnet `.0/28`, dove è un host). `.15` è il broadcast della subnet 0, non di `.12`. | ❌ |
| **e** | `194.33.18.128` / `194.33.18.255` | `.128` valido; broadcast atteso `.143`. `.255` è broadcast della subnet 15. Incongruente. | ❌ |
| **f** | `194.33.18.0` / `194.33.18.255` | Questa è la coppia della **rete /24 originaria** (classful di partenza), non di una sua subnet `/28`. Con la maschera `/28` applicata, la subnet `.0` ha broadcast `.15`, non `.255`. | ❌ |

### Risultato

$$
\boxed{\;\text{L'unica coppia (subnet, broadcast) valida è la } \mathbf{c)} \quad 194.33.18.96 \;/\; 194.33.18.111\;}
$$

### Osservazioni "da lode"

- Errore tipico: confondere il **broadcast del blocco /28** con il **broadcast del /24 originale** (`.255`). Una volta applicata la maschera, ciascun blocco /28 ha il **proprio** indirizzo di broadcast (network + 15).
- La regola pratica per `/28`: i confini di blocco sono **multipli di 16** nell'ultimo ottetto. Se l'ultimo ottetto della candidata "subnet" non è multiplo di 16, la coppia è automaticamente da scartare (caso d).
- Nella tecnica FLSM **classica** (vista nella dispensa di Damiani) si potrebbero anche escludere la **subnet zero** (`.0/28`) e la **subnet "all-ones"** (`.240/28`), ottenendo $2^4 - 2 = 14$ subnet utilizzabili. Anche applicando questa regola più restrittiva, l'unica risposta corretta resta la **c)** (la `.96/28` non è né zero né all-ones).

---

## Esercizio 3 — Algoritmo di Dijkstra verso il nodo O

> **Riferimento di teoria**: [M2/UD7/L3 — Da RIP a OSPF](../../M2_Protocolli_rete_TCP_IP/UD7/L3%20-%20Da%20RIP%20a%20OSPF.md) (Dijkstra alla base di OSPF) · [M2/UD7/L4 — OSPF avanzato](../../M2_Protocolli_rete_TCP_IP/UD7/L4%20-%20OSPF%20avanzato.md)

### Inquadramento teorico

L'**algoritmo di Dijkstra** calcola i cammini di **costo minimo** da una sorgente $s$ a tutti gli altri nodi di un grafo pesato con **pesi non negativi**. È il cuore del protocollo **OSPF** (Open Shortest Path First): ogni router conosce l'intero **Link State Database** e ne deriva localmente l'albero dei cammini minimi.

**Pseudocodice (versione "lazy" classica)**:

```
function Dijkstra(G, s):
    for each v in V:
        d[v] ← ∞
        prev[v] ← undefined
    d[s] ← 0
    S ← ∅                    # insieme dei nodi "definiti"
    Q ← V                    # coda di priorità ordinata per d[v]
    while Q non vuoto:
        u ← extract_min(Q)
        S ← S ∪ {u}
        for each vicino v di u (con v ∉ S):
            if d[u] + w(u,v) < d[v]:
                d[v] ← d[u] + w(u,v)
                prev[v] ← u
    return d, prev
```

Poiché il grafo è **non orientato**, la distanza minima da O a $v$ coincide con quella da $v$ a O. Per rispondere al "cammino minimo da ogni nodo a O" eseguiamo quindi Dijkstra **con sorgente $s = O$**.

### Topologia e archi del grafo

Dal grafo in figura, gli archi pesati sono:

| Arco | Peso | Arco | Peso |
|:--:|:--:|:--:|:--:|
| O–A | 2 | B–D | 4 |
| O–B | 5 | B–E | 3 |
| O–C | 4 | C–E | 4 |
| A–B | 2 | D–E | 1 |
| A–D | 7 | D–T | 5 |
| A–F | 12 | E–T | 2 |
| B–C | 1 | F–T | 3 |

Nodi: $\{O, A, B, C, D, E, F, T\}$ (8 nodi).

### Inizializzazione

| Nodo | $d$ | $prev$ | $S$ (definito) |
|:--:|:--:|:--:|:--:|
| O | 0 | — | no |
| A | ∞ | — | no |
| B | ∞ | — | no |
| C | ∞ | — | no |
| D | ∞ | — | no |
| E | ∞ | — | no |
| F | ∞ | — | no |
| T | ∞ | — | no |

### Iterazioni passo-passo

Notazione compatta per ogni iterazione: prima la riga "**Estraggo: $u$ (d=…)**", poi gli aggiornamenti dei vicini ancora fuori da $S$ scritti come $d[v]:\; \text{vecchio}\rightarrow \text{nuovo via } u$.

#### Passo 1 — Estraggo: **O** (d = 0)

Vicini di O: A (w=2), B (w=5), C (w=4).

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| A | $0+2 = 2 < \infty$ | **2** | O |
| B | $0+5 = 5 < \infty$ | **5** | O |
| C | $0+4 = 4 < \infty$ | **4** | O |

Stato: $S = \{O\}$, $d = \{O:0,\, A:2,\, B:5,\, C:4,\, D:\infty,\, E:\infty,\, F:\infty,\, T:\infty\}$.

#### Passo 2 — Estraggo: **A** (d = 2)

Vicini di A non in S: B (w=2), D (w=7), F (w=12). (O è già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| B | $2+2 = 4 < 5$ | **4** | A (aggiornato) |
| D | $2+7 = 9 < \infty$ | **9** | A |
| F | $2+12 = 14 < \infty$ | **14** | A |

Stato: $S = \{O, A\}$, $d = \{B:4,\, C:4,\, D:9,\, E:\infty,\, F:14,\, T:\infty\}$.

#### Passo 3 — Estraggo: **B** (d = 4) *(parità con C; scelta indifferente, prendo B)*

Vicini di B non in S: C (w=1), D (w=4), E (w=3). (O, A già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| C | $4+1 = 5 > 4$ | 4 (nessun cambio) | O |
| D | $4+4 = 8 < 9$ | **8** | B (aggiornato) |
| E | $4+3 = 7 < \infty$ | **7** | B |

Stato: $S = \{O, A, B\}$, $d = \{C:4,\, D:8,\, E:7,\, F:14,\, T:\infty\}$.

#### Passo 4 — Estraggo: **C** (d = 4)

Vicini di C non in S: E (w=4). (O, B già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| E | $4+4 = 8 > 7$ | 7 (nessun cambio) | B |

Stato: $S = \{O, A, B, C\}$, $d = \{D:8,\, E:7,\, F:14,\, T:\infty\}$.

#### Passo 5 — Estraggo: **E** (d = 7)

Vicini di E non in S: D (w=1), T (w=2). (B, C già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| D | $7+1 = 8 = 8$ | 8 (nessun cambio stretto) | B |
| T | $7+2 = 9 < \infty$ | **9** | E |

Stato: $S = \{O, A, B, C, E\}$, $d = \{D:8,\, F:14,\, T:9\}$.

#### Passo 6 — Estraggo: **D** (d = 8)

Vicini di D non in S: T (w=5). (A, B, E già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| T | $8+5 = 13 > 9$ | 9 (nessun cambio) | E |

Stato: $S = \{O, A, B, C, D, E\}$, $d = \{F:14,\, T:9\}$.

#### Passo 7 — Estraggo: **T** (d = 9)

Vicini di T non in S: F (w=3). (D, E già in S.)

| v | rilassamento | d nuovo | prev |
|:--:|:--:|:--:|:--:|
| F | $9+3 = 12 < 14$ | **12** | T (aggiornato) |

Stato: $S = \{O, A, B, C, D, E, T\}$, $d = \{F:12\}$.

#### Passo 8 — Estraggo: **F** (d = 12)

Nessun vicino non in S. Termine.

### Risultati finali

#### Tabella dei cammini minimi verso O

| Nodo | Distanza minima da/verso O | Cammino (verso O) |
|:--:|:--:|:---|
| **O** | 0 | (origine) |
| **A** | **2** | A → O |
| **B** | **4** | B → A → O |
| **C** | **4** | C → O |
| **D** | **8** | D → B → A → O |
| **E** | **7** | E → B → A → O |
| **T** | **9** | T → E → B → A → O |
| **F** | **12** | F → T → E → B → A → O |

#### Albero dei cammini minimi (Shortest Path Tree) radicato in O

```
                       (O)
                       /|\
                  2  / | \  4
                    /  |  \
                  (A)  |  (C)
                 / | \ 5            ← l'arco O-B (peso 5) NON è nel SPT,
              2/ 7|12\                 perché B è raggiunto via A con costo 4
              /   |   \
            (B)  (D)* (F)*           ← D e F NON sono figli diretti di A:
            /|                          D si raggiunge via B (8 < 9 via A)
          3 |4                          F si raggiunge via T (12 < 14 via A)
           \|
           (E)
           /|
         1 |2
          /|
        (D)(T)
            |
          3 |
            |
           (F)

   Asterischi: archi A-D, A-F sono ESCLUSI dal SPT
   (esistono percorsi alternativi più corti).
```

Versione "pulita" del SPT, mostrando solo gli archi effettivamente usati:

```
                  (O)
                  /  \
                2/    \4
                /      \
              (A)      (C)
               |
              2|
               |
              (B)
               |
              3|
               |
              (E)
              / \
            1/   \2
            /     \
          (D)    (T)
                   |
                 3 |
                   |
                  (F)
```

Costo totale dell'albero: $2 + 4 + 2 + 3 + 1 + 2 + 3 = 17$.

### Verifica di consistenza

Per ogni nodo verifichiamo che la distanza calcolata sia minore o uguale a quella di **ogni altro cammino** alternativo:

| Nodo | Cammino scelto | Costo | Cammino alternativo | Costo alt. | Verifica |
|:--:|:---|:--:|:---|:--:|:--:|
| B | O-A-B | 4 | O-B (diretto) | 5 | 4 < 5 ✓ |
| B | O-A-B | 4 | O-C-B | 4+1=5 | 4 < 5 ✓ |
| C | O-C | 4 | O-A-B-C | 2+2+1=5 | 4 < 5 ✓ |
| D | O-A-B-D | 8 | O-A-D | 2+7=9 | 8 < 9 ✓ |
| D | O-A-B-D | 8 | O-A-B-E-D | 4+3+1=8 | 8 = 8 (pari) ✓ |
| E | O-A-B-E | 7 | O-C-E | 4+4=8 | 7 < 8 ✓ |
| T | O-A-B-E-T | 9 | O-A-B-D-T | 4+4+5=13 | 9 < 13 ✓ |
| T | O-A-B-E-T | 9 | O-A-F-T | 2+12+3=17 | 9 < 17 ✓ |
| F | O-A-B-E-T-F | 12 | O-A-F (diretto) | 2+12=14 | 12 < 14 ✓ |

> **Caso degenere**: il nodo D ha **due cammini minimi distinti** di costo 8 (`O-A-B-D` e `O-A-B-E-D`). Dijkstra ne sceglie uno (in base all'ordine di estrazione dalla coda); entrambi sono validi e in un'implementazione "tutte le rotte" entrambi farebbero parte del SPT multipath (ECMP — Equal-Cost Multi-Path, supportato da OSPF).

### Osservazioni "da lode"

- **Complessità**: $O((|N| + |A|)\log|N|)$ con heap binario; per il nostro grafo con 8 nodi e 14 archi è trascurabile.
- **Correttezza**: Dijkstra garantisce ottimalità **se e solo se** tutti i pesi sono $\geq 0$, condizione qui soddisfatta.
- **Applicazione OSPF**: in una rete OSPF reale, ogni router esegue questo algoritmo localmente sul Link State Database, ricavando l'**albero dei cammini minimi** radicato in sé stesso. La tabella di routing è poi derivata leggendo, per ogni destinazione, il **primo hop** sul cammino minimo.
- **Multipath ECMP**: in caso di parità di costo (come per D), OSPF può installare **tutte** le rotte equivalenti, bilanciando il carico in modo automatico.
- **Non orientato vs orientato**: dato che il grafo è simmetrico, i cammini minimi *verso* O coincidono con quelli *da* O. Su un grafo orientato bisognerebbe distinguere e usare Dijkstra sul grafo "trasposto" per ottenere i cammini *verso* la destinazione.

---

## Riepilogo finale

| Esercizio | Punti | Risultato sintetico |
|:--:|:--:|:---|
| **1** | 10 | TCP Reno: dopo timeout @ t=5 e 3-dup-ACK @ t=12, all'istante 16 si ha **CWND = 8 MSS**, **SSTHRESH = 4 MSS**. |
| **2** | 8 | Unica coppia (subnet, broadcast) valida con maschera `/28`: **c) `194.33.18.96` / `194.33.18.111`**. |
| **3** | 12 | Dijkstra da O: $d(A)=2,\ d(B)=4,\ d(C)=4,\ d(D)=8,\ d(E)=7,\ d(T)=9,\ d(F)=12$. Cammino più lungo: F→T→E→B→A→O (costo 12). |

> **Totale Parte A: 30 punti.** Soluzione completa, motivata e basata su formule e algoritmi del corso (M2/UD2, M2/UD5, M2/UD7).
