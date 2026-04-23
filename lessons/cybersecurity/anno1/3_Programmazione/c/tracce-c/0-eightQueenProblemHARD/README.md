# Eight Queens Problem — README

## Che cos’è
Il **Rompicapo delle Otto Regine** chiede di posizionare **8 regine** su una **scacchiera 8×8** in modo che **nessuna** minacci un’altra (quindi non condividono **riga**, **colonna** né **diagonale**).  
È il caso classico del più generale **Problema delle N Regine**.

---

## Un po’ di storia
- **Origini:** formulato nel **1848** da **Max Bezzel**, compositore di problemi di scacchi.  
- **Generalizzazione:** nel **1850**, **Franz Nauck** estese il problema al caso di una scacchiera *N×N*, introducendo il concetto di **N-Queens Problem**.  
- **Risultati noti:** per l’8×8 esistono **92 soluzioni**, di cui **12 fondamentali** (le altre derivano da rotazioni e riflessioni).  
- **Rilevanza storica:** è uno dei primi problemi che ha portato allo sviluppo pratico degli algoritmi di **backtracking** e alla nascita di concetti fondamentali per l’**Intelligenza Artificiale** e la **Ricerca Operativa**.

---

## Perché interessa alla programmazione e all’AI
Il problema delle N regine è un esempio classico di **Constraint Satisfaction Problem (CSP)**.  
È un laboratorio perfetto per esercitarsi con:

- **Ricorsione** e **backtracking**  
- **Euristiche di ricerca** (come l’approccio *least constrained*)  
- **Ottimizzazioni con bitmask**  
- **Parallelizzazione**  
- **Risolutori SAT/CSP**

In ambito moderno viene usato per:
- Benchmark di algoritmi di ricerca e ottimizzazione  
- Test di algoritmi di intelligenza artificiale  
- Dimostrazioni pratiche di programmazione dichiarativa e constraint programming  

---

## Specifica del problema (8×8)
**Obiettivo:** trovare tutte le configurazioni in cui 8 regine sono posizionate su una scacchiera 8×8 senza che nessuna possa attaccare un’altra.

**Vincoli:**
1. Nessuna regina sulla stessa **riga**
2. Nessuna regina sulla stessa **colonna**
3. Nessuna regina sulla stessa **diagonale**

![alt text](/c/tracce-c/0-eightQueenProblemHARD/src/0-imgs-and-pdf/figura1.png)

**Output:**  
Tutte le configurazioni valide, stampate come scacchiera con:
- **Q** per le regine  
- **-** per le caselle bianche  
- **\*** per le caselle nere  
- Bordo numerato **(1–8)** a sinistra e lettere **(a–h)** in basso  
- Bordo superiore e destro **0-based (0–7)** per riferirsi agli indici della **matrice interna**  

---

## Rappresentazione dello stato
Usiamo un **vettore di 8 interi** `regine[8]`, dove:
- L’indice `j` (0–7) rappresenta la **colonna**  
- Il valore `regine[j]` (0–7) rappresenta la **riga**

Esempio:

[7, 4, 0, 3, 6, 1, 5, 2]


Significa:
- In colonna 0 (a) → riga 7  
- In colonna 1 (b) → riga 4  
- ecc.

Ogni colonna contiene esattamente una regina, quindi non servono controlli sulle colonne.

---

## Verifica dei vincoli
Due regine in colonne `j` e `k` (con `j ≠ k`) si minacciano se:
- Stessa riga: `regine[j] == regine[k]`
- Stessa diagonale: `abs(regine[j] - regine[k]) == abs(j - k)`

---

## Algoritmo di backtracking
1. Posiziona ricorsivamente una regina nella **colonna `col`**.  
2. Per ogni riga possibile:
   - Verifica che non sia in conflitto con le colonne precedenti (`0..col-1`).  
   - Se valida, passa a `col + 1`.  
3. Se `col == 8`, hai trovato una **soluzione completa** → stampala.  
4. In caso contrario, **backtrack** (torna indietro e prova un’altra riga).

**Complessità:** esponenziale; un bound grezzo è `O(N!)`.  
Il backtracking riduce enormemente le combinazioni esplorando solo configurazioni valide parziali.

---

## Estensione a N regine

Il codice può essere facilmente generalizzato:

* `regine[N]`
* Backtracking ricorsivo con parametro `N`
* Controlli identici
* Possibili ottimizzazioni:

  * Uso di **bitmask** per righe e diagonali
  * **Parallelizzazione** delle prime colonne
  * Implementazioni **bitboard**

---

## Struttura del progetto

Il progetto è organizzato in sottocartelle numerate, ognuna dedicata a una fase logica di sviluppo e analisi del **problema delle otto regine**.
```
/0-eightQueenProblemHARD
│
├── src/
│   │
│   ├── 0-imgs-and-pdf/                  # Risorse grafiche e documentazione teorica
│   │   ├── figura1.png
│   │   ├── figura2.png
│   │   └── traccia-e-soluzione.pdf
│   │
│   ├── 1-stampaConfig/                  # Esercizio 1.1 — stampa di una configurazione
│   │   ├── README.md                    # Spiegazione della stampa e regole di visualizzazione
│   │   └── stampaConfig.c               # Codice C per la stampa della scacchiera formattata
│   │
│   ├── 2-verificaConfig/                # Esercizio 1.2 — verifica di validità delle configurazioni
│   │   ├── README.md                    # Spiegazione dei controlli e delle funzioni di verifica
│   │   └── verificaConfig.c             # Implementazione delle funzioni di controllo
│   │
│   ├── 3-euristica/                     # Esercizio 1.3 — backtracking, ricorsione, euristiche
│   │   ├── README.md                    # Spiegazione delle tecniche di backtracking e ottimizzazione
│   │   └── euristica.c                  # Implementazione degli algoritmi di ricerca e risoluzione
│   │
│   └── (eventuali cartelle aggiuntive)  # Per moduli successivi o versioni ottimizzate
│
└── README.md                            # Documento principale del progetto
```

---

### 🧠 Suggerimento di evoluzione futura
In una versione completa potresti aggiungere:

```

├── include/
│   ├── stampaConfig.h
│   ├── verificaConfig.h
│   └── euristica.h
│
└── tests/
├── configurazioni_di_esempio.txt
└── output_attesi.txt

```

in modo da rendere il progetto **modulare e testabile** anche in ambito universitario (o GitHub Classroom).

---

### Consultare i README.md delle varie implementazioni nella cartella /src per comprendere passo passo come la traccia è stata risolta. Bon voyage!
